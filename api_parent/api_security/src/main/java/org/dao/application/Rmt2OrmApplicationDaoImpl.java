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

import com.api.persistence.DatabaseException;
import com.api.persistence.PersistenceClient;
import com.api.util.RMT2Date;
import com.api.util.RMT2Money;
import com.api.util.UserTimestamp;

/**
 * An RMT2 ORM database implementation of the {@link AppDao} interface which
 * functions to manage application data and applicaltion user access profiles.
 * 
 * @author Roy Terrell
 * 
 */
class Rmt2OrmApplicationDaoImpl extends SecurityDaoImpl implements AppDao {

    private static final Logger logger = Logger.getLogger(Rmt2OrmApplicationDaoImpl.class);

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
     * Create an ApplicationDaoRmt2OrmImpl object.
     * 
     * @param client
     */
    public Rmt2OrmApplicationDaoImpl(PersistenceClient client) {
        super(client);
    }

    private ApplicationDto fetchApp(int appId) throws SecurityDaoException {
        Application app = AppDaoFactory.createCriteria(null);
        app.addCriteria(Application.PROP_APPID, appId);
        app.addOrderBy(Application.PROP_NAME, Application.ORDERBY_ASCENDING);
        Application results = null;
        try {
            results = (Application) this.client.retrieveObject(app);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new AppDaoException(e);
        }
        ApplicationDto dto = Rmt2OrmDtoFactory.getAppDtoInstance(results);
        return dto;
    }

    @Override
    public List<ApplicationDto> fetchApp(ApplicationDto criteria) throws SecurityDaoException {
        Application app = AppDaoFactory.createCriteria(criteria);
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
    @Override
    public int maintainApp(ApplicationDto appDto) throws AppDaoException {
        if (appDto == null) {
            throw new AppDaoException("The input parameter is required");
        }

        // Transfer the DTO data to an instance of Application.
        Application app = new Application();
        app.setAppId(appDto.getApplicationId());
        app.setName(appDto.getAppName());
        app.setDescription(appDto.getAppDescription());
        app.setActive((appDto.getActive() != null && RMT2Money.isNumeric(appDto.getActive()) ? Integer.valueOf(appDto.getActive())
                : 1));
        app.setUserId(appDto.getUpdateUserId());

        // Perform update transaction
        int rc = 0;
        try {
            if (app.getAppId() > 0) {
                rc = this.update(app);
            }
            if (app.getAppId() == 0) {
                rc = this.insert(app);
                app.setAppId(rc);
                appDto.setApplicationId(rc);
            }
            return rc;
        } catch (Exception e) {
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
        ApplicationDto orig = this.fetchApp(app.getAppId());
        app.setDateCreated(orig.getDateCreated());
        UserTimestamp ut = null;
        try {
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
     * Delete a record from the application table using the application id
     * 
     * @param appId
     *            The id of the application to delete.
     * @return Total number of rows deleted.
     * @throws ApplicationDaoException
     */
    @Override
    public int deleteApp(int appId) throws AppDaoException {
        int rc = 0;
        try {
            Application app = new Application();
            app.addCriteria(Application.PROP_APPID, appId);
            rc = this.client.deleteRow(app);
            this.client.commitTrans();
            return rc;
        } catch (DatabaseException e) {
            throw new AppDaoException("Unable to delete Application record, " + appId + ", due to database error", e);
        }
    }
}