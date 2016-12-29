/**
 * 
 */
package org.dao.lookup;

import org.dao.AddressBookDaoException;

/**
 * Exception class for handling data access errors pertaining to to lookup group
 * and lookup lookup operations.
 * 
 * @author rterrell
 * 
 */
public class LookupDaoException extends AddressBookDaoException {

    private static final long serialVersionUID = -6226861078350853129L;

    /**
     * 
     */
    public LookupDaoException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     */
    public LookupDaoException(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param e
     */
    public LookupDaoException(Exception e) {
        super(e);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     * @param e
     */
    public LookupDaoException(String msg, Throwable e) {
        super(msg, e);
        // TODO Auto-generated constructor stub
    }

}
