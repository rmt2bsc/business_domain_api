package org.modules.services.directory.file;

import com.api.BatchFileException;

/**
 * An exception thrown when an error occurs during the failure of processing
 * files contained in the designated drop directory.
 * 
 * @author Roy Terrell
 * 
 */
public class FileDropProcessingException extends BatchFileException {

    private static final long serialVersionUID = -2249933179263928838L;

    /**
     * 
     */
    public FileDropProcessingException() {
        return;
    }

    /**
     * @param msg
     */
    public FileDropProcessingException(String msg) {
        super(msg);
        return;
    }

    /**
     * @param e
     */
    public FileDropProcessingException(Exception e) {
        super(e);
        return;
    }

    /**
     * @param msg
     * @param cause
     */
    public FileDropProcessingException(String msg, Throwable cause) {
        super(msg, cause);
        return;
    }

}
