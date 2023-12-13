package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents an item master entity.
 * 
 * @author rterrell
 * 
 */
public interface ItemMasterDto extends ItemMasterTypeDto {

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

    /**
     * Sets the value of member variable active
     */
    void setActive(int value);

    /**
     * Gets the value of member variable active
     */
    int getActive();

    /**
     * Sets the value of item status id
     */
    void setItemStatusId(int value);

    /**
     * Gets the value of item status id
     */
    int getItemStatusId();

    /**
     * Sets the value of item status description
     */
    void setItemStatusDescription(String value);

    /**
     * Gets the value of item status description
     */
    String getItemStatusDescription();

    /**
     * Get the custom predicate for Unit Cost
     * 
     * @return String
     */
    String getUnitCostPredicate();

    /**
     * Set the custom predicate for Unit Cost
     * 
     * @param predicate
     *            String
     */
    void setUnitCostPredicate(String predicate);

    /**
     * Get the custom predicate for Quantity on Hand
     * 
     * @return String
     */
    String getQtyOnHandPredicate();

    /**
     * Set the custom predicate for Quantity on Hand
     * 
     * @param predicate
     *            String
     */
    void setQtyOnHandPredicate(String predicate);
}
