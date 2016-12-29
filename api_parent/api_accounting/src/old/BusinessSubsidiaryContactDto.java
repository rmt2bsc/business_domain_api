package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents contact information for a
 * subsidiary.
 * 
 * @author Roy Terrell.
 */
public interface BusinessSubsidiaryContactDto extends CommonAccountingDto {

    /**
     * Sets the value of the contact id.
     * <p>
     * This can be either the unique id of a personal or business contact.
     * 
     * @param value
     *            the contact id which is either personal or business id.
     */
    void setContactId(int value);

    /**
     * Returns the value of the contact id.
     * <p>
     * This can be either the unique id of a personal or business contact.
     * 
     * @return the contact id which is either personal or business id.
     * 
     */
    int getContactId();

    /**
     * Sets the Contact name.
     * 
     * @param value
     *            The contact's name. For personal contacts, the name will be
     *            first name and last name separated by a space. For business
     *            contacts, the business' long name is used.
     */
    void setContactName(String value);

    /**
     * Returns the Contact name.
     * 
     * @return The contact's name. For personal contacts, the name will be first
     *         name and last name separated by a space. For business contacts,
     *         the business' long name is used.
     */
    String getContactName();

    /**
     * Sets the contact's email.
     * 
     * @param value
     *            email address.
     */
    void setContactEmail(String value);

    /**
     * Returns the contact's email address.
     * 
     * @return email address.
     */
    String getContactEmail();

    /**
     * Sets the contact's type.
     * 
     * @param value
     *            equals either <i>per</i> or <i>bus</i> for Personal or
     *            Business, respectively.
     */
    void setContactType(String value);

    /**
     * Returns the contact's type.
     * 
     * @return equals either <i>per</i> or <i>bus</i> for Personal or Business,
     *         respectively.
     */
    String getContactType();

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
     * Set the short name of the business contact.
     * 
     * @param value
     *            String
     */
    void setContactShortname(String value);

    /**
     * Get the short name of the business contact.
     * 
     * @return String
     */
    String getContactShortname();

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

    /**
     * Set the internal id of the address.
     * 
     * @param value
     *            int
     */
    void setAddrId(int value);

    /**
     * Get the internal id of the address.
     * 
     * @return int
     */
    int getAddrId();

    /**
     * Set address line 1.
     * 
     * @param value
     *            String
     */
    void setAddr1(String value);

    /**
     * Get address line 1.
     * 
     * @return String
     */
    String getAddr1();

    /**
     * Set address line 2.
     * 
     * @param value
     *            String
     */
    void setAddr2(String value);

    /**
     * Get address line 2.
     * 
     * @return String
     */
    String getAddr2();

    /**
     * Set address line 3.
     * 
     * @param value
     *            String
     */
    void setAddr3(String value);

    /**
     * Get address line 3.
     * 
     * @return String
     */
    String getAddr3();

    /**
     * Set address line 4.
     * 
     * @param value
     *            String
     */
    void setAddr4(String value);

    /**
     * Get address line 4.
     * 
     * @return String
     */
    String getAddr4();

    /**
     * Set zip lookup.
     * 
     * @param value
     *            int
     */
    void setZip(int value);

    /**
     * Get zip lookup.
     * 
     * @return int
     */
    int getZip();

    /**
     * Set zip lookup extension.
     * 
     * @param value
     *            int
     */
    void setZipext(int value);

    /**
     * Get zip lookup extension.
     * 
     * @return int
     */
    int getZipext();

    /**
     * Set the address' city.
     * 
     * @param value
     *            the name of the city
     */
    void setCity(String value);

    /**
     * Get the city name.
     * 
     * @return String
     */
    String getCity();

    /**
     * Set the address' state
     * 
     * @param value
     *            String
     */
    void setState(String value);

    /**
     * Get the address' state
     * 
     * @return String
     */
    String getState();

}