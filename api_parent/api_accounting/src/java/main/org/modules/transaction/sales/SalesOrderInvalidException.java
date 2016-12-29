package org.modules.transaction.sales;

/**
 * Handles the recognition of an invalid sale order.
 * 
 * @author Roy Terrell
 * 
 */
public class SalesOrderInvalidException extends SalesApiException {
    private static final long serialVersionUID = -1884703323759924257L;

    public SalesOrderInvalidException() {
        super();
    }

    public SalesOrderInvalidException(String msg) {
        super(msg);
    }

    public SalesOrderInvalidException(Exception e) {
        super(e);
    }

    public SalesOrderInvalidException(String msg, Exception e) {
        super(msg, e);
    }
}
