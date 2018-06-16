package org.modules.audiovideo;

import org.dao.audiovideo.AudioVideoDaoException;

public class AvTrackDataValidationException extends AudioVideoDaoException {
    private static final long serialVersionUID = -4204042569634960159L;

    public AvTrackDataValidationException() {
        super();
    }

    public AvTrackDataValidationException(String msg) {
        super(msg);
    }

    public AvTrackDataValidationException(String msg, Throwable e) {
        super(msg, e);
    }

    public AvTrackDataValidationException(Exception e) {
        super(e);
    }
}
