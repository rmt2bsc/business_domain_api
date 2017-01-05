package org.dao.document;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.MediaAppConfig;
import org.apache.log4j.Logger;
import org.dao.MediaDaoImpl;
import org.dao.mapping.orm.rmt2.Content;
import org.dao.mapping.orm.rmt2.MimeTypes;
import org.dto.ContentDto;
import org.dto.MimeTypeDto;
import org.dto.adapter.orm.Rmt2MediaDtoFactory;

import com.SystemException;
import com.api.persistence.DatabaseException;

/**
 * An common implementation of the {@link ContentDao} for fetching, deleting,
 * and validating media file document.
 * 
 * @author Roy Terrell
 * 
 */
abstract class AbstractRmt2OrmContentDaoImpl extends MediaDaoImpl {

    private static Logger logger = Logger
            .getLogger(AbstractRmt2OrmContentDaoImpl.class);

    protected org.FileListenerConfig config;

    /**
     * Default constructor which sets up or obtains the configuration for the
     * media file environment.
     */
    protected AbstractRmt2OrmContentDaoImpl() {
        super();
        this.setup();
        // try {
        // this.config = MediaAppConfig.getConfigInstance();
        // } catch (Exception e) {
        // this.msg = "Error instantiating " + this.getClass().getSimpleName()
        // + " object";
        // throw new SystemException(this.msg, e);
        // }
        return;
    }

    /**
     * Default constructor which sets up or obtains the configuration for the
     * media file environment.
     */
    protected AbstractRmt2OrmContentDaoImpl(String appName) {
        super(appName);
        this.setup();
        // try {
        // this.config = MediaAppConfig.getConfigInstance();
        // } catch (Exception e) {
        // this.msg = "Error instantiating " + this.getClass().getSimpleName()
        // + " object";
        // throw new SystemException(this.msg, e);
        // }
        return;
    }

    protected void setup() {
        try {
            this.config = MediaAppConfig.getConfigInstance();
        } catch (Exception e) {
            this.msg = "Error instantiating " + this.getClass().getSimpleName()
                    + " object";
            throw new SystemException(this.msg, e);
        }
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
        ResultSet rs = this.client.executeSql(sql);
        try {
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
                // // Use base64 encoding to encode image data in order to
                // transport over the wire without data corruption
                // if (binaryData != null) {
                // String encodedImg = RMT2Base64Encoder.encode(binaryData);
                // mime.setImageData(encodedImg);
                // }
                // else {
                // mime.setImageData(null);
                // }
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            throw new ContentDaoException(e);
        }

        ContentDto dto = Rmt2MediaDtoFactory.getContentInstance(mime);
        return dto;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.db.MimeContentApi#getMimeType(java.lang.String)
     */
    public List<MimeTypeDto> fetchMimeType(MimeTypeDto dtoCriteria)
            throws ContentDaoException {
        MimeTypes criteria = new MimeTypes();

        if (dtoCriteria != null) {
            if (dtoCriteria.getMimeTypeId() > 0) {
                criteria.addCriteria(MimeTypes.PROP_MIMETYPEID,
                        dtoCriteria.getMimeTypeId());
            }
            if (dtoCriteria.getFileExt() != null) {
                criteria.addLikeClause(MimeTypes.PROP_FILEEXT,
                        dtoCriteria.getFileExt());
            }
            if (dtoCriteria.getMediaType() != null) {
                criteria.addLikeClause(MimeTypes.PROP_MEDIATYPE,
                        dtoCriteria.getMediaType());
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

    /**
     * Verifies that the file name and file path properties are present.
     * 
     * @param dto
     *            an instance of {@link ContentDto} which will be validated.
     * @return always returns null.
     * @throws ContentDaoException
     *             when either the file name or file path is not present.
     */
    protected File validate(ContentDto dto) throws ContentDaoException {
        logger.info("Validate file name");
        if (dto.getFilename() == null) {
            this.msg = "File name is required to persist MIME type";
            AbstractRmt2OrmContentDaoImpl.logger.error(this.msg);
            throw new ContentValidationDaoException(this.msg);
        }

        logger.info("Validate file path");
        if (dto.getFilepath() == null) {
            this.msg = "File path is required to persist MIME type";
            AbstractRmt2OrmContentDaoImpl.logger.error(this.msg);
            throw new ContentValidationDaoException(this.msg);
        }
        return null;
    }

}
