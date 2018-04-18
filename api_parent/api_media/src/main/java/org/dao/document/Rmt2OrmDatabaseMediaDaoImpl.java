package org.dao.document;

import org.dto.ContentDto;

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
            RMT2File.outputFile(mime.getImageData(), path);
            return mime;
        }
        else {
            this.msg = "Media image content is not available to be retrieved as a File instance";
            throw new ContentDaoException(this.msg);
        }
    }
}
