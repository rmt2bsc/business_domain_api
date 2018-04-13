package org.modules.document;

import java.util.List;

import org.apache.log4j.Logger;
import org.dao.document.ContentDao;
import org.dao.document.ContentDaoException;
import org.dao.document.ContentDaoFactory;
import org.dto.ContentDto;
import org.dto.MimeTypeDto;
import org.modules.MediaModuleException;
import org.modules.services.DocumentInboundDirectoryListener;

import com.api.foundation.AbstractTransactionApiImpl;
import com.util.RMT2File;

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

    private DocumentInboundDirectoryListener listener;

    /**
     * 
     */
    protected DocumentContentApiImpl() {
        super();
        this.factory = new ContentDaoFactory();
    }

    /**
     * Add media document using a particular {@link ContentDao} implementation
     * based on the flag, <i>embedded</i>.
     * 
     * @param media
     *            an instance of {@link ContentDto}
     * @param embedded
     *            Determines how the image data of the media document image data
     *            is to be persisted. Set to <i>true</i> when the media image
     *            data is to be saved in the same datastore as the media detail
     *            data. Set to <i>false</i> when the media document image data
     *            is to be saved as an external file.
     * @return The internal unique identifier of the document object added.
     * @throws MediaModuleException
     */
    @Override
    public int addMedia(ContentDto media, boolean embedded) throws MediaModuleException {
        ContentDao dao;
        if (embedded) {
            dao = this.factory.createEmbeddedMediaDaoInstance();
        }
        else {
            dao = this.factory.createExternalFileMediaDaoInstance();
        }
        return this.addMedia(dao, media);
    }

    /**
     * Creates a media document detail record in the <i>document</i> table and
     * either saves the media document image data as a column in the
     * <i>document</i> table or as an external file.
     * <p>
     * This logic is dependent upon the runtime type of <i>dao</i> input
     * paramteter.
     * 
     * @param dao
     *            an implementation of {@link ContentDao} which is either a
     *            database or external file implementation.
     * @param media
     *            an instance of {@link ContentDto}
     * @return The internal unique identifier of the document object added.
     * @throws MediaModuleException
     */
    private int addMedia(ContentDao dao, ContentDto media) throws MediaModuleException {
        dao.setDaoUser(this.apiUser);
        int newContentId = 0;
        try {
            newContentId = dao.addContent(media);
            return newContentId;
        } catch (ContentDaoException e) {
            this.msg = "Unable to add media document as a database recrod or as an external file";
            throw new MediaModuleException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
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
    public ContentDto getMedia(int contentId, boolean embedded) throws MediaModuleException {
        ContentDao dao;
        if (embedded) {
            dao = this.factory.createEmbeddedMediaDaoInstance();
        }
        else {
            dao = this.factory.createExternalFileMediaDaoInstance();
        }
        return this.getMedia(contentId, dao);
    }

    /**
     * Retrieves the media document detail record from the <i>document</i> table
     * and either obtains the media document image data from a property or as an
     * external file.
     * <p>
     * This logic is dependent upon the runtime type of <i>dao</i> input
     * paramteter.
     * 
     * @param contentId
     *            the internal unique id of the document record to retrieve
     * @param dao
     *            an implementation of {@link ContentDao} which is either a
     *            database or external file implementation.
     * @return The internal unique identifier of the document object added.
     * @throws MediaModuleException
     */
    private ContentDto getMedia(int contentId, ContentDao dao) throws MediaModuleException {
        ContentDto content = null;
        try {
            content = dao.fetchContent(contentId);
            return content;
        } catch (ContentDaoException e) {
            this.msg = "Unable to retrieve media document identified by document id: "
                    + contentId;
            throw new MediaModuleException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
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
    public int deleteMedia(int contentId) throws MediaModuleException {
        ContentDao dao = this.factory.createEmbeddedMediaDaoInstance();
        ContentDto deletedRec = null;
        int rows = 0;

        // Delete detail record
        try {
            deletedRec = dao.deleteContent(contentId);
            // Recognize that the media document detail record was handled
            // successfully
            if (deletedRec != null) {
                rows = 1;
                logger.info("Media document detail record was deleted successfully [document id = "
                        + contentId + "]");
            }
        } catch (ContentDaoException e) {
            this.msg = "Unable to delete media document identified by document id: "
                    + contentId;
            throw new MediaModuleException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }

        // Determine if we need to delete an external file
        if (deletedRec.getImageData() == null) {
            String path = deletedRec.getFilepath() + deletedRec.getFilename();
            rows = RMT2File.deleteFile(path);
            logger.info("Media document external file was deleted successfully ["
                    + path + "]");
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
        ContentDao dao = this.factory.createEmbeddedMediaDaoInstance();
        MimeTypeDto mt = null;
        try {
            mt = dao.fetchMimeType(mimeTypeId);
            return mt;
        } catch (ContentDaoException e) {
            this.msg = "Unable to retrieve mime type object identified by mime type id: "
                    + mimeTypeId;
            throw new MediaModuleException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
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
        ContentDao dao = this.factory.createEmbeddedMediaDaoInstance();
        List<MimeTypeDto> results = null;
        try {
            results = dao.fetchMimeType(criteria);
            return results;
        } catch (ContentDaoException e) {
            this.msg = "Unable to retrieve list of mime type objects using DTO selection criteria";
            throw new MediaModuleException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.document.DocumentContentApi#startMediaFileListener()
     */
    @Override
    public void startMediaFileListener() {
        this.listener = new DocumentInboundDirectoryListener();
        Thread t = new Thread(this.listener);
        t.start();

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.document.DocumentContentApi#stopMediaFileListener()
     */
    @Override
    public void stopMediaFileListener() {
        if (this.listener == null) {
            return;
        }
        this.listener.stop();
    }

}
