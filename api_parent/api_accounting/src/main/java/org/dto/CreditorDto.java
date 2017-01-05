package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents an creditor.
 * 
 * @author Roy Terrell
 */
public interface CreditorDto extends SubsidiaryDto {

    /**
     * Sets the value of creditor id
     */
    void setCreditorId(int value);

    /**
     * Gets the value of creditor id
     */
    int getCreditorId();

    /**
     * Set the creditor type id
     */
    void setCreditorTypeId(int value);

    /**
     * Get the creditor type id
     */
    int getCreditorTypeId();

    /**
     * Set the creditor's annual percentage rate
     */
    void setApr(double value);

    /**
     * Get the creditor's annual percentage rate
     */
    double getApr();

    /**
     * Set the creditor's external account number
     */
    void setExtAccountNumber(String value);

    /**
     * Get the creditor's external account number
     */
    String getExtAccountNumber();

}