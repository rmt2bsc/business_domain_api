package org.dto;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that represents a project.
 * 
 * @author Roy Terrell
 * 
 */
public interface ProjectDto extends TransactionDto {
    /**
     * Sets the value of member variable projId
     */
    void setProjId(int value);

    /**
     * Gets the value of member variable projId
     */
    int getProjId();

    /**
     * Sets the value of member variable clientId
     */
    void setClientId(int value);

    /**
     * Gets the value of member variable clientId
     */
    int getClientId();

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
}
