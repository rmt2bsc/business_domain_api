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
     * Set the business entity type group id
     * 
     * @param value
     *            int.
     */
    void setEntityTypeGrpId(int value);

    /**
     * Get the business entity type group id.
     * 
     * @return int
     */
    int getEntityTypeGrpId();

    /**
     * Set the business entity type short name
     * 
     * @param value
     *            String.
     */
    void setEntityTypeShortdesc(String value);

    /**
     * Get the business entity type short name.
     * 
     * @return String
     */
    String getEntityTypeShortdesc();

    /**
     * Set the business entity type long name
     * 
     * @param value
     *            String.
     */
    void setEntityTypeLongdesc(String value);

    /**
     * Get the business entity type long name.
     * 
     * @return String
     */
    String getEntityTypeLongtdesc();

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
     * Set the business service type group id
     * 
     * @param value
     *            int.
     */
    void setServTypeGrpId(int value);

    /**
     * Get the business service type group id.
     * 
     * @return int
     */
    int getServTypeGrpId();

    /**
     * Set the business service type short name
     * 
     * @param value
     *            String.
     */
    void setServTypeShortdesc(String value);

    /**
     * Get the business service type short name.
     * 
     * @return String
     */
    String getServTypeShortdesc();

    /**
     * Set the business service type long name
     * 
     * @param value
     *            String.
     */
    void setServTypeLongdesc(String value);

    /**
     * Get the business service type long name.
     * 
     * @return String
     */
    String getServTypeLongtdesc();

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

}