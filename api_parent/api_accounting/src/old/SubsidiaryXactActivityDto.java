package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents an transaction activity of 
 * a subsidiary such as customer and creditor entity.
 * 
 * @author rterrell
 *
 */
public interface SubsidiaryXactActivityDto extends CommonAccountingDto {

    /**
     * Sets the internal unique identifier of the customer or creditor account.
     */
    void setSubsidiaryId(int value);

    /**
     * Gets the internal unique identifier of the customer or creditor account.
     */
    int getSubsidiaryId();

    /**
     * Sets the value of member variable xactId
     */
    void setXactId(int value);

    /**
     * Gets the value of member variable xactId
     */
    int getXactId();

    /**
     * Sets the value of member variable amount
     */
    void setAmount(double value);

    /**
     * Gets the value of member variable amount
     */
    double getAmount();

}
