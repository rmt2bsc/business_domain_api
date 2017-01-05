package org.dao.audiovideo;

/**
 * 
 * @author appdev
 * 
 */
public class MP3ApiInstantiationException extends AudioVideoDaoException {
    private static final long serialVersionUID = -4204042569634960159L;

    public MP3ApiInstantiationException() {
        super();
    }

    public MP3ApiInstantiationException(String msg) {
        super(msg);
    }

    public MP3ApiInstantiationException(Exception e) {
        super(e);
    }

    public MP3ApiInstantiationException(Throwable e) {
        super(e);
    }

    public MP3ApiInstantiationException(String msg, Throwable e) {
        super(msg, e);
    }
}
