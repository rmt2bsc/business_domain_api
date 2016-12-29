package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents an item association
 * entity.
 * <p>
 * An inventory item can be assoicated with either a sales order or a purchase
 * order.
 * 
 * @author rterrell
 * 
 */
public interface ItemAssociationDto {

    /**
     * Set the internal unique identifier of the association entity which will
     * represent either the sales order id or the purcahse order id.
     */
    void setAssociationId(int value);

    /**
     * Get the internal unique identifier of the association entity which will
     * represent either the sales order id or the purcahse order id.
     */
    int getAssociationId();

    /**
     * Set the identifier of the assoicated item which can be either the id of
     * an item belonging to a sales order or a purchase order.
     */
    void setAssociationItemId(int value);

    /**
     * Get the identifier of the assoicated item which can be either the id of
     * an item belonging to a sales order or a purchase order.
     */
    int getAssociationItemId();

    /**
     * Set the identifier of the item master item which is basically the same as
     * the <i>assoc_item_id</i>.
     */
    void setItemId(int value);

    /**
     * Get the identifier of the item master item which is basically the same as
     * the <i>assoc_item_id</i>.
     */
    int getItemId();

    /**
     * Set the cost of the item
     */
    void setItemCost(double value);

    /**
     * Get the cost of the item
     */
    double getItemCost();

    /**
     * Set the item's order quantity.
     */
    void setOrderQty(double value);

    /**
     * Get the item's order quantity.
     */
    double getOrderQty();

    /**
     * Set the type of item assoication.
     * <p>
     * This will indicate whether the assocation is a sales order type ("so") or
     * a purchase order type ("po") type.
     */
    void setAssociationType(String value);

    /**
     * Get the type of item assoication.
     * <p>
     * This will indicate whether the assocation is a sales order type ("so") or
     * a purchase order type ("po") type.
     */
    String getAssociationType();
}
