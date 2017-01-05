package org.modules.resource;

import org.modules.SecurityModuleException;

/**
 * Manages resource registry maintenance errors
 * 
 * @author RTerrell
 * 
 */
public class ResourceRegistryApiException extends SecurityModuleException {
    private static final long serialVersionUID = 2969536074770899864L;

    public ResourceRegistryApiException() {
        super();
    }

    public ResourceRegistryApiException(String msg) {
        super(msg);
    }

    public ResourceRegistryApiException(Exception e) {
        super(e);
    }

    public ResourceRegistryApiException(String msg, Throwable e) {
        super(msg, e);
    }
}
