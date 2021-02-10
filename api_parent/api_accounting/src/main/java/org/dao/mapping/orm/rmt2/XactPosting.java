package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the xact_posting database table/view.
 *
 * @author auto generated.
 */
public class XactPosting extends OrmBean {




	// Property name constants that belong to respective DataSource, XactPostingView

/** The property name constant equivalent to property, XactPostId, of respective DataSource view. */
  public static final String PROP_XACTPOSTID = "XactPostId";
/** The property name constant equivalent to property, AcctId, of respective DataSource view. */
  public static final String PROP_ACCTID = "AcctId";
/** The property name constant equivalent to property, XactId, of respective DataSource view. */
  public static final String PROP_XACTID = "XactId";
/** The property name constant equivalent to property, AcctSubsidiaryId, of respective DataSource view. */
  public static final String PROP_ACCTSUBSIDIARYID = "AcctSubsidiaryId";
/** The property name constant equivalent to property, AcctPeriodId, of respective DataSource view. */
  public static final String PROP_ACCTPERIODID = "AcctPeriodId";
/** The property name constant equivalent to property, Period, of respective DataSource view. */
  public static final String PROP_PERIOD = "Period";
/** The property name constant equivalent to property, PostAmount, of respective DataSource view. */
  public static final String PROP_POSTAMOUNT = "PostAmount";
/** The property name constant equivalent to property, PostDate, of respective DataSource view. */
  public static final String PROP_POSTDATE = "PostDate";
/** The property name constant equivalent to property, DateCreated, of respective DataSource view. */
  public static final String PROP_DATECREATED = "DateCreated";
/** The property name constant equivalent to property, DateUpdated, of respective DataSource view. */
  public static final String PROP_DATEUPDATED = "DateUpdated";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";



	/** The javabean property equivalent of database column xact_posting.xact_post_id */
  private int xactPostId;
/** The javabean property equivalent of database column xact_posting.acct_id */
  private int acctId;
/** The javabean property equivalent of database column xact_posting.xact_id */
  private int xactId;
/** The javabean property equivalent of database column xact_posting.acct_subsidiary_id */
  private String acctSubsidiaryId;
/** The javabean property equivalent of database column xact_posting.acct_period_id */
  private int acctPeriodId;
/** The javabean property equivalent of database column xact_posting.period */
  private int period;
/** The javabean property equivalent of database column xact_posting.post_amount */
  private double postAmount;
/** The javabean property equivalent of database column xact_posting.post_date */
  private java.util.Date postDate;
/** The javabean property equivalent of database column xact_posting.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column xact_posting.date_updated */
  private java.util.Date dateUpdated;
/** The javabean property equivalent of database column xact_posting.user_id */
  private String userId;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public XactPosting() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable xactPostId
 */
  public void setXactPostId(int value) {
    this.xactPostId = value;
  }
/**
 * Gets the value of member variable xactPostId
 */
  public int getXactPostId() {
    return this.xactPostId;
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
 * Sets the value of member variable xactId
 */
  public void setXactId(int value) {
    this.xactId = value;
  }
/**
 * Gets the value of member variable xactId
 */
  public int getXactId() {
    return this.xactId;
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
 * Sets the value of member variable period
 */
  public void setPeriod(int value) {
    this.period = value;
  }
/**
 * Gets the value of member variable period
 */
  public int getPeriod() {
    return this.period;
  }
/**
 * Sets the value of member variable postAmount
 */
  public void setPostAmount(double value) {
    this.postAmount = value;
  }
/**
 * Gets the value of member variable postAmount
 */
  public double getPostAmount() {
    return this.postAmount;
  }
/**
 * Sets the value of member variable postDate
 */
  public void setPostDate(java.util.Date value) {
    this.postDate = value;
  }
/**
 * Gets the value of member variable postDate
 */
  public java.util.Date getPostDate() {
    return this.postDate;
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
 * Sets the value of member variable dateUpdated
 */
  public void setDateUpdated(java.util.Date value) {
    this.dateUpdated = value;
  }
/**
 * Gets the value of member variable dateUpdated
 */
  public java.util.Date getDateUpdated() {
    return this.dateUpdated;
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
   final XactPosting other = (XactPosting) obj; 
   if (EqualityAssistant.notEqual(this.xactPostId, other.xactPostId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.acctId, other.acctId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactId, other.xactId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.acctSubsidiaryId, other.acctSubsidiaryId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.acctPeriodId, other.acctPeriodId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.period, other.period)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.postAmount, other.postAmount)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.postDate, other.postDate)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.xactPostId),
               HashCodeAssistant.hashObject(this.acctId),
               HashCodeAssistant.hashObject(this.xactId),
               HashCodeAssistant.hashObject(this.acctSubsidiaryId),
               HashCodeAssistant.hashObject(this.acctPeriodId),
               HashCodeAssistant.hashObject(this.period),
               HashCodeAssistant.hashObject(this.postAmount),
               HashCodeAssistant.hashObject(this.postDate));
} 

@Override
public String toString() {
   return "XactPosting [xactPostId=" + xactPostId + 
          ", acctId=" + acctId + 
          ", xactId=" + xactId + 
          ", acctSubsidiaryId=" + acctSubsidiaryId + 
          ", acctPeriodId=" + acctPeriodId + 
          ", period=" + period + 
          ", postAmount=" + postAmount + 
          ", postDate=" + postDate  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}