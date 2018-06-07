package org.dao.resources;

import org.dao.mapping.orm.rmt2.UserResource;
import org.dto.ResourceDto;
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
     * Build Extended User selection criteria from a UserDto instance
     * 
     * @param criteria an instance of {@link UserDto}
     * @return an instance of [{@link VwUser}
     */
    public static final UserResource buildResourceCriteria(ResourceDto criteria) {
        UserResource user = new UserResource();

        if (criteria != null) {
            if (criteria.getUid() > 0) {
                user.addCriteria(UserResource.PROP_RSRCID, criteria.getUid());
            }
            if (criteria.getTypeId() > 0) {
                user.addCriteria(UserResource.PROP_RSRCTYPEID, criteria.getTypeId());
            }
            if (criteria.getName() != null) {
                user.addLikeClause(UserResource.PROP_NAME, criteria.getName());
            }
            if (criteria.getSubTypeId() > 0) {
                user.addCriteria(UserResource.PROP_RSRCSUBTYPEID, criteria.getSubTypeId());
            }
            if (criteria.isSecured() != null) {
                user.addCriteria(UserResource.PROP_SECURED, criteria.isSecured() ? 1 : 0);
            }
            if (criteria.getDescription() != null) {
                user.addLikeClause(UserResource.PROP_DESCRIPTION, criteria.getDescription());
            }
        }
        return user;
    }

}
