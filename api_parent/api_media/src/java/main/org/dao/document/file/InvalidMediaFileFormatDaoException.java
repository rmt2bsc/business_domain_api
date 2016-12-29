package org.dao.document.file;

import com.api.BatchFileException;

/**
 * An exception thrown when the format of the source data file's filename is
 * incorrect.
 * 
 * @author appdev
 * 
 */
public class InvalidMediaFileFormatDaoException extends BatchFileException {

    private static final long serialVersionUID = -2249933179263928838L;

    /**
     * 
     */
    public InvalidMediaFileFormatDaoException() {
        return;
    }

    /**
     * @param msg
     */
    public InvalidMediaFileFormatDaoException(String msg) {
        super(msg);
        return;
    }

    /**
     * @param e
     */
    public InvalidMediaFileFormatDaoException(Exception e) {
        super(e);
        return;
    }

    /**
     * @param msg
     * @param cause
     */
    public InvalidMediaFileFormatDaoException(String msg, Throwable cause) {
        super(msg, cause);
        return;
    }

}
