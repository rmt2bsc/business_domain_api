package org.dao.audiovideo;

/**
 * 
 * @author appdev
 * 
 */
public class AvInvalidSourceFileException extends AudioVideoDaoException {
    private static final long serialVersionUID = -4204042569634960159L;

    public AvInvalidSourceFileException() {
        super();
    }

    public AvInvalidSourceFileException(String msg) {
        super(msg);
    }

    public AvInvalidSourceFileException(String msg, Throwable e) {
        super(msg, e);
    }

    public AvInvalidSourceFileException(Exception e) {
        super(e);
    }
}
