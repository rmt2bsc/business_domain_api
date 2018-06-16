package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a sales order status
 * history entity.
 * 
 * @author rterrell
 * 
 */
public interface SalesOrderStatusHistDto extends SalesOrderStatusDto {

    /**
     * Set sales order status history id
     */
    void setSoStatusHistId(int value);

    /**
     * Get sales order status history id
     */
    int getSoStatusHistId();

    /**
     * Sets the value of member variable soId
     */
    void setSoId(int value);

    /**
     * Gets the value of member variable soId
     */
    int getSoId();

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

    /**
     * Sets the value of member variable reason
     */
    void setReason(String value);

    /**
     * Gets the value of member variable reason
     */
    String getReason();
}
