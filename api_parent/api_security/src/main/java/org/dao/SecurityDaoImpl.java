package org.dao;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.PersistenceClient;

/**
 * @author Roy Terrell
 * 
 */
public class SecurityDaoImpl extends AbstractDaoClientImpl {

    protected String apiName = "Security";

    /**
     * 
     */
    public SecurityDaoImpl() {
        super((String) null);
        return;
    }

    /**
     * 
     * @param appName
     */
    public SecurityDaoImpl(String appName) {
        super(appName);
        return;
    }
    
    /**
     * 
     * @param client
     */
    public SecurityDaoImpl(PersistenceClient client) {
        super(client);
    }
}
