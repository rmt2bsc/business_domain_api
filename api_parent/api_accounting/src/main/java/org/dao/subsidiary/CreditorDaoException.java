package org.dao.subsidiary;

/**
 * An exception class for handling creditor DAO errors.
 * 
 * @author Roy Terrell
 * 
 */
public class CreditorDaoException extends SubsidiaryDaoException {

    /**
     * 
     */
    private static final long serialVersionUID = 4513922270291021153L;

    /**
     * 
     */
    public CreditorDaoException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     */
    public CreditorDaoException(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param e
     */
    public CreditorDaoException(Exception e) {
        super(e);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     * @param e
     */
    public CreditorDaoException(String msg, Throwable e) {
        super(msg, e);
        // TODO Auto-generated constructor stub
    }

}
