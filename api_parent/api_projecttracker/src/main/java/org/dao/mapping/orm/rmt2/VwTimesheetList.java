package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;

import com.api.persistence.db.orm.OrmBean;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.SystemException;


/**
 * Peer object that maps to the vw_timesheet_list database table/view.
 *
 * @author auto generated.
 */
public class VwTimesheetList extends OrmBean {




	// Property name constants that belong to respective DataSource, VwTimesheetListView

/** The property name constant equivalent to property, TimesheetId, of respective DataSource view. */
  public static final String PROP_TIMESHEETID = "TimesheetId";
/** The property name constant equivalent to property, ClientId, of respective DataSource view. */
  public static final String PROP_CLIENTID = "ClientId";
/** The property name constant equivalent to property, EmpId, of respective DataSource view. */
  public static final String PROP_EMPID = "EmpId";
/** The property name constant equivalent to property, DisplayValue, of respective DataSource view. */
  public static final String PROP_DISPLAYVALUE = "DisplayValue";
/** The property name constant equivalent to property, BeginPeriod, of respective DataSource view. */
  public static final String PROP_BEGINPERIOD = "BeginPeriod";
/** The property name constant equivalent to property, EndPeriod, of respective DataSource view. */
  public static final String PROP_ENDPERIOD = "EndPeriod";
/** The property name constant equivalent to property, InvoiceRefNo, of respective DataSource view. */
  public static final String PROP_INVOICEREFNO = "InvoiceRefNo";
/** The property name constant equivalent to property, ExtRef, of respective DataSource view. */
  public static final String PROP_EXTREF = "ExtRef";
/** The property name constant equivalent to property, ProjId, of respective DataSource view. */
  public static final String PROP_PROJID = "ProjId";
/** The property name constant equivalent to property, Comments, of respective DataSource view. */
  public static final String PROP_COMMENTS = "Comments";
/** The property name constant equivalent to property, DocumentId, of respective DataSource view. */
  public static final String PROP_DOCUMENTID = "DocumentId";
/** The property name constant equivalent to property, TimesheetStatusId, of respective DataSource view. */
  public static final String PROP_TIMESHEETSTATUSID = "TimesheetStatusId";
/** The property name constant equivalent to property, StatusEffectiveDate, of respective DataSource view. */
  public static final String PROP_STATUSEFFECTIVEDATE = "StatusEffectiveDate";
/** The property name constant equivalent to property, StatusEndDate, of respective DataSource view. */
  public static final String PROP_STATUSENDDATE = "StatusEndDate";
/** The property name constant equivalent to property, ProjTimesheetHistId, of respective DataSource view. */
  public static final String PROP_PROJTIMESHEETHISTID = "ProjTimesheetHistId";
/** The property name constant equivalent to property, StatusName, of respective DataSource view. */
  public static final String PROP_STATUSNAME = "StatusName";
/** The property name constant equivalent to property, StatusDescription, of respective DataSource view. */
  public static final String PROP_STATUSDESCRIPTION = "StatusDescription";
/** The property name constant equivalent to property, TypeId, of respective DataSource view. */
  public static final String PROP_TYPEID = "TypeId";
/** The property name constant equivalent to property, Firstname, of respective DataSource view. */
  public static final String PROP_FIRSTNAME = "Firstname";
/** The property name constant equivalent to property, Lastname, of respective DataSource view. */
  public static final String PROP_LASTNAME = "Lastname";
/** The property name constant equivalent to property, ManagerId, of respective DataSource view. */
  public static final String PROP_MANAGERID = "ManagerId";
/** The property name constant equivalent to property, HourlyOverRate, of respective DataSource view. */
  public static final String PROP_HOURLYOVERRATE = "HourlyOverRate";
/** The property name constant equivalent to property, HourlyRate, of respective DataSource view. */
  public static final String PROP_HOURLYRATE = "HourlyRate";
/** The property name constant equivalent to property, LastFirstName, of respective DataSource view. */
  public static final String PROP_LASTFIRSTNAME = "LastFirstName";
/** The property name constant equivalent to property, ClientName, of respective DataSource view. */
  public static final String PROP_CLIENTNAME = "ClientName";
/** The property name constant equivalent to property, AccountNo, of respective DataSource view. */
  public static final String PROP_ACCOUNTNO = "AccountNo";
/** The property name constant equivalent to property, BillHrs, of respective DataSource view. */
  public static final String PROP_BILLHRS = "BillHrs";
/** The property name constant equivalent to property, NonBillHrs, of respective DataSource view. */
  public static final String PROP_NONBILLHRS = "NonBillHrs";



	/** The javabean property equivalent of database column vw_timesheet_list.timesheet_id */
  private int timesheetId;
/** The javabean property equivalent of database column vw_timesheet_list.client_id */
  private int clientId;
/** The javabean property equivalent of database column vw_timesheet_list.emp_id */
  private int empId;
/** The javabean property equivalent of database column vw_timesheet_list.display_value */
  private String displayValue;
/** The javabean property equivalent of database column vw_timesheet_list.begin_period */
  private java.util.Date beginPeriod;
/** The javabean property equivalent of database column vw_timesheet_list.end_period */
  private java.util.Date endPeriod;
/** The javabean property equivalent of database column vw_timesheet_list.invoice_ref_no */
  private String invoiceRefNo;
/** The javabean property equivalent of database column vw_timesheet_list.ext_ref */
  private String extRef;
/** The javabean property equivalent of database column vw_timesheet_list.proj_id */
  private int projId;
/** The javabean property equivalent of database column vw_timesheet_list.comments */
  private String comments;
/** The javabean property equivalent of database column vw_timesheet_list.document_id */
  private int documentId;
/** The javabean property equivalent of database column vw_timesheet_list.timesheet_status_id */
  private int timesheetStatusId;
/** The javabean property equivalent of database column vw_timesheet_list.status_effective_date */
  private java.util.Date statusEffectiveDate;
/** The javabean property equivalent of database column vw_timesheet_list.status_end_date */
  private java.util.Date statusEndDate;
/** The javabean property equivalent of database column vw_timesheet_list.proj_timesheet_hist_id */
  private int projTimesheetHistId;
/** The javabean property equivalent of database column vw_timesheet_list.status_name */
  private String statusName;
/** The javabean property equivalent of database column vw_timesheet_list.status_description */
  private String statusDescription;
/** The javabean property equivalent of database column vw_timesheet_list.type_id */
  private int typeId;
/** The javabean property equivalent of database column vw_timesheet_list.firstname */
  private String firstname;
/** The javabean property equivalent of database column vw_timesheet_list.lastname */
  private String lastname;
/** The javabean property equivalent of database column vw_timesheet_list.manager_id */
  private int managerId;
/** The javabean property equivalent of database column vw_timesheet_list.hourly_over_rate */
  private double hourlyOverRate;
/** The javabean property equivalent of database column vw_timesheet_list.hourly_rate */
  private double hourlyRate;
/** The javabean property equivalent of database column vw_timesheet_list.last_first_name */
  private String lastFirstName;
/** The javabean property equivalent of database column vw_timesheet_list.client_name */
  private String clientName;
/** The javabean property equivalent of database column vw_timesheet_list.account_no */
  private String accountNo;
/** The javabean property equivalent of database column vw_timesheet_list.bill_hrs */
  private double billHrs;
/** The javabean property equivalent of database column vw_timesheet_list.non_bill_hrs */
  private double nonBillHrs;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwTimesheetList() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable timesheetId
 */
  public void setTimesheetId(int value) {
    this.timesheetId = value;
  }
/**
 * Gets the value of member variable timesheetId
 */
  public int getTimesheetId() {
    return this.timesheetId;
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
 * Sets the value of member variable empId
 */
  public void setEmpId(int value) {
    this.empId = value;
  }
/**
 * Gets the value of member variable empId
 */
  public int getEmpId() {
    return this.empId;
  }
/**
 * Sets the value of member variable displayValue
 */
  public void setDisplayValue(String value) {
    this.displayValue = value;
  }
/**
 * Gets the value of member variable displayValue
 */
  public String getDisplayValue() {
    return this.displayValue;
  }
/**
 * Sets the value of member variable beginPeriod
 */
  public void setBeginPeriod(java.util.Date value) {
    this.beginPeriod = value;
  }
/**
 * Gets the value of member variable beginPeriod
 */
  public java.util.Date getBeginPeriod() {
    return this.beginPeriod;
  }
/**
 * Sets the value of member variable endPeriod
 */
  public void setEndPeriod(java.util.Date value) {
    this.endPeriod = value;
  }
/**
 * Gets the value of member variable endPeriod
 */
  public java.util.Date getEndPeriod() {
    return this.endPeriod;
  }
/**
 * Sets the value of member variable invoiceRefNo
 */
  public void setInvoiceRefNo(String value) {
    this.invoiceRefNo = value;
  }
/**
 * Gets the value of member variable invoiceRefNo
 */
  public String getInvoiceRefNo() {
    return this.invoiceRefNo;
  }
/**
 * Sets the value of member variable extRef
 */
  public void setExtRef(String value) {
    this.extRef = value;
  }
/**
 * Gets the value of member variable extRef
 */
  public String getExtRef() {
    return this.extRef;
  }
/**
 * Sets the value of member variable projId
 */
  public void setProjId(int value) {
    this.projId = value;
  }
/**
 * Gets the value of member variable projId
 */
  public int getProjId() {
    return this.projId;
  }
/**
 * Sets the value of member variable comments
 */
  public void setComments(String value) {
    this.comments = value;
  }
/**
 * Gets the value of member variable comments
 */
  public String getComments() {
    return this.comments;
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
 * Sets the value of member variable timesheetStatusId
 */
  public void setTimesheetStatusId(int value) {
    this.timesheetStatusId = value;
  }
/**
 * Gets the value of member variable timesheetStatusId
 */
  public int getTimesheetStatusId() {
    return this.timesheetStatusId;
  }
/**
 * Sets the value of member variable statusEffectiveDate
 */
  public void setStatusEffectiveDate(java.util.Date value) {
    this.statusEffectiveDate = value;
  }
/**
 * Gets the value of member variable statusEffectiveDate
 */
  public java.util.Date getStatusEffectiveDate() {
    return this.statusEffectiveDate;
  }
/**
 * Sets the value of member variable statusEndDate
 */
  public void setStatusEndDate(java.util.Date value) {
    this.statusEndDate = value;
  }
/**
 * Gets the value of member variable statusEndDate
 */
  public java.util.Date getStatusEndDate() {
    return this.statusEndDate;
  }
/**
 * Sets the value of member variable projTimesheetHistId
 */
  public void setProjTimesheetHistId(int value) {
    this.projTimesheetHistId = value;
  }
/**
 * Gets the value of member variable projTimesheetHistId
 */
  public int getProjTimesheetHistId() {
    return this.projTimesheetHistId;
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
 * Sets the value of member variable typeId
 */
  public void setTypeId(int value) {
    this.typeId = value;
  }
/**
 * Gets the value of member variable typeId
 */
  public int getTypeId() {
    return this.typeId;
  }
/**
 * Sets the value of member variable firstname
 */
  public void setFirstname(String value) {
    this.firstname = value;
  }
/**
 * Gets the value of member variable firstname
 */
  public String getFirstname() {
    return this.firstname;
  }
/**
 * Sets the value of member variable lastname
 */
  public void setLastname(String value) {
    this.lastname = value;
  }
/**
 * Gets the value of member variable lastname
 */
  public String getLastname() {
    return this.lastname;
  }
/**
 * Sets the value of member variable managerId
 */
  public void setManagerId(int value) {
    this.managerId = value;
  }
/**
 * Gets the value of member variable managerId
 */
  public int getManagerId() {
    return this.managerId;
  }
/**
 * Sets the value of member variable hourlyOverRate
 */
  public void setHourlyOverRate(double value) {
    this.hourlyOverRate = value;
  }
/**
 * Gets the value of member variable hourlyOverRate
 */
  public double getHourlyOverRate() {
    return this.hourlyOverRate;
  }
/**
 * Sets the value of member variable hourlyRate
 */
  public void setHourlyRate(double value) {
    this.hourlyRate = value;
  }
/**
 * Gets the value of member variable hourlyRate
 */
  public double getHourlyRate() {
    return this.hourlyRate;
  }
/**
 * Sets the value of member variable lastFirstName
 */
  public void setLastFirstName(String value) {
    this.lastFirstName = value;
  }
/**
 * Gets the value of member variable lastFirstName
 */
  public String getLastFirstName() {
    return this.lastFirstName;
  }
/**
 * Sets the value of member variable clientName
 */
  public void setClientName(String value) {
    this.clientName = value;
  }
/**
 * Gets the value of member variable clientName
 */
  public String getClientName() {
    return this.clientName;
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
 * Sets the value of member variable billHrs
 */
  public void setBillHrs(double value) {
    this.billHrs = value;
  }
/**
 * Gets the value of member variable billHrs
 */
  public double getBillHrs() {
    return this.billHrs;
  }
/**
 * Sets the value of member variable nonBillHrs
 */
  public void setNonBillHrs(double value) {
    this.nonBillHrs = value;
  }
/**
 * Gets the value of member variable nonBillHrs
 */
  public double getNonBillHrs() {
    return this.nonBillHrs;
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
   final VwTimesheetList other = (VwTimesheetList) obj; 
   if (EqualityAssistant.notEqual(this.timesheetId, other.timesheetId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.clientId, other.clientId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.empId, other.empId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.displayValue, other.displayValue)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.beginPeriod, other.beginPeriod)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.endPeriod, other.endPeriod)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.invoiceRefNo, other.invoiceRefNo)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.extRef, other.extRef)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.projId, other.projId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.comments, other.comments)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.documentId, other.documentId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.timesheetStatusId, other.timesheetStatusId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.statusEffectiveDate, other.statusEffectiveDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.statusEndDate, other.statusEndDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.projTimesheetHistId, other.projTimesheetHistId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.statusName, other.statusName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.statusDescription, other.statusDescription)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.typeId, other.typeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.firstname, other.firstname)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.lastname, other.lastname)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.managerId, other.managerId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.hourlyOverRate, other.hourlyOverRate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.hourlyRate, other.hourlyRate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.lastFirstName, other.lastFirstName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.clientName, other.clientName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.accountNo, other.accountNo)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.billHrs, other.billHrs)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.nonBillHrs, other.nonBillHrs)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.timesheetId),
               HashCodeAssistant.hashObject(this.clientId),
               HashCodeAssistant.hashObject(this.empId),
               HashCodeAssistant.hashObject(this.displayValue),
               HashCodeAssistant.hashObject(this.beginPeriod),
               HashCodeAssistant.hashObject(this.endPeriod),
               HashCodeAssistant.hashObject(this.invoiceRefNo),
               HashCodeAssistant.hashObject(this.extRef),
               HashCodeAssistant.hashObject(this.projId),
               HashCodeAssistant.hashObject(this.comments),
               HashCodeAssistant.hashObject(this.documentId),
               HashCodeAssistant.hashObject(this.timesheetStatusId),
               HashCodeAssistant.hashObject(this.statusEffectiveDate),
               HashCodeAssistant.hashObject(this.statusEndDate),
               HashCodeAssistant.hashObject(this.projTimesheetHistId),
               HashCodeAssistant.hashObject(this.statusName),
               HashCodeAssistant.hashObject(this.statusDescription),
               HashCodeAssistant.hashObject(this.typeId),
               HashCodeAssistant.hashObject(this.firstname),
               HashCodeAssistant.hashObject(this.lastname),
               HashCodeAssistant.hashObject(this.managerId),
               HashCodeAssistant.hashObject(this.hourlyOverRate),
               HashCodeAssistant.hashObject(this.hourlyRate),
               HashCodeAssistant.hashObject(this.lastFirstName),
               HashCodeAssistant.hashObject(this.clientName),
               HashCodeAssistant.hashObject(this.accountNo),
               HashCodeAssistant.hashObject(this.billHrs),
               HashCodeAssistant.hashObject(this.nonBillHrs));
} 

@Override
public String toString() {
   return "VwTimesheetList [timesheetId=" + timesheetId + 
          ", clientId=" + clientId + 
          ", empId=" + empId + 
          ", displayValue=" + displayValue + 
          ", beginPeriod=" + beginPeriod + 
          ", endPeriod=" + endPeriod + 
          ", invoiceRefNo=" + invoiceRefNo + 
          ", extRef=" + extRef + 
          ", projId=" + projId + 
          ", comments=" + comments + 
          ", documentId=" + documentId + 
          ", timesheetStatusId=" + timesheetStatusId + 
          ", statusEffectiveDate=" + statusEffectiveDate + 
          ", statusEndDate=" + statusEndDate + 
          ", projTimesheetHistId=" + projTimesheetHistId + 
          ", statusName=" + statusName + 
          ", statusDescription=" + statusDescription + 
          ", typeId=" + typeId + 
          ", firstname=" + firstname + 
          ", lastname=" + lastname + 
          ", managerId=" + managerId + 
          ", hourlyOverRate=" + hourlyOverRate + 
          ", hourlyRate=" + hourlyRate + 
          ", lastFirstName=" + lastFirstName + 
          ", clientName=" + clientName + 
          ", accountNo=" + accountNo + 
          ", billHrs=" + billHrs + 
          ", nonBillHrs=" + nonBillHrs  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}