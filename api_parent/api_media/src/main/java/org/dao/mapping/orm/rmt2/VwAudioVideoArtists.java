package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the vw_audio_video_artists database table/view.
 *
 * @author auto generated.
 */
public class VwAudioVideoArtists extends OrmBean {




	// Property name constants that belong to respective DataSource, VwAudioVideoArtistsView

/** The property name constant equivalent to property, PrimaryArtist, of respective DataSource view. */
  public static final String PROP_PRIMARYARTIST = "PrimaryArtist";
/** The property name constant equivalent to property, ProjectTypeId, of respective DataSource view. */
  public static final String PROP_PROJECTTYPEID = "ProjectTypeId";
/** The property name constant equivalent to property, ProjectTypeName, of respective DataSource view. */
  public static final String PROP_PROJECTTYPENAME = "ProjectTypeName";
/** The property name constant equivalent to property, Artist, of respective DataSource view. */
  public static final String PROP_ARTIST = "Artist";
/** The property name constant equivalent to property, ArtistId, of respective DataSource view. */
  public static final String PROP_ARTISTID = "ArtistId";
/** The property name constant equivalent to property, ProjectId, of respective DataSource view. */
  public static final String PROP_PROJECTID = "ProjectId";
/** The property name constant equivalent to property, ProjectTitle, of respective DataSource view. */
  public static final String PROP_PROJECTTITLE = "ProjectTitle";
/** The property name constant equivalent to property, ProjectComments, of respective DataSource view. */
  public static final String PROP_PROJECTCOMMENTS = "ProjectComments";
/** The property name constant equivalent to property, GenreId, of respective DataSource view. */
  public static final String PROP_GENREID = "GenreId";
/** The property name constant equivalent to property, ContentId, of respective DataSource view. */
  public static final String PROP_CONTENTID = "ContentId";
/** The property name constant equivalent to property, MediaTypeId, of respective DataSource view. */
  public static final String PROP_MEDIATYPEID = "MediaTypeId";
/** The property name constant equivalent to property, Year, of respective DataSource view. */
  public static final String PROP_YEAR = "Year";
/** The property name constant equivalent to property, MasterDupId, of respective DataSource view. */
  public static final String PROP_MASTERDUPID = "MasterDupId";
/** The property name constant equivalent to property, Ripped, of respective DataSource view. */
  public static final String PROP_RIPPED = "Ripped";
/** The property name constant equivalent to property, Cost, of respective DataSource view. */
  public static final String PROP_COST = "Cost";
/** The property name constant equivalent to property, ContentPath, of respective DataSource view. */
  public static final String PROP_CONTENTPATH = "ContentPath";
/** The property name constant equivalent to property, ContentFilename, of respective DataSource view. */
  public static final String PROP_CONTENTFILENAME = "ContentFilename";
/** The property name constant equivalent to property, ArtWorkPath, of respective DataSource view. */
  public static final String PROP_ARTWORKPATH = "ArtWorkPath";
/** The property name constant equivalent to property, ArtWorkFilename, of respective DataSource view. */
  public static final String PROP_ARTWORKFILENAME = "ArtWorkFilename";
/** The property name constant equivalent to property, TotalTime, of respective DataSource view. */
  public static final String PROP_TOTALTIME = "TotalTime";
/** The property name constant equivalent to property, Producer, of respective DataSource view. */
  public static final String PROP_PRODUCER = "Producer";
/** The property name constant equivalent to property, TrackId, of respective DataSource view. */
  public static final String PROP_TRACKID = "TrackId";
/** The property name constant equivalent to property, TrackDiscNumber, of respective DataSource view. */
  public static final String PROP_TRACKDISCNUMBER = "TrackDiscNumber";
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
/** The property name constant equivalent to property, TrackComments, of respective DataSource view. */
  public static final String PROP_TRACKCOMMENTS = "TrackComments";



	/** The javabean property equivalent of database column vw_audio_video_artists.primary_artist */
  private int primaryArtist;
/** The javabean property equivalent of database column vw_audio_video_artists.project_type_id */
  private int projectTypeId;
/** The javabean property equivalent of database column vw_audio_video_artists.project_type_name */
  private String projectTypeName;
/** The javabean property equivalent of database column vw_audio_video_artists.artist */
  private String artist;
/** The javabean property equivalent of database column vw_audio_video_artists.artist_id */
  private int artistId;
/** The javabean property equivalent of database column vw_audio_video_artists.project_id */
  private int projectId;
/** The javabean property equivalent of database column vw_audio_video_artists.project_title */
  private String projectTitle;
/** The javabean property equivalent of database column vw_audio_video_artists.project_comments */
  private String projectComments;
/** The javabean property equivalent of database column vw_audio_video_artists.genre_id */
  private int genreId;
/** The javabean property equivalent of database column vw_audio_video_artists.content_id */
  private int contentId;
/** The javabean property equivalent of database column vw_audio_video_artists.media_type_id */
  private int mediaTypeId;
/** The javabean property equivalent of database column vw_audio_video_artists.year */
  private int year;
/** The javabean property equivalent of database column vw_audio_video_artists.master_dup_id */
  private int masterDupId;
/** The javabean property equivalent of database column vw_audio_video_artists.ripped */
  private int ripped;
/** The javabean property equivalent of database column vw_audio_video_artists.cost */
  private double cost;
/** The javabean property equivalent of database column vw_audio_video_artists.content_path */
  private String contentPath;
/** The javabean property equivalent of database column vw_audio_video_artists.content_filename */
  private String contentFilename;
/** The javabean property equivalent of database column vw_audio_video_artists.art_work_path */
  private String artWorkPath;
/** The javabean property equivalent of database column vw_audio_video_artists.art_work_filename */
  private String artWorkFilename;
/** The javabean property equivalent of database column vw_audio_video_artists.total_time */
  private int totalTime;
/** The javabean property equivalent of database column vw_audio_video_artists.producer */
  private String producer;
/** The javabean property equivalent of database column vw_audio_video_artists.track_id */
  private int trackId;
/** The javabean property equivalent of database column vw_audio_video_artists.track_disc_number */
  private String trackDiscNumber;
/** The javabean property equivalent of database column vw_audio_video_artists.track_number */
  private int trackNumber;
/** The javabean property equivalent of database column vw_audio_video_artists.track_title */
  private String trackTitle;
/** The javabean property equivalent of database column vw_audio_video_artists.track_hours */
  private int trackHours;
/** The javabean property equivalent of database column vw_audio_video_artists.track_minutes */
  private int trackMinutes;
/** The javabean property equivalent of database column vw_audio_video_artists.track_seconds */
  private int trackSeconds;
/** The javabean property equivalent of database column vw_audio_video_artists.track_comments */
  private String trackComments;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwAudioVideoArtists() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable primaryArtist
 */
  public void setPrimaryArtist(int value) {
    this.primaryArtist = value;
  }
/**
 * Gets the value of member variable primaryArtist
 */
  public int getPrimaryArtist() {
    return this.primaryArtist;
  }
/**
 * Sets the value of member variable projectTypeId
 */
  public void setProjectTypeId(int value) {
    this.projectTypeId = value;
  }
/**
 * Gets the value of member variable projectTypeId
 */
  public int getProjectTypeId() {
    return this.projectTypeId;
  }
/**
 * Sets the value of member variable projectTypeName
 */
  public void setProjectTypeName(String value) {
    this.projectTypeName = value;
  }
/**
 * Gets the value of member variable projectTypeName
 */
  public String getProjectTypeName() {
    return this.projectTypeName;
  }
/**
 * Sets the value of member variable artist
 */
  public void setArtist(String value) {
    this.artist = value;
  }
/**
 * Gets the value of member variable artist
 */
  public String getArtist() {
    return this.artist;
  }
/**
 * Sets the value of member variable artistId
 */
  public void setArtistId(int value) {
    this.artistId = value;
  }
/**
 * Gets the value of member variable artistId
 */
  public int getArtistId() {
    return this.artistId;
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
 * Sets the value of member variable projectTitle
 */
  public void setProjectTitle(String value) {
    this.projectTitle = value;
  }
/**
 * Gets the value of member variable projectTitle
 */
  public String getProjectTitle() {
    return this.projectTitle;
  }
/**
 * Sets the value of member variable projectComments
 */
  public void setProjectComments(String value) {
    this.projectComments = value;
  }
/**
 * Gets the value of member variable projectComments
 */
  public String getProjectComments() {
    return this.projectComments;
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
/**
 * Sets the value of member variable contentId
 */
  public void setContentId(int value) {
    this.contentId = value;
  }
/**
 * Gets the value of member variable contentId
 */
  public int getContentId() {
    return this.contentId;
  }
/**
 * Sets the value of member variable mediaTypeId
 */
  public void setMediaTypeId(int value) {
    this.mediaTypeId = value;
  }
/**
 * Gets the value of member variable mediaTypeId
 */
  public int getMediaTypeId() {
    return this.mediaTypeId;
  }
/**
 * Sets the value of member variable year
 */
  public void setYear(int value) {
    this.year = value;
  }
/**
 * Gets the value of member variable year
 */
  public int getYear() {
    return this.year;
  }
/**
 * Sets the value of member variable masterDupId
 */
  public void setMasterDupId(int value) {
    this.masterDupId = value;
  }
/**
 * Gets the value of member variable masterDupId
 */
  public int getMasterDupId() {
    return this.masterDupId;
  }
/**
 * Sets the value of member variable ripped
 */
  public void setRipped(int value) {
    this.ripped = value;
  }
/**
 * Gets the value of member variable ripped
 */
  public int getRipped() {
    return this.ripped;
  }
/**
 * Sets the value of member variable cost
 */
  public void setCost(double value) {
    this.cost = value;
  }
/**
 * Gets the value of member variable cost
 */
  public double getCost() {
    return this.cost;
  }
/**
 * Sets the value of member variable contentPath
 */
  public void setContentPath(String value) {
    this.contentPath = value;
  }
/**
 * Gets the value of member variable contentPath
 */
  public String getContentPath() {
    return this.contentPath;
  }
/**
 * Sets the value of member variable contentFilename
 */
  public void setContentFilename(String value) {
    this.contentFilename = value;
  }
/**
 * Gets the value of member variable contentFilename
 */
  public String getContentFilename() {
    return this.contentFilename;
  }
/**
 * Sets the value of member variable artWorkPath
 */
  public void setArtWorkPath(String value) {
    this.artWorkPath = value;
  }
/**
 * Gets the value of member variable artWorkPath
 */
  public String getArtWorkPath() {
    return this.artWorkPath;
  }
/**
 * Sets the value of member variable artWorkFilename
 */
  public void setArtWorkFilename(String value) {
    this.artWorkFilename = value;
  }
/**
 * Gets the value of member variable artWorkFilename
 */
  public String getArtWorkFilename() {
    return this.artWorkFilename;
  }
/**
 * Sets the value of member variable totalTime
 */
  public void setTotalTime(int value) {
    this.totalTime = value;
  }
/**
 * Gets the value of member variable totalTime
 */
  public int getTotalTime() {
    return this.totalTime;
  }
/**
 * Sets the value of member variable producer
 */
  public void setProducer(String value) {
    this.producer = value;
  }
/**
 * Gets the value of member variable producer
 */
  public String getProducer() {
    return this.producer;
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
 * Sets the value of member variable trackDiscNumber
 */
  public void setTrackDiscNumber(String value) {
    this.trackDiscNumber = value;
  }
/**
 * Gets the value of member variable trackDiscNumber
 */
  public String getTrackDiscNumber() {
    return this.trackDiscNumber;
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
 * Sets the value of member variable trackComments
 */
  public void setTrackComments(String value) {
    this.trackComments = value;
  }
/**
 * Gets the value of member variable trackComments
 */
  public String getTrackComments() {
    return this.trackComments;
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
   final VwAudioVideoArtists other = (VwAudioVideoArtists) obj; 
   if (EqualityAssistant.notEqual(this.primaryArtist, other.primaryArtist)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.projectTypeId, other.projectTypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.projectTypeName, other.projectTypeName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.artist, other.artist)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.artistId, other.artistId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.projectId, other.projectId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.projectTitle, other.projectTitle)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.projectComments, other.projectComments)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.genreId, other.genreId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.contentId, other.contentId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.mediaTypeId, other.mediaTypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.year, other.year)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.masterDupId, other.masterDupId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.ripped, other.ripped)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.cost, other.cost)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.contentPath, other.contentPath)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.contentFilename, other.contentFilename)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.artWorkPath, other.artWorkPath)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.artWorkFilename, other.artWorkFilename)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.totalTime, other.totalTime)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.producer, other.producer)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.trackId, other.trackId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.trackDiscNumber, other.trackDiscNumber)) {
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
   if (EqualityAssistant.notEqual(this.trackComments, other.trackComments)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.primaryArtist),
               HashCodeAssistant.hashObject(this.projectTypeId),
               HashCodeAssistant.hashObject(this.projectTypeName),
               HashCodeAssistant.hashObject(this.artist),
               HashCodeAssistant.hashObject(this.artistId),
               HashCodeAssistant.hashObject(this.projectId),
               HashCodeAssistant.hashObject(this.projectTitle),
               HashCodeAssistant.hashObject(this.projectComments),
               HashCodeAssistant.hashObject(this.genreId),
               HashCodeAssistant.hashObject(this.contentId),
               HashCodeAssistant.hashObject(this.mediaTypeId),
               HashCodeAssistant.hashObject(this.year),
               HashCodeAssistant.hashObject(this.masterDupId),
               HashCodeAssistant.hashObject(this.ripped),
               HashCodeAssistant.hashObject(this.cost),
               HashCodeAssistant.hashObject(this.contentPath),
               HashCodeAssistant.hashObject(this.contentFilename),
               HashCodeAssistant.hashObject(this.artWorkPath),
               HashCodeAssistant.hashObject(this.artWorkFilename),
               HashCodeAssistant.hashObject(this.totalTime),
               HashCodeAssistant.hashObject(this.producer),
               HashCodeAssistant.hashObject(this.trackId),
               HashCodeAssistant.hashObject(this.trackDiscNumber),
               HashCodeAssistant.hashObject(this.trackNumber),
               HashCodeAssistant.hashObject(this.trackTitle),
               HashCodeAssistant.hashObject(this.trackHours),
               HashCodeAssistant.hashObject(this.trackMinutes),
               HashCodeAssistant.hashObject(this.trackSeconds),
               HashCodeAssistant.hashObject(this.trackComments));
} 

@Override
public String toString() {
   return "VwAudioVideoArtists [primaryArtist=" + primaryArtist + 
          ", projectTypeId=" + projectTypeId + 
          ", projectTypeName=" + projectTypeName + 
          ", artist=" + artist + 
          ", artistId=" + artistId + 
          ", projectId=" + projectId + 
          ", projectTitle=" + projectTitle + 
          ", projectComments=" + projectComments + 
          ", genreId=" + genreId + 
          ", contentId=" + contentId + 
          ", mediaTypeId=" + mediaTypeId + 
          ", year=" + year + 
          ", masterDupId=" + masterDupId + 
          ", ripped=" + ripped + 
          ", cost=" + cost + 
          ", contentPath=" + contentPath + 
          ", contentFilename=" + contentFilename + 
          ", artWorkPath=" + artWorkPath + 
          ", artWorkFilename=" + artWorkFilename + 
          ", totalTime=" + totalTime + 
          ", producer=" + producer + 
          ", trackId=" + trackId + 
          ", trackDiscNumber=" + trackDiscNumber + 
          ", trackNumber=" + trackNumber + 
          ", trackTitle=" + trackTitle + 
          ", trackHours=" + trackHours + 
          ", trackMinutes=" + trackMinutes + 
          ", trackSeconds=" + trackSeconds + 
          ", trackComments=" + trackComments  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}