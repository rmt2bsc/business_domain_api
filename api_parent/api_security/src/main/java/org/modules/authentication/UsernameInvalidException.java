package org.modules.authentication;

import com.InvalidDataException;

/**
 * Manages invalid user name errors
 * 
 * @author RTerrell
 * 
 */
public class UsernameInvalidException extends InvalidDataException {
    private static final long serialVersionUID = 2969536074770899864L;

    /**
     * 
     */
    public UsernameInvalidException() {
        super();
    }

    /**
     * 
     * @param msg
     */
    public UsernameInvalidException(String msg) {
        super(msg);
    }

    /**
     * 
     * @param e
     */
    public UsernameInvalidException(Exception e) {
        super(e);
    }

    /**
     * 
     * @param msg
     * @param e
     */
    public UsernameInvalidException(String msg, Throwable e) {
        super(msg, e);
    }
}
