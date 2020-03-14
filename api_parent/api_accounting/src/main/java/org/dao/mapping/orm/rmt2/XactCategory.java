package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;

import com.api.persistence.db.orm.OrmBean;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.SystemException;


/**
 * Peer object that maps to the xact_category database table/view.
 *
 * @author auto generated.
 */
public class XactCategory extends OrmBean {




	// Property name constants that belong to respective DataSource, XactCategoryView

/** The property name constant equivalent to property, XactCatgId, of respective DataSource view. */
  public static final String PROP_XACTCATGID = "XactCatgId";
/** The property name constant equivalent to property, Description, of respective DataSource view. */
  public static final String PROP_DESCRIPTION = "Description";
/** The property name constant equivalent to property, Code, of respective DataSource view. */
  public static final String PROP_CODE = "Code";



	/** The javabean property equivalent of database column xact_category.xact_catg_id */
  private int xactCatgId;
/** The javabean property equivalent of database column xact_category.description */
  private String description;
/** The javabean property equivalent of database column xact_category.code */
  private String code;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public XactCategory() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable xactCatgId
 */
  public void setXactCatgId(int value) {
    this.xactCatgId = value;
  }
/**
 * Gets the value of member variable xactCatgId
 */
  public int getXactCatgId() {
    return this.xactCatgId;
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
 * Sets the value of member variable code
 */
  public void setCode(String value) {
    this.code = value;
  }
/**
 * Gets the value of member variable code
 */
  public String getCode() {
    return this.code;
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
   final XactCategory other = (XactCategory) obj; 
   if (EqualityAssistant.notEqual(this.xactCatgId, other.xactCatgId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.description, other.description)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.code, other.code)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.xactCatgId),
               HashCodeAssistant.hashObject(this.description),
               HashCodeAssistant.hashObject(this.code));
} 

@Override
public String toString() {
   return "XactCategory [xactCatgId=" + xactCatgId + 
          ", description=" + description + 
          ", code=" + code  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}