package org.modules.timesheet;

/**
 * Handles invalid status change attempts for a given timesheet.
 * 
 * @author Roy Terrell
 * 
 */
public class InvalidStatusChangeException extends TimesheetApiException {

    private static final long serialVersionUID = -5399306021195061423L;

    /**
     * 
     */
    public InvalidStatusChangeException() {
        super();
    }

    /**
     * @param msg
     */
    public InvalidStatusChangeException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public InvalidStatusChangeException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public InvalidStatusChangeException(String msg, Throwable e) {
        super(msg, e);
    }

}
