package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a vendor item entity
 * 
 * @author rterrell
 * 
 */
public interface VendorItemDto extends ItemMasterDto {

    /**
     * Sets the value of member variable vendor item serial number
     */
    void setVendorItemSerialNo(String value);

    /**
     * Gets the value of member variable vendor item serial number
     */
    String getVendorItemSerialNo();
    
    /**
     * Sets the value of member variable vendor item unit cost
     */
    void setVendorItemUnitCost(double value);

    /**
     * Gets the value of member variable vendor item unit cost
     */
    double getVendorItemUnitCost();

}
