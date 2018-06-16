package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the vw_xact_item_list database table/view.
 *
 * @author auto generated.
 */
public class VwXactItemList extends OrmBean {




	// Property name constants that belong to respective DataSource, VwXactItemListView

/** The property name constant equivalent to property, ItemActivityId, of respective DataSource view. */
  public static final String PROP_ITEMACTIVITYID = "ItemActivityId";
/** The property name constant equivalent to property, XactTypeItemId, of respective DataSource view. */
  public static final String PROP_XACTTYPEITEMID = "XactTypeItemId";
/** The property name constant equivalent to property, XactId, of respective DataSource view. */
  public static final String PROP_XACTID = "XactId";
/** The property name constant equivalent to property, XactTypeId, of respective DataSource view. */
  public static final String PROP_XACTTYPEID = "XactTypeId";
/** The property name constant equivalent to property, Amount, of respective DataSource view. */
  public static final String PROP_AMOUNT = "Amount";
/** The property name constant equivalent to property, Name, of respective DataSource view. */
  public static final String PROP_NAME = "Name";
/** The property name constant equivalent to property, ItemXactReason, of respective DataSource view. */
  public static final String PROP_ITEMXACTREASON = "ItemXactReason";



	/** The javabean property equivalent of database column vw_xact_item_list.item_activity_id */
  private int itemActivityId;
/** The javabean property equivalent of database column vw_xact_item_list.xact_type_item_id */
  private int xactTypeItemId;
/** The javabean property equivalent of database column vw_xact_item_list.xact_id */
  private int xactId;
/** The javabean property equivalent of database column vw_xact_item_list.xact_type_id */
  private int xactTypeId;
/** The javabean property equivalent of database column vw_xact_item_list.amount */
  private double amount;
/** The javabean property equivalent of database column vw_xact_item_list.name */
  private String name;
/** The javabean property equivalent of database column vw_xact_item_list.item_xact_reason */
  private String itemXactReason;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwXactItemList() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable itemActivityId
 */
  public void setItemActivityId(int value) {
    this.itemActivityId = value;
  }
/**
 * Gets the value of member variable itemActivityId
 */
  public int getItemActivityId() {
    return this.itemActivityId;
  }
/**
 * Sets the value of member variable xactTypeItemId
 */
  public void setXactTypeItemId(int value) {
    this.xactTypeItemId = value;
  }
/**
 * Gets the value of member variable xactTypeItemId
 */
  public int getXactTypeItemId() {
    return this.xactTypeItemId;
  }
/**
 * Sets the value of member variable xactId
 */
  public void setXactId(int value) {
    this.xactId = value;
  }
/**
 * Gets the value of member variable xactId
 */
  public int getXactId() {
    return this.xactId;
  }
/**
 * Sets the value of member variable xactTypeId
 */
  public void setXactTypeId(int value) {
    this.xactTypeId = value;
  }
/**
 * Gets the value of member variable xactTypeId
 */
  public int getXactTypeId() {
    return this.xactTypeId;
  }
/**
 * Sets the value of member variable amount
 */
  public void setAmount(double value) {
    this.amount = value;
  }
/**
 * Gets the value of member variable amount
 */
  public double getAmount() {
    return this.amount;
  }
/**
 * Sets the value of member variable name
 */
  public void setName(String value) {
    this.name = value;
  }
/**
 * Gets the value of member variable name
 */
  public String getName() {
    return this.name;
  }
/**
 * Sets the value of member variable itemXactReason
 */
  public void setItemXactReason(String value) {
    this.itemXactReason = value;
  }
/**
 * Gets the value of member variable itemXactReason
 */
  public String getItemXactReason() {
    return this.itemXactReason;
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
   final VwXactItemList other = (VwXactItemList) obj; 
   if (EqualityAssistant.notEqual(this.itemActivityId, other.itemActivityId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactTypeItemId, other.xactTypeItemId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactId, other.xactId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactTypeId, other.xactTypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.amount, other.amount)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.name, other.name)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.itemXactReason, other.itemXactReason)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.itemActivityId),
               HashCodeAssistant.hashObject(this.xactTypeItemId),
               HashCodeAssistant.hashObject(this.xactId),
               HashCodeAssistant.hashObject(this.xactTypeId),
               HashCodeAssistant.hashObject(this.amount),
               HashCodeAssistant.hashObject(this.name),
               HashCodeAssistant.hashObject(this.itemXactReason));
} 

@Override
public String toString() {
   return "VwXactItemList [itemActivityId=" + itemActivityId + 
          ", xactTypeItemId=" + xactTypeItemId + 
          ", xactId=" + xactId + 
          ", xactTypeId=" + xactTypeId + 
          ", amount=" + amount + 
          ", name=" + name + 
          ", itemXactReason=" + itemXactReason  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}