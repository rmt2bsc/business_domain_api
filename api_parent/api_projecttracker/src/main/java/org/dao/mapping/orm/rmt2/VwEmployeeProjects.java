package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the vw_employee_projects database table/view.
 *
 * @author auto generated.
 */
public class VwEmployeeProjects extends OrmBean {




	// Property name constants that belong to respective DataSource, VwEmployeeProjectsView

/** The property name constant equivalent to property, EmpProjId, of respective DataSource view. */
  public static final String PROP_EMPPROJID = "EmpProjId";
/** The property name constant equivalent to property, EmpId, of respective DataSource view. */
  public static final String PROP_EMPID = "EmpId";
/** The property name constant equivalent to property, ProjId, of respective DataSource view. */
  public static final String PROP_PROJID = "ProjId";
/** The property name constant equivalent to property, ProjempEffectiveDate, of respective DataSource view. */
  public static final String PROP_PROJEMPEFFECTIVEDATE = "ProjempEffectiveDate";
/** The property name constant equivalent to property, ProjempEndDate, of respective DataSource view. */
  public static final String PROP_PROJEMPENDDATE = "ProjempEndDate";
/** The property name constant equivalent to property, PayRate, of respective DataSource view. */
  public static final String PROP_PAYRATE = "PayRate";
/** The property name constant equivalent to property, OtPayRate, of respective DataSource view. */
  public static final String PROP_OTPAYRATE = "OtPayRate";
/** The property name constant equivalent to property, FlatRate, of respective DataSource view. */
  public static final String PROP_FLATRATE = "FlatRate";
/** The property name constant equivalent to property, Comments, of respective DataSource view. */
  public static final String PROP_COMMENTS = "Comments";
/** The property name constant equivalent to property, ClientId, of respective DataSource view. */
  public static final String PROP_CLIENTID = "ClientId";
/** The property name constant equivalent to property, ProjectName, of respective DataSource view. */
  public static final String PROP_PROJECTNAME = "ProjectName";
/** The property name constant equivalent to property, ProjEffectiveDate, of respective DataSource view. */
  public static final String PROP_PROJEFFECTIVEDATE = "ProjEffectiveDate";
/** The property name constant equivalent to property, ProjEndDate, of respective DataSource view. */
  public static final String PROP_PROJENDDATE = "ProjEndDate";
/** The property name constant equivalent to property, ClientName, of respective DataSource view. */
  public static final String PROP_CLIENTNAME = "ClientName";
/** The property name constant equivalent to property, AccountNo, of respective DataSource view. */
  public static final String PROP_ACCOUNTNO = "AccountNo";
/** The property name constant equivalent to property, BusinessId, of respective DataSource view. */
  public static final String PROP_BUSINESSID = "BusinessId";
/** The property name constant equivalent to property, ClientBillRate, of respective DataSource view. */
  public static final String PROP_CLIENTBILLRATE = "ClientBillRate";
/** The property name constant equivalent to property, ClientOtBillRate, of respective DataSource view. */
  public static final String PROP_CLIENTOTBILLRATE = "ClientOtBillRate";



	/** The javabean property equivalent of database column vw_employee_projects.emp_proj_id */
  private int empProjId;
/** The javabean property equivalent of database column vw_employee_projects.emp_id */
  private int empId;
/** The javabean property equivalent of database column vw_employee_projects.proj_id */
  private int projId;
/** The javabean property equivalent of database column vw_employee_projects.projemp_effective_date */
  private java.util.Date projempEffectiveDate;
/** The javabean property equivalent of database column vw_employee_projects.projemp_end_date */
  private java.util.Date projempEndDate;
/** The javabean property equivalent of database column vw_employee_projects.pay_rate */
  private double payRate;
/** The javabean property equivalent of database column vw_employee_projects.ot_pay_rate */
  private double otPayRate;
/** The javabean property equivalent of database column vw_employee_projects.flat_rate */
  private double flatRate;
/** The javabean property equivalent of database column vw_employee_projects.comments */
  private String comments;
/** The javabean property equivalent of database column vw_employee_projects.client_id */
  private int clientId;
/** The javabean property equivalent of database column vw_employee_projects.project_name */
  private String projectName;
/** The javabean property equivalent of database column vw_employee_projects.proj_effective_date */
  private java.util.Date projEffectiveDate;
/** The javabean property equivalent of database column vw_employee_projects.proj_end_date */
  private java.util.Date projEndDate;
/** The javabean property equivalent of database column vw_employee_projects.client_name */
  private String clientName;
/** The javabean property equivalent of database column vw_employee_projects.account_no */
  private String accountNo;
/** The javabean property equivalent of database column vw_employee_projects.business_id */
  private int businessId;
/** The javabean property equivalent of database column vw_employee_projects.client_bill_rate */
  private double clientBillRate;
/** The javabean property equivalent of database column vw_employee_projects.client_ot_bill_rate */
  private double clientOtBillRate;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwEmployeeProjects() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable empProjId
 */
  public void setEmpProjId(int value) {
    this.empProjId = value;
  }
/**
 * Gets the value of member variable empProjId
 */
  public int getEmpProjId() {
    return this.empProjId;
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
 * Sets the value of member variable projempEffectiveDate
 */
  public void setProjempEffectiveDate(java.util.Date value) {
    this.projempEffectiveDate = value;
  }
/**
 * Gets the value of member variable projempEffectiveDate
 */
  public java.util.Date getProjempEffectiveDate() {
    return this.projempEffectiveDate;
  }
/**
 * Sets the value of member variable projempEndDate
 */
  public void setProjempEndDate(java.util.Date value) {
    this.projempEndDate = value;
  }
/**
 * Gets the value of member variable projempEndDate
 */
  public java.util.Date getProjempEndDate() {
    return this.projempEndDate;
  }
/**
 * Sets the value of member variable payRate
 */
  public void setPayRate(double value) {
    this.payRate = value;
  }
/**
 * Gets the value of member variable payRate
 */
  public double getPayRate() {
    return this.payRate;
  }
/**
 * Sets the value of member variable otPayRate
 */
  public void setOtPayRate(double value) {
    this.otPayRate = value;
  }
/**
 * Gets the value of member variable otPayRate
 */
  public double getOtPayRate() {
    return this.otPayRate;
  }
/**
 * Sets the value of member variable flatRate
 */
  public void setFlatRate(double value) {
    this.flatRate = value;
  }
/**
 * Gets the value of member variable flatRate
 */
  public double getFlatRate() {
    return this.flatRate;
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
 * Sets the value of member variable projectName
 */
  public void setProjectName(String value) {
    this.projectName = value;
  }
/**
 * Gets the value of member variable projectName
 */
  public String getProjectName() {
    return this.projectName;
  }
/**
 * Sets the value of member variable projEffectiveDate
 */
  public void setProjEffectiveDate(java.util.Date value) {
    this.projEffectiveDate = value;
  }
/**
 * Gets the value of member variable projEffectiveDate
 */
  public java.util.Date getProjEffectiveDate() {
    return this.projEffectiveDate;
  }
/**
 * Sets the value of member variable projEndDate
 */
  public void setProjEndDate(java.util.Date value) {
    this.projEndDate = value;
  }
/**
 * Gets the value of member variable projEndDate
 */
  public java.util.Date getProjEndDate() {
    return this.projEndDate;
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
 * Sets the value of member variable clientBillRate
 */
  public void setClientBillRate(double value) {
    this.clientBillRate = value;
  }
/**
 * Gets the value of member variable clientBillRate
 */
  public double getClientBillRate() {
    return this.clientBillRate;
  }
/**
 * Sets the value of member variable clientOtBillRate
 */
  public void setClientOtBillRate(double value) {
    this.clientOtBillRate = value;
  }
/**
 * Gets the value of member variable clientOtBillRate
 */
  public double getClientOtBillRate() {
    return this.clientOtBillRate;
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
   final VwEmployeeProjects other = (VwEmployeeProjects) obj; 
   if (EqualityAssistant.notEqual(this.empProjId, other.empProjId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.empId, other.empId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.projId, other.projId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.projempEffectiveDate, other.projempEffectiveDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.projempEndDate, other.projempEndDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.payRate, other.payRate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.otPayRate, other.otPayRate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.flatRate, other.flatRate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.comments, other.comments)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.clientId, other.clientId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.projectName, other.projectName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.projEffectiveDate, other.projEffectiveDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.projEndDate, other.projEndDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.clientName, other.clientName)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.accountNo, other.accountNo)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.businessId, other.businessId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.clientBillRate, other.clientBillRate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.clientOtBillRate, other.clientOtBillRate)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.empProjId),
   HashCodeAssistant.hashObject(this.empId),
   HashCodeAssistant.hashObject(this.projId),
   HashCodeAssistant.hashObject(this.projempEffectiveDate),
   HashCodeAssistant.hashObject(this.projempEndDate),
   HashCodeAssistant.hashObject(this.payRate),
   HashCodeAssistant.hashObject(this.otPayRate),
   HashCodeAssistant.hashObject(this.flatRate),
   HashCodeAssistant.hashObject(this.comments),
   HashCodeAssistant.hashObject(this.clientId),
   HashCodeAssistant.hashObject(this.projectName),
   HashCodeAssistant.hashObject(this.projEffectiveDate),
   HashCodeAssistant.hashObject(this.projEndDate),
   HashCodeAssistant.hashObject(this.clientName),
   HashCodeAssistant.hashObject(this.accountNo),
   HashCodeAssistant.hashObject(this.businessId),
   HashCodeAssistant.hashObject(this.clientBillRate),
   HashCodeAssistant.hashObject(this.clientOtBillRate));
} 

@Override
public String toString() {
   return "VwEmployeeProjects [empProjId=" + empProjId + 
          ", empId=" + empId + 
          ", projId=" + projId + 
          ", projempEffectiveDate=" + projempEffectiveDate + 
          ", projempEndDate=" + projempEndDate + 
          ", payRate=" + payRate + 
          ", otPayRate=" + otPayRate + 
          ", flatRate=" + flatRate + 
          ", comments=" + comments + 
          ", clientId=" + clientId + 
          ", projectName=" + projectName + 
          ", projEffectiveDate=" + projEffectiveDate + 
          ", projEndDate=" + projEndDate + 
          ", clientName=" + clientName + 
          ", accountNo=" + accountNo + 
          ", businessId=" + businessId + 
          ", clientBillRate=" + clientBillRate + 
          ", clientOtBillRate=" + clientOtBillRate  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}