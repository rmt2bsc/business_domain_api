package org.modules.subsidiary;

import org.modules.CommonAccountingConst;

import com.RMT2Base;
import com.api.persistence.DaoClient;

/**
 * A factory for creating resources that pertain to the Subsidiary API
 * Maintenance module
 * 
 * @author Roy Terrell
 * 
 */
public class SubsidiaryApiFactory extends RMT2Base {

    /**
     * Default method.
     */
    public SubsidiaryApiFactory() {
        return;
    }

    /**
     * Creates an instance of {@link CustomerApi} identifying the user accessing
     * the module.
     * 
     * @return an instance of {@link CustomerApi}
     */
    public CustomerApi createCustomerApi() {
        return this.createCustomerApi(CommonAccountingConst.DEFAULT_CONTEXT_NAME);
    }

    /**
     * Creates an instance of {@link CustomerApi} identifying the user accessing
     * the module.
     * 
     * @param appName
     * @return an instance of {@link CustomerApi}
     */
    public CustomerApi createCustomerApi(String appName) {
        CustomerApi api = new CustomerApiImp(appName);
        return api;
    }

    /**
     * Creates an instance of {@link CustomerApi} using the transaction api
     * implementation and initializing it with a DAO connection.
     * 
     * @param connection
     *            an instance of {@link DaoClient} used to share the DAO
     *            connection.
     * 
     * @return an instance of {@link CustomerApi} or null if <i>connection</i>
     *         is null.
     */
    public CustomerApi createCustomerApi(DaoClient connection) {
        if (connection == null) {
            return null;
        }
        CustomerApiImp api = new CustomerApiImp(connection);
        api.setApiUser(connection.getDaoUser());
        return api;
    }

    /**
     * Creates an instance of {@link CreditorApi} identifying the user accessing
     * the module.
     * 
     * @return an instance of {@link CreditorApi}
     */
    public CreditorApi createCreditorApi() {
        return this.createCreditorApi(CommonAccountingConst.DEFAULT_CONTEXT_NAME);
    }

    /**
     * Creates an instance of {@link CreditorApi} identifying the user accessing
     * the module.
     * 
     * @param appName
     * @return an instance of {@link CreditorApi}
     */
    public CreditorApi createCreditorApi(String appName) {
        CreditorApi api = new CreditorApiImpl(appName);
        return api;
    }

    /**
     * Creates an instance of {@link CreditorApi} using the transaction api
     * implementation and initializing it with a DAO connection.
     * 
     * @param connection
     *            an instance of {@link DaoClient} used to share the DAO
     *            connection.
     * 
     * @return an instance of {@link CreditorApi} or null if <i>connection</i>
     *         is null.
     */
    public CreditorApi createCreditorApi(DaoClient connection) {
        if (connection == null) {
            return null;
        }
        CreditorApiImpl api = new CreditorApiImpl(connection);
        api.setApiUser(connection.getDaoUser());
        return api;
    }

}
