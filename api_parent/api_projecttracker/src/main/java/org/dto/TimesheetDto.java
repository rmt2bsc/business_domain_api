package org.dto;

import java.util.List;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that represents a timesheet.
 * 
 * @author Roy Terrell
 * 
 */
public interface TimesheetDto extends TransactionDto {

    /**
     * Sets the value of member variable timesheetId
     */
    void setTimesheetId(int value);

    /**
     * Gets the value of member variable timesheetId
     */
    int getTimesheetId();

    /**
     * Sets the list of timesheetId
     */
    void setTimesheetIdList(List<Integer> value);

    /**
     * Gets the list of timesheetId
     */
    List<Integer> getTimesheetIdList();

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
     * Sets the value of member variable projId
     */
    void setProjId(int value);

    /**
     * Gets the value of member variable projId
     */
    int getProjId();

    /**
     * Sets the value of member variable comments
     */
    void setComments(String value);

    /**
     * Gets the value of member variable comments
     */
    String getComments();

    /**
     * Sets the value of member variable documentId
     */
    void setDocumentId(int value);

    /**
     * Gets the value of member variable documentId
     */
    int getDocumentId();

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
     * Sets the value of member variable projTimesheetHistId
     */
    void setStatusHistId(int value);

    /**
     * Gets the value of member variable projTimesheetHistId
     */
    int getStatusHistId();

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
     * Sets the value of member variable typeId
     */
    void setEmployeeTypeId(int value);

    /**
     * Gets the value of member variable typeId
     */
    int getEmployeeTypeId();

    /**
     * Sets the value of member variable firstname
     */
    void setEmployeeFirstname(String value);

    /**
     * Gets the value of member variable firstname
     */
    String getEmployeeFirstname();

    /**
     * Sets the value of member variable lastname
     */
    void setEmployeeLastname(String value);

    /**
     * Gets the value of member variable lastname
     */
    String getEmployeeLastname();

    /**
     * Sets the value of member variable managerId
     */
    void setEmployeeManagerId(int value);

    /**
     * Gets the value of member variable managerId
     */
    int getEmployeeManagerId();

    /**
     * Sets the value of member variable hourlyOverRate
     */
    void setEmployeeHourlyOverRate(double value);

    /**
     * Gets the value of member variable hourlyOverRate
     */
    double getEmployeeHourlyOverRate();

    /**
     * Sets the value of member variable hourlyRate
     */
    void setEmployeeHourlyRate(double value);

    /**
     * Gets the value of member variable hourlyRate
     */
    double getEmployeeHourlyRate();

    /**
     * Sets the value of member variable lastFirstName
     */
    void setEmployeeFullName(String value);

    /**
     * Gets the value of member variable lastFirstName
     */
    String getEmployeeFullName();

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
     * Sets the value of member variable billHrs
     */
    void setBillHrs(double value);

    /**
     * Gets the value of member variable billHrs
     */
    double getBillHrs();

    /**
     * Sets the value of member variable nonBillHrs
     */
    void setNonBillHrs(double value);

    /**
     * Gets the value of member variable nonBillHrs
     */
    double getNonBillHrs();
}
