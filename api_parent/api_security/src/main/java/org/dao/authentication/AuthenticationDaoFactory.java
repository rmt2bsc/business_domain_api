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
     * Creates a LDAP implementataion of UserDao interface which is capable of
     * accessing data from Lightweight Directory Access Protocol server.
     * 
     * @return Always return null.
     */
    public static final AuthenticationDao createLdapDao() {
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
    public static final AuthenticationDao createLdapDao(String ldapProfile) {
        AuthenticationDao dao = new LdapAuthenticationDaoImpl(ldapProfile);
        return dao;
    }
}
