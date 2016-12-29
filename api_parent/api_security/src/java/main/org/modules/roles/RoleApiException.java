package org.modules.roles;

import org.modules.SecurityModuleException;

/**
 * An exception class for handling error pertaining to the Role api module.
 * 
 * @author rterrell
 * 
 */
public class RoleApiException extends SecurityModuleException {
    private static final long serialVersionUID = 3146419736970380825L;

    /**
     * Default constructor that creates an RoleApiException object with a null
     * message.
     * 
     */
    public RoleApiException() {
        super();
    }

    /**
     * Creates an RoleApiException with a message.
     * 
     * @param msg
     *            The text message.
     */
    public RoleApiException(String msg) {
        super(msg);
    }

    /**
     * Creates an RoleApiException using an Exception.
     * 
     * @param e
     *            An Exception object.
     */
    public RoleApiException(Exception e) {
        super(e);
    }

    /**
     * Creates a RoleApiException using a message text and the causable
     * exception.
     * 
     * @param msg
     * @param e
     */
    public RoleApiException(String msg, Throwable e) {
        super(msg, e);
    }

}
