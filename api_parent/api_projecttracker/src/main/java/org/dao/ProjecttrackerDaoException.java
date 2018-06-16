package org.dao;

import com.api.persistence.DatabaseException;

/**
 * The base exception for all DAO components of the ProjectTracker API.
 * 
 * @author Roy Terrell
 * 
 */
public class ProjecttrackerDaoException extends DatabaseException {

    private static final long serialVersionUID = 2969536074770899864L;

    public ProjecttrackerDaoException() {
        super();
    }

    public ProjecttrackerDaoException(String msg) {
        super(msg);
    }

    public ProjecttrackerDaoException(Exception e) {
        super(e);
    }

    public ProjecttrackerDaoException(String msg, Throwable e) {
        super(msg, e);
    }
}
