package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;

import com.api.persistence.db.orm.OrmBean;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.SystemException;


/**
 * Peer object that maps to the acct_period database table/view.
 *
 * @author auto generated.
 */
public class AcctPeriod extends OrmBean {




	// Property name constants that belong to respective DataSource, AcctPeriodView

/** The property name constant equivalent to property, AcctPeriodId, of respective DataSource view. */
  public static final String PROP_ACCTPERIODID = "AcctPeriodId";
/** The property name constant equivalent to property, Description, of respective DataSource view. */
  public static final String PROP_DESCRIPTION = "Description";



	/** The javabean property equivalent of database column acct_period.acct_period_id */
  private int acctPeriodId;
/** The javabean property equivalent of database column acct_period.description */
  private String description;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public AcctPeriod() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable acctPeriodId
 */
  public void setAcctPeriodId(int value) {
    this.acctPeriodId = value;
  }
/**
 * Gets the value of member variable acctPeriodId
 */
  public int getAcctPeriodId() {
    return this.acctPeriodId;
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
   final AcctPeriod other = (AcctPeriod) obj; 
   if (EqualityAssistant.notEqual(this.acctPeriodId, other.acctPeriodId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.description, other.description)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.acctPeriodId),
               HashCodeAssistant.hashObject(this.description));
} 

@Override
public String toString() {
   return "AcctPeriod [acctPeriodId=" + acctPeriodId + 
          ", description=" + description  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}