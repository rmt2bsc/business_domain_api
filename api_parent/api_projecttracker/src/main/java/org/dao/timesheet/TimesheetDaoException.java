package org.dao.timesheet;

import org.dao.ProjecttrackerDaoException;

/**
 * The exception class for handling errors pertaining to the Timesheet DAO
 * component.
 * 
 * @author Roy Terrell
 * 
 */
public class TimesheetDaoException extends ProjecttrackerDaoException {

    private static final long serialVersionUID = -9121106275070847124L;

    /**
     * 
     */
    public TimesheetDaoException() {
        super();
    }

    /**
     * @param msg
     */
    public TimesheetDaoException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public TimesheetDaoException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public TimesheetDaoException(String msg, Throwable e) {
        super(msg, e);
    }

}
