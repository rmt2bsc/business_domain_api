package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the gl_subsidiary database table/view.
 *
 * @author auto generated.
 */
public class GlSubsidiary extends OrmBean {




	// Property name constants that belong to respective DataSource, GlSubsidiaryView

/** The property name constant equivalent to property, AcctSubsidiaryId, of respective DataSource view. */
  public static final String PROP_ACCTSUBSIDIARYID = "AcctSubsidiaryId";
/** The property name constant equivalent to property, AcctId, of respective DataSource view. */
  public static final String PROP_ACCTID = "AcctId";
/** The property name constant equivalent to property, ActivityTable, of respective DataSource view. */
  public static final String PROP_ACTIVITYTABLE = "ActivityTable";
/** The property name constant equivalent to property, Description, of respective DataSource view. */
  public static final String PROP_DESCRIPTION = "Description";



	/** The javabean property equivalent of database column gl_subsidiary.acct_subsidiary_id */
  private String acctSubsidiaryId;
/** The javabean property equivalent of database column gl_subsidiary.acct_id */
  private int acctId;
/** The javabean property equivalent of database column gl_subsidiary.activity_table */
  private String activityTable;
/** The javabean property equivalent of database column gl_subsidiary.description */
  private String description;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public GlSubsidiary() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable acctSubsidiaryId
 */
  public void setAcctSubsidiaryId(String value) {
    this.acctSubsidiaryId = value;
  }
/**
 * Gets the value of member variable acctSubsidiaryId
 */
  public String getAcctSubsidiaryId() {
    return this.acctSubsidiaryId;
  }
/**
 * Sets the value of member variable acctId
 */
  public void setAcctId(int value) {
    this.acctId = value;
  }
/**
 * Gets the value of member variable acctId
 */
  public int getAcctId() {
    return this.acctId;
  }
/**
 * Sets the value of member variable activityTable
 */
  public void setActivityTable(String value) {
    this.activityTable = value;
  }
/**
 * Gets the value of member variable activityTable
 */
  public String getActivityTable() {
    return this.activityTable;
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
   final GlSubsidiary other = (GlSubsidiary) obj; 
   if (EqualityAssistant.notEqual(this.acctSubsidiaryId, other.acctSubsidiaryId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.acctId, other.acctId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.activityTable, other.activityTable)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.description, other.description)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.acctSubsidiaryId),
               HashCodeAssistant.hashObject(this.acctId),
               HashCodeAssistant.hashObject(this.activityTable),
               HashCodeAssistant.hashObject(this.description));
} 

@Override
public String toString() {
   return "GlSubsidiary [acctSubsidiaryId=" + acctSubsidiaryId + 
          ", acctId=" + acctId + 
          ", activityTable=" + activityTable + 
          ", description=" + description  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}