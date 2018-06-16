package org.dto;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that represents a RegionDto/Province
 * entites.
 * <p>
 * An instance of this class represents one related record. If the desire arises
 * to manage a List of these instances, then redundant data could exist.
 * 
 * @author Roy Terrell
 */
public interface RegionDto extends TransactionDto {

    /**
     * Set the state id.
     * 
     * @param value
     *            int
     */
    void setStateId(int value);

    /**
     * Return the state id.
     * 
     * @return int
     */
    int getStateId();

    /**
     * Set the abbreviated state lookup.
     * 
     * @param value
     *            String
     */
    void setStateCode(String value);

    /**
     * Return the abbreviated state lookup.
     * 
     * @return int
     */
    String getStateCode();

    /**
     * Set the state name.
     * 
     * @param value
     *            String
     */
    void setStateName(String value);

    /**
     * Return the state name.
     * 
     * @return String
     */
    String getStateName();

    /**
     * Set the indicator that determines whether or not the state record is
     * modifiable.
     * 
     * @param value
     *            <i>Y</i> means the record can be modified and <i>N</i> means
     *            the record cannot be changed.
     */
    void setStatePermcol(String value);

    /**
     * Return the indicator that determines whether or not the state record is
     * modifiable.
     * 
     * @return String where <i>Y</i> means the record can be modified and
     *         <i>N</i> means the record cannot be changed.
     */
    String getStatePermcol();

    /**
     * Set the country id.
     * 
     * @param value
     *            int
     */
    void setCountryId(int value);

    /**
     * Return the country id.
     * 
     * @return int
     */
    int getCountryId();

    /**
     * Set country name
     * 
     * @param value
     */
    void setCountryName(String value);

    /**
     * Return country name
     * 
     * @return String
     */
    String getCountryName();
}