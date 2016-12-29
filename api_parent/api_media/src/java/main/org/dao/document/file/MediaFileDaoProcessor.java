package org.dao.document.file;

import com.api.BatchFileProcessor;

/**
 * Interface for processing multi media files as the exist in the file system.
 * 
 * @author Roy Terrell
 * 
 */
public interface MediaFileDaoProcessor extends BatchFileProcessor {

    static final String CONFIG_CLASSPATH = "com.resources.config.";

    /**
     * Generates a listing of file names to process for a specified module
     * identified as <i>moduleId</i>.
     * 
     * @param moduleId
     * 
     */
    void setModuleId(int moduleId);

    /**
     * Create and send report detailing the the success/failure of one or more
     * files processed from the designated DROP directory.
     * 
     * @throws FileDropReportException
     */
    void sendDropReport() throws FileDropReportException;

    /**
     * Verifies if the multi media data files are available for processing.
     * 
     * @return true when files are availabe, and false otherwise.
     */
    boolean isFilesAvailable();
}
