package org.modules.employee;

/**
 * Handles an invalid employee.
 * 
 * @author Roy Terrell
 * 
 */
public class InvalidEmployeeException extends EmployeeApiException {

    private static final long serialVersionUID = -4636310379147625769L;

    /**
     * 
     */
    public InvalidEmployeeException() {
        super();
    }

    /**
     * @param msg
     */
    public InvalidEmployeeException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public InvalidEmployeeException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public InvalidEmployeeException(String msg, Throwable e) {
        super(msg, e);
    }

}
