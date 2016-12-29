package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents an accounting transaction code entity.
 * 
 * @author rterrell
 *
 */
public interface XactCodeDto extends CommonAccountingDto {

    /**
    * Sets the value of member variable xactCodeGrpId
    */
    void setXactCodeGrpId(int value);

    /**
     * Gets the value of member variable xactCodeGrpId
     */
    int getXactCodeGrpId();
}
