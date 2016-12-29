package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents an creditor activity.
 * 
 * @author rterrell
 *
 */
public interface CreditorActivityDto extends CommonAccountingDto {

    /**
     * Sets the value of member variable creditorId
     */
    void setCreditorId(int value);

    /**
     * Gets the value of member variable creditorId
     */
    int getCreditorId();

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
