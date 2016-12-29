package org.modules.admin;

import org.modules.ProjectTrackerApiConst;

import com.RMT2Base;
import com.api.persistence.DaoClient;

/**
 * A factory for creating resources that pertain to the project administration
 * entities of the Project Tracker API module.
 * 
 * @author Roy Terrell
 * 
 */
public class ProjectApiFactory extends RMT2Base {

    /**
     * Default method.
     */
    public ProjectApiFactory() {
        return;
    }

    /**
     * Creates an instance of {@link ProjectApi} using the transaction api
     * implementation.
     * 
     * @return an instance of {@link ProjectApi}
     */
    public ProjectApi createApi() {
        // ProjectApiImpl api = new ProjectApiImpl();
        // return api;
        return this.createApi(ProjectTrackerApiConst.DEFAULT_CONTEXT_NAME);
    }

    /**
     * Creates an instance of {@link ProjectApi} using the transaction api
     * implementation.
     * 
     * @param the
     *            name of the application.
     * @return an instance of {@link ProjectApi}
     */
    public ProjectApi createApi(String appName) {
        ProjectApiImpl api = new ProjectApiImpl(appName);
        return api;
    }

    /**
     * Creates an instance of {@link ProjectApi} using the transaction api
     * implementation and initializing it with a DAO connection.
     * 
     * @param connection
     *            an instance of {@link DaoClient} used to share the DAO
     *            connection.
     * 
     *            ProjectApi * null.
     */
    public ProjectApi createApi(DaoClient connection) {
        if (connection == null) {
            return null;
        }
        ProjectApiImpl api = new ProjectApiImpl(connection);
        api.setApiUser(connection.getDaoUser());
        return api;
    }

}
