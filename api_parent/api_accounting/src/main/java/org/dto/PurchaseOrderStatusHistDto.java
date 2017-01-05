package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a purchase order status
 * history entity.
 * 
 * @author rterrell
 * 
 */
public interface PurchaseOrderStatusHistDto extends PurchaseOrderStatusDto {

    /**
     * Set the purchase order status history id
     */
    void setPoStatusHistId(int value);

    /**
     * Get the purchase order status history id
     */
    int getPoStatusHistId();

    /**
     * Set the purchase order id
     */
    void setPoId(int value);

    /**
     * Get the purchase order id
     */
    int getPoId();

    /**
     * Sets the value of member variable effectiveDate
     */
    void setEffectiveDate(java.util.Date value);

    /**
     * Gets the value of member variable effectiveDate
     */
    java.util.Date getEffectiveDate();

    /**
     * Sets the value of member variable endDate
     */
    void setEndDate(java.util.Date value);

    /**
     * Gets the value of member variable endDate
     */
    java.util.Date getEndDate();
}
