package org.modules.postal;

import org.modules.AddressBookConstants;

import com.RMT2Base;

/**
 * A factory for creating resources that pertain to the Postal module.
 * 
 * @author rterrell
 * 
 */
public class PostalApiFactory extends RMT2Base {

    /**
     * Default method.
     */
    public PostalApiFactory() {
        return;
    }

    /**
     * Creates an instance of {@link PostalApi} identifying the user accessing
     * the API.
     * 
     * @return an instance of {@link PostalApi}
     */
    public PostalApi createApi() {
        // PostalApi api = new PostalApiImpl();
        // return api;
        return this.createApi(AddressBookConstants.DEFAULT_CONTEXT_NAME);
    }

    /**
     * Creates an instance of {@link PostalApi} identifying the user accessing
     * the API.
     * 
     * @param appName
     *            application name
     * @return an instance of {@link PostalApi}
     */
    public PostalApi createApi(String appName) {
        PostalApi api = new PostalApiImpl(appName);
        return api;
    }
}
