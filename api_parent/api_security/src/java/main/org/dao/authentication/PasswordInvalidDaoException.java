package org.dao.authentication;

import org.dao.SecurityDaoException;

/**
 * Represents invalid user password error.
 * 
 * @author RTerrell
 * 
 */
public class PasswordInvalidDaoException extends SecurityDaoException {
    private static final long serialVersionUID = 2969536074770899864L;

    /**
     * 
     */
    public PasswordInvalidDaoException() {
        super();
    }

    /**
     * 
     * @param msg
     */
    public PasswordInvalidDaoException(String msg) {
        super(msg);
    }

    /**
     * 
     * @param e
     */
    public PasswordInvalidDaoException(Exception e) {
        super(e);
    }

    /**
     * 
     * @param msg
     * @param e
     */
    public PasswordInvalidDaoException(String msg, Throwable e) {
        super(msg, e);
    }
}
