package org.dao.postal;

import org.dao.AddressBookDaoException;

/**
 * Exception class for handling missing selection criteria for zip code search
 * operations.
 * 
 * @author rterrell
 * 
 */
public class InvalidZipcodeCriteriaDaoException extends AddressBookDaoException {

    private static final long serialVersionUID = -6226861078350853129L;

    /**
     * 
     */
    public InvalidZipcodeCriteriaDaoException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     */
    public InvalidZipcodeCriteriaDaoException(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param e
     */
    public InvalidZipcodeCriteriaDaoException(Exception e) {
        super(e);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     * @param e
     */
    public InvalidZipcodeCriteriaDaoException(String msg, Throwable e) {
        super(msg, e);
        // TODO Auto-generated constructor stub
    }

}
