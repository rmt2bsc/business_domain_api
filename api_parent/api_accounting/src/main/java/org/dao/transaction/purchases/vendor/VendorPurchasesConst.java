package org.dao.transaction.purchases.vendor;

/**
 * Constants for the Vendor Purchases module
 * 
 * @author Roy Terrell
 *
 */
public class VendorPurchasesConst {
  
    /**
     * Indicates that the purchase order is new and has not been assigned a
     * status.
     */
    public static final int PURCH_STATUS_NEW = -1;

    /** Quote Purchases Status Code */
    public static final int PURCH_STATUS_QUOTE = 1;

    /** Finalized Purchases Status Code */
    public static final int PURCH_STATUS_FINALIZE = 2;

    /** Received Purchases Status Code */
    public static final int PURCH_STATUS_RECEIVED = 3;

    /** Cancelled Purchases Status Code */
    public static final int PURCH_STATUS_CANCEL = 4;

    /** Purchases, Returns, and Allowances Status Code */
    public static final int PURCH_STATUS_RETURN = 5;

    /** Partial Purchase, Return, and Allowances Status Code */
    public static final int PURCH_STATUS_PARTRET = 6;

    /** Indicates the successfull update of a Quote or Finalize purchase order */
    public static final int PO_UPDATE_SUCCESSFUL = 1;

    /**
     * Indicates the successfull update of a Finalize purchase order which its
     * total order quantity has been received
     */
    public static final int PO_UPDATE_RECEIVED = 0;

    /**
     * SQL where clause used to query the databsae for purchase order items that are not assigned to a particular PO number.
     */
    public static final String SQL_UNASSIGNED_PO_ITEMS = "item_id not in ( select item_id from purchase_order_items where po_id = ?)";
    
    /**
     * SQL where clause used to query the databsae for the current purchase order status.
     */
    public static final String SQL_CURRENT_PO_STATUS = "end_date is null";
}