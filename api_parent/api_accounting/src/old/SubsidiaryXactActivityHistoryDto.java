package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a common subsidiary's 
 * transaction activity history.
 * 
 * @author rterrell
 *
 */
public interface SubsidiaryXactActivityHistoryDto extends CommonAccountingDto {

    /**
     * Sets the value of transaction id
     */
    void setXactId(int value);

    /**
     * Gets the value of transaction id
     */
    int getXactId();
    
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
    void setTenderId(int value);

    /**
     * Gets the value of member variable tenderId
     */
    int getTenderId();

    /**
     * Sets the value of member variable negInstrNo
     */
    void setNegInstrNo(String value);

    /**
     * Gets the value of member variable negInstrNo
     */
    String getNegInstrNo();

    /**
     * Sets the value of member variable bankTransInd
     */
    void setBankTransInd(String value);

    /**
     * Gets the value of member variable bankTransInd
     */
    String getBankTransInd();

    /**
     * Sets the value of member variable confirmNo
     */
    void setConfirmNo(String value);

    /**
     * Gets the value of member variable confirmNo
     */
    String getConfirmNo();

    /**
     * Sets the value of member variable entityRefNo
     */
    void setEntityRefNo(String value);

    /**
     * Gets the value of member variable entityRefNo
     */
    String getEntityRefNo();

    /**
     * Sets the value of member variable postedDate
     */
    void setPostedDate(java.util.Date value);

    /**
     * Gets the value of member variable postedDate
     */
    java.util.Date getPostedDate();

    /**
     * Sets the value of member variable reason
     */
    void setReason(String value);

    /**
     * Gets the value of member variable reason
     */
    String getReason();

    /**
     * Sets the value of member variable documentId
     */
    void setDocumentId(int value);

    /**
     * Gets the value of member variable documentId
     */
    int getDocumentId();
    
    /**
     * Sets the value transaction type description
     */
    void setXactTypeDescription(String value);

    /**
     * Gets the value transaction type description
     */
    String getXactTypeDescription();
    
    /**
     * Sets the value of subsidiary transaction activity amount
     */
    void setSubsidiaryXactActvAmount(double value);

    /**
     * Gets the value of subsidiary transaction activity amount
     */
    double getSubsidiaryXactActvAmount();
}
