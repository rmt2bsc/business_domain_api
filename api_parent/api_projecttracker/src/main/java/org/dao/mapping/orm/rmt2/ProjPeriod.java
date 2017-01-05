package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the proj_period database table/view.
 *
 * @author auto generated.
 */
public class ProjPeriod extends OrmBean {




	// Property name constants that belong to respective DataSource, ProjPeriodView

/** The property name constant equivalent to property, ProjPeriodId, of respective DataSource view. */
  public static final String PROP_PROJPERIODID = "ProjPeriodId";
/** The property name constant equivalent to property, PrdType, of respective DataSource view. */
  public static final String PROP_PRDTYPE = "PrdType";
/** The property name constant equivalent to property, MaxRegHrs, of respective DataSource view. */
  public static final String PROP_MAXREGHRS = "MaxRegHrs";



	/** The javabean property equivalent of database column proj_period.proj_period_id */
  private double projPeriodId;
/** The javabean property equivalent of database column proj_period.prd_type */
  private String prdType;
/** The javabean property equivalent of database column proj_period.max_reg_hrs */
  private int maxRegHrs;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public ProjPeriod() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable projPeriodId
 */
  public void setProjPeriodId(double value) {
    this.projPeriodId = value;
  }
/**
 * Gets the value of member variable projPeriodId
 */
  public double getProjPeriodId() {
    return this.projPeriodId;
  }
/**
 * Sets the value of member variable prdType
 */
  public void setPrdType(String value) {
    this.prdType = value;
  }
/**
 * Gets the value of member variable prdType
 */
  public String getPrdType() {
    return this.prdType;
  }
/**
 * Sets the value of member variable maxRegHrs
 */
  public void setMaxRegHrs(int value) {
    this.maxRegHrs = value;
  }
/**
 * Gets the value of member variable maxRegHrs
 */
  public int getMaxRegHrs() {
    return this.maxRegHrs;
  }
/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}