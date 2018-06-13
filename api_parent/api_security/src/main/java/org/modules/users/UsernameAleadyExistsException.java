package org.modules.users;

import com.InvalidDataException;

/**
 * Used for the handling validation errors in cases where the username is about
 * to be duplicated.
 * 
 * @author RTerrell
 * 
 */
public class UsernameAleadyExistsException extends InvalidDataException {
    private static final long serialVersionUID = 2969536074770899864L;

    /**
     * 
     */
    public UsernameAleadyExistsException() {
        super();
    }

    /**
     * 
     * @param msg
     */
    public UsernameAleadyExistsException(String msg) {
        super(msg);
    }

    /**
     * 
     * @param e
     */
    public UsernameAleadyExistsException(Exception e) {
        super(e);
    }

    /**
     * 
     * @param msg
     * @param e
     */
    public UsernameAleadyExistsException(String msg, Throwable e) {
        super(msg, e);
    }
}
