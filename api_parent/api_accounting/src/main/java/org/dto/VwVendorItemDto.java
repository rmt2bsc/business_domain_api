package org.dto;

import com.api.foundation.BaseDto;

/**
 * Data Transfer Object (DTO) contract that represents a extended vendor item entity
 * 
 * @author Roy Terrell
 * 
 */
public interface VwVendorItemDto extends BaseDto {

    /**
     * Set the item id
     */
    void setItemId(int value);

    /**
     * Get the item id
     */
    int getItemId();

    /**
     * Set the item description
     */
    void setItemName(String value);

    /**
     * Get the item description
     */
    String getItemName();

    /**
     * Sets the value of member variable creditorId
     */
    void setVendorId(int value);

    /**
     * Gets the value of member variable creditorId
     */
    int getVendorId();

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
     * Sets the value of member variable overrideRetail
     */
    void setOverrideRetail(int value);

    /**
     * Gets the value of member variable overrideRetail
     */
    int getOverrideRetail();
}
