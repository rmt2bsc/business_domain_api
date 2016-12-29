package org.dao.resources;

/**
 * Manages DAO error pertaining to the resource data persistence.
 * 
 * @author rterrell
 * 
 */
public class ResourceDaoUpdateException extends ResourceDaoException {

    private static final long serialVersionUID = 4289799427122857388L;

    /**
     * 
     */
    public ResourceDaoUpdateException() {
        super();
    }

    /**
     * @param msg
     */
    public ResourceDaoUpdateException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public ResourceDaoUpdateException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public ResourceDaoUpdateException(String msg, Throwable e) {
        super(msg, e);
    }

}
