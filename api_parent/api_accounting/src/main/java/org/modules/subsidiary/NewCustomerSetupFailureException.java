package org.modules.subsidiary;

/**
 * Signals the condition when preparation for new customer fails.
 * 
 * @author roy terrell
 *
 */
public class NewCustomerSetupFailureException extends CustomerApiException {

    private static final long serialVersionUID = -9164390097665174956L;

    /**
     * 
     */
    public NewCustomerSetupFailureException() {
        return;
    }

    /**
     * @param msg
     */
    public NewCustomerSetupFailureException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public NewCustomerSetupFailureException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public NewCustomerSetupFailureException(String msg, Throwable e) {
        super(msg, e);
    }

}
