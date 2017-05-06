package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the purchase_order_status_hist database table/view.
 *
 * @author auto generated.
 */
public class PurchaseOrderStatusHist extends OrmBean {




	// Property name constants that belong to respective DataSource, PurchaseOrderStatusHistView

/** The property name constant equivalent to property, PoStatusHistId, of respective DataSource view. */
  public static final String PROP_POSTATUSHISTID = "PoStatusHistId";
/** The property name constant equivalent to property, PoStatusId, of respective DataSource view. */
  public static final String PROP_POSTATUSID = "PoStatusId";
/** The property name constant equivalent to property, PoId, of respective DataSource view. */
  public static final String PROP_POID = "PoId";
/** The property name constant equivalent to property, EffectiveDate, of respective DataSource view. */
  public static final String PROP_EFFECTIVEDATE = "EffectiveDate";
/** The property name constant equivalent to property, EndDate, of respective DataSource view. */
  public static final String PROP_ENDDATE = "EndDate";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";



	/** The javabean property equivalent of database column purchase_order_status_hist.po_status_hist_id */
  private int poStatusHistId;
/** The javabean property equivalent of database column purchase_order_status_hist.po_status_id */
  private int poStatusId;
/** The javabean property equivalent of database column purchase_order_status_hist.po_id */
  private int poId;
/** The javabean property equivalent of database column purchase_order_status_hist.effective_date */
  private java.util.Date effectiveDate;
/** The javabean property equivalent of database column purchase_order_status_hist.end_date */
  private java.util.Date endDate;
/** The javabean property equivalent of database column purchase_order_status_hist.user_id */
  private String userId;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public PurchaseOrderStatusHist() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable poStatusHistId
 */
  public void setPoStatusHistId(int value) {
    this.poStatusHistId = value;
  }
/**
 * Gets the value of member variable poStatusHistId
 */
  public int getPoStatusHistId() {
    return this.poStatusHistId;
  }
/**
 * Sets the value of member variable poStatusId
 */
  public void setPoStatusId(int value) {
    this.poStatusId = value;
  }
/**
 * Gets the value of member variable poStatusId
 */
  public int getPoStatusId() {
    return this.poStatusId;
  }
/**
 * Sets the value of member variable poId
 */
  public void setPoId(int value) {
    this.poId = value;
  }
/**
 * Gets the value of member variable poId
 */
  public int getPoId() {
    return this.poId;
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
   final PurchaseOrderStatusHist other = (PurchaseOrderStatusHist) obj; 
   if (EqualityAssistant.notEqual(this.poStatusHistId, other.poStatusHistId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.poStatusId, other.poStatusId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.poId, other.poId)) {
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
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.poStatusHistId),
               HashCodeAssistant.hashObject(this.poStatusId),
               HashCodeAssistant.hashObject(this.poId),
               HashCodeAssistant.hashObject(this.effectiveDate),
               HashCodeAssistant.hashObject(this.endDate),
               HashCodeAssistant.hashObject(this.userId));
} 

@Override
public String toString() {
   return "PurchaseOrderStatusHist [poStatusHistId=" + poStatusHistId + 
          ", poStatusId=" + poStatusId + 
          ", poId=" + poId + 
          ", effectiveDate=" + effectiveDate + 
          ", endDate=" + endDate + 
          ", userId=" + userId  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}