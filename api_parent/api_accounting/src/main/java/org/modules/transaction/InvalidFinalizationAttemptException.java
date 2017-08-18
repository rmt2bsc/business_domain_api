package org.modules.transaction;

/**
 * Used to flag occurrences when a transaction associated with an 
 * incorrect transaction type is attempting to be finalized.
 * 
 * @author Roy Terrell
 * 
 */
public class InvalidFinalizationAttemptException extends XactApiException {
    private static final long serialVersionUID = -1884703323759924257L;

    public InvalidFinalizationAttemptException() {
        super();
    }

    public InvalidFinalizationAttemptException(String msg) {
        super(msg);
    }

    public InvalidFinalizationAttemptException(Exception e) {
        super(e);
    }

    public InvalidFinalizationAttemptException(String msg, Exception e) {
        super(msg, e);
    }
}
