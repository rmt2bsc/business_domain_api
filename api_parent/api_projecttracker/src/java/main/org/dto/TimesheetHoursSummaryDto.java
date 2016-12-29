package org.dto;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that represents a timesheet hours
 * summary.
 * 
 * @author Roy Terrell
 * 
 */
public interface TimesheetHoursSummaryDto extends TransactionDto {

    /**
     * Sets the value of member variable timesheetId
     */
    void setTimesheetId(int value);

    /**
     * Gets the value of member variable timesheetId
     */
    int getTimesheetId();

    /**
     * Sets the value of member variable lastFirstName
     */
    void setEmployeeFullName(String value);

    /**
     * Gets the value of member variable lastFirstName
     */
    String getEmployeeFullName();

    /**
     * Sets the value of member variable displayValue
     */
    void setDisplayValue(String value);

    /**
     * Gets the value of member variable displayValue
     */
    String getDisplayValue();

    /**
     * Sets the value of member variable endPeriod
     */
    void setEndPeriod(java.util.Date value);

    /**
     * Gets the value of member variable endPeriod
     */
    java.util.Date getEndPeriod();

    /**
     * Sets the value of member variable documentId
     */
    void setDocumentId(int value);

    /**
     * Gets the value of member variable documentId
     */
    int getDocumentId();

    /**
     * Sets the total hours for day 1
     */
    void setHours1(double value);

    /**
     * Gets the total hours for day 1
     */
    double getHours1();

    /**
     * Sets the total hours for day 2
     */
    void setHours2(double value);

    /**
     * Gets the total hours for day 2
     */
    double getHours2();

    /**
     * Sets the total hours for day 3
     */
    void setHours3(double value);

    /**
     * Gets the total hours for day 3
     */
    double getHours3();

    /**
     * Sets the total hours for day 4
     */
    void setHours4(double value);

    /**
     * Gets the total hours for day 4
     */
    double getHours4();

    /**
     * Sets the total hours for day 5
     */
    void setHours5(double value);

    /**
     * Gets the total hours for day 5
     */
    double getHours5();

    /**
     * Sets the total hours for day 6
     */
    void setHours6(double value);

    /**
     * Gets the total hours for day 6
     */
    double getHours6();

    /**
     * Sets the total hours for day 7
     */
    void setHours7(double value);

    /**
     * Gets the total hours for day 7
     */
    double getHours7();

    /**
     * Sets cumalative hours for day 1 through 7
     */
    void setTotalHours(double value);

    /**
     * Gets cumalative hours for day 1 through 7
     */
    double getTotalHours();
}
