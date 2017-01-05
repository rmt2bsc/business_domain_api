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
public class LookupUpdateDaoException extends LookupDaoException {

    private static final long serialVersionUID = 8206356893590283807L;

    /**
     * 
     */
    public LookupUpdateDaoException() {
        return;
    }

    /**
     * @param msg
     */
    public LookupUpdateDaoException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public LookupUpdateDaoException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public LookupUpdateDaoException(String msg, Throwable e) {
        super(msg, e);
    }

}
