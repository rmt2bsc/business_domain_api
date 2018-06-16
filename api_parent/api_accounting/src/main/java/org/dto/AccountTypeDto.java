package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a general ledger account
 * type entity.
 * 
 * @author rterrell
 * 
 */
public interface AccountTypeDto extends CommonAccountingDto {

    /**
     * Sets the value of acct Type Id
     */
    void setAcctTypeId(int value);

    /**
     * Gets the value of acct Type Id
     */
    int getAcctTypeId();

    /**
     * Sets the value of acct Bal type Id
     */
    void setBalanceTypeId(int value);

    /**
     * Gets the value of acct Bal type Id
     */
    int getBalanceTypeId();

    /**
     * Sets the value of the account type description
     */
    void setAcctTypeDescription(String value);

    /**
     * Gets the value of account type description
     */
    String getAcctTypeDescription();
}
