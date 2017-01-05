package org.modules.audiovideo.batch;

/**
 * 
 * @author rterrell
 * 
 */
public class AvBatchValidationException extends BatchFileProcessException {
    private static final long serialVersionUID = -4204042569634960159L;

    public AvBatchValidationException() {
        super();
    }

    public AvBatchValidationException(String msg) {
        super(msg);
    }

    public AvBatchValidationException(String msg, Throwable e) {
        super(msg, e);
    }

    public AvBatchValidationException(Exception e) {
        super(e);
    }
}
