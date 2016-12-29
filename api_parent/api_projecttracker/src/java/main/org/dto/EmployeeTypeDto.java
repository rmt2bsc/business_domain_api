package org.dto;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that represents an employee type.
 * 
 * @author Roy Terrell
 * 
 */
public interface EmployeeTypeDto extends TransactionDto {
    /**
     * Sets the value of member variable empTitleId
     * 
     * 
     */
    void setEmployeeTypeId(int value);

    /**
     * Gets the value of member variable empTitleId
     * 
     * 
     */
    int getEmployeeTypeId();

    /**
     * Sets the value of member variable description
     * 
     * 
     */
    void setEmployeeTypeDescription(String value);

    /**
     * Gets the value of member variable description
     * 
     * 
     */
    String getEmployeeTypeDescription();
}
