package org.dao.application;

/**
 * Exception class for handling invalid application objects.
 * 
 * @author Roy Terrell
 * 
 */
public class InvalidAppInstanceException extends AppDaoException {

    private static final long serialVersionUID = -5010232925094893357L;

    /**
     * 
     */
    public InvalidAppInstanceException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     */
    public InvalidAppInstanceException(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param e
     */
    public InvalidAppInstanceException(Exception e) {
        super(e);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     * @param e
     */
    public InvalidAppInstanceException(String msg, Throwable e) {
        super(msg, e);
        // TODO Auto-generated constructor stub
    }

}
