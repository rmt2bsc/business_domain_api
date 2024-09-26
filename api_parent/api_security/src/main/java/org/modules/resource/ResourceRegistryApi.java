package org.modules.resource;

import java.util.List;

import org.dto.ResourceDto;

import com.api.foundation.TransactionApi;

/**
 * An API contract for for managing the resource module.
 * 
 * @author Roy Terrell
 * 
 */
public interface ResourceRegistryApi extends TransactionApi {

    /**
     * Obtains a master list of resource type objects.
     * 
     * @param  criteria an instance of {@link ResourceDto} containing values to build
     *            the selection criteria.
     * @return List of {@link ResourceDto} objects containing the resource type
     *         data or null if no data is found.
     * @throws ResourceRegistryApiException
     */
    List<ResourceDto> getResourceType(ResourceDto criteria) throws ResourceRegistryApiException;

    /**
     * Obtains a master list of resource sub type objects.
     * 
     * @param criteria
     *            an instance of {@link ResourceDto} containing values to build
     *            the selection criteria.
     * @return List of {@link ResourceDto} objects containing the resource sub
     *         type data or null if no data is found.
     * @throws ResourceRegistryApiException
     */
    List<ResourceDto> getResourceSubType(ResourceDto criteria) throws ResourceRegistryApiException;

    /**
     * Obtains a master list of resource sub type objects with extended data.
     * 
     * @param criteria
     *            an instance of {@link ResourceDto} containing values to build
     *            the selection criteria.
     * @return List of {@link ResourceDto} objects containing the resource sub
     *         type data or null if no data is found.
     * @throws ResourceRegistryApiException
     */
    List<ResourceDto> getResourceSubTypeExt(ResourceDto criteria) throws ResourceRegistryApiException;

    /**
     * Obtains one or more extended resource objects based any combination
     * selection criteria contained in <i>criteria</i>.
     * <p>
     * All of the desired selection criteria will reside in <i>criteria</i>. The
     * following properties are recognized for each level of the resource:
     * <blockquote> <b>Resource</b>
     * <ul>
     * <li>id</li>
     * <li>name</li>
     * <li>description</li>
     * <li>host</li>
     * <li>secured</li>
     * </ul>
     * <b>Resource Type</b>
     * <ul>
     * <li>id</li>
     * <li>description</li>
     * </ul>
     * <b>Resource Sub Type</b>
     * <ul>
     * <li>id</li>
     * <li>name</li>
     * <li>description</li>
     * </ul>
     * </blockquote>
     * 
     * @param criteria
     *            an instance of {@link ResourceDto} containing a combination of
     *            selection criteria pertaining to resource, resource types,
     *            and/or resource sub type properties.
     * @return a List of {@link ResourceDto} objects ordered by resource name,
     *         resource type description, and resource sub type name in
     *         ascending order or null if no data is found.
     * @throws ResourceRegistryApiException
     */
    List<ResourceDto> getResource(ResourceDto criteria) throws ResourceRegistryApiException;

    /**
     * Create a new or modifies an existing resource.
     * 
     * @param obj
     *            an instance of {@link ResourceDto} containing the resource
     *            data to update.
     * @return an int value representing either the new unique id of the
     *         resource created or the total number of objects effeccted by the
     *         modification process.
     * @throws ResourceRegistryApiException
     */
    int updateResource(ResourceDto obj) throws ResourceRegistryApiException;

    /**
     * Create a new or modifies an existing resource type.
     * 
     * @param obj
     *            an instance of {@link ResourceDto} containing the resource
     *            type data to update.
     * @return an int value representing either the new unique id of the
     *         resource type created or the total number of objects effeccted by
     *         the modification process.
     * @throws ResourceRegistryApiException
     */
    int updateResourceType(ResourceDto obj) throws ResourceRegistryApiException;

    /**
     * Create a new or modifies an existing resource sub type.
     * 
     * @param obj
     *            an instance of {@link ResourceDto} containing the resource sub
     *            type data to update.
     * @return an int value representing either the new unique id of the
     *         resource sub type created or the total number of objects
     *         effeccted by the modification process.
     * @throws ResourceRegistryApiException
     */
    int updateResourceSubType(ResourceDto obj)  throws ResourceRegistryApiException;

    /**
     * Deletes a resource object
     * 
     * @param criteria
     *            an instance of {@link ResourceDto} containing values to build
     *            the selection criteria.
     * @return the total number of objects effected by the delete operation.
     * @throws ResourceRegistryApiException
     */
    int deleteResource(ResourceDto criteria) throws ResourceRegistryApiException;

    /**
     * Deletes a resource type object
     * 
     * @param criteria
     *            an instance of {@link ResourceDto} containing values to build
     *            the selection criteria.
     * @return the total number of objects effected by the delete operation.
     * @throws ResourceRegistryApiException
     */
    int deleteResourceType(ResourceDto criteria) throws ResourceRegistryApiException;

    /**
     * Deletes a resource sub type object
     * 
     * @param criteria
     *            an instance of {@link ResourceDto} containing values to build
     *            the selection criteria.
     * @return the total number of objects effected by the delete operation.
     * @throws ResourceRegistryApiException
     */
    int deleteResourceSubType(ResourceDto criteria) throws ResourceRegistryApiException;
}