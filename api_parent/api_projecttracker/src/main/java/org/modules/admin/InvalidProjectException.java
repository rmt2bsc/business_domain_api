package org.modules.admin;

/**
 * Handles projects that fail validations.
 * 
 * @author Roy Terrell
 * 
 */
public class InvalidProjectException extends ProjectAdminApiException {

    private static final long serialVersionUID = -5399306021195061423L;

    /**
     * 
     */
    public InvalidProjectException() {
        super();
    }

    /**
     * @param msg
     */
    public InvalidProjectException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public InvalidProjectException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public InvalidProjectException(String msg, Throwable e) {
        super(msg, e);
    }

}
