package org.dao.contacts;

import com.RMT2Base;

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

}
