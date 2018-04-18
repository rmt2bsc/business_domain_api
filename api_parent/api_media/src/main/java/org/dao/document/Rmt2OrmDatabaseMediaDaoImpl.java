package org.dao.document;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.dto.ContentDto;

import com.SystemException;
import com.util.RMT2File;

/**
 * The database media content implementation of the {@link ContentDao} interface
 * to manage meida document that lives in the <i>content</i> table.
 * <p>
 * <b>NOTE</b><br>
 * It is best to use vanilla JDBC calls instead of the ORM api due to
 * proprietary Sybase dialect.
 * 
 * @author Roy Terrell
 * 
 */
class Rmt2OrmDatabaseMediaDaoImpl extends AbstractRmt2OrmContentDaoImpl {

    private static Logger logger = Logger.getLogger(Rmt2OrmDatabaseMediaDaoImpl.class);

    /**
     * Create a Rmt2OrmSybaseEmbeddedMediaDaoImpl object
     */
    public Rmt2OrmDatabaseMediaDaoImpl() {
        super();
        return;
    }

    /**
     * Create a Rmt2OrmSybaseEmbeddedMediaDaoImpl object
     * 
     * @param appName
     *            application name
     */
    public Rmt2OrmDatabaseMediaDaoImpl(String appName) {
        super(appName);
        return;
    }


    /**
     * Saves the binary or text data, that represents the actual content of a
     * media file, to the content table tageting the column, <i>image_data</i>.
     * 
     * @param mediaRec
     *            an instance of {@link ContentDto} containing the meta data to add
     *            to the database including the large binary and/or text data.
     * @return the total number of bytes written to the database.
     * @throws ContentDaoException
     */
    @Override
    public int saveContent(ContentDto mediaRec) throws ContentDaoException {
        // Obtain next primary key value for content table.
        Connection con = (Connection) this.client.getConnection();
        if (con == null) {
            this.msg = "JDBC connection object is not initailized";
            throw new ContentDaoException(this.msg);
        }
        // Lastly, attempt to update the record with the long binary
        // or long text MIME data using straight JDBC call.
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery("select image_data from content where content_id = " + mediaRec.getContentId());
            if (rs != null && rs.next()) {
                byte[] data = (byte[]) mediaRec.getImageData();
                rs.updateBytes("image_data", data);
                rs.updateRow();
                return data.length;
            }
            return 0;
        } catch (SQLException | SystemException e) {
            this.msg = "Error occurred saving media binary data to the content table [contentId=" + mediaRec.getContentId() + "]";
            throw new ContentDaoException(this.msg, e);
        }
        finally {
            try {
                if (stmt != null) {
                    stmt.close();    
                }
                if (rs != null) {
                    rs.close();    
                }
            }
            catch (SQLException e) {
                throw new ContentDaoException("An error occurred attempting to close either SQL Statement or ResultSet objects");
            }
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
    public ContentDto fetchContentAsFile(int contentId) throws ContentDaoException {
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
            byte binaryData[] = mime.getImageData();
            RMT2File.outputFile(binaryData, path);
            // Create File instance and assign to the imageData property.
            File file = new File(path);
            // mime.setImageData(file);
            // mime.setFilepath(path);
            return mime;
        }
        else {
            this.msg = "Media content is not available to be retrieved as a File instance";
            throw new ContentDaoException(this.msg);
        }
    }
}
