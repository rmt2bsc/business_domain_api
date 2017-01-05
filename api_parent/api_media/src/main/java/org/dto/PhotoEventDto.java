package org.dto;

import com.api.foundation.TransactionDto;

/**
 * DTO Contract for keeping photo event information as it relates to imaging
 * media.
 * 
 * @author rterrell
 * 
 */
public interface PhotoEventDto extends TransactionDto {

    /**
     * Sets the event Id
     */
    void setEventId(int value);

    /**
     * Gets the event Id
     */
    int getEventId();

    /**
     * Sets the album Id
     */
    void setAlbumId(int value);

    /**
     * Gets the album Id
     */
    int getAlbumId();

    /**
     * Sets the event Name
     */
    void setEventName(String value);

    /**
     * Gets the event Name
     */
    String getEventName();

    /**
     * Sets the location
     */
    void setLocation(String value);

    /**
     * Gets the location
     */
    String getLocation();
}
