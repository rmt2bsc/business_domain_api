package org.modules.transaction.sales;

import java.util.List;

import org.dto.SalesInvoiceDto;
import org.dto.SalesOrderDto;
import org.dto.SalesOrderItemDto;
import org.dto.SalesOrderStatusDto;
import org.dto.SalesOrderStatusHistDto;
import org.dto.XactDto;
import org.modules.transaction.XactApi;

/**
 * An API interface form managing sales related transactions.
 * 
 * Functional capabilities would be the creation and management of sales orders
 * and sales invoices. Also provides various methods to create and maintain
 * historical occurrences of sales related transactions.
 * 
 * @author Roy Terrell
 * 
 */
public interface SalesApi extends XactApi {

    /**
     * Retrieves sales order based on selection criteria.
     * 
     * @param criteria
     *            an instance of {@link SalesOrderDto} which contains the
     *            selection criteria
     * @return a List of {@link SalesOrderDto} objects
     * @throws SalesApiException
     */
    List<SalesOrderDto> getSalesOrder(SalesOrderDto criteria) throws SalesApiException;

    /**
     * Retrieves a sales order by sales order id.
     * 
     * @param salesOrderId
     *            the id of the sales order
     * @return an instance of {@link SalesOrderDto}
     * @throws SalesApiException
     */
    SalesOrderDto getSalesOrder(Integer salesOrderId) throws SalesApiException;

    /**
     * Retireve sales order items.
     * 
     * @param salesOrderId
     * @return
     * @throws SalesApiException
     */
    List<SalesOrderItemDto> getLineItems(Integer salesOrderId) throws SalesApiException;
    
    /**
     * Retireve sales order items.
     * 
     * @param salesOrderId
     * @return
     * @throws SalesApiException
     */
    List<SalesOrderItemDto> getLineItemsExt(Integer salesOrderId) throws SalesApiException;

    /**
     * Retrieves a sales order invoice by sales order id.
     * 
     * @param salesOrderId
     *            the id of the sales order
     * @return an instance of {@link SalesInvoiceDto}
     * @throws SalesApiException
     */
    SalesInvoiceDto getInvoice(Integer salesOrderId) throws SalesApiException;

    /**
     * Retrieves a list of sales order invoices using selection criteira.
     * 
     * @param criteria
     * @return List of {@link SalesInvoiceDto}
     * @throws SalesApiException
     */
    List<SalesInvoiceDto> getInvoice(SalesInvoiceDto criteria) throws SalesApiException;

    /**
     * Retrieves a sales order status by sales order status id.
     * 
     * @param statusId
     *            the id of the sales order status
     * @return an instance of {@link SalesOrderStatusDto}
     * @throws SalesApiException
     */
    SalesOrderStatusDto getStatus(Integer statusId) throws SalesApiException;

    /**
     * Retrieves the current status of a sales order.
     * 
     * @param salesOrderId
     *            the id of the sales order
     * @return an instance of {@link SalesOrderStatusHistDto}
     * @throws SalesApiException
     */
    SalesOrderStatusHistDto getCurrentStatus(Integer salesOrderId) throws SalesApiException;

    /**
     * Generates an invoice number.
     * 
     * @param salesOrder
     * @return Invoice number
     * @throws SalesApiException
     */
    String createInvoiceNumber(SalesOrderDto salesOrder) throws SalesApiException;

    /**
     * Creates a new or updates and existing sales order.
     * <p>
     * In order for the sales order to be persisted (created or modified)
     * successfully, the sales order must pass all validations as mandated by
     * the method, {@link SalesApi#validate(SalesOrderDto, int, List)}. Once the
     * <i>customerId</i> is deemed valid, the sales order is assoicated with the
     * customer by assinging <i>customerId</i> to <i>order</i>.
     * 
     * @param order
     *            an instance of {@link SalesOrderDto}.
     * @param items
     *            a List of {@link SalesOrderItemDto} instances.
     * @return the id of the sales order created or zero when the sales order is
     *         updated.
     * @throws SalesApiException
     */
    int updateSalesOrder(SalesOrderDto order, List<SalesOrderItemDto> items) 
            throws SalesApiException;

    /**
     * Closes selected invoiced sales orders based on payment on account transaction.
     * <p>
     * Updates the status of one or more invoiced sales orders to "Closed" when
     * a payment is received. The total amount of selected invoices must equal 
     * the amount of payment received for the account.
     * 
     * @param order
     *            a List of orders covering the payment.
     * @param paymentXact
     *            an isntance of {@link XactDto} representing the payment
     * @return the total number of orders processed.
     * @throws SalesApiException
     * @throws InvalidDataException Validation error
     */
    int closeSalesOrderForPayment(List<SalesOrderDto> orders, XactDto paymentXact) 
            throws SalesApiException;

    /**
     * Creates a sales invoice from a selected sales order.
     * 
     * @param order
     *            an instance of {@link SalesOrderDto}
     * @param items
     *            an instance of {@link SalesOrderItemDto}
     * @param receivePayment
     *            indicates whether or not a full payment transaction should be
     *            performed.
     * @return invoice id
     * @throws SalesApiException
     */
    int invoiceSalesOrder(SalesOrderDto order, List<SalesOrderItemDto> items, 
            boolean receivePayment) throws SalesApiException;

    /**
     * Cancels a sales order.
     * 
     * @param salesOrderId
     *            The sales order id
     * @return the transaction id of the sales order cancellation.
     * @throws SalesApiException
     */
    int cancelSalesOrder(Integer salesOrderId) throws SalesApiException;

    /**
     * Refund sales order.
     * 
     * @param salesOrderId
     * @return
     * @throws SalesApiException
     */
    int refundSalesOrder(Integer salesOrderId) throws SalesApiException;

    /**
     * Deletes a sales order including its items.
     * <p>
     * The sales order must be currently in quote status in order to be deleted.
     * 
     * @param salesOrderId
     *            the id of the target sales order.
     * @return totall number of sales orders deleted
     * @throws SalesApiException
     */
    int deleteSalesOrder(Integer salesOrderId) throws SalesApiException;

    /**
     * Calculates the sales order total at retail.
     * <p>
     * The sales order total encompasses item total, sales order fees, sales
     * order taxes, and other charges.
     * 
     * @param salesOrderId
     *            the sales order id
     * @return the sales order total amount
     * @throws SalesApiException
     */
    double calculateTotal(int salesOrderId) throws SalesApiException;
}
