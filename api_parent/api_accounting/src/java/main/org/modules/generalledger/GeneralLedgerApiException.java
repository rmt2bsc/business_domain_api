package org.modules.generalledger;

import org.modules.AccountingModuleException;

/**
 * An exception class for handling general ledger errors.
 * 
 * @author Roy Terrell
 * 
 */
public class GeneralLedgerApiException extends AccountingModuleException {

    private static final long serialVersionUID = -7802047187719081753L;

    /**
     * 
     */
    public GeneralLedgerApiException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     */
    public GeneralLedgerApiException(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param e
     */
    public GeneralLedgerApiException(Exception e) {
        super(e);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param msg
     * @param e
     */
    public GeneralLedgerApiException(String msg, Throwable e) {
        super(msg, e);
        // TODO Auto-generated constructor stub
    }

}
