package org.dao;

import com.api.persistence.AbstractDaoClientImpl;

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
}
