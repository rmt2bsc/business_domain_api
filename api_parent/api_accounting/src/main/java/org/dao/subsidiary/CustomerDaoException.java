package org.dao.subsidiary;

/**
 * An exception class for handling customer DAO errors.
 * 
 * @author Roy Terrell
 * 
 */
public class CustomerDaoException extends SubsidiaryDaoException {

    /**
     * 
     */
    private static final long serialVersionUID = 4513922270291021153L;

    /**
     * 
     */
    public CustomerDaoException() {
        super();
    }

    /**
     * @param msg
     */
    public CustomerDaoException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public CustomerDaoException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public CustomerDaoException(String msg, Throwable e) {
        super(msg, e);
    }
}
