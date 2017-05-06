package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the proj_timesheet database table/view.
 *
 * @author auto generated.
 */
public class ProjTimesheet extends OrmBean {




	// Property name constants that belong to respective DataSource, ProjTimesheetView

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
/** The property name constant equivalent to property, DateCreated, of respective DataSource view. */
  public static final String PROP_DATECREATED = "DateCreated";
/** The property name constant equivalent to property, DateUpdated, of respective DataSource view. */
  public static final String PROP_DATEUPDATED = "DateUpdated";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";
/** The property name constant equivalent to property, Comments, of respective DataSource view. */
  public static final String PROP_COMMENTS = "Comments";
/** The property name constant equivalent to property, ProjId, of respective DataSource view. */
  public static final String PROP_PROJID = "ProjId";
/** The property name constant equivalent to property, ExtRef, of respective DataSource view. */
  public static final String PROP_EXTREF = "ExtRef";
/** The property name constant equivalent to property, DocumentId, of respective DataSource view. */
  public static final String PROP_DOCUMENTID = "DocumentId";
/** The property name constant equivalent to property, IpCreated, of respective DataSource view. */
  public static final String PROP_IPCREATED = "IpCreated";
/** The property name constant equivalent to property, IpUpdated, of respective DataSource view. */
  public static final String PROP_IPUPDATED = "IpUpdated";



	/** The javabean property equivalent of database column proj_timesheet.timesheet_id */
  private int timesheetId;
/** The javabean property equivalent of database column proj_timesheet.client_id */
  private int clientId;
/** The javabean property equivalent of database column proj_timesheet.emp_id */
  private int empId;
/** The javabean property equivalent of database column proj_timesheet.display_value */
  private String displayValue;
/** The javabean property equivalent of database column proj_timesheet.begin_period */
  private java.util.Date beginPeriod;
/** The javabean property equivalent of database column proj_timesheet.end_period */
  private java.util.Date endPeriod;
/** The javabean property equivalent of database column proj_timesheet.invoice_ref_no */
  private String invoiceRefNo;
/** The javabean property equivalent of database column proj_timesheet.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column proj_timesheet.date_updated */
  private java.util.Date dateUpdated;
/** The javabean property equivalent of database column proj_timesheet.user_id */
  private String userId;
/** The javabean property equivalent of database column proj_timesheet.comments */
  private String comments;
/** The javabean property equivalent of database column proj_timesheet.proj_id */
  private int projId;
/** The javabean property equivalent of database column proj_timesheet.ext_ref */
  private String extRef;
/** The javabean property equivalent of database column proj_timesheet.document_id */
  private int documentId;
/** The javabean property equivalent of database column proj_timesheet.ip_created */
  private String ipCreated;
/** The javabean property equivalent of database column proj_timesheet.ip_updated */
  private String ipUpdated;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public ProjTimesheet() throws SystemException {
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
   final ProjTimesheet other = (ProjTimesheet) obj; 
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
   if (EqualityAssistant.notEqual(this.dateCreated, other.dateCreated)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.dateUpdated, other.dateUpdated)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.userId, other.userId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.comments, other.comments)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.projId, other.projId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.extRef, other.extRef)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.documentId, other.documentId)) {
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
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.timesheetId),
   HashCodeAssistant.hashObject(this.clientId),
   HashCodeAssistant.hashObject(this.empId),
   HashCodeAssistant.hashObject(this.displayValue),
   HashCodeAssistant.hashObject(this.beginPeriod),
   HashCodeAssistant.hashObject(this.endPeriod),
   HashCodeAssistant.hashObject(this.invoiceRefNo),
   HashCodeAssistant.hashObject(this.dateCreated),
   HashCodeAssistant.hashObject(this.dateUpdated),
   HashCodeAssistant.hashObject(this.userId),
   HashCodeAssistant.hashObject(this.comments),
   HashCodeAssistant.hashObject(this.projId),
   HashCodeAssistant.hashObject(this.extRef),
   HashCodeAssistant.hashObject(this.documentId),
   HashCodeAssistant.hashObject(this.ipCreated),
   HashCodeAssistant.hashObject(this.ipUpdated));
} 

@Override
public String toString() {
   return "ProjTimesheet [timesheetId=" + timesheetId + 
          ", clientId=" + clientId + 
          ", empId=" + empId + 
          ", displayValue=" + displayValue + 
          ", beginPeriod=" + beginPeriod + 
          ", endPeriod=" + endPeriod + 
          ", invoiceRefNo=" + invoiceRefNo + 
          ", dateCreated=" + dateCreated + 
          ", dateUpdated=" + dateUpdated + 
          ", userId=" + userId + 
          ", comments=" + comments + 
          ", projId=" + projId + 
          ", extRef=" + extRef + 
          ", documentId=" + documentId + 
          ", ipCreated=" + ipCreated + 
          ", ipUpdated=" + ipUpdated  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}