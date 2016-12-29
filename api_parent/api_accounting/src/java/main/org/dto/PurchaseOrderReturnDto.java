package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a purchase order return
 * entity.
 * 
 * @author rterrell
 * 
 */
public interface PurchaseOrderReturnDto {

    /**
     * Set purchase order return id
     */
    void setPoReturnId(int value);

    /**
     * Get purchase order return id
     */
    int getPoReturnId();

    /**
     * Sets the value of member variable poId
     */
    void setPoId(int value);

    /**
     * Gets the value of member variable poId
     */
    int getPoId();

    /**
     * Sets the value of member variable xactId
     */
    void setXactId(int value);

    /**
     * Gets the value of member variable xactId
     */
    int getXactId();

    /**
     * Sets the value of member variable reason
     */
    void setReason(String value);

    /**
     * Gets the value of member variable reason
     */
    String getReason();
}
