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
    public static final AvBatchFileProcessorApi createMediaFileBatchImportApiInstance(String srcDir) throws BatchFileProcessException {
        AvBatchFileProcessorApi api;
        api = new AvFileMetaDataLoaderApiImpl(srcDir);
        return api;
    }

    /**
     * Creates an instance of the AvBatchFileProcessorApi using the CSV video
     * batch file meta data importer implementation.
     * 
     * @return an instance of {@link AvBatchFileProcessorApi}
     * @throws BatchFileProcessException
     *             Unable to create AvBatchFileProcessorApi
     */
    public static final AvBatchFileProcessorApi createCsvBatchImportApiInstance(String srcDir) throws BatchFileProcessException {
        AvBatchFileProcessorApi api;
        api = new CsvVideoMetaDataLoaderApiImpl(srcDir);
        return api;
    }
}
