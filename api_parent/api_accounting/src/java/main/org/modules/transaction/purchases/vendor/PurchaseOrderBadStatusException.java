package org.modules.transaction.purchases.vendor;

/**
 * Handles ill advised operations against a purchase order in the wrong status.
 * 
 * @author Roy Terrell
 * 
 */
public class PurchaseOrderBadStatusException extends
        VendorPurchasesApiException {

    private static final long serialVersionUID = -7365644983317529733L;

    public PurchaseOrderBadStatusException() {
        super();
    }

    public PurchaseOrderBadStatusException(String msg) {
        super(msg);
    }

    public PurchaseOrderBadStatusException(Exception e) {
        super(e);
    }

    public PurchaseOrderBadStatusException(String msg, Exception e) {
        super(msg, e);
    }
}
