package org.dao.document.file;

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
     * Creates an instance of MediaFileDaoProcessor iterface using the single
     * file processor DAO implementation.
     * 
     * @return an instance of {@link MediaFileDaoProcessor} or null if unable to
     *         create implementation.
     */
    public static MediaFileDaoProcessor createSingleFileProcessor() {
        try {
            MediaFileDaoProcessor api = new SingleMediaFileProcessorDaoImpl();
            return api;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Creates an instance of MediaFileDaoProcessor iterface using the batch
     * file processor DAO implementation.
     * 
     * @return an instance of {@link MediaFileDaoProcessor} or null if unable to
     *         create implementation.
     */
    public static MediaFileDaoProcessor createBatchFileProcessor() {
        try {
            MediaFileDaoProcessor api = new BatchMediaFileProcessorDaoImpl();
            return api;
        } catch (Exception e) {
            return null;
        }
    }

}
