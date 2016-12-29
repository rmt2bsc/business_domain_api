package org.modules.generalledger;

import org.modules.CommonAccountingConst;

import com.RMT2Base;

/**
 * A factory for creating resources that pertain to the General Ledger Account
 * Maintenance module
 * 
 * @author rterrell
 * 
 */
public class GeneralLedgerApiFactory extends RMT2Base {

    /**
     * Default method.
     */
    public GeneralLedgerApiFactory() {
        return;
    }

    /**
     * Creates an instance of {@link GlAccountApi} identifying the user
     * accessing the module.
     * 
     * @return an instance of {@link GlAccountApi}
     */
    public GlAccountApi createApi() {
        // GlAccountApi api = new BasicAccountMaintApiImpl();
        // return api;
        return this.createApi(CommonAccountingConst.DEFAULT_CONTEXT_NAME);
    }

    /**
     * Creates an instance of {@link GlAccountApi} identifying the user
     * accessing the module.
     * 
     * @param appName
     *            application name
     * @return an instance of {@link GlAccountApi}
     */
    public GlAccountApi createApi(String appName) {
        GlAccountApi api = new BasicAccountMaintApiImpl(appName);
        return api;
    }
}
