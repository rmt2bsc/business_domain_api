package org.dao.roles;

import org.dao.mapping.orm.rmt2.Roles;
import org.dao.mapping.orm.rmt2.VwAppRoles;
import org.dao.mapping.orm.rmt2.VwUserAppRoles;
import org.dto.CategoryDto;

import com.RMT2Base;
import com.api.persistence.DaoClient;

/**
 * A factory for DAO instances that manage role related entities.
 * 
 * @author rterrell
 * 
 */
public class RoleDaoFactory extends RMT2Base {

    /**
     * Default Constructor
     */
    public RoleDaoFactory() {
        return;
    }

    /**
     * Creates a database implementataion of RoleDao interface which is capable
     * of accessing data from relational database management system (RDBMS).
     * 
     * @return an instance of {@link RoleDao}
     */
    public static final RoleDao createRmt2OrmDao() {
        RoleDao dao = new Rmt2OrmRoleDaoImpl();
        return dao;
    }

    /**
     * Creates a database implementataion of RoleDao interface which is capable
     * of accessing data from relational database management system (RDBMS).
     * 
     * @param appName
     *            application name
     * @return an instance of {@link RoleDao}
     */
    public static final RoleDao createRmt2OrmDao(String appName) {
        RoleDao dao = new Rmt2OrmRoleDaoImpl(appName);
        return dao;
    }
    
    /**
     * 
     * @param dao
     * @return
     */
    public static final RoleDao createRmt2OrmDao(DaoClient dao) {
        Rmt2OrmRoleDaoImpl d = new Rmt2OrmRoleDaoImpl(dao.getClient());
        d.setDaoUser(dao.getDaoUser());
        return d;
    }
    

    /**
     * Creates a LDAp implementataion of RoleDao interface which is capable of
     * accessing data from a LDAP server.
     * 
     * @return an instance of {@link RoleDao}
     */
    public static final RoleDao createLdapDao() {
        RoleDao dao = new LdapRoleDaoImpl();
        return dao;
    }

    /**
     * 
     * @param criteria
     * @return
     */
    public static final Roles createCriteriaRoles(CategoryDto criteria) {
        Roles obj = new Roles();
        if (criteria != null) {
            if (criteria.getRoleId() > 0) {
                obj.addCriteria(Roles.PROP_ROLEID, criteria.getRoleId());
            }
            if (criteria.getRoleName() != null) {
                obj.addLikeClause(Roles.PROP_NAME, criteria.getRoleName());
            }
            if (criteria.getRoleDescription() != null) {
                obj.addLikeClause(Roles.PROP_DESCRIPTION, criteria.getRoleDescription());
            }
        }
        return obj;
    }
    
    /**
     * 
     * @param criteria
     * @return
     */
    public static final VwAppRoles createCriteriaVwAppRoles(CategoryDto criteria) {
        VwAppRoles obj = new VwAppRoles();
        if (criteria != null) {
            if (criteria.getApplicationId() > 0) {
                obj.addCriteria(VwAppRoles.PROP_APPLICATIONID, criteria.getApplicationId());
            }
            if (criteria.getAppName() != null) {
                obj.addLikeClause(VwAppRoles.PROP_APPNAME, criteria.getAppName());
            }
            if (criteria.getAppRoleId() > 0) {
                obj.addCriteria(VwAppRoles.PROP_APPROLEID, criteria.getAppRoleId());
            }
            if (criteria.getRoleId() > 0) {
                obj.addCriteria(VwAppRoles.PROP_ROLEID, criteria.getRoleId());
            }
            if (criteria.getRoleName() != null) {
                obj.addLikeClause(VwAppRoles.PROP_ROLENAME, criteria.getRoleName());
            }
            if (criteria.getAppRoleCode() != null) {
                obj.addLikeClause(VwAppRoles.PROP_APPROLECODE, criteria.getAppRoleCode());
            }
        }
        return obj;
    }

    /**
     * 
     * @param criteria
     * @return
     */
    public static final VwUserAppRoles createCriteriaVwUserAppRoles(CategoryDto criteria) {
        VwUserAppRoles obj = new VwUserAppRoles();
        if (criteria != null) {
            if (criteria.getUsername() != null) {
                obj.addCriteria(VwUserAppRoles.PROP_USERNAME, criteria.getUsername());
            }
            if (criteria.getLoginUid() > 0) {
                obj.addCriteria(VwUserAppRoles.PROP_LOGINUID, criteria.getLoginUid());
            }
            if (criteria.getFirstname() != null) {
                obj.addCriteria(VwUserAppRoles.PROP_FIRSTNAME, criteria.getFirstname());
            }
            if (criteria.getLastname() != null) {
                obj.addCriteria(VwUserAppRoles.PROP_LASTNAME, criteria.getLastname());
            }
            if (criteria.getAppRoleId() > 0) {
                obj.addCriteria(VwUserAppRoles.PROP_APPROLEID, criteria.getAppRoleId());
            }
            if (criteria.getApplicationId() > 0) {
                obj.addCriteria(VwUserAppRoles.PROP_APPLICATIONID, criteria.getApplicationId());
            }
            if (criteria.getAppName() != null) {
                obj.addCriteria(VwUserAppRoles.PROP_APPNAME, criteria.getAppName());
            }
            if (criteria.getRoleId() > 0) {
                obj.addCriteria(VwUserAppRoles.PROP_ROLEID, criteria.getRoleId());
            }
        }
        return obj;
    }
}
