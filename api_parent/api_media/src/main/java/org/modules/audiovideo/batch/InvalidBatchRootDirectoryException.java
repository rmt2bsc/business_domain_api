package org.modules.audiovideo.batch;

/**
 * @author Roy Terrell
 * 
 */
public class InvalidBatchRootDirectoryException extends BatchFileProcessException {

    /**
     * 
     */
    private static final long serialVersionUID = 5519777230424946940L;

    /**
     * 
     */
    public InvalidBatchRootDirectoryException() {

    }

    /**
     * @param msg
     */
    public InvalidBatchRootDirectoryException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public InvalidBatchRootDirectoryException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public InvalidBatchRootDirectoryException(String msg, Throwable e) {
        super(msg, e);
    }

}
