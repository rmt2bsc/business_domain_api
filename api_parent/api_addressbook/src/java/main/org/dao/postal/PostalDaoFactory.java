package org.dao.postal;

import com.RMT2Base;

/**
 * A factory for creating DAO instances that manage postal related data
 * resources.
 * 
 * @author rterrell
 * 
 */
public class PostalDaoFactory extends RMT2Base {

    /**
     * Default Constructor
     */
    public PostalDaoFactory() {
        return;
    }

    /**
     * Creates a database implementataion of {@link PostalLocationDao} interface
     * which is capable of accessing data from relational database management
     * system (RDBMS).
     * 
     * @return an instance of {@link PostalLocationDao}
     */
    public PostalLocationDao createRmt2OrmPostalDao() {
        PostalLocationDao dao = new Rmt2OrmPostalLocationDaoImpl();
        return dao;
    }

    /**
     * Creates a database implementataion of {@link PostalLocationDao} interface
     * which is capable of accessing data from relational database management
     * system (RDBMS).
     * 
     * @param appName
     *            application name
     * @return an instance of {@link PostalLocationDao}
     */
    public PostalLocationDao createRmt2OrmPostalDao(String appName) {
        PostalLocationDao dao = new Rmt2OrmPostalLocationDaoImpl(appName);
        return dao;
    }

    /**
     * Creates a JNDI/LDAP implementataion of {@link PostalLocationDao}
     * interface which is capable of accessing data from a directory server.
     * 
     * @return an instance of {@link PostalLocationDao}
     */
    public PostalLocationDao createLdapPostalDao() {
        PostalLocationDao dao = new LdapPostalLocationDaoImpl();
        return dao;
    }

    /**
     * Creates a database implementataion of {@link RegionCountryDao} interface
     * which is capable of accessing data from relational database management
     * system (RDBMS).
     * 
     * @return an instance of {@link RegionCountryDao}
     */
    public RegionCountryDao createRmt2OrmRegionCountryDao() {
        RegionCountryDao dao = new Rmt2OrmRegionCountryDaoImpl();
        return dao;
    }

    /**
     * Creates a database implementataion of {@link RegionCountryDao} interface
     * which is capable of accessing data from relational database management
     * system (RDBMS).
     * 
     * @param appName
     *            application name
     * 
     * @return an instance of {@link RegionCountryDao}
     */
    public RegionCountryDao createRmt2OrmRegionCountryDao(String appName) {
        RegionCountryDao dao = new Rmt2OrmRegionCountryDaoImpl();
        return dao;
    }

    /**
     * Creates a JNDI/LDAP implementataion of {@link RegionCountryDao} interface
     * which is capable of accessing data from a LDAP server.
     * 
     * @return an instance of {@link RegionCountryDao}
     */
    public RegionCountryDao createLdapRegionCountryDao() {
        RegionCountryDao dao = new LdapRegionCountryDaoImpl();
        return dao;
    }

}
