package org.dto.adapter.ldap;

import org.dao.mapping.orm.ldap.LdapZipcode;
import org.dto.ZipcodeDto;

import com.RMT2Constants;

/**
 * Adapts a JNDI/LDAP <i>Zip Code</i> object to an <i>ZipcodeDto</i> object.
 * 
 * @author Roy Terrell
 * 
 */
class ZipCodeLdapAdapter extends AbstractAddressBookLdapAdapter implements
        ZipcodeDto {

    private LdapZipcode z;

    /**
     * Create a ZipCodeLdapAdapter using an instance of <i>LdapZipcode</i>.
     * 
     * @param zip
     *            an instance of {@link LdapZipcode} or null for the purpose of
     *            creating a new Zip Code object
     */
    protected ZipCodeLdapAdapter(LdapZipcode zip) {
        if (zip == null) {
            zip = new LdapZipcode();
        }
        this.z = zip;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setId(int)
     */
    @Override
    public void setId(int value) {
        this.z.setZip(String.valueOf(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getId()
     */
    @Override
    public int getId() {
        int zip;
        try {
            zip = Integer.parseInt(this.z.getZip());
        } catch (NumberFormatException e) {
            zip = 0;
        }
        return zip;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setIdStr(java.lang.String)
     */
    @Override
    public void setIdStr(String value) {
        this.z.setZipId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getIdStr()
     */
    @Override
    public String getIdStr() {
        return this.z.getZipId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setZip(int)
     */
    @Override
    public void setZip(int value) {
        this.z.setZip(String.valueOf(value));

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getZip()
     */
    @Override
    public int getZip() {
        int zip;
        try {
            zip = Integer.parseInt(this.z.getZip());
        } catch (NumberFormatException e) {
            zip = 0;
        }
        return zip;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setCity(java.lang.String)
     */
    @Override
    public void setCity(String value) {
        this.z.setCity(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getCity()
     */
    @Override
    public String getCity() {
        return this.z.getCity();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setStateCode(java.lang.String)
     */
    @Override
    public void setStateCode(String value) {
        this.z.setState(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getStateCode()
     */
    @Override
    public String getStateCode() {
        return this.z.getState();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setStateName(java.lang.String)
     */
    @Override
    public void setStateName(String value) {
        this.z.setStateName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getStateName()
     */
    @Override
    public String getStateName() {
        return this.z.getStateName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setAreaCode(java.lang.String)
     */
    @Override
    public void setAreaCode(String value) {
        this.z.setAreaCode(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getAreaCode()
     */
    @Override
    public String getAreaCode() {
        return this.z.getAreaCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setCityAliasName(java.lang.String)
     */
    @Override
    public void setCityAliasName(String value) {
        this.z.setCityAliasName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getCityAliasName()
     */
    @Override
    public String getCityAliasName() {
        return this.z.getCityAliasName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setCountyName(java.lang.String)
     */
    @Override
    public void setCountyName(String value) {
        this.z.setCountyName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getCountyName()
     */
    @Override
    public String getCountyName() {
        return this.z.getCountyName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setCountyFips(java.lang.String)
     */
    @Override
    public void setCountyFips(String value) {
        this.z.setCountyFips(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getCountyFips()
     */
    @Override
    public String getCountyFips() {
        return this.z.getCountyFips();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setTimeZoneDescr(java.lang.String)
     */
    @Override
    public void setTimeZoneDescr(String value) {
        this.z.setTimeZoneId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getTimeZoneDescr()
     */
    @Override
    public String getTimeZoneDescr() {
        return this.z.getTimeZoneId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setDayLightSaving(java.lang.String)
     */
    @Override
    public void setDayLightSaving(String value) {
        this.z.setDayLightSaving(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getDayLightSaving()
     */
    @Override
    public String getDayLightSaving() {
        return this.z.getDayLightSaving();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setLatitude(double)
     */
    @Override
    public void setLatitude(double value) {
        this.z.setLatitude(String.valueOf(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getLatitude()
     */
    @Override
    public double getLatitude() {
        double val;
        try {
            val = Double.parseDouble(this.z.getLatitude());
        } catch (NumberFormatException e) {
            val = 0.0;
        }
        return val;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setLongitude(double)
     */
    @Override
    public void setLongitude(double value) {
        this.z.setLongitude(String.valueOf(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getLongitude()
     */
    @Override
    public double getLongitude() {
        double val;
        try {
            val = Double.parseDouble(this.z.getLongitude());
        } catch (NumberFormatException e) {
            val = 0.0;
        }
        return val;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setElevation(double)
     */
    @Override
    public void setElevation(double value) {
        this.z.setElevation(String.valueOf(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getElevation()
     */
    @Override
    public double getElevation() {
        double val;
        try {
            val = Double.parseDouble(this.z.getElevation());
        } catch (NumberFormatException e) {
            val = 0.0;
        }
        return val;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setMsa(double)
     */
    @Override
    public void setMsa(double value) {
        this.z.setMsa(String.valueOf(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getMsa()
     */
    @Override
    public double getMsa() {
        double val;
        try {
            val = Double.parseDouble(this.z.getMsa());
        } catch (NumberFormatException e) {
            val = 0.0;
        }
        return val;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setPmsa(double)
     */
    @Override
    public void setPmsa(double value) {
        this.z.setPmsa(String.valueOf(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getPmsa()
     */
    @Override
    public double getPmsa() {
        double val;
        try {
            val = Double.parseDouble(this.z.getPmsa());
        } catch (NumberFormatException e) {
            val = 0.0;
        }
        return val;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setCbsa(double)
     */
    @Override
    public void setCbsa(double value) {
        this.z.setCbsa(String.valueOf(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getCbsa()
     */
    @Override
    public double getCbsa() {
        double val;
        try {
            val = Double.parseDouble(this.z.getCbsa());
        } catch (NumberFormatException e) {
            val = 0.0;
        }
        return val;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setCbsaDiv(double)
     */
    @Override
    public void setCbsaDiv(double value) {
        this.z.setCbsaDiv(String.valueOf(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getCbsaDiv()
     */
    @Override
    public double getCbsaDiv() {
        double val;
        try {
            val = Double.parseDouble(this.z.getCbsaDiv());
        } catch (NumberFormatException e) {
            val = 0.0;
        }
        return val;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setPersonsPerHousehold(double)
     */
    @Override
    public void setPersonsPerHousehold(double value) {
        this.z.setPersonsPerHousehold(String.valueOf(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getPersonsPerHousehold()
     */
    @Override
    public double getPersonsPerHousehold() {
        double val;
        try {
            val = Double.parseDouble(this.z.getPersonsPerHousehold());
        } catch (NumberFormatException e) {
            val = 0.0;
        }
        return val;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setZipPopulation(double)
     */
    @Override
    public void setZipPopulation(double value) {
        this.z.setZipcodePopulation(String.valueOf(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getZipPopulation()
     */
    @Override
    public double getZipPopulation() {
        double val;
        try {
            val = Double.parseDouble(this.z.getZipcodePopulation());
        } catch (NumberFormatException e) {
            val = 0.0;
        }
        return val;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setCountiesArea(double)
     */
    @Override
    public void setCountiesArea(double value) {
        this.z.setCountiesArea(String.valueOf(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getCountiesArea()
     */
    @Override
    public double getCountiesArea() {
        double val;
        try {
            val = Double.parseDouble(this.z.getCountiesArea());
        } catch (NumberFormatException e) {
            val = 0.0;
        }
        return val;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setHouseholdsPerZipcode(double)
     */
    @Override
    public void setHouseholdsPerZipcode(double value) {
        this.z.setHouseholdsPerZipcode(String.valueOf(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getHouseholdsPerZipcode()
     */
    @Override
    public double getHouseholdsPerZipcode() {
        double val;
        try {
            val = Double.parseDouble(this.z.getHouseholdsPerZipcode());
        } catch (NumberFormatException e) {
            val = 0.0;
        }
        return val;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setWhitePopulation(double)
     */
    @Override
    public void setWhitePopulation(double value) {
        this.z.setWhitePopulation(String.valueOf(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getWhitePopulation()
     */
    @Override
    public double getWhitePopulation() {
        double val;
        try {
            val = Double.parseDouble(this.z.getWhitePopulation());
        } catch (NumberFormatException e) {
            val = 0.0;
        }
        return val;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setBlackPopulation(double)
     */
    @Override
    public void setBlackPopulation(double value) {
        this.z.setBlackPopulation(String.valueOf(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getBlackPopulation()
     */
    @Override
    public double getBlackPopulation() {
        double val;
        try {
            val = Double.parseDouble(this.z.getBlackPopulation());
        } catch (NumberFormatException e) {
            val = 0.0;
        }
        return val;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setHispanicPopulation(double)
     */
    @Override
    public void setHispanicPopulation(double value) {
        this.z.setHispanicPopulation(String.valueOf(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getHispanicPopulation()
     */
    @Override
    public double getHispanicPopulation() {
        double val;
        try {
            val = Double.parseDouble(this.z.getHispanicPopulation());
        } catch (NumberFormatException e) {
            val = 0.0;
        }
        return val;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setIncomePerHousehold(double)
     */
    @Override
    public void setIncomePerHousehold(double value) {
        this.z.setIncomePerHousehold(String.valueOf(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getIncomePerHousehold()
     */
    @Override
    public double getIncomePerHousehold() {
        double val;
        try {
            val = Double.parseDouble(this.z.getIncomePerHousehold());
        } catch (NumberFormatException e) {
            val = 0.0;
        }
        return val;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setAverageHouseValue(double)
     */
    @Override
    public void setAverageHouseValue(double value) {
        this.z.setAverageHouseValue(String.valueOf(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getAverageHouseValue()
     */
    @Override
    public double getAverageHouseValue() {
        double val;
        try {
            val = Double.parseDouble(this.z.getAverageHouseValue());
        } catch (NumberFormatException e) {
            val = 0.0;
        }
        return val;
    }

    /**
     * Not Supported
     */
    @Override
    public void setTimeZoneId(int value) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not Supported
     */
    @Override
    public int getTimeZoneId() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not supported
     */
    @Override
    public void setCityAliasAbbr(String value) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not Supported
     */
    @Override
    public String getCityAliasAbbr() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not Supported
     */
    @Override
    public void setCityTypeId(String value) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not Supported
     */
    @Override
    public String getCityTypeId() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not Supported
     */
    @Override
    public void setCityTypDescr(String value) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not Supported
     */
    @Override
    public String getCityTypDescr() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

}
