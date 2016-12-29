package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a record or object that
 * is common ao all types of transactions.
 * 
 * @author Roy Terrell
 * 
 */
public interface CommonXactDto {

    /**
     * Sets the value of the subsidiary id
     */
    void setSubsidiaryId(int value);

    /**
     * Gets the value of the subsidiary id
     */
    int getSubsidiaryId();

    /**
     * Sets the value of specific transaction level 1 id
     */
    void setSpecXactLevel1Id(int value);

    /**
     * Gets the value of specific transaction level 1 id
     */
    int getSpecXactLevel1Id();

    /**
     * Sets the value of specific transaction level 1 date
     */
    void setSpecXactLevel1Date(java.util.Date value);

    /**
     * Gets the value of specific transaction level 1 date
     */
    java.util.Date getSpecXactLevel1Date();

    /**
     * Sets the value of specific transaction level 2 id
     */
    void setSpecXactLevel2Id(int value);

    /**
     * Gets the value of specific transaction level 2 id
     */
    int getSpecXactLevel2Id();

    /**
     * Sets the value of specific transaction level 2 date
     */
    void setSpecXactLevel2Date(java.util.Date value);

    /**
     * Gets the value of specific transaction level 2 date
     */
    java.util.Date getSpecXactLevel2Date();

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
     * Sets the value of member variable xactDate String
     */
    void setXactDateStr(String value);

    /**
     * Gets the value of member variable xactDate String
     */
    String getXactDateStr();

    /**
     * Sets the value of member variable xactAmount
     */
    void setXactAmount(double value);

    /**
     * Gets the value of member variable xactAmount
     */
    double getXactAmount();

    /**
     * Sets the value of member variable confirmNo
     */
    void setConfirmNo(String value);

    /**
     * Gets the value of member variable confirmNo
     */
    String getConfirmNo();

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
     * Sets the value of account number
     */
    void setAccountNo(String value);

    /**
     * Gets the value of account number
     */
    String getAccountNo();

    /**
     * Sets the value of business id
     */
    void setBusinessId(int value);

    /**
     * Gets the value of business id
     */
    int getBusinessId();

    /**
     * Sets the value of business name
     */
    void setBusinessName(String value);

    /**
     * Gets the value of business name
     */
    String getBusinessName();

    /**
     * Sets the value of invoice number
     */
    void setInvoiceNo(String value);

    /**
     * Gets the value of invoice number
     */
    String getInvoiceNo();
}
