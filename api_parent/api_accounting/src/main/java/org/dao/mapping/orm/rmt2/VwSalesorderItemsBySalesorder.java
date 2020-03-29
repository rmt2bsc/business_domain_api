package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the vw_salesorder_items_by_salesorder database table/view.
 *
 * @author auto generated.
 */
public class VwSalesorderItemsBySalesorder extends OrmBean {




	// Property name constants that belong to respective DataSource, VwSalesorderItemsBySalesorderView

/** The property name constant equivalent to property, SalesOrderItemId, of respective DataSource view. */
  public static final String PROP_SALESORDERITEMID = "SalesOrderItemId";
/** The property name constant equivalent to property, SoId, of respective DataSource view. */
  public static final String PROP_SOID = "SoId";
/** The property name constant equivalent to property, ItemId, of respective DataSource view. */
  public static final String PROP_ITEMID = "ItemId";
/** The property name constant equivalent to property, CreditorId, of respective DataSource view. */
  public static final String PROP_CREDITORID = "CreditorId";
/** The property name constant equivalent to property, ItemTypeId, of respective DataSource view. */
  public static final String PROP_ITEMTYPEID = "ItemTypeId";
/** The property name constant equivalent to property, ItemName, of respective DataSource view. */
  public static final String PROP_ITEMNAME = "ItemName";
/** The property name constant equivalent to property, VendorItemNo, of respective DataSource view. */
  public static final String PROP_VENDORITEMNO = "VendorItemNo";
/** The property name constant equivalent to property, ItemSerialNo, of respective DataSource view. */
  public static final String PROP_ITEMSERIALNO = "ItemSerialNo";
/** The property name constant equivalent to property, QtyOnHand, of respective DataSource view. */
  public static final String PROP_QTYONHAND = "QtyOnHand";
/** The property name constant equivalent to property, UnitCost, of respective DataSource view. */
  public static final String PROP_UNITCOST = "UnitCost";
/** The property name constant equivalent to property, Markup, of respective DataSource view. */
  public static final String PROP_MARKUP = "Markup";
/** The property name constant equivalent to property, RetailPrice, of respective DataSource view. */
  public static final String PROP_RETAILPRICE = "RetailPrice";
/** The property name constant equivalent to property, ItemTypeDescr, of respective DataSource view. */
  public static final String PROP_ITEMTYPEDESCR = "ItemTypeDescr";
/** The property name constant equivalent to property, ItemNameOverride, of respective DataSource view. */
  public static final String PROP_ITEMNAMEOVERRIDE = "ItemNameOverride";
/** The property name constant equivalent to property, OrderQty, of respective DataSource view. */
  public static final String PROP_ORDERQTY = "OrderQty";
/** The property name constant equivalent to property, BackOrderQty, of respective DataSource view. */
  public static final String PROP_BACKORDERQTY = "BackOrderQty";
/** The property name constant equivalent to property, InitUnitCost, of respective DataSource view. */
  public static final String PROP_INITUNITCOST = "InitUnitCost";
/** The property name constant equivalent to property, InitMarkup, of respective DataSource view. */
  public static final String PROP_INITMARKUP = "InitMarkup";
/** The property name constant equivalent to property, CustomerId, of respective DataSource view. */
  public static final String PROP_CUSTOMERID = "CustomerId";
/** The property name constant equivalent to property, Invoiced, of respective DataSource view. */
  public static final String PROP_INVOICED = "Invoiced";
/** The property name constant equivalent to property, PersonId, of respective DataSource view. */
  public static final String PROP_PERSONID = "PersonId";
/** The property name constant equivalent to property, BusinessId, of respective DataSource view. */
  public static final String PROP_BUSINESSID = "BusinessId";



	/** The javabean property equivalent of database column vw_salesorder_items_by_salesorder.sales_order_item_id */
  private int salesOrderItemId;
/** The javabean property equivalent of database column vw_salesorder_items_by_salesorder.so_id */
  private int soId;
/** The javabean property equivalent of database column vw_salesorder_items_by_salesorder.item_id */
  private int itemId;
/** The javabean property equivalent of database column vw_salesorder_items_by_salesorder.creditor_id */
  private int creditorId;
/** The javabean property equivalent of database column vw_salesorder_items_by_salesorder.item_type_id */
  private int itemTypeId;
/** The javabean property equivalent of database column vw_salesorder_items_by_salesorder.item_name */
  private String itemName;
/** The javabean property equivalent of database column vw_salesorder_items_by_salesorder.vendor_item_no */
  private String vendorItemNo;
/** The javabean property equivalent of database column vw_salesorder_items_by_salesorder.item_serial_no */
  private String itemSerialNo;
/** The javabean property equivalent of database column vw_salesorder_items_by_salesorder.qty_on_hand */
  private int qtyOnHand;
/** The javabean property equivalent of database column vw_salesorder_items_by_salesorder.unit_cost */
  private double unitCost;
/** The javabean property equivalent of database column vw_salesorder_items_by_salesorder.markup */
  private double markup;
/** The javabean property equivalent of database column vw_salesorder_items_by_salesorder.retail_price */
  private double retailPrice;
/** The javabean property equivalent of database column vw_salesorder_items_by_salesorder.item_type_descr */
  private String itemTypeDescr;
/** The javabean property equivalent of database column vw_salesorder_items_by_salesorder.item_name_override */
  private String itemNameOverride;
/** The javabean property equivalent of database column vw_salesorder_items_by_salesorder.order_qty */
  private double orderQty;
/** The javabean property equivalent of database column vw_salesorder_items_by_salesorder.back_order_qty */
  private double backOrderQty;
/** The javabean property equivalent of database column vw_salesorder_items_by_salesorder.init_unit_cost */
  private double initUnitCost;
/** The javabean property equivalent of database column vw_salesorder_items_by_salesorder.init_markup */
  private double initMarkup;
/** The javabean property equivalent of database column vw_salesorder_items_by_salesorder.customer_id */
  private int customerId;
/** The javabean property equivalent of database column vw_salesorder_items_by_salesorder.invoiced */
  private int invoiced;
/** The javabean property equivalent of database column vw_salesorder_items_by_salesorder.person_id */
  private int personId;
/** The javabean property equivalent of database column vw_salesorder_items_by_salesorder.business_id */
  private int businessId;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwSalesorderItemsBySalesorder() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable salesOrderItemId
 */
  public void setSalesOrderItemId(int value) {
    this.salesOrderItemId = value;
  }
/**
 * Gets the value of member variable salesOrderItemId
 */
  public int getSalesOrderItemId() {
    return this.salesOrderItemId;
  }
/**
 * Sets the value of member variable soId
 */
  public void setSoId(int value) {
    this.soId = value;
  }
/**
 * Gets the value of member variable soId
 */
  public int getSoId() {
    return this.soId;
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
 * Sets the value of member variable creditorId
 */
  public void setCreditorId(int value) {
    this.creditorId = value;
  }
/**
 * Gets the value of member variable creditorId
 */
  public int getCreditorId() {
    return this.creditorId;
  }
/**
 * Sets the value of member variable itemTypeId
 */
  public void setItemTypeId(int value) {
    this.itemTypeId = value;
  }
/**
 * Gets the value of member variable itemTypeId
 */
  public int getItemTypeId() {
    return this.itemTypeId;
  }
/**
 * Sets the value of member variable itemName
 */
  public void setItemName(String value) {
    this.itemName = value;
  }
/**
 * Gets the value of member variable itemName
 */
  public String getItemName() {
    return this.itemName;
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
 * Sets the value of member variable retailPrice
 */
  public void setRetailPrice(double value) {
    this.retailPrice = value;
  }
/**
 * Gets the value of member variable retailPrice
 */
  public double getRetailPrice() {
    return this.retailPrice;
  }
/**
 * Sets the value of member variable itemTypeDescr
 */
  public void setItemTypeDescr(String value) {
    this.itemTypeDescr = value;
  }
/**
 * Gets the value of member variable itemTypeDescr
 */
  public String getItemTypeDescr() {
    return this.itemTypeDescr;
  }
/**
 * Sets the value of member variable itemNameOverride
 */
  public void setItemNameOverride(String value) {
    this.itemNameOverride = value;
  }
/**
 * Gets the value of member variable itemNameOverride
 */
  public String getItemNameOverride() {
    return this.itemNameOverride;
  }
/**
 * Sets the value of member variable orderQty
 */
  public void setOrderQty(double value) {
    this.orderQty = value;
  }
/**
 * Gets the value of member variable orderQty
 */
  public double getOrderQty() {
    return this.orderQty;
  }
/**
 * Sets the value of member variable backOrderQty
 */
  public void setBackOrderQty(double value) {
    this.backOrderQty = value;
  }
/**
 * Gets the value of member variable backOrderQty
 */
  public double getBackOrderQty() {
    return this.backOrderQty;
  }
/**
 * Sets the value of member variable initUnitCost
 */
  public void setInitUnitCost(double value) {
    this.initUnitCost = value;
  }
/**
 * Gets the value of member variable initUnitCost
 */
  public double getInitUnitCost() {
    return this.initUnitCost;
  }
/**
 * Sets the value of member variable initMarkup
 */
  public void setInitMarkup(double value) {
    this.initMarkup = value;
  }
/**
 * Gets the value of member variable initMarkup
 */
  public double getInitMarkup() {
    return this.initMarkup;
  }
/**
 * Sets the value of member variable customerId
 */
  public void setCustomerId(int value) {
    this.customerId = value;
  }
/**
 * Gets the value of member variable customerId
 */
  public int getCustomerId() {
    return this.customerId;
  }
/**
 * Sets the value of member variable invoiced
 */
  public void setInvoiced(int value) {
    this.invoiced = value;
  }
/**
 * Gets the value of member variable invoiced
 */
  public int getInvoiced() {
    return this.invoiced;
  }
/**
 * Sets the value of member variable personId
 */
  public void setPersonId(int value) {
    this.personId = value;
  }
/**
 * Gets the value of member variable personId
 */
  public int getPersonId() {
    return this.personId;
  }
/**
 * Sets the value of member variable businessId
 */
  public void setBusinessId(int value) {
    this.businessId = value;
  }
/**
 * Gets the value of member variable businessId
 */
  public int getBusinessId() {
    return this.businessId;
  }

@Override
public boolean equals(Object obj) {
   if (this == obj) {
      return true;
   }
   if (obj == null) {
      return false;
   }
   if (getClass() != obj.getClass()) {
      return false;
   }
   final VwSalesorderItemsBySalesorder other = (VwSalesorderItemsBySalesorder) obj; 
   if (EqualityAssistant.notEqual(this.salesOrderItemId, other.salesOrderItemId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.soId, other.soId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.itemId, other.itemId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.creditorId, other.creditorId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.itemTypeId, other.itemTypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.itemName, other.itemName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.vendorItemNo, other.vendorItemNo)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.itemSerialNo, other.itemSerialNo)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.qtyOnHand, other.qtyOnHand)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.unitCost, other.unitCost)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.markup, other.markup)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.retailPrice, other.retailPrice)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.itemTypeDescr, other.itemTypeDescr)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.itemNameOverride, other.itemNameOverride)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.orderQty, other.orderQty)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.backOrderQty, other.backOrderQty)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.initUnitCost, other.initUnitCost)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.initMarkup, other.initMarkup)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.customerId, other.customerId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.invoiced, other.invoiced)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.personId, other.personId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.businessId, other.businessId)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.salesOrderItemId),
               HashCodeAssistant.hashObject(this.soId),
               HashCodeAssistant.hashObject(this.itemId),
               HashCodeAssistant.hashObject(this.creditorId),
               HashCodeAssistant.hashObject(this.itemTypeId),
               HashCodeAssistant.hashObject(this.itemName),
               HashCodeAssistant.hashObject(this.vendorItemNo),
               HashCodeAssistant.hashObject(this.itemSerialNo),
               HashCodeAssistant.hashObject(this.qtyOnHand),
               HashCodeAssistant.hashObject(this.unitCost),
               HashCodeAssistant.hashObject(this.markup),
               HashCodeAssistant.hashObject(this.retailPrice),
               HashCodeAssistant.hashObject(this.itemTypeDescr),
               HashCodeAssistant.hashObject(this.itemNameOverride),
               HashCodeAssistant.hashObject(this.orderQty),
               HashCodeAssistant.hashObject(this.backOrderQty),
               HashCodeAssistant.hashObject(this.initUnitCost),
               HashCodeAssistant.hashObject(this.initMarkup),
               HashCodeAssistant.hashObject(this.customerId),
               HashCodeAssistant.hashObject(this.invoiced),
               HashCodeAssistant.hashObject(this.personId),
               HashCodeAssistant.hashObject(this.businessId));
} 

@Override
public String toString() {
   return "VwSalesorderItemsBySalesorder [salesOrderItemId=" + salesOrderItemId + 
          ", soId=" + soId + 
          ", itemId=" + itemId + 
          ", creditorId=" + creditorId + 
          ", itemTypeId=" + itemTypeId + 
          ", itemName=" + itemName + 
          ", vendorItemNo=" + vendorItemNo + 
          ", itemSerialNo=" + itemSerialNo + 
          ", qtyOnHand=" + qtyOnHand + 
          ", unitCost=" + unitCost + 
          ", markup=" + markup + 
          ", retailPrice=" + retailPrice + 
          ", itemTypeDescr=" + itemTypeDescr + 
          ", itemNameOverride=" + itemNameOverride + 
          ", orderQty=" + orderQty + 
          ", backOrderQty=" + backOrderQty + 
          ", initUnitCost=" + initUnitCost + 
          ", initMarkup=" + initMarkup + 
          ", customerId=" + customerId + 
          ", invoiced=" + invoiced + 
          ", personId=" + personId + 
          ", businessId=" + businessId  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}