package org.dto;

import java.util.Date;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that represents project/task/project task
 * data.
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
     * Sets the value of member variable timesheetId
     */
    void setTimesheetId(int value);

    /**
     * Gets the value of member variable timesheetId
     */
    int getTimesheetId();

    /**
     * Sets the project id
     */
    void setProjId(int value);

    /**
     * Gets the project id
     */
    int getProjId();

    /**
     * Sets the value of member variable taskId
     */
    void setTaskId(int value);

    /**
     * Gets the value of member variable taskId
     */
    int getTaskId();

    /**
     * Sets the client id
     */
    void setClientId(int value);

    /**
     * Gets the client id
     */
    int getClientId();

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
     * Sets the project description
     */
    void setProjectDescription(String value);

    /**
     * Gets the project description
     */
    String getProjectDescription();

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
     * Sets the delete indicator
     */
    void setDeleteFlag(boolean value);

    /**
     * Gets the delete indicator.
     */
    boolean isDeleteFlag();

}
