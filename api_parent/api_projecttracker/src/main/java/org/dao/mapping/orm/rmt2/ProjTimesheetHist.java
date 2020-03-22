package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the proj_timesheet_hist database table/view.
 *
 * @author auto generated.
 */
public class ProjTimesheetHist extends OrmBean {




	// Property name constants that belong to respective DataSource, ProjTimesheetHistView

/** The property name constant equivalent to property, TimesheetHistId, of respective DataSource view. */
  public static final String PROP_TIMESHEETHISTID = "TimesheetHistId";
/** The property name constant equivalent to property, TimesheetId, of respective DataSource view. */
  public static final String PROP_TIMESHEETID = "TimesheetId";
/** The property name constant equivalent to property, TimesheetStatusId, of respective DataSource view. */
  public static final String PROP_TIMESHEETSTATUSID = "TimesheetStatusId";
/** The property name constant equivalent to property, EffectiveDate, of respective DataSource view. */
  public static final String PROP_EFFECTIVEDATE = "EffectiveDate";
/** The property name constant equivalent to property, EndDate, of respective DataSource view. */
  public static final String PROP_ENDDATE = "EndDate";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";
/** The property name constant equivalent to property, IpCreated, of respective DataSource view. */
  public static final String PROP_IPCREATED = "IpCreated";
/** The property name constant equivalent to property, IpUpdated, of respective DataSource view. */
  public static final String PROP_IPUPDATED = "IpUpdated";



	/** The javabean property equivalent of database column proj_timesheet_hist.timesheet_hist_id */
  private int timesheetHistId;
/** The javabean property equivalent of database column proj_timesheet_hist.timesheet_id */
  private int timesheetId;
/** The javabean property equivalent of database column proj_timesheet_hist.timesheet_status_id */
  private int timesheetStatusId;
/** The javabean property equivalent of database column proj_timesheet_hist.effective_date */
  private java.util.Date effectiveDate;
/** The javabean property equivalent of database column proj_timesheet_hist.end_date */
  private java.util.Date endDate;
/** The javabean property equivalent of database column proj_timesheet_hist.user_id */
  private String userId;
/** The javabean property equivalent of database column proj_timesheet_hist.ip_created */
  private String ipCreated;
/** The javabean property equivalent of database column proj_timesheet_hist.ip_updated */
  private String ipUpdated;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public ProjTimesheetHist() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable timesheetHistId
 */
  public void setTimesheetHistId(int value) {
    this.timesheetHistId = value;
  }
/**
 * Gets the value of member variable timesheetHistId
 */
  public int getTimesheetHistId() {
    return this.timesheetHistId;
  }
/**
 * Sets the value of member variable timesheetId
 */
  public void setTimesheetId(int value) {
    this.timesheetId = value;
  }
/**
 * Gets the value of member variable timesheetId
 */
  public int getTimesheetId() {
    return this.timesheetId;
  }
/**
 * Sets the value of member variable timesheetStatusId
 */
  public void setTimesheetStatusId(int value) {
    this.timesheetStatusId = value;
  }
/**
 * Gets the value of member variable timesheetStatusId
 */
  public int getTimesheetStatusId() {
    return this.timesheetStatusId;
  }
/**
 * Sets the value of member variable effectiveDate
 */
  public void setEffectiveDate(java.util.Date value) {
    this.effectiveDate = value;
  }
/**
 * Gets the value of member variable effectiveDate
 */
  public java.util.Date getEffectiveDate() {
    return this.effectiveDate;
  }
/**
 * Sets the value of member variable endDate
 */
  public void setEndDate(java.util.Date value) {
    this.endDate = value;
  }
/**
 * Gets the value of member variable endDate
 */
  public java.util.Date getEndDate() {
    return this.endDate;
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
 * Sets the value of member variable ipCreated
 */
  public void setIpCreated(String value) {
    this.ipCreated = value;
  }
/**
 * Gets the value of member variable ipCreated
 */
  public String getIpCreated() {
    return this.ipCreated;
  }
/**
 * Sets the value of member variable ipUpdated
 */
  public void setIpUpdated(String value) {
    this.ipUpdated = value;
  }
/**
 * Gets the value of member variable ipUpdated
 */
  public String getIpUpdated() {
    return this.ipUpdated;
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
   final ProjTimesheetHist other = (ProjTimesheetHist) obj; 
   if (EqualityAssistant.notEqual(this.timesheetHistId, other.timesheetHistId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.timesheetId, other.timesheetId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.timesheetStatusId, other.timesheetStatusId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.effectiveDate, other.effectiveDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.endDate, other.endDate)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.timesheetHistId),
               HashCodeAssistant.hashObject(this.timesheetId),
               HashCodeAssistant.hashObject(this.timesheetStatusId),
               HashCodeAssistant.hashObject(this.effectiveDate),
               HashCodeAssistant.hashObject(this.endDate));
} 

@Override
public String toString() {
   return "ProjTimesheetHist [timesheetHistId=" + timesheetHistId + 
          ", timesheetId=" + timesheetId + 
          ", timesheetStatusId=" + timesheetStatusId + 
          ", effectiveDate=" + effectiveDate + 
          ", endDate=" + endDate  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}