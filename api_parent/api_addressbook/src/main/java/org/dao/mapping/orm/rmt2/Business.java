package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;

import com.api.persistence.db.orm.OrmBean;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.SystemException;


/**
 * Peer object that maps to the business database table/view.
 *
 * @author auto generated.
 */
public class Business extends OrmBean {




	// Property name constants that belong to respective DataSource, BusinessView

/** The property name constant equivalent to property, BusinessId, of respective DataSource view. */
  public static final String PROP_BUSINESSID = "BusinessId";
/** The property name constant equivalent to property, EntityTypeId, of respective DataSource view. */
  public static final String PROP_ENTITYTYPEID = "EntityTypeId";
/** The property name constant equivalent to property, ServTypeId, of respective DataSource view. */
  public static final String PROP_SERVTYPEID = "ServTypeId";
/** The property name constant equivalent to property, Longname, of respective DataSource view. */
  public static final String PROP_LONGNAME = "Longname";
/** The property name constant equivalent to property, Shortname, of respective DataSource view. */
  public static final String PROP_SHORTNAME = "Shortname";
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
/** The property name constant equivalent to property, TaxId, of respective DataSource view. */
  public static final String PROP_TAXID = "TaxId";
/** The property name constant equivalent to property, Website, of respective DataSource view. */
  public static final String PROP_WEBSITE = "Website";
/** The property name constant equivalent to property, DateCreated, of respective DataSource view. */
  public static final String PROP_DATECREATED = "DateCreated";
/** The property name constant equivalent to property, DateUpdated, of respective DataSource view. */
  public static final String PROP_DATEUPDATED = "DateUpdated";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";
/** The property name constant equivalent to property, CategoryId, of respective DataSource view. */
  public static final String PROP_CATEGORYID = "CategoryId";



	/** The javabean property equivalent of database column business.business_id */
  private int businessId;
/** The javabean property equivalent of database column business.entity_type_id */
  private int entityTypeId;
/** The javabean property equivalent of database column business.serv_type_id */
  private int servTypeId;
/** The javabean property equivalent of database column business.longname */
  private String longname;
/** The javabean property equivalent of database column business.shortname */
  private String shortname;
/** The javabean property equivalent of database column business.contact_firstname */
  private String contactFirstname;
/** The javabean property equivalent of database column business.contact_lastname */
  private String contactLastname;
/** The javabean property equivalent of database column business.contact_phone */
  private String contactPhone;
/** The javabean property equivalent of database column business.contact_ext */
  private String contactExt;
/** The javabean property equivalent of database column business.contact_email */
  private String contactEmail;
/** The javabean property equivalent of database column business.tax_id */
  private String taxId;
/** The javabean property equivalent of database column business.website */
  private String website;
/** The javabean property equivalent of database column business.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column business.date_updated */
  private java.util.Date dateUpdated;
/** The javabean property equivalent of database column business.user_id */
  private String userId;
/** The javabean property equivalent of database column business.category_id */
  private int categoryId;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public Business() throws SystemException {
	super();
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
 * Sets the value of member variable entityTypeId
 */
  public void setEntityTypeId(int value) {
    this.entityTypeId = value;
  }
/**
 * Gets the value of member variable entityTypeId
 */
  public int getEntityTypeId() {
    return this.entityTypeId;
  }
/**
 * Sets the value of member variable servTypeId
 */
  public void setServTypeId(int value) {
    this.servTypeId = value;
  }
/**
 * Gets the value of member variable servTypeId
 */
  public int getServTypeId() {
    return this.servTypeId;
  }
/**
 * Sets the value of member variable longname
 */
  public void setLongname(String value) {
    this.longname = value;
  }
/**
 * Gets the value of member variable longname
 */
  public String getLongname() {
    return this.longname;
  }
/**
 * Sets the value of member variable shortname
 */
  public void setShortname(String value) {
    this.shortname = value;
  }
/**
 * Gets the value of member variable shortname
 */
  public String getShortname() {
    return this.shortname;
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
/**
 * Sets the value of member variable taxId
 */
  public void setTaxId(String value) {
    this.taxId = value;
  }
/**
 * Gets the value of member variable taxId
 */
  public String getTaxId() {
    return this.taxId;
  }
/**
 * Sets the value of member variable website
 */
  public void setWebsite(String value) {
    this.website = value;
  }
/**
 * Gets the value of member variable website
 */
  public String getWebsite() {
    return this.website;
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
 * Sets the value of member variable categoryId
 */
  public void setCategoryId(int value) {
    this.categoryId = value;
  }
/**
 * Gets the value of member variable categoryId
 */
  public int getCategoryId() {
    return this.categoryId;
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
   final Business other = (Business) obj; 
   if (EqualityAssistant.notEqual(this.businessId, other.businessId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.entityTypeId, other.entityTypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.servTypeId, other.servTypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.longname, other.longname)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.shortname, other.shortname)) {
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
   if (EqualityAssistant.notEqual(this.taxId, other.taxId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.website, other.website)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.categoryId, other.categoryId)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.businessId),
               HashCodeAssistant.hashObject(this.entityTypeId),
               HashCodeAssistant.hashObject(this.servTypeId),
               HashCodeAssistant.hashObject(this.longname),
               HashCodeAssistant.hashObject(this.shortname),
               HashCodeAssistant.hashObject(this.contactFirstname),
               HashCodeAssistant.hashObject(this.contactLastname),
               HashCodeAssistant.hashObject(this.contactPhone),
               HashCodeAssistant.hashObject(this.contactExt),
               HashCodeAssistant.hashObject(this.contactEmail),
               HashCodeAssistant.hashObject(this.taxId),
               HashCodeAssistant.hashObject(this.website),
               HashCodeAssistant.hashObject(this.categoryId));
} 

@Override
public String toString() {
   return "Business [businessId=" + businessId + 
          ", entityTypeId=" + entityTypeId + 
          ", servTypeId=" + servTypeId + 
          ", longname=" + longname + 
          ", shortname=" + shortname + 
          ", contactFirstname=" + contactFirstname + 
          ", contactLastname=" + contactLastname + 
          ", contactPhone=" + contactPhone + 
          ", contactExt=" + contactExt + 
          ", contactEmail=" + contactEmail + 
          ", taxId=" + taxId + 
          ", website=" + website + 
          ", categoryId=" + categoryId  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}