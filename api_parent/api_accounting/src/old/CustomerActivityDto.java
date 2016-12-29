package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents an customer activity.
 * 
 * @author rterrell
 *
 */
public interface CustomerActivityDto extends CommonAccountingDto {

    /**
     * Sets the value of member variable customerId
     */
    void setCustomerId(int value);

    /**
     * Gets the value of member variable customerId
     */
    int getCustomerId();

    /**
     * Sets the value of member variable xactId
     */
    void setXactId(int value);

    /**
     * Gets the value of member variable xactId
     */
    int getXactId();

    /**
     * Sets the value of member variable amount
     */
    void setAmount(double value);

    /**
     * Gets the value of member variable amount
     */
    double getAmount();

}
