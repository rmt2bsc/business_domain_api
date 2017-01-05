package org.dao.mapping.orm.ldap;

/**
 * A bean for capturing and managing IP address location data from an LDAP data
 * source.
 * 
 * @author Roy Terrell
 * 
 */
public class LdapIp {

    private String ipLocId;

    private String ipCountry;

    private String ipRegion;

    private String ipCity;

    private String ipZip;

    private String ipLatitude;

    private String ipLongitude;

    private String ipMetroCode;

    private String ipAreaCode;

    private String ipRangeId;

    private String ipFrom;

    private String ipTo;

    private int ipBlockCount;

    /**
     * Default constructor.
     */
    public LdapIp() {
        return;
    }

    /**
     * @return the ipLocId
     */
    public String getIpLocId() {
        return ipLocId;
    }

    /**
     * @param ipLocId
     *            the ipLocId to set
     */
    public void setIpLocId(String uid) {
        this.ipLocId = uid;
    }

    /**
     * @return the ipCountry
     */
    public String getIpCountry() {
        return ipCountry;
    }

    /**
     * @param ipCountry
     *            the ipCountry to set
     */
    public void setIpCountry(String countryCode) {
        this.ipCountry = countryCode;
    }

    /**
     * @return the ipRegion
     */
    public String getIpRegion() {
        return ipRegion;
    }

    /**
     * @param ipRegion
     *            the ipRegion to set
     */
    public void setIpRegion(String ipRegion) {
        this.ipRegion = ipRegion;
    }

    /**
     * @return the ipCity
     */
    public String getIpCity() {
        return ipCity;
    }

    /**
     * @param ipCity
     *            the ipCity to set
     */
    public void setIpCity(String city) {
        this.ipCity = city;
    }

    /**
     * @return the ipZip
     */
    public String getIpZip() {
        return ipZip;
    }

    /**
     * @param ipZip
     *            the ipZip to set
     */
    public void setIpZip(String zip) {
        this.ipZip = zip;
    }

    /**
     * @return the ipLatitude
     */
    public String getIpLatitude() {
        return ipLatitude;
    }

    /**
     * @param ipLatitude
     *            the ipLatitude to set
     */
    public void setIpLatitude(String latitude) {
        this.ipLatitude = latitude;
    }

    /**
     * @return the ipLongitude
     */
    public String getIpLongitude() {
        return ipLongitude;
    }

    /**
     * @param ipLongitude
     *            the ipLongitude to set
     */
    public void setIpLongitude(String longitude) {
        this.ipLongitude = longitude;
    }

    /**
     * @return the ipMetroCode
     */
    public String getIpMetroCode() {
        return ipMetroCode;
    }

    /**
     * @param ipMetroCode
     *            the ipMetroCode to set
     */
    public void setIpMetroCode(String metroCode) {
        this.ipMetroCode = metroCode;
    }

    /**
     * @return the ipAreaCode
     */
    public String getIpAreaCode() {
        return ipAreaCode;
    }

    /**
     * @param ipAreaCode
     *            the ipAreaCode to set
     */
    public void setIpAreaCode(String areaCode) {
        this.ipAreaCode = areaCode;
    }

    /**
     * @return the ipFrom
     */
    public String getIpFrom() {
        return ipFrom;
    }

    /**
     * @param ipFrom
     *            the ipFrom to set
     */
    public void setIpFrom(String ipFrom) {
        this.ipFrom = ipFrom;
    }

    /**
     * @return the ipTo
     */
    public String getIpTo() {
        return ipTo;
    }

    /**
     * @param ipTo
     *            the ipTo to set
     */
    public void setIpTo(String ipTo) {
        this.ipTo = ipTo;
    }

    /**
     * @return the ipBlockCount
     */
    public int getIpBlockCount() {
        return ipBlockCount;
    }

    /**
     * @param ipBlockCount
     *            the ipBlockCount to set
     */
    public void setIpBlockCount(int ipBlockCount) {
        this.ipBlockCount = ipBlockCount;
    }

    /**
     * @return the ipRangeId
     */
    public String getIpRangeId() {
        return ipRangeId;
    }

    /**
     * @param ipRangeId
     *            the ipRangeId to set
     */
    public void setIpRangeId(String ipRangeId) {
        this.ipRangeId = ipRangeId;
    }

}