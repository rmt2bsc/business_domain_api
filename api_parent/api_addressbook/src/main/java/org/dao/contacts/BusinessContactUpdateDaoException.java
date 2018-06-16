package org.dao.contacts;

/**
 * Exception class for handling data persistence errors pertaining to inserting
 * or updating a business contact.
 * 
 * @author rterrell
 * 
 */
public class BusinessContactUpdateDaoException extends ContactUpdateDaoException {

    private static final long serialVersionUID = -6226861078350853129L;

    /**
     * 
     */
    public BusinessContactUpdateDaoException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     */
    public BusinessContactUpdateDaoException(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param e
     */
    public BusinessContactUpdateDaoException(Exception e) {
        super(e);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     * @param e
     */
    public BusinessContactUpdateDaoException(String msg, Throwable e) {
        super(msg, e);
        // TODO Auto-generated constructor stub
    }

}
