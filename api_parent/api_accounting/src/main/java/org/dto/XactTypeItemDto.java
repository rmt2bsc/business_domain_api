package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents an accounting transaction
 * type item entity.
 * 
 * @author rterrell
 * 
 */
public interface XactTypeItemDto extends XactDto {

    /**
     * Set the value of transaction item id
     */
    void setXactItemId(int value);

    /**
     * Get the value of transaction item id
     */
    int getXactItemId();

    /**
     * Set the value of transaction item name
     */
    void setXactItemName(String value);

    /**
     * Get the value of transaction item name
     */
    String getXactItemName();
}
