package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the sales_order_items database table/view.
 *
 * @author auto generated.
 */
public class SalesOrderItems extends OrmBean {




	// Property name constants that belong to respective DataSource, SalesOrderItemsView

/** The property name constant equivalent to property, SoItemId, of respective DataSource view. */
  public static final String PROP_SOITEMID = "SoItemId";
/** The property name constant equivalent to property, ItemId, of respective DataSource view. */
  public static final String PROP_ITEMID = "ItemId";
/** The property name constant equivalent to property, SoId, of respective DataSource view. */
  public static final String PROP_SOID = "SoId";
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



	/** The javabean property equivalent of database column sales_order_items.so_item_id */
  private int soItemId;
/** The javabean property equivalent of database column sales_order_items.item_id */
  private int itemId;
/** The javabean property equivalent of database column sales_order_items.so_id */
  private int soId;
/** The javabean property equivalent of database column sales_order_items.item_name_override */
  private String itemNameOverride;
/** The javabean property equivalent of database column sales_order_items.order_qty */
  private double orderQty;
/** The javabean property equivalent of database column sales_order_items.back_order_qty */
  private double backOrderQty;
/** The javabean property equivalent of database column sales_order_items.init_unit_cost */
  private double initUnitCost;
/** The javabean property equivalent of database column sales_order_items.init_markup */
  private double initMarkup;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public SalesOrderItems() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable soItemId
 */
  public void setSoItemId(int value) {
    this.soItemId = value;
  }
/**
 * Gets the value of member variable soItemId
 */
  public int getSoItemId() {
    return this.soItemId;
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
   final SalesOrderItems other = (SalesOrderItems) obj; 
   if (EqualityAssistant.notEqual(this.soItemId, other.soItemId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.itemId, other.itemId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.soId, other.soId)) {
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
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.soItemId),
               HashCodeAssistant.hashObject(this.itemId),
               HashCodeAssistant.hashObject(this.soId),
               HashCodeAssistant.hashObject(this.itemNameOverride),
               HashCodeAssistant.hashObject(this.orderQty),
               HashCodeAssistant.hashObject(this.backOrderQty),
               HashCodeAssistant.hashObject(this.initUnitCost),
               HashCodeAssistant.hashObject(this.initMarkup));
} 

@Override
public String toString() {
   return "SalesOrderItems [soItemId=" + soItemId + 
          ", itemId=" + itemId + 
          ", soId=" + soId + 
          ", itemNameOverride=" + itemNameOverride + 
          ", orderQty=" + orderQty + 
          ", backOrderQty=" + backOrderQty + 
          ", initUnitCost=" + initUnitCost + 
          ", initMarkup=" + initMarkup  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}