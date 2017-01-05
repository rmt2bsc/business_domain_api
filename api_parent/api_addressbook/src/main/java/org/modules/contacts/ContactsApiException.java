/**
 * 
 */
package org.modules.contacts;

import org.modules.AddressBookModuleException;

/**
 * Handles errors pertaining to the Contacts module.
 * 
 * @author rterrell
 * 
 */
public class ContactsApiException extends AddressBookModuleException {

    private static final long serialVersionUID = -4191866012794129878L;

    /**
     * 
     */
    public ContactsApiException() {
    }

    /**
     * @param msg
     */
    public ContactsApiException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public ContactsApiException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public ContactsApiException(String msg, Throwable e) {
        super(msg, e);
    }

}
