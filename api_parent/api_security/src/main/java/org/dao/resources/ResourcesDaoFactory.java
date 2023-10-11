package org.dao.resources;

import org.dao.mapping.orm.rmt2.UserResource;
import org.dao.mapping.orm.rmt2.UserResourceSubtype;
import org.dao.mapping.orm.rmt2.UserResourceType;
import org.dao.mapping.orm.rmt2.VwResource;
import org.dao.mapping.orm.rmt2.VwResourceType;
import org.dto.ResourceDto;
import org.dto.WebServiceDto;
import org.modules.SecurityConstants;

import com.RMT2Base;

/**
 * A factory for creating DAO instances that manage resources related entities.
 * 
 * @author rterrell
 * 
 */
public class ResourcesDaoFactory extends RMT2Base {

    /**
     * Default Constructor
     */
    public ResourcesDaoFactory() {
        return;
    }

    /**
     * Creates a database implementataion of ResourceDao interface which is
     * capable of accessing data from relational database management system
     * (RDBMS).
     * 
     * @return an instance of {@link ResourceDao}
     */
    public static final ResourceDao createRmt2OrmDao() {
        ResourceDao dao = createRmt2OrmDao(SecurityConstants.APP_NAME);
        return dao;
    }

    /**
     * Creates a database implementataion of ResourceDao interface which is
     * capable of accessing data from relational database management system
     * (RDBMS).
     * 
     * @param appName
     *            application name
     * @return an instance of {@link ResourceDao}
     */
    public static final ResourceDao createRmt2OrmDao(String appName) {
        ResourceDao dao = new Rmt2OrmWebServicesDaoImpl(appName);
        return dao;
    }

    /**
     * Creates a LDAP implementataion of ResourceDao interface which is capable
     * of accessing data from a LDAP server.
     * 
     * @return an instance of {@link ResourceDao}
     */
    public static final ResourceDao createLdapDao() {
        ResourceDao dao = new LdapWebServicesDaoImpl();
        return dao;
    }

    /**
     * Creates a JNDI implementataion of ComputerResourceDao interface which is
     * capable of accessing data pertaining one or more application server
     * configurations from a LDAP server.
     * 
     * @return an instance of {@link ComputerResourceDao}
     */
    public static final ComputerResourceDao createLdapComputerAppServerDao() {
        ComputerResourceDao dao = new LdapComputerAppServerDaoImpl();
        return dao;
    }
    
    /**
     * Build selection criteria for retrieving UserResource data
     * 
     * @param criteria an instance of {@link ResourceDto}
     * @return an instance of [{@link UserResource}
     */
    public static final UserResource createCriteriaResource(ResourceDto criteria) {
        UserResource results = new UserResource();

        if (criteria != null) {
            if (criteria.getUid() > 0) {
                results.addCriteria(UserResource.PROP_RSRCID, criteria.getUid());
            }
            if (criteria.getTypeId() > 0) {
                results.addCriteria(UserResource.PROP_RSRCTYPEID, criteria.getTypeId());
            }
            if (criteria.getName() != null) {
                results.addLikeClause(UserResource.PROP_NAME, criteria.getName());
            }
            if (criteria.getSubTypeId() > 0) {
                results.addCriteria(UserResource.PROP_RSRCSUBTYPEID, criteria.getSubTypeId());
            }
            if (criteria.getSecured() != null && criteria.getSecured() != -1) {
                results.addCriteria(UserResource.PROP_SECURED, criteria.getSecured());
            }
            if (criteria.getDescription() != null) {
                results.addLikeClause(UserResource.PROP_DESCRIPTION, criteria.getDescription());
            }
        }
        return results;
    }

    /**
     * Build selection criteria for retrieving VwResource data
     * 
     * @param criteria an instance of {@link ResourceDto}
     * @return an instance of [{@link VwResource}
     */
    public static final VwResource createCriteriaResourceExt(ResourceDto criteria) {
        VwResource rsrc = new VwResource();

        // Setup criteria
        if (criteria != null) {
            // Check for UserResource related predicates
            if (criteria.getUid() > 0) {
                rsrc.addCriteria(VwResource.PROP_RSRCID, criteria.getUid());
            }
            if (criteria.getName() != null) {
                rsrc.addLikeClause(VwResource.PROP_NAME, criteria.getName());
            }
            if (criteria.getDescription() != null) {
                rsrc.addLikeClause(VwResource.PROP_DESCRIPTION, criteria.getDescription());
            }
            if (((WebServiceDto) criteria).isQuerySecureFlag()) {
                rsrc.addCriteria(VwResource.PROP_SECURED, ((WebServiceDto) criteria).getSecured());
            }

            // Check for UserResourceType related predicates
            if (criteria.getTypeId() > 0) {
                rsrc.addCriteria(VwResource.PROP_RSRCTYPEID, criteria.getTypeId());
            }
            if (criteria.getTypeDescription() != null) {
                rsrc.addLikeClause(VwResource.PROP_TYPEDESCR, criteria.getTypeDescription());
            }

            // Check for UserResourceSubtype related predicates
            if (criteria.getSubTypeId() > 0) {
                rsrc.addCriteria(VwResource.PROP_RSRCSUBTYPEID, criteria.getSubTypeId());
            }
            if (criteria.getSubTypeName() != null) {
                rsrc.addLikeClause(VwResource.PROP_SUBTYPENAME, criteria.getSubTypeName());
            }
            if (criteria.getSubTypeDescription() != null) {
                rsrc.addLikeClause(VwResource.PROP_SUBTYPEDESC, criteria.getSubTypeDescription());
            }
        }
        return rsrc;
    }
    
    /**
     * Build selection criteria for retrieving VwResourceType data
     * 
     * @param criteria
     *            an instance of {@link ResourceDto}
     * @return an instance of [{@link VwResourceType}
     */
    public static final VwResourceType createCriteriaVwResourceType(ResourceDto criteria) {
        VwResourceType rsrc = new VwResourceType();

        // Setup criteria
        if (criteria != null) {
            // Check for VwResourceType related predicates
            if (criteria.getTypeId() > 0) {
                rsrc.addCriteria(VwResourceType.PROP_RESRCTYPEID, criteria.getTypeId());
            }
            if (criteria.getTypeDescription() != null) {
                rsrc.addLikeClause(VwResourceType.PROP_RESRCTYPENAME, criteria.getTypeDescription());
            }

            // Check for UserResourceSubtype related predicates
            if (criteria.getSubTypeId() > 0) {
                rsrc.addCriteria(VwResourceType.PROP_RESRCSUBTYPEID, criteria.getSubTypeId());
            }
            if (criteria.getSubTypeName() != null) {
                rsrc.addLikeClause(VwResourceType.PROP_RESRCSUBTYPENAME, criteria.getSubTypeName());
            }
            if (criteria.getSubTypeDescription() != null) {
                rsrc.addLikeClause(VwResourceType.PROP_RESRCSUBTYPEDESC, criteria.getSubTypeDescription());
            }
        }
        return rsrc;
    }

    /**
     * Build selection criteria for retrieving UserResourceSubtype data
     * 
     * @param criteria
     *            an instance of {@link ResourceDto}
     * @return an instance of [{@link UserResourceSubtype}
     */
    public static final UserResourceSubtype createCriteriaResourceSubtype(ResourceDto criteria) {
        UserResourceSubtype results = new UserResourceSubtype();

        if (criteria != null) {
            if (criteria.getSubTypeId() > 0) {
                results.addCriteria(UserResourceSubtype.PROP_RSRCSUBTYPEID, criteria.getSubTypeId());
            }
            if (criteria.getTypeId() > 0) {
                results.addCriteria(UserResourceSubtype.PROP_RSRCTYPEID, criteria.getTypeId());
            }
            if (criteria.getName() != null) {
                results.addLikeClause(UserResourceSubtype.PROP_NAME, criteria.getName());
            }
            if (criteria.getDescription() != null) {
                results.addLikeClause(UserResourceSubtype.PROP_DESCRIPTION, criteria.getDescription());
            }
        }
        return results;
    }
    
    /**
     * Build selection criteria for retrieving UserResourceType data
     * 
     * @param criteria an instance of {@link ResourceDto}
     * @return an instance of [{@link UserResourceType}
     */
    public static final UserResourceType createCriteriaResourceType(ResourceDto criteria) {
        UserResourceType results = new UserResourceType();

        if (criteria != null) {
            if (criteria.getTypeId() > 0) {
                results.addCriteria(UserResourceType.PROP_RSRCTYPEID, criteria.getTypeId());
            }
            if (criteria.getTypeDescription() != null) {
                results.addLikeClause(UserResourceType.PROP_DESCRIPTION, criteria.getTypeDescription());
            }
        }
        return results;
    }
}
