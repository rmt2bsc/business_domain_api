package org.dto;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that represents a zip lookup.
 * <p>
 * An instance of this class represents one related record. If the desire arises
 * to manage a List of these instances, then redundant data could exist.
 * 
 * @author Roy Terrell.
 */
public interface ZipcodeDto extends TransactionDto {

    /**
     * Set the internal id for the zip lookup object.
     * 
     * @param value
     *            int
     */
    void setId(int value);

    /**
     * Get the internal id for the zip lookup object.
     * 
     * @return int
     * 
     */
    int getId();

    /**
     * Set the internal id String for the zip lookup object.
     * 
     * @param value
     *            int
     */
    void setIdStr(String value);

    /**
     * Get the internal id String for the zip lookup object.
     * 
     * @return int
     * 
     */
    String getIdStr();

    /**
     * Set the zip lookup value.
     * 
     * @param value
     *            int
     */
    void setZip(int value);

    /**
     * Get the zip lookup value.
     * 
     * @return int
     */
    int getZip();

    /**
     * Set the name of the city.
     * 
     * @param value
     *            String
     */
    void setCity(String value);

    /**
     * Get the name of the city.
     * 
     * @return String
     */
    String getCity();

    /**
     * Set the abbreviated state lookup.
     * 
     * @param value
     *            String
     */
    void setStateCode(String value);

    /**
     * Get the abbreviated state lookup.
     * 
     * @return String
     */
    String getStateCode();

    /**
     * Set the name of the state/province
     * 
     * @param value
     *            String
     */
    void setStateName(String value);

    /**
     * Get the name of the state/province
     * 
     * @return String
     */
    String getStateName();

    /**
     * Set the area lookup.
     * 
     * @param value
     *            String
     */
    void setAreaCode(String value);

    /**
     * Get the area lookup.
     * 
     * @return String
     */
    String getAreaCode();

    /**
     * Set the city alias name.
     * 
     * @param value
     *            String
     */
    void setCityAliasName(String value);

    /**
     * Get the city alias name.
     * 
     * @return String
     */
    String getCityAliasName();

    /**
     * Set the city alias abbreviated lookup.
     * 
     * @param value
     *            String
     */
    void setCityAliasAbbr(String value);

    /**
     * Get the city alias abbreviated lookup.
     * 
     * @return String
     */
    String getCityAliasAbbr();

    /**
     * Set the city type id.
     * 
     * @param value
     *            String
     */
    void setCityTypeId(String value);

    /**
     * Get the city type id.
     * 
     * @return String
     */
    String getCityTypeId();

    /**
     * Set the city type description.
     * 
     * @param value
     *            String
     */
    void setCityTypDescr(String value);

    /**
     * Get the city type description.
     * 
     * @return String
     */
    String getCityTypDescr();

    /**
     * Set the country name.
     * 
     * @param value
     *            String
     */
    void setCountyName(String value);

    /**
     * Get the country name.
     * 
     * @return String
     */
    String getCountyName();

    /**
     * Set the country FIPS.
     * 
     * @param value
     *            String
     */
    void setCountyFips(String value);

    /**
     * Get the country FIPS.
     * 
     * @return String
     * 
     */
    String getCountyFips();

    /**
     * Set the timezone id.
     * 
     * @param value
     *            int
     */
    void setTimeZoneId(int value);

    /**
     * Get the timezone id.
     * 
     * @return int
     */
    int getTimeZoneId();

    /**
     * Set the timezone description.
     * 
     * @param value
     *            String
     */
    void setTimeZoneDescr(String value);

    /**
     * Get the timezone description.
     * 
     * @return String
     */
    String getTimeZoneDescr();

    /**
     * Set day light savings indicator.
     * 
     * @param value
     *            String
     */
    void setDayLightSaving(String value);

    /**
     * Get day light savings indicator.
     * 
     * @return String
     */
    String getDayLightSaving();

    /**
     * Set latitude of zip.
     * 
     * @param value
     *            double
     */
    void setLatitude(double value);

    /**
     * Get latitude of zip.
     * 
     * @return double
     */
    double getLatitude();

    /**
     * Set longitude of zip.
     * 
     * @param value
     *            double
     */
    void setLongitude(double value);

    /**
     * Get longitude of zip.
     * 
     * @return double
     */
    double getLongitude();

    /**
     * Set elevation of zip.
     * 
     * @param value
     *            double
     */
    void setElevation(double value);

    /**
     * Get elevation of zip.
     * 
     * @return double
     */
    double getElevation();

    /**
     * Set MSA of zip.
     * 
     * @param value
     *            double
     */
    void setMsa(double value);

    /**
     * Get MSA of zip.
     * 
     * @return double
     */
    double getMsa();

    /**
     * Set PMSA of zip.
     * 
     * @param value
     *            double
     */
    void setPmsa(double value);

    /**
     * Set PMSA of zip.
     * 
     * @return double
     */
    double getPmsa();

    /**
     * Set CBSA of zip.
     * 
     * @param value
     *            double
     */
    void setCbsa(double value);

    /**
     * Get CBSA of zip.
     * 
     * @return double
     */
    double getCbsa();

    /**
     * Set CBSA Division of zip.
     * 
     * @param value
     *            double
     */
    void setCbsaDiv(double value);

    /**
     * Get CBSA Division of zip.
     * 
     * @return double
     */
    double getCbsaDiv();

    /**
     * Set persons per household of zip.
     * 
     * @param value
     *            double
     */
    void setPersonsPerHousehold(double value);

    /**
     * Get persons per household of zip.
     * 
     * @return double
     */
    double getPersonsPerHousehold();

    /**
     * Set population of zip.
     * 
     * @param value
     */
    void setZipPopulation(double value);

    /**
     * Get population of zip.
     * 
     * @return double
     */
    double getZipPopulation();

    /**
     * Set counties area.
     * 
     * @param value
     *            double
     */
    void setCountiesArea(double value);

    /**
     * Get counties area.
     * 
     * @return double
     */
    double getCountiesArea();

    /**
     * Set the number of households per zip lookup.
     * 
     * @param value
     *            double
     */
    void setHouseholdsPerZipcode(double value);

    /**
     * Get the number of households per zip lookup.
     * 
     * @return double
     */
    double getHouseholdsPerZipcode();

    /**
     * Set the white population for zip lookup.
     * 
     * @param value
     *            double
     */
    void setWhitePopulation(double value);

    /**
     * Get the white population for zip lookup.
     * 
     * @return double
     */
    double getWhitePopulation();

    /**
     * Set the black population for zip lookup.
     * 
     * @param value
     *            double
     */
    void setBlackPopulation(double value);

    /**
     * Get the black population for zip lookup.
     * 
     * @return double
     */
    double getBlackPopulation();

    /**
     * Set the hispanic population for zip lookup.
     * 
     * @param value
     *            double
     */
    void setHispanicPopulation(double value);

    /**
     * Get the hispanic population for zip lookup.
     * 
     * @return double
     */
    double getHispanicPopulation();

    /**
     * Set income per household for zip lookup.
     * 
     * @param value
     *            double
     */
    void setIncomePerHousehold(double value);

    /**
     * Get income per household for zip lookup.
     * 
     * @return double
     */
    double getIncomePerHousehold();

    /**
     * Set average household value for zip lookup.
     * 
     * @param value
     *            double
     */
    void setAverageHouseValue(double value);

    /**
     * Get average household value for zip lookup.
     * 
     * @return double
     */
    double getAverageHouseValue();

}