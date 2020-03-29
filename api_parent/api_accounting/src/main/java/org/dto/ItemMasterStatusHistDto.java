package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents an item master status
 * history entity.
 * 
 * @author rterrell
 * 
 */
public interface ItemMasterStatusHistDto extends CommonAccountingDto {

    /**
     * Sets the value of member variable itemId
     */
    void setItemId(int value);

    /**
     * Gets the value of member variable itemId
     */
    int getItemId();

    /**
     * Sets the value of member variable itemStatusId
     */
    void setItemStatusId(int value);

    /**
     * Gets the value of member variable itemStatusId
     */
    int getItemStatusId();

    /**
     * Sets the item Status description
     */
    void setItemStatusName(String value);

    /**
     * Gets the item Status description
     */
    String getItemStatusName();

    /**
     * Sets the value of member variable unitCost
     */
    void setUnitCost(double value);

    /**
     * Gets the value of member variable unitCost
     */
    double getUnitCost();

    /**
     * Sets the value of member variable markup
     */
    void setMarkup(double value);

    /**
     * Gets the value of member variable markup
     */
    double getMarkup();

    /**
     * Sets the value of member variable effectiveDate
     */
    void setEffectiveDate(java.util.Date value);

    /**
     * Gets the value of member variable effectiveDate
     */
    java.util.Date getEffectiveDate();

    /**
     * Sets the value of member variable endDate
     */
    void setEndDate(java.util.Date value);

    /**
     * Gets the value of member variable endDate
     */
    java.util.Date getEndDate();

    /**
     * Sets the value of member variable reason
     */
    void setReason(String value);

    /**
     * Gets the value of member variable reason
     */
    String getReason();
}
