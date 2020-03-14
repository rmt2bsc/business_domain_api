package org.modules.contacts;

import org.modules.AddressBookConstants;

import com.RMT2Base;

/**
 * A factory for creating resources that pertain to the Contacts module
 * 
 * @author rterrell
 * 
 */
public class ContactsApiFactory extends RMT2Base {

    /**
     * Default method.
     */
    public ContactsApiFactory() {
        return;
    }

    /**
     * Creates an instance of {@link ContactsApi} identifying the user accessing
     * the module.
     * 
     * @return an instance of {@link ContactsApi}
     */
    public static final ContactsApi createApi() {
        return ContactsApiFactory.createApi(AddressBookConstants.DEFAULT_CONTEXT_NAME);
    }

    /**
     * Creates an instance of {@link ContactsApi} identifying the user accessing
     * the module.
     * 
     * @param appName
     *            application name
     * @return an instance of {@link ContactsApi}
     */
    public static final ContactsApi createApi(String appName) {
        ContactsApi api = new ContactsApiImpl(appName);
        return api;
    }
}
