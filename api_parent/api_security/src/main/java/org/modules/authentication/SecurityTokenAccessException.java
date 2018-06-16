package org.modules.authentication;

import org.modules.SecurityModuleException;

/**
 * Handles security token access errors.
 * 
 * @author rterrell
 * 
 */
public class SecurityTokenAccessException extends SecurityModuleException {

    private static final long serialVersionUID = 8053866875644675089L;

    /**
     * 
     */
    public SecurityTokenAccessException() {
        return;
    }

    /**
     * @param msg
     */
    public SecurityTokenAccessException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public SecurityTokenAccessException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public SecurityTokenAccessException(String msg, Throwable e) {
        super(msg, e);
    }

}
