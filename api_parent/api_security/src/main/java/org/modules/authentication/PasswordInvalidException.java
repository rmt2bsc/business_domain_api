package org.modules.authentication;

import com.InvalidDataException;

/**
 * Represents invalid user password error.
 * 
 * @author RTerrell
 * 
 */
public class PasswordInvalidException extends InvalidDataException {
    private static final long serialVersionUID = 2969536074770899864L;

    /**
     * 
     */
    public PasswordInvalidException() {
        super();
    }

    /**
     * 
     * @param msg
     */
    public PasswordInvalidException(String msg) {
        super(msg);
    }

    /**
     * 
     * @param e
     */
    public PasswordInvalidException(Exception e) {
        super(e);
    }

    /**
     * 
     * @param msg
     * @param e
     */
    public PasswordInvalidException(String msg, Throwable e) {
        super(msg, e);
    }
}
