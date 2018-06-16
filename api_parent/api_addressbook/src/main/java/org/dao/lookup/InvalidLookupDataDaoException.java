/**
 * 
 */
package org.dao.lookup;

/**
 * Handles persistence error pertaining to inserting and updating lookup lookup
 * objects.
 * 
 * @author rterrell
 * 
 */
public class InvalidLookupDataDaoException extends LookupDaoException {

    private static final long serialVersionUID = 8206356893590283807L;

    /**
     * 
     */
    public InvalidLookupDataDaoException() {
        return;
    }

    /**
     * @param msg
     */
    public InvalidLookupDataDaoException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public InvalidLookupDataDaoException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public InvalidLookupDataDaoException(String msg, Throwable e) {
        super(msg, e);
    }

}
