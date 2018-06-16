package org.modules.services.document.directory.file;

import com.api.BatchFileException;

/**
 * Handles general data access errors as it pertains to the handling and
 * processing of a single media file.
 * 
 * @author Roy Terrell
 * 
 */
public class MediaFileOperationException extends BatchFileException {

    private static final long serialVersionUID = -2249933179263928838L;

    /**
     * 
     */
    public MediaFileOperationException() {
        return;
    }

    /**
     * @param msg
     */
    public MediaFileOperationException(String msg) {
        super(msg);
        return;
    }

    /**
     * @param e
     */
    public MediaFileOperationException(Exception e) {
        super(e);
        return;
    }

    /**
     * @param msg
     * @param cause
     */
    public MediaFileOperationException(String msg, Throwable cause) {
        super(msg, cause);
        return;
    }

}
