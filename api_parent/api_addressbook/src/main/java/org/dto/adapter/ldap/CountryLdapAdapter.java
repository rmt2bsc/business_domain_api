package org.dto.adapter.ldap;

import org.dao.mapping.orm.ldap.LdapCountry;
import org.dto.CountryDto;

import com.RMT2Constants;
import com.RMT2RuntimeException;

/**
 * Adapts a JNDI/LDAP <i>LdapCountry</i> object to an <i>CountryDto</i>.
 * 
 * @author Roy Terrell
 * 
 */
class CountryLdapAdapter extends AbstractAddressBookLdapAdapter implements
        CountryDto {

    private LdapCountry c;

    /**
     * Create a CountryLdapAdapter using an instance of <i>LdapCountry</i>.
     * 
     * @param country
     *            an instance of {@link LdapCountry} or null for the purpose of
     *            creating a new LdapCountry object
     */
    protected CountryLdapAdapter(LdapCountry country) {
        if (country == null) {
            country = new LdapCountry();
        }
        this.c = country;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CountryDto#setCountryId(int)
     */
    @Override
    public void setCountryId(int value) {
        this.c.setCountryId(String.valueOf(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CountryDto#getCountryId()
     */
    @Override
    public int getCountryId() {
        if (this.c.getCountryId() == null) {
            return 0;
        }
        try {
            return Integer.parseInt(this.c.getCountryId());
        } catch (NumberFormatException e) {
            throw new RMT2RuntimeException("Country id must be numeric: "
                    + this.c.getCountryId());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CountryDto#setCountryCode(java.lang.String)
     */
    @Override
    public void setCountryCode(String value) {
        this.c.setCn(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CountryDto#getCountryCode()
     */
    @Override
    public String getCountryCode() {
        return this.c.getCn();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CountryDto#setCountryName(java.lang.String)
     */
    @Override
    public void setCountryName(String value) {
        this.c.setC(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CountryDto#getCountryName()
     */
    @Override
    public String getCountryName() {
        return this.c.getC();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CountryDto#setCountryPermcol(java.lang.String)
     */
    @Override
    public void setCountryPermcol(String value) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CountryDto#getCountryPermcol()
     */
    @Override
    public String getCountryPermcol() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

}
