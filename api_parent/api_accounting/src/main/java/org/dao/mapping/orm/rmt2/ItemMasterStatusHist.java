package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the item_master_status_hist database table/view.
 *
 * @author auto generated.
 */
public class ItemMasterStatusHist extends OrmBean {




	// Property name constants that belong to respective DataSource, ItemMasterStatusHistView

/** The property name constant equivalent to property, ItemStatusHistId, of respective DataSource view. */
  public static final String PROP_ITEMSTATUSHISTID = "ItemStatusHistId";
/** The property name constant equivalent to property, ItemId, of respective DataSource view. */
  public static final String PROP_ITEMID = "ItemId";
/** The property name constant equivalent to property, ItemStatusId, of respective DataSource view. */
  public static final String PROP_ITEMSTATUSID = "ItemStatusId";
/** The property name constant equivalent to property, UnitCost, of respective DataSource view. */
  public static final String PROP_UNITCOST = "UnitCost";
/** The property name constant equivalent to property, Markup, of respective DataSource view. */
  public static final String PROP_MARKUP = "Markup";
/** The property name constant equivalent to property, EffectiveDate, of respective DataSource view. */
  public static final String PROP_EFFECTIVEDATE = "EffectiveDate";
/** The property name constant equivalent to property, EndDate, of respective DataSource view. */
  public static final String PROP_ENDDATE = "EndDate";
/** The property name constant equivalent to property, Reason, of respective DataSource view. */
  public static final String PROP_REASON = "Reason";
/** The property name constant equivalent to property, DateCreated, of respective DataSource view. */
  public static final String PROP_DATECREATED = "DateCreated";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";
/** The property name constant equivalent to property, IpCreated, of respective DataSource view. */
  public static final String PROP_IPCREATED = "IpCreated";
/** The property name constant equivalent to property, IpUpdated, of respective DataSource view. */
  public static final String PROP_IPUPDATED = "IpUpdated";



	/** The javabean property equivalent of database column item_master_status_hist.item_status_hist_id */
  private int itemStatusHistId;
/** The javabean property equivalent of database column item_master_status_hist.item_id */
  private int itemId;
/** The javabean property equivalent of database column item_master_status_hist.item_status_id */
  private int itemStatusId;
/** The javabean property equivalent of database column item_master_status_hist.unit_cost */
  private double unitCost;
/** The javabean property equivalent of database column item_master_status_hist.markup */
  private double markup;
/** The javabean property equivalent of database column item_master_status_hist.effective_date */
  private java.util.Date effectiveDate;
/** The javabean property equivalent of database column item_master_status_hist.end_date */
  private java.util.Date endDate;
/** The javabean property equivalent of database column item_master_status_hist.reason */
  private String reason;
/** The javabean property equivalent of database column item_master_status_hist.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column item_master_status_hist.user_id */
  private String userId;
/** The javabean property equivalent of database column item_master_status_hist.ip_created */
  private String ipCreated;
/** The javabean property equivalent of database column item_master_status_hist.ip_updated */
  private String ipUpdated;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public ItemMasterStatusHist() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable itemStatusHistId
 */
  public void setItemStatusHistId(int value) {
    this.itemStatusHistId = value;
  }
/**
 * Gets the value of member variable itemStatusHistId
 */
  public int getItemStatusHistId() {
    return this.itemStatusHistId;
  }
/**
 * Sets the value of member variable itemId
 */
  public void setItemId(int value) {
    this.itemId = value;
  }
/**
 * Gets the value of member variable itemId
 */
  public int getItemId() {
    return this.itemId;
  }
/**
 * Sets the value of member variable itemStatusId
 */
  public void setItemStatusId(int value) {
    this.itemStatusId = value;
  }
/**
 * Gets the value of member variable itemStatusId
 */
  public int getItemStatusId() {
    return this.itemStatusId;
  }
/**
 * Sets the value of member variable unitCost
 */
  public void setUnitCost(double value) {
    this.unitCost = value;
  }
/**
 * Gets the value of member variable unitCost
 */
  public double getUnitCost() {
    return this.unitCost;
  }
/**
 * Sets the value of member variable markup
 */
  public void setMarkup(double value) {
    this.markup = value;
  }
/**
 * Gets the value of member variable markup
 */
  public double getMarkup() {
    return this.markup;
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
 * Sets the value of member variable ipCreated
 */
  public void setIpCreated(String value) {
    this.ipCreated = value;
  }
/**
 * Gets the value of member variable ipCreated
 */
  public String getIpCreated() {
    return this.ipCreated;
  }
/**
 * Sets the value of member variable ipUpdated
 */
  public void setIpUpdated(String value) {
    this.ipUpdated = value;
  }
/**
 * Gets the value of member variable ipUpdated
 */
  public String getIpUpdated() {
    return this.ipUpdated;
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
   final ItemMasterStatusHist other = (ItemMasterStatusHist) obj; 
   if (EqualityAssistant.notEqual(this.itemStatusHistId, other.itemStatusHistId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.itemId, other.itemId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.itemStatusId, other.itemStatusId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.unitCost, other.unitCost)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.markup, other.markup)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.effectiveDate, other.effectiveDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.endDate, other.endDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.reason, other.reason)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.dateCreated, other.dateCreated)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.userId, other.userId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.ipCreated, other.ipCreated)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.ipUpdated, other.ipUpdated)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.itemStatusHistId),
               HashCodeAssistant.hashObject(this.itemId),
               HashCodeAssistant.hashObject(this.itemStatusId),
               HashCodeAssistant.hashObject(this.unitCost),
               HashCodeAssistant.hashObject(this.markup),
               HashCodeAssistant.hashObject(this.effectiveDate),
               HashCodeAssistant.hashObject(this.endDate),
               HashCodeAssistant.hashObject(this.reason),
               HashCodeAssistant.hashObject(this.dateCreated),
               HashCodeAssistant.hashObject(this.userId),
               HashCodeAssistant.hashObject(this.ipCreated),
               HashCodeAssistant.hashObject(this.ipUpdated));
} 

@Override
public String toString() {
   return "ItemMasterStatusHist [itemStatusHistId=" + itemStatusHistId + 
          ", itemId=" + itemId + 
          ", itemStatusId=" + itemStatusId + 
          ", unitCost=" + unitCost + 
          ", markup=" + markup + 
          ", effectiveDate=" + effectiveDate + 
          ", endDate=" + endDate + 
          ", reason=" + reason + 
          ", dateCreated=" + dateCreated + 
          ", userId=" + userId + 
          ", ipCreated=" + ipCreated + 
          ", ipUpdated=" + ipUpdated  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}