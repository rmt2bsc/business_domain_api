package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the proj_employee_type database table/view.
 *
 * @author auto generated.
 */
public class ProjEmployeeType extends OrmBean {




	// Property name constants that belong to respective DataSource, ProjEmployeeTypeView

/** The property name constant equivalent to property, EmpTypeId, of respective DataSource view. */
  public static final String PROP_EMPTYPEID = "EmpTypeId";
/** The property name constant equivalent to property, Description, of respective DataSource view. */
  public static final String PROP_DESCRIPTION = "Description";



	/** The javabean property equivalent of database column proj_employee_type.emp_type_id */
  private int empTypeId;
/** The javabean property equivalent of database column proj_employee_type.description */
  private String description;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public ProjEmployeeType() throws SystemException {
	super();
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
 * Sets the value of member variable description
 */
  public void setDescription(String value) {
    this.description = value;
  }
/**
 * Gets the value of member variable description
 */
  public String getDescription() {
    return this.description;
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
   final ProjEmployeeType other = (ProjEmployeeType) obj; 
   if (EqualityAssistant.notEqual(this.empTypeId, other.empTypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.description, other.description)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.empTypeId),
               HashCodeAssistant.hashObject(this.description));
} 

@Override
public String toString() {
   return "ProjEmployeeType [empTypeId=" + empTypeId + 
          ", description=" + description  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}