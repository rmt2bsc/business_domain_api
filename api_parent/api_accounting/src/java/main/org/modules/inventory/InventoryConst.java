package org.modules.inventory;

public class InventoryConst {
    /** Service Item Type Code */
    public static final int ITEM_TYPE_SRVC = 1;

    /** Merchandise Item Type Code */
    public static final int ITEM_TYPE_MERCH = 2;

    /** Available Item Status Code */
    public static final int ITEM_STATUS_AVAIL = 26;

    /** In Service Item Status Code */
    public static final int ITEM_STATUS_INSRVC = 27;

    /** Out of Service Item Status Code */
    public static final int ITEM_STATUS_OUTSRVC = 28;

    /** Replaced Item Status Code */
    public static final int ITEM_STATUS_REPLACE = 29;

    /** Cancelled Item Status Code */
    public static final int ITEM_STATUS_CANCEL = 30;

    /** Out of Stock Item Status Code */
    public static final int ITEM_STATUS_OUTSTOCK = 31;

    /** Reorder Item Status Code */
    public static final int ITEM_STATUS_REORDER = 32;

    /** Replenish Item Status Code */
    public static final int ITEM_STATUS_REPLENISH = 33;

    /** Vendor Item override is activated for item */
    public static final int ITEM_STATUS_OVERRIDE_ACTIVE = 34;

    /** Vendor Item override is removed for item */
    public static final int ITEM_STATUS_OVERRIDE_INACTIVE = 35;

    /** Constant that indicates that item is active */
    public static final int ITEM_ACTIVE_YES = 1;

    /** Constant that indicates that item has been inactive */
    public static final int ITEM_ACTIVE_NO = 0;

    /** Value indicating Inventory item is overriden by vendor item data */
    public static final int ITEM_OVERRIDE_YES = 1;

    /** Value indicating Inventory item is not overriden by vendor item data */
    public static final int ITEM_OVERRIDE_NO = 0;

}