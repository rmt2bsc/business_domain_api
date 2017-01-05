package org.modules.transaction.receipts;

import org.modules.CommonAccountingConst;

import com.RMT2Base;
import com.api.persistence.DaoClient;

/**
 * A factory for creating resources that pertain to the Sales Order Cash Receipt
 * Transaction API module.
 * 
 * @author Roy Terrell
 * 
 */
public class CashReceiptApiFactory extends RMT2Base {

    /**
     * Default method.
     */
    public CashReceiptApiFactory() {
        return;
    }

    /**
     * Creates an instance of {@link CashReceiptApi} using the transaction api
     * implementation.
     * 
     * @return an instance of {@link CashReceiptApi}
     */
    public CashReceiptApi createApi() {
        // CashReceiptApiImpl api = new CashReceiptApiImpl();
        // return api;
        return this.createApi(CommonAccountingConst.DEFAULT_CONTEXT_NAME);
    }

    /**
     * Creates an instance of {@link CashReceiptApi} using the transaction api
     * implementation.
     * 
     * @param appName
     *            application name
     * @return an instance of {@link CashReceiptApi}
     */
    public CashReceiptApi createApi(String appName) {
        CashReceiptApiImpl api = new CashReceiptApiImpl(appName);
        return api;
    }

    /**
     * Creates an instance of {@link CashReceiptApi} using the transaction api
     * implementation and initializing it with a DAO connection.
     * 
     * @param connection
     *            an instance of {@link DaoClient} used to share the DAO
     *            connection.
     * 
     * @return an instance of {@link CashReceiptApi} or null if
     *         <i>connection</i> is null.
     */
    public CashReceiptApi createApi(DaoClient connection) {
        if (connection == null) {
            return null;
        }
        CashReceiptApiImpl api = new CashReceiptApiImpl(connection);
        api.setApiUser(connection.getDaoUser());
        return api;
    }

}
