package org.modules.transaction.receipts;

import com.RMT2RuntimeException;

/**
 * Handles payment email confirmation errors.
 * 
 * @author Roy Terrell
 * 
 */
public class PaymentEmailConfirmationException extends RMT2RuntimeException {
    private static final long serialVersionUID = -1884703323759924257L;

    public PaymentEmailConfirmationException() {
        super();
    }

    public PaymentEmailConfirmationException(String msg) {
        super(msg);
    }

    public PaymentEmailConfirmationException(Exception e) {
        super(e);
    }

    public PaymentEmailConfirmationException(String msg, Exception e) {
        super(msg, e);
    }
}
