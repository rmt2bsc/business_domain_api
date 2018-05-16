package org.modules.audiovideo.batch;

import com.RMT2Base;

/**
 * A factory for creating audio video batch API objects.
 * 
 * @author Roy Terrell
 * 
 */
public class AvBatchApiFactory extends RMT2Base {

    /**
     * Default contructor
     */
    public AvBatchApiFactory() {
        return;
    }

    /**
     * Creates an instance of the AvBatchFileProcessorApi using
     * the batch file meta data importer implementation.
     * 
     * @return an instance of {@link AvBatchFileProcessorApi}
     */
    public AvBatchFileProcessorApi createApiInstance(String srcDir) {
        AvBatchFileProcessorApi api;
        try {
            api = new AvFileMetaDataLoaderApiImpl(srcDir);
            return api;
        } catch (BatchFileProcessException e) {
            return null;
        }
    }
}
