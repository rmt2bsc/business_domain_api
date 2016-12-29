package org.dao;

import com.api.persistence.AbstractDaoClientImpl;

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
}
