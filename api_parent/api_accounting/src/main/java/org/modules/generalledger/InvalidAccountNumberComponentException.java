package org.modules.generalledger;

import org.modules.AccountingModuleException;

/**
 * An exception class for handling error conditions when individual account
 * number components are discovered to be invalid.
 * 
 * @author Roy Terrell
 * 
 */
public class InvalidAccountNumberComponentException
        extends AccountingModuleException {

    private static final long serialVersionUID = -7802047187719081753L;

    /**
     * 
     */
    public InvalidAccountNumberComponentException() {
        return;
    }

    /**
     * @param msg
     */
    public InvalidAccountNumberComponentException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public InvalidAccountNumberComponentException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public InvalidAccountNumberComponentException(String msg, Throwable e) {
        super(msg, e);
    }
}
