package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents an accounting transaction
 * type item activity entity.
 * 
 * @author rterrell
 * 
 */
public interface XactTypeItemActivityDto extends XactTypeItemDto {

    /**
     * Sets the value of transaction type item activity id
     */
    void setXactTypeItemActvId(int value);

    /**
     * Gets the value of transaction type item activity id
     */
    int getXactTypeItemActvId();

    /**
     * Sets the value of transaction type item activity description
     */
    void setXactTypeItemActvName(String value);

    /**
     * Gets the value of transaction type item activity description
     */
    String getXactTypeItemActvName();

    /**
     * Sets the value of transaction activity amount
     */
    void setActivityAmount(double value);

    /**
     * Gets the value of transaction activity amount
     */
    double getActivityAmount();
}
