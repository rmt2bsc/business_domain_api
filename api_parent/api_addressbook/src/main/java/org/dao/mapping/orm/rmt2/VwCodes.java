package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;

import com.api.persistence.db.orm.OrmBean;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.SystemException;


/**
 * Peer object that maps to the vw_codes database table/view.
 *
 * @author auto generated.
 */
public class VwCodes extends OrmBean {




	// Property name constants that belong to respective DataSource, VwCodesView

/** The property name constant equivalent to property, GroupId, of respective DataSource view. */
  public static final String PROP_GROUPID = "GroupId";
/** The property name constant equivalent to property, GroupDesc, of respective DataSource view. */
  public static final String PROP_GROUPDESC = "GroupDesc";
/** The property name constant equivalent to property, CodeId, of respective DataSource view. */
  public static final String PROP_CODEID = "CodeId";
/** The property name constant equivalent to property, CodeShortdesc, of respective DataSource view. */
  public static final String PROP_CODESHORTDESC = "CodeShortdesc";
/** The property name constant equivalent to property, CodeLongdesc, of respective DataSource view. */
  public static final String PROP_CODELONGDESC = "CodeLongdesc";



	/** The javabean property equivalent of database column vw_codes.group_id */
  private int groupId;
/** The javabean property equivalent of database column vw_codes.group_desc */
  private String groupDesc;
/** The javabean property equivalent of database column vw_codes.code_id */
  private int codeId;
/** The javabean property equivalent of database column vw_codes.code_shortdesc */
  private String codeShortdesc;
/** The javabean property equivalent of database column vw_codes.code_longdesc */
  private String codeLongdesc;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwCodes() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable groupId
 */
  public void setGroupId(int value) {
    this.groupId = value;
  }
/**
 * Gets the value of member variable groupId
 */
  public int getGroupId() {
    return this.groupId;
  }
/**
 * Sets the value of member variable groupDesc
 */
  public void setGroupDesc(String value) {
    this.groupDesc = value;
  }
/**
 * Gets the value of member variable groupDesc
 */
  public String getGroupDesc() {
    return this.groupDesc;
  }
/**
 * Sets the value of member variable codeId
 */
  public void setCodeId(int value) {
    this.codeId = value;
  }
/**
 * Gets the value of member variable codeId
 */
  public int getCodeId() {
    return this.codeId;
  }
/**
 * Sets the value of member variable codeShortdesc
 */
  public void setCodeShortdesc(String value) {
    this.codeShortdesc = value;
  }
/**
 * Gets the value of member variable codeShortdesc
 */
  public String getCodeShortdesc() {
    return this.codeShortdesc;
  }
/**
 * Sets the value of member variable codeLongdesc
 */
  public void setCodeLongdesc(String value) {
    this.codeLongdesc = value;
  }
/**
 * Gets the value of member variable codeLongdesc
 */
  public String getCodeLongdesc() {
    return this.codeLongdesc;
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
   final VwCodes other = (VwCodes) obj; 
   if (EqualityAssistant.notEqual(this.groupId, other.groupId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.groupDesc, other.groupDesc)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.codeId, other.codeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.codeShortdesc, other.codeShortdesc)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.codeLongdesc, other.codeLongdesc)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.groupId),
               HashCodeAssistant.hashObject(this.groupDesc),
               HashCodeAssistant.hashObject(this.codeId),
               HashCodeAssistant.hashObject(this.codeShortdesc),
               HashCodeAssistant.hashObject(this.codeLongdesc));
} 

@Override
public String toString() {
   return "VwCodes [groupId=" + groupId + 
          ", groupDesc=" + groupDesc + 
          ", codeId=" + codeId + 
          ", codeShortdesc=" + codeShortdesc + 
          ", codeLongdesc=" + codeLongdesc  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}