package org.dao.document;

import java.io.File;

import org.dto.ContentDto;

import com.api.util.RMT2File;

/**
 * A Sybase Adaptive Server Anywhere media content implementation of the
 * {@link ContentDao} interface to manage meida document that lives in the
 * <i>content</i> table.
 * <p>
 * <b>NOTE</b><br>
 * It is best to use vanilla JDBC calls instead of the ORM api due to
 * proprietary Sybase dialect.
 * 
 * @author Roy Terrell
 * 
 */
class Rmt2SybaseMediaDaoImpl extends AbstractRmt2OrmContentDaoImpl {
    
    /**
     * Create a Rmt2OrmSybaseEmbeddedMediaDaoImpl object
     */
    public Rmt2SybaseMediaDaoImpl() {
        super();
        return;
    }

    /**
     * Create a Rmt2OrmSybaseEmbeddedMediaDaoImpl object
     * 
     * @param appName
     *            application name
     */
    public Rmt2SybaseMediaDaoImpl(String appName) {
        super(appName);
        return;
    }

    /**
     * Retrieves multi media document by its primary key value.
     * <p>
     * It is best to use vanilla JDBC calls instead of the ORM api to obtain
     * data via the mime services bean classes due to incompatibility issues
     * involving the binary data returned for the column, <i>image_data</i>. The
     * implementor is free to override this method for the purpose of providing
     * optimum performance in obtaining the large binary or large text column
     * data.
     * 
     * @param contentId
     *            a integer value representing the database primary key of the
     *            multi media document that is to be fetched.
     * @return Object an instance of {@link ContentDto} or null if no data is
     *         found.
     * @throws ContentDaoException
     *             NotFoundException
     */
    @Override
    public ContentDto fetchContent(int contentId) throws ContentDaoException {
        ContentDto results = super.fetchContent(contentId);

        // Obtain binary data for media object
        if (results != null) {
            this.getBinaryContentFromDatabase(results);
        }
        return results;
    }

    private void getBinaryContentFromDatabase(ContentDto media) {
        String waFileName = RMT2File.createUserSessionWorkArea() + File.separator + media.getFilename();
        String sql = "select xp_write_file(\'" + waFileName + "\', image_data) from content where content_id = "
                + media.getContentId();
        try {
            // Manually retrieve image data from DB and save in the user's work
            // area.
            this.client.executeSql(sql);

            // Retrieve the file contents from the user's workd area and
            // load into memory.
            byte[] binaryData = RMT2File.getFileContentsAsBytes(waFileName);
            media.setImageData(binaryData);
        } catch (Exception e) {
            throw new ContentDaoException("Error fetching media binary data from the content table", e);
        } finally {
            RMT2File.deleteFile(waFileName);
        }
    }

    /**
     * Adds a record to the <i>content</i> table including the image data.
     * 
     * @param mediaRec
     *            An instance of {@link ContentDto}
     * @return The primary key of the row just added to the <i>content</i>
     *         table.
     * @throws ContentDaoException
     *             general database access error
     */
    @Override
    public int saveContent(ContentDto mediaRec) throws ContentDaoException {
        // Point media file path to the user's temporary work area. 
        mediaRec.setFilepath(RMT2File.createUserSessionWorkArea());

        int newContentId = 0;
        try {
            // Add media meta data first
            newContentId = super.saveContent(mediaRec);

            // Update new media record with the actual binary content to the database
            this.saveBinaryContentToDatabase(mediaRec);
            return newContentId;
        } catch (Exception e) {
            this.msg = "Error adding media meta data and content to the content table";
            throw new ContentDaoException(this.msg, e);
        }
    }

    private int saveBinaryContentToDatabase(ContentDto mediaRec) {
        // Save file content to the user's temporary work area.
        String waFileName = mediaRec.getFilepath() + File.separator + mediaRec.getFilename();
        RMT2File.outputFile(mediaRec.getImageData(), waFileName);

        // Build SQL update statement to add file to the DB.
        StringBuilder query = new StringBuilder();
        query.append("update content set image_data = ");
        query.append("(select dbo.xp_read_file(\'");
        query.append(waFileName);
        query.append("\')) where content_id = ");
        query.append(mediaRec.getContentId());
        String sql = query.toString();
        try {
            int rc = this.client.executeUpdate(sql);
            return rc;
        } finally {
            // remove file from user's work area
            RMT2File.deleteFile(waFileName);
        }
    }
}
