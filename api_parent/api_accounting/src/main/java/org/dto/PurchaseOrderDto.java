package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a purchase order entity
 * 
 * @author Roy Terrell
 * 
 */
public interface PurchaseOrderDto extends CommonAccountingDto {

    /**
     * Sets the value of member variable poId
     */
    void setPoId(int value);

    /**
     * Gets the value of member variable poId
     */
    int getPoId();

    /**
     * Sets the value of member variable xactId
     */
    void setXactId(int value);

    /**
     * Gets the value of member variable xactId
     */
    int getXactId();

    /**
     * Sets the value of member variable creditorId
     */
    void setCreditorId(int value);

    /**
     * Gets the value of member variable creditorId
     */
    int getCreditorId();

    /**
     * Sets the value of member variable refNo
     */
    void setRefNo(String value);

    /**
     * Gets the value of member variable refNo
     */
    String getRefNo();

    /**
     * Sets the value of member variable total
     */
    void setTotal(double value);

    /**
     * Gets the value of member variable total
     */
    double getTotal();

    /**
     * Sets the value of purchase order status id
     */
    void setStatusId(int value);

    /**
     * Gets the value of purchase order status id
     */
    int getStatusId();

    /**
     * Sets the value of purchase order status description
     */
    void setStatusDescription(String value);

    /**
     * Gets the value of purchase order status description
     */
    String getStatusDescription();

    /**
     * Sets the value of member variable creditorTypeId
     */
    void setCreditorTypeId(int value);

    /**
     * Gets the value of member variable creditorTypeId
     */
    int getCreditorTypeId();

    /**
     * Sets the value of member variable businessId
     */
    void setBusinessId(int value);

    /**
     * Gets the value of member variable businessId
     */
    int getBusinessId();

    /**
     * Sets the value of member variable accountNumber
     */
    void setAccountNumber(String value);

    /**
     * Gets the value of member variable accountNumber
     */
    String getAccountNumber();

    /**
     * Sets the value of creditor type description
     */
    void setCreditorTypeDescription(String value);

    /**
     * Gets the value of creditor type description
     */
    String getCreditorTypeDescription();

    /**
     * Sets the value of member variable creditLimit
     */
    void setCreditLimit(double value);

    /**
     * Gets the value of member variable creditLimit
     */
    double getCreditLimit();

    /**
     * Sets the value of purchase order total
     */
    void setPurchaseOrderTotal(double value);

    /**
     * Gets the value of purchase order total
     */
    double getPurchaseOrderTotal();

    /**
     * Sets the value of purchase order Status history Id
     */
    void setStatusHistId(int value);

    /**
     * Gets the value of purchase order Status history Id
     */
    int getStatusHistId();

    /**
     * Sets the value of effective date
     */
    void setEffectiveDate(java.util.Date value);

    /**
     * Gets the value of effective date
     */
    java.util.Date getEffectiveDate();

    /**
     * Sets the value of end date
     */
    void setEndDate(java.util.Date value);

    /**
     * Gets the value of end date
     */
    java.util.Date getEndDate();
}
