package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the gl_balance_type database table/view.
 *
 * @author auto generated.
 */
public class GlBalanceType extends OrmBean {




	// Property name constants that belong to respective DataSource, GlBalanceTypeView

/** The property name constant equivalent to property, AcctBaltypeId, of respective DataSource view. */
  public static final String PROP_ACCTBALTYPEID = "AcctBaltypeId";
/** The property name constant equivalent to property, LongDesc, of respective DataSource view. */
  public static final String PROP_LONGDESC = "LongDesc";
/** The property name constant equivalent to property, ShortDesc, of respective DataSource view. */
  public static final String PROP_SHORTDESC = "ShortDesc";



	/** The javabean property equivalent of database column gl_balance_type.acct_baltype_id */
  private int acctBaltypeId;
/** The javabean property equivalent of database column gl_balance_type.long_desc */
  private String longDesc;
/** The javabean property equivalent of database column gl_balance_type.short_desc */
  private String shortDesc;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public GlBalanceType() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable acctBaltypeId
 */
  public void setAcctBaltypeId(int value) {
    this.acctBaltypeId = value;
  }
/**
 * Gets the value of member variable acctBaltypeId
 */
  public int getAcctBaltypeId() {
    return this.acctBaltypeId;
  }
/**
 * Sets the value of member variable longDesc
 */
  public void setLongDesc(String value) {
    this.longDesc = value;
  }
/**
 * Gets the value of member variable longDesc
 */
  public String getLongDesc() {
    return this.longDesc;
  }
/**
 * Sets the value of member variable shortDesc
 */
  public void setShortDesc(String value) {
    this.shortDesc = value;
  }
/**
 * Gets the value of member variable shortDesc
 */
  public String getShortDesc() {
    return this.shortDesc;
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
   final GlBalanceType other = (GlBalanceType) obj; 
   if (EqualityAssistant.notEqual(this.acctBaltypeId, other.acctBaltypeId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.longDesc, other.longDesc)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.shortDesc, other.shortDesc)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.acctBaltypeId),
               HashCodeAssistant.hashObject(this.longDesc),
               HashCodeAssistant.hashObject(this.shortDesc));
} 

@Override
public String toString() {
   return "GlBalanceType [acctBaltypeId=" + acctBaltypeId + 
          ", longDesc=" + longDesc + 
          ", shortDesc=" + shortDesc  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}