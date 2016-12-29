package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the nulltable database table/view.
 *
 * @author auto generated.
 */
public class Nulltable extends OrmBean {




	// Property name constants that belong to respective DataSource, NulltableView

/** The property name constant equivalent to property, Nullcol, of respective DataSource view. */
  public static final String PROP_NULLCOL = "Nullcol";



	/** The javabean property equivalent of database column nulltable.nullcol */
  private int nullcol;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public Nulltable() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable nullcol
 */
  public void setNullcol(int value) {
    this.nullcol = value;
  }
/**
 * Gets the value of member variable nullcol
 */
  public int getNullcol() {
    return this.nullcol;
  }
/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}