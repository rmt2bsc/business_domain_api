package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the xact_code_group database table/view.
 *
 * @author auto generated.
 */
public class XactCodeGroup extends OrmBean {




	// Property name constants that belong to respective DataSource, XactCodeGroupView

/** The property name constant equivalent to property, XactCodeGrpId, of respective DataSource view. */
  public static final String PROP_XACTCODEGRPID = "XactCodeGrpId";
/** The property name constant equivalent to property, Description, of respective DataSource view. */
  public static final String PROP_DESCRIPTION = "Description";
/** The property name constant equivalent to property, DateCreated, of respective DataSource view. */
  public static final String PROP_DATECREATED = "DateCreated";
/** The property name constant equivalent to property, DateUpdated, of respective DataSource view. */
  public static final String PROP_DATEUPDATED = "DateUpdated";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";



	/** The javabean property equivalent of database column xact_code_group.xact_code_grp_id */
  private int xactCodeGrpId;
/** The javabean property equivalent of database column xact_code_group.description */
  private String description;
/** The javabean property equivalent of database column xact_code_group.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column xact_code_group.date_updated */
  private java.util.Date dateUpdated;
/** The javabean property equivalent of database column xact_code_group.user_id */
  private String userId;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public XactCodeGroup() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable xactCodeGrpId
 */
  public void setXactCodeGrpId(int value) {
    this.xactCodeGrpId = value;
  }
/**
 * Gets the value of member variable xactCodeGrpId
 */
  public int getXactCodeGrpId() {
    return this.xactCodeGrpId;
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
   final XactCodeGroup other = (XactCodeGroup) obj; 
   if (EqualityAssistant.notEqual(this.xactCodeGrpId, other.xactCodeGrpId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.description, other.description)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.xactCodeGrpId),
               HashCodeAssistant.hashObject(this.description));
} 

@Override
public String toString() {
   return "XactCodeGroup [xactCodeGrpId=" + xactCodeGrpId + 
          ", description=" + description  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}