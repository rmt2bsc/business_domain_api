package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the vw_item_master database table/view.
 *
 * @author auto generated.
 */
public class VwItemMaster extends OrmBean {




	// Property name constants that belong to respective DataSource, VwItemMasterView

/** The property name constant equivalent to property, Id, of respective DataSource view. */
  public static final String PROP_ID = "Id";
/** The property name constant equivalent to property, VendorId, of respective DataSource view. */
  public static final String PROP_VENDORID = "VendorId";
/** The property name constant equivalent to property, ItemTypeId, of respective DataSource view. */
  public static final String PROP_ITEMTYPEID = "ItemTypeId";
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
/** The property name constant equivalent to property, ItemCreateDate, of respective DataSource view. */
  public static final String PROP_ITEMCREATEDATE = "ItemCreateDate";
/** The property name constant equivalent to property, ItemUpdateDate, of respective DataSource view. */
  public static final String PROP_ITEMUPDATEDATE = "ItemUpdateDate";
/** The property name constant equivalent to property, UpdateUserid, of respective DataSource view. */
  public static final String PROP_UPDATEUSERID = "UpdateUserid";
/** The property name constant equivalent to property, ItemTypeDescription, of respective DataSource view. */
  public static final String PROP_ITEMTYPEDESCRIPTION = "ItemTypeDescription";
/** The property name constant equivalent to property, ItemStatus, of respective DataSource view. */
  public static final String PROP_ITEMSTATUS = "ItemStatus";
/** The property name constant equivalent to property, ItemStatusId, of respective DataSource view. */
  public static final String PROP_ITEMSTATUSID = "ItemStatusId";



	/** The javabean property equivalent of database column vw_item_master.id */
  private int id;
/** The javabean property equivalent of database column vw_item_master.vendor_id */
  private int vendorId;
/** The javabean property equivalent of database column vw_item_master.item_type_id */
  private int itemTypeId;
/** The javabean property equivalent of database column vw_item_master.description */
  private String description;
/** The javabean property equivalent of database column vw_item_master.vendor_item_no */
  private String vendorItemNo;
/** The javabean property equivalent of database column vw_item_master.item_serial_no */
  private String itemSerialNo;
/** The javabean property equivalent of database column vw_item_master.qty_on_hand */
  private int qtyOnHand;
/** The javabean property equivalent of database column vw_item_master.unit_cost */
  private double unitCost;
/** The javabean property equivalent of database column vw_item_master.markup */
  private double markup;
/** The javabean property equivalent of database column vw_item_master.retail_price */
  private double retailPrice;
/** The javabean property equivalent of database column vw_item_master.override_retail */
  private int overrideRetail;
/** The javabean property equivalent of database column vw_item_master.active */
  private int active;
/** The javabean property equivalent of database column vw_item_master.item_create_date */
  private java.util.Date itemCreateDate;
/** The javabean property equivalent of database column vw_item_master.item_update_date */
  private java.util.Date itemUpdateDate;
/** The javabean property equivalent of database column vw_item_master.update_userid */
  private String updateUserid;
/** The javabean property equivalent of database column vw_item_master.item_type_description */
  private String itemTypeDescription;
/** The javabean property equivalent of database column vw_item_master.item_status */
  private String itemStatus;
/** The javabean property equivalent of database column vw_item_master.item_status_id */
  private int itemStatusId;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwItemMaster() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable id
 */
  public void setId(int value) {
    this.id = value;
  }
/**
 * Gets the value of member variable id
 */
  public int getId() {
    return this.id;
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
 * Sets the value of member variable itemCreateDate
 */
  public void setItemCreateDate(java.util.Date value) {
    this.itemCreateDate = value;
  }
/**
 * Gets the value of member variable itemCreateDate
 */
  public java.util.Date getItemCreateDate() {
    return this.itemCreateDate;
  }
/**
 * Sets the value of member variable itemUpdateDate
 */
  public void setItemUpdateDate(java.util.Date value) {
    this.itemUpdateDate = value;
  }
/**
 * Gets the value of member variable itemUpdateDate
 */
  public java.util.Date getItemUpdateDate() {
    return this.itemUpdateDate;
  }
/**
 * Sets the value of member variable updateUserid
 */
  public void setUpdateUserid(String value) {
    this.updateUserid = value;
  }
/**
 * Gets the value of member variable updateUserid
 */
  public String getUpdateUserid() {
    return this.updateUserid;
  }
/**
 * Sets the value of member variable itemTypeDescription
 */
  public void setItemTypeDescription(String value) {
    this.itemTypeDescription = value;
  }
/**
 * Gets the value of member variable itemTypeDescription
 */
  public String getItemTypeDescription() {
    return this.itemTypeDescription;
  }
/**
 * Sets the value of member variable itemStatus
 */
  public void setItemStatus(String value) {
    this.itemStatus = value;
  }
/**
 * Gets the value of member variable itemStatus
 */
  public String getItemStatus() {
    return this.itemStatus;
  }
/**
 * Sets the value of member variable itemStatusId
 */
  public void setItemStatusId(int value) {
    this.itemStatusId = value;
  }
/**
 * Gets the value of member variable itemStatusId
 */
  public int getItemStatusId() {
    return this.itemStatusId;
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
   final VwItemMaster other = (VwItemMaster) obj; 
   if (EqualityAssistant.notEqual(this.id, other.id)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.vendorId, other.vendorId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.itemTypeId, other.itemTypeId)) {
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
   if (EqualityAssistant.notEqual(this.itemCreateDate, other.itemCreateDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.itemUpdateDate, other.itemUpdateDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.updateUserid, other.updateUserid)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.itemTypeDescription, other.itemTypeDescription)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.itemStatus, other.itemStatus)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.itemStatusId, other.itemStatusId)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.id),
               HashCodeAssistant.hashObject(this.vendorId),
               HashCodeAssistant.hashObject(this.itemTypeId),
               HashCodeAssistant.hashObject(this.description),
               HashCodeAssistant.hashObject(this.vendorItemNo),
               HashCodeAssistant.hashObject(this.itemSerialNo),
               HashCodeAssistant.hashObject(this.qtyOnHand),
               HashCodeAssistant.hashObject(this.unitCost),
               HashCodeAssistant.hashObject(this.markup),
               HashCodeAssistant.hashObject(this.retailPrice),
               HashCodeAssistant.hashObject(this.overrideRetail),
               HashCodeAssistant.hashObject(this.active),
               HashCodeAssistant.hashObject(this.itemCreateDate),
               HashCodeAssistant.hashObject(this.itemUpdateDate),
               HashCodeAssistant.hashObject(this.updateUserid),
               HashCodeAssistant.hashObject(this.itemTypeDescription),
               HashCodeAssistant.hashObject(this.itemStatus),
               HashCodeAssistant.hashObject(this.itemStatusId));
} 

@Override
public String toString() {
   return "VwItemMaster [id=" + id + 
          ", vendorId=" + vendorId + 
          ", itemTypeId=" + itemTypeId + 
          ", description=" + description + 
          ", vendorItemNo=" + vendorItemNo + 
          ", itemSerialNo=" + itemSerialNo + 
          ", qtyOnHand=" + qtyOnHand + 
          ", unitCost=" + unitCost + 
          ", markup=" + markup + 
          ", retailPrice=" + retailPrice + 
          ", overrideRetail=" + overrideRetail + 
          ", active=" + active + 
          ", itemCreateDate=" + itemCreateDate + 
          ", itemUpdateDate=" + itemUpdateDate + 
          ", updateUserid=" + updateUserid + 
          ", itemTypeDescription=" + itemTypeDescription + 
          ", itemStatus=" + itemStatus + 
          ", itemStatusId=" + itemStatusId  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}