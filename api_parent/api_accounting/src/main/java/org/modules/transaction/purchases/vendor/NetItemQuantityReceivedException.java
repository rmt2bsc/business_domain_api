package org.modules.transaction.purchases.vendor;

import com.RMT2RuntimeException;

/**
 * Used to capture errors pertaining to calculating the net quantity received of
 * a purchase order item.
 * 
 * @author Roy Terrell
 * 
 */
public class NetItemQuantityReceivedException extends RMT2RuntimeException {

    private static final long serialVersionUID = -7365644983317529733L;

    public NetItemQuantityReceivedException() {
        super();
    }

    public NetItemQuantityReceivedException(String msg) {
        super(msg);
    }

    public NetItemQuantityReceivedException(Exception e) {
        super(e);
    }

    public NetItemQuantityReceivedException(String msg, Exception e) {
        super(msg, e);
    }
}
