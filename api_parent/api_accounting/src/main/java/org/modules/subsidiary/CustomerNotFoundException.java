package org.modules.subsidiary;

/**
 * Signals the condition when a customer is not found or does not exists.
 * 
 * @author roy terrell
 *
 */
public class CustomerNotFoundException extends CustomerApiException {

    private static final long serialVersionUID = -5040558876923429931L;

    /**
     * 
     */
    public CustomerNotFoundException() {
        return;
    }

    /**
     * @param msg
     */
    public CustomerNotFoundException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public CustomerNotFoundException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public CustomerNotFoundException(String msg, Throwable e) {
        super(msg, e);
    }

}
