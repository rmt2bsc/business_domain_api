package org.dao.document;

import java.io.File;
import java.io.FileInputStream;

import org.apache.log4j.Logger;
import org.dto.ContentDto;

import com.util.RMT2Base64Encoder;
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
     * @param uid
     *            a integer value representing the database primary key of the
     *            multi media document that is to be fetched.
     * @return an instance of {@link ContentDto} or null no data is found.
     * @throws ContentDaoException
     */
    public ContentDto fetchContent(int uid) throws ContentDaoException {
        // Try to input file to Content instance.
        ContentDto mime = null;
        try {
            mime = this.fetchContentAsFile(uid);
            if (mime == null) {
                return null;
            }
        } catch (Exception e) {
            throw new ContentDaoException(e);
        }

        File file = (File) mime.getImageData();
        if (file == null) {
            mime.setImageData(null);
        }
        else {
            try {
                FileInputStream fis = new FileInputStream(file);
                byte binaryData[] = RMT2File.getStreamByteData(fis);
                String encodedImg = RMT2Base64Encoder.encode(binaryData);
                mime.setImageData(encodedImg);
            } catch (Exception e) {
                this.msg = "Error fetching external media file, " + file.getAbsolutePath();
                throw new ContentDaoException(this.msg, e);
            }
        }
        return mime;
    }

    /**
     * Returns a {@link ContentDto} object where the image data property is
     * represented as an {@link File} object.
     * <p>
     * In this case, the media record's image data is not stored directly in the
     * database table, <i>content</i>. Instead the media record points to a
     * media file somewhere in the file system via the properties <i>file
     * path</i> and <i>file name</i>.
     * 
     * @param contentId
     *            a integer value representing the unique id or primary key of
     *            the multi media document that is to be fetched.
     * @return An instance of {@link ContentDto}
     * @throws ContentDaoException
     */
    public ContentDto fetchContentAsFile(int contentId) throws ContentDaoException {
        ContentDto mime = super.fetchContent(contentId);
        if (mime == null) {
            return null;
        }

        // Try to input file to Content instance.
        File file = new File(mime.getFilepath() + mime.getFilename());
        int fileVerifyCode = RMT2File.verifyFile(file);
        if (fileVerifyCode != RMT2File.FILE_IO_EXIST) {
            this.msg = "Unable to locate external media file, " + file.getAbsolutePath();
            throw new ContentDaoException(this.msg);
        }
        
        // TODO: need to obtain the file contents as byte array
        mime.setImageData(file);
        return mime;
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
     * @return the total number of bytes saved to disk.
     */
    @Override
    public int saveContent(ContentDto mediaRec) {
        Thread t = new Thread(new MediaFilePeristenceThread(mediaRec));
        t.start();
        return mediaRec.getSize();
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
    
}
