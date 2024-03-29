package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the acct_pref database table/view.
 *
 * @author auto generated.
 */
public class AcctPref extends OrmBean {




	// Property name constants that belong to respective DataSource, AcctPrefView

/** The property name constant equivalent to property, AcctPrefId, of respective DataSource view. */
  public static final String PROP_ACCTPREFID = "AcctPrefId";
/** The property name constant equivalent to property, AcctPeriodId, of respective DataSource view. */
  public static final String PROP_ACCTPERIODID = "AcctPeriodId";
/** The property name constant equivalent to property, AcctPrdBegMonth, of respective DataSource view. */
  public static final String PROP_ACCTPRDBEGMONTH = "AcctPrdBegMonth";



	/** The javabean property equivalent of database column acct_pref.acct_pref_id */
  private int acctPrefId;
/** The javabean property equivalent of database column acct_pref.acct_period_id */
  private int acctPeriodId;
/** The javabean property equivalent of database column acct_pref.acct_prd_beg_month */
  private int acctPrdBegMonth;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public AcctPref() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable acctPrefId
 */
  public void setAcctPrefId(int value) {
    this.acctPrefId = value;
  }
/**
 * Gets the value of member variable acctPrefId
 */
  public int getAcctPrefId() {
    return this.acctPrefId;
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
 * Sets the value of member variable acctPrdBegMonth
 */
  public void setAcctPrdBegMonth(int value) {
    this.acctPrdBegMonth = value;
  }
/**
 * Gets the value of member variable acctPrdBegMonth
 */
  public int getAcctPrdBegMonth() {
    return this.acctPrdBegMonth;
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
   final AcctPref other = (AcctPref) obj; 
   if (EqualityAssistant.notEqual(this.acctPrefId, other.acctPrefId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.acctPeriodId, other.acctPeriodId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.acctPrdBegMonth, other.acctPrdBegMonth)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.acctPrefId),
               HashCodeAssistant.hashObject(this.acctPeriodId),
               HashCodeAssistant.hashObject(this.acctPrdBegMonth));
} 

@Override
public String toString() {
   return "AcctPref [acctPrefId=" + acctPrefId + 
          ", acctPeriodId=" + acctPeriodId + 
          ", acctPrdBegMonth=" + acctPrdBegMonth  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}