package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a accounting transaction
 * entity.
 * 
 * @author rterrell
 * 
 */
public interface XactDto extends XactTypeDto {

    /**
     * Sets the value of transaction id
     */
    void setXactId(int value);

    /**
     * Gets the value of transaction id
     */
    int getXactId();

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
    void setDocumentId(int value);

    /**
     * Gets the value of member variable documentId
     */
    int getDocumentId();

    /**
     * Sets the value of transaction code group id
     */
    void setXactCodeGrpId(int value);

    /**
     * Gets the value of transaction code group id
     */
    int getXactCodeGrpId();

    /**
     * Sets the value of transaction code group description
     */
    void setXactCodeGrpDescription(String value);

    /**
     * Gets the value of transaction code group description
     */
    String getXactCodeGrpDescription();

    /**
     * Sets the value of transaction code id
     */
    void setXactCodeId(int value);

    /**
     * Gets the value of transaction code id
     */
    int getXactCodeId();

    /**
     * Sets the value of transaction code description
     */
    void setXactCodeDescription(String value);

    /**
     * Gets the value of transaction code description
     */
    String getXactCodeDescription();

//    /**
//     * Sets the value of custom criteria
//     */
//    void setCriteria(String value);
//
//    /**
//     * Gets the value of custom criteria
//     */
//    String getCriteria();

}
