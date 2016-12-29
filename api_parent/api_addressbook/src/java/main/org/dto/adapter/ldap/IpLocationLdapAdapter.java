package org.dto.adapter.ldap;

import org.dao.mapping.orm.ldap.LdapIp;

import org.dto.IpLocationDto;

import com.api.foundation.TransactionDtoImpl;

/**
 * Adapts a JNDI/LDAP <i>LdapIp</i> object to an <i>IpLocationDto</i>.
 * 
 * @author Roy Terrell
 * 
 */
class IpLocationLdapAdapter extends TransactionDtoImpl implements IpLocationDto {

    private LdapIp ip;

    /**
     * Create a IpLocationLdapAdapter using an instance of <i>LdapIp</i>.
     * 
     * @param ipLoc
     *            an instance of {@link LdapIp} or null for the purpose of
     *            creating a new LdapIp object
     */
    protected IpLocationLdapAdapter(LdapIp ipLoc) {
        super();
        if (ipLoc == null) {
            ipLoc = new LdapIp();
        }
        this.ip = ipLoc;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#setLocId(int)
     */
    @Override
    public void setLocId(int value) {
        this.ip.setIpLocId(String.valueOf(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#getLocId()
     */
    @Override
    public int getLocId() {
        int value = 0;
        if (this.ip.getIpLocId() != null) {
            try {
                value = Integer.parseInt(this.ip.getIpLocId());
            } catch (NumberFormatException e) {
                throw new RuntimeException("ipLocId value is not numeric: "
                        + this.ip.getIpLocId());
            }
        }
        return value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#setIpRangeId(int)
     */
    @Override
    public void setIpRangeId(int value) {
        this.ip.setIpRangeId(String.valueOf(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#getIpRangeId()
     */
    @Override
    public int getIpRangeId() {
        int value = 0;
        if (this.ip.getIpRangeId() != null) {
            try {
                value = Integer.parseInt(this.ip.getIpRangeId());
            } catch (NumberFormatException e) {
                throw new RuntimeException("ipRangeId value is not numeric: "
                        + this.ip.getIpRangeId());
            }
        }
        return value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#setCountryCode(java.lang.String)
     */
    @Override
    public void setAreaCode(String value) {
        this.ip.setIpAreaCode(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#getCountryCode()
     */
    @Override
    public String getAreaCode() {
        return this.ip.getIpAreaCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#setCountryName(java.lang.String)
     */
    @Override
    public void setCountry(String value) {
        this.ip.setIpCountry(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#getCountryName()
     */
    @Override
    public String getCountry() {
        return this.ip.getIpCountry();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#setRegion(java.lang.String)
     */
    @Override
    public void setRegion(String value) {
        this.ip.setIpRegion(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#getRegion()
     */
    @Override
    public String getRegion() {
        return this.ip.getIpRegion();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#setCity(java.lang.String)
     */
    @Override
    public void setCity(String value) {
        this.ip.setIpCity(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#getCity()
     */
    @Override
    public String getCity() {
        return this.ip.getIpCity();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#setLatitude(double)
     */
    @Override
    public void setLatitude(double value) {
        this.ip.setIpLatitude(String.valueOf(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#getLatitude()
     */
    @Override
    public double getLatitude() {
        double value = 0;
        if (this.ip.getIpLatitude() != null) {
            try {
                value = Double.parseDouble(this.ip.getIpLatitude());
            } catch (NumberFormatException e) {
                throw new RuntimeException("Lattitude value is not numeric: "
                        + this.ip.getIpLatitude());
            }
        }
        return value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#setLongitude(double)
     */
    @Override
    public void setLongitude(double value) {
        this.ip.setIpLongitude(String.valueOf(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#getLongitude()
     */
    @Override
    public double getLongitude() {
        double value = 0;
        if (this.ip.getIpLongitude() != null) {
            try {
                value = Double.parseDouble(this.ip.getIpLongitude());
            } catch (NumberFormatException e) {
                throw new RuntimeException("Longitude value is not numeric: "
                        + this.ip.getIpLongitude());
            }
        }
        return value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#setZipcode(java.lang.String)
     */
    @Override
    public void setPostalCode(String value) {
        this.ip.setIpZip(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#getZipcode()
     */
    @Override
    public String getPostalCode() {
        return this.ip.getIpZip();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#setTimezone(java.lang.String)
     */
    @Override
    public void setMetroCode(String value) {
        this.ip.setIpMetroCode(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#getTimezone()
     */
    @Override
    public String getMetroCode() {
        return this.ip.getIpMetroCode();
    }
}
