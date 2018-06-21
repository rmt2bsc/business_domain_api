package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;

import com.api.persistence.db.orm.OrmBean;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.SystemException;


/**
 * Peer object that maps to the user_app_role database table/view.
 *
 * @author auto generated.
 */
public class UserAppRole extends OrmBean {




	// Property name constants that belong to respective DataSource, UserAppRoleView

/** The property name constant equivalent to property, UserAppRoleId, of respective DataSource view. */
  public static final String PROP_USERAPPROLEID = "UserAppRoleId";
/** The property name constant equivalent to property, AppRoleId, of respective DataSource view. */
  public static final String PROP_APPROLEID = "AppRoleId";
/** The property name constant equivalent to property, LoginId, of respective DataSource view. */
  public static final String PROP_LOGINID = "LoginId";
/** The property name constant equivalent to property, DateCreated, of respective DataSource view. */
  public static final String PROP_DATECREATED = "DateCreated";
/** The property name constant equivalent to property, DateUpdated, of respective DataSource view. */
  public static final String PROP_DATEUPDATED = "DateUpdated";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";



	/** The javabean property equivalent of database column user_app_role.user_app_role_id */
  private int userAppRoleId;
/** The javabean property equivalent of database column user_app_role.app_role_id */
  private int appRoleId;
/** The javabean property equivalent of database column user_app_role.login_id */
  private int loginId;
/** The javabean property equivalent of database column user_app_role.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column user_app_role.date_updated */
  private java.util.Date dateUpdated;
/** The javabean property equivalent of database column user_app_role.user_id */
  private String userId;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public UserAppRole() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable userAppRoleId
 */
  public void setUserAppRoleId(int value) {
    this.userAppRoleId = value;
  }
/**
 * Gets the value of member variable userAppRoleId
 */
  public int getUserAppRoleId() {
    return this.userAppRoleId;
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
 * Sets the value of member variable loginId
 */
  public void setLoginId(int value) {
    this.loginId = value;
  }
/**
 * Gets the value of member variable loginId
 */
  public int getLoginId() {
    return this.loginId;
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
   final UserAppRole other = (UserAppRole) obj; 
   if (EqualityAssistant.notEqual(this.userAppRoleId, other.userAppRoleId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.appRoleId, other.appRoleId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.loginId, other.loginId)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.userAppRoleId),
               HashCodeAssistant.hashObject(this.appRoleId),
               HashCodeAssistant.hashObject(this.loginId));
} 

@Override
public String toString() {
   return "UserAppRole [userAppRoleId=" + userAppRoleId + 
          ", appRoleId=" + appRoleId + 
          ", loginId=" + loginId  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}