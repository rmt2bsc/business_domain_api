package org.dao.application;

import com.RMT2Base;

/**
 * A factory for DAO instances that manage application related entities.
 * 
 * @author rterrell
 * 
 */
public class AppDaoFactory extends RMT2Base {

    /**
     * Default method.
     */
    public AppDaoFactory() {
        return;
    }

    /**
     * Creates a database implementataion of ApplicationDao interface which is
     * capable of accessing data from relational database management system
     * (RDBMS).
     * 
     * @return an instance of {@link AppDao}
     */
    public AppDao createRmt2OrmDao() {
        AppDao dao = new Rmt2OrmApplicationDaoImpl();
        return dao;
    }

    /**
     * Creates a database implementataion of ApplicationDao interface which is
     * capable of accessing data from relational database management system
     * (RDBMS).
     * 
     * @param appName
     *            application name
     * @return an instance of {@link AppDao}
     */
    public AppDao createRmt2OrmDao(String appName) {
        AppDao dao = new Rmt2OrmApplicationDaoImpl(appName);
        return dao;
    }

    /**
     * Creates a JNDI implementataion of ApplicationDao interface which is
     * capable of accessing data from a Lightweight Directory Access Protocol
     * server.
     * 
     * @return Always return null.
     */
    public AppDao createLdapDao() {
        AppDao dao = new LdapApplicationDaoImpl();
        return dao;
    }
}
