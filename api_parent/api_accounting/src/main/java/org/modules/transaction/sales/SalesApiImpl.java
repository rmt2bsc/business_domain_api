package org.modules.transaction.sales;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.modules.CommonAccountingConst;
import org.modules.inventory.InventoryApi;
import org.modules.inventory.InventoryApiException;
import org.modules.inventory.InventoryApiFactory;
import org.modules.inventory.InventoryConst;
import org.modules.subsidiary.CustomerApi;
import org.modules.subsidiary.CustomerApiException;
import org.modules.subsidiary.SubsidiaryApiFactory;
import org.modules.transaction.AbstractXactApiImpl;
import org.modules.transaction.XactApiException;
import org.modules.transaction.XactConst;
import org.modules.transaction.receipts.CashReceiptApi;
import org.modules.transaction.receipts.CashReceiptApiException;
import org.modules.transaction.receipts.CashReceiptApiFactory;

import com.InvalidDataException;
import com.NotFoundException;
import com.RMT2RuntimeException;
import com.api.persistence.DaoClient;
import com.util.RMT2Date;
import com.util.RMT2String;
import com.util.assistants.Verifier;
import com.util.assistants.VerifyException;

/**
 * This class implements the functionality required for creating, maintaining,
 * cancelling, refunding, and trackings sales orders.
 * <p>
 * When a sales order is invoiced, the base transaction amount is posted to the
 * xact table as a positive value, and the customer activity amount is posted as
 * a positive value which increases the value of the company's revenue and the
 * customer's account. Conversely, when a sales order is cancelled or refunded,
 * the base transaction amount is posted to the xact table as a negative value,
 * and the customer activity amount is posted as negative value which decreases
 * the value of the company's revenue and the customer's account.
 * 
 * @author Roy Terrell
 * 
 */
public class SalesApiImpl extends AbstractXactApiImpl implements SalesApi {

    private static final String SALES_ORDER_NEW_TAG = "[new order]";

    private static final Logger logger = Logger.getLogger(SalesApiImpl.class);

    private SalesOrderDaoFactory daoFact;

    private SalesOrderDao dao;
    
    private Map<Integer, ItemMasterDto> itemMasterCache;

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
        this.itemMasterCache = new HashMap<>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.transaction.sales.SalesApi#getSalesOrder(org.dto.SalesOrderDto
     * )
     */
    @Override
    public List<SalesOrderDto> getSalesOrder(SalesOrderDto criteria) throws SalesApiException {
        try {
            Verifier.verifyNotNull(criteria);
        } catch (VerifyException e) {
            throw new InvalidDataException("Sales order selection criteria object is required", e);
        }

        List<SalesOrderDto> results = null;
        try {
            results = dao.fetchSalesOrder(criteria);
            return results;
        } catch (SalesOrderDaoException e) {
            StringBuilder buf = new StringBuilder();
            buf.append("Sales order DAO error occurred");
            buf.append(criteria.toString());
            this.msg = buf.toString();
            throw new SalesApiException(this.msg, e);
        }
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
    public SalesOrderDto getSalesOrder(Integer salesOrderId) throws SalesApiException {
        try {
            Verifier.verifyNotNull(salesOrderId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Sales order id is required", e);
        }
        try {
            Verifier.verifyPositive(salesOrderId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Sales order id must be greater than zero", e);
        }

        SalesOrderDto criteria = Rmt2SalesOrderDtoFactory.createSalesOrderInstance(null);
        criteria.setSalesOrderId(salesOrderId);
        List<SalesOrderDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = this.getSalesOrder(criteria);
            if (results == null) {
                return null;
            }
        } catch (SalesApiException e) {
            buf.append("Sales order DAO error occurred when retrieving sales order by id, ");
            buf.append(salesOrderId);
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new SalesApiException(this.msg, e);
        }

        if (results.size() > 1) {
            buf.append("Error: Method was expecting a single sales order to be returned using sales order id, ");
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.transaction.sales.SalesApi#getSalesOrderItems(java.lang.Integer
     * )
     */
    @Override
    public List<SalesOrderItemDto> getLineItems(Integer salesOrderId) throws SalesApiException {
        try {
            Verifier.verifyNotNull(salesOrderId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Sales order id is required", e);
        }
        try {
            Verifier.verifyPositive(salesOrderId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Sales order id must be greater than zero", e);
        }

        List<SalesOrderItemDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchSalesOrderItem(salesOrderId);
            return results;
        } catch (SalesOrderDaoException e) {
            buf.append("Sales Order DAO error occurred fetching line items for sales order id, ");
            buf.append(salesOrderId);
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new SalesApiException(this.msg, e);
        }
    }

    /**
     * Retireve sales order items.
     * <p>
     * Each sales order item object in the result set will contain extra data
     * pertaiing to sales order, customer, vendor, item master, and item master type.
     * 
     * @param salesOrderId
     * @return
     * @throws SalesApiException
     */
    @Override
    public List<SalesOrderItemDto> getLineItemsExt(Integer salesOrderId)
            throws SalesApiException {
        try {
            Verifier.verifyNotNull(salesOrderId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Sales order id is required", e);
        }
        try {
            Verifier.verifyPositive(salesOrderId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Sales order id must be greater than zero", e);
        }

        List<SalesOrderItemDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchExtSalesOrderItem(salesOrderId);
            return results;
        } catch (SalesOrderDaoException e) {
            buf.append("Sales Order DAO error occurred fetching line items for sales order id, ");
            buf.append(salesOrderId);
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new SalesApiException(this.msg, e);
        }
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
    public SalesOrderStatusDto getStatus(Integer statusId) throws SalesApiException {
        try {
            Verifier.verifyNotNull(statusId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Sales order status id is required", e);
        }
        try {
            Verifier.verifyPositive(statusId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Sales order status id must be greater than zero", e);
        }
        SalesOrderStatusDto criteria = Rmt2SalesOrderDtoFactory.createSalesOrderStatusInstance(null);
        criteria.setSoStatusId(statusId);

        List<SalesOrderStatusDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchSalesOrderStatus(criteria);
            if (results == null) {
                return null;
            }
        } catch (SalesOrderDaoException e) {
            buf.append("DAO error occurred retrieving sales order status by status id, ");
            buf.append(statusId);
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new SalesApiException(this.msg, e);
        }

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
    public SalesInvoiceDto getInvoice(Integer salesOrderId) throws SalesApiException {
        try {
            Verifier.verifyNotNull(salesOrderId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Sales order id is required", e);
        }
        try {
            Verifier.verifyPositive(salesOrderId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Sales order id must be greater than zero", e);
        }

        SalesInvoiceDto criteria = Rmt2SalesOrderDtoFactory.createSalesIvoiceInstance((SalesInvoice) null);
        criteria.setSalesOrderId(salesOrderId);
        List<SalesInvoiceDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchExtSalesInvoice(criteria);
            if (results == null) {
                return null;
            }
        } catch (SalesInvoiceDaoException e) {
            buf.append("DAO error occurred fetching sales order invoice by sales order id, ");
            buf.append(salesOrderId);
            this.msg = buf.toString();
            throw new SalesApiException(this.msg, e);
        }

        if (results.size() > 1) {
            buf.append("Error: Query method is expecting a single sales order invoice to be returned using sales order id, ");
            buf.append(salesOrderId);
            buf.append(".  Instead ");
            buf.append(results.size());
            buf.append("  were returned.");
            this.msg = buf.toString();
            throw new SalesApiException(this.msg);
        }
        return results.get(0);
    }

    /**
     * Retrieves a list of sales order invoices using selection criteira.
     * <p>
     * The DTO returned from the query will contain information that extends beyond
     * the sale invoice entity such as sales order, sales order status, and customer 
     * information.
     * 
     * @param criteria
     * @return List of {@link SalesInvoiceDto}
     * @throws SalesApiException
     */
    @Override
    public List<SalesInvoiceDto> getInvoice(SalesInvoiceDto criteria) throws SalesApiException {
        try {
            Verifier.verifyNotNull(criteria);
        } catch (VerifyException e) {
            throw new InvalidDataException("Extended Sales order criteria object is required", e);
        }

        List<SalesInvoiceDto> results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchExtSalesInvoice(criteria);
            if (results == null) {
                return null;
            }
        } catch (SalesInvoiceDaoException e) {
            buf.append("Sales order DAO error occurred retrieving extended sales order invoice data");
            this.msg = buf.toString();
            throw new SalesApiException(this.msg, e);
        }
        return results;
    }

    /**
     * Retrieves the current status of a sales order from the
     * <i>sales_order_status_hist</i> table.
     * 
     * @param salesOrderId
     *            the id of the sales order
     * @return an instance of {@link SalesOrderStatusHistDto} representing the
     *         current status.
     * @throws SalesApiException DAO related errors
     * @throws InvalidInvalidDataException
     *             <i>salesOrderId</i> is null or less than or equal to zero.
     * @throws MissingCurrentStatusException
     *             Current status could not be obtained due to a sales order id
     *             that is non existent or the sales order history does not
     *             contain a current status which is an anomaly.
     */
    @Override
    public SalesOrderStatusHistDto getCurrentStatus(Integer salesOrderId) throws SalesApiException {
        try {
            Verifier.verifyNotNull(salesOrderId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Sales order id is required", e);
        }
        try {
            Verifier.verifyPositive(salesOrderId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Sales order id must be greater than zero", e);
        }

        SalesOrderStatusHistDto results;
        StringBuilder buf = new StringBuilder();
        try {
            results = dao.fetchCurrentSalesOrderStatus(salesOrderId);
            if (results == null) {
                throw new MissingCurrentStatusException(
                        "Unable to obtain current status of sales order [sales order id="
                                + salesOrderId
                                + "].  Either the sales order id is invalid or there is a database anomaly");
            }
            return results;
        } catch (SalesOrderDaoException e) {
            buf.append("DAO error occurred retrieving the current status for sales order, ");
            buf.append(salesOrderId);
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new SalesApiException(this.msg, e);
        }
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
    private SalesOrderStatusHistDto evaluateSalesOrderStatusChange(int salesOrderId, 
            int newStatusId) throws SalesApiException {
        StringBuilder buf = new StringBuilder();
        if (!this.isValidSalesOrderStatus(newStatusId)) {
            buf.append("The destination sales order status id is invalid,  ");
            buf.append(newStatusId);
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new SalesOrderStatusInvalidException(this.msg);
        }
        
        // Get current status of sales order
        SalesOrderStatusHistDto sosh = null;
        int currentStatusId = 0;
        try {
            sosh = this.getCurrentStatus(salesOrderId);    
            currentStatusId = sosh.getSoStatusId();
        } catch (MissingCurrentStatusException e) {
            currentStatusId = SalesApiConst.STATUS_CODE_NEW;
        }
        
        // Determine if it is okay to navigate from the current sales order 
        // status to the new destination status. 
        switch (newStatusId) {
            case SalesApiConst.STATUS_CODE_QUOTE:
                if (currentStatusId != SalesApiConst.STATUS_CODE_NEW) {
                    this.msg = "Quote status can only be assigned when the sales order is new";
                    logger.error(this.msg);
                    throw new SalesOrderStatusInvalidDestinationException(this.msg);
                }
                break;

            case SalesApiConst.STATUS_CODE_INVOICED:
                if (currentStatusId != SalesApiConst.STATUS_CODE_QUOTE) {
                    this.msg = "Sales order must be in Quote status before changing to Invoiced";
                    logger.error(this.msg);
                    throw new SalesOrderStatusInvalidDestinationException(this.msg);
                }
                break;

            case SalesApiConst.STATUS_CODE_CLOSED:
                if (currentStatusId != SalesApiConst.STATUS_CODE_INVOICED) {
                    this.msg = "Sales order must be in Invoiced status before changing to Closed";
                    logger.error(this.msg);
                    throw new SalesOrderStatusInvalidDestinationException(this.msg);
                }
                break;

            case SalesApiConst.STATUS_CODE_CANCELLED:
                if (currentStatusId != SalesApiConst.STATUS_CODE_INVOICED) {
                    this.msg = "Sales order must be in Invoiced status before changing to Cancelled";
                    logger.error(this.msg);
                    throw new SalesOrderStatusInvalidDestinationException(this.msg);
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
                        throw new SalesOrderStatusInvalidDestinationException(this.msg);
                } // end inner switch
                break;

        } // end outer switch

        return sosh;
    }

    private SalesOrderStatusHistDto changeSalesOrderStatus(int salesOrderId, int newStatusId) throws SalesApiException {
        try {
            // Verify that it is okay to move to invoice status
            SalesOrderStatusHistDto sosh = 
                    this.evaluateSalesOrderStatusChange(salesOrderId, newStatusId);
                    
            // Terminate the current status
            if (sosh != null) {
                dao.maintain(sosh);
            }
            // Create new invoice status.
            sosh = Rmt2SalesOrderDtoFactory.createSalesOrderStatusHistoryInstance(null);
            sosh.setSoId(salesOrderId);
            sosh.setSoStatusId(newStatusId);
            dao.maintain(sosh);
            return sosh;
        } catch (SalesOrderDaoException e) {
            this.msg = "DAO error occurred while attempting to update the sales order status history";
            throw new SalesApiException(this.msg, e);
        } catch (SalesOrderStatusInvalidDestinationException | SalesOrderStatusInvalidException e) {
            this.msg = "Unable to update sales order status history due to the destination status id failed validations";
            throw new SalesApiException(this.msg, e);
        }
    }

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
    public int updateSalesOrder(SalesOrderDto order, List<SalesOrderItemDto> items) 
            throws SalesApiException {
        logger.info("Began processing sales order " + order.getSalesOrderId() + "...");
        try {
            logger.info("Validating sales order...");
            this.validate(order, items);
        } catch (Exception e) {
            StringBuilder buf = new StringBuilder();
            buf.append("Sales order failed validation check ===> Sales order Id: ");
            buf.append((order.getSalesOrderId() > 0 ? order.getSalesOrderId() : SalesApiImpl.SALES_ORDER_NEW_TAG));
            this.msg = buf.toString();
            throw new SalesApiException(this.msg, e);
        }
        logger.info("Validating sales order complete.");

        // Determine if new or existing
        boolean newOrder = order.getSalesOrderId() == 0;
        int rc = 0;
        dao.setDaoUser(this.getApiUser());
        try {
            // Persist base sales order changes
            logger.info("Persisiting base sales orde changes.");
            rc = dao.maintain(order);
            // Remove all existing items for sales order, if applicable
            logger.info("Deleting existing sales order items.");
            dao.deleteSalesOrderItems(order.getSalesOrderId());

            // Persist the sales order items
            logger.info("Adding new sales order items.");
            for (SalesOrderItemDto item : items) {
                item.setSalesOrderId(order.getSalesOrderId());
                // Gather inventory details for sales order item.
                this.setupSalesOrderItemInvDetails(item);
                // Persist sales order item
                dao.maintain(item);
            }
            // For new orders, change the order status to "quote"
            if (newOrder) {
                this.changeSalesOrderStatus(order.getSalesOrderId(), 
                        SalesApiConst.STATUS_CODE_QUOTE);
            }
            logger.info("Processing sales order " + order.getSalesOrderId() + " complete!");
            return rc;
        } catch (SalesOrderDaoException e) {
            logger.error("Processing sales order " + order.getSalesOrderId() + " completed with errors!");
            throw new SalesApiException(e);
        } finally {
            
        }
    }

    /**
     * Updates the status of one or more invoiced sales orders to "Closed" when
     * a payment is received.
     * <p>
     * The total amount of selected invoices must equal the amount of
     * payment transaction received for the account.
     * 
     * @param order
     *            a List of orders covering the payment.
     * @param paymentXact
     *            an isntance of {@link XactDto} representing the payment
     * @return the total number of orders processed.
     * @throws SalesApiException
     *             <i>orders</i> and/or <i>xaact</i> is null or the sum of all
     *             order totals belonging to <i>orders</i> does not equal
     *             transaction amount of <i>paymentXact</i>.
     * @throws InvalidDataException <i>orders</i> or <i>paymentXact</i> are null.
     */
    public int closeSalesOrderForPayment(List<SalesOrderDto> orders, XactDto paymentXact) throws SalesApiException {
        try {
            Verifier.verifyNotNull(orders);
        } catch (VerifyException e) {
            this.msg = "The list of sales orders is required";
            throw new InvalidDataException(this.msg, e);
        }
        try {
            Verifier.verifyPositive(orders.size());
        } catch (VerifyException e) {
            this.msg = "There are no sales orders to close out";
            return 0;
        }
        try {
            Verifier.verifyNotNull(paymentXact);
        } catch (VerifyException e) {
            this.msg = "Payment transaction is required";
            throw new InvalidDataException(this.msg, e);
        }

        // Verify that total of selected orders equals transaction amount
        double combinedSalesOrderTotal = 0;
        for (SalesOrderDto order : orders) {
            combinedSalesOrderTotal += order.getOrderTotal();
        }
        try {
            Verifier.verify(combinedSalesOrderTotal == paymentXact.getXactAmount());
        } catch (VerifyException e) {
            this.msg = "The total dollar amount of selected invoices must be equal to the payment amount received";
            throw new SalesApiException(this.msg, e);
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

    /**
     * Validte sales order.
     * <p>
     * The following rules must be met in order for a sales order to be
     * considered valid:
     * <ul>
     * <li><i>order</i> and <i>items</i> cannot be null.</li>
     * <li>The <i>customerId</i> property of <i>order</i> must be greater than
     * zero.</li>
     * <li>The customer must exist in the system.</li>
     * <li>There must be at least one sales order item contained in
     * <i>items</i>.</li>
     * <li>The item id property of each sales order item contained in
     * <i>items</i> must be greater than zero.</li>
     * <li>Each sales order item in <i>items</i> must exist in inventory either
     * as a service (soft) item type or as type merchandise.</li>
     * <li>The base sales order total must equal the sum of ssales order item
     * amounts.</li>
     * </ul>
     * 
     * @param order
     *            an instance of {@link SalesOrderDto}.
     * @param items
     *            a List of {@link SalesOrderItemDto} instances.
     * @throws SalesApiException
     *             when any one of the specified validations are not met.
     */
    protected void validate(SalesOrderDto order, List<SalesOrderItemDto> items) throws SalesApiException {
        try {
            Verifier.verifyNotNull(order);
        } catch (VerifyException e) {
            throw new InvalidDataException("Sales order object is required", e);
        }

        // Verify order is valid provided it is masquerading as existing (sales
        // order is greater than zero).
        boolean newOrder = order.getSalesOrderId() == 0;
        if (!newOrder) {
            try {
                Verifier.verify(this.isValidSalesOrder(order.getSalesOrderId()));
            } catch (VerifyException e) {
                this.msg = "Sales order does not exist.  Sales order id: " + order.getSalesOrderId();
                throw new NotFoundException(this.msg, e);
            } catch (SalesApiException e) {
                this.msg = "I/O error occurred validating sales order [" + order.getSalesOrderId() + "]";
                throw new RMT2RuntimeException(this.msg, e);
            }
        }

        // validate customer
        this.validateCustomer(order.getCustomerId());    

        // validate item(s)
        this.validateSalesOrderItems(items);
    }

    private void validateCustomer(int customerId) throws SalesApiException {
        try {
            Verifier.verifyPositive(customerId);
        } catch (VerifyException e) {
            throw new SalesOrderCustomerIdInvalidException("Customer id must be greater than zero", e);
        }

        SubsidiaryApiFactory custFact = new SubsidiaryApiFactory();
        CustomerApi custApi = custFact.createCustomerApi(CommonAccountingConst.APP_NAME);
        StringBuilder buf = new StringBuilder();
        try {
            Object test = custApi.get(customerId);
            if (test == null) {
                buf.append("Customer does not exists for sales order update operation.  Customer Id [");
                buf.append(customerId);
                buf.append("]");
                this.msg = buf.toString();
                logger.error(this.msg);
                throw new SalesOrderCustomerIdInvalidException(this.msg);
            }
        } catch (CustomerApiException e) {
            buf.append("Database error occurred while validating the existinece of customer,  ");
            buf.append(customerId);
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new RMT2RuntimeException(this.msg, e);
        } finally {
            custApi = null;
        }
    }

    private void validateSalesOrderItems(List<SalesOrderItemDto> items) throws SalesApiException {
        try {
            Verifier.verifyNotNull(items);
        } catch (VerifyException e) {
            throw new InvalidDataException("Sales order items list object cannot be null for sales order", e);
        }

        try {
            Verifier.verifyPositive(items.size());
        } catch (VerifyException e) {
            throw new InvalidDataException("There must be at least one detail item associated with sales order", e);
        }

        StringBuilder buf = new StringBuilder();
        for (SalesOrderItemDto item : items) {
            try {
                Verifier.verifyPositive(item.getItemId());
            } catch (VerifyException e) {
                buf.append("One of the sales order items failed validation check for sales order ");
                buf.append("due to the discovery of an invalid item id");
                this.msg = buf.toString();
                throw new InvalidDataException(this.msg, e);
            }

            InventoryApiFactory invFact = new InventoryApiFactory();
            InventoryApi invApi = invFact.createApi(this.dao);
            try {
                ItemMasterDto imDto = invApi.getItemById(item.getItemId());
                try {
                    Verifier.verifyNotNull(imDto);
                    // add inventory item to the internal item master cache.
                    this.itemMasterCache.put(item.getItemId(), imDto);
                    
                } catch (VerifyException e) {
                    buf.append("Item id, ");
                    buf.append(item.getItemId());
                    buf.append(", is not found in inventory");
                    this.msg = buf.toString();
                    throw new NotFoundException(this.msg, e);
                }
            } catch (InventoryApiException e) {
                buf.append("Database error occurred while validating the existinece of sales order item, ");
                buf.append(item.getItemId());
                buf.append(", against inventory");
                this.msg = buf.toString();
                logger.error(this.msg);
                throw new RMT2RuntimeException(this.msg, e);
            } finally {
                invApi = null;
            }
        }
    }

    private boolean isValidSalesOrder(int salesOrderId) throws SalesApiException {
        try {
            if (this.getSalesOrder(salesOrderId) == null) {
                return false;
            }
            return true;
        } catch (Exception e) {
            throw new SalesApiException(this.msg, e);
        }
    }

    private boolean isValidSalesOrderStatus(int statusId) throws SalesApiException {
        try {
            if (this.getStatus(statusId) == null) {
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
    private void setupSalesOrderItemInvDetails(SalesOrderItemDto soi) throws SalesApiException {
        double backOrderQty = 0;
        StringBuilder buf = new StringBuilder();
        ItemMasterDto im = null;
        try {
            im = this.itemMasterCache.get(soi.getItemId());
            Verifier.verifyNotNull(im);
        }
        catch (VerifyException e) {
            buf.append("Inventory item doe not exist in the item master cache sale order line item, ");
            buf.append(soi.getSoItemId());
            this.msg = buf.toString();
            throw new SalesApiException(this.msg, e);
        }
        catch (Exception e) {
            buf.append("Unable to obtain inventory details for item, ");
            buf.append(soi.getItemId());
            buf.append(", in order to populate sales order item");
            this.msg = buf.toString();
            throw new SalesApiException(this.msg, e);
        }
        
        
        // Compare order quantity of item to actual inventory quantity on
        // hand to determine if back order is required.
        if (soi.getOrderQty() > im.getQtyOnHand()) {
            backOrderQty = soi.getOrderQty() - im.getQtyOnHand();
            soi.setBackOrderQty(backOrderQty);
            soi.setOrderQty(im.getQtyOnHand());
        }
        // Assign inventory markup provided it is not set at the sales order item level
        if (soi.getInitMarkup() <= 0) {
            soi.setInitMarkup(im.getMarkup());
        }
        
        // Assign inventory unit cost provided it is not set at the sales order item level
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
    public int invoiceSalesOrder(SalesOrderDto order, List<SalesOrderItemDto> items, boolean receivePayment)
            throws SalesApiException {

        int rc = this.updateSalesOrder(order, items);
        try {
            rc = this.invoiceSalesOrder(order);
            if (receivePayment) {
                this.applyFullSalesOrderPayment(order, order.getOrderTotal());
            }
            this.msg = "The invoicing of sales order, " + order.getSalesOrderId() + ", was successful!!!!";
            logger.info(this.msg);
            return rc;
        } catch (Exception e) {
            dao.rollbackTrans();
            this.msg = "Sales order API update failed";
            logger.error(this.msg, e);
            throw new SalesApiException(this.msg, e);
        }
    }

    private int invoiceSalesOrder(SalesOrderDto order) throws SalesApiException {
        logger.info("Began invoicing sales order " + order.getSalesOrderId() + "...");
        // Create Transaction
        XactDto xact = Rmt2XactDtoFactory.createXactInstance((Xact) null);
        try {
            logger.info("Creating transaction entry for sales order.");
            xact.setXactAmount(order.getOrderTotal());
            xact.setXactTypeId(XactConst.XACT_TYPE_SALESONACCTOUNT);
            int xactId = 0;
            xactId = super.update(xact, null);
            // Verify change
            xact = super.getXactById(xactId);
            if (xact == null) {
                this.msg = "Unable to verify the invoicing of sales order " + order.getSalesOrderId() + ".";
                throw new SalesApiException(this.msg);
            }

            // Take care of any single quote literals.
            String reason = RMT2String.replaceAll(xact.getXactReason(), "''", "'");
            xact.setXactReason(reason);
            // Revise transaction amount in the event other charges were
            // included.
            double revisedXactAmount = this.calculateTotal(order.getSalesOrderId());
            XactDao xactDao = this.getXactDao();
            xactDao.maintain(xact);

            // Create customer activity (transaction history) regarding sale
            // order transaction.
            logger.info("Creating customer subsidiary activity entries.");
            super.createSubsidiaryActivity(order.getCustomerId(), xactId, revisedXactAmount);
        } catch (Exception e) {
            this.msg = "Sales order transaction creation failed";
            throw new SalesApiException(this.msg, e);
        } finally {
            logger.info("Invoicing of sales order " + order.getSalesOrderId() + " complete!");
        }

        // Setup invoice
        int rc = 0;
        String invoiceNumber = this.createInvoiceNumber(order);
        SalesInvoiceDto si = Rmt2SalesOrderDtoFactory.createSalesIvoiceInstance((SalesInvoice) null);
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
        this.changeSalesOrderStatus(order.getSalesOrderId(), SalesApiConst.STATUS_CODE_INVOICED);

        // Deallocate inventory based on order quantity of each sales order item
        this.updateInventory(order.getSalesOrderId(), SalesApiConst.UPDATE_INV_ADD);

        return rc;
    }

    /**
     * Create cash receipt transaction of the entire amount of sales order.
     * <p>
     * The entire amount of the sales order entails the total of all line 
     * items, sales order fees, taxes, and other sales order charges.
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
    private void applyFullSalesOrderPayment(SalesOrderDto order, double amount) throws SalesApiException {
        logger.info("Began creating cash receipt of sales order " + order.getSalesOrderId() + "...");
        if (order.getOrderTotal() != amount) {
            this.msg = "Sales order total and the desired payment amount must equal in order to fulfill full payment on the sales order, "
                    + order.getSalesOrderId();
            logger.error(this.msg);
            throw new SalesApiException(this.msg);
        }
        // Apply the payment
        this.applySalesOrderPayment(order, amount);

        // Change sales order status to closed when full payment is made
        this.changeSalesOrderStatus(order.getSalesOrderId(), SalesApiConst.STATUS_CODE_CLOSED);
        logger.info("Creation of sales order cash receipt complete!");
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
            crApi.applyPaymentToInvoice(order, amount);
        } catch (CashReceiptApiException e) {
            this.msg = "Unable to apply customer payment sales order, " + order.getSalesOrderId();
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
    public String createInvoiceNumber(SalesOrderDto salesOrder) throws SalesApiException {
        try {
            Verifier.verifyNotNull(salesOrder);
        } catch (VerifyException e) {
            throw new InvalidDataException("Sales order object is required", e);
        }

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
    private int updateInventory(int salesOrderId, int action) throws SalesApiException {

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
    public int cancelSalesOrder(Integer salesOrderId) throws SalesApiException {
        try {
            Verifier.verifyNotNull(salesOrderId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Sales order id is required when cancelling a sales order", e);
        }
        try {
            Verifier.verifyPositive(salesOrderId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Sales order id must be greater than zero", e);
        }

        SalesOrderDto so = this.getSalesOrder(salesOrderId);
        try {
            Verifier.verifyNotNull(so);
        } catch (VerifyException e) {
            throw new InvalidDataException("Sales order does not exists", e);
        }

        // Obtain the current status of the sales order
        SalesOrderStatusHistDto sosh;
        try {
            sosh = dao.fetchCurrentSalesOrderStatus(salesOrderId);
            if (sosh == null || sosh.getSoStatusId() != SalesApiConst.STATUS_CODE_INVOICED) {
                this.msg = "Problem cancelling sales order.  Discovered that the current sales order status is not set as \"Invoiced\"";
                throw new SalesApiException(this.msg);
            }
        } catch (SalesOrderDaoException e) {
            this.msg = "Error occurred trying to obtain current status for sales order id, "
                    + salesOrderId;
            throw new SalesApiException(this.msg, e);
        }

        try {
            XactDto rc = this.cancelSalesOrder(so, XactConst.XACT_SUBTYPE_CANCEL);

            // Change sales order status to cancelled
            this.changeSalesOrderStatus(salesOrderId, SalesApiConst.STATUS_CODE_CANCELLED);
            this.msg = "Sales order API cancellation succeeded for sales order, " + salesOrderId;
            logger.info(this.msg);
            return rc.getXactId();
        } catch (Exception e) {
            this.msg = "Sales order API cancellation failed";
            logger.error(this.msg, e);
            throw new SalesApiException(this.msg, e);
        }
    }

    private XactDto cancelSalesOrder(SalesOrderDto so, int xactTypeId) throws SalesApiException {
        // Verify that sales order is invoiced
        try {
            Verifier.verify(so.isInvoiced());
        } catch (VerifyException e) {
            this.msg = "Sales order must be flagged as Invoiced in order to cancel";
            throw new SalesApiException(this.msg, e);
        }

        // Verify that sales order invoice exists
        SalesInvoiceDto si = this.getInvoice(so.getSalesOrderId());
        if (si == null) {
            this.msg = "Problem cancelling sales order.  Unable to fetch sales order invoice record";
            throw new SalesApiException(this.msg);
        }

        // Verify that transaction object is modifiable
        XactDto xact;
        try {
            xact = this.getXactById(si.getXactId());
            if (!super.isModifiable(xact)) {
                this.msg = "Sales Order cannot be cancelled, because its associated transaction has been finalized";
                throw new SalesApiException(this.msg);
            }
        } catch (XactApiException e1) {
            this.msg = "Problem cancelling sales order.  Unable to fetch transaction associated with the sales order invoice record";
            throw new SalesApiException(this.msg);
        }
        logger.info("Sales order was found to be invoiced");

        // Put back inventory for each sales order item
        this.updateInventory(so.getSalesOrderId(), SalesApiConst.UPDATE_INV_DEPLETE);
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
            xact.setXactAmount(xact.getXactAmount() * XactConst.REVERSE_MULTIPLIER);
            xact.setXactSubtypeId(xactTypeId);
            switch (xactTypeId) {
                case XactConst.XACT_SUBTYPE_CANCEL:
                    xact.setXactReason("Cancelled Sales Order " + so.getSalesOrderId());
                    break;
                case XactConst.XACT_TYPE_SALESRETURNS:
                    xact.setXactReason("Refunded Sales Order " + so.getSalesOrderId());
                    break;
            }
            newXactId = this.update(xact, null);
            logger.info("Sales order transaction was cancelled.  New transaction id is: " + newXactId);
        } catch (XactApiException e) {
            this.msg = "Problem cancelling sales order.  Unable to reverse transaction associated with the sales order invoice record";
            throw new SalesApiException(this.msg);
        }

        // Create customer activity (transaction history) regarding sale
        // order cancellation transaction. Basically this is a transaction
        // reversal.
        try {
            super.createSubsidiaryActivity(so.getCustomerId(), newXactId, xact.getXactAmount());
        } catch (XactApiException e) {
            this.msg = "Problem cancelling sales order.  Unable to create customer transaction history for the cancellation transaction associated with the sales order invoice record";
            throw new SalesApiException(this.msg);
        }
        return xact;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.sales.SalesApi#deleteSalesOrder(int)
     */
    @Override
    public int deleteSalesOrder(Integer salesOrderId) throws SalesApiException {
        try {
            Verifier.verifyNotNull(salesOrderId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Sales order id is required when deleting a sales order", e);
        }

        int rc = 0;
        SalesOrderStatusHistDto sosh = this.getCurrentStatus(salesOrderId);
        SalesOrderStatusDto sos = this.getStatus(sosh.getSoStatusId());
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
            rc = dao.deleteSalesOrderStatus(salesOrderId);
            rc = dao.deleteSalesOrderItems(salesOrderId);
            rc = dao.deleteSalesOrder(salesOrderId);
        } catch (SalesOrderDaoException e) {
            this.msg = "Error deleting sales order, " + salesOrderId;
            logger.info(this.msg, e);
            throw new SalesApiException(this.msg, e);
        }
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
    @Override
    public double calculateTotal(int salesOrderId) throws SalesApiException {
        try {
            Verifier.verifyNotNull(salesOrderId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Sales order id is required", e);
        }
        try {
            Verifier.verifyPositive(salesOrderId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Sales order id must be greater than zero", e);
        }

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
    private double calcSalesOrderItemTotal(int salesOrderId) throws SalesApiException {
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
            amt += soi.getOrderQty() * (soi.getInitUnitCost() * soi.getInitMarkup());
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
    private double calcSalesOrderOtherCharges(int _orderId) throws SalesApiException {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.transaction.sales.SalesApi#refundSalesOrder(int)
     */
    @Override
    public int refundSalesOrder(Integer salesOrderId) throws SalesApiException {
        try {
            Verifier.verifyNotNull(salesOrderId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Sales order id is required", e);
        }
        try {
            Verifier.verifyPositive(salesOrderId);
        } catch (VerifyException e) {
            throw new InvalidDataException("Sales order id must be greater than zero", e);
        }

        SalesOrderDto so = this.getSalesOrder(salesOrderId);
        try {
            Verifier.verifyNotNull(so);
        } catch (VerifyException e) {
            throw new InvalidDataException("Sales order does not exists", e);
        }

        // Obtain the current status of the sales order
        SalesOrderStatusHistDto sosh;
        try {
            sosh = dao.fetchCurrentSalesOrderStatus(salesOrderId);
        } catch (SalesOrderDaoException e) {
            this.msg = "I/O error getting current status for sales order id, " + salesOrderId;
            throw new SalesApiException(this.msg, e);
        }
        try {
            Verifier.verifyNotNull(sosh);
        } catch (VerifyException e) {
            throw new SalesApiException("Unable to obtain current sales order status which is required", e);
        }
        try {
            Verifier.verify(sosh.getSoStatusId() == SalesApiConst.STATUS_CODE_CLOSED);
        } catch (VerifyException e) {
            throw new SalesApiException("The current sales order status is required to be \"Closed\"", e);
        }

        try {
            XactDto rc = this.cancelSalesOrder(so, XactConst.XACT_TYPE_SALESRETURNS);
            // The amount should not require any changes since it is expected to
            // have a negative value.
            rc.setXactId(0);
            rc.setXactSubtypeId(XactConst.XACT_TYPE_SALESRETURNS);
            rc.setXactReason("Reversed trans amount related to the refunding of sales order " + salesOrderId);

            // Create cash receipt transaction of the entire amount of
            // sales order being reversed. This will offset the original cash
            // receipt transaction that was originally created along with sales
            // order that is currently being reversed.
            CashReceiptApiFactory crFact = new CashReceiptApiFactory();
            CashReceiptApi crApi = crFact.createApi(this.dao);
            try {
                crApi.receivePayment(rc, so.getCustomerId());
            } catch (CashReceiptApiException e) {
                this.msg = "Unable to apply cash receipt reversal for the refunding of sales order, " + salesOrderId;
                throw new SalesApiException(this.msg, e);
            } finally {
                crApi = null;
            }

            // Change sales order status to refunded
            this.changeSalesOrderStatus(salesOrderId, SalesApiConst.STATUS_CODE_REFUNDED);
            this.msg = "Sales order API refund succeeded for sales order, " + salesOrderId;
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
    public SubsidiaryType evaluateSubsidiaryType(Integer subsidiaryId) throws SubsidiaryDaoException {
        return SubsidiaryType.CUSTOMER;
    }
}