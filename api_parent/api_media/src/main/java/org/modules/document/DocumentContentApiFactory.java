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

}
