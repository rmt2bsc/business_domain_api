package org.dao.mapping.orm.ldap;

/**
 * A bean for capturing and managing region/state/province data from an LDAP
 * data source.
 * 
 * @author Roy Terrell
 * 
 */
public class LdapRegion {

    private String c;

    private String cn;

    private String regionId;

    private String countryName;

    /**
     * Default contructor
     */
    public LdapRegion() {
        return;
    }

    /**
     * @return the c
     */
    public String getC() {
        return c;
    }

    /**
     * @param c
     *            the c to set
     */
    public void setC(String c) {
        this.c = c;
    }

    /**
     * @return the cn
     */
    public String getCn() {
        return cn;
    }

    /**
     * @param cn
     *            the cn to set
     */
    public void setCn(String cn) {
        this.cn = cn;
    }

    /**
     * @return the regionId
     */
    public String getRegionId() {
        return regionId;
    }

    /**
     * @param regionId
     *            the regionId to set
     */
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    /**
     * @return the countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @param countryName
     *            the countryName to set
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
