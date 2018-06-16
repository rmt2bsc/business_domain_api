package org.dao.transaction.receipts;

import com.RMT2Base;
import com.api.persistence.DaoClient;

/**
 * Factory class for creating cash receipt Transaction DAO related objects.
 * 
 * @author Roy Terrell
 * 
 */
public class CashReceiptDaoFactory extends RMT2Base {

    /**
     * Default constructory
     */
    public CashReceiptDaoFactory() {
        return;
    }

    /**
     * Creates an instance of <i>CashReceiptDao</i> using the RMT2 transaction
     * ORM implementation.
     * 
     * @return an instance of {@link CashReceiptDao}
     */
    public CashReceiptDao createRmt2OrmDao() {
        Rmt2CashReceiptDaoImpl dao = new Rmt2CashReceiptDaoImpl();
        return dao;
    }

    /**
     * Creates an instance of <i>CashReceiptDao</i> using the RMT2 ORM basic
     * maintenance implementation.
     * 
     * @param appName
     *            application name
     * 
     * @return an instance of {@link CashReceiptDao}
     */
    public CashReceiptDao createRmt2OrmDao(String appName) {
        Rmt2CashReceiptDaoImpl d = new Rmt2CashReceiptDaoImpl(appName);
        return d;
    }

    /**
     * Creates an instance of <i>CashReceiptDao</i> using the RMT2 ORM basic
     * maintenance implementation.
     * 
     * @param client
     *            an instnace of {@link PersistenceClient}
     * 
     * @return an instance of {@link CashReceiptDao}
     */
    public CashReceiptDao createRmt2OrmDao(DaoClient dao) {
        Rmt2CashReceiptDaoImpl d = new Rmt2CashReceiptDaoImpl(dao.getClient());
        d.setDaoUser(dao.getDaoUser());
        return d;
    }

}
