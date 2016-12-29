package org.dao.content;

import org.apache.log4j.Logger;
import org.dao.mapping.orm.rmt2.Content;
import org.dto.ContentDto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.Date;
import java.util.List;

import com.RMT2Constants;
import com.util.RMT2File;
import com.util.RMT2String;


/**
 * Generic implementation of the {@link ContentDao} interface to manage large text based 
 * media content.
 * <p>
 * <b>NOTE</b><br>
 * It is best to use vanilla JDBC calls instead of the ORM api due to proprietary Sybase 
 * dialect. 
 * 
 * @author Roy Terrell
 *
 */
class TextFileContentDaoImpl extends AbstractRmt2OrmContentDaoImpl implements ContentDao {
    private static final long serialVersionUID = -5438562667692863170L;

    private static Logger logger = Logger.getLogger(TextFileContentDaoImpl.class);

    
    public TextFileContentDaoImpl() {
	super();
	return;
    }
    

    /**
     * Adds multi media content to the database.  The content can be text character 
     * sets and various binary data sets.
     * 
     * @param content
     *          an instance of {@link ContentDto}
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
    public int addContent(ContentDto content) throws ContentDaoException {
	super.validate(content);

	Content rec = new Content();
	rec.setContentId(content.getContentId());
	rec.setMimeTypeId(content.getMimeTypeId());
	rec.setFilepath(content.getFilepath());
	rec.setFilename(content.getFilename());
	rec.setSize(content.getSize());
	rec.setUserId(content.getUpdateUserId());
	rec.setDateCreated(new Date());
	
	// Set app code and module code from the first two values of 
	// the file name that are separated by the character, "_".
	List<String> tokens = RMT2String.getTokens(rec.getFilename(), "_");
	if (tokens != null && tokens.size() > 2) {
	    rec.setAppCode(tokens.get(0));
	    rec.setModuleCode(tokens.get(1));
	}

	// Get mime type id of file name.
	String ext = RMT2File.getFileExt(rec.getFilename());

	// Verify whether MIME data should be text or binary based on the extension of the file name. 
	boolean dataIsText = this.isPlainTextMime(ext);
	if (!dataIsText) {
	    this.msg = "The multi media content is of binary content based on the file extension of the data file";
	    TextFileContentDaoImpl.logger.error(this.msg);
	    throw new ContentValidationDaoException(this.msg);
	}

	// Reset image_data and text_data properties to null and allow separate block of logic 
	// to persist the data to the database later on.
	rec.setImageData(null);
	rec.setTextData(null);

	// Validate if file exist
	File file = new File(rec.getFilepath() + rec.getFilename());
	FileInputStream fis;
	String txtData = null;
	try {
	    fis = new FileInputStream(file);
	    txtData = RMT2File.getStreamStringData(fis);
	}
	catch (FileNotFoundException e) {
	    throw new ContentValidationDaoException("Input file, " + rec.getFilepath() + rec.getFilename() + ", is not found");
	}

	this.client.beginTrans();
	try {
	    // First, insert the record without binary MIME data. 
	    int uid = this.client.insertRow(rec, true);
	    // Lastly, attempt to update the record with the long binary 
	    // or long text MIME data using straight JDBC call.
	    Connection con = (Connection) this.client.getConnection();
	    if (con == null) {
		this.msg = "JDBC connection object is not initailized";
		throw new ContentDaoException(this.msg);
	    }
	    Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
	    ResultSet rs = stmt.executeQuery("select text_data from content where content_id = " + uid);
	    if (rs != null && rs.next()) {
		rs.updateString("text_data", txtData);
		rs.updateRow();
	    }
	    this.client.commitTrans();
	    return uid;
	}
	catch (Exception e) {
	    this.client.rollbackTrans();
	    throw new ContentDaoException(e);
	}
    }

    /**
     * Determine if MIME extension is truely plain text.
     * 
     * @param fileExt
     * @return
     * @throws ContentDaoException
     */
    private boolean isPlainTextMime(String fileExt) throws ContentDaoException {
	StringBuffer sql = new StringBuffer();
	sql.append("SELECT a.file_ext file_ext, a.media_type media_type  ");
	sql.append("FROM MIME_TYPES a  ");
	sql.append("where a.file_ext in (select c.file_ext from mime_types c where c.media_type like \'text/%\' and c.media_type = a.media_type)   ");
	sql.append("and a.file_ext = \'");
	sql.append(fileExt);
	sql.append("\'  ");
	sql.append("order by a.media_type  ");

	try {
	    ResultSet rs = this.client.executeSql(sql.toString());
	    if (rs != null && rs.next()) {
		// RTF file format is an exception.
		if (fileExt.equalsIgnoreCase(".rtf")) {
		    return false;
		}
		return true;
	    }
	    return false;
	}
	catch (Exception e) {
	    throw new ContentDaoException(e);
	}
    }


    /* (non-Javadoc)
     * @see com.api.db.MimeContentApi#fetchContentAsFile(int)
     */
    public ContentDto fetchContentAsFile(int uid) throws ContentDaoException {
	throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

}
