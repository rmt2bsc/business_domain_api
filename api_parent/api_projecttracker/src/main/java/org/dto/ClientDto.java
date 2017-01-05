package org.dto;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that represents the client of a project.
 * 
 * @author Roy Terrell
 * 
 */
public interface ClientDto extends TransactionDto {
    /**
     * Sets the value of member variable clientId
     */
    void setClientId(int value);

    /**
     * Gets the value of member variable clientId
     */
    int getClientId();

    /**
     * Sets the value of member variable businessId
     */
    void setBusinessId(int value);

    /**
     * Gets the value of member variable businessId
     */
    int getBusinessId();

    /**
     * Sets the value of member variable accountNo
     */
    void setAccountNo(String value);

    /**
     * Gets the value of member variable accountNo
     */
    String getAccountNo();

    /**
     * Sets the value of member variable name
     */
    void setClientName(String value);

    /**
     * Gets the value of member variable name
     */
    String getClientName();

    /**
     * Sets the value of member variable billRate
     */
    void setClientBillRate(double value);

    /**
     * Gets the value of member variable billRate
     */
    double getClientBillRate();

    /**
     * Sets the value of member variable otBillRate
     */
    void setClientOtBillRate(double value);

    /**
     * Gets the value of member variable otBillRate
     */
    double getClientOtBillRate();

    /**
     * Sets the value of member variable contactFirstname
     */
    void setClientContactFirstname(String value);

    /**
     * Gets the value of member variable contactFirstname
     */
    String getClientContactFirstname();

    /**
     * Sets the value of member variable contactLastname
     */
    void setClientContactLastname(String value);

    /**
     * Gets the value of member variable contactLastname
     */
    String getClientContactLastname();

    /**
     * Sets the value of member variable contactPhone
     */
    void setClientContactPhone(String value);

    /**
     * Gets the value of member variable contactPhone
     */
    String getClientContactPhone();

    /**
     * Sets the value of member variable contactExt
     */
    void setClientContactExt(String value);

    /**
     * Gets the value of member variable contactExt
     */
    String getClientContactExt();

    /**
     * Sets the value of member variable contactEmail
     */
    void setClientContactEmail(String value);

    /**
     * Gets the value of member variable contactEmail
     */
    String getClientContactEmail();
}
