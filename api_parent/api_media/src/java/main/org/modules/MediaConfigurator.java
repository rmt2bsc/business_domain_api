package org.modules;

import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.api.config.AbstractApiConfiguratorPropertiesImpl;
import com.api.config.ConfigConstants;
import com.api.config.ConfigException;
import com.util.RMT2File;

/**
 * @author appdev
 *
 */
public class MediaConfigurator extends AbstractApiConfiguratorPropertiesImpl {

    private static Logger logger;

    private static final String CONFIG_PATH = "config.Media-AppParms";

    private ResourceBundle config;

    /**
     * Creates an MediaConfigurator.
     * <p>
     * Loads the configuration, Media-AppParms.properties.
     * 
     * @throws com.SystemException
     *             error loading configuration.
     */
    public MediaConfigurator() {
        this.configPath = "config.Media-AppParms";
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
        String logPath = this.config
                .getString(ConfigConstants.API_LOGGER_CONFIG_PATH_KEY);
        Properties prop = RMT2File.loadPropertiesFromClasspath(logPath);
        PropertyConfigurator.configure(prop);
        logger = Logger.getLogger(MediaConfigurator.class);
        logger.info("Logger initialized locally for application, "
                + this.appName);

    }
}
