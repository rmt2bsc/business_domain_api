package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the sales_order_status_hist database table/view.
 *
 * @author auto generated.
 */
public class SalesOrderStatusHist extends OrmBean {




	// Property name constants that belong to respective DataSource, SalesOrderStatusHistView

/** The property name constant equivalent to property, SoStatusHistId, of respective DataSource view. */
  public static final String PROP_SOSTATUSHISTID = "SoStatusHistId";
/** The property name constant equivalent to property, SoId, of respective DataSource view. */
  public static final String PROP_SOID = "SoId";
/** The property name constant equivalent to property, SoStatusId, of respective DataSource view. */
  public static final String PROP_SOSTATUSID = "SoStatusId";
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



	/** The javabean property equivalent of database column sales_order_status_hist.so_status_hist_id */
  private int soStatusHistId;
/** The javabean property equivalent of database column sales_order_status_hist.so_id */
  private int soId;
/** The javabean property equivalent of database column sales_order_status_hist.so_status_id */
  private int soStatusId;
/** The javabean property equivalent of database column sales_order_status_hist.effective_date */
  private java.util.Date effectiveDate;
/** The javabean property equivalent of database column sales_order_status_hist.end_date */
  private java.util.Date endDate;
/** The javabean property equivalent of database column sales_order_status_hist.reason */
  private String reason;
/** The javabean property equivalent of database column sales_order_status_hist.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column sales_order_status_hist.user_id */
  private String userId;
/** The javabean property equivalent of database column sales_order_status_hist.ip_created */
  private String ipCreated;
/** The javabean property equivalent of database column sales_order_status_hist.ip_updated */
  private String ipUpdated;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public SalesOrderStatusHist() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable soStatusHistId
 */
  public void setSoStatusHistId(int value) {
    this.soStatusHistId = value;
  }
/**
 * Gets the value of member variable soStatusHistId
 */
  public int getSoStatusHistId() {
    return this.soStatusHistId;
  }
/**
 * Sets the value of member variable soId
 */
  public void setSoId(int value) {
    this.soId = value;
  }
/**
 * Gets the value of member variable soId
 */
  public int getSoId() {
    return this.soId;
  }
/**
 * Sets the value of member variable soStatusId
 */
  public void setSoStatusId(int value) {
    this.soStatusId = value;
  }
/**
 * Gets the value of member variable soStatusId
 */
  public int getSoStatusId() {
    return this.soStatusId;
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
   final SalesOrderStatusHist other = (SalesOrderStatusHist) obj; 
   if (EqualityAssistant.notEqual(this.soStatusHistId, other.soStatusHistId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.soId, other.soId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.soStatusId, other.soStatusId)) {
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
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.soStatusHistId),
               HashCodeAssistant.hashObject(this.soId),
               HashCodeAssistant.hashObject(this.soStatusId),
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
   return "SalesOrderStatusHist [soStatusHistId=" + soStatusHistId + 
          ", soId=" + soId + 
          ", soStatusId=" + soStatusId + 
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