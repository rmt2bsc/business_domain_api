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
     * Find application using primary key id.
     * 
     * @param uid
     *            A unique id identifying an application.
     * @return An instance of {@link ApplicationDto} conatining the desired
     *         application data or null if no data is found.
     * @throws SecurityDaoException
     */
    ApplicationDto fetchApp(int uid) throws SecurityDaoException;

    /**
     * Find application using application name.
     * 
     * @param appName
     * @return An instance of {@link ApplicationDto} conatining the desired
     *         application data or null if no data is found.
     * @throws SecurityDaoException
     */
    ApplicationDto fetchApp(String appName) throws SecurityDaoException;

    /**
     * Fetches all applications in the system.
     * 
     * @return A List of {@link ApplicationDto} objects conatining the desired
     *         application data or null if no data is found.
     * @throws SecurityDaoException
     */
    List<ApplicationDto> fetchApp() throws SecurityDaoException;

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

    /**
     * Delete an application from the system using its name
     * 
     * @param appName
     *            The name of the application
     * @return Total number of application records deleted.
     * @throws SecurityDaoException
     */
    int deleteApp(String appName) throws SecurityDaoException;
}