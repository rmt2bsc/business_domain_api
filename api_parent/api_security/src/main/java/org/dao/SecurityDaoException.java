package org.dao;

import com.api.persistence.DatabaseException;

/**
 * The base DAO exception for all modules of the Security API.
 * 
 * @author RTerrell
 * 
 */
public class SecurityDaoException extends DatabaseException {
    private static final long serialVersionUID = 2969536074770899864L;

    public SecurityDaoException() {
        super();
    }

    public SecurityDaoException(String msg) {
        super(msg);
    }

    public SecurityDaoException(Exception e) {
        super(e);
    }

    public SecurityDaoException(String msg, Throwable e) {
        super(msg, e);
    }
}
