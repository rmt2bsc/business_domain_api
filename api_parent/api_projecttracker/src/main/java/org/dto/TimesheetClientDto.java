package org.dto;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that represents a timesheet client
 * summary information.
 * 
 * @author Roy Terrell
 * 
 */
public interface TimesheetClientDto extends TransactionDto {
    /**
     * Sets the value of member variable clientId
     */
    void setClientId(int value);

    /**
     * Gets the value of member variable clientId
     */
    int getClientId();

    /**
     * Sets the value of member variable businessId
     */
    void setBusinessId(int value);

    /**
     * Gets the value of member variable businessId
     */
    int getBusinessId();

    /**
     * Sets the value of member variable clientName
     */
    void setClientName(String value);

    /**
     * Gets the value of member variable clientName
     */
    String getClientName();

    /**
     * Sets the value of member variable accountNo
     */
    void setClientAccountNo(String value);

    /**
     * Gets the value of member variable accountNo
     */
    String getClientAccountNo();

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

    /**
     * Set timesheet count
     * 
     * @param value
     */
    void setTimesheetCount(int value);

    /**
     * Return timesheet count
     * 
     * @return
     */
    int getTimesheetCount();
}
