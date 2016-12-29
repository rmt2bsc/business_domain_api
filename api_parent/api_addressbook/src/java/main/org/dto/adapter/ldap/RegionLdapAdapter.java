package org.dto.adapter.ldap;

import org.dao.mapping.orm.ldap.LdapRegion;

import org.dto.RegionDto;

import com.RMT2Constants;

import com.RMT2RuntimeException;

/**
 * Adapts a JNDI/LDAP <i>LdapRegion</i> object to an <i>RegionDto</i>.
 * 
 * @author Roy Terrell
 * 
 */
class RegionLdapAdapter extends AbstractAddressBookLdapAdapter implements
        RegionDto {

    private LdapRegion r;

    /**
     * Create a RegionLdapAdapter using an instance of <i>LdapRegion</i>.
     * 
     * @param region
     *            an instance of {@link LdapRegion} or null for the purpose of
     *            creating a new LdapRegion object
     */
    protected RegionLdapAdapter(LdapRegion region) {
        if (region == null) {
            region = new LdapRegion();
        }
        this.r = region;
    }

    @Override
    public void setStateId(int value) {
        this.r.setRegionId(String.valueOf(value));
    }

    @Override
    public int getStateId() {
        if (this.r.getRegionId() == null) {
            return 0;
        }
        try {
            return Integer.parseInt(this.r.getRegionId());
        } catch (NumberFormatException e) {
            throw new RMT2RuntimeException(
                    "Region/State/Province Id in not numeric: "
                            + this.r.getRegionId());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CountryDto#setCountryCode(java.lang.String)
     */
    @Override
    public void setStateCode(String value) {
        this.r.setCn(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CountryDto#getCountryCode()
     */
    @Override
    public String getStateCode() {
        return this.r.getCn();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CountryDto#setCountryName(java.lang.String)
     */
    @Override
    public void setStateName(String value) {
        this.r.setC(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CountryDto#getCountryName()
     */
    @Override
    public String getStateName() {
        return this.r.getC();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CountryDto#setCountryPermcol(java.lang.String)
     */
    @Override
    public void setStatePermcol(String value) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CountryDto#getCountryPermcol()
     */
    @Override
    public String getStatePermcol() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Always return zero
     */
    @Override
    public int getCountryId() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CountryDto#setCountryId(int)
     */
    @Override
    public void setCountryId(int value) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.RegionDto#setCountryName(java.lang.String)
     */
    @Override
    public void setCountryName(String value) {
        this.r.setCountryName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.RegionDto#getCountryName()
     */
    @Override
    public String getCountryName() {
        return this.r.getCountryName();
    }

}
