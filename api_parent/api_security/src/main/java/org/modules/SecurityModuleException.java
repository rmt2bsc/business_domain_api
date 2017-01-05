package org.modules;

import com.RMT2Exception;

/**
 * The base exception for all modules of the Security API.
 * 
 * @author RTerrell
 * 
 */
public class SecurityModuleException extends RMT2Exception {
    private static final long serialVersionUID = 2969536074770899864L;

    public SecurityModuleException() {
        super();
    }

    public SecurityModuleException(String msg) {
        super(msg);
    }

    public SecurityModuleException(Exception e) {
        super(e);
    }

    public SecurityModuleException(String msg, Throwable e) {
        super(msg, e);
    }
}
