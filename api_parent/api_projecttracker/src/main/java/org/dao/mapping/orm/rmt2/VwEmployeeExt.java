package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;

import com.api.persistence.db.orm.OrmBean;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.SystemException;


/**
 * Peer object that maps to the vw_employee_ext database table/view.
 *
 * @author auto generated.
 */
public class VwEmployeeExt extends OrmBean {




	// Property name constants that belong to respective DataSource, VwEmployeeExtView

/** The property name constant equivalent to property, EmployeeId, of respective DataSource view. */
  public static final String PROP_EMPLOYEEID = "EmployeeId";
/** The property name constant equivalent to property, LoginId, of respective DataSource view. */
  public static final String PROP_LOGINID = "LoginId";
/** The property name constant equivalent to property, LoginName, of respective DataSource view. */
  public static final String PROP_LOGINNAME = "LoginName";
/** The property name constant equivalent to property, StartDate, of respective DataSource view. */
  public static final String PROP_STARTDATE = "StartDate";
/** The property name constant equivalent to property, TerminationDate, of respective DataSource view. */
  public static final String PROP_TERMINATIONDATE = "TerminationDate";
/** The property name constant equivalent to property, TitleId, of respective DataSource view. */
  public static final String PROP_TITLEID = "TitleId";
/** The property name constant equivalent to property, TypeId, of respective DataSource view. */
  public static final String PROP_TYPEID = "TypeId";
/** The property name constant equivalent to property, ManagerId, of respective DataSource view. */
  public static final String PROP_MANAGERID = "ManagerId";
/** The property name constant equivalent to property, Firstname, of respective DataSource view. */
  public static final String PROP_FIRSTNAME = "Firstname";
/** The property name constant equivalent to property, Lastname, of respective DataSource view. */
  public static final String PROP_LASTNAME = "Lastname";
/** The property name constant equivalent to property, Shortname, of respective DataSource view. */
  public static final String PROP_SHORTNAME = "Shortname";
/** The property name constant equivalent to property, CompanyName, of respective DataSource view. */
  public static final String PROP_COMPANYNAME = "CompanyName";
/** The property name constant equivalent to property, Ssn, of respective DataSource view. */
  public static final String PROP_SSN = "Ssn";
/** The property name constant equivalent to property, IsManager, of respective DataSource view. */
  public static final String PROP_ISMANAGER = "IsManager";
/** The property name constant equivalent to property, Email, of respective DataSource view. */
  public static final String PROP_EMAIL = "Email";
/** The property name constant equivalent to property, EmployeeTitle, of respective DataSource view. */
  public static final String PROP_EMPLOYEETITLE = "EmployeeTitle";
/** The property name constant equivalent to property, EmployeeType, of respective DataSource view. */
  public static final String PROP_EMPLOYEETYPE = "EmployeeType";



	/** The javabean property equivalent of database column vw_employee_ext.employee_id */
  private int employeeId;
/** The javabean property equivalent of database column vw_employee_ext.login_id */
  private int loginId;
/** The javabean property equivalent of database column vw_employee_ext.login_name */
  private String loginName;
/** The javabean property equivalent of database column vw_employee_ext.start_date */
  private java.util.Date startDate;
/** The javabean property equivalent of database column vw_employee_ext.termination_date */
  private java.util.Date terminationDate;
/** The javabean property equivalent of database column vw_employee_ext.title_id */
  private int titleId;
/** The javabean property equivalent of database column vw_employee_ext.type_id */
  private int typeId;
/** The javabean property equivalent of database column vw_employee_ext.manager_id */
  private int managerId;
/** The javabean property equivalent of database column vw_employee_ext.firstname */
  private String firstname;
/** The javabean property equivalent of database column vw_employee_ext.lastname */
  private String lastname;
/** The javabean property equivalent of database column vw_employee_ext.shortname */
  private String shortname;
/** The javabean property equivalent of database column vw_employee_ext.company_name */
  private String companyName;
/** The javabean property equivalent of database column vw_employee_ext.ssn */
  private String ssn;
/** The javabean property equivalent of database column vw_employee_ext.is_manager */
  private int isManager;
/** The javabean property equivalent of database column vw_employee_ext.email */
  private String email;
/** The javabean property equivalent of database column vw_employee_ext.employee_title */
  private String employeeTitle;
/** The javabean property equivalent of database column vw_employee_ext.employee_type */
  private String employeeType;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwEmployeeExt() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable employeeId
 */
  public void setEmployeeId(int value) {
    this.employeeId = value;
  }
/**
 * Gets the value of member variable employeeId
 */
  public int getEmployeeId() {
    return this.employeeId;
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
 * Sets the value of member variable titleId
 */
  public void setTitleId(int value) {
    this.titleId = value;
  }
/**
 * Gets the value of member variable titleId
 */
  public int getTitleId() {
    return this.titleId;
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
 * Sets the value of member variable shortname
 */
  public void setShortname(String value) {
    this.shortname = value;
  }
/**
 * Gets the value of member variable shortname
 */
  public String getShortname() {
    return this.shortname;
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
 * Sets the value of member variable employeeTitle
 */
  public void setEmployeeTitle(String value) {
    this.employeeTitle = value;
  }
/**
 * Gets the value of member variable employeeTitle
 */
  public String getEmployeeTitle() {
    return this.employeeTitle;
  }
/**
 * Sets the value of member variable employeeType
 */
  public void setEmployeeType(String value) {
    this.employeeType = value;
  }
/**
 * Gets the value of member variable employeeType
 */
  public String getEmployeeType() {
    return this.employeeType;
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
   final VwEmployeeExt other = (VwEmployeeExt) obj; 
   if (EqualityAssistant.notEqual(this.employeeId, other.employeeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.loginId, other.loginId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.loginName, other.loginName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.startDate, other.startDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.terminationDate, other.terminationDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.titleId, other.titleId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.typeId, other.typeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.managerId, other.managerId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.firstname, other.firstname)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.lastname, other.lastname)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.shortname, other.shortname)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.companyName, other.companyName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.ssn, other.ssn)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.isManager, other.isManager)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.email, other.email)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.employeeTitle, other.employeeTitle)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.employeeType, other.employeeType)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.employeeId),
               HashCodeAssistant.hashObject(this.loginId),
               HashCodeAssistant.hashObject(this.loginName),
               HashCodeAssistant.hashObject(this.startDate),
               HashCodeAssistant.hashObject(this.terminationDate),
               HashCodeAssistant.hashObject(this.titleId),
               HashCodeAssistant.hashObject(this.typeId),
               HashCodeAssistant.hashObject(this.managerId),
               HashCodeAssistant.hashObject(this.firstname),
               HashCodeAssistant.hashObject(this.lastname),
               HashCodeAssistant.hashObject(this.shortname),
               HashCodeAssistant.hashObject(this.companyName),
               HashCodeAssistant.hashObject(this.ssn),
               HashCodeAssistant.hashObject(this.isManager),
               HashCodeAssistant.hashObject(this.email),
               HashCodeAssistant.hashObject(this.employeeTitle),
               HashCodeAssistant.hashObject(this.employeeType));
} 

@Override
public String toString() {
   return "VwEmployeeExt [employeeId=" + employeeId + 
          ", loginId=" + loginId + 
          ", loginName=" + loginName + 
          ", startDate=" + startDate + 
          ", terminationDate=" + terminationDate + 
          ", titleId=" + titleId + 
          ", typeId=" + typeId + 
          ", managerId=" + managerId + 
          ", firstname=" + firstname + 
          ", lastname=" + lastname + 
          ", shortname=" + shortname + 
          ", companyName=" + companyName + 
          ", ssn=" + ssn + 
          ", isManager=" + isManager + 
          ", email=" + email + 
          ", employeeTitle=" + employeeTitle + 
          ", employeeType=" + employeeType  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}