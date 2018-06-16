package org.dao;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.PersistenceClient;

/**
 * @author Roy Terrell
 * 
 */
public class MediaDaoImpl extends AbstractDaoClientImpl {

    protected String apiName = "Media";

    /**
     * 
     */
    public MediaDaoImpl() {
        super((String) null);
        return;
    }

    /**
     * 
     */
    public MediaDaoImpl(String appName) {
        super(appName);
        return;
    }
    
    /**
     * Creates a MediaDaoImpl object with a shared persistent client.
     * 
     * @param client
     */
    public MediaDaoImpl(PersistenceClient client) {
        super(client);
    }
}
