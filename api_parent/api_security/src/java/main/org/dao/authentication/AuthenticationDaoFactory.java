package org.dao.authentication;

import com.RMT2Base;

/**
 * A factory for DAO instances that manage user, group, and role related
 * entities.
 * 
 * @author rterrell
 * 
 */
public class AuthenticationDaoFactory extends RMT2Base {

    /**
     * Default method.
     */
    public AuthenticationDaoFactory() {
        return;
    }

    /**
     * Creates a database implementataion of UserDao interface which is capable
     * of accessing data from relational database management system (RDBMS).
     * 
     * @return an instance of {@link AuthenticationDao}
     */
    public AuthenticationDao createRmt2OrmDao() {
        AuthenticationDao dao = new Rmt2OrmAuthenticationDaoImpl();
        return dao;
    }

    /**
     * Creates a database implementataion of UserDao interface which is capable
     * of accessing data from relational database management system (RDBMS).
     * 
     * @param appName
     *            application name
     * @return an instance of {@link AuthenticationDao}
     */
    public AuthenticationDao createRmt2OrmDao(String appName) {
        AuthenticationDao dao = new Rmt2OrmAuthenticationDaoImpl(appName);
        return dao;
    }

    /**
     * Creates a LDAP implementataion of UserDao interface which is capable of
     * accessing data from Lightweight Directory Access Protocol server.
     * 
     * @return Always return null.
     */
    public AuthenticationDao createLdapDao() {
        AuthenticationDao dao = new LdapAuthenticationDaoImpl();
        return dao;
    }

    /**
     * Creates a LDAP implementataion of UserDao interface pointing to a
     * specific profile.
     * <p>
     * This object is capable of accessing data from Lightweight Directory
     * Access Protocol server.
     * 
     * @param ldapProfile
     * @return
     */
    public AuthenticationDao createLdapDao(String ldapProfile) {
        AuthenticationDao dao = new LdapAuthenticationDaoImpl(ldapProfile);
        return dao;
    }
}
