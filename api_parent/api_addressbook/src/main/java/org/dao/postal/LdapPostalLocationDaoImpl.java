package org.dao.postal;

import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.SearchControls;

import org.apache.log4j.Logger;
import org.dao.mapping.orm.ldap.LdapIp;
import org.dao.mapping.orm.ldap.LdapTimezone;
import org.dao.mapping.orm.ldap.LdapZipcode;
import org.dto.IpLocationDto;
import org.dto.TimeZoneDto;
import org.dto.ZipcodeDto;
import org.dto.adapter.ldap.LdapAddressBookDtoFactory;

import com.RMT2Constants;
import com.api.ldap.AbstractLdapDaoClient;
import com.api.ldap.operation.LdapSearchOperation;
import com.util.RMT2String;
import com.util.RMT2Utility;

/**
 * A JNDI implementation of the {@link PostalLocationDao} interface which
 * functions to access zip code, time zone, and IP address location data
 * configurations from a LDAP server.
 * <p>
 * All operations belonging to this implementtaion will be anchored at the base
 * DN, <i>c=United States,ou=Country,o=RMT2BSC,dc=rmt2,dc=net</i>,
 * <i>ou=Timezone,ou=General,ou=LookupCodes,o=RMT2BSC,dc=rmt2,dc=net</i>, or
 * <i>ou=Countries,ou=IpLocations,o=RMT2BSC,dc=rmt2,dc=net</i> for zip code,
 * timezone and IP address info queries, respectively.
 * 
 * @author Roy Terrell
 * 
 */
class LdapPostalLocationDaoImpl extends AbstractLdapDaoClient implements
        PostalLocationDao {

    private static final Logger logger = Logger
            .getLogger(LdapPostalLocationDaoImpl.class);

    private static final String BASE_ZIP_DN = "c=United States,ou=Country";

    private static final String BASE_TIMEZONE_DN = "ou=Timezone,ou=General,ou=LookupCodes";

    private static final String BASE_IP_BLOCK_DN = "ou=Blocks,ou=IpInfo";

    private static final String BASE_IP_LOC_DN = "ou=Locations,ou=IpInfo";

    /**
     * Create a LdapPostalLocationDaoImpl object which establishes a connection
     * to the LDAP server.
     */
    public LdapPostalLocationDaoImpl() {
        super();
    }

    /**
     * Fetches a list of zip code entries from the base DN, <i>c=United
     * States,ou=Country,o=RMT2BSC,dc=rmt2,dc=net</i> using data from
     * <i>criteria</i> as selection criteria.
     * <p>
     * The following attributes can be targeted to build either single or
     * multiple predicate criteria using LDAP equality rules: zipId, zip, city,
     * state, and areaCode. It is required that <i>criteria</i> is valid and
     * contain at least one property to be used as criteria. Note that employing
     * the "state" as criteria, at least one other attribute must be present as
     * criteria. This is mandatory due the fact that most LDAP clients can only
     * receive the first 1,000 results when the make an LDAP query to a
     * directory service.
     * 
     * @param criteria
     *            an instance of {@link ZipcodeDto} containing properties used
     *            to build the query's selection criteria. This parameter cannot
     *            be null and is required have at least one property value
     *            available for selection criteria. The following properties
     *            values are used to build selection criteria: idStr, city, zip,
     *            areaCode, and stateCode.
     * @return a List of {@link ZipcodeDto}
     * @throws PostalDaoException
     *             <i>criteria</i> is null or contains zero properties assigned
     *             to be used for selection criteria, the state property is the
     *             only property used to build selection criteria, or general
     *             LDAP access errors.
     */
    @Override
    public List<ZipcodeDto> fetchZipCode(ZipcodeDto criteria)
            throws PostalDaoException {
        if (criteria == null) {
            this.msg = "Client must supply a valid selection criteria object";
            throw new PostalDaoException(this.msg);
        }

        boolean zipInDn = false;
        boolean cityInDn = false;
        StringBuffer rdn = new StringBuffer();

        // Create search operation instance and default its scope to subtree
        LdapSearchOperation op = new LdapSearchOperation();
        op.setScope(SearchControls.SUBTREE_SCOPE);

        // Add any suffixes to the base DN as it pertains to state name, city,
        // and zip id.
        if (criteria.getStateName() != null && criteria.getCity() != null
                && criteria.getIdStr() != null) {
            // Include zipId, city and state as part of the DN
            rdn.append("zipId=");
            rdn.append(criteria.getIdStr());
            rdn.append(",ou=ZipCodes,");
            rdn.append("ou=");
            rdn.append(criteria.getCity());
            rdn.append(",");
            rdn.append("ou=Cities,");
            rdn.append("c=");
            rdn.append(criteria.getStateName());
            rdn.append(",");
            zipInDn = cityInDn = true;
            op.setScope(SearchControls.OBJECT_SCOPE);
            op.getSearchFilterArgs().put("objectClass", "*");
        }
        else if (criteria.getStateName() != null && criteria.getCity() != null) {
            // Include city and state as part of the DN
            rdn.append("ou=");
            rdn.append(criteria.getCity());
            rdn.append(",");
            rdn.append("ou=Cities,");
            rdn.append("c=");
            rdn.append(criteria.getStateName());
            rdn.append(",");
            cityInDn = true;
        }
        else if (criteria.getStateName() != null) {
            // Include state name as part of the DN
            rdn.append("c=");
            rdn.append(criteria.getStateName());
            rdn.append(",");
        }

        // Begin to build the search filter pending any expressions are
        // available
        if (criteria.getIdStr() != null && !zipInDn) {
            // Include zipId as part of the filter expression only if its value
            // is not part of the DN
            op.getSearchFilterArgs().put("zipId", criteria.getIdStr());
        }

        if (criteria.getCity() != null && !cityInDn) {
            // Include city as part of the filter only if its value is not part
            // of the DN
            op.getSearchFilterArgs().put("city", criteria.getCity());
        }

        if (criteria.getZip() != 0) {
            // Include zip code as part of the filter
            op.getSearchFilterArgs().put("zip",
                    String.valueOf(criteria.getZip()));
        }

        if (criteria.getAreaCode() != null) {
            // Include area code as part of the filter
            op.getSearchFilterArgs().put("areaCode", criteria.getAreaCode());
        }
        // Only apply state selection criteria as part of the filter expression
        // when other
        // arguments have been applied. This will facilitate recognizing the
        // LDAP server's
        // maximum result set siz limitation which is usually 1000 as well as
        // better performance
        if (criteria.getStateCode() != null) {
            if (op.getSearchFilterArgs().size() == 0 && rdn.length() == 0) {
                this.msg = "Cannot query zip code data based on State selection criteria alone";
                throw new PostalDaoException(this.msg);
            }
            op.getSearchFilterArgs().put("state", criteria.getStateCode());
        }

        if (op.getSearchFilterArgs().size() == 0 && rdn.length() == 0) {
            this.msg = "Zip code fetch operation requires at least one selection criteria predicate";
            throw new PostalDaoException(this.msg);
        }

        // Start search at the web service base DN.
        List<LdapZipcode> zipList = null;
        try {
            // Set Base DN in which more RDN suffixes could be appended.
            String dn = null;
            if (rdn.length() > 0) {
                dn = rdn + LdapPostalLocationDaoImpl.BASE_ZIP_DN;
            }
            else {
                dn = LdapPostalLocationDaoImpl.BASE_ZIP_DN;
            }
            op.setDn(dn);
            op.setUseSearchFilterExpression(true);
            logger.info("LDAP base DN to be applied for zip code search operation: "
                    + dn);
            logger.info("LDAP filter to be applied for zip code search operation: "
                    + op.getSearchFilter());
            System.out
                    .println("LDAP base DN to be applied for zip code search operation: "
                            + dn);
            System.out
                    .println("LDAP filter to be applied for zip code search operation: "
                            + op.getSearchFilter());
            op.setMappingBeanName("org.dao.mapping.orm.ldap.LdapZipcode");
            Object results[] = this.ldap.retrieve(op);
            zipList = this.ldap.extractLdapResults(results);
        } catch (Exception e) {
            throw new PostalDaoException(e);
        }

        if (zipList == null) {
            return null;
        }

        List<ZipcodeDto> list = new ArrayList<ZipcodeDto>();
        for (LdapZipcode item : zipList) {
            ZipcodeDto dto = LdapAddressBookDtoFactory.getZipCodeInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Fetches a time zone entry at base DN,
     * <i>ou=Timezone,ou=General,ou=LookupCodes,o=RMT2BSC,dc=rmt2,dc=net</i> by
     * using its internal unique id.
     * 
     * @param uid
     *            The primary key of the time zone to search.
     * @return an instance of {@link TimeZoneDto}
     * @throws PostalDaoException
     */
    @Override
    public TimeZoneDto fetchTimezone(int uid) throws PostalDaoException {
        List<LdapTimezone> tzList = null;
        try {
            LdapSearchOperation op = new LdapSearchOperation();
            op.setDn(LdapPostalLocationDaoImpl.BASE_TIMEZONE_DN);
            op.getMatchAttributes().put("uid", String.valueOf(uid));
            op.setMappingBeanName("org.dao.mapping.orm.ldap.LdapTimezone");
            Object results[] = this.ldap.retrieve(op);
            tzList = this.ldap.extractLdapResults(results);
            if (tzList == null) {
                return null;
            }
        } catch (Exception e) {
            throw new PostalDaoException(e);
        }

        LdapTimezone obj = null;
        if (tzList.size() > 1) {
            this.msg = "Too many time zone objects were returned from LDAP server for a single time zone query";
            throw new PostalDaoException();
        }
        else {
            obj = tzList.get(0);
        }
        TimeZoneDto dto = LdapAddressBookDtoFactory.getTimezoneInstance(obj);
        return dto;
    }

    /**
     * Fetches a list of time zone entries at base DN,
     * <i>ou=Timezone,ou=General,ou=LookupCodes,o=RMT2BSC,dc=rmt2,dc=net</i>
     * using data included in <i>criteria</i> as selection criteria.
     * 
     * @param criteria
     *            an instance of {@link TimeZoneDto} containing properties used
     *            to build the query's selection criteria. The following
     *            properties are recognized when building the selection
     *            criteria: <i>group id</i> and <i>group description</i>.
     * @return a List of {@link TimeZoneDto}
     * @throws PostalDaoException
     */
    @Override
    public List<TimeZoneDto> fetchTimezone(TimeZoneDto criteria)
            throws PostalDaoException {
        List<LdapTimezone> ldapResults = null;
        try {
            LdapSearchOperation op = new LdapSearchOperation();
            op.setDn(LdapPostalLocationDaoImpl.BASE_TIMEZONE_DN);
            if (criteria != null) {
                if (criteria.getTimeZoneId() > 0) {
                    op.getMatchAttributes().put("uid",
                            String.valueOf(criteria.getTimeZoneId()));
                }
                if (criteria.getTimeZoneDescr() != null) {
                    op.getMatchAttributes().put("description",
                            String.valueOf(criteria.getTimeZoneDescr()));
                }
            }

            op.setMappingBeanName("org.dao.mapping.orm.ldap.LdapTimezone");
            Object results[] = this.ldap.retrieve(op);
            ldapResults = this.ldap.extractLdapResults(results);
            if (ldapResults == null) {
                return null;
            }
        } catch (Exception e) {
            throw new PostalDaoException(e);
        }

        List<TimeZoneDto> tzList = new ArrayList<TimeZoneDto>();
        for (LdapTimezone item : ldapResults) {
            TimeZoneDto dto = LdapAddressBookDtoFactory
                    .getTimezoneInstance(item);
            tzList.add(dto);
        }
        return tzList;
    }

    /**
     * Fetch the location details of an IPv4 formatted IP address from the base
     * DN, <i>ou=Countries,ou=IpLocations,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * <p>
     * The IP address, <i>ip</i>, is converted to a long value which is used as
     * an input parameter for the subsequent invocation of
     * {@link LdapPostalLocationDaoImpl#fetchIpInfo(long) fetchIpInfo(long)}.
     * 
     * @param ip
     *            the IPv4 representation of the IP address
     * @return an instance of {@link IpLocationDto}
     * @throws PostalDaoException
     *             LDAP server access error
     */
    @Override
    public IpLocationDto fetchIpInfo(String ip) throws PostalDaoException {
        long ipNum = 0;
        try {
            ipNum = RMT2Utility.convertIp(ip);
            return this.fetchIpInfo(ipNum);
        } catch (Exception e) {
            this.msg = "LDAP server access error occurred while fetching IP Location record by IP Adderss: "
                    + ip;
            throw new IpDaoException(this.msg, e);
        }
    }

    /**
     * Fetch the location details of an IP address that is in numeric form from
     * the base DN, <i>ou=Countries,ou=IpLocations,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * <p>
     * The search is performed using <i>ip</i> in a filter at subtree scope in
     * hopes of finding an entry at DN,
     * <i>uid=xxxx-xxxx,zip=xxxxx,areaCode=xxx,city
     * =xxxxxxx,ipRegion=xx,ipCountryCode
     * =xx,ou=Countries,ou=IpLocations,o=RMT2BSC,dc=rmt2,dc=net</i>, where the
     * ip long fits in between a the ipFrom and ipTo range.
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
     *             LDAP server access error.
     */
    @Override
    public IpLocationDto fetchIpInfo(long ip) throws PostalDaoException {
        int ipLocId = this.fetchIpBlockRangeId(ip);
        if (ipLocId <= 0) {
            return null;
        }
        return this.fetchIpLocation(ipLocId);
    }

    private int fetchIpBlockRangeId(long ip) throws PostalDaoException {
        List<LdapIp> ipList = null;
        String filter = "(&(|(ipFrom<=xxx)(ipFrom=xxx))(|(ipTo>=xxx)(ipTo=xxx)))";
        filter = RMT2String.replaceAll(filter, String.valueOf(ip), "xxx");
        try {
            LdapSearchOperation op = new LdapSearchOperation();
            op.setDn(LdapPostalLocationDaoImpl.BASE_IP_BLOCK_DN);
            op.setScope(SearchControls.ONELEVEL_SCOPE);
            op.setSearchFilter(filter);
            logger.info("LDAP filter to be applied for IP Address info search operation: "
                    + filter);
            op.setMappingBeanName("org.dao.mapping.orm.ldap.LdapIp");
            Object results[] = this.ldap.retrieve(op);
            ipList = this.ldap.extractLdapResults(results);
            if (ipList == null) {
                return 0;
            }
        } catch (Exception e) {
            throw new PostalDaoException(e);
        }

        LdapIp obj = null;
        if (ipList.size() > 1) {
            this.msg = "Too many time IP Address objects were returned from the LDAP server for a single Ip Address query";
            throw new PostalDaoException();
        }
        else {
            obj = ipList.get(0);
        }
        IpLocationDto dto = LdapAddressBookDtoFactory.getIpAddressInstance(obj);
        return dto.getIpRangeId();
    }

    private IpLocationDto fetchIpLocation(int locId) throws PostalDaoException {
        List<LdapIp> ipList = null;
        try {
            LdapSearchOperation op = new LdapSearchOperation();
            String dn = "ipLocId=" + locId
                    + LdapPostalLocationDaoImpl.BASE_IP_LOC_DN;
            op.setDn(dn);
            op.setScope(SearchControls.OBJECT_SCOPE);
            logger.info("LDAP base DN to be applied for IP Address Location info search operation: "
                    + dn);
            op.setMappingBeanName("org.dao.mapping.orm.ldap.LdapIp");
            Object results[] = this.ldap.retrieve(op);
            ipList = this.ldap.extractLdapResults(results);
            if (ipList == null) {
                return null;
            }
        } catch (Exception e) {
            throw new PostalDaoException(e);
        }

        LdapIp obj = null;
        if (ipList.size() > 1) {
            this.msg = "Too many time IP Address objects were returned from the LDAP server for a single Ip Address Location query";
            throw new PostalDaoException();
        }
        else {
            obj = ipList.get(0);
        }
        IpLocationDto dto = LdapAddressBookDtoFactory.getIpAddressInstance(obj);
        return dto;
    }

    /**
     * Not supported
     */
    @Override
    public ZipcodeDto fetchZipCode(int uid) throws PostalDaoException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }
}
