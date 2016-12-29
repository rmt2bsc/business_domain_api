package org.modules.transaction.purchases.vendor;

/**
 * Handles validation errors for purchase order line items.
 * 
 * @author Roy Terrell
 * 
 */
public class PurchaseOrderItemValidationException extends
        VendorPurchasesApiException {

    private static final long serialVersionUID = -7365644983317529733L;

    public PurchaseOrderItemValidationException() {
        super();
    }

    public PurchaseOrderItemValidationException(String msg) {
        super(msg);
    }

    public PurchaseOrderItemValidationException(Exception e) {
        super(e);
    }

    public PurchaseOrderItemValidationException(String msg, Exception e) {
        super(msg, e);
    }
}
