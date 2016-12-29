package org.dao.user;

import com.RMT2Base;

/**
 * A factory for DAO instances that manage user related entities.
 * 
 * @author rterrell
 * 
 */
public class UserDaoFactory extends RMT2Base {

    /**
     * Default Constructor
     */
    public UserDaoFactory() {
        return;
    }

    /**
     * Creates a database implementataion of UserDao interface which is capable
     * of accessing data from relational database management system (RDBMS).
     * 
     * @return an instance of {@link UserDao}
     */
    public UserDao createRmt2OrmDao() {
        UserDao dao = new Rmt2OrmUserDaoImpl();
        return dao;
    }

    /**
     * Creates a database implementataion of UserDao interface which is capable
     * of accessing data from relational database management system (RDBMS).
     * 
     * @param appName
     *            application name
     * @return an instance of {@link UserDao}
     */
    public UserDao createRmt2OrmDao(String appName) {
        UserDao dao = new Rmt2OrmUserDaoImpl(appName);
        return dao;
    }

    /**
     * Creates a LDAP implementataion of UserDao interface which is capable of
     * accessing data from a directory server by using an anonymous connection.
     * 
     * @return an instance of {@link UserDao}
     */
    public UserDao createLdapDao() {
        UserDao dao = new LdapUserDaoImpl();
        return dao;
    }

}
