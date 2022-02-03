package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the vw_customer_balance database table/view.
 *
 * @author auto generated.
 */
public class VwCustomerBalance extends OrmBean {




	// Property name constants that belong to respective DataSource, VwCustomerBalanceView

/** The property name constant equivalent to property, CustomerId, of respective DataSource view. */
  public static final String PROP_CUSTOMERID = "CustomerId";
/** The property name constant equivalent to property, Balance, of respective DataSource view. */
  public static final String PROP_BALANCE = "Balance";



	/** The javabean property equivalent of database column vw_customer_balance.customer_id */
  private int customerId;
/** The javabean property equivalent of database column vw_customer_balance.balance */
  private double balance;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwCustomerBalance() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable customerId
 */
  public void setCustomerId(int value) {
    this.customerId = value;
  }
/**
 * Gets the value of member variable customerId
 */
  public int getCustomerId() {
    return this.customerId;
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
   final VwCustomerBalance other = (VwCustomerBalance) obj; 
   if (EqualityAssistant.notEqual(this.customerId, other.customerId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.balance, other.balance)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.customerId),
               HashCodeAssistant.hashObject(this.balance));
} 

@Override
public String toString() {
   return "VwCustomerBalance [customerId=" + customerId + 
          ", balance=" + balance  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}