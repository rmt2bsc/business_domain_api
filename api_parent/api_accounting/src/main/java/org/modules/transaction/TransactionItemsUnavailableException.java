package org.modules.transaction;

/**
 * Used to flag occurrences when a transaction is not accompanied with detail
 * items.
 * 
 * @author Roy Terrell
 * 
 */
public class TransactionItemsUnavailableException extends XactApiException {
    private static final long serialVersionUID = -1884703323759924257L;

    public TransactionItemsUnavailableException() {
        super();
    }

    public TransactionItemsUnavailableException(String msg) {
        super(msg);
    }

    public TransactionItemsUnavailableException(Exception e) {
        super(e);
    }

    public TransactionItemsUnavailableException(String msg, Exception e) {
        super(msg, e);
    }
}
