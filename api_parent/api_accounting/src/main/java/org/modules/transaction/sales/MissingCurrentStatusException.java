package org.modules.transaction.sales;

/**
 * Handles the errorneous scenario when the current status cannot be determined
 * for a sales order.
 * 
 * @author Roy Terrell
 * 
 */
public class MissingCurrentStatusException extends SalesApiException {
    private static final long serialVersionUID = -1884703323759924257L;

    /**
     * Default constructor
     */
    public MissingCurrentStatusException() {
        super();
    }

    /**
     * 
     * @param msg
     */
    public MissingCurrentStatusException(String msg) {
        super(msg);
    }

    /**
     * 
     * @param e
     */
    public MissingCurrentStatusException(Exception e) {
        super(e);
    }

    /**
     * 
     * @param msg
     * @param e
     */
    public MissingCurrentStatusException(String msg, Throwable e) {
        super(msg, e);
    }
}
