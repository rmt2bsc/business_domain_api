package org.dao.transaction.purchases.creditor;

import org.dao.transaction.XactDaoException;

/**
 * Exception class for handling sales invoice errors.
 * 
 * @author Roy Terrell
 * 
 */
public class CreditorPurchasesDaoException extends XactDaoException {

    /**
     * 
     */
    private static final long serialVersionUID = 8423111478028998861L;

    /**
     * 
     */
    public CreditorPurchasesDaoException() {
        super();
    }

    /**
     * @param msg
     */
    public CreditorPurchasesDaoException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public CreditorPurchasesDaoException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public CreditorPurchasesDaoException(String msg, Exception e) {
        super(msg, e);
    }

}
