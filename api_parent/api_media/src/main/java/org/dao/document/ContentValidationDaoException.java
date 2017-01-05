package org.dao.document;

import org.dao.MediaDaoException;

/**
 * Handles errors pertaining to the multi media file name and file path.
 * 
 * @author appdev
 * 
 */
public class ContentValidationDaoException extends MediaDaoException {

    private static final long serialVersionUID = -2249933179263928838L;

    /**
     * 
     */
    public ContentValidationDaoException() {
        return;
    }

    /**
     * @param msg
     */
    public ContentValidationDaoException(String msg) {
        super(msg);
        return;
    }

    public ContentValidationDaoException(String msg, int errorCode) {
        this(msg);
        this.errorCode = errorCode;
    }

    /**
     * @param e
     */
    public ContentValidationDaoException(Exception e) {
        super(e);
        return;
    }

    /**
     * @param msg
     * @param cause
     */
    public ContentValidationDaoException(String msg, Throwable cause) {
        super(msg, cause);
        return;
    }

}
