package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a business contact.
 * <p>
 * An instance of this class represents one related record. If the desire arises
 * to manage a List of these instances, then redundant data could exist.
 * 
 * @author Roy Terrell
 */
public interface BusinessContactDto extends ContactDto {

    /**
     * Set the business short name.
     * 
     * @param value
     *            String
     */
    void setShortName(String value);

    /**
     * Get the business short name.
     * 
     * @return String
     */
    String getShortName();

    /**
     * Set the business entity type id.
     * 
     * @param value
     *            int
     */
    void setEntityTypeId(int value);

    /**
     * Get the business entity type id.
     * 
     * @return int
     */
    int getEntityTypeId();

    /**
     * Set the business service type id.
     * 
     * @param value
     *            int
     */
    void setServTypeId(int value);

    /**
     * Get the business service type id.
     * 
     * @return int
     */
    int getServTypeId();

    /**
     * Set the first name of the business contact.
     * 
     * @param value
     *            String.
     */
    void setContactFirstname(String value);

    /**
     * Get the first name of the business contact.
     * 
     * @return String
     */
    String getContactFirstname();

    /**
     * Set the last name of the business contact.
     * 
     * @param value
     *            String
     */
    void setContactLastname(String value);

    /**
     * Get the last name of the business contact.
     * 
     * @return String
     */
    String getContactLastname();

    /**
     * Set the phone number of the business contact.
     * 
     * @param value
     *            String
     */
    void setContactPhone(String value);

    /**
     * Get the phone number of the business contact.
     * 
     * @return String
     * 
     */
    String getContactPhone();

    /**
     * Set the phone extention of the business contact.
     * 
     * @param value
     *            String
     */
    void setContactExt(String value);

    /**
     * Get the phone extention of the business contact.
     * 
     * @return String
     */
    String getContactExt();

    /**
     * Set the tax id.
     * 
     * @param value
     *            String
     */
    void setTaxId(String value);

    /**
     * Get the tax id.
     * 
     * @return String
     */
    String getTaxId();

    /**
     * Set the web site.
     * 
     * @param value
     *            String
     */
    void setWebsite(String value);

    /**
     * Get the web site
     * 
     * @return String
     */
    String getWebsite();

    /**
     * Set the category id.
     * 
     * @param value
     *            int
     */
    void setCategoryId(int value);

    /**
     * Get the category id
     * 
     * @return int
     */
    int getCategoryId();

}