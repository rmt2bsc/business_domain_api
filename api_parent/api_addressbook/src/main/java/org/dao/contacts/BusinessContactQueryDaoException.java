package org.dao.contacts;

import org.dao.AddressBookDaoException;

/**
 * Exception class for handling data access errors pertaining to business
 * contact operations.
 * 
 * @author rterrell
 * 
 */
public class BusinessContactQueryDaoException extends AddressBookDaoException {

    private static final long serialVersionUID = -6226861078350853129L;

    /**
     * 
     */
    public BusinessContactQueryDaoException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     */
    public BusinessContactQueryDaoException(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param e
     */
    public BusinessContactQueryDaoException(Exception e) {
        super(e);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     * @param e
     */
    public BusinessContactQueryDaoException(String msg, Throwable e) {
        super(msg, e);
        // TODO Auto-generated constructor stub
    }

}
