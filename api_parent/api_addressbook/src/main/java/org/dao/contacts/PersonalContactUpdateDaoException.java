package org.dao.contacts;

/**
 * Exception class for handling data persistence errors pertaining to inserting
 * or updating a personal contact.
 * 
 * @author rterrell
 * 
 */
public class PersonalContactUpdateDaoException extends ContactUpdateDaoException {

    private static final long serialVersionUID = -6226861078350853129L;

    /**
     * 
     */
    public PersonalContactUpdateDaoException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     */
    public PersonalContactUpdateDaoException(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param e
     */
    public PersonalContactUpdateDaoException(Exception e) {
        super(e);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     * @param e
     */
    public PersonalContactUpdateDaoException(String msg, Throwable e) {
        super(msg, e);
        // TODO Auto-generated constructor stub
    }

}
