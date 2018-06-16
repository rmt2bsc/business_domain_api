package org.modules.subsidiary;


/**
 * Manages Creditor module errors
 * 
 * @author Roy Terrell
 * 
 */
public class CreditorApiException extends SubsidiaryException {

    /**
     * 
     */
    private static final long serialVersionUID = -832548866368682910L;

    /**
     * 
     */
    public CreditorApiException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     */
    public CreditorApiException(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param e
     */
    public CreditorApiException(Exception e) {
        super(e);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     * @param e
     */
    public CreditorApiException(String msg, Throwable e) {
        super(msg, e);
        // TODO Auto-generated constructor stub
    }

}
