package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a purchase order item
 * entity.
 * 
 * @author rterrell
 * 
 */
public interface PurchaseOrderItemDto extends PurchaseOrderDto {
    /**
     * Sets the value of member variable poItemId
     */
    void setPoItemId(int value);

    /**
     * Gets the value of member variable poItemId
     */
    int getPoItemId();

    /**
     * Sets the value of member variable itemId
     */
    void setItemId(int value);

    /**
     * Gets the value of member variable itemId
     */
    int getItemId();

    /**
     * Sets the value of unit cost assigned to purchased order item
     */
    void setActualUnitCost(double value);

    /**
     * Gets the value of unit cost assigned to purchased order item
     */
    double getActualUnitCost();

    /**
     * Sets the value of quantity ordered
     */
    void setQtyOrdered(int value);

    /**
     * Gets the value of quantity ordered
     */
    int getQtyOrdered();

    /**
     * Sets the value of member variable qtyRcvd
     */
    void setQtyRcvd(int value);

    /**
     * Gets the value of member variable qtyRcvd
     */
    int getQtyRcvd();

    /**
     * Sets the value of member variable qtyRtn
     */
    void setQtyRtn(int value);

    /**
     * Gets the value of member variable qtyRtn
     */
    int getQtyRtn();

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
    void setVendorItemSerialNo(String value);

    /**
     * Gets the value of member variable itemSerialNo
     */
    String getVendorItemSerialNo();

    /**
     * Sets the value of member variable qtyOnHand
     */
    void setVendorQtyOnHand(int value);

    /**
     * Gets the value of member variable qtyOnHand
     */
    int getVendorQtyOnHand();

    /**
     * Sets the value of member variable unitCost
     */
    void setVendorUnitCost(double value);

    /**
     * Gets the value of member variable unitCost
     */
    double getVendorUnitCost();

    /**
     * Sets the value of member variable markup
     */
    void setVendorMarkup(double value);

    /**
     * Gets the value of member variable markup
     */
    double getVendorMarkup();

    /**
     * Sets the value of member variable overrideRetail
     */
    void setVendorOverrideRetail(int value);

    /**
     * Gets the value of member variable overrideRetail
     */
    int getVendorOverrideRetail();

}
