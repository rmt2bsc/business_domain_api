package org.modules.employee;

/**
 * Handles an invalid project/employee.
 * 
 * @author Roy Terrell
 * 
 */
public class InvalidProjectEmployeeException extends EmployeeApiException {

    private static final long serialVersionUID = -4636310379147625769L;

    /**
     * 
     */
    public InvalidProjectEmployeeException() {
        super();
    }

    /**
     * @param msg
     */
    public InvalidProjectEmployeeException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public InvalidProjectEmployeeException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public InvalidProjectEmployeeException(String msg, Throwable e) {
        super(msg, e);
    }

}
