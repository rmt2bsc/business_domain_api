package org.dto;

import com.api.foundation.TransactionDto;

/**
 * A common contract for accounting DTO entities.
 * 
 * @author rterrell
 * 
 */
public interface CommonAccountingDto extends TransactionDto {

    /**
     * Sets the internal unique id of an accounting entity.
     * 
     * @param value
     *            int
     */
    void setEntityId(int value);

    /**
     * Gets the internal unique id of an accounting entity.
     * 
     * @return int
     */
    int getEntityId();

    /**
     * Sets the entity name.
     * 
     * @param value
     *            String
     */
    void setEntityName(String value);

    /**
     * Get the entity name.
     * 
     * @return String
     */
    String getEntityName();

}
