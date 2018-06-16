package org.modules.transaction.receipts;

import org.modules.transaction.sales.SalesApiException;

/**
 * Handles sales payment errors.
 * 
 * @author Roy Terrell
 * 
 */
public class CashReceiptApiException extends SalesApiException {
    private static final long serialVersionUID = -1884703323759924257L;

    public CashReceiptApiException() {
        super();
    }

    public CashReceiptApiException(String msg) {
        super(msg);
    }

    public CashReceiptApiException(Exception e) {
        super(e);
    }

    public CashReceiptApiException(String msg, Exception e) {
        super(msg, e);
    }
}
