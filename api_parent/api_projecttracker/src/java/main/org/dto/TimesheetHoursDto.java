package org.dto;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that represents a timesheet hours detail.
 * 
 * @author Roy Terrell
 * 
 */
public interface TimesheetHoursDto extends TransactionDto {

    /**
     * Sets the value of member variable timesheetId
     */
    void setTimesheetId(int value);

    /**
     * Gets the value of member variable timesheetId
     */
    int getTimesheetId();

    /**
     * Sets the value of member variable clientId
     */
    void setClientId(int value);

    /**
     * Gets the value of member variable clientId
     */
    int getClientId();

    /**
     * Sets the value of member variable empId
     */
    void setEmpId(int value);

    /**
     * Gets the value of member variable empId
     */
    int getEmpId();

    /**
     * Sets the value of member variable displayValue
     */
    void setDisplayValue(String value);

    /**
     * Gets the value of member variable displayValue
     */
    String getDisplayValue();

    /**
     * Sets the value of member variable beginPeriod
     */
    void setBeginPeriod(java.util.Date value);

    /**
     * Gets the value of member variable beginPeriod
     */
    java.util.Date getBeginPeriod();

    /**
     * Sets the value of member variable endPeriod
     */
    void setEndPeriod(java.util.Date value);

    /**
     * Gets the value of member variable endPeriod
     */
    java.util.Date getEndPeriod();

    /**
     * Sets the value of member variable invoiceRefNo
     */
    void setInvoiceRefNo(String value);

    /**
     * Gets the value of member variable invoiceRefNo
     */
    String getInvoiceRefNo();

    /**
     * Sets the value of member variable extRef
     */
    void setExtRef(String value);

    /**
     * Gets the value of member variable extRef
     */
    String getExtRef();

    /**
     * Sets the value of member variable documentId
     */
    void setDocumentId(int value);

    /**
     * Gets the value of member variable documentId
     */
    int getDocumentId();

    /**
     * Sets the value of member variable projId
     */
    void setProjId(int value);

    /**
     * Gets the value of member variable projId
     */
    int getProjId();

    /**
     * Sets the value of member variable description
     */
    void setProjectDescription(String value);

    /**
     * Gets the value of member variable description
     */
    String getProjectDescription();

    /**
     * Sets the value of member variable effectiveDate
     */
    void setProjectEffectiveDate(java.util.Date value);

    /**
     * Gets the value of member variable effectiveDate
     */
    java.util.Date getProjectEffectiveDate();

    /**
     * Sets the value of member variable endDate
     */
    void setProjectEndDate(java.util.Date value);

    /**
     * Gets the value of member variable endDate
     */
    java.util.Date getProjectEndDate();

    /**
     * Sets the value of member variable taskId
     */
    void setTaskId(int value);

    /**
     * Gets the value of member variable taskId
     */
    int getTaskId();

    /**
     * Sets the value of member variable description
     */
    void setTaskDescription(String value);

    /**
     * Gets the value of member variable description
     */
    String getTaskDescription();

    /**
     * Sets the value of member variable billable
     */
    void setTaskBillable(int value);

    /**
     * Gets the value of member variable billable
     */
    int getTaskBillable();

    /**
     * Sets the value of member variable projectTaskId
     */
    void setProjectTaskId(int value);

    /**
     * Gets the value of member variable projectTaskId
     */
    int getProjectTaskId();

    /**
     * Sets the value of member variable eventId
     */
    void setEventId(int value);

    /**
     * Gets the value of member variable eventId
     */
    int getEventId();

    /**
     * Sets the value of member variable eventDate
     */
    void setEventDate(java.util.Date value);

    /**
     * Gets the value of member variable eventDate
     */
    java.util.Date getEventDate();

    /**
     * Sets the value of member variable hours
     */
    void setEventHours(double value);

    /**
     * Gets the value of member variable hours
     */
    double getEventHours();

    /**
     * Sets the value of member variable eventDateCreated
     */
    void setEventDateCreated(java.util.Date value);

    /**
     * Gets the value of member variable eventDateCreated
     */
    java.util.Date getEventDateCreated();
}
