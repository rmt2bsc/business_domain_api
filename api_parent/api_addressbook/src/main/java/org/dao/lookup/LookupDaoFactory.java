package org.dao.lookup;

import com.RMT2Base;

/**
 * A factory for creating DAO instances that manage Lookup related data
 * resources.
 * 
 * @author rterrell
 * 
 */
public class LookupDaoFactory extends RMT2Base {

    /**
     * Default Constructor
     */
    public LookupDaoFactory() {
        return;
    }

    /**
     * Creates a database implementataion of LookupDao interface which is
     * capable of accessing data from relational database management system
     * (RDBMS).
     * 
     * @return an instance of {@link LookupDao}
     */
    public LookupDao createRmt2OrmDao() {
        LookupDao dao = new Rmt2OrmLookupDaoImpl();
        return dao;
    }

    /**
     * Creates a database implementataion of LookupDao interface which is
     * capable of accessing data from relational database management system
     * (RDBMS).
     * 
     * @param appName
     *            application name
     * @return an instance of {@link LookupDao}
     */
    public LookupDao createRmt2OrmDao(String appName) {
        LookupDao dao = new Rmt2OrmLookupDaoImpl(appName);
        return dao;
    }

    /**
     * Creates a JNDI implementataion of LookupDao interface which is capable of
     * accessing data from a LDAP data source
     * 
     * @return an instance of {@link LookupDao}
     */
    public LookupDao createLdapDao() {
        LookupDao dao = new LdapLookupDaoImpl();
        return dao;
    }

}
