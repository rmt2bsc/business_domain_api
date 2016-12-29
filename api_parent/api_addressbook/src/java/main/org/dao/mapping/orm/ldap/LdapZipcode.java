package org.dao.mapping.orm.ldap;

import com.api.ldap.beans.LdapCommonEntity;

/**
 * A bean for capturing and managing zip code information from an LDAP data
 * source.
 * 
 * @author Roy Terrell
 * 
 */
public class LdapZipcode extends LdapCommonEntity {

    private String areaCode;

    private String city;

    private String cityAliasName;

    private String countyName;

    private String state;

    private String stateName;

    private String timeZoneId;

    private String zip;

    private String zipId;

    private String averageHouseValue;

    private String blackPopulation;

    private String cbsa;

    private String cbsaDiv;

    private String countiesArea;

    private String countyFips;

    private String dayLightSaving;

    private String elevation;

    private String hispanicPopulation;

    private String householdsPerZipcode;

    private String incomePerHousehold;

    private String latitude;

    private String longitude;

    private String msa;

    private String personsPerHousehold;

    private String pmsa;

    private String whitePopulation;

    private String zipcodePopulation;

    /**
     * Default constructor.
     */
    public LdapZipcode() {
        super();
    }

    /**
     * @return the areaCode
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * @param areaCode
     *            the areaCode to set
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     *            the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the cityAliasName
     */
    public String getCityAliasName() {
        return cityAliasName;
    }

    /**
     * @param cityAliasName
     *            the cityAliasName to set
     */
    public void setCityAliasName(String cityAliasName) {
        this.cityAliasName = cityAliasName;
    }

    /**
     * @return the countyName
     */
    public String getCountyName() {
        return countyName;
    }

    /**
     * @param countyName
     *            the countyName to set
     */
    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state
     *            the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the timeZoneId
     */
    public String getTimeZoneId() {
        return timeZoneId;
    }

    /**
     * @param timeZoneId
     *            the timeZoneId to set
     */
    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip
     *            the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @return the zipId
     */
    public String getZipId() {
        return zipId;
    }

    /**
     * @param zipId
     *            the zipId to set
     */
    public void setZipId(String zipId) {
        this.zipId = zipId;
    }

    /**
     * @return the averageHouseValue
     */
    public String getAverageHouseValue() {
        return averageHouseValue;
    }

    /**
     * @param averageHouseValue
     *            the averageHouseValue to set
     */
    public void setAverageHouseValue(String averageHouseValue) {
        this.averageHouseValue = averageHouseValue;
    }

    /**
     * @return the blackPopulation
     */
    public String getBlackPopulation() {
        return blackPopulation;
    }

    /**
     * @param blackPopulation
     *            the blackPopulation to set
     */
    public void setBlackPopulation(String blackPopulation) {
        this.blackPopulation = blackPopulation;
    }

    /**
     * @return the cbsa
     */
    public String getCbsa() {
        return cbsa;
    }

    /**
     * @param cbsa
     *            the cbsa to set
     */
    public void setCbsa(String cbsa) {
        this.cbsa = cbsa;
    }

    /**
     * @return the cbsaDiv
     */
    public String getCbsaDiv() {
        return cbsaDiv;
    }

    /**
     * @param cbsaDiv
     *            the cbsaDiv to set
     */
    public void setCbsaDiv(String cbsaDiv) {
        this.cbsaDiv = cbsaDiv;
    }

    /**
     * @return the countiesArea
     */
    public String getCountiesArea() {
        return countiesArea;
    }

    /**
     * @param countiesArea
     *            the countiesArea to set
     */
    public void setCountiesArea(String countiesArea) {
        this.countiesArea = countiesArea;
    }

    /**
     * @return the countyFips
     */
    public String getCountyFips() {
        return countyFips;
    }

    /**
     * @param countyFips
     *            the countyFips to set
     */
    public void setCountyFips(String countyFips) {
        this.countyFips = countyFips;
    }

    /**
     * @return the dayLightSaving
     */
    public String getDayLightSaving() {
        return dayLightSaving;
    }

    /**
     * @param dayLightSaving
     *            the dayLightSaving to set
     */
    public void setDayLightSaving(String dayLightSaving) {
        this.dayLightSaving = dayLightSaving;
    }

    /**
     * @return the elevation
     */
    public String getElevation() {
        return elevation;
    }

    /**
     * @param elevation
     *            the elevation to set
     */
    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    /**
     * @return the hispanicPopulation
     */
    public String getHispanicPopulation() {
        return hispanicPopulation;
    }

    /**
     * @param hispanicPopulation
     *            the hispanicPopulation to set
     */
    public void setHispanicPopulation(String hispanicPopulation) {
        this.hispanicPopulation = hispanicPopulation;
    }

    /**
     * @return the householdsPerZipcode
     */
    public String getHouseholdsPerZipcode() {
        return householdsPerZipcode;
    }

    /**
     * @param householdsPerZipcode
     *            the householdsPerZipcode to set
     */
    public void setHouseholdsPerZipcode(String householdsPerZipcode) {
        this.householdsPerZipcode = householdsPerZipcode;
    }

    /**
     * @return the incomePerHousehold
     */
    public String getIncomePerHousehold() {
        return incomePerHousehold;
    }

    /**
     * @param incomePerHousehold
     *            the incomePerHousehold to set
     */
    public void setIncomePerHousehold(String incomePerHousehold) {
        this.incomePerHousehold = incomePerHousehold;
    }

    /**
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude
     *            the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude
     *            the longitude to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the msa
     */
    public String getMsa() {
        return msa;
    }

    /**
     * @param msa
     *            the msa to set
     */
    public void setMsa(String msa) {
        this.msa = msa;
    }

    /**
     * @return the personsPerHousehold
     */
    public String getPersonsPerHousehold() {
        return personsPerHousehold;
    }

    /**
     * @param personsPerHousehold
     *            the personsPerHousehold to set
     */
    public void setPersonsPerHousehold(String personsPerHousehold) {
        this.personsPerHousehold = personsPerHousehold;
    }

    /**
     * @return the pmsa
     */
    public String getPmsa() {
        return pmsa;
    }

    /**
     * @param pmsa
     *            the pmsa to set
     */
    public void setPmsa(String pmsa) {
        this.pmsa = pmsa;
    }

    /**
     * @return the whitePopulation
     */
    public String getWhitePopulation() {
        return whitePopulation;
    }

    /**
     * @param whitePopulation
     *            the whitePopulation to set
     */
    public void setWhitePopulation(String whitePopulation) {
        this.whitePopulation = whitePopulation;
    }

    /**
     * @return the zipcodePopulation
     */
    public String getZipcodePopulation() {
        return zipcodePopulation;
    }

    /**
     * @param zipcodePopulation
     *            the zipcodePopulation to set
     */
    public void setZipcodePopulation(String zipcodePopulation) {
        this.zipcodePopulation = zipcodePopulation;
    }

    /**
     * @return the stateName
     */
    public String getStateName() {
        return stateName;
    }

    /**
     * @param stateName
     *            the stateName to set
     */
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

}