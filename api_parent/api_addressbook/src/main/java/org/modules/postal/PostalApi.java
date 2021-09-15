package org.modules.postal;

import java.util.List;

import org.dto.CountryDto;
import org.dto.CountryRegionDto;
import org.dto.IpLocationDto;
import org.dto.RegionDto;
import org.dto.TimeZoneDto;
import org.dto.ZipcodeDto;

import com.api.foundation.TransactionApi;

/**
 * An contract for executing transactions related to the postal module.
 * 
 * @author rterrell
 * 
 */
// IS-70: Changed class definition so to take advantage of the "close()"
// operation
public interface PostalApi extends TransactionApi {

    /**
     * Obtains a single country object by its internal unique id.
     * 
     * @param uid
     *            thei internal unique id of the country
     * @return an instance of {@link CountryDto} or null if no data is found.
     * @throws PostalApiException
     */
    CountryDto getCountry(int uid) throws PostalApiException;

    /**
     * Obtains one or more country objects using various attributes of
     * <i>criteria</i> as selection criteria.
     * 
     * @param criteria
     *            an instance of {@link CountryDto} containing the properties
     *            that are to be applied to the query as selection criteria.
     * @return a List of {@link CountryDto} or null if no data is found.
     * @throws PostalApiException
     */
    List<CountryDto> getCountry(CountryDto criteria) throws PostalApiException;

    /**
     * Obtains one or more objects containign both country and region data using
     * various attributes of <i>criteria</i> as selection criteria.
     * 
     * @param criteria
     *            an instance of {@link CountryRegionDto} containing the
     *            properties that are to be applied to the query as selection
     *            criteria.
     * @return a List of {@link CountryRegionDto} or null if no data is found.
     * @throws PostalApiException
     */
    List<CountryRegionDto> getCountryRegion(CountryRegionDto criteria) throws PostalApiException;

    /**
     * Obtains a single region (state/province) object by its internal unique
     * id.
     * 
     * @param uid
     *            thei internal unique id of the region
     * @return an instance of {@link RegionDto} or null if no data is found.
     * @throws PostalApiException
     */
    RegionDto getRegion(int uid) throws PostalApiException;

    /**
     * Obtains one or more region (state/province) objects using various
     * attributes of <i>criteria</i> as selection criteria.
     * 
     * @param criteria
     *            an instance of {@link RegionDto} containing the properties
     *            that are to be applied to the query as selection criteria.
     * @return a List of {@link RegionDto} or null if no data is found.
     * @throws PostalApiException
     */
    List<RegionDto> getRegion(RegionDto criteria) throws PostalApiException;

    /**
     * Obtains a zip code entry by using its internal unique id.
     * 
     * @param uid
     *            The primary key of the zip code object.
     * @return An instance of {@link ZipcodeDto}
     * @throws PostalApiException
     */
    ZipcodeDto getZipCode(int uid) throws PostalApiException;

    /**
     * Obtains a list of zip code entries based on selection criteria included
     * in <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link ZipcodeDto} containing properties used
     *            to build the query's selection criteria.
     * @return a List of {@link ZipcodeDto} or null for no data set found.
     * @throws PostalApiException
     */
    List<ZipcodeDto> getZipCode(ZipcodeDto criteria) throws PostalApiException;

    /**
     * Obtains a time zone entry by using its internal unique id.
     * 
     * @param uid
     *            The primary key of the time zone to search.
     * @return an instance of {@link TimeZoneDto}
     * @throws PostalApiException
     */
    TimeZoneDto getTimezone(int uid) throws PostalApiException;

    /**
     * Obtains a list of time zone entries based on selection criteria included
     * in <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link TimeZoneDto} containing properties used
     *            to build the query's selection criteria. The following
     *            properties are recognized when building the selection
     *            criteria: <i>group id</i> and <i>group description</i>.
     * @return a List of {@link TimeZoneDto}
     * @throws PostalApiException
     */
    List<TimeZoneDto> getTimezone(TimeZoneDto criteria) throws PostalApiException;

    /**
     * Obtains the location details for an IP address that is in IPv4 form.
     * 
     * @param ip
     *            the IPv4 representation of the IP address
     * @return an instance of {@link IpLocationDto}
     * @throws PostalApiException
     */
    IpLocationDto getIpInfo(String ip) throws PostalApiException;

    /**
     * Obtains the location details for an IP address that is in integer form.
     * 
     * @param ip
     *            the integer valud of the IP address
     * @return an instance of {@link IpLocationDto}
     * @throws PostalApiException
     */
    IpLocationDto getIpInfo(long ip) throws PostalApiException;

    /**
     * Creates a new or modifies an existing region object and persist the
     * changes to a data source.
     * 
     * @param obj
     *            an {@link RegionDto} intstance to persist
     * @return int an internal unique id for new objects or the total number of
     *         rows effected by the transaction.
     * @throws PostalApiException
     *             General database error
     */
    int updateRegion(RegionDto obj) throws PostalApiException;

    /**
     * Deletes a Region record from the system
     * 
     * @param stateId
     *            The internal unique id of the state objet to delete.
     * @return The total number of row effected.
     * @throws PostalApiException
     *             General database error
     */
    int deleteRegion(int stateId) throws PostalApiException;

    /**
     * Creates a new or modifies an existing country object and persist the
     * changes to a data source.
     * 
     * @param obj
     *            an {@link CountryDto} intstance to persist
     * @return int an internal unique id for new objects or the total number of
     *         rows effected by the transaction.
     * @throws PostalApiException
     *             General database error
     */
    int updateCountry(CountryDto obj) throws PostalApiException;

    /**
     * Deletes a Country record from the system.
     * 
     * @param countryId
     *            The internal unique id of the country objet to delete.
     * @return The total number of row effected.
     * @throws PostalApiException
     *             General database error
     */
    int deleteCountry(int countryId) throws PostalApiException;

}
