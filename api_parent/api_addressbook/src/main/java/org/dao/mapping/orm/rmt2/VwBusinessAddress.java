package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the vw_business_address database table/view.
 *
 * @author auto generated.
 */
public class VwBusinessAddress extends OrmBean {




	// Property name constants that belong to respective DataSource, VwBusinessAddressView

/** The property name constant equivalent to property, AddrId, of respective DataSource view. */
  public static final String PROP_ADDRID = "AddrId";
/** The property name constant equivalent to property, AddrPhoneCell, of respective DataSource view. */
  public static final String PROP_ADDRPHONECELL = "AddrPhoneCell";
/** The property name constant equivalent to property, AddrPhoneExt, of respective DataSource view. */
  public static final String PROP_ADDRPHONEEXT = "AddrPhoneExt";
/** The property name constant equivalent to property, AddrPhoneFax, of respective DataSource view. */
  public static final String PROP_ADDRPHONEFAX = "AddrPhoneFax";
/** The property name constant equivalent to property, AddrPhoneHome, of respective DataSource view. */
  public static final String PROP_ADDRPHONEHOME = "AddrPhoneHome";
/** The property name constant equivalent to property, AddrPhoneMain, of respective DataSource view. */
  public static final String PROP_ADDRPHONEMAIN = "AddrPhoneMain";
/** The property name constant equivalent to property, AddrPhonePager, of respective DataSource view. */
  public static final String PROP_ADDRPHONEPAGER = "AddrPhonePager";
/** The property name constant equivalent to property, AddrPhoneWork, of respective DataSource view. */
  public static final String PROP_ADDRPHONEWORK = "AddrPhoneWork";
/** The property name constant equivalent to property, AddrZip, of respective DataSource view. */
  public static final String PROP_ADDRZIP = "AddrZip";
/** The property name constant equivalent to property, AddrZipext, of respective DataSource view. */
  public static final String PROP_ADDRZIPEXT = "AddrZipext";
/** The property name constant equivalent to property, Addr1, of respective DataSource view. */
  public static final String PROP_ADDR1 = "Addr1";
/** The property name constant equivalent to property, Addr2, of respective DataSource view. */
  public static final String PROP_ADDR2 = "Addr2";
/** The property name constant equivalent to property, Addr3, of respective DataSource view. */
  public static final String PROP_ADDR3 = "Addr3";
/** The property name constant equivalent to property, Addr4, of respective DataSource view. */
  public static final String PROP_ADDR4 = "Addr4";
/** The property name constant equivalent to property, AddrPersonId, of respective DataSource view. */
  public static final String PROP_ADDRPERSONID = "AddrPersonId";
/** The property name constant equivalent to property, AddrBusinessId, of respective DataSource view. */
  public static final String PROP_ADDRBUSINESSID = "AddrBusinessId";
/** The property name constant equivalent to property, ZipCity, of respective DataSource view. */
  public static final String PROP_ZIPCITY = "ZipCity";
/** The property name constant equivalent to property, ZipState, of respective DataSource view. */
  public static final String PROP_ZIPSTATE = "ZipState";
/** The property name constant equivalent to property, BusContactExt, of respective DataSource view. */
  public static final String PROP_BUSCONTACTEXT = "BusContactExt";
/** The property name constant equivalent to property, BusContactFirstname, of respective DataSource view. */
  public static final String PROP_BUSCONTACTFIRSTNAME = "BusContactFirstname";
/** The property name constant equivalent to property, BusContactLastname, of respective DataSource view. */
  public static final String PROP_BUSCONTACTLASTNAME = "BusContactLastname";
/** The property name constant equivalent to property, BusContactPhone, of respective DataSource view. */
  public static final String PROP_BUSCONTACTPHONE = "BusContactPhone";
/** The property name constant equivalent to property, BusLongname, of respective DataSource view. */
  public static final String PROP_BUSLONGNAME = "BusLongname";
/** The property name constant equivalent to property, BusServTypeId, of respective DataSource view. */
  public static final String PROP_BUSSERVTYPEID = "BusServTypeId";
/** The property name constant equivalent to property, BusShortname, of respective DataSource view. */
  public static final String PROP_BUSSHORTNAME = "BusShortname";
/** The property name constant equivalent to property, ContactEmail, of respective DataSource view. */
  public static final String PROP_CONTACTEMAIL = "ContactEmail";
/** The property name constant equivalent to property, BusTaxId, of respective DataSource view. */
  public static final String PROP_BUSTAXID = "BusTaxId";
/** The property name constant equivalent to property, BusWebsite, of respective DataSource view. */
  public static final String PROP_BUSWEBSITE = "BusWebsite";
/** The property name constant equivalent to property, BusEntityTypeId, of respective DataSource view. */
  public static final String PROP_BUSENTITYTYPEID = "BusEntityTypeId";
/** The property name constant equivalent to property, BusinessId, of respective DataSource view. */
  public static final String PROP_BUSINESSID = "BusinessId";



	/** The javabean property equivalent of database column vw_business_address.addr_id */
  private int addrId;
/** The javabean property equivalent of database column vw_business_address.addr_phone_cell */
  private String addrPhoneCell;
/** The javabean property equivalent of database column vw_business_address.addr_phone_ext */
  private String addrPhoneExt;
/** The javabean property equivalent of database column vw_business_address.addr_phone_fax */
  private String addrPhoneFax;
/** The javabean property equivalent of database column vw_business_address.addr_phone_home */
  private String addrPhoneHome;
/** The javabean property equivalent of database column vw_business_address.addr_phone_main */
  private String addrPhoneMain;
/** The javabean property equivalent of database column vw_business_address.addr_phone_pager */
  private String addrPhonePager;
/** The javabean property equivalent of database column vw_business_address.addr_phone_work */
  private String addrPhoneWork;
/** The javabean property equivalent of database column vw_business_address.addr_zip */
  private int addrZip;
/** The javabean property equivalent of database column vw_business_address.addr_zipext */
  private int addrZipext;
/** The javabean property equivalent of database column vw_business_address.addr1 */
  private String addr1;
/** The javabean property equivalent of database column vw_business_address.addr2 */
  private String addr2;
/** The javabean property equivalent of database column vw_business_address.addr3 */
  private String addr3;
/** The javabean property equivalent of database column vw_business_address.addr4 */
  private String addr4;
/** The javabean property equivalent of database column vw_business_address.addr_person_id */
  private int addrPersonId;
/** The javabean property equivalent of database column vw_business_address.addr_business_id */
  private int addrBusinessId;
/** The javabean property equivalent of database column vw_business_address.zip_city */
  private String zipCity;
/** The javabean property equivalent of database column vw_business_address.zip_state */
  private String zipState;
/** The javabean property equivalent of database column vw_business_address.bus_contact_ext */
  private String busContactExt;
/** The javabean property equivalent of database column vw_business_address.bus_contact_firstname */
  private String busContactFirstname;
/** The javabean property equivalent of database column vw_business_address.bus_contact_lastname */
  private String busContactLastname;
/** The javabean property equivalent of database column vw_business_address.bus_contact_phone */
  private String busContactPhone;
/** The javabean property equivalent of database column vw_business_address.bus_longname */
  private String busLongname;
/** The javabean property equivalent of database column vw_business_address.bus_serv_type_id */
  private int busServTypeId;
/** The javabean property equivalent of database column vw_business_address.bus_shortname */
  private String busShortname;
/** The javabean property equivalent of database column vw_business_address.contact_email */
  private String contactEmail;
/** The javabean property equivalent of database column vw_business_address.bus_tax_id */
  private String busTaxId;
/** The javabean property equivalent of database column vw_business_address.bus_website */
  private String busWebsite;
/** The javabean property equivalent of database column vw_business_address.bus_entity_type_id */
  private int busEntityTypeId;
/** The javabean property equivalent of database column vw_business_address.business_id */
  private int businessId;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwBusinessAddress() throws SystemException {
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
 * Sets the value of member variable addrPhoneCell
 */
  public void setAddrPhoneCell(String value) {
    this.addrPhoneCell = value;
  }
/**
 * Gets the value of member variable addrPhoneCell
 */
  public String getAddrPhoneCell() {
    return this.addrPhoneCell;
  }
/**
 * Sets the value of member variable addrPhoneExt
 */
  public void setAddrPhoneExt(String value) {
    this.addrPhoneExt = value;
  }
/**
 * Gets the value of member variable addrPhoneExt
 */
  public String getAddrPhoneExt() {
    return this.addrPhoneExt;
  }
/**
 * Sets the value of member variable addrPhoneFax
 */
  public void setAddrPhoneFax(String value) {
    this.addrPhoneFax = value;
  }
/**
 * Gets the value of member variable addrPhoneFax
 */
  public String getAddrPhoneFax() {
    return this.addrPhoneFax;
  }
/**
 * Sets the value of member variable addrPhoneHome
 */
  public void setAddrPhoneHome(String value) {
    this.addrPhoneHome = value;
  }
/**
 * Gets the value of member variable addrPhoneHome
 */
  public String getAddrPhoneHome() {
    return this.addrPhoneHome;
  }
/**
 * Sets the value of member variable addrPhoneMain
 */
  public void setAddrPhoneMain(String value) {
    this.addrPhoneMain = value;
  }
/**
 * Gets the value of member variable addrPhoneMain
 */
  public String getAddrPhoneMain() {
    return this.addrPhoneMain;
  }
/**
 * Sets the value of member variable addrPhonePager
 */
  public void setAddrPhonePager(String value) {
    this.addrPhonePager = value;
  }
/**
 * Gets the value of member variable addrPhonePager
 */
  public String getAddrPhonePager() {
    return this.addrPhonePager;
  }
/**
 * Sets the value of member variable addrPhoneWork
 */
  public void setAddrPhoneWork(String value) {
    this.addrPhoneWork = value;
  }
/**
 * Gets the value of member variable addrPhoneWork
 */
  public String getAddrPhoneWork() {
    return this.addrPhoneWork;
  }
/**
 * Sets the value of member variable addrZip
 */
  public void setAddrZip(int value) {
    this.addrZip = value;
  }
/**
 * Gets the value of member variable addrZip
 */
  public int getAddrZip() {
    return this.addrZip;
  }
/**
 * Sets the value of member variable addrZipext
 */
  public void setAddrZipext(int value) {
    this.addrZipext = value;
  }
/**
 * Gets the value of member variable addrZipext
 */
  public int getAddrZipext() {
    return this.addrZipext;
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
 * Sets the value of member variable addrPersonId
 */
  public void setAddrPersonId(int value) {
    this.addrPersonId = value;
  }
/**
 * Gets the value of member variable addrPersonId
 */
  public int getAddrPersonId() {
    return this.addrPersonId;
  }
/**
 * Sets the value of member variable addrBusinessId
 */
  public void setAddrBusinessId(int value) {
    this.addrBusinessId = value;
  }
/**
 * Gets the value of member variable addrBusinessId
 */
  public int getAddrBusinessId() {
    return this.addrBusinessId;
  }
/**
 * Sets the value of member variable zipCity
 */
  public void setZipCity(String value) {
    this.zipCity = value;
  }
/**
 * Gets the value of member variable zipCity
 */
  public String getZipCity() {
    return this.zipCity;
  }
/**
 * Sets the value of member variable zipState
 */
  public void setZipState(String value) {
    this.zipState = value;
  }
/**
 * Gets the value of member variable zipState
 */
  public String getZipState() {
    return this.zipState;
  }
/**
 * Sets the value of member variable busContactExt
 */
  public void setBusContactExt(String value) {
    this.busContactExt = value;
  }
/**
 * Gets the value of member variable busContactExt
 */
  public String getBusContactExt() {
    return this.busContactExt;
  }
/**
 * Sets the value of member variable busContactFirstname
 */
  public void setBusContactFirstname(String value) {
    this.busContactFirstname = value;
  }
/**
 * Gets the value of member variable busContactFirstname
 */
  public String getBusContactFirstname() {
    return this.busContactFirstname;
  }
/**
 * Sets the value of member variable busContactLastname
 */
  public void setBusContactLastname(String value) {
    this.busContactLastname = value;
  }
/**
 * Gets the value of member variable busContactLastname
 */
  public String getBusContactLastname() {
    return this.busContactLastname;
  }
/**
 * Sets the value of member variable busContactPhone
 */
  public void setBusContactPhone(String value) {
    this.busContactPhone = value;
  }
/**
 * Gets the value of member variable busContactPhone
 */
  public String getBusContactPhone() {
    return this.busContactPhone;
  }
/**
 * Sets the value of member variable busLongname
 */
  public void setBusLongname(String value) {
    this.busLongname = value;
  }
/**
 * Gets the value of member variable busLongname
 */
  public String getBusLongname() {
    return this.busLongname;
  }
/**
 * Sets the value of member variable busServTypeId
 */
  public void setBusServTypeId(int value) {
    this.busServTypeId = value;
  }
/**
 * Gets the value of member variable busServTypeId
 */
  public int getBusServTypeId() {
    return this.busServTypeId;
  }
/**
 * Sets the value of member variable busShortname
 */
  public void setBusShortname(String value) {
    this.busShortname = value;
  }
/**
 * Gets the value of member variable busShortname
 */
  public String getBusShortname() {
    return this.busShortname;
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
 * Sets the value of member variable busTaxId
 */
  public void setBusTaxId(String value) {
    this.busTaxId = value;
  }
/**
 * Gets the value of member variable busTaxId
 */
  public String getBusTaxId() {
    return this.busTaxId;
  }
/**
 * Sets the value of member variable busWebsite
 */
  public void setBusWebsite(String value) {
    this.busWebsite = value;
  }
/**
 * Gets the value of member variable busWebsite
 */
  public String getBusWebsite() {
    return this.busWebsite;
  }
/**
 * Sets the value of member variable busEntityTypeId
 */
  public void setBusEntityTypeId(int value) {
    this.busEntityTypeId = value;
  }
/**
 * Gets the value of member variable busEntityTypeId
 */
  public int getBusEntityTypeId() {
    return this.busEntityTypeId;
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
   final VwBusinessAddress other = (VwBusinessAddress) obj; 
   if (EqualityAssistant.notEqual(this.addrId, other.addrId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.addrPhoneCell, other.addrPhoneCell)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.addrPhoneExt, other.addrPhoneExt)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.addrPhoneFax, other.addrPhoneFax)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.addrPhoneHome, other.addrPhoneHome)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.addrPhoneMain, other.addrPhoneMain)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.addrPhonePager, other.addrPhonePager)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.addrPhoneWork, other.addrPhoneWork)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.addrZip, other.addrZip)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.addrZipext, other.addrZipext)) {
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
   if (EqualityAssistant.notEqual(this.addrPersonId, other.addrPersonId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.addrBusinessId, other.addrBusinessId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.zipCity, other.zipCity)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.zipState, other.zipState)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.busContactExt, other.busContactExt)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.busContactFirstname, other.busContactFirstname)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.busContactLastname, other.busContactLastname)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.busContactPhone, other.busContactPhone)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.busLongname, other.busLongname)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.busServTypeId, other.busServTypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.busShortname, other.busShortname)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.contactEmail, other.contactEmail)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.busTaxId, other.busTaxId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.busWebsite, other.busWebsite)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.busEntityTypeId, other.busEntityTypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.businessId, other.businessId)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.addrId),
               HashCodeAssistant.hashObject(this.addrPhoneCell),
               HashCodeAssistant.hashObject(this.addrPhoneExt),
               HashCodeAssistant.hashObject(this.addrPhoneFax),
               HashCodeAssistant.hashObject(this.addrPhoneHome),
               HashCodeAssistant.hashObject(this.addrPhoneMain),
               HashCodeAssistant.hashObject(this.addrPhonePager),
               HashCodeAssistant.hashObject(this.addrPhoneWork),
               HashCodeAssistant.hashObject(this.addrZip),
               HashCodeAssistant.hashObject(this.addrZipext),
               HashCodeAssistant.hashObject(this.addr1),
               HashCodeAssistant.hashObject(this.addr2),
               HashCodeAssistant.hashObject(this.addr3),
               HashCodeAssistant.hashObject(this.addr4),
               HashCodeAssistant.hashObject(this.addrPersonId),
               HashCodeAssistant.hashObject(this.addrBusinessId),
               HashCodeAssistant.hashObject(this.zipCity),
               HashCodeAssistant.hashObject(this.zipState),
               HashCodeAssistant.hashObject(this.busContactExt),
               HashCodeAssistant.hashObject(this.busContactFirstname),
               HashCodeAssistant.hashObject(this.busContactLastname),
               HashCodeAssistant.hashObject(this.busContactPhone),
               HashCodeAssistant.hashObject(this.busLongname),
               HashCodeAssistant.hashObject(this.busServTypeId),
               HashCodeAssistant.hashObject(this.busShortname),
               HashCodeAssistant.hashObject(this.contactEmail),
               HashCodeAssistant.hashObject(this.busTaxId),
               HashCodeAssistant.hashObject(this.busWebsite),
               HashCodeAssistant.hashObject(this.busEntityTypeId),
               HashCodeAssistant.hashObject(this.businessId));
} 

@Override
public String toString() {
   return "VwBusinessAddress [addrId=" + addrId + 
          ", addrPhoneCell=" + addrPhoneCell + 
          ", addrPhoneExt=" + addrPhoneExt + 
          ", addrPhoneFax=" + addrPhoneFax + 
          ", addrPhoneHome=" + addrPhoneHome + 
          ", addrPhoneMain=" + addrPhoneMain + 
          ", addrPhonePager=" + addrPhonePager + 
          ", addrPhoneWork=" + addrPhoneWork + 
          ", addrZip=" + addrZip + 
          ", addrZipext=" + addrZipext + 
          ", addr1=" + addr1 + 
          ", addr2=" + addr2 + 
          ", addr3=" + addr3 + 
          ", addr4=" + addr4 + 
          ", addrPersonId=" + addrPersonId + 
          ", addrBusinessId=" + addrBusinessId + 
          ", zipCity=" + zipCity + 
          ", zipState=" + zipState + 
          ", busContactExt=" + busContactExt + 
          ", busContactFirstname=" + busContactFirstname + 
          ", busContactLastname=" + busContactLastname + 
          ", busContactPhone=" + busContactPhone + 
          ", busLongname=" + busLongname + 
          ", busServTypeId=" + busServTypeId + 
          ", busShortname=" + busShortname + 
          ", contactEmail=" + contactEmail + 
          ", busTaxId=" + busTaxId + 
          ", busWebsite=" + busWebsite + 
          ", busEntityTypeId=" + busEntityTypeId + 
          ", businessId=" + businessId  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}