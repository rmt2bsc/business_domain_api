package org.modules.subsidiary;

import com.RMT2Exception;

/**
 * An exception class for handling common subsidiary DAO errors.
 * 
 * @author Roy Terrell
 * 
 */
public class SubsidiaryException extends RMT2Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 4513922270291021153L;

    /**
     * 
     */
    public SubsidiaryException() {
        super();
    }

    /**
     * @param msg
     */
    public SubsidiaryException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public SubsidiaryException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public SubsidiaryException(String msg, Throwable e) {
        super(msg, e);
    }
}
