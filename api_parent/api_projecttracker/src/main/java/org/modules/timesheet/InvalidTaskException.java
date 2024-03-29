package org.modules.timesheet;

/**
 * Handles timesheet task that fail validations.
 * 
 * @author Roy Terrell
 * 
 */
public class InvalidTaskException extends TimesheetApiException {

    private static final long serialVersionUID = -5399306021195061423L;

    /**
     * 
     */
    public InvalidTaskException() {
        super();
    }

    /**
     * @param msg
     */
    public InvalidTaskException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public InvalidTaskException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public InvalidTaskException(String msg, Throwable e) {
        super(msg, e);
    }

}
