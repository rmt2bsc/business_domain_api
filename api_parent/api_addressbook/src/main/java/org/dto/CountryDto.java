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
public interface CountryDto extends TransactionDto {

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
     * Set the country lookup.
     * 
     * @param value
     *            String.
     */
    void setCountryCode(String value);

    /**
     * Return the counrty lookup.
     * 
     * @return String.
     */
    String getCountryCode();

    /**
     * Set the country name.
     * 
     * @param value
     *            String
     */
    void setCountryName(String value);

    /**
     * Return the country name.
     * 
     * @return String
     */
    String getCountryName();

    /**
     * Set the indicator that determines whether or not the country record is
     * modifiable.
     * 
     * @param value
     *            <i>Y</i> means the record can be modified and <i>N</i> means
     *            the record cannot be changed.
     */
    void setCountryPermcol(String value);

    /**
     * Return the indicator that determines whether or not the country record is
     * modifiable.
     * 
     * @return <i>Y</i> means the record can be modified and <i>N</i> means the
     *         record cannot be changed.
     */
    String getCountryPermcol();

}