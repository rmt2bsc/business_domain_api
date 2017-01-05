package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a common subsidiary's
 * transaction activity history.
 * 
 * @author rterrell
 * 
 */
public interface SubsidiaryXactHistoryDto extends SubsidiaryActivityDto {

    /**
     * Sets the value of member variable xactTypeId
     */
    void setXactTypeId(int value);

    /**
     * Gets the value of member variable xactTypeId
     */
    int getXactTypeId();

    /**
     * Sets the value of member variable xactSubtypeId
     */
    void setXactSubtypeId(int value);

    /**
     * Gets the value of member variable xactSubtypeId
     */
    int getXactSubtypeId();

    /**
     * Sets the value of member variable xactDate
     */
    void setXactDate(java.util.Date value);

    /**
     * Gets the value of member variable xactDate
     */
    java.util.Date getXactDate();

    /**
     * Sets the value of member variable xactAmount
     */
    void setXactAmount(double value);

    /**
     * Gets the value of member variable xactAmount
     */
    double getXactAmount();

    /**
     * Sets the value of member variable tenderId
     */
    void setXactTenderId(int value);

    /**
     * Gets the value of member variable tenderId
     */
    int getXactTenderId();

    /**
     * Sets the value of member variable negInstrNo
     */
    void setXactNegInstrNo(String value);

    /**
     * Gets the value of member variable negInstrNo
     */
    String getXactNegInstrNo();

    /**
     * Sets the value of member variable bankTransInd
     */
    void setXactBankTransInd(String value);

    /**
     * Gets the value of member variable bankTransInd
     */
    String getXactBankTransInd();

    /**
     * Sets the value of member variable confirmNo
     */
    void setXactConfirmNo(String value);

    /**
     * Gets the value of member variable confirmNo
     */
    String getXactConfirmNo();

    /**
     * Sets the value of member variable entityRefNo
     */
    void setXactEntityRefNo(String value);

    /**
     * Gets the value of member variable entityRefNo
     */
    String getXactEntityRefNo();

    /**
     * Sets the value of member variable postedDate
     */
    void setXactPostedDate(java.util.Date value);

    /**
     * Gets the value of member variable postedDate
     */
    java.util.Date getXactPostedDate();

    /**
     * Sets the value of member variable reason
     */
    void setXactReason(String value);

    /**
     * Gets the value of member variable reason
     */
    String getXactReason();

    /**
     * Sets the value of member variable documentId
     */
    void setXactDocumentId(int value);

    /**
     * Gets the value of member variable documentId
     */
    int getXactDocumentId();

    /**
     * Sets the value transaction type description
     */
    void setXactTypeDescription(String value);

    /**
     * Gets the value transaction type description
     */
    String getXactTypeDescription();

}
