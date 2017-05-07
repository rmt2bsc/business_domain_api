package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the vw_purchase_order_list database table/view.
 *
 * @author auto generated.
 */
public class VwPurchaseOrderList extends OrmBean {




	// Property name constants that belong to respective DataSource, VwPurchaseOrderListView

/** The property name constant equivalent to property, Id, of respective DataSource view. */
  public static final String PROP_ID = "Id";
/** The property name constant equivalent to property, VendorId, of respective DataSource view. */
  public static final String PROP_VENDORID = "VendorId";
/** The property name constant equivalent to property, StatusId, of respective DataSource view. */
  public static final String PROP_STATUSID = "StatusId";
/** The property name constant equivalent to property, RefNo, of respective DataSource view. */
  public static final String PROP_REFNO = "RefNo";
/** The property name constant equivalent to property, Total, of respective DataSource view. */
  public static final String PROP_TOTAL = "Total";
/** The property name constant equivalent to property, StatusDescription, of respective DataSource view. */
  public static final String PROP_STATUSDESCRIPTION = "StatusDescription";
/** The property name constant equivalent to property, StatusHistId, of respective DataSource view. */
  public static final String PROP_STATUSHISTID = "StatusHistId";
/** The property name constant equivalent to property, EffectiveDate, of respective DataSource view. */
  public static final String PROP_EFFECTIVEDATE = "EffectiveDate";
/** The property name constant equivalent to property, EndDate, of respective DataSource view. */
  public static final String PROP_ENDDATE = "EndDate";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";
/** The property name constant equivalent to property, CreditorId, of respective DataSource view. */
  public static final String PROP_CREDITORID = "CreditorId";
/** The property name constant equivalent to property, BusinessId, of respective DataSource view. */
  public static final String PROP_BUSINESSID = "BusinessId";
/** The property name constant equivalent to property, AccountNumber, of respective DataSource view. */
  public static final String PROP_ACCOUNTNUMBER = "AccountNumber";
/** The property name constant equivalent to property, CreditLimit, of respective DataSource view. */
  public static final String PROP_CREDITLIMIT = "CreditLimit";
/** The property name constant equivalent to property, CreditTypeId, of respective DataSource view. */
  public static final String PROP_CREDITTYPEID = "CreditTypeId";
/** The property name constant equivalent to property, CreditorTypeDescr, of respective DataSource view. */
  public static final String PROP_CREDITORTYPEDESCR = "CreditorTypeDescr";



	/** The javabean property equivalent of database column vw_purchase_order_list.id */
  private int id;
/** The javabean property equivalent of database column vw_purchase_order_list.vendor_id */
  private int vendorId;
/** The javabean property equivalent of database column vw_purchase_order_list.status_id */
  private int statusId;
/** The javabean property equivalent of database column vw_purchase_order_list.ref_no */
  private String refNo;
/** The javabean property equivalent of database column vw_purchase_order_list.total */
  private double total;
/** The javabean property equivalent of database column vw_purchase_order_list.status_description */
  private String statusDescription;
/** The javabean property equivalent of database column vw_purchase_order_list.status_hist_id */
  private int statusHistId;
/** The javabean property equivalent of database column vw_purchase_order_list.effective_date */
  private java.util.Date effectiveDate;
/** The javabean property equivalent of database column vw_purchase_order_list.end_date */
  private java.util.Date endDate;
/** The javabean property equivalent of database column vw_purchase_order_list.user_id */
  private String userId;
/** The javabean property equivalent of database column vw_purchase_order_list.creditor_id */
  private int creditorId;
/** The javabean property equivalent of database column vw_purchase_order_list.business_id */
  private int businessId;
/** The javabean property equivalent of database column vw_purchase_order_list.account_number */
  private String accountNumber;
/** The javabean property equivalent of database column vw_purchase_order_list.credit_limit */
  private double creditLimit;
/** The javabean property equivalent of database column vw_purchase_order_list.credit_type_id */
  private int creditTypeId;
/** The javabean property equivalent of database column vw_purchase_order_list.creditor_type_descr */
  private String creditorTypeDescr;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwPurchaseOrderList() throws SystemException {
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
 * Sets the value of member variable vendorId
 */
  public void setVendorId(int value) {
    this.vendorId = value;
  }
/**
 * Gets the value of member variable vendorId
 */
  public int getVendorId() {
    return this.vendorId;
  }
/**
 * Sets the value of member variable statusId
 */
  public void setStatusId(int value) {
    this.statusId = value;
  }
/**
 * Gets the value of member variable statusId
 */
  public int getStatusId() {
    return this.statusId;
  }
/**
 * Sets the value of member variable refNo
 */
  public void setRefNo(String value) {
    this.refNo = value;
  }
/**
 * Gets the value of member variable refNo
 */
  public String getRefNo() {
    return this.refNo;
  }
/**
 * Sets the value of member variable total
 */
  public void setTotal(double value) {
    this.total = value;
  }
/**
 * Gets the value of member variable total
 */
  public double getTotal() {
    return this.total;
  }
/**
 * Sets the value of member variable statusDescription
 */
  public void setStatusDescription(String value) {
    this.statusDescription = value;
  }
/**
 * Gets the value of member variable statusDescription
 */
  public String getStatusDescription() {
    return this.statusDescription;
  }
/**
 * Sets the value of member variable statusHistId
 */
  public void setStatusHistId(int value) {
    this.statusHistId = value;
  }
/**
 * Gets the value of member variable statusHistId
 */
  public int getStatusHistId() {
    return this.statusHistId;
  }
/**
 * Sets the value of member variable effectiveDate
 */
  public void setEffectiveDate(java.util.Date value) {
    this.effectiveDate = value;
  }
/**
 * Gets the value of member variable effectiveDate
 */
  public java.util.Date getEffectiveDate() {
    return this.effectiveDate;
  }
/**
 * Sets the value of member variable endDate
 */
  public void setEndDate(java.util.Date value) {
    this.endDate = value;
  }
/**
 * Gets the value of member variable endDate
 */
  public java.util.Date getEndDate() {
    return this.endDate;
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
 * Sets the value of member variable creditTypeId
 */
  public void setCreditTypeId(int value) {
    this.creditTypeId = value;
  }
/**
 * Gets the value of member variable creditTypeId
 */
  public int getCreditTypeId() {
    return this.creditTypeId;
  }
/**
 * Sets the value of member variable creditorTypeDescr
 */
  public void setCreditorTypeDescr(String value) {
    this.creditorTypeDescr = value;
  }
/**
 * Gets the value of member variable creditorTypeDescr
 */
  public String getCreditorTypeDescr() {
    return this.creditorTypeDescr;
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
   final VwPurchaseOrderList other = (VwPurchaseOrderList) obj; 
   if (EqualityAssistant.notEqual(this.id, other.id)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.vendorId, other.vendorId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.statusId, other.statusId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.refNo, other.refNo)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.total, other.total)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.statusDescription, other.statusDescription)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.statusHistId, other.statusHistId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.effectiveDate, other.effectiveDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.endDate, other.endDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.userId, other.userId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.creditorId, other.creditorId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.businessId, other.businessId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.accountNumber, other.accountNumber)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.creditLimit, other.creditLimit)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.creditTypeId, other.creditTypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.creditorTypeDescr, other.creditorTypeDescr)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.id),
               HashCodeAssistant.hashObject(this.vendorId),
               HashCodeAssistant.hashObject(this.statusId),
               HashCodeAssistant.hashObject(this.refNo),
               HashCodeAssistant.hashObject(this.total),
               HashCodeAssistant.hashObject(this.statusDescription),
               HashCodeAssistant.hashObject(this.statusHistId),
               HashCodeAssistant.hashObject(this.effectiveDate),
               HashCodeAssistant.hashObject(this.endDate),
               HashCodeAssistant.hashObject(this.userId),
               HashCodeAssistant.hashObject(this.creditorId),
               HashCodeAssistant.hashObject(this.businessId),
               HashCodeAssistant.hashObject(this.accountNumber),
               HashCodeAssistant.hashObject(this.creditLimit),
               HashCodeAssistant.hashObject(this.creditTypeId),
               HashCodeAssistant.hashObject(this.creditorTypeDescr));
} 

@Override
public String toString() {
   return "VwPurchaseOrderList [id=" + id + 
          ", vendorId=" + vendorId + 
          ", statusId=" + statusId + 
          ", refNo=" + refNo + 
          ", total=" + total + 
          ", statusDescription=" + statusDescription + 
          ", statusHistId=" + statusHistId + 
          ", effectiveDate=" + effectiveDate + 
          ", endDate=" + endDate + 
          ", userId=" + userId + 
          ", creditorId=" + creditorId + 
          ", businessId=" + businessId + 
          ", accountNumber=" + accountNumber + 
          ", creditLimit=" + creditLimit + 
          ", creditTypeId=" + creditTypeId + 
          ", creditorTypeDescr=" + creditorTypeDescr  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}