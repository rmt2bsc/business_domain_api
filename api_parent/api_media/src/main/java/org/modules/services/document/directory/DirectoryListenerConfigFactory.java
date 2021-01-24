package org.modules.services.document.directory;

import org.apache.log4j.Logger;
import org.modules.MediaConstants;

import com.RMT2Base;
import com.api.config.ConfigConstants;
import com.api.util.RMT2File;

/**
 * Factory class for creating entities related to configuring the
 * DocumentInboundDirectoryListener.
 * 
 * @author roy.terrell
 * 
 */
public class DirectoryListenerConfigFactory extends RMT2Base {

    private static Logger logger = Logger.getLogger(DirectoryListenerConfigFactory.class);

    public static final String configFile = "BatchImportConfig";

    public static String ENV = System.getProperty(ConfigConstants.PROPNAME_ENV);

    /**
     * Create DirectoryListenerConfigFactory
     */
    public DirectoryListenerConfigFactory() {
        DirectoryListenerConfigFactory.logger.info("Logger initialized");
    }

    /**
     * Creates a FileListenerConfig instance from the default MIME configuration
     * proerties file, MimeConfig_<environment indicator>.properties.
     * 
     * @return {@link FileListenerConfig}
     */
    public static DirectoryListenerConfigBean getDocumentListenerConfigBeanInstance() {
        // Setup to use default MimeConfig.properties file
        String propFile = MediaConstants.CONFIG_CLASSPATH + "."  + DirectoryListenerConfigFactory.configFile; 

        // When environment is production, use the default configuration file,
        // BatchImportConfig.properties
        if (ENV != null && !ENV.equalsIgnoreCase(ConfigConstants.ENVTYPE_PROD)
                && !ENV.equalsIgnoreCase(ConfigConstants.ENVTYPE_DEV)) {
            // Use environment specific MimeConfig_<ENV>.properties file
            propFile += "_" + DirectoryListenerConfigFactory.ENV;
        }

        logger.info("Looking for MIME Configuration in, " + propFile);
        return DirectoryListenerConfigFactory.getDocumentListenerConfigBeanInstance(propFile);
    }

    /**
     * Creates a FileListenerConfig instance from a specified configuration
     * file.
     * 
     * @param propFile
     *            a .properties file containing the MIME configuration.
     * @return an instance of {@link FileListenerConfig}
     */
    public static DirectoryListenerConfigBean getDocumentListenerConfigBeanInstance(String propFile) {
        DirectoryListenerConfigBean config = new DirectoryListenerConfigBean();
        String msg = null;
        String propVal = null;

        // Set email results indicator
        boolean flag = false;
        try {
            flag = Boolean.parseBoolean(RMT2File.getPropertyValue(propFile, "mime.emailResults"));
        } catch (Exception e) {
            flag = false;
        } finally {
            logger.info("mime.emailResults = " + flag);
            config.setEmailResults(flag);
        }

        // Set Reporting email sender
        config.setEmailSender(RMT2File.getPropertyValue(propFile, "mime.emailSender"));
        // Set Reporting email recipient
        config.setEmailRecipients(RMT2File.getPropertyValue(propFile, "mime.emailRecipients"));

        // Set module count
        int moduleCount = 0;
        try {
            moduleCount = Integer.parseInt(RMT2File.getPropertyValue(propFile, "mime.moduleCount"));
        } catch (NumberFormatException e) {
            moduleCount = 10000;
            msg = "Module count for MIME listener could not be determined.  Defaulting to zero";
            DirectoryListenerConfigFactory.logger.warn(msg);
        } finally {
            propVal = RMT2File.getPropertyValue(propFile, "mime.moduleCount");
            logger.info("mime.moduleCount = " + propVal);
            config.setModuleCount(moduleCount);
        }

        // Load module specific configurations such as datasource, table,
        // primary key, foreign key hashes, and etc.
        for (int moduleNdx = 0; moduleNdx < moduleCount; moduleNdx++) {
            String filePattern = RMT2File.getPropertyValue(propFile, "mime.module." + moduleNdx + ".filePattern");
            String moduleName = RMT2File.getPropertyValue(propFile, "mime.module." + moduleNdx + ".moduleName");
            String projectName = RMT2File.getPropertyValue(propFile, "mime.module." + moduleNdx + ".projectName");
            String entityUid = RMT2File.getPropertyValue(propFile, "mime.module." + moduleNdx + ".entityUid");
            String moduleCode = RMT2File.getPropertyValue(propFile, "mime.module." + moduleNdx);
            ApplicationModuleBean mod = new ApplicationModuleBean(moduleCode);
            mod.setFilePattern(filePattern);
            mod.setProjectName(projectName);
            mod.setEntityUid(entityUid);
            mod.setModuleName(moduleName);
            config.getModules().put(moduleNdx, mod);
        }

        // Set Polling frequency
        int pollFreq = 0;
        try {
            pollFreq = Integer.parseInt(RMT2File.getPropertyValue(propFile,"mime.pollFreq"));
        } catch (NumberFormatException e) {
            pollFreq = 10000;
            msg = "Polling frequency for MIME listener could not be determined.  Defaulting to 10 seconds";
            DirectoryListenerConfigFactory.logger.error(msg);
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
            archiveAge = Integer.parseInt(RMT2File.getPropertyValue(propFile, "mime.archiveAge"));
        } catch (NumberFormatException e) {
            archiveAge = 72;
            msg = "Archiving age for MIME listener could not be determined.  Defaulting to 72 hours";
            DirectoryListenerConfigFactory.logger.warn(msg);
        } finally {
            propVal = RMT2File.getPropertyValue(propFile, "mime.archiveAge");
            logger.info("mime.archiveAge = " + propVal);
            config.setArchiveAge(archiveAge);
        }
        return config;
    }

}
