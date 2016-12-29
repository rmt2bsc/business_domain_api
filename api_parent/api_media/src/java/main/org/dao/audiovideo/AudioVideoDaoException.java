package org.dao.audiovideo;

import org.dao.MediaDaoException;

/**
 * Exception class for handling audio/video errors
 * 
 * @author rterrell
 * 
 */
public class AudioVideoDaoException extends MediaDaoException {
    private static final long serialVersionUID = -4204042569634960159L;

    public AudioVideoDaoException() {
        super();
    }

    public AudioVideoDaoException(String msg) {
        super(msg);
    }

    public AudioVideoDaoException(Exception e) {
        super(e);
    }

    public AudioVideoDaoException(Throwable e) {
        super(e);
    }

    public AudioVideoDaoException(String msg, Throwable e) {
        super(msg, e);
    }
}
