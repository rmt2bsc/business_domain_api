package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a general ledger account type.
 * 
 * @author rterrell
 *
 */
public interface AccountTypesDto extends CommonAccountingDto {

    /**
     * Sets the value of member variable acctBaltypeId
     */
    void setAcctBaltypeId(int value);

    /**
     * Gets the value of member variable acctBaltypeId
     */
    int getAcctBaltypeId();
}
