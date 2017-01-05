package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the vw_timesheet_project_task database table/view.
 *
 * @author auto generated.
 */
public class VwTimesheetProjectTask extends OrmBean {




	// Property name constants that belong to respective DataSource, VwTimesheetProjectTaskView

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



	/** The javabean property equivalent of database column vw_timesheet_project_task.project_task_id */
  private int projectTaskId;
/** The javabean property equivalent of database column vw_timesheet_project_task.timesheet_id */
  private int timesheetId;
/** The javabean property equivalent of database column vw_timesheet_project_task.project_id */
  private int projectId;
/** The javabean property equivalent of database column vw_timesheet_project_task.task_id */
  private int taskId;
/** The javabean property equivalent of database column vw_timesheet_project_task.client_id */
  private int clientId;
/** The javabean property equivalent of database column vw_timesheet_project_task.project_name */
  private String projectName;
/** The javabean property equivalent of database column vw_timesheet_project_task.effective_date */
  private java.util.Date effectiveDate;
/** The javabean property equivalent of database column vw_timesheet_project_task.end_date */
  private java.util.Date endDate;
/** The javabean property equivalent of database column vw_timesheet_project_task.task_name */
  private String taskName;
/** The javabean property equivalent of database column vw_timesheet_project_task.billable */
  private int billable;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwTimesheetProjectTask() throws SystemException {
	super();
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
/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}