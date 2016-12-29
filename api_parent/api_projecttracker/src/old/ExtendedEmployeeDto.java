package org.dto;

import java.util.Date;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that represents the employee/employee
 * title/employee type data.
 * 
 * @author Roy Terrell
 * @deprecated Not used
 * 
 */
public interface ExtendedEmployeeDto extends TransactionDto {

    /**
     * Sets the value of member variable employeeId
     * 
     * 
     */
    void setEmployeeId(int value);

    /**
     * Gets the value of member variable employeeId
     * 
     * 
     */
    int getEmployeeId();

    /**
     * Sets the value of member variable loginId
     * 
     * 
     */
    void setLoginId(int value);

    /**
     * Gets the value of member variable loginId
     * 
     * 
     */
    int getLoginId();

    /**
     * Sets the value of member variable loginName
     * 
     * 
     */
    void setLoginName(String value);

    /**
     * Gets the value of member variable loginName
     * 
     * 
     */
    String getLoginName();

    /**
     * Sets the value of member variable startDate
     * 
     * 
     */
    void setStartDate(Date value);

    /**
     * Gets the value of member variable startDate
     * 
     * 
     */
    Date getStartDate();

    /**
     * Sets the value of member variable terminationDate
     * 
     * 
     */
    void setTerminationDate(Date value);

    /**
     * Gets the value of member variable terminationDate
     * 
     * 
     */
    Date getTerminationDate();

    /**
     * Sets the value of member variable titleId
     * 
     * 
     */
    void setEmployeeTitleId(int value);

    /**
     * Gets the value of member variable titleId
     * 
     * 
     */
    int getEmployeeTitleId();

    /**
     * Sets the value of member variable typeId
     * 
     * 
     */
    void setEmployeeTypeId(int value);

    /**
     * Gets the value of member variable typeId
     * 
     * 
     */
    int getEmployeeTypeId();

    /**
     * Sets the value of member variable managerId
     * 
     * 
     */
    void setManagerId(int value);

    /**
     * Gets the value of member variable managerId
     * 
     * 
     */
    int getManagerId();

    /**
     * Sets the value of member variable firstname
     * 
     * 
     */
    void setEmployeeFirstname(String value);

    /**
     * Gets the value of member variable firstname
     * 
     * 
     */
    String getEmployeeFirstname();

    /**
     * Sets the value of member variable lastname
     * 
     * 
     */
    void setEmployeeLastname(String value);

    /**
     * Gets the value of member variable lastname
     * 
     * 
     */
    String getEmployeeLastname();

    /**
     * Sets the value of member variable shortname
     * 
     * 
     */
    void setEmployeeShortname(String value);

    /**
     * Gets the value of member variable shortname
     * 
     * 
     */
    String getEmployeeShortname();

    /**
     * Sets the value of member variable companyName
     * 
     * 
     */
    void setEmployeeCompanyName(String value);

    /**
     * Gets the value of member variable companyName
     * 
     * 
     */
    String getEmployeeCompanyName();

    /**
     * Sets the value of member variable ssn
     * 
     * 
     */
    void setSsn(String value);

    /**
     * Gets the value of member variable ssn
     * 
     * 
     */
    String getSsn();

    /**
     * Sets the value of member variable isManager
     * 
     * 
     */
    void setIsManager(int value);

    /**
     * Gets the value of member variable isManager
     * 
     * 
     */
    int getIsManager();

    /**
     * Sets the value of member variable email
     * 
     * 
     */
    void setEmployeeEmail(String value);

    /**
     * Gets the value of member variable email
     * 
     * 
     */
    String getEmployeeEmail();

    /**
     * Sets the value of member variable employeeTitle
     * 
     * 
     */
    void setEmployeeTitle(String value);

    /**
     * Gets the value of member variable employeeTitle
     * 
     * 
     */
    String getEmployeeTitle();

    /**
     * Sets the value of member variable employeeType
     * 
     * 
     */
    void setEmployeeType(String value);

    /**
     * Gets the value of member variable employeeType
     * 
     * 
     */
    String getEmployeeType();
}
