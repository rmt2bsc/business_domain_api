package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the vw_vendor_items database table/view.
 *
 * @author auto generated.
 */
public class VwVendorItems extends OrmBean {




	// Property name constants that belong to respective DataSource, VwVendorItemsView

/** The property name constant equivalent to property, ItemId, of respective DataSource view. */
  public static final String PROP_ITEMID = "ItemId";
/** The property name constant equivalent to property, CreditorId, of respective DataSource view. */
  public static final String PROP_CREDITORID = "CreditorId";
/** The property name constant equivalent to property, VendorItemNo, of respective DataSource view. */
  public static final String PROP_VENDORITEMNO = "VendorItemNo";
/** The property name constant equivalent to property, ItemSerialNo, of respective DataSource view. */
  public static final String PROP_ITEMSERIALNO = "ItemSerialNo";
/** The property name constant equivalent to property, UnitCost, of respective DataSource view. */
  public static final String PROP_UNITCOST = "UnitCost";
/** The property name constant equivalent to property, Description, of respective DataSource view. */
  public static final String PROP_DESCRIPTION = "Description";
/** The property name constant equivalent to property, QtyOnHand, of respective DataSource view. */
  public static final String PROP_QTYONHAND = "QtyOnHand";
/** The property name constant equivalent to property, Markup, of respective DataSource view. */
  public static final String PROP_MARKUP = "Markup";
/** The property name constant equivalent to property, OverrideRetail, of respective DataSource view. */
  public static final String PROP_OVERRIDERETAIL = "OverrideRetail";



	/** The javabean property equivalent of database column vw_vendor_items.item_id */
  private int itemId;
/** The javabean property equivalent of database column vw_vendor_items.creditor_id */
  private int creditorId;
/** The javabean property equivalent of database column vw_vendor_items.vendor_item_no */
  private String vendorItemNo;
/** The javabean property equivalent of database column vw_vendor_items.item_serial_no */
  private String itemSerialNo;
/** The javabean property equivalent of database column vw_vendor_items.unit_cost */
  private double unitCost;
/** The javabean property equivalent of database column vw_vendor_items.description */
  private String description;
/** The javabean property equivalent of database column vw_vendor_items.qty_on_hand */
  private int qtyOnHand;
/** The javabean property equivalent of database column vw_vendor_items.markup */
  private double markup;
/** The javabean property equivalent of database column vw_vendor_items.override_retail */
  private int overrideRetail;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwVendorItems() throws SystemException {
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
   final VwVendorItems other = (VwVendorItems) obj; 
   if (EqualityAssistant.notEqual(this.itemId, other.itemId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.creditorId, other.creditorId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.vendorItemNo, other.vendorItemNo)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.itemSerialNo, other.itemSerialNo)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.unitCost, other.unitCost)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.description, other.description)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.qtyOnHand, other.qtyOnHand)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.markup, other.markup)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.overrideRetail, other.overrideRetail)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.itemId),
               HashCodeAssistant.hashObject(this.creditorId),
               HashCodeAssistant.hashObject(this.vendorItemNo),
               HashCodeAssistant.hashObject(this.itemSerialNo),
               HashCodeAssistant.hashObject(this.unitCost),
               HashCodeAssistant.hashObject(this.description),
               HashCodeAssistant.hashObject(this.qtyOnHand),
               HashCodeAssistant.hashObject(this.markup),
               HashCodeAssistant.hashObject(this.overrideRetail));
} 

@Override
public String toString() {
   return "VwVendorItems [itemId=" + itemId + 
          ", creditorId=" + creditorId + 
          ", vendorItemNo=" + vendorItemNo + 
          ", itemSerialNo=" + itemSerialNo + 
          ", unitCost=" + unitCost + 
          ", description=" + description + 
          ", qtyOnHand=" + qtyOnHand + 
          ", markup=" + markup + 
          ", overrideRetail=" + overrideRetail  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}