package org.dao.transaction.receipts;

import org.dao.transaction.XactDaoException;

/**
 * Exception class for handling cash receipt DAO errors.
 * 
 * @author Roy Terrell
 * 
 */
public class CashReceiptDaoException extends XactDaoException {

    /**
     * 
     */
    private static final long serialVersionUID = 8423111478028998861L;

    /**
     * 
     */
    public CashReceiptDaoException() {
        super();
    }

    /**
     * @param msg
     */
    public CashReceiptDaoException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public CashReceiptDaoException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public CashReceiptDaoException(String msg, Exception e) {
        super(msg, e);
    }

}
