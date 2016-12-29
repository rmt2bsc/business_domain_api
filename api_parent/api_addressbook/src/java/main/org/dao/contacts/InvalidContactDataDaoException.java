package org.dao.contacts;

import org.dao.AddressBookDaoException;

/**
 * Exception class for handling invalid data errors pertaining to a contact.
 * 
 * @author rterrell
 * 
 */
public class InvalidContactDataDaoException extends AddressBookDaoException {

    private static final long serialVersionUID = -6226861078350853129L;

    /**
     * 
     */
    public InvalidContactDataDaoException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     */
    public InvalidContactDataDaoException(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param e
     */
    public InvalidContactDataDaoException(Exception e) {
        super(e);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     * @param e
     */
    public InvalidContactDataDaoException(String msg, Throwable e) {
        super(msg, e);
        // TODO Auto-generated constructor stub
    }

}
