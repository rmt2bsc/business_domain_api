package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a general ledger account
 * category entity.
 * 
 * @author rterrell
 * 
 */
public interface AccountCategoryDto extends CommonAccountingDto {

    /**
     * Sets the value of acct Catg Id
     */
    void setAcctCatgId(int value);

    /**
     * Gets the value of acct Catg Id
     */
    int getAcctCatgId();

    /**
     * Sets the value of acct Type Id
     */
    void setAcctTypeId(int value);

    /**
     * Gets the value of acct Type Id
     */
    int getAcctTypeId();

    /**
     * Sets the value of the account category description
     */
    void setAcctCatgDescription(String value);

    /**
     * Gets the value of account category description
     */
    String getAcctCatgDescription();
}
