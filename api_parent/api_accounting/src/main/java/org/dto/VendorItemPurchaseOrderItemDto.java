package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a combination of vendor item and purchase order item entities.
 * 
 * @author Roy Terrell
 * 
 */
public interface VendorItemPurchaseOrderItemDto extends PurchaseOrderItemDto {

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
