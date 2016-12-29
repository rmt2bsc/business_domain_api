package org.modules;

import java.util.Properties;

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
public class ProjectTrackerConfigurator extends
        AbstractApiConfiguratorPropertiesImpl {

    private static Logger logger;

    /**
     * Creates an ProjectTrackerConfigurator.
     * <p>
     * Loads the configuration, ProjectTracker-AppParms.properties.
     * 
     * @throws com.SystemException
     *             error loading configuration.
     */
    public ProjectTrackerConfigurator() {
        this.configPath = "config.ProjectTracker-AppParms";
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
        logger = Logger.getLogger(ProjectTrackerConfigurator.class);
        logger.info("Logger initialized locally for application, "
                + this.appName);

    }

}
