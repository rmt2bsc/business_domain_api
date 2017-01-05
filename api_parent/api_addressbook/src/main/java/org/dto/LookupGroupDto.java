package org.dto;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that represents a lookup groups.
 * <p>
 * An instance of this class represents one related record. If the desire arises
 * to manage a List of these instances, then redundant data could exist.
 * 
 * @author rterrell
 * 
 */
public interface LookupGroupDto extends TransactionDto {

    /**
     * Set group id.
     * 
     * @param value
     *            int
     */
    void setGrpId(int value);

    /**
     * Get the group id.
     * 
     * @return int
     */
    int getGrpId();

    /**
     * Set group description.
     * 
     * @param value
     *            String
     */
    void setGrpDescr(String value);

    /**
     * Get group description.
     * 
     * @return String
     */
    String getGrpDescr();

    /**
     * Set the indicator that determines whether or not the group record is
     * modifiable.
     * 
     * @param value
     *            <i>Y</i> means the record can be modified and <i>N</i> means
     *            the record cannot be changed.
     */
    void setGrpPermcol(String value);

    /**
     * Get the indicator that determines whether or not the group record is
     * modifiable.
     * 
     * @return <i>Y</i> means the record can be modified and <i>N</i> means the
     *         record cannot be changed.
     */
    String getGrpPermcol();
}
