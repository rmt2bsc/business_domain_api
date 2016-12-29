package org.dto;

import java.util.Date;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that represents
 * employee/client/project/task data.
 * 
 * @author Roy Terrell
 * 
 */
public interface ProjectEmployeeDto extends TransactionDto {
    /**
     * Sets the project employee id
     */
    void setEmpProjId(int value);

    /**
     * Gets the project employee id
     */
    int getEmpProjId();

    /**
     * Sets the employee id
     */
    void setEmpId(int value);

    /**
     * Gets the employee id
     */
    int getEmpId();

    /**
     * Sets the project id
     */
    void setProjId(int value);

    /**
     * Gets the project id
     */
    int getProjId();

    /**
     * Sets the client id
     */
    void setClientId(int value);

    /**
     * Gets the client id
     */
    int getClientId();

    /**
     * Sets the client business id
     */
    void setBusinessId(int value);

    /**
     * Gets the client business id
     */
    int getBusinessId();

    /**
     * Sets the client account number
     */
    void setAccountNo(String value);

    /**
     * Gets the client account number
     */
    String getAccountNo();

    /**
     * Sets the name of the client
     */
    void setClientName(String value);

    /**
     * Gets the name of the client
     */
    String getClientName();

    /**
     * Sets the employee/project effective date
     */
    void setProjEmpEffectiveDate(Date value);

    /**
     * Gets the employee/project effective date
     */
    Date getProjEmpEffectiveDate();

    /**
     * Sets the employee/project end date
     */
    void setProjEmpEndDate(Date value);

    /**
     * Gets the employee/project end date
     */
    Date getProjEmpEndDate();

    /**
     * Sets the project effective date
     */
    void setProjectEffectiveDate(Date value);

    /**
     * Gets the project effective date
     */
    Date getProjectEffectiveDate();

    /**
     * Sets the project end date
     */
    void setProjectEndDate(Date value);

    /**
     * Gets the project end date
     */
    Date getProjectEndDate();

    /**
     * Sets the employee/project hourly rate
     */
    void setHourlyRate(double value);

    /**
     * Gets the employee/project hourly rate
     */
    double getHourlyRate();

    /**
     * Sets the employee/project overtime hourly rate
     */
    void setHourlyOverRate(double value);

    /**
     * Gets the employee/project overtime hourly rate
     */
    double getHourlyOverRate();

    /**
     * Sets the employee/project flat rate
     */
    void setFlatRate(double value);

    /**
     * Gets the employee/project flat rate
     */
    double getFlatRate();

    /**
     * Sets the client billable rate
     */
    void setClientBillRate(double value);

    /**
     * Gets the client billable rate
     */
    double getClientBillRate();

    /**
     * Sets the client overtime billable rate
     */
    void setClientOtBillRate(double value);

    /**
     * Gets the client overtime billable rate
     */
    double getClientOtBillRate();

    /**
     * Sets the project description
     */
    void setProjectDescription(String value);

    /**
     * Gets the project description
     */
    String getProjectDescription();

    /**
     * Sets the employee/project comments
     */
    void setComments(String value);

    /**
     * Gets the employee/project comments
     */
    String getComments();
}
