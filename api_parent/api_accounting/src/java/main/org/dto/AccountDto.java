package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a general ledger account
 * entity.
 * 
 * @author rterrell
 * 
 */
public interface AccountDto extends CommonAccountingDto {

    /**
     * Sets the value of gl account id
     */
    void setAcctId(int value);

    /**
     * Gets the value of gl account id
     */
    int getAcctId();

    /**
     * Sets the value of acct Type Id
     */
    void setAcctTypeId(int value);

    /**
     * Gets the value of acct Type Id
     */
    int getAcctTypeId();

    /**
     * Sets the value of acct Catg Id
     */
    void setAcctCatgId(int value);

    /**
     * Gets the value of acct Catg Id
     */
    int getAcctCatgId();

    /**
     * Sets the value of acct Seq
     */
    void setAcctSeq(int value);

    /**
     * Gets the value of acct Seq
     */
    int getAcctSeq();

    /**
     * Sets the value of acct No
     */
    void setAcctNo(String value);

    /**
     * Gets the value of acct No
     */
    String getAcctNo();

    /**
     * Sets the value of account name
     */
    void setAcctName(String value);

    /**
     * Gets the value of account name
     */
    String getAcctName();

    /**
     * Sets the value of account code
     */
    void setAcctCode(String value);

    /**
     * Gets the value of account code
     */
    String getAcctCode();

    /**
     * Sets the value of account description
     */
    void setAcctDescription(String value);

    /**
     * Gets the value of account description
     */
    String getAcctDescription();

    /**
     * Sets the value of acct Bal type Id
     */
    void setBalanceTypeId(int value);

    /**
     * Gets the value of acct Bal type Id
     */
    int getBalanceTypeId();
}
