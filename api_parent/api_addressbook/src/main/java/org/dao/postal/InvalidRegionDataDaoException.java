package org.dao.postal;

/**
 * Handles data validation errors pertaining to a Region DTO object.
 * 
 * @author rterrell
 * 
 */
public class InvalidRegionDataDaoException extends PostalDaoException {

    private static final long serialVersionUID = 8206356893590283807L;

    /**
     * 
     */
    public InvalidRegionDataDaoException() {
        return;
    }

    /**
     * @param msg
     */
    public InvalidRegionDataDaoException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public InvalidRegionDataDaoException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public InvalidRegionDataDaoException(String msg, Throwable e) {
        super(msg, e);
    }

}
