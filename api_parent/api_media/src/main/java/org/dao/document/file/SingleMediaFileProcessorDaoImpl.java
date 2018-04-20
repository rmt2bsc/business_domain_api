package org.dao.document.file;

import org.apache.log4j.Logger;
import org.dao.document.ContentDao;
import org.dao.document.ContentDaoFactory;
import org.dto.ContentDto;
import org.dto.adapter.orm.Rmt2MediaDtoFactory;

import com.api.BatchFileException;

/**
 * An implementation of {@link MediaFileDaoProcessor} for managing a single
 * media file at a time.
 * 
 * @author rterrell
 * 
 */
class SingleMediaFileProcessorDaoImpl extends AbstractMediaFileProcessorDaoImp {

    private static Logger logger = Logger.getLogger(SingleMediaFileProcessorDaoImpl.class);

    /**
     * Creates a SingleMediaFileProcessorDaoImpl object.
     * 
     */
    protected SingleMediaFileProcessorDaoImpl() {
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
            SingleMediaFileProcessorDaoImpl.logger.info("Create Content Object");
            ContentDto mime = Rmt2MediaDtoFactory.getContentInstance(fileName);

            // Add media file to the document table in the database.
            SingleMediaFileProcessorDaoImpl.logger.info("Get document API");
            dao = daoFactory.createDatabaseMediaDaoInstance();
            dao.setDaoUser(AbstractMediaFileProcessorDaoImp.UPDATE_USERID);
            SingleMediaFileProcessorDaoImpl.logger.info("Initialize document API");
            SingleMediaFileProcessorDaoImpl.logger.info("Add document to MIME database");
            contentId = dao.saveContent(mime);
            SingleMediaFileProcessorDaoImpl.logger.info("MIME Database updates completed");
            return contentId;
        } catch (Exception e) {
            SingleMediaFileProcessorDaoImpl.logger.error(e.getMessage());
            throw new MediaFileOperationDaoException(e);
        } finally {
            dao.close();
            dao = null;
        }
    }

}
