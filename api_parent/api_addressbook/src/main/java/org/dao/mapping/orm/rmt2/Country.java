package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the country database table/view.
 *
 * @author auto generated.
 */
public class Country extends OrmBean {




	// Property name constants that belong to respective DataSource, CountryView

/** The property name constant equivalent to property, CountryId, of respective DataSource view. */
  public static final String PROP_COUNTRYID = "CountryId";
/** The property name constant equivalent to property, Name, of respective DataSource view. */
  public static final String PROP_NAME = "Name";
/** The property name constant equivalent to property, CntryVoidInd, of respective DataSource view. */
  public static final String PROP_CNTRYVOIDIND = "CntryVoidInd";
/** The property name constant equivalent to property, Code, of respective DataSource view. */
  public static final String PROP_CODE = "Code";



	/** The javabean property equivalent of database column country.country_id */
  private int countryId;
/** The javabean property equivalent of database column country.name */
  private String name;
/** The javabean property equivalent of database column country.cntry_void_ind */
  private String cntryVoidInd;
/** The javabean property equivalent of database column country.code */
  private String code;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public Country() throws SystemException {
	super();
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
 * Sets the value of member variable cntryVoidInd
 */
  public void setCntryVoidInd(String value) {
    this.cntryVoidInd = value;
  }
/**
 * Gets the value of member variable cntryVoidInd
 */
  public String getCntryVoidInd() {
    return this.cntryVoidInd;
  }
/**
 * Sets the value of member variable code
 */
  public void setCode(String value) {
    this.code = value;
  }
/**
 * Gets the value of member variable code
 */
  public String getCode() {
    return this.code;
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
   final Country other = (Country) obj; 
   if (EqualityAssistant.notEqual(this.countryId, other.countryId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.name, other.name)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.cntryVoidInd, other.cntryVoidInd)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.code, other.code)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.countryId),
               HashCodeAssistant.hashObject(this.name),
               HashCodeAssistant.hashObject(this.cntryVoidInd),
               HashCodeAssistant.hashObject(this.code));
} 

@Override
public String toString() {
   return "Country [countryId=" + countryId + 
          ", name=" + name + 
          ", cntryVoidInd=" + cntryVoidInd + 
          ", code=" + code  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}