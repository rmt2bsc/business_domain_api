package org.dto;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that represents a purchase order status
 * history entity.
 * 
 * @author rterrell
 * 
 */
public interface PurchaseOrderStatusDto extends TransactionDto {

    /**
     * Set the purchase order status id
     */
    void setPoStatusId(int value);

    /**
     * Get the purchase order status id
     */
    int getPoStatusId();

    /**
     * Set purchase order status description
     */
    void setPoStatusDescription(String value);

    /**
     * Get purchase order status description
     */
    String getPoStatusDescription();
}
