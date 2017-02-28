package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the photo_image database table/view.
 *
 * @author auto generated.
 */
public class PhotoImage extends OrmBean {




	// Property name constants that belong to respective DataSource, PhotoImageView

/** The property name constant equivalent to property, ImageId, of respective DataSource view. */
  public static final String PROP_IMAGEID = "ImageId";
/** The property name constant equivalent to property, EventId, of respective DataSource view. */
  public static final String PROP_EVENTID = "EventId";
/** The property name constant equivalent to property, MimeTypeId, of respective DataSource view. */
  public static final String PROP_MIMETYPEID = "MimeTypeId";
/** The property name constant equivalent to property, DirPath, of respective DataSource view. */
  public static final String PROP_DIRPATH = "DirPath";
/** The property name constant equivalent to property, FileName, of respective DataSource view. */
  public static final String PROP_FILENAME = "FileName";
/** The property name constant equivalent to property, FileSize, of respective DataSource view. */
  public static final String PROP_FILESIZE = "FileSize";
/** The property name constant equivalent to property, FileExt, of respective DataSource view. */
  public static final String PROP_FILEEXT = "FileExt";
/** The property name constant equivalent to property, Url, of respective DataSource view. */
  public static final String PROP_URL = "Url";
/** The property name constant equivalent to property, DateCreated, of respective DataSource view. */
  public static final String PROP_DATECREATED = "DateCreated";
/** The property name constant equivalent to property, DateUpdated, of respective DataSource view. */
  public static final String PROP_DATEUPDATED = "DateUpdated";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";



	/** The javabean property equivalent of database column photo_image.image_id */
  private int imageId;
/** The javabean property equivalent of database column photo_image.event_id */
  private int eventId;
/** The javabean property equivalent of database column photo_image.mime_type_id */
  private int mimeTypeId;
/** The javabean property equivalent of database column photo_image.dir_path */
  private String dirPath;
/** The javabean property equivalent of database column photo_image.file_name */
  private String fileName;
/** The javabean property equivalent of database column photo_image.file_size */
  private int fileSize;
/** The javabean property equivalent of database column photo_image.file_ext */
  private String fileExt;
/** The javabean property equivalent of database column photo_image.url */
  private String url;
/** The javabean property equivalent of database column photo_image.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column photo_image.date_updated */
  private java.util.Date dateUpdated;
/** The javabean property equivalent of database column photo_image.user_id */
  private String userId;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public PhotoImage() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable imageId
 */
  public void setImageId(int value) {
    this.imageId = value;
  }
/**
 * Gets the value of member variable imageId
 */
  public int getImageId() {
    return this.imageId;
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
 * Sets the value of member variable mimeTypeId
 */
  public void setMimeTypeId(int value) {
    this.mimeTypeId = value;
  }
/**
 * Gets the value of member variable mimeTypeId
 */
  public int getMimeTypeId() {
    return this.mimeTypeId;
  }
/**
 * Sets the value of member variable dirPath
 */
  public void setDirPath(String value) {
    this.dirPath = value;
  }
/**
 * Gets the value of member variable dirPath
 */
  public String getDirPath() {
    return this.dirPath;
  }
/**
 * Sets the value of member variable fileName
 */
  public void setFileName(String value) {
    this.fileName = value;
  }
/**
 * Gets the value of member variable fileName
 */
  public String getFileName() {
    return this.fileName;
  }
/**
 * Sets the value of member variable fileSize
 */
  public void setFileSize(int value) {
    this.fileSize = value;
  }
/**
 * Gets the value of member variable fileSize
 */
  public int getFileSize() {
    return this.fileSize;
  }
/**
 * Sets the value of member variable fileExt
 */
  public void setFileExt(String value) {
    this.fileExt = value;
  }
/**
 * Gets the value of member variable fileExt
 */
  public String getFileExt() {
    return this.fileExt;
  }
/**
 * Sets the value of member variable url
 */
  public void setUrl(String value) {
    this.url = value;
  }
/**
 * Gets the value of member variable url
 */
  public String getUrl() {
    return this.url;
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
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}