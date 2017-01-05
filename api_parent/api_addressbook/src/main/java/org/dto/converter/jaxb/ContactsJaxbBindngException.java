/**
 * 
 */
package org.dto.converter.jaxb;

import org.modules.AddressBookModuleException;

/**
 * Handles errors pertaining to the Contacts module.
 * 
 * @author rterrell
 * 
 */
public class ContactsJaxbBindngException extends AddressBookModuleException {

    private static final long serialVersionUID = -4191866012794129878L;

    /**
     * 
     */
    public ContactsJaxbBindngException() {
    }

    /**
     * @param msg
     */
    public ContactsJaxbBindngException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public ContactsJaxbBindngException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public ContactsJaxbBindngException(String msg, Throwable e) {
        super(msg, e);
    }

}
