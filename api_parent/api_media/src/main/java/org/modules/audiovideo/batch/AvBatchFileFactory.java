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
     * Creates an instance of the AvBatchFileProcessorApi using the local batch
     * file metadata importer implementation.
     * 
     * @param parms
     *            an instance of {@link AvBatchImportParameters}
     * @return an instance of {@link AvBatchFileProcessorApi}
     * @throws BatchFileProcessException
     *             Unable to create AvBatchFileProcessorApi
     */
    public static final AvBatchFileProcessorApi createLocalAudioBatchImportApiInstance(AvBatchImportParameters parms)
            throws BatchFileProcessException {
        AvBatchFileProcessorApi api;
        api = new LocalAudioMetaDataLoaderApiImpl(parms);
        return api;
    }

    /**
     * Creates an instance of the AvBatchFileProcessorApi using the remote batch
     * file metadata importer implementation.
     * 
     * @param parms
     *            an instance of {@link AvBatchImportParameters}
     * @return an instance of {@link AvBatchFileProcessorApi}
     * @throws BatchFileProcessException
     *             Unable to create AvBatchFileProcessorApi
     */
    public static final AvBatchFileProcessorApi createRemoteAudioBatchImportApiInstance(AvBatchImportParameters parms)
            throws BatchFileProcessException {
        AvBatchFileProcessorApi api = new RemoteAudioMetaDataLoaderApiImpl(parms);
        return api;
    }

    /**
     * Creates an instance of the AvBatchFileProcessorApi using the CSV video
     * batch file meta data importer implementation.
     * 
     * @param parms
     *            an instance of {@link AvBatchImportParameters}
     * @return an instance of {@link AvBatchFileProcessorApi}
     * @throws BatchFileProcessException
     *             Unable to create AvBatchFileProcessorApi
     */
    public static final AvBatchFileProcessorApi createCsvBatchImportApiInstance(AvBatchImportParameters parms)
            throws BatchFileProcessException {
        AvBatchFileProcessorApi api;
        api = new CsvVideoMetaDataLoaderApiImpl(parms);
        return api;
    }
}
