package org.modules.resource;

import java.util.List;

import org.apache.log4j.Logger;
import org.dao.resources.ResourceDao;
import org.dao.resources.ResourcesDaoFactory;
import org.dto.ResourceDto;
import org.dto.WebServiceDto;
import org.modules.SecurityConstants;

import com.InvalidDataException;
import com.api.foundation.AbstractTransactionApiImpl;
import com.api.util.assistants.Verifier;
import com.api.util.assistants.VerifyException;

/**
 * A implementation of {@link ResourceRegistryApi} interface that is
 * specifically designed for managing data residing in the Web Services node of
 * an LDAP server.
 * <p>
 * The web services node's DN is <i>ou=Web
 * Services,ou=Resources,o=RMT2BSC,dc=rmt2,dc=net</i>.
 * 
 * @author Roy Terrell
 * 
 */
class WebServiceRegistryApiImpl extends AbstractTransactionApiImpl implements ResourceRegistryApi {

    private static final Logger logger = Logger.getLogger(WebServiceRegistryApiImpl.class);

    private ResourceDao dao;

    /**
     * Create a WebServiceRegistryApiImpl which initializes the DAO factory.
     */
    WebServiceRegistryApiImpl() {
        super(SecurityConstants.APP_NAME);
        this.dao = ResourcesDaoFactory.createRmt2OrmDao(SecurityConstants.APP_NAME);
        this.setSharedDao(this.dao);
        logger.info("Resource Api is initialized by default constructor");
    }
    
    WebServiceRegistryApiImpl(String appName) {
        super(appName);
        this.dao = ResourcesDaoFactory.createRmt2OrmDao(appName);
        this.setSharedDao(this.dao);
        logger.info("Resource Api is initialized by application name, " + appName);
    }

    @Override
    public void init() {
        super.init();
    }
    
    /**
     * Obtains a master list of resource type objects using the RMT ORM DAO.
     * 
     * @return List of {@link ResourceDto} objects containing the resource type
     *         data or null if no data is found.
     * @throws ResourceRegistryApiException
     * @throws {@link InvalidDataException}
     */
    @Override
    public List<ResourceDto> getResourceType(ResourceDto criteria) throws ResourceRegistryApiException {
        try {
            Verifier.verifyNotNull(criteria);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Selection criteria object is required for UserResoureceType Query", e);
        }
        
        try {
            List<ResourceDto> results = dao.fetchResourceType(criteria);
            return results;
        } catch (Exception e) {
            this.msg = "Unable to fetch master list of resource type objects";
            logger.error(this.msg);
            throw new ResourceRegistryApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /**
     * Obtains a master list of resource sub type objects using the RMT ORM DAO.
     * 
     * @return List of {@link ResourceDto} objects containing the resource sub
     *         type data or null if no data is found.
     * @throws ResourceRegistryApiException
     * @throws {@link InvalidDataException}
     */
    @Override
    public List<ResourceDto> getResourceSubType(ResourceDto criteria) throws ResourceRegistryApiException {
        try {
            Verifier.verifyNotNull(criteria);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Selection criteria object is required for UserResoureceSubtype Query", e);
        }
        
        try {
            List<ResourceDto> results = dao.fetchResourceSubType(criteria);
            return results;
        } catch (Exception e) {
            this.msg = "Unable to fetch master list of resource sub type objects";
            logger.error(this.msg);
            throw new ResourceRegistryApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }


    /**
     * Obtains one or more extended resource objects based any combination
     * selection criteria contained in <i>criteria</i> using the RMT ORM DAO.
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
     * @throws {@link InvalidDataException}
     */
    @Override
    public List<ResourceDto> getResource(ResourceDto criteria) throws ResourceRegistryApiException {
        try {
            Verifier.verifyNotNull(criteria);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Selection criteria object is required for UserResourece Query", e);
        }
        
        try {
            List<ResourceDto> results = dao.fetchResource(criteria);
            return results;
        } catch (Exception e) {
            this.msg = "Unable to successfully fetch list of extended resource objects using resource DTO criteria";
            logger.error(this.msg);
            throw new ResourceRegistryApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /**
     * Create a new or modifies an existing resource using the RMT ORM DAO.
     * 
     * @param obj
     *            an instance of {@link ResourceDto} containing the resource
     *            data to update.
     * @return an int value representing either the new unique id of the
     *         resource created or the total number of objects effeccted by the
     *         modification process.
     * @throws ResourceRegistryApiException
     */
    @Override
    public int updateResource(ResourceDto obj) throws ResourceRegistryApiException {
        this.validateResource(obj);
        
        dao.setDaoUser(this.apiUser);
        try {
            return dao.maintainResource(obj);
        } catch (Exception e) {
            this.msg = "Unable to update UserResource object:  " + obj.toString();
            throw new ResourceRegistryApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /**
     * Create a new or modifies an existing resource type using the RMT ORM DAO.
     * 
     * @param obj
     *            an instance of {@link ResourceDto} containing the resource
     *            type data to update.
     * @return an int value representing either the new unique id of the
     *         resource type created or the total number of objects effeccted by
     *         the modification process.
     * @throws ResourceRegistryApiException
     */
    @Override
    public int updateResourceType(ResourceDto obj) throws ResourceRegistryApiException {
        this.validateResourceType(obj);
        
        dao.setDaoUser(this.apiUser);
        try {
            return dao.maintainResourceType(obj);
        } catch (Exception e) {
            this.msg = "Unable to update resource type object:  " + obj.getTypeDescription();
            throw new ResourceRegistryApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /**
     * Create a new or modifies an existing resource sub type using the RMT ORM
     * DAO.
     * 
     * @param obj
     *            an instance of {@link ResourceDto} containing the resource sub
     *            type data to update.
     * @return an int value representing either the new unique id of the
     *         resource sub type created or the total number of objects
     *         effeccted by the modification process.
     * @throws ResourceRegistryApiException
     */
    @Override
    public int updateResourceSubType(ResourceDto obj) throws ResourceRegistryApiException {
        this.validateResourceSubType(obj);
        
        dao.setDaoUser(this.apiUser);
        try {
            return dao.maintainResourceSubType(obj);
        } catch (Exception e) {
            this.msg = "Unable to update resource sub type object:  " + obj.getSubTypeDescription();
            logger.error(this.msg);
            throw new ResourceRegistryApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /**
     * Deletes a resource object using the RMT ORM DAO
     * 
     * @param criteria
     *            an instance of {@link ResourceDto} containing data to build
     *            the selection criteria.
     * @return the total number of objects effected by the delete operation.
     * @throws ResourceRegistryApiException
     */
    @Override
    public int deleteResource(ResourceDto criteria) throws ResourceRegistryApiException {
        try {
            return dao.deleteResource(criteria);
        } catch (Exception e) {
            this.msg = "Unable to delete resource object identified by:  "
                    + criteria.getName();
            logger.error(this.msg);
            throw new ResourceRegistryApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /**
     * Deletes a resource sub type object using the RMT ORM DAO
     * 
     * @param resourceSubTypeName
     *            the unique id for the resouce sub type object to be deleted.
     * @return the total number of objects effected by the delete operation.
     * @throws ResourceRegistryApiException
     */
    @Override
    public int deleteResourceSubType(ResourceDto criteria) throws ResourceRegistryApiException {
        try {
            return dao.deleteResourceSubType(criteria.getSubTypeId());
        } catch (Exception e) {
            this.msg = "Unable to delete resource sub type object identified by id:  "  + criteria.getSubTypeId();
            logger.error(this.msg);
            throw new ResourceRegistryApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
        }
    }

    /**
     * This method is responsble for validating a resource profile.
     * <p>
     * The name, description, and url of the profile is required to have a
     * value. The properties resource type and resource sub-type, must have a
     * value greater than zero.
     * 
     * @param resource
     *            an instance of {@link ResourceDto}
     * @throws InvalidDataException
     *             The properties name, description, and URL are null or the
     *             properties resource type and resource sub type are less than
     *             or equal to zero.
     */
    protected void validateResource(ResourceDto resource) throws InvalidDataException {
        try {
            Verifier.verifyNotNull(resource);
        } catch (VerifyException e) {
            throw new InvalidDataException("Resource object is required and cannot be null", e);
        }

        try {
            Verifier.verifyTrue(resource instanceof WebServiceDto);
        } catch (VerifyException e) {
            throw new InvalidDataException(
                    "The target web service resource object is required to be of type, WebServiceDto, at runtime",
                    e);
        }
        
        try {
            Verifier.verifyNotEmpty(resource.getName());
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Resource Name is required", e);
        }
        
        try {
            Verifier.verifyNotEmpty(resource.getDescription());
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Resource Description is required", e);
        }

        try {
            Verifier.verifyNotEmpty(((WebServiceDto) resource).getRequestUrl());
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Resource URL is required", e);
        }

        try {
            Verifier.verifyPositive(resource.getTypeId());
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Resource Type is required", e);
        }
        
        try {
            Verifier.verifyPositive(resource.getSubTypeId());
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Resource Sub-Type is required", e);
        }
    }
    
    /**
     * Validates the resource type object.
     * <p>
     * The description property is required.
     * 
     * @param resourceType
     *            an instance of {@link ResourceDto} containing resource type
     *            data
     * @throws InvalidDataException
     *             The description is null or equal to spaces.
     */
    protected void validateResourceType(ResourceDto resourceType) throws InvalidDataException {
        try {
            Verifier.verifyNotNull(resourceType);
        } catch (VerifyException e) {
            throw new InvalidDataException("ResourceType object is required and cannot be null", e);
        }
        
        try {
            Verifier.verifyNotEmpty(resourceType.getTypeDescription());
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Resource Type Description is required", e);
        }
    }
    
    /**
     * This method is responsble for validating a resource sub type profile.
     * <p>
     * The name and description are required to have a value.
     * 
     * @param subType
     *            an instance of {@link ResourceDto} containing the criteria
     *            specific to resource sub type.
     * @throws InvalidDataException
     *             The name and description properties are null or spaces.
     */
    protected void validateResourceSubType(ResourceDto subType) throws InvalidDataException {
        try {
            Verifier.verifyNotNull(subType);
        } catch (VerifyException e) {
            throw new InvalidDataException("ResourceSubtype object is required and cannot be null", e);
        }

        try {
            Verifier.verifyNotEmpty(subType.getSubTypeName());
        } catch (VerifyException e) {
            throw new InvalidDataException("Resource SubType Name is required", e);
        }
        
        try {
            Verifier.verifyNotEmpty(subType.getSubTypeDescription());
        } catch (VerifyException e) {
            throw new InvalidDataException("Resource SubType Description is required", e);
        }
    }
    
    /**
     * Deletes a resource type object using the RMT ORM DAO
     * 
     * @param resourceTypeId
     *            the unique id for the resouce type object to be deleted.
     * @return the total number of objects effected by the delete operation.
     * @throws ResourceRegistryApiException
     */
    @Override
    public int deleteResourceType(ResourceDto criteria) throws ResourceRegistryApiException {
        try {
            return dao.deleteResourceType(criteria.getTypeId());
        } catch (Exception e) {
            this.msg = "Unable to delete resource type object identified by id:  "  + criteria.getSubTypeId();
            throw new ResourceRegistryApiException(this.msg, e);
        }
    }

}
