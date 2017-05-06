package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the vw_timesheet_hours database table/view.
 *
 * @author auto generated.
 */
public class VwTimesheetHours extends OrmBean {




	// Property name constants that belong to respective DataSource, VwTimesheetHoursView

/** The property name constant equivalent to property, TimesheetId, of respective DataSource view. */
  public static final String PROP_TIMESHEETID = "TimesheetId";
/** The property name constant equivalent to property, ClientId, of respective DataSource view. */
  public static final String PROP_CLIENTID = "ClientId";
/** The property name constant equivalent to property, EmployeeId, of respective DataSource view. */
  public static final String PROP_EMPLOYEEID = "EmployeeId";
/** The property name constant equivalent to property, DisplayValue, of respective DataSource view. */
  public static final String PROP_DISPLAYVALUE = "DisplayValue";
/** The property name constant equivalent to property, TimesheetBeginPeriod, of respective DataSource view. */
  public static final String PROP_TIMESHEETBEGINPERIOD = "TimesheetBeginPeriod";
/** The property name constant equivalent to property, TimesheetEndPeriod, of respective DataSource view. */
  public static final String PROP_TIMESHEETENDPERIOD = "TimesheetEndPeriod";
/** The property name constant equivalent to property, InvoiceRefNo, of respective DataSource view. */
  public static final String PROP_INVOICEREFNO = "InvoiceRefNo";
/** The property name constant equivalent to property, ExtRef, of respective DataSource view. */
  public static final String PROP_EXTREF = "ExtRef";
/** The property name constant equivalent to property, DocumentId, of respective DataSource view. */
  public static final String PROP_DOCUMENTID = "DocumentId";
/** The property name constant equivalent to property, ProjectId, of respective DataSource view. */
  public static final String PROP_PROJECTID = "ProjectId";
/** The property name constant equivalent to property, TaskId, of respective DataSource view. */
  public static final String PROP_TASKID = "TaskId";
/** The property name constant equivalent to property, EventId, of respective DataSource view. */
  public static final String PROP_EVENTID = "EventId";
/** The property name constant equivalent to property, ProjTaskId, of respective DataSource view. */
  public static final String PROP_PROJTASKID = "ProjTaskId";
/** The property name constant equivalent to property, ProjectName, of respective DataSource view. */
  public static final String PROP_PROJECTNAME = "ProjectName";
/** The property name constant equivalent to property, TaskName, of respective DataSource view. */
  public static final String PROP_TASKNAME = "TaskName";
/** The property name constant equivalent to property, EffectiveDate, of respective DataSource view. */
  public static final String PROP_EFFECTIVEDATE = "EffectiveDate";
/** The property name constant equivalent to property, EndDate, of respective DataSource view. */
  public static final String PROP_ENDDATE = "EndDate";
/** The property name constant equivalent to property, Billable, of respective DataSource view. */
  public static final String PROP_BILLABLE = "Billable";
/** The property name constant equivalent to property, EventDate, of respective DataSource view. */
  public static final String PROP_EVENTDATE = "EventDate";
/** The property name constant equivalent to property, Hours, of respective DataSource view. */
  public static final String PROP_HOURS = "Hours";
/** The property name constant equivalent to property, DateCreated, of respective DataSource view. */
  public static final String PROP_DATECREATED = "DateCreated";



	/** The javabean property equivalent of database column vw_timesheet_hours.timesheet_id */
  private int timesheetId;
/** The javabean property equivalent of database column vw_timesheet_hours.client_id */
  private int clientId;
/** The javabean property equivalent of database column vw_timesheet_hours.employee_id */
  private int employeeId;
/** The javabean property equivalent of database column vw_timesheet_hours.display_value */
  private String displayValue;
/** The javabean property equivalent of database column vw_timesheet_hours.timesheet_begin_period */
  private java.util.Date timesheetBeginPeriod;
/** The javabean property equivalent of database column vw_timesheet_hours.timesheet_end_period */
  private java.util.Date timesheetEndPeriod;
/** The javabean property equivalent of database column vw_timesheet_hours.invoice_ref_no */
  private String invoiceRefNo;
/** The javabean property equivalent of database column vw_timesheet_hours.ext_ref */
  private String extRef;
/** The javabean property equivalent of database column vw_timesheet_hours.document_id */
  private int documentId;
/** The javabean property equivalent of database column vw_timesheet_hours.project_id */
  private int projectId;
/** The javabean property equivalent of database column vw_timesheet_hours.task_id */
  private int taskId;
/** The javabean property equivalent of database column vw_timesheet_hours.event_id */
  private int eventId;
/** The javabean property equivalent of database column vw_timesheet_hours.proj_task_id */
  private int projTaskId;
/** The javabean property equivalent of database column vw_timesheet_hours.project_name */
  private String projectName;
/** The javabean property equivalent of database column vw_timesheet_hours.task_name */
  private String taskName;
/** The javabean property equivalent of database column vw_timesheet_hours.effective_date */
  private java.util.Date effectiveDate;
/** The javabean property equivalent of database column vw_timesheet_hours.end_date */
  private java.util.Date endDate;
/** The javabean property equivalent of database column vw_timesheet_hours.billable */
  private int billable;
/** The javabean property equivalent of database column vw_timesheet_hours.event_date */
  private java.util.Date eventDate;
/** The javabean property equivalent of database column vw_timesheet_hours.hours */
  private double hours;
/** The javabean property equivalent of database column vw_timesheet_hours.date_created */
  private java.util.Date dateCreated;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwTimesheetHours() throws SystemException {
	super();
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
 * Sets the value of member variable employeeId
 */
  public void setEmployeeId(int value) {
    this.employeeId = value;
  }
/**
 * Gets the value of member variable employeeId
 */
  public int getEmployeeId() {
    return this.employeeId;
  }
/**
 * Sets the value of member variable displayValue
 */
  public void setDisplayValue(String value) {
    this.displayValue = value;
  }
/**
 * Gets the value of member variable displayValue
 */
  public String getDisplayValue() {
    return this.displayValue;
  }
/**
 * Sets the value of member variable timesheetBeginPeriod
 */
  public void setTimesheetBeginPeriod(java.util.Date value) {
    this.timesheetBeginPeriod = value;
  }
/**
 * Gets the value of member variable timesheetBeginPeriod
 */
  public java.util.Date getTimesheetBeginPeriod() {
    return this.timesheetBeginPeriod;
  }
/**
 * Sets the value of member variable timesheetEndPeriod
 */
  public void setTimesheetEndPeriod(java.util.Date value) {
    this.timesheetEndPeriod = value;
  }
/**
 * Gets the value of member variable timesheetEndPeriod
 */
  public java.util.Date getTimesheetEndPeriod() {
    return this.timesheetEndPeriod;
  }
/**
 * Sets the value of member variable invoiceRefNo
 */
  public void setInvoiceRefNo(String value) {
    this.invoiceRefNo = value;
  }
/**
 * Gets the value of member variable invoiceRefNo
 */
  public String getInvoiceRefNo() {
    return this.invoiceRefNo;
  }
/**
 * Sets the value of member variable extRef
 */
  public void setExtRef(String value) {
    this.extRef = value;
  }
/**
 * Gets the value of member variable extRef
 */
  public String getExtRef() {
    return this.extRef;
  }
/**
 * Sets the value of member variable documentId
 */
  public void setDocumentId(int value) {
    this.documentId = value;
  }
/**
 * Gets the value of member variable documentId
 */
  public int getDocumentId() {
    return this.documentId;
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
 * Sets the value of member variable projTaskId
 */
  public void setProjTaskId(int value) {
    this.projTaskId = value;
  }
/**
 * Gets the value of member variable projTaskId
 */
  public int getProjTaskId() {
    return this.projTaskId;
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
   final VwTimesheetHours other = (VwTimesheetHours) obj; 
   if (EqualityAssistant.notEqual(this.timesheetId, other.timesheetId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.clientId, other.clientId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.employeeId, other.employeeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.displayValue, other.displayValue)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.timesheetBeginPeriod, other.timesheetBeginPeriod)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.timesheetEndPeriod, other.timesheetEndPeriod)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.invoiceRefNo, other.invoiceRefNo)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.extRef, other.extRef)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.documentId, other.documentId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.projectId, other.projectId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.taskId, other.taskId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.eventId, other.eventId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.projTaskId, other.projTaskId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.projectName, other.projectName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.taskName, other.taskName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.effectiveDate, other.effectiveDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.endDate, other.endDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.billable, other.billable)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.eventDate, other.eventDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.hours, other.hours)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.dateCreated, other.dateCreated)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.timesheetId),
   HashCodeAssistant.hashObject(this.clientId),
   HashCodeAssistant.hashObject(this.employeeId),
   HashCodeAssistant.hashObject(this.displayValue),
   HashCodeAssistant.hashObject(this.timesheetBeginPeriod),
   HashCodeAssistant.hashObject(this.timesheetEndPeriod),
   HashCodeAssistant.hashObject(this.invoiceRefNo),
   HashCodeAssistant.hashObject(this.extRef),
   HashCodeAssistant.hashObject(this.documentId),
   HashCodeAssistant.hashObject(this.projectId),
   HashCodeAssistant.hashObject(this.taskId),
   HashCodeAssistant.hashObject(this.eventId),
   HashCodeAssistant.hashObject(this.projTaskId),
   HashCodeAssistant.hashObject(this.projectName),
   HashCodeAssistant.hashObject(this.taskName),
   HashCodeAssistant.hashObject(this.effectiveDate),
   HashCodeAssistant.hashObject(this.endDate),
   HashCodeAssistant.hashObject(this.billable),
   HashCodeAssistant.hashObject(this.eventDate),
   HashCodeAssistant.hashObject(this.hours),
   HashCodeAssistant.hashObject(this.dateCreated));
} 

@Override
public String toString() {
   return "VwTimesheetHours [timesheetId=" + timesheetId + 
          ", clientId=" + clientId + 
          ", employeeId=" + employeeId + 
          ", displayValue=" + displayValue + 
          ", timesheetBeginPeriod=" + timesheetBeginPeriod + 
          ", timesheetEndPeriod=" + timesheetEndPeriod + 
          ", invoiceRefNo=" + invoiceRefNo + 
          ", extRef=" + extRef + 
          ", documentId=" + documentId + 
          ", projectId=" + projectId + 
          ", taskId=" + taskId + 
          ", eventId=" + eventId + 
          ", projTaskId=" + projTaskId + 
          ", projectName=" + projectName + 
          ", taskName=" + taskName + 
          ", effectiveDate=" + effectiveDate + 
          ", endDate=" + endDate + 
          ", billable=" + billable + 
          ", eventDate=" + eventDate + 
          ", hours=" + hours + 
          ", dateCreated=" + dateCreated  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}