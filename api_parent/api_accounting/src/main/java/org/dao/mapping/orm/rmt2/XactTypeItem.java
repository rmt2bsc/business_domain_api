package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the xact_type_item database table/view.
 *
 * @author auto generated.
 */
public class XactTypeItem extends OrmBean {




	// Property name constants that belong to respective DataSource, XactTypeItemView

/** The property name constant equivalent to property, XactItemId, of respective DataSource view. */
  public static final String PROP_XACTITEMID = "XactItemId";
/** The property name constant equivalent to property, XactTypeId, of respective DataSource view. */
  public static final String PROP_XACTTYPEID = "XactTypeId";
/** The property name constant equivalent to property, Name, of respective DataSource view. */
  public static final String PROP_NAME = "Name";
/** The property name constant equivalent to property, DateCreated, of respective DataSource view. */
  public static final String PROP_DATECREATED = "DateCreated";
/** The property name constant equivalent to property, DateUpdated, of respective DataSource view. */
  public static final String PROP_DATEUPDATED = "DateUpdated";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";



	/** The javabean property equivalent of database column xact_type_item.xact_item_id */
  private int xactItemId;
/** The javabean property equivalent of database column xact_type_item.xact_type_id */
  private int xactTypeId;
/** The javabean property equivalent of database column xact_type_item.name */
  private String name;
/** The javabean property equivalent of database column xact_type_item.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column xact_type_item.date_updated */
  private java.util.Date dateUpdated;
/** The javabean property equivalent of database column xact_type_item.user_id */
  private String userId;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public XactTypeItem() throws SystemException {
	super();
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
   final XactTypeItem other = (XactTypeItem) obj; 
   if (EqualityAssistant.notEqual(this.xactItemId, other.xactItemId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactTypeId, other.xactTypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.name, other.name)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.xactItemId),
               HashCodeAssistant.hashObject(this.xactTypeId),
               HashCodeAssistant.hashObject(this.name));
} 

@Override
public String toString() {
   return "XactTypeItem [xactItemId=" + xactItemId + 
          ", xactTypeId=" + xactTypeId + 
          ", name=" + name  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}