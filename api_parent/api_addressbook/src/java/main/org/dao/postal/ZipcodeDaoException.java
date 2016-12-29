package org.dao.postal;

import org.dao.AddressBookDaoException;

/**
 * Exception class for handling data access errors pertaining to to zip code
 * operations.
 * 
 * @author rterrell
 * 
 */
public class ZipcodeDaoException extends AddressBookDaoException {

    private static final long serialVersionUID = -6226861078350853129L;

    /**
     * 
     */
    public ZipcodeDaoException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     */
    public ZipcodeDaoException(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param e
     */
    public ZipcodeDaoException(Exception e) {
        super(e);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     * @param e
     */
    public ZipcodeDaoException(String msg, Throwable e) {
        super(msg, e);
        // TODO Auto-generated constructor stub
    }

}
