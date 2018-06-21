package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;

import com.api.persistence.db.orm.OrmBean;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.SystemException;


/**
 * Peer object that maps to the application_access database table/view.
 *
 * @author auto generated.
 */
public class ApplicationAccess extends OrmBean {




	// Property name constants that belong to respective DataSource, ApplicationAccessView

/** The property name constant equivalent to property, AppAccessId, of respective DataSource view. */
  public static final String PROP_APPACCESSID = "AppAccessId";
/** The property name constant equivalent to property, AppId, of respective DataSource view. */
  public static final String PROP_APPID = "AppId";
/** The property name constant equivalent to property, LoginId, of respective DataSource view. */
  public static final String PROP_LOGINID = "LoginId";
/** The property name constant equivalent to property, Loggedin, of respective DataSource view. */
  public static final String PROP_LOGGEDIN = "Loggedin";
/** The property name constant equivalent to property, LoginDate, of respective DataSource view. */
  public static final String PROP_LOGINDATE = "LoginDate";
/** The property name constant equivalent to property, LastLoginDate, of respective DataSource view. */
  public static final String PROP_LASTLOGINDATE = "LastLoginDate";
/** The property name constant equivalent to property, SessionId, of respective DataSource view. */
  public static final String PROP_SESSIONID = "SessionId";



	/** The javabean property equivalent of database column application_access.app_access_id */
  private int appAccessId;
/** The javabean property equivalent of database column application_access.app_id */
  private int appId;
/** The javabean property equivalent of database column application_access.login_id */
  private int loginId;
/** The javabean property equivalent of database column application_access.loggedin */
  private int loggedin;
/** The javabean property equivalent of database column application_access.login_date */
  private java.util.Date loginDate;
/** The javabean property equivalent of database column application_access.last_login_date */
  private java.util.Date lastLoginDate;
/** The javabean property equivalent of database column application_access.session_id */
  private String sessionId;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public ApplicationAccess() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable appAccessId
 */
  public void setAppAccessId(int value) {
    this.appAccessId = value;
  }
/**
 * Gets the value of member variable appAccessId
 */
  public int getAppAccessId() {
    return this.appAccessId;
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
 * Sets the value of member variable loggedin
 */
  public void setLoggedin(int value) {
    this.loggedin = value;
  }
/**
 * Gets the value of member variable loggedin
 */
  public int getLoggedin() {
    return this.loggedin;
  }
/**
 * Sets the value of member variable loginDate
 */
  public void setLoginDate(java.util.Date value) {
    this.loginDate = value;
  }
/**
 * Gets the value of member variable loginDate
 */
  public java.util.Date getLoginDate() {
    return this.loginDate;
  }
/**
 * Sets the value of member variable lastLoginDate
 */
  public void setLastLoginDate(java.util.Date value) {
    this.lastLoginDate = value;
  }
/**
 * Gets the value of member variable lastLoginDate
 */
  public java.util.Date getLastLoginDate() {
    return this.lastLoginDate;
  }
/**
 * Sets the value of member variable sessionId
 */
  public void setSessionId(String value) {
    this.sessionId = value;
  }
/**
 * Gets the value of member variable sessionId
 */
  public String getSessionId() {
    return this.sessionId;
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
   final ApplicationAccess other = (ApplicationAccess) obj; 
   if (EqualityAssistant.notEqual(this.appAccessId, other.appAccessId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.appId, other.appId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.loginId, other.loginId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.loggedin, other.loggedin)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.loginDate, other.loginDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.lastLoginDate, other.lastLoginDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.sessionId, other.sessionId)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.appAccessId),
               HashCodeAssistant.hashObject(this.appId),
               HashCodeAssistant.hashObject(this.loginId),
               HashCodeAssistant.hashObject(this.loggedin),
               HashCodeAssistant.hashObject(this.loginDate),
               HashCodeAssistant.hashObject(this.lastLoginDate),
               HashCodeAssistant.hashObject(this.sessionId));
} 

@Override
public String toString() {
   return "ApplicationAccess [appAccessId=" + appAccessId + 
          ", appId=" + appId + 
          ", loginId=" + loginId + 
          ", loggedin=" + loggedin + 
          ", loginDate=" + loginDate + 
          ", lastLoginDate=" + lastLoginDate + 
          ", sessionId=" + sessionId  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}