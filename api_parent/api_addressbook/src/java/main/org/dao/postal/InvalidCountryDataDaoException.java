package org.dao.postal;

/**
 * Handles data validation errors pertaining to a Country DTO object.
 * 
 * @author rterrell
 * 
 */
public class InvalidCountryDataDaoException extends PostalDaoException {

    private static final long serialVersionUID = 8206356893590283807L;

    /**
     * 
     */
    public InvalidCountryDataDaoException() {
        return;
    }

    /**
     * @param msg
     */
    public InvalidCountryDataDaoException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public InvalidCountryDataDaoException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public InvalidCountryDataDaoException(String msg, Throwable e) {
        super(msg, e);
    }

}
