package org.modules.transaction.sales;

import com.RMT2Exception;

/**
 * Handles sale order/ sales invoice transacton errors.
 * 
 * @author Roy Terrell
 * 
 */
public class SalesApiException extends RMT2Exception {
    private static final long serialVersionUID = -1884703323759924257L;

    public SalesApiException() {
        super();
    }

    public SalesApiException(String msg) {
        super(msg);
    }

    public SalesApiException(Exception e) {
        super(e);
    }

    public SalesApiException(String msg, Exception e) {
        super(msg, e);
    }
}
