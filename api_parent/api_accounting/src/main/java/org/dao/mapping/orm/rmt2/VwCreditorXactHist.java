package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the vw_creditor_xact_hist database table/view.
 *
 * @author auto generated.
 */
public class VwCreditorXactHist extends OrmBean {




	// Property name constants that belong to respective DataSource, VwCreditorXactHistView

/** The property name constant equivalent to property, XactId, of respective DataSource view. */
  public static final String PROP_XACTID = "XactId";
/** The property name constant equivalent to property, CreditorId, of respective DataSource view. */
  public static final String PROP_CREDITORID = "CreditorId";
/** The property name constant equivalent to property, AccountNumber, of respective DataSource view. */
  public static final String PROP_ACCOUNTNUMBER = "AccountNumber";
/** The property name constant equivalent to property, CreditorTypeId, of respective DataSource view. */
  public static final String PROP_CREDITORTYPEID = "CreditorTypeId";
/** The property name constant equivalent to property, Active, of respective DataSource view. */
  public static final String PROP_ACTIVE = "Active";
/** The property name constant equivalent to property, Apr, of respective DataSource view. */
  public static final String PROP_APR = "Apr";
/** The property name constant equivalent to property, CreditLimit, of respective DataSource view. */
  public static final String PROP_CREDITLIMIT = "CreditLimit";
/** The property name constant equivalent to property, CreditorTypeDescription, of respective DataSource view. */
  public static final String PROP_CREDITORTYPEDESCRIPTION = "CreditorTypeDescription";
/** The property name constant equivalent to property, XactAmount, of respective DataSource view. */
  public static final String PROP_XACTAMOUNT = "XactAmount";
/** The property name constant equivalent to property, XactDate, of respective DataSource view. */
  public static final String PROP_XACTDATE = "XactDate";
/** The property name constant equivalent to property, XactTypeId, of respective DataSource view. */
  public static final String PROP_XACTTYPEID = "XactTypeId";
/** The property name constant equivalent to property, XactSubtypeId, of respective DataSource view. */
  public static final String PROP_XACTSUBTYPEID = "XactSubtypeId";
/** The property name constant equivalent to property, Reason, of respective DataSource view. */
  public static final String PROP_REASON = "Reason";
/** The property name constant equivalent to property, XactTypeName, of respective DataSource view. */
  public static final String PROP_XACTTYPENAME = "XactTypeName";
/** The property name constant equivalent to property, DateCreated, of respective DataSource view. */
  public static final String PROP_DATECREATED = "DateCreated";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";
/** The property name constant equivalent to property, PostedDate, of respective DataSource view. */
  public static final String PROP_POSTEDDATE = "PostedDate";
/** The property name constant equivalent to property, ConfirmNo, of respective DataSource view. */
  public static final String PROP_CONFIRMNO = "ConfirmNo";
/** The property name constant equivalent to property, NegInstrNo, of respective DataSource view. */
  public static final String PROP_NEGINSTRNO = "NegInstrNo";
/** The property name constant equivalent to property, TenderId, of respective DataSource view. */
  public static final String PROP_TENDERID = "TenderId";
/** The property name constant equivalent to property, DocumentId, of respective DataSource view. */
  public static final String PROP_DOCUMENTID = "DocumentId";
/** The property name constant equivalent to property, CreditorActivityId, of respective DataSource view. */
  public static final String PROP_CREDITORACTIVITYID = "CreditorActivityId";
/** The property name constant equivalent to property, CreditorActivityAmount, of respective DataSource view. */
  public static final String PROP_CREDITORACTIVITYAMOUNT = "CreditorActivityAmount";



	/** The javabean property equivalent of database column vw_creditor_xact_hist.xact_id */
  private int xactId;
/** The javabean property equivalent of database column vw_creditor_xact_hist.creditor_id */
  private int creditorId;
/** The javabean property equivalent of database column vw_creditor_xact_hist.account_number */
  private String accountNumber;
/** The javabean property equivalent of database column vw_creditor_xact_hist.creditor_type_id */
  private int creditorTypeId;
/** The javabean property equivalent of database column vw_creditor_xact_hist.active */
  private int active;
/** The javabean property equivalent of database column vw_creditor_xact_hist.apr */
  private double apr;
/** The javabean property equivalent of database column vw_creditor_xact_hist.credit_limit */
  private double creditLimit;
/** The javabean property equivalent of database column vw_creditor_xact_hist.creditor_type_description */
  private String creditorTypeDescription;
/** The javabean property equivalent of database column vw_creditor_xact_hist.xact_amount */
  private double xactAmount;
/** The javabean property equivalent of database column vw_creditor_xact_hist.xact_date */
  private java.util.Date xactDate;
/** The javabean property equivalent of database column vw_creditor_xact_hist.xact_type_id */
  private int xactTypeId;
/** The javabean property equivalent of database column vw_creditor_xact_hist.xact_subtype_id */
  private int xactSubtypeId;
/** The javabean property equivalent of database column vw_creditor_xact_hist.reason */
  private String reason;
/** The javabean property equivalent of database column vw_creditor_xact_hist.xact_type_name */
  private String xactTypeName;
/** The javabean property equivalent of database column vw_creditor_xact_hist.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column vw_creditor_xact_hist.user_id */
  private String userId;
/** The javabean property equivalent of database column vw_creditor_xact_hist.posted_date */
  private java.util.Date postedDate;
/** The javabean property equivalent of database column vw_creditor_xact_hist.confirm_no */
  private String confirmNo;
/** The javabean property equivalent of database column vw_creditor_xact_hist.neg_instr_no */
  private String negInstrNo;
/** The javabean property equivalent of database column vw_creditor_xact_hist.tender_id */
  private int tenderId;
/** The javabean property equivalent of database column vw_creditor_xact_hist.document_id */
  private int documentId;
/** The javabean property equivalent of database column vw_creditor_xact_hist.creditor_activity_id */
  private int creditorActivityId;
/** The javabean property equivalent of database column vw_creditor_xact_hist.creditor_activity_amount */
  private double creditorActivityAmount;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwCreditorXactHist() throws SystemException {
	super();
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
 * Sets the value of member variable creditorId
 */
  public void setCreditorId(int value) {
    this.creditorId = value;
  }
/**
 * Gets the value of member variable creditorId
 */
  public int getCreditorId() {
    return this.creditorId;
  }
/**
 * Sets the value of member variable accountNumber
 */
  public void setAccountNumber(String value) {
    this.accountNumber = value;
  }
/**
 * Gets the value of member variable accountNumber
 */
  public String getAccountNumber() {
    return this.accountNumber;
  }
/**
 * Sets the value of member variable creditorTypeId
 */
  public void setCreditorTypeId(int value) {
    this.creditorTypeId = value;
  }
/**
 * Gets the value of member variable creditorTypeId
 */
  public int getCreditorTypeId() {
    return this.creditorTypeId;
  }
/**
 * Sets the value of member variable active
 */
  public void setActive(int value) {
    this.active = value;
  }
/**
 * Gets the value of member variable active
 */
  public int getActive() {
    return this.active;
  }
/**
 * Sets the value of member variable apr
 */
  public void setApr(double value) {
    this.apr = value;
  }
/**
 * Gets the value of member variable apr
 */
  public double getApr() {
    return this.apr;
  }
/**
 * Sets the value of member variable creditLimit
 */
  public void setCreditLimit(double value) {
    this.creditLimit = value;
  }
/**
 * Gets the value of member variable creditLimit
 */
  public double getCreditLimit() {
    return this.creditLimit;
  }
/**
 * Sets the value of member variable creditorTypeDescription
 */
  public void setCreditorTypeDescription(String value) {
    this.creditorTypeDescription = value;
  }
/**
 * Gets the value of member variable creditorTypeDescription
 */
  public String getCreditorTypeDescription() {
    return this.creditorTypeDescription;
  }
/**
 * Sets the value of member variable xactAmount
 */
  public void setXactAmount(double value) {
    this.xactAmount = value;
  }
/**
 * Gets the value of member variable xactAmount
 */
  public double getXactAmount() {
    return this.xactAmount;
  }
/**
 * Sets the value of member variable xactDate
 */
  public void setXactDate(java.util.Date value) {
    this.xactDate = value;
  }
/**
 * Gets the value of member variable xactDate
 */
  public java.util.Date getXactDate() {
    return this.xactDate;
  }
/**
 * Sets the value of member variable xactTypeId
 */
  public void setXactTypeId(int value) {
    this.xactTypeId = value;
  }
/**
 * Gets the value of member variable xactTypeId
 */
  public int getXactTypeId() {
    return this.xactTypeId;
  }
/**
 * Sets the value of member variable xactSubtypeId
 */
  public void setXactSubtypeId(int value) {
    this.xactSubtypeId = value;
  }
/**
 * Gets the value of member variable xactSubtypeId
 */
  public int getXactSubtypeId() {
    return this.xactSubtypeId;
  }
/**
 * Sets the value of member variable reason
 */
  public void setReason(String value) {
    this.reason = value;
  }
/**
 * Gets the value of member variable reason
 */
  public String getReason() {
    return this.reason;
  }
/**
 * Sets the value of member variable xactTypeName
 */
  public void setXactTypeName(String value) {
    this.xactTypeName = value;
  }
/**
 * Gets the value of member variable xactTypeName
 */
  public String getXactTypeName() {
    return this.xactTypeName;
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
 * Sets the value of member variable postedDate
 */
  public void setPostedDate(java.util.Date value) {
    this.postedDate = value;
  }
/**
 * Gets the value of member variable postedDate
 */
  public java.util.Date getPostedDate() {
    return this.postedDate;
  }
/**
 * Sets the value of member variable confirmNo
 */
  public void setConfirmNo(String value) {
    this.confirmNo = value;
  }
/**
 * Gets the value of member variable confirmNo
 */
  public String getConfirmNo() {
    return this.confirmNo;
  }
/**
 * Sets the value of member variable negInstrNo
 */
  public void setNegInstrNo(String value) {
    this.negInstrNo = value;
  }
/**
 * Gets the value of member variable negInstrNo
 */
  public String getNegInstrNo() {
    return this.negInstrNo;
  }
/**
 * Sets the value of member variable tenderId
 */
  public void setTenderId(int value) {
    this.tenderId = value;
  }
/**
 * Gets the value of member variable tenderId
 */
  public int getTenderId() {
    return this.tenderId;
  }
/**
 * Sets the value of member variable documentId
 */
  public void setDocumentId(int value) {
    this.documentId = value;
  }
/**
 * Gets the value of member variable documentId
 */
  public int getDocumentId() {
    return this.documentId;
  }
/**
 * Sets the value of member variable creditorActivityId
 */
  public void setCreditorActivityId(int value) {
    this.creditorActivityId = value;
  }
/**
 * Gets the value of member variable creditorActivityId
 */
  public int getCreditorActivityId() {
    return this.creditorActivityId;
  }
/**
 * Sets the value of member variable creditorActivityAmount
 */
  public void setCreditorActivityAmount(double value) {
    this.creditorActivityAmount = value;
  }
/**
 * Gets the value of member variable creditorActivityAmount
 */
  public double getCreditorActivityAmount() {
    return this.creditorActivityAmount;
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
   final VwCreditorXactHist other = (VwCreditorXactHist) obj; 
   if (EqualityAssistant.notEqual(this.xactId, other.xactId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.creditorId, other.creditorId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.accountNumber, other.accountNumber)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.creditorTypeId, other.creditorTypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.active, other.active)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.apr, other.apr)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.creditLimit, other.creditLimit)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.creditorTypeDescription, other.creditorTypeDescription)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactAmount, other.xactAmount)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactDate, other.xactDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactTypeId, other.xactTypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactSubtypeId, other.xactSubtypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.reason, other.reason)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactTypeName, other.xactTypeName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.postedDate, other.postedDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.confirmNo, other.confirmNo)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.negInstrNo, other.negInstrNo)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.tenderId, other.tenderId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.documentId, other.documentId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.creditorActivityId, other.creditorActivityId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.creditorActivityAmount, other.creditorActivityAmount)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.xactId),
               HashCodeAssistant.hashObject(this.creditorId),
               HashCodeAssistant.hashObject(this.accountNumber),
               HashCodeAssistant.hashObject(this.creditorTypeId),
               HashCodeAssistant.hashObject(this.active),
               HashCodeAssistant.hashObject(this.apr),
               HashCodeAssistant.hashObject(this.creditLimit),
               HashCodeAssistant.hashObject(this.creditorTypeDescription),
               HashCodeAssistant.hashObject(this.xactAmount),
               HashCodeAssistant.hashObject(this.xactDate),
               HashCodeAssistant.hashObject(this.xactTypeId),
               HashCodeAssistant.hashObject(this.xactSubtypeId),
               HashCodeAssistant.hashObject(this.reason),
               HashCodeAssistant.hashObject(this.xactTypeName),
               HashCodeAssistant.hashObject(this.postedDate),
               HashCodeAssistant.hashObject(this.confirmNo),
               HashCodeAssistant.hashObject(this.negInstrNo),
               HashCodeAssistant.hashObject(this.tenderId),
               HashCodeAssistant.hashObject(this.documentId),
               HashCodeAssistant.hashObject(this.creditorActivityId),
               HashCodeAssistant.hashObject(this.creditorActivityAmount));
} 

@Override
public String toString() {
   return "VwCreditorXactHist [xactId=" + xactId + 
          ", creditorId=" + creditorId + 
          ", accountNumber=" + accountNumber + 
          ", creditorTypeId=" + creditorTypeId + 
          ", active=" + active + 
          ", apr=" + apr + 
          ", creditLimit=" + creditLimit + 
          ", creditorTypeDescription=" + creditorTypeDescription + 
          ", xactAmount=" + xactAmount + 
          ", xactDate=" + xactDate + 
          ", xactTypeId=" + xactTypeId + 
          ", xactSubtypeId=" + xactSubtypeId + 
          ", reason=" + reason + 
          ", xactTypeName=" + xactTypeName + 
          ", postedDate=" + postedDate + 
          ", confirmNo=" + confirmNo + 
          ", negInstrNo=" + negInstrNo + 
          ", tenderId=" + tenderId + 
          ", documentId=" + documentId + 
          ", creditorActivityId=" + creditorActivityId + 
          ", creditorActivityAmount=" + creditorActivityAmount  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}