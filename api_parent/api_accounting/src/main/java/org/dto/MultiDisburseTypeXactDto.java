package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a general and creditor
 * related disbursment transaction entities.
 * 
 * @author rterrell
 * 
 */
public interface MultiDisburseTypeXactDto extends XactDto {

    /**
     * Sets the value of creditor id
     */
    void setCreditorId(int value);

    /**
     * Gets the value of creditor id
     */
    int getCreditorId();

    /**
     * Sets the value of creditor type id
     */
    void setCreditorTypeId(int value);

    /**
     * Gets the value of creditor type id
     */
    int getCreditorTypeId();

    /**
     * Sets the value of member variable accountNumber
     */
    void setAccountNumber(String value);

    /**
     * Gets the value of member variable accountNumber
     */
    String getAccountNumber();

    /**
     * Sets the value of member variable creditLimit
     */
    void setCreditLimit(double value);

    /**
     * Gets the value of member variable creditLimit
     */
    double getCreditLimit();

    /**
     * Set the description of the transaction tender
     */
    void setTenderDescription(String value);

    /**
     * Get the description of the transaction tender
     */
    String getTenderDescription();

}
