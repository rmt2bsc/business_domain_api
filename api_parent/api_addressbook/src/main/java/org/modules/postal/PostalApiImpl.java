package org.modules.postal;

import java.util.List;

import org.dao.postal.PostalDaoException;
import org.dao.postal.PostalDaoFactory;
import org.dao.postal.PostalLocationDao;
import org.dao.postal.RegionCountryDao;
import org.dto.CountryDto;
import org.dto.CountryRegionDto;
import org.dto.IpLocationDto;
import org.dto.RegionDto;
import org.dto.TimeZoneDto;
import org.dto.ZipcodeDto;
import org.dto.adapter.orm.Rmt2AddressBookDtoFactory;

import com.api.foundation.AbstractTransactionApiImpl;

/**
 * An implementation of {@link PostalApi} for managing postal location related
 * transactions.
 * 
 * @author Roy Terrell
 * 
 */
class PostalApiImpl extends AbstractTransactionApiImpl implements PostalApi {

    private PostalDaoFactory factory;

    private String appName;

    /**
     * Default Cnstructor
     */
    public PostalApiImpl() {
        super();
        this.factory = new PostalDaoFactory();
        this.appName = null;
    }

    public PostalApiImpl(String appName) {
        super();
        this.factory = new PostalDaoFactory();
        this.appName = appName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.postal.PostalApi#fetchCountry(int)
     */
    @Override
    public CountryDto getCountry(int uid) throws PostalApiException {
        RegionCountryDao dao = this.factory.createRmt2OrmRegionCountryDao(this.appName);
        try {
            return dao.fetchCountry(uid);
        } catch (PostalDaoException e) {
            this.msg = "Unable to fetch country object by internal unique id, " + uid;
            throw new PostalApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.postal.PostalApi#fetchCountry(org.dto.CountryDto)
     */
    @Override
    public List<CountryDto> getCountry(CountryDto criteria) throws PostalApiException {
        RegionCountryDao dao = this.factory.createRmt2OrmRegionCountryDao(this.appName);
        try {
            return dao.fetchCountry(criteria);
        } catch (PostalDaoException e) {
            this.msg = "Unable to fetch country object by DTO criteria";
            throw new PostalApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
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
        RegionCountryDao dao = this.factory.createRmt2OrmRegionCountryDao(this.appName);
        try {
            return dao.fetchCountryRegion(criteria);
        } catch (PostalDaoException e) {
            this.msg = "Unable to fetch country/region combination object(s) by DTO criteria";
            throw new PostalApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.postal.PostalApi#fetchRegion(int)
     */
    @Override
    public RegionDto getRegion(int uid) throws PostalApiException {
        RegionCountryDao dao = this.factory.createRmt2OrmRegionCountryDao(this.appName);
        try {
            return dao.fetchRegion(uid);
        } catch (PostalDaoException e) {
            this.msg = "Unable to fetch region (state/province) object by internal unique id, " + uid;
            throw new PostalApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.postal.PostalApi#fetchRegion(org.dto.RegionDto)
     */
    @Override
    public List<RegionDto> getRegion(RegionDto criteria) throws PostalApiException {
        RegionCountryDao dao = this.factory.createRmt2OrmRegionCountryDao(this.appName);
        try {
            return dao.fetchRegion(criteria);
        } catch (PostalDaoException e) {
            this.msg = "Unable to fetch region object(s) by DTO criteria";
            throw new PostalApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
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
        ZipcodeDto criteria = Rmt2AddressBookDtoFactory.getZipCodeInstance(null);
        criteria.setZip(zipCode);
        List<ZipcodeDto> results = null;
        try {
            results = dao.fetchZipCode(criteria);
            if (results == null) {
                return null;
            }
        } catch (PostalDaoException e) {
            this.msg = "Unable to fetch zip code object by internal unique id, " + zipCode;
            throw new PostalApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }

        if (results.size() > 1) {
            this.msg = "Results should contain only one zip code record, but instead returned " + results.size();
            throw new PostalApiException(this.msg);
        }
        return results.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.postal.PostalApi#fetchZipCode(org.dto.ZipcodeDto)
     */
    @Override
    public List<ZipcodeDto> getZipCode(ZipcodeDto criteria) throws PostalApiException {
        PostalLocationDao dao = this.factory.createRmt2OrmPostalDao(this.appName);
        try {
            return dao.fetchZipCode(criteria);
        } catch (PostalDaoException e) {
            this.msg = "Unable to fetch zip code object(s) using DTO criteria";
            throw new PostalApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
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
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.postal.PostalApi#fetchTimezone(org.dto.TimeZoneDto)
     */
    @Override
    public List<TimeZoneDto> getTimezone(TimeZoneDto criteria) throws PostalApiException {
        PostalLocationDao dao = this.factory.createRmt2OrmPostalDao(this.appName);
        try {
            return dao.fetchTimezone(criteria);
        } catch (PostalDaoException e) {
            this.msg = "Unable to fetch time zone object(s) using DTO criteria";
            throw new PostalApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
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
        } catch (PostalDaoException e) {
            this.msg = "Unable to fetch IP address object by " + ip;
            throw new PostalApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
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
        } catch (PostalDaoException e) {
            this.msg = "Unable to fetch IP address object by " + ip;
            throw new PostalApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.postal.PostalApi#updateRegion(org.dto.RegionDto)
     */
    @Override
    public int updateRegion(RegionDto obj) throws PostalApiException {
        RegionCountryDao dao = this.factory.createRmt2OrmRegionCountryDao(this.appName);
        try {
            return dao.maintainRegion(obj);
        } catch (PostalDaoException e) {
            this.msg = "Unable to update region (State/Province) object";
            throw new PostalApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.postal.PostalApi#deleteRegion(int)
     */
    @Override
    public int deleteRegion(int stateId) throws PostalApiException {
        RegionCountryDao dao = this.factory.createRmt2OrmRegionCountryDao(this.appName);
        try {
            return dao.deleteRegion(stateId);
        } catch (PostalDaoException e) {
            this.msg = "Unable to delete region (State/Province) object";
            throw new PostalApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.postal.PostalApi#updateCountry(org.dto.CountryDto)
     */
    @Override
    public int updateCountry(CountryDto obj) throws PostalApiException {
        RegionCountryDao dao = this.factory.createRmt2OrmRegionCountryDao(this.appName);
        try {
            return dao.maintainCountry(obj);
        } catch (PostalDaoException e) {
            this.msg = "Unable to update country object";
            throw new PostalApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.postal.PostalApi#deleteCountry(int)
     */
    @Override
    public int deleteCountry(int countryId) throws PostalApiException {
        RegionCountryDao dao = this.factory.createRmt2OrmRegionCountryDao(this.appName);
        try {
            return dao.deleteCountry(countryId);
        } catch (PostalDaoException e) {
            this.msg = "Unable to delete country object";
            throw new PostalApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

}
