package org.modules.transaction;

/**
 * Used to flag occurrences when the base transaction amount does not balance
 * with the sum of transaction detail item amounts.
 * 
 * @author Roy Terrell
 * 
 */
public class TransactionAmountsUnbalancedException extends XactApiException {
    private static final long serialVersionUID = -1884703323759924257L;

    public TransactionAmountsUnbalancedException() {
        super();
    }

    public TransactionAmountsUnbalancedException(String msg) {
        super(msg);
    }

    public TransactionAmountsUnbalancedException(Exception e) {
        super(e);
    }

    public TransactionAmountsUnbalancedException(String msg, Exception e) {
        super(msg, e);
    }
}
