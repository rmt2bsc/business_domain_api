package org.dao.transaction.sales;

import org.dao.transaction.XactDaoException;

/**
 * Exception class for handling sales order errors.
 * 
 * @author Roy Terrell
 * 
 */
public class SalesOrderDaoException extends XactDaoException {

    /**
     * 
     */
    private static final long serialVersionUID = 8423111478028998861L;

    /**
     * 
     */
    public SalesOrderDaoException() {
        super();
    }

    /**
     * @param msg
     */
    public SalesOrderDaoException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public SalesOrderDaoException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public SalesOrderDaoException(String msg, Exception e) {
        super(msg, e);
    }

}
