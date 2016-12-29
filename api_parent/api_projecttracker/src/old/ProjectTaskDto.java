package org.dto;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that represents project/task data.
 * 
 * @author Roy Terrell
 * 
 */
public interface ProjectTaskDto extends TransactionDto {
    /**
     * Sets the value of member variable projectTaskId
     */
    void setProjectTaskId(int value);

    /**
     * Gets the value of member variable projectTaskId
     */
    int getProjectTaskId();

    /**
     * Sets the value of member variable taskId
     */
    void setTaskId(int value);

    /**
     * Gets the value of member variable taskId
     */
    int getTaskId();

    /**
     * Sets the value of member variable timesheetId
     */
    void setTimesheetId(int value);

    /**
     * Gets the value of member variable timesheetId
     */
    int getTimesheetId();

    /**
     * Sets the value of member variable projId
     */
    void setProjId(int value);

    /**
     * Gets the value of member variable projId
     */
    int getProjId();
}
