package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a accounting transaction
 * code entitiy.
 * 
 * @author Roy Terrell
 * 
 */
public interface XactCodeDto extends CommonAccountingDto {

    /**
     * Sets the value of transaction code group id
     */
    void setGrpId(int value);

    /**
     * Gets the value of transaction code group id
     */
    int getGrpId();

}
