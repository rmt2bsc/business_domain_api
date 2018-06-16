package org.modules.transaction.sales;

/**
 * Handles the recognition of an invalid sale order status.
 * 
 * @author Roy Terrell
 * 
 */
public class SalesOrderStatusInvalidException extends SalesApiException {
    private static final long serialVersionUID = -1884703323759924257L;

    public SalesOrderStatusInvalidException() {
        super();
    }

    public SalesOrderStatusInvalidException(String msg) {
        super(msg);
    }

    public SalesOrderStatusInvalidException(Exception e) {
        super(e);
    }

    public SalesOrderStatusInvalidException(String msg, Exception e) {
        super(msg, e);
    }
}
