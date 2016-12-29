package org.dao.document.file;

import com.api.BatchFileException;

/**
 * An exception thrown when an error occurs during the failure of initializing
 * one or more database connections during a file drop request.
 * 
 * @author Roy Terrell
 * 
 */
public class FileDropConnectionException extends BatchFileException {

    private static final long serialVersionUID = -2249933179263928838L;

    /**
     * 
     */
    public FileDropConnectionException() {
        return;
    }

    /**
     * @param msg
     */
    public FileDropConnectionException(String msg) {
        super(msg);
        return;
    }

    /**
     * @param e
     */
    public FileDropConnectionException(Exception e) {
        super(e);
        return;
    }

    /**
     * @param msg
     * @param cause
     */
    public FileDropConnectionException(String msg, Throwable cause) {
        super(msg, cause);
        return;
    }

}
