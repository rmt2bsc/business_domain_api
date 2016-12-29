package org.modules.audiovideo.batch;

/**
 * An exception thrown when an error occurs during the File Drop Report creation
 * and/or transmission.
 * 
 * @author appdev
 * 
 */
public class AvBatchReportException extends BatchFileProcessException {

    private static final long serialVersionUID = -2249933179263928838L;

    /**
     * 
     */
    public AvBatchReportException() {
    }

    /**
     * @param msg
     */
    public AvBatchReportException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public AvBatchReportException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param cause
     */
    public AvBatchReportException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
