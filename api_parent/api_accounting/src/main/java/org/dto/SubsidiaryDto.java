package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a common subsidiary
 * account.
 * 
 * @author Roy Terrell
 * 
 */
public interface SubsidiaryDto extends SubsidiaryContactInfoDto {

    /**
     * Sets the internal unique identifier of the subsidiary account such as
     * customer or creditor.
     */
    void setSubsidiaryId(int value);

    /**
     * Gets the internal unique identifier of the subsidiary account such as
     * customer or creditor
     */
    int getSubsidiaryId();

    /**
     * Sets the GL account id.
     */
    void setAcctId(int value);

    /**
     * Get the GL account id.
     */
    int getAcctId();

    /**
     * Set the subsidiary account number
     */
    void setAccountNo(String value);

    /**
     * Get the subsidiary account number
     */
    String getAccountNo();

    /**
     * Set the credit limit
     */
    void setCreditLimit(double value);

    /**
     * Get the credit limit
     */
    double getCreditLimit();

    /**
     * Set the active flag
     */
    void setActive(int value);

    /**
     * Get the active flag
     */
    int getActive();
}
