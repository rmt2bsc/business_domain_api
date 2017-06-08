package org.modules;

/**
 * An exception class for handling error conditions when too many items have been returned.
 * 
 * @author Roy Terrell
 * 
 */
public class TooManyItemsReturnedApiException extends AccountingModuleException {

    private static final long serialVersionUID = -7802047187719081753L;

    /**
     * 
     */
    public TooManyItemsReturnedApiException() {
        return;
    }

    /**
     * @param msg
     */
    public TooManyItemsReturnedApiException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public TooManyItemsReturnedApiException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public TooManyItemsReturnedApiException(String msg, Throwable e) {
        super(msg, e);
    }

}
