package org.modules.postal;

import org.modules.AddressBookModuleException;

/**
 * Handles errors pertaining to the Postal module.
 * 
 * @author rterrell
 * 
 */
public class PostalApiException extends AddressBookModuleException {

    private static final long serialVersionUID = -4191866012794129878L;

    /**
     * 
     */
    public PostalApiException() {
    }

    /**
     * @param msg
     */
    public PostalApiException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public PostalApiException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public PostalApiException(String msg, Throwable e) {
        super(msg, e);
    }

}
