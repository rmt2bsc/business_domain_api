package org.dao.roles;

import com.RMT2Base;

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
    public RoleDao createRmt2OrmDao() {
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
    public RoleDao createRmt2OrmDao(String appName) {
        RoleDao dao = new Rmt2OrmRoleDaoImpl(appName);
        return dao;
    }

    /**
     * Creates a LDAp implementataion of RoleDao interface which is capable of
     * accessing data from a LDAP server.
     * 
     * @return an instance of {@link RoleDao}
     */
    public RoleDao createLdapDao() {
        RoleDao dao = new LdapRoleDaoImpl();
        return dao;
    }

}
