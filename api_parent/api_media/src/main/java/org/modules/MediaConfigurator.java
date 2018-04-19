package org.modules;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.modules.document.DocumentContentApiFactory;

import com.api.config.AbstractApiConfiguratorPropertiesImpl;
import com.api.config.ConfigConstants;
import com.api.config.ConfigException;
import com.util.RMT2File;

/**
 * Configurator for initializing the Media API.
 * <p>
 * This implementation basically sets up the logging environment. The core level
 * logic handles the remaining tasks needed to bootstrap this API.
 * 
 * @author roy terrell
 * 
 */
public class MediaConfigurator extends AbstractApiConfiguratorPropertiesImpl {

    private static Logger logger;

    private static final String CONFIG_PATH = "config.Media-AppParms";

    /**
     * Creates an MediaConfigurator.
     * <p>
     * Loads the configuration, Media-AppParms.properties.
     * 
     * @throws com.SystemException
     *             error loading configuration.
     */
    public MediaConfigurator() {
        this.configPath = MediaConfigurator.CONFIG_PATH;
    }

    /**
     * Initializes the api's environment using the loaded configuration.
     */
    @Override
    public void start() throws ConfigException {
        super.start();
    }

    /**
     * Setup logging environment for API locally
     */
    @Override
    protected void setupLogger() {
        // Setup Logging environment
        String logPath = this.config.getString(ConfigConstants.API_LOGGER_CONFIG_PATH_KEY);
        Properties prop = RMT2File.loadPropertiesFromClasspath(logPath);
        PropertyConfigurator.configure(prop);
        logger = Logger.getLogger(MediaConfigurator.class);
        logger.info("Logger initialized locally for application, " + this.appName);

    }

    /**
     * Responsible for starting the media directory/file listener
     */
    @Override
    protected void doPostStart() {
        super.doPostStart();
        // Start Media inbound directory file listener
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        f.createMediaContentApi().startMediaFileListener();
    }
}
