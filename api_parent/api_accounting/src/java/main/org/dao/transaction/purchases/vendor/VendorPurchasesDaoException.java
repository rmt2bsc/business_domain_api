package org.dao.transaction.purchases.vendor;

import org.dao.transaction.XactDaoException;

/**
 * Exception class for handling sales invoice errors.
 * 
 * @author Roy Terrell
 * 
 */
public class VendorPurchasesDaoException extends XactDaoException {

    /**
     * 
     */
    private static final long serialVersionUID = 8423111478028998861L;

    /**
     * 
     */
    public VendorPurchasesDaoException() {
        super();
    }

    /**
     * @param msg
     */
    public VendorPurchasesDaoException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public VendorPurchasesDaoException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public VendorPurchasesDaoException(String msg, Exception e) {
        super(msg, e);
    }

}
