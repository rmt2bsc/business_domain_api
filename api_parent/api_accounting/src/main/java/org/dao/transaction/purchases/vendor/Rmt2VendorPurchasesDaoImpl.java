package org.dao.transaction.purchases.vendor;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dao.mapping.orm.rmt2.PurchaseOrder;
import org.dao.mapping.orm.rmt2.PurchaseOrderItems;
import org.dao.mapping.orm.rmt2.PurchaseOrderStatus;
import org.dao.mapping.orm.rmt2.PurchaseOrderStatusHist;
import org.dao.mapping.orm.rmt2.VendorItems;
import org.dao.mapping.orm.rmt2.VwVendorItemPurchaseOrderItem;
import org.dao.mapping.orm.rmt2.VwVendorItems;
import org.dao.transaction.Rmt2XactDaoImpl;
import org.dto.PurchaseOrderDto;
import org.dto.PurchaseOrderItemDto;
import org.dto.PurchaseOrderStatusDto;
import org.dto.PurchaseOrderStatusHistDto;
import org.dto.VendorItemDto;
import org.dto.VwVendorItemDto;
import org.dto.adapter.orm.transaction.purchaseorder.Rmt2PurchaseOrderDtoFactory;

import com.api.persistence.PersistenceClient;
import com.util.RMT2Date;
import com.util.RMT2String;
import com.util.UserTimestamp;

/**
 * An implementation of {@link VendorPurchasesDao}. It provides functionality
 * that creates, updates, deletes, and queries vendor purchases related data.
 * 
 * @author Roy Terrell
 * 
 */
class Rmt2VendorPurchasesDaoImpl extends Rmt2XactDaoImpl implements  VendorPurchasesDao {

    private static Logger logger = Logger.getLogger(Rmt2VendorPurchasesDaoImpl.class);

    /**
     * Creates a Rmt2VendorPurchasesDaoImpl object with its own persistent
     * client.
     */
    public Rmt2VendorPurchasesDaoImpl() {
        super();
    }

    public Rmt2VendorPurchasesDaoImpl(String appName) {
        super(appName);
    }

    /**
     * Creates a Rmt2VendorPurchasesDaoImpl object with a shared persistent
     * client.
     * 
     * @param client
     */
    public Rmt2VendorPurchasesDaoImpl(PersistenceClient client) {
        super(client);
    }

    /**
     * Fetch purchase orders from the <i>purchase_order</i> table.
     * <p>
     * Selection criteria is constructed from the available properties contained
     * in <i>criteria</i>.
     * 
     * @param criteria
     *            An instance of {@link PurchaseOrderDto} used to filter the
     *            result set
     * @return List of {@link PurchaseOrderDto} or null when no data is found.
     * @throws VendorPurchasesDaoException
     */
    @Override
    public List<PurchaseOrderDto> fetchPurchaseOrder(PurchaseOrderDto criteria) throws VendorPurchasesDaoException {

        PurchaseOrder obj = VendorPurchasesDaoFactory.createCriteria(criteria);
        obj.addOrderBy(PurchaseOrder.PROP_POID, PurchaseOrder.ORDERBY_ASCENDING);

        List<PurchaseOrder> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new VendorPurchasesDaoException(e);
        }

        List<PurchaseOrderDto> list = new ArrayList<PurchaseOrderDto>();
        for (PurchaseOrder item : results) {
            PurchaseOrderDto dto = Rmt2PurchaseOrderDtoFactory.createPurchaseOrderInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Fetch purchase order items from the <i>purchase_order_items</i> table.
     * <p>
     * Selection criteria is constructed from the available properties contained
     * in <i>criteria</i>.
     * 
     * @param criteria
     *            An instance of {@link PurchaseOrderItemDto} used to filter the
     *            result set regarding purchase order item information.
     * @return List of {@link PurchaseOrderItemDto} or null when no data is
     *         found.
     * @throws VendorPurchasesDaoException
     */
    @Override
    public List<PurchaseOrderItemDto> fetchPurchaseOrderItem(PurchaseOrderItemDto criteria) 
            throws VendorPurchasesDaoException {
        PurchaseOrderItems obj = VendorPurchasesDaoFactory.createCriteria(criteria);
        obj.addOrderBy(PurchaseOrderItems.PROP_POITEMID, PurchaseOrderItems.ORDERBY_ASCENDING);

        List<PurchaseOrderItems> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null || results.isEmpty()) {
                return null;
            }
        } catch (Exception e) {
            throw new VendorPurchasesDaoException(e);
        }

        List<PurchaseOrderItemDto> list = new ArrayList<PurchaseOrderItemDto>();
        for (PurchaseOrderItems item : results) {
            PurchaseOrderItemDto dto = Rmt2PurchaseOrderDtoFactory.createPurchaseOrderItemInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Fetches vendor specfic inventory item data from the <i>vendor_items</i>
     * table.
     * 
     * @param criteria
     *            an instance of {@link VendorItemDto}
     * @return List of {@link VendorItemDto} or null when no data is found.
     * @throws VendorPurchasesDaoException
     */
    @Override
    public List<VendorItemDto> fetchVendorItem(VendorItemDto criteria) throws VendorPurchasesDaoException {
        VendorItems obj = VendorPurchasesDaoFactory.createCriteria(criteria);
        obj.addOrderBy(VendorItems.PROP_CREDITORID, VendorItems.ORDERBY_ASCENDING);
        obj.addOrderBy(VendorItems.PROP_VENDORITEMNO, VendorItems.ORDERBY_ASCENDING);

        List<VendorItems> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new VendorPurchasesDaoException(e);
        }

        List<VendorItemDto> list = new ArrayList<VendorItemDto>();
        for (VendorItems item : results) {
            VendorItemDto dto = Rmt2PurchaseOrderDtoFactory.createVendorItemInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Fetches the item master version and the external vendor item
     * version of an inventory item using vendor id and item id from the
     * <i>vw_vendor_items</> view.
     * <p>
     * This can be used to fetch external items specific to a particular vendor.
     * The query is filterd by data contained in <i>criteria</i> object.
     * 
     * @param criteria
     *            an instance of {@link VwVendorItemDto}
     * @return List of {@link VwVendorItemDto} or null when no data is found.
     * @throws VendorPurchasesDaoException
     */
    @Override
    public List<VwVendorItemDto> fetchInternalExternalItemData(VwVendorItemDto criteria) throws VendorPurchasesDaoException {
        VwVendorItems obj = VendorPurchasesDaoFactory.createCriteria(criteria);
        obj.addOrderBy(VwVendorItems.PROP_DESCRIPTION, VwVendorItems.ORDERBY_ASCENDING);
        obj.addOrderBy(VwVendorItems.PROP_CREDITORID, VwVendorItems.ORDERBY_ASCENDING);
        obj.addOrderBy(VwVendorItems.PROP_VENDORITEMNO, VwVendorItems.ORDERBY_ASCENDING);

        List<VwVendorItems> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new VendorPurchasesDaoException(e);
        }

        List<VwVendorItemDto> list = new ArrayList<VwVendorItemDto>();
        for (VwVendorItems item : results) {
            VwVendorItemDto dto = Rmt2PurchaseOrderDtoFactory.createVendorItemExtInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Fetches all purchase order items pertaining to a given vendor.
     * <p>
     * The query involves the combination of purchase order items and vendor
     * items data from the <i>Vw_Vendor_Item_Purchase_Order_Item</i> view.
     * 
     * @param vendorId
     *            The vendor id
     * @param poId
     *            The purchase order id
     * @return A List of {@link PurchaseOrderItemDto} or null when no data is
     *         found.
     * @throws VendorPurchasesDaoException
     */
    @Override
    public List<PurchaseOrderItemDto> fetchCombinedVendorPurchaseOrderItem(int vendorId, int poId) 
            throws VendorPurchasesDaoException {
        VwVendorItemPurchaseOrderItem obj = VendorPurchasesDaoFactory.createCriteria(vendorId, poId);
        obj.addOrderBy(VendorItems.PROP_CREDITORID, VendorItems.ORDERBY_ASCENDING);
        obj.addOrderBy(VendorItems.PROP_VENDORITEMNO, VendorItems.ORDERBY_ASCENDING);

        List<VwVendorItemPurchaseOrderItem> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new VendorPurchasesDaoException(e);
        }

        List<PurchaseOrderItemDto> list = new ArrayList<PurchaseOrderItemDto>();
        for (VwVendorItemPurchaseOrderItem item : results) {
            PurchaseOrderItemDto dto = Rmt2PurchaseOrderDtoFactory.createPurchaseOrderExternalItemInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Fetches vendor/purchase order items that are not assoicated with a given
     * purchase order from <i>vw_vendor_items</i> view.
     * 
     * @param vendorId
     *            the vendor id
     * @param poId
     *            the purchase order id
     * @return List of {@link VendorItemDto} or null when no data is found.
     * @throws VendorPurchasesDaoException
     */
    @Override
    public List<VwVendorItemDto> fetchUnassignedPurchaseOrderItems(int vendorId, int poId) 
            throws VendorPurchasesDaoException {

        // Build SQL custom where clause
        String sql = RMT2String.replace(VendorPurchasesConst.SQL_UNASSIGNED_PO_ITEMS, String.valueOf(poId), "?");

        VwVendorItems obj = VendorPurchasesDaoFactory.createCriteria(vendorId);
        obj.addCustomCriteria(sql);
        obj.addOrderBy(VwVendorItems.PROP_CREDITORID, VendorItems.ORDERBY_ASCENDING);
        obj.addOrderBy(VwVendorItems.PROP_DESCRIPTION, VendorItems.ORDERBY_ASCENDING);
        obj.addOrderBy(VwVendorItems.PROP_VENDORITEMNO, VendorItems.ORDERBY_ASCENDING);
        List<VwVendorItems> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new VendorPurchasesDaoException(e);
        }

        List<VwVendorItemDto> list = new ArrayList<VwVendorItemDto>();
        for (VwVendorItems item : results) {
            VwVendorItemDto dto = Rmt2PurchaseOrderDtoFactory.createVendorItemExtInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Fetch a list of purchase order statuses from the
     * <i>purchase_order_status</i> table based on given selection criteria.
     * 
     * @param criteria
     *            an instance of {@link PurchaseOrderStatusDto}
     * @return A List of {@link PurchaseOrderStatusDto} or null when no data is
     *         found.
     * @throws VendorPurchasesDaoException
     */
    @Override
    public List<PurchaseOrderStatusDto> fetchStatus(PurchaseOrderStatusDto criteria) throws VendorPurchasesDaoException {
        PurchaseOrderStatus obj = VendorPurchasesDaoFactory.createCriteria(criteria);
        obj.addOrderBy(PurchaseOrderStatus.PROP_DESCRIPTION, PurchaseOrderStatus.ORDERBY_ASCENDING);

        List<PurchaseOrderStatus> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new VendorPurchasesDaoException(e);
        }

        List<PurchaseOrderStatusDto> list = new ArrayList<PurchaseOrderStatusDto>();
        for (PurchaseOrderStatus item : results) {
            PurchaseOrderStatusDto dto = Rmt2PurchaseOrderDtoFactory.createPurchaseOrderStatusInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Fetches the status history of a given purchase order from the
     * <i>purchase_order_status_hist</i> table.
     * <p>
     * This implementation is capable of fetching all statuses, a single status,
     * or the current status of a purchase order. The purchase order id in
     * <i>criteria</i> is required in order to single out a specific purchase
     * order.
     * 
     * @param criteria
     *            An instance of {@link PurchaseOrderStatusHistDto} to be used
     *            as a query filter.
     * @param currentStatusOnly
     *            Set to true when fetching the current purchase order status
     *            only. Otherwise, set to false.
     * @return A List of {@link PurchaseOrderStatusHistDto} objects or null if
     *         no data is found
     * @throws VendorPurchasesDaoException
     */
    @Override
    public List<PurchaseOrderStatusHistDto> fetchPurchaseOrderHistory(PurchaseOrderStatusHistDto criteria, 
            boolean currentStatusOnly) throws VendorPurchasesDaoException {
        List<PurchaseOrderStatusHist> results = this.fetchPurchaseOrderHistoryOrm(criteria, currentStatusOnly);
        List<PurchaseOrderStatusHistDto> list = new ArrayList<PurchaseOrderStatusHistDto>();
        for (PurchaseOrderStatusHist item : results) {
            PurchaseOrderStatusHistDto dto = Rmt2PurchaseOrderDtoFactory.createPurchaseOrderStatusHistoryInstance(item);
            list.add(dto);
        }
        return list;
    }

    private List<PurchaseOrderStatusHist> fetchPurchaseOrderHistoryOrm(PurchaseOrderStatusHistDto criteria, 
            boolean currentStatusOnly) throws VendorPurchasesDaoException {
        if (currentStatusOnly) {
            criteria.setCriteria(VendorPurchasesConst.SQL_CURRENT_PO_STATUS);
        }
        PurchaseOrderStatusHist obj = VendorPurchasesDaoFactory.createCriteria(criteria);
        obj.addOrderBy(PurchaseOrderStatusHist.PROP_EFFECTIVEDATE, PurchaseOrderStatus.ORDERBY_DESCENDING);
        obj.addOrderBy(PurchaseOrderStatusHist.PROP_POSTATUSHISTID, PurchaseOrderStatus.ORDERBY_DESCENDING);

        List<PurchaseOrderStatusHist> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (Exception e) {
            throw new VendorPurchasesDaoException(e);
        }
        return results;
    }

    /**
     * Deletes the base purchase order record from the <i>purchase_order</i>
     * table.
     * <p>
     * Purchase order can only be deleted from the system when in Quote status.
     * It is required that <i>criteria</i> contains a purchase order id.
     * Otherwise, and error is thrown.
     * 
     * @param criteria
     *            An instance of {@link PurchaseOrderDto} representing the
     *            criteria used to delete the purchase order.
     * @return the total number of rows effected
     * @throws VendorPurchasesDaoException
     */
    @Override
    public int deletePurchaseOrder(PurchaseOrderDto criteria) throws VendorPurchasesDaoException {
        PurchaseOrder obj = VendorPurchasesDaoFactory.createCriteria(criteria);
        try {
            int results = this.client.deleteRow(obj);
            logger.info(results  + " rows were deleted from the purchase_order table");
            return results;
        } catch (Exception e) {
            throw new VendorPurchasesDaoException(e);
        }
    }

    /**
     * Deletes one or more purchase order line items from the
     * <i>purchase_order_items</i> table.
     * <p>
     * It is required that <i>criteria</i> contains a purchase order id.
     * Otherwise, and error is thrown.
     * 
     * @param criteria
     *            An instance of {@link PurchaseOrderItemDto} representing the
     *            criteria used to delete the purchase order item(s).
     * @return the total number of rows effected
     * @throws VendorPurchasesDaoException
     */
    @Override
    public int deletePurchaseOrderItem(PurchaseOrderItemDto criteria) throws VendorPurchasesDaoException {
        PurchaseOrderItems obj = VendorPurchasesDaoFactory.createCriteria(criteria);
        try {
            int results = this.client.deleteRow(obj);
            logger.info(results + " rows were deleted from the purchase_order_items table");
            return results;
        } catch (Exception e) {
            throw new VendorPurchasesDaoException(e);
        }
    }

    /**
     * Creates a new or modifies an existing purchase order by updating the
     * <i>purchase_order</i> and <i>purchase_order_items</i> tables
     * 
     * @param po
     *            an instance of {@link PurchaseOrderDto} representing the base
     *            purchase order
     * @param items
     *            an instance of {@link PurchaseOrderItemDto} representing list
     *            of purchase order items.
     * @param refresh
     *            set to true when purchase order items are to be deleted prior
     *            to posting purchase order revisions.
     * @return the id of the new purchase order created or the total number of
     *         rows modifed when an existing purchase order is updated.
     * @throws VendorPurchasesDaoException
     */
    @Override
    public int maintainPurchaseOrder(PurchaseOrderDto po) throws VendorPurchasesDaoException {
        PurchaseOrder p = VendorPurchasesDaoFactory.createPurchaseOrderOrm(po);
        int rc = 0;
        if (p.getPoId() <= 0) {
            rc = this.createPurchaseOrder(p);
        }
        else {
            rc = this.updatePurchaseOrder(p);
        }
        return rc;
    }

    /**
     * Creates a new or modifies an existing purchase order item.
     * 
     * @param item
     *            purchase order item.
     * @return the id of the new purchase order item created or the total number
     *         of rows modifed when an existing purchase order item is updated.
     * @throws VendorPurchasesDaoException
     */
    @Override
    public int maintainPurchaseOrderItem(PurchaseOrderItemDto item) throws VendorPurchasesDaoException {
        PurchaseOrderItems i = VendorPurchasesDaoFactory.createPurchaseOrderItemOrm(item);
        int rc = 0;
        if (i.getPoItemId() <= 0) {
            rc = this.createPurchaseOrderItem(i);
        }
        else {
            rc = this.updatePurchaseOrderItem(i);
        }
        return rc;
    }

    /**
     * Adds a purchase order to the databse. A status of "Quote" will be
     * assigned to the new purchase order.
     * 
     * @param po
     *            Base purchase order data.
     * @return The new purchase order id
     * @throws VendorPurchasesException
     */
    private int createPurchaseOrder(PurchaseOrder po) throws VendorPurchasesDaoException {
        int poId = 0;

        // We must be working with a valid po at this point, so add to the
        // database
        UserTimestamp ut = null;
        try {
            // Setup DataSource object to apply database updates.
            ut = RMT2Date.getUserTimeStamp(this.getDaoUser());

            // Apply base purchase order
            po.setNull("status");
            po.setNull("xactId");
            po.setDateCreated(ut.getDateCreated());
            po.setDateUpdated(ut.getDateCreated());
            po.setUserId(ut.getLoginId());
            poId = this.client.insertRow(po, true);
            po.setPoId(poId);
            msg = "purchase order, " + poId + " , was inserted successfully to the database";
            logger.info(msg);
            return poId;
        } catch (Exception e) {
            msg = "Failed to insert base purchase order to database";
            logger.error(msg, e);
            throw new VendorPurchasesDaoException(e);
        }
    }

    /**
     * Updates the base purchase order to the databse. A status of "Quote" will
     * be assigned to the new purchase order.
     * 
     * @param po
     *            Base purchase order data.
     * @return The new purchase order id
     * @throws VendorPurchasesException
     */
    private int updatePurchaseOrder(PurchaseOrder po) throws VendorPurchasesDaoException {
        int rc = 0;

        // We must be working with a valid po at this point, so add to the
        // database
        UserTimestamp ut = null;
        try {
            // Setup DataSource object to apply database updates.
            ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            po.setDateUpdated(ut.getDateCreated());
            po.setUserId(ut.getLoginId());
            po.addCriteria(PurchaseOrder.PROP_POID, po.getPoId());
            rc = this.client.updateRow(po);
            msg = "purchase order, " + po.getPoId() + " , was updated successfully to the database";
            logger.info(msg);
            return rc;
        } catch (Exception e) {
            msg = "Failed to update base purchase order to database";
            logger.error(msg, e);
            throw new VendorPurchasesDaoException(e);
        }
    }

    /**
     * Adds a purchase order item to the databse
     * 
     * @param items
     *            the purchase order item
     * @return The total number of rows effect by transaction.
     * @throws VendorPurchasesDaoException
     */
    private int createPurchaseOrderItem(PurchaseOrderItems item) throws VendorPurchasesDaoException {
        // We must be working with a valid list of PO Items at this point, so
        // add to the database
        UserTimestamp ut = null;
        int rc = 0;
        try {
            ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            // Apply all items belonging to the base purchase order.
            item.setDateCreated(ut.getDateCreated());
            item.setDateUpdated(ut.getDateCreated());
            item.setUserId(ut.getLoginId());
            rc = this.client.insertRow(item, true);
            item.setPoItemId(rc);
            msg = "Purchase order item, "
                    + rc
                    + " , was inserted successfully to the database for PO id, "
                    + item.getPoId();
            logger.info(msg);
        } catch (Exception e) {
            msg = "Failed to insert a purchase order item to database for PO id, " + item.getPoId();
            logger.error(msg, e);
            throw new VendorPurchasesDaoException(e);
        }
        return rc;
    }

    /**
     * Updates an existing purchase order item to the databse
     * 
     * @param item
     *            the purchase order item
     * @return The total number of rows effect by transaction.
     * @throws VendorPurchasesDaoException
     */
    private int updatePurchaseOrderItem(PurchaseOrderItems item) throws VendorPurchasesDaoException {
        // We must be working with a valid list of PO Items at this point, so
        // add to the database
        UserTimestamp ut = null;
        int rc = 0;
        try {
            ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            // Apply all items belonging to the base purchase order.
            item.setDateUpdated(ut.getDateCreated());
            item.setUserId(ut.getLoginId());
            rc = this.client.updateRow(item);
            msg = "Purchase order item, " + rc  + " , was updated successfully to the database for PO id, "  + item.getPoId();
            logger.info(msg);
            return rc;
        } catch (Exception e) {
            msg = "Failed to update a purchase order item to database for PO id, "  + item.getPoId();
            logger.error(msg, e);
            throw new VendorPurchasesDaoException(e);
        }
    }

    /**
     * Assigns a new status to a purchase order and applies the changes to
     * purchase order status history table in the database.
     * <p>
     * Before the new status assignment, the current status is terminated by
     * assigning an end date of the current day. Since there is no logic
     * implemented in this method to govern the movement of purchase order
     * statuses, invoke method, verifyStatusChange(int, int), prior to this
     * method in order to verify that moving to the new status is in alignment
     * with the business rules of changing purchase order statuses.
     * 
     * @param dto
     *            an instance of {@link PurchaseOrderStatusHistDto}. If null,
     *            method will abort without attempting to change status.
     * @param newStatusId
     *            The id of status that is to be assigned to the purchase order.
     * @throws VendorPurchasesException
     */
    @Override
    public void changePurchaseOrderStatus(PurchaseOrderStatusHistDto dto, int newStatusId) 
            throws VendorPurchasesDaoException {
        if (dto == null) {
            msg = "Unable to change purchase order status due to PO status history object is invalid";
            logger.warn(msg);
            return;
        }
        PurchaseOrderStatusHist posh = VendorPurchasesDaoFactory.createPurchaseOrderStatusHist(dto);
        // Close out current status
        if (posh != null) {
            this.updatePurchaseOrderStatusHist(posh);
        }

        // Add new status
        this.createPurchaseOrderStatusHist(posh.getPoId(), newStatusId);
    }

    /**
     * Inserts a purchase order status history item by setting the end date
     * property to null.
     * 
     * @param poId
     *            The id of the target purchase order
     * @param newStatusId
     *            The id of status that is to be assigned to the purchase order.
     * @return the id of the new purchase order status history item
     * @throws VendorPurchasesDaoException
     */
    private int createPurchaseOrderStatusHist(int poId, int newStatusId) throws VendorPurchasesDaoException {
        try {
            int rc = 0;
            PurchaseOrderStatusHist posh = VendorPurchasesDaoFactory.createPurchaseOrderStatusHist(poId, newStatusId);
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            posh.setEffectiveDate(ut.getDateCreated());
            posh.setNull("endDate");
            posh.setUserId(ut.getLoginId());
            rc = this.client.insertRow(posh, true);
            return rc;
        } catch (Exception e) {
            throw new VendorPurchasesDaoException(e);
        }
    }

    /**
     * Terminates a purchase order status history item by setting the end date
     * property to the current timestamp.
     * 
     * @param posh
     *            The purchase order object
     * @return The total number of rows effected by the transaction.
     * @throws VendorPurchasesDaoException
     */
    private int updatePurchaseOrderStatusHist(PurchaseOrderStatusHist posh) throws VendorPurchasesDaoException {
        int rc = 0;
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            posh.setEndDate(ut.getDateCreated());
            posh.setUserId(ut.getLoginId());
            posh.addCriteria(PurchaseOrderStatusHist.PROP_POSTATUSHISTID, posh.getPoStatusHistId());
            rc = this.client.updateRow(posh);
            return rc;
        } catch (Exception e) {
            throw new VendorPurchasesDaoException(e);
        }
    }

}
