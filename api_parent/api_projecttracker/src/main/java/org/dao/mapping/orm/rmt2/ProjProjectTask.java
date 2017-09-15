package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the proj_project_task database table/view.
 *
 * @author auto generated.
 */
public class ProjProjectTask extends OrmBean {




	// Property name constants that belong to respective DataSource, ProjProjectTaskView

/** The property name constant equivalent to property, ProjectTaskId, of respective DataSource view. */
  public static final String PROP_PROJECTTASKID = "ProjectTaskId";
/** The property name constant equivalent to property, TaskId, of respective DataSource view. */
  public static final String PROP_TASKID = "TaskId";
/** The property name constant equivalent to property, TimesheetId, of respective DataSource view. */
  public static final String PROP_TIMESHEETID = "TimesheetId";
/** The property name constant equivalent to property, ProjId, of respective DataSource view. */
  public static final String PROP_PROJID = "ProjId";



	/** The javabean property equivalent of database column proj_project_task.project_task_id */
  private int projectTaskId;
/** The javabean property equivalent of database column proj_project_task.task_id */
  private int taskId;
/** The javabean property equivalent of database column proj_project_task.timesheet_id */
  private int timesheetId;
/** The javabean property equivalent of database column proj_project_task.proj_id */
  private int projId;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public ProjProjectTask() throws SystemException {
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
 * Sets the value of member variable projId
 */
  public void setProjId(int value) {
    this.projId = value;
  }
/**
 * Gets the value of member variable projId
 */
  public int getProjId() {
    return this.projId;
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
   final ProjProjectTask other = (ProjProjectTask) obj; 
   if (EqualityAssistant.notEqual(this.projectTaskId, other.projectTaskId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.taskId, other.taskId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.timesheetId, other.timesheetId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.projId, other.projId)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.projectTaskId),
               HashCodeAssistant.hashObject(this.taskId),
               HashCodeAssistant.hashObject(this.timesheetId),
               HashCodeAssistant.hashObject(this.projId));
} 

@Override
public String toString() {
   return "ProjProjectTask [projectTaskId=" + projectTaskId + 
          ", taskId=" + taskId + 
          ", timesheetId=" + timesheetId + 
          ", projId=" + projId  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}