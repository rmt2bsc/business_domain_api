package org.modules.audiovideo;

import org.modules.MediaModuleException;

/**
 * @author rterrell
 * 
 */
public class AudioVideoApiException extends MediaModuleException {

    private static final long serialVersionUID = -4530019430176943098L;

    /**
     * 
     */
    public AudioVideoApiException() {
    }

    /**
     * @param msg
     */
    public AudioVideoApiException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public AudioVideoApiException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public AudioVideoApiException(String msg, Throwable e) {
        super(msg, e);
    }

}
