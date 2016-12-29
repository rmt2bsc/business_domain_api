package org.dao.mapping.orm.ldap;

/**
 * A bean for capturing and managing country data from an LDAP data source.
 * 
 * @author Roy Terrell
 * 
 */
public class LdapCountry {

    private String c;

    private String cn;

    private String countryId;

    /**
     * Default contructor
     */
    public LdapCountry() {
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
     * @return the countryId
     */
    public String getCountryId() {
        return countryId;
    }

    /**
     * @param countryId
     *            the countryId to set
     */
    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

}
