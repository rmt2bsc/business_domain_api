package org.modules.users;

import org.modules.SecurityModuleException;

/**
 * An exception class for handling errors pertaining to the User and Group Api
 * module.
 * 
 * @author rterrell
 * 
 */
public class UserApiException extends SecurityModuleException {
    private static final long serialVersionUID = 3146419736970380825L;

    /**
     * Default constructor that creates an UserApiException object with a null
     * message.
     * 
     */
    public UserApiException() {
        super();
    }

    /**
     * Creates an UserApiException with a message.
     * 
     * @param msg
     *            The text message.
     */
    public UserApiException(String msg) {
        super(msg);
    }

    /**
     * Creates an UserApiException using an Exception.
     * 
     * @param e
     *            An Exception object.
     */
    public UserApiException(Exception e) {
        super(e);
    }

    /**
     * Creates a UserApiException using a message text and the causable
     * exception.
     * 
     * @param msg
     * @param e
     */
    public UserApiException(String msg, Throwable e) {
        super(msg, e);
    }

}
