package org.modules.resource;

import java.util.List;

import org.apache.log4j.Logger;
import org.dao.resources.ResourceDao;
import org.dao.resources.ResourcesDaoFactory;
import org.dto.ResourceDto;
import org.modules.SecurityConstants;

import com.RMT2Constants;
import com.api.foundation.AbstractTransactionApiImpl;

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
    
//    /**
//     * Obtains a master list of resource objects using the RMT ORM DAO.
//     * 
//     * @return List of {@link ResourceDto} objects containing the resource data
//     *         or null if no data is found.
//     * @throws ResourceRegistryApiException
//     */
//    @Override
//    public List<ResourceDto> getResource() throws ResourceRegistryApiException {
//        ResourceDao dao = this.factory.createLdapDao();
//        try {
//            List<ResourceDto> results = dao.fetchResource();
//            return results;
//        } catch (Exception e) {
//            this.msg = "Unable to fetch master list of resource objects";
//            logger.error(this.msg);
//            throw new ResourceRegistryApiException(this.msg, e);
//        } finally {
//            dao.close();
//            dao = null;
//        }
//    }

    /**
     * Obtains a master list of resource type objects using the RMT ORM DAO.
     * 
     * @return List of {@link ResourceDto} objects containing the resource type
     *         data or null if no data is found.
     * @throws ResourceRegistryApiException
     */
    @Override
    public List<ResourceDto> getResourceType() throws ResourceRegistryApiException {
        try {
            List<ResourceDto> results = dao.fetchResourceType();
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
     */
    @Override
    public List<ResourceDto> getResourceSubType() throws ResourceRegistryApiException {
        try {
            List<ResourceDto> results = dao.fetchResourceSubType();
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
     * Obtains a single resource sub type object based on a unique id using the
     * RMT ORM DAO.
     * 
     * @param resourceSubTypeName
     *            the id of the resource
     * @return an instance of {@link ResourceDto} containing the resource sub
     *         type data or null if no data is found
     * @throws ResourceRegistryApiException
     */
    @Override
    public ResourceDto getResourceSubType(String resourceSubTypeName) throws ResourceRegistryApiException {
        try {
            ResourceDto results = dao.fetchResourceSubType(resourceSubTypeName);
            return results;
        } catch (Exception e) {
            this.msg = "Unable to fetch resource sub type with by its unique key, " + resourceSubTypeName;
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
     */
    @Override
    public List<ResourceDto> getResource(ResourceDto criteria) throws ResourceRegistryApiException {
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
        dao.setDaoUser(this.apiUser);
        try {
            return dao.maintainResource(obj);
        } catch (Exception e) {
            this.msg = "Unable to update resource object:  " + obj.getName();
            logger.error(this.msg);
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
        dao.setDaoUser(this.apiUser);
        try {
            return dao.maintainResourceType(obj);
        } catch (Exception e) {
            this.msg = "Unable to update resource type object:  " + obj.getTypeDescription();
            logger.error(this.msg);
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
    public int deleteResourceSubType(String resourceSubTypeName) throws ResourceRegistryApiException {
        try {
            return dao.deleteResourceSubType(resourceSubTypeName);
        } catch (Exception e) {
            this.msg = "Unable to delete resource sub type object identified by:  "  + resourceSubTypeName;
            logger.error(this.msg);
            throw new ResourceRegistryApiException(this.msg, e);
        } finally {
            dao.close();
            dao = null;
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
    public int deleteResourceType(String resourceTypeId) throws ResourceRegistryApiException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }
    
    /**
     * Not supported
     */
    @Override
    public ResourceDto getResourceType(int resourceTypeId) throws ResourceRegistryApiException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Not supported
     */
    @Override
    public ResourceDto getResourceSubType(int resourceSubTypeId) throws ResourceRegistryApiException {
        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

//    /**
//     * Not supported
//     */
//    @Override
//    public ResourceDto getResource(int resourceId) throws ResourceRegistryApiException {
//        throw new UnsupportedOperationException(RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
//    }
}
