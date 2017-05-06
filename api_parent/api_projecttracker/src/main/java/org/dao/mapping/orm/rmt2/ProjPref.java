package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the proj_pref database table/view.
 *
 * @author auto generated.
 */
public class ProjPref extends OrmBean {




	// Property name constants that belong to respective DataSource, ProjPrefView

/** The property name constant equivalent to property, EndPeriodDay, of respective DataSource view. */
  public static final String PROP_ENDPERIODDAY = "EndPeriodDay";
/** The property name constant equivalent to property, EventSubmitFreq, of respective DataSource view. */
  public static final String PROP_EVENTSUBMITFREQ = "EventSubmitFreq";



	/** The javabean property equivalent of database column proj_pref.end_period_day */
  private String endPeriodDay;
/** The javabean property equivalent of database column proj_pref.event_submit_freq */
  private String eventSubmitFreq;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public ProjPref() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable endPeriodDay
 */
  public void setEndPeriodDay(String value) {
    this.endPeriodDay = value;
  }
/**
 * Gets the value of member variable endPeriodDay
 */
  public String getEndPeriodDay() {
    return this.endPeriodDay;
  }
/**
 * Sets the value of member variable eventSubmitFreq
 */
  public void setEventSubmitFreq(String value) {
    this.eventSubmitFreq = value;
  }
/**
 * Gets the value of member variable eventSubmitFreq
 */
  public String getEventSubmitFreq() {
    return this.eventSubmitFreq;
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
   final ProjPref other = (ProjPref) obj; 
   if (EqualityAssistant.notEqual(this.endPeriodDay, other.endPeriodDay)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.eventSubmitFreq, other.eventSubmitFreq)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.endPeriodDay),
   HashCodeAssistant.hashObject(this.eventSubmitFreq));
} 

@Override
public String toString() {
   return "ProjPref [endPeriodDay=" + endPeriodDay + 
          ", eventSubmitFreq=" + eventSubmitFreq  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}