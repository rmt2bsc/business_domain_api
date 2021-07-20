package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
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
   final Nulltable other = (Nulltable) obj; 
   if (EqualityAssistant.notEqual(this.nullcol, other.nullcol)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.nullcol));
} 

@Override
public String toString() {
   return "Nulltable [nullcol=" + nullcol  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}