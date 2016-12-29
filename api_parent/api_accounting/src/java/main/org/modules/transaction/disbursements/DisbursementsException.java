package org.modules.transaction.disbursements;

import org.modules.transaction.sales.SalesApiException;

/**
 * Handles disbursements errors.
 * 
 * @author Roy Terrell
 * 
 */
public class DisbursementsException extends SalesApiException {
    private static final long serialVersionUID = -1884703323759924257L;

    public DisbursementsException() {
        super();
    }

    public DisbursementsException(String msg) {
        super(msg);
    }

    public DisbursementsException(Exception e) {
        super(e);
    }

    public DisbursementsException(String msg, Exception e) {
        super(msg, e);
    }
}
