package org.dao.transaction.sales;

import org.dao.transaction.XactDaoException;

/**
 * Exception class for handling sales invoice errors.
 * 
 * @author Roy Terrell
 * 
 */
public class SalesInvoiceDaoException extends XactDaoException {

    /**
     * 
     */
    private static final long serialVersionUID = 8423111478028998861L;

    /**
     * 
     */
    public SalesInvoiceDaoException() {
        super();
    }

    /**
     * @param msg
     */
    public SalesInvoiceDaoException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public SalesInvoiceDaoException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public SalesInvoiceDaoException(String msg, Exception e) {
        super(msg, e);
    }

}
