package org.modules.timesheet.invoice;

import com.InvalidDataException;

/**
 * Handles errors pertaining to a timesheet being in an invalid satate for billing.
 * 
 * @author Roy Terrell
 * 
 */
public class InvalidStateForBillingException extends InvalidDataException {

    private static final long serialVersionUID = -8563975448348160007L;

    /**
     * 
     */
    public InvalidStateForBillingException() {
        super();
    }

    /**
     * @param msg
     */
    public InvalidStateForBillingException(String msg) {
        super(msg);
    }

    /**
     * @param e
     */
    public InvalidStateForBillingException(Exception e) {
        super(e);
    }

    /**
     * @param msg
     * @param e
     */
    public InvalidStateForBillingException(String msg, Throwable e) {
        super(msg, e);
    }

}
