package org.modules.document;

import java.util.List;

import org.apache.log4j.Logger;
import org.dao.document.ContentDao;
import org.dao.document.ContentDaoException;
import org.dao.document.ContentDaoFactory;
import org.dto.ContentDto;
import org.dto.MimeTypeDto;
import org.dto.adapter.orm.Rmt2MediaDtoFactory;
import org.modules.MediaModuleException;
import org.modules.services.document.directory.DirectoryInboundDocumentListener;

import com.InvalidDataException;
import com.NotFoundException;
import com.api.foundation.AbstractTransactionApiImpl;
import com.api.util.RMT2File;
import com.api.util.assistants.Verifier;
import com.api.util.assistants.VerifyException;

/**
 * A basic impelentation of {@link DocumentContentApi} that provides the user
 * the ability to access and manage media document.
 * 
 * @author Roy Terrell
 * 
 */
class DocumentContentApiImpl extends AbstractTransactionApiImpl implements DocumentContentApi {

    private static Logger logger = Logger.getLogger(AbstractTransactionApiImpl.class);

    private ContentDaoFactory factory;
    
    private ContentDao dao;

    private static DirectoryInboundDocumentListener MEDIA_DIR_LISTENER;
    private static final String STATUS_RUNNING = "Running";
    private static final String STATUS_STOPPED = "Stopped";



    /**
     * Creates an DocumentContentApiImpl identified application and defaults to
     * saving media to the database.
     * 
     * @param appName
     */
    protected DocumentContentApiImpl(String appName) {
        this(appName, true);
    }
    
    /**
     * Creates an DocumentContentApiImpl identified by application name.
     * 
     * @param appName
     *            the application name
     * @param persistInDb
     *            Determines how the media document image data is to be
     *            persisted. Set to <i>true</i> when the media image data is to
     *            be saved in the same datastore as the media detail data. Set
     *            to <i>false</i> when the media document image data is to be
     *            saved as an external file.
     */
    protected DocumentContentApiImpl(String appName, boolean persistInDb) {
        super(appName);
        this.factory = new ContentDaoFactory();
        if (persistInDb) {
            dao = this.factory.createSybaseAsaDatabaseMediaDaoInstance();
        }
        else {
            dao = this.factory.createExternalFileMediaDaoInstance();
        }
    }
    
    /**
     * Creates a media document detail record in the <i>document</i> table and
     * either saves the media document image data as a column in the
     * <i>document</i> table or as an external file.
     * <p>
     * This logic is dependent upon the runtime type of <i>ContentDao</i> used.
     * 
     * @param media
     *            an instance of {@link ContentDto}
     * @return The internal unique identifier of the document object added.
     * @throws MediaModuleException
     */
    @Override
    public int add(String filePath) throws MediaModuleException {
        try {
            Verifier.verifyNotEmpty(filePath);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("File Path is required");
        }
        
        dao.setDaoUser(this.apiUser);
        int newContentId = 0;
        try {
            ContentDto media = Rmt2MediaDtoFactory.getContentInstance(filePath);
            byte[] binaryData = RMT2File.getFileContentsAsBytes(filePath);
            media.setImageData(binaryData);
            media.setSize(binaryData.length);
            
            
            // Now that we've obtained the media's content, set the filePath property to the output directory.
            // This property is only used by the DAO repsonsible for creating external physical files.  Other
            // DAO implementations should ignore.
            String outputDir = this.getConfig().getProperty("media_output_location");
            media.setFilepath(outputDir);
            
            // Validate Media DTO
            this.validate(media);
            // Save content
            newContentId = dao.saveContent(media);
            return newContentId;
        } catch (ContentDaoException e) {
            this.msg = "Unable to add media document as a database recrod or as an external file";
            throw new MediaModuleException(this.msg, e);
        } 
        catch (NotFoundException e) {
            this.msg = "Unable to add media document as a database recrod or as an external file due to input file cannot be located";
            throw new MediaModuleException(this.msg, e);
        }
    }

    /**
     * Add media document in which the image data and assoicated meta data is
     * availble.
     * 
     * @param document
     *            An instance of {@link ContentDto} which contains the document
     *            image and its metadata.
     * @return The internal unique identifier of the document object added.
     * @throws MediaModuleException
     */
    @Override
    public int add(ContentDto document) throws MediaModuleException {
        try {
            Verifier.verifyNotNull(document);
        } catch (VerifyException e) {
            throw new InvalidDataException("A valid ContentDto oject must exists for this API operation");
        }

        try {
            Verifier.verifyNotEmpty(document.getImageData());
        } catch (VerifyException e) {
            throw new InvalidDataException("Document content is required");
        }

        dao.setDaoUser(this.apiUser);
        int newContentId = 0;
        try {
            String fileExt = RMT2File.getFileExt(document.getFilename());
            if (fileExt != null) {
                MimeTypeDto mtCriteria = Rmt2MediaDtoFactory.getMimeTypeInstance(null);
                mtCriteria.setFileExt("." + fileExt);
                List<MimeTypeDto> mtDto = this.dao.fetchMimeType(mtCriteria);
                if (mtDto != null && mtDto.size() == 1) {
                    document.setMimeTypeId(mtDto.get(0).getMimeTypeId());
                }
            }

            // Validate Media DTO
            this.validate(document);

            // Save content
            newContentId = dao.saveContent(document);
            return newContentId;
        } catch (ContentDaoException e) {
            this.msg = "Unable to add media document, " + document.getFilename() + ", as a database recrod";
            throw new MediaModuleException(this.msg, e);
        }
    }

    /**
     * Retrieves the media based on it internal unique identifier using a
     * particular {@link ContentDao} implementation based on the flag,
     * <i>embedded</i>.
     * 
     * @param contentId
     *            The internal unique identifier of the media document. param
     *            embedded Determines how the image data of the media document
     *            detail record is to be retrieved. Set to <i>true</i> when the
     *            media image data exists as part of the document detail record.
     *            Set to <i>false</i> when the media document's image data is to
     *            be retrieved from an external file.
     * @return An instance of {@link ContentDto} representing the media
     *         document.
     * @throws MediaModuleException
     */
    @Override
    public ContentDto get(int contentId) throws MediaModuleException {
        ContentDto content = null;
        try {
            content = dao.fetchContent(contentId);
            return content;
        } catch (ContentDaoException e) {
            this.msg = "Unable to retrieve media document identified by document id: " + contentId;
            throw new MediaModuleException(this.msg, e);
        }
    }



    /**
     * Deletes media document from the systems based on the internal unique
     * identifier, document id.
     * <p>
     * First, the media document detail record is removed, and lastly, if
     * applicable, the external file representing the media document image data
     * is removed.
     * 
     * @param contentId
     *            The internal unique identifier of the media document.
     * @return The total number of instances effected by the operation.
     * @throws MediaModuleException
     */
    @Override
    public int delete(int contentId) throws MediaModuleException {
        try {
            Verifier.verifyPositive(contentId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Content Id must be greater than zero", e);
        }

        // Delete detail record
        int rows = 0;
        try {
            rows = dao.deleteContent(contentId);
        } catch (ContentDaoException e) {
            this.msg = "Unable to delete media document identified by document id: " + contentId;
            throw new MediaModuleException(this.msg, e);
        } 
        return rows;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.document.DocumentContentApi#getMimeType(int)
     */
    @Override
    public MimeTypeDto getMimeType(int mimeTypeId) throws MediaModuleException {
        MimeTypeDto mt = null;
        try {
            mt = dao.fetchMimeType(mimeTypeId);
            return mt;
        } catch (ContentDaoException e) {
            this.msg = "Unable to retrieve mime type object identified by mime type id: " + mimeTypeId;
            throw new MediaModuleException(this.msg, e);
        } 
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.document.DocumentContentApi#getMimeType(org.dto.MimeTypeDto)
     */
    @Override
    public List<MimeTypeDto> getMimeType(MimeTypeDto criteria) throws MediaModuleException {
        List<MimeTypeDto> results = null;
        try {
            results = dao.fetchMimeType(criteria);
            return results;
        } catch (ContentDaoException e) {
            this.msg = "Unable to retrieve list of mime type objects using DTO selection criteria";
            throw new MediaModuleException(this.msg, e);
        } 
    }

    
    /**
     * Verifies that the media content record contains valid data..
     * <p>
     * It is required that the file name and file path are populated. Also, validates whether the
     * file extension is registered in the database. If the MIME type exists,
     * then the MIME type id is assigned to the mimeTypeId property of
     * <i>dto</i>.
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
    protected void validate(ContentDto dto) throws MediaModuleException {
        logger.info("Validate file name");
        if (dto.getFilename() == null) {
            this.msg = "File name is required to persist MIME type";
            DocumentContentApiImpl.logger.error(this.msg);
            throw new InvalidDataException(this.msg);
        }

        // logger.info("Validate file path");
        // if (dto.getFilepath() == null) {
        // this.msg = "File path is required to persist MIME type";
        // DocumentContentApiImpl.logger.error(this.msg);
        // throw new InvalidDataException(this.msg);
        // }
        
        logger.info("Validate file extension");
        String ext = RMT2File.getFileExt(dto.getFilename());
        if (ext == null) {
            this.msg = "File name is required to have an extension for the purposes of identifying/validationg the MIME type";
            throw new InvalidDataException(this.msg);
        }

        // Get mime type id of file name.
        MimeTypeDto mtCriteria = Rmt2MediaDtoFactory.getMimeTypeInstance(null);
        mtCriteria.setFileExt("." + ext);
        List<MimeTypeDto> list = this.getMimeType(mtCriteria);
        if (list == null) {
            this.msg = "File, "
                    + dto.getFilename()
                    + ", was not persisted due to file extension is not registered in the MIME database";
            throw new InvalidDataException(this.msg);
        }
        MimeTypeDto mt = list.get(0);
        logger.info("Media File: " + dto.getFilepath() + dto.getFilename());
        logger.info("Mime Type: " + mt.getMediaType());
        dto.setMimeTypeId(mt.getMimeTypeId());
        logger.info("Passed media file validations");
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.document.DocumentContentApi#startMediaFileListener()
     */
    @Override
    public void startMediaFileListener() {
        MEDIA_DIR_LISTENER = new DirectoryInboundDocumentListener();
        Thread t = new Thread(MEDIA_DIR_LISTENER);
        t.start();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.document.DocumentContentApi#stopMediaFileListener()
     */
    @Override
    public void stopMediaFileListener() {
        if (MEDIA_DIR_LISTENER == null) {
            return;
        }
        MEDIA_DIR_LISTENER.stop();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.document.DocumentContentApi#getMediaFileListenerStatus()
     */
    @Override
    public String getMediaFileListenerStatus() {
        if (MEDIA_DIR_LISTENER != null && MEDIA_DIR_LISTENER.getStatus()) {
            return DocumentContentApiImpl.STATUS_RUNNING;
        }
        else {
            return DocumentContentApiImpl.STATUS_STOPPED;
        }
    }
}
