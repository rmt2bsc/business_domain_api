package org.modules.timesheet;

/**
 * Handles events that fail validations.
 * 
 * @author Roy Terrell
 * 
 */
public class InvalidEventException extends TimesheetApiException {

    private static final long serialVersionUID = -5399306021195061423L;

    /**
     * 
     */
    public InvalidEventException() {
        super();
    }

    /**
     * @param msg
     */
    public InvalidEventException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public InvalidEventException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public InvalidEventException(String msg, Throwable e) {
        super(msg, e);
    }

}
