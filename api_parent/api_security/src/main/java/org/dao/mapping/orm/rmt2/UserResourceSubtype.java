package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the user_resource_subtype database table/view.
 *
 * @author auto generated.
 */
public class UserResourceSubtype extends OrmBean {




	// Property name constants that belong to respective DataSource, UserResourceSubtypeView

/** The property name constant equivalent to property, RsrcSubtypeId, of respective DataSource view. */
  public static final String PROP_RSRCSUBTYPEID = "RsrcSubtypeId";
/** The property name constant equivalent to property, RsrcTypeId, of respective DataSource view. */
  public static final String PROP_RSRCTYPEID = "RsrcTypeId";
/** The property name constant equivalent to property, Name, of respective DataSource view. */
  public static final String PROP_NAME = "Name";
/** The property name constant equivalent to property, Description, of respective DataSource view. */
  public static final String PROP_DESCRIPTION = "Description";
/** The property name constant equivalent to property, DateCreated, of respective DataSource view. */
  public static final String PROP_DATECREATED = "DateCreated";
/** The property name constant equivalent to property, DateUpdated, of respective DataSource view. */
  public static final String PROP_DATEUPDATED = "DateUpdated";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";



	/** The javabean property equivalent of database column user_resource_subtype.rsrc_subtype_id */
  private int rsrcSubtypeId;
/** The javabean property equivalent of database column user_resource_subtype.rsrc_type_id */
  private int rsrcTypeId;
/** The javabean property equivalent of database column user_resource_subtype.name */
  private String name;
/** The javabean property equivalent of database column user_resource_subtype.description */
  private String description;
/** The javabean property equivalent of database column user_resource_subtype.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column user_resource_subtype.date_updated */
  private java.util.Date dateUpdated;
/** The javabean property equivalent of database column user_resource_subtype.user_id */
  private String userId;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public UserResourceSubtype() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable rsrcSubtypeId
 */
  public void setRsrcSubtypeId(int value) {
    this.rsrcSubtypeId = value;
  }
/**
 * Gets the value of member variable rsrcSubtypeId
 */
  public int getRsrcSubtypeId() {
    return this.rsrcSubtypeId;
  }
/**
 * Sets the value of member variable rsrcTypeId
 */
  public void setRsrcTypeId(int value) {
    this.rsrcTypeId = value;
  }
/**
 * Gets the value of member variable rsrcTypeId
 */
  public int getRsrcTypeId() {
    return this.rsrcTypeId;
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
   final UserResourceSubtype other = (UserResourceSubtype) obj; 
   if (EqualityAssistant.notEqual(this.rsrcSubtypeId, other.rsrcSubtypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.rsrcTypeId, other.rsrcTypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.name, other.name)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.description, other.description)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.dateCreated, other.dateCreated)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.dateUpdated, other.dateUpdated)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.userId, other.userId)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.rsrcSubtypeId),
   HashCodeAssistant.hashObject(this.rsrcTypeId),
   HashCodeAssistant.hashObject(this.name),
   HashCodeAssistant.hashObject(this.description),
   HashCodeAssistant.hashObject(this.dateCreated),
   HashCodeAssistant.hashObject(this.dateUpdated),
   HashCodeAssistant.hashObject(this.userId));
} 

@Override
public String toString() {
   return "UserResourceSubtype [rsrcSubtypeId=" + rsrcSubtypeId + 
          ", rsrcTypeId=" + rsrcTypeId + 
          ", name=" + name + 
          ", description=" + description + 
          ", dateCreated=" + dateCreated + 
          ", dateUpdated=" + dateUpdated + 
          ", userId=" + userId  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}