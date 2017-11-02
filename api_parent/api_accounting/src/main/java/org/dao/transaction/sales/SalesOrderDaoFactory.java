package org.dao.transaction.sales;

import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.SalesInvoice;
import org.dao.mapping.orm.rmt2.SalesOrder;
import org.dao.mapping.orm.rmt2.SalesOrderItems;
import org.dao.mapping.orm.rmt2.SalesOrderStatus;
import org.dao.mapping.orm.rmt2.SalesOrderStatusHist;
import org.dao.mapping.orm.rmt2.VwSalesOrderInvoice;
import org.dao.mapping.orm.rmt2.VwSalesorderItemsBySalesorder;
import org.dto.SalesInvoiceDto;
import org.dto.SalesOrderDto;
import org.dto.SalesOrderItemDto;
import org.dto.SalesOrderStatusDto;
import org.dto.SalesOrderStatusHistDto;
import org.dto.adapter.orm.transaction.sales.Rmt2SalesOrderDtoFactory;

import com.RMT2Base;
import com.api.persistence.DaoClient;

/**
 * Factory class for creating Sales Order Transaction DAO related objects.
 * 
 * @author Roy Terrell
 * 
 */
public class SalesOrderDaoFactory extends RMT2Base {

    /**
     * Default constructory
     */
    public SalesOrderDaoFactory() {
        return;
    }

    /**
     * Creates an instance of <i>SalesOrderDao</i> using the RMT2 transaction
     * ORM implementation.
     * 
     * @return an instance of {@link SalesOrderDao}
     */
    public SalesOrderDao createRmt2OrmDao() {
        SalesOrderDao dao = new Rmt2SalesOrderDaoImpl();
        return dao;
    }

    /**
     * Creates an instance of <i>SalesOrderDao</i> using the RMT2 ORM basic
     * maintenance implementation.
     * 
     * @param appName
     *            application name
     * 
     * @return an instance of {@link SalesOrderDao}
     */
    public SalesOrderDao createRmt2OrmDao(String appName) {
        SalesOrderDao d = new Rmt2SalesOrderDaoImpl(appName);
        return d;
    }

    /**
     * Creates an instance of <i>SalesOrderDao</i> using the RMT2 ORM basic
     * maintenance implementation.
     * 
     * @param client
     *            an instnace of {@link PersistenceClient}
     * 
     * @return an instance of {@link SalesOrderDao}
     */
    public SalesOrderDao createRmt2OrmDao(DaoClient dao) {
        SalesOrderDao d = new Rmt2SalesOrderDaoImpl(dao.getClient());
        d.setDaoUser(dao.getDaoUser());
        return d;
    }

    /**
     * Creates and returns an <i>SalesOrder</i> object containing selection
     * criteria obtained from an instance of <i>SalesOrderDto</i>.
     * 
     * @param criteria
     *            an instance of {@link SalesOrderDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>soId</li>
     *            <li>customerId</li>
     *            <li>invoiced</li>
     *            <li>orderTotal</li>
     *            </ul>
     * @return an instance of {@link SalesOrder}
     */
    public static final SalesOrder createCriteriaSalesOrder(
            SalesOrderDto criteria) {
        SalesOrder obj = new SalesOrder();
        if (criteria != null) {
            if (criteria.getSalesOrderId() > 0) {
                obj.addCriteria(SalesOrder.PROP_SOID,
                        criteria.getSalesOrderId());
            }
            if (criteria.getCustomerId() > 0) {
                obj.addCriteria(SalesOrder.PROP_CUSTOMERID,
                        criteria.getCustomerId());
            }
            if (criteria.isInvoiced() != null) {
                obj.addCriteria(SalesOrder.PROP_INVOICED,
                        criteria.isInvoiced() ? 1 : 0);
            }
            if (criteria.getOrderTotal() > 0) {
                obj.addCriteria(SalesOrder.PROP_ORDERTOTAL,
                        criteria.getOrderTotal());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>SalesInvoice</i> object containing selection
     * criteria obtained from an instance of <i>SalesInvoiceDto</i>.
     * 
     * @param criteria
     *            an instance of {@link SalesInvoiceDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>invoiceId</li>
     *            <li>salesOrderId</li>
     *            <li>xactId</li>
     *            <li>invoiceNo</li>
     *            </ul>
     * @return an instance of {@link SalesInvoice}
     */
    public static final SalesInvoice createCriteriaSalesInvoice(
            SalesInvoiceDto criteria) {
        SalesInvoice obj = new SalesInvoice();
        if (criteria != null) {
            if (criteria.getInvoiceId() > 0) {
                obj.addCriteria(SalesInvoice.PROP_INVOICEID,
                        criteria.getInvoiceId());
            }
            if (criteria.getSalesOrderId() > 0) {
                obj.addCriteria(SalesInvoice.PROP_SOID,
                        criteria.getSalesOrderId());
            }
            if (criteria.getXactId() > 0) {
                obj.addCriteria(SalesInvoice.PROP_XACTID, criteria.getXactId());
            }
            if (criteria.getInvoiceNo() != null) {
                obj.addLikeClause(SalesInvoice.PROP_INVOICENO,
                        criteria.getInvoiceNo());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>VwSalesOrderInvoice</i> object containing
     * selection criteria obtained from an instance of <i>SalesInvoiceDto</i>.
     * 
     * @param criteria
     *            an instance of {@link SalesInvoiceDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>invoiceId</li>
     *            <li>salesOrderId</li>
     *            <li>xactId</li>
     *            <li>invoiceNo</li>
     *            </ul>
     * @return an instance of {@link VwSalesOrderInvoice}
     */
    public static final VwSalesOrderInvoice createCriteriaSalesInvoiceExt(
            SalesInvoiceDto criteria) {
        VwSalesOrderInvoice obj = new VwSalesOrderInvoice();
        if (criteria != null) {
            if (criteria.getInvoiceId() > 0) {
                obj.addCriteria(VwSalesOrderInvoice.PROP_INVOICEID,
                        criteria.getInvoiceId());
            }
            if (criteria.getSalesOrderId() > 0) {
                obj.addCriteria(VwSalesOrderInvoice.PROP_SALESORDERID,
                        criteria.getSalesOrderId());
            }
            if (criteria.getXactId() > 0) {
                obj.addCriteria(VwSalesOrderInvoice.PROP_XACTID,
                        criteria.getXactId());
            }
            if (criteria.getInvoiceNo() != null) {
                obj.addLikeClause(VwSalesOrderInvoice.PROP_INVOICENO,
                        criteria.getInvoiceNo());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>VwSalesOrderInvoice</i> object containing
     * selection criteria obtained from an instance of <i>SalesInvoiceDto</i>.
     * 
     * @param criteria
     *            an instance of {@link SalesInvoiceDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>invoiceId</li>
     *            <li>customerId</li>
     *            <li>salesOrderId</li>
     *            <li>xactId</li>
     *            <li>invoiceNo</li>
     *            <li>accountNo</li>
     *            <li>invoiced</li>
     *            <li>soStatusId</li>
     *            <li>orderTotal</li>
     *            <li>salesOrderDate</li>
     *            <li>invoiceDate</li>
     *            </ul>
     * @return an instance of {@link SalesOrder}
     */
    public static final VwSalesOrderInvoice createCriteriaExtSalesOrder(
            SalesInvoiceDto criteria) {
        VwSalesOrderInvoice obj = new VwSalesOrderInvoice();
        if (criteria != null) {
            if (criteria.getInvoiceId() > 0) {
                obj.addCriteria(VwSalesOrderInvoice.PROP_INVOICEID,
                        criteria.getInvoiceId());
            }
            if (criteria.getCustomerId() > 0) {
                obj.addCriteria(VwSalesOrderInvoice.PROP_CUSTOMERID,
                        criteria.getCustomerId());
            }
            if (criteria.getSalesOrderId() > 0) {
                obj.addCriteria(VwSalesOrderInvoice.PROP_SALESORDERID,
                        criteria.getSalesOrderId());
            }
            if (criteria.getXactId() > 0) {
                obj.addCriteria(VwSalesOrderInvoice.PROP_XACTID,
                        criteria.getXactId());
            }
            if (criteria.getInvoiceNo() != null) {
                obj.addLikeClause(VwSalesOrderInvoice.PROP_INVOICENO,
                        criteria.getInvoiceNo());
            }
            if (criteria.getAccountNo() != null) {
                obj.addLikeClause(VwSalesOrderInvoice.PROP_ACCOUNTNO,
                        criteria.getAccountNo());
            }
            if (criteria.isInvoiced() != null) {
                obj.addCriteria(SalesOrder.PROP_INVOICED,
                        criteria.isInvoiced() ? 1 : 0);
            }
            if (criteria.getSoStatusId() > 0) {
                obj.addCriteria(VwSalesOrderInvoice.PROP_ORDERSTATUSID,
                        criteria.getCustomerId());
            }
            if (criteria.getOrderTotal() > 0) {
                obj.addCriteria(VwSalesOrderInvoice.PROP_ORDERTOTAL,
                        criteria.getOrderTotal());
            }
            if (criteria.getSaleOrderDate() != null) {
                obj.addCriteria(VwSalesOrderInvoice.PROP_SALESORDERDATE,
                        criteria.getSaleOrderDate());
            }
            if (criteria.getInvoiceDate() != null) {
                obj.addCriteria(VwSalesOrderInvoice.PROP_INVOICEDATE,
                        criteria.getInvoiceDate());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>SalesOrderStatus</i> object containing
     * selection criteria obtained from an instance of
     * <i>SalesOrderStatusDto</i>.
     * 
     * @param criteria
     *            an instance of {@link SalesOrderStatusDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>soStatusId</li>
     *            <li>description</li>
     *            </ul>
     * @return an instance of {@link SalesOrder}
     */
    public static final SalesOrderStatus createCriteriaSalesOrderStatus(
            SalesOrderStatusDto criteria) {
        SalesOrderStatus obj = new SalesOrderStatus();
        if (criteria != null) {
            if (criteria.getSoStatusId() > 0) {
                obj.addCriteria(SalesOrderStatus.PROP_SOSTATUSID,
                        criteria.getSoStatusId());
            }
            if (criteria.getSoStatusDescription() != null) {
                obj.addLikeClause(SalesOrderStatus.PROP_DESCRIPTION,
                        criteria.getSoStatusDescription());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>SalesOrderStatusHist</i> object containing
     * selection criteria obtained from an instance of
     * <i>SalesOrderStatusHistDto</i>.
     * 
     * @param criteria
     *            an instance of {@link SalesOrderStatusHistDto} which the
     *            following properties are recognized:
     *            <ul>
     *            <li>soStatusHistId</li>
     *            <li>soId</li>
     *            </ul>
     * @return an instance of {@link SalesOrder}
     */
    public static final SalesOrderStatusHist createCriteriaSalesOrderStatusHist(
            SalesOrderStatusHistDto criteria) {
        SalesOrderStatusHist obj = new SalesOrderStatusHist();
        if (criteria != null) {
            if (criteria.getSoStatusHistId() > 0) {
                obj.addCriteria(SalesOrderStatusHist.PROP_SOSTATUSHISTID,
                        criteria.getSoStatusHistId());
            }
            if (criteria.getSoId() > 0) {
                obj.addCriteria(SalesOrderStatusHist.PROP_SOID,
                        criteria.getSoId());
            }
        }
        return obj;
    }

    /**
     * Creates a list of sales order DTO's from a list of ORM sales order
     * objects.
     * 
     * @param items
     *            List of {@link SalesOrder} objects
     * @return List of {@link SalesOrderDto} objects or null if items is null or
     *         empty
     */
    public static final List<SalesOrderDto> createSalesOrder(
            List<SalesOrder> items) {
        if (items == null || items.isEmpty()) {
            return null;
        }
        List<SalesOrderDto> list = new ArrayList<SalesOrderDto>();
        for (SalesOrder item : items) {
            SalesOrderDto dto = Rmt2SalesOrderDtoFactory.createSalesOrderInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Creates a <i>SalesOrder</i> instance from a <i>SalesOrderDto</i> object.
     * 
     * @param item
     *            an instance of {@link SalesOrderDto}
     * @return An instance of {@link SalesOrder} or null if item is null.
     */
    public static final SalesOrder createOrmSalesOrder(SalesOrderDto item) {
        if (item == null) {
            return null;
        }
        SalesOrder obj = new SalesOrder();
        obj.setSoId(item.getSalesOrderId());
        obj.setCustomerId(item.getCustomerId());
        obj.setInvoiced((item.isInvoiced() == null || !item.isInvoiced()) ? 0
                : 1);
        obj.setEffectiveDate(item.getSaleOrderDate());
        obj.setOrderTotal(item.getOrderTotal());
        obj.setDateCreated(item.getDateCreated());
        obj.setDateUpdated(item.getDateUpdated());
        obj.setUserId(item.getUpdateUserId());
        obj.setIpCreated(item.getIpCreated());
        obj.setIpUpdated(item.getIpUpdated());
        return obj;
    }

    /**
     * Creates a <i>SalesInvoice</i> instance from a <i>SalesInvoiceDto</i>
     * object.
     * 
     * @param item
     *            an instance of {@link SalesInvoiceDto}
     * @return An instance of {@link SalesInvoice} or null if item is null.
     */
    public static final SalesInvoice createOrmSalesInvoice(SalesInvoiceDto item) {
        if (item == null) {
            return null;
        }
        SalesInvoice obj = new SalesInvoice();
        obj.setInvoiceId(item.getInvoiceId());
        obj.setSoId(item.getSalesOrderId());
        obj.setXactId(item.getXactId());
        obj.setInvoiceNo(item.getInvoiceNo());
        obj.setDateCreated(item.getDateCreated());
        obj.setDateUpdated(item.getDateUpdated());
        obj.setUserId(item.getUpdateUserId());
        obj.setIpCreated(item.getIpCreated());
        obj.setIpUpdated(item.getIpUpdated());
        return obj;
    }

    /**
     * Creates a list of sales order invoice DTO's from a list of ORM sales
     * order invoice objects.
     * 
     * @param items
     *            List of {@link SalesInvoice} objects
     * @return List of {@link SalesInvoiceDto} objects or null if items is null
     *         or empty
     */
    public static final List<SalesInvoiceDto> createSalesInvoice(
            List<SalesInvoice> items) {
        if (items == null || items.isEmpty()) {
            return null;
        }
        List<SalesInvoiceDto> list = new ArrayList<SalesInvoiceDto>();
        for (SalesInvoice item : items) {
            SalesInvoiceDto dto = Rmt2SalesOrderDtoFactory
                    .createSalesIvoiceInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Creates a list of sales order invoice DTO's from a list of ORM sales
     * order invoice view objects.
     * 
     * @param items
     *            List of {@link VwSalesOrderInvoice} objects
     * @return List of {@link SalesInvoiceDto} objects or null if items is null
     *         or empty
     */
    public static final List<SalesInvoiceDto> createExtSalesInvoice(
            List<VwSalesOrderInvoice> items) {
        if (items == null || items.isEmpty()) {
            return null;
        }
        List<SalesInvoiceDto> list = new ArrayList<SalesInvoiceDto>();
        for (VwSalesOrderInvoice item : items) {
            SalesInvoiceDto dto = Rmt2SalesOrderDtoFactory
                    .createSalesIvoiceInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Creates a list of sales order item DTO's from a list of ORM sales order
     * item objects.
     * 
     * @param items
     *            List of {@link SalesOrderItems} objects
     * @return List of {@link SalesOrderItemDto} objects or null if items is
     *         null or empty
     */
    public static final List<SalesOrderItemDto> createSalesOrderItem(
            List<SalesOrderItems> items) {
        if (items == null || items.isEmpty()) {
            return null;
        }
        List<SalesOrderItemDto> list = new ArrayList<SalesOrderItemDto>();
        for (SalesOrderItems item : items) {
            SalesOrderItemDto dto = Rmt2SalesOrderDtoFactory
                    .createSalesOrderItemInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Creates a list of sales order item ORM's from a list of sales order item
     * DTO objects.
     * 
     * @param items
     *            List of {@link SalesOrderItemDto} objects
     * @return List of {@link SalesOrderItems} objects or null if items is null
     *         or empty
     */
    public static final List<SalesOrderItems> createOrmSalesOrderItem(
            List<SalesOrderItemDto> items) {
        if (items == null || items.isEmpty()) {
            return null;
        }
        List<SalesOrderItems> list = new ArrayList<SalesOrderItems>();
        for (SalesOrderItemDto item : items) {
            SalesOrderItems obj = SalesOrderDaoFactory
                    .createOrmSalesOrderItem(item);
            if (obj != null) {
                list.add(obj);
            }
        }
        return list;
    }

    /**
     * Creates a sales order item ORM from a sales order item DTO object.
     * 
     * @param item
     *            instance of {@link SalesOrderItemDto}
     * @return {@link SalesOrderItems} object or null if item is null or empty
     */
    public static final SalesOrderItems createOrmSalesOrderItem(
            SalesOrderItemDto item) {
        if (item == null) {
            return null;
        }
        SalesOrderItems obj = new SalesOrderItems();
        obj.setSoItemId(item.getSoItemId());
        obj.setItemId(item.getItemId());
        obj.setSoId(item.getSalesOrderId());
        obj.setItemNameOverride(item.getItemNameOverride());
        obj.setOrderQty(item.getOrderQty());
        obj.setBackOrderQty(item.getBackOrderQty());
        obj.setInitMarkup(item.getInitMarkup());
        obj.setInitUnitCost(item.getInitUnitCost());
        return obj;
    }

    /**
     * Creates a list of sales order item DTO's from a list of ORM extended
     * sales order item objects.
     * 
     * @param items
     *            List of {@link VwSalesorderItemsBySalesorder} objects
     * @return List of {@link SalesOrderItemDto} objects or null if items is
     *         null or empty
     */
    public static final List<SalesOrderItemDto> createExtSalesOrderItem(
            List<VwSalesorderItemsBySalesorder> items) {
        if (items == null || items.isEmpty()) {
            return null;
        }
        List<SalesOrderItemDto> list = new ArrayList<SalesOrderItemDto>();
        for (VwSalesorderItemsBySalesorder item : items) {
            SalesOrderItemDto dto = Rmt2SalesOrderDtoFactory
                    .createSalesOrderItemInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Creates a list of sales order status DTO's from a list of ORM sales order
     * status objects.
     * 
     * @param items
     *            List of {@link SalesOrderStatus} objects
     * @return List of {@link SalesOrderStatusDto} objects or null if items is
     *         null or empty
     */
    public static final List<SalesOrderStatusDto> createSalesOrderStatus(
            List<SalesOrderStatus> items) {
        if (items == null || items.isEmpty()) {
            return null;
        }
        List<SalesOrderStatusDto> list = new ArrayList<SalesOrderStatusDto>();
        for (SalesOrderStatus item : items) {
            SalesOrderStatusDto dto = Rmt2SalesOrderDtoFactory
                    .createSalesOrderStatusInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Creates a list of sales order status history DTO's from a list of ORM
     * sales order status history objects.
     * 
     * @param items
     *            List of {@link SalesOrderStatusHist} objects
     * @return List of {@link SalesOrderStatusDto} objects or null if items is
     *         null or empty
     */
    public static final List<SalesOrderStatusHistDto> createSalesOrderStatusHist(
            List<SalesOrderStatusHist> items) {
        if (items == null || items.isEmpty()) {
            return null;
        }
        List<SalesOrderStatusHistDto> list = new ArrayList<SalesOrderStatusHistDto>();
        for (SalesOrderStatusHist item : items) {
            SalesOrderStatusHistDto dto = Rmt2SalesOrderDtoFactory
                    .createSalesOrderStatusHistoryInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Creates a sales order status history ORM from a sales order status
     * history DTO object.
     * 
     * @param item
     *            an instance of {@link SalesOrderStatusDto}
     * @return an instance of {@link SalesOrderStatusHist} or null if item is
     *         null.
     */
    public static final SalesOrderStatusHist createOrmSalesOrderStatusHist(
            SalesOrderStatusHistDto item) {
        if (item == null) {
            return null;
        }
        SalesOrderStatusHist obj = new SalesOrderStatusHist();
        obj.setSoStatusHistId(item.getSoStatusHistId());
        obj.setSoId(item.getSoId());
        obj.setSoStatusId(item.getSoStatusId());
        obj.setEffectiveDate(item.getEffectiveDate());
        obj.setEndDate(item.getEndDate());
        obj.setReason(item.getReason());
        obj.setDateCreated(item.getDateCreated());
        obj.setUserId(item.getUpdateUserId());
        obj.setIpCreated(item.getIpCreated());
        obj.setIpUpdated(item.getIpUpdated());
        return obj;
    }
}
