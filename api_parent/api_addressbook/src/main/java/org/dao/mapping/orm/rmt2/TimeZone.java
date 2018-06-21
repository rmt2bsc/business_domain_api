package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;

import com.api.persistence.db.orm.OrmBean;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.SystemException;


/**
 * Peer object that maps to the time_zone database table/view.
 *
 * @author auto generated.
 */
public class TimeZone extends OrmBean {




	// Property name constants that belong to respective DataSource, TimeZoneView

/** The property name constant equivalent to property, TimeZoneId, of respective DataSource view. */
  public static final String PROP_TIMEZONEID = "TimeZoneId";
/** The property name constant equivalent to property, Descr, of respective DataSource view. */
  public static final String PROP_DESCR = "Descr";



	/** The javabean property equivalent of database column time_zone.time_zone_id */
  private int timeZoneId;
/** The javabean property equivalent of database column time_zone.descr */
  private String descr;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public TimeZone() throws SystemException {
	super();
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
 * Sets the value of member variable descr
 */
  public void setDescr(String value) {
    this.descr = value;
  }
/**
 * Gets the value of member variable descr
 */
  public String getDescr() {
    return this.descr;
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
   final TimeZone other = (TimeZone) obj; 
   if (EqualityAssistant.notEqual(this.timeZoneId, other.timeZoneId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.descr, other.descr)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.timeZoneId),
               HashCodeAssistant.hashObject(this.descr));
} 

@Override
public String toString() {
   return "TimeZone [timeZoneId=" + timeZoneId + 
          ", descr=" + descr  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}