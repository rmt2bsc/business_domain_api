package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;

import com.api.persistence.db.orm.OrmBean;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.SystemException;


/**
 * Peer object that maps to the purchase_order_item_return database table/view.
 *
 * @author auto generated.
 */
public class PurchaseOrderItemReturn extends OrmBean {




	// Property name constants that belong to respective DataSource, PurchaseOrderItemReturnView

/** The property name constant equivalent to property, PoItemReturnId, of respective DataSource view. */
  public static final String PROP_POITEMRETURNID = "PoItemReturnId";
/** The property name constant equivalent to property, PoReturnId, of respective DataSource view. */
  public static final String PROP_PORETURNID = "PoReturnId";
/** The property name constant equivalent to property, ItemId, of respective DataSource view. */
  public static final String PROP_ITEMID = "ItemId";
/** The property name constant equivalent to property, QtyRtn, of respective DataSource view. */
  public static final String PROP_QTYRTN = "QtyRtn";



	/** The javabean property equivalent of database column purchase_order_item_return.po_item_return_id */
  private int poItemReturnId;
/** The javabean property equivalent of database column purchase_order_item_return.po_return_id */
  private int poReturnId;
/** The javabean property equivalent of database column purchase_order_item_return.item_id */
  private int itemId;
/** The javabean property equivalent of database column purchase_order_item_return.qty_rtn */
  private int qtyRtn;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public PurchaseOrderItemReturn() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable poItemReturnId
 */
  public void setPoItemReturnId(int value) {
    this.poItemReturnId = value;
  }
/**
 * Gets the value of member variable poItemReturnId
 */
  public int getPoItemReturnId() {
    return this.poItemReturnId;
  }
/**
 * Sets the value of member variable poReturnId
 */
  public void setPoReturnId(int value) {
    this.poReturnId = value;
  }
/**
 * Gets the value of member variable poReturnId
 */
  public int getPoReturnId() {
    return this.poReturnId;
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
   final PurchaseOrderItemReturn other = (PurchaseOrderItemReturn) obj; 
   if (EqualityAssistant.notEqual(this.poItemReturnId, other.poItemReturnId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.poReturnId, other.poReturnId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.itemId, other.itemId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.qtyRtn, other.qtyRtn)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.poItemReturnId),
               HashCodeAssistant.hashObject(this.poReturnId),
               HashCodeAssistant.hashObject(this.itemId),
               HashCodeAssistant.hashObject(this.qtyRtn));
} 

@Override
public String toString() {
   return "PurchaseOrderItemReturn [poItemReturnId=" + poItemReturnId + 
          ", poReturnId=" + poReturnId + 
          ", itemId=" + itemId + 
          ", qtyRtn=" + qtyRtn  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}