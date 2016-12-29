package org.modules.audiovideo.batch;

/**
 * @author Roy Terrell
 * 
 */
public class InvalidBatchRootDirectoryException extends
        BatchFileProcessException {

    /**
     * 
     */
    private static final long serialVersionUID = 5519777230424946940L;

    /**
     * 
     */
    public InvalidBatchRootDirectoryException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     */
    public InvalidBatchRootDirectoryException(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param e
     */
    public InvalidBatchRootDirectoryException(Exception e) {
        super(e);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     * @param e
     */
    public InvalidBatchRootDirectoryException(String msg, Throwable e) {
        super(msg, e);
        // TODO Auto-generated constructor stub
    }

}
