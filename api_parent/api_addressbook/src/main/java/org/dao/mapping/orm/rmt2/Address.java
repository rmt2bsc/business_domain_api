package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the address database table/view.
 *
 * @author auto generated.
 */
public class Address extends OrmBean {




	// Property name constants that belong to respective DataSource, AddressView

/** The property name constant equivalent to property, AddrId, of respective DataSource view. */
  public static final String PROP_ADDRID = "AddrId";
/** The property name constant equivalent to property, PersonId, of respective DataSource view. */
  public static final String PROP_PERSONID = "PersonId";
/** The property name constant equivalent to property, BusinessId, of respective DataSource view. */
  public static final String PROP_BUSINESSID = "BusinessId";
/** The property name constant equivalent to property, Addr1, of respective DataSource view. */
  public static final String PROP_ADDR1 = "Addr1";
/** The property name constant equivalent to property, Addr2, of respective DataSource view. */
  public static final String PROP_ADDR2 = "Addr2";
/** The property name constant equivalent to property, Addr3, of respective DataSource view. */
  public static final String PROP_ADDR3 = "Addr3";
/** The property name constant equivalent to property, Addr4, of respective DataSource view. */
  public static final String PROP_ADDR4 = "Addr4";
/** The property name constant equivalent to property, Zip, of respective DataSource view. */
  public static final String PROP_ZIP = "Zip";
/** The property name constant equivalent to property, Zipext, of respective DataSource view. */
  public static final String PROP_ZIPEXT = "Zipext";
/** The property name constant equivalent to property, PhoneHome, of respective DataSource view. */
  public static final String PROP_PHONEHOME = "PhoneHome";
/** The property name constant equivalent to property, PhoneWork, of respective DataSource view. */
  public static final String PROP_PHONEWORK = "PhoneWork";
/** The property name constant equivalent to property, PhoneExt, of respective DataSource view. */
  public static final String PROP_PHONEEXT = "PhoneExt";
/** The property name constant equivalent to property, PhoneMain, of respective DataSource view. */
  public static final String PROP_PHONEMAIN = "PhoneMain";
/** The property name constant equivalent to property, PhoneCell, of respective DataSource view. */
  public static final String PROP_PHONECELL = "PhoneCell";
/** The property name constant equivalent to property, PhoneFax, of respective DataSource view. */
  public static final String PROP_PHONEFAX = "PhoneFax";
/** The property name constant equivalent to property, PhonePager, of respective DataSource view. */
  public static final String PROP_PHONEPAGER = "PhonePager";
/** The property name constant equivalent to property, DateCreated, of respective DataSource view. */
  public static final String PROP_DATECREATED = "DateCreated";
/** The property name constant equivalent to property, DateUpdated, of respective DataSource view. */
  public static final String PROP_DATEUPDATED = "DateUpdated";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";



	/** The javabean property equivalent of database column address.addr_id */
  private int addrId;
/** The javabean property equivalent of database column address.person_id */
  private int personId;
/** The javabean property equivalent of database column address.business_id */
  private int businessId;
/** The javabean property equivalent of database column address.addr1 */
  private String addr1;
/** The javabean property equivalent of database column address.addr2 */
  private String addr2;
/** The javabean property equivalent of database column address.addr3 */
  private String addr3;
/** The javabean property equivalent of database column address.addr4 */
  private String addr4;
/** The javabean property equivalent of database column address.zip */
  private int zip;
/** The javabean property equivalent of database column address.zipext */
  private int zipext;
/** The javabean property equivalent of database column address.phone_home */
  private String phoneHome;
/** The javabean property equivalent of database column address.phone_work */
  private String phoneWork;
/** The javabean property equivalent of database column address.phone_ext */
  private String phoneExt;
/** The javabean property equivalent of database column address.phone_main */
  private String phoneMain;
/** The javabean property equivalent of database column address.phone_cell */
  private String phoneCell;
/** The javabean property equivalent of database column address.phone_fax */
  private String phoneFax;
/** The javabean property equivalent of database column address.phone_pager */
  private String phonePager;
/** The javabean property equivalent of database column address.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column address.date_updated */
  private java.util.Date dateUpdated;
/** The javabean property equivalent of database column address.user_id */
  private String userId;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public Address() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable addrId
 */
  public void setAddrId(int value) {
    this.addrId = value;
  }
/**
 * Gets the value of member variable addrId
 */
  public int getAddrId() {
    return this.addrId;
  }
/**
 * Sets the value of member variable personId
 */
  public void setPersonId(int value) {
    this.personId = value;
  }
/**
 * Gets the value of member variable personId
 */
  public int getPersonId() {
    return this.personId;
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
 * Sets the value of member variable addr1
 */
  public void setAddr1(String value) {
    this.addr1 = value;
  }
/**
 * Gets the value of member variable addr1
 */
  public String getAddr1() {
    return this.addr1;
  }
/**
 * Sets the value of member variable addr2
 */
  public void setAddr2(String value) {
    this.addr2 = value;
  }
/**
 * Gets the value of member variable addr2
 */
  public String getAddr2() {
    return this.addr2;
  }
/**
 * Sets the value of member variable addr3
 */
  public void setAddr3(String value) {
    this.addr3 = value;
  }
/**
 * Gets the value of member variable addr3
 */
  public String getAddr3() {
    return this.addr3;
  }
/**
 * Sets the value of member variable addr4
 */
  public void setAddr4(String value) {
    this.addr4 = value;
  }
/**
 * Gets the value of member variable addr4
 */
  public String getAddr4() {
    return this.addr4;
  }
/**
 * Sets the value of member variable zip
 */
  public void setZip(int value) {
    this.zip = value;
  }
/**
 * Gets the value of member variable zip
 */
  public int getZip() {
    return this.zip;
  }
/**
 * Sets the value of member variable zipext
 */
  public void setZipext(int value) {
    this.zipext = value;
  }
/**
 * Gets the value of member variable zipext
 */
  public int getZipext() {
    return this.zipext;
  }
/**
 * Sets the value of member variable phoneHome
 */
  public void setPhoneHome(String value) {
    this.phoneHome = value;
  }
/**
 * Gets the value of member variable phoneHome
 */
  public String getPhoneHome() {
    return this.phoneHome;
  }
/**
 * Sets the value of member variable phoneWork
 */
  public void setPhoneWork(String value) {
    this.phoneWork = value;
  }
/**
 * Gets the value of member variable phoneWork
 */
  public String getPhoneWork() {
    return this.phoneWork;
  }
/**
 * Sets the value of member variable phoneExt
 */
  public void setPhoneExt(String value) {
    this.phoneExt = value;
  }
/**
 * Gets the value of member variable phoneExt
 */
  public String getPhoneExt() {
    return this.phoneExt;
  }
/**
 * Sets the value of member variable phoneMain
 */
  public void setPhoneMain(String value) {
    this.phoneMain = value;
  }
/**
 * Gets the value of member variable phoneMain
 */
  public String getPhoneMain() {
    return this.phoneMain;
  }
/**
 * Sets the value of member variable phoneCell
 */
  public void setPhoneCell(String value) {
    this.phoneCell = value;
  }
/**
 * Gets the value of member variable phoneCell
 */
  public String getPhoneCell() {
    return this.phoneCell;
  }
/**
 * Sets the value of member variable phoneFax
 */
  public void setPhoneFax(String value) {
    this.phoneFax = value;
  }
/**
 * Gets the value of member variable phoneFax
 */
  public String getPhoneFax() {
    return this.phoneFax;
  }
/**
 * Sets the value of member variable phonePager
 */
  public void setPhonePager(String value) {
    this.phonePager = value;
  }
/**
 * Gets the value of member variable phonePager
 */
  public String getPhonePager() {
    return this.phonePager;
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
   final Address other = (Address) obj; 
   if (EqualityAssistant.notEqual(this.addrId, other.addrId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.personId, other.personId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.businessId, other.businessId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.addr1, other.addr1)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.addr2, other.addr2)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.addr3, other.addr3)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.addr4, other.addr4)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.zip, other.zip)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.zipext, other.zipext)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.phoneHome, other.phoneHome)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.phoneWork, other.phoneWork)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.phoneExt, other.phoneExt)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.phoneMain, other.phoneMain)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.phoneCell, other.phoneCell)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.phoneFax, other.phoneFax)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.phonePager, other.phonePager)) {
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
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.addrId),
   HashCodeAssistant.hashObject(this.personId),
   HashCodeAssistant.hashObject(this.businessId),
   HashCodeAssistant.hashObject(this.addr1),
   HashCodeAssistant.hashObject(this.addr2),
   HashCodeAssistant.hashObject(this.addr3),
   HashCodeAssistant.hashObject(this.addr4),
   HashCodeAssistant.hashObject(this.zip),
   HashCodeAssistant.hashObject(this.zipext),
   HashCodeAssistant.hashObject(this.phoneHome),
   HashCodeAssistant.hashObject(this.phoneWork),
   HashCodeAssistant.hashObject(this.phoneExt),
   HashCodeAssistant.hashObject(this.phoneMain),
   HashCodeAssistant.hashObject(this.phoneCell),
   HashCodeAssistant.hashObject(this.phoneFax),
   HashCodeAssistant.hashObject(this.phonePager),
   HashCodeAssistant.hashObject(this.dateCreated),
   HashCodeAssistant.hashObject(this.dateUpdated),
   HashCodeAssistant.hashObject(this.userId));
} 

@Override
public String toString() {
   return "Address [addrId=" + addrId + 
          ", personId=" + personId + 
          ", businessId=" + businessId + 
          ", addr1=" + addr1 + 
          ", addr2=" + addr2 + 
          ", addr3=" + addr3 + 
          ", addr4=" + addr4 + 
          ", zip=" + zip + 
          ", zipext=" + zipext + 
          ", phoneHome=" + phoneHome + 
          ", phoneWork=" + phoneWork + 
          ", phoneExt=" + phoneExt + 
          ", phoneMain=" + phoneMain + 
          ", phoneCell=" + phoneCell + 
          ", phoneFax=" + phoneFax + 
          ", phonePager=" + phonePager + 
          ", dateCreated=" + dateCreated + 
          ", dateUpdated=" + dateUpdated + 
          ", userId=" + userId  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}