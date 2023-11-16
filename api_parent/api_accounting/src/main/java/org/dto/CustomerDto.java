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
    
    /**
     * Set customer description
     * 
     * @param value
     */
    void setDescription(String value);
    
    /**
     * Return customer description
     * @return
     */
    String getDescription();

    /**
     * Get customer's balance
     * 
     * @return
     */
    public Double getBalance();

    /**
     * Set customer's balance
     * 
     * @param balance
     */
    public void setBalance(Double balance);
}
