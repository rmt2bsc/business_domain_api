package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the vw_resource database table/view.
 *
 * @author auto generated.
 */
public class VwResource extends OrmBean {




	// Property name constants that belong to respective DataSource, VwResourceView

/** The property name constant equivalent to property, RsrcId, of respective DataSource view. */
  public static final String PROP_RSRCID = "RsrcId";
/** The property name constant equivalent to property, Name, of respective DataSource view. */
  public static final String PROP_NAME = "Name";
/** The property name constant equivalent to property, Url, of respective DataSource view. */
  public static final String PROP_URL = "Url";
/** The property name constant equivalent to property, Description, of respective DataSource view. */
  public static final String PROP_DESCRIPTION = "Description";
/** The property name constant equivalent to property, Secured, of respective DataSource view. */
  public static final String PROP_SECURED = "Secured";
/** The property name constant equivalent to property, Host, of respective DataSource view. */
  public static final String PROP_HOST = "Host";
/** The property name constant equivalent to property, RsrcTypeId, of respective DataSource view. */
  public static final String PROP_RSRCTYPEID = "RsrcTypeId";
/** The property name constant equivalent to property, TypeDescr, of respective DataSource view. */
  public static final String PROP_TYPEDESCR = "TypeDescr";
/** The property name constant equivalent to property, RsrcSubtypeId, of respective DataSource view. */
  public static final String PROP_RSRCSUBTYPEID = "RsrcSubtypeId";
/** The property name constant equivalent to property, SubtypeName, of respective DataSource view. */
  public static final String PROP_SUBTYPENAME = "SubtypeName";
/** The property name constant equivalent to property, SubtypeDesc, of respective DataSource view. */
  public static final String PROP_SUBTYPEDESC = "SubtypeDesc";



	/** The javabean property equivalent of database column vw_resource.rsrc_id */
  private int rsrcId;
/** The javabean property equivalent of database column vw_resource.name */
  private String name;
/** The javabean property equivalent of database column vw_resource.url */
  private String url;
/** The javabean property equivalent of database column vw_resource.description */
  private String description;
/** The javabean property equivalent of database column vw_resource.secured */
  private int secured;
/** The javabean property equivalent of database column vw_resource.host */
  private String host;
/** The javabean property equivalent of database column vw_resource.rsrc_type_id */
  private int rsrcTypeId;
/** The javabean property equivalent of database column vw_resource.type_descr */
  private String typeDescr;
/** The javabean property equivalent of database column vw_resource.rsrc_subtype_id */
  private int rsrcSubtypeId;
/** The javabean property equivalent of database column vw_resource.subtype_name */
  private String subtypeName;
/** The javabean property equivalent of database column vw_resource.subtype_desc */
  private String subtypeDesc;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwResource() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable rsrcId
 */
  public void setRsrcId(int value) {
    this.rsrcId = value;
  }
/**
 * Gets the value of member variable rsrcId
 */
  public int getRsrcId() {
    return this.rsrcId;
  }
/**
 * Sets the value of member variable name
 */
  public void setName(String value) {
    this.name = value;
  }
/**
 * Gets the value of member variable name
 */
  public String getName() {
    return this.name;
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
 * Sets the value of member variable description
 */
  public void setDescription(String value) {
    this.description = value;
  }
/**
 * Gets the value of member variable description
 */
  public String getDescription() {
    return this.description;
  }
/**
 * Sets the value of member variable secured
 */
  public void setSecured(int value) {
    this.secured = value;
  }
/**
 * Gets the value of member variable secured
 */
  public int getSecured() {
    return this.secured;
  }
/**
 * Sets the value of member variable host
 */
  public void setHost(String value) {
    this.host = value;
  }
/**
 * Gets the value of member variable host
 */
  public String getHost() {
    return this.host;
  }
/**
 * Sets the value of member variable rsrcTypeId
 */
  public void setRsrcTypeId(int value) {
    this.rsrcTypeId = value;
  }
/**
 * Gets the value of member variable rsrcTypeId
 */
  public int getRsrcTypeId() {
    return this.rsrcTypeId;
  }
/**
 * Sets the value of member variable typeDescr
 */
  public void setTypeDescr(String value) {
    this.typeDescr = value;
  }
/**
 * Gets the value of member variable typeDescr
 */
  public String getTypeDescr() {
    return this.typeDescr;
  }
/**
 * Sets the value of member variable rsrcSubtypeId
 */
  public void setRsrcSubtypeId(int value) {
    this.rsrcSubtypeId = value;
  }
/**
 * Gets the value of member variable rsrcSubtypeId
 */
  public int getRsrcSubtypeId() {
    return this.rsrcSubtypeId;
  }
/**
 * Sets the value of member variable subtypeName
 */
  public void setSubtypeName(String value) {
    this.subtypeName = value;
  }
/**
 * Gets the value of member variable subtypeName
 */
  public String getSubtypeName() {
    return this.subtypeName;
  }
/**
 * Sets the value of member variable subtypeDesc
 */
  public void setSubtypeDesc(String value) {
    this.subtypeDesc = value;
  }
/**
 * Gets the value of member variable subtypeDesc
 */
  public String getSubtypeDesc() {
    return this.subtypeDesc;
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
   final VwResource other = (VwResource) obj; 
   if (EqualityAssistant.notEqual(this.rsrcId, other.rsrcId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.name, other.name)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.url, other.url)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.description, other.description)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.secured, other.secured)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.host, other.host)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.rsrcTypeId, other.rsrcTypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.typeDescr, other.typeDescr)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.rsrcSubtypeId, other.rsrcSubtypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.subtypeName, other.subtypeName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.subtypeDesc, other.subtypeDesc)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.rsrcId),
               HashCodeAssistant.hashObject(this.name),
               HashCodeAssistant.hashObject(this.url),
               HashCodeAssistant.hashObject(this.description),
               HashCodeAssistant.hashObject(this.secured),
               HashCodeAssistant.hashObject(this.host),
               HashCodeAssistant.hashObject(this.rsrcTypeId),
               HashCodeAssistant.hashObject(this.typeDescr),
               HashCodeAssistant.hashObject(this.rsrcSubtypeId),
               HashCodeAssistant.hashObject(this.subtypeName),
               HashCodeAssistant.hashObject(this.subtypeDesc));
} 

@Override
public String toString() {
   return "VwResource [rsrcId=" + rsrcId + 
          ", name=" + name + 
          ", url=" + url + 
          ", description=" + description + 
          ", secured=" + secured + 
          ", host=" + host + 
          ", rsrcTypeId=" + rsrcTypeId + 
          ", typeDescr=" + typeDescr + 
          ", rsrcSubtypeId=" + rsrcSubtypeId + 
          ", subtypeName=" + subtypeName + 
          ", subtypeDesc=" + subtypeDesc  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}