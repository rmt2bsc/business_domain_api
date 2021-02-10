package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the vw_xact_type_item_activity database table/view.
 *
 * @author auto generated.
 */
public class VwXactTypeItemActivity extends OrmBean {




	// Property name constants that belong to respective DataSource, VwXactTypeItemActivityView

/** The property name constant equivalent to property, Id, of respective DataSource view. */
  public static final String PROP_ID = "Id";
/** The property name constant equivalent to property, XactTypeItemId, of respective DataSource view. */
  public static final String PROP_XACTTYPEITEMID = "XactTypeItemId";
/** The property name constant equivalent to property, XactId, of respective DataSource view. */
  public static final String PROP_XACTID = "XactId";
/** The property name constant equivalent to property, ItemAmount, of respective DataSource view. */
  public static final String PROP_ITEMAMOUNT = "ItemAmount";
/** The property name constant equivalent to property, ItemName, of respective DataSource view. */
  public static final String PROP_ITEMNAME = "ItemName";
/** The property name constant equivalent to property, XactDate, of respective DataSource view. */
  public static final String PROP_XACTDATE = "XactDate";
/** The property name constant equivalent to property, XactAmount, of respective DataSource view. */
  public static final String PROP_XACTAMOUNT = "XactAmount";
/** The property name constant equivalent to property, Reason, of respective DataSource view. */
  public static final String PROP_REASON = "Reason";
/** The property name constant equivalent to property, ConfirmNo, of respective DataSource view. */
  public static final String PROP_CONFIRMNO = "ConfirmNo";
/** The property name constant equivalent to property, DocumentId, of respective DataSource view. */
  public static final String PROP_DOCUMENTID = "DocumentId";
/** The property name constant equivalent to property, XactTypeId, of respective DataSource view. */
  public static final String PROP_XACTTYPEID = "XactTypeId";
/** The property name constant equivalent to property, XactTypeItemName, of respective DataSource view. */
  public static final String PROP_XACTTYPEITEMNAME = "XactTypeItemName";
/** The property name constant equivalent to property, XactTypeXactCode, of respective DataSource view. */
  public static final String PROP_XACTTYPEXACTCODE = "XactTypeXactCode";
/** The property name constant equivalent to property, XactTypeDescription, of respective DataSource view. */
  public static final String PROP_XACTTYPEDESCRIPTION = "XactTypeDescription";
/** The property name constant equivalent to property, XactCategoryId, of respective DataSource view. */
  public static final String PROP_XACTCATEGORYID = "XactCategoryId";
/** The property name constant equivalent to property, XactCategoryDescription, of respective DataSource view. */
  public static final String PROP_XACTCATEGORYDESCRIPTION = "XactCategoryDescription";
/** The property name constant equivalent to property, XactCategoryCode, of respective DataSource view. */
  public static final String PROP_XACTCATEGORYCODE = "XactCategoryCode";



	/** The javabean property equivalent of database column vw_xact_type_item_activity.id */
  private int id;
/** The javabean property equivalent of database column vw_xact_type_item_activity.xact_type_item_id */
  private int xactTypeItemId;
/** The javabean property equivalent of database column vw_xact_type_item_activity.xact_id */
  private int xactId;
/** The javabean property equivalent of database column vw_xact_type_item_activity.item_amount */
  private double itemAmount;
/** The javabean property equivalent of database column vw_xact_type_item_activity.item_name */
  private String itemName;
/** The javabean property equivalent of database column vw_xact_type_item_activity.xact_date */
  private java.util.Date xactDate;
/** The javabean property equivalent of database column vw_xact_type_item_activity.xact_amount */
  private double xactAmount;
/** The javabean property equivalent of database column vw_xact_type_item_activity.reason */
  private String reason;
/** The javabean property equivalent of database column vw_xact_type_item_activity.confirm_no */
  private String confirmNo;
/** The javabean property equivalent of database column vw_xact_type_item_activity.document_id */
  private int documentId;
/** The javabean property equivalent of database column vw_xact_type_item_activity.xact_type_id */
  private int xactTypeId;
/** The javabean property equivalent of database column vw_xact_type_item_activity.xact_type_item_name */
  private String xactTypeItemName;
/** The javabean property equivalent of database column vw_xact_type_item_activity.xact_type_xact_code */
  private String xactTypeXactCode;
/** The javabean property equivalent of database column vw_xact_type_item_activity.xact_type_description */
  private String xactTypeDescription;
/** The javabean property equivalent of database column vw_xact_type_item_activity.xact_category_id */
  private int xactCategoryId;
/** The javabean property equivalent of database column vw_xact_type_item_activity.xact_category_description */
  private String xactCategoryDescription;
/** The javabean property equivalent of database column vw_xact_type_item_activity.xact_category_code */
  private String xactCategoryCode;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwXactTypeItemActivity() throws SystemException {
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
 * Sets the value of member variable xactTypeItemId
 */
  public void setXactTypeItemId(int value) {
    this.xactTypeItemId = value;
  }
/**
 * Gets the value of member variable xactTypeItemId
 */
  public int getXactTypeItemId() {
    return this.xactTypeItemId;
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
 * Sets the value of member variable itemAmount
 */
  public void setItemAmount(double value) {
    this.itemAmount = value;
  }
/**
 * Gets the value of member variable itemAmount
 */
  public double getItemAmount() {
    return this.itemAmount;
  }
/**
 * Sets the value of member variable itemName
 */
  public void setItemName(String value) {
    this.itemName = value;
  }
/**
 * Gets the value of member variable itemName
 */
  public String getItemName() {
    return this.itemName;
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
 * Sets the value of member variable xactTypeItemName
 */
  public void setXactTypeItemName(String value) {
    this.xactTypeItemName = value;
  }
/**
 * Gets the value of member variable xactTypeItemName
 */
  public String getXactTypeItemName() {
    return this.xactTypeItemName;
  }
/**
 * Sets the value of member variable xactTypeXactCode
 */
  public void setXactTypeXactCode(String value) {
    this.xactTypeXactCode = value;
  }
/**
 * Gets the value of member variable xactTypeXactCode
 */
  public String getXactTypeXactCode() {
    return this.xactTypeXactCode;
  }
/**
 * Sets the value of member variable xactTypeDescription
 */
  public void setXactTypeDescription(String value) {
    this.xactTypeDescription = value;
  }
/**
 * Gets the value of member variable xactTypeDescription
 */
  public String getXactTypeDescription() {
    return this.xactTypeDescription;
  }
/**
 * Sets the value of member variable xactCategoryId
 */
  public void setXactCategoryId(int value) {
    this.xactCategoryId = value;
  }
/**
 * Gets the value of member variable xactCategoryId
 */
  public int getXactCategoryId() {
    return this.xactCategoryId;
  }
/**
 * Sets the value of member variable xactCategoryDescription
 */
  public void setXactCategoryDescription(String value) {
    this.xactCategoryDescription = value;
  }
/**
 * Gets the value of member variable xactCategoryDescription
 */
  public String getXactCategoryDescription() {
    return this.xactCategoryDescription;
  }
/**
 * Sets the value of member variable xactCategoryCode
 */
  public void setXactCategoryCode(String value) {
    this.xactCategoryCode = value;
  }
/**
 * Gets the value of member variable xactCategoryCode
 */
  public String getXactCategoryCode() {
    return this.xactCategoryCode;
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
   final VwXactTypeItemActivity other = (VwXactTypeItemActivity) obj; 
   if (EqualityAssistant.notEqual(this.id, other.id)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactTypeItemId, other.xactTypeItemId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactId, other.xactId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.itemAmount, other.itemAmount)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.itemName, other.itemName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactDate, other.xactDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactAmount, other.xactAmount)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.reason, other.reason)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.confirmNo, other.confirmNo)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.documentId, other.documentId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactTypeId, other.xactTypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactTypeItemName, other.xactTypeItemName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactTypeXactCode, other.xactTypeXactCode)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactTypeDescription, other.xactTypeDescription)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactCategoryId, other.xactCategoryId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactCategoryDescription, other.xactCategoryDescription)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactCategoryCode, other.xactCategoryCode)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.id),
               HashCodeAssistant.hashObject(this.xactTypeItemId),
               HashCodeAssistant.hashObject(this.xactId),
               HashCodeAssistant.hashObject(this.itemAmount),
               HashCodeAssistant.hashObject(this.itemName),
               HashCodeAssistant.hashObject(this.xactDate),
               HashCodeAssistant.hashObject(this.xactAmount),
               HashCodeAssistant.hashObject(this.reason),
               HashCodeAssistant.hashObject(this.confirmNo),
               HashCodeAssistant.hashObject(this.documentId),
               HashCodeAssistant.hashObject(this.xactTypeId),
               HashCodeAssistant.hashObject(this.xactTypeItemName),
               HashCodeAssistant.hashObject(this.xactTypeXactCode),
               HashCodeAssistant.hashObject(this.xactTypeDescription),
               HashCodeAssistant.hashObject(this.xactCategoryId),
               HashCodeAssistant.hashObject(this.xactCategoryDescription),
               HashCodeAssistant.hashObject(this.xactCategoryCode));
} 

@Override
public String toString() {
   return "VwXactTypeItemActivity [id=" + id + 
          ", xactTypeItemId=" + xactTypeItemId + 
          ", xactId=" + xactId + 
          ", itemAmount=" + itemAmount + 
          ", itemName=" + itemName + 
          ", xactDate=" + xactDate + 
          ", xactAmount=" + xactAmount + 
          ", reason=" + reason + 
          ", confirmNo=" + confirmNo + 
          ", documentId=" + documentId + 
          ", xactTypeId=" + xactTypeId + 
          ", xactTypeItemName=" + xactTypeItemName + 
          ", xactTypeXactCode=" + xactTypeXactCode + 
          ", xactTypeDescription=" + xactTypeDescription + 
          ", xactCategoryId=" + xactCategoryId + 
          ", xactCategoryDescription=" + xactCategoryDescription + 
          ", xactCategoryCode=" + xactCategoryCode  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}