package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the av_project database table/view.
 *
 * @author auto generated.
 */
public class AvProject extends OrmBean {




	// Property name constants that belong to respective DataSource, AvProjectView

/** The property name constant equivalent to property, ProjectId, of respective DataSource view. */
  public static final String PROP_PROJECTID = "ProjectId";
/** The property name constant equivalent to property, ArtistId, of respective DataSource view. */
  public static final String PROP_ARTISTID = "ArtistId";
/** The property name constant equivalent to property, ProjectTypeId, of respective DataSource view. */
  public static final String PROP_PROJECTTYPEID = "ProjectTypeId";
/** The property name constant equivalent to property, GenreId, of respective DataSource view. */
  public static final String PROP_GENREID = "GenreId";
/** The property name constant equivalent to property, ContentId, of respective DataSource view. */
  public static final String PROP_CONTENTID = "ContentId";
/** The property name constant equivalent to property, MediaTypeId, of respective DataSource view. */
  public static final String PROP_MEDIATYPEID = "MediaTypeId";
/** The property name constant equivalent to property, Title, of respective DataSource view. */
  public static final String PROP_TITLE = "Title";
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
/** The property name constant equivalent to property, ProjectComments, of respective DataSource view. */
  public static final String PROP_PROJECTCOMMENTS = "ProjectComments";
/** The property name constant equivalent to property, DateCreated, of respective DataSource view. */
  public static final String PROP_DATECREATED = "DateCreated";
/** The property name constant equivalent to property, DateUpdated, of respective DataSource view. */
  public static final String PROP_DATEUPDATED = "DateUpdated";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";
/** The property name constant equivalent to property, TotalTime, of respective DataSource view. */
  public static final String PROP_TOTALTIME = "TotalTime";
/** The property name constant equivalent to property, Producer, of respective DataSource view. */
  public static final String PROP_PRODUCER = "Producer";



	/** The javabean property equivalent of database column av_project.project_id */
  private int projectId;
/** The javabean property equivalent of database column av_project.artist_id */
  private int artistId;
/** The javabean property equivalent of database column av_project.project_type_id */
  private int projectTypeId;
/** The javabean property equivalent of database column av_project.genre_id */
  private int genreId;
/** The javabean property equivalent of database column av_project.content_id */
  private int contentId;
/** The javabean property equivalent of database column av_project.media_type_id */
  private int mediaTypeId;
/** The javabean property equivalent of database column av_project.title */
  private String title;
/** The javabean property equivalent of database column av_project.year */
  private int year;
/** The javabean property equivalent of database column av_project.master_dup_id */
  private int masterDupId;
/** The javabean property equivalent of database column av_project.ripped */
  private int ripped;
/** The javabean property equivalent of database column av_project.cost */
  private double cost;
/** The javabean property equivalent of database column av_project.content_path */
  private String contentPath;
/** The javabean property equivalent of database column av_project.content_filename */
  private String contentFilename;
/** The javabean property equivalent of database column av_project.art_work_path */
  private String artWorkPath;
/** The javabean property equivalent of database column av_project.art_work_filename */
  private String artWorkFilename;
/** The javabean property equivalent of database column av_project.project_comments */
  private String projectComments;
/** The javabean property equivalent of database column av_project.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column av_project.date_updated */
  private java.util.Date dateUpdated;
/** The javabean property equivalent of database column av_project.user_id */
  private String userId;
/** The javabean property equivalent of database column av_project.total_time */
  private int totalTime;
/** The javabean property equivalent of database column av_project.producer */
  private String producer;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public AvProject() throws SystemException {
	super();
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
 * Sets the value of member variable title
 */
  public void setTitle(String value) {
    this.title = value;
  }
/**
 * Gets the value of member variable title
 */
  public String getTitle() {
    return this.title;
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
   final AvProject other = (AvProject) obj; 
   if (EqualityAssistant.notEqual(this.projectId, other.projectId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.artistId, other.artistId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.projectTypeId, other.projectTypeId)) {
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
   if (EqualityAssistant.notEqual(this.title, other.title)) {
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
   if (EqualityAssistant.notEqual(this.projectComments, other.projectComments)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.totalTime, other.totalTime)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.producer, other.producer)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.projectId),
               HashCodeAssistant.hashObject(this.artistId),
               HashCodeAssistant.hashObject(this.projectTypeId),
               HashCodeAssistant.hashObject(this.genreId),
               HashCodeAssistant.hashObject(this.contentId),
               HashCodeAssistant.hashObject(this.mediaTypeId),
               HashCodeAssistant.hashObject(this.title),
               HashCodeAssistant.hashObject(this.year),
               HashCodeAssistant.hashObject(this.masterDupId),
               HashCodeAssistant.hashObject(this.ripped),
               HashCodeAssistant.hashObject(this.cost),
               HashCodeAssistant.hashObject(this.contentPath),
               HashCodeAssistant.hashObject(this.contentFilename),
               HashCodeAssistant.hashObject(this.artWorkPath),
               HashCodeAssistant.hashObject(this.artWorkFilename),
               HashCodeAssistant.hashObject(this.projectComments),
               HashCodeAssistant.hashObject(this.totalTime),
               HashCodeAssistant.hashObject(this.producer));
} 

@Override
public String toString() {
   return "AvProject [projectId=" + projectId + 
          ", artistId=" + artistId + 
          ", projectTypeId=" + projectTypeId + 
          ", genreId=" + genreId + 
          ", contentId=" + contentId + 
          ", mediaTypeId=" + mediaTypeId + 
          ", title=" + title + 
          ", year=" + year + 
          ", masterDupId=" + masterDupId + 
          ", ripped=" + ripped + 
          ", cost=" + cost + 
          ", contentPath=" + contentPath + 
          ", contentFilename=" + contentFilename + 
          ", artWorkPath=" + artWorkPath + 
          ", artWorkFilename=" + artWorkFilename + 
          ", projectComments=" + projectComments + 
          ", totalTime=" + totalTime + 
          ", producer=" + producer  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}