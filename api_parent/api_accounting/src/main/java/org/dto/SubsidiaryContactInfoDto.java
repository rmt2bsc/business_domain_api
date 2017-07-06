package org.dto;

import java.util.List;

/**
 * Data Transfer Object (DTO) contract that represents a common subsidiary
 * account contact information.
 * 
 * @author Roy Terrell
 * 
 */
public interface SubsidiaryContactInfoDto extends CommonAccountingDto {

    /**
     * Set the subsidiary's contact id
     */
    void setContactIdList(List<Integer> value);

    /**
     * Get the subsidiary's contact id
     */
    List<Integer> getContactIdList();

    /**
     * Set the subsidiary's contact id
     */
    void setContactId(int value);

    /**
     * Get the subsidiary's contact id
     */
    int getContactId();

    /**
     * Set the subsidiary name
     */
    void setContactName(String value);

    /**
     * Get the subsidiary name
     */
    String getContactName();

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

    /**
     * Set the home phone number.
     * 
     * @param value
     *            String
     */
    void setPhoneHome(String value);

    /**
     * Get the home phone number.
     * 
     * @return String
     */
    String getPhoneHome();

    /**
     * Set the work phone number.
     * 
     * @param value
     *            String
     */
    void setPhoneWork(String value);

    /**
     * Get the work phone number.
     * 
     * @return String
     */
    String getPhoneWork();

    /**
     * Set the work phone extention number.
     * 
     * @param value
     *            String
     */
    void setPhoneExt(String value);

    /**
     * Get the work phone extention number.
     * 
     * @return String
     */
    String getPhoneExt();

    /**
     * Set the company phone number.
     * 
     * @param value
     *            String
     */
    void setPhoneCompany(String value);

    /**
     * Get the company phone number.
     * 
     * @return String
     */
    String getPhoneCompany();

    /**
     * Set the cellular phone number.
     * 
     * @param value
     *            String
     */
    void setPhoneCell(String value);

    /**
     * Get the cellular number.
     * 
     * @return String
     */
    String getPhoneCell();

    /**
     * Set the fax number.
     * 
     * @param value
     *            String
     */
    void setPhoneFax(String value);

    /**
     * Get the fax number.
     * 
     * @return String
     */
    String getPhoneFax();

    /**
     * Set the pager number.
     * 
     * @param value
     *            String
     */
    void setPhonePager(String value);

    /**
     * Get the pager number.
     * 
     * @return String
     */
    String getPhonePager();

}
