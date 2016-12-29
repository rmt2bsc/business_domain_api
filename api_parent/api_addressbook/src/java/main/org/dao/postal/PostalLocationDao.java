package org.dao.postal;

import java.util.List;

import org.dto.IpLocationDto;
import org.dto.TimeZoneDto;
import org.dto.ZipcodeDto;

import com.api.persistence.DaoClient;

/**
 * Contract for accessing postal location information as it pertains to zip
 * code, time zone, and IP address data.
 * 
 * @author Roy Terrell
 * 
 */
public interface PostalLocationDao extends DaoClient {

    /**
     * Fetches a zip code entry by using its internal unique id.
     * 
     * @param uid
     *            The primary key of the zip code object.
     * @return An instance of {@link ZipcodeDto}
     * @throws PostalDaoException
     */
    ZipcodeDto fetchZipCode(int uid) throws PostalDaoException;

    /**
     * Fetches a list of zip code entries based on selection criteria included
     * in <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link ZipcodeDto} containing properties used
     *            to build the query's selection criteria.
     * @return a List of {@link ZipcodeDto}
     * @throws PostalDaoException
     */
    List<ZipcodeDto> fetchZipCode(ZipcodeDto criteria)
            throws PostalDaoException;

    /**
     * Fetches a time zone entry by using its internal unique id.
     * 
     * @param uid
     *            The primary key of the time zone to search.
     * @return an instance of {@link TimeZoneDto}
     * @throws PostalDaoException
     */
    TimeZoneDto fetchTimezone(int uid) throws PostalDaoException;

    /**
     * Fetches a list of time zone entries based on selection criteria included
     * in <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link TimeZoneDto} containing properties used
     *            to build the query's selection criteria. The following
     *            properties are recognized when building the selection
     *            criteria: <i>group id</i> and <i>group description</i>.
     * @return a List of {@link TimeZoneDto}
     * @throws PostalDaoException
     */
    List<TimeZoneDto> fetchTimezone(TimeZoneDto criteria)
            throws PostalDaoException;

    /**
     * Fetch the location details for an IP address that is in IPv4 form.
     * 
     * @param ip
     *            the IPv4 representation of the IP address
     * @return an instance of {@link IpLocationDto}
     * @throws PostalDaoException
     */
    IpLocationDto fetchIpInfo(String ip) throws PostalDaoException;

    /**
     * Fetch the location details for an IP address that is in integer form.
     * 
     * @param ip
     *            the integer valud of the IP address
     * @return an instance of {@link IpLocationDto}
     * @throws PostalDaoException
     */
    IpLocationDto fetchIpInfo(long ip) throws PostalDaoException;

}
