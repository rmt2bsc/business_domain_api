package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;

import com.api.persistence.db.orm.OrmBean;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.SystemException;


/**
 * Peer object that maps to the vw_resource_type database table/view.
 *
 * @author auto generated.
 */
public class VwResourceType extends OrmBean {




	// Property name constants that belong to respective DataSource, VwResourceTypeView

/** The property name constant equivalent to property, ResrcTypeId, of respective DataSource view. */
  public static final String PROP_RESRCTYPEID = "ResrcTypeId";
/** The property name constant equivalent to property, ResrcTypeName, of respective DataSource view. */
  public static final String PROP_RESRCTYPENAME = "ResrcTypeName";
/** The property name constant equivalent to property, ResrcSubtypeId, of respective DataSource view. */
  public static final String PROP_RESRCSUBTYPEID = "ResrcSubtypeId";
/** The property name constant equivalent to property, ResrcSubtypeName, of respective DataSource view. */
  public static final String PROP_RESRCSUBTYPENAME = "ResrcSubtypeName";
/** The property name constant equivalent to property, ResrcSubtypeDesc, of respective DataSource view. */
  public static final String PROP_RESRCSUBTYPEDESC = "ResrcSubtypeDesc";



	/** The javabean property equivalent of database column vw_resource_type.resrc_type_id */
  private int resrcTypeId;
/** The javabean property equivalent of database column vw_resource_type.resrc_type_name */
  private String resrcTypeName;
/** The javabean property equivalent of database column vw_resource_type.resrc_subtype_id */
  private int resrcSubtypeId;
/** The javabean property equivalent of database column vw_resource_type.resrc_subtype_name */
  private String resrcSubtypeName;
/** The javabean property equivalent of database column vw_resource_type.resrc_subtype_desc */
  private String resrcSubtypeDesc;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwResourceType() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable resrcTypeId
 */
  public void setResrcTypeId(int value) {
    this.resrcTypeId = value;
  }
/**
 * Gets the value of member variable resrcTypeId
 */
  public int getResrcTypeId() {
    return this.resrcTypeId;
  }
/**
 * Sets the value of member variable resrcTypeName
 */
  public void setResrcTypeName(String value) {
    this.resrcTypeName = value;
  }
/**
 * Gets the value of member variable resrcTypeName
 */
  public String getResrcTypeName() {
    return this.resrcTypeName;
  }
/**
 * Sets the value of member variable resrcSubtypeId
 */
  public void setResrcSubtypeId(int value) {
    this.resrcSubtypeId = value;
  }
/**
 * Gets the value of member variable resrcSubtypeId
 */
  public int getResrcSubtypeId() {
    return this.resrcSubtypeId;
  }
/**
 * Sets the value of member variable resrcSubtypeName
 */
  public void setResrcSubtypeName(String value) {
    this.resrcSubtypeName = value;
  }
/**
 * Gets the value of member variable resrcSubtypeName
 */
  public String getResrcSubtypeName() {
    return this.resrcSubtypeName;
  }
/**
 * Sets the value of member variable resrcSubtypeDesc
 */
  public void setResrcSubtypeDesc(String value) {
    this.resrcSubtypeDesc = value;
  }
/**
 * Gets the value of member variable resrcSubtypeDesc
 */
  public String getResrcSubtypeDesc() {
    return this.resrcSubtypeDesc;
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
   final VwResourceType other = (VwResourceType) obj; 
   if (EqualityAssistant.notEqual(this.resrcTypeId, other.resrcTypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.resrcTypeName, other.resrcTypeName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.resrcSubtypeId, other.resrcSubtypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.resrcSubtypeName, other.resrcSubtypeName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.resrcSubtypeDesc, other.resrcSubtypeDesc)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.resrcTypeId),
               HashCodeAssistant.hashObject(this.resrcTypeName),
               HashCodeAssistant.hashObject(this.resrcSubtypeId),
               HashCodeAssistant.hashObject(this.resrcSubtypeName),
               HashCodeAssistant.hashObject(this.resrcSubtypeDesc));
} 

@Override
public String toString() {
   return "VwResourceType [resrcTypeId=" + resrcTypeId + 
          ", resrcTypeName=" + resrcTypeName + 
          ", resrcSubtypeId=" + resrcSubtypeId + 
          ", resrcSubtypeName=" + resrcSubtypeName + 
          ", resrcSubtypeDesc=" + resrcSubtypeDesc  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}