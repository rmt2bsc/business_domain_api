package org.modules;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.api.config.AbstractApiConfiguratorPropertiesImpl;
import com.api.config.ConfigConstants;
import com.api.config.ConfigException;
import com.util.RMT2File;

/**
 * Configurator for initializing the AddressBook API.
 * <p>
 * This implementation basically sets up the logging environment. The core level
 * logic handles the remaining tasks needed to bootstrap this API.
 * 
 * @author roy terrell
 * 
 */
public class AddressBookConfigurator extends AbstractApiConfiguratorPropertiesImpl {

    private static Logger logger;

    /**
     * Creates an AddressBookConfigurator.
     * <p>
     * Loads the configuration, AddressBook-AppParms.properties.
     * 
     * @throws com.SystemException
     *             error loading configuration.
     */
    public AddressBookConfigurator() {
        this.configPath = "config.AddressBook-AppParms";
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
        logger = Logger.getLogger(AddressBookConfigurator.class);
        logger.info("Logger initialized locally for application, " + this.appName);

    }

}
