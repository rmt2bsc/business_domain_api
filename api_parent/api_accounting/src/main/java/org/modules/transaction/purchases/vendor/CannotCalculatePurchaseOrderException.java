package org.modules.transaction.purchases.vendor;

import com.RMT2RuntimeException;

/**
 * Used to capture errors pertaining to the inabliltity to calcualte the total
 * of all items of a purchase order.
 * 
 * @author Roy Terrell
 * 
 */
public class CannotCalculatePurchaseOrderException extends  RMT2RuntimeException {

    private static final long serialVersionUID = -7365644983317529733L;

    public CannotCalculatePurchaseOrderException() {
        super();
    }

    public CannotCalculatePurchaseOrderException(String msg) {
        super(msg);
    }

    public CannotCalculatePurchaseOrderException(Exception e) {
        super(e);
    }

    public CannotCalculatePurchaseOrderException(String msg, Exception e) {
        super(msg, e);
    }
}
