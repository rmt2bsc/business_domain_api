package org.dao.transaction.purchases.vendor;

import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.PurchaseOrder;
import org.dao.mapping.orm.rmt2.PurchaseOrderItems;
import org.dao.mapping.orm.rmt2.PurchaseOrderStatus;
import org.dao.mapping.orm.rmt2.PurchaseOrderStatusHist;
import org.dao.mapping.orm.rmt2.VendorItems;
import org.dao.mapping.orm.rmt2.VwVendorItemPurchaseOrderItem;
import org.dao.mapping.orm.rmt2.VwVendorItems;
import org.dto.PurchaseOrderDto;
import org.dto.PurchaseOrderItemDto;
import org.dto.PurchaseOrderStatusDto;
import org.dto.PurchaseOrderStatusHistDto;
import org.dto.VendorItemDto;
import org.dto.VwVendorItemDto;

import com.RMT2Base;
import com.api.persistence.DaoClient;

/**
 * Factory class for creating Vendor Purchases Transaction DAO related objects.
 * 
 * @author Roy Terrell
 * 
 */
public class VendorPurchasesDaoFactory extends RMT2Base {

    /**
     * Default constructor
     */
    public VendorPurchasesDaoFactory() {
        return;
    }

    /**
     * Creates an instance of <i>VendorPurchasesDao</i> using the RMT2
     * transaction ORM implementation.
     * 
     * @return an instance of {@link VendorPurchasesDao}
     */
    public VendorPurchasesDao createRmt2OrmDao() {
        VendorPurchasesDao dao = new Rmt2VendorPurchasesDaoImpl();
        return dao;
    }

    /**
     * Creates an instance of <i>VendorPurchasesDao</i> using the RMT2
     * transaction ORM implementation.
     * 
     * @param appName
     *            application name
     * @return an instance of {@link VendorPurchasesDao}
     */
    public VendorPurchasesDao createRmt2OrmDao(String appName) {
        VendorPurchasesDao dao = new Rmt2VendorPurchasesDaoImpl(appName);
        return dao;
    }

    /**
     * Creates an instance of <i>VendorPurchasesDao</i> using the RMT2 ORM basic
     * maintenance implementation.
     * 
     * @param client
     *            an instnace of {@link PersistenceClient}
     * 
     * @return an instance of {@link VendorPurchasesDao}
     */
    public VendorPurchasesDao createRmt2OrmDao(DaoClient dao) {
        VendorPurchasesDao d = new Rmt2VendorPurchasesDaoImpl(dao.getClient());
        d.setDaoUser(dao.getDaoUser());
        return d;
    }

    /**
     * Creates and returns an <i>PurchaseOrder</i> object containing selection
     * criteria obtained from an instance of <i>PurchaseOrderDto</i>.
     * 
     * @param criteria
     *            an instance of {@link PurchaseOrderDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>poId</li>
     *            <li>creditorId</li>
     *            <li>xactId</li>
     *            <li>refNo</li>
     *            <li>criteria - include custom selection criteria if available</li>
     *            </ul>
     * @return an instance of {@link PurchaseOrder}
     */
    public static final PurchaseOrder createCriteria(PurchaseOrderDto criteria) {
        PurchaseOrder obj = new PurchaseOrder();
        if (criteria != null) {
            if (criteria.getPoId() > 0) {
                obj.addCriteria(PurchaseOrder.PROP_POID, criteria.getPoId());
            }
            if (criteria.getCreditorId() > 0) {
                obj.addCriteria(PurchaseOrder.PROP_CREDITORID,
                        criteria.getCreditorId());
            }
            if (criteria.getXactId() > 0) {
                obj.addCriteria(PurchaseOrder.PROP_XACTID, criteria.getXactId());
            }
            if (criteria.getRefNo() != null) {
                obj.addLikeClause(PurchaseOrder.PROP_REFNO, criteria.getRefNo());
            }
            if (criteria.getCriteria() != null) {
                obj.addCustomCriteria(criteria.getCriteria());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>PurchaseOrderItems</i> object containing
     * selection criteria obtained from an instance of
     * <i>PurchaseOrderItemDto</i>.
     * 
     * @param criteria
     *            an instance of {@link PurchaseOrderItemDto} which the
     *            following properties are recognized:
     *            <ul>
     *            <li>poId</li>
     *            <li>poItemId</li>
     *            <li>itemId</li>
     *            <li>criteria - include custom selection criteria if available</li>
     *            </ul>
     * @return an instance of {@link PurchaseOrderItems}
     */
    public static final PurchaseOrderItems createCriteria(PurchaseOrderItemDto criteria) {
        PurchaseOrderItems obj = new PurchaseOrderItems();
        if (criteria != null) {
            if (criteria.getPoId() > 0) {
                obj.addCriteria(PurchaseOrderItems.PROP_POID, criteria.getPoId());
            }
            if (criteria.getPoItemId() > 0) {
                obj.addCriteria(PurchaseOrderItems.PROP_POITEMID, criteria.getPoItemId());
            }
            if (criteria.getItemId() > 0) {
                obj.addCriteria(PurchaseOrderItems.PROP_ITEMID, criteria.getItemId());
            }
            if (criteria.getCriteria() != null) {
                obj.addCustomCriteria(criteria.getCriteria());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>VendorItems</i> object containing selection
     * criteria obtained from an instance of <i>VendorItemDto</i>.
     * 
     * @param criteria
     *            an instance of {@link VendorItemDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>vendorId</li>
     *            <li>vendorItemNo</li>
     *            <li>itemSerialNo</li>
     *            <li>criteria - include custom selection criteria if available</li>
     *            </ul>
     * @return an instance of {@link VendorItems}
     */
    public static final VendorItems createCriteria(VendorItemDto criteria) {
        VendorItems obj = new VendorItems();
        if (criteria != null) {
            if (criteria.getVendorId() > 0) {
                obj.addCriteria(VendorItems.PROP_CREDITORID,
                        criteria.getVendorId());
            }
            if (criteria.getVendorItemNo() != null) {
                obj.addLikeClause(VendorItems.PROP_VENDORITEMNO,
                        criteria.getVendorItemNo());
            }
            if (criteria.getVendorItemSerialNo() != null) {
                obj.addCriteria(VendorItems.PROP_ITEMSERIALNO,
                        criteria.getVendorItemSerialNo());
            }
            if (criteria.getCriteria() != null) {
                obj.addCustomCriteria(criteria.getCriteria());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>VwVendorItemPurchaseOrderItem</i> object
     * containing selection criteria obtained from <i>vendorId</i> and
     * <i>poId</i>.
     * 
     * @param vendorId
     * @param poId
     * @return an instance of {@link VwVendorItemPurchaseOrderItem}
     */
    public static final VwVendorItemPurchaseOrderItem createCriteria(
            int vendorId, int poId) {
        VwVendorItemPurchaseOrderItem obj = new VwVendorItemPurchaseOrderItem();
        if (vendorId > 0) {
            obj.addCriteria(VwVendorItemPurchaseOrderItem.PROP_VENDORID,
                    vendorId);
        }
        if (poId > 0) {
            obj.addCriteria(VwVendorItemPurchaseOrderItem.PROP_POID, poId);
        }
        return obj;
    }

    /**
     * Creates and returns an <i>VwVendorItems</i> object containing selection
     * criteria obtained from <i>vendorId</i>.
     * 
     * @param vendorId
     *            the vendor id
     * @return an instance of {@link VwVendorItems}
     */
    public static final VwVendorItems createCriteria(int vendorId) {
        VwVendorItems obj = new VwVendorItems();
        if (vendorId > 0) {
            obj.addCriteria(VwVendorItems.PROP_CREDITORID, vendorId);
        }
        return obj;
    }

    /**
     * Creates and returns an <i>VwVendorItems</i> object containing selection
     * criteria obtained from an instance of <i>VwVendorItemDto</i>.
     * 
     * @param criteria
     *            an instance of {@link VwVendorItemDto} which the following
     *            properties are recognized:
     *            <ul>
     *            <li>itemId</li>
     *            <li>creditorId</li>
     *            <li>vendorItemNo</li>
     *            <li>itemSerialNo</li>
     *            <li>itemName</li>
     *            <li>criteria - include custom selection criteria if available</li>
     *            </ul>
     * @return an instance of {@link VwVendorItems}
     */
    public static final VwVendorItems createCriteria(VwVendorItemDto criteria) {
        VwVendorItems obj = new VwVendorItems();
        if (criteria != null) {
            if (criteria.getItemId() > 0) {
                obj.addCriteria(VwVendorItems.PROP_ITEMID, criteria.getItemId());
            }
            if (criteria.getVendorId() > 0) {
                obj.addCriteria(VwVendorItems.PROP_CREDITORID,
                        criteria.getVendorId());
            }
            if (criteria.getVendorItemNo() != null) {
                obj.addLikeClause(VwVendorItems.PROP_VENDORITEMNO,
                        criteria.getVendorItemNo());
            }
            if (criteria.getItemSerialNo() != null) {
                obj.addCriteria(VwVendorItems.PROP_ITEMSERIALNO,
                        criteria.getItemSerialNo());
            }
            if (criteria.getItemName() != null) {
                obj.addLikeClause(VwVendorItems.PROP_DESCRIPTION,
                        criteria.getItemName());
            }
            if (criteria.getCriteria() != null) {
                obj.addCustomCriteria(criteria.getCriteria());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>PurchaseOrderStatus</i> object containing
     * selection criteria obtained from an instance of
     * <i>PurchaseOrderStatusDto</i>.
     * 
     * @param criteria
     *            an instance of {@link PurchaseOrderStatusDto} which the
     *            following properties are recognized:
     *            <ul>
     *            <li>poStatusId</li>
     *            <li>poStatusDescription</li>
     *            <li>criteria - include custom selection criteria if available</li>
     *            </ul>
     * @return an instance of {@link PurchaseOrderStatus}
     */
    public static final PurchaseOrderStatus createCriteria(
            PurchaseOrderStatusDto criteria) {
        PurchaseOrderStatus obj = new PurchaseOrderStatus();
        if (criteria != null) {
            if (criteria.getPoStatusId() > 0) {
                obj.addCriteria(PurchaseOrderStatus.PROP_POSTATUSID,
                        criteria.getPoStatusId());
            }
            if (criteria.getPoStatusDescription() != null) {
                obj.addLikeClause(PurchaseOrderStatus.PROP_DESCRIPTION,
                        criteria.getPoStatusDescription());
            }
            if (criteria.getCriteria() != null) {
                obj.addCustomCriteria(criteria.getCriteria());
            }
        }
        return obj;
    }

    /**
     * Creates and returns an <i>PurchaseOrderStatusHist</i> object containing
     * selection criteria obtained from an instance of
     * <i>PurchaseOrderStatusHistDto</i>.
     * 
     * @param criteria
     *            an instance of {@link PurchaseOrderStatusHistDto} which the
     *            following properties are recognized:
     *            <ul>
     *            <li>poStatusHistId</li>
     *            <li>poStatusId</li>
     *            <li>poId</li>
     *            <li>criteria - include custom selection criteria if available</li>
     *            </ul>
     * @return an instance of {@link PurchaseOrderStatusHist}
     */
    public static final PurchaseOrderStatusHist createCriteria(
            PurchaseOrderStatusHistDto criteria) {
        PurchaseOrderStatusHist obj = new PurchaseOrderStatusHist();
        if (criteria != null) {
            if (criteria.getPoStatusHistId() > 0) {
                obj.addCriteria(PurchaseOrderStatusHist.PROP_POSTATUSHISTID,
                        criteria.getPoStatusHistId());
            }
            if (criteria.getPoStatusId() > 0) {
                obj.addCriteria(PurchaseOrderStatusHist.PROP_POSTATUSID,
                        criteria.getPoStatusId());
            }
            if (criteria.getPoId() > 0) {
                obj.addCriteria(PurchaseOrderStatusHist.PROP_POID,
                        criteria.getPoId());
            }
            if (criteria.getCriteria() != null) {
                obj.addCustomCriteria(criteria.getCriteria());
            }
        }
        return obj;
    }

    /**
     * Create a purchase order status history ORM bean with purchase order id
     * and status id.
     * 
     * @param poId
     *            The purchase order id
     * @param statusId
     *            The purchase order status id
     * @return {@link PurchaseOrderStatusHist}
     */
    public static PurchaseOrderStatusHist createPurchaseOrderStatusHist(
            int poId, int statusId) {
        PurchaseOrderStatusHist obj = new PurchaseOrderStatusHist();
        obj.setPoId(poId);
        obj.setPoStatusId(statusId);
        return obj;
    }

    /**
     * Create a purchase order status history ORM bean from a purchase order
     * status history DTO.
     * 
     * @param dto
     *            an instance of {@link PurchaseOrderStatusHistDto}
     * @return {@link PurchaseOrderStatusHist}
     */
    public static PurchaseOrderStatusHist createPurchaseOrderStatusHist(
            PurchaseOrderStatusHistDto dto) {
        PurchaseOrderStatusHist obj = new PurchaseOrderStatusHist();
        obj.setPoStatusHistId(dto.getPoStatusHistId());
        obj.setPoId(dto.getPoId());
        obj.setPoStatusId(dto.getPoStatusId());
        obj.setEffectiveDate(dto.getEffectiveDate());
        obj.setEndDate(dto.getEndDate());
        obj.setUserId(dto.getUpdateUserId());
        return obj;
    }

    /**
     * Create a purchase order ORM bean from a purchase order DTO.
     * 
     * @param dto
     *            an instance of {@link PurchaseOrderDto}
     * @return {@link PurchaseOrder}
     */
    public static PurchaseOrder createPurchaseOrderOrm(PurchaseOrderDto dto) {
        PurchaseOrder obj = new PurchaseOrder();
        obj.setPoId(dto.getPoId());
        obj.setXactId(dto.getXactId());
        obj.setCreditorId(dto.getCreditorId());
        obj.setRefNo(dto.getRefNo());
        obj.setTotal(dto.getTotal());
        obj.setDateCreated(dto.getDateCreated());
        obj.setDateUpdated(dto.getDateUpdated());
        obj.setUserId(dto.getUpdateUserId());
        return obj;
    }

    /**
     * Create a purchase order item ORM bean from a purchase order item DTO.
     * 
     * @param dto
     *            an instance of {@link PurchaseOrderItemDto}
     * @return {@link PurchaseOrderItems} or null if <i>dto</i> is null.
     */
    public static PurchaseOrderItems createPurchaseOrderItemOrm(
            PurchaseOrderItemDto dto) {
        if (dto == null) {
            return null;
        }
        PurchaseOrderItems obj = new PurchaseOrderItems();
        obj.setPoItemId(dto.getPoItemId());
        obj.setPoId(dto.getPoId());
        obj.setItemId(dto.getItemId());
        obj.setUnitCost(dto.getActualUnitCost());
        obj.setQty(dto.getQtyOrdered());
        obj.setQtyRcvd(dto.getQtyRcvd());
        obj.setQtyRtn(dto.getQtyRtn());
        obj.setDateCreated(dto.getDateCreated());
        obj.setDateUpdated(dto.getDateUpdated());
        obj.setUserId(dto.getUpdateUserId());
        return obj;
    }

    /**
     * Create a List of purchase order item ORM beans from a purchase order item
     * DTO List.
     * 
     * @param dto
     *            a List of {@link PurchaseOrderItemDto}
     * @return List of {@link PurchaseOrderItems} or null if <i>dto</i> is null.
     */
    public static List<PurchaseOrderItems> createPurchaseOrderItemOrm(
            List<PurchaseOrderItemDto> dto) {
        List<PurchaseOrderItems> list = new ArrayList<PurchaseOrderItems>();
        if (dto == null) {
            return null;
        }
        for (PurchaseOrderItemDto item : dto) {
            list.add(VendorPurchasesDaoFactory.createPurchaseOrderItemOrm(item));
        }
        return list;
    }
}
