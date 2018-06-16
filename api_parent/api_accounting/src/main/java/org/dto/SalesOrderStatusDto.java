package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a sales order status
 * entity.
 * 
 * @author rterrell
 * 
 */
public interface SalesOrderStatusDto extends CommonAccountingDto {

    /**
     * Set sales order status id
     */
    void setSoStatusId(int value);

    /**
     * Get sales order status id
     */
    int getSoStatusId();

    /**
     * Set sales order status description
     */
    void setSoStatusDescription(String value);

    /**
     * Get sales order status description
     */
    String getSoStatusDescription();
}
