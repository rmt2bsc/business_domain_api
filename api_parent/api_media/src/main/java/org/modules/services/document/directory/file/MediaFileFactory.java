package org.modules.services.document.directory.file;

import org.apache.log4j.Logger;

import com.RMT2Base;

/**
 * A factory for creating Multi Media file processor resources.
 * 
 * @author appdev
 * 
 */
public class MediaFileFactory extends RMT2Base {

    private static Logger logger = Logger.getLogger(MediaFileFactory.class);

    /**
     * Create MediaFileFactory object
     */
    private MediaFileFactory() {
        MediaFileFactory.logger.info("Logger initialized");
    }

    /**
     * Creates an instance of MediaFileDaoProcessor iterface using the batch
     * file processor DAO implementation.
     * 
     * @return an instance of {@link MediaFileDaoProcessor} or null if unable to
     *         create implementation.
     */
    public static MediaFileProcessor createBatchFileProcessor() {
        try {
            MediaFileProcessor api = new BatchMediaFileProcessorImpl();
            return api;
        } catch (Exception e) {
            return null;
        }
    }

}
