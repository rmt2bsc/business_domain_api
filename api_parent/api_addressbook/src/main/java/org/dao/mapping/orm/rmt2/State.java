package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the state database table/view.
 *
 * @author auto generated.
 */
public class State extends OrmBean {




	// Property name constants that belong to respective DataSource, StateView

/** The property name constant equivalent to property, StateId, of respective DataSource view. */
  public static final String PROP_STATEID = "StateId";
/** The property name constant equivalent to property, AbbrCode, of respective DataSource view. */
  public static final String PROP_ABBRCODE = "AbbrCode";
/** The property name constant equivalent to property, CountryId, of respective DataSource view. */
  public static final String PROP_COUNTRYID = "CountryId";
/** The property name constant equivalent to property, StateName, of respective DataSource view. */
  public static final String PROP_STATENAME = "StateName";
/** The property name constant equivalent to property, SttVoidInd, of respective DataSource view. */
  public static final String PROP_STTVOIDIND = "SttVoidInd";



	/** The javabean property equivalent of database column state.state_id */
  private int stateId;
/** The javabean property equivalent of database column state.abbr_code */
  private String abbrCode;
/** The javabean property equivalent of database column state.country_id */
  private int countryId;
/** The javabean property equivalent of database column state.state_name */
  private String stateName;
/** The javabean property equivalent of database column state.stt_void_ind */
  private String sttVoidInd;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public State() throws SystemException {
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
 * Sets the value of member variable abbrCode
 */
  public void setAbbrCode(String value) {
    this.abbrCode = value;
  }
/**
 * Gets the value of member variable abbrCode
 */
  public String getAbbrCode() {
    return this.abbrCode;
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
   final State other = (State) obj; 
   if (EqualityAssistant.notEqual(this.stateId, other.stateId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.abbrCode, other.abbrCode)) {
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
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.stateId),
               HashCodeAssistant.hashObject(this.abbrCode),
               HashCodeAssistant.hashObject(this.countryId),
               HashCodeAssistant.hashObject(this.stateName),
               HashCodeAssistant.hashObject(this.sttVoidInd));
} 

@Override
public String toString() {
   return "State [stateId=" + stateId + 
          ", abbrCode=" + abbrCode + 
          ", countryId=" + countryId + 
          ", stateName=" + stateName + 
          ", sttVoidInd=" + sttVoidInd  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}