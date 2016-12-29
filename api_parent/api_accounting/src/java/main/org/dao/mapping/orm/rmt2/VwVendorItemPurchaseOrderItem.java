package org.dao.mapping.orm.rmt2;

import java.util.Date;
import java.io.*;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;

/**
 * Peer object that maps to the vw_vendor_item_purchase_order_item database
 * table/view.
 * 
 * @author auto generated.
 */
public class VwVendorItemPurchaseOrderItem extends OrmBean {

    // Property name constants that belong to respective DataSource,
    // VwVendorItemPurchaseOrderItemView

    /**
     * The property name constant equivalent to property, ItemId, of respective
     * DataSource view.
     */
    public static final String PROP_ITEMID = "ItemId";
    /**
     * The property name constant equivalent to property, VendorId, of
     * respective DataSource view.
     */
    public static final String PROP_VENDORID = "VendorId";
    /**
     * The property name constant equivalent to property, VendorItemNo, of
     * respective DataSource view.
     */
    public static final String PROP_VENDORITEMNO = "VendorItemNo";
    /**
     * The property name constant equivalent to property, ItemSerialNo, of
     * respective DataSource view.
     */
    public static final String PROP_ITEMSERIALNO = "ItemSerialNo";
    /**
     * The property name constant equivalent to property, UnitCost, of
     * respective DataSource view.
     */
    public static final String PROP_UNITCOST = "UnitCost";
    /**
     * The property name constant equivalent to property, Description, of
     * respective DataSource view.
     */
    public static final String PROP_DESCRIPTION = "Description";
    /**
     * The property name constant equivalent to property, QtyOnHand, of
     * respective DataSource view.
     */
    public static final String PROP_QTYONHAND = "QtyOnHand";
    /**
     * The property name constant equivalent to property, Markup, of respective
     * DataSource view.
     */
    public static final String PROP_MARKUP = "Markup";
    /**
     * The property name constant equivalent to property, OverrideRetail, of
     * respective DataSource view.
     */
    public static final String PROP_OVERRIDERETAIL = "OverrideRetail";
    /**
     * The property name constant equivalent to property, PoId, of respective
     * DataSource view.
     */
    public static final String PROP_POID = "PoId";
    /**
     * The property name constant equivalent to property, QtyOrderd, of
     * respective DataSource view.
     */
    public static final String PROP_QTYORDERD = "QtyOrderd";
    /**
     * The property name constant equivalent to property, ActualUnitCost, of
     * respective DataSource view.
     */
    public static final String PROP_ACTUALUNITCOST = "ActualUnitCost";
    /**
     * The property name constant equivalent to property, QtyReceived, of
     * respective DataSource view.
     */
    public static final String PROP_QTYRECEIVED = "QtyReceived";
    /**
     * The property name constant equivalent to property, QtyReturned, of
     * respective DataSource view.
     */
    public static final String PROP_QTYRETURNED = "QtyReturned";

    /**
     * The javabean property equivalent of database column
     * vw_vendor_item_purchase_order_item.item_id
     */
    private int itemId;
    /**
     * The javabean property equivalent of database column
     * vw_vendor_item_purchase_order_item.vendor_id
     */
    private int vendorId;
    /**
     * The javabean property equivalent of database column
     * vw_vendor_item_purchase_order_item.vendor_item_no
     */
    private String vendorItemNo;
    /**
     * The javabean property equivalent of database column
     * vw_vendor_item_purchase_order_item.item_serial_no
     */
    private String itemSerialNo;
    /**
     * The javabean property equivalent of database column
     * vw_vendor_item_purchase_order_item.unit_cost
     */
    private double unitCost;
    /**
     * The javabean property equivalent of database column
     * vw_vendor_item_purchase_order_item.description
     */
    private String description;
    /**
     * The javabean property equivalent of database column
     * vw_vendor_item_purchase_order_item.qty_on_hand
     */
    private int qtyOnHand;
    /**
     * The javabean property equivalent of database column
     * vw_vendor_item_purchase_order_item.markup
     */
    private double markup;
    /**
     * The javabean property equivalent of database column
     * vw_vendor_item_purchase_order_item.override_retail
     */
    private int overrideRetail;
    /**
     * The javabean property equivalent of database column
     * vw_vendor_item_purchase_order_item.po_id
     */
    private int poId;
    /**
     * The javabean property equivalent of database column
     * vw_vendor_item_purchase_order_item.qty_orderd
     */
    private int qtyOrderd;
    /**
     * The javabean property equivalent of database column
     * vw_vendor_item_purchase_order_item.actual_unit_cost
     */
    private double actualUnitCost;
    /**
     * The javabean property equivalent of database column
     * vw_vendor_item_purchase_order_item.qty_received
     */
    private int qtyReceived;
    /**
     * The javabean property equivalent of database column
     * vw_vendor_item_purchase_order_item.qty_returned
     */
    private int qtyReturned;

    // Getter/Setter Methods

    /**
     * Default constructor.
     */
    public VwVendorItemPurchaseOrderItem() throws SystemException {
        super();
    }

    /**
     * Sets the value of member variable itemId
     */
    public void setItemId(int value) {
        this.itemId = value;
    }

    /**
     * Gets the value of member variable itemId
     */
    public int getItemId() {
        return this.itemId;
    }

    /**
     * Sets the value of member variable vendorId
     */
    public void setVendorId(int value) {
        this.vendorId = value;
    }

    /**
     * Gets the value of member variable vendorId
     */
    public int getVendorId() {
        return this.vendorId;
    }

    /**
     * Sets the value of member variable vendorItemNo
     */
    public void setVendorItemNo(String value) {
        this.vendorItemNo = value;
    }

    /**
     * Gets the value of member variable vendorItemNo
     */
    public String getVendorItemNo() {
        return this.vendorItemNo;
    }

    /**
     * Sets the value of member variable itemSerialNo
     */
    public void setItemSerialNo(String value) {
        this.itemSerialNo = value;
    }

    /**
     * Gets the value of member variable itemSerialNo
     */
    public String getItemSerialNo() {
        return this.itemSerialNo;
    }

    /**
     * Sets the value of member variable unitCost
     */
    public void setUnitCost(double value) {
        this.unitCost = value;
    }

    /**
     * Gets the value of member variable unitCost
     */
    public double getUnitCost() {
        return this.unitCost;
    }

    /**
     * Sets the value of member variable description
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of member variable description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the value of member variable qtyOnHand
     */
    public void setQtyOnHand(int value) {
        this.qtyOnHand = value;
    }

    /**
     * Gets the value of member variable qtyOnHand
     */
    public int getQtyOnHand() {
        return this.qtyOnHand;
    }

    /**
     * Sets the value of member variable markup
     */
    public void setMarkup(double value) {
        this.markup = value;
    }

    /**
     * Gets the value of member variable markup
     */
    public double getMarkup() {
        return this.markup;
    }

    /**
     * Sets the value of member variable overrideRetail
     */
    public void setOverrideRetail(int value) {
        this.overrideRetail = value;
    }

    /**
     * Gets the value of member variable overrideRetail
     */
    public int getOverrideRetail() {
        return this.overrideRetail;
    }

    /**
     * Sets the value of member variable poId
     */
    public void setPoId(int value) {
        this.poId = value;
    }

    /**
     * Gets the value of member variable poId
     */
    public int getPoId() {
        return this.poId;
    }

    /**
     * Sets the value of member variable qtyOrderd
     */
    public void setQtyOrderd(int value) {
        this.qtyOrderd = value;
    }

    /**
     * Gets the value of member variable qtyOrderd
     */
    public int getQtyOrderd() {
        return this.qtyOrderd;
    }

    /**
     * Sets the value of member variable actualUnitCost
     */
    public void setActualUnitCost(double value) {
        this.actualUnitCost = value;
    }

    /**
     * Gets the value of member variable actualUnitCost
     */
    public double getActualUnitCost() {
        return this.actualUnitCost;
    }

    /**
     * Sets the value of member variable qtyReceived
     */
    public void setQtyReceived(int value) {
        this.qtyReceived = value;
    }

    /**
     * Gets the value of member variable qtyReceived
     */
    public int getQtyReceived() {
        return this.qtyReceived;
    }

    /**
     * Sets the value of member variable qtyReturned
     */
    public void setQtyReturned(int value) {
        this.qtyReturned = value;
    }

    /**
     * Gets the value of member variable qtyReturned
     */
    public int getQtyReturned() {
        return this.qtyReturned;
    }

    /**
     * Stubbed initialization method designed to implemented by developer.
     */
    public void initBean() throws SystemException {
    }
}