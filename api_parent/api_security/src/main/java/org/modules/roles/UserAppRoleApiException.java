package org.modules.roles;

import org.modules.SecurityModuleException;

/**
 * An exception class for handling error pertaining to the User Application Role
 * api module.
 * 
 * @author rterrell
 * 
 */
public class UserAppRoleApiException extends SecurityModuleException {
    private static final long serialVersionUID = 3146419736970380825L;

    /**
     * Default constructor that creates an UserAppRoleApiException object with a
     * null message.
     * 
     */
    public UserAppRoleApiException() {
        super();
    }

    /**
     * Creates an UserAppRoleApiException with a message.
     * 
     * @param msg
     *            The text message.
     */
    public UserAppRoleApiException(String msg) {
        super(msg);
    }

    /**
     * Creates an UserAppRoleApiException using an Exception.
     * 
     * @param e
     *            An Exception object.
     */
    public UserAppRoleApiException(Exception e) {
        super(e);
    }

    /**
     * Creates a UserAppRoleApiException using a message text and the causable
     * exception.
     * 
     * @param msg
     * @param e
     */
    public UserAppRoleApiException(String msg, Throwable e) {
        super(msg, e);
    }

}
