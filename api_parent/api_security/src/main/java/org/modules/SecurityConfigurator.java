package org.modules;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.api.config.AbstractApiConfiguratorPropertiesImpl;
import com.api.config.ConfigConstants;
import com.api.config.ConfigException;
import com.api.util.RMT2File;

/**
 * Configurator for initializing the Security API.
 * <p>
 * This implementation basically sets up the logging environment. The core level
 * logic handles the remaining tasks needed to bootstrap this API.
 * 
 * @author roy terrell
 * 
 */
public class SecurityConfigurator extends AbstractApiConfiguratorPropertiesImpl {

    private static Logger logger;

    /**
     * Creates an SecurityConfigurator.
     * <p>
     * Loads the configuration, Security-AppParms.properties.
     * 
     * @throws com.SystemException
     *             error loading configuration.
     */
    public SecurityConfigurator() {
        this.configPath = "config.Security-AppParms";
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
        logger = Logger.getLogger(SecurityConfigurator.class);
        logger.info("Logger initialized locally for application, " + this.appName);

    }
}
