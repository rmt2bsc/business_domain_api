/**
 * 
 */
package org.modules.lookup;

import org.modules.AddressBookModuleException;

/**
 * Handles errors pertaining to the Lookup Code module.
 * 
 * @author rterrell
 * 
 */
public class LookupDataApiException extends AddressBookModuleException {

    private static final long serialVersionUID = -4191866012794129878L;

    /**
     * 
     */
    public LookupDataApiException() {
    }

    /**
     * @param msg
     */
    public LookupDataApiException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public LookupDataApiException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public LookupDataApiException(String msg, Throwable e) {
        super(msg, e);
    }

}
