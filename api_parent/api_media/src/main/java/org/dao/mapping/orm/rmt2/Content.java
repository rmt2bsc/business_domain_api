package org.dao.mapping.orm.rmt2;


import com.SystemException;
import com.api.persistence.db.orm.OrmBean;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;


/**
 * Peer object that maps to the content database table/view.
 *
 * @author auto generated.
 */
public class Content extends OrmBean {




	// Property name constants that belong to respective DataSource, ContentView

/** The property name constant equivalent to property, ContentId, of respective DataSource view. */
  public static final String PROP_CONTENTID = "ContentId";
/** The property name constant equivalent to property, MimeTypeId, of respective DataSource view. */
  public static final String PROP_MIMETYPEID = "MimeTypeId";
/** The property name constant equivalent to property, ImageData, of respective DataSource view. */
  public static final String PROP_IMAGEDATA = "ImageData";
/** The property name constant equivalent to property, TextData, of respective DataSource view. */
  public static final String PROP_TEXTDATA = "TextData";
/** The property name constant equivalent to property, AppCode, of respective DataSource view. */
  public static final String PROP_APPCODE = "AppCode";
/** The property name constant equivalent to property, ModuleCode, of respective DataSource view. */
  public static final String PROP_MODULECODE = "ModuleCode";
/** The property name constant equivalent to property, Filepath, of respective DataSource view. */
  public static final String PROP_FILEPATH = "Filepath";
/** The property name constant equivalent to property, Filename, of respective DataSource view. */
  public static final String PROP_FILENAME = "Filename";
/** The property name constant equivalent to property, Size, of respective DataSource view. */
  public static final String PROP_SIZE = "Size";
/** The property name constant equivalent to property, DateCreated, of respective DataSource view. */
  public static final String PROP_DATECREATED = "DateCreated";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";
/** The property name constant equivalent to property, ProjectId, of respective DataSource view. */
  public static final String PROP_PROJECTID = "ProjectId";



	/** The javabean property equivalent of database column content.content_id */
  private int contentId;
/** The javabean property equivalent of database column content.mime_type_id */
  private int mimeTypeId;
/** The javabean property equivalent of database column content.image_data */
  private byte[] imageData;
/** The javabean property equivalent of database column content.text_data */
  private String textData;
/** The javabean property equivalent of database column content.app_code */
  private String appCode;
/** The javabean property equivalent of database column content.module_code */
  private String moduleCode;
/** The javabean property equivalent of database column content.filepath */
  private String filepath;
/** The javabean property equivalent of database column content.filename */
  private String filename;
/** The javabean property equivalent of database column content.size */
  private int size;
/** The javabean property equivalent of database column content.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column content.user_id */
  private String userId;
/** The javabean property equivalent of database column content.project_id */
  private int projectId;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public Content() throws SystemException {
	super();
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
 * Sets the value of member variable imageData
 */
  public void setImageData(byte[] value) {
    this.imageData = value;
  }
/**
 * Gets the value of member variable imageData
 */
  public byte[] getImageData() {
    return this.imageData;
  }
/**
 * Sets the value of member variable textData
 */
  public void setTextData(String value) {
    this.textData = value;
  }
/**
 * Gets the value of member variable textData
 */
  public String getTextData() {
    return this.textData;
  }
/**
 * Sets the value of member variable appCode
 */
  public void setAppCode(String value) {
    this.appCode = value;
  }
/**
 * Gets the value of member variable appCode
 */
  public String getAppCode() {
    return this.appCode;
  }
/**
 * Sets the value of member variable moduleCode
 */
  public void setModuleCode(String value) {
    this.moduleCode = value;
  }
/**
 * Gets the value of member variable moduleCode
 */
  public String getModuleCode() {
    return this.moduleCode;
  }
/**
 * Sets the value of member variable filepath
 */
  public void setFilepath(String value) {
    this.filepath = value;
  }
/**
 * Gets the value of member variable filepath
 */
  public String getFilepath() {
    return this.filepath;
  }
/**
 * Sets the value of member variable filename
 */
  public void setFilename(String value) {
    this.filename = value;
  }
/**
 * Gets the value of member variable filename
 */
  public String getFilename() {
    return this.filename;
  }
/**
 * Sets the value of member variable size
 */
  public void setSize(int value) {
    this.size = value;
  }
/**
 * Gets the value of member variable size
 */
  public int getSize() {
    return this.size;
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
   final Content other = (Content) obj; 
   if (EqualityAssistant.notEqual(this.contentId, other.contentId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.mimeTypeId, other.mimeTypeId)) {
      return false;
   }
   if (EqualityAssistant.bytesNotEqual(this.imageData, other.imageData)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.textData, other.textData)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.appCode, other.appCode)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.moduleCode, other.moduleCode)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.filepath, other.filepath)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.filename, other.filename)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.size, other.size)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.projectId, other.projectId)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.contentId),
               HashCodeAssistant.hashObject(this.mimeTypeId),
               HashCodeAssistant.hashByteArray(this.imageData),
               HashCodeAssistant.hashObject(this.textData),
               HashCodeAssistant.hashObject(this.appCode),
               HashCodeAssistant.hashObject(this.moduleCode),
               HashCodeAssistant.hashObject(this.filepath),
               HashCodeAssistant.hashObject(this.filename),
               HashCodeAssistant.hashObject(this.size),
               HashCodeAssistant.hashObject(this.projectId));
} 

@Override
public String toString() {
   return "Content [contentId=" + contentId + 
          ", mimeTypeId=" + mimeTypeId + 
          ", imageData=" + imageData + 
          ", textData=" + textData + 
          ", appCode=" + appCode + 
          ", moduleCode=" + moduleCode + 
          ", filepath=" + filepath + 
          ", filename=" + filename + 
          ", size=" + size + 
          ", projectId=" + projectId  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}