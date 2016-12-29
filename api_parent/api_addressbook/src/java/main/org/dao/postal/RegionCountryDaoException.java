package org.dao.postal;

import org.dao.AddressBookDaoException;

/**
 * Exception class for handling data access errors pertaining to State/Province
 * operations.
 * 
 * @author rterrell
 * 
 */
public class RegionCountryDaoException extends AddressBookDaoException {

    private static final long serialVersionUID = -6226861078350853129L;

    /**
     * 
     */
    public RegionCountryDaoException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     */
    public RegionCountryDaoException(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param e
     */
    public RegionCountryDaoException(Exception e) {
        super(e);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     * @param e
     */
    public RegionCountryDaoException(String msg, Throwable e) {
        super(msg, e);
        // TODO Auto-generated constructor stub
    }

}
