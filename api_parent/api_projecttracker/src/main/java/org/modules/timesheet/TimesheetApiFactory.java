package org.modules.timesheet;

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
public class TimesheetApiFactory extends RMT2Base {

    /**
     * Default method.
     */
    public TimesheetApiFactory() {
        return;
    }

    /**
     * Creates an instance of {@link TimesheetApi} using the transaction api
     * implementation.
     * 
     * @return an instance of {@link TimesheetApi}
     */
    public static final TimesheetApi createApi() {
        return TimesheetApiFactory.createApi(ProjectTrackerApiConst.DEFAULT_CONTEXT_NAME);
    }

    /**
     * Creates an instance of {@link TimesheetApi} using the transaction api
     * implementation.
     * 
     * @param appName
     *            the application name
     * @return an instance of {@link TimesheetApi}
     */
    public static final TimesheetApi createApi(String appName) {
        TimesheetApiImpl api = new TimesheetApiImpl(appName);
        return api;
    }

    /**
     * Creates an instance of {@link TimesheetApi} using the transaction api
     * implementation and initializing it with a DAO connection.
     * 
     * @param connection
     *            an instance of {@link DaoClient} used to share the DAO
     *            connection.
     * 
     * @return an instance of {@link TimesheetApi} or null when
     *         <i>connection</i> is null.
     */
    public static final TimesheetApi createApi(DaoClient connection) {
        if (connection == null) {
            return null;
        }
        TimesheetApiImpl api = new TimesheetApiImpl(connection);
        api.setApiUser(connection.getDaoUser());
        return api;
    }

    /**
     * Creates an instance of {@link TimesheetTransmissionApi} using the
     * transaction api implementation.
     * 
     * @return an instance of {@link TimesheetTransmissionApi}
     */
    @Deprecated
    public static final TimesheetTransmissionApi createTransmissionApi() {
        SmtpTimesheetTransmissionApiImpl api = new SmtpTimesheetTransmissionApiImpl();
        return api;
    }
}
