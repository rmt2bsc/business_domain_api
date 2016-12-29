package org.dao.audiovideo;

public class AvProjectDataValidationException extends AudioVideoDaoException {
    private static final long serialVersionUID = -4204042569634960159L;

    public AvProjectDataValidationException() {
        super();
    }

    public AvProjectDataValidationException(String msg) {
        super(msg);
    }

    public AvProjectDataValidationException(Exception e) {
        super(e);
    }

    public AvProjectDataValidationException(String msg, Throwable e) {
        super(msg, e);
    }
}
