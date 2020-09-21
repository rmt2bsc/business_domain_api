package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the mime_types database table/view.
 *
 * @author auto generated.
 */
public class MimeTypes extends OrmBean {




	// Property name constants that belong to respective DataSource, MimeTypesView

/** The property name constant equivalent to property, MimeTypeId, of respective DataSource view. */
  public static final String PROP_MIMETYPEID = "MimeTypeId";
/** The property name constant equivalent to property, FileExt, of respective DataSource view. */
  public static final String PROP_FILEEXT = "FileExt";
/** The property name constant equivalent to property, MediaType, of respective DataSource view. */
  public static final String PROP_MEDIATYPE = "MediaType";



	/** The javabean property equivalent of database column mime_types.mime_type_id */
  private int mimeTypeId;
/** The javabean property equivalent of database column mime_types.file_ext */
  private String fileExt;
/** The javabean property equivalent of database column mime_types.media_type */
  private String mediaType;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public MimeTypes() throws SystemException {
	super();
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
   final MimeTypes other = (MimeTypes) obj; 
   if (EqualityAssistant.notEqual(this.mimeTypeId, other.mimeTypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.fileExt, other.fileExt)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.mediaType, other.mediaType)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.mimeTypeId),
               HashCodeAssistant.hashObject(this.fileExt),
               HashCodeAssistant.hashObject(this.mediaType));
} 

@Override
public String toString() {
   return "MimeTypes [mimeTypeId=" + mimeTypeId + 
          ", fileExt=" + fileExt + 
          ", mediaType=" + mediaType  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}