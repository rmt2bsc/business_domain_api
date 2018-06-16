package org.dao.contacts;

import org.dao.AddressBookDaoException;

/**
 * Exception class for handling data access errors pertaining to common contact
 * operations.
 * 
 * @author rterrell
 * 
 */
public class ContactDaoException extends AddressBookDaoException {

    private static final long serialVersionUID = -6226861078350853129L;

    /**
     * 
     */
    public ContactDaoException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     */
    public ContactDaoException(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param e
     */
    public ContactDaoException(Exception e) {
        super(e);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     * @param e
     */
    public ContactDaoException(String msg, Throwable e) {
        super(msg, e);
        // TODO Auto-generated constructor stub
    }

}
