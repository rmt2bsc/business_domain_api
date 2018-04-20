package org.modules.services.directory.file;

import java.io.File;

import org.apache.log4j.Logger;
import org.dao.document.ContentDao;
import org.dao.document.ContentDaoFactory;
import org.dto.ContentDto;
import org.dto.adapter.orm.Rmt2MediaDtoFactory;
import org.modules.MediaConstants;

import com.RMT2Base;
import com.RMT2Constants;
import com.api.BatchFileException;

/**
 * An implementation of {@link MediaFileDaoProcessor} for managing a single
 * media file at a time.
 * 
 * @author rterrell
 * 
 */
abstract class AbstractMediaFileProcessorImpl extends RMT2Base implements MediaFileProcessor { //extends AbstractMediaFileProcessorDaoImp {

    private static Logger logger = Logger.getLogger(AbstractMediaFileProcessorImpl.class);

    /**
     * Creates a SingleMediaFileProcessorDaoImpl object.
     * 
     */
    protected AbstractMediaFileProcessorImpl() {
        super();
        return;
    }

    /**
     * Adds media document to the database and joins the document with the
     * target record of the home application.
     * <p>
     * Successful execution of this method requires that the name of the file,
     * <i>fileName</i>, is of the correct format
     * "<app_code>_<module_code>_<primary key value of target recrod>.*". After
     * determining that <i>fileName</i> is correctly formatted, the MIME
     * document is added to the MIME database and then associated with target
     * record of the home application. The primary key value identified in
     * <i>fileName</i> is used to locate the correct home appliation record.
     * 
     * @param fileName
     *            the filename of the document that is to be added to the
     *            system.
     * @return int the primary key value from the <i>Content</i> table
     *         representing the recently added MIME document record.
     * @throws BatchFileException
     *             An error occurred adding the document to the MIME database,
     *             or an error occurs associating the MIME document record with
     *             the target record of the home application, or <i>fileName</i>
     *             is not in the correct format.
     */
    public Integer processSingleFile(String fileName, Object parent) throws BatchFileException {
        int contentId = 0;
        ContentDaoFactory daoFactory = new ContentDaoFactory();
        ContentDao dao = null;
        try {
            // Build MIME document object
            AbstractMediaFileProcessorImpl.logger.info("Create Content Object");
            ContentDto mime = Rmt2MediaDtoFactory.getContentInstance(fileName);

            // Add media file to the document table in the database.
            AbstractMediaFileProcessorImpl.logger.info("Get document API");
            dao = daoFactory.createDatabaseMediaDaoInstance();
            dao.setDaoUser(MediaConstants.UPDATE_USERID);
            AbstractMediaFileProcessorImpl.logger.info("Initialize document API");
            AbstractMediaFileProcessorImpl.logger.info("Add document to MIME database");
            contentId = dao.saveContent(mime);
            AbstractMediaFileProcessorImpl.logger.info("MIME Database updates completed");
            return contentId;
        } catch (Exception e) {
            AbstractMediaFileProcessorImpl.logger.error(e.getMessage());
            throw new MediaFileOperationException(e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    @Override
    public void initConnection(Object extSource) throws BatchFileException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    @Override
    public void initConnection(String configFile1, String configFile2) throws BatchFileException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    @Override
    public Object processDirectory(File dir, Object parent) throws BatchFileException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    @Override
    public Object processSingleFile(File file, Object parent) throws BatchFileException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }
}
