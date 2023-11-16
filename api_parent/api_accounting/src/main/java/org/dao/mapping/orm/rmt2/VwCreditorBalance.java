package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the vw_creditor_balance database table/view.
 *
 * @author auto generated.
 */
public class VwCreditorBalance extends OrmBean {




	// Property name constants that belong to respective DataSource, VwCreditorBalanceView

/** The property name constant equivalent to property, BusinessId, of respective DataSource view. */
  public static final String PROP_BUSINESSID = "BusinessId";
/** The property name constant equivalent to property, Balance, of respective DataSource view. */
  public static final String PROP_BALANCE = "Balance";



	/** The javabean property equivalent of database column vw_creditor_balance.business_id */
  private int businessId;
/** The javabean property equivalent of database column vw_creditor_balance.balance */
  private double balance;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwCreditorBalance() throws SystemException {
	super();
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
 * Sets the value of member variable balance
 */
  public void setBalance(double value) {
    this.balance = value;
  }
/**
 * Gets the value of member variable balance
 */
  public double getBalance() {
    return this.balance;
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
   final VwCreditorBalance other = (VwCreditorBalance) obj; 
   if (EqualityAssistant.notEqual(this.businessId, other.businessId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.balance, other.balance)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.businessId),
               HashCodeAssistant.hashObject(this.balance));
} 

@Override
public String toString() {
   return "VwCreditorBalance [businessId=" + businessId + 
          ", balance=" + balance  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}