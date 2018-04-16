package org.modules.document;

import com.RMT2Base;

/**
 * Factory class for creating media document api related objects.
 * 
 * @author rterrell
 * 
 */
public class DocumentContentApiFactory extends RMT2Base {

    /**
     * Default constructor
     */
    public DocumentContentApiFactory() {
        super();
    }

    /**
     * Creates an instance of DocumentContentApi using the
     * {@link DocumentContentApiImpl} implementation.
     * 
     * @return an instance of {@link DocumentContentApi}
     */
    public DocumentContentApi createMediaContentApi() {
        DocumentContentApi api = new DocumentContentApiImpl();
        return api;
    }

    /**
     * Creates an instance of {@link DocumentContentApi} using the transaction api
     * implementation.
     */
    public DocumentContentApi createMediaContentApi(String appName) {
        DocumentContentApi api = new DocumentContentApiImpl(appName);
        return api;
    }
    
    /**
     * Creates an instance of {@link DocumentContentApi} using the transaction api
     * implementation.
     * 
     * @param appName
     * @param persistInDb
     * @return
     */
    public DocumentContentApi createMediaContentApi(String appName, boolean persistInDb) {
        DocumentContentApi api = new DocumentContentApiImpl(appName,  persistInDb);
        return api;
    }
}
