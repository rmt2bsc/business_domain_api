package org.dao.transaction.disbursements;

import org.dao.transaction.XactDaoException;

/**
 * Exception class for handling disbursement DAO errors.
 * 
 * @author Roy Terrell
 * 
 */
public class DisbursementsDaoException extends XactDaoException {

    /**
     * 
     */
    private static final long serialVersionUID = 8423111478028998861L;

    /**
     * 
     */
    public DisbursementsDaoException() {
        super();
    }

    /**
     * @param msg
     */
    public DisbursementsDaoException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public DisbursementsDaoException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public DisbursementsDaoException(String msg, Throwable e) {
        super(msg, e);
    }

}
