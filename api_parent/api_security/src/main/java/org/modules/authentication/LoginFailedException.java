package org.modules.authentication;

import com.RMT2RuntimeException;

/**
 * Handles user login failures
 * 
 * @author RTerrell
 * 
 */
public class LoginFailedException extends RMT2RuntimeException {
    private static final long serialVersionUID = 2969536074770899864L;

    /**
     * 
     */
    public LoginFailedException() {
        super();
    }

    /**
     * 
     * @param msg
     */
    public LoginFailedException(String msg) {
        super(msg);
    }

    /**
     * 
     * @param e
     */
    public LoginFailedException(Exception e) {
        super(e);
    }

    /**
     * 
     * @param msg
     * @param e
     */
    public LoginFailedException(String msg, Throwable e) {
        super(msg, e);
    }
}
