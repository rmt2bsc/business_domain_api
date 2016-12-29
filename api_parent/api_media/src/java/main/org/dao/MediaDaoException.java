package org.dao;

import com.api.persistence.DatabaseException;

/**
 * The base DAO exception for all modules pertaining to the Multi Media API.
 * 
 * @author RTerrell
 * 
 */
public class MediaDaoException extends DatabaseException {
    private static final long serialVersionUID = 2969536074770899864L;

    public MediaDaoException() {
        super();
    }

    public MediaDaoException(String msg) {
        super(msg);
    }

    public MediaDaoException(Exception e) {
        super(e);
    }

    public MediaDaoException(Throwable e) {
        super(e);
    }

    public MediaDaoException(String msg, Throwable e) {
        super(msg, e);
    }
}
