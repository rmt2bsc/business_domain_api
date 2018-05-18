package org.modules.audiovideo;

import com.RMT2RuntimeException;

/**
 * Used to report the inability to create the proper MP3 utility that is used to
 * read the metadata of a media resource
 * 
 * @author appdev
 * 
 */
public class MP3ApiInstantiationException extends RMT2RuntimeException {
    private static final long serialVersionUID = -4204042569634960159L;

    /**
     * 
     */
    public MP3ApiInstantiationException() {
        super();
    }

    /**
     * 
     * @param msg
     */
    public MP3ApiInstantiationException(String msg) {
        super(msg);
    }

    /**
     * 
     * @param e
     */
    public MP3ApiInstantiationException(Exception e) {
        super(e);
    }

    /**
     * 
     * @param e
     */
    public MP3ApiInstantiationException(Throwable e) {
        super(e);
    }

    /**
     * 
     * @param msg
     * @param e
     */
    public MP3ApiInstantiationException(String msg, Throwable e) {
        super(msg, e);
    }
}
