package org.dao.document.file;

import com.api.BatchFileException;

/**
 * Handles general data access errors as it pertains to the handling and
 * processing of a single media file.
 * 
 * @author Roy Terrell
 * 
 */
public class MediaFileOperationDaoException extends BatchFileException {

    private static final long serialVersionUID = -2249933179263928838L;

    /**
     * 
     */
    public MediaFileOperationDaoException() {
        return;
    }

    /**
     * @param msg
     */
    public MediaFileOperationDaoException(String msg) {
        super(msg);
        return;
    }

    /**
     * @param e
     */
    public MediaFileOperationDaoException(Exception e) {
        super(e);
        return;
    }

    /**
     * @param msg
     * @param cause
     */
    public MediaFileOperationDaoException(String msg, Throwable cause) {
        super(msg, cause);
        return;
    }

}
