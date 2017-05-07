package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the proj_task database table/view.
 *
 * @author auto generated.
 */
public class ProjTask extends OrmBean {




	// Property name constants that belong to respective DataSource, ProjTaskView

/** The property name constant equivalent to property, TaskId, of respective DataSource view. */
  public static final String PROP_TASKID = "TaskId";
/** The property name constant equivalent to property, Description, of respective DataSource view. */
  public static final String PROP_DESCRIPTION = "Description";
/** The property name constant equivalent to property, Billable, of respective DataSource view. */
  public static final String PROP_BILLABLE = "Billable";
/** The property name constant equivalent to property, DateCreated, of respective DataSource view. */
  public static final String PROP_DATECREATED = "DateCreated";
/** The property name constant equivalent to property, DateUpdated, of respective DataSource view. */
  public static final String PROP_DATEUPDATED = "DateUpdated";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";



	/** The javabean property equivalent of database column proj_task.task_id */
  private int taskId;
/** The javabean property equivalent of database column proj_task.description */
  private String description;
/** The javabean property equivalent of database column proj_task.billable */
  private int billable;
/** The javabean property equivalent of database column proj_task.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column proj_task.date_updated */
  private java.util.Date dateUpdated;
/** The javabean property equivalent of database column proj_task.user_id */
  private String userId;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public ProjTask() throws SystemException {
	super();
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
 * Sets the value of member variable description
 */
  public void setDescription(String value) {
    this.description = value;
  }
/**
 * Gets the value of member variable description
 */
  public String getDescription() {
    return this.description;
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
   final ProjTask other = (ProjTask) obj; 
   if (EqualityAssistant.notEqual(this.taskId, other.taskId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.description, other.description)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.billable, other.billable)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.dateCreated, other.dateCreated)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.dateUpdated, other.dateUpdated)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.userId, other.userId)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.taskId),
   HashCodeAssistant.hashObject(this.description),
   HashCodeAssistant.hashObject(this.billable),
   HashCodeAssistant.hashObject(this.dateCreated),
   HashCodeAssistant.hashObject(this.dateUpdated),
   HashCodeAssistant.hashObject(this.userId));
} 

@Override
public String toString() {
   return "ProjTask [taskId=" + taskId + 
          ", description=" + description + 
          ", billable=" + billable + 
          ", dateCreated=" + dateCreated + 
          ", dateUpdated=" + dateUpdated + 
          ", userId=" + userId  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}