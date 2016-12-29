package org.modules.transaction.sales;

import java.util.List;

import org.AccountingConst.SubsidiaryType;
import org.apache.log4j.Logger;
import org.dao.mapping.orm.rmt2.SalesInvoice;
import org.dao.mapping.orm.rmt2.Xact;
import org.dao.subsidiary.SubsidiaryDaoException;
import org.dao.transaction.XactDao;
import org.dao.transaction.sales.SalesInvoiceDaoException;
import org.dao.transaction.sales.SalesOrderDao;
import org.dao.transaction.sales.SalesOrderDaoException;
import org.dao.transaction.sales.SalesOrderDaoFactory;
import org.dto.ItemMasterDto;
import org.dto.SalesInvoiceDto;
import org.dto.SalesOrderDto;
import org.dto.SalesOrderItemDto;
import org.dto.SalesOrderStatusDto;
import org.dto.SalesOrderStatusHistDto;
import org.dto.XactDto;
import org.dto.adapter.orm.transaction.Rmt2XactDtoFactory;
import org.dto.adapter.orm.transaction.sales.Rmt2SalesOrderDtoFactory;
import org.modules.inventory.InventoryApi;
import org.modules.inventory.InventoryApiFactory;
import org.modules.inventory.InventoryConst;
import org.modules.inventory.InventoryException;
import org.modules.subsidiary.CustomerApi;
import org.modules.subsidiary.CustomerApiException;
import org.modules.subsidiary.SubsidiaryApiFactory;
import org.modules.transaction.AbstractXactApiImpl;
import org.modules.transaction.XactApiException;
import org.modules.transaction.XactConst;
import org.modules.transaction.receipts.CashReceiptApi;
import org.modules.transaction.receipts.CashReceiptApiException;
import org.modules.transaction.receipts.CashReceiptApiFactory;

import com.api.persistence.DaoClient;
import com.util.RMT2Date;
import com.util.RMT2String;

/**
 * @author Roy Terrell
 * 
 */
public class SalesApiImpl extends AbstractXactApiImpl implements SalesApi {

    private static final String SALES_ORDER_NEW_TAG = "[new order]";

    private static final Logger logger = Logger.getLogger(SalesApiImpl.class);

    private SalesOrderDaoFactory daoFact;

    private SalesOrderDao dao;

    /**
     * Creates an SalesApiImpl which creates a stand alone connection.
     */
    SalesApiImpl() {
        super();
        this.dao = this.daoFact.createRmt2OrmDao();
        this.setSharedDao(this.dao);
        return;
    }

    /**
     * Creates an SalesApiImpl which creates a stand alone connection.
     * 
     * @param appName
     *            application name
     */
    protected SalesApiImpl(String appName) {
        super();
        this.dao = this.daoFact.createRmt2OrmDao(appName);
        this.setSharedDao(this.dao);
        return;
    }

    /**
     * Creates an SalesApiImpl initialized with a shared connection, <i>dao</i>.
     * object.
     * 
     * @param connection
     */
    protected SalesApiImpl(DaoClient connection) {
        super(connection);
        this.dao = this.daoFact.createRmt2OrmDao(this.getSharedDao());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.RMT2Base#init()
     */
    @Override
    public void init() {
        super.init();
        this.daoFact = new SalesOrderDaoFactory();
    }

    /**
     * Retrieves a sales order by sales order id from the <i>sales_order</i>
     * table.
     * 
     * @param salesOrderId
     *            the id of the sales order
     * @return an instance of {@link SalesOrderDto}
     * @throws SalesApiException
     */
    @Override
    public SalesOrderDto getSalesOrderById(int salesOrderId)
            throws SalesApiException {
        SalesOrderDto criteria = Rmt2SalesOrderDtoFactory
                .createSalesOrderInstance(null);
        criteria.setSalesOrderId(salesOrderId);

        // SalesOrderDaoFactory f = new SalesOrderDaoFactory();
        // SalesOrderDao dao = f.createRmt2OrmDao();
        List<SalesOrderDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchSalesOrder(criteria);
            if (results == null) {
                return null;
            }
        } catch (SalesOrderDaoException e) {
            buf.append("Database error occurred retrieving sales order by id, ");
            buf.append(salesOrderId);
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new SalesApiException(this.msg, e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // }

        if (results.size() > 1) {
            buf.append("Error: Query method is expecting a single sales order to be returned using sales order id, ");
            buf.append(salesOrderId);
            buf.append(".  Instead ");
            buf.append(results.size());
            buf.append("  were returned.");
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new SalesApiException(this.msg);
        }
        return results.get(0);
    }

    /**
     * Retrieves a sales order status by sales order status id from the
     * <i>sales_order_status</i> table..
     * 
     * @param statusId
     *            the id of the sales order status
     * @return an instance of {@link SalesOrderStatusDto}
     * @throws SalesApiException
     */
    @Override
    public SalesOrderStatusDto getSalesOrderStatusById(int statusId)
            throws SalesApiException {
        SalesOrderStatusDto criteria = Rmt2SalesOrderDtoFactory
                .createSalesOrderStatusInstance(null);
        criteria.setSoStatusId(statusId);

        // SalesOrderDaoFactory f = new SalesOrderDaoFactory();
        // SalesOrderDao dao = f.createRmt2OrmDao();
        List<SalesOrderStatusDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchSalesOrderStatus(criteria);
            if (results == null) {
                return null;
            }
        } catch (SalesOrderDaoException e) {
            buf.append("Database error occurred retrieving sales order status by status id, ");
            buf.append(statusId);
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new SalesApiException(this.msg, e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // }

        if (results.size() > 1) {
            buf.append("Error: Query method is expecting a single sales order status to be returned using sales order status id, ");
            buf.append(statusId);
            buf.append(".  Instead ");
            buf.append(results.size());
            buf.append("  were returned.");
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new SalesApiException(this.msg);
        }
        return results.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.transaction.sales.SalesApi#getSalesOrderInvoiceBySalesOrder
     * (int)
     */
    @Override
    public SalesInvoiceDto getSalesOrderInvoiceBySalesOrder(int salesOrderId)
            throws SalesApiException {
        SalesInvoiceDto criteria = Rmt2SalesOrderDtoFactory
                .createSalesIvoiceInstance((SalesInvoice) null);
        criteria.setSalesOrderId(salesOrderId);

        // SalesOrderDaoFactory f = new SalesOrderDaoFactory();
        // SalesOrderDao dao = f.createRmt2OrmDao();
        List<SalesInvoiceDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchSalesInvoiceExt(criteria);
            if (results == null) {
                return null;
            }
        } catch (SalesInvoiceDaoException e) {
            buf.append("Database error occurred retrieving sales order invoice by sales order id, ");
            buf.append(salesOrderId);
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new SalesApiException(this.msg, e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // }

        if (results.size() > 1) {
            buf.append("Error: Query method is expecting a single sales order invoice to be returned using sales order id, ");
            buf.append(salesOrderId);
            buf.append(".  Instead ");
            buf.append(results.size());
            buf.append("  were returned.");
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new SalesApiException(this.msg);
        }
        return results.get(0);
    }

    /**
     * Retrieves the current status of a sales order from the
     * <i>sales_order_status_hist</i> table.
     * 
     * @param salesOrderId
     *            the id of the sales order
     * @return an instance of {@link SalesOrderStatusHistDto} representing the
     *         current status.
     * @throws SalesApiException
     */
    @Override
    public SalesOrderStatusHistDto getCurrentSalesOrderStatus(int salesOrderId)
            throws SalesApiException {
        // SalesOrderDaoFactory f = new SalesOrderDaoFactory();
        // SalesOrderDao dao = f.createRmt2OrmDao();
        SalesOrderStatusHistDto results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchCurrentSalesOrderStatus(salesOrderId);
            if (results == null) {
                return null;
            }
            return results;
        } catch (SalesOrderDaoException e) {
            buf.append("Database error occurred retrieving the current status for sales order, ");
            buf.append(salesOrderId);
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new SalesApiException(this.msg, e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // }
    }

    /**
     * Verifies whether or not a sales order can change its status to
     * <i>newStatusId</i>.
     * 
     * @param soId
     *            The id of sales order
     * @param newStatusId
     *            The id of the status to apply to the sales order
     * @return {@link SalesOrderStatusHistDto} object representing the current
     *         sales order status before any change.
     * @throws SalesApiException
     *             <i>salesOrderId</i> is not a valid sales order,
     *             <i>newStatusId</i> is not a valid sales order status, or
     *             moving the sales order status to <i>newStatusId</i> would
     *             violate business rules.
     */
    // @Override
    private SalesOrderStatusHistDto evaluateSalesOrderStatusChange(
            int salesOrderId, int newStatusId) throws SalesApiException {
        StringBuilder buf = new StringBuilder();
        // if (!this.isValidSalesOrder(salesOrderId)) {
        // buf.append("Cannot find sales order by sales order id,  ");
        // buf.append(salesOrderId);
        // this.msg = buf.toString();
        // logger.error(this.msg);
        // throw new SalesOrderInvalidException(this.msg);
        // }
        if (!this.isValidSalesOrderStatus(newStatusId)) {
            buf.append("The destination sales order status id is invalid,  ");
            buf.append(newStatusId);
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new SalesOrderStatusInvalidException(this.msg);
        }
        SalesOrderStatusHistDto sosh = this
                .getCurrentSalesOrderStatus(salesOrderId);
        int currentStatusId = (sosh == null ? SalesApiConst.STATUS_CODE_NEW
                : sosh.getSoStatusId());

        // Begin to evaluate current and destination sales order statuses
        switch (newStatusId) {
            case SalesApiConst.STATUS_CODE_QUOTE:
                if (currentStatusId != SalesApiConst.STATUS_CODE_NEW) {
                    this.msg = "Quote status can only be assigned when the sales order is new";
                    logger.error(this.msg);
                    throw new SalesOrderStatusInvalidDestinationException(
                            this.msg);
                }
                break;

            case SalesApiConst.STATUS_CODE_INVOICED:
                if (currentStatusId != SalesApiConst.STATUS_CODE_QUOTE) {
                    this.msg = "Sales order must be in Quote status before changing to Invoiced";
                    logger.error(this.msg);
                    throw new SalesOrderStatusInvalidDestinationException(
                            this.msg);
                }
                break;

            case SalesApiConst.STATUS_CODE_CLOSED:
                if (currentStatusId != SalesApiConst.STATUS_CODE_INVOICED) {
                    this.msg = "Sales order must be in Invoiced status before changing to Closed";
                    logger.error(this.msg);
                    throw new SalesOrderStatusInvalidDestinationException(
                            this.msg);
                }
                break;

            case SalesApiConst.STATUS_CODE_CANCELLED:
                if (currentStatusId != SalesApiConst.STATUS_CODE_INVOICED) {
                    this.msg = "Sales order must be in Invoiced status before changing to Cancelled";
                    logger.error(this.msg);
                    throw new SalesOrderStatusInvalidDestinationException(
                            this.msg);
                }
                break;

            case SalesApiConst.STATUS_CODE_REFUNDED:
                switch (currentStatusId) {
                    case SalesApiConst.STATUS_CODE_INVOICED:
                    case SalesApiConst.STATUS_CODE_CLOSED:
                        break;

                    default:
                        this.msg = "Sales order must be in Invoiced or Closed statuses before changing to Refunded";
                        logger.error(this.msg);
                        throw new SalesOrderStatusInvalidDestinationException(
                                this.msg);
                } // end inner switch
                break;

        } // end outer switch

        return sosh;
    }

    private SalesOrderStatusHistDto changeSalesOrderStatus(int salesOrderId,
            int newStatusId) throws SalesApiException {
        try {
            // Verify that it is okay to move to invoice status
            SalesOrderStatusHistDto sosh = this.evaluateSalesOrderStatusChange(
                    salesOrderId, newStatusId);
            // Terminate the current status
            if (sosh != null) {
                dao.maintain(sosh);
            }
            // Create new invoice status.
            sosh = Rmt2SalesOrderDtoFactory
                    .createSalesOrderStatusHistoryInstance(null);
            sosh.setSoId(salesOrderId);
            sosh.setSoStatusId(newStatusId);
            dao.maintain(sosh);
            return sosh;
        } catch (SalesOrderDaoException e) {
            throw new SalesApiException(e);
        }
    }

    // private SalesOrderStatusHistDto changeSalesOrderStatus(int salesOrderId,
    // int newStatusId, SalesOrderDao dao) throws SalesApiException {
    // try {
    // // Verify that it is okay to move to invoice status
    // SalesOrderStatusHistDto sosh = this.evaluateSalesOrderStatusChange(
    // salesOrderId, newStatusId);
    // // Terminate the current status
    // if (sosh != null) {
    // dao.maintain(sosh);
    // }
    // // Create new invoice status.
    // sosh = Rmt2SalesOrderDtoFactory
    // .createSalesOrderStatusHistoryInstance(null);
    // sosh.setSoId(salesOrderId);
    // sosh.setSoStatusId(newStatusId);
    // dao.maintain(sosh);
    // return sosh;
    // } catch (SalesOrderDaoException e) {
    // throw new SalesApiException(e);
    // }
    // }

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
     * @param customerId
     *            the unique identifier of the customer.
     * @param items
     *            a List of {@link SalesOrderItemDto} instances.
     * @return the id of the sales order created or zero when the sales order is
     *         updated.
     * @throws SalesApiException
     */
    @Override
    public int updateSalesOrder(SalesOrderDto order, int customerId,
            List<SalesOrderItemDto> items) throws SalesApiException {
        order.setCustomerId(customerId);
        StringBuilder buf = new StringBuilder();
        try {
            this.validate(order, items);
        } catch (Exception e) {
            buf.append("Sales order validation failed for sales order  ");
            buf.append((order.getSalesOrderId() > 0 ? order.getSalesOrderId()
                    : SalesApiImpl.SALES_ORDER_NEW_TAG));
            this.msg = buf.toString();
            logger.error(this.msg, e);
            throw new SalesApiException(this.msg, e);
        }

        // Determine if new or existing
        boolean newOrder = order.getSalesOrderId() == 0;
        int rc = 0;
        dao.setDaoUser(this.getApiUser());
        try {
            // Persist base sales order changes
            rc = dao.maintain(order);
            // Remove all existing items for sales order, if applicable
            dao.deleteSalesOrderItems(order.getSalesOrderId());
            // Persist the sales order items
            int itemCount = items.size();
            for (int ndx = 0; ndx < itemCount; ndx++) {
                items.get(ndx).setSalesOrderId(order.getSalesOrderId());
                // Gather inventory details for sales order item.
                this.setupSalesOrderItemInvDetails(items.get(ndx));
                // Persist sales order item
                dao.maintain(items.get(ndx));
            }
            // For new orders, change the order status to "quote"
            if (newOrder) {
                this.changeSalesOrderStatus(order.getSalesOrderId(),
                        SalesApiConst.STATUS_CODE_QUOTE);
            }
            return rc;
        } catch (SalesOrderDaoException e) {
            throw new SalesApiException(e);
        }
    }

    // public int updateSalesOrder(SalesOrderDto order, int customerId,
    // List<SalesOrderItemDto> items) throws SalesApiException {
    //
    // SalesOrderDaoFactory f = new SalesOrderDaoFactory();
    // SalesOrderDao dao = f.createRmt2OrmDao();
    // try {
    // dao.beginTrans();
    // order.setCustomerId(customerId);
    // int rc = this.updateSalesOrder(order, items, dao);
    // dao.commitTrans();
    // this.msg = "Sales order API update succeeded for sales order, "
    // + order.getSalesOrderId();
    // logger.info(this.msg);
    // return rc;
    // } catch (Exception e) {
    // dao.rollbackTrans();
    // this.msg = "Sales order API update failed";
    // logger.error(this.msg, e);
    // throw new SalesApiException(this.msg, e);
    // } finally {
    // dao.close();
    // dao = null;
    // f = null;
    // }

    // this.validate(order, customerId, items);
    //
    // // Determine if new or existing
    // boolean newOrder = order.getSalesOrderId() == 0;
    //
    // int rc = 0;
    // SalesOrderDaoFactory f = new SalesOrderDaoFactory();
    // SalesOrderDao dao = f.createRmt2OrmDao();
    // dao.setDaoUser(this.getApiUser());
    // try {
    // dao.beginTrans();
    // // Persist base sales order changes
    // order.setCustomerId(customerId);
    // rc = dao.maintain(order);
    // // Remove all existing items for sales order, if applicable
    // dao.deleteSalesOrderItems(order.getSalesOrderId());
    // // Persist the sales order items
    // int itemCount = items.size();
    // for (int ndx = 0; ndx < itemCount; ndx++) {
    // items.get(ndx).setSalesOrderId(order.getSalesOrderId());
    // // Gather inventory details for sales order item.
    // this.setupSalesOrderItemInvDetails(items.get(ndx));
    // // Persist sales order item
    // dao.maintain(items.get(ndx));
    // }
    // // For new orders, change the order status to "quote"
    // if (newOrder) {
    // SalesOrderStatusHistDto sosh = this
    // .evaluateSalesOrderStatusChange(
    // order.getSalesOrderId(),
    // SalesApiConst.STATUS_CODE_QUOTE);
    // // Terminate current status, if available
    // if (sosh != null) {
    // dao.maintain(sosh);
    // }
    // // Setup the new status.
    // sosh = Rmt2SalesOrderDtoFactory
    // .createSalesOrderStatusHistoryInstance(null);
    // sosh.setSoId(order.getSalesOrderId());
    // sosh.setSoStatusId(SalesApiConst.STATUS_CODE_QUOTE);
    // dao.maintain(sosh);
    // }
    // dao.commitTrans();
    // return rc;
    // } catch (SalesOrderDaoException e) {
    // dao.rollbackTrans();
    // this.msg = "Sales order API update failed";
    // throw new SalesApiException(this.msg, e);
    // } finally {
    // dao.close();
    // dao = null;
    // }
    // }

    // private int updateSalesOrder(SalesOrderDto order,
    // List<SalesOrderItemDto> items, SalesOrderDao dao)
    // throws SalesApiException {
    // StringBuilder buf = new StringBuilder();
    // try {
    // this.validate(order, items);
    // } catch (Exception e) {
    // buf.append("Sales order validation failed for sales order  ");
    // buf.append((order.getSalesOrderId() > 0 ? order.getSalesOrderId()
    // : SalesApiImpl.SALES_ORDER_NEW_TAG));
    // this.msg = buf.toString();
    // logger.error(this.msg, e);
    // throw new SalesApiException(this.msg, e);
    // }
    //
    // // Determine if new or existing
    // boolean newOrder = order.getSalesOrderId() == 0;
    //
    // int rc = 0;
    // dao.setDaoUser(this.getApiUser());
    // try {
    // // Persist base sales order changes
    // rc = dao.maintain(order);
    // // Remove all existing items for sales order, if applicable
    // dao.deleteSalesOrderItems(order.getSalesOrderId());
    // // Persist the sales order items
    // int itemCount = items.size();
    // for (int ndx = 0; ndx < itemCount; ndx++) {
    // items.get(ndx).setSalesOrderId(order.getSalesOrderId());
    // // Gather inventory details for sales order item.
    // this.setupSalesOrderItemInvDetails(items.get(ndx));
    // // Persist sales order item
    // dao.maintain(items.get(ndx));
    // }
    // // For new orders, change the order status to "quote"
    // if (newOrder) {
    // this.changeSalesOrderStatus(order.getSalesOrderId(),
    // SalesApiConst.STATUS_CODE_QUOTE, dao);
    // }
    // return rc;
    // } catch (SalesOrderDaoException e) {
    // throw new SalesApiException(e);
    // }
    // }

    /**
     * Updates the status of one or more invoiced sales orders to "Closed" when
     * a payment is received.
     * <p>
     * The total amount of selected invoices must not exceed the amount of
     * payment received for the account.
     * 
     * @param order
     *            a List of orders covering the payment.
     * @param xact
     *            an isntance of {@link XactDto} representing the payment
     * @return the total number of orders processed.
     * @throws SalesApiException
     *             <i>orders</i> and/or <i>xaact</i> is null or the sum of all
     *             order totals belonging to <i>orders</i> does not equal
     *             transaction amount of <i>xact</i>.
     */
    public int updateSalesOrderPaymentStatus(List<SalesOrderDto> orders,
            XactDto xact) throws SalesApiException {

        if (orders == null || xact == null) {
            this.msg = "The list of sales orders and/or the transaction object are invalid";
            throw new SalesApiException(this.msg);
        }
        if (orders.size() <= 0) {
            return 0;
        }

        // Verify that total of selected orders equals transaction amount
        double orderTotal = 0;
        for (SalesOrderDto order : orders) {
            orderTotal += order.getOrderTotal();
        }
        if (orderTotal != xact.getXactAmount()) {
            this.msg = "The total dollar amount of selected invoices must be equal to the payment amount received";
            throw new SalesApiException(this.msg);
        }

        // Close out each sales order
        int processCount = 0;
        for (SalesOrderDto order : orders) {
            this.changeSalesOrderStatus(order.getSalesOrderId(),
                    SalesApiConst.STATUS_CODE_CLOSED);
            processCount++;
        }
        return processCount;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.transaction.sales.SalesApi#validate(org.dto.SalesOrderDto ,
     * int, java.util.List)
     */
    @Override
    public void validate(SalesOrderDto order, List<SalesOrderItemDto> items)
            throws SalesApiException {
        if (order == null) {
            this.msg = "Sales order object cannot be null";
            logger.error(this.msg);
            throw new SalesApiException(this.msg);
        }

        // Verify order is valid provided it is masquerading as existing (sales
        // order is greater than zero).
        boolean newOrder = order.getSalesOrderId() == 0;
        if (!newOrder && !this.isValidSalesOrder(order.getSalesOrderId())) {
            this.msg = "Sales order is not valid.  Sales order id: "
                    + order.getSalesOrderId();
            logger.error(this.msg);
            throw new SalesApiException(this.msg);
        }
        // validate customer
        this.validateCustomer(order.getCustomerId());

        // validate item(s)
        this.validateSalesOrderItems(items);

        // // Verify that totals are equal
        // double soItemTotal = 0;
        // StringBuilder buf = new StringBuilder();
        // for (SalesOrderItemDto item : items) {
        // soItemTotal += item.getOrderQty()
        // * (item.getInitUnitCost() * item.getInitMarkup());
        // }
        // if (order.getOrderTotal() != soItemTotal) {
        // buf.append("Sales order amount must equal the sum of sales order item amounts: ");
        // buf.append("base sales order = ");
        // buf.append(order.getOrderTotal());
        // buf.append(" and sum of sales order items = ");
        // buf.append(soItemTotal);
        // this.msg = buf.toString();
        // logger.error(this.msg);
        // throw new SalesApiException(this.msg);
        // }

    }

    private void validateCustomer(int customerId)
            throws SalesOrderInvalidCustomerException {
        if (customerId <= 0) {
            this.msg = "Customer id for sales order must be greater than zero";
            logger.error(this.msg);
            throw new SalesOrderInvalidCustomerException(this.msg);
        }

        SubsidiaryApiFactory custFact = new SubsidiaryApiFactory();
        CustomerApi custApi = custFact.createCustomerApi();
        StringBuilder buf = new StringBuilder();
        try {
            Object test = custApi.getByCustomerId(customerId);
            if (test == null) {
                buf.append("Sales order update failed due to customer id does not exist: ");
                buf.append(customerId);
                this.msg = buf.toString();
                logger.error(this.msg);
                throw new SalesOrderInvalidCustomerException(this.msg);
            }
        } catch (CustomerApiException e) {
            buf.append("Database error occurred while validating the existinece of customer,  ");
            buf.append(customerId);
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new SalesOrderInvalidCustomerException(this.msg, e);
        } finally {
            custApi = null;
        }
    }

    private void validateSalesOrderItems(List<SalesOrderItemDto> items)
            throws SalesOrderInvalidCustomerException {
        StringBuilder buf = new StringBuilder();

        if (items == null) {
            this.msg = "Sales order item object cannot be null for sales order";
            logger.error(this.msg);
            throw new SalesOrderInvalidCustomerException(this.msg);
        }
        if (items.size() <= 0) {
            this.msg = "There must be at least one detail item associated with sales order";
            logger.error(this.msg);
            throw new SalesOrderInvalidCustomerException(this.msg);
        }

        for (SalesOrderItemDto item : items) {
            if (item.getItemId() <= 0) {
                buf.append("One of the sales order items failed validation check for sales order ");
                buf.append("due to the discovery of an invalid item id");
                this.msg = buf.toString();
                logger.error(this.msg);
                throw new SalesOrderInvalidCustomerException(this.msg);
            }
            InventoryApiFactory invFact = new InventoryApiFactory();
            InventoryApi invApi = invFact.createApi(this.dao);
            try {
                Object test = invApi.getItemById(item.getItemId());
                if (test == null) {
                    buf.append("Item id, ");
                    buf.append(item.getItemId());
                    buf.append(", is not found in inventory");
                    this.msg = buf.toString();
                    logger.error(this.msg);
                    throw new SalesOrderInvalidCustomerException(this.msg);
                }
            } catch (InventoryException e) {
                buf.append("Database error occurred while validating the existinece of sales order item, ");
                buf.append(item.getItemId());
                buf.append(", against inventory");
                this.msg = buf.toString();
                logger.error(this.msg);
                throw new SalesOrderInvalidCustomerException(this.msg, e);
            } finally {
                invApi = null;
            }
        }
    }

    private boolean isValidSalesOrder(int salesOrderId)
            throws SalesApiException {
        try {
            if (this.getSalesOrderById(salesOrderId) == null) {
                return false;
            }
            return true;
        } catch (Exception e) {
            throw new SalesApiException(this.msg, e);
        }
    }

    private boolean isValidSalesOrderStatus(int statusId)
            throws SalesApiException {
        try {
            if (this.getSalesOrderStatusById(statusId) == null) {
                return false;
            }
            return true;
        } catch (Exception e) {
            throw new SalesApiException(this.msg, e);
        }
    }

    /**
     * Gather inventory item details to populate sales order item.
     * 
     * @param soi
     *            an instance of {@link SalesOrderItemDto}
     * @throws SalesApiException
     */
    private void setupSalesOrderItemInvDetails(SalesOrderItemDto soi)
            throws SalesApiException {
        double backOrderQty = 0;
        StringBuilder buf = new StringBuilder();
        ItemMasterDto im;
        InventoryApi invApi;
        try {
            InventoryApiFactory invFact = new InventoryApiFactory();
            invApi = invFact.createApi();
            im = invApi.getItemById(soi.getItemId());
        } catch (InventoryException e) {
            buf.append("Unable to obtain inventory details for item, ");
            buf.append(soi.getItemId());
            buf.append(", needed to populate sales order item");
            this.msg = buf.toString();
            throw new SalesApiException(this.msg, e);
        } finally {
            invApi = null;
        }

        // Compare order quantity of item to actual inventory quantity on
        // hand to determine if back order is required.
        if (soi.getOrderQty() > im.getQtyOnHand()) {
            backOrderQty = soi.getOrderQty() - im.getQtyOnHand();
            soi.setBackOrderQty(backOrderQty);
        }
        // Apply to database
        if (soi.getInitMarkup() <= 0) {
            soi.setInitMarkup(im.getMarkup());
        }
        if (soi.getInitUnitCost() <= 0) {
            soi.setInitUnitCost(im.getUnitCost());
        }
    }

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
    @Override
    public int invoiceSalesOrder(SalesOrderDto order,
            List<SalesOrderItemDto> items, boolean receivePayment)
            throws SalesApiException {

        try {
            int rc = this.updateSalesOrder(order, order.getCustomerId(), items);
            rc = this.invoiceSalesOrder(order);
            if (receivePayment) {
                this.applyFullSalesOrderPayment(order, order.getOrderTotal());
            }
            this.msg = "Sales order API update succeeded for sales order, "
                    + order.getSalesOrderId();
            logger.info(this.msg);
            return rc;
        } catch (Exception e) {
            dao.rollbackTrans();
            this.msg = "Sales order API update failed";
            logger.error(this.msg, e);
            throw new SalesApiException(this.msg, e);
        }
    }

    // public int invoiceSalesOrder(SalesOrderDto order,
    // List<SalesOrderItemDto> items, boolean receivePayment)
    // throws SalesApiException {
    //
    // SalesOrderDaoFactory f = new SalesOrderDaoFactory();
    // SalesOrderDao dao = f.createRmt2OrmDao();
    // try {
    // dao.beginTrans();
    // int rc = this.updateSalesOrder(order, items, dao);
    // rc = this.invoiceSalesOrder(order, dao);
    // if (receivePayment) {
    // // TODO: add logic to perform cash receipts transaction for
    // // sales order.
    // }
    // dao.commitTrans();
    // this.msg = "Sales order API update succeeded for sales order, "
    // + order.getSalesOrderId();
    // logger.info(this.msg);
    // return rc;
    // } catch (Exception e) {
    // dao.rollbackTrans();
    // this.msg = "Sales order API update failed";
    // logger.error(this.msg, e);
    // throw new SalesApiException(this.msg, e);
    // } finally {
    // dao.close();
    // dao = null;
    // f = null;
    // }
    // }

    private int invoiceSalesOrder(SalesOrderDto order) throws SalesApiException {

        // // Set shared connection so that we can access the underlying Xact
        // Api
        // // functionality.
        // this.setSharedDao(dao);

        // Create Transaction
        XactDto xact = Rmt2XactDtoFactory.createXactInstance((Xact) null);
        try {
            xact.setXactAmount(order.getOrderTotal());
            xact.setXactTypeId(XactConst.XACT_TYPE_SALESONACCTOUNT);
            int xactId = 0;
            xactId = super.update(xact, null);
            // Verify change
            xact = super.getXactById(xactId);
            if (xact == null) {
                this.msg = "Problem invoicing sales order because transaction could not be verified";
                throw new SalesApiException(this.msg);
            }

            // Take care of any single quote literals.
            String reason = RMT2String.replaceAll(xact.getXactReason(), "''",
                    "'");
            xact.setXactReason(reason);
            // Revise transaction amount in the event other charges were
            // included.
            double revisedXactAmount = this.getSalesOrderTotal(order
                    .getSalesOrderId());
            XactDao xactDao = this.getXactDao();
            xactDao.maintain(xact);

            // Create customer activity (transaction history) regarding sale
            // order transaction.
            super.createSubsidiaryTransaction(order.getCustomerId(), xactId,
                    revisedXactAmount);
        } catch (Exception e) {
            this.msg = "Sales order transaction creation failed";
            throw new SalesApiException(this.msg, e);
        }

        // Setup invoice
        int rc = 0;
        String invoiceNumber = this.createInvoiceNumber(order);
        SalesInvoiceDto si = Rmt2SalesOrderDtoFactory
                .createSalesIvoiceInstance((SalesInvoice) null);
        si.setInvoiceNo(invoiceNumber);
        si.setSalesOrderId(order.getSalesOrderId());
        si.setXactId(xact.getXactId());
        try {
            rc = dao.maintainInvoice(si);
        } catch (SalesInvoiceDaoException e) {
            this.msg = "Sales order invoice creation failed";
            throw new SalesApiException(this.msg, e);
        }

        // Flag Sales order base as invoiced and update sales order total with
        // transaction amount
        try {
            order.setOrderTotal(xact.getXactAmount());
            order.setInvoiced(true);
            dao.maintain(order);
        } catch (SalesOrderDaoException e) {
            this.msg = "Switching sales order invoiced flag to true failed";
            throw new SalesApiException(this.msg, e);
        }

        // Change the order status to "invoiced"
        this.changeSalesOrderStatus(order.getSalesOrderId(),
                SalesApiConst.STATUS_CODE_INVOICED);

        // Deallocate inventory based on order quantity of each sales order item
        this.updateInventory(order.getSalesOrderId(),
                SalesApiConst.UPDATE_INV_ADD);

        return rc;
    }

    /**
     * Create cash receipt transaction of the entire amount of sales order.
     * <p>
     * This transaction will change the status of the sales order to "Closed".
     * 
     * @param order
     *            an instnace of {@link SalesOrderDto}
     * @param amount
     *            the payment amount to be applied to the sales order which
     *            should equal the amount contained in <i>
     *            {@link SalesOrderDto#getOrderTotal()
     *            <i>order.getOrderTotal()</i>}
     * @throws SalesApiException
     *             The sales order total in <i>order</i> does not equal
     *             <i>amount</i>.
     */
    private void applyFullSalesOrderPayment(SalesOrderDto order, double amount)
            throws SalesApiException {

        if (order.getOrderTotal() != amount) {
            this.msg = "Sales order total and the desired payment amount must equal in order to fulfill full payment on the sales order, "
                    + order.getSalesOrderId();
            logger.error(this.msg);
            throw new SalesApiException(this.msg);
        }
        // Apply the payment
        this.applySalesOrderPayment(order, amount);

        // Change sales order status to closed when full payment is made
        this.changeSalesOrderStatus(order.getSalesOrderId(),
                SalesApiConst.STATUS_CODE_CLOSED);
    }

    /**
     * Create cash receipt transaction for a given sales order.
     * 
     * @param order
     *            an instnace of {@link SalesOrderDto}
     * @param amount
     *            the payment amount to be applied to the sales order.
     * @throws SalesApiException
     *             General error in regards to applying the customer payment.
     */
    private void applySalesOrderPayment(SalesOrderDto order, double amount)
            throws SalesApiException {

        // Create cash receipt transaction of the entire amount of
        // sales order being reversed. This will offset the original cash
        // receipt transaction that was created along with sales order that
        // is currently being reversed.
        CashReceiptApiFactory crFact = new CashReceiptApiFactory();
        CashReceiptApi crApi = crFact.createApi(this.dao);
        try {
            crApi.applyCustomerPayment(order, order.getCustomerId());
        } catch (CashReceiptApiException e) {
            this.msg = "Unable to apply customer payment sales order, "
                    + order.getSalesOrderId();
            logger.error(this.msg);
            throw new SalesApiException(this.msg, e);
        } finally {
            crApi = null;
        }
    }

    /**
     * Generates an invoice number that will be formatted as <i>&lt;sales order
     * id&gt;-&lt;yyyyMMdd&gt;</i>
     * 
     * @param salesOrder
     *            an instance of {@link SalesOrderDto}
     * @return Invoice number A String value
     * @throws SalesApiException
     *             the date part is unobtainable.
     */
    @Override
    public String createInvoiceNumber(SalesOrderDto salesOrder)
            throws SalesApiException {
        String datePart = null;
        String invoiceNumber = null;
        java.util.Date today = new java.util.Date();
        datePart = RMT2Date.combineDateParts(today);
        if (datePart == null) {
            this.msg = "Problem assigning an invoice number";
            throw new SalesApiException(this.msg);
        }
        invoiceNumber = salesOrder.getSalesOrderId() + "-" + datePart;
        return invoiceNumber;
    }

    /**
     * Updates inventory based on the items associated with a given sales order.
     * <p>
     * Decreases inventory by the order quantity of each sales order item. This
     * applies to only those sales order items that are of type "Merchandise".
     * 
     * @param salesOrderId
     *            the id of the sales order
     * @param action
     *            indicates whether to push or pull sales order items to or from
     *            inventory respectively.
     * @param dao
     *            the sales order DAO object
     * @return the total number of items recognized for the sales order
     * @throws SalesApiException
     */
    private int updateInventory(int salesOrderId, int action)
            throws SalesApiException {

        InventoryApiFactory invFact = new InventoryApiFactory();
        InventoryApi api = invFact.createApi(dao);
        List<SalesOrderItemDto> items;
        int merchCount = 0;
        try {
            items = dao.fetchSalesOrderItem(salesOrderId);
            for (SalesOrderItemDto soi : items) {
                ItemMasterDto invItem = api.getItemById(soi.getItemId());
                if (invItem.getItemTypeId() == InventoryConst.ITEM_TYPE_MERCH) {
                    int orderQty = new Double(soi.getOrderQty()).intValue();
                    switch (action) {
                        case SalesApiConst.UPDATE_INV_ADD:
                            api.pullInventory(invItem.getItemId(), orderQty);
                            break;
                        case SalesApiConst.UPDATE_INV_DEPLETE:
                            api.pushInventory(invItem.getItemId(), orderQty);
                            break;
                    }
                    merchCount++;
                }
            }
        } catch (Exception e) {
            this.msg = "Problem occurred updating inventory by invoiced sales order items";
            throw new SalesApiException(this.msg);
        } finally {
            invFact = null;
        }
        return merchCount;
    }

    // private int updateInventory(int salesOrderId, int action, SalesOrderDao
    // dao)
    // throws SalesApiException {
    //
    // InventoryApiFactory invFact = new InventoryApiFactory();
    // InventoryApi api = invFact.createApi(dao);
    // List<SalesOrderItemDto> items;
    // int merchCount = 0;
    // try {
    // items = dao.fetchSalesOrderItem(salesOrderId);
    // for (SalesOrderItemDto soi : items) {
    // ItemMasterDto invItem = api.getItemById(soi.getItemId());
    // if (invItem.getItemTypeId() == InventoryConst.ITEM_TYPE_MERCH) {
    // int orderQty = new Double(soi.getOrderQty()).intValue();
    // switch (action) {
    // case SalesApiConst.UPDATE_INV_ADD:
    // api.pullInventory(invItem.getItemId(), orderQty);
    // break;
    // case SalesApiConst.UPDATE_INV_DEPLETE:
    // api.pushInventory(invItem.getItemId(), orderQty);
    // break;
    // }
    // merchCount++;
    // }
    // }
    // } catch (Exception e) {
    // this.msg =
    // "Problem occurred updating inventory by invoiced sales order items";
    // throw new SalesApiException(this.msg);
    // } finally {
    // invFact = null;
    // }
    // return merchCount;
    // }

    /**
     * Cancels a sales order.
     * <p>
     * The sales order must be in <i>invoiced</i> status.
     * 
     * @param salesOrderId
     *            The sales order id
     * @return the transaction id of the sales order cancellation.
     * @throws SalesApiException
     */
    @Override
    public int cancelSalesOrder(int salesOrderId) throws SalesApiException {
        // Obtain the current status of the sales order
        SalesOrderStatusHistDto sosh;
        try {
            sosh = dao.fetchCurrentSalesOrderStatus(salesOrderId);
            if (sosh == null
                    || sosh.getSoStatusId() != SalesApiConst.STATUS_CODE_INVOICED) {
                this.msg = "Problem cancelling sales order.  Discovered that the current sales order status is not set as \"Invoiced\"";
                throw new SalesApiException(this.msg);
            }
        } catch (SalesOrderDaoException e) {
            this.msg = "Error occurred trying to obtain current status for sales order id, "
                    + salesOrderId;
            throw new SalesApiException(this.msg, e);
        }

        try {
            // dao.beginTrans();
            XactDto rc = this.cancelSalesOrder(salesOrderId,
                    XactConst.XACT_TYPE_CANCEL);

            // Change sales order status to cancelled
            this.changeSalesOrderStatus(salesOrderId,
                    SalesApiConst.STATUS_CODE_CANCELLED);
            // dao.commitTrans();
            this.msg = "Sales order API cancellation succeeded for sales order, "
                    + salesOrderId;
            logger.info(this.msg);
            return rc.getXactId();
        } catch (Exception e) {
            // dao.rollbackTrans();
            this.msg = "Sales order API cancellation failed";
            logger.error(this.msg, e);
            throw new SalesApiException(this.msg, e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // f = null;
        // }
    }

    private XactDto cancelSalesOrder(int salesOrderId, int xactTypeId)
            throws SalesApiException {

        // Verify that sales order exists
        SalesOrderDto so = this.getSalesOrderById(salesOrderId);
        if (so == null) {
            this.msg = "Problem cancelling sales order, " + salesOrderId
                    + ".  Sales order  does not exist";
            throw new SalesApiException(this.msg);
        }
        if (!so.isInvoiced()) {
            this.msg = "Problem cancelling sales order.  Sales order must be flagged as Invoiced";
            throw new SalesApiException(this.msg);
        }

        // Verify that sales order invoice exists
        SalesInvoiceDto si = this
                .getSalesOrderInvoiceBySalesOrder(salesOrderId);
        if (si == null) {
            this.msg = "Problem cancelling sales order.  Unable to fetch sales order invoice record";
            throw new SalesApiException(this.msg);
        }

        // Verify that transaction object is modifiable
        XactDto xact;
        try {
            xact = this.getXactById(si.getXactId());
            if (!super.isTransModifiable(xact)) {
                this.msg = "Sales Order cannot be cancelled, because its associated transaction has been finalized";
                throw new SalesApiException(this.msg);
            }
        } catch (XactApiException e1) {
            this.msg = "Problem cancelling sales order.  Unable to fetch transaction associated with the sales order invoice record";
            throw new SalesApiException(this.msg);
        }
        logger.info("Sales order was found to be invoiced");

        // Put back inventory for each sales order item
        this.updateInventory(so.getSalesOrderId(),
                SalesApiConst.UPDATE_INV_DEPLETE);
        logger.info("Sales order items were pushed back to inventory");

        // Flag this transaction to be final.
        try {
            super.finalizeXact(xact);
        } catch (XactApiException e) {
            this.msg = "Problem cancelling sales order.  Unable to finalize transaction associated with the sales order invoice record";
            throw new SalesApiException(this.msg);
        }

        // Apply the revesed transaction amount
        int newXactId = 0;
        try {
            xact.setXactAmount(xact.getXactAmount()
                    * XactConst.REVERSE_MULTIPLIER);
            xact.setXactSubtypeId(xactTypeId);
            switch (xactTypeId) {
                case XactConst.XACT_TYPE_CANCEL:
                    xact.setXactReason("Cancelled Sales Order "
                            + so.getSalesOrderId());
                    break;
                case XactConst.XACT_TYPE_SALESRETURNS:
                    xact.setXactReason("Refunded Sales Order "
                            + so.getSalesOrderId());
                    break;
            }
            newXactId = this.update(xact, null);
            logger.info("Sales order transaction was cancelled.  New transaction id is: "
                    + newXactId);
        } catch (XactApiException e) {
            this.msg = "Problem cancelling sales order.  Unable to reverse transaction associated with the sales order invoice record";
            throw new SalesApiException(this.msg);
        }

        // Create customer activity (transaction history) regarding sale
        // order cancellation transaction. Basically this is a transaction
        // reversal.
        try {
            super.createSubsidiaryTransaction(so.getCustomerId(), newXactId,
                    xact.getXactAmount());
        } catch (XactApiException e) {
            this.msg = "Problem cancelling sales order.  Unable to create customer transaction history for the cancellation transaction associated with the sales order invoice record";
            throw new SalesApiException(this.msg);
        }

        // xact.setXactId(newXactId);
        return xact;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.sales.SalesApi#deleteSalesOrder(int)
     */
    @Override
    public int deleteSalesOrder(int salesOrderId) throws SalesApiException {
        // SalesOrderDaoFactory f = new SalesOrderDaoFactory();
        // SalesOrderDao dao = f.createRmt2OrmDao();
        int rc = 0;

        SalesOrderStatusHistDto sosh = this
                .getCurrentSalesOrderStatus(salesOrderId);
        SalesOrderStatusDto sos = this.getSalesOrderStatusById(sosh
                .getSoStatusId());
        switch (sos.getSoStatusId()) {
            case SalesApiConst.STATUS_CODE_QUOTE:
            case SalesApiConst.STATUS_CODE_NEW:
                break;
            default:
                this.msg = "Sales order must be in quote status in order to be deleted.  Sales Order Id:  "
                        + salesOrderId;
                logger.info(this.msg);
                throw new SalesApiException(this.msg);
        }

        try {
            // dao.beginTrans();
            rc = dao.deleteSalesOrderStatus(salesOrderId);
            rc = dao.deleteSalesOrderItems(salesOrderId);
            rc = dao.deleteSalesOrder(salesOrderId);
            // dao.commitTrans();
        } catch (SalesOrderDaoException e) {
            // dao.rollbackTrans();
            this.msg = "Error deleting sales order, " + salesOrderId;
            logger.info(this.msg, e);
            throw new SalesApiException(this.msg, e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // }
        return rc;
    }

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
    public double getSalesOrderTotal(int salesOrderId) throws SalesApiException {
        double soTotal = 0;

        soTotal += this.calcSalesOrderItemTotal(salesOrderId);
        soTotal += this.calcSalesOrderFees(salesOrderId);
        soTotal += this.calcSalesOrderTaxes(salesOrderId);
        soTotal += this.calcSalesOrderOtherCharges(salesOrderId);
        return soTotal;
    }

    /**
     * Calculates sales order item total. Override the method to implement
     * customizations for item total
     * 
     * @param salesOrderId
     *            The sales order id of of items to calculate
     * @return Total retail dollar amount of all items of a sales order
     * @throws SalesApiException
     */
    private double calcSalesOrderItemTotal(int salesOrderId)
            throws SalesApiException {
        // SalesOrderDaoFactory f = new SalesOrderDaoFactory();
        // SalesOrderDao dao = f.createRmt2OrmDao();
        List<SalesOrderItemDto> items = null;
        double amt = 0;

        try {
            items = dao.fetchSalesOrderItem(salesOrderId);
        } catch (SalesOrderDaoException e) {
            this.msg = "Error fetching items for sales order, " + salesOrderId;
            logger.info(this.msg, e);
            throw new SalesApiException(this.msg, e);
        }
        if (items == null) {
            return 0;
        }
        for (SalesOrderItemDto soi : items) {
            amt += soi.getOrderQty()
                    * (soi.getInitUnitCost() * soi.getInitMarkup());
        }
        return amt;
    }

    /**
     * Calculates sales order fees. Override the method to implement
     * customizations for fee calculations
     * 
     * @param _orderId
     *            The sales order id used to calculate fees
     * @return Fee amount
     * @throws SalesApiException
     */
    private double calcSalesOrderFees(int _orderId) throws SalesApiException {
        return 0;
    }

    /**
     * Calculates sales order taxes. Override the method to implement
     * customizations for sales order tax calculations
     * 
     * @param _orderId
     *            The sales order id used to calculate taxes
     * @return Sales Order tax amount
     * @throws SalesApiException
     */
    private double calcSalesOrderTaxes(int _orderId) throws SalesApiException {
        return 0;
    }

    /**
     * Calculate other charges pertaining to the sales order
     * 
     * @param _orderId
     *            The id of the sales order to calculate other charges
     * @return The amount of other charges
     * @throws SalesApiException
     */
    private double calcSalesOrderOtherCharges(int _orderId)
            throws SalesApiException {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.sales.SalesApi#refundSalesOrder(int)
     */
    @Override
    public int refundSalesOrder(int salesOrderId) throws SalesApiException {
        // Obtain the current status of the sales order
        SalesOrderStatusHistDto sosh;
        try {
            sosh = dao.fetchCurrentSalesOrderStatus(salesOrderId);
            if (sosh == null
                    || sosh.getSoStatusId() != SalesApiConst.STATUS_CODE_CLOSED) {
                this.msg = "Problem refunding sales order.  The current sales order status is required to be \"Closed\"";
                throw new SalesApiException(this.msg);
            }
        } catch (SalesOrderDaoException e) {
            this.msg = "Error occurred trying to obtain current status for sales order id, "
                    + salesOrderId;
            throw new SalesApiException(this.msg, e);
        }

        try {
            XactDto rc = this.cancelSalesOrder(salesOrderId,
                    XactConst.XACT_TYPE_SALESRETURNS);
            // The amount should not require any changes since it is expected to
            // have a negative value.
            rc.setXactId(0);
            rc.setXactSubtypeId(XactConst.XACT_TYPE_SALESRETURNS);
            rc.setXactReason("Reversed trans amount related to the refunding of sales order "
                    + salesOrderId);
            SalesOrderDto so = this.getSalesOrderById(salesOrderId);

            // Create cash receipt transaction of the entire amount of
            // sales order being reversed. This will offset the original cash
            // receipt transaction that was created along with sales order that
            // is currently being reversed.
            CashReceiptApiFactory crFact = new CashReceiptApiFactory();
            CashReceiptApi crApi = crFact.createApi(this.dao);
            try {
                crApi.createCashPayment(rc, so.getCustomerId());
            } catch (CashReceiptApiException e) {
                this.msg = "Unable to apply cash receipt reversal for the refunding of sales order, "
                        + salesOrderId;
                logger.error(this.msg);
                throw new SalesApiException(this.msg, e);
            } finally {
                crApi = null;
            }

            // Change sales order status to refunded
            this.changeSalesOrderStatus(salesOrderId,
                    SalesApiConst.STATUS_CODE_REFUNDED);
            this.msg = "Sales order API refund succeeded for sales order, "
                    + salesOrderId;
            logger.info(this.msg);
            return rc.getXactId();
        } catch (Exception e) {
            this.msg = "Sales order API refund failed";
            logger.error(this.msg, e);
            throw new SalesApiException(this.msg, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.AbstractXactApiImpl#
     * evaluateSubsidiaryType(int)
     */
    @Override
    public SubsidiaryType evaluateSubsidiaryType(int subsidiaryId)
            throws SubsidiaryDaoException {
        return SubsidiaryType.CUSTOMER;
    }

}