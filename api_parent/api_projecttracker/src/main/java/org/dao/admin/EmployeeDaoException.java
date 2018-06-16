package org.dao.admin;

import org.dao.ProjecttrackerDaoException;

/**
 * The exception class for handling errors pertaining to the Employee DAO
 * component.
 * 
 * @author Roy Terrell
 * 
 */
public class EmployeeDaoException extends ProjecttrackerDaoException {

    private static final long serialVersionUID = -9121106275070847124L;

    /**
     * 
     */
    public EmployeeDaoException() {
        super();
    }

    /**
     * @param msg
     */
    public EmployeeDaoException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public EmployeeDaoException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public EmployeeDaoException(String msg, Throwable e) {
        super(msg, e);
    }

}
