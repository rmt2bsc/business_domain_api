package org.modules.services.document.directory.file;

import com.api.BatchFileException;

/**
 * An exception thrown when an error occurs during the File Drop Report creation
 * and/or transmission.
 * 
 * @author appdev
 * 
 */
public class FileDropReportException extends BatchFileException {

    private static final long serialVersionUID = -2249933179263928838L;

    /**
     * 
     */
    public FileDropReportException() {
        return;
    }

    /**
     * @param msg
     */
    public FileDropReportException(String msg) {
        super(msg);
        return;
    }

    /**
     * @param e
     */
    public FileDropReportException(Exception e) {
        super(e);
        return;
    }

    /**
     * @param msg
     * @param cause
     */
    public FileDropReportException(String msg, Throwable cause) {
        super(msg, cause);
        return;
    }

}
