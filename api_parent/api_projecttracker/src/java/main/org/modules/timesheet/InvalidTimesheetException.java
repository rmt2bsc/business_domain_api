package org.modules.timesheet;

/**
 * Represents exceptions regarding an invalid timesheet.
 * 
 * @author Roy Terrell
 * 
 */
public class InvalidTimesheetException extends TimesheetApiException {
    private static final long serialVersionUID = -8851874846044566159L;

    public InvalidTimesheetException() {
        super();
    }

    public InvalidTimesheetException(String msg) {
        super(msg);
    }

    public InvalidTimesheetException(String msg, Throwable e) {
        super(msg, e);
    }

    public InvalidTimesheetException(Exception e) {
        super(e);
    }
}
