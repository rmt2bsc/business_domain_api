package org.dao.application;

import org.dao.mapping.orm.rmt2.Application;
import org.dto.ApplicationDto;

import com.RMT2Base;
import com.api.persistence.DaoClient;

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
     * Creates an instance of the {@link AudioVideoDao} using the RMT2 ORM basic
     * DAO implementation.
     * 
     * @param dao
     *            an instnace of {@link PersistenceClient}
     * @return an instance of {@link AudioVideoDao}
     */
    public AppDao createRmt2OrmDao(DaoClient dao) {
        AppDao d = new Rmt2OrmApplicationDaoImpl(dao.getClient());
        return d;
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

    /**
     * Creates and returns an <i>Application</i> object containing selection
     * criteria obtained from an instance of <i>ApplicationDto</i>.
     * 
     * @param criteria
     *            an instance of {@link ApplicationDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>Application Id</li>
     *            <li>App Name</li>
     *            <li>App Description</li>
     *            </ul>
     * @return an instance of {@link Application}
     */
    public static final Application createCriteria(ApplicationDto criteria) {
        Application obj = new Application();
        if (criteria != null) {
            if (criteria.getApplicationId() > 0) {
                obj.addCriteria(Application.PROP_APPID, criteria.getApplicationId());
            }
            if (criteria.getAppName() != null) {
                obj.addLikeClause(Application.PROP_NAME, criteria.getAppName());
            }
            if (criteria.getAppDescription() != null) {
                obj.addLikeClause(Application.PROP_DESCRIPTION, criteria.getAppDescription());
            }
            if (criteria.getCriteria() != null) {
                obj.addCustomCriteria(criteria.getCriteria());
            }
        }
        return obj;
    }
}
