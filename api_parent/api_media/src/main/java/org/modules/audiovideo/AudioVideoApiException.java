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
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     */
    public AudioVideoApiException(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param e
     */
    public AudioVideoApiException(Exception e) {
        super(e);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     * @param e
     */
    public AudioVideoApiException(String msg, Throwable e) {
        super(msg, e);
        // TODO Auto-generated constructor stub
    }

}
