package org.dao.document.file;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.modules.services.directory.ApplicationModuleBean;
import org.modules.services.directory.DirectoryListenerConfigBean;
import org.modules.services.directory.file.FileDropConnectionException;
import org.modules.services.directory.file.HomeApplicationRecordCommittedException;
import org.modules.services.directory.file.HomeApplicationRecordNotFoundException;

import com.SystemException;
import com.api.BatchFileException;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.DatabaseConnectionBean;
import com.api.persistence.db.DatabaseConnectionFactory;
import com.api.persistence.db.SimpleConnectionProviderImpl;

/**
 * An implementation of {@link MediaFileDaoProcessor} for processing multiple
 * media files in a single batch.
 * <p>
 * Generally the file will be located in the directory designated as the inbound
 * directory in <i>MimeConfig_XXX.properties</i> file.
 * 
 * @author rterrell
 * 
 */
public class HomeAppDaoImpl extends AbstractMediaFileDaoImp implements HomeAppDao {

    private static Logger logger = Logger.getLogger(HomeAppDaoImpl.class);

    private DatabaseConnectionBean appConBean;

    private DirectoryListenerConfigBean config;

    private Map<Integer, DatabaseConnectionBean> conMap;


    /**
     * 
     */
    protected HomeAppDaoImpl() {
        super();
    }
    
    /**
     * Default constructor which sets up or obtains the configuration for the
     * media file environment.
     * <p>
     * This class depends on the existence of a <i>MimeConfig_XXX.properties</i>
     * file containing the necessary configuration information for batch
     * processing media files. It expects to pickup one or more files from a
     * inbound directory specified in the <i>MimeConfig_XXX.properties</i>,
     * persist the media document record to the <i>document</i> table for each
     * media file, link the media document to some arbitrary record of the Home
     * application, archive the source data file, and send a file drop report to
     * the user designated in the home application's MIME configuration.
     */
    public HomeAppDaoImpl(DirectoryListenerConfigBean config) {
        this();
        
        this.config = config;
        try {
            this.initConnection();
        } catch (BatchFileException e) {
            this.msg = "Error initializing Home App DB connections";
            throw new SystemException(this.msg, e);
        }
        return;
    }

    /**
     * Setup database connections for the home application as well as the
     * <i>mime</i> database using the individual {@link AppModuleConfig}
     * instances representing each home application that are contained in the
     * member variable which is an instance of {@link FileListenerConfig} for a
     * particular execution environment.
     * 
     * @throws BatchFileException
     *             when the attempt to establish a connection for either system
     *             fails.
     */
    public synchronized void initConnection() throws BatchFileException {
        super.initConnection();

        int modCount = this.config.getModuleCount();
        this.conMap = new HashMap<Integer, DatabaseConnectionBean>();
        int count = 0;
        DatabaseConnectionFactory f = new DatabaseConnectionFactory();
        try {
            for (int ndx = 0; ndx < modCount; ndx++) {
                ApplicationModuleBean mod = this.config.getModules().get(ndx);
                SimpleConnectionProviderImpl conUtil = new SimpleConnectionProviderImpl();
                DatabaseConnectionBean con = conUtil.getConnection(
                        mod.getDbDriver(), mod.getDbUrl(), mod.getDbUserId(),
                        mod.getDbPassword());
                this.conMap.put(ndx, con);
                logger.info("Opened database connection object for home application, "
                        + con.getName() + ", DB URL: " + con.getDbUrl());
                count++;
            }
        } catch (Exception e) {
            throw new FileDropConnectionException(e);
        }
        if (this.conMap.isEmpty()) {
            this.msg = "Failed to establish a database connection for the home application(s) required for the media file drop operation";
            logger.log(Level.ERROR, this.msg);
            throw new FileDropConnectionException(this.msg);
        }
        logger.info("Media file drop thread opened " + count + " home application database connection(s)");
    }

    /**
     * Closes the database connection for the home application and the MIME
     * application.
     */
    public synchronized void close() {
        super.close();

        // Close all module DB connections
        if (this.conMap != null && !this.conMap.isEmpty()) {
            Iterator<Integer> iter = this.conMap.keySet().iterator();
            int count = 0;
            while (iter.hasNext()) {
                Integer key = iter.next();
                DatabaseConnectionBean con = this.conMap.get(key);
                logger.info("Closing database connection object for application, "
                        + con.getName() + ", DB URL: " + con.getDbUrl());
                con.close();
                con = null;
                count++;
            }
            logger.info(count
                    + " home application database connections were closed successfully for the MIME thread");
        }
        this.appConBean = null;
    }

    /**
     * Applies database updates to the target record of the home application
     * using the primary key value of the MIME record recently added to the
     * document table. The parameters, <i>contentId</i> and
     * <i>fileNameTokens</i> are used to dynamically build SQL query needed
     * target and update the appropriate record of the home application based on
     * the mime configuration specified by the application.
     * 
     * @param moduleId The id of application module to link document media. 
     * @param contentId
     *            the id of the document record from the MIME database.
     * @param fileNameTokens
     *            a List of Strings representing the parsed file name. The List
     *            collection should contain three elements where element(0) is
     *            the applictaion code, element(1) is the module code, and
     *            element(2) is the target recrod's primary key value.
     * @throws HomeApplicationRecordCommittedException
     *             home application record is already associated with MIME
     *             document.
     * @throws HomeApplicationRecordNotFoundException
     *             hombe application record was not found using the primary key
     *             contained in <i>fileNameTokens</i>
     * @throws DatabaseException
     *             general database errors
     * @throws BatchFileException
     *             an invalid module configuration instance was obtained.
     * 
     */
    @Override
    public void update(int moduleId, int contentId, List<String> fileNameTokens) throws BatchFileException {
        logger.log(Level.INFO, "Inside updateHomeApp");
        String pkVal = fileNameTokens.get(2);

        ApplicationModuleBean mod = this.config.getModules().get(moduleId);
        if (mod == null) {
            this.msg = "Failure to update target application record due to an invalid moudle configuration instance";
            logger.log(Level.ERROR, this.msg);
            throw new BatchFileException(this.msg);
        }
        String sql = this.buildTargetAppQuery(mod, pkVal, contentId);
        logger.log(Level.INFO, sql);
        this.appConBean = this.conMap.get(moduleId);
        try {
            Statement stmt = this.appConBean.getNativeConnection()
                    .createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(sql);
            if (rs == null || !rs.next()) {
                this.msg = "The home application record was not found by primary key, "
                        + fileNameTokens.get(2) + ".  SQL: " + sql;
                logger.log(Level.ERROR, this.msg);
                throw new HomeApplicationRecordNotFoundException(this.msg);
            }
            // Verify that home application record is not already assoicated
            // with document.
            int content_id = rs.getInt(mod.getForeignKey());
            logger.log(Level.INFO, "Content Id value fetched from Home App record foreign key, ["
                            + mod.getForeignKey() + "] :" + content_id);
            if (content_id > 0) {
                this.msg = "Unable to update the home application record's document id, because it is already assigned with document identified by: "
                        + content_id;
                logger.log(Level.ERROR, this.msg);
                throw new HomeApplicationRecordCommittedException(this.msg);
            }
            logger.log(Level.INFO, "Updating Home App with document id, " + contentId);
            rs.updateInt(mod.getForeignKey(), contentId);
            rs.updateRow();
            logger.log(Level.INFO, "Home App updated successfully");
            return;
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new DatabaseException(e);
        }
    }

    /**
     * Builds SQL query using a valid moduld configuration instance, the primary
     * key of the target record of the home application, and the document id
     * from the MIME database.
     * 
     * @param moduleConfig
     *            the configuration needed to obtain the database table name,
     *            primary key column name, and the foreign key name of the home
     *            application's target record to build SQL query.
     * @param primaryKey
     *            the primary key value that is encoded as part of the source
     *            file's filename which represents the id of the home
     *            application's target record.
     * @param contentId
     *            the id of the MIME record.
     * @return SQL query.
     */
    private String buildTargetAppQuery(ApplicationModuleBean appModuleConfig, String primaryKey, int contentId) {
        StringBuffer sql = new StringBuffer();
        sql.append("select ");
        sql.append(appModuleConfig.getForeignKey());
        sql.append(" from ");
        sql.append(appModuleConfig.getTable());
        sql.append(" where ");
        sql.append(appModuleConfig.getPrimaryKey());
        sql.append(" = ");
        sql.append(primaryKey);
        return sql.toString();
    }

    @Override
    public void commitTrans() {
        try {
            this.mimeConBean.getNativeConnection().commit();
            logger.log(Level.INFO, "Media File was committed in MIME database");
            if (AbstractMediaFileDaoImp.ADD_AND_LINK) {
                this.appConBean.getNativeConnection().commit();
                logger.log(Level.INFO, "Media File was committed in HOME database");
            }
        } catch (SQLException ee) {
            this.msg = "Error attempting to commit Home Application Update transaction(s)";
            throw new DatabaseException(this.msg, ee);
        }

    }

    @Override
    public void rollbackTrans() {
        try {
            this.mimeConBean.getNativeConnection().rollback();
            logger.log(Level.INFO, "Media File was rolled back in MIME database");
            if (AbstractMediaFileDaoImp.ADD_AND_LINK) {
                this.appConBean.getNativeConnection().rollback();
                logger.log(Level.INFO, "Media File was rolled back in HOME database");
            }
        } catch (SQLException ee) {
            this.msg = "Error attempting to rollback Home Application Update transaction(s)";
            throw new DatabaseException(this.msg, ee);
        }
    }

    @Override
    public boolean isAddLink() {
        return AbstractMediaFileDaoImp.ADD_AND_LINK;
    }
}
