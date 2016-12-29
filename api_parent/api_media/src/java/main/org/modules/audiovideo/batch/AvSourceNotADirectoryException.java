package org.modules.audiovideo.batch;

/**
 * 
 * @author Roy Terrell
 * 
 */
public class AvSourceNotADirectoryException extends BatchFileProcessException {
    private static final long serialVersionUID = -4204042569634960159L;

    public AvSourceNotADirectoryException() {
        super();
    }

    public AvSourceNotADirectoryException(String msg) {
        super(msg);
    }

    public AvSourceNotADirectoryException(String msg, Throwable e) {
        super(msg, e);
    }

    public AvSourceNotADirectoryException(Exception e) {
        super(e);
    }
}
