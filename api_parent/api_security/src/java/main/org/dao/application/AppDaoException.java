package org.dao.application;

import org.dao.SecurityDaoException;

/**
 * Manages application maintenance errors
 * 
 * @author RTerrell
 * 
 */
public class AppDaoException extends SecurityDaoException {
    private static final long serialVersionUID = 2969536074770899864L;

    public AppDaoException() {
        super();
    }

    public AppDaoException(String msg) {
        super(msg);
    }

    public AppDaoException(Exception e) {
        super(e);
    }

    public AppDaoException(String msg, Throwable e) {
        super(msg, e);
    }
}
