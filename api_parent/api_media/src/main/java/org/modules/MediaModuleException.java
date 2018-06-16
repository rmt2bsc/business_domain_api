package org.modules;

import com.RMT2Exception;

/**
 * The base exception for all modules of the Address Book API.
 * 
 * @author RTerrell
 * 
 */
public class MediaModuleException extends RMT2Exception {
    private static final long serialVersionUID = 2969536074770899864L;

    public MediaModuleException() {
        super();
    }

    public MediaModuleException(String msg) {
        super(msg);
    }

    public MediaModuleException(Exception e) {
        super(e);
    }

    public MediaModuleException(String msg, Throwable e) {
        super(msg, e);
    }
}
