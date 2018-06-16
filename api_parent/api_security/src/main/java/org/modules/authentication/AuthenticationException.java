package org.modules.authentication;

import org.modules.SecurityModuleException;

/**
 * Exception class for handling general user authentication errors.
 * 
 * @author rterrell
 * 
 */
public class AuthenticationException extends SecurityModuleException {
    private static final long serialVersionUID = 3146419736970380825L;

    /**
     * Default constructor that creates an AuthenticationException object with a
     * null message.
     * 
     */
    public AuthenticationException() {
        super();
    }

    /**
     * Creates an AuthenticationException with a message.
     * 
     * @param msg
     *            The text message.
     */
    public AuthenticationException(String msg) {
        super(msg);
    }

    /**
     * Creates an AuthenticationException using an Exception.
     * 
     * @param e
     *            An Exception object.
     */
    public AuthenticationException(Exception e) {
        super(e);
    }

    /**
     * Creates a new AuthenticationException with a the specified message and
     * the causing throwable instance.
     * 
     * @param msg
     *            the message that explains the error.
     * @param cause
     *            the cause (which is saved for later retrieval by the
     *            Throwable.getCause() method). (A null value is permitted, and
     *            indicates that the cause is nonexistent or unknown.)
     * 
     */
    public AuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
