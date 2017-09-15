package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the proj_timesheet_status database table/view.
 *
 * @author auto generated.
 */
public class ProjTimesheetStatus extends OrmBean {




	// Property name constants that belong to respective DataSource, ProjTimesheetStatusView

/** The property name constant equivalent to property, TimesheetStatusId, of respective DataSource view. */
  public static final String PROP_TIMESHEETSTATUSID = "TimesheetStatusId";
/** The property name constant equivalent to property, Name, of respective DataSource view. */
  public static final String PROP_NAME = "Name";
/** The property name constant equivalent to property, Description, of respective DataSource view. */
  public static final String PROP_DESCRIPTION = "Description";



	/** The javabean property equivalent of database column proj_timesheet_status.timesheet_status_id */
  private int timesheetStatusId;
/** The javabean property equivalent of database column proj_timesheet_status.name */
  private String name;
/** The javabean property equivalent of database column proj_timesheet_status.description */
  private String description;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public ProjTimesheetStatus() throws SystemException {
	super();
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
   final ProjTimesheetStatus other = (ProjTimesheetStatus) obj; 
   if (EqualityAssistant.notEqual(this.timesheetStatusId, other.timesheetStatusId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.name, other.name)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.description, other.description)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.timesheetStatusId),
               HashCodeAssistant.hashObject(this.name),
               HashCodeAssistant.hashObject(this.description));
} 

@Override
public String toString() {
   return "ProjTimesheetStatus [timesheetStatusId=" + timesheetStatusId + 
          ", name=" + name + 
          ", description=" + description  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}