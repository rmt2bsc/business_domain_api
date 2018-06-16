package org.dao.document;

import org.apache.log4j.Logger;
import org.dto.ContentDto;

import com.InvalidDataException;
import com.NotFoundException;
import com.RMT2RuntimeException;
import com.SystemException;
import com.util.RMT2File;

/**
 * File system database implementation of the {@link ContentDao} interface to
 * manage large binary or text based media document which exists as an external
 * file.
 * 
 * @author Roy Terrell
 * 
 */
class Rmt2OrmExternalFileMediaDaoImpl extends AbstractRmt2OrmContentDaoImpl {

    private static Logger logger = Logger.getLogger(Rmt2OrmExternalFileMediaDaoImpl.class);

    /**
     * 
     */
    public Rmt2OrmExternalFileMediaDaoImpl() {
        super();
        return;
    }

    /**
     * 
     * @param appName
     */
    public Rmt2OrmExternalFileMediaDaoImpl(String appName) {
        super(appName);
        return;
    }


    /**
     * Fetches a row from the <i>content</i> table by its primary key where the
     * associated media data exists as an external file.
     * <p>
     * The results are returned as a {@link ContentDto} object which the value
     * of the <i>imageData</i> property is an instance of {@link File}.
     * 
     * @param contentId
     *            a integer value representing the database primary key of the
     *            multi media document that is to be fetched.
     * @return an instance of {@link ContentDto} or null when <i>contentId</i>'s file is not found in the file system.
     * @throws ContentDaoException
     */
    public ContentDto fetchContent(int contentId) throws ContentDaoException {
        // Try to input file to Content instance.
        ContentDto mediaContent = super.fetchContent(contentId);
        if (mediaContent == null) {
            return null;
        }
        this.fetchContentFromFile(mediaContent);
        
        return mediaContent;
    }

    /**
     * Retrieves the media's image data from the file system and populates
     * <i>mime</i>'s imageData property with the fetched results.
     * <p>
     * In this case, the media record's image data is not stored directly in the
     * database table, <i>content</i>. Instead the media record points to a
     * media file somewhere in the file system via the properties <i>file
     * path</i> and <i>file name</i>.
     * 
     * @param mediaContent
     *            An instance of {@link ContentDto} which uses its
     *            {@link ContentDto#getFilepath()} and
     *            {@link ContentDto#getFilename()} methods to locate and
     *            retrieve the contents from the file system.
     * @return An instance of {@link ContentDto}
     * @throws ContentDaoException
     *             Error obtaining the contents from the file system
     * @throws InvalidDataException
     *             <i>mime</i> is null
     */
    protected void fetchContentFromFile(ContentDto mediaContent) throws ContentDaoException {
        if (mediaContent == null) {
            throw new InvalidDataException("Content DTO cannot be null when fetching image data from a file");
        }

        // Get file contents as a byte array and assign to the Content instance.
        try {
            String filePath = mediaContent.getFilepath() + mediaContent.getFilename();
            byte fileContents[] = RMT2File.getFileContentsAsBytes(filePath);
            mediaContent.setImageData(fileContents);
        }
        catch (NotFoundException | SystemException e) {
            this.msg = "Unable to fetch media content from the file system for content id, " + mediaContent.getContentId();
            throw new ContentDaoException(this.msg, e);
        }
    }


    /**
     * Saves the media content data as file in the archive directory location
     * delcared in the <i>MimeConfig_XXX.properties</i> file.
     * <p>
     * The data contained in the <i>mediaRec</i>'s property, imageData, is
     * required to be a Base 64 encoded String so that it may be decoded as byte
     * array.
     * 
     * @param mediaRec
     *            an instance of {@link ContentDto} containing the image data to
     *            be saved.
     * @return the new content id of the media added to the content table.
     * @throws ContentDaoException
     */
    @Override
    public int saveContent(ContentDto mediaRec) {
        // Do not include image data in the content table
        byte[] imageDataHold = mediaRec.getImageData();
        mediaRec.setImageData(null);
        int newContentId;
        try {
            newContentId = super.saveContent(mediaRec);    
        }
        catch (Exception e) {
            this.msg = "Unable to persist media content as an external file due to saveConent operation failed";
            throw new ContentDaoException(this.msg, e);
        }
        
        // Create the external file
        mediaRec.setImageData(imageDataHold);
        Thread t = new Thread(new MediaFilePeristenceThread(mediaRec));
        t.start();
        mediaRec.getSize();
        
        return newContentId;
    }

    /**
     * Asynchronously saves the media file so that the user does not
     * have to wait.
     * 
     * @author Roy Terrell
     * 
     */
    private class MediaFilePeristenceThread implements Runnable {

        private ContentDto rec;

        private MediaFilePeristenceThread(ContentDto dto) {
            this.rec = dto;
            return;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {
            String msg = null;
            // Try to create a File object based on the properties of the
            // content DTO.
            String path = null;
            int bytesWritten = 0;
            if (this.rec.getImageData() != null) {
                path = this.rec.getFilepath() + this.rec.getFilename();
                byte binaryData[] = (byte[]) rec.getImageData(); 
                bytesWritten = RMT2File.outputFile(binaryData, path);
                this.rec.setSize(bytesWritten);
                msg = "External media file, "
                        + this.rec.getFilename()
                        + ", was persisted successfully in the following location: "
                        + this.rec.getFilepath();
                logger.info(msg);
                msg = "External media file, "
                        + this.rec.getFilename()
                        + ", is associated with a record in the content table which its content id is: "
                        + this.rec.getContentId();
                logger.info(msg);
                msg = "The total number of bytes written for external media file, "
                        + path + ", was " + bytesWritten;
                logger.info(msg);
            }
            else {
                msg = "Media content could not be saved as an external file, "
                        + path
                        + ", due to image data propertye is invalid.  Content Id of the erroneous record: "
                        + this.rec.getContentId();
                logger.error(msg);
                throw new ContentDaoException();
            }
        }
    }

    /**
     * Delete multi media document from the database and from the file system.
     * 
     * @param contentId
     *            a integer value representing the unique id or primary key of
     *            the multi media document that is to be deleted.
     * @return The total number of rows deleted from the content table and file system.
     * @throws ContentDaoException
     */
    @Override
    public int deleteContent(int contentId) throws ContentDaoException {
        logger.info("Preparing to delete database entry and external file related to content id, "
                        + contentId);
        // Fetch the original record from the content table. Do not call this
        // fetchContent method to prevent picking up the the image data from the
        // file system.
        ContentDto origRec = super.fetchContent(contentId);
        // Now delete the original record.
        int rows = super.deleteContent(contentId);
        
        // Attempt to delete associated external file, if it exists.
        // As a safeguard to deleting the wrong record, delete only those
        // records not conatining any image data.
        String path = null;
        if (origRec.getImageData() == null) {
            path = origRec.getFilepath() + origRec.getFilename();
            try {
                RMT2File.deleteFile(path);
                logger.info("Media document was deleted successfully as an external file [" + path + "]");
                rows++;
            }
            catch (RMT2RuntimeException e) {
                logger.warn("Unable to locate media document in file system for deleltion [File=" + path + "]");
            }
        }
        else {
            logger.warn("Skip deleting media content file, " + path
                    + ", due to the record contains image data from the content table");
        }
        return rows;
    }
    
}
