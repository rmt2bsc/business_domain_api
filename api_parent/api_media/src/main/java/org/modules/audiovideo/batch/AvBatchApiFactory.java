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
     * Creates an instance of the {@link AudioVideoBatchFileProcessorApi} using
     * the batch file meta data importer implementation.
     * 
     * @return an instance of {@link AudioVideoBatchFileProcessorApi}
     */
    public AvBatchFileProcessorApi createRmt2OrmBatchLoaderDaoInstance(String srcDir) {
        AvBatchFileProcessorApi dao;
        try {
            dao = new AvFileMetaDataLoaderApiImpl(srcDir);
            return dao;
        } catch (BatchFileProcessException e) {
            return null;
        }
    }
}
