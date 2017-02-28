package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the vw_photo database table/view.
 *
 * @author auto generated.
 */
public class VwPhoto extends OrmBean {




	// Property name constants that belong to respective DataSource, VwPhotoView

/** The property name constant equivalent to property, AlbumId, of respective DataSource view. */
  public static final String PROP_ALBUMID = "AlbumId";
/** The property name constant equivalent to property, AlbumName, of respective DataSource view. */
  public static final String PROP_ALBUMNAME = "AlbumName";
/** The property name constant equivalent to property, AlbumDate, of respective DataSource view. */
  public static final String PROP_ALBUMDATE = "AlbumDate";
/** The property name constant equivalent to property, EventId, of respective DataSource view. */
  public static final String PROP_EVENTID = "EventId";
/** The property name constant equivalent to property, EventName, of respective DataSource view. */
  public static final String PROP_EVENTNAME = "EventName";
/** The property name constant equivalent to property, ImageId, of respective DataSource view. */
  public static final String PROP_IMAGEID = "ImageId";
/** The property name constant equivalent to property, DirPath, of respective DataSource view. */
  public static final String PROP_DIRPATH = "DirPath";
/** The property name constant equivalent to property, FileName, of respective DataSource view. */
  public static final String PROP_FILENAME = "FileName";
/** The property name constant equivalent to property, FileSize, of respective DataSource view. */
  public static final String PROP_FILESIZE = "FileSize";
/** The property name constant equivalent to property, FileExt, of respective DataSource view. */
  public static final String PROP_FILEEXT = "FileExt";
/** The property name constant equivalent to property, MimeTypeId, of respective DataSource view. */
  public static final String PROP_MIMETYPEID = "MimeTypeId";
/** The property name constant equivalent to property, MimeTypeFileExt, of respective DataSource view. */
  public static final String PROP_MIMETYPEFILEEXT = "MimeTypeFileExt";
/** The property name constant equivalent to property, MediaType, of respective DataSource view. */
  public static final String PROP_MEDIATYPE = "MediaType";



	/** The javabean property equivalent of database column vw_photo.album_id */
  private int albumId;
/** The javabean property equivalent of database column vw_photo.album_name */
  private String albumName;
/** The javabean property equivalent of database column vw_photo.album_date */
  private java.util.Date albumDate;
/** The javabean property equivalent of database column vw_photo.event_id */
  private int eventId;
/** The javabean property equivalent of database column vw_photo.event_name */
  private String eventName;
/** The javabean property equivalent of database column vw_photo.image_id */
  private int imageId;
/** The javabean property equivalent of database column vw_photo.dir_path */
  private String dirPath;
/** The javabean property equivalent of database column vw_photo.file_name */
  private String fileName;
/** The javabean property equivalent of database column vw_photo.file_size */
  private int fileSize;
/** The javabean property equivalent of database column vw_photo.file_ext */
  private String fileExt;
/** The javabean property equivalent of database column vw_photo.mime_type_id */
  private int mimeTypeId;
/** The javabean property equivalent of database column vw_photo.mime_type_file_ext */
  private String mimeTypeFileExt;
/** The javabean property equivalent of database column vw_photo.media_type */
  private String mediaType;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwPhoto() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable albumId
 */
  public void setAlbumId(int value) {
    this.albumId = value;
  }
/**
 * Gets the value of member variable albumId
 */
  public int getAlbumId() {
    return this.albumId;
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
 * Sets the value of member variable mimeTypeFileExt
 */
  public void setMimeTypeFileExt(String value) {
    this.mimeTypeFileExt = value;
  }
/**
 * Gets the value of member variable mimeTypeFileExt
 */
  public String getMimeTypeFileExt() {
    return this.mimeTypeFileExt;
  }
/**
 * Sets the value of member variable mediaType
 */
  public void setMediaType(String value) {
    this.mediaType = value;
  }
/**
 * Gets the value of member variable mediaType
 */
  public String getMediaType() {
    return this.mediaType;
  }
/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}