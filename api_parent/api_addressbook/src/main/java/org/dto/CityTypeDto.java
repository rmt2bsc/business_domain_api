package org.dto;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that represents a zip lookup.
 * <p>
 * An instance of this class represents one related record. If the desire arises
 * to manage a List of these instances, then redundant data could exist.
 * 
 * @author Roy Terrell
 */
public interface CityTypeDto extends TransactionDto {

    /**
     * Set the id of the city type.
     * 
     * @param value
     *            String
     */
    void setCityTypeId(String value);

    /**
     * Return the id of the city type.
     * 
     * @return String
     */
    String getCityTypeId();

    /**
     * Set the description of the city type.
     * 
     * @param value
     *            String
     */
    void setCityTypDescr(String value);

    /**
     * Return the city type's description.
     * 
     * @return String
     */
    String getCityTypDescr();

}