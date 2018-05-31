package org.dao.application;

import java.util.List;

import org.dao.SecurityDaoException;
import org.dto.ApplicationDto;

import com.api.persistence.DaoClient;

/**
 * Contract for accessing and managing data regarding the Application module of
 * the Security API.
 * 
 * @author rterrell
 * 
 */
public interface AppDao extends DaoClient {

    /**
     * Retrieves a list of application objects using various selection criteria.
     * 
     * @param criteria
     *            An instance of {@link ApplicationDto} containing the values
     *            needed to build selection criteria.
     * @return An List of {@link ApplicationDto} instances or null if no data is
     *         found.
     * @throws SecurityDaoException
     */
    List<ApplicationDto> fetchApp(ApplicationDto criteria) throws SecurityDaoException;
    
    /**
     * Creates or modifies an application object.
     * 
     * @param app
     *            An instance of {@link ApplicationDto} containing the data
     *            needed to update an application record.
     * @return The application id for new records or the total number of records
     *         effected by the update.
     * @throws SecurityDaoException
     */
    int maintainApp(ApplicationDto app) throws SecurityDaoException;

    
    /**
     * Delete an application from the system using the id of the application.
     * 
     * @param appId
     *            The id of the application
     * @return Total number of application records deleted.
     * @throws SecurityDaoException
     */
    int deleteApp(int appId) throws SecurityDaoException;

}