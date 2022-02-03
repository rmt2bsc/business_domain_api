package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a accounting transaction
 * customer balance entitiy.
 * 
 * @author Roy Terrell
 * 
 */
public interface SubsidiaryBalanceDto {
    
    /**
     * et subsidiary id
     * 
     * @param value
     */
    void setSubsidiaryId(int value);
    
    /**
     * Get subsidiary id
     * 
     * @return int
     */
    int getSubsidiaryId();
   
    /**
     * Sets the value of transaction customer balance
     * 
     * @param value
     */
    void setBalance(double value);
    
    /**
     * Gets the value of transaction customer balance
     * 
     * @return double
     */
    double getBalance();

}
