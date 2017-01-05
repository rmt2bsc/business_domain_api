package org.modules.resource;

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
    public ResourceRegistryApi createWebServiceRegistryApi() {
        ResourceRegistryApi api = new WebServiceRegistryApiImpl();
        return api;
    }
}
