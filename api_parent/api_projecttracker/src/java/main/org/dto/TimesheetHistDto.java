package org.dto;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that represents the history of a
 * timesheet.
 * 
 * @author Roy Terrell
 * 
 */
public interface TimesheetHistDto extends TransactionDto {
    /**
     * Sets the value of member variable projTimesheetHistId
     */
    void setStatusHistId(int value);

    /**
     * Gets the value of member variable projTimesheetHistId
     */
    int getStatusHistId();

    /**
     * Sets the value of member variable timesheetId
     */
    void setTimesheetId(int value);

    /**
     * Gets the value of member variable timesheetId
     */
    int getTimesheetId();

    /**
     * Sets the value of member variable timesheetStatusId
     */
    void setStatusId(int value);

    /**
     * Gets the value of member variable timesheetStatusId
     */
    int getStatusId();

    /**
     * Sets the value of member variable statusEffectiveDate
     */
    void setStatusEffectiveDate(java.util.Date value);

    /**
     * Gets the value of member variable statusEffectiveDate
     */
    java.util.Date getStatusEffectiveDate();

    /**
     * Sets the value of member variable statusEndDate
     */
    void setStatusEndDate(java.util.Date value);

    /**
     * Gets the value of member variable statusEndDate
     */
    java.util.Date getStatusEndDate();

    /**
     * Sets the flag that determines of current history should be pulled.
     * 
     * @param flag
     *            set to true for obtaiing current history only.
     */
    void setCurrentStatusFlag(boolean flag);

    /**
     * Gets the flag that determines of current history should be pulled.
     * 
     * @return
     */
    boolean isCurrentStatusFlag();
}
