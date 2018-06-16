package org.dto;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that represents a timesheet status.
 * 
 * @author Roy Terrell
 * 
 */
public interface TimesheetStatusDto extends TransactionDto {
    /**
     * Sets the value of member variable timesheetStatusId
     */
    void setStatusId(int value);

    /**
     * Gets the value of member variable timesheetStatusId
     */
    int getStatusId();

    /**
     * Sets the value of member variable statusName
     */
    void setStatusName(String value);

    /**
     * Gets the value of member variable statusName
     */
    String getStatusName();

    /**
     * Sets the value of member variable statusDescription
     */
    void setStatusDescription(String value);

    /**
     * Gets the value of member variable statusDescription
     */
    String getStatusDescription();

}
