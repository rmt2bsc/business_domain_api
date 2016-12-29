package org.modules.timesheet;

import org.modules.ProjectTrackerModuleException;

/**
 * Handles employee related Project Tracker errors
 * 
 * @author Roy Terrell
 * 
 */
public class TimesheetApiException extends ProjectTrackerModuleException {

    private static final long serialVersionUID = 2653509661759927388L;

    /**
     * 
     */
    public TimesheetApiException() {
        super();
    }

    /**
     * @param msg
     */
    public TimesheetApiException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public TimesheetApiException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public TimesheetApiException(String msg, Throwable e) {
        super(msg, e);
    }

}
