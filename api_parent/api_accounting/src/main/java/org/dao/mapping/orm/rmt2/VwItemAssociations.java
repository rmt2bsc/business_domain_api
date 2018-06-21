package org.dao.mapping.orm.rmt2;


import com.SystemException;
import com.api.persistence.db.orm.OrmBean;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;


/**
 * Peer object that maps to the vw_item_associations database table/view.
 *
 * @author auto generated.
 */
public class VwItemAssociations extends OrmBean {




	// Property name constants that belong to respective DataSource, VwItemAssociationsView

/** The property name constant equivalent to property, AssocId, of respective DataSource view. */
  public static final String PROP_ASSOCID = "AssocId";
/** The property name constant equivalent to property, AssocItemId, of respective DataSource view. */
  public static final String PROP_ASSOCITEMID = "AssocItemId";
/** The property name constant equivalent to property, ItemId, of respective DataSource view. */
  public static final String PROP_ITEMID = "ItemId";
/** The property name constant equivalent to property, ItemCost, of respective DataSource view. */
  public static final String PROP_ITEMCOST = "ItemCost";
/** The property name constant equivalent to property, OrderQty, of respective DataSource view. */
  public static final String PROP_ORDERQTY = "OrderQty";
/** The property name constant equivalent to property, AssocType, of respective DataSource view. */
  public static final String PROP_ASSOCTYPE = "AssocType";



	/** The javabean property equivalent of database column vw_item_associations.assoc_id */
  private int assocId;
/** The javabean property equivalent of database column vw_item_associations.assoc_item_id */
  private int assocItemId;
/** The javabean property equivalent of database column vw_item_associations.item_id */
  private int itemId;
/** The javabean property equivalent of database column vw_item_associations.item_cost */
  private double itemCost;
/** The javabean property equivalent of database column vw_item_associations.order_qty */
  private double orderQty;
/** The javabean property equivalent of database column vw_item_associations.assoc_type */
  private String assocType;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwItemAssociations() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable assocId
 */
  public void setAssocId(int value) {
    this.assocId = value;
  }
/**
 * Gets the value of member variable assocId
 */
  public int getAssocId() {
    return this.assocId;
  }
/**
 * Sets the value of member variable assocItemId
 */
  public void setAssocItemId(int value) {
    this.assocItemId = value;
  }
/**
 * Gets the value of member variable assocItemId
 */
  public int getAssocItemId() {
    return this.assocItemId;
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
 * Sets the value of member variable itemCost
 */
  public void setItemCost(double value) {
    this.itemCost = value;
  }
/**
 * Gets the value of member variable itemCost
 */
  public double getItemCost() {
    return this.itemCost;
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
 * Sets the value of member variable assocType
 */
  public void setAssocType(String value) {
    this.assocType = value;
  }
/**
 * Gets the value of member variable assocType
 */
  public String getAssocType() {
    return this.assocType;
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
   final VwItemAssociations other = (VwItemAssociations) obj; 
   if (EqualityAssistant.notEqual(this.assocId, other.assocId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.assocItemId, other.assocItemId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.itemId, other.itemId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.itemCost, other.itemCost)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.orderQty, other.orderQty)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.assocType, other.assocType)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.assocId),
               HashCodeAssistant.hashObject(this.assocItemId),
               HashCodeAssistant.hashObject(this.itemId),
               HashCodeAssistant.hashObject(this.itemCost),
               HashCodeAssistant.hashObject(this.orderQty),
               HashCodeAssistant.hashObject(this.assocType));
} 

@Override
public String toString() {
   return "VwItemAssociations [assocId=" + assocId + 
          ", assocItemId=" + assocItemId + 
          ", itemId=" + itemId + 
          ", itemCost=" + itemCost + 
          ", orderQty=" + orderQty + 
          ", assocType=" + assocType  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}