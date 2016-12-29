package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a purchase order item
 * return entity.
 * 
 * @author rterrell
 * 
 */
public interface PurchaseOrderReturnItemDto {

    /**
     * Sets the value of member variable poReturnId
     */
    void setPoItemReturnId(int value);

    /**
     * Gets the value of member variable poReturnId
     */
    int getPoItemReturnId();

    /**
     * Sets the value of member variable poReturnId
     */
    void setPoReturnId(int value);

    /**
     * Gets the value of member variable poReturnId
     */
    int getPoReturnId();

    /**
     * Sets the value of member variable itemId
     */
    void setItemId(int value);

    /**
     * Gets the value of member variable itemId
     */
    int getItemId();

    /**
     * Sets the value of member variable qtyRtn
     */
    void setQtyRtn(int value);

    /**
     * Gets the value of member variable qtyRtn
     */
    int getQtyRtn();
}
