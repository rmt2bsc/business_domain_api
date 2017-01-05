package org.dao.roles;

import org.dao.SecurityDaoException;

/**
 * Manages application maintenance errors
 * 
 * @author RTerrell
 * 
 */
public class RoleDaoException extends SecurityDaoException {
    private static final long serialVersionUID = 2969536074770899864L;

    /**
     * 
     */
    public RoleDaoException() {
        super();
    }

    /**
     * 
     * @param msg
     */
    public RoleDaoException(String msg) {
        super(msg);
    }

    /**
     * 
     * @param e
     */
    public RoleDaoException(Exception e) {
        super(e);
    }

    /**
     * 
     * @param msg
     * @param e
     */
    public RoleDaoException(String msg, Throwable e) {
        super(msg, e);
    }
}
