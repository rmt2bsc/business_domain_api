package org.modules.audiovideo.batch;

import com.RMT2Base;

/**
 * A factory for creating audio video batch API objects.
 * 
 * @author Roy Terrell
 * 
 */
public class AudioVideoBatchApiFactory extends RMT2Base {

    /**
     * Default contructor
     */
    public AudioVideoBatchApiFactory() {
        return;
    }

    /**
     * Creates an instance of the {@link AudioVideoBatchFileProcessorApi} using
     * the batch file meta data importer implementation.
     * 
     * @return an instance of {@link AudioVideoBatchFileProcessorApi}
     */
    public AudioVideoBatchFileProcessorApi createRmt2OrmBatchLoaderDaoInstance(
            String srcDir) {
        AudioVideoBatchFileProcessorApi dao;
        try {
            dao = new MetaDataFileLoaderApiImpl(srcDir);
            return dao;
        } catch (BatchFileProcessException e) {
            return null;
        }
    }
}
