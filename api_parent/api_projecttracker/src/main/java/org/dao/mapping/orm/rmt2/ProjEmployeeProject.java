package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the proj_employee_project database table/view.
 *
 * @author auto generated.
 */
public class ProjEmployeeProject extends OrmBean {




	// Property name constants that belong to respective DataSource, ProjEmployeeProjectView

/** The property name constant equivalent to property, EmpProjId, of respective DataSource view. */
  public static final String PROP_EMPPROJID = "EmpProjId";
/** The property name constant equivalent to property, EmpId, of respective DataSource view. */
  public static final String PROP_EMPID = "EmpId";
/** The property name constant equivalent to property, ProjId, of respective DataSource view. */
  public static final String PROP_PROJID = "ProjId";
/** The property name constant equivalent to property, EffectiveDate, of respective DataSource view. */
  public static final String PROP_EFFECTIVEDATE = "EffectiveDate";
/** The property name constant equivalent to property, EndDate, of respective DataSource view. */
  public static final String PROP_ENDDATE = "EndDate";
/** The property name constant equivalent to property, HourlyRate, of respective DataSource view. */
  public static final String PROP_HOURLYRATE = "HourlyRate";
/** The property name constant equivalent to property, HourlyOverRate, of respective DataSource view. */
  public static final String PROP_HOURLYOVERRATE = "HourlyOverRate";
/** The property name constant equivalent to property, FlatRate, of respective DataSource view. */
  public static final String PROP_FLATRATE = "FlatRate";
/** The property name constant equivalent to property, Comments, of respective DataSource view. */
  public static final String PROP_COMMENTS = "Comments";
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



	/** The javabean property equivalent of database column proj_employee_project.emp_proj_id */
  private int empProjId;
/** The javabean property equivalent of database column proj_employee_project.emp_id */
  private int empId;
/** The javabean property equivalent of database column proj_employee_project.proj_id */
  private int projId;
/** The javabean property equivalent of database column proj_employee_project.effective_date */
  private java.util.Date effectiveDate;
/** The javabean property equivalent of database column proj_employee_project.end_date */
  private java.util.Date endDate;
/** The javabean property equivalent of database column proj_employee_project.hourly_rate */
  private double hourlyRate;
/** The javabean property equivalent of database column proj_employee_project.hourly_over_rate */
  private double hourlyOverRate;
/** The javabean property equivalent of database column proj_employee_project.flat_rate */
  private double flatRate;
/** The javabean property equivalent of database column proj_employee_project.comments */
  private String comments;
/** The javabean property equivalent of database column proj_employee_project.date_created */
  private java.util.Date dateCreated;
/** The javabean property equivalent of database column proj_employee_project.date_updated */
  private java.util.Date dateUpdated;
/** The javabean property equivalent of database column proj_employee_project.user_id */
  private String userId;
/** The javabean property equivalent of database column proj_employee_project.ip_created */
  private String ipCreated;
/** The javabean property equivalent of database column proj_employee_project.ip_updated */
  private String ipUpdated;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public ProjEmployeeProject() throws SystemException {
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
   final ProjEmployeeProject other = (ProjEmployeeProject) obj; 
   if (EqualityAssistant.notEqual(this.empProjId, other.empProjId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.empId, other.empId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.projId, other.projId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.effectiveDate, other.effectiveDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.endDate, other.endDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.hourlyRate, other.hourlyRate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.hourlyOverRate, other.hourlyOverRate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.flatRate, other.flatRate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.comments, other.comments)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.empProjId),
               HashCodeAssistant.hashObject(this.empId),
               HashCodeAssistant.hashObject(this.projId),
               HashCodeAssistant.hashObject(this.effectiveDate),
               HashCodeAssistant.hashObject(this.endDate),
               HashCodeAssistant.hashObject(this.hourlyRate),
               HashCodeAssistant.hashObject(this.hourlyOverRate),
               HashCodeAssistant.hashObject(this.flatRate),
               HashCodeAssistant.hashObject(this.comments));
} 

@Override
public String toString() {
   return "ProjEmployeeProject [empProjId=" + empProjId + 
          ", empId=" + empId + 
          ", projId=" + projId + 
          ", effectiveDate=" + effectiveDate + 
          ", endDate=" + endDate + 
          ", hourlyRate=" + hourlyRate + 
          ", hourlyOverRate=" + hourlyOverRate + 
          ", flatRate=" + flatRate + 
          ", comments=" + comments  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}