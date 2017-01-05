package org.dto;

import java.util.Date;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that represents project/client data.
 * 
 * @author Roy Terrell
 * 
 */
public interface ProjectClientDto extends TransactionDto {
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
     * Sets the name of the client
     */
    void setClientName(String value);

    /**
     * Gets the name of the client
     */
    String getClientName();

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

}
