package org.modules.transaction.purchases.vendor;

import org.modules.CommonAccountingConst;

import com.RMT2Base;
import com.api.persistence.DaoClient;

/**
 * A factory for creating resources that pertain to the vendor purchases
 * Transaction API module.
 * 
 * @author Roy Terrell
 * 
 */
public class VendorPurchasesApiFactory extends RMT2Base {

    /**
     * Default method.
     */
    public VendorPurchasesApiFactory() {
        return;
    }

    /**
     * Creates an instance of {@link VendorPurchasesApi} using the transaction
     * api implementation.
     * 
     * @return an instance of {@link VendorPurchasesApi}
     */
    public VendorPurchasesApi createApi() {
        return this.createApi(CommonAccountingConst.DEFAULT_CONTEXT_NAME);
    }

    /**
     * Creates an instance of {@link VendorPurchasesApi} using the transaction
     * api implementation.
     * 
     * @param appName
     * @return an instance of {@link VendorPurchasesApi}
     */
    public VendorPurchasesApi createApi(String appName) {
        VendorPurchasesApiImpl api = new VendorPurchasesApiImpl(appName);
        return api;
    }

    /**
     * Creates an instance of {@link VendorPurchasesApi} using the transaction
     * api implementation and initializing it with a DAO connection.
     * 
     * @param connection
     *            an instance of {@link DaoClient} used to share the DAO
     *            connection.
     * 
     * @return an instance of {@link VendorPurchasesApi} or null if
     *         <i>connection</i> is null.
     */
    public VendorPurchasesApi createApi(DaoClient connection) {
        if (connection == null) {
            return null;
        }
        VendorPurchasesApiImpl api = new VendorPurchasesApiImpl(connection);
        api.setApiUser(connection.getDaoUser());
        return api;
    }

}
