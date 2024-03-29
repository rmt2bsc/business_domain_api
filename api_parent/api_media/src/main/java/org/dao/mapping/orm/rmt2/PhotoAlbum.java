package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the photo_album database table/view.
 *
 * @author auto generated.
 */
public class PhotoAlbum extends OrmBean {




	// Property name constants that belong to respective DataSource, PhotoAlbumView

/** The property name constant equivalent to property, AlbumId2, of respective DataSource view. */
  public static final String PROP_ALBUMID2 = "AlbumId2";
/** The property name constant equivalent to property, AlbumName, of respective DataSource view. */
  public static final String PROP_ALBUMNAME = "AlbumName";
/** The property name constant equivalent to property, AlbumDate, of respective DataSource view. */
  public static final String PROP_ALBUMDATE = "AlbumDate";
/** The property name constant equivalent to property, Location, of respective DataSource view. */
  public static final String PROP_LOCATION = "Location";
/** The property name constant equivalent to property, Servername, of respective DataSource view. */
  public static final String PROP_SERVERNAME = "Servername";
/** The property name constant equivalent to property, Sharename, of respective DataSource view. */
  public static final String PROP_SHARENAME = "Sharename";
/** The property name constant equivalent to property, DateCreated, of respective DataSource view. */
  public static final String PROP_DATECREATED = "DateCreated";
/** The property name constant equivalent to property, DateUpdated, of respective DataSource view. */
  public static final String PROP_DATEUPDATED = "DateUpdated";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";



	/** The javabean property equivalent of database column photo_album.album_id2 */
  private int albumId2;
/** The javabean property equivalent of database column photo_album.album_name */
  private String albumName;
/** The javabean property equivalent of database column photo_album.album_date */
  private java.util.Date albumDate;
/** The javabean property equivalent of database column photo_album.location */
  private String location;
/** The javabean property equivalent of database column photo_album.servername */
  private String servername;
/** The javabean property equivalent of database column photo_album.sharename */
  private String sharename;
/** The javabean property equivalent of database column photo_album.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column photo_album.date_updated */
  private java.util.Date dateUpdated;
/** The javabean property equivalent of database column photo_album.user_id */
  private String userId;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public PhotoAlbum() throws SystemException {
	super();
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
 * Sets the value of member variable albumName
 */
  public void setAlbumName(String value) {
    this.albumName = value;
  }
/**
 * Gets the value of member variable albumName
 */
  public String getAlbumName() {
    return this.albumName;
  }
/**
 * Sets the value of member variable albumDate
 */
  public void setAlbumDate(java.util.Date value) {
    this.albumDate = value;
  }
/**
 * Gets the value of member variable albumDate
 */
  public java.util.Date getAlbumDate() {
    return this.albumDate;
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
 * Sets the value of member variable servername
 */
  public void setServername(String value) {
    this.servername = value;
  }
/**
 * Gets the value of member variable servername
 */
  public String getServername() {
    return this.servername;
  }
/**
 * Sets the value of member variable sharename
 */
  public void setSharename(String value) {
    this.sharename = value;
  }
/**
 * Gets the value of member variable sharename
 */
  public String getSharename() {
    return this.sharename;
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
   final PhotoAlbum other = (PhotoAlbum) obj; 
   if (EqualityAssistant.notEqual(this.albumId2, other.albumId2)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.albumName, other.albumName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.albumDate, other.albumDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.location, other.location)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.servername, other.servername)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.sharename, other.sharename)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.albumId2),
               HashCodeAssistant.hashObject(this.albumName),
               HashCodeAssistant.hashObject(this.albumDate),
               HashCodeAssistant.hashObject(this.location),
               HashCodeAssistant.hashObject(this.servername),
               HashCodeAssistant.hashObject(this.sharename));
} 

@Override
public String toString() {
   return "PhotoAlbum [albumId2=" + albumId2 + 
          ", albumName=" + albumName + 
          ", albumDate=" + albumDate + 
          ", location=" + location + 
          ", servername=" + servername + 
          ", sharename=" + sharename  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}