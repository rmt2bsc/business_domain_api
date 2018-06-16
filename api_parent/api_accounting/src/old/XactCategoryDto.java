package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents an accounting tansaction 
 * category entity.
 * 
 * @author rterrell
 *
 */
public interface XactCategoryDto extends CommonAccountingDto {

    /**
     * Sets the value of member variable code
     */
    void setCode(String value);

    /**
     * Gets the value of member variable code
     */
    String getCode();
}
