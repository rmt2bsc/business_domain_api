package org.modules.subsidiary;

/**
 * Manages Customer module errors
 * 
 * @author Roy Terrell
 * 
 */
public class CustomerApiException extends SubsidiaryException {

    /**
     * 
     */
    private static final long serialVersionUID = -832548866368682910L;

    /**
     * 
     */
    public CustomerApiException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     */
    public CustomerApiException(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param e
     */
    public CustomerApiException(Exception e) {
        super(e);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     * @param e
     */
    public CustomerApiException(String msg, Throwable e) {
        super(msg, e);
        // TODO Auto-generated constructor stub
    }

}
