package org.dao.postal;

import org.dao.AddressBookDaoException;

/**
 * Exception class for handling data access errors pertaining to to time zone
 * operations.
 * 
 * @author rterrell
 * 
 */
public class TimezoneDaoException extends AddressBookDaoException {

    private static final long serialVersionUID = -6226861078350853129L;

    /**
     * 
     */
    public TimezoneDaoException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     */
    public TimezoneDaoException(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param e
     */
    public TimezoneDaoException(Exception e) {
        super(e);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     * @param e
     */
    public TimezoneDaoException(String msg, Throwable e) {
        super(msg, e);
        // TODO Auto-generated constructor stub
    }

}
