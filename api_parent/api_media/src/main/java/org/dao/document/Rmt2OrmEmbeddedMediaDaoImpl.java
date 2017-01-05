package org.dao.document;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;
import org.dao.mapping.orm.rmt2.Content;
import org.dto.ContentDto;
import org.dto.MimeTypeDto;
import org.dto.adapter.orm.Rmt2MediaDtoFactory;

import com.util.RMT2Date;
import com.util.RMT2File;
import com.util.RMT2String;
import com.util.UserTimestamp;

/**
 * The embedded media content implementation of the {@link ContentDao} interface
 * to manage meida document that lives in the <i>content</i> table.
 * <p>
 * <b>NOTE</b><br>
 * It is best to use vanilla JDBC calls instead of the ORM api due to
 * proprietary Sybase dialect.
 * 
 * @author Roy Terrell
 * 
 */
class Rmt2OrmEmbeddedMediaDaoImpl extends AbstractRmt2OrmContentDaoImpl
        implements ContentDao {

    private static Logger logger = Logger
            .getLogger(Rmt2OrmEmbeddedMediaDaoImpl.class);

    /**
     * Create a Rmt2OrmSybaseEmbeddedMediaDaoImpl object
     */
    public Rmt2OrmEmbeddedMediaDaoImpl() {
        super();
        return;
    }

    /**
     * Create a Rmt2OrmSybaseEmbeddedMediaDaoImpl object
     * 
     * @param appName
     *            application name
     */
    public Rmt2OrmEmbeddedMediaDaoImpl(String appName) {
        super(appName);
        return;
    }

    /**
     * Creates a row in the <i>content</i> database table where the large binary
     * and/or text data streams are stored as a column of byte data.
     * <p>
     * The content can be text character sets and various binary data sets.
     * 
     * @param mediaRec
     *            an instance of {@link ContentDto} containing the data to add
     *            including the large binary and/or text data.
     * @return int the new content id just added.
     * @throws ContentDaoException
     *             <ul>
     *             <li>if the filename is absent</li> <li>if the image data and
     *             text data are both null</li> <li>if the image data and text
     *             data are not mutually exclusive</li> <li>when the image or
     *             text data is found to be of the incorrect format based on the
     *             extension for the file name</li>
     *             </ul>
     */
    public int addContent(ContentDto mediaRec) throws ContentDaoException {
        logger.info("Begin to calculate and/or assign data to content DTO for update");
        byte[] binaryData = RMT2File.getFileContentsAsBytes(mediaRec
                .getFilepath() + mediaRec.getFilename());
        mediaRec.setImageData(binaryData);

        logger.info("Validate Content record");
        this.validate(mediaRec);
        // binaryData = RMT2Base64Decoder.decodeToBytes(mediaRec.getImageData()
        // .toString());
        mediaRec.setSize(binaryData.length);
        mediaRec.setUpdateUserId(this.getDaoUser());

        // Persist content record and binary data
        try {
            this.client.beginTrans();
            int newContentId = this.saveMetaData(mediaRec);
            int bytesWritten = this.saveContent(newContentId, binaryData);

            // // Obtain next primary key value for content table.
            // Connection con = (Connection) this.client.getConnection();
            // if (con == null) {
            // this.msg = "JDBC connection object is not initailized";
            // throw new ContentDaoException(this.msg);
            // }
            // // Lastly, attempt to update the record with the long binary
            // // or long text MIME data using straight JDBC call.
            // Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
            // ResultSet.CONCUR_UPDATABLE);
            // ResultSet rs =
            // stmt.executeQuery("select image_data from content where content_id = "
            // + newContentId);
            // if (rs != null && rs.next()) {
            // rs.updateBytes("image_data", binaryData);
            // rs.updateRow();
            // }
            //
            // this.client.commitTrans();
            // mediaRec.setContentId(newContentId);

            this.client.commitTrans();

            // Print log message
            StringBuffer buf = new StringBuffer();
            buf.append(mediaRec.getFilepath());
            buf.append(" was saved as content id, ");
            buf.append(newContentId);
            buf.append(", and ");
            buf.append(bytesWritten);
            buf.append(" bytes were saved to the content table as binay data");
            logger.info(buf);

            return newContentId;
        } catch (Exception e) {
            this.client.rollbackTrans();
            logger.error(e.getMessage());
            throw new ContentDaoException(e);
        }
    }

    /**
     * Adds a record to the <i>content</i> table without including the image
     * data (binary or text).
     * <p>
     * The filepath and filename properties of <i>mediaRec</i> should point the
     * the directory location and the file name where the media content will be
     * persisted, respectively.
     * 
     * @param mediaRec
     *            An instance of {@link ContentDto}
     * @return The primary key of the row just added to the <i>content</i>
     *         table.
     * @throws ContentDaoException
     *             general database access error
     */
    protected int saveMetaData(ContentDto mediaRec) throws ContentDaoException {
        mediaRec.setUpdateUserId(this.getDaoUser());

        Content rec = new Content();
        rec.setMimeTypeId(mediaRec.getMimeTypeId());
        rec.setFilepath(mediaRec.getFilepath());
        rec.setFilename(mediaRec.getFilename());
        rec.setSize(mediaRec.getSize());
        rec.setImageData(null);
        rec.setTextData(null);
        rec.setAppCode(null);
        rec.setModuleCode(null);

        int newContentId = 0;
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(mediaRec
                    .getUpdateUserId());
            rec.setDateCreated(ut.getDateCreated());
            rec.setUserId(ut.getLoginId());
            rec.setNull(Content.PROP_PROJECTID);
            newContentId = this.client.insertRow(rec, true);
            mediaRec.setContentId(newContentId);
            return newContentId;
        } catch (Exception e) {
            this.msg = "Error adding media record without embedded content";
            throw new ContentDaoException(this.msg, e);
        }
    }

    /**
     * Saves the binary or text data, that represents the actual content of a
     * media file, to the content table tageting the column, <i>image_data</i>.
     * 
     * @param contentId
     *            the primary key of the content table row to update
     * @param binaryData
     *            a byte array representing the media content to save
     * @return the total number of bytes written to the database.
     * @throws ContentDaoException
     */
    protected int saveContent(int contentId, byte binaryData[])
            throws ContentDaoException {
        // Obtain next primary key value for content table.
        Connection con = (Connection) this.client.getConnection();
        if (con == null) {
            this.msg = "JDBC connection object is not initailized";
            throw new ContentDaoException(this.msg);
        }
        // Lastly, attempt to update the record with the long binary
        // or long text MIME data using straight JDBC call.
        Statement stmt;
        try {
            stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt
                    .executeQuery("select image_data from content where content_id = "
                            + contentId);
            if (rs != null && rs.next()) {
                rs.updateBytes("image_data", binaryData);
                rs.updateRow();
            }
            return binaryData.length;
        } catch (SQLException e) {
            this.msg = "Error occurred saving media binary data to the content table";
            throw new ContentDaoException(this.msg, e);
        }
    }

    /**
     * Fetches the media content record from the database table, <i>content</i>,
     * to where the actual image data is represented as an instance of
     * {@link File}.
     * <p>
     * The <i>contentId</i> parameter is used as the primary key to fetch the
     * content record. The actual content is returned in an instance of
     * <i>ContentDto</i> that is mapped to the <i>imageData</i> property as an
     * instance of {@link File}. The file path and name is obtained from the
     * properties ob the content record retrieved.
     * 
     * @param contentId
     *            the primary key used to locate the record.
     * @return an isntance of {@link ContentDto} or null if no data is found.
     * @throws ContentDaoException
     */
    public ContentDto fetchContentAsFile(int contentId)
            throws ContentDaoException {
        ContentDto mime = super.fetchContent(contentId);
        if (mime == null) {
            return mime;
        }

        String path = null;
        if (mime.getImageData() != null) {
            // build file name and path
            String outBoundDir = this.config.getOutboundDir();
            java.util.Date cd = new java.util.Date();
            String fileName = cd.getTime() + "_" + mime.getFilename();

            // Save media contents as a file in the outbound directory
            path = outBoundDir + fileName;
            byte binaryData[] = (byte[]) mime.getImageData();
            RMT2File.outputFile(binaryData, path);
            // Create File instance and assign to the imageData property.
            File file = new File(path);
            mime.setImageData(file);
            mime.setFilepath(path);
            return mime;
        }
        else {
            this.msg = "Media content is not available to be retrieved as a File instance";
            throw new ContentDaoException(this.msg);
        }

        // String sql = "select xp_write_file(\'" + outBoundDir +
        // "\' || filename, image_data) from content where content_id = " +
        // contentId;
        // this.client.executeSql(sql);

    }

    /**
     * Verifies that the media content record contains valid data..
     * <p>
     * It is required that the file name and the image data is not null, and the
     * image data must be a Base64 encoded String. Also, validates whether the
     * file extension is registered in the database. If the MIME type exists,
     * then the MIME type id is assigned to the mimeTypeId property of
     * <i>dto</i>.
     * <p>
     * By default the filepath property will be assigned the value of filename
     * property.
     * 
     * @param dto
     *            an instance of {@link ContentDto} which will be validated.
     * @return an instance of {@link File} representing the media document in
     *         the file system.
     * @throws ContentDaoException
     *             when either file name, file extension or image data proerties
     *             are not present, or general database error while attempting
     *             to obtain MIME type data based on file extension.
     */
    protected File validate(ContentDto dto) throws ContentDaoException {
        String inFileName = dto.getFilename();
        if (inFileName == null
                || inFileName.equals(RMT2String.spaces(inFileName.length()))) {
            this.msg = "The filename property is required when persisting embedded content";
            throw new ContentValidationDaoException(this.msg);
        }
        if (dto.getImageData() == null) {
            this.msg = "The data (binary or text) representing the media content, that is to be persisted as an embeddeded object, cannot be null";
            throw new ContentValidationDaoException(this.msg);
        }
        // if (!(dto.getImageData() instanceof String)) {
        // this.msg =
        // "The data (binary or text) representing the media content, that is to be persisted as an embedded objet, must be a base 64 encoded String";
        // throw new ContentValidationDaoException(this.msg);
        // }

        dto.setFilepath(dto.getFilename());

        logger.info("Validate file extension");
        String ext = RMT2File.getFileExt(dto.getFilename());
        if (ext == null) {
            this.msg = "File name is required to have an extension for the purposes of identifying/validationg the MIME type";
            throw new ContentValidationDaoException(this.msg);
        }

        // Get mime type id of file name.
        MimeTypeDto mtCriteria = Rmt2MediaDtoFactory.getMimeTypeInstance(null);
        mtCriteria.setFileExt(ext);
        List<MimeTypeDto> list = this.fetchMimeType(mtCriteria);
        if (list == null) {
            this.msg = "File, "
                    + dto.getFilename()
                    + ", was not persisted due to file extension is not registered in the MIME database";
            throw new ContentValidationDaoException(this.msg);
        }
        MimeTypeDto mt = list.get(0);
        dto.setMimeTypeId(mt.getMimeTypeId());
        logger.info("Passed Sysbase Adaptive Server Anywhere media file validations");
        return null;
    }

}
