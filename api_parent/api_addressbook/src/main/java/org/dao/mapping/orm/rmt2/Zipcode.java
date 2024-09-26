package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the zipcode database table/view.
 *
 * @author auto generated.
 */
public class Zipcode extends OrmBean {




	// Property name constants that belong to respective DataSource, ZipcodeView

/** The property name constant equivalent to property, ZipId, of respective DataSource view. */
  public static final String PROP_ZIPID = "ZipId";
/** The property name constant equivalent to property, Zip, of respective DataSource view. */
  public static final String PROP_ZIP = "Zip";
/** The property name constant equivalent to property, City, of respective DataSource view. */
  public static final String PROP_CITY = "City";
/** The property name constant equivalent to property, State, of respective DataSource view. */
  public static final String PROP_STATE = "State";
/** The property name constant equivalent to property, AreaCode, of respective DataSource view. */
  public static final String PROP_AREACODE = "AreaCode";
/** The property name constant equivalent to property, CityAliasName, of respective DataSource view. */
  public static final String PROP_CITYALIASNAME = "CityAliasName";
/** The property name constant equivalent to property, CityAliasAbbr, of respective DataSource view. */
  public static final String PROP_CITYALIASABBR = "CityAliasAbbr";
/** The property name constant equivalent to property, CityTypeId, of respective DataSource view. */
  public static final String PROP_CITYTYPEID = "CityTypeId";
/** The property name constant equivalent to property, CountyName, of respective DataSource view. */
  public static final String PROP_COUNTYNAME = "CountyName";
/** The property name constant equivalent to property, CountyFips, of respective DataSource view. */
  public static final String PROP_COUNTYFIPS = "CountyFips";
/** The property name constant equivalent to property, TimeZoneId, of respective DataSource view. */
  public static final String PROP_TIMEZONEID = "TimeZoneId";
/** The property name constant equivalent to property, DayLightSaving, of respective DataSource view. */
  public static final String PROP_DAYLIGHTSAVING = "DayLightSaving";
/** The property name constant equivalent to property, Latitude, of respective DataSource view. */
  public static final String PROP_LATITUDE = "Latitude";
/** The property name constant equivalent to property, Longitude, of respective DataSource view. */
  public static final String PROP_LONGITUDE = "Longitude";
/** The property name constant equivalent to property, Elevation, of respective DataSource view. */
  public static final String PROP_ELEVATION = "Elevation";
/** The property name constant equivalent to property, Msa, of respective DataSource view. */
  public static final String PROP_MSA = "Msa";
/** The property name constant equivalent to property, Pmsa, of respective DataSource view. */
  public static final String PROP_PMSA = "Pmsa";
/** The property name constant equivalent to property, Cbsa, of respective DataSource view. */
  public static final String PROP_CBSA = "Cbsa";
/** The property name constant equivalent to property, CbsaDiv, of respective DataSource view. */
  public static final String PROP_CBSADIV = "CbsaDiv";
/** The property name constant equivalent to property, PersonsPerHousehold, of respective DataSource view. */
  public static final String PROP_PERSONSPERHOUSEHOLD = "PersonsPerHousehold";
/** The property name constant equivalent to property, ZipcodePopulation, of respective DataSource view. */
  public static final String PROP_ZIPCODEPOPULATION = "ZipcodePopulation";
/** The property name constant equivalent to property, CountiesArea, of respective DataSource view. */
  public static final String PROP_COUNTIESAREA = "CountiesArea";
/** The property name constant equivalent to property, HouseholdsPerZipcode, of respective DataSource view. */
  public static final String PROP_HOUSEHOLDSPERZIPCODE = "HouseholdsPerZipcode";
/** The property name constant equivalent to property, WhitePopulation, of respective DataSource view. */
  public static final String PROP_WHITEPOPULATION = "WhitePopulation";
/** The property name constant equivalent to property, BlackPopulation, of respective DataSource view. */
  public static final String PROP_BLACKPOPULATION = "BlackPopulation";
/** The property name constant equivalent to property, HispanicPopulation, of respective DataSource view. */
  public static final String PROP_HISPANICPOPULATION = "HispanicPopulation";
/** The property name constant equivalent to property, IncomePerHousehold, of respective DataSource view. */
  public static final String PROP_INCOMEPERHOUSEHOLD = "IncomePerHousehold";
/** The property name constant equivalent to property, AverageHouseValue, of respective DataSource view. */
  public static final String PROP_AVERAGEHOUSEVALUE = "AverageHouseValue";



	/** The javabean property equivalent of database column zipcode.zip_id */
  private int zipId;
/** The javabean property equivalent of database column zipcode.zip */
  private int zip;
/** The javabean property equivalent of database column zipcode.city */
  private String city;
/** The javabean property equivalent of database column zipcode.state */
  private String state;
/** The javabean property equivalent of database column zipcode.area_code */
  private String areaCode;
/** The javabean property equivalent of database column zipcode.city_alias_name */
  private String cityAliasName;
/** The javabean property equivalent of database column zipcode.city_alias_abbr */
  private String cityAliasAbbr;
/** The javabean property equivalent of database column zipcode.city_type_id */
  private String cityTypeId;
/** The javabean property equivalent of database column zipcode.county_name */
  private String countyName;
/** The javabean property equivalent of database column zipcode.county_fips */
  private String countyFips;
/** The javabean property equivalent of database column zipcode.time_zone_id */
  private int timeZoneId;
/** The javabean property equivalent of database column zipcode.day_light_saving */
  private String dayLightSaving;
/** The javabean property equivalent of database column zipcode.latitude */
  private double latitude;
/** The javabean property equivalent of database column zipcode.longitude */
  private double longitude;
/** The javabean property equivalent of database column zipcode.elevation */
  private double elevation;
/** The javabean property equivalent of database column zipcode.msa */
  private double msa;
/** The javabean property equivalent of database column zipcode.pmsa */
  private double pmsa;
/** The javabean property equivalent of database column zipcode.cbsa */
  private double cbsa;
/** The javabean property equivalent of database column zipcode.cbsa_div */
  private double cbsaDiv;
/** The javabean property equivalent of database column zipcode.persons_per_household */
  private double personsPerHousehold;
/** The javabean property equivalent of database column zipcode.zipcode_population */
  private double zipcodePopulation;
/** The javabean property equivalent of database column zipcode.counties_area */
  private double countiesArea;
/** The javabean property equivalent of database column zipcode.households_per_zipcode */
  private double householdsPerZipcode;
/** The javabean property equivalent of database column zipcode.white_population */
  private double whitePopulation;
/** The javabean property equivalent of database column zipcode.black_population */
  private double blackPopulation;
/** The javabean property equivalent of database column zipcode.hispanic_population */
  private double hispanicPopulation;
/** The javabean property equivalent of database column zipcode.income_per_household */
  private double incomePerHousehold;
/** The javabean property equivalent of database column zipcode.average_house_value */
  private double averageHouseValue;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public Zipcode() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable zipId
 */
  public void setZipId(int value) {
    this.zipId = value;
  }
/**
 * Gets the value of member variable zipId
 */
  public int getZipId() {
    return this.zipId;
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
 * Sets the value of member variable state
 */
  public void setState(String value) {
    this.state = value;
  }
/**
 * Gets the value of member variable state
 */
  public String getState() {
    return this.state;
  }
/**
 * Sets the value of member variable areaCode
 */
  public void setAreaCode(String value) {
    this.areaCode = value;
  }
/**
 * Gets the value of member variable areaCode
 */
  public String getAreaCode() {
    return this.areaCode;
  }
/**
 * Sets the value of member variable cityAliasName
 */
  public void setCityAliasName(String value) {
    this.cityAliasName = value;
  }
/**
 * Gets the value of member variable cityAliasName
 */
  public String getCityAliasName() {
    return this.cityAliasName;
  }
/**
 * Sets the value of member variable cityAliasAbbr
 */
  public void setCityAliasAbbr(String value) {
    this.cityAliasAbbr = value;
  }
/**
 * Gets the value of member variable cityAliasAbbr
 */
  public String getCityAliasAbbr() {
    return this.cityAliasAbbr;
  }
/**
 * Sets the value of member variable cityTypeId
 */
  public void setCityTypeId(String value) {
    this.cityTypeId = value;
  }
/**
 * Gets the value of member variable cityTypeId
 */
  public String getCityTypeId() {
    return this.cityTypeId;
  }
/**
 * Sets the value of member variable countyName
 */
  public void setCountyName(String value) {
    this.countyName = value;
  }
/**
 * Gets the value of member variable countyName
 */
  public String getCountyName() {
    return this.countyName;
  }
/**
 * Sets the value of member variable countyFips
 */
  public void setCountyFips(String value) {
    this.countyFips = value;
  }
/**
 * Gets the value of member variable countyFips
 */
  public String getCountyFips() {
    return this.countyFips;
  }
/**
 * Sets the value of member variable timeZoneId
 */
  public void setTimeZoneId(int value) {
    this.timeZoneId = value;
  }
/**
 * Gets the value of member variable timeZoneId
 */
  public int getTimeZoneId() {
    return this.timeZoneId;
  }
/**
 * Sets the value of member variable dayLightSaving
 */
  public void setDayLightSaving(String value) {
    this.dayLightSaving = value;
  }
/**
 * Gets the value of member variable dayLightSaving
 */
  public String getDayLightSaving() {
    return this.dayLightSaving;
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
 * Sets the value of member variable elevation
 */
  public void setElevation(double value) {
    this.elevation = value;
  }
/**
 * Gets the value of member variable elevation
 */
  public double getElevation() {
    return this.elevation;
  }
/**
 * Sets the value of member variable msa
 */
  public void setMsa(double value) {
    this.msa = value;
  }
/**
 * Gets the value of member variable msa
 */
  public double getMsa() {
    return this.msa;
  }
/**
 * Sets the value of member variable pmsa
 */
  public void setPmsa(double value) {
    this.pmsa = value;
  }
/**
 * Gets the value of member variable pmsa
 */
  public double getPmsa() {
    return this.pmsa;
  }
/**
 * Sets the value of member variable cbsa
 */
  public void setCbsa(double value) {
    this.cbsa = value;
  }
/**
 * Gets the value of member variable cbsa
 */
  public double getCbsa() {
    return this.cbsa;
  }
/**
 * Sets the value of member variable cbsaDiv
 */
  public void setCbsaDiv(double value) {
    this.cbsaDiv = value;
  }
/**
 * Gets the value of member variable cbsaDiv
 */
  public double getCbsaDiv() {
    return this.cbsaDiv;
  }
/**
 * Sets the value of member variable personsPerHousehold
 */
  public void setPersonsPerHousehold(double value) {
    this.personsPerHousehold = value;
  }
/**
 * Gets the value of member variable personsPerHousehold
 */
  public double getPersonsPerHousehold() {
    return this.personsPerHousehold;
  }
/**
 * Sets the value of member variable zipcodePopulation
 */
  public void setZipcodePopulation(double value) {
    this.zipcodePopulation = value;
  }
/**
 * Gets the value of member variable zipcodePopulation
 */
  public double getZipcodePopulation() {
    return this.zipcodePopulation;
  }
/**
 * Sets the value of member variable countiesArea
 */
  public void setCountiesArea(double value) {
    this.countiesArea = value;
  }
/**
 * Gets the value of member variable countiesArea
 */
  public double getCountiesArea() {
    return this.countiesArea;
  }
/**
 * Sets the value of member variable householdsPerZipcode
 */
  public void setHouseholdsPerZipcode(double value) {
    this.householdsPerZipcode = value;
  }
/**
 * Gets the value of member variable householdsPerZipcode
 */
  public double getHouseholdsPerZipcode() {
    return this.householdsPerZipcode;
  }
/**
 * Sets the value of member variable whitePopulation
 */
  public void setWhitePopulation(double value) {
    this.whitePopulation = value;
  }
/**
 * Gets the value of member variable whitePopulation
 */
  public double getWhitePopulation() {
    return this.whitePopulation;
  }
/**
 * Sets the value of member variable blackPopulation
 */
  public void setBlackPopulation(double value) {
    this.blackPopulation = value;
  }
/**
 * Gets the value of member variable blackPopulation
 */
  public double getBlackPopulation() {
    return this.blackPopulation;
  }
/**
 * Sets the value of member variable hispanicPopulation
 */
  public void setHispanicPopulation(double value) {
    this.hispanicPopulation = value;
  }
/**
 * Gets the value of member variable hispanicPopulation
 */
  public double getHispanicPopulation() {
    return this.hispanicPopulation;
  }
/**
 * Sets the value of member variable incomePerHousehold
 */
  public void setIncomePerHousehold(double value) {
    this.incomePerHousehold = value;
  }
/**
 * Gets the value of member variable incomePerHousehold
 */
  public double getIncomePerHousehold() {
    return this.incomePerHousehold;
  }
/**
 * Sets the value of member variable averageHouseValue
 */
  public void setAverageHouseValue(double value) {
    this.averageHouseValue = value;
  }
/**
 * Gets the value of member variable averageHouseValue
 */
  public double getAverageHouseValue() {
    return this.averageHouseValue;
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
   final Zipcode other = (Zipcode) obj; 
   if (EqualityAssistant.notEqual(this.zipId, other.zipId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.zip, other.zip)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.city, other.city)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.state, other.state)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.areaCode, other.areaCode)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.cityAliasName, other.cityAliasName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.cityAliasAbbr, other.cityAliasAbbr)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.cityTypeId, other.cityTypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.countyName, other.countyName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.countyFips, other.countyFips)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.timeZoneId, other.timeZoneId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.dayLightSaving, other.dayLightSaving)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.latitude, other.latitude)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.longitude, other.longitude)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.elevation, other.elevation)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.msa, other.msa)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.pmsa, other.pmsa)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.cbsa, other.cbsa)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.cbsaDiv, other.cbsaDiv)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.personsPerHousehold, other.personsPerHousehold)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.zipcodePopulation, other.zipcodePopulation)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.countiesArea, other.countiesArea)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.householdsPerZipcode, other.householdsPerZipcode)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.whitePopulation, other.whitePopulation)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.blackPopulation, other.blackPopulation)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.hispanicPopulation, other.hispanicPopulation)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.incomePerHousehold, other.incomePerHousehold)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.averageHouseValue, other.averageHouseValue)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.zipId),
               HashCodeAssistant.hashObject(this.zip),
               HashCodeAssistant.hashObject(this.city),
               HashCodeAssistant.hashObject(this.state),
               HashCodeAssistant.hashObject(this.areaCode),
               HashCodeAssistant.hashObject(this.cityAliasName),
               HashCodeAssistant.hashObject(this.cityAliasAbbr),
               HashCodeAssistant.hashObject(this.cityTypeId),
               HashCodeAssistant.hashObject(this.countyName),
               HashCodeAssistant.hashObject(this.countyFips),
               HashCodeAssistant.hashObject(this.timeZoneId),
               HashCodeAssistant.hashObject(this.dayLightSaving),
               HashCodeAssistant.hashObject(this.latitude),
               HashCodeAssistant.hashObject(this.longitude),
               HashCodeAssistant.hashObject(this.elevation),
               HashCodeAssistant.hashObject(this.msa),
               HashCodeAssistant.hashObject(this.pmsa),
               HashCodeAssistant.hashObject(this.cbsa),
               HashCodeAssistant.hashObject(this.cbsaDiv),
               HashCodeAssistant.hashObject(this.personsPerHousehold),
               HashCodeAssistant.hashObject(this.zipcodePopulation),
               HashCodeAssistant.hashObject(this.countiesArea),
               HashCodeAssistant.hashObject(this.householdsPerZipcode),
               HashCodeAssistant.hashObject(this.whitePopulation),
               HashCodeAssistant.hashObject(this.blackPopulation),
               HashCodeAssistant.hashObject(this.hispanicPopulation),
               HashCodeAssistant.hashObject(this.incomePerHousehold),
               HashCodeAssistant.hashObject(this.averageHouseValue));
} 

@Override
public String toString() {
   return "Zipcode [zipId=" + zipId + 
          ", zip=" + zip + 
          ", city=" + city + 
          ", state=" + state + 
          ", areaCode=" + areaCode + 
          ", cityAliasName=" + cityAliasName + 
          ", cityAliasAbbr=" + cityAliasAbbr + 
          ", cityTypeId=" + cityTypeId + 
          ", countyName=" + countyName + 
          ", countyFips=" + countyFips + 
          ", timeZoneId=" + timeZoneId + 
          ", dayLightSaving=" + dayLightSaving + 
          ", latitude=" + latitude + 
          ", longitude=" + longitude + 
          ", elevation=" + elevation + 
          ", msa=" + msa + 
          ", pmsa=" + pmsa + 
          ", cbsa=" + cbsa + 
          ", cbsaDiv=" + cbsaDiv + 
          ", personsPerHousehold=" + personsPerHousehold + 
          ", zipcodePopulation=" + zipcodePopulation + 
          ", countiesArea=" + countiesArea + 
          ", householdsPerZipcode=" + householdsPerZipcode + 
          ", whitePopulation=" + whitePopulation + 
          ", blackPopulation=" + blackPopulation + 
          ", hispanicPopulation=" + hispanicPopulation + 
          ", incomePerHousehold=" + incomePerHousehold + 
          ", averageHouseValue=" + averageHouseValue  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}