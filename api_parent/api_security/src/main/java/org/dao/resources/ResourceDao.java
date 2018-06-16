package org.dao.resources;

import java.util.List;

import org.dto.ResourceDto;

import com.api.persistence.DaoClient;

/**
 * Contract for accessing and managing data regarding Systems Resources.
 * 
 * @author rterrell
 * 
 */
public interface ResourceDao extends DaoClient {

    /**
     * Fetch all resource records that match the given selection criterai,
     * <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link ResourceDto}
     * @return A List of {@link ResourceDto} objects or null if no data is found
     * @throws ResourceDaoException
     */
    List<ResourceDto> fetchResource(ResourceDto criteria) throws ResourceDaoException;

    /**
     * Fetch a single resource record by its resource id.
     * 
     * @param resourceId
     * @return an instance of {@link ResourceDto} containting the resource
     *         related data or null if no data is found.
     * @throws ResourceDaoException
     */
    ResourceDto fetchResource(int resourceId) throws ResourceDaoException;

    /**
     * Fetch all resource type records.
     * 
     * @return A List of {@link ResourceDto} objects containting resource data.
     * @throws ResourceDaoException
     */
    List<ResourceDto> fetchResourceType(ResourceDto criteria) throws ResourceDaoException;

    /**
     * Fetch a single resource type record by its resource type id.
     * 
     * @param resourceTypeId
     * @return An instance of {@link ResourceDto} objects containting resource
     *         type data.
     * @throws ResourceDaoException
     */
    ResourceDto fetchResourceType(int resourceTypeId) throws ResourceDaoException;

    /**
     * Fetch all resource sub type records.
     * 
     * @return A List of {@link ResourceDto} objects containting resource sub
     *         type data.
     * @throws ResourceDaoException
     */
    List<ResourceDto> fetchResourceSubType(ResourceDto criteria) throws ResourceDaoException;

    /**
     * Fetch a single resource sub type record by its resource sub type id.
     * 
     * @param resourceSubTypeId
     * @return An instance of {@link ResourceDto} objects containting resource
     *         sub type data.
     * @throws ResourceDaoException
     */
    ResourceDto fetchResourceSubType(int resourceSubTypeId) throws ResourceDaoException;

    /**
     * Fetch a single resource sub type record by its name
     * 
     * @param resourceSubTypeName
     *            the name of the resource sub type
     * @return An instance of {@link ResourceDto} objects containting resource
     *         sub type data.
     * @throws ResourceDaoException
     */
    ResourceDto fetchResourceSubType(String resourceSubTypeName) throws ResourceDaoException;

    /**
     * Fetch data where the selection criteria targets a single category
     * (resource, resource type, or resource sub type) or a combination of all
     * categories.
     * <p>
     * All of the desired selection criteria will reside in <i>criteria</i>.
     * <p>
     * The purpose of this method is to serve as a one stop shop for all
     * resource query needs and to eliminate the need of implementing a separate
     * method for every small query requirment.
     * 
     * @param criteria
     *            an instance of {@link ResourceDto} containing a combination of
     *            selection criteria pertaining to resource, resource types,
     *            and/or resource sub type properties.
     * @return a List of {@link ResourceDto} objects or null if no data is
     *         found.
     * @throws ResourceDaoException
     */
    List<ResourceDto> fetchExtResource(ResourceDto criteria) throws ResourceDaoException;

    /**
     * Creates a new or modifies an existing Resource record.
     * 
     * @param rsrc
     *            an instance of {@link ResourceDto} containting the resource
     *            data.
     * @return an int value representing either the unique identifier of the
     *         resource inserted, or the total number of rows effected by the
     *         update of the resource.
     * @throws ResourceDaoException
     */
    int maintainResource(ResourceDto rsrc) throws ResourceDaoException;

    /**
     * Creates a new or modifies an existing Resource Type record.
     * 
     * @param rsrcType
     *            an instance of {@link ResourceDto} containting the resource
     *            type data.
     * @return an int value representing either the unique identifier of the
     *         resource type inserted, or the total number of rows effected by
     *         the update of the resource type.
     * @throws ResourceDaoException
     */
    int maintainResourceType(ResourceDto rsrcType) throws ResourceDaoException;

    /**
     * Creates a new or modifies an existing Resource Sub Type record.
     * 
     * @param rsrcSubType
     *            an instance of {@link ResourceDto} containting the resource
     *            sub type data.
     * @return an int value representing either the unique identifier of the
     *         resource sub type inserted, or the total number of rows effected
     *         by the update of the resource sub type.
     * @throws ResourceDaoException
     */
    int maintainResourceSubType(ResourceDto rsrcSubType) throws ResourceDaoException;

    /**
     * Deletes a resource record.
     * 
     * @param resourceId
     *            the resource id
     * @return total number of records effected.
     * @throws ResourceDaoException
     */
    int deleteResource(int resourceId) throws ResourceDaoException;

    /**
     * Deletes a resource record by name.
     * 
     * @param criteria
     *            an instance of {@link ResourceDto} containing selection
     *            criteria values
     * @return total number of records effected.
     * @throws ResourceDaoException
     */
    int deleteResource(ResourceDto criteria) throws ResourceDaoException;

    /**
     * Deletes a resource type record.
     * 
     * @param resourceTypeId
     *            the id of the resource type
     * @return total number of records effected.
     * @throws ResourceDaoException
     */
    int deleteResourceType(int resourceTypeId) throws ResourceDaoException;

    /**
     * Deletes a resource sub type record.
     * 
     * @param resourceSubTypeId
     *            the id of the resource sub type
     * @return total number of records effected.
     * @throws ResourceDaoException
     */
    int deleteResourceSubType(int resourceSubTypeId) throws ResourceDaoException;

    /**
     * Deletes a resource sub type record by name.
     * 
     * @param subTypeName
     *            the name of the resource sub type
     * @return total number of records effected.
     * @throws ResourceDaoException
     */
    int deleteResourceSubType(String subTypeName) throws ResourceDaoException;

}
