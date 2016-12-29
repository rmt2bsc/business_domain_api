package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the proj_employee_title database table/view.
 *
 * @author auto generated.
 */
public class ProjEmployeeTitle extends OrmBean {




	// Property name constants that belong to respective DataSource, ProjEmployeeTitleView

/** The property name constant equivalent to property, EmpTitleId, of respective DataSource view. */
  public static final String PROP_EMPTITLEID = "EmpTitleId";
/** The property name constant equivalent to property, Description, of respective DataSource view. */
  public static final String PROP_DESCRIPTION = "Description";



	/** The javabean property equivalent of database column proj_employee_title.emp_title_id */
  private int empTitleId;
/** The javabean property equivalent of database column proj_employee_title.description */
  private String description;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public ProjEmployeeTitle() throws SystemException {
	super();
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
/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}