package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;

import com.api.persistence.db.orm.OrmBean;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.SystemException;


/**
 * Peer object that maps to the xact_type_item_activity database table/view.
 *
 * @author auto generated.
 */
public class XactTypeItemActivity extends OrmBean {




	// Property name constants that belong to respective DataSource, XactTypeItemActivityView

/** The property name constant equivalent to property, XactTypeItemActvId, of respective DataSource view. */
  public static final String PROP_XACTTYPEITEMACTVID = "XactTypeItemActvId";
/** The property name constant equivalent to property, XactId, of respective DataSource view. */
  public static final String PROP_XACTID = "XactId";
/** The property name constant equivalent to property, XactItemId, of respective DataSource view. */
  public static final String PROP_XACTITEMID = "XactItemId";
/** The property name constant equivalent to property, Amount, of respective DataSource view. */
  public static final String PROP_AMOUNT = "Amount";
/** The property name constant equivalent to property, Description, of respective DataSource view. */
  public static final String PROP_DESCRIPTION = "Description";
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



	/** The javabean property equivalent of database column xact_type_item_activity.xact_type_item_actv_id */
  private int xactTypeItemActvId;
/** The javabean property equivalent of database column xact_type_item_activity.xact_id */
  private int xactId;
/** The javabean property equivalent of database column xact_type_item_activity.xact_item_id */
  private int xactItemId;
/** The javabean property equivalent of database column xact_type_item_activity.amount */
  private double amount;
/** The javabean property equivalent of database column xact_type_item_activity.description */
  private String description;
/** The javabean property equivalent of database column xact_type_item_activity.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column xact_type_item_activity.date_updated */
  private java.util.Date dateUpdated;
/** The javabean property equivalent of database column xact_type_item_activity.user_id */
  private String userId;
/** The javabean property equivalent of database column xact_type_item_activity.ip_created */
  private String ipCreated;
/** The javabean property equivalent of database column xact_type_item_activity.ip_updated */
  private String ipUpdated;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public XactTypeItemActivity() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable xactTypeItemActvId
 */
  public void setXactTypeItemActvId(int value) {
    this.xactTypeItemActvId = value;
  }
/**
 * Gets the value of member variable xactTypeItemActvId
 */
  public int getXactTypeItemActvId() {
    return this.xactTypeItemActvId;
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
 * Sets the value of member variable xactItemId
 */
  public void setXactItemId(int value) {
    this.xactItemId = value;
  }
/**
 * Gets the value of member variable xactItemId
 */
  public int getXactItemId() {
    return this.xactItemId;
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
   final XactTypeItemActivity other = (XactTypeItemActivity) obj; 
   if (EqualityAssistant.notEqual(this.xactTypeItemActvId, other.xactTypeItemActvId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactId, other.xactId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactItemId, other.xactItemId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.amount, other.amount)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.description, other.description)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.xactTypeItemActvId),
               HashCodeAssistant.hashObject(this.xactId),
               HashCodeAssistant.hashObject(this.xactItemId),
               HashCodeAssistant.hashObject(this.amount),
               HashCodeAssistant.hashObject(this.description));
} 

@Override
public String toString() {
   return "XactTypeItemActivity [xactTypeItemActvId=" + xactTypeItemActvId + 
          ", xactId=" + xactId + 
          ", xactItemId=" + xactItemId + 
          ", amount=" + amount + 
          ", description=" + description  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}