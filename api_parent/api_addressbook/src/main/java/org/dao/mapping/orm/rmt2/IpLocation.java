package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the ip_location database table/view.
 *
 * @author auto generated.
 */
public class IpLocation extends OrmBean {




	// Property name constants that belong to respective DataSource, IpLocationView

/** The property name constant equivalent to property, IpFrom, of respective DataSource view. */
  public static final String PROP_IPFROM = "IpFrom";
/** The property name constant equivalent to property, IpTo, of respective DataSource view. */
  public static final String PROP_IPTO = "IpTo";
/** The property name constant equivalent to property, CountryCode, of respective DataSource view. */
  public static final String PROP_COUNTRYCODE = "CountryCode";
/** The property name constant equivalent to property, CountryName, of respective DataSource view. */
  public static final String PROP_COUNTRYNAME = "CountryName";
/** The property name constant equivalent to property, Region, of respective DataSource view. */
  public static final String PROP_REGION = "Region";
/** The property name constant equivalent to property, City, of respective DataSource view. */
  public static final String PROP_CITY = "City";
/** The property name constant equivalent to property, Latitude, of respective DataSource view. */
  public static final String PROP_LATITUDE = "Latitude";
/** The property name constant equivalent to property, Longitude, of respective DataSource view. */
  public static final String PROP_LONGITUDE = "Longitude";
/** The property name constant equivalent to property, Zipcode, of respective DataSource view. */
  public static final String PROP_ZIPCODE = "Zipcode";
/** The property name constant equivalent to property, Timezone, of respective DataSource view. */
  public static final String PROP_TIMEZONE = "Timezone";
/** The property name constant equivalent to property, IpId, of respective DataSource view. */
  public static final String PROP_IPID = "IpId";



	/** The javabean property equivalent of database column ip_location.ip_from */
  private double ipFrom;
/** The javabean property equivalent of database column ip_location.ip_to */
  private double ipTo;
/** The javabean property equivalent of database column ip_location.country_code */
  private String countryCode;
/** The javabean property equivalent of database column ip_location.country_name */
  private String countryName;
/** The javabean property equivalent of database column ip_location.region */
  private String region;
/** The javabean property equivalent of database column ip_location.city */
  private String city;
/** The javabean property equivalent of database column ip_location.latitude */
  private double latitude;
/** The javabean property equivalent of database column ip_location.longitude */
  private double longitude;
/** The javabean property equivalent of database column ip_location.zipcode */
  private String zipcode;
/** The javabean property equivalent of database column ip_location.timezone */
  private String timezone;
/** The javabean property equivalent of database column ip_location.ip_id */
  private int ipId;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public IpLocation() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable ipFrom
 */
  public void setIpFrom(double value) {
    this.ipFrom = value;
  }
/**
 * Gets the value of member variable ipFrom
 */
  public double getIpFrom() {
    return this.ipFrom;
  }
/**
 * Sets the value of member variable ipTo
 */
  public void setIpTo(double value) {
    this.ipTo = value;
  }
/**
 * Gets the value of member variable ipTo
 */
  public double getIpTo() {
    return this.ipTo;
  }
/**
 * Sets the value of member variable countryCode
 */
  public void setCountryCode(String value) {
    this.countryCode = value;
  }
/**
 * Gets the value of member variable countryCode
 */
  public String getCountryCode() {
    return this.countryCode;
  }
/**
 * Sets the value of member variable countryName
 */
  public void setCountryName(String value) {
    this.countryName = value;
  }
/**
 * Gets the value of member variable countryName
 */
  public String getCountryName() {
    return this.countryName;
  }
/**
 * Sets the value of member variable region
 */
  public void setRegion(String value) {
    this.region = value;
  }
/**
 * Gets the value of member variable region
 */
  public String getRegion() {
    return this.region;
  }
/**
 * Sets the value of member variable city
 */
  public void setCity(String value) {
    this.city = value;
  }
/**
 * Gets the value of member variable city
 */
  public String getCity() {
    return this.city;
  }
/**
 * Sets the value of member variable latitude
 */
  public void setLatitude(double value) {
    this.latitude = value;
  }
/**
 * Gets the value of member variable latitude
 */
  public double getLatitude() {
    return this.latitude;
  }
/**
 * Sets the value of member variable longitude
 */
  public void setLongitude(double value) {
    this.longitude = value;
  }
/**
 * Gets the value of member variable longitude
 */
  public double getLongitude() {
    return this.longitude;
  }
/**
 * Sets the value of member variable zipcode
 */
  public void setZipcode(String value) {
    this.zipcode = value;
  }
/**
 * Gets the value of member variable zipcode
 */
  public String getZipcode() {
    return this.zipcode;
  }
/**
 * Sets the value of member variable timezone
 */
  public void setTimezone(String value) {
    this.timezone = value;
  }
/**
 * Gets the value of member variable timezone
 */
  public String getTimezone() {
    return this.timezone;
  }
/**
 * Sets the value of member variable ipId
 */
  public void setIpId(int value) {
    this.ipId = value;
  }
/**
 * Gets the value of member variable ipId
 */
  public int getIpId() {
    return this.ipId;
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
   final IpLocation other = (IpLocation) obj; 
   if (EqualityAssistant.notEqual(this.ipFrom, other.ipFrom)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.ipTo, other.ipTo)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.countryCode, other.countryCode)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.countryName, other.countryName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.region, other.region)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.city, other.city)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.latitude, other.latitude)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.longitude, other.longitude)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.zipcode, other.zipcode)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.timezone, other.timezone)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.ipId, other.ipId)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.ipFrom),
               HashCodeAssistant.hashObject(this.ipTo),
               HashCodeAssistant.hashObject(this.countryCode),
               HashCodeAssistant.hashObject(this.countryName),
               HashCodeAssistant.hashObject(this.region),
               HashCodeAssistant.hashObject(this.city),
               HashCodeAssistant.hashObject(this.latitude),
               HashCodeAssistant.hashObject(this.longitude),
               HashCodeAssistant.hashObject(this.zipcode),
               HashCodeAssistant.hashObject(this.timezone),
               HashCodeAssistant.hashObject(this.ipId));
} 

@Override
public String toString() {
   return "IpLocation [ipFrom=" + ipFrom + 
          ", ipTo=" + ipTo + 
          ", countryCode=" + countryCode + 
          ", countryName=" + countryName + 
          ", region=" + region + 
          ", city=" + city + 
          ", latitude=" + latitude + 
          ", longitude=" + longitude + 
          ", zipcode=" + zipcode + 
          ", timezone=" + timezone + 
          ", ipId=" + ipId  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}