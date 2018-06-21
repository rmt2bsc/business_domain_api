package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;

import com.api.persistence.db.orm.OrmBean;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.SystemException;


/**
 * Peer object that maps to the purchase_order database table/view.
 *
 * @author auto generated.
 */
public class PurchaseOrder extends OrmBean {




	// Property name constants that belong to respective DataSource, PurchaseOrderView

/** The property name constant equivalent to property, PoId, of respective DataSource view. */
  public static final String PROP_POID = "PoId";
/** The property name constant equivalent to property, XactId, of respective DataSource view. */
  public static final String PROP_XACTID = "XactId";
/** The property name constant equivalent to property, CreditorId, of respective DataSource view. */
  public static final String PROP_CREDITORID = "CreditorId";
/** The property name constant equivalent to property, RefNo, of respective DataSource view. */
  public static final String PROP_REFNO = "RefNo";
/** The property name constant equivalent to property, Total, of respective DataSource view. */
  public static final String PROP_TOTAL = "Total";
/** The property name constant equivalent to property, DateCreated, of respective DataSource view. */
  public static final String PROP_DATECREATED = "DateCreated";
/** The property name constant equivalent to property, DateUpdated, of respective DataSource view. */
  public static final String PROP_DATEUPDATED = "DateUpdated";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";



	/** The javabean property equivalent of database column purchase_order.po_id */
  private int poId;
/** The javabean property equivalent of database column purchase_order.xact_id */
  private int xactId;
/** The javabean property equivalent of database column purchase_order.creditor_id */
  private int creditorId;
/** The javabean property equivalent of database column purchase_order.ref_no */
  private String refNo;
/** The javabean property equivalent of database column purchase_order.total */
  private double total;
/** The javabean property equivalent of database column purchase_order.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column purchase_order.date_updated */
  private java.util.Date dateUpdated;
/** The javabean property equivalent of database column purchase_order.user_id */
  private String userId;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public PurchaseOrder() throws SystemException {
	super();
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
 * Sets the value of member variable refNo
 */
  public void setRefNo(String value) {
    this.refNo = value;
  }
/**
 * Gets the value of member variable refNo
 */
  public String getRefNo() {
    return this.refNo;
  }
/**
 * Sets the value of member variable total
 */
  public void setTotal(double value) {
    this.total = value;
  }
/**
 * Gets the value of member variable total
 */
  public double getTotal() {
    return this.total;
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
   final PurchaseOrder other = (PurchaseOrder) obj; 
   if (EqualityAssistant.notEqual(this.poId, other.poId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactId, other.xactId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.creditorId, other.creditorId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.refNo, other.refNo)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.total, other.total)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.poId),
               HashCodeAssistant.hashObject(this.xactId),
               HashCodeAssistant.hashObject(this.creditorId),
               HashCodeAssistant.hashObject(this.refNo),
               HashCodeAssistant.hashObject(this.total));
} 

@Override
public String toString() {
   return "PurchaseOrder [poId=" + poId + 
          ", xactId=" + xactId + 
          ", creditorId=" + creditorId + 
          ", refNo=" + refNo + 
          ", total=" + total  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}