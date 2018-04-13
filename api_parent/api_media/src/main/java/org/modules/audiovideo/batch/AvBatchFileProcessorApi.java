package org.modules.audiovideo.batch;

import com.api.BatchFileProcessor;

/**
 * A batch file contract for accessing and managing information pertaining to
 * audio and video media.
 * 
 * @author appdev
 * 
 */
public interface AvBatchFileProcessorApi extends BatchFileProcessor {

    /**
     * Perform any validations specific to the implementation design.
     * 
     * @throws BatchFileProcessException
     */
    void validate() throws BatchFileProcessException;

}
