package org.modules.audiovideo;

import com.RMT2RuntimeException;

/**
 * Used to report the inability to identify which MP3 Reader implementation to use with API.
 * 
 * @author appdev
 * 
 */
public class Mp3ReaderIdentityNotConfiguredException extends RMT2RuntimeException {
    private static final long serialVersionUID = -4204042569634960159L;

    /**
     * 
     */
    public Mp3ReaderIdentityNotConfiguredException() {
        super();
    }

    /**
     * 
     * @param msg
     */
    public Mp3ReaderIdentityNotConfiguredException(String msg) {
        super(msg);
    }

    /**
     * 
     * @param e
     */
    public Mp3ReaderIdentityNotConfiguredException(Exception e) {
        super(e);
    }

    /**
     * 
     * @param e
     */
    public Mp3ReaderIdentityNotConfiguredException(Throwable e) {
        super(e);
    }

    /**
     * 
     * @param msg
     * @param e
     */
    public Mp3ReaderIdentityNotConfiguredException(String msg, Throwable e) {
        super(msg, e);
    }
}
