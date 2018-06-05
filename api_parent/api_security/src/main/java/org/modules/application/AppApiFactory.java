package org.modules.application;

import org.modules.SecurityConstants;

import com.RMT2Base;

/**
 * A factory for creating API instances that manage application related domain.
 * 
 * @author rterrell
 * 
 */
public class AppApiFactory extends RMT2Base {

    /**
     * Default method.
     */
    public AppApiFactory() {
        return;
    }

    /**
     * Creates an instance of {@link AppApi} identifying the user accessing the
     * API.
     * 
     * @return an instance of {@link AppApi}
     */
    public AppApi createApi() {
        AppApi api = createApiInstance(SecurityConstants.APP_NAME);
        return api;
    }

    /**
     * Creates an instance of AppApi using the {@link AppApiImpl}
     * implementation.
     */
    public static final AppApi createApiInstance(String appName) {
        AppApi api = new AppApiImpl(appName);
        return api;
    }

}
