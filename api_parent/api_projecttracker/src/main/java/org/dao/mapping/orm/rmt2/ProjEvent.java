package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the proj_event database table/view.
 *
 * @author auto generated.
 */
public class ProjEvent extends OrmBean {




	// Property name constants that belong to respective DataSource, ProjEventView

/** The property name constant equivalent to property, EventId, of respective DataSource view. */
  public static final String PROP_EVENTID = "EventId";
/** The property name constant equivalent to property, ProjectTaskId, of respective DataSource view. */
  public static final String PROP_PROJECTTASKID = "ProjectTaskId";
/** The property name constant equivalent to property, EventDate, of respective DataSource view. */
  public static final String PROP_EVENTDATE = "EventDate";
/** The property name constant equivalent to property, Hours, of respective DataSource view. */
  public static final String PROP_HOURS = "Hours";
/** The property name constant equivalent to property, DateCreated, of respective DataSource view. */
  public static final String PROP_DATECREATED = "DateCreated";
/** The property name constant equivalent to property, DateUpdated, of respective DataSource view. */
  public static final String PROP_DATEUPDATED = "DateUpdated";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";



	/** The javabean property equivalent of database column proj_event.event_id */
  private int eventId;
/** The javabean property equivalent of database column proj_event.project_task_id */
  private int projectTaskId;
/** The javabean property equivalent of database column proj_event.event_date */
  private java.util.Date eventDate;
/** The javabean property equivalent of database column proj_event.hours */
  private double hours;
/** The javabean property equivalent of database column proj_event.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column proj_event.date_updated */
  private java.util.Date dateUpdated;
/** The javabean property equivalent of database column proj_event.user_id */
  private String userId;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public ProjEvent() throws SystemException {
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
   final ProjEvent other = (ProjEvent) obj; 
   if (EqualityAssistant.notEqual(this.eventId, other.eventId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.projectTaskId, other.projectTaskId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.eventDate, other.eventDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.hours, other.hours)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.eventId),
               HashCodeAssistant.hashObject(this.projectTaskId),
               HashCodeAssistant.hashObject(this.eventDate),
               HashCodeAssistant.hashObject(this.hours));
} 

@Override
public String toString() {
   return "ProjEvent [eventId=" + eventId + 
          ", projectTaskId=" + projectTaskId + 
          ", eventDate=" + eventDate + 
          ", hours=" + hours  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}