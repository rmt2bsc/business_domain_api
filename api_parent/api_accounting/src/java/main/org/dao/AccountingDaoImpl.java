package org.dao;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.PersistenceClient;

/**
 * @author Roy Terrell
 * 
 */
public class AccountingDaoImpl extends AbstractDaoClientImpl {

    protected String apiName;

    /**
     * Default constructor which gets its datasource connection using
     * {@link AccountingDaoImpl#API_NAME} context name.
     */
    public AccountingDaoImpl() {
        super((String) null);
        this.apiName = null;
        return;
    }

    /**
     * Creates a AccountingDaoImpl using an existing PersistienceClient object.
     * 
     * @param appName
     *            application name
     */
    public AccountingDaoImpl(String appName) {
        super(appName);
        this.apiName = appName;
        return;
    }

    /**
     * Creates a AccountingDaoImpl using an existing PersistienceClient object.
     * 
     * @param client
     *            an instance of {@link PersistenceClient}
     */
    public AccountingDaoImpl(PersistenceClient client) {
        super(client);
        this.apiName = null;
        return;
    }
}
