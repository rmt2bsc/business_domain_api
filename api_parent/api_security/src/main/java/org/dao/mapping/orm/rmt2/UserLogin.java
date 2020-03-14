package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;

import com.api.persistence.db.orm.OrmBean;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.SystemException;


/**
 * Peer object that maps to the user_login database table/view.
 *
 * @author auto generated.
 */
public class UserLogin extends OrmBean {




	// Property name constants that belong to respective DataSource, UserLoginView

/** The property name constant equivalent to property, LoginId, of respective DataSource view. */
  public static final String PROP_LOGINID = "LoginId";
/** The property name constant equivalent to property, GrpId, of respective DataSource view. */
  public static final String PROP_GRPID = "GrpId";
/** The property name constant equivalent to property, Username, of respective DataSource view. */
  public static final String PROP_USERNAME = "Username";
/** The property name constant equivalent to property, Firstname, of respective DataSource view. */
  public static final String PROP_FIRSTNAME = "Firstname";
/** The property name constant equivalent to property, Lastname, of respective DataSource view. */
  public static final String PROP_LASTNAME = "Lastname";
/** The property name constant equivalent to property, BirthDate, of respective DataSource view. */
  public static final String PROP_BIRTHDATE = "BirthDate";
/** The property name constant equivalent to property, Ssn, of respective DataSource view. */
  public static final String PROP_SSN = "Ssn";
/** The property name constant equivalent to property, StartDate, of respective DataSource view. */
  public static final String PROP_STARTDATE = "StartDate";
/** The property name constant equivalent to property, TerminationDate, of respective DataSource view. */
  public static final String PROP_TERMINATIONDATE = "TerminationDate";
/** The property name constant equivalent to property, Description, of respective DataSource view. */
  public static final String PROP_DESCRIPTION = "Description";
/** The property name constant equivalent to property, Password, of respective DataSource view. */
  public static final String PROP_PASSWORD = "Password";
/** The property name constant equivalent to property, TotalLogons, of respective DataSource view. */
  public static final String PROP_TOTALLOGONS = "TotalLogons";
/** The property name constant equivalent to property, Email, of respective DataSource view. */
  public static final String PROP_EMAIL = "Email";
/** The property name constant equivalent to property, Active, of respective DataSource view. */
  public static final String PROP_ACTIVE = "Active";
/** The property name constant equivalent to property, DateCreated, of respective DataSource view. */
  public static final String PROP_DATECREATED = "DateCreated";
/** The property name constant equivalent to property, DateUpdated, of respective DataSource view. */
  public static final String PROP_DATEUPDATED = "DateUpdated";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";
/** The property name constant equivalent to property, LoggedIn, of respective DataSource view. */
  public static final String PROP_LOGGEDIN = "LoggedIn";



	/** The javabean property equivalent of database column user_login.login_id */
  private int loginId;
/** The javabean property equivalent of database column user_login.grp_id */
  private int grpId;
/** The javabean property equivalent of database column user_login.username */
  private String username;
/** The javabean property equivalent of database column user_login.firstname */
  private String firstname;
/** The javabean property equivalent of database column user_login.lastname */
  private String lastname;
/** The javabean property equivalent of database column user_login.birth_date */
  private java.util.Date birthDate;
/** The javabean property equivalent of database column user_login.ssn */
  private String ssn;
/** The javabean property equivalent of database column user_login.start_date */
  private java.util.Date startDate;
/** The javabean property equivalent of database column user_login.termination_date */
  private java.util.Date terminationDate;
/** The javabean property equivalent of database column user_login.description */
  private String description;
/** The javabean property equivalent of database column user_login.password */
  private String password;
/** The javabean property equivalent of database column user_login.total_logons */
  private int totalLogons;
/** The javabean property equivalent of database column user_login.email */
  private String email;
/** The javabean property equivalent of database column user_login.active */
  private int active;
/** The javabean property equivalent of database column user_login.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column user_login.date_updated */
  private java.util.Date dateUpdated;
/** The javabean property equivalent of database column user_login.user_id */
  private String userId;
/** The javabean property equivalent of database column user_login.logged_in */
  private int loggedIn;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public UserLogin() throws SystemException {
	super();
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
 * Sets the value of member variable grpId
 */
  public void setGrpId(int value) {
    this.grpId = value;
  }
/**
 * Gets the value of member variable grpId
 */
  public int getGrpId() {
    return this.grpId;
  }
/**
 * Sets the value of member variable username
 */
  public void setUsername(String value) {
    this.username = value;
  }
/**
 * Gets the value of member variable username
 */
  public String getUsername() {
    return this.username;
  }
/**
 * Sets the value of member variable firstname
 */
  public void setFirstname(String value) {
    this.firstname = value;
  }
/**
 * Gets the value of member variable firstname
 */
  public String getFirstname() {
    return this.firstname;
  }
/**
 * Sets the value of member variable lastname
 */
  public void setLastname(String value) {
    this.lastname = value;
  }
/**
 * Gets the value of member variable lastname
 */
  public String getLastname() {
    return this.lastname;
  }
/**
 * Sets the value of member variable birthDate
 */
  public void setBirthDate(java.util.Date value) {
    this.birthDate = value;
  }
/**
 * Gets the value of member variable birthDate
 */
  public java.util.Date getBirthDate() {
    return this.birthDate;
  }
/**
 * Sets the value of member variable ssn
 */
  public void setSsn(String value) {
    this.ssn = value;
  }
/**
 * Gets the value of member variable ssn
 */
  public String getSsn() {
    return this.ssn;
  }
/**
 * Sets the value of member variable startDate
 */
  public void setStartDate(java.util.Date value) {
    this.startDate = value;
  }
/**
 * Gets the value of member variable startDate
 */
  public java.util.Date getStartDate() {
    return this.startDate;
  }
/**
 * Sets the value of member variable terminationDate
 */
  public void setTerminationDate(java.util.Date value) {
    this.terminationDate = value;
  }
/**
 * Gets the value of member variable terminationDate
 */
  public java.util.Date getTerminationDate() {
    return this.terminationDate;
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
 * Sets the value of member variable password
 */
  public void setPassword(String value) {
    this.password = value;
  }
/**
 * Gets the value of member variable password
 */
  public String getPassword() {
    return this.password;
  }
/**
 * Sets the value of member variable totalLogons
 */
  public void setTotalLogons(int value) {
    this.totalLogons = value;
  }
/**
 * Gets the value of member variable totalLogons
 */
  public int getTotalLogons() {
    return this.totalLogons;
  }
/**
 * Sets the value of member variable email
 */
  public void setEmail(String value) {
    this.email = value;
  }
/**
 * Gets the value of member variable email
 */
  public String getEmail() {
    return this.email;
  }
/**
 * Sets the value of member variable active
 */
  public void setActive(int value) {
    this.active = value;
  }
/**
 * Gets the value of member variable active
 */
  public int getActive() {
    return this.active;
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
 * Sets the value of member variable loggedIn
 */
  public void setLoggedIn(int value) {
    this.loggedIn = value;
  }
/**
 * Gets the value of member variable loggedIn
 */
  public int getLoggedIn() {
    return this.loggedIn;
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
   final UserLogin other = (UserLogin) obj; 
   if (EqualityAssistant.notEqual(this.loginId, other.loginId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.grpId, other.grpId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.username, other.username)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.firstname, other.firstname)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.lastname, other.lastname)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.birthDate, other.birthDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.ssn, other.ssn)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.startDate, other.startDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.terminationDate, other.terminationDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.description, other.description)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.password, other.password)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.totalLogons, other.totalLogons)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.email, other.email)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.active, other.active)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.loggedIn, other.loggedIn)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.loginId),
               HashCodeAssistant.hashObject(this.grpId),
               HashCodeAssistant.hashObject(this.username),
               HashCodeAssistant.hashObject(this.firstname),
               HashCodeAssistant.hashObject(this.lastname),
               HashCodeAssistant.hashObject(this.birthDate),
               HashCodeAssistant.hashObject(this.ssn),
               HashCodeAssistant.hashObject(this.startDate),
               HashCodeAssistant.hashObject(this.terminationDate),
               HashCodeAssistant.hashObject(this.description),
               HashCodeAssistant.hashObject(this.password),
               HashCodeAssistant.hashObject(this.totalLogons),
               HashCodeAssistant.hashObject(this.email),
               HashCodeAssistant.hashObject(this.active),
               HashCodeAssistant.hashObject(this.loggedIn));
} 

@Override
public String toString() {
   return "UserLogin [loginId=" + loginId + 
          ", grpId=" + grpId + 
          ", username=" + username + 
          ", firstname=" + firstname + 
          ", lastname=" + lastname + 
          ", birthDate=" + birthDate + 
          ", ssn=" + ssn + 
          ", startDate=" + startDate + 
          ", terminationDate=" + terminationDate + 
          ", description=" + description + 
          ", password=" + password + 
          ", totalLogons=" + totalLogons + 
          ", email=" + email + 
          ", active=" + active + 
          ", loggedIn=" + loggedIn  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}