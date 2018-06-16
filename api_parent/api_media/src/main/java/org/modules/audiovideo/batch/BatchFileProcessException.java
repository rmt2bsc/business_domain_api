package org.modules.audiovideo.batch;

import com.api.BatchFileException;

/**
 * 
 * @author appdev
 * 
 */
public class BatchFileProcessException extends BatchFileException {
    private static final long serialVersionUID = -4204042569634960159L;

    public BatchFileProcessException() {
        super();
    }

    public BatchFileProcessException(String msg) {
        super(msg);
    }

    public BatchFileProcessException(Exception e) {
        super(e);
    }

    public BatchFileProcessException(String msg, Throwable e) {
        super(msg, e);
    }
}
