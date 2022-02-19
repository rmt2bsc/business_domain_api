package org.modules.contacts;

import org.modules.AddressBookConstants;

import com.RMT2Base;
import com.api.persistence.DaoClient;

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
    
    /**
     * Creates an instance of {@link ContactsApi} using the transaction api
     * implementation and initializing it with a DAO connection.
     * 
     * @param connection
     *            an instance of {@link DaoClient} used to share the DAO
     *            connection.
     * 
     * @return an instance of {@link ContactsApi} or null if <i>connection</i>
     *         is null.
     */
    public static final ContactsApi createApi(DaoClient connection) {
        if (connection == null) {
            return null;
        }
        ContactsApi api = new ContactsApiImpl(connection);
        api.setApiUser(connection.getDaoUser());
        return api;
    }
}
