package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the vw_client_timesheet_summary database table/view.
 *
 * @author auto generated.
 */
public class VwClientTimesheetSummary extends OrmBean {




	// Property name constants that belong to respective DataSource, VwClientTimesheetSummaryView

/** The property name constant equivalent to property, ClientId, of respective DataSource view. */
  public static final String PROP_CLIENTID = "ClientId";
/** The property name constant equivalent to property, BusinessId, of respective DataSource view. */
  public static final String PROP_BUSINESSID = "BusinessId";
/** The property name constant equivalent to property, Name, of respective DataSource view. */
  public static final String PROP_NAME = "Name";
/** The property name constant equivalent to property, AccountNo, of respective DataSource view. */
  public static final String PROP_ACCOUNTNO = "AccountNo";
/** The property name constant equivalent to property, StatusId, of respective DataSource view. */
  public static final String PROP_STATUSID = "StatusId";
/** The property name constant equivalent to property, StatusName, of respective DataSource view. */
  public static final String PROP_STATUSNAME = "StatusName";
/** The property name constant equivalent to property, StatusDescr, of respective DataSource view. */
  public static final String PROP_STATUSDESCR = "StatusDescr";
/** The property name constant equivalent to property, TimesheetCount, of respective DataSource view. */
  public static final String PROP_TIMESHEETCOUNT = "TimesheetCount";



	/** The javabean property equivalent of database column vw_client_timesheet_summary.client_id */
  private int clientId;
/** The javabean property equivalent of database column vw_client_timesheet_summary.business_id */
  private int businessId;
/** The javabean property equivalent of database column vw_client_timesheet_summary.name */
  private String name;
/** The javabean property equivalent of database column vw_client_timesheet_summary.account_no */
  private String accountNo;
/** The javabean property equivalent of database column vw_client_timesheet_summary.status_id */
  private int statusId;
/** The javabean property equivalent of database column vw_client_timesheet_summary.status_name */
  private String statusName;
/** The javabean property equivalent of database column vw_client_timesheet_summary.status_descr */
  private String statusDescr;
/** The javabean property equivalent of database column vw_client_timesheet_summary.timesheet_count */
  private int timesheetCount;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwClientTimesheetSummary() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable clientId
 */
  public void setClientId(int value) {
    this.clientId = value;
  }
/**
 * Gets the value of member variable clientId
 */
  public int getClientId() {
    return this.clientId;
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
 * Sets the value of member variable name
 */
  public void setName(String value) {
    this.name = value;
  }
/**
 * Gets the value of member variable name
 */
  public String getName() {
    return this.name;
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
 * Sets the value of member variable statusName
 */
  public void setStatusName(String value) {
    this.statusName = value;
  }
/**
 * Gets the value of member variable statusName
 */
  public String getStatusName() {
    return this.statusName;
  }
/**
 * Sets the value of member variable statusDescr
 */
  public void setStatusDescr(String value) {
    this.statusDescr = value;
  }
/**
 * Gets the value of member variable statusDescr
 */
  public String getStatusDescr() {
    return this.statusDescr;
  }
/**
 * Sets the value of member variable timesheetCount
 */
  public void setTimesheetCount(int value) {
    this.timesheetCount = value;
  }
/**
 * Gets the value of member variable timesheetCount
 */
  public int getTimesheetCount() {
    return this.timesheetCount;
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
   final VwClientTimesheetSummary other = (VwClientTimesheetSummary) obj; 
   if (EqualityAssistant.notEqual(this.clientId, other.clientId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.businessId, other.businessId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.name, other.name)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.accountNo, other.accountNo)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.statusId, other.statusId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.statusName, other.statusName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.statusDescr, other.statusDescr)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.timesheetCount, other.timesheetCount)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.clientId),
   HashCodeAssistant.hashObject(this.businessId),
   HashCodeAssistant.hashObject(this.name),
   HashCodeAssistant.hashObject(this.accountNo),
   HashCodeAssistant.hashObject(this.statusId),
   HashCodeAssistant.hashObject(this.statusName),
   HashCodeAssistant.hashObject(this.statusDescr),
   HashCodeAssistant.hashObject(this.timesheetCount));
} 

@Override
public String toString() {
   return "VwClientTimesheetSummary [clientId=" + clientId + 
          ", businessId=" + businessId + 
          ", name=" + name + 
          ", accountNo=" + accountNo + 
          ", statusId=" + statusId + 
          ", statusName=" + statusName + 
          ", statusDescr=" + statusDescr + 
          ", timesheetCount=" + timesheetCount  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}