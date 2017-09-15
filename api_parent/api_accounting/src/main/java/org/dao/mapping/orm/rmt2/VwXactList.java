package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the vw_xact_list database table/view.
 *
 * @author auto generated.
 */
public class VwXactList extends OrmBean {




	// Property name constants that belong to respective DataSource, VwXactListView

/** The property name constant equivalent to property, Id, of respective DataSource view. */
  public static final String PROP_ID = "Id";
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
/** The property name constant equivalent to property, TenderDescription, of respective DataSource view. */
  public static final String PROP_TENDERDESCRIPTION = "TenderDescription";
/** The property name constant equivalent to property, XactTypeId, of respective DataSource view. */
  public static final String PROP_XACTTYPEID = "XactTypeId";
/** The property name constant equivalent to property, XactSubtypeId, of respective DataSource view. */
  public static final String PROP_XACTSUBTYPEID = "XactSubtypeId";
/** The property name constant equivalent to property, XactTypeName, of respective DataSource view. */
  public static final String PROP_XACTTYPENAME = "XactTypeName";
/** The property name constant equivalent to property, Reason, of respective DataSource view. */
  public static final String PROP_REASON = "Reason";
/** The property name constant equivalent to property, CreateDate, of respective DataSource view. */
  public static final String PROP_CREATEDATE = "CreateDate";
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



	/** The javabean property equivalent of database column vw_xact_list.id */
  private int id;
/** The javabean property equivalent of database column vw_xact_list.xact_amount */
  private double xactAmount;
/** The javabean property equivalent of database column vw_xact_list.xact_date */
  private java.util.Date xactDate;
/** The javabean property equivalent of database column vw_xact_list.posted_date */
  private java.util.Date postedDate;
/** The javabean property equivalent of database column vw_xact_list.confirm_no */
  private String confirmNo;
/** The javabean property equivalent of database column vw_xact_list.neg_instr_no */
  private String negInstrNo;
/** The javabean property equivalent of database column vw_xact_list.tender_id */
  private int tenderId;
/** The javabean property equivalent of database column vw_xact_list.tender_description */
  private String tenderDescription;
/** The javabean property equivalent of database column vw_xact_list.xact_type_id */
  private int xactTypeId;
/** The javabean property equivalent of database column vw_xact_list.xact_subtype_id */
  private int xactSubtypeId;
/** The javabean property equivalent of database column vw_xact_list.xact_type_name */
  private String xactTypeName;
/** The javabean property equivalent of database column vw_xact_list.reason */
  private String reason;
/** The javabean property equivalent of database column vw_xact_list.create_date */
  private java.util.Date createDate;
/** The javabean property equivalent of database column vw_xact_list.user_id */
  private String userId;
/** The javabean property equivalent of database column vw_xact_list.document_id */
  private int documentId;
/** The javabean property equivalent of database column vw_xact_list.xact_catg_id */
  private int xactCatgId;
/** The javabean property equivalent of database column vw_xact_list.to_multiplier */
  private int toMultiplier;
/** The javabean property equivalent of database column vw_xact_list.from_multiplier */
  private int fromMultiplier;
/** The javabean property equivalent of database column vw_xact_list.to_acct_type_id */
  private int toAcctTypeId;
/** The javabean property equivalent of database column vw_xact_list.to_acct_catg_id */
  private int toAcctCatgId;
/** The javabean property equivalent of database column vw_xact_list.from_acct_type_id */
  private int fromAcctTypeId;
/** The javabean property equivalent of database column vw_xact_list.from_acct_catg_id */
  private int fromAcctCatgId;
/** The javabean property equivalent of database column vw_xact_list.has_subsidiary */
  private int hasSubsidiary;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwXactList() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable id
 */
  public void setId(int value) {
    this.id = value;
  }
/**
 * Gets the value of member variable id
 */
  public int getId() {
    return this.id;
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
 * Sets the value of member variable createDate
 */
  public void setCreateDate(java.util.Date value) {
    this.createDate = value;
  }
/**
 * Gets the value of member variable createDate
 */
  public java.util.Date getCreateDate() {
    return this.createDate;
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
   final VwXactList other = (VwXactList) obj; 
   if (EqualityAssistant.notEqual(this.id, other.id)) {
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
   if (EqualityAssistant.notEqual(this.tenderDescription, other.tenderDescription)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactTypeId, other.xactTypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactSubtypeId, other.xactSubtypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactTypeName, other.xactTypeName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.reason, other.reason)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.createDate, other.createDate)) {
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
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.id),
               HashCodeAssistant.hashObject(this.xactAmount),
               HashCodeAssistant.hashObject(this.xactDate),
               HashCodeAssistant.hashObject(this.postedDate),
               HashCodeAssistant.hashObject(this.confirmNo),
               HashCodeAssistant.hashObject(this.negInstrNo),
               HashCodeAssistant.hashObject(this.tenderId),
               HashCodeAssistant.hashObject(this.tenderDescription),
               HashCodeAssistant.hashObject(this.xactTypeId),
               HashCodeAssistant.hashObject(this.xactSubtypeId),
               HashCodeAssistant.hashObject(this.xactTypeName),
               HashCodeAssistant.hashObject(this.reason),
               HashCodeAssistant.hashObject(this.createDate),
               HashCodeAssistant.hashObject(this.documentId),
               HashCodeAssistant.hashObject(this.xactCatgId),
               HashCodeAssistant.hashObject(this.toMultiplier),
               HashCodeAssistant.hashObject(this.fromMultiplier),
               HashCodeAssistant.hashObject(this.toAcctTypeId),
               HashCodeAssistant.hashObject(this.toAcctCatgId),
               HashCodeAssistant.hashObject(this.fromAcctTypeId),
               HashCodeAssistant.hashObject(this.fromAcctCatgId),
               HashCodeAssistant.hashObject(this.hasSubsidiary));
} 

@Override
public String toString() {
   return "VwXactList [id=" + id + 
          ", xactAmount=" + xactAmount + 
          ", xactDate=" + xactDate + 
          ", postedDate=" + postedDate + 
          ", confirmNo=" + confirmNo + 
          ", negInstrNo=" + negInstrNo + 
          ", tenderId=" + tenderId + 
          ", tenderDescription=" + tenderDescription + 
          ", xactTypeId=" + xactTypeId + 
          ", xactSubtypeId=" + xactSubtypeId + 
          ", xactTypeName=" + xactTypeName + 
          ", reason=" + reason + 
          ", createDate=" + createDate + 
          ", documentId=" + documentId + 
          ", xactCatgId=" + xactCatgId + 
          ", toMultiplier=" + toMultiplier + 
          ", fromMultiplier=" + fromMultiplier + 
          ", toAcctTypeId=" + toAcctTypeId + 
          ", toAcctCatgId=" + toAcctCatgId + 
          ", fromAcctTypeId=" + fromAcctTypeId + 
          ", fromAcctCatgId=" + fromAcctCatgId + 
          ", hasSubsidiary=" + hasSubsidiary  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}