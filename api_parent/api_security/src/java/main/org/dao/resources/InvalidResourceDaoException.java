package org.dao.resources;

/**
 * Manages DAO validation errors pertaining to the resource data.
 * 
 * @author rterrell
 * 
 */
public class InvalidResourceDaoException extends ResourceDaoException {

    private static final long serialVersionUID = 4289799427122857388L;

    /**
     * 
     */
    public InvalidResourceDaoException() {
        super();
    }

    /**
     * @param msg
     */
    public InvalidResourceDaoException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public InvalidResourceDaoException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public InvalidResourceDaoException(String msg, Throwable e) {
        super(msg, e);
    }

}
