package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the proj_employee database table/view.
 *
 * @author auto generated.
 */
public class ProjEmployee extends OrmBean {




	// Property name constants that belong to respective DataSource, ProjEmployeeView

/** The property name constant equivalent to property, EmpId, of respective DataSource view. */
  public static final String PROP_EMPID = "EmpId";
/** The property name constant equivalent to property, EmpTypeId, of respective DataSource view. */
  public static final String PROP_EMPTYPEID = "EmpTypeId";
/** The property name constant equivalent to property, ManagerId, of respective DataSource view. */
  public static final String PROP_MANAGERID = "ManagerId";
/** The property name constant equivalent to property, EmpTitleId, of respective DataSource view. */
  public static final String PROP_EMPTITLEID = "EmpTitleId";
/** The property name constant equivalent to property, LoginId, of respective DataSource view. */
  public static final String PROP_LOGINID = "LoginId";
/** The property name constant equivalent to property, StartDate, of respective DataSource view. */
  public static final String PROP_STARTDATE = "StartDate";
/** The property name constant equivalent to property, TerminationDate, of respective DataSource view. */
  public static final String PROP_TERMINATIONDATE = "TerminationDate";
/** The property name constant equivalent to property, DateCreated, of respective DataSource view. */
  public static final String PROP_DATECREATED = "DateCreated";
/** The property name constant equivalent to property, DateUpdated, of respective DataSource view. */
  public static final String PROP_DATEUPDATED = "DateUpdated";
/** The property name constant equivalent to property, UserId, of respective DataSource view. */
  public static final String PROP_USERID = "UserId";
/** The property name constant equivalent to property, Firstname, of respective DataSource view. */
  public static final String PROP_FIRSTNAME = "Firstname";
/** The property name constant equivalent to property, Lastname, of respective DataSource view. */
  public static final String PROP_LASTNAME = "Lastname";
/** The property name constant equivalent to property, Ssn, of respective DataSource view. */
  public static final String PROP_SSN = "Ssn";
/** The property name constant equivalent to property, Email, of respective DataSource view. */
  public static final String PROP_EMAIL = "Email";
/** The property name constant equivalent to property, LoginName, of respective DataSource view. */
  public static final String PROP_LOGINNAME = "LoginName";
/** The property name constant equivalent to property, CompanyName, of respective DataSource view. */
  public static final String PROP_COMPANYNAME = "CompanyName";
/** The property name constant equivalent to property, IsManager, of respective DataSource view. */
  public static final String PROP_ISMANAGER = "IsManager";



	/** The javabean property equivalent of database column proj_employee.emp_id */
  private int empId;
/** The javabean property equivalent of database column proj_employee.emp_type_id */
  private int empTypeId;
/** The javabean property equivalent of database column proj_employee.manager_id */
  private int managerId;
/** The javabean property equivalent of database column proj_employee.emp_title_id */
  private int empTitleId;
/** The javabean property equivalent of database column proj_employee.login_id */
  private int loginId;
/** The javabean property equivalent of database column proj_employee.start_date */
  private java.util.Date startDate;
/** The javabean property equivalent of database column proj_employee.termination_date */
  private java.util.Date terminationDate;
/** The javabean property equivalent of database column proj_employee.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column proj_employee.date_updated */
  private java.util.Date dateUpdated;
/** The javabean property equivalent of database column proj_employee.user_id */
  private String userId;
/** The javabean property equivalent of database column proj_employee.firstname */
  private String firstname;
/** The javabean property equivalent of database column proj_employee.lastname */
  private String lastname;
/** The javabean property equivalent of database column proj_employee.ssn */
  private String ssn;
/** The javabean property equivalent of database column proj_employee.email */
  private String email;
/** The javabean property equivalent of database column proj_employee.login_name */
  private String loginName;
/** The javabean property equivalent of database column proj_employee.company_name */
  private String companyName;
/** The javabean property equivalent of database column proj_employee.is_manager */
  private int isManager;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public ProjEmployee() throws SystemException {
	super();
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
 * Sets the value of member variable empTypeId
 */
  public void setEmpTypeId(int value) {
    this.empTypeId = value;
  }
/**
 * Gets the value of member variable empTypeId
 */
  public int getEmpTypeId() {
    return this.empTypeId;
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
 * Sets the value of member variable empTitleId
 */
  public void setEmpTitleId(int value) {
    this.empTitleId = value;
  }
/**
 * Gets the value of member variable empTitleId
 */
  public int getEmpTitleId() {
    return this.empTitleId;
  }
/**
 * Sets the value of member variable loginId
 */
  public void setLoginId(int value) {
    this.loginId = value;
  }
/**
 * Gets the value of member variable loginId
 */
  public int getLoginId() {
    return this.loginId;
  }
/**
 * Sets the value of member variable startDate
 */
  public void setStartDate(java.util.Date value) {
    this.startDate = value;
  }
/**
 * Gets the value of member variable startDate
 */
  public java.util.Date getStartDate() {
    return this.startDate;
  }
/**
 * Sets the value of member variable terminationDate
 */
  public void setTerminationDate(java.util.Date value) {
    this.terminationDate = value;
  }
/**
 * Gets the value of member variable terminationDate
 */
  public java.util.Date getTerminationDate() {
    return this.terminationDate;
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
 * Sets the value of member variable ssn
 */
  public void setSsn(String value) {
    this.ssn = value;
  }
/**
 * Gets the value of member variable ssn
 */
  public String getSsn() {
    return this.ssn;
  }
/**
 * Sets the value of member variable email
 */
  public void setEmail(String value) {
    this.email = value;
  }
/**
 * Gets the value of member variable email
 */
  public String getEmail() {
    return this.email;
  }
/**
 * Sets the value of member variable loginName
 */
  public void setLoginName(String value) {
    this.loginName = value;
  }
/**
 * Gets the value of member variable loginName
 */
  public String getLoginName() {
    return this.loginName;
  }
/**
 * Sets the value of member variable companyName
 */
  public void setCompanyName(String value) {
    this.companyName = value;
  }
/**
 * Gets the value of member variable companyName
 */
  public String getCompanyName() {
    return this.companyName;
  }
/**
 * Sets the value of member variable isManager
 */
  public void setIsManager(int value) {
    this.isManager = value;
  }
/**
 * Gets the value of member variable isManager
 */
  public int getIsManager() {
    return this.isManager;
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
   final ProjEmployee other = (ProjEmployee) obj; 
   if (EqualityAssistant.notEqual(this.empId, other.empId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.empTypeId, other.empTypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.managerId, other.managerId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.empTitleId, other.empTitleId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.loginId, other.loginId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.startDate, other.startDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.terminationDate, other.terminationDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.firstname, other.firstname)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.lastname, other.lastname)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.ssn, other.ssn)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.email, other.email)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.loginName, other.loginName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.companyName, other.companyName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.isManager, other.isManager)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.empId),
               HashCodeAssistant.hashObject(this.empTypeId),
               HashCodeAssistant.hashObject(this.managerId),
               HashCodeAssistant.hashObject(this.empTitleId),
               HashCodeAssistant.hashObject(this.loginId),
               HashCodeAssistant.hashObject(this.startDate),
               HashCodeAssistant.hashObject(this.terminationDate),
               HashCodeAssistant.hashObject(this.firstname),
               HashCodeAssistant.hashObject(this.lastname),
               HashCodeAssistant.hashObject(this.ssn),
               HashCodeAssistant.hashObject(this.email),
               HashCodeAssistant.hashObject(this.loginName),
               HashCodeAssistant.hashObject(this.companyName),
               HashCodeAssistant.hashObject(this.isManager));
} 

@Override
public String toString() {
   return "ProjEmployee [empId=" + empId + 
          ", empTypeId=" + empTypeId + 
          ", managerId=" + managerId + 
          ", empTitleId=" + empTitleId + 
          ", loginId=" + loginId + 
          ", startDate=" + startDate + 
          ", terminationDate=" + terminationDate + 
          ", firstname=" + firstname + 
          ", lastname=" + lastname + 
          ", ssn=" + ssn + 
          ", email=" + email + 
          ", loginName=" + loginName + 
          ", companyName=" + companyName + 
          ", isManager=" + isManager  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}