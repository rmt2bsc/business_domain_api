package org.dao.contacts;

/**
 * Exception class for handling errors pertaining to creating new or updating
 * existing contact data.
 * 
 * @author rterrell
 * 
 */
public class ContactUpdateDaoException extends ContactDaoException {

    private static final long serialVersionUID = -6226861078350853129L;

    /**
     * 
     */
    public ContactUpdateDaoException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     */
    public ContactUpdateDaoException(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param e
     */
    public ContactUpdateDaoException(Exception e) {
        super(e);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     * @param e
     */
    public ContactUpdateDaoException(String msg, Throwable e) {
        super(msg, e);
        // TODO Auto-generated constructor stub
    }

}
