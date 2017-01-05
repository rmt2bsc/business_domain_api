package org.dao.subsidiary;

import org.dao.AccountingDaoException;

/**
 * An exception class for handling common subsidiary DAO errors.
 * 
 * @author Roy Terrell
 * 
 */
public class SubsidiaryDaoException extends AccountingDaoException {

    /**
     * 
     */
    private static final long serialVersionUID = 4513922270291021153L;

    /**
     * 
     */
    public SubsidiaryDaoException() {
        super();
    }

    /**
     * @param msg
     */
    public SubsidiaryDaoException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public SubsidiaryDaoException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public SubsidiaryDaoException(String msg, Throwable e) {
        super(msg, e);
    }
}
