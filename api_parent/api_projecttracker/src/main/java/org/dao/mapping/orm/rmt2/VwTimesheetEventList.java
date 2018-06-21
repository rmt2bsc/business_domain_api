package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;

import com.api.persistence.db.orm.OrmBean;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.SystemException;


/**
 * Peer object that maps to the vw_timesheet_event_list database table/view.
 *
 * @author auto generated.
 */
public class VwTimesheetEventList extends OrmBean {




	// Property name constants that belong to respective DataSource, VwTimesheetEventListView

/** The property name constant equivalent to property, EventId, of respective DataSource view. */
  public static final String PROP_EVENTID = "EventId";
/** The property name constant equivalent to property, EventDate, of respective DataSource view. */
  public static final String PROP_EVENTDATE = "EventDate";
/** The property name constant equivalent to property, Hours, of respective DataSource view. */
  public static final String PROP_HOURS = "Hours";
/** The property name constant equivalent to property, EventDateCreated, of respective DataSource view. */
  public static final String PROP_EVENTDATECREATED = "EventDateCreated";
/** The property name constant equivalent to property, ProjectTaskId, of respective DataSource view. */
  public static final String PROP_PROJECTTASKID = "ProjectTaskId";
/** The property name constant equivalent to property, TimesheetId, of respective DataSource view. */
  public static final String PROP_TIMESHEETID = "TimesheetId";
/** The property name constant equivalent to property, ProjectId, of respective DataSource view. */
  public static final String PROP_PROJECTID = "ProjectId";
/** The property name constant equivalent to property, TaskId, of respective DataSource view. */
  public static final String PROP_TASKID = "TaskId";
/** The property name constant equivalent to property, ClientId, of respective DataSource view. */
  public static final String PROP_CLIENTID = "ClientId";
/** The property name constant equivalent to property, ProjectName, of respective DataSource view. */
  public static final String PROP_PROJECTNAME = "ProjectName";
/** The property name constant equivalent to property, EffectiveDate, of respective DataSource view. */
  public static final String PROP_EFFECTIVEDATE = "EffectiveDate";
/** The property name constant equivalent to property, EndDate, of respective DataSource view. */
  public static final String PROP_ENDDATE = "EndDate";
/** The property name constant equivalent to property, TaskName, of respective DataSource view. */
  public static final String PROP_TASKNAME = "TaskName";
/** The property name constant equivalent to property, Billable, of respective DataSource view. */
  public static final String PROP_BILLABLE = "Billable";



	/** The javabean property equivalent of database column vw_timesheet_event_list.event_id */
  private int eventId;
/** The javabean property equivalent of database column vw_timesheet_event_list.event_date */
  private java.util.Date eventDate;
/** The javabean property equivalent of database column vw_timesheet_event_list.hours */
  private double hours;
/** The javabean property equivalent of database column vw_timesheet_event_list.event_date_created */
  private java.util.Date eventDateCreated;
/** The javabean property equivalent of database column vw_timesheet_event_list.project_task_id */
  private int projectTaskId;
/** The javabean property equivalent of database column vw_timesheet_event_list.timesheet_id */
  private int timesheetId;
/** The javabean property equivalent of database column vw_timesheet_event_list.project_id */
  private int projectId;
/** The javabean property equivalent of database column vw_timesheet_event_list.task_id */
  private int taskId;
/** The javabean property equivalent of database column vw_timesheet_event_list.client_id */
  private int clientId;
/** The javabean property equivalent of database column vw_timesheet_event_list.project_name */
  private String projectName;
/** The javabean property equivalent of database column vw_timesheet_event_list.effective_date */
  private java.util.Date effectiveDate;
/** The javabean property equivalent of database column vw_timesheet_event_list.end_date */
  private java.util.Date endDate;
/** The javabean property equivalent of database column vw_timesheet_event_list.task_name */
  private String taskName;
/** The javabean property equivalent of database column vw_timesheet_event_list.billable */
  private int billable;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwTimesheetEventList() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable eventId
 */
  public void setEventId(int value) {
    this.eventId = value;
  }
/**
 * Gets the value of member variable eventId
 */
  public int getEventId() {
    return this.eventId;
  }
/**
 * Sets the value of member variable eventDate
 */
  public void setEventDate(java.util.Date value) {
    this.eventDate = value;
  }
/**
 * Gets the value of member variable eventDate
 */
  public java.util.Date getEventDate() {
    return this.eventDate;
  }
/**
 * Sets the value of member variable hours
 */
  public void setHours(double value) {
    this.hours = value;
  }
/**
 * Gets the value of member variable hours
 */
  public double getHours() {
    return this.hours;
  }
/**
 * Sets the value of member variable eventDateCreated
 */
  public void setEventDateCreated(java.util.Date value) {
    this.eventDateCreated = value;
  }
/**
 * Gets the value of member variable eventDateCreated
 */
  public java.util.Date getEventDateCreated() {
    return this.eventDateCreated;
  }
/**
 * Sets the value of member variable projectTaskId
 */
  public void setProjectTaskId(int value) {
    this.projectTaskId = value;
  }
/**
 * Gets the value of member variable projectTaskId
 */
  public int getProjectTaskId() {
    return this.projectTaskId;
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
 * Sets the value of member variable projectId
 */
  public void setProjectId(int value) {
    this.projectId = value;
  }
/**
 * Gets the value of member variable projectId
 */
  public int getProjectId() {
    return this.projectId;
  }
/**
 * Sets the value of member variable taskId
 */
  public void setTaskId(int value) {
    this.taskId = value;
  }
/**
 * Gets the value of member variable taskId
 */
  public int getTaskId() {
    return this.taskId;
  }
/**
 * Sets the value of member variable clientId
 */
  public void setClientId(int value) {
    this.clientId = value;
  }
/**
 * Gets the value of member variable clientId
 */
  public int getClientId() {
    return this.clientId;
  }
/**
 * Sets the value of member variable projectName
 */
  public void setProjectName(String value) {
    this.projectName = value;
  }
/**
 * Gets the value of member variable projectName
 */
  public String getProjectName() {
    return this.projectName;
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
 * Sets the value of member variable taskName
 */
  public void setTaskName(String value) {
    this.taskName = value;
  }
/**
 * Gets the value of member variable taskName
 */
  public String getTaskName() {
    return this.taskName;
  }
/**
 * Sets the value of member variable billable
 */
  public void setBillable(int value) {
    this.billable = value;
  }
/**
 * Gets the value of member variable billable
 */
  public int getBillable() {
    return this.billable;
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
   final VwTimesheetEventList other = (VwTimesheetEventList) obj; 
   if (EqualityAssistant.notEqual(this.eventId, other.eventId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.eventDate, other.eventDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.hours, other.hours)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.eventDateCreated, other.eventDateCreated)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.projectTaskId, other.projectTaskId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.timesheetId, other.timesheetId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.projectId, other.projectId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.taskId, other.taskId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.clientId, other.clientId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.projectName, other.projectName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.effectiveDate, other.effectiveDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.endDate, other.endDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.taskName, other.taskName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.billable, other.billable)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.eventId),
               HashCodeAssistant.hashObject(this.eventDate),
               HashCodeAssistant.hashObject(this.hours),
               HashCodeAssistant.hashObject(this.eventDateCreated),
               HashCodeAssistant.hashObject(this.projectTaskId),
               HashCodeAssistant.hashObject(this.timesheetId),
               HashCodeAssistant.hashObject(this.projectId),
               HashCodeAssistant.hashObject(this.taskId),
               HashCodeAssistant.hashObject(this.clientId),
               HashCodeAssistant.hashObject(this.projectName),
               HashCodeAssistant.hashObject(this.effectiveDate),
               HashCodeAssistant.hashObject(this.endDate),
               HashCodeAssistant.hashObject(this.taskName),
               HashCodeAssistant.hashObject(this.billable));
} 

@Override
public String toString() {
   return "VwTimesheetEventList [eventId=" + eventId + 
          ", eventDate=" + eventDate + 
          ", hours=" + hours + 
          ", eventDateCreated=" + eventDateCreated + 
          ", projectTaskId=" + projectTaskId + 
          ", timesheetId=" + timesheetId + 
          ", projectId=" + projectId + 
          ", taskId=" + taskId + 
          ", clientId=" + clientId + 
          ", projectName=" + projectName + 
          ", effectiveDate=" + effectiveDate + 
          ", endDate=" + endDate + 
          ", taskName=" + taskName + 
          ", billable=" + billable  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}