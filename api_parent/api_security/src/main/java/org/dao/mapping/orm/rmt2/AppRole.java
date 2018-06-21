package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;

import com.api.persistence.db.orm.OrmBean;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.SystemException;


/**
 * Peer object that maps to the app_role database table/view.
 *
 * @author auto generated.
 */
public class AppRole extends OrmBean {




	// Property name constants that belong to respective DataSource, AppRoleView

/** The property name constant equivalent to property, AppRoleId, of respective DataSource view. */
  public static final String PROP_APPROLEID = "AppRoleId";
/** The property name constant equivalent to property, AppId, of respective DataSource view. */
  public static final String PROP_APPID = "AppId";
/** The property name constant equivalent to property, RoleId, of respective DataSource view. */
  public static final String PROP_ROLEID = "RoleId";
/** The property name constant equivalent to property, Code, of respective DataSource view. */
  public static final String PROP_CODE = "Code";
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



	/** The javabean property equivalent of database column app_role.app_role_id */
  private int appRoleId;
/** The javabean property equivalent of database column app_role.app_id */
  private int appId;
/** The javabean property equivalent of database column app_role.role_id */
  private int roleId;
/** The javabean property equivalent of database column app_role.code */
  private String code;
/** The javabean property equivalent of database column app_role.name */
  private String name;
/** The javabean property equivalent of database column app_role.description */
  private String description;
/** The javabean property equivalent of database column app_role.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column app_role.date_updated */
  private java.util.Date dateUpdated;
/** The javabean property equivalent of database column app_role.user_id */
  private String userId;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public AppRole() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable appRoleId
 */
  public void setAppRoleId(int value) {
    this.appRoleId = value;
  }
/**
 * Gets the value of member variable appRoleId
 */
  public int getAppRoleId() {
    return this.appRoleId;
  }
/**
 * Sets the value of member variable appId
 */
  public void setAppId(int value) {
    this.appId = value;
  }
/**
 * Gets the value of member variable appId
 */
  public int getAppId() {
    return this.appId;
  }
/**
 * Sets the value of member variable roleId
 */
  public void setRoleId(int value) {
    this.roleId = value;
  }
/**
 * Gets the value of member variable roleId
 */
  public int getRoleId() {
    return this.roleId;
  }
/**
 * Sets the value of member variable code
 */
  public void setCode(String value) {
    this.code = value;
  }
/**
 * Gets the value of member variable code
 */
  public String getCode() {
    return this.code;
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
   final AppRole other = (AppRole) obj; 
   if (EqualityAssistant.notEqual(this.appRoleId, other.appRoleId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.appId, other.appId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.roleId, other.roleId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.code, other.code)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.name, other.name)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.description, other.description)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.appRoleId),
               HashCodeAssistant.hashObject(this.appId),
               HashCodeAssistant.hashObject(this.roleId),
               HashCodeAssistant.hashObject(this.code),
               HashCodeAssistant.hashObject(this.name),
               HashCodeAssistant.hashObject(this.description));
} 

@Override
public String toString() {
   return "AppRole [appRoleId=" + appRoleId + 
          ", appId=" + appId + 
          ", roleId=" + roleId + 
          ", code=" + code + 
          ", name=" + name + 
          ", description=" + description  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}