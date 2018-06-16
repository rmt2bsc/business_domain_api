package org.modules.resource;

import org.modules.SecurityConstants;

import com.RMT2Base;

/**
 * A factory for creating api instances that manage the Resource module.
 * 
 * @author rterrell
 * 
 */
public class ResourceRegistryApiFactory extends RMT2Base {

    /**
     * Default method.
     */
    public ResourceRegistryApiFactory() {
        return;
    }

    /**
     * Creates an instance of ResourceRegistryApi from the web service resource
     * registry api implementation.
     * 
     * @return an instance of {@link ResourceRegistryApi}
     */
    public static final ResourceRegistryApi createWebServiceRegistryApiInstance() {
        ResourceRegistryApi api = createWebServiceRegistryApiInstance(SecurityConstants.APP_NAME);
        return api;
    }
    
    /**
     * Creates an instance of ResourceRegistryApi from the web service resource
     * registry api implementation.
     * 
     * @param appName
     * @return an instance of {@link ResourceRegistryApi}
     */
    public static final ResourceRegistryApi createWebServiceRegistryApiInstance(String appName) {
        ResourceRegistryApi api = new WebServiceRegistryApiImpl(appName);
        return api;
    }
}
