package org.modules.timesheet;

import com.InvalidDataException;

/**
 * Handles timesheet project/tasks that fail validations.
 * 
 * @author Roy Terrell
 * 
 */
public class InvalidProjectTaskException extends InvalidDataException {

    private static final long serialVersionUID = -5399306021195061423L;

    /**
     * 
     */
    public InvalidProjectTaskException() {
        super();
    }

    /**
     * @param msg
     */
    public InvalidProjectTaskException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public InvalidProjectTaskException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public InvalidProjectTaskException(String msg, Throwable e) {
        super(msg, e);
    }

}
