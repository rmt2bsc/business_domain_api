package org.dto;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that represents a timesheet event.
 * 
 * @author Roy Terrell
 * 
 */
public interface EventDto extends TransactionDto {
    /**
     * Sets the value of member variable eventId
     */
    void setEventId(int value);

    /**
     * Gets the value of member variable eventId
     */
    int getEventId();

    /**
     * Sets the value of member variable projectTaskId
     */
    void setProjectTaskId(int value);

    /**
     * Gets the value of member variable projectTaskId
     */
    int getProjectTaskId();

    /**
     * Sets the value of member variable eventDate
     */
    void setEventDate(java.util.Date value);

    /**
     * Gets the value of member variable eventDate
     */
    java.util.Date getEventDate();

    /**
     * Sets the value of member variable hours
     */
    void setEventHours(double value);

    /**
     * Gets the value of member variable hours
     */
    double getEventHours();
}
