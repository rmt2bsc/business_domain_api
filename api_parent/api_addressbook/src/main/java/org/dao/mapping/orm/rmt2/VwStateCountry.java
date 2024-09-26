package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the vw_state_country database table/view.
 *
 * @author auto generated.
 */
public class VwStateCountry extends OrmBean {




	// Property name constants that belong to respective DataSource, VwStateCountryView

/** The property name constant equivalent to property, StateId, of respective DataSource view. */
  public static final String PROP_STATEID = "StateId";
/** The property name constant equivalent to property, StateCode, of respective DataSource view. */
  public static final String PROP_STATECODE = "StateCode";
/** The property name constant equivalent to property, CountryId, of respective DataSource view. */
  public static final String PROP_COUNTRYID = "CountryId";
/** The property name constant equivalent to property, StateName, of respective DataSource view. */
  public static final String PROP_STATENAME = "StateName";
/** The property name constant equivalent to property, SttVoidInd, of respective DataSource view. */
  public static final String PROP_STTVOIDIND = "SttVoidInd";
/** The property name constant equivalent to property, CountryName, of respective DataSource view. */
  public static final String PROP_COUNTRYNAME = "CountryName";



	/** The javabean property equivalent of database column vw_state_country.state_id */
  private int stateId;
/** The javabean property equivalent of database column vw_state_country.state_code */
  private String stateCode;
/** The javabean property equivalent of database column vw_state_country.country_id */
  private int countryId;
/** The javabean property equivalent of database column vw_state_country.state_name */
  private String stateName;
/** The javabean property equivalent of database column vw_state_country.stt_void_ind */
  private String sttVoidInd;
/** The javabean property equivalent of database column vw_state_country.country_name */
  private String countryName;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwStateCountry() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable stateId
 */
  public void setStateId(int value) {
    this.stateId = value;
  }
/**
 * Gets the value of member variable stateId
 */
  public int getStateId() {
    return this.stateId;
  }
/**
 * Sets the value of member variable stateCode
 */
  public void setStateCode(String value) {
    this.stateCode = value;
  }
/**
 * Gets the value of member variable stateCode
 */
  public String getStateCode() {
    return this.stateCode;
  }
/**
 * Sets the value of member variable countryId
 */
  public void setCountryId(int value) {
    this.countryId = value;
  }
/**
 * Gets the value of member variable countryId
 */
  public int getCountryId() {
    return this.countryId;
  }
/**
 * Sets the value of member variable stateName
 */
  public void setStateName(String value) {
    this.stateName = value;
  }
/**
 * Gets the value of member variable stateName
 */
  public String getStateName() {
    return this.stateName;
  }
/**
 * Sets the value of member variable sttVoidInd
 */
  public void setSttVoidInd(String value) {
    this.sttVoidInd = value;
  }
/**
 * Gets the value of member variable sttVoidInd
 */
  public String getSttVoidInd() {
    return this.sttVoidInd;
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
   final VwStateCountry other = (VwStateCountry) obj; 
   if (EqualityAssistant.notEqual(this.stateId, other.stateId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.stateCode, other.stateCode)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.countryId, other.countryId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.stateName, other.stateName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.sttVoidInd, other.sttVoidInd)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.countryName, other.countryName)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.stateId),
               HashCodeAssistant.hashObject(this.stateCode),
               HashCodeAssistant.hashObject(this.countryId),
               HashCodeAssistant.hashObject(this.stateName),
               HashCodeAssistant.hashObject(this.sttVoidInd),
               HashCodeAssistant.hashObject(this.countryName));
} 

@Override
public String toString() {
   return "VwStateCountry [stateId=" + stateId + 
          ", stateCode=" + stateCode + 
          ", countryId=" + countryId + 
          ", stateName=" + stateName + 
          ", sttVoidInd=" + sttVoidInd + 
          ", countryName=" + countryName  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}