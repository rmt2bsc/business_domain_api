package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a accounting transaction
 * category entity.
 * 
 * @author rterrell
 * 
 */
public interface XactCategoryDto extends CommonAccountingDto {

    /**
     * Set the transaction category id.
     */
    void setXactCatgId(int value);

    /**
     * Get the transaction category id.
     */
    int getXactCatgId();

    /**
     * Set the transaction category description.
     */
    void setXactCatgDescription(String value);

    /**
     * Get the transaction category description.
     */
    String getXactCatgDescription();

    /**
     * Set the transaction category code.
     */
    void setXactCatgCode(String value);

    /**
     * Get the transaction category code.
     */
    String getXactCatgCode();
}
