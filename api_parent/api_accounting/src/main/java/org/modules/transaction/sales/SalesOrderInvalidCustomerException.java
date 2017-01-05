package org.modules.transaction.sales;

/**
 * Handles the invalid customer associated with a sale order.
 * 
 * @author Roy Terrell
 * 
 */
public class SalesOrderInvalidCustomerException extends SalesApiException {
    private static final long serialVersionUID = -1884703323759924257L;

    public SalesOrderInvalidCustomerException() {
        super();
    }

    public SalesOrderInvalidCustomerException(String msg) {
        super(msg);
    }

    public SalesOrderInvalidCustomerException(Exception e) {
        super(e);
    }

    public SalesOrderInvalidCustomerException(String msg, Exception e) {
        super(msg, e);
    }
}
