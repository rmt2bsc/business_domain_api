package org.modules.audiovideo.batch;

import org.apache.log4j.Logger;

import com.RMT2Base;

/**
 * A factory for creating audio video API objects related to the batch file process.
 * 
 * @author Roy Terrell
 * 
 */
public class AvBatchFileFactory extends RMT2Base {
    
    public static final Logger LOGGER = Logger.getLogger(AvBatchFileFactory.class);

    /**
     * Default contructor
     */
    public AvBatchFileFactory() {
        return;
    }

    /**
     * Creates an instance of the AvBatchFileProcessorApi using
     * the batch file meta data importer implementation.
     * 
     * @return an instance of {@link AvBatchFileProcessorApi}
     * @throws BatchFileProcessException Unable to create AvBatchFileProcessorApi
     */
    public AvBatchFileProcessorApi createApiInstance(String srcDir) throws BatchFileProcessException {
        AvBatchFileProcessorApi api;
        try {
            api = new AvFileMetaDataLoaderApiImpl(srcDir);
            return api;
        } catch (BatchFileProcessException e) {
            throw new BatchFileProcessException("Could not instantiate Audio/Video batch file loader class", e);
        }
    }
}
