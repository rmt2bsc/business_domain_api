package org.modules.transaction.purchases.vendor;

import org.modules.transaction.sales.SalesApiException;

/**
 * Handles vendor purchases API errors.
 * 
 * @author Roy Terrell
 * 
 */
public class VendorPurchasesApiException extends SalesApiException {
    private static final long serialVersionUID = -1884703323759924257L;

    public VendorPurchasesApiException() {
        super();
    }

    public VendorPurchasesApiException(String msg) {
        super(msg);
    }

    public VendorPurchasesApiException(Exception e) {
        super(e);
    }

    public VendorPurchasesApiException(String msg, Exception e) {
        super(msg, e);
    }
}
