package org.dao.document.file;

import org.dao.MediaDaoException;

/**
 * An exception thrown when the target record of the home application is found
 * to be already associated with MIME document.
 * 
 * @author Roy Terrell
 * 
 */
public class HomeApplicationRecordCommittedException extends MediaDaoException {

    private static final long serialVersionUID = -2249933179263928838L;

    /**
     * 
     */
    public HomeApplicationRecordCommittedException() {
        return;
    }

    /**
     * @param msg
     */
    public HomeApplicationRecordCommittedException(String msg) {
        super(msg);
        return;
    }

    /**
     * @param e
     */
    public HomeApplicationRecordCommittedException(Exception e) {
        super(e);
        return;
    }

    /**
     * @param msg
     * @param cause
     */
    public HomeApplicationRecordCommittedException(String msg, Throwable cause) {
        super(msg, cause);
        return;
    }

}
