package org.dto;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that represents an address.
 * <p>
 * An instance of this class represents one related record. If the desire arises
 * to manage a List of these instances, then redundant data could exist.
 * 
 * @author Roy Terrell
 */
public interface AddressDto extends TransactionDto {

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