package org.dto;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that represents an employee title.
 * 
 * @author Roy Terrell
 * 
 */
public interface EmployeeTitleDto extends TransactionDto {
    /**
     * Sets the value of member variable empTitleId
     * 
     * 
     */
    void setEmployeeTitleId(int value);

    /**
     * Gets the value of member variable empTitleId
     * 
     * 
     */
    int getEmployeeTitleId();

    /**
     * Sets the value of member variable description
     * 
     * 
     */
    void setEmployeeTitleDescription(String value);

    /**
     * Gets the value of member variable description
     * 
     * 
     */
    String getEmployeeTitleDescription();
}
