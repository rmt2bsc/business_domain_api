package org.modules.lookup;

import org.modules.AddressBookConstants;

import com.RMT2Base;

/**
 * A factory for creating resources that pertain to the Lookup Code API.
 * 
 * @author rterrell
 * 
 */
public class LookupDataApiFactory extends RMT2Base {

    /**
     * Default method.
     */
    public LookupDataApiFactory() {
        return;
    }

    /**
     * Creates an instance of {@link LookupDataApi} identifying the user
     * accessing the API.
     * 
     * @return an instance of {@link LookupDataApi}
     */
    public LookupDataApi createApi() {
        // LookupDataApi api = new LookupDataApiImpl();
        // return api;
        return this.createApi(AddressBookConstants.DEFAULT_CONTEXT_NAME);
    }

    /**
     * Creates an instance of {@link LookupDataApi} identifying the user
     * accessing the API.
     * 
     * @param appName
     *            application name
     * @return an instance of {@link LookupDataApi}
     */
    public LookupDataApi createApi(String appName) {
        LookupDataApi api = new LookupDataApiImpl(appName);
        return api;
    }
}
