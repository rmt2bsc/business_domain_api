package org.modules.transaction.purchases.vendor;

/**
 * Handles erroneous situations when trying to change from one purchase order
 * status to another.
 * 
 * @author Roy Terrell
 * 
 */
public class CannotChangePurchaseOrderStatusException extends
        VendorPurchasesApiException {

    private static final long serialVersionUID = -7365644983317529733L;

    public CannotChangePurchaseOrderStatusException() {
        super();
    }

    public CannotChangePurchaseOrderStatusException(String msg) {
        super(msg);
    }

    public CannotChangePurchaseOrderStatusException(Exception e) {
        super(e);
    }

    public CannotChangePurchaseOrderStatusException(String msg, Exception e) {
        super(msg, e);
    }
}
