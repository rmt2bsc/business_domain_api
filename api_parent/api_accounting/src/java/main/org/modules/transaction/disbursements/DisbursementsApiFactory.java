package org.modules.transaction.disbursements;

import org.modules.CommonAccountingConst;

import com.RMT2Base;
import com.api.persistence.DaoClient;

/**
 * A factory for creating resources that pertain to the disbursement Transaction
 * API module.
 * 
 * @author Roy Terrell
 * 
 */
public class DisbursementsApiFactory extends RMT2Base {

    /**
     * Default method.
     */
    public DisbursementsApiFactory() {
        return;
    }

    /**
     * Creates an instance of {@link DisbursementsApi} using the transaction api
     * implementation.
     * 
     * @return an instance of {@link DisbursementsApi}
     */
    public DisbursementsApi createApi() {
        // DisbursementsApiImpl api = new DisbursementsApiImpl();
        // return api;
        return this.createApi(CommonAccountingConst.DEFAULT_CONTEXT_NAME);
    }

    /**
     * Creates an instance of {@link DisbursementsApi} using the transaction api
     * implementation.
     * 
     * @param appName
     * @return an instance of {@link DisbursementsApi}
     */
    public DisbursementsApi createApi(String appName) {
        DisbursementsApiImpl api = new DisbursementsApiImpl(appName);
        return api;
    }

    /**
     * Creates an instance of {@link DisbursementsApi} using the transaction api
     * implementation and initializing it with a DAO connection.
     * 
     * @param connection
     *            an instance of {@link DaoClient} used to share the DAO
     *            connection.
     * 
     * @return an instance of {@link DisbursementsApi} or null if
     *         <i>connection</i> is null.
     */
    public DisbursementsApi createApi(DaoClient connection) {
        if (connection == null) {
            return null;
        }
        DisbursementsApiImpl api = new DisbursementsApiImpl(connection);
        api.setApiUser(connection.getDaoUser());
        return api;
    }

}
