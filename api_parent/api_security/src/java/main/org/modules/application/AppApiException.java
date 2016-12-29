package org.modules.application;

import org.modules.SecurityModuleException;

/**
 * Manages application maintenance errors
 * 
 * @author RTerrell
 * 
 */
public class AppApiException extends SecurityModuleException {
    private static final long serialVersionUID = 2969536074770899864L;

    public AppApiException() {
        super();
    }

    public AppApiException(String msg) {
        super(msg);
    }

    public AppApiException(Exception e) {
        super(e);
    }

    public AppApiException(String msg, Throwable e) {
        super(msg, e);
    }
}
