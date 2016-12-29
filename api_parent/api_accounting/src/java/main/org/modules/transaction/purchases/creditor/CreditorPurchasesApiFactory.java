package org.modules.transaction.purchases.creditor;

import org.modules.CommonAccountingConst;

import com.RMT2Base;
import com.api.persistence.DaoClient;

/**
 * A factory for creating resources that pertain to the creditor purchases
 * Transaction API module.
 * 
 * @author Roy Terrell
 * 
 */
public class CreditorPurchasesApiFactory extends RMT2Base {

    /**
     * Default method.
     */
    public CreditorPurchasesApiFactory() {
        return;
    }

    /**
     * Creates an instance of {@link CreditorPurchasesApi} using the transaction
     * api implementation.
     * 
     * @return an instance of {@link CreditorPurchasesApi}
     */
    public CreditorPurchasesApi createApi() {
        // CreditorPurchasesApiImpl api = new CreditorPurchasesApiImpl();
        // return api;
        return this.createApi(CommonAccountingConst.DEFAULT_CONTEXT_NAME);
    }

    /**
     * Creates an instance of {@link CreditorPurchasesApi} using the transaction
     * api implementation.
     * 
     * @param appName
     * @return an instance of {@link CreditorPurchasesApi}
     */
    public CreditorPurchasesApi createApi(String appName) {
        CreditorPurchasesApiImpl api = new CreditorPurchasesApiImpl(appName);
        return api;
    }

    /**
     * Creates an instance of {@link CreditorPurchasesApi} using the transaction
     * api implementation and initializing it with a DAO connection.
     * 
     * @param connection
     *            an instance of {@link DaoClient} used to share the DAO
     *            connection.
     * 
     * @return an instance of {@link CreditorPurchasesApi} or null if
     *         <i>connection</i> is null.
     */
    public CreditorPurchasesApi createApi(DaoClient connection) {
        if (connection == null) {
            return null;
        }
        CreditorPurchasesApiImpl api = new CreditorPurchasesApiImpl(connection);
        api.setApiUser(connection.getDaoUser());
        return api;
    }

}
