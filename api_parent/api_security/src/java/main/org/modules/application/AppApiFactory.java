package org.modules.application;

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
        AppApi api = new AppApiImpl();
        return api;
    }
}
