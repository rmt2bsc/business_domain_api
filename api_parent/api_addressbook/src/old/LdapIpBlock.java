package org.dao.mapping.orm.ldap;

public class LdapIpBlock {

    private String countryCode;

    private String ipRegion;

    private String city;

    private String ipFrom;

    private String ipTo;

    /**
     * Default constructor.
     */
    public LdapIpBlock() {
	return;
    }

 
    /**
     * @return the countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @param countryCode the countryCode to set
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * @return the ipRegion
     */
    public String getIpRegion() {
        return ipRegion;
    }

    /**
     * @param ipRegion the ipRegion to set
     */
    public void setIpRegion(String ipRegion) {
        this.ipRegion = ipRegion;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

  
    /**
     * @return the ipFrom
     */
    public String getIpFrom() {
        return ipFrom;
    }

    /**
     * @param ipFrom the ipFrom to set
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
     * @param ipTo the ipTo to set
     */
    public void setIpTo(String ipTo) {
        this.ipTo = ipTo;
    }

}