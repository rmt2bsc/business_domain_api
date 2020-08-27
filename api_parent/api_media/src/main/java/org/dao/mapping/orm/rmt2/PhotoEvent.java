package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the photo_event database table/view.
 *
 * @author auto generated.
 */
public class PhotoEvent extends OrmBean {




	// Property name constants that belong to respective DataSource, PhotoEventView

/** The property name constant equivalent to property, EventId3, of respective DataSource view. */
  public static final String PROP_EVENTID3 = "EventId3";
/** The property name constant equivalent to property, AlbumId2, of respective DataSource view. */
  public static final String PROP_ALBUMID2 = "AlbumId2";
/** The property name constant equivalent to property, EventName, of respective DataSource view. */
  public static final String PROP_EVENTNAME = "EventName";
/** The property name constant equivalent to property, Location, of respective DataSource view. */
  public static final String PROP_LOCATION = "Location";
/** The property name constant equivalent to property, DateCreated, of respective DataSource view. */
  public static final String PROP_DATECREATED = "DateCreated";
/** The property name constant equivalent to property, DateUpdated, of respective DataSource view. */
  public static final String PROP_DATEUPDATED = "DateUpdated";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";



	/** The javabean property equivalent of database column photo_event.event_id3 */
  private int eventId3;
/** The javabean property equivalent of database column photo_event.album_id2 */
  private int albumId2;
/** The javabean property equivalent of database column photo_event.event_name */
  private String eventName;
/** The javabean property equivalent of database column photo_event.location */
  private String location;
/** The javabean property equivalent of database column photo_event.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column photo_event.date_updated */
  private java.util.Date dateUpdated;
/** The javabean property equivalent of database column photo_event.user_id */
  private String userId;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public PhotoEvent() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable eventId3
 */
  public void setEventId3(int value) {
    this.eventId3 = value;
  }
/**
 * Gets the value of member variable eventId3
 */
  public int getEventId3() {
    return this.eventId3;
  }
/**
 * Sets the value of member variable albumId2
 */
  public void setAlbumId2(int value) {
    this.albumId2 = value;
  }
/**
 * Gets the value of member variable albumId2
 */
  public int getAlbumId2() {
    return this.albumId2;
  }
/**
 * Sets the value of member variable eventName
 */
  public void setEventName(String value) {
    this.eventName = value;
  }
/**
 * Gets the value of member variable eventName
 */
  public String getEventName() {
    return this.eventName;
  }
/**
 * Sets the value of member variable location
 */
  public void setLocation(String value) {
    this.location = value;
  }
/**
 * Gets the value of member variable location
 */
  public String getLocation() {
    return this.location;
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
   final PhotoEvent other = (PhotoEvent) obj; 
   if (EqualityAssistant.notEqual(this.eventId3, other.eventId3)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.albumId2, other.albumId2)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.eventName, other.eventName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.location, other.location)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.eventId3),
               HashCodeAssistant.hashObject(this.albumId2),
               HashCodeAssistant.hashObject(this.eventName),
               HashCodeAssistant.hashObject(this.location));
} 

@Override
public String toString() {
   return "PhotoEvent [eventId3=" + eventId3 + 
          ", albumId2=" + albumId2 + 
          ", eventName=" + eventName + 
          ", location=" + location  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}