package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents the transaction credit
 * charge entity
 * 
 * @author Roy Terrell
 */
public interface XactCreditChargeDto extends XactDto {

    /**
     * Sets the value of member variable creditorId
     */
    void setCreditorId(int value);

    /**
     * Gets the value of member variable creditorId
     */
    int getCreditorId();

    /**
     * Sets the value of member variable businessId
     */
    void setBusinessId(int value);

    /**
     * Gets the value of member variable businessId
     */
    int getBusinessId();

    /**
     * Set the creditor's name
     */
    void setCreditorName(String value);

    /**
     * Get the creditor's name
     */
    String getCreditorName();

    /**
     * Sets the value of member variable accountNumber
     */
    void setAccountNumber(String value);

    /**
     * Gets the value of member variable accountNumber
     */
    String getAccountNumber();

    /**
     * Set the creditor's external account number
     */
    void setExtAccountNumber(String value);

    /**
     * Get the creditor's external account number
     */
    String getExtAccountNumber();

    /**
     * Sets the value of member variable active
     */
    void setActive(int value);

    /**
     * Gets the value of member variable active
     */
    int getActive();

    /**
     * Sets the value of account balance
     */
    void setBalance(double value);

    /**
     * Gets the value of account balance
     */
    double getBalance();

    /**
     * Sets the value of member variable taxId
     */
    void setTaxId(String value);

    /**
     * Gets the value of member variable taxId
     */
    String getTaxId();

    /**
     * Sets the value of member variable phone
     */
    void setPhone(String value);

    /**
     * Gets the value of member variable phone
     */
    String getPhone();
    // /**
    // * Sets the value of transaction id
    // */
    // void setXactId(int value);
    //
    // /**
    // * Gets the value of transaction id
    // */
    // int getXactId();
    //
    // /**
    // * Sets the value of member variable xactSubtypeId
    // */
    // void setXactSubtypeId(int value);
    //
    // /**
    // * Gets the value of member variable xactSubtypeId
    // */
    // int getXactSubtypeId();
    //
    // /**
    // * Sets the value of member variable xactDate
    // */
    // void setXactDate(java.util.Date value);
    //
    // /**
    // * Gets the value of member variable xactDate
    // */
    // java.util.Date getXactDate();
    //
    // /**
    // * Sets the value of member variable xactAmount
    // */
    // void setXactAmount(double value);
    //
    // /**
    // * Gets the value of member variable xactAmount
    // */
    // double getXactAmount();
    //
    // /**
    // * Sets the value of member variable tenderId
    // */
    // void setXactTenderId(int value);
    //
    // /**
    // * Gets the value of member variable tenderId
    // */
    // int getXactTenderId();
    //
    // /**
    // * Set the tender name
    // */
    // void setXactTenderName(String value);
    //
    // /**
    // * Gets the tender name
    // */
    // String getXactTenderName();
    //
    // /**
    // * Sets the value of member variable negInstrNo
    // */
    // void setXactNegInstrNo(String value);
    //
    // /**
    // * Gets the value of member variable negInstrNo
    // */
    // String getXactNegInstrNo();
    //
    // /**
    // * Sets the value of member variable bankTransInd
    // */
    // void setXactBankTransInd(String value);
    //
    // /**
    // * Gets the value of member variable bankTransInd
    // */
    // String getXactBankTransInd();
    //
    // /**
    // * Sets the value of member variable confirmNo
    // */
    // void setXactConfirmNo(String value);
    //
    // /**
    // * Gets the value of member variable confirmNo
    // */
    // String getXactConfirmNo();
    //
    // /**
    // * Sets the value of member variable entityRefNo
    // */
    // void setXactEntityRefNo(String value);
    //
    // /**
    // * Gets the value of member variable entityRefNo
    // */
    // String getXactEntityRefNo();
    //
    // /**
    // * Sets the value of member variable reason
    // */
    // void setXactReason(String value);
    //
    // /**
    // * Gets the value of member variable reason
    // */
    // String getXactReason();
    //
    // /**
    // * Sets the value of member variable documentId
    // */
    // void setDocumentId(int value);
    //
    // /**
    // * Gets the value of member variable documentId
    // */
    // int getDocumentId();
    //
    // /**
    // * Set transaction items
    // *
    // * @param items
    // * a List of {@link VwXactItemList} objects
    // */
    // void setXactItems(List<VwXactItemList> items);
    //
    // /**
    // * Get transaction items
    // *
    // * @return List of {@link XactTypeItemActivityDto}
    // */
    // List<XactTypeItemActivityDto> getXactItems();

}