package org.dao.postal;

import org.dao.AddressBookDaoException;

/**
 * Exception class for handling data access errors pertaining to postal related
 * data resources.
 * 
 * @author rterrell
 * 
 */
public class PostalDaoException extends AddressBookDaoException {

    private static final long serialVersionUID = -6226861078350853129L;

    /**
     * 
     */
    public PostalDaoException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     */
    public PostalDaoException(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param e
     */
    public PostalDaoException(Exception e) {
        super(e);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     * @param e
     */
    public PostalDaoException(String msg, Throwable e) {
        super(msg, e);
        // TODO Auto-generated constructor stub
    }

}
