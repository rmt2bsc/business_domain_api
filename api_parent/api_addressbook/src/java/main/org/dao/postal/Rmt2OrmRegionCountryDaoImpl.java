package org.dao.postal;

import java.util.ArrayList;
import java.util.List;

import org.dao.AddressBookDaoImpl;
import org.dao.mapping.orm.rmt2.Country;
import org.dao.mapping.orm.rmt2.State;
import org.dao.mapping.orm.rmt2.VwStateCountry;
import org.dto.CountryDto;
import org.dto.CountryRegionDto;
import org.dto.RegionDto;
import org.dto.adapter.orm.Rmt2AddressBookDtoFactory;

import com.api.persistence.DatabaseException;

/**
 * An RMT2 ORM implementation of {@link RegionCountryDao} contract for accessing
 * state/province and country location information as it pertains to the state
 * and country tables, and the vw_state_country view.
 * 
 * @author rterrell
 * 
 */
class Rmt2OrmRegionCountryDaoImpl extends AddressBookDaoImpl implements
        RegionCountryDao {

    /**
     * Default constructor
     */
    protected Rmt2OrmRegionCountryDaoImpl() {
        super();
    }

    protected Rmt2OrmRegionCountryDaoImpl(String appName) {
        super(appName);
    }

    /**
     * Fetches a single state/province record from the state table by its
     * primary key.
     * 
     * @param uid
     *            the internal unique id of the region or state/province
     *            servinig as the primary key of the table.
     * @return an instance of {@link RegionDto} containing state data or null if
     *         no data is found.
     * @throws RegionCountryDaoException
     */
    @Override
    public RegionDto fetchRegion(int uid) throws RegionCountryDaoException {
        State s = new State();
        s.addCriteria(State.PROP_STATEID, uid);
        try {
            s = (State) this.client.retrieveObject(s);
            if (s == null) {
                return null;
            }
        } catch (DatabaseException e) {
            this.msg = "Database access error occurred while fetching single region (state/province) record by primary key, "
                    + uid;
            throw new RegionCountryDaoException(this.msg, e);
        }
        RegionDto dto = Rmt2AddressBookDtoFactory.getRegionInstance(s);
        return dto;
    }

    /**
     * Fetches one or more state/province records from the <i>state</i> table
     * using various assigned properties set in <i>criteria</i> as selection
     * criteria.
     * 
     * @param criteria
     *            an instance of {@link RegionDto} containing the properties
     *            that are to be applied to the query as selection criteria or
     *            <i>null</i> to fetch all records. The properties eligiable to
     *            be used as selection criteria correspond to the following
     *            table columns: <i>state_id</i>, <i>abbr_code</i>,
     *            <i>country_id</i>, and <i>state_name</i>.
     * 
     * @return a List of {@link RegionDto} or null if no data is found.
     * @throws RegionCountryDaoException
     */
    @Override
    public List<RegionDto> fetchRegion(RegionDto criteria)
            throws RegionCountryDaoException {
        State s = new State();
        if (criteria != null) {
            if (criteria.getStateId() > 0) {
                s.addCriteria(State.PROP_STATEID, criteria.getStateId());
            }
            if (criteria.getCountryId() > 0) {
                s.addCriteria(State.PROP_COUNTRYID, criteria.getCountryId());
            }
            if (criteria.getStateCode() != null) {
                s.addCriteria(State.PROP_ABBRCODE, criteria.getStateCode());
            }
            if (criteria.getStateName() != null) {
                s.addLikeClause(State.PROP_STATENAME, criteria.getStateName());
            }
        }

        List<State> results;
        try {
            results = this.client.retrieveList(s);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            this.msg = "Database access error occurred while fetching list of region (state/province) records by DTO criteria";
            throw new RegionCountryDaoException(this.msg, e);
        }

        List<RegionDto> list = new ArrayList<RegionDto>();
        for (State item : results) {
            RegionDto dto = Rmt2AddressBookDtoFactory.getRegionInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Fetches a single country record from the country table by its primary
     * key.
     * 
     * @param uid
     *            the internal unique id of the country servinig as the primary
     *            key of the table.
     * @return an instance of {@link CountryDto} containing state data or null
     *         if no data is found.
     * @throws RegionCountryDaoException
     */
    @Override
    public CountryDto fetchCountry(int uid) throws RegionCountryDaoException {
        Country c = new Country();
        c.addCriteria(State.PROP_COUNTRYID, uid);
        try {
            c = (Country) this.client.retrieveObject(c);
            if (c == null) {
                return null;
            }
        } catch (DatabaseException e) {
            this.msg = "Database access error occurred while fetching single country record by primary key, "
                    + uid;
            throw new RegionCountryDaoException(this.msg, e);
        }
        CountryDto dto = Rmt2AddressBookDtoFactory.getCountryInstance(c);
        return dto;
    }

    /**
     * Fetches one or more country records from the <i>country</i> table using
     * various assigned properties set in <i>criteria</i> as selection criteria.
     * 
     * @param criteria
     *            an instance of {@link CountryDto} containing the properties
     *            that are to be applied to the query as selection criteria or
     *            <i>null</i> to fetch all records. The properties eligiable to
     *            be used as selection criteria correspond to the following
     *            table columns: <i>country_id</i>, <i>name</i>, and
     *            <i>code</i>.
     * 
     * @return a List of {@link CountryDto} or null if no data is found.
     * @throws RegionCountryDaoException
     */
    @Override
    public List<CountryDto> fetchCountry(CountryDto criteria)
            throws RegionCountryDaoException {
        Country s = new Country();
        if (criteria != null) {
            if (criteria.getCountryId() > 0) {
                s.addCriteria(Country.PROP_COUNTRYID, criteria.getCountryId());
            }
            if (criteria.getCountryName() != null) {
                s.addLikeClause(Country.PROP_NAME, criteria.getCountryName());
            }
            if (criteria.getCountryCode() != null) {
                s.addCriteria(Country.PROP_CODE, criteria.getCountryCode());
            }
        }

        List<Country> results;
        try {
            results = this.client.retrieveList(s);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            this.msg = "Database access error occurred while fetching list of country records by DTO criteria";
            throw new RegionCountryDaoException(this.msg, e);
        }

        List<CountryDto> list = new ArrayList<CountryDto>();
        for (Country item : results) {
            CountryDto dto = Rmt2AddressBookDtoFactory.getCountryInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Fetches one or more records from the <i>vw_state_country</i> view that
     * combines state and country data using various assigned properties set in
     * <i>criteria</i> as selection criteria.
     * 
     * @param criteria
     *            an instance of {@link RegionDto} containing the properties
     *            that are to be applied to the query as selection criteria or
     *            <i>null</i> to fetch all records. The properties eligiable to
     *            be used as selection criteria correspond to the following
     *            table columns: <i>state_id</i>, <i>state_code</i>,
     *            <i>country_id</i>, <i>state_name</i>, and <i>country_name</i>.
     * 
     * @return a List of {@link RegionDto} or null if no data is found.
     * @throws RegionCountryDaoException
     */
    @Override
    public List<CountryRegionDto> fetchCountryRegion(CountryRegionDto criteria)
            throws RegionCountryDaoException {
        VwStateCountry s = new VwStateCountry();
        if (criteria != null) {
            if (criteria.getCountryId() > 0) {
                s.addCriteria(Country.PROP_COUNTRYID, criteria.getCountryId());
            }
            if (criteria.getCountryName() != null) {
                s.addLikeClause(Country.PROP_NAME, criteria.getCountryName());
            }

            if (criteria.getStateId() > 0) {
                s.addCriteria(State.PROP_STATEID, criteria.getStateId());
            }
            if (criteria.getStateCode() != null) {
                s.addCriteria(State.PROP_ABBRCODE, criteria.getStateCode());
            }
            if (criteria.getStateName() != null) {
                s.addLikeClause(State.PROP_STATENAME, criteria.getStateName());
            }
        }

        List<VwStateCountry> results;
        try {
            results = this.client.retrieveList(s);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            this.msg = "Database access error occurred while fetching list of combined country/state records by DTO criteria";
            throw new RegionCountryDaoException(this.msg, e);
        }

        List<CountryRegionDto> list = new ArrayList<CountryRegionDto>();
        for (VwStateCountry item : results) {
            CountryRegionDto dto = Rmt2AddressBookDtoFactory
                    .getCountryRegionInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Creates a new or modifies an existing region object and persist the
     * changes to the <i>state</i> table in the contacts database.
     * 
     * @param dto
     *            an {@link RegionDto} intstance to persist
     * @return int an internal unique id for new objects or the total number of
     *         rows effected by the transaction.
     * @throws RegionCountryDaoException
     *             General database error
     */
    @Override
    public int maintainRegion(RegionDto dto) throws RegionCountryDaoException {
        this.validateRegion(dto);

        State state = new State();
        state.setStateId(dto.getStateId());
        state.setAbbrCode(dto.getStateCode());
        state.setCountryId(dto.getCountryId());
        state.setStateName(dto.getStateName());

        this.client.beginTrans();
        try {
            int rc = 0;
            if (state.getStateId() > 0) {
                rc = this.updateRegion(state);
            }
            if (state.getStateId() == 0) {
                rc = this.insertRegion(state);
                state.setStateId(rc);
                dto.setStateId(rc);
            }
            this.client.commitTrans();
            return rc;
        } catch (RegionCountryDaoException e) {
            this.client.rollbackTrans();
            throw e;
        }

    }

    /**
     * Adds a record to the state table using <i>obj</i>.
     * 
     * @param obj
     *            The state ORM object add.
     * @return The state id of the record to add.
     * @throws RegionCountryDaoException
     *             General database error
     */
    private int insertRegion(State obj) throws RegionCountryDaoException {
        try {
            // Create State
            int key = this.client.insertRow(obj, true);
            return key;
        } catch (Exception e) {
            throw new RegionCountryDaoException(e);
        }
    }

    /**
     * Modifies a single record targeting the state table and persists the
     * changes to the database.
     * 
     * @param obj
     *            The state object to modify.
     * @return an integer value represeinting the total number of records
     *         effected by the transaction.
     * @throws RegionCountryDaoException
     *             General database error
     */
    private int updateRegion(State obj) throws RegionCountryDaoException {
        try {
            obj.addCriteria(State.PROP_STATEID, obj.getStateId());
            int rows = this.client.updateRow(obj);
            return rows;
        } catch (Exception e) {
            throw new RegionCountryDaoException(e);
        }
    }

    /**
     * Validates a RegionDto object for insert and update opertaions.
     * 
     * @param obj
     *            an instance of {@link RegionDto} that is to be validated.
     * @throws InvalidRegionDataDaoException
     *             if <i>obj</i> is null or <i>state code</i>, <i>state name</i>
     *             and/or <i>country id</i> have not been assinged a value.
     */
    protected void validateRegion(RegionDto obj)
            throws InvalidRegionDataDaoException {
        if (obj == null) {
            this.msg = "Region instance cannot be null for insert/update operations";
            throw new InvalidRegionDataDaoException(this.msg);
        }

        if (obj.getStateCode() == null || obj.getStateCode().equals("")) {
            this.msg = "Region (State/Province) code is required";
            throw new InvalidRegionDataDaoException(this.msg);
        }
        if (obj.getStateName() == null || obj.getStateName().equals("")) {
            this.msg = "Region (State/Province) name is required";
            throw new InvalidRegionDataDaoException(this.msg);
        }
        if (obj.getCountryId() <= 0) {
            this.msg = "Country code is required";
            throw new InvalidRegionDataDaoException(this.msg);
        }
    }

    /**
     * Deletes a single record from the <i>state</i> table of the contacts
     * database.
     * 
     * @param stateId
     *            The primary key of the state object to delete.
     * @return The total number of row effected.
     * @throws RegionCountryDaoException
     *             <i>stateId</i> is not greater than zero or general database
     *             error
     */
    @Override
    public int deleteRegion(int stateId) throws RegionCountryDaoException {
        if (stateId <= 0) {
            this.msg = "State id is invalid...must be greater than zero";
            throw new InvalidRegionDataDaoException(this.msg);
        }
        State obj = new State();

        this.client.beginTrans();
        try {
            obj.addCriteria(State.PROP_STATEID, stateId);
            int rows = this.client.deleteRow(obj);
            this.client.commitTrans();
            return rows;
        } catch (Exception e) {
            this.client.rollbackTrans();
            throw new RegionCountryDaoException(e);
        }
    }

    /**
     * Creates a new or modifies an existing country record and persists the
     * changes to the <i>country</i> table in the contacts database.
     * 
     * @param dto
     *            an {@link CountryDto} intstance to persist
     * @return int an internal unique id for new objects or the total number of
     *         rows effected by the transaction.
     * @throws RegionCountryDaoException
     *             General database error
     */
    @Override
    public int maintainCountry(CountryDto dto) throws RegionCountryDaoException {
        this.validate(dto);

        Country c = new Country();
        c.setCountryId(dto.getCountryId());
        c.setName(dto.getCountryName());
        c.setCode(dto.getCountryCode());
        c.setCntryVoidInd(dto.getCountryPermcol());

        this.client.beginTrans();
        try {
            int rc = 0;
            if (c.getCountryId() > 0) {
                rc = this.updateCountry(c);
            }
            if (c.getCountryId() == 0) {
                rc = this.insertCountry(c);
                c.setCountryId(rc);
                dto.setCountryId(rc);
            }
            this.client.commitTrans();
            return rc;
        } catch (RegionCountryDaoException e) {
            this.client.rollbackTrans();
            throw e;
        }

    }

    /**
     * Adds a record to the country table using <i>obj</i>.
     * 
     * @param obj
     *            The country ORM object add.
     * @return The new country id of the record to add.
     * @throws RegionCountryDaoException
     *             General database error
     */
    private int insertCountry(Country obj) throws RegionCountryDaoException {
        try {
            // Create State
            int key = this.client.insertRow(obj, true);
            return key;
        } catch (Exception e) {
            throw new RegionCountryDaoException(e);
        }
    }

    /**
     * Modifies a single record targeting the country table and persists the
     * changes to the database.
     * 
     * @param obj
     *            The country object to modify.
     * @return an integer value represeinting the total number of records
     *         effected by the transaction.
     * @throws RegionCountryDaoException
     *             General database error
     */
    private int updateCountry(Country obj) throws RegionCountryDaoException {
        try {
            obj.addCriteria(Country.PROP_COUNTRYID, obj.getCountryId());
            int rows = this.client.updateRow(obj);
            return rows;
        } catch (Exception e) {
            throw new RegionCountryDaoException(e);
        }
    }

    /**
     * Validates a CountryDto object for insert and update operations.
     * 
     * @param obj
     *            an instance of {@link CountryDto} that is to be validated.
     * @throws InvalidRegionDataDaoException
     *             if <i>obj</i> is null or <i>country name</i> does not been
     *             assinged a value.
     */
    protected void validate(CountryDto obj)
            throws InvalidRegionDataDaoException {
        if (obj == null) {
            this.msg = "Country instance cannot be null for insert/update operations";
            throw new InvalidRegionDataDaoException(this.msg);
        }
        if (obj.getCountryName() == null || obj.getCountryName().equals("")) {
            this.msg = "Country name is required";
            throw new InvalidRegionDataDaoException(this.msg);
        }
    }

    /**
     * Deletes a single record from the <i>country</i> table of the contacts
     * database.
     * 
     * @param countryId
     *            The internal unique id of the country objet to delete.
     * @return The total number of row effected.
     * @throws RegionCountryDaoException
     *             <i>countryId</i> is not greater than zero or general database
     *             error
     */
    @Override
    public int deleteCountry(int countryId) throws RegionCountryDaoException {
        if (countryId <= 0) {
            this.msg = "Country id is invalid...must be greater than zero";
            throw new InvalidRegionDataDaoException(this.msg);
        }
        Country obj = new Country();

        this.client.beginTrans();
        try {
            obj.addCriteria(Country.PROP_COUNTRYID, countryId);
            int rows = this.client.deleteRow(obj);
            this.client.commitTrans();
            return rows;
        } catch (Exception e) {
            this.client.rollbackTrans();
            throw new RegionCountryDaoException(e);
        }
    }

}
