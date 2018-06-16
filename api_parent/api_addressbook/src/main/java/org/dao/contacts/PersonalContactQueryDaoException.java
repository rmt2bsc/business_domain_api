package org.dao.contacts;

import org.dao.AddressBookDaoException;

/**
 * Exception class for handling data access errors pertaining to inserting or
 * updating personal contact.
 * 
 * @author rterrell
 * 
 */
public class PersonalContactQueryDaoException extends AddressBookDaoException {

    private static final long serialVersionUID = -6226861078350853129L;

    /**
     * 
     */
    public PersonalContactQueryDaoException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     */
    public PersonalContactQueryDaoException(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param e
     */
    public PersonalContactQueryDaoException(Exception e) {
        super(e);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     * @param e
     */
    public PersonalContactQueryDaoException(String msg, Throwable e) {
        super(msg, e);
        // TODO Auto-generated constructor stub
    }

}
