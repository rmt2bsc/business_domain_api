package org.dao.postal;

import java.util.List;

import org.dto.CountryDto;
import org.dto.RegionDto;
import org.dto.CountryRegionDto;

import com.api.persistence.DaoClient;

/**
 * Contract for accessing postal location information as it pertains to
 * state/province and country data.
 * 
 * @author rterrell
 * 
 */
public interface RegionCountryDao extends DaoClient {

    /**
     * Fetches a single country object by its internal unique id.
     * 
     * @param uid
     *            thei internal unique id of the country
     * @return an instance of {@link CountryDto} or null if no data is found.
     * @throws RegionCountryDaoException
     */
    CountryDto fetchCountry(int uid) throws RegionCountryDaoException;

    /**
     * Fetches one or more country objects using various attributes of
     * <i>criteria</i> as selection criteria.
     * 
     * @param criteria
     *            an instance of {@link CountryDto} containing the properties
     *            that are to be applied to the query as selection criteria.
     * @return a List of {@link CountryDto} or null if no data is found.
     * @throws RegionCountryDaoException
     */
    List<CountryDto> fetchCountry(CountryDto criteria) throws RegionCountryDaoException;

    /**
     * Fetches one or more objects containign both country and region data using
     * various attributes of <i>criteria</i> as selection criteria.
     * 
     * @param criteria
     *            an instance of {@link CountryRegionDto} containing the
     *            properties that are to be applied to the query as selection
     *            criteria.
     * @return a List of {@link CountryRegionDto} or null if no data is found.
     * @throws RegionCountryDaoException
     */
    List<CountryRegionDto> fetchCountryRegion(CountryRegionDto criteria) throws RegionCountryDaoException;

    /**
     * Fetches a single region (state/province) object by its internal unique
     * id.
     * 
     * @param uid
     *            thei internal unique id of the region
     * @return an instance of {@link RegionDto} or null if no data is found.
     * @throws RegionCountryDaoException
     */
    RegionDto fetchRegion(int uid) throws RegionCountryDaoException;

    /**
     * Fetches one or more region (state/province) objects using various
     * attributes of <i>criteria</i> as selection criteria.
     * 
     * @param criteria
     *            an instance of {@link RegionDto} containing the properties
     *            that are to be applied to the query as selection criteria.
     * @return a List of {@link RegionDto} or null if no data is found.
     * @throws RegionCountryDaoException
     */
    List<RegionDto> fetchRegion(RegionDto criteria) throws RegionCountryDaoException;

    /**
     * Creates a new or modifies an existing region object and persist the
     * changes to an external data source.
     * 
     * @param obj
     *            an {@link RegionDto} intstance to persist
     * @return int an internal unique id for new objects or the total number of
     *         rows effected by the transaction.
     * @throws RegionCountryDaoException
     *             General database error
     */
    int maintainRegion(RegionDto obj) throws RegionCountryDaoException;

    /**
     * Deletes a Region record from an external datasource.
     * 
     * @param stateId
     *            The internal unique id of the state objet to delete.
     * @return The total number of row effected.
     * @throws RegionCountryDaoException
     *             General database error
     */
    int deleteRegion(int stateId) throws RegionCountryDaoException;

    /**
     * Creates a new or modifies an existing country object and persist the
     * changes to an external data source.
     * 
     * @param obj
     *            an {@link CountryDto} intstance to persist
     * @return int an internal unique id for new objects or the total number of
     *         rows effected by the transaction.
     * @throws RegionCountryDaoException
     *             General database error
     */
    int maintainCountry(CountryDto obj) throws RegionCountryDaoException;

    /**
     * Deletes a Country record from an external datasource.
     * 
     * @param countryId
     *            The internal unique id of the country objet to delete.
     * @return The total number of row effected.
     * @throws RegionCountryDaoException
     *             General database error
     */
    int deleteCountry(int countryId) throws RegionCountryDaoException;
}
