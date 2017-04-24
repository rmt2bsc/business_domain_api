package org.dao.mapping.orm.rmt2;

import java.util.Date;
import java.io.*;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;

/**
 * Peer object that maps to the ip_location database table/view.
 * 
 * @author auto generated.
 */
public class IpLocation extends OrmBean {

    // Property name constants that belong to respective DataSource,
    // IpLocationView

    /**
     * The property name constant equivalent to property, LocId, of respective
     * DataSource view.
     */
    public static final String PROP_LOCID = "LocId";
    /**
     * The property name constant equivalent to property, Country, of respective
     * DataSource view.
     */
    public static final String PROP_COUNTRY = "Country";
    /**
     * The property name constant equivalent to property, Region, of respective
     * DataSource view.
     */
    public static final String PROP_REGION = "Region";
    /**
     * The property name constant equivalent to property, City, of respective
     * DataSource view.
     */
    public static final String PROP_CITY = "City";
    /**
     * The property name constant equivalent to property, PostalCode, of
     * respective DataSource view.
     */
    public static final String PROP_POSTALCODE = "PostalCode";
    /**
     * The property name constant equivalent to property, Latitude, of
     * respective DataSource view.
     */
    public static final String PROP_LATITUDE = "Latitude";
    /**
     * The property name constant equivalent to property, Longitude, of
     * respective DataSource view.
     */
    public static final String PROP_LONGITUDE = "Longitude";
    /**
     * The property name constant equivalent to property, MetroCode, of
     * respective DataSource view.
     */
    public static final String PROP_METROCODE = "MetroCode";
    /**
     * The property name constant equivalent to property, AreaCode, of
     * respective DataSource view.
     */
    public static final String PROP_AREACODE = "AreaCode";

    /**
     * The javabean property equivalent of database column ip_location.loc_id
     */
    private int locId;
    /**
     * The javabean property equivalent of database column ip_location.country
     */
    private String country;
    /**
     * The javabean property equivalent of database column ip_location.region
     */
    private String region;
    /** The javabean property equivalent of database column ip_location.city */
    private String city;
    /**
     * The javabean property equivalent of database column
     * ip_location.postal_code
     */
    private String postalCode;
    /**
     * The javabean property equivalent of database column ip_location.latitude
     */
    private double latitude;
    /**
     * The javabean property equivalent of database column ip_location.longitude
     */
    private double longitude;
    /**
     * The javabean property equivalent of database column
     * ip_location.metro_code
     */
    private String metroCode;
    /**
     * The javabean property equivalent of database column ip_location.area_code
     */
    private String areaCode;

    // Getter/Setter Methods

    /**
     * Default constructor.
     */
    public IpLocation() throws SystemException {
        super();
    }

    /**
     * Sets the value of member variable locId
     */
    public void setLocId(int value) {
        this.locId = value;
    }

    /**
     * Gets the value of member variable locId
     */
    public int getLocId() {
        return this.locId;
    }

    /**
     * Sets the value of member variable country
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of member variable country
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * Sets the value of member variable region
     */
    public void setRegion(String value) {
        this.region = value;
    }

    /**
     * Gets the value of member variable region
     */
    public String getRegion() {
        return this.region;
    }

    /**
     * Sets the value of member variable city
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of member variable city
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Sets the value of member variable postalCode
     */
    public void setPostalCode(String value) {
        this.postalCode = value;
    }

    /**
     * Gets the value of member variable postalCode
     */
    public String getPostalCode() {
        return this.postalCode;
    }

    /**
     * Sets the value of member variable latitude
     */
    public void setLatitude(double value) {
        this.latitude = value;
    }

    /**
     * Gets the value of member variable latitude
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * Sets the value of member variable longitude
     */
    public void setLongitude(double value) {
        this.longitude = value;
    }

    /**
     * Gets the value of member variable longitude
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * Sets the value of member variable metroCode
     */
    public void setMetroCode(String value) {
        this.metroCode = value;
    }

    /**
     * Gets the value of member variable metroCode
     */
    public String getMetroCode() {
        return this.metroCode;
    }

    /**
     * Sets the value of member variable areaCode
     */
    public void setAreaCode(String value) {
        this.areaCode = value;
    }

    /**
     * Gets the value of member variable areaCode
     */
    public String getAreaCode() {
        return this.areaCode;
    }

    /**
     * Stubbed initialization method designed to implemented by developer.
     */
    public void initBean() throws SystemException {
    }
}