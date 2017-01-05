package org.dao.document;

import org.dao.MediaDaoException;

/**
 * Handles general multi media data access errors.
 * 
 * @author appdev
 * 
 */
public class ContentDaoException extends MediaDaoException {

    private static final long serialVersionUID = -2249933179263928838L;

    /**
     * 
     */
    public ContentDaoException() {
        return;
    }

    /**
     * @param msg
     */
    public ContentDaoException(String msg) {
        super(msg);
        return;
    }

    /**
     * @param e
     */
    public ContentDaoException(Exception e) {
        super(e);
        return;
    }

    /**
     * @param msg
     * @param cause
     */
    public ContentDaoException(String msg, Throwable cause) {
        super(msg, cause);
        return;
    }

}
