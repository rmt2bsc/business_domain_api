package org.dao.user;

import org.dao.SecurityDaoException;

/**
 * Manages User DAO accessiblity errors
 * 
 * @author RTerrell
 * 
 */
public class UserDaoException extends SecurityDaoException {
    private static final long serialVersionUID = 2969536074770899864L;

    /**
     * 
     */
    public UserDaoException() {
        super();
    }

    /**
     * 
     * @param msg
     */
    public UserDaoException(String msg) {
        super(msg);
    }

    /**
     * 
     * @param e
     */
    public UserDaoException(Exception e) {
        super(e);
    }

    /**
     * 
     * @param msg
     * @param e
     */
    public UserDaoException(String msg, Throwable e) {
        super(msg, e);
    }
}
