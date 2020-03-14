package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;

import com.api.persistence.db.orm.OrmBean;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.SystemException;


/**
 * Peer object that maps to the proj_client database table/view.
 *
 * @author auto generated.
 */
public class ProjClient extends OrmBean {




	// Property name constants that belong to respective DataSource, ProjClientView

/** The property name constant equivalent to property, ClientId, of respective DataSource view. */
  public static final String PROP_CLIENTID = "ClientId";
/** The property name constant equivalent to property, BusinessId, of respective DataSource view. */
  public static final String PROP_BUSINESSID = "BusinessId";
/** The property name constant equivalent to property, Name, of respective DataSource view. */
  public static final String PROP_NAME = "Name";
/** The property name constant equivalent to property, BillRate, of respective DataSource view. */
  public static final String PROP_BILLRATE = "BillRate";
/** The property name constant equivalent to property, OtBillRate, of respective DataSource view. */
  public static final String PROP_OTBILLRATE = "OtBillRate";
/** The property name constant equivalent to property, DateCreated, of respective DataSource view. */
  public static final String PROP_DATECREATED = "DateCreated";
/** The property name constant equivalent to property, DateUpdated, of respective DataSource view. */
  public static final String PROP_DATEUPDATED = "DateUpdated";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";
/** The property name constant equivalent to property, AccountNo, of respective DataSource view. */
  public static final String PROP_ACCOUNTNO = "AccountNo";
/** The property name constant equivalent to property, ContactFirstname, of respective DataSource view. */
  public static final String PROP_CONTACTFIRSTNAME = "ContactFirstname";
/** The property name constant equivalent to property, ContactLastname, of respective DataSource view. */
  public static final String PROP_CONTACTLASTNAME = "ContactLastname";
/** The property name constant equivalent to property, ContactPhone, of respective DataSource view. */
  public static final String PROP_CONTACTPHONE = "ContactPhone";
/** The property name constant equivalent to property, ContactExt, of respective DataSource view. */
  public static final String PROP_CONTACTEXT = "ContactExt";
/** The property name constant equivalent to property, ContactEmail, of respective DataSource view. */
  public static final String PROP_CONTACTEMAIL = "ContactEmail";



	/** The javabean property equivalent of database column proj_client.client_id */
  private int clientId;
/** The javabean property equivalent of database column proj_client.business_id */
  private int businessId;
/** The javabean property equivalent of database column proj_client.name */
  private String name;
/** The javabean property equivalent of database column proj_client.bill_rate */
  private double billRate;
/** The javabean property equivalent of database column proj_client.ot_bill_rate */
  private double otBillRate;
/** The javabean property equivalent of database column proj_client.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column proj_client.date_updated */
  private java.util.Date dateUpdated;
/** The javabean property equivalent of database column proj_client.user_id */
  private String userId;
/** The javabean property equivalent of database column proj_client.account_no */
  private String accountNo;
/** The javabean property equivalent of database column proj_client.contact_firstname */
  private String contactFirstname;
/** The javabean property equivalent of database column proj_client.contact_lastname */
  private String contactLastname;
/** The javabean property equivalent of database column proj_client.contact_phone */
  private String contactPhone;
/** The javabean property equivalent of database column proj_client.contact_ext */
  private String contactExt;
/** The javabean property equivalent of database column proj_client.contact_email */
  private String contactEmail;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public ProjClient() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable clientId
 */
  public void setClientId(int value) {
    this.clientId = value;
  }
/**
 * Gets the value of member variable clientId
 */
  public int getClientId() {
    return this.clientId;
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
 * Sets the value of member variable billRate
 */
  public void setBillRate(double value) {
    this.billRate = value;
  }
/**
 * Gets the value of member variable billRate
 */
  public double getBillRate() {
    return this.billRate;
  }
/**
 * Sets the value of member variable otBillRate
 */
  public void setOtBillRate(double value) {
    this.otBillRate = value;
  }
/**
 * Gets the value of member variable otBillRate
 */
  public double getOtBillRate() {
    return this.otBillRate;
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
 * Sets the value of member variable accountNo
 */
  public void setAccountNo(String value) {
    this.accountNo = value;
  }
/**
 * Gets the value of member variable accountNo
 */
  public String getAccountNo() {
    return this.accountNo;
  }
/**
 * Sets the value of member variable contactFirstname
 */
  public void setContactFirstname(String value) {
    this.contactFirstname = value;
  }
/**
 * Gets the value of member variable contactFirstname
 */
  public String getContactFirstname() {
    return this.contactFirstname;
  }
/**
 * Sets the value of member variable contactLastname
 */
  public void setContactLastname(String value) {
    this.contactLastname = value;
  }
/**
 * Gets the value of member variable contactLastname
 */
  public String getContactLastname() {
    return this.contactLastname;
  }
/**
 * Sets the value of member variable contactPhone
 */
  public void setContactPhone(String value) {
    this.contactPhone = value;
  }
/**
 * Gets the value of member variable contactPhone
 */
  public String getContactPhone() {
    return this.contactPhone;
  }
/**
 * Sets the value of member variable contactExt
 */
  public void setContactExt(String value) {
    this.contactExt = value;
  }
/**
 * Gets the value of member variable contactExt
 */
  public String getContactExt() {
    return this.contactExt;
  }
/**
 * Sets the value of member variable contactEmail
 */
  public void setContactEmail(String value) {
    this.contactEmail = value;
  }
/**
 * Gets the value of member variable contactEmail
 */
  public String getContactEmail() {
    return this.contactEmail;
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
   final ProjClient other = (ProjClient) obj; 
   if (EqualityAssistant.notEqual(this.clientId, other.clientId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.businessId, other.businessId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.name, other.name)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.billRate, other.billRate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.otBillRate, other.otBillRate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.accountNo, other.accountNo)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.contactFirstname, other.contactFirstname)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.contactLastname, other.contactLastname)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.contactPhone, other.contactPhone)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.contactExt, other.contactExt)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.contactEmail, other.contactEmail)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.clientId),
               HashCodeAssistant.hashObject(this.businessId),
               HashCodeAssistant.hashObject(this.name),
               HashCodeAssistant.hashObject(this.billRate),
               HashCodeAssistant.hashObject(this.otBillRate),
               HashCodeAssistant.hashObject(this.accountNo),
               HashCodeAssistant.hashObject(this.contactFirstname),
               HashCodeAssistant.hashObject(this.contactLastname),
               HashCodeAssistant.hashObject(this.contactPhone),
               HashCodeAssistant.hashObject(this.contactExt),
               HashCodeAssistant.hashObject(this.contactEmail));
} 

@Override
public String toString() {
   return "ProjClient [clientId=" + clientId + 
          ", businessId=" + businessId + 
          ", name=" + name + 
          ", billRate=" + billRate + 
          ", otBillRate=" + otBillRate + 
          ", accountNo=" + accountNo + 
          ", contactFirstname=" + contactFirstname + 
          ", contactLastname=" + contactLastname + 
          ", contactPhone=" + contactPhone + 
          ", contactExt=" + contactExt + 
          ", contactEmail=" + contactEmail  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}