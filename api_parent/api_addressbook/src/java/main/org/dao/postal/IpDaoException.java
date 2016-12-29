package org.dao.postal;

import org.dao.AddressBookDaoException;

/**
 * Exception class for handling data access errors pertaining to to IP address
 * operations.
 * 
 * @author rterrell
 * 
 */
public class IpDaoException extends AddressBookDaoException {

    private static final long serialVersionUID = -6226861078350853129L;

    /**
     * 
     */
    public IpDaoException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     */
    public IpDaoException(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param e
     */
    public IpDaoException(Exception e) {
        super(e);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     * @param e
     */
    public IpDaoException(String msg, Throwable e) {
        super(msg, e);
        // TODO Auto-generated constructor stub
    }

}
