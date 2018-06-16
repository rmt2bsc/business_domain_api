package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a customer transaction
 * activity history.
 * 
 * @author Roy Terrell
 */
public interface CustomerXactHistoryDto extends SubsidiaryXactHistoryDto {

    /**
     * Sets the value of customer id
     */
    void setCustomerId(int value);

    /**
     * Gets the value of customer id
     */
    int getCustomerId();

    /**
     * Sets the value of member variable accountNo
     */
    void setAccountNo(String value);

    /**
     * Gets the value of member variable accountNo
     */
    String getAccountNo();

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
     * Sets the value of member variable creditLimit
     */
    void setCreditLimit(double value);

    /**
     * Gets the value of member variable creditLimit
     */
    double getCreditLimit();

    /**
     * Sets the value of member variable active
     */
    void setActive(int value);

    /**
     * Gets the value of member variable active
     */
    int getActive();

}