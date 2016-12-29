package org.dto.adapter.orm;

import org.dao.mapping.orm.rmt2.CityType;
import org.dao.mapping.orm.rmt2.Country;
import org.dao.mapping.orm.rmt2.State;
import org.dao.mapping.orm.rmt2.TimeZone;
import org.dao.mapping.orm.rmt2.VwStateCountry;
import org.dao.mapping.orm.rmt2.Zipcode;
import org.dto.CityTypeDto;
import org.dto.CountryDto;
import org.dto.CountryRegionDto;
import org.dto.RegionDto;
import org.dto.TimeZoneDto;
import org.dto.ZipcodeDto;

import com.RMT2Constants;
import com.api.foundation.TransactionDtoImpl;

/**
 * Adapts Postal related RMT2 ORM objects to an appropriate DTO.
 * <p>
 * This implementation is capable of adapting database derived data pertaining
 * to zip lookup, time zone, country, state/province, combined state and
 * country, and city type.
 * <p>
 * The following DTO interfaces are implemented: <i>ZipcodeDto</i>,
 * <i>TimeZoneDto</i>, <i>CountryDto</i>, <i>RegionDto</i>,
 * <i>CountryRegionDto</i>, and <i>CityTypeDto</i>.
 * 
 * @author rterrell
 * 
 */
class PostalDataRmt2OrmAdapter extends TransactionDtoImpl implements
        ZipcodeDto, TimeZoneDto, CountryDto, RegionDto, CountryRegionDto,
        CityTypeDto {

    private Zipcode zip;

    private TimeZone tz;

    private CityType ct;

    private Country country;

    private State state;

    private VwStateCountry vwsc;

    /**
     * Default constructor
     */
    private PostalDataRmt2OrmAdapter() {
        super();
        this.zip = null;
        this.tz = null;
        this.ct = null;
        this.country = null;
        this.state = null;
        this.vwsc = null;
    }

    /**
     * Create a PostalDataRmt2OrmAdapter using an instance of <i>Zipcode</i>.
     * 
     * @param zip
     *            an instance of {@link Zipcode} or null for the purpose of
     *            creating a new Zipcode object
     */
    protected PostalDataRmt2OrmAdapter(Zipcode zip) {
        this();
        if (zip == null) {
            zip = new Zipcode();
        }
        this.zip = zip;
        return;
    }

    /**
     * Create a PostalDataRmt2OrmAdapter using an instance of <i>TimeZone</i>.
     * 
     * @param tz
     *            an instance of {@link TimeZone} or null for the purpose of
     *            creating a new TimeZone object
     */
    protected PostalDataRmt2OrmAdapter(TimeZone tz) {
        this();
        if (tz == null) {
            tz = new TimeZone();
        }
        this.tz = tz;
        return;
    }

    /**
     * Create a PostalDataRmt2OrmAdapter using an instance of <i>Country</i>.
     * 
     * @param country
     *            an instance of {@link Country} or null for the purpose of
     *            creating a new Country object
     */
    protected PostalDataRmt2OrmAdapter(Country country) {
        this();
        if (country == null) {
            country = new Country();
        }
        this.country = country;
        this.updateUserId = "N/A";
    }

    /**
     * Create a PostalDataRmt2OrmAdapter using an instance of <i>State</i>.
     * 
     * @param state
     *            an instance of {@link State} or null for the purpose of
     *            creating a new State object
     */
    public PostalDataRmt2OrmAdapter(State state) {
        this();
        if (state == null) {
            state = new State();
        }
        this.state = state;
        this.updateUserId = "N/A";
    }

    /**
     * Create a PostalDataRmt2OrmAdapter using an instance of
     * <i>VwStateCountry</i>.
     * 
     * @param arg
     *            an instance of {@link VwStateCountry} or null for the purpose
     *            of creating a new State object
     */
    public PostalDataRmt2OrmAdapter(VwStateCountry arg) {
        this();
        if (arg == null) {
            arg = new VwStateCountry();
        }
        this.vwsc = arg;
        this.updateUserId = "N/A";
    }

    /**
     * Create a PostalDataRmt2OrmAdapter using an instance of <i>CityType</i>.
     * 
     * @param ct
     *            an instance of {@link CityType} or null for the purpose of
     *            creating a new CityType object
     */
    protected PostalDataRmt2OrmAdapter(CityType ct) {
        this();
        if (ct == null) {
            ct = new CityType();
        }
        this.ct = ct;
        return;
    }

    /**
     * Create a PostalDataRmt2OrmAdapter using an instance of <i>Zipcode</i>,
     * <i>TimeZone</i>, and <i>CityType</i>.
     * 
     * @param zip
     *            an instance of {@link Zipcode} or null for the purpose of
     *            creating a new Zipcode object
     * @param tz
     *            an instance of {@link TimeZone} or null for the purpose of
     *            creating a new TimeZone object
     * @param ct
     *            an instance of {@link CityType} or null for the purpose of
     *            creating a new CityType object
     */
    protected PostalDataRmt2OrmAdapter(Zipcode zip, TimeZone tz, CityType ct) {
        this(zip);
        if (tz == null) {
            tz = new TimeZone();
        }
        if (ct == null) {
            ct = new CityType();
        }
        this.zip = zip;
        this.tz = tz;
        this.ct = ct;
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setZipId(int)
     */
    @Override
    public void setId(int value) {
        this.zip.setZipId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getZipId()
     */
    @Override
    public int getId() {
        return this.zip.getZipId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setZip(int)
     */
    @Override
    public void setZip(int value) {
        this.zip.setZip(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getZip()
     */
    @Override
    public int getZip() {
        return this.zip.getZip();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setZipCity(java.lang.String)
     */
    @Override
    public void setCity(String value) {
        this.zip.setCity(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getZipCity()
     */
    @Override
    public String getCity() {
        return this.zip.getCity();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.RegionDto#setCountryId(int)
     */
    @Override
    public void setCountryId(int value) {
        if (this.country != null) {
            this.country.setCountryId(value);
        }
        if (this.state != null) {
            this.state.setCountryId(value);
        }
        if (this.vwsc != null) {
            this.vwsc.setCountryId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.RegionDto#getCountryId()
     */
    @Override
    public int getCountryId() {
        if (this.country != null) {
            return this.country.getCountryId();
        }
        if (this.state != null) {
            return this.state.getCountryId();
        }
        if (this.vwsc != null) {
            return this.vwsc.getCountryId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CountryDto#setCountryCode(java.lang.String)
     */
    @Override
    public void setCountryCode(String value) {
        this.country.setCode(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CountryDto#getCountryCode()
     */
    @Override
    public String getCountryCode() {
        return this.country.getCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.RegionDto#setCountryName(java.lang.String)
     */
    @Override
    public void setCountryName(String value) {
        if (this.country != null) {
            this.country.setName(value);
        }
        if (this.vwsc != null) {
            this.vwsc.setCountryName(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.RegionDto#getCountryName()
     */
    @Override
    public String getCountryName() {
        if (this.country != null) {
            return this.country.getName();
        }
        if (this.vwsc != null) {
            return this.vwsc.getCountryName();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CountryDto#setCountryPermcol(java.lang.String)
     */
    @Override
    public void setCountryPermcol(String value) {
        this.country.setCntryVoidInd(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CountryDto#getCountryPermcol()
     */
    @Override
    public String getCountryPermcol() {
        return this.country.getCntryVoidInd();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.RegionDto#setStateId(int)
     */
    @Override
    public void setStateId(int value) {
        if (this.state != null) {
            this.state.setStateId(value);
        }
        if (this.vwsc != null) {
            this.vwsc.setStateId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.RegionDto#getStateId()
     */
    @Override
    public int getStateId() {
        if (this.state != null) {
            return this.state.getStateId();
        }
        if (this.vwsc != null) {
            return this.vwsc.getStateId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setZipState(java.lang.String)
     */
    @Override
    public void setStateCode(String value) {
        if (this.zip != null) {
            this.zip.setState(value);
        }
        if (this.state != null) {
            this.state.setAbbrCode(value);
        }
        if (this.vwsc != null) {
            this.vwsc.setStateCode(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getZipState()
     */
    @Override
    public String getStateCode() {
        if (this.zip != null) {
            return this.zip.getState();
        }
        if (this.state != null) {
            return this.state.getAbbrCode();
        }
        if (this.vwsc != null) {
            return this.vwsc.getStateCode();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.RegionDto#setName(java.lang.String)
     */
    @Override
    public void setStateName(String value) {
        if (this.state != null) {
            this.state.setStateName(value);
        }
        if (this.vwsc != null) {
            this.vwsc.setStateName(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.RegionDto#getName()
     */
    @Override
    public String getStateName() {
        if (this.state != null) {
            return this.state.getStateName();
        }
        if (this.vwsc != null) {
            return this.vwsc.getStateName();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.RegionDto#setPermcol(java.lang.String)
     */
    @Override
    public void setStatePermcol(String value) {
        this.state.setSttVoidInd(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.RegionDto#getPermcol()
     */
    @Override
    public String getStatePermcol() {
        return this.state.getSttVoidInd();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setAreaCode(java.lang.String)
     */
    @Override
    public void setAreaCode(String value) {
        this.zip.setAreaCode(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getAreaCode()
     */
    @Override
    public String getAreaCode() {
        return this.zip.getAreaCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setCityAliasName(java.lang.String)
     */
    @Override
    public void setCityAliasName(String value) {
        this.zip.setCityAliasName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getCityAliasName()
     */
    @Override
    public String getCityAliasName() {
        return this.zip.getCityAliasName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setCityAliasAbbr(java.lang.String)
     */
    @Override
    public void setCityAliasAbbr(String value) {
        this.zip.setCityAliasAbbr(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getCityAliasAbbr()
     */
    @Override
    public String getCityAliasAbbr() {
        return this.zip.getCityAliasAbbr();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setCityTypeId(java.lang.String)
     */
    @Override
    public void setCityTypeId(String value) {
        this.zip.setCityTypeId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getCityTypeId()
     */
    @Override
    public String getCityTypeId() {
        if (this.zip != null) {
            return this.zip.getCityTypeId();
        }
        if (this.ct != null) {
            return this.ct.getCityTypeId();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setCityTypDescr(java.lang.String)
     */
    @Override
    public void setCityTypDescr(String value) {
        this.ct.setDescr(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getCityTypDescr()
     */
    @Override
    public String getCityTypDescr() {
        return this.ct.getDescr();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setCountyName(java.lang.String)
     */
    @Override
    public void setCountyName(String value) {
        this.zip.setCountyName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getCountyName()
     */
    @Override
    public String getCountyName() {
        return this.zip.getCountyName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setCountyFips(java.lang.String)
     */
    @Override
    public void setCountyFips(String value) {
        this.zip.setCountyFips(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getCountyFips()
     */
    @Override
    public String getCountyFips() {
        return this.zip.getCountyFips();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setTimeZoneId(int)
     */
    @Override
    public void setTimeZoneId(int value) {
        this.zip.setTimeZoneId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getTimeZoneId()
     */
    @Override
    public int getTimeZoneId() {
        if (this.zip != null) {
            return this.zip.getTimeZoneId();
        }
        if (this.tz != null) {
            return this.tz.getTimeZoneId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setTimeZoneDescr(java.lang.String)
     */
    @Override
    public void setTimeZoneDescr(String value) {
        this.tz.setDescr(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getTimeZoneDescr()
     */
    @Override
    public String getTimeZoneDescr() {
        return this.tz.getDescr();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setDayLightSaving(java.lang.String)
     */
    @Override
    public void setDayLightSaving(String value) {
        this.zip.setDayLightSaving(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getDayLightSaving()
     */
    @Override
    public String getDayLightSaving() {
        return this.zip.getDayLightSaving();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setLatitude(double)
     */
    @Override
    public void setLatitude(double value) {
        this.zip.setLatitude(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getLatitude()
     */
    @Override
    public double getLatitude() {
        return this.zip.getLatitude();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setLongitude(double)
     */
    @Override
    public void setLongitude(double value) {
        this.zip.setLongitude(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getLongitude()
     */
    @Override
    public double getLongitude() {
        return this.zip.getLongitude();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setElevation(double)
     */
    @Override
    public void setElevation(double value) {
        this.zip.setElevation(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getElevation()
     */
    @Override
    public double getElevation() {
        return this.zip.getElevation();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setMsa(double)
     */
    @Override
    public void setMsa(double value) {
        this.zip.setMsa(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getMsa()
     */
    @Override
    public double getMsa() {
        return this.zip.getMsa();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setPmsa(double)
     */
    @Override
    public void setPmsa(double value) {
        this.zip.setPmsa(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getPmsa()
     */
    @Override
    public double getPmsa() {
        return this.zip.getPmsa();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setCbsa(double)
     */
    @Override
    public void setCbsa(double value) {
        this.zip.setCbsa(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getCbsa()
     */
    @Override
    public double getCbsa() {
        return this.zip.getCbsa();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setCbsaDiv(double)
     */
    @Override
    public void setCbsaDiv(double value) {
        this.zip.setCbsaDiv(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getCbsaDiv()
     */
    @Override
    public double getCbsaDiv() {
        return this.zip.getCbsaDiv();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setPersonsPerHousehold(double)
     */
    @Override
    public void setPersonsPerHousehold(double value) {
        this.zip.setPersonsPerHousehold(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getPersonsPerHousehold()
     */
    @Override
    public double getPersonsPerHousehold() {
        return this.zip.getPersonsPerHousehold();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setZipPopulation(double)
     */
    @Override
    public void setZipPopulation(double value) {
        this.zip.setZipcodePopulation(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getZipPopulation()
     */
    @Override
    public double getZipPopulation() {
        return this.zip.getZipcodePopulation();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setCountiesArea(double)
     */
    @Override
    public void setCountiesArea(double value) {
        this.zip.setCountiesArea(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getCountiesArea()
     */
    @Override
    public double getCountiesArea() {
        return this.zip.getCountiesArea();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setHouseholdsPerZipcode(double)
     */
    @Override
    public void setHouseholdsPerZipcode(double value) {
        this.zip.setHouseholdsPerZipcode(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getHouseholdsPerZipcode()
     */
    @Override
    public double getHouseholdsPerZipcode() {
        return this.zip.getHouseholdsPerZipcode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setWhitePopulation(double)
     */
    @Override
    public void setWhitePopulation(double value) {
        this.zip.setWhitePopulation(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getWhitePopulation()
     */
    @Override
    public double getWhitePopulation() {
        return this.zip.getWhitePopulation();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setBlackPopulation(double)
     */
    @Override
    public void setBlackPopulation(double value) {
        this.zip.setBlackPopulation(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getBlackPopulation()
     */
    @Override
    public double getBlackPopulation() {
        return this.zip.getBlackPopulation();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setHispanicPopulation(double)
     */
    @Override
    public void setHispanicPopulation(double value) {
        this.zip.setHispanicPopulation(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getHispanicPopulation()
     */
    @Override
    public double getHispanicPopulation() {
        return this.zip.getHispanicPopulation();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setIncomePerHousehold(double)
     */
    @Override
    public void setIncomePerHousehold(double value) {
        this.zip.setIncomePerHousehold(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getIncomePerHousehold()
     */
    @Override
    public double getIncomePerHousehold() {
        return this.zip.getIncomePerHousehold();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setAverageHouseValue(double)
     */
    @Override
    public void setAverageHouseValue(double value) {
        this.zip.setAverageHouseValue(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getAverageHouseValue()
     */
    @Override
    public double getAverageHouseValue() {
        return this.zip.getAverageHouseValue();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#setIdStr(java.lang.String)
     */
    @Override
    public void setIdStr(String value) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ZipcodeDto#getIdStr()
     */
    @Override
    public String getIdStr() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

}
