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
    int createCashPayment(XactDto xact, int customerId)
            throws CashReceiptApiException;

    /**
     * Applies cash receipt transaction for the invoiced sales order.
     * 
     * @param order
     * @param amount
     * @throws CashReceiptApiException
     */
    void applyCustomerPayment(SalesOrderDto order, double amount)
            throws CashReceiptApiException;

    /**
     * Emails the payment confirmation to the customer.
     * 
     * @param salesOrderId
     *            the sales order id
     * @param xactId
     *            the transaction id
     * @throws CashReceiptApiException
     */
    void emailPaymentConfirmation(int salesOrderId, int xactId)
            throws CashReceiptApiException;
}
