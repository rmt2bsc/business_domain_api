package org.dao.document;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.dao.mapping.orm.rmt2.Content;
import org.dto.ContentDto;
import org.dto.MimeTypeDto;
import org.dto.adapter.orm.Rmt2MediaDtoFactory;

import com.util.RMT2Base64Decoder;
import com.util.RMT2Base64Encoder;
import com.util.RMT2Date;
import com.util.RMT2File;
import com.util.RMT2String;
import com.util.UserTimestamp;

/**
 * File system database implementation of the {@link ContentDao} interface to
 * manage large binary or text based media document which exists as an external
 * file.
 * 
 * @author Roy Terrell
 * 
 */
class Rmt2OrmExternalFileMediaDaoImpl extends AbstractRmt2OrmContentDaoImpl
        implements ContentDao {

    private static Logger logger = Logger
            .getLogger(Rmt2OrmExternalFileMediaDaoImpl.class);

    public Rmt2OrmExternalFileMediaDaoImpl() {
        super();
        return;
    }

    public Rmt2OrmExternalFileMediaDaoImpl(String appName) {
        super(appName);
        return;
    }

    /**
     * Creates media document as a combination of a database row and an external
     * file.
     * <p>
     * An entry is made to the <i>content</i> table without storing the actual
     * media document in the imageData column. Instead, the media document is
     * stored as an external file in the file location designated as the <i>MIME
     * archive directory</i>. The <i>MIME archive directory</i> is configured in
     * the <i>MimeConfig_XXX.properties</i> file.
     * <p>
     * The user of this method is required to pass the image data as a Base64
     * encoded String in order to prevent any possible data loss in the event
     * the data is transported bewteen different processes. For instance, from
     * one web application context to another.
     * 
     * @param mediaRec
     *            an instance of {@link ContentDto} containing the data to add
     *            including the large binary and/or text data.
     * @return int the new content id just added.
     * @throws ContentDaoException
     *             <ul>
     *             <li>The <i>mediaRec</i> does not meet validation rules.</li>
     *             <li>Unable to create a row in the <i>content</i> table.</li>
     *             <li>Media content could not be saved as an external file.
     *             </li>
     *             </ul>
     */
    public int addContent(ContentDto mediaRec) throws ContentDaoException {
        logger.info("Validate Content record");
        try {
            this.validate(mediaRec);
        } catch (Exception e) {
            throw new ContentDaoException(e);
        }

        // We want to save the media content to the "archive" directory as well
        // as have
        // the media content metadata record point to the archive direcotry.
        mediaRec.setFilepath(this.config.getArchiveDir());

        logger.info("Begin to persisting media content record with external media file");
        int contentId = this.saveMetaData(mediaRec);
        try {
            int rc = this.saveContentAsFile(mediaRec);
            logger.info("Total number of bytes written to disk for media file, "
                    + mediaRec.getFilename() + ": " + rc);
        } catch (Exception e) {
            throw new ContentDaoException(e);
        }
        return contentId;
    }

    /**
     * Adds a record to the <i>content</i> table without including the image
     * data (binary or text).
     * <p>
     * The filepath and filename properties of <i>mediaRec</i> should point the
     * the directory location and the file name where the media document will be
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
        this.client.beginTrans();
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(mediaRec
                    .getUpdateUserId());
            rec.setDateCreated(ut.getDateCreated());
            rec.setUserId(ut.getLoginId());
            rec.setNull(Content.PROP_PROJECTID);
            newContentId = this.client.insertRow(rec, true);
            mediaRec.setContentId(newContentId);
            this.client.commitTrans();
            return newContentId;
        } catch (Exception e) {
            this.client.rollbackTrans();
            this.msg = "Error adding media record without embedded content";
            throw new ContentDaoException(this.msg, e);
        }
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
                this.msg = "Error fetching external media file, "
                        + file.getAbsolutePath();
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
    public ContentDto fetchContentAsFile(int contentId)
            throws ContentDaoException {
        ContentDto mime = super.fetchContent(contentId);
        if (mime == null) {
            return null;
        }

        // Try to input file to Content instance.
        File file = new File(mime.getFilepath() + mime.getFilename());
        int fileVerifyCode = RMT2File.verifyFile(file);
        if (fileVerifyCode != RMT2File.FILE_IO_EXIST) {
            this.msg = "Unable to locate external media file, "
                    + file.getAbsolutePath();
            throw new ContentDaoException(this.msg);
        }
        mime.setImageData(file);
        return mime;
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
     * By default the filepath property will be assigned the file path of the
     * archive directory as declared in <i>MimeConfig_XXX.properties</i> file.
     * 
     * @param dto
     *            an instance of {@link ContentDto} which will be validated.
     * @return Always return null.
     * @throws ContentDaoException
     *             The file name or the image data of <i>dto</i> is null, the
     *             image data is not Base 64 encoded, or the file extension is
     *             not an extension that exists in the database.
     */
    protected File validate(ContentDto dto) throws ContentDaoException {
        String inFileName = dto.getFilename();
        if (inFileName == null
                || inFileName.equals(RMT2String.spaces(inFileName.length()))) {
            this.msg = "The filename property is required";
            throw new ContentValidationDaoException(this.msg);
        }
        if (dto.getImageData() == null) {
            this.msg = "The data (binary or text) representing the media content, that is to be persisted as an external file, cannot be null";
            throw new ContentValidationDaoException(this.msg);
        }
        if (!(dto.getImageData() instanceof String)) {
            this.msg = "The data (binary or text) representing the media content, that is to be persisted as an external file, must be a base 64 encoded String";
            throw new ContentValidationDaoException(this.msg);
        }

        // dto.setFilepath(this.config.getArchiveDir());

        logger.info("Validate file extension");
        String ext = RMT2File.getFileExt(dto.getFilename());
        if (ext == null) {
            this.msg = "File name is required to have an extension when persisting an external media file";
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
    protected int saveContentAsFile(ContentDto mediaRec) {
        Thread t = new Thread(new MediaFilePeristenceThread(mediaRec));
        t.start();
        return 0;
    }

    /**
     * Performs asynchronously saves the media file so that the user does not
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
                byte binaryData[] = RMT2Base64Decoder.decodeToBytes(this.rec
                        .getImageData().toString());
                bytesWritten = RMT2File.outputFile(binaryData, path);
                this.rec.setSize(bytesWritten);
                msg = "External media file, "
                        + this.rec.getFilename()
                        + ", was persisted successfully in the following location:  "
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
