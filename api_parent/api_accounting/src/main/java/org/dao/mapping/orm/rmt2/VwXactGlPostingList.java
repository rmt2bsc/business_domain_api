package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the vw_xact_gl_posting_list database table/view.
 *
 * @author auto generated.
 */
public class VwXactGlPostingList extends OrmBean {




	// Property name constants that belong to respective DataSource, VwXactGlPostingListView

/** The property name constant equivalent to property, Id, of respective DataSource view. */
  public static final String PROP_ID = "Id";
/** The property name constant equivalent to property, GlAccountId, of respective DataSource view. */
  public static final String PROP_GLACCOUNTID = "GlAccountId";
/** The property name constant equivalent to property, GlAcctName, of respective DataSource view. */
  public static final String PROP_GLACCTNAME = "GlAcctName";
/** The property name constant equivalent to property, XactAmount, of respective DataSource view. */
  public static final String PROP_XACTAMOUNT = "XactAmount";
/** The property name constant equivalent to property, XactDate, of respective DataSource view. */
  public static final String PROP_XACTDATE = "XactDate";
/** The property name constant equivalent to property, PostDate, of respective DataSource view. */
  public static final String PROP_POSTDATE = "PostDate";
/** The property name constant equivalent to property, XactTypeId, of respective DataSource view. */
  public static final String PROP_XACTTYPEID = "XactTypeId";
/** The property name constant equivalent to property, XactTypeName, of respective DataSource view. */
  public static final String PROP_XACTTYPENAME = "XactTypeName";
/** The property name constant equivalent to property, XactCategoryId, of respective DataSource view. */
  public static final String PROP_XACTCATEGORYID = "XactCategoryId";
/** The property name constant equivalent to property, XactCategoryDescription, of respective DataSource view. */
  public static final String PROP_XACTCATEGORYDESCRIPTION = "XactCategoryDescription";
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
/** The property name constant equivalent to property, Reason, of respective DataSource view. */
  public static final String PROP_REASON = "Reason";



	/** The javabean property equivalent of database column vw_xact_gl_posting_list.id */
  private int id;
/** The javabean property equivalent of database column vw_xact_gl_posting_list.gl_account_id */
  private int glAccountId;
/** The javabean property equivalent of database column vw_xact_gl_posting_list.gl_acct_name */
  private String glAcctName;
/** The javabean property equivalent of database column vw_xact_gl_posting_list.xact_amount */
  private double xactAmount;
/** The javabean property equivalent of database column vw_xact_gl_posting_list.xact_date */
  private java.util.Date xactDate;
/** The javabean property equivalent of database column vw_xact_gl_posting_list.post_date */
  private java.util.Date postDate;
/** The javabean property equivalent of database column vw_xact_gl_posting_list.xact_type_id */
  private int xactTypeId;
/** The javabean property equivalent of database column vw_xact_gl_posting_list.xact_type_name */
  private String xactTypeName;
/** The javabean property equivalent of database column vw_xact_gl_posting_list.xact_category_id */
  private int xactCategoryId;
/** The javabean property equivalent of database column vw_xact_gl_posting_list.xact_category_description */
  private String xactCategoryDescription;
/** The javabean property equivalent of database column vw_xact_gl_posting_list.to_multiplier */
  private int toMultiplier;
/** The javabean property equivalent of database column vw_xact_gl_posting_list.from_multiplier */
  private int fromMultiplier;
/** The javabean property equivalent of database column vw_xact_gl_posting_list.to_acct_type_id */
  private int toAcctTypeId;
/** The javabean property equivalent of database column vw_xact_gl_posting_list.to_acct_catg_id */
  private int toAcctCatgId;
/** The javabean property equivalent of database column vw_xact_gl_posting_list.from_acct_type_id */
  private int fromAcctTypeId;
/** The javabean property equivalent of database column vw_xact_gl_posting_list.from_acct_catg_id */
  private int fromAcctCatgId;
/** The javabean property equivalent of database column vw_xact_gl_posting_list.has_subsidiary */
  private int hasSubsidiary;
/** The javabean property equivalent of database column vw_xact_gl_posting_list.reason */
  private String reason;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwXactGlPostingList() throws SystemException {
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
 * Sets the value of member variable glAccountId
 */
  public void setGlAccountId(int value) {
    this.glAccountId = value;
  }
/**
 * Gets the value of member variable glAccountId
 */
  public int getGlAccountId() {
    return this.glAccountId;
  }
/**
 * Sets the value of member variable glAcctName
 */
  public void setGlAcctName(String value) {
    this.glAcctName = value;
  }
/**
 * Gets the value of member variable glAcctName
 */
  public String getGlAcctName() {
    return this.glAcctName;
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
   final VwXactGlPostingList other = (VwXactGlPostingList) obj; 
   if (EqualityAssistant.notEqual(this.id, other.id)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.glAccountId, other.glAccountId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.glAcctName, other.glAcctName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactAmount, other.xactAmount)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactDate, other.xactDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.postDate, other.postDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactTypeId, other.xactTypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactTypeName, other.xactTypeName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactCategoryId, other.xactCategoryId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactCategoryDescription, other.xactCategoryDescription)) {
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
   if (EqualityAssistant.notEqual(this.reason, other.reason)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.id),
               HashCodeAssistant.hashObject(this.glAccountId),
               HashCodeAssistant.hashObject(this.glAcctName),
               HashCodeAssistant.hashObject(this.xactAmount),
               HashCodeAssistant.hashObject(this.xactDate),
               HashCodeAssistant.hashObject(this.postDate),
               HashCodeAssistant.hashObject(this.xactTypeId),
               HashCodeAssistant.hashObject(this.xactTypeName),
               HashCodeAssistant.hashObject(this.xactCategoryId),
               HashCodeAssistant.hashObject(this.xactCategoryDescription),
               HashCodeAssistant.hashObject(this.toMultiplier),
               HashCodeAssistant.hashObject(this.fromMultiplier),
               HashCodeAssistant.hashObject(this.toAcctTypeId),
               HashCodeAssistant.hashObject(this.toAcctCatgId),
               HashCodeAssistant.hashObject(this.fromAcctTypeId),
               HashCodeAssistant.hashObject(this.fromAcctCatgId),
               HashCodeAssistant.hashObject(this.hasSubsidiary),
               HashCodeAssistant.hashObject(this.reason));
} 

@Override
public String toString() {
   return "VwXactGlPostingList [id=" + id + 
          ", glAccountId=" + glAccountId + 
          ", glAcctName=" + glAcctName + 
          ", xactAmount=" + xactAmount + 
          ", xactDate=" + xactDate + 
          ", postDate=" + postDate + 
          ", xactTypeId=" + xactTypeId + 
          ", xactTypeName=" + xactTypeName + 
          ", xactCategoryId=" + xactCategoryId + 
          ", xactCategoryDescription=" + xactCategoryDescription + 
          ", toMultiplier=" + toMultiplier + 
          ", fromMultiplier=" + fromMultiplier + 
          ", toAcctTypeId=" + toAcctTypeId + 
          ", toAcctCatgId=" + toAcctCatgId + 
          ", fromAcctTypeId=" + fromAcctTypeId + 
          ", fromAcctCatgId=" + fromAcctCatgId + 
          ", hasSubsidiary=" + hasSubsidiary + 
          ", reason=" + reason  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}