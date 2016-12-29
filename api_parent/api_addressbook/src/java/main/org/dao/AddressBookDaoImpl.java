package org.dao;

import com.api.persistence.AbstractDaoClientImpl;

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
}
