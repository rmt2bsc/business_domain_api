package org.dto;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that represents a IP Location details.
 * <p>
 * An instance of this class represents one related record. If the desire arises
 * to manage a List of these instances, then redundant data could exist.
 * 
 * @author Roy Terrell
 */
public interface IpLocationDto extends TransactionDto {

    /**
     * Set the internal IP range id which is synonymous to location id.
     * 
     * @param value
     *            Integer
     */
    void setIpRangeId(Integer value);

    /**
     * Get the internal IP range id which is synonymous to location id.
     * 
     * @return int
     */
    Integer getIpRangeId();
    
    /**
     * Get the IP address in its standard form such as: xxx.xxx.xxx.xxx
     * 
     * @return
     */
    String getStandardIp();
    
    /**
     * Set the IP address in its standard form such as: xxx.xxx.xxx.xxx
     * 
     * @param ip
     */
    void setStandardIp(String ip);

    /**
     * Set the area code.
     * 
     * @param value
     *            String
     */
    void setAreaCode(String value);

    /**
     * Get the area code.
     * 
     * @return String
     */
    String getAreaCode();

    /**
     * Set the country name.
     * 
     * @param value
     *            String
     */
    void setCountry(String value);

    /**
     * Get the country name.
     * 
     * @return String
     */
    String getCountry();

    /**
     * Set the country region better known as state/province.
     * 
     * @param value
     *            String
     */
    void setRegion(String value);

    /**
     * Get the country region better known as state/province.
     * 
     * @return String
     */
    String getRegion();

    /**
     * Set the city.
     * 
     * @param value
     *            String
     */
    void setCity(String value);

    /**
     * Get the city.
     * 
     * @return String
     */
    String getCity();

    /**
     * Set the latitude.
     * 
     * @param value
     *            double
     */
    void setLatitude(double value);

    /**
     * Get the Latitued.
     * 
     * @return double
     */
    double getLatitude();

    /**
     * Set the longitude.
     * 
     * @param value
     *            double
     */
    void setLongitude(double value);

    /**
     * Get the longitude.
     * 
     * @return double
     */
    double getLongitude();

    /**
     * Set the postal code better known as zip code.
     * 
     * @param value
     *            String
     */
    void setPostalCode(String value);

    /**
     * Get the postal code better known as zip code.
     * 
     * @return String
     */
    String getPostalCode();

    /**
     * Set the metropolitan code.
     * 
     * @param value
     *            String
     */
    void setMetroCode(String value);

    /**
     * Get the metropolitan code.
     * 
     * @return String
     */
    String getMetroCode();
    
    /**
     * Set the From IP.
     * 
     * @param value
     *            double
     */
    void setIpFrom(double value);

    /**
     * Get the From IP.
     * 
     * @return String
     */
    double getIpFrom();
    
    /**
     * Set the To IP.
     * 
     * @param value
     *            double
     */
    void setIpTo(double value);

    /**
     * Get the To IP.
     * 
     * @return double
     */
    double getIpTo();

}