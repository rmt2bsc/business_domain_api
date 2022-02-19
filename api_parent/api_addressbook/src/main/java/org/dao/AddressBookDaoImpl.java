package org.dao;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.PersistenceClient;

/**
 * @author Roy Terrell
 * 
 */
public class AddressBookDaoImpl extends AbstractDaoClientImpl {

    protected String apiName = "AddressBook";

    /**
     * 
     */
    public AddressBookDaoImpl() {
        super((String) null);
        return;
    }

    public AddressBookDaoImpl(String appName) {
        super(appName);
        return;
    }
    
    public AddressBookDaoImpl(PersistenceClient client) {
        super(client);
        return;
    }
}
