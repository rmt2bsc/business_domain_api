package org.dao;

import org.dao.inventory.InventoryDaoConst;

/**
 * Class for managing constant SQL statements for the accounting API.
 * 
 * @author Roy Terrell
 * 
 */
public class AccountingSqlConst {

    /** SQL statement to retrieve a customer's balance */
    public static final String SQL_CUSTOMER_BALANCE = " select isnull(sum(amount), 0) balance from customer_activity where customer_id = $1";
    /** SQL statement to retrieve a creditor's balance */
    public static final String SQL_CREDITOR_BALANCE = "  select isnull(sum(amount), 0) balance from creditor_activity where creditor_id = $1";
    /**
     * SQL Selection criteria clause for retrieving vendor unassigned iventory
     * items
     */
    public static final String SQL_CRTIERIA_VENDOR_UNASSIGNED_ITEM = " item_id not in ( select item_id from vendor_items where creditor_id = $1) and item_type_id = "
            + InventoryDaoConst.ITEM_TYPE_MERCH;

}
