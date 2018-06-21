package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;

import com.api.persistence.db.orm.OrmBean;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.SystemException;


/**
 * Peer object that maps to the person database table/view.
 *
 * @author auto generated.
 */
public class Person extends OrmBean {




	// Property name constants that belong to respective DataSource, PersonView

/** The property name constant equivalent to property, PersonId, of respective DataSource view. */
  public static final String PROP_PERSONID = "PersonId";
/** The property name constant equivalent to property, Firstname, of respective DataSource view. */
  public static final String PROP_FIRSTNAME = "Firstname";
/** The property name constant equivalent to property, Midname, of respective DataSource view. */
  public static final String PROP_MIDNAME = "Midname";
/** The property name constant equivalent to property, Lastname, of respective DataSource view. */
  public static final String PROP_LASTNAME = "Lastname";
/** The property name constant equivalent to property, Maidenname, of respective DataSource view. */
  public static final String PROP_MAIDENNAME = "Maidenname";
/** The property name constant equivalent to property, Generation, of respective DataSource view. */
  public static final String PROP_GENERATION = "Generation";
/** The property name constant equivalent to property, Shortname, of respective DataSource view. */
  public static final String PROP_SHORTNAME = "Shortname";
/** The property name constant equivalent to property, Title, of respective DataSource view. */
  public static final String PROP_TITLE = "Title";
/** The property name constant equivalent to property, GenderId, of respective DataSource view. */
  public static final String PROP_GENDERID = "GenderId";
/** The property name constant equivalent to property, MaritalStatusId, of respective DataSource view. */
  public static final String PROP_MARITALSTATUSID = "MaritalStatusId";
/** The property name constant equivalent to property, BirthDate, of respective DataSource view. */
  public static final String PROP_BIRTHDATE = "BirthDate";
/** The property name constant equivalent to property, RaceId, of respective DataSource view. */
  public static final String PROP_RACEID = "RaceId";
/** The property name constant equivalent to property, Ssn, of respective DataSource view. */
  public static final String PROP_SSN = "Ssn";
/** The property name constant equivalent to property, Email, of respective DataSource view. */
  public static final String PROP_EMAIL = "Email";
/** The property name constant equivalent to property, DateCreated, of respective DataSource view. */
  public static final String PROP_DATECREATED = "DateCreated";
/** The property name constant equivalent to property, DateUpdated, of respective DataSource view. */
  public static final String PROP_DATEUPDATED = "DateUpdated";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";
/** The property name constant equivalent to property, CategoryId, of respective DataSource view. */
  public static final String PROP_CATEGORYID = "CategoryId";



	/** The javabean property equivalent of database column person.person_id */
  private int personId;
/** The javabean property equivalent of database column person.firstname */
  private String firstname;
/** The javabean property equivalent of database column person.midname */
  private String midname;
/** The javabean property equivalent of database column person.lastname */
  private String lastname;
/** The javabean property equivalent of database column person.maidenname */
  private String maidenname;
/** The javabean property equivalent of database column person.generation */
  private String generation;
/** The javabean property equivalent of database column person.shortname */
  private String shortname;
/** The javabean property equivalent of database column person.title */
  private int title;
/** The javabean property equivalent of database column person.gender_id */
  private int genderId;
/** The javabean property equivalent of database column person.marital_status_id */
  private int maritalStatusId;
/** The javabean property equivalent of database column person.birth_date */
  private java.util.Date birthDate;
/** The javabean property equivalent of database column person.race_id */
  private int raceId;
/** The javabean property equivalent of database column person.ssn */
  private String ssn;
/** The javabean property equivalent of database column person.email */
  private String email;
/** The javabean property equivalent of database column person.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column person.date_updated */
  private java.util.Date dateUpdated;
/** The javabean property equivalent of database column person.user_id */
  private String userId;
/** The javabean property equivalent of database column person.category_id */
  private int categoryId;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public Person() throws SystemException {
	super();
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
 * Sets the value of member variable firstname
 */
  public void setFirstname(String value) {
    this.firstname = value;
  }
/**
 * Gets the value of member variable firstname
 */
  public String getFirstname() {
    return this.firstname;
  }
/**
 * Sets the value of member variable midname
 */
  public void setMidname(String value) {
    this.midname = value;
  }
/**
 * Gets the value of member variable midname
 */
  public String getMidname() {
    return this.midname;
  }
/**
 * Sets the value of member variable lastname
 */
  public void setLastname(String value) {
    this.lastname = value;
  }
/**
 * Gets the value of member variable lastname
 */
  public String getLastname() {
    return this.lastname;
  }
/**
 * Sets the value of member variable maidenname
 */
  public void setMaidenname(String value) {
    this.maidenname = value;
  }
/**
 * Gets the value of member variable maidenname
 */
  public String getMaidenname() {
    return this.maidenname;
  }
/**
 * Sets the value of member variable generation
 */
  public void setGeneration(String value) {
    this.generation = value;
  }
/**
 * Gets the value of member variable generation
 */
  public String getGeneration() {
    return this.generation;
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
 * Sets the value of member variable title
 */
  public void setTitle(int value) {
    this.title = value;
  }
/**
 * Gets the value of member variable title
 */
  public int getTitle() {
    return this.title;
  }
/**
 * Sets the value of member variable genderId
 */
  public void setGenderId(int value) {
    this.genderId = value;
  }
/**
 * Gets the value of member variable genderId
 */
  public int getGenderId() {
    return this.genderId;
  }
/**
 * Sets the value of member variable maritalStatusId
 */
  public void setMaritalStatusId(int value) {
    this.maritalStatusId = value;
  }
/**
 * Gets the value of member variable maritalStatusId
 */
  public int getMaritalStatusId() {
    return this.maritalStatusId;
  }
/**
 * Sets the value of member variable birthDate
 */
  public void setBirthDate(java.util.Date value) {
    this.birthDate = value;
  }
/**
 * Gets the value of member variable birthDate
 */
  public java.util.Date getBirthDate() {
    return this.birthDate;
  }
/**
 * Sets the value of member variable raceId
 */
  public void setRaceId(int value) {
    this.raceId = value;
  }
/**
 * Gets the value of member variable raceId
 */
  public int getRaceId() {
    return this.raceId;
  }
/**
 * Sets the value of member variable ssn
 */
  public void setSsn(String value) {
    this.ssn = value;
  }
/**
 * Gets the value of member variable ssn
 */
  public String getSsn() {
    return this.ssn;
  }
/**
 * Sets the value of member variable email
 */
  public void setEmail(String value) {
    this.email = value;
  }
/**
 * Gets the value of member variable email
 */
  public String getEmail() {
    return this.email;
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
   final Person other = (Person) obj; 
   if (EqualityAssistant.notEqual(this.personId, other.personId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.firstname, other.firstname)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.midname, other.midname)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.lastname, other.lastname)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.maidenname, other.maidenname)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.generation, other.generation)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.shortname, other.shortname)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.title, other.title)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.genderId, other.genderId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.maritalStatusId, other.maritalStatusId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.birthDate, other.birthDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.raceId, other.raceId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.ssn, other.ssn)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.email, other.email)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.categoryId, other.categoryId)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.personId),
               HashCodeAssistant.hashObject(this.firstname),
               HashCodeAssistant.hashObject(this.midname),
               HashCodeAssistant.hashObject(this.lastname),
               HashCodeAssistant.hashObject(this.maidenname),
               HashCodeAssistant.hashObject(this.generation),
               HashCodeAssistant.hashObject(this.shortname),
               HashCodeAssistant.hashObject(this.title),
               HashCodeAssistant.hashObject(this.genderId),
               HashCodeAssistant.hashObject(this.maritalStatusId),
               HashCodeAssistant.hashObject(this.birthDate),
               HashCodeAssistant.hashObject(this.raceId),
               HashCodeAssistant.hashObject(this.ssn),
               HashCodeAssistant.hashObject(this.email),
               HashCodeAssistant.hashObject(this.categoryId));
} 

@Override
public String toString() {
   return "Person [personId=" + personId + 
          ", firstname=" + firstname + 
          ", midname=" + midname + 
          ", lastname=" + lastname + 
          ", maidenname=" + maidenname + 
          ", generation=" + generation + 
          ", shortname=" + shortname + 
          ", title=" + title + 
          ", genderId=" + genderId + 
          ", maritalStatusId=" + maritalStatusId + 
          ", birthDate=" + birthDate + 
          ", raceId=" + raceId + 
          ", ssn=" + ssn + 
          ", email=" + email + 
          ", categoryId=" + categoryId  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}