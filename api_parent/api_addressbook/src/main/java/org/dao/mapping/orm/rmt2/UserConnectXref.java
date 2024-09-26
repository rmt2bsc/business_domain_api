package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the user_connect_xref database table/view.
 *
 * @author auto generated.
 */
public class UserConnectXref extends OrmBean {




	// Property name constants that belong to respective DataSource, UserConnectXrefView

/** The property name constant equivalent to property, Id, of respective DataSource view. */
  public static final String PROP_ID = "Id";
/** The property name constant equivalent to property, ConId, of respective DataSource view. */
  public static final String PROP_CONID = "ConId";
/** The property name constant equivalent to property, ConUserId, of respective DataSource view. */
  public static final String PROP_CONUSERID = "ConUserId";
/** The property name constant equivalent to property, AppUserId, of respective DataSource view. */
  public static final String PROP_APPUSERID = "AppUserId";



	/** The javabean property equivalent of database column user_connect_xref.id */
  private int id;
/** The javabean property equivalent of database column user_connect_xref.con_id */
  private String conId;
/** The javabean property equivalent of database column user_connect_xref.con_user_id */
  private String conUserId;
/** The javabean property equivalent of database column user_connect_xref.app_user_id */
  private String appUserId;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public UserConnectXref() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable id
 */
  public void setId(int value) {
    this.id = value;
  }
/**
 * Gets the value of member variable id
 */
  public int getId() {
    return this.id;
  }
/**
 * Sets the value of member variable conId
 */
  public void setConId(String value) {
    this.conId = value;
  }
/**
 * Gets the value of member variable conId
 */
  public String getConId() {
    return this.conId;
  }
/**
 * Sets the value of member variable conUserId
 */
  public void setConUserId(String value) {
    this.conUserId = value;
  }
/**
 * Gets the value of member variable conUserId
 */
  public String getConUserId() {
    return this.conUserId;
  }
/**
 * Sets the value of member variable appUserId
 */
  public void setAppUserId(String value) {
    this.appUserId = value;
  }
/**
 * Gets the value of member variable appUserId
 */
  public String getAppUserId() {
    return this.appUserId;
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
   final UserConnectXref other = (UserConnectXref) obj; 
   if (EqualityAssistant.notEqual(this.id, other.id)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.conId, other.conId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.conUserId, other.conUserId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.appUserId, other.appUserId)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.id),
               HashCodeAssistant.hashObject(this.conId),
               HashCodeAssistant.hashObject(this.conUserId),
               HashCodeAssistant.hashObject(this.appUserId));
} 

@Override
public String toString() {
   return "UserConnectXref [id=" + id + 
          ", conId=" + conId + 
          ", conUserId=" + conUserId + 
          ", appUserId=" + appUserId  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}