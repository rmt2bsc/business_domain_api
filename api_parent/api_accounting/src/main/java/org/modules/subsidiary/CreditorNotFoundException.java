package org.modules.subsidiary;

/**
 * Signals the condition when a creditor is not found or does not exists.
 * 
 * @author roy terrell
 *
 */
public class CreditorNotFoundException extends CreditorApiException {

    private static final long serialVersionUID = -5040558876923429931L;

    /**
     * 
     */
    public CreditorNotFoundException() {
        return;
    }

    /**
     * @param msg
     */
    public CreditorNotFoundException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public CreditorNotFoundException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public CreditorNotFoundException(String msg, Throwable e) {
        super(msg, e);
    }

}
