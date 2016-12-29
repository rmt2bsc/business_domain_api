package org.dto;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that represents a task of a project.
 * 
 * @author Roy Terrell
 * 
 */
public interface TaskDto extends TransactionDto {
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
}
