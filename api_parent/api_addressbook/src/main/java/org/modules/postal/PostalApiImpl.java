package org.modules.postal;

import java.util.List;

import org.dao.postal.IpDaoException;
import org.dao.postal.PostalDaoException;
import org.dao.postal.PostalDaoFactory;
import org.dao.postal.PostalLocationDao;
import org.dao.postal.RegionCountryDao;
import org.dao.postal.RegionCountryDaoException;
import org.dao.postal.ZipcodeDaoException;
import org.dto.CountryDto;
import org.dto.CountryRegionDto;
import org.dto.IpLocationDto;
import org.dto.RegionDto;
import org.dto.TimeZoneDto;
import org.dto.ZipcodeDto;

import com.api.foundation.AbstractTransactionApiImpl;
import com.api.util.RMT2String2;

/**
 * An implementation of {@link PostalApi} for managing postal location related
 * transactions.
 * 
 * @author Roy Terrell
 * 
 */
class PostalApiImpl extends AbstractTransactionApiImpl implements PostalApi {

    private PostalDaoFactory factory;
    private RegionCountryDao dao;
    private String appName;

    /**
     * Creates a PostalApiImpl object in which the configuration is identified
     * by the name of a given application.
     * 
     * @param appName
     */
    public PostalApiImpl(String appName) {
        super();
        this.factory = new PostalDaoFactory();
        dao = this.factory.createRmt2OrmRegionCountryDao(appName);
        this.setSharedDao(dao);
        dao.setDaoUser(this.apiUser);
        this.appName = appName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.postal.PostalApi#fetchCountry(int)
     */
    @Override
    public CountryDto getCountry(int uid) throws PostalApiException {
        try {
            return dao.fetchCountry(uid);
        } catch (PostalDaoException e) {
            this.msg = "Unable to fetch country object by internal unique id, " + uid;
            throw new PostalApiException(this.msg, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.postal.PostalApi#fetchCountry(org.dto.CountryDto)
     */
    @Override
    public List<CountryDto> getCountry(CountryDto criteria) throws PostalApiException {
        if (criteria == null) {
            this.msg = "Country criteia instance cannot be null for fetch operations";
            throw new PostalApiException(this.msg);
        }
        try {
            return dao.fetchCountry(criteria);
        } catch (RegionCountryDaoException e) {
            this.msg = "Unable to fetch country object by DTO criteria";
            throw new PostalApiException(this.msg, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.postal.PostalApi#fetchCountryRegion(org.dto.CountryRegionDto)
     */
    @Override
    public List<CountryRegionDto> getCountryRegion(CountryRegionDto criteria) throws PostalApiException {
        if (criteria == null) {
            this.msg = "Country/Region criteia instance cannot be null for fetch operations";
            throw new PostalApiException(this.msg);
        }
        try {
            return dao.fetchCountryRegion(criteria);
        } catch (RegionCountryDaoException e) {
            this.msg = "Unable to fetch country/region combination object(s) by DTO criteria";
            throw new PostalApiException(this.msg, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.postal.PostalApi#fetchRegion(int)
     */
    @Override
    public RegionDto getRegion(int uid) throws PostalApiException {
        try {
            return dao.fetchRegion(uid);
        } catch (RegionCountryDaoException e) {
            this.msg = "Unable to fetch region (state/province) object by internal unique id, " + uid;
            throw new PostalApiException(this.msg, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.postal.PostalApi#fetchRegion(org.dto.RegionDto)
     */
    @Override
    public List<RegionDto> getRegion(RegionDto criteria) throws PostalApiException {
        if (criteria == null) {
            this.msg = "Region criteia instance cannot be null for fetch operations";
            throw new PostalApiException(this.msg);
        }
        try {
            return dao.fetchRegion(criteria);
        } catch (RegionCountryDaoException e) {
            this.msg = "Unable to fetch region object(s) by DTO criteria";
            throw new PostalApiException(this.msg, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.postal.PostalApi#fetchZipCode(int)
     */
    @Override
    public ZipcodeDto getZipCode(int zipCode) throws PostalApiException {
        if (zipCode == 0) {
            return null;
        }
        PostalLocationDao dao = this.factory.createRmt2OrmPostalDao(this.appName);
        ZipcodeDto results = null;
        try {
            results = dao.fetchZipCode(zipCode);
            if (results == null) {
                return null;
            }
        } catch (ZipcodeDaoException e) {
            this.msg = "Unable to fetch zip code object by internal unique id, " + zipCode;
            throw new PostalApiException(this.msg, e);
        }
 finally {
            if (dao != null) {
                dao.close();
            }
        }
        return results;
    }

    /**
     * Obtains a list of zip code entries based on selection criteria included
     * in <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link ZipcodeDto} containing properties used
     *            to build the query's selection criteria.
     *            The following conditions must be met for this parameter for query opertations:<br>
     *            <ol>
     *              <li>Must not be null.</li>
     *              <li>At least one of the following properties must its respective condtion(s):</li>
     *                 <ul>
     *                   <li><i>id</i> must greater than zero.</li>
     *                   <li><i>zip</i> must be greater than zero.</li>
     *                   <li><i>timeZoneId</i> must be greater than zero</li>
     *                   <li><i>stateCode</i> must not be null.</li>
     *                   <li><i>countyName</i> must not be null.</li>
     *                   <li><i>areaCode</i> must not be null.</li>
     *                   <li><i>city</i> must not be null.</li>
     *                 </ul>
     *              <li></li>
     *              <li></li>
     *              
     *            </ol>
     * @return a List of {@link ZipcodeDto} or null for no data set found.
     * @throws PostalApiException 
     *              <i>criteria</i> is null or any of the required criteria properties have not been set.
     */
    @Override
    public List<ZipcodeDto> getZipCode(ZipcodeDto criteria) throws PostalApiException {
        if (criteria == null) {
            this.msg = "Zip code criteia instance cannot be null for fetch operations";
            throw new PostalApiException(this.msg);
        }
        if (!this.isCriteriaAvailable(criteria)) {
            this.msg = "At least one selection criteria property is required";
            throw new PostalApiException(this.msg);
        }
        PostalLocationDao dao = this.factory.createRmt2OrmPostalDao(this.appName);
        try {
            return dao.fetchZipCode(criteria);
        } catch (ZipcodeDaoException e) {
            this.msg = "Unable to fetch zip code object(s) using DTO criteria";
            throw new PostalApiException(this.msg, e);
        }
    }
    
    private boolean isCriteriaAvailable(ZipcodeDto criteria) {
        int criteriaCount = 0;
        if (criteria.getId() > 0) {
            criteriaCount++;
        }
        if (criteria.getZip() > 0) {
            criteriaCount++;
        }
        if (criteria.getTimeZoneId() > 0) {
            criteriaCount++;
        }
        if (criteria.getStateCode() != null) {
            criteriaCount++;
        }
        if (criteria.getCountyName() != null) {
            criteriaCount++;
        }
        if (criteria.getAreaCode() != null) {
            criteriaCount++;
        }
        if (criteria.getCity() != null) {
            criteriaCount++;
        }
        return (criteriaCount > 0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.postal.PostalApi#fetchTimezone(int)
     */
    @Override
    public TimeZoneDto getTimezone(int uid) throws PostalApiException {
        PostalLocationDao dao = this.factory.createRmt2OrmPostalDao(this.appName);
        try {
            return dao.fetchTimezone(uid);
        } catch (PostalDaoException e) {
            this.msg = "Unable to fetch time zone object by internal unique id, " + uid;
            throw new PostalApiException(this.msg, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.postal.PostalApi#fetchTimezone(org.dto.TimeZoneDto)
     */
    @Override
    public List<TimeZoneDto> getTimezone(TimeZoneDto criteria) throws PostalApiException {
        if (criteria == null) {
            this.msg = "Timezone criteia instance cannot be null for fetch operations";
            throw new PostalApiException(this.msg);
        }
        PostalLocationDao dao = this.factory.createRmt2OrmPostalDao(this.appName);
        try {
            return dao.fetchTimezone(criteria);
        } catch (ZipcodeDaoException e) {
            this.msg = "Unable to fetch time zone object(s) using DTO criteria";
            throw new PostalApiException(this.msg, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.postal.PostalApi#fetchIpInfo(java.lang.String)
     */
    @Override
    public IpLocationDto getIpInfo(String ip) throws PostalApiException {
        PostalLocationDao dao = this.factory.createRmt2OrmPostalDao(this.appName);
        try {
            return dao.fetchIpInfo(ip);
        } catch (IpDaoException e) {
            this.msg = "Unable to fetch IP address object by " + ip;
            throw new PostalApiException(this.msg, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.postal.PostalApi#fetchIpInfo(long)
     */
    @Override
    public IpLocationDto getIpInfo(long ip) throws PostalApiException {
        PostalLocationDao dao = this.factory.createRmt2OrmPostalDao(this.appName);
        try {
            return dao.fetchIpInfo(ip);
        } catch (IpDaoException e) {
            this.msg = "Unable to fetch IP address object by " + ip;
            throw new PostalApiException(this.msg, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.postal.PostalApi#updateRegion(org.dto.RegionDto)
     */
    @Override
    public int updateRegion(RegionDto obj) throws PostalApiException {
        this.validateRegion(obj);
        try {
            return dao.maintainRegion(obj);
        } catch (RegionCountryDaoException e) {
            this.msg = "Unable to update region (State/Province) object";
            throw new PostalApiException(this.msg, e);
        }
    }

    /**
     * Validates a RegionDto object for insert and update opertaions.
     * 
     * @param obj
     *            an instance of {@link RegionDto} that is to be validated.
     * @throws PostalApiException
     *             if <i>obj</i> is null or <i>state code</i>, <i>state name</i>
     *             and/or <i>country id</i> have not been assinged a value.
     */
    protected void validateRegion(RegionDto obj) throws PostalApiException {
        if (obj == null) {
            this.msg = "Region instance cannot be null for insert/update operations";
            throw new PostalApiException(this.msg);
        }

        if (obj.getStateCode() == null || obj.getStateCode().equals("")) {
            this.msg = "Region (State/Province) code is required";
            throw new PostalApiException(this.msg);
        }
        if (obj.getStateName() == null || obj.getStateName().equals("")) {
            this.msg = "Region (State/Province) name is required";
            throw new PostalApiException(this.msg);
        }
        if (obj.getCountryId() <= 0) {
            this.msg = "Country code is required";
            throw new PostalApiException(this.msg);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.postal.PostalApi#deleteRegion(int)
     */
    @Override
    public int deleteRegion(int stateId) throws PostalApiException {
        if (stateId <= 0) {
            this.msg = "State id is invalid...must be greater than zero";
            throw new PostalApiException(this.msg);
        }
        try {
            return dao.deleteRegion(stateId);
        } catch (RegionCountryDaoException e) {
            this.msg = "Unable to delete region (State/Province) object";
            throw new PostalApiException(this.msg, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.postal.PostalApi#updateCountry(org.dto.CountryDto)
     */
    @Override
    public int updateCountry(CountryDto obj) throws PostalApiException {
        this.validate(obj);
        try {
            return dao.maintainCountry(obj);
        } catch (RegionCountryDaoException e) {
            this.msg = "Unable to update country object";
            throw new PostalApiException(this.msg, e);
        }
    }

    /**
     * Validates a CountryDto object for insert and update operations.
     * 
     * @param obj
     *            an instance of {@link CountryDto} that is to be validated.
     * @throws PostalApiException
     *             if <i>obj</i> is null or <i>country name</i> does not been
     *             assinged a value.
     */
    protected void validate(CountryDto obj) throws PostalApiException {
        if (obj == null) {
            this.msg = "Country instance cannot be null for insert/update operations";
            throw new PostalApiException(this.msg);
        }
        if (RMT2String2.isEmpty(obj.getCountryName())) {
            this.msg = "Country name is required";
            throw new PostalApiException(this.msg);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.postal.PostalApi#deleteCountry(int)
     */
    @Override
    public int deleteCountry(int countryId) throws PostalApiException {
        if (countryId <= 0) {
            this.msg = "Country id is invalid...must be greater than zero";
            throw new PostalApiException(this.msg);
        }
        try {
            return dao.deleteCountry(countryId);
        } catch (RegionCountryDaoException e) {
            this.msg = "Unable to delete country object";
            throw new PostalApiException(this.msg, e);
        }
    }

}
