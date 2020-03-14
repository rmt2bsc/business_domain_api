package org.modules.transaction.sales;

import org.modules.CommonAccountingConst;

import com.RMT2Base;
import com.api.persistence.DaoClient;

/**
 * A factory for creating resources that pertain to the Sales Order Transaction
 * API module.
 * 
 * @author Roy Terrell
 * 
 */
public class SalesApiFactory extends RMT2Base {

    /**
     * Default method.
     */
    public SalesApiFactory() {
        return;
    }

    /**
     * Creates an instance of {@link SalesApi} using the transaction api
     * implementation.
     * 
     * @return an instance of {@link SalesApi}
     */
    public static final SalesApi createApi() {
        return SalesApiFactory.createApi(CommonAccountingConst.DEFAULT_CONTEXT_NAME);
    }

    /**
     * Creates an instance of {@link SalesApi} using the transaction api
     * implementation.
     * 
     * @return an instance of {@link SalesApi}
     */
    public static final SalesApi createApi(String appName) {
        SalesApiImpl api = new SalesApiImpl(appName);
        return api;
    }

    /**
     * Creates an instance of {@link SalesApi} using the transaction api
     * implementation and initializing it with a DAO connection.
     * 
     * @param connection
     *            an instance of {@link DaoClient} used to share the DAO
     *            connection.
     * 
     * @return an instance of {@link SalesApi} or null if <i>connection</i> is
     *         null.
     */
    public static final SalesApi createApi(DaoClient connection) {
        if (connection == null) {
            return null;
        }
        SalesApiImpl api = new SalesApiImpl(connection);
        api.setApiUser(connection.getDaoUser());
        return api;
    }

}
