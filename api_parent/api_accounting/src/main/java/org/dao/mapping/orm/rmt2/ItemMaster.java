package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;

import com.api.persistence.db.orm.OrmBean;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.SystemException;


/**
 * Peer object that maps to the item_master database table/view.
 *
 * @author auto generated.
 */
public class ItemMaster extends OrmBean {




	// Property name constants that belong to respective DataSource, ItemMasterView

/** The property name constant equivalent to property, ItemId, of respective DataSource view. */
  public static final String PROP_ITEMID = "ItemId";
/** The property name constant equivalent to property, ItemTypeId, of respective DataSource view. */
  public static final String PROP_ITEMTYPEID = "ItemTypeId";
/** The property name constant equivalent to property, CreditorId, of respective DataSource view. */
  public static final String PROP_CREDITORID = "CreditorId";
/** The property name constant equivalent to property, Description, of respective DataSource view. */
  public static final String PROP_DESCRIPTION = "Description";
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
/** The property name constant equivalent to property, OverrideRetail, of respective DataSource view. */
  public static final String PROP_OVERRIDERETAIL = "OverrideRetail";
/** The property name constant equivalent to property, Active, of respective DataSource view. */
  public static final String PROP_ACTIVE = "Active";
/** The property name constant equivalent to property, DateCreated, of respective DataSource view. */
  public static final String PROP_DATECREATED = "DateCreated";
/** The property name constant equivalent to property, DateUpdated, of respective DataSource view. */
  public static final String PROP_DATEUPDATED = "DateUpdated";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";
/** The property name constant equivalent to property, IpCreated, of respective DataSource view. */
  public static final String PROP_IPCREATED = "IpCreated";
/** The property name constant equivalent to property, IpUpdated, of respective DataSource view. */
  public static final String PROP_IPUPDATED = "IpUpdated";



	/** The javabean property equivalent of database column item_master.item_id */
  private int itemId;
/** The javabean property equivalent of database column item_master.item_type_id */
  private int itemTypeId;
/** The javabean property equivalent of database column item_master.creditor_id */
  private int creditorId;
/** The javabean property equivalent of database column item_master.description */
  private String description;
/** The javabean property equivalent of database column item_master.vendor_item_no */
  private String vendorItemNo;
/** The javabean property equivalent of database column item_master.item_serial_no */
  private String itemSerialNo;
/** The javabean property equivalent of database column item_master.qty_on_hand */
  private int qtyOnHand;
/** The javabean property equivalent of database column item_master.unit_cost */
  private double unitCost;
/** The javabean property equivalent of database column item_master.markup */
  private double markup;
/** The javabean property equivalent of database column item_master.retail_price */
  private double retailPrice;
/** The javabean property equivalent of database column item_master.override_retail */
  private int overrideRetail;
/** The javabean property equivalent of database column item_master.active */
  private int active;
/** The javabean property equivalent of database column item_master.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column item_master.date_updated */
  private java.util.Date dateUpdated;
/** The javabean property equivalent of database column item_master.user_id */
  private String userId;
/** The javabean property equivalent of database column item_master.ip_created */
  private String ipCreated;
/** The javabean property equivalent of database column item_master.ip_updated */
  private String ipUpdated;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public ItemMaster() throws SystemException {
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
 * Sets the value of member variable active
 */
  public void setActive(int value) {
    this.active = value;
  }
/**
 * Gets the value of member variable active
 */
  public int getActive() {
    return this.active;
  }
/**
 * Sets the value of member variable dateCreated
 */
  public void setDateCreated(java.util.Date value) {
    this.dateCreated = value;
  }
/**
 * Gets the value of member variable dateCreated
 */
  public java.util.Date getDateCreated() {
    return this.dateCreated;
  }
/**
 * Sets the value of member variable dateUpdated
 */
  public void setDateUpdated(java.util.Date value) {
    this.dateUpdated = value;
  }
/**
 * Gets the value of member variable dateUpdated
 */
  public java.util.Date getDateUpdated() {
    return this.dateUpdated;
  }
/**
 * Sets the value of member variable userId
 */
  public void setUserId(String value) {
    this.userId = value;
  }
/**
 * Gets the value of member variable userId
 */
  public String getUserId() {
    return this.userId;
  }
/**
 * Sets the value of member variable ipCreated
 */
  public void setIpCreated(String value) {
    this.ipCreated = value;
  }
/**
 * Gets the value of member variable ipCreated
 */
  public String getIpCreated() {
    return this.ipCreated;
  }
/**
 * Sets the value of member variable ipUpdated
 */
  public void setIpUpdated(String value) {
    this.ipUpdated = value;
  }
/**
 * Gets the value of member variable ipUpdated
 */
  public String getIpUpdated() {
    return this.ipUpdated;
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
   final ItemMaster other = (ItemMaster) obj; 
   if (EqualityAssistant.notEqual(this.itemId, other.itemId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.itemTypeId, other.itemTypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.creditorId, other.creditorId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.description, other.description)) {
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
   if (EqualityAssistant.notEqual(this.overrideRetail, other.overrideRetail)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.active, other.active)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.itemId),
               HashCodeAssistant.hashObject(this.itemTypeId),
               HashCodeAssistant.hashObject(this.creditorId),
               HashCodeAssistant.hashObject(this.description),
               HashCodeAssistant.hashObject(this.vendorItemNo),
               HashCodeAssistant.hashObject(this.itemSerialNo),
               HashCodeAssistant.hashObject(this.qtyOnHand),
               HashCodeAssistant.hashObject(this.unitCost),
               HashCodeAssistant.hashObject(this.markup),
               HashCodeAssistant.hashObject(this.retailPrice),
               HashCodeAssistant.hashObject(this.overrideRetail),
               HashCodeAssistant.hashObject(this.active));
} 

@Override
public String toString() {
   return "ItemMaster [itemId=" + itemId + 
          ", itemTypeId=" + itemTypeId + 
          ", creditorId=" + creditorId + 
          ", description=" + description + 
          ", vendorItemNo=" + vendorItemNo + 
          ", itemSerialNo=" + itemSerialNo + 
          ", qtyOnHand=" + qtyOnHand + 
          ", unitCost=" + unitCost + 
          ", markup=" + markup + 
          ", retailPrice=" + retailPrice + 
          ", overrideRetail=" + overrideRetail + 
          ", active=" + active  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}