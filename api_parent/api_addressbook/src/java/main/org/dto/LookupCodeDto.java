package org.dto;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that represents a general codes entity.
 * <p>
 * An instance of this class represents one related record. If the desire arises
 * to manage a List of these instances, then redundant data could exist.
 * 
 * @author Roy Terrell
 */
public interface LookupCodeDto extends TransactionDto {

    /**
     * Set the id for the detail lookup.
     * 
     * @param value
     *            int
     */
    void setCodeId(int value);

    /**
     * Get the id for the detail lookup.
     * 
     * @return nt
     */
    int getCodeId();

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
     * Set the lookup's short description.
     * 
     * @param value
     *            String
     */
    void setCodeShortDesc(String value);

    /**
     * Get the lookup's short description.
     * 
     * @return String
     */
    String getCodeShortName();

    /**
     * Set the lookup's long name.
     * 
     * @param value
     *            String
     */
    void setCodeLongName(String value);

    /**
     * Get the lookup's long name.
     * 
     * @return String
     */
    String getCodeLongName();

    /**
     * Set the lookup's general indicator value.
     * 
     * @param value
     *            String
     */
    void setCodeGenIndValue(String value);

    /**
     * Get the lookup's general indicator value.
     * 
     * @return String
     */
    String getCodeGenIndValue();

    /**
     * Set the indicator that determines whether or not the group record is
     * modifiable.
     * 
     * @param value
     *            <i>Y</i> means the record can be modified and <i>N</i> means
     *            the record cannot be changed.
     */
    void setCodePermcol(String value);

    /**
     * Get the indicator that determines whether or not the lookup record is
     * modifiable.
     * 
     * @return <i>Y</i> means the record can be modified and <i>N</i> means the
     *         record cannot be changed.
     */
    String getCodePermcol();
}