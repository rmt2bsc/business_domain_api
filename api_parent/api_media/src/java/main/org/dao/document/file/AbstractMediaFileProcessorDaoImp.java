package org.dao.document.file;

import java.io.File;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.dao.MediaDaoImpl;

import com.RMT2Constants;
import com.api.BatchFileException;
import com.api.config.ConfigConstants;
import com.api.persistence.db.DatabaseConnectionBean;
import com.util.RMT2File;

/**
 * An abstract implementation of the {@link MediaFileDaoProcessor} interface
 * providing basic functionality for opening and closing the connection to the
 * MIME database.
 * 
 * @author Roy Terrell
 * 
 */
abstract class AbstractMediaFileProcessorDaoImp extends MediaDaoImpl implements
        MediaFileDaoProcessor {

    private static Logger logger = Logger
            .getLogger(AbstractMediaFileProcessorDaoImp.class);

    /**
     * Indicates whether or not media document is required to be assoicated with
     * its home application.
     * <p>
     * This value is obtained from the hash value, <i>add_and_link</i>, that is
     * found in <i>AppParms.properties</i>.
     */
    public static boolean ADD_AND_LINK;

    /**
     * The internal user id for timestamping database update activity in regards
     * to processing media files
     */
    protected static final String UPDATE_USERID = "media_api_user";

    /**
     * The database bean used for interacting with the MIME database.
     */
    protected DatabaseConnectionBean mimeConBean;

    /**
     * Creates a CommonMediaFileProcessorDaoImp object initialized with the MIME
     * file listener configuration of a specific application.
     * 
     */
    protected AbstractMediaFileProcessorDaoImp() {
        AbstractMediaFileProcessorDaoImp.ADD_AND_LINK = Boolean
                .parseBoolean(RMT2File.getPropertyValue(
                        ConfigConstants.CONFIG_APP, "add_and_link"));
        return;
    }

    /**
     * Setup the database connection as it pertains to the <i>mime</i> database
     * 
     * @throws BatchFileException
     *             when the attempt to establish a connection for either system
     *             fails.
     */
    public synchronized void initConnection() throws BatchFileException {
        // Obtain database connection for MIME database.
        this.mimeConBean = (DatabaseConnectionBean) this.client
                .getConnectionWrapper();
        if (this.mimeConBean == null) {
            this.msg = "Failed to obtain a database connection for the media application";
            logger.log(Level.ERROR, this.msg);
            throw new FileDropConnectionException(this.msg);
        }
        logger.info("Opened local database connection object for MIME application"
                + this.mimeConBean.getName()
                + ", DB URL: "
                + this.mimeConBean.getDbUrl());
    }

    /**
     * Closes the database connection for the home application and the MIME
     * application.
     */
    public synchronized void close() {
        if (this.mimeConBean != null) {
            this.mimeConBean.close();
            this.mimeConBean = null;
            logger.info("The MIME database connection was closed sucessfully");
        }
    }

    /**
     * Implementation is not required for this method.
     * 
     * @param extSource
     *            N/A
     * @throws UnsupportedOperationException
     */
    public void initConnection(Object extSource) throws BatchFileException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * This operation is not supported.
     * 
     * @param appConfigFile
     *            N/A
     * @param mimeConfigFile
     *            N/A
     * @throws UnsupportedOperationException
     */
    public void initConnection(String appConfigFile, String mimeConfigFile)
            throws BatchFileException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * This operation is not supported.
     * 
     * @param file
     *            N/A
     * @param parent
     *            N/A
     * @return N/A
     * @throws UnsupportedOperationException
     */
    public Object processSingleFile(File file, Object parent)
            throws BatchFileException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.BatchFileProcessor#processBatch()
     */
    public int processBatch() throws BatchFileException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * This operation is not supported.
     * 
     * @param dir
     *            N/A
     * @return N/A
     * @throws UnsupportedOperationException
     */
    public Object processDirectory(File dir, Object parent)
            throws BatchFileException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.BatchFileProcessor#getFileListing()
     */
    public List<String> getFileListing() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.api.BatchFileProcessor#processFiles(java.util.List,
     * java.lang.Object)
     */
    public Object processFiles(List<String> files, Object parent)
            throws BatchFileException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.file.MediaFileDaoProcessor#setModuleId(int)
     */
    public void setModuleId(int moduleId) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.file.MediaFileDaoProcessor#sendDropReport()
     */
    public void sendDropReport() throws FileDropReportException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.file.MediaFileDaoProcessor#isFilesAvailable()
     */
    public boolean isFilesAvailable() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

}
