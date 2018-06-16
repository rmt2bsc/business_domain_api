package org.modules.audiovideo.batch;

import org.modules.audiovideo.AudioVideoApiException;

/**
 * 
 * @author appdev
 * 
 */
public class AvFileExtractionException extends AudioVideoApiException {
    private static final long serialVersionUID = -4204042569634960159L;

    public AvFileExtractionException() {
        super();
    }

    public AvFileExtractionException(String msg) {
        super(msg);
    }

    public AvFileExtractionException(String msg, Throwable e) {
        super(msg, e);
    }

    public AvFileExtractionException(Exception e) {
        super(e);
    }
}
