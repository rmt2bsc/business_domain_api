package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents an customer.
 * 
 * @author rterrell
 * 
 */
public interface CustomerDto extends SubsidiaryDto {

    /**
     * Set the customer id
     */
    void setCustomerId(int value);

    /**
     * Get the customer id
     */
    int getCustomerId();

    /**
     * Sets the value of member variable personId
     */
    void setPersonId(int value);

    /**
     * Gets the value of member variable personId
     */
    int getPersonId();

}
