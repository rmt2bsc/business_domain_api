package org.dao.resources;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dao.SecurityDaoImpl;
import org.dao.mapping.orm.rmt2.UserResource;
import org.dao.mapping.orm.rmt2.UserResourceSubtype;
import org.dao.mapping.orm.rmt2.UserResourceType;
import org.dao.mapping.orm.rmt2.VwResource;
import org.dto.ResourceDto;
import org.dto.WebServiceDto;
import org.dto.adapter.orm.Rmt2OrmDtoFactory;

import com.RMT2Constants;
import com.SystemException;
import com.api.persistence.DatabaseException;
import com.util.RMT2Date;
import com.util.UserTimestamp;

/**
 * An RMT2 ORM database implementation of the {@link ResourceDao} interface
 * which functions to manage resource, resource type, and resource sub type
 * data.
 * 
 * @author rterrell
 * 
 */
class Rmt2OrmWebServicesDaoImpl extends SecurityDaoImpl implements ResourceDao {

    private static final Logger logger = Logger
            .getLogger(Rmt2OrmWebServicesDaoImpl.class);

    /**
     * Default constructor for creating a Rmt2OrmWebServicesDaoImpl.
     */
    protected Rmt2OrmWebServicesDaoImpl() {
        super();
        logger.info(Rmt2OrmWebServicesDaoImpl.class.getName() + " initialized");
    }

    /**
     * constructor for creating a Rmt2OrmWebServicesDaoImpl.
     * 
     * @param appName
     *            application name
     */
    protected Rmt2OrmWebServicesDaoImpl(String appName) {
        super(appName);
        logger.info(Rmt2OrmWebServicesDaoImpl.class.getName() + " initialized");
    }

    /**
     * Query the user_resource table for the purpose of returning the entire
     * data set.
     * 
     * @return List of {@link ResourceDto} objects or null if no data is found
     * @throws ResourceDaoException
     *             Database access error
     */
    @Override
    public List<ResourceDto> fetchResource() throws ResourceDaoException {
        UserResource rsrc = new UserResource();
        List<UserResource> rsrcList = null;
        try {
            rsrcList = this.client.retrieveList(rsrc);
            if (rsrcList == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new ResourceDaoException(e);
        }

        List<ResourceDto> list = new ArrayList<ResourceDto>();
        for (UserResource item : rsrcList) {
            ResourceDto dto = Rmt2OrmDtoFactory.getResourceDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Query the user_resource table for a single record using the prinary key.
     * 
     * @param resourceId
     *            the primary key of the resource record.
     * @return an instance of {@link ResourceDto} containting the resource
     *         related data or null if no data is found.
     * @throws ResourceDaoException
     *             Database access error
     */
    @Override
    public ResourceDto fetchResource(int resourceId)
            throws ResourceDaoException {
        UserResource rsrc = new UserResource();
        rsrc.addCriteria(UserResource.PROP_RSRCID, resourceId);
        try {
            UserResource results = (UserResource) this.client
                    .retrieveObject(rsrc);
            if (results == null) {
                return null;
            }
            return Rmt2OrmDtoFactory.getResourceDtoInstance(results);
        } catch (DatabaseException e) {
            throw new ResourceDaoException(e);
        }
    }

    /**
     * Query the user_resource_type table for the purpose of returning the
     * entire data set.
     * 
     * @return List of {@link ResourceDto} objects containing the resource type
     *         data or null if no data is found
     * @throws ResourceDaoException
     *             Database access error
     */
    @Override
    public List<ResourceDto> fetchResourceType() throws ResourceDaoException {
        UserResourceType rsrc = new UserResourceType();
        List<UserResourceType> rsrcList = null;
        try {
            rsrcList = this.client.retrieveList(rsrc);
            if (rsrcList == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new ResourceDaoException(e);
        }

        List<ResourceDto> list = new ArrayList<ResourceDto>();
        for (UserResourceType item : rsrcList) {
            ResourceDto dto = Rmt2OrmDtoFactory.getResourceDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Query the user_resource_type table for a single record using the prinary
     * key.
     * 
     * @param resourceTypeId
     *            the primary key of the resource type record.
     * @return an instance of {@link ResourceDto} containting the resource type
     *         related data or null if no data is found.
     * @throws ResourceDaoException
     *             Database access error
     */
    @Override
    public ResourceDto fetchResourceType(int resourceTypeId)
            throws ResourceDaoException {
        UserResourceType rsrc = new UserResourceType();
        rsrc.addCriteria(UserResource.PROP_RSRCTYPEID, resourceTypeId);
        try {
            UserResourceType results = (UserResourceType) this.client
                    .retrieveObject(rsrc);
            if (results == null) {
                return null;
            }
            return Rmt2OrmDtoFactory.getResourceDtoInstance(results);
        } catch (DatabaseException e) {
            throw new ResourceDaoException(e);
        }
    }

    /**
     * Query the user_resource_subtype table for the purpose of returning the
     * entire data set.
     * 
     * @return List of {@link ResourceDto} objects containing the resource sub
     *         type data or null if no data is found
     * @throws ResourceDaoException
     *             Database access error
     */
    @Override
    public List<ResourceDto> fetchResourceSubType() throws ResourceDaoException {
        UserResourceSubtype rsrc = new UserResourceSubtype();
        List<UserResourceSubtype> rsrcList = null;
        try {
            rsrcList = this.client.retrieveList(rsrc);
            if (rsrcList == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new ResourceDaoException(e);
        }

        List<ResourceDto> list = new ArrayList<ResourceDto>();
        for (UserResourceSubtype item : rsrcList) {
            ResourceDto dto = Rmt2OrmDtoFactory.getResourceDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Query the user_resource_subtype table for a single record using the
     * prinary key.
     * 
     * @param resourceSubTypeId
     *            the primary key of the resource sub type record.
     * @return an instance of {@link ResourceDto} containting the resource sub
     *         type related data or null if no data is found.
     * @throws ResourceDaoException
     *             Database access error
     */
    @Override
    public ResourceDto fetchResourceSubType(int resourceSubTypeId)
            throws ResourceDaoException {
        UserResourceSubtype rsrc = new UserResourceSubtype();
        rsrc.addCriteria(UserResource.PROP_RSRCSUBTYPEID, resourceSubTypeId);
        try {
            UserResourceSubtype results = (UserResourceSubtype) this.client
                    .retrieveObject(rsrc);
            if (results == null) {
                return null;
            }
            return Rmt2OrmDtoFactory.getResourceDtoInstance(results);
        } catch (DatabaseException e) {
            throw new ResourceDaoException(e);
        }
    }

    /**
     * Fetch data where the selection criteria targets a single category
     * (resource, resource type, or resource sub type) or a combination of all
     * categories.
     * <p>
     * All of the desired selection criteria will reside in <i>criteria</i>. The
     * following properties are recognized for each level of the resource:
     * <blockquote> <b>Resource</b>
     * <ul>
     * <li>id</li>
     * <li>name</li>
     * <li>description</li>
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
     *            and/or resource sub type properties. The contents of
     *            <i>criteria</i> will be translated to an instance of
     *            {@link VwResource} in order to perform the actual database
     *            query against the vw_resource view.
     * @return a List of {@link ResourceDto} objects ordered by resource name,
     *         resource type description, and resource sub type name in
     *         ascending order or null if no data is found.
     * @throws ResourceDaoException
     */
    @Override
    public List<ResourceDto> fetchExtResource(ResourceDto criteria)
            throws ResourceDaoException {
        VwResource rsrc = new VwResource();

        // Setup criteria
        if (criteria != null) {
            if (!(criteria instanceof WebServiceDto)) {
                throw new InvalidResourceDaoException(
                        "Input parameter is required to be of type, WebServiceDto, at runtime for DB query");
            }

            // Check for UserResource related predicates
            if (criteria.getUid() > 0) {
                rsrc.addCriteria(VwResource.PROP_RSRCID, criteria.getUid());
            }
            if (criteria.getName() != null) {
                rsrc.addLikeClause(VwResource.PROP_NAME, criteria.getName());
            }
            if (criteria.getDescription() != null) {
                rsrc.addLikeClause(VwResource.PROP_DESCRIPTION,
                        criteria.getDescription());
            }
            if (((WebServiceDto) criteria).isQuerySecureFlag()) {
                rsrc.addCriteria(VwResource.PROP_SECURED,
                        ((WebServiceDto) criteria).getSecured());
            }

            // Check for UserResourceType related predicates
            if (criteria.getTypeId() > 0) {
                rsrc.addCriteria(VwResource.PROP_RSRCTYPEID,
                        criteria.getTypeId());
            }
            if (criteria.getTypeDescription() != null) {
                rsrc.addLikeClause(VwResource.PROP_TYPEDESCR,
                        criteria.getTypeDescription());
            }

            // Check for UserResourceSubtype related predicates
            if (criteria.getSubTypeId() > 0) {
                rsrc.addCriteria(VwResource.PROP_RSRCSUBTYPEID,
                        criteria.getSubTypeId());
            }
            if (criteria.getSubTypeName() != null) {
                rsrc.addLikeClause(VwResource.PROP_SUBTYPENAME,
                        criteria.getSubTypeName());
            }
            if (criteria.getSubTypeDescription() != null) {
                rsrc.addLikeClause(VwResource.PROP_SUBTYPEDESC,
                        criteria.getSubTypeDescription());
            }
        }

        // Order result set by resource name, resource type description, and
        // resource sub type name in ascending order.
        rsrc.addOrderBy(VwResource.PROP_NAME, VwResource.ORDERBY_ASCENDING);
        rsrc.addOrderBy(VwResource.PROP_TYPEDESCR, VwResource.ORDERBY_ASCENDING);
        rsrc.addOrderBy(VwResource.PROP_SUBTYPENAME,
                VwResource.ORDERBY_ASCENDING);

        // Perform query
        List<VwResource> rsrcList = null;
        try {
            rsrcList = this.client.retrieveList(rsrc);
            if (rsrcList == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new ResourceDaoException(e);
        }

        List<ResourceDto> list = new ArrayList<ResourceDto>();
        for (VwResource item : rsrcList) {
            ResourceDto dto = Rmt2OrmDtoFactory.getResourceDtoInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Creates a new or modifies an existing record in the user_resouce table.
     * <p>
     * A SQL insert is performed when the primary key is equal to zero. When the
     * primary key id is greater than zero, an SQL update is applied.
     * 
     * @param resource
     *            an instance of {@link ResourceDto} containting data that can
     *            be mapped to {@link UserResource}.
     * @return an int value representing either the unique identifier of the
     *         resource inserted, or the total number of rows effected by the
     *         update of the resource.
     * @throws ResourceDaoException
     */
    @Override
    public int maintainResource(ResourceDto resource)
            throws ResourceDaoException {
        this.validateResource(resource);

        int rc = 0;
        UserResource r = new UserResource();
        r.setRsrcId(resource.getUid());
        r.setName(resource.getName());
        r.setRsrcTypeId(resource.getTypeId());
        r.setRsrcSubtypeId(resource.getSubTypeId());
        r.setUrl(((WebServiceDto) resource).getRequestUrl());
        r.setDescription(resource.getDescription());
        r.setSecured(((WebServiceDto) resource).getSecured());
        r.setHost(((WebServiceDto) resource).getHost());
        r.setDateCreated(resource.getDateCreated());
        r.setDateUpdated(resource.getDateUpdated());
        r.setUserId(resource.getUpdateUserId());

        this.client.beginTrans();
        try {
            if (r.getRsrcId() > 0) {
                rc = this.updateResource(r);
            }
            if (r.getRsrcId() == 0) {
                rc = this.createResource(r);
                r.setRsrcId(rc);
                resource.setUid(rc);
            }
            this.client.commitTrans();
            return rc;
        } catch (ResourceDaoException e) {
            this.client.rollbackTrans();
            throw e;
        }
    }

    private int createResource(UserResource obj)
            throws ResourceDaoUpdateException {
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            obj.setDateCreated(ut.getDateCreated());
            obj.setDateUpdated(ut.getDateCreated());
            obj.setUserId(ut.getLoginId());
            int rc = this.client.insertRow(obj, true);
            return rc;
        } catch (DatabaseException e) {
            this.msg = "Database error occurred during record insert targeting user_resource table";
            throw new ResourceDaoUpdateException(this.msg, e);
        } catch (SystemException e) {
            this.msg = "Unable to establish UserTimestamp instance before inserting record in user_resource table";
            throw new ResourceDaoUpdateException(this.msg, e);
        }
    }

    private int updateResource(UserResource obj)
            throws ResourceDaoUpdateException {
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            obj.setDateUpdated(ut.getDateCreated());
            obj.setUserId(ut.getLoginId());
            obj.addCriteria(UserResource.PROP_RSRCID, obj.getRsrcId());
            int rc = this.client.updateRow(obj);
            return rc;
        } catch (DatabaseException e) {
            this.msg = "Database error occurred during record update targeting user_resource table";
            throw new ResourceDaoUpdateException(this.msg, e);
        } catch (SystemException e) {
            this.msg = "Unable to establish UserTimestamp instance before updating record in user_resource table";
            throw new ResourceDaoUpdateException(this.msg, e);
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
     * @throws InvalidResourceDaoException
     *             The properties name, description, and URL are null or the
     *             properties resource type and resource sub type are less than
     *             or equal to zero.
     */
    protected void validateResource(ResourceDto resource)
            throws InvalidResourceDaoException {
        if (resource == null) {
            throw new InvalidResourceDaoException(
                    "DTO instance for resource is required and cannot be null");
        }
        if (!(resource instanceof WebServiceDto)) {
            throw new InvalidResourceDaoException(
                    "The target web service resource object is required to be of type, WebServiceDto, at runtime");
        }
        if (resource.getName() == null || resource.getName().length() <= 0) {
            this.msg = "Resource Name is required";
            throw new InvalidResourceDaoException(this.msg);
        }
        if (resource.getDescription() == null
                || resource.getDescription().length() <= 0) {
            this.msg = "Resource Description is required";
            throw new InvalidResourceDaoException(this.msg);
        }
        if (((WebServiceDto) resource).getRequestUrl() == null
                || ((WebServiceDto) resource).getRequestUrl().length() <= 0) {
            this.msg = "Resource URL is required";
            throw new InvalidResourceDaoException(this.msg);
        }
        if (resource.getTypeId() <= 0) {
            this.msg = "Resource type is required";
            throw new InvalidResourceDaoException(this.msg);
        }
        if (resource.getSubTypeId() <= 0) {
            this.msg = "Resource sub-type is required";
            throw new InvalidResourceDaoException(this.msg);
        }
    }

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
    @Override
    public int maintainResourceType(ResourceDto rsrcType)
            throws ResourceDaoException {
        this.validateResourceType(rsrcType);

        int rc = 0;
        UserResourceType r = new UserResourceType();
        r.setRsrcTypeId(rsrcType.getTypeId());
        r.setDescription(rsrcType.getTypeDescription());
        r.setDateCreated(rsrcType.getDateCreated());
        r.setDateUpdated(rsrcType.getDateUpdated());
        r.setUserId(rsrcType.getUpdateUserId());

        this.client.beginTrans();
        try {
            if (r.getRsrcTypeId() > 0) {
                rc = this.updateResourceType(r);
            }
            if (r.getRsrcTypeId() == 0) {
                rc = this.createResourceType(r);
                r.setRsrcTypeId(rc);
                rsrcType.setTypeId(rc);
            }
            this.client.commitTrans();
            return rc;
        } catch (ResourceDaoException e) {
            this.client.rollbackTrans();
            throw e;
        }

    }

    private int createResourceType(UserResourceType obj)
            throws ResourceDaoUpdateException {
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            obj.setDateCreated(ut.getDateCreated());
            obj.setDateUpdated(ut.getDateCreated());
            obj.setUserId(ut.getLoginId());
            int rc = this.client.insertRow(obj, true);
            return rc;
        } catch (DatabaseException e) {
            this.msg = "Database error occurred during record insert targeting user_resource_type table";
            throw new ResourceDaoUpdateException(this.msg, e);
        } catch (SystemException e) {
            this.msg = "Unable to establish UserTimestamp instance before inserting record in user_resource_type table";
            throw new ResourceDaoUpdateException(this.msg, e);
        }
    }

    private int updateResourceType(UserResourceType obj)
            throws ResourceDaoUpdateException {
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            obj.setDateUpdated(ut.getDateCreated());
            obj.setUserId(ut.getLoginId());
            obj.addCriteria(UserResourceType.PROP_RSRCTYPEID,
                    obj.getRsrcTypeId());
            int rc = this.client.updateRow(obj);
            return rc;
        } catch (DatabaseException e) {
            this.msg = "Database error occurred during record update targeting user_resource_type table";
            throw new ResourceDaoUpdateException(this.msg, e);
        } catch (SystemException e) {
            this.msg = "Unable to establish UserTimestamp instance before updating record in user_resource_type table";
            throw new ResourceDaoUpdateException(this.msg, e);
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
     * @throws InvalidResourceDaoException
     *             The description is null or equal to spaces.
     */
    protected void validateResourceType(ResourceDto resourceType)
            throws InvalidResourceDaoException {
        if (resourceType == null) {
            throw new InvalidResourceDaoException(
                    "DTO instance for resourct type is required and cannot be null");
        }
        if (resourceType.getTypeDescription() == null
                || resourceType.getTypeDescription().length() <= 0) {
            this.msg = "Resource Type Description is required";
            throw new InvalidResourceDaoException(this.msg);
        }
    }

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
    @Override
    public int maintainResourceSubType(ResourceDto rsrcSubType)
            throws ResourceDaoException {
        this.validateResourceSubType(rsrcSubType);

        int rc = 0;
        UserResourceSubtype r = new UserResourceSubtype();
        r.setRsrcSubtypeId(rsrcSubType.getSubTypeId());
        r.setRsrcTypeId(rsrcSubType.getTypeId());
        r.setName(rsrcSubType.getSubTypeName());
        r.setDescription(rsrcSubType.getSubTypeDescription());
        r.setDateCreated(rsrcSubType.getDateCreated());
        r.setDateUpdated(rsrcSubType.getDateUpdated());
        r.setUserId(rsrcSubType.getUpdateUserId());

        this.client.beginTrans();
        try {
            if (r.getRsrcSubtypeId() > 0) {
                rc = this.updateResourceSubType(r);
            }
            if (r.getRsrcSubtypeId() == 0) {
                rc = this.createResourceSubType(r);
                r.setRsrcSubtypeId(rc);
                rsrcSubType.setSubTypeId(rc);
            }
            this.client.commitTrans();
            return rc;
        } catch (ResourceDaoException e) {
            this.client.rollbackTrans();
            throw e;
        }

    }

    private int createResourceSubType(UserResourceSubtype obj)
            throws ResourceDaoUpdateException {
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            obj.setDateCreated(ut.getDateCreated());
            obj.setDateUpdated(ut.getDateCreated());
            obj.setUserId(ut.getLoginId());
            int rc = this.client.insertRow(obj, true);
            return rc;
        } catch (DatabaseException e) {
            this.msg = "Database error occurred during record insert targeting user_resource_subtype table";
            throw new ResourceDaoUpdateException(this.msg, e);
        } catch (SystemException e) {
            this.msg = "Unable to establish UserTimestamp instance before inserting record in user_resource_subtype table";
            throw new ResourceDaoUpdateException(this.msg, e);
        }
    }

    private int updateResourceSubType(UserResourceSubtype obj)
            throws ResourceDaoUpdateException {
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            obj.setDateUpdated(ut.getDateCreated());
            obj.setUserId(ut.getLoginId());
            obj.addCriteria(UserResourceSubtype.PROP_RSRCSUBTYPEID,
                    obj.getRsrcSubtypeId());
            int rc = this.client.updateRow(obj);
            return rc;
        } catch (DatabaseException e) {
            this.msg = "Database error occurred during record update targeting user_resource_subtype table";
            throw new ResourceDaoUpdateException(this.msg, e);
        } catch (SystemException e) {
            this.msg = "Unable to establish UserTimestamp instance before updating record in user_resource_subtype table";
            throw new ResourceDaoUpdateException(this.msg, e);
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
     * @throws InvalidResourceDaoException
     *             The name and description properties are null or spaces.
     */
    protected void validateResourceSubType(ResourceDto subType)
            throws InvalidResourceDaoException {
        if (subType == null) {
            throw new InvalidResourceDaoException(
                    "DTO instance for resource sub type is required and cannot be null");
        }
        if (subType.getSubTypeName() == null
                || subType.getSubTypeName().length() <= 0) {
            this.msg = "Resource Sub Type Name is required";
            throw new InvalidResourceDaoException(this.msg);
        }
        if (subType.getSubTypeDescription() == null
                || subType.getSubTypeDescription().length() <= 0) {
            this.msg = "Resource Sub Type Description is required";
            throw new InvalidResourceDaoException(this.msg);
        }
    }

    /**
     * Deletes a record from the user_resource table using the primary key.
     * 
     * @param resourceId
     *            the primary key of the record to delete
     * @return total number of rows effected.
     * @throws ResourceDaoException
     *             <i>resourceId</i> is less than or equal to zero.
     */
    @Override
    public int deleteResource(int resourceId) throws ResourceDaoException {
        if (resourceId <= 0) {
            this.msg = "Resource id is required and must be greater than zero";
            throw new ResourceDaoException(this.msg);
        }
        try {
            UserResource obj = new UserResource();
            obj.addCriteria(UserResource.PROP_RSRCID, resourceId);
            return this.client.deleteRow(obj);
        } catch (DatabaseException e) {
            throw new ResourceDaoException(e);
        }
    }

    /**
     * Deletes a record from the user_resource_type table using the primary key.
     * 
     * @param resourceTypeId
     *            the primary key of the record to delete
     * @return total number of rows effected.
     * @throws ResourceDaoException
     *             <i>resourceTypeId</i> is less than or equal to zero.
     */
    @Override
    public int deleteResourceType(int resourceTypeId)
            throws ResourceDaoException {
        if (resourceTypeId <= 0) {
            this.msg = "Resource type id is required and must be greater than zero";
            throw new ResourceDaoException(this.msg);
        }
        try {
            UserResourceType obj = new UserResourceType();
            obj.addCriteria(UserResourceType.PROP_RSRCTYPEID, resourceTypeId);
            return this.client.deleteRow(obj);
        } catch (DatabaseException e) {
            throw new ResourceDaoException(e);
        }
    }

    /**
     * Deletes a record from the user_resource_subtype table using the primary
     * key.
     * 
     * @param resourceSubTypeId
     *            the id of the resource sub type
     * @return total number of records effected.
     * @throws ResourceDaoException
     *             <i>resourceSubTypeId</i> is less than or equal to zero.
     */
    @Override
    public int deleteResourceSubType(int resourceSubTypeId)
            throws ResourceDaoException {
        if (resourceSubTypeId <= 0) {
            this.msg = "Resource sub type id is required and must be greater than zero";
            throw new ResourceDaoException(this.msg);
        }
        try {
            UserResourceSubtype obj = new UserResourceSubtype();
            obj.addCriteria(UserResourceSubtype.PROP_RSRCSUBTYPEID,
                    resourceSubTypeId);
            return this.client.deleteRow(obj);
        } catch (DatabaseException e) {
            throw new ResourceDaoException(e);
        }
    }

    /**
     * Method not supported.
     */
    @Override
    public List<ResourceDto> fetchResource(ResourceDto criteria)
            throws ResourceDaoException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Method not supported.
     */
    @Override
    public ResourceDto fetchResourceSubType(String resourceSubTypeName)
            throws ResourceDaoException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Method not supported.
     */
    @Override
    public int deleteResourceSubType(String subTypeName)
            throws ResourceDaoException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /**
     * Method not supported.
     */
    @Override
    public int deleteResource(ResourceDto criteria) throws ResourceDaoException {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }
}
