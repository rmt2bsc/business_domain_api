package org.modules.lookup;

import java.util.List;

import org.dto.LookupCodeDto;
import org.dto.LookupExtDto;
import org.dto.LookupGroupDto;

import com.api.foundation.TransactionApi;

/**
 * An module contract for executing transactions related to the Lookup Codes.
 * 
 * @author rterrell
 * 
 */
public interface LookupDataApi extends TransactionApi {

    /**
     * Retrieves a general lookup by using a unique identifier.
     * 
     * @param codeId
     *            The unique identifier of the lookup.
     * @return An instance of {@link LookupCodeDto}
     * @throws LookupDataApiException
     */
    LookupCodeDto getCode(int codeId) throws LookupDataApiException;

    /**
     * Retrieves a list of general lookup entries based on selection criteria
     * included in <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link LookupCodeDto} containing properties
     *            used to build the query's selection criteria. The following
     *            properties recognized are <i>lookup id</i>, <i>group id</i>,
     *            <i>short name</i>, and <i>long description</i>.
     * @return a List of {@link LookupCodeDto}
     * @throws LookupDataApiException
     */
    List<LookupCodeDto> getCode(LookupCodeDto criteria)
            throws LookupDataApiException;

    /**
     * Retrieves a General Code Group using a unique identifier.
     * 
     * @param grpId
     *            The unique identifier of the lookup.
     * @return an instance of {@link LookupGroupDto}
     * @throws LookupDataApiException
     */
    LookupGroupDto getGroup(int grpId) throws LookupDataApiException;

    /**
     * Retrieves a list of general lookup group entries based on selection
     * criteria included in <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link LookupGroupDto} containing properties
     *            used to build the query's selection criteria. The following
     *            properties are recognized when building the selection
     *            criteria: <i>group id</i> and <i>group description</i>.
     * @return a List of {@link LookupGroupDto}
     * @throws LookupDataApiException
     */
    List<LookupGroupDto> getGroup(LookupGroupDto criteria)
            throws LookupDataApiException;

    /**
     * Retrieves a list of extension lookup objects containg both lookup group
     * and lookup detail data based on selection criteria included in
     * <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link LookupExtDto} containing properties used
     *            to build the query's selection criteria. The following
     *            properties recognized are <i>lookup id</i>, <i>group id</i>,
     *            <i>group description</i>, <i>lookup short description</i>, and
     *            <i>lookup long description</i>.
     * @return a List of {@link LookupExtDto}
     * @throws LookupDataApiException
     */
    List<LookupExtDto> getCodeExt(LookupExtDto criteria)
            throws LookupDataApiException;

    /**
     * Creates a new or modifies an existing general lookup group object in the
     * system.
     * 
     * @param group
     *            an instance of {@link LookupGroupDto}
     * @return The lookup id for new records or the total number of records
     *         effected by the update.
     * @throws LookupDataApiException
     */
    int updateGroup(LookupGroupDto group) throws LookupDataApiException;

    /**
     * Removes a general lookup group from the system.
     * 
     * @param groupId
     *            the internal unique id of the lookup group.
     * @return the total number of records effected.
     * @throws LookupDataApiException
     */
    int deleteGroup(int groupId) throws LookupDataApiException;

    /**
     * Creates a new or modifies an existing general lookup object in the
     * system.
     * 
     * @param lookup
     *            an instance of {@link LookupCodeDto}
     * @return The lookup id for new records or the total number of records
     *         effected by the update.
     * @throws LookupDataApiException
     */
    int updateCode(LookupCodeDto lookup) throws LookupDataApiException;

    /**
     * Removes a general lookup from the system.
     * 
     * @param codeId
     *            the internal unique id of the lookup.
     * @return the total number of records effected.
     * @throws LookupDataApiException
     */
    int deleteCode(int codeId) throws LookupDataApiException;
}
