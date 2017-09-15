package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the batch_log database table/view.
 *
 * @author auto generated.
 */
public class BatchLog extends OrmBean {




	// Property name constants that belong to respective DataSource, BatchLogView

/** The property name constant equivalent to property, Id, of respective DataSource view. */
  public static final String PROP_ID = "Id";
/** The property name constant equivalent to property, BatId, of respective DataSource view. */
  public static final String PROP_BATID = "BatId";
/** The property name constant equivalent to property, Msg, of respective DataSource view. */
  public static final String PROP_MSG = "Msg";
/** The property name constant equivalent to property, BatchDate, of respective DataSource view. */
  public static final String PROP_BATCHDATE = "BatchDate";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";



	/** The javabean property equivalent of database column batch_log.id */
  private int id;
/** The javabean property equivalent of database column batch_log.bat_id */
  private String batId;
/** The javabean property equivalent of database column batch_log.msg */
  private String msg;
/** The javabean property equivalent of database column batch_log.batch_date */
  private java.util.Date batchDate;
/** The javabean property equivalent of database column batch_log.user_id */
  private String userId;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public BatchLog() throws SystemException {
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
 * Sets the value of member variable batId
 */
  public void setBatId(String value) {
    this.batId = value;
  }
/**
 * Gets the value of member variable batId
 */
  public String getBatId() {
    return this.batId;
  }
/**
 * Sets the value of member variable msg
 */
  public void setMsg(String value) {
    this.msg = value;
  }
/**
 * Gets the value of member variable msg
 */
  public String getMsg() {
    return this.msg;
  }
/**
 * Sets the value of member variable batchDate
 */
  public void setBatchDate(java.util.Date value) {
    this.batchDate = value;
  }
/**
 * Gets the value of member variable batchDate
 */
  public java.util.Date getBatchDate() {
    return this.batchDate;
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
   final BatchLog other = (BatchLog) obj; 
   if (EqualityAssistant.notEqual(this.id, other.id)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.batId, other.batId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.msg, other.msg)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.batchDate, other.batchDate)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.id),
               HashCodeAssistant.hashObject(this.batId),
               HashCodeAssistant.hashObject(this.msg),
               HashCodeAssistant.hashObject(this.batchDate));
} 

@Override
public String toString() {
   return "BatchLog [id=" + id + 
          ", batId=" + batId + 
          ", msg=" + msg + 
          ", batchDate=" + batchDate  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}