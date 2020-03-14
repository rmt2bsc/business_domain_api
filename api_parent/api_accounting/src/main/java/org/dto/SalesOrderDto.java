package org.dto;

import java.util.Date;

/**
 * Data Transfer Object (DTO) contract that represents a sales order entity.
 * 
 * @author rterrell
 * 
 */
public interface SalesOrderDto extends SalesOrderStatusDto {

    /**
     * Sets the value of sales order id
     */
    void setSalesOrderId(int value);

    /**
     * Gets the value of sales order id
     */
    int getSalesOrderId();

    /**
     * Sets the value of member variable customerId
     */
    void setCustomerId(int value);

    /**
     * Gets the value of member variable customerId
     */
    int getCustomerId();

    /**
     * Sets the value of member variable invoiced
     */
    void setInvoiced(Boolean value);

    /**
     * Gets the value of member variable invoiced
     */
    Boolean isInvoiced();

    /**
     * Sets the value of member variable orderTotal
     */
    void setOrderTotal(double value);

    /**
     * Gets the value of member variable orderTotal
     */
    double getOrderTotal();

    /**
     * Set sales order date
     */
    void setSaleOrderDate(Date value);

    /**
     * Get sales order date
     */
    Date getSaleOrderDate();

    /**
     * Sets the value of customer name
     */
    void setCustomerName(String value);

    /**
     * Gets the value of customer name
     */
    String getCustomerName();

    /**
     * Sets the value of member variable acctId
     */
    void setAcctId(int value);

    /**
     * Gets the value of member variable acctId
     */
    int getAcctId();

    /**
     * Sets the value of member variable accountNo
     */
    void setAccountNo(String value);

    /**
     * Gets the value of member variable accountNo
     */
    String getAccountNo();

    /**
     * Sets the value of member variable creditLimit
     */
    void setCreditLimit(double value);

    /**
     * Gets the value of member variable creditLimit
     */
    double getCreditLimit();

    /**
     * Sets the value of member variable personId
     */
    void setPersonId(int value);

    /**
     * Gets the value of member variable personId
     */
    int getPersonId();

    /**
     * Sets the value of member variable businessId
     */
    void setBusinessId(int value);

    /**
     * Gets the value of member variable businessId
     */
    int getBusinessId();

    /**
     * Get Effective date
     * 
     * @return
     */
    Date getEffectiveDate();

    /**
     * Set Effective Date
     * 
     * @param value
     */
    void setEffectiveDate(Date value);
}
