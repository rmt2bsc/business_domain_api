package org.dao.transaction.sales;

import java.util.List;

import org.dao.mapping.orm.rmt2.SalesInvoice;
import org.dao.mapping.orm.rmt2.SalesOrder;
import org.dao.mapping.orm.rmt2.SalesOrderItems;
import org.dao.mapping.orm.rmt2.SalesOrderStatus;
import org.dao.mapping.orm.rmt2.SalesOrderStatusHist;
import org.dao.mapping.orm.rmt2.VwSalesOrderInvoice;
import org.dao.mapping.orm.rmt2.VwSalesorderItemsBySalesorder;
import org.dao.transaction.Rmt2XactDaoImpl;
import org.dto.SalesInvoiceDto;
import org.dto.SalesOrderDto;
import org.dto.SalesOrderItemDto;
import org.dto.SalesOrderStatusDto;
import org.dto.SalesOrderStatusHistDto;
import org.dto.adapter.orm.transaction.sales.Rmt2SalesOrderDtoFactory;

import com.api.persistence.PersistenceClient;
import com.util.RMT2Date;
import com.util.UserTimestamp;

/**
 * An implementation of {@link SalesOrderDao}. It provides functionality that
 * creates, updates, deletes, and queries sales order related data.
 * 
 * @author Roy Terrell
 * 
 */
public class Rmt2SalesOrderDaoImpl extends Rmt2XactDaoImpl implements
        SalesOrderDao {

    /**
     * Creates a SalesORderDaoImpl object with its own persistent client.
     */
    Rmt2SalesOrderDaoImpl() {
        super();
    }

    /**
     * Creates a SalesORderDaoImpl object with its own persistent client.
     * 
     * @param appName
     */
    Rmt2SalesOrderDaoImpl(String appName) {
        super(appName);
    }

    /**
     * Creates a SalesORderDaoImpl object with a shared persistent client.
     * 
     * @param client
     */
    Rmt2SalesOrderDaoImpl(PersistenceClient client) {
        super(client);
    }

    /**
     * Retrieves sales order data from the <i>sales_order</i> table based on
     * selection criteria contained in <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link SalesOrderDto}
     * @return a List of {@link SalesOrderDto} or null when no data is found
     * @throws SalesOrderDaoException
     */
    @Override
    public List<SalesOrderDto> fetchSalesOrder(SalesOrderDto criteria) throws SalesOrderDaoException {
        SalesOrder obj = SalesOrderDaoFactory.createCriteriaSalesOrder(criteria);
        List<SalesOrder> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new SalesOrderDaoException(e);
        }
        return SalesOrderDaoFactory.createSalesOrder(results);
    }

    /**
     * Retrieves sales invoice data from the <i>sales_invoice</i> table based on
     * selection criteria contained in <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link SalesInvoiceDto}
     * @return a List of {@link SalesInvoiceDto} or null when no data is found
     * @throws SalesInvoiceDaoException
     */
    @Override
    public List<SalesInvoiceDto> fetchSalesInvoice(SalesInvoiceDto criteria) throws SalesInvoiceDaoException {
        SalesInvoice obj = SalesOrderDaoFactory.createCriteriaSalesInvoice(criteria);
        List<SalesInvoice> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new SalesInvoiceDaoException(e);
        }
        return SalesOrderDaoFactory.createSalesInvoice(results);
    }

    /**
     * Retrieves sales invoice data from the <i>sales_invoice</i> table based on
     * selection criteria contained in <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link SalesInvoiceDto}
     * @return a List of {@link SalesInvoiceDto} or null when no data is found
     * @throws SalesInvoiceDaoException
     */
    @Override
    public List<SalesInvoiceDto> fetchSalesInvoiceExt(SalesInvoiceDto criteria) throws SalesInvoiceDaoException {
        VwSalesOrderInvoice obj = SalesOrderDaoFactory.createCriteriaSalesInvoiceExt(criteria);
        List<VwSalesOrderInvoice> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new SalesInvoiceDaoException(e);
        }
        return SalesOrderDaoFactory.createExtSalesInvoice(results);
    }

    /**
     * Retrieves sales order item data from the <i>sales_order_items</i> table
     * based on sales order id supplied by the user.
     * 
     * @param salesOrderId
     *            the id of the sale order
     * @return a List of {@link SalesOrderItemDto} or null when no data is found
     * @throws SalesOrderDaoException
     */
    @Override
    public List<SalesOrderItemDto> fetchSalesOrderItem(int salesOrderId) throws SalesOrderDaoException {
        SalesOrderItems criteria = new SalesOrderItems();
        criteria.addCriteria(SalesOrderItems.PROP_SOID, salesOrderId);
        List<SalesOrderItems> results = null;
        try {
            results = this.client.retrieveList(criteria);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new SalesOrderDaoException(e);
        }
        return SalesOrderDaoFactory.createSalesOrderItem(results);
    }

    /**
     * Retrieves extended sales order data from the
     * <i>vw_sales_order_invoice</i> view based on selection criteria contained
     * in <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link SalesInvoiceDto}
     * @return a List of {@link SalesInvoiceDto} or null when no data is found
     * @throws SalesOrderDaoException
     */
    @Override
    public List<SalesInvoiceDto> fetchExtSalesOrder(SalesInvoiceDto criteria) throws SalesOrderDaoException {
        VwSalesOrderInvoice obj = SalesOrderDaoFactory.createCriteriaExtSalesOrder(criteria);
        List<VwSalesOrderInvoice> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new SalesOrderDaoException(e);
        }
        return SalesOrderDaoFactory.createExtSalesInvoice(results);
    }

    /**
     * Retrieves extended sales order item data from the
     * <i>vw_sales_order_items_by_sale_order</i> view based on a patricular
     * sales order.
     * 
     * @param salesOrderId
     *            The id of the sales order
     * @return a List of {@link SalesOrderItemDto} or null when no data is found
     * @throws SalesOrderDaoException
     */
    @Override
    public List<SalesOrderItemDto> fetchExtSalesOrderItem(int salesOrderId) throws SalesOrderDaoException {
        VwSalesorderItemsBySalesorder criteria = new VwSalesorderItemsBySalesorder();
        criteria.addCriteria(VwSalesorderItemsBySalesorder.PROP_SOID, salesOrderId);
        List<VwSalesorderItemsBySalesorder> results = null;
        try {
            results = this.client.retrieveList(criteria);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new SalesOrderDaoException(e);
        }
        return SalesOrderDaoFactory.createExtSalesOrderItem(results);
    }

    /**
     * Retrieves sales order status data from the <i>sales_order_status</i>
     * table based on selection criteria contained in <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link SalesOrderStatusDto}
     * @return a List of {@link SalesOrderStatusDto} or null when no data is
     *         found
     * @throws SalesOrderDaoException
     */
    @Override
    public List<SalesOrderStatusDto> fetchSalesOrderStatus(SalesOrderStatusDto criteria) throws SalesOrderDaoException {
        SalesOrderStatus obj = SalesOrderDaoFactory.createCriteriaSalesOrderStatus(criteria);
        List<SalesOrderStatus> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new SalesOrderDaoException(e);
        }
        return SalesOrderDaoFactory.createSalesOrderStatus(results);
    }

    /**
     * Retrieve sales order status history data from the
     * <i>sales_order_status_hist</i> table based on selectin criteria contained
     * in <i>criteria</i>.
     * 
     * @param criteria
     *            an instance of {@link SalesOrderStatusHistDto}
     * @return a List of {@link SalesOrderStatusHistDto} or null when no data is
     *         found
     * @throws SalesOrderDaoException
     */
    @Override
    public List<SalesOrderStatusHistDto> fetchSalesOrderStatusHistory(SalesOrderStatusHistDto criteria)
            throws SalesOrderDaoException {
        SalesOrderStatusHist obj = SalesOrderDaoFactory.createCriteriaSalesOrderStatusHist(criteria);
        List<SalesOrderStatusHist> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new SalesOrderDaoException(e);
        }
        return SalesOrderDaoFactory.createSalesOrderStatusHist(results);
    }

    /**
     * Retrieve the current sales order status history from the
     * <i>sales_order_status_hist</i> table for a particular sales order
     * 
     * @param salesOrderId
     *            sales order id
     * @return an instance of {@link SalesOrderStatusHistDto} or null when no
     *         data is found
     * @throws SalesOrderDaoException
     */
    @Override
    public SalesOrderStatusHistDto fetchCurrentSalesOrderStatus(int salesOrderId) throws SalesOrderDaoException {
        SalesOrderStatusHist obj = new SalesOrderStatusHist();
        obj.addCriteria(SalesOrderStatusHist.PROP_SOID, salesOrderId);
        String criteria = " end_date is null";
        obj.addCustomCriteria(criteria);
        SalesOrderStatusHist results = null;
        try {
            results = (SalesOrderStatusHist) this.client.retrieveObject(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new SalesOrderDaoException(e);
        }
        SalesOrderStatusHistDto dto = Rmt2SalesOrderDtoFactory
                .createSalesOrderStatusHistoryInstance(results);
        return dto;
    }

    /**
     * Applies the changes of a sales order to the <i>sales_order</i> database
     * table.
     * <p>
     * The sales order id property of <i>order</i> determines whether to create
     * a new or modify an existing sales order. The sales order is considered to
     * already exist and all changes will be applied as an update when the sales
     * order id is greater than or equal to "1". Otherwise, the sales order will
     * be identified as "new" and added to the system for the first time.
     * 
     * @param order
     *            an instance of {@link SalesOrderDto}.
     * @return the id of the sales order when a database insert is performed or
     *         the total number of rows effected by the database update.
     * @throws SalesOrderDaoException
     *             <i>order</i> is null or general database access errors
     */
    @Override
    public int maintain(SalesOrderDto order) throws SalesOrderDaoException {
        if (order == null) {
            throw new SalesOrderDaoException("Sales order DTO cannot be null");
        }

        // Determine if we are creating or modifying a Sales Order.
        int rc;
        SalesOrder so = SalesOrderDaoFactory.createOrmSalesOrder(order);

        if (order.getSalesOrderId() <= 0) {
            rc = this.createSalesOrder(so);
            order.setSalesOrderId(rc);
        }
        else {
            rc = this.updateSalesOrder(so);
        }
        return rc;
    }

    /**
     * Inserts a sales order.
     * 
     * @param so
     *            Sales order object
     * @return the news ales order id
     * @throws SalesOrderDaoException
     */
    private int createSalesOrder(SalesOrder so) throws SalesOrderDaoException {
        int soId = 0;
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            so.setDateCreated(ut.getDateCreated());
            so.setDateUpdated(ut.getDateCreated());
            so.setUserId(ut.getLoginId());
            so.setIpCreated(ut.getIpAddr());
            so.setIpUpdated(ut.getIpAddr());
            soId = this.client.insertRow(so, true);
            return soId;
        } catch (Exception e) {
            throw new SalesOrderDaoException(e);
        }
    }

    /**
     * Updates a sales order.
     * 
     * @param so
     *            The sales order object
     * @return The total number of rows effected by the transaction.
     * @throws SalesOrderDaoException
     */
    private int updateSalesOrder(SalesOrder so) throws SalesOrderDaoException {
        int rc = 0;
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            so.setDateUpdated(ut.getDateCreated());
            so.setUserId(ut.getLoginId());
            so.setIpUpdated(ut.getIpAddr());
            so.addCriteria(SalesOrder.PROP_SOID, so.getSoId());
            rc = this.client.updateRow(so);
            return rc;
        } catch (Exception e) {
            throw new SalesOrderDaoException(e);
        }
    }

    /**
     * Inserts a new sales order item in the <i>sales_order_items</i> database
     * table.
     * 
     * @param item
     *            and instance of {@link SalesOrderItemDto}
     * @return Always returns the new sales order item id.
     * @throws SalesOrderDaoException
     */
    @Override
    public int maintain(SalesOrderItemDto item) throws SalesOrderDaoException {
        int rc = 0;
        SalesOrderItems soi = SalesOrderDaoFactory
                .createOrmSalesOrderItem(item);
        try {
            rc = this.client.insertRow(soi, true);
            item.setSoItemId(rc);
            return rc;
        } catch (Exception e) {
            throw new SalesOrderDaoException(e);
        }
    }

    /**
     * Deletes a Sales Order.
     * <p>
     * The sales order must be currently in quote status in order to be deleted.
     * 
     * @param salesOrderId
     *            The id of the sales order
     * @return total number of sales orders deleted
     * @throws SalesOrderDaoException
     */
    @Override
    public int deleteSalesOrder(int salesOrderId) throws SalesOrderDaoException {
        SalesOrder so = new SalesOrder();
        int rc = 0;
        try {
            so.resetCriteria();
            so.addCriteria(SalesOrder.PROP_SOID, salesOrderId);
            rc = this.client.deleteRow(so);
            return rc;
        } catch (Exception e) {
            this.msg = "An error occurred trying to delete sales order, "
                    + salesOrderId;
            throw new SalesOrderDaoException(this.msg, e);
        }
    }

    /**
     * Deletes all tems from the <i>sales_order_items</i> table realted to
     * <i>salesOrderId</i>.
     * 
     * @param salesOrderId
     *            The id of the sales order
     * @return A count of all items deleted.
     * @throws SalesOrderDaoException
     */
    @Override
    public int deleteSalesOrderItems(int salesOrderId) throws SalesOrderDaoException {
        SalesOrderItems soi = new SalesOrderItems();
        int rc = 0;
        try {
            soi.resetCriteria();
            soi.addCriteria(SalesOrderItems.PROP_SOID, salesOrderId);
            rc = this.client.deleteRow(soi);
            return rc;
        } catch (Exception e) {
            this.msg = "An error occurred trying to delete items from sales order, "
                    + salesOrderId;
            throw new SalesOrderDaoException(this.msg, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.transaction.sales.SalesOrderDao#deleteSalesOrderStatus(int)
     */
    @Override
    public int deleteSalesOrderStatus(int salesOrderId) throws SalesOrderDaoException {
        SalesOrderStatusHist sosh = new SalesOrderStatusHist();
        int rc = 0;
        try {
            sosh.resetCriteria();
            sosh.addCriteria(SalesOrderStatusHist.PROP_SOID, salesOrderId);
            rc = this.client.deleteRow(sosh);
            return rc;
        } catch (Exception e) {
            this.msg = "An error occurred trying to delete all statuses for sales order, "
                    + salesOrderId;
            throw new SalesOrderDaoException(this.msg, e);
        }
    }

    /**
     * Creates a new or updates an existing sales order status.
     * 
     * @param status
     *            an instance of {@link SalesOrderStatusHistDto}
     * @return the total number of rows effected
     * @throws SalesOrderDaoException
     */
    @Override
    public int maintain(SalesOrderStatusHistDto status) throws SalesOrderDaoException {
        // Determine if we are creating or modifying a Sales Order Status
        // History.
        int rc;
        SalesOrderStatusHist sosh = SalesOrderDaoFactory
                .createOrmSalesOrderStatusHist(status);
        if (sosh.getSoStatusHistId() <= 0) {
            rc = this.createSalesOrderStatusHist(sosh);
            status.setSoStatusHistId(rc);
        }
        else {
            rc = this.updateSalesOrderStatusHist(sosh);
        }
        return rc;
    }

    /**
     * Inserts a sales order status history item by setting the end date
     * property to null.
     * 
     * @param sosh
     *            an instance of {@link SalesOrderStatusHist}
     * @return the id of the new sales order status histroy item
     * @throws SalesOrderDaoException
     */
    private int createSalesOrderStatusHist(SalesOrderStatusHist sosh) throws SalesOrderDaoException {
        int rc = 0;
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            sosh.setEffectiveDate(ut.getDateCreated());
            sosh.setNull("endDate");
            sosh.setDateCreated(ut.getDateCreated());
            sosh.setUserId(ut.getLoginId());
            sosh.setIpCreated(ut.getIpAddr());
            sosh.setIpUpdated(ut.getIpAddr());
            rc = this.client.insertRow(sosh, true);
            return rc;
        } catch (Exception e) {
            throw new SalesOrderDaoException(e);
        }
    }

    /**
     * Terminates a sales order status history item by setting the end date
     * property to the current timestamp.
     * 
     * @param sosh
     *            The sales order object
     * @return The total number of rows effected by the transaction.
     * @throws SalesOrderDaoException
     */
    private int updateSalesOrderStatusHist(SalesOrderStatusHist sosh) throws SalesOrderDaoException {
        int rc = 0;
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            sosh.setEndDate(ut.getDateCreated());
            sosh.setUserId(ut.getLoginId());
            sosh.addCriteria(SalesOrderStatusHist.PROP_SOSTATUSHISTID,
                    sosh.getSoStatusHistId());
            sosh.setIpUpdated(ut.getIpAddr());
            rc = this.client.updateRow(sosh);
            return rc;
        } catch (Exception e) {
            throw new SalesOrderDaoException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.transaction.sales.SalesOrderDao#maintainInvoice(org.dto.
     * SalesInvoiceDto)
     */
    @Override
    public int maintainInvoice(SalesInvoiceDto invoice) throws SalesInvoiceDaoException {
        if (invoice == null) {
            throw new SalesInvoiceDaoException("Sales invoice DTO cannot be null");
        }

        int rc;
        SalesInvoice si = SalesOrderDaoFactory.createOrmSalesInvoice(invoice);
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            si.setDateCreated(ut.getDateCreated());
            si.setDateUpdated(ut.getDateCreated());
            si.setUserId(ut.getLoginId());
            si.setIpCreated(ut.getIpAddr());
            si.setIpUpdated(ut.getIpAddr());
            rc = this.client.insertRow(si, true);
            invoice.setInvoiceId(rc);
            return rc;
        } catch (Exception e) {
            throw new SalesInvoiceDaoException(e);
        }
    }

}
