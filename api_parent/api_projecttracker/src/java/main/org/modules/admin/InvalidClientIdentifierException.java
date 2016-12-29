package org.modules.admin;

/**
 * Handles errors where the client id of a client is invalid or does not exist.
 * 
 * @author Roy Terrell
 * 
 */
public class InvalidClientIdentifierException extends ProjectApiException {

    private static final long serialVersionUID = 8412120949582512772L;

    /**
     * 
     */
    public InvalidClientIdentifierException() {
        super();
    }

    /**
     * @param msg
     */
    public InvalidClientIdentifierException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public InvalidClientIdentifierException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public InvalidClientIdentifierException(String msg, Throwable e) {
        super(msg, e);
    }
}
