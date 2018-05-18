package org.modules.audiovideo.batch;

/**
 * Used to report the inability to create specific MP3 Reader implementation to use with API.
 * 
 * @author appdev
 * 
 */
public class CannotCreateMp3LibraryException extends Mp3ReaderIdentityNotConfiguredException {
    private static final long serialVersionUID = -4204042569634960159L;

    /**
     * 
     */
    public CannotCreateMp3LibraryException() {
        super();
    }

    /**
     * 
     * @param msg
     */
    public CannotCreateMp3LibraryException(String msg) {
        super(msg);
    }

    /**
     * 
     * @param e
     */
    public CannotCreateMp3LibraryException(Exception e) {
        super(e);
    }

    /**
     * 
     * @param e
     */
    public CannotCreateMp3LibraryException(Throwable e) {
        super(e);
    }

    /**
     * 
     * @param msg
     * @param e
     */
    public CannotCreateMp3LibraryException(String msg, Throwable e) {
        super(msg, e);
    }
}
