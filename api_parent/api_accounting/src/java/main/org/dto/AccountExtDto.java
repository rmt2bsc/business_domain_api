package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a general ledger account
 * extension entity.
 * 
 * @author rterrell
 * 
 */
public interface AccountExtDto extends CommonAccountingDto {

    /**
     * Sets the value of gl account id
     */
    void setAcctId(int value);

    /**
     * Gets the value of gl account id
     */
    int getAcctId();

    /**
     * Sets the value of member variable acctTypeId
     */
    void setAcctTypeId(int value);

    /**
     * Gets the value of member variable acctTypeId
     */
    int getAcctTypeId();

    /**
     * Sets the value of member variable acctCatgId
     */
    void setAcctCatgId(int value);

    /**
     * Gets the value of member variable acctCatgId
     */
    int getAcctCatgId();

    /**
     * Sets the value of member variable acctSeq
     */
    void setAcctSeq(int value);

    /**
     * Gets the value of member variable acctSeq
     */
    int getAcctSeq();

    /**
     * Sets the value of member variable acctNo
     */
    void setAcctNo(String value);

    /**
     * Gets the value of member variable acctNo
     */
    String getAcctNo();

    /**
     * Sets the value of member variable code
     */
    void setAcctCode(String value);

    /**
     * Gets the value of member variable code
     */
    String getAcctCode();

    /**
     * Sets the value of member variable name
     */
    void setAcctName(String value);

    /**
     * Gets the value of member variable name
     */
    String getAcctName();

    /**
     * Sets the value of member variable description
     */
    void setAcctDescription(String value);

    /**
     * Gets the value of member variable description
     */
    String getAcctDescription();

    /**
     * Sets the value of member variable acctBaltypeId
     */
    void setBalanceTypeId(int value);

    /**
     * Gets the value of member variable acctBaltypeId
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

    /**
     * Sets the value of the account category description
     */
    void setAcctCatgDescription(String value);

    /**
     * Gets the value of account category description
     */
    String getAcctCatgDescription();
}
