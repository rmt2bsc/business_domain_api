package org.dao.content;

import org.apache.log4j.Logger;

import org.dto.ContentDto;
import org.dto.MimeTypeDto;
import org.dto.adapter.orm.Rmt2MediaDtoFactory;

import java.io.File;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.List;

import com.util.RMT2File;


/**
 * Sybase SQL Anywhere implementation of the {@link ContentDao} interface to manage meida 
 * content that lives in the <i>content</i> table.
 * <p>
 * <b>NOTE</b><br>
 * It is best to use vanilla JDBC calls instead of the ORM api due to proprietary Sybase 
 * dialect.
 * 
 * @author Roy Terrell
 *
 */
class Rmt2OrmSybaseEmbeddedMediaDaoImpl extends AbstractRmt2OrmContentDaoImpl implements ContentDao {
    private static final long serialVersionUID = -5438562667692863170L;

    private static Logger logger = Logger.getLogger(Rmt2OrmSybaseEmbeddedMediaDaoImpl.class);
    

    /**
     * Create a Rmt2OrmSybaseEmbeddedMediaDaoImpl object
     */
    public Rmt2OrmSybaseEmbeddedMediaDaoImpl() {
	super();
	return;
    }

    /**
     * Creates a row in the <i>content</i> database table where the large binary and/or text 
     * data streams are embedded as the actual media content.  
     * <p>
     * The content can be text character sets and various binary data sets.
     * 
     * @param mediaRec
     *          an instance of {@link ContentDto} containing the data to add including the 
     *          large binary and/or text data.
     * @return int
     *           the new content id just added.
     * @throws ContentDaoException
     *           <ul>
     *             <li>if the filename is absent</li>
     *             <li>if the image data and text data are both null</li>
     *             <li>if the image data and text data are not mutually exclusive</li>
     *             <li>when the image or text data is found to be of the incorrect format based on the extension for the file name</li>
     *           </ul>
     */
    public int addContent(ContentDto mediaRec) throws ContentDaoException {
	File mediaFile = null;
	logger.info("Validate Content record");
	try {
	    mediaFile = this.validate(mediaRec);
	}
	catch (Throwable e) {
	    e.printStackTrace();
	    logger.error(e.getMessage());
	    throw new ContentDaoException(e.getMessage());
	}
	logger.info("Begin to calculate and/or assign data to content DTO for update");
	mediaRec.setSize((int) mediaFile.length());
	mediaRec.setUpdateUserId(this.getDaoUser());

	try {
	    // Treat input path as if we are running a local process.
	    String inPath = mediaRec.getFilepath() + "\\" + mediaRec.getFilename();

	    // If not local process, copy file to database server to satisfy the 
	    // requirement that the file must be local to the DB Server
	    if (!this.config.isArchiveLocal()) {
		String outPath = config.getOutboundDir() + mediaRec.getFilename();
		RMT2File.copyFileWithOverwrite(inPath, outPath);
		// Change input path to equal remote output path to satisfy ASA DB Server requirements.
		logger.info("Image file was copied from the remote location, " + inPath + ", to " + outPath + " successfully");
		inPath = outPath;
	    }

	    // Obtain next primary key value for content table.
	    Connection con = (Connection) this.client.getConnection();
	    if (con == null) {
		this.msg = "JDBC connection object is not initailized";
		throw new ContentDaoException(this.msg);
	    }
	    Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery("select max(content_id) cur_key_val from content");
	    int nextKeyVal = 0;
	    if (rs != null && rs.next()) {
		nextKeyVal = rs.getInt("cur_key_val");
		nextKeyVal++;
	    }
	    else {
		this.msg = "Unable to add row for content table.  Error occured obtaining the next primary key value for the content table";
		logger.error(this.msg);
		throw new ContentDaoException(this.msg);
	    }

	    // Insert the media record which includes the binary data.
	    int uid = 0;
	    StringBuffer query = new StringBuffer();
	    query.append("insert into content (content_id, mime_type_id, app_code, module_code, filepath, filename, size, user_id, image_data) values (");
	    query.append(nextKeyVal);
	    query.append(", ");
	    query.append(mediaRec.getMimeTypeId());
	    query.append(", \'");
	    query.append(mediaRec.getAppCode());
	    query.append("\', \'");
	    query.append(mediaRec.getModuleCode());
	    query.append("\', \'");
	    query.append(mediaRec.getFilepath());
	    query.append("\', \'");
	    query.append(mediaRec.getFilename());
	    query.append("\', ");
	    query.append(mediaRec.getSize());
	    query.append(", \'");
	    query.append(mediaRec.getUpdateUserId());
	    query.append("\', ");
	    query.append("(select dbo.xp_read_file(\'" + inPath + "\'))) ");

	    this.client.beginTrans();
	    stmt = con.createStatement();
	    logger.info(query.toString());
	    int rc = stmt.executeUpdate(query.toString(), Statement.RETURN_GENERATED_KEYS);
	    if (rc == 1) {
		rs = stmt.getGeneratedKeys();
		if (rs.next()) {
		    String temp = rs.getString(1);
		    uid = Integer.parseInt(temp);
		}
		else {
		    uid = -1;
		}
	    }
	    this.client.commitTrans();
	    mediaRec.setContentId(uid);
	    return uid;
	}
	catch (Exception e) {
	    this.client.rollbackTrans();
	    logger.error(e.getMessage());
	    throw new ContentDaoException(e);
	}
    }

    /**
     * Fetches the media content record from the database table, <i>content</i>, to where the 
     * actual image data is represented as an instance of {@link File}.   
     * <p>
     * The <i>contentId</i> parameter is used as the primary key to fetch the content record.  
     * The actual content is returned in an instance of <i>ContentDto</i> that is mapped 
     * to the <i>imageData</i> property as an instance of {@link File}.  The file path 
     * and name is obtained from the properties ob the content record retrieved.
     * 
     * @param contentId
     *          the primary key used to locate the record.
     * @return
     *    an isntance of {@link ContentDto} or null if no data is found.
     * @throws ContentDaoException
     */
    public ContentDto fetchContentAsFile(int contentId) throws ContentDaoException {
	ContentDto mime = super.fetchContent(contentId);
	if (mime == null) {
	    return mime;
	}

	// Query the media content and save to a file in the "outbound directory".
	String outBoundDir = this.config.getOutboundDir();
	String sql = "select xp_write_file(\'" + outBoundDir + "\' || filename, image_data) from content where content_id = " + contentId;
	this.client.executeSql(sql);

	// Assign File instance to the image data property.
	File file = new File(outBoundDir + mime.getFilename());
	mime.setImageData(file);
	mime.setFilepath(outBoundDir);
	return mime;
    }

    /**
     * Verifies that the file extension is present and the media file indeed exists based on 
     * its stated file path.
     * <p>
     * Also, validates whether the file extension is registered in the database.  If the MIME 
     * type exists, then the MIME type id is assigned to the mimeTypeId property of <i>dto</i>.
     * 
     * @param dto
     *          an instance of {@link ContentDto} which will be validated.
     * @return
     *     an instance of {@link File} representing the media content in the file system.          
     * @throws ContentDaoException
     *           when either the file name, file path or file extension is not present, 
     *           or general database error while attempting to obtain MIME type data based on 
     *           file extension.           
     */
    protected File validate(ContentDto dto) throws ContentDaoException {
	// Perform super class validations
	super.validate(dto);

	logger.info("Validate file extension");
	String ext = RMT2File.getFileExt(dto.getFilename());
	if (ext == null) {
	    this.msg = "File name is required to have an extension for the purposes of identifying/validationg the MIME type";
	    throw new ContentValidationDaoException(this.msg);
	}
	
	// Try to create a File object based on the properties of the content DTO.
	File file = null;
	String inPath = dto.getFilepath() + "\\" + dto.getFilename();
	try {
	    file = new File(inPath);
	}
	catch (NullPointerException e) {
	    this.msg = "Error creating File instance.  The content DTO's path and file properties combined to result as a null value";
	    throw new ContentValidationDaoException(this.msg);
	}
	// Verify that the media file actually exists.
	if (RMT2File.verifyFile(file) != RMT2File.FILE_IO_EXIST) {
	    this.msg = "Media file, " + inPath + ", does not exist in the file system";
	    throw new ContentValidationDaoException(this.msg);
	}

	// Get mime type id of file name.
	MimeTypeDto mtCriteria = Rmt2MediaDtoFactory.getMimeTypeInstance(null);
	mtCriteria.setFileExt(ext);
	List<MimeTypeDto> list = this.fetchMimeType(mtCriteria);
	if (list == null) {
	    this.msg = "File, " + dto.getFilename() + ", was not persisted due to file extension is not registered in the MIME database";
	    throw new ContentValidationDaoException(this.msg);
	}
	MimeTypeDto mt = list.get(0);
	dto.setMimeTypeId(mt.getMimeTypeId());
	logger.info("Passed Sysbase Adaptive Server Anywhere media file validations");
	return file;
    }
    
}
