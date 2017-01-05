package org.modules.transaction.sales;

/**
 * Handles the recognition of an incorrect destination sale order status.
 * 
 * @author Roy Terrell
 * 
 */
public class SalesOrderStatusInvalidDestinationException extends
        SalesApiException {
    private static final long serialVersionUID = -1884703323759924257L;

    public SalesOrderStatusInvalidDestinationException() {
        super();
    }

    public SalesOrderStatusInvalidDestinationException(String msg) {
        super(msg);
    }

    public SalesOrderStatusInvalidDestinationException(Exception e) {
        super(e);
    }

    public SalesOrderStatusInvalidDestinationException(String msg, Exception e) {
        super(msg, e);
    }
}
