package org.modules;

import com.RMT2Exception;

/**
 * The base exception for all modules of the Address Book API.
 * 
 * @author RTerrell
 * 
 */
public class AddressBookModuleException extends RMT2Exception {
    private static final long serialVersionUID = 2969536074770899864L;

    public AddressBookModuleException() {
        super();
    }

    public AddressBookModuleException(String msg) {
        super(msg);
    }

    public AddressBookModuleException(Exception e) {
        super(e);
    }

    public AddressBookModuleException(String msg, Throwable e) {
        super(msg, e);
    }
}
