package org.modules.transaction.purchases.creditor;

import org.modules.transaction.sales.SalesApiException;

/**
 * Handles creditor purchases API errors.
 * 
 * @author Roy Terrell
 * 
 */
public class CreditorPurchasesApiException extends SalesApiException {
    private static final long serialVersionUID = -1884703323759924257L;

    public CreditorPurchasesApiException() {
        super();
    }

    public CreditorPurchasesApiException(String msg) {
        super(msg);
    }

    public CreditorPurchasesApiException(Exception e) {
        super(e);
    }

    public CreditorPurchasesApiException(String msg, Exception e) {
        super(msg, e);
    }
}
