package org.modules.roles;

import org.modules.SecurityModuleException;

/**
 * An exception class for handling error pertaining to the Application Role api
 * module.
 * 
 * @author rterrell
 * 
 */
public class AppRoleApiException extends SecurityModuleException {
    private static final long serialVersionUID = 3146419736970380825L;

    /**
     * Default constructor that creates an AppRoleApiException object with a
     * null message.
     * 
     */
    public AppRoleApiException() {
        super();
    }

    /**
     * Creates an AppRoleApiException with a message.
     * 
     * @param msg
     *            The text message.
     */
    public AppRoleApiException(String msg) {
        super(msg);
    }

    /**
     * Creates an AppRoleApiException using an Exception.
     * 
     * @param e
     *            An Exception object.
     */
    public AppRoleApiException(Exception e) {
        super(e);
    }

    /**
     * Creates a AppRoleApiException using a message text and the causable
     * exception.
     * 
     * @param msg
     * @param e
     */
    public AppRoleApiException(String msg, Throwable e) {
        super(msg, e);
    }

}
