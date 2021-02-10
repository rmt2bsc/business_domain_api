package org.modules.transaction.receipts;

import org.dto.SalesOrderDto;
import org.dto.XactDto;
import org.modules.transaction.XactApi;

/**
 * An API interface form managing sales order payment transactions.
 * 
 * @author Roy Terrell
 * 
 */
public interface CashReceiptApi extends XactApi {

    /**
     * Creates a customer payment transaction or reverses and existing customer
     * payment transaction.
     * 
     * @param xact
     *            Source transaction.
     * @param customerId
     *            The id of the customer of this transaction.
     * @return The id of the sales order payment transaction.
     * @throws CashReceiptApiException
     */
    int receivePayment(XactDto xact, Integer customerId) throws CashReceiptApiException;

    /**
     * Applies cash receipt transaction for the invoiced sales order.
     * 
     * @param order
     * @param amount
     * @return transaction id
     * @throws CashReceiptApiException
     */
    int applyPaymentToInvoice(SalesOrderDto order, Double amount) throws CashReceiptApiException;
}
