package org.dao.authentication;

import org.dao.SecurityDaoException;

/**
 * Manages invaoid user name errors
 * 
 * @author RTerrell
 * 
 */
public class UsernameInvalidDaoException extends SecurityDaoException {
    private static final long serialVersionUID = 2969536074770899864L;

    /**
     * 
     */
    public UsernameInvalidDaoException() {
        super();
    }

    /**
     * 
     * @param msg
     */
    public UsernameInvalidDaoException(String msg) {
        super(msg);
    }

    /**
     * 
     * @param e
     */
    public UsernameInvalidDaoException(Exception e) {
        super(e);
    }

    /**
     * 
     * @param msg
     * @param e
     */
    public UsernameInvalidDaoException(String msg, Throwable e) {
        super(msg, e);
    }
}
