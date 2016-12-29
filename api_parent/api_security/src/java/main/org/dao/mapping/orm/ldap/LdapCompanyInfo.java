package org.dao.mapping.orm.ldap;

import java.util.List;

import com.api.ldap.beans.LdapCommonEntity;

/**
 * ORM bean for mapping company information coming from a LDAP server.
 * 
 * @author Roy Terrell
 * 
 */
public class LdapCompanyInfo extends LdapCommonEntity {

    private String bn;

    private List<String> o;

    private String taxId;

    private List<String> businessCategory;

    private String city;

    private String companyPhone;

    private String country;

    private String email;

    private String onm;

    private String phoneFax;

    private String state;

    private String street;

    private String website;

    private String zip;

    /**
     * Creates a LdapCompanyInfo object withou intializing any of its
     * properties.
     */
    public LdapCompanyInfo() {
        super();
    }

    /**
     * Get the business name
     * 
     * @return String
     */
    public String getBn() {
        return bn;
    }

    /**
     * Set the business name
     * 
     * @param bn
     *            Strng
     */
    public void setBn(String bn) {
        this.bn = bn;
    }

    /**
     * Get the organization name
     * 
     * @return List of String
     */
    public List<String> getO() {
        return o;
    }

    /**
     * Get the organization name
     * 
     * @param o
     *            List of String
     */
    public void setO(List<String> o) {
        this.o = o;
    }

    /**
     * Get tax id
     * 
     * @return String
     */
    public String getTaxId() {
        return taxId;
    }

    /**
     * Set tax id
     * 
     * @param taxId
     *            String
     */
    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    /**
     * Get the busiess category
     * 
     * @return List of String
     */
    public List<String> getBusinessCategory() {
        return businessCategory;
    }

    /**
     * Set the business category
     * 
     * @param businessCategory
     *            List of String
     */
    public void setBusinessCategory(List<String> businessCategory) {
        this.businessCategory = businessCategory;
    }

    /**
     * Get city
     * 
     * @return String
     */
    public String getCity() {
        return city;
    }

    /**
     * Set the city
     * 
     * @param city
     *            String
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Get company phone number
     * 
     * @return String
     */
    public String getCompanyPhone() {
        return companyPhone;
    }

    /**
     * Set the company phone number
     * 
     * @param companyPhone
     *            Srting
     */
    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    /**
     * Get the country
     * 
     * @return String
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set the country
     * 
     * @param country
     *            String
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Get the email address
     * 
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email address
     * 
     * @param email
     *            String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the name of the owner
     * 
     * @return String
     */
    public String getOnm() {
        return onm;
    }

    /**
     * Set the name of the owner
     * 
     * @param onm
     *            String - the owner name
     */
    public void setOnm(String onm) {
        this.onm = onm;
    }

    /**
     * Get fax number
     * 
     * @return String
     */
    public String getPhoneFax() {
        return phoneFax;
    }

    /**
     * Set the fax number
     * 
     * @param phoneFax
     *            String
     */
    public void setPhoneFax(String phoneFax) {
        this.phoneFax = phoneFax;
    }

    /**
     * Get the state/province name
     * 
     * @return String
     */
    public String getState() {
        return state;
    }

    /**
     * Set the state/province name
     * 
     * @param state
     *            String
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Get the street address
     * 
     * @return String
     */
    public String getStreet() {
        return street;
    }

    /**
     * Set the street address
     * 
     * @param street
     *            String
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Get the web site address
     * 
     * @return String
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Set the web site address
     * 
     * @param website
     *            String
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * Get the zip code
     * 
     * @return String
     */
    public String getZip() {
        return zip;
    }

    /**
     * Set the zip code
     * 
     * @param zip
     *            String
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

}
