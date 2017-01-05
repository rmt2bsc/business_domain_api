package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents an item master type
 * entity.
 * 
 * @author rterrell
 * 
 */
public interface ItemMasterTypeDto extends CommonAccountingDto {

    /**
     * Set the item type id
     * 
     * @param value
     *            int
     */
    void setItemTypeId(int value);

    /**
     * Get the item type id.
     * 
     * @return int
     */
    int getItemTypeId();

    /**
     * Set the item type description.
     * 
     * @param value
     *            String
     */
    void setItemTypeDescription(String value);

    /**
     * Get the item type description.
     * 
     * @return String
     */
    String getItemTypeDescription();
}
