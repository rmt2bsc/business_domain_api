package org.modules.transaction.sales;

/**
 * Handles the recognition of when the new destination sales order status is out
 * of synch with the an incorrect current sale order status.
 * 
 * @author Roy Terrell
 * 
 */
public class OutOfSyncSalesOrderStatusesException extends
        SalesApiException {
    private static final long serialVersionUID = -1884703323759924257L;

    public OutOfSyncSalesOrderStatusesException() {
        super();
    }

    public OutOfSyncSalesOrderStatusesException(String msg) {
        super(msg);
    }

    public OutOfSyncSalesOrderStatusesException(Exception e) {
        super(e);
    }

    public OutOfSyncSalesOrderStatusesException(String msg, Exception e) {
        super(msg, e);
    }
}
