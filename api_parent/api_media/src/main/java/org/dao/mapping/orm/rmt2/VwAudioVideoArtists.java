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
/** The property name constant equivalent to property, ProjectTitle, of respective DataSource view. */
  public static final String PROP_PROJECTTITLE = "ProjectTitle";
/** The property name constant equivalent to property, ProjectId, of respective DataSource view. */
  public static final String PROP_PROJECTID = "ProjectId";
/** The property name constant equivalent to property, ProjectComments, of respective DataSource view. */
  public static final String PROP_PROJECTCOMMENTS = "ProjectComments";
/** The property name constant equivalent to property, TrackId, of respective DataSource view. */
  public static final String PROP_TRACKID = "TrackId";
/** The property name constant equivalent to property, TrackTitle, of respective DataSource view. */
  public static final String PROP_TRACKTITLE = "TrackTitle";
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
/** The javabean property equivalent of database column vw_audio_video_artists.project_title */
  private String projectTitle;
/** The javabean property equivalent of database column vw_audio_video_artists.project_id */
  private int projectId;
/** The javabean property equivalent of database column vw_audio_video_artists.project_comments */
  private String projectComments;
/** The javabean property equivalent of database column vw_audio_video_artists.track_id */
  private int trackId;
/** The javabean property equivalent of database column vw_audio_video_artists.track_title */
  private String trackTitle;
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
   if (EqualityAssistant.notEqual(this.projectTitle, other.projectTitle)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.projectId, other.projectId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.projectComments, other.projectComments)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.trackId, other.trackId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.trackTitle, other.trackTitle)) {
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
               HashCodeAssistant.hashObject(this.projectTitle),
               HashCodeAssistant.hashObject(this.projectId),
               HashCodeAssistant.hashObject(this.projectComments),
               HashCodeAssistant.hashObject(this.trackId),
               HashCodeAssistant.hashObject(this.trackTitle),
               HashCodeAssistant.hashObject(this.trackComments));
} 

@Override
public String toString() {
   return "VwAudioVideoArtists [primaryArtist=" + primaryArtist + 
          ", projectTypeId=" + projectTypeId + 
          ", projectTypeName=" + projectTypeName + 
          ", artist=" + artist + 
          ", artistId=" + artistId + 
          ", projectTitle=" + projectTitle + 
          ", projectId=" + projectId + 
          ", projectComments=" + projectComments + 
          ", trackId=" + trackId + 
          ", trackTitle=" + trackTitle + 
          ", trackComments=" + trackComments  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}