package org.dto;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that represents a World Time Zone
 * entites.
 * <p>
 * An instance of this class represents one related record. If the desire arises
 * to manage a List of these instances, then redundant data could exist.
 * 
 * @author Roy Terrell.
 */
public interface TimeZoneDto extends TransactionDto {

    /**
     * Set the id of the time zone
     * 
     * @param value
     *            int
     */
    void setTimeZoneId(int value);

    /**
     * Return the id of the time zone
     * 
     * @return int
     */
    int getTimeZoneId();

    /**
     * Set the description of the time zone.
     * 
     * @param value
     *            String
     */
    void setTimeZoneDescr(String value);

    /**
     * Return the description of the time zone.
     * 
     * @return String
     */
    String getTimeZoneDescr();

}