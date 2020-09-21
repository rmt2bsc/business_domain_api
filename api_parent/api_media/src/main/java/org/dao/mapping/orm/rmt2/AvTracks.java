package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the av_tracks database table/view.
 *
 * @author auto generated.
 */
public class AvTracks extends OrmBean {




	// Property name constants that belong to respective DataSource, AvTracksView

/** The property name constant equivalent to property, TrackId, of respective DataSource view. */
  public static final String PROP_TRACKID = "TrackId";
/** The property name constant equivalent to property, ProjectId, of respective DataSource view. */
  public static final String PROP_PROJECTID = "ProjectId";
/** The property name constant equivalent to property, TrackNumber, of respective DataSource view. */
  public static final String PROP_TRACKNUMBER = "TrackNumber";
/** The property name constant equivalent to property, TrackTitle, of respective DataSource view. */
  public static final String PROP_TRACKTITLE = "TrackTitle";
/** The property name constant equivalent to property, TrackHours, of respective DataSource view. */
  public static final String PROP_TRACKHOURS = "TrackHours";
/** The property name constant equivalent to property, TrackMinutes, of respective DataSource view. */
  public static final String PROP_TRACKMINUTES = "TrackMinutes";
/** The property name constant equivalent to property, TrackSeconds, of respective DataSource view. */
  public static final String PROP_TRACKSECONDS = "TrackSeconds";
/** The property name constant equivalent to property, TrackDisc, of respective DataSource view. */
  public static final String PROP_TRACKDISC = "TrackDisc";
/** The property name constant equivalent to property, TrackProducer, of respective DataSource view. */
  public static final String PROP_TRACKPRODUCER = "TrackProducer";
/** The property name constant equivalent to property, TrackComposer, of respective DataSource view. */
  public static final String PROP_TRACKCOMPOSER = "TrackComposer";
/** The property name constant equivalent to property, TrackLyricist, of respective DataSource view. */
  public static final String PROP_TRACKLYRICIST = "TrackLyricist";
/** The property name constant equivalent to property, LocServername, of respective DataSource view. */
  public static final String PROP_LOCSERVERNAME = "LocServername";
/** The property name constant equivalent to property, LocSharename, of respective DataSource view. */
  public static final String PROP_LOCSHARENAME = "LocSharename";
/** The property name constant equivalent to property, LocRootPath, of respective DataSource view. */
  public static final String PROP_LOCROOTPATH = "LocRootPath";
/** The property name constant equivalent to property, LocPath, of respective DataSource view. */
  public static final String PROP_LOCPATH = "LocPath";
/** The property name constant equivalent to property, LocFilename, of respective DataSource view. */
  public static final String PROP_LOCFILENAME = "LocFilename";
/** The property name constant equivalent to property, Comments, of respective DataSource view. */
  public static final String PROP_COMMENTS = "Comments";
/** The property name constant equivalent to property, DateCreated, of respective DataSource view. */
  public static final String PROP_DATECREATED = "DateCreated";
/** The property name constant equivalent to property, DateUpdated, of respective DataSource view. */
  public static final String PROP_DATEUPDATED = "DateUpdated";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";
/** The property name constant equivalent to property, GenreId, of respective DataSource view. */
  public static final String PROP_GENREID = "GenreId";



	/** The javabean property equivalent of database column av_tracks.track_id */
  private int trackId;
/** The javabean property equivalent of database column av_tracks.project_id */
  private int projectId;
/** The javabean property equivalent of database column av_tracks.track_number */
  private int trackNumber;
/** The javabean property equivalent of database column av_tracks.track_title */
  private String trackTitle;
/** The javabean property equivalent of database column av_tracks.track_hours */
  private int trackHours;
/** The javabean property equivalent of database column av_tracks.track_minutes */
  private int trackMinutes;
/** The javabean property equivalent of database column av_tracks.track_seconds */
  private int trackSeconds;
/** The javabean property equivalent of database column av_tracks.track_disc */
  private String trackDisc;
/** The javabean property equivalent of database column av_tracks.track_producer */
  private String trackProducer;
/** The javabean property equivalent of database column av_tracks.track_composer */
  private String trackComposer;
/** The javabean property equivalent of database column av_tracks.track_lyricist */
  private String trackLyricist;
/** The javabean property equivalent of database column av_tracks.loc_servername */
  private String locServername;
/** The javabean property equivalent of database column av_tracks.loc_sharename */
  private String locSharename;
/** The javabean property equivalent of database column av_tracks.loc_root_path */
  private String locRootPath;
/** The javabean property equivalent of database column av_tracks.loc_path */
  private String locPath;
/** The javabean property equivalent of database column av_tracks.loc_filename */
  private String locFilename;
/** The javabean property equivalent of database column av_tracks.comments */
  private String comments;
/** The javabean property equivalent of database column av_tracks.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column av_tracks.date_updated */
  private java.util.Date dateUpdated;
/** The javabean property equivalent of database column av_tracks.user_id */
  private String userId;
/** The javabean property equivalent of database column av_tracks.genre_id */
  private int genreId;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public AvTracks() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable trackId
 */
  public void setTrackId(int value) {
    this.trackId = value;
  }
/**
 * Gets the value of member variable trackId
 */
  public int getTrackId() {
    return this.trackId;
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
 * Sets the value of member variable trackNumber
 */
  public void setTrackNumber(int value) {
    this.trackNumber = value;
  }
/**
 * Gets the value of member variable trackNumber
 */
  public int getTrackNumber() {
    return this.trackNumber;
  }
/**
 * Sets the value of member variable trackTitle
 */
  public void setTrackTitle(String value) {
    this.trackTitle = value;
  }
/**
 * Gets the value of member variable trackTitle
 */
  public String getTrackTitle() {
    return this.trackTitle;
  }
/**
 * Sets the value of member variable trackHours
 */
  public void setTrackHours(int value) {
    this.trackHours = value;
  }
/**
 * Gets the value of member variable trackHours
 */
  public int getTrackHours() {
    return this.trackHours;
  }
/**
 * Sets the value of member variable trackMinutes
 */
  public void setTrackMinutes(int value) {
    this.trackMinutes = value;
  }
/**
 * Gets the value of member variable trackMinutes
 */
  public int getTrackMinutes() {
    return this.trackMinutes;
  }
/**
 * Sets the value of member variable trackSeconds
 */
  public void setTrackSeconds(int value) {
    this.trackSeconds = value;
  }
/**
 * Gets the value of member variable trackSeconds
 */
  public int getTrackSeconds() {
    return this.trackSeconds;
  }
/**
 * Sets the value of member variable trackDisc
 */
  public void setTrackDisc(String value) {
    this.trackDisc = value;
  }
/**
 * Gets the value of member variable trackDisc
 */
  public String getTrackDisc() {
    return this.trackDisc;
  }
/**
 * Sets the value of member variable trackProducer
 */
  public void setTrackProducer(String value) {
    this.trackProducer = value;
  }
/**
 * Gets the value of member variable trackProducer
 */
  public String getTrackProducer() {
    return this.trackProducer;
  }
/**
 * Sets the value of member variable trackComposer
 */
  public void setTrackComposer(String value) {
    this.trackComposer = value;
  }
/**
 * Gets the value of member variable trackComposer
 */
  public String getTrackComposer() {
    return this.trackComposer;
  }
/**
 * Sets the value of member variable trackLyricist
 */
  public void setTrackLyricist(String value) {
    this.trackLyricist = value;
  }
/**
 * Gets the value of member variable trackLyricist
 */
  public String getTrackLyricist() {
    return this.trackLyricist;
  }
/**
 * Sets the value of member variable locServername
 */
  public void setLocServername(String value) {
    this.locServername = value;
  }
/**
 * Gets the value of member variable locServername
 */
  public String getLocServername() {
    return this.locServername;
  }
/**
 * Sets the value of member variable locSharename
 */
  public void setLocSharename(String value) {
    this.locSharename = value;
  }
/**
 * Gets the value of member variable locSharename
 */
  public String getLocSharename() {
    return this.locSharename;
  }
/**
 * Sets the value of member variable locRootPath
 */
  public void setLocRootPath(String value) {
    this.locRootPath = value;
  }
/**
 * Gets the value of member variable locRootPath
 */
  public String getLocRootPath() {
    return this.locRootPath;
  }
/**
 * Sets the value of member variable locPath
 */
  public void setLocPath(String value) {
    this.locPath = value;
  }
/**
 * Gets the value of member variable locPath
 */
  public String getLocPath() {
    return this.locPath;
  }
/**
 * Sets the value of member variable locFilename
 */
  public void setLocFilename(String value) {
    this.locFilename = value;
  }
/**
 * Gets the value of member variable locFilename
 */
  public String getLocFilename() {
    return this.locFilename;
  }
/**
 * Sets the value of member variable comments
 */
  public void setComments(String value) {
    this.comments = value;
  }
/**
 * Gets the value of member variable comments
 */
  public String getComments() {
    return this.comments;
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
/**
 * Sets the value of member variable genreId
 */
  public void setGenreId(int value) {
    this.genreId = value;
  }
/**
 * Gets the value of member variable genreId
 */
  public int getGenreId() {
    return this.genreId;
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
   final AvTracks other = (AvTracks) obj; 
   if (EqualityAssistant.notEqual(this.trackId, other.trackId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.projectId, other.projectId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.trackNumber, other.trackNumber)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.trackTitle, other.trackTitle)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.trackHours, other.trackHours)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.trackMinutes, other.trackMinutes)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.trackSeconds, other.trackSeconds)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.trackDisc, other.trackDisc)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.trackProducer, other.trackProducer)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.trackComposer, other.trackComposer)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.trackLyricist, other.trackLyricist)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.locServername, other.locServername)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.locSharename, other.locSharename)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.locRootPath, other.locRootPath)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.locPath, other.locPath)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.locFilename, other.locFilename)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.comments, other.comments)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.genreId, other.genreId)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.trackId),
               HashCodeAssistant.hashObject(this.projectId),
               HashCodeAssistant.hashObject(this.trackNumber),
               HashCodeAssistant.hashObject(this.trackTitle),
               HashCodeAssistant.hashObject(this.trackHours),
               HashCodeAssistant.hashObject(this.trackMinutes),
               HashCodeAssistant.hashObject(this.trackSeconds),
               HashCodeAssistant.hashObject(this.trackDisc),
               HashCodeAssistant.hashObject(this.trackProducer),
               HashCodeAssistant.hashObject(this.trackComposer),
               HashCodeAssistant.hashObject(this.trackLyricist),
               HashCodeAssistant.hashObject(this.locServername),
               HashCodeAssistant.hashObject(this.locSharename),
               HashCodeAssistant.hashObject(this.locRootPath),
               HashCodeAssistant.hashObject(this.locPath),
               HashCodeAssistant.hashObject(this.locFilename),
               HashCodeAssistant.hashObject(this.comments),
               HashCodeAssistant.hashObject(this.genreId));
} 

@Override
public String toString() {
   return "AvTracks [trackId=" + trackId + 
          ", projectId=" + projectId + 
          ", trackNumber=" + trackNumber + 
          ", trackTitle=" + trackTitle + 
          ", trackHours=" + trackHours + 
          ", trackMinutes=" + trackMinutes + 
          ", trackSeconds=" + trackSeconds + 
          ", trackDisc=" + trackDisc + 
          ", trackProducer=" + trackProducer + 
          ", trackComposer=" + trackComposer + 
          ", trackLyricist=" + trackLyricist + 
          ", locServername=" + locServername + 
          ", locSharename=" + locSharename + 
          ", locRootPath=" + locRootPath + 
          ", locPath=" + locPath + 
          ", locFilename=" + locFilename + 
          ", comments=" + comments + 
          ", genreId=" + genreId  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}