package org.modules.audiovideo.batch;

import java.io.File;

import org.dao.audiovideo.AudioVideoDaoException;
import org.dao.audiovideo.AvCombinedProjectBean;

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
     * Reads the tag data from the media file, <i>sourceFile</i>, and packages
     * the data in an instance of AvCombinedProjectBean.
     * 
     * @param sourceFile
     *            the audio/video file to extract data from.
     * @return an instance of {@link AvCombinedProjectBean}
     * @throws AudioVideoDaoException
     */
    AvCombinedProjectBean extractFileMetaData(File sourceFile) throws AudioVideoDaoException;
    
}
