package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;

import com.api.persistence.db.orm.OrmBean;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.SystemException;


/**
 * Peer object that maps to the customer_activity database table/view.
 *
 * @author auto generated.
 */
public class CustomerActivity extends OrmBean {




	// Property name constants that belong to respective DataSource, CustomerActivityView

/** The property name constant equivalent to property, CustomerActvId, of respective DataSource view. */
  public static final String PROP_CUSTOMERACTVID = "CustomerActvId";
/** The property name constant equivalent to property, CustomerId, of respective DataSource view. */
  public static final String PROP_CUSTOMERID = "CustomerId";
/** The property name constant equivalent to property, XactId, of respective DataSource view. */
  public static final String PROP_XACTID = "XactId";
/** The property name constant equivalent to property, Amount, of respective DataSource view. */
  public static final String PROP_AMOUNT = "Amount";
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



	/** The javabean property equivalent of database column customer_activity.customer_actv_id */
  private int customerActvId;
/** The javabean property equivalent of database column customer_activity.customer_id */
  private int customerId;
/** The javabean property equivalent of database column customer_activity.xact_id */
  private int xactId;
/** The javabean property equivalent of database column customer_activity.amount */
  private double amount;
/** The javabean property equivalent of database column customer_activity.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column customer_activity.date_updated */
  private java.util.Date dateUpdated;
/** The javabean property equivalent of database column customer_activity.user_id */
  private String userId;
/** The javabean property equivalent of database column customer_activity.ip_created */
  private String ipCreated;
/** The javabean property equivalent of database column customer_activity.ip_updated */
  private String ipUpdated;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public CustomerActivity() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable customerActvId
 */
  public void setCustomerActvId(int value) {
    this.customerActvId = value;
  }
/**
 * Gets the value of member variable customerActvId
 */
  public int getCustomerActvId() {
    return this.customerActvId;
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
   final CustomerActivity other = (CustomerActivity) obj; 
   if (EqualityAssistant.notEqual(this.customerActvId, other.customerActvId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.customerId, other.customerId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactId, other.xactId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.amount, other.amount)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.customerActvId),
               HashCodeAssistant.hashObject(this.customerId),
               HashCodeAssistant.hashObject(this.xactId),
               HashCodeAssistant.hashObject(this.amount));
} 

@Override
public String toString() {
   return "CustomerActivity [customerActvId=" + customerActvId + 
          ", customerId=" + customerId + 
          ", xactId=" + xactId + 
          ", amount=" + amount  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}