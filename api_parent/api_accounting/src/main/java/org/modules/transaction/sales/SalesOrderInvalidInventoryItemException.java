package org.modules.transaction.sales;

/**
 * Handles an invalid item id of the sales order itme associated with a
 * partucular sale order.
 * 
 * @author Roy Terrell
 * 
 */
public class SalesOrderInvalidInventoryItemException extends SalesApiException {
    private static final long serialVersionUID = -1884703323759924257L;

    public SalesOrderInvalidInventoryItemException() {
        super();
    }

    public SalesOrderInvalidInventoryItemException(String msg) {
        super(msg);
    }

    public SalesOrderInvalidInventoryItemException(Exception e) {
        super(e);
    }

    public SalesOrderInvalidInventoryItemException(String msg, Exception e) {
        super(msg, e);
    }
}
