package org.dao.transaction.sales;

import java.util.List;

import org.dao.transaction.XactDao;
import org.dto.SalesInvoiceDto;
import org.dto.SalesOrderDto;
import org.dto.SalesOrderItemDto;
import org.dto.SalesOrderStatusDto;
import org.dto.SalesOrderStatusHistDto;

/**
 * Interface for accessing and managing data pertaining to sales order and sales
 * invoice transactions.
 * 
 * @author Roy Terrell
 * 
 */
public interface SalesOrderDao extends XactDao {

    /**
     * Queries basic sales order data based on selectin criteria contained in
     * <i>criteria</i>
     * 
     * @param criteria
     *            an instance of {@link SalesOrderDto}
     * @return a List of {@link SalesOrderDto} or null when no data is found
     * @throws SalesOrderDaoException
     */
    List<SalesOrderDto> fetchSalesOrder(SalesOrderDto criteria)
            throws SalesOrderDaoException;

    /**
     * Queries basic sales invoice data based on selectin criteria contained in
     * <i>criteria</i>
     * 
     * @param criteria
     *            an instance of {@link SalesInvoiceDto}
     * @return a List of {@link SalesInvoiceDto} or null when no data is found
     * @throws SalesInvoiceDaoException
     */
    List<SalesInvoiceDto> fetchSalesInvoice(SalesInvoiceDto criteria)
            throws SalesInvoiceDaoException;

    /**
     * Queries extended sales order information, which includes sales order,
     * sales invoice, and customer related data, based on selectin criteria
     * contained in <i>criteria</i>
     * 
     * @param criteria
     *            an instance of {@link SalesInvoiceDto}
     * @return a List of {@link SalesInvoiceDto} or null when no data is found
     * @throws SalesInvoiceDaoException
     */
    List<SalesInvoiceDto> fetchExtSalesInvoice(SalesInvoiceDto criteria)
            throws SalesInvoiceDaoException;

    /**
     * Queries all items belonging to a sales order.
     * 
     * @param salesOrderId
     *            the sales order id
     * @return a List of {@link SalesOrderItemDto} or null when no data is found
     * @throws SalesOrderDaoException
     */
    List<SalesOrderItemDto> fetchSalesOrderItem(int salesOrderId)
            throws SalesOrderDaoException;

    /**
     * Queries all items belonging to a sales order in which includes sales
     * order, sales order item, customer, item master and item master type
     * information for each sales order item.
     * 
     * @param salesOrderId
     *            the sales order id
     * @return a List of {@link SalesOrderItemDto} or null when no data is found
     * @throws SalesOrderDaoException
     */
    List<SalesOrderItemDto> fetchExtSalesOrderItem(int salesOrderId)
            throws SalesOrderDaoException;

    /**
     * Queries sales order status data based on selectin criteria contained in
     * <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link SalesOrderStatusDto}
     * @return a List of {@link SalesOrderStatusDto} or null when no data is
     *         found
     * @throws SalesOrderDaoException
     */
    List<SalesOrderStatusDto> fetchSalesOrderStatus(SalesOrderStatusDto criteria)
            throws SalesOrderDaoException;

    /**
     * Queries sales order status history data based on selectin criteria
     * contained in <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link SalesOrderStatusHistDto}
     * @return a List of {@link SalesOrderStatusHistDto} or null when no data is
     *         found
     * @throws SalesOrderDaoException
     */
    List<SalesOrderStatusHistDto> fetchSalesOrderStatusHistory(
            SalesOrderStatusHistDto criteria) throws SalesOrderDaoException;

    /**
     * Queries the current status of a particular sales order.
     * 
     * @param salesOrderId
     *            sales order id
     * @return an instance of {@link SalesOrderStatusHistDto} or null when no
     *         data is found
     * @throws SalesOrderDaoException
     */
    SalesOrderStatusHistDto fetchCurrentSalesOrderStatus(int salesOrderId)
            throws SalesOrderDaoException;

    /**
     * Persists the base sales order.
     * <p>
     * The sales order id property of <i>order</i> determines whether to create
     * a new or modify an existing sales order. The sales order is considered to
     * already exist and all changes will be applied as an update when the sales
     * order id is greater than or equal to "1". Otherwise, the sales order will
     * be identified as "new" and added to the system for the first time.
     * 
     * @param order
     *            an instance of {@link SalesOrderDto}.
     * @return the id of the sales order when an insert is performed or the
     *         total number of rows effected by the update.
     * @throws SalesOrderDaoException
     */
    int maintain(SalesOrderDto order) throws SalesOrderDaoException;

    /**
     * 
     * @param invoice
     * @return
     * @throws SalesInvoiceDaoException
     */
    int maintainInvoice(SalesInvoiceDto invoice)
            throws SalesInvoiceDaoException;

    /**
     * Persists a sales order item.
     * <p>
     * Ths sales order item must be associated with a valid sales order id.
     * 
     * @param item
     *            an instance of {@link SalesOrderItemDto}.
     * @return the id of the sales order item
     * @throws SalesOrderDaoException
     */
    int maintain(SalesOrderItemDto item) throws SalesOrderDaoException;

    /**
     * Creates a new or updates an existing sales order status.
     * 
     * @param status
     *            an instance of {@link SalesOrderStatusHistDto}
     * @return the total number of rows effected
     * @throws SalesOrderDaoException
     */
    int maintain(SalesOrderStatusHistDto status) throws SalesOrderDaoException;

    /**
     * Deletes a Sales Order.
     * 
     * @param salesOrderId
     *            The id of the sales order
     * @return A count of all items deleted.
     * @throws SalesOrderDaoException
     */
    int deleteSalesOrder(int salesOrderId) throws SalesOrderDaoException;

    /**
     * Deletes one or more items from a sales order.
     * 
     * @param salesOrderId
     *            The id of the sales order
     * @return A count of all items deleted.
     * @throws SalesOrderDaoException
     */
    int deleteSalesOrderItems(int salesOrderId) throws SalesOrderDaoException;

    /**
     * Deletes all statuses of a sales order.
     * 
     * @param salesOrderId
     *            The id of the sales order
     * @return A count of all items deleted.
     * @throws SalesOrderDaoException
     */
    int deleteSalesOrderStatus(int salesOrderId) throws SalesOrderDaoException;
}
