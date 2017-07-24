package org.modules.subsidiary;

/**
 * Signals the condition when preparation for new creditor fails.
 * 
 * @author roy terrell
 *
 */
public class NewCreditorSetupFailureException extends CreditorApiException {

    private static final long serialVersionUID = -9164390097665174956L;

    /**
     * 
     */
    public NewCreditorSetupFailureException() {
        return;
    }

    /**
     * @param msg
     */
    public NewCreditorSetupFailureException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public NewCreditorSetupFailureException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public NewCreditorSetupFailureException(String msg, Throwable e) {
        super(msg, e);
    }

}
