package org.modules.employee;

import org.modules.ProjectTrackerApiConst;

import com.RMT2Base;
import com.api.persistence.DaoClient;

/**
 * A factory for creating resources that pertain to the employee entities of the
 * Project Tracker API module.
 * 
 * @author Roy Terrell
 * 
 */
public class EmployeeApiFactory extends RMT2Base {

    /**
     * Default method.
     */
    public EmployeeApiFactory() {
        return;
    }

    /**
     * Creates an instance of {@link EmployeeApi} using the transaction api
     * implementation.
     * 
     * @return an instance of {@link EmployeeApi}
     */
    public EmployeeApi createApi() {
        // EmployeeApiImpl api = new EmployeeApiImpl();
        // return api;
        return this.createApi(ProjectTrackerApiConst.DEFAULT_CONTEXT_NAME);
    }

    /**
     * Creates an instance of {@link EmployeeApi} using the transaction api
     * implementation.
     * 
     * @param appName
     *            application name.
     * @return an instance of {@link EmployeeApi}
     */
    public EmployeeApi createApi(String appName) {
        EmployeeApiImpl api = new EmployeeApiImpl(appName);
        return api;
    }

    /**
     * Creates an instance of {@link EmployeeApi} using the transaction api
     * implementation and initializing it with a DAO connection.
     * 
     * @param connection
     *            an instance of {@link DaoClient} used to share the DAO
     *            connection.
     * 
     *            ProjectApi * null.
     */
    public EmployeeApi createApi(DaoClient connection) {
        if (connection == null) {
            return null;
        }
        EmployeeApiImpl api = new EmployeeApiImpl(connection);
        api.setApiUser(connection.getDaoUser());
        return api;
    }

}
