package org.modules.employee;

import org.modules.ProjectTrackerModuleException;

/**
 * Handles employee related Project Tracker errors
 * 
 * @author Roy Terrell
 * 
 */
public class EmployeeApiException extends ProjectTrackerModuleException {

    private static final long serialVersionUID = 2653509661759927388L;

    /**
     * 
     */
    public EmployeeApiException() {
        super();
    }

    /**
     * @param msg
     */
    public EmployeeApiException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public EmployeeApiException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public EmployeeApiException(String msg, Throwable e) {
        super(msg, e);
    }

}
