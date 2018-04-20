package org.dao.document.file;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.dao.MediaDaoImpl;
import org.modules.services.directory.file.FileDropConnectionException;

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
public abstract class AbstractMediaFileDaoImp extends MediaDaoImpl {

    private static Logger logger = Logger.getLogger(AbstractMediaFileDaoImp.class);

    /**
     * Indicates whether or not media document is required to be assoicated with
     * its home application.
     * <p>
     * This value is obtained from the hash value, <i>add_and_link</i>, that is
     * found in <i>AppParms.properties</i>.
     */
    public static boolean ADD_AND_LINK;

    /**
     * The database bean used for interacting with the MIME database.
     */
    protected DatabaseConnectionBean mimeConBean;

    /**
     * Creates a CommonMediaFileProcessorDaoImp object initialized with the MIME
     * file listener configuration of a specific application.
     * 
     */
    protected AbstractMediaFileDaoImp() {
        AbstractMediaFileDaoImp.ADD_AND_LINK = 
                Boolean.parseBoolean(RMT2File.getPropertyValue(ConfigConstants.CONFIG_APP, "add_and_link"));
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
        this.mimeConBean = (DatabaseConnectionBean) this.client.getConnectionWrapper();
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

}
