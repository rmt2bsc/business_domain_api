package org.dto;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that represents a project period.
 * 
 * @author Roy Terrell
 * 
 */
public interface PeriodDto extends TransactionDto {
    /**
     * Sets the value of member variable projPeriodId
     * 
     * 
     */
    void setPeriodId(double value);

    /**
     * Gets the value of member variable projPeriodId
     * 
     * 
     */
    double getPeriodId();

    /**
     * Sets the value of member variable prdType
     * 
     * 
     */
    void setPeriodType(String value);

    /**
     * Gets the value of member variable prdType
     * 
     * 
     */
    String getPeriodType();

    /**
     * Sets the value of member variable maxRegHrs
     * 
     * 
     */
    void setPeriodMaxRegHrs(int value);

    /**
     * Gets the value of member variable maxRegHrs
     * 
     * 
     */
    int getPeriodMaxRegHrs();
}
