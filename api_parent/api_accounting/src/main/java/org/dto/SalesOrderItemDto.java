package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a sales order item
 * entity.
 * 
 * @author rterrell
 * 
 */
public interface SalesOrderItemDto extends SalesOrderDto {

    /**
     * Sets the value of member variable soItemId
     */
    void setSoItemId(int value);

    /**
     * Gets the value of member variable soItemId
     */
    int getSoItemId();

    /**
     * Sets the value of member variable itemId
     */
    void setItemId(int value);

    /**
     * Gets the value of member variable itemId
     */
    int getItemId();

    /**
     * Sets the value of sales order id
     */
    void setSalesOrderId(int value);

    /**
     * Gets the value of sales order id
     */
    int getSalesOrderId();

    /**
     * Set the sales order item item name override
     */
    void setItemNameOverride(String value);

    /**
     * Get the sales order item item name override
     */
    String getItemNameOverride();

    /**
     * Set the sales order item order quantity
     */
    void setOrderQty(double value);

    /**
     * Get the sales order item order quantity
     */
    double getOrderQty();

    /**
     * Set the sales order item back order quantity
     */
    void setBackOrderQty(double value);

    /**
     * Get the sales order item back order quantity
     */
    double getBackOrderQty();

    /**
     * Set the sales order item unit cost
     */
    void setInitUnitCost(double value);

    /**
     * Get the sales order item unit cost
     */
    double getInitUnitCost();

    /**
     * Set the sales order item intial mark up
     */
    void setInitMarkup(double value);

    /**
     * Get the sales order item intial mark up
     */
    double getInitMarkup();

    /**
     * Set the item master name
     */
    void setImName(String value);

    /**
     * Get the item master name
     */
    String getImName();

    /**
     * Set item master type id
     */
    void setImItemTypeId(int value);

    /**
     * Get item master type id
     */
    int getImItemTypeId();

    /**
     * Set item master type description
     */
    void setImItemTypeDescription(String value);

    /**
     * Get item master type description
     */
    String getImItemTypeDescription();

    /**
     * Set item master vendor item number
     */
    void setImVendorItemNo(String value);

    /**
     * Get item master vendor item number
     */
    String getImVendorItemNo();

    /**
     * Set item master serial number
     */
    void setImSerialNo(String value);

    /**
     * Get item master serial number
     */
    String getImSerialNo();

    /**
     * Set item master quantity on hand
     */
    void setImQtyOnHand(int value);

    /**
     * Get item master quantity on hand
     */
    int getImQtyOnHand();

    /**
     * Set item master unit cost
     */
    void setImUnitCost(double value);

    /**
     * Get item master unit cost
     */
    double getImUnitCost();

    /**
     * Set item master mark up
     */
    void setImMarkup(double value);

    /**
     * Get item master mark up
     */
    double getImMarkup();

    /**
     * Set item master retail price
     */
    void setImRetailPrice(double value);

    /**
     * Get item master retail price
     */
    double getImRetailPrice();
    
    /**
     * Get the vendor id of the item
     * @return
     */
    int getImVendorId();
    
    /**
     * Set the vendor of the item.
     * @param vendorId
     */
    void setImVendorId(int vendorId);

}
