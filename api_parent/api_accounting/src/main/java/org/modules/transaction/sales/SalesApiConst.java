package org.modules.transaction.sales;

/**
 * Constants for the sales order API module.
 * 
 * @author Roy Terrell
 * 
 */
public class SalesApiConst {
    /** Flag indicating sales order is invoiced */
    public static final int INVOICED_FLAG_YES = 1;

    /** Flag indicating sales order is cancelled */
    public static final int INVOICED_FLAG_NO = 0;

    /**
     * Sales Order Inventory update mode for increasing inventory items with
     * sales order items
     */
    public static final int UPDATE_INV_ADD = 100;

    /**
     * Sales Order Inventory update mode for decreasing inventory items with
     * sales order items
     */
    public static final int UPDATE_INV_DEPLETE = 200;

    /** New Sales Order Status Code */
    public static final int STATUS_CODE_NEW = 0;

    /** Quote Sales Order Status Code */
    public static final int STATUS_CODE_QUOTE = 1;

    /** Invoiced Order Status Code */
    public static final int STATUS_CODE_INVOICED = 2;

    /** Closed Sales Order Status Code */
    public static final int STATUS_CODE_CLOSED = 3;

    /** Cancelled Sales Order Status Code */
    public static final int STATUS_CODE_CANCELLED = 100;

    /** Refunded Sales Order Status Code */
    public static final int STATUS_CODE_REFUNDED = 200;
}