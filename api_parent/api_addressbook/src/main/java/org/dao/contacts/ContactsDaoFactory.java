package org.dao.contacts;

import com.RMT2Base;
import com.api.persistence.DaoClient;

/**
 * A factory for creating DAO instances that manage Contacts data resources.
 * 
 * @author rterrell
 * 
 */
public class ContactsDaoFactory extends RMT2Base {

    /**
     * Default Constructor
     */
    public ContactsDaoFactory() {
        return;
    }

    /**
     * Creates a database implementataion of ContactsDao interface which is
     * capable of accessing data from relational database management system
     * (RDBMS).
     * 
     * @return an instance of {@link ContactsDao}
     */
    public ContactsDao createRmt2OrmDao() {
        ContactsDao dao = new Rmt2OrmDefaultContactDaoImpl();
        return dao;
    }

    /**
     * Creates a database implementataion of ContactsDao interface which is
     * capable of accessing data from relational database management system
     * (RDBMS).
     * 
     * @param appName
     *            application name
     * @return an instance of {@link ContactsDao}
     */
    public ContactsDao createRmt2OrmDao(String appName) {
        ContactsDao dao = new Rmt2OrmDefaultContactDaoImpl(appName);
        return dao;
    }

    /**
     * Creates a database implementataion of ContactsDao interface using a
     * shared DAO connection
     * 
     * @param dao
     *            instance of {@link DaoClient}
     * @return an instance of {@link ContactsDao}
     */
    public ContactsDao createRmt2OrmDao(DaoClient dao) {
        ContactsDao obj = new Rmt2OrmDefaultContactDaoImpl(dao.getClient());
        return obj;
    }
}
