package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents an account category.
 * 
 * @author rterrell
 *
 */
public interface AccountCategoryDto extends CommonAccountingDto {

    /**
     * Sets the value of member variable acctTypeId
     */
    void setAcctTypeId(int value);

    /**
     * Gets the value of member variable acctTypeId
     */
    int getAcctTypeId();
}
