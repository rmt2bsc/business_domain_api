package org.modules.transaction.purchases.vendor;

/**
 * Handles validation errors for base purchase orders.
 * 
 * @author Roy Terrell
 * 
 */
public class PurchaseOrderValidationException extends
        VendorPurchasesApiException {

    private static final long serialVersionUID = -7365644983317529733L;

    public PurchaseOrderValidationException() {
        super();
    }

    public PurchaseOrderValidationException(String msg) {
        super(msg);
    }

    public PurchaseOrderValidationException(Exception e) {
        super(e);
    }

    public PurchaseOrderValidationException(String msg, Exception e) {
        super(msg, e);
    }
}
