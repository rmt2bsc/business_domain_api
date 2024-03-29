package org.dao.document;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dao.MediaDaoImpl;
import org.dao.mapping.orm.rmt2.Content;
import org.dao.mapping.orm.rmt2.MimeTypes;
import org.dto.ContentDto;
import org.dto.MimeTypeDto;
import org.dto.adapter.orm.Rmt2MediaDtoFactory;

import com.api.persistence.DatabaseException;
import com.api.util.RMT2Date;
import com.api.util.UserTimestamp;

/**
 * An common RMT2 ORM implementation of the {@link ContentDao} for managing
 * media meta data and media content such as text, audio, video, images, binary
 * documents and etc.
 * 
 * @author Roy Terrell
 * 
 */
abstract class AbstractRmt2OrmContentDaoImpl extends MediaDaoImpl implements ContentDao {

    private static Logger logger = Logger.getLogger(AbstractRmt2OrmContentDaoImpl.class);

    /**
     * Default constructor which sets up or obtains the configuration for the
     * media file environment.
     */
    protected AbstractRmt2OrmContentDaoImpl() {
        super();
        return;
    }

    /**
     * Default constructor which sets up or obtains the configuration for the
     * media file environment.
     */
    protected AbstractRmt2OrmContentDaoImpl(String appName) {
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
        Content criteria = new Content();
        criteria.addCriteria(Content.PROP_CONTENTID, contentId);
        Content results;
        try {
            results = (Content) this.client.retrieveObject(criteria);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            this.msg = "DAO error occurred fetching media content record by content id, " + contentId;
            throw new ContentDaoException(this.msg, e);
        }
        // Convert results to DTO
        ContentDto dto = Rmt2MediaDtoFactory.getContentInstance(results);
        return dto;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.db.MimeContentApi#getMimeType(java.lang.String)
     */
    public List<MimeTypeDto> fetchMimeType(MimeTypeDto dtoCriteria) throws ContentDaoException {
        MimeTypes criteria = new MimeTypes();

        if (dtoCriteria != null) {
            if (dtoCriteria.getMimeTypeId() > 0) {
                criteria.addCriteria(MimeTypes.PROP_MIMETYPEID, dtoCriteria.getMimeTypeId());
            }
            if (dtoCriteria.getFileExt() != null) {
                criteria.addLikeClause(MimeTypes.PROP_FILEEXT, dtoCriteria.getFileExt());
            }
            if (dtoCriteria.getMediaType() != null) {
                criteria.addLikeClause(MimeTypes.PROP_MEDIATYPE, dtoCriteria.getMediaType());
            }
        }

        List<MimeTypes> results = null;
        try {
            results = this.client.retrieveList(criteria);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new ContentDaoException(e);
        }

        List<MimeTypeDto> list = new ArrayList<MimeTypeDto>();
        for (MimeTypes item : results) {
            MimeTypeDto dto = Rmt2MediaDtoFactory.getMimeTypeInstance(item);
            list.add(dto);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.db.MimeContentApi#getMimeType(int)
     */
    public MimeTypeDto fetchMimeType(int mimeTypeId) throws ContentDaoException {
        MimeTypes criteria = new MimeTypes();
        criteria.addCriteria(MimeTypes.PROP_MIMETYPEID, mimeTypeId);
        MimeTypes results = null;
        try {
            results = (MimeTypes) this.client.retrieveObject(criteria);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new ContentDaoException(e);
        }
        MimeTypeDto dto = Rmt2MediaDtoFactory.getMimeTypeInstance(results);
        return dto;
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
        // Identify logged in user to track updates
        mediaRec.setUpdateUserId(this.getDaoUser());
        if (mediaRec.getImageData() != null) {
            mediaRec.setSize(mediaRec.getImageData().length);
        }

        // Add media meta data and media binary content to hte database
        Content rec = new Content();
        rec.setMimeTypeId(mediaRec.getMimeTypeId());
        rec.setFilepath(mediaRec.getFilepath());
        rec.setFilename(mediaRec.getFilename());
        rec.setSize(mediaRec.getSize());
        rec.setTextData(null);
        rec.setAppCode(mediaRec.getAppCode());
        rec.setModuleCode(mediaRec.getModuleCode());

        int newContentId = 0;
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(mediaRec.getUpdateUserId());
            rec.setDateCreated(ut.getDateCreated());
            rec.setUserId(ut.getLoginId());
            rec.setNull(Content.PROP_PROJECTID);

            // Add media medta data first
            newContentId = this.client.insertRow(rec, true);
            mediaRec.setContentId(newContentId);
            return newContentId;
        } catch (Exception e) {
            this.msg = "Error adding media meta data and content to the content table";
            throw new ContentDaoException(this.msg, e);
        }
    }

    /**
     * Deletes a single media document record from the database.
     * 
     * @param contentId
     *            a integer value representing the database primary key of the
     *            document record that is to be deleted.
     * @return The total number of rows removed from the content table.
     * @throws ContentDaoException
     */
    public int deleteContent(int contentId) throws ContentDaoException {
        int rows = 0;
        Content mime = new Content();
        mime.addCriteria(Content.PROP_CONTENTID, contentId);
        try {
            rows = this.client.deleteRow(mime);
            logger.info("The total number of records deleted: " + rows);
        } catch (Exception e) {
            this.msg = "Error deleting media content from the content table [contentId=" + contentId + "]";
            throw new ContentDaoException(this.msg, e);
        }
        return rows;
    }
}
