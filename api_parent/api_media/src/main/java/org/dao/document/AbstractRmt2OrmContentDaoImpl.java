package org.dao.document;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.util.RMT2Date;
import com.util.UserTimestamp;

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

    protected org.FileListenerConfig config;

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
    public ContentDto fetchContent(int contentId) throws ContentDaoException {
        Content mime = null;
        String sql = "select content_id, mime_type_id, app_code, module_code, filepath, filename, size, date_created, user_id, image_data, text_data from content where content_id = "
                + contentId;
        logger.info("Execute Query: " + sql);
        try {
            ResultSet rs = this.client.executeSql(sql);
            if (rs != null && rs.next()) {
                mime = new Content();
                mime.setContentId(rs.getInt("content_id"));
                mime.setMimeTypeId(rs.getInt("mime_type_id"));
                mime.setAppCode(rs.getString("app_code"));
                mime.setModuleCode(rs.getString("module_code"));
                mime.setFilepath(rs.getString("filepath"));
                mime.setFilename(rs.getString("filename"));
                mime.setSize(rs.getInt("size"));
                mime.setDateCreated(rs.getDate("date_created"));
                mime.setUserId(rs.getString("user_id"));
                mime.setTextData(rs.getString("text_data"));
                byte binaryData[] = rs.getBytes("image_data");
                mime.setImageData(binaryData);
            }
            else {
                return null;
            }
        } catch (SQLException | DatabaseException e) {
            this.msg = "Error occurred saving document media meta data to the database";
            throw new ContentDaoException(this.msg, e);
        }

        ContentDto dto = Rmt2MediaDtoFactory.getContentInstance(mime);
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
     * Adds a record to the <i>content</i> table including the image
     * data.
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
        mediaRec.setUpdateUserId(this.getDaoUser());

        Content rec = new Content();
        rec.setMimeTypeId(mediaRec.getMimeTypeId());
        rec.setFilepath(mediaRec.getFilepath());
        rec.setFilename(mediaRec.getFilename());
        rec.setSize(mediaRec.getSize());
        rec.setTextData(null);
        rec.setAppCode(null);
        rec.setModuleCode(null);
        
        int newContentId = 0;
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(mediaRec.getUpdateUserId());
            rec.setDateCreated(ut.getDateCreated());
            rec.setUserId(ut.getLoginId());
            rec.setNull(Content.PROP_PROJECTID);
            newContentId = this.client.insertRow(rec, true);
            mediaRec.setContentId(newContentId);
            return newContentId;
        } catch (Exception e) {
            this.msg = "Error adding media meta data and content to the content table";
            throw new ContentDaoException(this.msg, e);
        }
    }
    
    /**
     * Deletes a single media document record from the database
     * 
     * @param contentId
     *            a integer value representing the database primary key of the
     *            document record that is to be deleted.
     * @return an instance of {@link ContentDto} as the orginal record just
     *         deleted.
     * @throws ContentDaoException
     */
    public ContentDto deleteContent(int contentId) throws ContentDaoException {
        if (contentId <= 0) {
            this.msg = "Content id is invalid";
            throw new ContentValidationDaoException(this.msg);
        }
        ContentDto origRec = this.fetchContent(contentId);

        Content mime = new Content();
        mime.addCriteria(Content.PROP_CONTENTID, contentId);
        this.client.beginTrans();
        try {
            int rows = this.client.deleteRow(mime);
            logger.info("The total number of records deleted: " + rows);
            this.client.commitTrans();
            return origRec;
        } catch (Exception e) {
            this.client.rollbackTrans();
            throw new ContentDaoException(e);
        }
    }
}
