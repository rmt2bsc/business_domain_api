package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the vw_generic_xact_list database table/view.
 *
 * @author auto generated.
 */
public class VwGenericXactList extends OrmBean {




	// Property name constants that belong to respective DataSource, VwGenericXactListView

/** The property name constant equivalent to property, XactId, of respective DataSource view. */
  public static final String PROP_XACTID = "XactId";
/** The property name constant equivalent to property, XactDate, of respective DataSource view. */
  public static final String PROP_XACTDATE = "XactDate";
/** The property name constant equivalent to property, XactDateStr, of respective DataSource view. */
  public static final String PROP_XACTDATESTR = "XactDateStr";
/** The property name constant equivalent to property, XactAmount, of respective DataSource view. */
  public static final String PROP_XACTAMOUNT = "XactAmount";
/** The property name constant equivalent to property, XactTypeId, of respective DataSource view. */
  public static final String PROP_XACTTYPEID = "XactTypeId";
/** The property name constant equivalent to property, XactSubtypeId, of respective DataSource view. */
  public static final String PROP_XACTSUBTYPEID = "XactSubtypeId";
/** The property name constant equivalent to property, XactTypeName, of respective DataSource view. */
  public static final String PROP_XACTTYPENAME = "XactTypeName";
/** The property name constant equivalent to property, XactReason, of respective DataSource view. */
  public static final String PROP_XACTREASON = "XactReason";
/** The property name constant equivalent to property, ConfirmNo, of respective DataSource view. */
  public static final String PROP_CONFIRMNO = "ConfirmNo";
/** The property name constant equivalent to property, DocumentId, of respective DataSource view. */
  public static final String PROP_DOCUMENTID = "DocumentId";
/** The property name constant equivalent to property, ParentEntityId, of respective DataSource view. */
  public static final String PROP_PARENTENTITYID = "ParentEntityId";
/** The property name constant equivalent to property, SpecXactLevel1Id, of respective DataSource view. */
  public static final String PROP_SPECXACTLEVEL1ID = "SpecXactLevel1Id";
/** The property name constant equivalent to property, SpecXactLevel1Date, of respective DataSource view. */
  public static final String PROP_SPECXACTLEVEL1DATE = "SpecXactLevel1Date";
/** The property name constant equivalent to property, SpecXactLevel2Id, of respective DataSource view. */
  public static final String PROP_SPECXACTLEVEL2ID = "SpecXactLevel2Id";
/** The property name constant equivalent to property, SpecXactLevel2Date, of respective DataSource view. */
  public static final String PROP_SPECXACTLEVEL2DATE = "SpecXactLevel2Date";
/** The property name constant equivalent to property, AccountNo, of respective DataSource view. */
  public static final String PROP_ACCOUNTNO = "AccountNo";
/** The property name constant equivalent to property, BusinessId, of respective DataSource view. */
  public static final String PROP_BUSINESSID = "BusinessId";
/** The property name constant equivalent to property, BusinessName, of respective DataSource view. */
  public static final String PROP_BUSINESSNAME = "BusinessName";
/** The property name constant equivalent to property, InvoiceNo, of respective DataSource view. */
  public static final String PROP_INVOICENO = "InvoiceNo";



	/** The javabean property equivalent of database column vw_generic_xact_list.xact_id */
  private int xactId;
/** The javabean property equivalent of database column vw_generic_xact_list.xact_date */
  private java.util.Date xactDate;
/** The javabean property equivalent of database column vw_generic_xact_list.xact_date_str */
  private String xactDateStr;
/** The javabean property equivalent of database column vw_generic_xact_list.xact_amount */
  private double xactAmount;
/** The javabean property equivalent of database column vw_generic_xact_list.xact_type_id */
  private int xactTypeId;
/** The javabean property equivalent of database column vw_generic_xact_list.xact_subtype_id */
  private int xactSubtypeId;
/** The javabean property equivalent of database column vw_generic_xact_list.xact_type_name */
  private String xactTypeName;
/** The javabean property equivalent of database column vw_generic_xact_list.xact_reason */
  private String xactReason;
/** The javabean property equivalent of database column vw_generic_xact_list.confirm_no */
  private String confirmNo;
/** The javabean property equivalent of database column vw_generic_xact_list.document_id */
  private int documentId;
/** The javabean property equivalent of database column vw_generic_xact_list.parent_entity_id */
  private int parentEntityId;
/** The javabean property equivalent of database column vw_generic_xact_list.spec_xact_level1_id */
  private int specXactLevel1Id;
/** The javabean property equivalent of database column vw_generic_xact_list.spec_xact_level1_date */
  private java.util.Date specXactLevel1Date;
/** The javabean property equivalent of database column vw_generic_xact_list.spec_xact_level2_id */
  private int specXactLevel2Id;
/** The javabean property equivalent of database column vw_generic_xact_list.spec_xact_level2_date */
  private java.util.Date specXactLevel2Date;
/** The javabean property equivalent of database column vw_generic_xact_list.account_no */
  private String accountNo;
/** The javabean property equivalent of database column vw_generic_xact_list.business_id */
  private int businessId;
/** The javabean property equivalent of database column vw_generic_xact_list.business_name */
  private String businessName;
/** The javabean property equivalent of database column vw_generic_xact_list.invoice_no */
  private String invoiceNo;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwGenericXactList() throws SystemException {
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
 * Sets the value of member variable xactDateStr
 */
  public void setXactDateStr(String value) {
    this.xactDateStr = value;
  }
/**
 * Gets the value of member variable xactDateStr
 */
  public String getXactDateStr() {
    return this.xactDateStr;
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
 * Sets the value of member variable xactReason
 */
  public void setXactReason(String value) {
    this.xactReason = value;
  }
/**
 * Gets the value of member variable xactReason
 */
  public String getXactReason() {
    return this.xactReason;
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
 * Sets the value of member variable parentEntityId
 */
  public void setParentEntityId(int value) {
    this.parentEntityId = value;
  }
/**
 * Gets the value of member variable parentEntityId
 */
  public int getParentEntityId() {
    return this.parentEntityId;
  }
/**
 * Sets the value of member variable specXactLevel1Id
 */
  public void setSpecXactLevel1Id(int value) {
    this.specXactLevel1Id = value;
  }
/**
 * Gets the value of member variable specXactLevel1Id
 */
  public int getSpecXactLevel1Id() {
    return this.specXactLevel1Id;
  }
/**
 * Sets the value of member variable specXactLevel1Date
 */
  public void setSpecXactLevel1Date(java.util.Date value) {
    this.specXactLevel1Date = value;
  }
/**
 * Gets the value of member variable specXactLevel1Date
 */
  public java.util.Date getSpecXactLevel1Date() {
    return this.specXactLevel1Date;
  }
/**
 * Sets the value of member variable specXactLevel2Id
 */
  public void setSpecXactLevel2Id(int value) {
    this.specXactLevel2Id = value;
  }
/**
 * Gets the value of member variable specXactLevel2Id
 */
  public int getSpecXactLevel2Id() {
    return this.specXactLevel2Id;
  }
/**
 * Sets the value of member variable specXactLevel2Date
 */
  public void setSpecXactLevel2Date(java.util.Date value) {
    this.specXactLevel2Date = value;
  }
/**
 * Gets the value of member variable specXactLevel2Date
 */
  public java.util.Date getSpecXactLevel2Date() {
    return this.specXactLevel2Date;
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
 * Sets the value of member variable businessName
 */
  public void setBusinessName(String value) {
    this.businessName = value;
  }
/**
 * Gets the value of member variable businessName
 */
  public String getBusinessName() {
    return this.businessName;
  }
/**
 * Sets the value of member variable invoiceNo
 */
  public void setInvoiceNo(String value) {
    this.invoiceNo = value;
  }
/**
 * Gets the value of member variable invoiceNo
 */
  public String getInvoiceNo() {
    return this.invoiceNo;
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
   final VwGenericXactList other = (VwGenericXactList) obj; 
   if (EqualityAssistant.notEqual(this.xactId, other.xactId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactDate, other.xactDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactDateStr, other.xactDateStr)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactAmount, other.xactAmount)) {
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
   if (EqualityAssistant.notEqual(this.xactReason, other.xactReason)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.confirmNo, other.confirmNo)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.documentId, other.documentId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.parentEntityId, other.parentEntityId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.specXactLevel1Id, other.specXactLevel1Id)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.specXactLevel1Date, other.specXactLevel1Date)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.specXactLevel2Id, other.specXactLevel2Id)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.specXactLevel2Date, other.specXactLevel2Date)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.accountNo, other.accountNo)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.businessId, other.businessId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.businessName, other.businessName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.invoiceNo, other.invoiceNo)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.xactId),
               HashCodeAssistant.hashObject(this.xactDate),
               HashCodeAssistant.hashObject(this.xactDateStr),
               HashCodeAssistant.hashObject(this.xactAmount),
               HashCodeAssistant.hashObject(this.xactTypeId),
               HashCodeAssistant.hashObject(this.xactSubtypeId),
               HashCodeAssistant.hashObject(this.xactTypeName),
               HashCodeAssistant.hashObject(this.xactReason),
               HashCodeAssistant.hashObject(this.confirmNo),
               HashCodeAssistant.hashObject(this.documentId),
               HashCodeAssistant.hashObject(this.parentEntityId),
               HashCodeAssistant.hashObject(this.specXactLevel1Id),
               HashCodeAssistant.hashObject(this.specXactLevel1Date),
               HashCodeAssistant.hashObject(this.specXactLevel2Id),
               HashCodeAssistant.hashObject(this.specXactLevel2Date),
               HashCodeAssistant.hashObject(this.accountNo),
               HashCodeAssistant.hashObject(this.businessId),
               HashCodeAssistant.hashObject(this.businessName),
               HashCodeAssistant.hashObject(this.invoiceNo));
} 

@Override
public String toString() {
   return "VwGenericXactList [xactId=" + xactId + 
          ", xactDate=" + xactDate + 
          ", xactDateStr=" + xactDateStr + 
          ", xactAmount=" + xactAmount + 
          ", xactTypeId=" + xactTypeId + 
          ", xactSubtypeId=" + xactSubtypeId + 
          ", xactTypeName=" + xactTypeName + 
          ", xactReason=" + xactReason + 
          ", confirmNo=" + confirmNo + 
          ", documentId=" + documentId + 
          ", parentEntityId=" + parentEntityId + 
          ", specXactLevel1Id=" + specXactLevel1Id + 
          ", specXactLevel1Date=" + specXactLevel1Date + 
          ", specXactLevel2Id=" + specXactLevel2Id + 
          ", specXactLevel2Date=" + specXactLevel2Date + 
          ", accountNo=" + accountNo + 
          ", businessId=" + businessId + 
          ", businessName=" + businessName + 
          ", invoiceNo=" + invoiceNo  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}