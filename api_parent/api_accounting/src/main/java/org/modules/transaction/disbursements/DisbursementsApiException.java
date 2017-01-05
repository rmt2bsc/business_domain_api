package org.modules.transaction.disbursements;

import org.modules.transaction.sales.SalesApiException;

/**
 * Handles Disbursement API errors.
 * 
 * @author Roy Terrell
 * 
 */
public class DisbursementsApiException extends SalesApiException {
    private static final long serialVersionUID = -1884703323759924257L;

    public DisbursementsApiException() {
        super();
    }

    public DisbursementsApiException(String msg) {
        super(msg);
    }

    public DisbursementsApiException(Exception e) {
        super(e);
    }

    public DisbursementsApiException(String msg, Exception e) {
        super(msg, e);
    }
}
