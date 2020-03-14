package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;

import com.api.persistence.db.orm.OrmBean;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.SystemException;


/**
 * Peer object that maps to the vw_xact_credit_charge_list database table/view.
 *
 * @author auto generated.
 */
public class VwXactCreditChargeList extends OrmBean {




	// Property name constants that belong to respective DataSource, VwXactCreditChargeListView

/** The property name constant equivalent to property, XactId, of respective DataSource view. */
  public static final String PROP_XACTID = "XactId";
/** The property name constant equivalent to property, XactAmount, of respective DataSource view. */
  public static final String PROP_XACTAMOUNT = "XactAmount";
/** The property name constant equivalent to property, XactDate, of respective DataSource view. */
  public static final String PROP_XACTDATE = "XactDate";
/** The property name constant equivalent to property, PostedDate, of respective DataSource view. */
  public static final String PROP_POSTEDDATE = "PostedDate";
/** The property name constant equivalent to property, ConfirmNo, of respective DataSource view. */
  public static final String PROP_CONFIRMNO = "ConfirmNo";
/** The property name constant equivalent to property, NegInstrNo, of respective DataSource view. */
  public static final String PROP_NEGINSTRNO = "NegInstrNo";
/** The property name constant equivalent to property, TenderId, of respective DataSource view. */
  public static final String PROP_TENDERID = "TenderId";
/** The property name constant equivalent to property, XactSubtypeId, of respective DataSource view. */
  public static final String PROP_XACTSUBTYPEID = "XactSubtypeId";
/** The property name constant equivalent to property, TenderDescription, of respective DataSource view. */
  public static final String PROP_TENDERDESCRIPTION = "TenderDescription";
/** The property name constant equivalent to property, XactTypeId, of respective DataSource view. */
  public static final String PROP_XACTTYPEID = "XactTypeId";
/** The property name constant equivalent to property, XactTypeName, of respective DataSource view. */
  public static final String PROP_XACTTYPENAME = "XactTypeName";
/** The property name constant equivalent to property, Reason, of respective DataSource view. */
  public static final String PROP_REASON = "Reason";
/** The property name constant equivalent to property, XactEntryDate, of respective DataSource view. */
  public static final String PROP_XACTENTRYDATE = "XactEntryDate";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";
/** The property name constant equivalent to property, DocumentId, of respective DataSource view. */
  public static final String PROP_DOCUMENTID = "DocumentId";
/** The property name constant equivalent to property, XactCatgId, of respective DataSource view. */
  public static final String PROP_XACTCATGID = "XactCatgId";
/** The property name constant equivalent to property, ToMultiplier, of respective DataSource view. */
  public static final String PROP_TOMULTIPLIER = "ToMultiplier";
/** The property name constant equivalent to property, FromMultiplier, of respective DataSource view. */
  public static final String PROP_FROMMULTIPLIER = "FromMultiplier";
/** The property name constant equivalent to property, ToAcctTypeId, of respective DataSource view. */
  public static final String PROP_TOACCTTYPEID = "ToAcctTypeId";
/** The property name constant equivalent to property, ToAcctCatgId, of respective DataSource view. */
  public static final String PROP_TOACCTCATGID = "ToAcctCatgId";
/** The property name constant equivalent to property, FromAcctTypeId, of respective DataSource view. */
  public static final String PROP_FROMACCTTYPEID = "FromAcctTypeId";
/** The property name constant equivalent to property, FromAcctCatgId, of respective DataSource view. */
  public static final String PROP_FROMACCTCATGID = "FromAcctCatgId";
/** The property name constant equivalent to property, HasSubsidiary, of respective DataSource view. */
  public static final String PROP_HASSUBSIDIARY = "HasSubsidiary";
/** The property name constant equivalent to property, CreditorId, of respective DataSource view. */
  public static final String PROP_CREDITORID = "CreditorId";
/** The property name constant equivalent to property, BusinessId, of respective DataSource view. */
  public static final String PROP_BUSINESSID = "BusinessId";
/** The property name constant equivalent to property, CreditorTypeId, of respective DataSource view. */
  public static final String PROP_CREDITORTYPEID = "CreditorTypeId";
/** The property name constant equivalent to property, AccountNo, of respective DataSource view. */
  public static final String PROP_ACCOUNTNO = "AccountNo";
/** The property name constant equivalent to property, CreditLimit, of respective DataSource view. */
  public static final String PROP_CREDITLIMIT = "CreditLimit";
/** The property name constant equivalent to property, Active, of respective DataSource view. */
  public static final String PROP_ACTIVE = "Active";
/** The property name constant equivalent to property, Apr, of respective DataSource view. */
  public static final String PROP_APR = "Apr";
/** The property name constant equivalent to property, CreditorDateCreated, of respective DataSource view. */
  public static final String PROP_CREDITORDATECREATED = "CreditorDateCreated";
/** The property name constant equivalent to property, CreditorTypeDescription, of respective DataSource view. */
  public static final String PROP_CREDITORTYPEDESCRIPTION = "CreditorTypeDescription";
/** The property name constant equivalent to property, Balance, of respective DataSource view. */
  public static final String PROP_BALANCE = "Balance";



	/** The javabean property equivalent of database column vw_xact_credit_charge_list.xact_id */
  private int xactId;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.xact_amount */
  private double xactAmount;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.xact_date */
  private java.util.Date xactDate;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.posted_date */
  private java.util.Date postedDate;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.confirm_no */
  private String confirmNo;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.neg_instr_no */
  private String negInstrNo;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.tender_id */
  private int tenderId;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.xact_subtype_id */
  private int xactSubtypeId;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.tender_description */
  private String tenderDescription;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.xact_type_id */
  private int xactTypeId;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.xact_type_name */
  private String xactTypeName;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.reason */
  private String reason;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.xact_entry_date */
  private java.util.Date xactEntryDate;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.user_id */
  private String userId;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.document_id */
  private int documentId;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.xact_catg_id */
  private int xactCatgId;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.to_multiplier */
  private int toMultiplier;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.from_multiplier */
  private int fromMultiplier;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.to_acct_type_id */
  private int toAcctTypeId;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.to_acct_catg_id */
  private int toAcctCatgId;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.from_acct_type_id */
  private int fromAcctTypeId;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.from_acct_catg_id */
  private int fromAcctCatgId;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.has_subsidiary */
  private int hasSubsidiary;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.creditor_id */
  private int creditorId;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.business_id */
  private int businessId;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.creditor_type_id */
  private int creditorTypeId;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.account_no */
  private String accountNo;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.credit_limit */
  private double creditLimit;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.active */
  private int active;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.apr */
  private double apr;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.creditor_date_created */
  private java.util.Date creditorDateCreated;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.creditor_type_description */
  private String creditorTypeDescription;
/** The javabean property equivalent of database column vw_xact_credit_charge_list.balance */
  private double balance;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwXactCreditChargeList() throws SystemException {
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
 * Sets the value of member variable tenderDescription
 */
  public void setTenderDescription(String value) {
    this.tenderDescription = value;
  }
/**
 * Gets the value of member variable tenderDescription
 */
  public String getTenderDescription() {
    return this.tenderDescription;
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
 * Sets the value of member variable xactEntryDate
 */
  public void setXactEntryDate(java.util.Date value) {
    this.xactEntryDate = value;
  }
/**
 * Gets the value of member variable xactEntryDate
 */
  public java.util.Date getXactEntryDate() {
    return this.xactEntryDate;
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
 * Sets the value of member variable toMultiplier
 */
  public void setToMultiplier(int value) {
    this.toMultiplier = value;
  }
/**
 * Gets the value of member variable toMultiplier
 */
  public int getToMultiplier() {
    return this.toMultiplier;
  }
/**
 * Sets the value of member variable fromMultiplier
 */
  public void setFromMultiplier(int value) {
    this.fromMultiplier = value;
  }
/**
 * Gets the value of member variable fromMultiplier
 */
  public int getFromMultiplier() {
    return this.fromMultiplier;
  }
/**
 * Sets the value of member variable toAcctTypeId
 */
  public void setToAcctTypeId(int value) {
    this.toAcctTypeId = value;
  }
/**
 * Gets the value of member variable toAcctTypeId
 */
  public int getToAcctTypeId() {
    return this.toAcctTypeId;
  }
/**
 * Sets the value of member variable toAcctCatgId
 */
  public void setToAcctCatgId(int value) {
    this.toAcctCatgId = value;
  }
/**
 * Gets the value of member variable toAcctCatgId
 */
  public int getToAcctCatgId() {
    return this.toAcctCatgId;
  }
/**
 * Sets the value of member variable fromAcctTypeId
 */
  public void setFromAcctTypeId(int value) {
    this.fromAcctTypeId = value;
  }
/**
 * Gets the value of member variable fromAcctTypeId
 */
  public int getFromAcctTypeId() {
    return this.fromAcctTypeId;
  }
/**
 * Sets the value of member variable fromAcctCatgId
 */
  public void setFromAcctCatgId(int value) {
    this.fromAcctCatgId = value;
  }
/**
 * Gets the value of member variable fromAcctCatgId
 */
  public int getFromAcctCatgId() {
    return this.fromAcctCatgId;
  }
/**
 * Sets the value of member variable hasSubsidiary
 */
  public void setHasSubsidiary(int value) {
    this.hasSubsidiary = value;
  }
/**
 * Gets the value of member variable hasSubsidiary
 */
  public int getHasSubsidiary() {
    return this.hasSubsidiary;
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
 * Sets the value of member variable businessId
 */
  public void setBusinessId(int value) {
    this.businessId = value;
  }
/**
 * Gets the value of member variable businessId
 */
  public int getBusinessId() {
    return this.businessId;
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
 * Sets the value of member variable accountNo
 */
  public void setAccountNo(String value) {
    this.accountNo = value;
  }
/**
 * Gets the value of member variable accountNo
 */
  public String getAccountNo() {
    return this.accountNo;
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
 * Sets the value of member variable creditorDateCreated
 */
  public void setCreditorDateCreated(java.util.Date value) {
    this.creditorDateCreated = value;
  }
/**
 * Gets the value of member variable creditorDateCreated
 */
  public java.util.Date getCreditorDateCreated() {
    return this.creditorDateCreated;
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
 * Sets the value of member variable balance
 */
  public void setBalance(double value) {
    this.balance = value;
  }
/**
 * Gets the value of member variable balance
 */
  public double getBalance() {
    return this.balance;
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
   final VwXactCreditChargeList other = (VwXactCreditChargeList) obj; 
   if (EqualityAssistant.notEqual(this.xactId, other.xactId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactAmount, other.xactAmount)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactDate, other.xactDate)) {
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
   if (EqualityAssistant.notEqual(this.xactSubtypeId, other.xactSubtypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.tenderDescription, other.tenderDescription)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactTypeId, other.xactTypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactTypeName, other.xactTypeName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.reason, other.reason)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactEntryDate, other.xactEntryDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.documentId, other.documentId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactCatgId, other.xactCatgId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.toMultiplier, other.toMultiplier)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.fromMultiplier, other.fromMultiplier)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.toAcctTypeId, other.toAcctTypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.toAcctCatgId, other.toAcctCatgId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.fromAcctTypeId, other.fromAcctTypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.fromAcctCatgId, other.fromAcctCatgId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.hasSubsidiary, other.hasSubsidiary)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.creditorId, other.creditorId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.businessId, other.businessId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.creditorTypeId, other.creditorTypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.accountNo, other.accountNo)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.creditLimit, other.creditLimit)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.active, other.active)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.apr, other.apr)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.creditorDateCreated, other.creditorDateCreated)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.creditorTypeDescription, other.creditorTypeDescription)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.balance, other.balance)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.xactId),
               HashCodeAssistant.hashObject(this.xactAmount),
               HashCodeAssistant.hashObject(this.xactDate),
               HashCodeAssistant.hashObject(this.postedDate),
               HashCodeAssistant.hashObject(this.confirmNo),
               HashCodeAssistant.hashObject(this.negInstrNo),
               HashCodeAssistant.hashObject(this.tenderId),
               HashCodeAssistant.hashObject(this.xactSubtypeId),
               HashCodeAssistant.hashObject(this.tenderDescription),
               HashCodeAssistant.hashObject(this.xactTypeId),
               HashCodeAssistant.hashObject(this.xactTypeName),
               HashCodeAssistant.hashObject(this.reason),
               HashCodeAssistant.hashObject(this.xactEntryDate),
               HashCodeAssistant.hashObject(this.documentId),
               HashCodeAssistant.hashObject(this.xactCatgId),
               HashCodeAssistant.hashObject(this.toMultiplier),
               HashCodeAssistant.hashObject(this.fromMultiplier),
               HashCodeAssistant.hashObject(this.toAcctTypeId),
               HashCodeAssistant.hashObject(this.toAcctCatgId),
               HashCodeAssistant.hashObject(this.fromAcctTypeId),
               HashCodeAssistant.hashObject(this.fromAcctCatgId),
               HashCodeAssistant.hashObject(this.hasSubsidiary),
               HashCodeAssistant.hashObject(this.creditorId),
               HashCodeAssistant.hashObject(this.businessId),
               HashCodeAssistant.hashObject(this.creditorTypeId),
               HashCodeAssistant.hashObject(this.accountNo),
               HashCodeAssistant.hashObject(this.creditLimit),
               HashCodeAssistant.hashObject(this.active),
               HashCodeAssistant.hashObject(this.apr),
               HashCodeAssistant.hashObject(this.creditorDateCreated),
               HashCodeAssistant.hashObject(this.creditorTypeDescription),
               HashCodeAssistant.hashObject(this.balance));
} 

@Override
public String toString() {
   return "VwXactCreditChargeList [xactId=" + xactId + 
          ", xactAmount=" + xactAmount + 
          ", xactDate=" + xactDate + 
          ", postedDate=" + postedDate + 
          ", confirmNo=" + confirmNo + 
          ", negInstrNo=" + negInstrNo + 
          ", tenderId=" + tenderId + 
          ", xactSubtypeId=" + xactSubtypeId + 
          ", tenderDescription=" + tenderDescription + 
          ", xactTypeId=" + xactTypeId + 
          ", xactTypeName=" + xactTypeName + 
          ", reason=" + reason + 
          ", xactEntryDate=" + xactEntryDate + 
          ", documentId=" + documentId + 
          ", xactCatgId=" + xactCatgId + 
          ", toMultiplier=" + toMultiplier + 
          ", fromMultiplier=" + fromMultiplier + 
          ", toAcctTypeId=" + toAcctTypeId + 
          ", toAcctCatgId=" + toAcctCatgId + 
          ", fromAcctTypeId=" + fromAcctTypeId + 
          ", fromAcctCatgId=" + fromAcctCatgId + 
          ", hasSubsidiary=" + hasSubsidiary + 
          ", creditorId=" + creditorId + 
          ", businessId=" + businessId + 
          ", creditorTypeId=" + creditorTypeId + 
          ", accountNo=" + accountNo + 
          ", creditLimit=" + creditLimit + 
          ", active=" + active + 
          ", apr=" + apr + 
          ", creditorDateCreated=" + creditorDateCreated + 
          ", creditorTypeDescription=" + creditorTypeDescription + 
          ", balance=" + balance  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}