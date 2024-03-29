package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the purchase_order_items database table/view.
 *
 * @author auto generated.
 */
public class PurchaseOrderItems extends OrmBean {




	// Property name constants that belong to respective DataSource, PurchaseOrderItemsView

/** The property name constant equivalent to property, PoItemId, of respective DataSource view. */
  public static final String PROP_POITEMID = "PoItemId";
/** The property name constant equivalent to property, PoId, of respective DataSource view. */
  public static final String PROP_POID = "PoId";
/** The property name constant equivalent to property, ItemId, of respective DataSource view. */
  public static final String PROP_ITEMID = "ItemId";
/** The property name constant equivalent to property, UnitCost, of respective DataSource view. */
  public static final String PROP_UNITCOST = "UnitCost";
/** The property name constant equivalent to property, Qty, of respective DataSource view. */
  public static final String PROP_QTY = "Qty";
/** The property name constant equivalent to property, QtyRcvd, of respective DataSource view. */
  public static final String PROP_QTYRCVD = "QtyRcvd";
/** The property name constant equivalent to property, QtyRtn, of respective DataSource view. */
  public static final String PROP_QTYRTN = "QtyRtn";
/** The property name constant equivalent to property, DateCreated, of respective DataSource view. */
  public static final String PROP_DATECREATED = "DateCreated";
/** The property name constant equivalent to property, DateUpdated, of respective DataSource view. */
  public static final String PROP_DATEUPDATED = "DateUpdated";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";



	/** The javabean property equivalent of database column purchase_order_items.po_item_id */
  private int poItemId;
/** The javabean property equivalent of database column purchase_order_items.po_id */
  private int poId;
/** The javabean property equivalent of database column purchase_order_items.item_id */
  private int itemId;
/** The javabean property equivalent of database column purchase_order_items.unit_cost */
  private double unitCost;
/** The javabean property equivalent of database column purchase_order_items.qty */
  private int qty;
/** The javabean property equivalent of database column purchase_order_items.qty_rcvd */
  private int qtyRcvd;
/** The javabean property equivalent of database column purchase_order_items.qty_rtn */
  private int qtyRtn;
/** The javabean property equivalent of database column purchase_order_items.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column purchase_order_items.date_updated */
  private java.util.Date dateUpdated;
/** The javabean property equivalent of database column purchase_order_items.user_id */
  private String userId;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public PurchaseOrderItems() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable poItemId
 */
  public void setPoItemId(int value) {
    this.poItemId = value;
  }
/**
 * Gets the value of member variable poItemId
 */
  public int getPoItemId() {
    return this.poItemId;
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
 * Sets the value of member variable qty
 */
  public void setQty(int value) {
    this.qty = value;
  }
/**
 * Gets the value of member variable qty
 */
  public int getQty() {
    return this.qty;
  }
/**
 * Sets the value of member variable qtyRcvd
 */
  public void setQtyRcvd(int value) {
    this.qtyRcvd = value;
  }
/**
 * Gets the value of member variable qtyRcvd
 */
  public int getQtyRcvd() {
    return this.qtyRcvd;
  }
/**
 * Sets the value of member variable qtyRtn
 */
  public void setQtyRtn(int value) {
    this.qtyRtn = value;
  }
/**
 * Gets the value of member variable qtyRtn
 */
  public int getQtyRtn() {
    return this.qtyRtn;
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
   final PurchaseOrderItems other = (PurchaseOrderItems) obj; 
   if (EqualityAssistant.notEqual(this.poItemId, other.poItemId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.poId, other.poId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.itemId, other.itemId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.unitCost, other.unitCost)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.qty, other.qty)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.qtyRcvd, other.qtyRcvd)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.qtyRtn, other.qtyRtn)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.poItemId),
               HashCodeAssistant.hashObject(this.poId),
               HashCodeAssistant.hashObject(this.itemId),
               HashCodeAssistant.hashObject(this.unitCost),
               HashCodeAssistant.hashObject(this.qty),
               HashCodeAssistant.hashObject(this.qtyRcvd),
               HashCodeAssistant.hashObject(this.qtyRtn));
} 

@Override
public String toString() {
   return "PurchaseOrderItems [poItemId=" + poItemId + 
          ", poId=" + poId + 
          ", itemId=" + itemId + 
          ", unitCost=" + unitCost + 
          ", qty=" + qty + 
          ", qtyRcvd=" + qtyRcvd + 
          ", qtyRtn=" + qtyRtn  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}