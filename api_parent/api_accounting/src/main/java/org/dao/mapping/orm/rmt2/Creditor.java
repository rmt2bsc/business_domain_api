package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;

import com.api.persistence.db.orm.OrmBean;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.SystemException;


/**
 * Peer object that maps to the creditor database table/view.
 *
 * @author auto generated.
 */
public class Creditor extends OrmBean {




	// Property name constants that belong to respective DataSource, CreditorView

/** The property name constant equivalent to property, CreditorId, of respective DataSource view. */
  public static final String PROP_CREDITORID = "CreditorId";
/** The property name constant equivalent to property, AcctId, of respective DataSource view. */
  public static final String PROP_ACCTID = "AcctId";
/** The property name constant equivalent to property, CreditorTypeId, of respective DataSource view. */
  public static final String PROP_CREDITORTYPEID = "CreditorTypeId";
/** The property name constant equivalent to property, BusinessId, of respective DataSource view. */
  public static final String PROP_BUSINESSID = "BusinessId";
/** The property name constant equivalent to property, AccountNumber, of respective DataSource view. */
  public static final String PROP_ACCOUNTNUMBER = "AccountNumber";
/** The property name constant equivalent to property, CreditLimit, of respective DataSource view. */
  public static final String PROP_CREDITLIMIT = "CreditLimit";
/** The property name constant equivalent to property, Apr, of respective DataSource view. */
  public static final String PROP_APR = "Apr";
/** The property name constant equivalent to property, Active, of respective DataSource view. */
  public static final String PROP_ACTIVE = "Active";
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
/** The property name constant equivalent to property, ExtAccountNumber, of respective DataSource view. */
  public static final String PROP_EXTACCOUNTNUMBER = "ExtAccountNumber";



	/** The javabean property equivalent of database column creditor.creditor_id */
  private int creditorId;
/** The javabean property equivalent of database column creditor.acct_id */
  private int acctId;
/** The javabean property equivalent of database column creditor.creditor_type_id */
  private int creditorTypeId;
/** The javabean property equivalent of database column creditor.business_id */
  private int businessId;
/** The javabean property equivalent of database column creditor.account_number */
  private String accountNumber;
/** The javabean property equivalent of database column creditor.credit_limit */
  private double creditLimit;
/** The javabean property equivalent of database column creditor.apr */
  private double apr;
/** The javabean property equivalent of database column creditor.active */
  private int active;
/** The javabean property equivalent of database column creditor.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column creditor.date_updated */
  private java.util.Date dateUpdated;
/** The javabean property equivalent of database column creditor.user_id */
  private String userId;
/** The javabean property equivalent of database column creditor.ip_created */
  private String ipCreated;
/** The javabean property equivalent of database column creditor.ip_updated */
  private String ipUpdated;
/** The javabean property equivalent of database column creditor.ext_account_number */
  private String extAccountNumber;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public Creditor() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable creditorId
 */
  public void setCreditorId(int value) {
    this.creditorId = value;
  }
/**
 * Gets the value of member variable creditorId
 */
  public int getCreditorId() {
    return this.creditorId;
  }
/**
 * Sets the value of member variable acctId
 */
  public void setAcctId(int value) {
    this.acctId = value;
  }
/**
 * Gets the value of member variable acctId
 */
  public int getAcctId() {
    return this.acctId;
  }
/**
 * Sets the value of member variable creditorTypeId
 */
  public void setCreditorTypeId(int value) {
    this.creditorTypeId = value;
  }
/**
 * Gets the value of member variable creditorTypeId
 */
  public int getCreditorTypeId() {
    return this.creditorTypeId;
  }
/**
 * Sets the value of member variable businessId
 */
  public void setBusinessId(int value) {
    this.businessId = value;
  }
/**
 * Gets the value of member variable businessId
 */
  public int getBusinessId() {
    return this.businessId;
  }
/**
 * Sets the value of member variable accountNumber
 */
  public void setAccountNumber(String value) {
    this.accountNumber = value;
  }
/**
 * Gets the value of member variable accountNumber
 */
  public String getAccountNumber() {
    return this.accountNumber;
  }
/**
 * Sets the value of member variable creditLimit
 */
  public void setCreditLimit(double value) {
    this.creditLimit = value;
  }
/**
 * Gets the value of member variable creditLimit
 */
  public double getCreditLimit() {
    return this.creditLimit;
  }
/**
 * Sets the value of member variable apr
 */
  public void setApr(double value) {
    this.apr = value;
  }
/**
 * Gets the value of member variable apr
 */
  public double getApr() {
    return this.apr;
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
/**
 * Sets the value of member variable extAccountNumber
 */
  public void setExtAccountNumber(String value) {
    this.extAccountNumber = value;
  }
/**
 * Gets the value of member variable extAccountNumber
 */
  public String getExtAccountNumber() {
    return this.extAccountNumber;
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
   final Creditor other = (Creditor) obj; 
   if (EqualityAssistant.notEqual(this.creditorId, other.creditorId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.acctId, other.acctId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.creditorTypeId, other.creditorTypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.businessId, other.businessId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.accountNumber, other.accountNumber)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.creditLimit, other.creditLimit)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.apr, other.apr)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.active, other.active)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.extAccountNumber, other.extAccountNumber)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.creditorId),
               HashCodeAssistant.hashObject(this.acctId),
               HashCodeAssistant.hashObject(this.creditorTypeId),
               HashCodeAssistant.hashObject(this.businessId),
               HashCodeAssistant.hashObject(this.accountNumber),
               HashCodeAssistant.hashObject(this.creditLimit),
               HashCodeAssistant.hashObject(this.apr),
               HashCodeAssistant.hashObject(this.active),
               HashCodeAssistant.hashObject(this.extAccountNumber));
} 

@Override
public String toString() {
   return "Creditor [creditorId=" + creditorId + 
          ", acctId=" + acctId + 
          ", creditorTypeId=" + creditorTypeId + 
          ", businessId=" + businessId + 
          ", accountNumber=" + accountNumber + 
          ", creditLimit=" + creditLimit + 
          ", apr=" + apr + 
          ", active=" + active + 
          ", extAccountNumber=" + extAccountNumber  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}