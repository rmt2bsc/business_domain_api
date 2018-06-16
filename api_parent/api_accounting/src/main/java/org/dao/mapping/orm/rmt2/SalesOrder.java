package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the sales_order database table/view.
 *
 * @author auto generated.
 */
public class SalesOrder extends OrmBean {




	// Property name constants that belong to respective DataSource, SalesOrderView

/** The property name constant equivalent to property, SoId, of respective DataSource view. */
  public static final String PROP_SOID = "SoId";
/** The property name constant equivalent to property, CustomerId, of respective DataSource view. */
  public static final String PROP_CUSTOMERID = "CustomerId";
/** The property name constant equivalent to property, Invoiced, of respective DataSource view. */
  public static final String PROP_INVOICED = "Invoiced";
/** The property name constant equivalent to property, OrderTotal, of respective DataSource view. */
  public static final String PROP_ORDERTOTAL = "OrderTotal";
/** The property name constant equivalent to property, DateCreated, of respective DataSource view. */
  public static final String PROP_DATECREATED = "DateCreated";
/** The property name constant equivalent to property, DateUpdated, of respective DataSource view. */
  public static final String PROP_DATEUPDATED = "DateUpdated";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";
/** The property name constant equivalent to property, IpCreated, of respective DataSource view. */
  public static final String PROP_IPCREATED = "IpCreated";
/** The property name constant equivalent to property, IpUpdated, of respective DataSource view. */
  public static final String PROP_IPUPDATED = "IpUpdated";
/** The property name constant equivalent to property, EffectiveDate, of respective DataSource view. */
  public static final String PROP_EFFECTIVEDATE = "EffectiveDate";



	/** The javabean property equivalent of database column sales_order.so_id */
  private int soId;
/** The javabean property equivalent of database column sales_order.customer_id */
  private int customerId;
/** The javabean property equivalent of database column sales_order.invoiced */
  private int invoiced;
/** The javabean property equivalent of database column sales_order.order_total */
  private double orderTotal;
/** The javabean property equivalent of database column sales_order.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column sales_order.date_updated */
  private java.util.Date dateUpdated;
/** The javabean property equivalent of database column sales_order.user_id */
  private String userId;
/** The javabean property equivalent of database column sales_order.ip_created */
  private String ipCreated;
/** The javabean property equivalent of database column sales_order.ip_updated */
  private String ipUpdated;
/** The javabean property equivalent of database column sales_order.effective_date */
  private java.util.Date effectiveDate;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public SalesOrder() throws SystemException {
	super();
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
 * Sets the value of member variable customerId
 */
  public void setCustomerId(int value) {
    this.customerId = value;
  }
/**
 * Gets the value of member variable customerId
 */
  public int getCustomerId() {
    return this.customerId;
  }
/**
 * Sets the value of member variable invoiced
 */
  public void setInvoiced(int value) {
    this.invoiced = value;
  }
/**
 * Gets the value of member variable invoiced
 */
  public int getInvoiced() {
    return this.invoiced;
  }
/**
 * Sets the value of member variable orderTotal
 */
  public void setOrderTotal(double value) {
    this.orderTotal = value;
  }
/**
 * Gets the value of member variable orderTotal
 */
  public double getOrderTotal() {
    return this.orderTotal;
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
   final SalesOrder other = (SalesOrder) obj; 
   if (EqualityAssistant.notEqual(this.soId, other.soId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.customerId, other.customerId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.invoiced, other.invoiced)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.orderTotal, other.orderTotal)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.effectiveDate, other.effectiveDate)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.soId),
               HashCodeAssistant.hashObject(this.customerId),
               HashCodeAssistant.hashObject(this.invoiced),
               HashCodeAssistant.hashObject(this.orderTotal),
               HashCodeAssistant.hashObject(this.effectiveDate));
} 

@Override
public String toString() {
   return "SalesOrder [soId=" + soId + 
          ", customerId=" + customerId + 
          ", invoiced=" + invoiced + 
          ", orderTotal=" + orderTotal + 
          ", effectiveDate=" + effectiveDate  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}