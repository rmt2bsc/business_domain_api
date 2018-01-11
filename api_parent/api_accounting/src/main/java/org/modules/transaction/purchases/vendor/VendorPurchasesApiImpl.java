package org.modules.transaction.purchases.vendor;

import java.util.Date;
import java.util.List;

import org.AccountingConst;
import org.apache.log4j.Logger;
import org.dao.mapping.orm.rmt2.PurchaseOrder;
import org.dao.mapping.orm.rmt2.Xact;
import org.dao.transaction.purchases.vendor.VendorPurchasesConst;
import org.dao.transaction.purchases.vendor.VendorPurchasesDao;
import org.dao.transaction.purchases.vendor.VendorPurchasesDaoException;
import org.dao.transaction.purchases.vendor.VendorPurchasesDaoFactory;
import org.dto.CreditorDto;
import org.dto.CreditorTypeDto;
import org.dto.ItemMasterDto;
import org.dto.ItemMasterTypeDto;
import org.dto.PurchaseOrderDto;
import org.dto.PurchaseOrderItemDto;
import org.dto.PurchaseOrderStatusDto;
import org.dto.PurchaseOrderStatusHistDto;
import org.dto.VendorItemDto;
import org.dto.VwVendorItemDto;
import org.dto.XactDto;
import org.dto.XactTypeItemActivityDto;
import org.dto.adapter.orm.transaction.Rmt2XactDtoFactory;
import org.dto.adapter.orm.transaction.purchaseorder.Rmt2PurchaseOrderDtoFactory;
import org.modules.inventory.InventoryApi;
import org.modules.inventory.InventoryApiException;
import org.modules.inventory.InventoryApiFactory;
import org.modules.inventory.InventoryConst;
import org.modules.subsidiary.CreditorApi;
import org.modules.subsidiary.CreditorApiException;
import org.modules.subsidiary.SubsidiaryApiFactory;
import org.modules.transaction.AbstractXactApiImpl;
import org.modules.transaction.XactApiException;
import org.modules.transaction.XactConst;

import com.InvalidDataException;
import com.NotFoundException;
import com.api.persistence.DaoClient;
import com.api.persistence.DatabaseException;
import com.util.RMT2String;
import com.util.assistants.Verifier;
import com.util.assistants.VerifyException;

/**
 * Api Implementation of {@link VendorPurchasesApi} that manages vendor purchase
 * transactions.
 * 
 * @author Roy Terrell
 * 
 */
class VendorPurchasesApiImpl extends AbstractXactApiImpl implements VendorPurchasesApi {

    private static final Logger logger = Logger.getLogger(VendorPurchasesApiImpl.class);

    private VendorPurchasesDaoFactory daoFact;

    private VendorPurchasesDao dao;

    private int vendorId;

    /**
     * Creates an VendorPurchasesApiImpl which creates a stand alone connection.
     */
    public VendorPurchasesApiImpl() {
        super();
        this.dao = this.daoFact.createRmt2OrmDao();
        this.setSharedDao(this.dao);
        return;
    }

    /**
     * Creates an VendorPurchasesApiImpl which creates a stand alone connection.
     * 
     * @param appName
     *            Application Name
     */
    public VendorPurchasesApiImpl(String appName) {
        super();
        this.dao = this.daoFact.createRmt2OrmDao(appName);
        this.setSharedDao(this.dao);
        return;
    }

    /**
     * Creates an VendorPurchasesApiImpl initialized with a shared connection,
     * <i>dao</i>. object.
     * 
     * @param connection
     */
    public VendorPurchasesApiImpl(DaoClient connection) {
        super(connection);
        this.dao = this.daoFact.createRmt2OrmDao(this.getSharedDao());
    }

    @Override
    public void init() {
        super.init();
        this.daoFact = new VendorPurchasesDaoFactory();
    }
    
    /**
     * Finds a purchase order that is associated with value.
     * 
     * @param poId
     *            The Id of the purchase order.
     * @return An instance of {@link PurchaseOrderDto}
     * @throws VendorPurchasesApiException
     * @throws {@link InvalidDataException} <i>poId</i> is null or less than or equal to zero.
     */
    @Override
    public PurchaseOrderDto getPurchaseOrder(Integer poId) throws VendorPurchasesApiException {
        this.validatePurchaseOrderId(poId);
        
        PurchaseOrderDto criteria = Rmt2PurchaseOrderDtoFactory.createPurchaseOrderInstance(null);
        criteria.setPoId(poId);
        List<PurchaseOrderDto> results;
        try {
            results = this.dao.fetchPurchaseOrder(criteria);
            if (results == null) {
                return null;
            }
        } catch (VendorPurchasesDaoException e) {
            msg = "DAO failed Purchase Order Fetch";
            logger.error(msg, e);
            throw new VendorPurchasesApiException(msg, e);
        }

        if (results.size() > 1) {
            msg = "DAO failed Purchase Order Fetch:  too many rows returned";
            logger.error(msg);
            throw new VendorPurchasesApiException(msg);
        }
        return results.get(0);
    }

    /**
     * Finds one or more purchase order using purchase order selection criteria.
     * 
     * @param criteria
     *            an instance of {@link PurchaseOrderDto}
     * @return A List of {@link PurchaseOrderDto} objects
     * @throws VendorPurchasesApiException
     */
    @Override
    public List<PurchaseOrderDto> getPurchaseOrder(PurchaseOrderDto criteria) throws VendorPurchasesApiException {
        try {
            Verifier.verifyNotNull(criteria);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Purchase order selection criteria object is required");
        }
        
        List<PurchaseOrderDto> results;
        try {
            results = this.dao.fetchPurchaseOrder(criteria);
            if (results == null) {
                return null;
            }
        } catch (VendorPurchasesDaoException e) {
            msg = "DAO failed Purchase Order Fetch using purchase order selection criteria: "
                    + criteria.toString();
            logger.error(msg, e);
            throw new VendorPurchasesApiException(msg, e);
        }
        return results;
    }
    
    /**
     * Finds one or more purchase order using custom selection criteria.
     * 
     * @param customCriteria
     *            A String representing custom criteria.
     * @return A List of {@link PurchaseOrderDto} objects
     * @throws VendorPurchasesApiException
     */
    @Override
    public List<PurchaseOrderDto> getPurchaseOrder(String customCriteria) throws VendorPurchasesApiException {
        PurchaseOrderDto criteria = Rmt2PurchaseOrderDtoFactory.createPurchaseOrderInstance(null);
        criteria.setCriteria(customCriteria);
        try {
            return this.getPurchaseOrder(criteria);
        } catch (VendorPurchasesDaoException e) {
            msg = "DAO failed Purchase Order Fetch using custom criteria: "
                    + customCriteria;
            logger.error(msg, e);
            throw new VendorPurchasesApiException(msg, e);
        }
    }

    /**
     * Finds a single purchase order item using _poItemId.
     * 
     * @param poId
     *            The Id of the purchase order.
     * @param poItemId
     *            The Id of the purchase order item.
     * @return An instance of {@link PurchaseOrderItemDto}
     * @throws VendorPurchasesApiException
     */
    @Override
    public PurchaseOrderItemDto getPurchaseOrderItem(Integer poItemId) throws VendorPurchasesApiException {
        this.validatePurchaseOrderItemId(poItemId);
        
        PurchaseOrderItemDto criteria = Rmt2PurchaseOrderDtoFactory.createPurchaseOrderItemInstance(null);
        criteria.setPoItemId(poItemId);
        List<PurchaseOrderItemDto> results;
        try {
            results = this.dao.fetchPurchaseOrderItem(criteria);
            if (results == null) {
                return null;
            }
        } catch (VendorPurchasesDaoException e) {
            msg = "DAO failed Purchase Order Item Fetch using PO Item Id: " + poItemId;
            logger.error(msg, e);
            throw new VendorPurchasesApiException(msg, e);
        }
        if (results.size() > 1) {
            msg = "DAO failed Purchase Order Item Fetch:  too many rows returned using PO Item Id: " + poItemId;
            logger.error(msg);
            throw new VendorPurchasesApiException(msg);
        }
        return results.get(0);
    }

    /**
     * Finds all items belonging to a purchase order identified as, value
     * 
     * @param poId
     *            The purchase order id
     * @return A List of {@link PurchaseOrderItemDto} objects
     * @throws VendorPurchasesApiException
     */
    @Override
    public List<PurchaseOrderItemDto> getPurchaseOrderItems(Integer poId) throws VendorPurchasesApiException {
        this.validatePurchaseOrderId(poId);
        
        PurchaseOrderItemDto criteria = Rmt2PurchaseOrderDtoFactory.createPurchaseOrderItemInstance(null);
        criteria.setPoId(poId);
        List<PurchaseOrderItemDto> results;
        try {
            results = this.dao.fetchPurchaseOrderItem(criteria);
            if (results == null) {
                return null;
            }
        } catch (VendorPurchasesDaoException e) {
            msg = "DAO failed Purchase Order Item Fetch using PO Id: " + poId;
            logger.error(msg, e);
            throw new VendorPurchasesApiException(msg, e);
        }
        return results;
    }

    /**
     * Finds one vendor item object using vendorId and itemId
     * 
     * @param vendorId
     *            The id of the vendor
     * @param vendorItemNo
     *            The external vendor item number
     * @return An instance of {@link VendorItemDto}
     * @throws VendorPurchasesApiException
     */
    @Override
    public VendorItemDto getVendorItem(Integer vendorId, String vendorItemNo) throws VendorPurchasesApiException {
        this.validateVendorId(vendorId);
        try {
            Verifier.verifyNotEmpty(vendorItemNo);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Vendor Item Number is required");
        }
        
        VendorItemDto criteria = Rmt2PurchaseOrderDtoFactory.createVendorItemInstance(null);
        criteria.setVendorId(vendorId);
        criteria.setVendorItemNo(vendorItemNo);
        List<VendorItemDto> results;
        StringBuffer buf = new StringBuffer();
        try {
            results = this.dao.fetchVendorItem(criteria);
            if (results == null) {
                return null;
            }
        } catch (VendorPurchasesDaoException e) {
            buf.append("DAO failed Purchase Order Vendor Item Fetch using vendor Id [");
            buf.append(vendorId);
            buf.append(" and vendor item number [");
            buf.append(vendorItemNo);
            buf.append("]");
            msg = buf.toString();
            logger.error(msg, e);
            throw new VendorPurchasesApiException(msg, e);
        }
        
        if (results.size() > 1) {
            msg = "DAO failed Purchase Order Vendor Item Fetch:  too many rows returned";
            logger.error(msg);
            throw new VendorPurchasesApiException(msg);
        }
        return results.get(0);
    }

    /**
     * Retrieve a list of purchase order items for a vendor in which each item
     * contains a combination of item master and vendor external item
     * information.
     * 
     * @param vendorId
     *            The vendor id
     * @param poId
     *            The purchase order id
     * @return A List of {@link PurchaseOrderItemDto} objects
     * @throws VendorPurchasesApiException
     */
    @Override
    public List<PurchaseOrderItemDto> getPurchaseOrderVendorInventoryItems(Integer vendorId, Integer poId) 
            throws VendorPurchasesApiException {
        
        this.validatePurchaseOrderId(poId);
        this.validateVendorId(vendorId);
        
        List<PurchaseOrderItemDto> results;
        StringBuffer buf = new StringBuffer();
        try {
            results = this.dao.fetchCombinedVendorPurchaseOrderItem(vendorId, poId);
            if (results == null) {
                return null;
            }
        } catch (VendorPurchasesDaoException e) {
            buf.append("DAO failed Purchase Order Combined Item Fetch using vendor Id [");
            buf.append(vendorId);
            buf.append(" and PO id [");
            buf.append(poId);
            buf.append("]");
            msg = buf.toString();
            logger.error(msg, e);
            throw new VendorPurchasesApiException(msg, e);
        }
        return results;
    }

    /**
     * Retrieves a list of inventory items which have not been assoicated with a
     * vendor's purchase order.
     * 
     * @param vendorId
     *            The id of the vendor.
     * @param poId
     *            The id of the purchase order.
     * @return A List of {@l VwVendorItemDto} objects
     * @throws VendorPurchasesApiException
     * @throws {@link InvalidDataException}
     */
    @Override
    public List<VwVendorItemDto> getPurchaseOrderAvailableItems(Integer vendorId, Integer poId)
            throws VendorPurchasesApiException {
        this.validateVendorId(vendorId);
        this.validatePurchaseOrderId(poId);
        
        List<VwVendorItemDto> results;
        StringBuffer buf = new StringBuffer();
        try {
            results = this.dao.fetchUnassignedPurchaseOrderItems(vendorId, poId);
            if (results == null) {
                return null;
            }
        } catch (VendorPurchasesDaoException e) {
            buf.append("DAO failed Purchase Order Available Items Fetch using vendor Id [");
            buf.append(vendorId);
            buf.append(" and PO id [");
            buf.append(poId);
            buf.append("]");
            msg = buf.toString();
            logger.error(msg, e);
            throw new VendorPurchasesApiException(msg, e);
        }
        return results;
    }

    /**
     * Retreives the vendor item data of an ItemMaster item.
     * <p>
     * The external vendor item information of the inventory ItemMaster item is returned.
     * 
     * @param vendorId
     *            The vendor id
     * @param itemMasterId
     *            The item master id of the item to retrieve
     * @return An instance of {@link VwVendorItemDto}
     * @throws VendorPurchasesApiException
     */
    @Override
    public VwVendorItemDto getVendorInventoryItem(Integer vendorId, Integer itemMasterId) throws VendorPurchasesApiException {
        this.validateItemMasterId(itemMasterId);
        this.validateVendorId(vendorId);
        
        VwVendorItemDto criteria = Rmt2PurchaseOrderDtoFactory.createVendorItemExtInstance(null);
        criteria.setVendorId(vendorId);
        criteria.setItemId(itemMasterId);
        List<VwVendorItemDto> results;
        StringBuffer buf = new StringBuffer();
        try {
            results = this.dao.fetchInternalExternalItemData(criteria);
            if (results == null) {
                return null;
            }
        } catch (VendorPurchasesDaoException e) {
            buf.append("DAO failed Purchase Order local/external Vendor Item Fetch using vendor Id [");
            buf.append(vendorId);
            buf.append(" and item id [");
            buf.append(itemMasterId);
            buf.append("]");
            msg = buf.toString();
            logger.error(msg, e);
            throw new VendorPurchasesApiException(msg, e);
        }
        if (results.size() > 1) {
            buf.append("DAO failed Purchase Order local/external Vendor Item Fetch:  too many rows returned using Vendor Id: ");
            buf.append(vendorId);
            buf.append(" and item id [");
            buf.append(itemMasterId);
            buf.append("]");
            msg = buf.toString();
            logger.error(msg);
            throw new VendorPurchasesApiException(msg);
        }
        return results.get(0);
    }

    /**
     * Retrieves the purchase order status based on the value of _poStatusId.
     * 
     * @param poStatusId
     *            The id of the purchase order status to retrieve.
     * @return An instance of {@link PurchaseOrderStatusDto}
     * @throws VendorPurchasesApiException
     */
    @Override
    public PurchaseOrderStatusDto getPurchaseOrderStatus(Integer poStatusId) throws VendorPurchasesApiException {
        this.validatePurchaseOrderStatusId(poStatusId);
        
        PurchaseOrderStatusDto criteria = Rmt2PurchaseOrderDtoFactory.createPurchaseOrderStatusInstance(null);
        criteria.setPoStatusId(poStatusId);
        List<PurchaseOrderStatusDto> results;
        StringBuffer buf = new StringBuffer();
        try {
            results = this.dao.fetchStatus(criteria);
            if (results == null) {
                return null;
            }
        } catch (VendorPurchasesDaoException e) {
            buf.append("DAO failed Purchase Order status Fetch using po status id [");
            buf.append(poStatusId);
            buf.append("]");
            msg = buf.toString();
            logger.error(msg, e);
            throw new VendorPurchasesApiException(msg, e);
        }
        if (results.size() > 1) {
            buf.append("DAO failed Purchase Order status Fetch:  too many rows returned using PO Status Id: ");
            buf.append(poStatusId);
            buf.append("]");
            msg = buf.toString();
            logger.error(msg);
            throw new VendorPurchasesApiException(msg);
        }
        return results.get(0);
    }

    /**
     * Retrieves the current status of a purchase order.
     * 
     * @param poId
     *            Is the id of the purchase order
     * @return An instance of {@link PurchaseOrderStatusHistDto}
     * @throws VendorPurchasesApiException
     */
    @Override
    public PurchaseOrderStatusHistDto getCurrentPurchaseOrderHistory(Integer poId) throws VendorPurchasesApiException {
        this.validatePurchaseOrderId(poId);
        
        PurchaseOrderStatusHistDto criteria = Rmt2PurchaseOrderDtoFactory.createPurchaseOrderStatusHistoryInstance(null);
        criteria.setPoId(poId);
        List<PurchaseOrderStatusHistDto> results;
        StringBuffer buf = new StringBuffer();
        try {
            results = this.dao.fetchPurchaseOrderHistory(criteria, true);
            if (results == null) {
                return null;
            }
        } catch (VendorPurchasesDaoException e) {
            buf.append("DAO failed Purchase Order current status Fetch using po id [");
            buf.append(poId);
            buf.append("]");
            msg = buf.toString();
            logger.error(msg, e);
            throw new VendorPurchasesApiException(msg, e);
        }
        if (results.size() > 1) {
            buf.append("DAO failed Purchase Order current status Fetch:  too many rows returned using PO Id: ");
            buf.append(poId);
            buf.append("]");
            msg = buf.toString();
            logger.error(msg);
            throw new VendorPurchasesApiException(msg);
        }
        return results.get(0);
    }

    /**
     * Retrieves the status history of a purchase order.
     * 
     * @param poId
     *            The purchase order id
     * @return A List of {@link PurchaseOrderStatusHistDto} objects
     * @throws VendorPurchasesApiException
     * @throws {@link InvalidDataException}
     */
    @Override
    public List<PurchaseOrderStatusHistDto> getPurchaseOrderHistory(Integer poId) throws VendorPurchasesApiException {
        this.validatePurchaseOrderId(poId);
        
        PurchaseOrderStatusHistDto criteria = Rmt2PurchaseOrderDtoFactory.createPurchaseOrderStatusHistoryInstance(null);
        criteria.setPoId(poId);
        List<PurchaseOrderStatusHistDto> results;
        StringBuffer buf = new StringBuffer();
        try {
            results = this.dao.fetchPurchaseOrderHistory(criteria, false);
            if (results == null) {
                return null;
            }
        } catch (VendorPurchasesDaoException e) {
            buf.append("DAO failed Purchase Order status history Fetch using po id [");
            buf.append(poId);
            buf.append("]");
            msg = buf.toString();
            logger.error(msg, e);
            throw new VendorPurchasesApiException(msg, e);
        }
        return results;
    }

    /**
     * Returns the current status of a purchase order.
     * 
     * @param poId
     * @return
     * @throws VendorPurchasesApiException
     */
    private PurchaseOrderStatusHistDto getCurrentPurchaseOrderStatus(int poId) throws VendorPurchasesApiException {
        PurchaseOrderStatusHistDto criteria = Rmt2PurchaseOrderDtoFactory.createPurchaseOrderStatusHistoryInstance(null);
        criteria.setPoId(poId);
        List<PurchaseOrderStatusHistDto> poshList = null;
        try {
            poshList = this.dao.fetchPurchaseOrderHistory(criteria, true);
            if (poshList == null) {
                return null;
            }
        } catch (VendorPurchasesDaoException e) {
            msg = "Unable to retrieve current status for purchase order, "
                    + poId;
            logger.error(msg, e);
            throw new VendorPurchasesApiException(msg, e);
        }
        if (poshList.size() > 1) {
            msg = "Unable to change purchase order status due to current status fetch operation returned too many rows";
            logger.error(msg);
            throw new VendorPurchasesApiException(msg);
        }
        return poshList.get(0);
    }

    /**
     * Get the current vendor profile
     * 
     * @param vendorId
     * @return an instance of {@link CreditorDto}
     * @throws VendorPurchasesException
     */
    private CreditorDto getVendorProfile(int vendorId) throws VendorPurchasesApiException {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi credApi = f.createCreditorApi(this.getSharedDao());
        // Get creditor data to used as validating metric
        try {
            CreditorDto vendor = null;
            // Vendor must exist in the database.
            vendor = credApi.getByCreditorId(vendorId);
            return vendor;
        } catch (CreditorApiException e) {
            throw new VendorPurchasesApiException(e);
        } finally {
            f = null;
            credApi = null;
        }
    }

    /**
     * Get creditor type profile for vendor
     * 
     * @return an instance of {@link CreditorTypeDto}
     * @throws VendorPurchasesException
     */
    private CreditorTypeDto getCreditorTypeProfileForVendor() throws VendorPurchasesApiException {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi credApi = f.createCreditorApi(this.getSharedDao());
        // Get creditor data to used as validating metric
        try {
            CreditorTypeDto ct = null;
            // Vendor type must exist in the database.
            ct = credApi.getCreditorType(AccountingConst.CREDITORTYPE_VENDOR);
            return ct;
        } catch (CreditorApiException e) {
            throw new VendorPurchasesApiException(e);
        } finally {
            f = null;
            credApi = null;
        }
    }

    /**
     * Creates a new or updates an existing purchase order and its items.
     * <p>
     * When the purchase order id equals zero, a new purchase order is created.
     * When the purchase order id greater than zero, an existing purchase order
     * is modified.
     * 
     * @param po
     *            Purchase Order object.
     * @param items
     *            list of purchase order items.
     * @return Id of a new purchase order when _po's id property equals zero or
     *         the total number of rows updated for existing purchase orders
     *         where po's id property is greater thatn zero.
     * @throws VendorPurchasesApiException
     *             validation errors or general database access error
     */
    @Override
    public int updatePurchaseOrder(PurchaseOrderDto po, List<PurchaseOrderItemDto> items)
            throws VendorPurchasesApiException {

        this.validatePurchaseOrder(po);
        this.validatePurchaseOrderItems(items);
        
        int rc = 0;
        if (po.getPoId() == 0) {
            // Returns new Purchase order id
            rc = this.createPurchaseOrder(po, items);
        }
        else {
            // Returns total number of rows updated.
            rc = this.updatePurchaseOrder(po, items, true);
        }
        return rc;
    }

    /**
     * 
     * @param po
     * @param items
     * @return
     * @throws VendorPurchasesApiException
     */
    private int createPurchaseOrder(PurchaseOrderDto po, List<PurchaseOrderItemDto> items)
            throws VendorPurchasesApiException {
        int poId = 0;
        try {
            poId = this.dao.maintainPurchaseOrder(po);
        } catch (VendorPurchasesDaoException e) {
            this.msg = "Unable to create purchase order base";
            logger.error(this.msg, e);
            throw new VendorPurchasesApiException(this.msg, e);
        }
        this.insertPurchaseOrderItems(poId, items);

        // Always assign a status of "Quote" for a new purchase order.
        this.setPurchaseOrderStatus(poId,
                VendorPurchasesConst.PURCH_STATUS_QUOTE);
        return poId;
    }

    /**
     * 
     * @param po
     * @param items
     * @param refresh
     *            boolean which determines whether existing purchase order items
     *            should be deleted prior to adding revisions.
     * @return
     * @throws VendorPurchasesApiException
     */
    private int updatePurchaseOrder(PurchaseOrderDto po, List<PurchaseOrderItemDto> items, boolean refresh)
            throws VendorPurchasesApiException {

        StringBuffer buf = new StringBuffer();

        // Purchase order can only be updated when status is either Quote or
        // Submitted.
        PurchaseOrderStatusHistDto posh = this.getCurrentPurchaseOrderHistory(po.getPoId());
        int statusId = posh.getPoStatusId();
        if (statusId != VendorPurchasesConst.PURCH_STATUS_QUOTE && statusId != VendorPurchasesConst.PURCH_STATUS_FINALIZE) {
            buf.append("Purchase order #");
            buf.append(po.getPoId());
            buf.append(" was denied updates.   Must be in Quote or Submitted status.");
            this.msg = buf.toString();
            logger.error(this.msg);
            throw new PurchaseOrderBadStatusException(msg);
        }

        int rc = 0;
        try {
            rc = this.dao.maintainPurchaseOrder(po);
        } catch (VendorPurchasesDaoException e) {
            this.msg = "Unable to create purchase order base";
            logger.error(this.msg, e);
            throw new VendorPurchasesApiException(this.msg, e);
        }

        // TODO: add logic to insert or refresh po items
        if (refresh) {
            rc = this.updatePurchaseOrderItems(po.getPoId(), items);
        }
        else {
            rc = this.insertPurchaseOrderItems(po.getPoId(), items);
        }

        // Determine if purchase order is ready to be placed in "Received"
        // status. The purchase order quantity of all items must have been
        // received and the current purchase order status must be "Submitted".
        if (rc == VendorPurchasesConst.PO_UPDATE_RECEIVED && statusId == VendorPurchasesConst.PURCH_STATUS_FINALIZE) {
            this.setPurchaseOrderStatus(po.getPoId(), VendorPurchasesConst.PURCH_STATUS_RECEIVED);
        }
        return rc;
    }

    /**
     * Adds a purchase order's items to the databse
     * 
     * @param poId
     *            Id of the base purchase order.
     * @param items
     *            All related purchase order items
     * @return The total number of rows effect by transaction.
     * @throws VendorPurchasesApiException
     */
    private int insertPurchaseOrderItems(int poId, List<PurchaseOrderItemDto> items) throws VendorPurchasesApiException {
        int rc = 0;
        try {
            // Apply all items belonging to the base purchase order.
            for (PurchaseOrderItemDto poi : items) {
                poi.setPoId(poId);
                this.validatePurchaseOrderItem(poi);
                rc += this.dao.maintainPurchaseOrderItem(poi);
            }
            return rc;
        } catch (Exception e) {
            logger.error(e);
            throw new VendorPurchasesApiException(e);
        }
    }

    /**
     * Modifies exisitng purchase order items and applys the changes to the
     * database. All existing items for a purchase order are deleted and
     * replaced by _items when the purchase order's current status is "Quote".
     * When the current status is "Submitted", all exisiting items can no longer
     * be deleted and are sequentially retrieved from and updated to the
     * database instead.
     * 
     * @param poId
     *            Id of the base purchase order
     * @param items
     *            Modified Purchase order items.
     * @return 1 indicating the successful update of a "Quote" purchase order or
     *         "Submitted" purchase order that has not received its total order
     *         quantity. 0 indicating the "Submitted" purchase order has been
     *         completely received.
     * @throws VendorPurchasesApiException
     */
    private int updatePurchaseOrderItems(int poId, List<PurchaseOrderItemDto> items) throws VendorPurchasesApiException {
        int rc = 0;
        int poUncollectCnt = 0;

        // Purchase order can only be updated when status is either Quote or
        // Submitted.
        PurchaseOrderStatusHistDto posh = this.getCurrentPurchaseOrderHistory(poId);
        int statusId = posh.getPoStatusId();

        // We must be working with a valid po at this point, so add to the
        // database
        try {
            // Use the current purchase order status to determine if items are
            // to be totally refreshed or if each item should be retrieved,
            // reconciled, and updated accordingly.
            switch (statusId) {
                case VendorPurchasesConst.PURCH_STATUS_QUOTE:
                    // Perform a complete refresh of all purchase order items.
                    this.deleteAllItems(poId);
                    for (PurchaseOrderItemDto deltaPoi : items) {
                        this.validatePurchaseOrderItem(deltaPoi);
                        rc = this.dao.maintainPurchaseOrderItem(deltaPoi);
                    }
                    rc = VendorPurchasesConst.PO_UPDATE_SUCCESSFUL;
                    break;

                case VendorPurchasesConst.PURCH_STATUS_FINALIZE:
                    for (PurchaseOrderItemDto deltaPoi : items) {
                        try {
                            rc = this.updatePurchaseOrderItem(deltaPoi);
                            // Add item's uncollected order quantity
                            poUncollectCnt += (deltaPoi.getVendorQtyOnHand()
                                    - deltaPoi.getQtyRcvd());
                        } catch (NotFoundException e) {
                            continue;
                        }
                    }
                    if (poUncollectCnt == 0) {
                        rc = VendorPurchasesConst.PO_UPDATE_RECEIVED;
                    }
                    if (poUncollectCnt >= 0) {
                        rc = VendorPurchasesConst.PO_UPDATE_SUCCESSFUL;
                    }
                    break;
            } // end switch

            return rc;
        } catch (Exception e) {
            logger.error(e);
            throw new VendorPurchasesApiException(e);
        }
    }

    /**
     * Applies the modifications of an exisitng purchase order item to the
     * database and updates inventory with the quantity received for _deltaItem.
     * This method is generally used during the time of updating a "Submitted"
     * purchase order for the purpose of reconciling the quantity ordered and
     * quantity received totals for each item.
     * <p>
     * Basic processing scenario:
     * <p>
     * <ol>
     * <li>Retrieve the orginal version of the purchase order</li>
     * <li>Update item's received quantity with input</li>
     * <li>Calculate difference in quantity ordered and quantity received</li>
     * <li>Apply item update to the database</li>
     * <li>Add the difference to uncollected order quantity count for the
     * purchase order</li>
     * </ol>
     * 
     * @param deltaItem
     *            The purchase order item data.
     * @return 0 indicating the total order quantity for this item has beed
     *         received. > 0 indicating the item's uncollected order quantity.
     * @throws VendorPurchasesApiException
     * @throws NotFoundException
     *             a problem accessing item data from the database.
     */
    private int updatePurchaseOrderItem(PurchaseOrderItemDto deltaItem) throws VendorPurchasesApiException {
        int rc = 0;
        int adjQtyOnHand = 0;
        PurchaseOrderItemDto oldPoi = null;
        StringBuffer buf = new StringBuffer();

        // We must be working with a valid po item at this point, so apply
        // updates to the database
        try {
            // Use the current purchase order status to determine if items are
            // to be totally refreshed or
            // if each item should be retrieved and updated accordingly.
            oldPoi = this.getPurchaseOrderItem(deltaItem.getItemId());
            if (oldPoi == null) {
                buf.append("Purchase order #");
                buf.append(deltaItem.getItemId());
                buf.append(", was not found for PO #");
                buf.append(deltaItem.getPoId());
                this.msg = buf.toString();
                logger.error(this.msg);
                throw new NotFoundException(this.msg);
            }

            // Apply Quantity Received and user update timestamp
            oldPoi.setQtyRcvd(deltaItem.getQtyRcvd() + oldPoi.getQtyRcvd());
            this.validatePurchaseOrderItem(oldPoi);
            rc = this.dao.maintainPurchaseOrderItem(oldPoi);

            // Update inventory by adding quantity received to Quantity on hand
            // for target item.
            try {
                InventoryApiFactory f = new InventoryApiFactory();
                InventoryApi invApi = f.createApi(getSharedDao());
                ItemMasterDto im = invApi.getItemById(oldPoi.getItemId());
                adjQtyOnHand = im.getQtyOnHand() + this.calculateItemNetOrderQty(oldPoi);
                im.setQtyOnHand(adjQtyOnHand);
                invApi.updateItemMaster(im);
            } catch (InventoryApiException e) {
                this.msg = "Problemu updating inventory for Purchase order item, "
                        + deltaItem.getItemId() + ", of PO #"
                        + deltaItem.getPoId() + ":  " + e.getMessage();
                throw new VendorPurchasesApiException(e);
            }

            if (rc >= 1) {
                // Set return code to equal the uncollected order quantity for
                // this item.
                rc = oldPoi.getVendorQtyOnHand() - oldPoi.getQtyRcvd();
            }
            return rc;
        } catch (Exception e) {
            logger.error(e);
            throw new VendorPurchasesApiException(e);
        }
    }

    /**
     * Deletes a purchase order.
     * 
     * @param poId
     *            The purchase order id
     * @return int
     * @throws VendorPurchasesApiException
     */
    @Override
    public int deletePurchaseOrder(Integer poId) throws VendorPurchasesApiException {
        this.validatePurchaseOrderId(poId);
        
        PurchaseOrderDto criteria = Rmt2PurchaseOrderDtoFactory.createPurchaseOrderInstance(null);
        criteria.setPoId(poId);
        StringBuffer buf = new StringBuffer();

        // Purchase order can only be deleted from the system when in Quote
        // status.
        PurchaseOrderStatusHistDto posh = this.getCurrentPurchaseOrderHistory(poId);
        int statusId = posh.getPoStatusId();
        if (statusId != VendorPurchasesConst.PURCH_STATUS_QUOTE) {
            buf.append("Purchase order #");
            buf.append(poId);
            buf.append(" cannot be deleted.   Must be in Quote status to perform deletes.");
            logger.error(this.msg);
            throw new PurchaseOrderBadStatusException(this.msg);
        }

        try {
            int rc = 0;
            // Remove all purchase order items
            rc = this.deleteAllItems(poId);
            // Remove actual purchase order object
            rc = this.dao.deletePurchaseOrder(criteria);
            buf.append("DAO Purchase Order Delete operation removed ");
            buf.append(rc);
            buf.append(" rows");
            msg = buf.toString();
            logger.info(msg);
            return rc;
        } catch (VendorPurchasesDaoException e) {
            buf.append("DAO failed Purchase Order Delete using PO Id [");
            buf.append(poId);
            buf.append("]");
            msg = buf.toString();
            logger.error(msg, e);
            throw new VendorPurchasesApiException(msg, e);
        }
    }

    /**
     * Deletes a single purchase order item.
     * 
     * @param poId
     *            Id of the purchase order which all items will be removed.
     * @param poItemId
     *            Id of the purchase order item
     * @return int
     * @throws VendorPurchasesApiException
     */
    @Override
    public int deleteItem(Integer poId, Integer poItemId) throws VendorPurchasesApiException {
        this.validatePurchaseOrderId(poId);
        this.validatePurchaseOrderItemId(poItemId);
        
        PurchaseOrderItemDto criteria = Rmt2PurchaseOrderDtoFactory.createPurchaseOrderItemInstance(null);
        criteria.setPoId(poId);
        criteria.setPoItemId(poItemId);
        StringBuffer buf = new StringBuffer();
        try {
            int rc = 0;
            rc = this.dao.deletePurchaseOrderItem(criteria);
            buf.append("DAO Purchase Order Item Delete operation removed ");
            buf.append(rc);
            buf.append(" rows");
            msg = buf.toString();
            logger.info(msg);
            return rc;
        } catch (VendorPurchasesDaoException e) {
            buf.append("DAO failed Purchase Order Item Delete using PO Id [");
            buf.append(poId);
            buf.append(" and item id [");
            buf.append(poItemId);
            buf.append("]");
            msg = buf.toString();
            logger.error(msg, e);
            throw new VendorPurchasesApiException(msg, e);
        }
    }

    /**
     * Deletes all items belonging to a purchase order.
     * 
     * @param poId
     *            Id of the purchase order which all items will be removed.
     * @return int
     * @throws VendorPurchasesApiException
     */
    @Override
    public int deleteAllItems(Integer poId) throws VendorPurchasesApiException {
        this.validatePurchaseOrderId(poId);
        
        PurchaseOrderItemDto criteria = Rmt2PurchaseOrderDtoFactory.createPurchaseOrderItemInstance(null);
        criteria.setPoId(poId);
        StringBuffer buf = new StringBuffer();
        try {
            int rc = 0;
            rc = this.dao.deletePurchaseOrderItem(criteria);
            buf.append("DAO Purchase Order Item Delete All operation removed ");
            buf.append(rc);
            buf.append(" rows");
            msg = buf.toString();
            logger.info(msg);
            return rc;
        } catch (VendorPurchasesDaoException e) {
            buf.append(
                    "DAO failed Purchase Order Item Delete All operation using PO Id [");
            buf.append(poId);
            buf.append("]");
            msg = buf.toString();
            logger.error(msg, e);
            throw new VendorPurchasesApiException(msg, e);
        }
    }

    /**
     * Assigns a new status to a purchase order.
     * 
     * @param poId
     *            The id of the target purchase order
     * @param newStatusId
     *            The id of status that is to be assigned to the purchase order.
     * @throws VendorPurchasesApiException
     */
    @Override
    public void setPurchaseOrderStatus(Integer poId, Integer newStatusId) throws VendorPurchasesApiException {
        this.validatePurchaseOrderId(poId);
        this.validatePurchaseOrderStatusId(newStatusId);
        
        PurchaseOrderStatusHistDto posh = this.getCurrentPurchaseOrderStatus(poId);
        if (posh == null) {
            msg = "Unable to change purchase order status due to current status does not exist";
            logger.error(msg);
            throw new VendorPurchasesApiException(msg);
        }
        try {
            this.dao.changePurchaseOrderStatus(posh, newStatusId);
        } catch (VendorPurchasesDaoException e) {
            msg = "Unable to change purchase order status due to DAO error";
            logger.error(msg, e);
            throw new VendorPurchasesApiException(msg, e);
        }
        return;
    }

    /**
     * Verifies that changing the status of the purchase order identified as
     * <i>poId</i> to the new status represented as <i>newStatusId</i> is legal.
     * The change is considered legal only if an exception is not thrown.
     * <p>
     * The following sequence must be followed when changing the status of a
     * purchase order:
     * <p>
     * <ul>
     * <li>The purchase order must be new in order to change the status to
     * "Quote"</li>
     * <li>The purchase order must be in "Quote" status before changing to
     * "Submitted".</li>
     * <li>The purchase order must be in "Submitted" status before changing to
     * "Received".</li>
     * <li>The purchase order must be in "Quote" or "Submitted" status before
     * changing to "Cancelled".</li>
     * </ul>
     * 
     * @param poId
     *            Target purchase order id
     * @param newStatusId
     *            The id of the status that is to be assigned to the purchase
     *            order.
     * @return The id of the old status.
     * @throws VendorPurchasesApiException
     *             When the prospective new status is not in sequence to the
     *             current status regarding changing the status of the purchase
     *             order. The exception should give a detail explanation as to
     *             the reason why the status cannot be changed.
     */
    protected int verifyStatusChange(int poId, int newStatusId) throws VendorPurchasesApiException {
        PurchaseOrderStatusHistDto posh = this.getCurrentPurchaseOrderStatus(poId);
        int currentStatusId = 0;
        boolean error = false;
        currentStatusId = (posh == null ? VendorPurchasesConst.PURCH_STATUS_NEW : posh.getPoStatusId());
        switch (newStatusId) {
            case VendorPurchasesConst.PURCH_STATUS_QUOTE:
                if (currentStatusId != VendorPurchasesConst.PURCH_STATUS_NEW) {
                    this.msg = "Quote status can only be assigned when the purchase order is new";
                    error = true;
                }
                break;

            case VendorPurchasesConst.PURCH_STATUS_FINALIZE:
                if (currentStatusId != VendorPurchasesConst.PURCH_STATUS_QUOTE) {
                    this.msg = "Purchase order must be in Quote status before changing to Submitted";
                    error = true;
                }
                break;

            case VendorPurchasesConst.PURCH_STATUS_RECEIVED:
                if (currentStatusId != VendorPurchasesConst.PURCH_STATUS_FINALIZE) {
                    this.msg = "Purchase order must be in Submitted status before changing to Received";
                    error = true;
                }
                break;

            case VendorPurchasesConst.PURCH_STATUS_CANCEL:
                switch (currentStatusId) {
                    case VendorPurchasesConst.PURCH_STATUS_QUOTE:
                    case VendorPurchasesConst.PURCH_STATUS_FINALIZE:
                        break;

                    default:
                        this.msg = "Purchase order must be in Quote or Submitted status before changing to Cancelled";
                        error = true;
                } // end inner switch
                break;

            case VendorPurchasesConst.PURCH_STATUS_RETURN:
                switch (currentStatusId) {
                    case VendorPurchasesConst.PURCH_STATUS_RECEIVED:
                    case VendorPurchasesConst.PURCH_STATUS_PARTRET:
                        break;

                    default:
                        this.msg = "Purchase order must be in Received status before changing to Returned";
                        error = true;
                } // end inner switch
                break;
        } // end outer switch

        if (error) {
            logger.error(this.msg);
            throw new CannotChangePurchaseOrderStatusException(this.msg);
        }
        return currentStatusId;
    }

    /**
     * Validates base purchase order data.
     * <p>
     * <p>
     * The following validations must be met:
     * <ul>
     * <li>A purchase order's vendor id must be greater than zero</li>
     * <li>Vendor must be a vendor type</li>
     * </ul>
     * 
     * @param po
     *            Purchase order object containing the base data.
     * @throws VendorPurchasesApiException
     *             if the vendor value is less than or equal to zero, or vendor
     *             does not exist, or creditor is not a vendor type.
     */
    protected void validatePurchaseOrder(PurchaseOrderDto po) throws VendorPurchasesApiException {
        try {
            Verifier.verifyNotNull(po);
        }
        catch (VerifyException e) {
            throw new PurchaseOrderValidationException("Purchase order DTO object is required");
        }

        // Vendor id must be greater than zero.
        try {
            Verifier.verifyPositive(po.getCreditorId()); 
        }
        catch (VerifyException e) {
            throw new PurchaseOrderValidationException("Purchase order's vendor id must be greater than zero");
        }

        // Vendor must exist in the database.
        CreditorDto vendor = this.getVendorProfile(po.getCreditorId());
        try {
            Verifier.verifyNotNull(vendor);
        }
        catch (VerifyException e) {
            throw new PurchaseOrderValidationException("Vendor does not exist for creditor id, " + po.getCreditorId());
        }

        // Get vendor data to used as validating metric
        CreditorTypeDto creditorType = this.getCreditorTypeProfileForVendor();
        try {
            Verifier.verifyNotNull(creditorType);
        }
        catch (VerifyException e) {
            throw new PurchaseOrderValidationException("Unable to obtain Creditor Type DTO for Vendor profile");
        }
        
        // Vendor must be of type vendor.
        if (vendor.getCreditorTypeId() != creditorType.getEntityId()) {
            throw new PurchaseOrderValidationException("Vendor must be of type vendor profile");
        }
    }

    /**
     * Validates an item belonging to a purchase order.
     * <p>
     * <p>
     * The following validations must be met:
     * <ul>
     * <li>The item must contain a valid purchase order id which must be greater
     * than zero. This check is only performed for existing purchase order items
     * </li>
     * <li>Item Master Id must be a valid and is greater than zero</li>
     * <li>The selected item master id must be a tangible and resalable
     * inventory item</li>
     * <li>The value of quantity orderd must be greater than or equal to
     * zero</li>
     * <li>The value of quantity received must be less than or equal to quantity
     * ordered</li>
     * </ul>
     * 
     * @param poi
     *            The purchase order item being evaluated.
     * @throws VendorPurchasesApiException
     */
    protected void validatePurchaseOrderItem(PurchaseOrderItemDto poi) throws VendorPurchasesApiException {
        PurchaseOrder po = null;
        try {
            if (poi == null) {
                this.msg = "Purchase order object is invalid";
                logger.error(this.msg);
                throw new PurchaseOrderItemValidationException(this.msg);
            }
            // Purchase order id must be greater than zero for existing PO items
            if (poi.getPoId() < 0) {
                this.msg = "Purchase order id must be greater than zero for existing PO items";
                logger.error(this.msg);
                throw new PurchaseOrderItemValidationException(this.msg);
            }
            // Purchase order id must be exist in the system
            po = (PurchaseOrder) this.getPurchaseOrder(poi.getPoId());
            if (po == null) {
                this.msg = "Purchase order must be exist in the system";
                logger.error(this.msg);
                throw new PurchaseOrderItemValidationException(this.msg);
            }
            // Item master id must be valid and greater than zero.
            if (poi.getItemId() <= 0) {
                this.msg = "Item master id for purchase order must be valid and greater than zero";
                logger.error(this.msg);
                throw new PurchaseOrderItemValidationException(this.msg);
            }
            // Get Item Master item.
            InventoryApiFactory f = new InventoryApiFactory();
            InventoryApi invApi = f.createApi(getSharedDao());
            ItemMasterDto im = null;
            ItemMasterTypeDto imt = null;
            im = invApi.getItemById(poi.getItemId());
            if (im == null) {
                this.msg = "Item master record for item, " + poi.getItemId();
                logger.error(this.msg);
                throw new PurchaseOrderItemValidationException(this.msg);
            }

            // Item must be a tangible resalable inventory item.
            imt = invApi.getItemTypeById(InventoryConst.ITEM_TYPE_MERCH);
            if (im.getItemTypeId() != imt.getItemTypeId()) {
                this.msg = "Item must be a tangible resalable inventory item";
                logger.error(this.msg);
                throw new PurchaseOrderItemValidationException(this.msg);
            }

            // Qty ordered must be >= to zero.
            if (poi.getQtyOrdered() < 0) {
                this.msg = "Qty ordered must be greater than or equal to zero";
                logger.error(this.msg);
                throw new PurchaseOrderItemValidationException(this.msg);
            }

            // Qty received must be <= Qty ordered
            if (poi.getQtyRcvd() > poi.getQtyOrdered()) {
                this.msg = "Qty received must be less than or equal to qty ordered";
                logger.error(this.msg);
                throw new PurchaseOrderItemValidationException(this.msg);
            }
        } catch (Exception e) {
            throw new PurchaseOrderItemValidationException(e);
        }
    }

    /**
     * Calculates the net order quantity of a purchase order item. The net order
     * quantity is basically the order quantity of a purchase order item
     * that is available to be applied to inventory based on the current state of a
     * purchase order item's order quantity, quantity received, and quantity
     * returned. 
     * <p>
     * <u><b>Legend</b></u><br>
     * <ol>
     *    <li>order quantity - The initial quantity of merchandise a warehouse has ordered.</li>
     *    <li>quantity received - The quantity of merchandise items the warehouse received.</li>
     *    <li>quantity returned - the quantity amount of merchandise items the warehouse returned.</li>
     *    <li></li>
     *    <li></li>
     * </ol>
     * The formula for calculating the net order quantity is:
     * <p>
     * Beginning order quantity - (beginning order quantity - quantity received) - quantity returned.
     * 
     * @param poItem
     * @return The net order quantity
     */
    protected int calculateItemNetOrderQty(PurchaseOrderItemDto poItem) {
        // TODO: Since I changed begOrdQty to obtain its value from qtyOrdered instead 
        //       of vendorQtyOnHand. verify this against the database view to see what 
        //       column "QtyOrdered" actually points to in regards to DB view, 
        //       Vw_Vendor_Item_Purchase_Order_Item. 
//        int begOrdQty = poItem.getVendorQtyOnHand();
        int begOrdQty = poItem.getQtyOrdered();
        int returnQty = poItem.getQtyRtn();
        int recvQty = poItem.getQtyRcvd();
        int remainOrdQty = 0;
        int adjOrdQty = 0;
        int netOrdQty = 0;

        remainOrdQty = begOrdQty - recvQty;
        adjOrdQty = begOrdQty - remainOrdQty;
        netOrdQty = adjOrdQty - returnQty;

        return netOrdQty;
    }

    /**
     * Pulls inventory for each purchase order item based on the requested item
     * quantity return amount.
     * 
     * @param items
     *            List of purchase order items accompanied with a quantity
     *            return value.
     * @return 0 when all items of the purchase order have been returned. >= 1
     *         when an order quantity exists for one or more items after the
     *         returns are applied.
     * @throws VendorPurchasesApiException
     *             When return quantity exceeds the order quantity, an item
     *             master error occurs, a database error occurs, or a system
     *             error occurrs.
     */
    private int pullPurchaseOrderInventory(List<PurchaseOrderItemDto> items) throws VendorPurchasesApiException {
        int availQty = 0;
        int qtyRtn = 0;
        int totalOrderQty = 0;
        PurchaseOrderItemDto oldPoi = null;
        StringBuffer buf = new StringBuffer();
        try {
            // Apply all items belonging to the base purchase order.
            for (PurchaseOrderItemDto deltaPoi : items) {
                oldPoi = this.getPurchaseOrderItem(deltaPoi.getItemId());
                availQty = this.calculateItemNetOrderQty(oldPoi);
                qtyRtn = deltaPoi.getQtyRtn();
                if (availQty < qtyRtn) {
                    buf.append("Return Quantity (");
                    buf.append(qtyRtn);
                    buf.append(") cannot exceed Available Quantity(");
                    buf.append(availQty);
                    buf.append(") for purchase order");
                    this.msg = buf.toString();
                    logger.error(this.msg);
                    throw new VendorPurchasesApiException(this.msg);
                }

                oldPoi.setQtyRtn(oldPoi.getQtyRtn() + qtyRtn);
                this.dao.maintainPurchaseOrderItem(oldPoi);

                // Keep track of the order quantity for each item after thr
                // return quantity is applied.
                totalOrderQty += this.calculateItemNetOrderQty(oldPoi);

                // Pull item from inventory based on the amount request
                // (qtyRtn).
                try {
                    InventoryApiFactory f = new InventoryApiFactory();
                    InventoryApi invApi = f.createApi(getSharedDao());
                    invApi.pullInventory(oldPoi.getItemId(), qtyRtn);
                } catch (InventoryApiException e) {
                    throw new VendorPurchasesApiException(e);
                }
            } // end for
            return totalOrderQty;
        } catch (Exception e) {
            throw new VendorPurchasesApiException(e);
        }
    }

    /**
     * Creates a purchases, returns, and allowances transaction for a purchase
     * order.
     * <p>
     * This method executes a purchases, returns, and allowances transaction
     * which will allow one or more items of the purchase order to be returned.
     * It is required for the purchase order to be in "Received" or "Partially
     * Received" status before a return is performed. Inventory will be pulled
     * for each item involved in this transaction. The purchase order will be
     * flagged with a status of "Returned" when the summation of quantity
     * ordered minus quantity returned for each item of the purchase order
     * equals zero. Otherwise, the purchase order will be placed in Partially
     * Returned status.
     * 
     * @param poId
     *            The id of target purchase order
     * @param items
     *            The items that are to be returned.
     * @throws VendorPurchasesApiException
     *             When the return quantity exceeds the quantity available for
     *             an item, database error occurs, or a system error occurs.
     */
    @Override
    public void returnPurchaseOrder(Integer poId, List<PurchaseOrderItemDto> items) throws VendorPurchasesApiException {
        this.validatePurchaseOrderId(poId);
        this.validatePurchaseOrderItems(items);
        
        PurchaseOrderDto po = this.getPurchaseOrder(poId);
        XactDto xact = null;
        int rc = 0;

        // Check to see if it is okay to cancel this PO. An exception will be
        // thrown if chech fails!
        this.verifyStatusChange(poId, VendorPurchasesConst.PURCH_STATUS_RETURN);

        // At this point it is evident that we have a transaction to reverse
        // since this.verifyStatusChange did not bomb!
        try {
            xact = this.getXactById(po.getXactId());
            this.reverse(xact, null);
            // Associate transaction with creditor activity
            this.createSubsidiaryActivity(po.getCreditorId(), xact.getXactId(),
                    xact.getXactAmount());
        } catch (XactApiException e) {
            throw new VendorPurchasesApiException(e);
        }

        // Pull applicable items from inventory
        rc = this.pullPurchaseOrderInventory(items);

        // Flagg Purchase Order as either Returned or Partially Returned
        if (rc >= 1) {
            this.setPurchaseOrderStatus(poId,
                    VendorPurchasesConst.PURCH_STATUS_PARTRET);
        }
        else {
            this.setPurchaseOrderStatus(poId,
                    VendorPurchasesConst.PURCH_STATUS_RETURN);
        }

    }

    /**
     * Obtains the total amount of a purchase order which may include 1)
     * purchase order item total, 2) taxes, and 3) other fees.
     * 
     * @param poId
     *            The Id of the target purchase order
     * @return Total amount
     * @throws VendorPurchasesApiException
     */
    @Override
    public double calcPurchaseOrderTotal(Integer poId) throws VendorPurchasesApiException {
        this.validatePurchaseOrderId(poId);
        
        double total = 0;

        total += this.calculateItemTotal(poId);
        total += this.calculateTaxes(poId);
        total += this.calculateOtherFees(poId);
        return total;
    }

    /**
     * Calculates the purchase order item total which is Qty * Unit Cost.
     * 
     * @param poId
     *            The id of the target purchase order
     * @return Item Total
     * @throws VendorPurchasesApiException
     */
    private double calculateItemTotal(int poId) throws VendorPurchasesApiException {
        List<PurchaseOrderItemDto> items = null;
        double total = 0;

        items = this.getPurchaseOrderItems(poId);
        if (items == null) {
            return 0;
        }
        // Cycle through all items summing each as (qty * unit_price)
        for (PurchaseOrderItemDto poi : items) {
            total += poi.getVendorUnitCost() * poi.getQtyOrdered();
        }
        return total;
    }

    /**
     * Calculates the purchase order taxes.
     * 
     * @param poId
     *            The id of the target purchase order
     * @return Purchase order tax amount.
     * @throws VendorPurchasesApiException
     */
    private double calculateTaxes(int poId) throws VendorPurchasesApiException {
        return 0;
    }

    /**
     * Calculate other fees that may be applicable to the purchase order.
     * 
     * @param poId
     *            The id of the target purchase order
     * @return Other fee total.
     * @throws VendorPurchasesApiException
     */
    private double calculateOtherFees(int poId) throws VendorPurchasesApiException {
        return 0;
    }

    /**
     * Creates a purchase order transaction.
     * <p>
     * This method begins the process of realizing the purchase order as a
     * transaction. Firstly, a purchase order transaction is created, secondly,
     * the creditor is realizes the transaction, thirdly, the pusrchase order
     * itself is associated with the transaction, and lastly, the status is
     * changed to "Submitted". The completion of the previous tasks are pending
     * on the fact that the status change is truly legal base on current
     * business rules. Creates a purchase order transaction.
     * 
     * 
     * @param po
     *            The purchase order
     * @param items
     *            Purchase order items
     * @return Transaction Id.
     * @throws VendorPurchasesApiException
     */
    @Override
    public int submitPurchaseOrder(PurchaseOrderDto po, List<PurchaseOrderItemDto> items)
            throws VendorPurchasesApiException {
        this.validatePurchaseOrder(po);
        this.validatePurchaseOrderItems(items);
        
        double poTotal = 0;
        int xactId = 0;
        XactDto xact = null;

        this.verifyStatusChange(po.getPoId(), VendorPurchasesConst.PURCH_STATUS_FINALIZE);
        this.updatePurchaseOrder(po, items);
        poTotal = this.calcPurchaseOrderTotal(po.getPoId());
        this.vendorId = po.getCreditorId();
        try {
            // Setup Transaction
            xact = Rmt2XactDtoFactory.createXactInstance((Xact) null);
            xact.setXactAmount(poTotal);
            xact.setXactDate(new Date());
            xact.setXactTypeId(XactConst.XACT_TYPE_VENDOR_PURCHASE);
            xact.setXactReason("Submitted Inventory Purchase Order " + po.getPoId());
            xactId = this.update(xact, null);

            // Associate transaction with creditor
            this.createSubsidiaryActivity(po.getCreditorId(), xactId, poTotal);

            // Associate transaction with PO
            po.setXactId(xactId);
            po.setTotal(poTotal);
            this.dao.maintainPurchaseOrder(po);

            // Change purchase order status to finalized
            this.setPurchaseOrderStatus(po.getPoId(), VendorPurchasesConst.PURCH_STATUS_FINALIZE);

            return xactId;
        } catch (Exception e) {
            throw new VendorPurchasesApiException(e);
        }
    }

    /**
     * Cancels a purchase order.
     * <p>
     * This will cancel purchase orders. The purchase order is required to be in
     * "Quote" or "Submitted" status prior to cancellation, and cannot undergo a
     * partial cancellation. Inventory will not be pulled as a result of this
     * transaction.
     * 
     * @param poId
     *            The id of target purchase order
     * @throws VendorPurchasesApiException
     *             If current status cannot be cancelled
     */
    @Override
    public void cancelPurchaseOrder(Integer poId) throws VendorPurchasesApiException {
        this.validatePurchaseOrderId(poId);
        
        PurchaseOrderDto po = this.getPurchaseOrder(poId);
        XactDto xact = null;
        int currentStatus = 0;

        // Check to see if it is okay to cancel this PO. An exception will be
        // thrown if chech fails!
        currentStatus = this.verifyStatusChange(poId, VendorPurchasesConst.PURCH_STATUS_CANCEL);

        // Reverse transaction if purchase order has been submitted and a
        // transaction has been assigned.
        if (currentStatus == VendorPurchasesConst.PURCH_STATUS_FINALIZE && po.getXactId() > 0) {
            try {
                xact = this.getXactById(po.getXactId());
                this.reverse(xact, null);
                this.createSubsidiaryActivity(po.getCreditorId(), xact.getXactId(), xact.getXactAmount());
            } catch (XactApiException e) {
                throw new VendorPurchasesApiException(e);
            }
        }

        // Cancel Purchase Order by changing the status.
        this.setPurchaseOrderStatus(poId, VendorPurchasesConst.PURCH_STATUS_CANCEL);
    }

    /**
     * This method will perform a purchases, returns, and allowances transaction
     * for an existing purchase order identified as, purchaseOrderId.
     * 
     * @param purchaseOrderId
     *            The id of a valid purchase order.
     * @throws VendorPurchasesApiException
     */
    @Override
    public void doVendorPurchaseReturnAllow(Integer purchaseOrderId) throws VendorPurchasesApiException {
        return;
    }

    /**
     * Masks the vendor's external account number details except the last four
     * digits and assigns the results to the xact instance.
     */
    @Override
    protected void preCreateXact(XactDto xact) {
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi credApi = f.createCreditorApi(this.getSharedDao());
        try {
            CreditorDto creditor = credApi.getByCreditorId(this.vendorId);
            if (creditor == null) {
                this.msg = "Unable to create vendor purchase order transction due to vendor's profile is not found in the database using creditor id: "
                        + this.vendorId;
                logger.error(this.msg);
                throw new NotFoundException();
            }
            String ccNoMask = RMT2String.maskCreditCardNumber(creditor.getExtAccountNumber());
            xact.setXactNegInstrNo(ccNoMask);
        } catch (CreditorApiException e) {
            this.msg = "Unable to create vendor purchase order transction due to the occurrence of a database error while attempting to fetch vendor's profile from the database using creditor id: "
                    + this.vendorId;
            logger.error(this.msg);
            throw new DatabaseException(this.msg, e);
        } finally {
            credApi = null;
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.transaction.AbstractXactApiImpl#preReverse(org.dto.XactDto,
     * java.util.List)
     */
    @Override
    protected void preReverse(XactDto xact,
            List<XactTypeItemActivityDto> xactItems) {
        // TODO Auto-generated method stub
        super.preReverse(xact, xactItems);
    }

    private void validatePurchaseOrderId(Integer poId) {
        try {
            Verifier.verifyNotNull(poId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Purchase Order Id is required");
        }
        try {
            Verifier.verifyPositive(poId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Purchase Order Id must be greater than zero");
        }
    }
    
    private void validateVendorId(Integer vendorId) {
        try {
            Verifier.verifyNotNull(vendorId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Vendor Id is required");
        }
        try {
            Verifier.verifyPositive(vendorId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Vendor Id must be greater than zero");
        }
    }
    
    private void validatePurchaseOrderItemId(Integer poItemId) {
        try {
            Verifier.verifyNotNull(poItemId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Purchase Order Item Id is required");
        }
        try {
            Verifier.verifyPositive(poItemId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Purchase Order Item Id must be greater than zero");
        }
    }
    
    private void validatePurchaseOrderStatusId(Integer poStatusId) {
        try {
            Verifier.verifyNotNull(poStatusId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Purchase Order Status Id is required");
        }
        try {
            Verifier.verifyPositive(poStatusId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Purchase Order Status Id must be greater than zero");
        }
    }
    
    private void validateItemMasterId(Integer itemMasterId) {
        try {
            Verifier.verifyNotNull(itemMasterId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Item Master Id is required");
        }
        try {
            Verifier.verifyPositive(itemMasterId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Item Master Id must be greater than zero");
        }
    }
    
    private void validatePurchaseOrderItems(List<PurchaseOrderItemDto> items) {
        try {
            Verifier.verifyNotNull(items);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("List of purchase order items is required");
        }
        
        try {
            Verifier.verifyPositive(items.size());    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("List of purchase order items must contain at least one item");
        }
    }

    
}
