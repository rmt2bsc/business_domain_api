package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the vw_sales_order_invoice database table/view.
 *
 * @author auto generated.
 */
public class VwSalesOrderInvoice extends OrmBean {




	// Property name constants that belong to respective DataSource, VwSalesOrderInvoiceView

/** The property name constant equivalent to property, SalesOrderId, of respective DataSource view. */
  public static final String PROP_SALESORDERID = "SalesOrderId";
/** The property name constant equivalent to property, CustomerId, of respective DataSource view. */
  public static final String PROP_CUSTOMERID = "CustomerId";
/** The property name constant equivalent to property, Invoiced, of respective DataSource view. */
  public static final String PROP_INVOICED = "Invoiced";
/** The property name constant equivalent to property, OrderStatusId, of respective DataSource view. */
  public static final String PROP_ORDERSTATUSID = "OrderStatusId";
/** The property name constant equivalent to property, OrderStatusDescr, of respective DataSource view. */
  public static final String PROP_ORDERSTATUSDESCR = "OrderStatusDescr";
/** The property name constant equivalent to property, SalesOrderDate, of respective DataSource view. */
  public static final String PROP_SALESORDERDATE = "SalesOrderDate";
/** The property name constant equivalent to property, InvoiceId, of respective DataSource view. */
  public static final String PROP_INVOICEID = "InvoiceId";
/** The property name constant equivalent to property, XactId, of respective DataSource view. */
  public static final String PROP_XACTID = "XactId";
/** The property name constant equivalent to property, InvoiceNo, of respective DataSource view. */
  public static final String PROP_INVOICENO = "InvoiceNo";
/** The property name constant equivalent to property, InvoiceDate, of respective DataSource view. */
  public static final String PROP_INVOICEDATE = "InvoiceDate";
/** The property name constant equivalent to property, OrderTotal, of respective DataSource view. */
  public static final String PROP_ORDERTOTAL = "OrderTotal";
/** The property name constant equivalent to property, AcctId, of respective DataSource view. */
  public static final String PROP_ACCTID = "AcctId";
/** The property name constant equivalent to property, AccountNo, of respective DataSource view. */
  public static final String PROP_ACCOUNTNO = "AccountNo";
/** The property name constant equivalent to property, CreditLimit, of respective DataSource view. */
  public static final String PROP_CREDITLIMIT = "CreditLimit";
/** The property name constant equivalent to property, Description, of respective DataSource view. */
  public static final String PROP_DESCRIPTION = "Description";



	/** The javabean property equivalent of database column vw_sales_order_invoice.sales_order_id */
  private int salesOrderId;
/** The javabean property equivalent of database column vw_sales_order_invoice.customer_id */
  private int customerId;
/** The javabean property equivalent of database column vw_sales_order_invoice.invoiced */
  private int invoiced;
/** The javabean property equivalent of database column vw_sales_order_invoice.order_status_id */
  private int orderStatusId;
/** The javabean property equivalent of database column vw_sales_order_invoice.order_status_descr */
  private String orderStatusDescr;
/** The javabean property equivalent of database column vw_sales_order_invoice.sales_order_date */
  private java.util.Date salesOrderDate;
/** The javabean property equivalent of database column vw_sales_order_invoice.invoice_id */
  private int invoiceId;
/** The javabean property equivalent of database column vw_sales_order_invoice.xact_id */
  private int xactId;
/** The javabean property equivalent of database column vw_sales_order_invoice.invoice_no */
  private String invoiceNo;
/** The javabean property equivalent of database column vw_sales_order_invoice.invoice_date */
  private java.util.Date invoiceDate;
/** The javabean property equivalent of database column vw_sales_order_invoice.order_total */
  private double orderTotal;
/** The javabean property equivalent of database column vw_sales_order_invoice.acct_id */
  private int acctId;
/** The javabean property equivalent of database column vw_sales_order_invoice.account_no */
  private String accountNo;
/** The javabean property equivalent of database column vw_sales_order_invoice.credit_limit */
  private double creditLimit;
/** The javabean property equivalent of database column vw_sales_order_invoice.description */
  private String description;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwSalesOrderInvoice() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable salesOrderId
 */
  public void setSalesOrderId(int value) {
    this.salesOrderId = value;
  }
/**
 * Gets the value of member variable salesOrderId
 */
  public int getSalesOrderId() {
    return this.salesOrderId;
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
 * Sets the value of member variable invoiced
 */
  public void setInvoiced(int value) {
    this.invoiced = value;
  }
/**
 * Gets the value of member variable invoiced
 */
  public int getInvoiced() {
    return this.invoiced;
  }
/**
 * Sets the value of member variable orderStatusId
 */
  public void setOrderStatusId(int value) {
    this.orderStatusId = value;
  }
/**
 * Gets the value of member variable orderStatusId
 */
  public int getOrderStatusId() {
    return this.orderStatusId;
  }
/**
 * Sets the value of member variable orderStatusDescr
 */
  public void setOrderStatusDescr(String value) {
    this.orderStatusDescr = value;
  }
/**
 * Gets the value of member variable orderStatusDescr
 */
  public String getOrderStatusDescr() {
    return this.orderStatusDescr;
  }
/**
 * Sets the value of member variable salesOrderDate
 */
  public void setSalesOrderDate(java.util.Date value) {
    this.salesOrderDate = value;
  }
/**
 * Gets the value of member variable salesOrderDate
 */
  public java.util.Date getSalesOrderDate() {
    return this.salesOrderDate;
  }
/**
 * Sets the value of member variable invoiceId
 */
  public void setInvoiceId(int value) {
    this.invoiceId = value;
  }
/**
 * Gets the value of member variable invoiceId
 */
  public int getInvoiceId() {
    return this.invoiceId;
  }
/**
 * Sets the value of member variable xactId
 */
  public void setXactId(int value) {
    this.xactId = value;
  }
/**
 * Gets the value of member variable xactId
 */
  public int getXactId() {
    return this.xactId;
  }
/**
 * Sets the value of member variable invoiceNo
 */
  public void setInvoiceNo(String value) {
    this.invoiceNo = value;
  }
/**
 * Gets the value of member variable invoiceNo
 */
  public String getInvoiceNo() {
    return this.invoiceNo;
  }
/**
 * Sets the value of member variable invoiceDate
 */
  public void setInvoiceDate(java.util.Date value) {
    this.invoiceDate = value;
  }
/**
 * Gets the value of member variable invoiceDate
 */
  public java.util.Date getInvoiceDate() {
    return this.invoiceDate;
  }
/**
 * Sets the value of member variable orderTotal
 */
  public void setOrderTotal(double value) {
    this.orderTotal = value;
  }
/**
 * Gets the value of member variable orderTotal
 */
  public double getOrderTotal() {
    return this.orderTotal;
  }
/**
 * Sets the value of member variable acctId
 */
  public void setAcctId(int value) {
    this.acctId = value;
  }
/**
 * Gets the value of member variable acctId
 */
  public int getAcctId() {
    return this.acctId;
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
 * Sets the value of member variable creditLimit
 */
  public void setCreditLimit(double value) {
    this.creditLimit = value;
  }
/**
 * Gets the value of member variable creditLimit
 */
  public double getCreditLimit() {
    return this.creditLimit;
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
   final VwSalesOrderInvoice other = (VwSalesOrderInvoice) obj; 
   if (EqualityAssistant.notEqual(this.salesOrderId, other.salesOrderId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.customerId, other.customerId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.invoiced, other.invoiced)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.orderStatusId, other.orderStatusId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.orderStatusDescr, other.orderStatusDescr)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.salesOrderDate, other.salesOrderDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.invoiceId, other.invoiceId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.xactId, other.xactId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.invoiceNo, other.invoiceNo)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.invoiceDate, other.invoiceDate)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.orderTotal, other.orderTotal)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.acctId, other.acctId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.accountNo, other.accountNo)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.creditLimit, other.creditLimit)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.description, other.description)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.salesOrderId),
               HashCodeAssistant.hashObject(this.customerId),
               HashCodeAssistant.hashObject(this.invoiced),
               HashCodeAssistant.hashObject(this.orderStatusId),
               HashCodeAssistant.hashObject(this.orderStatusDescr),
               HashCodeAssistant.hashObject(this.salesOrderDate),
               HashCodeAssistant.hashObject(this.invoiceId),
               HashCodeAssistant.hashObject(this.xactId),
               HashCodeAssistant.hashObject(this.invoiceNo),
               HashCodeAssistant.hashObject(this.invoiceDate),
               HashCodeAssistant.hashObject(this.orderTotal),
               HashCodeAssistant.hashObject(this.acctId),
               HashCodeAssistant.hashObject(this.accountNo),
               HashCodeAssistant.hashObject(this.creditLimit),
               HashCodeAssistant.hashObject(this.description));
} 

@Override
public String toString() {
   return "VwSalesOrderInvoice [salesOrderId=" + salesOrderId + 
          ", customerId=" + customerId + 
          ", invoiced=" + invoiced + 
          ", orderStatusId=" + orderStatusId + 
          ", orderStatusDescr=" + orderStatusDescr + 
          ", salesOrderDate=" + salesOrderDate + 
          ", invoiceId=" + invoiceId + 
          ", xactId=" + xactId + 
          ", invoiceNo=" + invoiceNo + 
          ", invoiceDate=" + invoiceDate + 
          ", orderTotal=" + orderTotal + 
          ", acctId=" + acctId + 
          ", accountNo=" + accountNo + 
          ", creditLimit=" + creditLimit + 
          ", description=" + description  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}