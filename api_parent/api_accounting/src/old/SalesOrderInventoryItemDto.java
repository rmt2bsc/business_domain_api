package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a sales order item entity.
 * 
 * @author rterrell
 *
 */
public interface SalesOrderInventoryItemDto extends SalesOrderItemDto {

    /**
     * Sets the value of member variable itemTypeId
     */
    void setItemTypeId(int value);

    /**
     * Gets the value of member variable itemTypeId
     */
    int getItemTypeId();

    /**
     * Sets the value of item type description
     */
    void setItemTypeDescription(String value);

    /**
     * Gets the value of item type description
     */
    String getItemTypeDescription();
    
    /**
     * Sets the value of member variable vendorItemNo
     */
    void setVendorItemNo(String value);

    /**
     * Gets the value of member variable vendorItemNo
     */
    String getVendorItemNo();

    /**
     * Sets the value of member variable itemSerialNo
     */
    void setItemSerialNo(String value);

    /**
     * Gets the value of member variable itemSerialNo
     */
    String getItemSerialNo();

    /**
     * Sets the value of member variable qtyOnHand
     */
    void setQtyOnHand(int value);

    /**
     * Gets the value of member variable qtyOnHand
     */
    int getQtyOnHand();

    /**
     * Sets the value of member variable unitCost
     */
    void setUnitCost(double value);

    /**
     * Gets the value of member variable unitCost
     */
    double getUnitCost();

    /**
     * Sets the value of member variable markup
     */
    void setMarkup(double value);

    /**
     * Gets the value of member variable markup
     */
    double getMarkup();

    /**
     * Sets the value of member variable retailPrice
     */
    void setRetailPrice(double value);

    /**
     * Gets the value of member variable retailPrice
     */
    double getRetailPrice();

    /**
     * Sets the value of member variable overrideRetail
     */
    void setOverrideRetail(int value);

    /**
     * Gets the value of member variable overrideRetail
     */
    int getOverrideRetail();
}
