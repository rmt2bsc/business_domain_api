package org;

import org.apache.log4j.Logger;

import com.RMT2Base;
import com.api.config.ConfigConstants;
import com.util.RMT2File;

/**
 * A factory for creating MIME file hanlder related objects.
 * 
 * @author appdev
 * 
 */
public class MediaAppConfig extends RMT2Base {

    private static Logger logger = Logger.getLogger(MediaAppConfig.class);

    public static final String configFile = "MimeConfig";

    public static final String ENV = RMT2File.getPropertyValue(
            ConfigConstants.CONFIG_APP, "ENVIRONMENT");

    /**
     * Create
     */
    private MediaAppConfig() {
        MediaAppConfig.logger.info("Logger initialized");
    }

    /**
     * Creates a FileListenerConfig instance from the default MIME configuration
     * proerties file, MimeConfig_<environment indicator>.properties.
     * 
     * @return {@link FileListenerConfig}
     */
    public static FileListenerConfig getConfigInstance() {
        String propFile = MediaConstants.CONFIG_CLASSPATH + "."
                + MediaAppConfig.configFile + "_" + MediaAppConfig.ENV;
        logger.info("Looking for MIME Configuration in, " + propFile);
        return MediaAppConfig.getConfigInstance(propFile);
    }

    /**
     * Creates a FileListenerConfig instance from a specified configuration
     * file.
     * 
     * @param propFile
     *            a .properties file containing the MIME configuration.
     * @return an instance of {@link FileListenerConfig}
     */
    public static FileListenerConfig getConfigInstance(String propFile) {
        FileListenerConfig config = new FileListenerConfig();
        String msg = null;
        String propVal = null;

        // Set MIME Database URL
        propVal = RMT2File.getPropertyValue(propFile, "mime.dbURL");
        logger.info("mime.dbURL = " + propVal);
        config.setDbUrl(propVal);

        // Set MIME file handler class
        propVal = RMT2File.getPropertyValue(propFile, "mime.handler");
        logger.info("mime.handler = " + propVal);
        config.setHandlerClass(propVal);

        // Set email results indicator
        boolean flag = false;
        try {
            flag = Boolean.parseBoolean(RMT2File.getPropertyValue(propFile,
                    "mime.emailResults"));
        } catch (Exception e) {
            flag = false;
        } finally {
            propVal = RMT2File.getPropertyValue(propFile, "mime.emailResults");
            logger.info("mime.emailResults = " + propVal);
            config.setEmailResults(flag);
        }

        // Set Reporting email recipient
        config.setReportEmail(RMT2File.getPropertyValue(propFile,
                "mime.reportEmail"));

        // Set Application code
        propVal = RMT2File.getPropertyValue(propFile, "mime.appCode");
        logger.info("mime.appCode = " + propVal);
        config.setAppCode(propVal);

        // Set module count
        int moduleCount = 0;
        try {
            moduleCount = Integer.parseInt(RMT2File.getPropertyValue(propFile,
                    "mime.moduleCount"));
        } catch (NumberFormatException e) {
            moduleCount = 10000;
            msg = "Module count for MIME listener could not be determined.  Defaulting to zero";
            MediaAppConfig.logger.warn(msg);
        } finally {
            propVal = RMT2File.getPropertyValue(propFile, "mime.moduleCount");
            logger.info("mime.moduleCount = " + propVal);
            config.setModuleCount(moduleCount);
        }

        // Load module specific configurations such as datasource, table,
        // primary key, foreign key hashes, and etc.
        for (int moduleNdx = 0; moduleNdx < moduleCount; moduleNdx++) {
            String moduleCode = RMT2File.getPropertyValue(propFile,
                    "mime.module." + moduleNdx);
            String filePattern = RMT2File.getPropertyValue(propFile,
                    "mime.module." + moduleNdx + ".filePattern");
            String dbUrl = RMT2File.getPropertyValue(propFile, "mime.module."
                    + moduleNdx + ".datasource.url");
            String dbDriver = RMT2File.getPropertyValue(propFile,
                    "mime.module." + moduleNdx + ".datasource.dbdriver");
            String table = RMT2File.getPropertyValue(propFile, "mime.module."
                    + moduleNdx + ".datasource.table");
            String primaryKey = RMT2File.getPropertyValue(propFile,
                    "mime.module." + moduleNdx + ".datasource.pk");
            String foreignKey = RMT2File.getPropertyValue(propFile,
                    "mime.module." + moduleNdx + ".datasource.fk");
            String dbUserId = RMT2File.getPropertyValue(propFile,
                    "mime.module." + moduleNdx + ".datasource.uid");
            String dbPassword = RMT2File.getPropertyValue(propFile,
                    "mime.module." + moduleNdx + ".datasource.pw");

            AppModuleConfig mod = new AppModuleConfig(moduleNdx);
            mod.setFilePattern(filePattern);
            mod.setDbUrl(dbUrl);
            mod.setDbDriver(dbDriver);
            mod.setTable(table);
            mod.setPrimaryKey(primaryKey);
            mod.setForeignKey(foreignKey);
            mod.setDbUserId(dbUserId);
            mod.setDbPassword(dbPassword);
            config.getModules().put(moduleNdx, mod);
        }

        // Set Polling frequency
        int pollFreq = 0;
        try {
            pollFreq = Integer.parseInt(RMT2File.getPropertyValue(propFile,
                    "mime.pollFreq"));
        } catch (NumberFormatException e) {
            pollFreq = 10000;
            msg = "Polling frequency for MIME listener could not be determined.  Defaulting to 10 seconds";
            MediaAppConfig.logger.error(msg);
        } finally {
            propVal = RMT2File.getPropertyValue(propFile, "mime.pollFreq");
            logger.info("mime.pollFreq = " + propVal);
            config.setPollFreq(pollFreq);
        }

        // Set In Bound Directory
        propVal = RMT2File.getPropertyValue(propFile, "mime.inboundDir");
        logger.info("mime.inboundDir = " + propVal);
        config.setInboundDir(propVal);

        // Set Out Bound Directory
        propVal = RMT2File.getPropertyValue(propFile, "mime.outboundDir");
        logger.info("mime.outboundDir = " + propVal);
        config.setOutboundDir(propVal);

        // Set Archive directory
        propVal = RMT2File.getPropertyValue(propFile, "mime.archiveDir");
        logger.info("mime.archiveDir = " + propVal);
        config.setArchiveDir(propVal);

        // Set archive age
        int archiveAge = 0;
        try {
            archiveAge = Integer.parseInt(RMT2File.getPropertyValue(propFile,
                    "mime.archiveAge"));
        } catch (NumberFormatException e) {
            archiveAge = 72;
            msg = "Archiving age for MIME listener could not be determined.  Defaulting to 72 hours";
            MediaAppConfig.logger.warn(msg);
        } finally {
            propVal = RMT2File.getPropertyValue(propFile, "mime.archiveAge");
            logger.info("mime.archiveAge = " + propVal);
            config.setArchiveAge(archiveAge);
        }
        return config;
    }

}
