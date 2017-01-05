package org.dto.adapter.orm;

import org.dao.mapping.orm.rmt2.IpLocation;
import org.dto.IpLocationDto;

import com.RMT2Constants;
import com.api.foundation.TransactionDtoImpl;

/**
 * Adapts a RMT2 ORM <i>IpLocation</i> object to an <i>IpLocationDto</i>.
 * 
 * @author rterrell
 * 
 */
class IpLocationRmt2OrmAdapter extends TransactionDtoImpl implements
        IpLocationDto {

    private IpLocation ip;

    /**
     * Create a IpLocationRmt2OrmAdapter using an instance of <i>IpLocation</i>.
     * 
     * @param ipLoc
     *            an instance of {@link IpLocation} or null for the purpose of
     *            creating a new IpLocation object
     */
    protected IpLocationRmt2OrmAdapter(IpLocation ipLoc) {
        super();
        if (ipLoc == null) {
            ipLoc = new IpLocation();
        }
        this.ip = ipLoc;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#setIpId(int)
     */
    @Override
    public void setLocId(int value) {
        this.ip.setLocId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#getIpId()
     */
    @Override
    public int getLocId() {
        return this.ip.getLocId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#setCountryCode(java.lang.String)
     */
    @Override
    public void setAreaCode(String value) {
        this.ip.setAreaCode(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#getCountryCode()
     */
    @Override
    public String getAreaCode() {
        return this.ip.getAreaCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#setCountryName(java.lang.String)
     */
    @Override
    public void setCountry(String value) {
        this.ip.setCountry(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#getCountryName()
     */
    @Override
    public String getCountry() {
        return this.ip.getCountry();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#setRegion(java.lang.String)
     */
    @Override
    public void setRegion(String value) {
        this.ip.setRegion(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#getRegion()
     */
    @Override
    public String getRegion() {
        return this.ip.getRegion();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#setCity(java.lang.String)
     */
    @Override
    public void setCity(String value) {
        this.ip.setCity(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#getCity()
     */
    @Override
    public String getCity() {
        return this.ip.getCity();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#setLatitude(double)
     */
    @Override
    public void setLatitude(double value) {
        this.ip.setLatitude(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#getLatitude()
     */
    @Override
    public double getLatitude() {
        return this.ip.getLatitude();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#setLongitude(double)
     */
    @Override
    public void setLongitude(double value) {
        this.ip.setLongitude(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#getLongitude()
     */
    @Override
    public double getLongitude() {
        return this.ip.getLongitude();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#setZipcode(java.lang.String)
     */
    @Override
    public void setPostalCode(String value) {
        this.ip.setPostalCode(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#getZipcode()
     */
    @Override
    public String getPostalCode() {
        return this.ip.getPostalCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#setTimezone(java.lang.String)
     */
    @Override
    public void setMetroCode(String value) {
        this.ip.setMetroCode(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.IpLocationDto#getTimezone()
     */
    @Override
    public String getMetroCode() {
        return this.ip.getMetroCode();
    }

    /**
     * Not supported
     */
    @Override
    public void setIpRangeId(int value) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not supported
     */
    @Override
    public int getIpRangeId() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

}
