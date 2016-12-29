package org.dto;

import java.util.Date;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that represents employe/project data.
 * 
 * @author Roy Terrell
 * 
 */
public interface EmployeeProjectDto extends TransactionDto {
    /**
     * Sets the value of member variable empProjId
     */
    void setEmpProjId(int value);

    /**
     * Gets the value of member variable empProjId
     */
    int getEmpProjId();

    /**
     * Sets the value of member variable empId
     */
    void setEmpId(int value);

    /**
     * Gets the value of member variable empId
     */
    int getEmpId();

    /**
     * Sets the value of member variable projId
     */
    void setProjId(int value);

    /**
     * Gets the value of member variable projId
     */
    int getProjId();

    /**
     * Sets the value of member variable effectiveDate
     */
    void setEffectiveDate(Date value);

    /**
     * Gets the value of member variable effectiveDate
     */
    Date getEffectiveDate();

    /**
     * Sets the value of member variable endDate
     */
    void setEndDate(Date value);

    /**
     * Gets the value of member variable endDate
     */
    Date getEndDate();

    /**
     * Sets the value of member variable hourlyRate
     */
    void setHourlyRate(double value);

    /**
     * Gets the value of member variable hourlyRate
     */
    double getHourlyRate();

    /**
     * Sets the value of member variable hourlyOverRate
     */
    void setHourlyOverRate(double value);

    /**
     * Gets the value of member variable hourlyOverRate
     */
    double getHourlyOverRate();

    /**
     * Sets the value of member variable flatRate
     */
    void setFlatRate(double value);

    /**
     * Gets the value of member variable flatRate
     */
    double getFlatRate();

    /**
     * Sets the value of member variable comments
     */
    void setComments(String value);

    /**
     * Gets the value of member variable comments
     */
    String getComments();
}
