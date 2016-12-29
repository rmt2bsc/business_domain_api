package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents an creditor transaction
 * history entity.
 * 
 * @author Roy Terrell
 */
public interface CreditorXactHistoryDto extends SubsidiaryXactHistoryDto {

    /**
     * Sets the value of creditor id
     */
    void setCreditorId(int value);

    /**
     * Gets the value of creditor id
     */
    int getCreditorId();

    /**
     * Sets the creditor's GL account id
     */
    void setAcctId(int value);

    /**
     * Get the creditor's GL account id
     */
    int getAcctId();

    /**
     * Set the creditor type id
     */
    void setCreditorTypeId(int value);

    /**
     * Get the creditor type id
     */
    int getCreditorTypeId();

    /**
     * Set the creditor's business id
     */
    void setBusinessId(int value);

    /**
     * Get the creditor's business id
     */
    int getBusinessId();

    /**
     * Set the creditor's account number
     */
    void setAccountNumber(String value);

    /**
     * Get the creditor's account number
     */
    String getAccountNumber();

    /**
     * Set the creditor's spending charge limit
     */
    void setCreditLimit(double value);

    /**
     * Get the creditor's spending charge limit
     */
    double getCreditLimit();

    /**
     * Set the creditor's annual percentage rate
     */
    void setApr(double value);

    /**
     * Get the creditor's annual percentage rate
     */
    double getApr();

    /**
     * Set the creditor's active flag
     */
    void setActive(int value);

    /**
     * Get the creditor's active flag
     */
    int getActive();

    /**
     * Set the creditor's external account number
     */
    void setExtAccountNumber(String value);

    /**
     * Get the creditor's external account number
     */
    String getExtAccountNumber();

    /**
     * Set the creditor type description
     */
    void setCreditorTypeDescription(String value);

    /**
     * Get the creditor type description
     */
    String getCreditorTypeDescription();
}