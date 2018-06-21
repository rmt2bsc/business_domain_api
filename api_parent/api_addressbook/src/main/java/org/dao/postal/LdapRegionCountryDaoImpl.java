package org.dao.postal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.naming.directory.SearchControls;

import org.apache.log4j.Logger;
import org.dao.mapping.orm.ldap.LdapCountry;
import org.dao.mapping.orm.ldap.LdapRegion;
import org.dto.CountryDto;
import org.dto.CountryRegionDto;
import org.dto.RegionDto;
import org.dto.adapter.ldap.LdapAddressBookDtoFactory;

import com.RMT2Constants;
import com.api.ldap.AbstractLdapDaoClient;
import com.api.ldap.operation.LdapSearchOperation;
import com.api.util.RMT2String;

/**
 * A JNDI implementation of {@link RegionCountryDao} interface for accessing
 * state/province and country location information as it pertains to the base
 * DN, <i>ou=Country,o=RMT2BSC,dc=rmt2,dc=net</i>, from a LDAP server.
 * <p>
 * This implemtation is for read purposes only.
 * 
 * @author Roy Terrell
 * 
 */
class LdapRegionCountryDaoImpl extends AbstractLdapDaoClient implements RegionCountryDao {

    private static final Logger logger = Logger.getLogger(LdapRegionCountryDaoImpl.class);

    /**
     * Base DN for country LDAP node
     */
    protected static final String BASE_DN_COUNTRY = "ou=Country";

    /**
     * Base DN for a particular country.
     * <p>
     * The country name RDN is represented by the character '?' which serves as
     * merely a place holder to be manipulated at runtime.
     */
    protected static final String BASE_DN_REGION = "c=?,ou=Country";

    /**
     * Creates a LdapRegionCountryDaoImpl and establishes the a connection the
     * the LDAP data source.
     */
    public LdapRegionCountryDaoImpl() {
        super();
    }

    /**
     * Fetches a single country record from the base DN,
     * <i>ou=Country,o=RMT2BSC,dc=rmt2,dc=net</i>, by its primary key.
     * 
     * @param countryId
     *            the internal unique id of the country which is matched against
     *            the LDAP attribute, countryId.
     * @return an instance of {@link CountryDto} containing state data or null
     *         if no data is found.
     * @throws RegionCountryDaoException
     */
    @Override
    public CountryDto fetchCountry(int countryId) throws RegionCountryDaoException {
        List<LdapCountry> list = null;
        try {
            LdapSearchOperation op = new LdapSearchOperation();
            op.setDn(LdapRegionCountryDaoImpl.BASE_DN_COUNTRY);
            op.getMatchAttributes().put("countryId", String.valueOf(countryId));
            op.setMappingBeanName("org.dao.mapping.orm.ldap.LdapCountry");
            Object results[] = this.ldap.retrieve(op);
            list = this.ldap.extractLdapResults(results);
        } catch (Exception e) {
            throw new RegionCountryDaoException(e);
        }

        LdapCountry obj = null;
        if (list.size() > 1) {
            this.msg = "Too many country objects were returned from LDAP server for a single country query";
            throw new RegionCountryDaoException();
        }
        else {
            obj = list.get(0);
        }
        CountryDto dto = LdapAddressBookDtoFactory.getCountryInstance(obj);
        return dto;
    }

    /**
     * Fetches one or more country records from the base DN,
     * <i>ou=Country,o=RMT2BSC,dc=rmt2,dc=net</i>, using various assigned
     * properties set in <i>criteria</i> as selection criteria.
     * 
     * @param criteria
     *            an instance of {@link CountryDto} containing the properties
     *            that are to be applied to the query as selection criteria or
     *            <i>null</i> to fetch all records. The LDAP attributes,
     *            <i>c</i>, <i>cn</i> and <i>countryId</i>, are eligible to be
     *            used as selection criteria and are mapped to the
     *            <i>criteria</i> properties identified as <i>countryName</i>,
     *            <i>countryCode</i>, and <i>countryId</i>, respectively.
     * 
     * @return a List of {@link CountryDto} or null if no data is found.
     * @throws RegionCountryDaoException
     */
    @Override
    public List<CountryDto> fetchCountry(CountryDto criteria) throws RegionCountryDaoException {
        LdapSearchOperation op = new LdapSearchOperation();
        if (criteria != null) {
            if (criteria.getCountryId() > 0) {
                op.getSearchFilterArgs().put("countryId", String.valueOf(criteria.getCountryId()));
            }
            else {
                if (criteria.getCountryName() != null) {
                    op.getSearchFilterArgs().put("c", criteria.getCountryName() + "*");
                }
                if (criteria.getCountryCode() != null) {
                    op.getSearchFilterArgs().put("cn", criteria.getCountryCode() + "*");
                }
            }
        }

        // Start search at the web service base DN.
        List<LdapCountry> countryList = null;
        try {
            op.setDn(LdapRegionCountryDaoImpl.BASE_DN_COUNTRY);
            op.setScope(SearchControls.ONELEVEL_SCOPE);
            op.setUseSearchFilterExpression(true);
            logger.info("LDAP filter to be applied for country search operation: " + op.getSearchFilter());
            op.setMappingBeanName("org.dao.mapping.orm.ldap.LdapCountry");
            Object results[] = this.ldap.retrieve(op);
            countryList = this.ldap.extractLdapResults(results);
        } catch (Exception e) {
            throw new RegionCountryDaoException(e);
        }

        if (countryList == null) {
            return null;
        }

        List<CountryDto> list = new ArrayList<CountryDto>();
        for (LdapCountry item : countryList) {
            CountryDto dto = LdapAddressBookDtoFactory.getCountryInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Fetches a single region (state/province) belonging to the country, United
     * States, by its provinceId at base DN, <i>c=United
     * States,ou=Country,o=RMT2BSC,dc=rmt2,dc=net</i>.
     * <p>
     * This method searchs for the region by applying a LDAP filter to provide
     * selection criteria
     * 
     * @param regionId
     *            the internal unique id of the region to build the search
     *            filter
     * @return an instance of {@link RegionDto} or null if no data is found.
     * @throws RegionCountryDaoException
     */
    @Override
    public RegionDto fetchRegion(int regionId) throws RegionCountryDaoException {
        List<LdapRegion> list = null;
        try {
            LdapSearchOperation op = new LdapSearchOperation();
            String dn = RMT2String.replace(LdapRegionCountryDaoImpl.BASE_DN_REGION, "United States", "?");
            op.setDn(dn);
            op.setScope(SearchControls.ONELEVEL_SCOPE);
            op.getSearchFilterArgs().put("provinceId", String.valueOf(regionId));
            op.setUseSearchFilterExpression(true);
            op.setMappingBeanName("org.dao.mapping.orm.ldap.LdapRegion");
            Object results[] = this.ldap.retrieve(op);
            list = this.ldap.extractLdapResults(results);
        } catch (Exception e) {
            throw new PostalDaoException(e);
        }

        LdapRegion obj = null;
        if (list.size() > 1) {
            this.msg = "Too many region/state/province objects were returned from LDAP server for a single region/state/province query";
            throw new PostalDaoException();
        }
        else {
            obj = list.get(0);
        }
        RegionDto dto = LdapAddressBookDtoFactory.getRegionInstance(obj);
        return dto;
    }

    /**
     * Fetches one or more region (state/province) objects assoicated with
     * country selected by the user.
     * <p>
     * The query will start at base DN,
     * <i>c=countryName,ou=Country,o=RMT2BSC,dc=rmt2,dc=net</i>. The base DN
     * attribute, <i>countryName</i>, will be supplied by the countryName
     * property in <i>criteria</i> and is required. In conjunction to the
     * countryName attribtue, <i>stateId</i>, <i>stateCode</i>, and
     * <i>stateName</i> can optionally be supplied in <i>criteria</i> to build
     * the LDAP search filter.
     * 
     * @param criteria
     *            Required to be a valid instance of {@link RegionDto} and
     *            cannot be null. Contains the properties <i>countryName</i>,
     *            <i>stateId</i>, <i>stateCode</i>, and <i>stateName</i> that
     *            are to be applied to the query as selection criteria. The
     *            property, <i>countryName</i> is required.
     * @return a List of {@link RegionDto} or null if no data is found.
     * @throws RegionCountryDaoException
     *             <i>criteria</i> is null, the country name property of
     *             <i>criteria</i> is null, or genreal LDAP access error.
     */
    @Override
    public List<RegionDto> fetchRegion(RegionDto criteria) throws RegionCountryDaoException {
        if (criteria == null) {
            throw new RegionCountryDaoException("Selection criteria must exist as an instance of RegionDto");
        }
        if (criteria.getCountryName() == null) {
            throw new RegionCountryDaoException("Selection criteria requires country name");
        }
        String dn = RMT2String.replace(LdapRegionCountryDaoImpl.BASE_DN_REGION, criteria.getCountryName(), "?");
        LdapSearchOperation op = new LdapSearchOperation();
        if (criteria.getStateId() > 0) {
            op.getSearchFilterArgs().put("provinceId", String.valueOf(criteria.getStateId()));
        }
        else {
            if (criteria.getStateName() != null) {
                op.getSearchFilterArgs().put("c", criteria.getStateName() + "*");
            }
            if (criteria.getStateCode() != null) {
                op.getSearchFilterArgs().put("cn", criteria.getStateCode() + "*");
            }
        }

        // Start search at the web service base DN.
        List<LdapRegion> regionList = null;
        try {
            op.setDn(dn);
            op.setScope(SearchControls.ONELEVEL_SCOPE);
            logger.info("LDAP base DN to be applied for region/state/province search operation: " + dn);
            logger.info(
                    "LDAP filter to be applied for region/state/province search operation: " + op.getSearchFilter());
            op.setMappingBeanName("org.dao.mapping.orm.ldap.LdapRegion");
            op.setUseSearchFilterExpression(true);
            Object results[] = this.ldap.retrieve(op);
            regionList = this.ldap.extractLdapResults(results);
        } catch (Exception e) {
            throw new PostalDaoException(e);
        }

        if (regionList == null) {
            return null;
        }

        List<RegionDto> list = new ArrayList<RegionDto>();
        for (LdapRegion item : regionList) {
            RegionDto dto = LdapAddressBookDtoFactory.getRegionInstance(item);
            list.add(dto);
        }

        // Order list by region name
        if (list.size() > 1) {
            RegionDtoComparator comp = new RegionDtoComparator();
            Collections.sort(list, comp);
            comp = null;
        }
        return list;
    }

    /**
     * Not supported
     */
    @Override
    public int maintainRegion(RegionDto obj) throws RegionCountryDaoException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not supported
     */
    @Override
    public int deleteRegion(int stateId) throws RegionCountryDaoException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not supported
     */
    @Override
    public int maintainCountry(CountryDto obj) throws RegionCountryDaoException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not supported
     */
    @Override
    public int deleteCountry(int countryId) throws RegionCountryDaoException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not supported
     */
    @Override
    public List<CountryRegionDto> fetchCountryRegion(CountryRegionDto criteria) throws RegionCountryDaoException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }
}
