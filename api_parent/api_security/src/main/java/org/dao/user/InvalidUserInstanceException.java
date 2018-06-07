package org.dao.user;

import com.InvalidDataException;

/**
 * Used for the handling validation errors regarding the User.
 * 
 * @author RTerrell
 * 
 */
public class InvalidUserInstanceException extends InvalidDataException {
    private static final long serialVersionUID = 2969536074770899864L;

    /**
     * 
     */
    public InvalidUserInstanceException() {
        super();
    }

    /**
     * 
     * @param msg
     */
    public InvalidUserInstanceException(String msg) {
        super(msg);
    }

    /**
     * 
     * @param e
     */
    public InvalidUserInstanceException(Exception e) {
        super(e);
    }

    /**
     * 
     * @param msg
     * @param e
     */
    public InvalidUserInstanceException(String msg, Throwable e) {
        super(msg, e);
    }
}
