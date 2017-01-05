package org.dao.transaction.disbursements;

import com.RMT2Base;
import com.api.persistence.DaoClient;

/**
 * Factory class for creating disbursement Transaction DAO related objects.
 * 
 * @author Roy Terrell
 * 
 */
public class DisbursementsDaoFactory extends RMT2Base {

    /**
     * Default constructor
     */
    public DisbursementsDaoFactory() {
        return;
    }

    /**
     * Creates an instance of <i>DisbursementsDao</i> using the RMT2 transaction
     * ORM implementation.
     * 
     * @return an instance of {@link DisbursementsDao}
     */
    public DisbursementsDao createRmt2OrmDao() {
        Rmt2DisbursementsDaoImpl dao = new Rmt2DisbursementsDaoImpl();
        return dao;
    }

    /**
     * Creates an instance of <i>DisbursementsDao</i> using the RMT2 ORM basic
     * maintenance implementation.
     * 
     * @param appName
     *            application name
     * 
     * @return an instance of {@link DisbursementsDao}
     */
    public DisbursementsDao createRmt2OrmDao(String appName) {
        Rmt2DisbursementsDaoImpl d = new Rmt2DisbursementsDaoImpl(appName);
        return d;
    }

    /**
     * Creates an instance of <i>DisbursementsDao</i> using the RMT2 ORM basic
     * maintenance implementation.
     * 
     * @param client
     *            an instnace of {@link PersistenceClient}
     * 
     * @return an instance of {@link DisbursementsDao}
     */
    public DisbursementsDao createRmt2OrmDao(DaoClient dao) {
        Rmt2DisbursementsDaoImpl d = new Rmt2DisbursementsDaoImpl(
                dao.getClient());
        d.setDaoUser(dao.getDaoUser());
        return d;
    }

}
