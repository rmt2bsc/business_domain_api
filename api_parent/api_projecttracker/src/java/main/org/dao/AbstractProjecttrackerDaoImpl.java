package org.dao;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.PersistenceClient;

/**
 * @author Roy Terrell
 * 
 */
public abstract class AbstractProjecttrackerDaoImpl extends
        AbstractDaoClientImpl {

    protected String apiName = ProjectTrackerDaoConstants.API_NAME;

    /**
     * Default constructor which gets its datasource connection using
     * {@link AbstractProjecttrackerDaoImpl#API_NAME} context name.
     */
    public AbstractProjecttrackerDaoImpl() {
        super((String) null);
        return;
    }

    /**
     * Default constructor which gets its datasource connection using
     * {@link AbstractProjecttrackerDaoImpl#API_NAME} context name.
     */
    public AbstractProjecttrackerDaoImpl(String appName) {
        super(appName);
        return;
    }

    /**
     * Creates a AbstractProjecttrackerDaoImpl using an existing
     * PersistienceClient object.
     * 
     * @param client
     *            an instance of {@link PersistenceClient}
     */
    public AbstractProjecttrackerDaoImpl(PersistenceClient client) {
        super(client);
        this.apiName = ProjectTrackerDaoConstants.API_NAME;
        return;
    }

}
