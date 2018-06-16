package org.modules.services.document.directory.file;

import org.dao.MediaDaoException;

/**
 * An exception thrown when the target record of the home application is not
 * found by a given primary key value.
 * 
 * @author Roy Terrell
 * 
 */
public class HomeApplicationRecordNotFoundException extends MediaDaoException {

    private static final long serialVersionUID = -2249933179263928838L;

    /**
     * 
     */
    public HomeApplicationRecordNotFoundException() {
        return;
    }

    /**
     * @param msg
     */
    public HomeApplicationRecordNotFoundException(String msg) {
        super(msg);
        return;
    }

    /**
     * @param e
     */
    public HomeApplicationRecordNotFoundException(Exception e) {
        super(e);
        return;
    }

    /**
     * @param msg
     * @param cause
     */
    public HomeApplicationRecordNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
        return;
    }

}
