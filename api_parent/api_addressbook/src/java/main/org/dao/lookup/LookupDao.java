package org.dao.lookup;

import java.util.List;

import org.dto.LookupCodeDto;
import org.dto.LookupExtDto;
import org.dto.LookupGroupDto;

import com.api.persistence.DaoClient;

/**
 * Contract for accessing and managing data codes and lookup groups regarding
 * the Lookup module of the Address Book API.
 * 
 * @author rterrell
 * 
 */
public interface LookupDao extends DaoClient {

    /**
     * Fetches a general lookup by using its primary key.
     * 
     * @param codeId
     *            The primary key lookup type object.
     * @return An instance of {@link LookupCodeDto}
     * @throws LookupDaoException
     */
    LookupCodeDto fetchCode(int codeId) throws LookupDaoException;

    /**
     * Fetches a list of general lookup entries based on selection criteria
     * included in <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link LookupCodeDto} containing properties
     *            used to build the query's selection criteria. The following
     *            properties recognized are <i>lookup id</i>, <i>group id</i>,
     *            <i>short name</i>, and <i>long description</i>.
     * @return a List of {@link LookupCodeDto}
     * @throws LookupDaoException
     */
    List<LookupCodeDto> fetchCode(LookupCodeDto criteria)
            throws LookupDaoException;

    /**
     * Fetches a Genreal Code Group using primary key
     * 
     * @param grpId
     *            The primary key of the group to search.
     * @return an instance of {@link LookupGroupDto}
     * @throws LookupDaoException
     */
    LookupGroupDto fetchGroup(int grpId) throws LookupDaoException;

    /**
     * Fetches a list of general lookup group entries based on selection
     * criteria included in <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link LookupGroupDto} containing properties
     *            used to build the query's selection criteria. The following
     *            properties are recognized when building the selection
     *            criteria: <i>group id</i> and <i>group description</i>.
     * @return a List of {@link LookupGroupDto}
     * @throws LookupDaoException
     */
    List<LookupGroupDto> fetchGroup(LookupGroupDto criteria)
            throws LookupDaoException;

    /**
     * Fetches a list of extension lookup objects containg both lookup group and
     * lookup detail data based on selection criteria included in
     * <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link LookupExtDto} containing properties used
     *            to build the query's selection criteria. The following
     *            properties recognized are <i>lookup id</i>, <i>group id</i>,
     *            <i>group description</i>, <i>lookup short description</i>, and
     *            <i>lookup long description</i>.
     * @return a List of {@link LookupExtDto}
     * @throws LookupDaoException
     */
    List<LookupExtDto> fetchCodeExt(LookupExtDto criteria)
            throws LookupDaoException;

    /**
     * Creates a new or modifies an existing general lookup group object
     * targeting some external data source.
     * 
     * @param group
     *            an instance of {@link LookupGroupDto}
     * @return The lookup id for new records or the total number of records
     *         effected by the update.
     * @throws LookupDaoException
     */
    int maintainGroup(LookupGroupDto group) throws LookupDaoException;

    /**
     * Removes a general lookup group from some external data source.
     * 
     * @param groupId
     *            the internal unique id of the lookup group.
     * @return the total number of records effected.
     * @throws LookupDaoException
     */
    int deleteGroup(int groupId) throws LookupDaoException;

    /**
     * Creates a new or modifies an existing general lookup object targeting
     * some external data source.
     * 
     * @param lookup
     *            an instance of {@link LookupCodeDto}
     * @return The lookup id for new records or the total number of records
     *         effected by the update.
     * @throws LookupDaoException
     */
    int maintainCode(LookupCodeDto lookup) throws LookupDaoException;

    /**
     * Removes a general lookup from some external data source.
     * 
     * @param codeId
     *            the internal unique id of the lookup.
     * @return the total number of records effected.
     * @throws LookupDaoException
     */
    int deleteCode(int codeId) throws LookupDaoException;

}
