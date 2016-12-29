package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents an accounting transaction
 * type entity.
 * 
 * @author rterrell
 * 
 */
public interface XactTypeDto extends XactCategoryDto {

    /**
     * Sets the value of transaction type id
     */
    void setXactTypeId(int value);

    /**
     * Gets the value of transaction type id
     */
    int getXactTypeId();

    /**
     * Sets the value of transaction type code
     */
    void setXactTypeCode(String value);

    /**
     * Gets the value of transaction type code
     */
    String getXactTypeCode();

    /**
     * Sets the value of transaction type description
     */
    void setXactTypeDescription(String value);

    /**
     * Gets the value of transaction type description
     */
    String getXactTypeDescription();

    /**
     * Sets the value of member variable toMultiplier
     */
    void setXactTypeToMultiplier(int value);

    /**
     * Gets the value of member variable toMultiplier
     */
    int getXactTypeToMultiplier();

    /**
     * Sets the value of member variable fromMultiplier
     */
    void setXactTypeFromMultiplier(int value);

    /**
     * Gets the value of member variable fromMultiplier
     */
    int getXactTypeFromMultiplier();

    /**
     * Sets the value of member variable toAcctTypeId
     */
    void setXactTypeToAcctTypeId(int value);

    /**
     * Gets the value of member variable toAcctTypeId
     */
    int getXactTypeToAcctTypeId();

    /**
     * Sets the value of member variable toAcctCatgId
     */
    void setXactTypeToAcctCatgId(int value);

    /**
     * Gets the value of member variable toAcctCatgId
     */
    int getXactTypeToAcctCatgId();

    /**
     * Sets the value of member variable fromAcctTypeId
     */
    void setXactTypeFromAcctTypeId(int value);

    /**
     * Gets the value of member variable fromAcctTypeId
     */
    int getXactTypeFromAcctTypeId();

    /**
     * Sets the value of member variable fromAcctCatgId
     */
    void setXactTypeFromAcctCatgId(int value);

    /**
     * Gets the value of member variable fromAcctCatgId
     */
    int getXactTypeFromAcctCatgId();

    /**
     * Sets the value of member variable hasSubsidiary
     */
    void setXactTypeHasSubsidiary(int value);

    /**
     * Gets the value of member variable hasSubsidiary
     */
    int getXactTypeHasSubsidiary();
}
