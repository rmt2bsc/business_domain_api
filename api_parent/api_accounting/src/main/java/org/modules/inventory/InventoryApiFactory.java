package org.modules.inventory;

import org.modules.CommonAccountingConst;

import com.RMT2Base;
import com.api.persistence.DaoClient;

/**
 * A factory for creating resources that pertain to the Inventory API
 * Maintenance module
 * 
 * @author Roy Terrell
 * 
 */
public class InventoryApiFactory extends RMT2Base {

    /**
     * Default method.
     */
    public InventoryApiFactory() {
        return;
    }

    /**
     * Creates an instance of {@link InventoryApi} identifying the user
     * accessing the module.
     * 
     * @return an instance of {@link InventoryApi}
     */
    public InventoryApi createApi() {
        return this.createApi(CommonAccountingConst.DEFAULT_CONTEXT_NAME);
    }

    /**
     * Creates an instance of {@link InventoryApi} identifying the user
     * accessing the module.
     * 
     * @param appName
     *            application name
     * @return an instance of {@link InventoryApi}
     */
    public InventoryApi createApi(String appName) {
        InventoryApi api = new InventoryApiImpl(appName);
        return api;
    }

    /**
     * Creates an instance of {@link InventoryApi} using the transaction api
     * implementation and initializing it with a DAO connection.
     * 
     * @param connection
     *            an instance of {@link DaoClient} used to share the DAO
     *            connection.
     * 
     * @return an instance of {@link InventoryApi} or null if <i>connection</i>
     *         is null.
     */
    public InventoryApi createApi(DaoClient connection) {
        if (connection == null) {
            return null;
        }
        InventoryApiImpl api = new InventoryApiImpl(connection);
        // api.setSharedDao(connection);
        api.setApiUser(connection.getDaoUser());
        return api;
    }
}
