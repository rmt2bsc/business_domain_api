package org.modules.transaction.receipts;

import com.RMT2RuntimeException;

/**
 * Handles payment email confirmation errors.
 * 
 * @author Roy Terrell
 * @deprecated no longer in use. Moved to amh-accounting project
 */
public class PaymentEmailConfirmationExceptionOld extends RMT2RuntimeException {
    private static final long serialVersionUID = -1884703323759924257L;

    public PaymentEmailConfirmationExceptionOld() {
        super();
    }

    public PaymentEmailConfirmationExceptionOld(String msg) {
        super(msg);
    }

    public PaymentEmailConfirmationExceptionOld(Exception e) {
        super(e);
    }

    public PaymentEmailConfirmationExceptionOld(String msg, Exception e) {
        super(msg, e);
    }
}
