package org.dao.application;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.dao.SecurityDaoException;
import org.dao.SecurityDaoImpl;
import org.dao.mapping.orm.rmt2.Application;
import org.dto.ApplicationDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;

import com.InvalidDataException;
import com.RMT2Constants;
import com.api.persistence.DatabaseException;
import com.util.RMT2Date;
import com.util.UserTimestamp;

/**
 * An RMT2 ORM database implementation of the {@link AppDao} interface which
 * functions to manage application data and applicaltion user access profiles.
 * 
 * @author Roy Terrell
 * 
 */
class Rmt2OrmApplicationDaoImpl extends SecurityDaoImpl implements AppDao {

    private static final Logger logger = Logger
            .getLogger(Rmt2OrmApplicationDaoImpl.class);

    /**
     * Create an ApplicationDaoRmt2OrmImpl object.
     * 
     */
    protected Rmt2OrmApplicationDaoImpl() {
        super();
        return;
    }

    /**
     * Create an ApplicationDaoRmt2OrmImpl object.
     * 
     * @param appName
     *            application name
     */
    protected Rmt2OrmApplicationDaoImpl(String appName) {
        super(appName);
        return;
    }

    /**
     * Find an application record using primary key id.
     * 
     * @param uid
     *            A unique id identifying an application.
     * @return An {@link Application}
     * @throws ApplicationDaoException
     *             General database errors.
     */
    public ApplicationDto fetchApp(int uid) throws AppDaoException {
        Application app = new Application();
        app.addCriteria(Application.PROP_APPID, uid);
        try {
            Application data = (Application) this.client.retrieveObject(app);
            if (data == null) {
                return null;
            }
            else {
                return Rmt2OrmDtoFactory.getAppDtoInstance(data);
            }
        } catch (DatabaseException e) {
            throw new AppDaoException(e);
        }
    }

    /**
     * Find application using application name.
     * 
     * @param appName
     *            The name of the applicatio to query.
     * @return An instance of {@link ApplicationDto} containing the application
     *         data.
     * @throws ApplicationDaoException
     *             When appName is null or general database errors
     */
    public ApplicationDto fetchApp(String appName) throws AppDaoException {
        if (appName == null) {
            throw new AppDaoException(
                    "Application name is required as an input parameter when fetching list of applications by application name");
        }
        Application app = new Application();
        app.addCriteria(Application.PROP_NAME, appName);
        try {
            Application data = (Application) this.client.retrieveObject(app);
            if (data == null) {
                return null;
            }
            else {
                return Rmt2OrmDtoFactory.getAppDtoInstance(data);
            }
        } catch (DatabaseException e) {
            throw new AppDaoException(e);
        }
    }

    /**
     * Fetches the complete list of application records.
     * 
     * @return A List of {@link ApplicationDto} containing application related
     *         data.
     * @throws ApplicationDaoException
     *             General database errors
     */
    public List<ApplicationDto> fetchApp() throws AppDaoException {
        Application app = new Application();
        app.addOrderBy(Application.PROP_NAME, Application.ORDERBY_ASCENDING);
        List<Application> apps = null;
        try {
            apps = this.client.retrieveList(app);
            if (apps == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new AppDaoException(e);
        }

        List<ApplicationDto> list = new ArrayList<ApplicationDto>();
        for (Application item : apps) {
            ApplicationDto dto = Rmt2OrmDtoFactory.getAppDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Creates a new or modifies an existing record in the application table.
     * <p>
     * A SQL insert is performed when the primary key is equal to zero. When the
     * primary key id is greater than zero, an SQL update is applied.
     * 
     * @param appDto
     *            an instance of {@link ApplicationDto} containing data that
     *            will be mapped to an {@link Application} instance targeted for
     *            a insert/update operation.
     * @return The application id for an insert operation or the total number of
     *         rows effected for an update operation.
     * @throws ApplicationDaoException
     */
    public int maintainApp(ApplicationDto appDto) throws AppDaoException {
        if (appDto == null) {
            throw new AppDaoException("The input parameter is required");
        }

        // Transfer the DTO data to an instance of Application.
        Application app = new Application();
        app.setAppId(appDto.getApplicationId());
        app.setName(appDto.getAppName());
        app.setDescription(appDto.getAppDescription());
        app.setUserId(appDto.getUpdateUserId());

        // Perform update transaction
        int rc = 0;
        this.client.beginTrans();
        try {
            if (app.getAppId() > 0) {
                rc = this.update(app);
            }
            if (app.getAppId() == 0) {
                rc = this.insert(app);
                app.setAppId(rc);
                appDto.setApplicationId(rc);
            }
            this.client.commitTrans();
            return rc;
        } catch (Exception e) {
            this.client.rollbackTrans();
            throw new AppDaoException("Application Module DAO update failed", e);
        }
    }

    /**
     * Adds an application record to the database,.
     * 
     * @param app
     *            {@link org.dao.bean.Application Application}.
     * @return int the application id of the new record.
     * @throws DatabaseException
     */
    private int insert(Application app) throws DatabaseException {

        UserTimestamp ut = null;
        try {
            this.validate(app);
            ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            app.setDateCreated(ut.getDateCreated());
            app.setDateUpdated(ut.getDateCreated());
            app.setUserId(ut.getLoginId());
            int newId = this.client.insertRow(app, true);
            return newId;
        } catch (Exception e) {
            this.msg = "Unable to insert Application record into database";
            throw new DatabaseException(this.msg, e);
        }
    }

    /**
     * Updates one an application record in the database.
     * 
     * @param app
     *            {@link org.dao.bean.Application Application}.
     * @return int the total number of rows effected by the SQL update
     * @throws DatabaseException
     *             for database and system errors.
     */
    private int update(Application app) throws DatabaseException {
        UserTimestamp ut = null;
        try {
            this.validate(app);
            ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            app.setDateUpdated(ut.getDateCreated());
            app.setUserId(ut.getLoginId());
            app.addCriteria(Application.PROP_APPID, app.getAppId());
            return this.client.updateRow(app);
        } catch (Exception e) {
            this.msg = "DatabaseExeception: " + e.getMessage();
            logger.log(Level.ERROR, this.msg);
            e.printStackTrace();
            throw new DatabaseException(this.msg, e);
        }
    }

    /**
     * This method is responsble for validating an application profile.
     * <p>
     * The name and description of the application are to have values.
     * 
     * @param app
     *            {@link org.dao.bean.Application Application}
     * @throws InvalidDataException
     */
    private void validate(Application app) throws InvalidDataException {
        if (app.getName() == null || app.getName().length() <= 0) {
            this.msg = "Application name cannot be blank";
            logger.log(Level.ERROR, this.msg);
            throw new InvalidDataException(this.msg);
        }

        if (app.getDescription() == null || app.getDescription().length() <= 0) {
            this.msg = "User Maintenance: Description cannot be blank";
            logger.log(Level.ERROR, this.msg);
            throw new InvalidDataException(this.msg);
        }
    }

    /**
     * Delete a record from the application table using the application id
     * 
     * @param appId
     *            The id of the application to delete.
     * @return Total number of rows deleted.
     * @throws ApplicationDaoException
     */
    public int deleteApp(int appId) throws AppDaoException {
        if (appId <= 0) {
            this.msg = "Application object delete failure.  Application id is invalid: "
                    + appId;
            throw new AppDaoException(this.msg);
        }

        // Start transaction
        int rc = 0;
        this.client.beginTrans();
        try {
            Application app = new Application();
            app.addCriteria(Application.PROP_APPID, appId);
            rc = this.client.deleteRow(app);
            this.client.commitTrans();
            return rc;
        } catch (DatabaseException e) {
            this.client.rollbackTrans();
            throw new AppDaoException("Unable to delete Application record, "
                    + appId + ", due to database error", e);
        }
    }

    /**
     * Not Supported.
     */
    @Override
    public int deleteApp(String appName) throws SecurityDaoException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

}