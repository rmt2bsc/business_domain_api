package org.dao.postal;

import java.util.ArrayList;
import java.util.List;

import org.dao.AddressBookDaoImpl;
import org.dao.mapping.orm.rmt2.IpBlock;
import org.dao.mapping.orm.rmt2.IpLocation;
import org.dao.mapping.orm.rmt2.TimeZone;
import org.dao.mapping.orm.rmt2.Zipcode;
import org.dto.IpLocationDto;
import org.dto.TimeZoneDto;
import org.dto.ZipcodeDto;
import org.dto.adapter.orm.Rmt2AddressBookDtoFactory;

import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.OrmBean;
import com.util.RMT2Utility;

/**
 * An RMT2 ORM implementation of {@link PostalLocationDao} contract for
 * accessing postal location information as it pertains to zip code, time zone,
 * and IP address location data.
 * 
 * @author Roy Terrell
 * 
 */
class Rmt2OrmPostalLocationDaoImpl extends AddressBookDaoImpl implements
        PostalLocationDao {

    /**
     * Default constructor.
     */
    protected Rmt2OrmPostalLocationDaoImpl() {
        super();
    }

    protected Rmt2OrmPostalLocationDaoImpl(String appName) {
        super(appName);
    }

    /**
     * Fetches a single zip code record from the zipcode table by its primary
     * key.
     * 
     * @param uid
     *            The primary key of the row targeted in the zipcode table.
     * @return An instance of {@link ZipcodeDto} or null if no data is found
     * @throws PostalDaoException
     */
    @Override
    public ZipcodeDto fetchZipCode(int uid) throws PostalDaoException {
        Zipcode z = new Zipcode();
        z.addCriteria(Zipcode.PROP_ZIPID, uid);
        try {
            Zipcode results = (Zipcode) this.client.retrieveObject(z);
            if (results == null) {
                return null;
            }
            ZipcodeDto dto = Rmt2AddressBookDtoFactory
                    .getZipCodeInstance(results);
            return dto;
        } catch (DatabaseException e) {
            this.msg = "Database access error occurred while fetching zip code record by primary key, "
                    + uid;
            throw new ZipcodeDaoException(this.msg, e);
        }
    }

    /**
     * Fetches a list of zip code records fromt the zipcode table using
     * selection criteria included in <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link ZipcodeDto} containing properties used
     *            to build the query's selection criteria. The following
     *            properties that can be assigned values are <i>UID</i>,
     *            <i>zip</i>, <i>timeZoneId</i>, <i>stateCode</i>,
     *            <i>countyName</i>, <i>areaCode</i>, and <i>city</i>.
     * @return a List of {@link ZipcodeDto} ordered by state, city, city alias,
     *         and zip code in which all are in ascendnig order or null if no
     *         data is found.
     * @throws ZipcodeDaoException
     *             <i>criteria</i> is equal null or database access error.
     * @throws InvalidZipcodeCriteriaDaoException
     *             Thera are no properties recognized in the non-null
     *             <i>criteria</i> object as being assigned a value.
     */
    @Override
    public List<ZipcodeDto> fetchZipCode(ZipcodeDto criteria)
            throws PostalDaoException {
        if (criteria == null) {
            this.msg = "Zip search criteria object is invalid";
            throw new ZipcodeDaoException(this.msg);
        }

        // Setup selection criteria
        Zipcode z = new Zipcode();
        if (criteria.getId() > 0) {
            z.addCriteria(Zipcode.PROP_ZIPID, criteria.getId());
        }
        if (criteria.getZip() > 0) {
            z.addCriteria(Zipcode.PROP_ZIP, criteria.getZip());
        }
        if (criteria.getTimeZoneId() > 0) {
            z.addCriteria(Zipcode.PROP_TIMEZONEID, criteria.getTimeZoneId());
        }
        if (criteria.getStateCode() != null) {
            z.addLikeClause(Zipcode.PROP_STATE, criteria.getStateCode());
        }
        if (criteria.getCountyName() != null) {
            z.addLikeClause(Zipcode.PROP_COUNTYNAME, criteria.getCountyName());
        }
        if (criteria.getAreaCode() != null) {
            z.addLikeClause(Zipcode.PROP_AREACODE, criteria.getAreaCode());
        }
        if (criteria.getCity() != null) {
            z.addLikeClause(Zipcode.PROP_CITY, criteria.getCity());
        }

        // At least one property is required to be set in the criteria object
        if (!z.isCriteriaAvailable() && !z.isInClauseAvailable()
                && !z.isCustomCriteriaAvailable()
                && !z.isLikeClauseAvailable(OrmBean.LIKE_BEGIN)
                && !z.isLikeClauseAvailable(OrmBean.LIKE_END)
                && !z.isLikeClauseAvailable(OrmBean.LIKE_CONTAINS)) {
            this.msg = "At least one property is required to be set in the zip code search criteria object";
            throw new InvalidZipcodeCriteriaDaoException(this.msg);
        }

        // Setup ordering for result set
        z.addOrderBy(Zipcode.PROP_STATE, Zipcode.ORDERBY_ASCENDING);
        z.addOrderBy(Zipcode.PROP_CITY, Zipcode.ORDERBY_ASCENDING);
        z.addOrderBy(Zipcode.PROP_CITYALIASNAME, Zipcode.ORDERBY_ASCENDING);
        z.addOrderBy(Zipcode.PROP_ZIP, Zipcode.ORDERBY_ASCENDING);

        List<Zipcode> results;
        try {
            results = this.client.retrieveList(z);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            this.msg = "Database access error occurred while fetching one or more zip code records using DTO criteria";
            throw new ZipcodeDaoException(this.msg, e);
        }

        List<ZipcodeDto> list = new ArrayList<ZipcodeDto>();
        for (Zipcode item : results) {
            ZipcodeDto dto = Rmt2AddressBookDtoFactory.getZipCodeInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Fetches a time zone record from the time_zone table by using its primary
     * key.
     * 
     * @param uid
     *            The primary key of the time zone to search.
     * @return an instance of {@link TimeZoneDto} contain time zone related data
     *         or null if no data is found.
     * @throws PostalDaoException
     *             database access errors.
     */
    @Override
    public TimeZoneDto fetchTimezone(int uid) throws PostalDaoException {
        TimeZone tz = new TimeZone();
        tz.addCriteria(Zipcode.PROP_TIMEZONEID, uid);
        try {
            TimeZone results = (TimeZone) this.client.retrieveObject(tz);
            if (results == null) {
                return null;
            }
            TimeZoneDto dto = Rmt2AddressBookDtoFactory
                    .getTimezoneInstance(results);
            return dto;
        } catch (DatabaseException e) {
            this.msg = "Database access error occurred while fetching time zone record by primary key, "
                    + uid;
            throw new ZipcodeDaoException(this.msg, e);
        }
    }

    /**
     * Fetches a list of time zone records from the time_zone table using
     * selection criteria included in <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link TimeZoneDto} containing properties used
     *            to build the query's selection criteria. The following
     *            properties are recognized when building the selection
     *            criteria: <i>timeZoneId</i> and <i>timeZoneDescr</i>.
     * @return a List of {@link TimeZoneDto} containing time zone data or null
     *         if no data is found.
     * @throws PostalDaoException
     *             database access errors.
     */
    @Override
    public List<TimeZoneDto> fetchTimezone(TimeZoneDto criteria)
            throws PostalDaoException {
        // Setup selection criteria
        TimeZone tz = new TimeZone();
        if (criteria != null) {
            if (criteria.getTimeZoneId() > 0) {
                tz.addCriteria(TimeZone.PROP_TIMEZONEID,
                        criteria.getTimeZoneId());
            }
            if (criteria.getTimeZoneDescr() != null) {
                tz.addLikeClause(TimeZone.PROP_DESCR,
                        criteria.getTimeZoneDescr());
            }
        }

        // Setup ordering for result set
        tz.addOrderBy(TimeZone.PROP_DESCR, TimeZone.ORDERBY_ASCENDING);

        List<TimeZone> results;
        try {
            results = this.client.retrieveList(tz);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            this.msg = "Database access error occurred while fetching one or more time zone records using DTO criteria";
            throw new ZipcodeDaoException(this.msg, e);
        }

        List<TimeZoneDto> list = new ArrayList<TimeZoneDto>();
        for (TimeZone item : results) {
            TimeZoneDto dto = Rmt2AddressBookDtoFactory
                    .getTimezoneInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Fetch the location details of an IPv4 formatted IP address from the
     * ip_location table.
     * 
     * @param ip
     *            the IPv4 representation of the IP address
     * @return an instance of {@link IpLocationDto}
     * @throws PostalDaoException
     *             database access error
     */
    @Override
    public IpLocationDto fetchIpInfo(String ip) throws PostalDaoException {
        long ipNum = 0;
        try {
            ipNum = RMT2Utility.convertIp(ip);
            return this.fetchIpInfo(ipNum);
        } catch (Exception e) {
            this.msg = "Database access error occurred while fetching IP Location record by IP Adderss: "
                    + ip;
            throw new IpDaoException(this.msg, e);
        }
    }

    /**
     * Fetch the location details of an IP address that is in numeric form from
     * the ip_location table.
     * <p>
     * First, the the location id is obtained from the ip_block table by
     * dtermining the block of IP address <i>ip</i> fits within. Lastly, the
     * address details are fetched from the ip_lcoation table using the location
     * id obtained from the previous query.
     * <p>
     * The data contained in the ip_block and ip_location tables was obtained
     * from the company, MaxMind. The <i>GeoLite City IPv6 (Beta)</i> is the
     * actual GeoLite Free Downloadable database used to populated the tables
     * which is comprised of two .CSV files.
     * 
     * @see <a href="http://dev.maxmind.com">MaxMind</a>
     * 
     * @param ip
     *            the long value of the IP address
     * @return an instance of {@link IpLocationDto}
     * @throws PostalDaoException
     *             database access error.
     */
    @Override
    public IpLocationDto fetchIpInfo(long ip) throws PostalDaoException {
        IpLocation loc = null;
        try {
            IpBlock blk = new IpBlock();
            blk.addCustomCriteria(ip + ">= ip_start and " + ip + " <= ip_end");
            blk = (IpBlock) this.client.retrieveObject(blk);
            if (blk == null) {
                return null;
            }
            loc = new IpLocation();
            loc.addCriteria(IpLocation.PROP_LOCID, blk.getIpLoc());
            loc = (IpLocation) this.client.retrieveObject(loc);
        } catch (DatabaseException e) {
            this.msg = "Database access error occurred while fetching IP Location record by IP number: "
                    + ip;
            throw new IpDaoException(this.msg, e);
        }

        IpLocationDto dto = Rmt2AddressBookDtoFactory
                .getIpLocationInstance(loc);
        return dto;
    }

}
