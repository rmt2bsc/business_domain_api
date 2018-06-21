package org.modules.inventory;

import java.util.List;

import org.apache.log4j.Logger;
import org.dao.AccountingSqlConst;
import org.dao.inventory.InventoryDao;
import org.dao.inventory.InventoryDaoException;
import org.dao.inventory.InventoryDaoFactory;
import org.dao.mapping.orm.rmt2.ItemMaster;
import org.dao.mapping.orm.rmt2.ItemMasterStatus;
import org.dao.mapping.orm.rmt2.ItemMasterStatusHist;
import org.dao.mapping.orm.rmt2.ItemMasterType;
import org.dao.mapping.orm.rmt2.VwItemAssociations;
import org.dao.mapping.orm.rmt2.VwVendorItems;
import org.dto.ItemAssociationDto;
import org.dto.ItemMasterDto;
import org.dto.ItemMasterStatusDto;
import org.dto.ItemMasterStatusHistDto;
import org.dto.ItemMasterTypeDto;
import org.dto.VendorItemDto;
import org.dto.adapter.orm.inventory.Rmt2InventoryDtoFactory;
import org.modules.subsidiary.CreditorApi;
import org.modules.subsidiary.CreditorApiException;
import org.modules.subsidiary.SubsidiaryApiFactory;

import com.InvalidDataException;
import com.RMT2Base;
import com.RMT2Exception;
import com.api.foundation.AbstractTransactionApiImpl;
import com.api.persistence.DaoClient;
import com.api.util.RMT2String;
import com.api.util.RMT2String2;
import com.api.util.assistants.Verifier;
import com.api.util.assistants.VerifyException;

/**
 * Implements the {@link InventoryApi} interface for maintaining inventory items.
 * 
 * @author roy terrell
 * 
 */
class InventoryApiImpl extends AbstractTransactionApiImpl implements InventoryApi {

    private static final Logger logger = Logger.getLogger(InventoryApiImpl.class);

    private InventoryDaoFactory factory;

    private InventoryDao dao;

    /**
     * Creates a InventoryApiImpl object assoicated with the default
     * InventoryDaoFactory object.
     */
    public InventoryApiImpl() {
        super();
        this.dao = this.factory.createRmt2OrmDao();
        this.setSharedDao(this.dao);
    }

    /**
     * Creates a InventoryApiImpl object assoicated with the default
     * InventoryDaoFactory object.
     * 
     * @param appName
     */
    public InventoryApiImpl(String appName) {
        super();
        this.dao = this.factory.createRmt2OrmDao(appName);
        this.setSharedDao(this.dao);
    }

    /**
     * Creates an InventoryApiImpl initialized with a shared connection,
     * <i>dao</i>. object.
     * 
     * @param connection
     */
    protected InventoryApiImpl(DaoClient connection) {
        super(connection);
        this.dao = this.factory.createRmt2OrmDao(this.getSharedDao());
        this.dao.setDaoUser(connection.getDaoUser());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.RMT2Base#init()
     */
    @Override
    public void init() {
        super.init();
        this.factory = new InventoryDaoFactory();
    }

    @Override
    public List<ItemMasterDto> getItem(ItemMasterDto criteria) throws InventoryApiException {
        try {
            Verifier.verifyNotNull(criteria);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Criteria object is required", e);
        }
        dao.setDaoUser(this.apiUser);
        List<ItemMasterDto> results;
        try {
            results = dao.fetch(criteria);
            return results;
        } catch (Exception e) {
            this.msg = "Error querying inventory item(s)";
            throw new InventoryApiException(this.msg, e);
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.modules.inventory.InventoryApi#getItemById(int)
     */
    @Override
    public ItemMasterDto getItemById(Integer itemId) throws InventoryApiException {
        try {
            Verifier.verifyNotNull(itemId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Inventory item id is required", e);
        }
        try {
            Verifier.verifyPositive(itemId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Inventory item must be greater than zero", e);
        }
        dao.setDaoUser(this.apiUser);
        List<ItemMasterDto> results;
        StringBuffer msgBuf = new StringBuffer();
        try {
            ItemMaster im = null;
            ItemMasterDto criteria = Rmt2InventoryDtoFactory.createItemMasterInstance(im);
            criteria.setItemId(itemId);
            results = this.getItem(criteria);
        } catch (Exception e) {
            this.msg = "Unable to retrieve inventory item by item id: " + itemId;
            throw new InventoryApiException(this.msg, e);
        }

        if (results == null) {
            msgBuf.append("Inventory item, ");
            msgBuf.append(itemId);
            msgBuf.append(", was not found ");
            logger.warn(msgBuf);
            return null;
        }
        if (results.size() > 1) {
            msgBuf.append("Too many inventory items returned for item id, ");
            msgBuf.append(itemId);
            msgBuf.append(" Count: ");
            msgBuf.append(results.size());
            logger.error(msgBuf);
            throw new InventoryApiException(msgBuf.toString());
        }
        msgBuf.append("Inventory object was retrieved for item id, ");
        msgBuf.append(itemId);
        logger.info(msgBuf);
        return results.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.inventory.InventoryApi#getItemByType(int)
     */
    @Override
    public List<ItemMasterDto> getItemByType(Integer itemTypeId)
            throws InventoryApiException {
        try {
            Verifier.verifyNotNull(itemTypeId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Inventory item type id is required", e);
        }
        try {
            Verifier.verifyPositive(itemTypeId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Inventory item type id must be greater than zero", e);
        }

        dao.setDaoUser(this.apiUser);
        List<ItemMasterDto> results;
        StringBuffer msgBuf = new StringBuffer();
        try {
            ItemMaster im = null;
            ItemMasterDto criteria = Rmt2InventoryDtoFactory
                    .createItemMasterInstance(im);
            criteria.setItemTypeId(itemTypeId);
            results = this.getItem(criteria);
        } catch (Exception e) {
            this.msg = "Unable to retrieve inventory item(s) by item type id: "
                    + itemTypeId;
            logger.error(this.msg, e);
            throw new InventoryApiException(e);
        }
  
        if (results == null) {
            msgBuf.append("Inventory items were not found by item type id, ");
            msgBuf.append(itemTypeId);
            logger.warn(msgBuf);
            return null;
        }
        msgBuf.append(results.size());
        msgBuf.append(" Inventory item object(s) were retrieved by item type id, ");
        msgBuf.append(itemTypeId);
        logger.info(msgBuf);
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.inventory.InventoryApi#getItemByVendorId(int)
     */
    @Override
    public List<ItemMasterDto> getItemByVendorId(Integer vendorId)
            throws InventoryApiException {
        try {
            Verifier.verifyNotNull(vendorId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Inventory Vendor id is required", e);
        }
        try {
            Verifier.verifyPositive(vendorId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Vendor Id is required and must be greater than zero", e);
        }

        dao.setDaoUser(this.apiUser);
        List<ItemMasterDto> results;
        StringBuffer msgBuf = new StringBuffer();
        try {
            ItemMaster im = null;
            ItemMasterDto criteria = Rmt2InventoryDtoFactory
                    .createItemMasterInstance(im);
            criteria.setVendorId(vendorId);
            results = this.getItem(criteria);
        } catch (Exception e) {
            this.msg = "Unable to retrieve inventory item(s) by vendor id: "
                    + vendorId;
            logger.error(this.msg, e);
            throw new InventoryApiException(e);
        }

        if (results == null) {
            msgBuf.append("Inventory items were not found by vendor id, ");
            msgBuf.append(vendorId);
            logger.warn(msgBuf);
            return null;
        }
        msgBuf.append(results.size());
        msgBuf.append(" Inventory item object(s) were retrieved by vendor id, ");
        msgBuf.append(vendorId);
        logger.info(msgBuf);
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.inventory.InventoryApi#getItemByVendorItemNo(java.lang.String
     * )
     */
    @Override
    public List<ItemMasterDto> getItemByVendorItemNo(String vendItemNo)
            throws InventoryApiException {
        if (RMT2String2.isEmpty(vendItemNo)) {
            throw new InvalidDataException("Vendor Item No. is required");
        }
        dao.setDaoUser(this.apiUser);
        List<ItemMasterDto> results;
        StringBuffer msgBuf = new StringBuffer();
        try {
            ItemMaster im = null;
            ItemMasterDto criteria = Rmt2InventoryDtoFactory
                    .createItemMasterInstance(im);
            criteria.setVendorItemNo(vendItemNo);
            results = this.getItem(criteria);
        } catch (Exception e) {
            this.msg = "Unable to retrieve inventory item(s) by vendor item number: "
                    + vendItemNo;
            logger.error(this.msg, e);
            throw new InventoryApiException(e);
        }

        if (results == null) {
            msgBuf.append("Inventory items were not found by vendor item number, ");
            msgBuf.append(vendItemNo);
            logger.warn(msgBuf);
            return null;
        }
        msgBuf.append(results.size());
        msgBuf.append(" Inventory item object(s) were retrieved by vendor item number, ");
        msgBuf.append(vendItemNo);
        logger.info(msgBuf);
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.inventory.InventoryApi#getItemBySerialNo(java.lang.String)
     */
    @Override
    public List<ItemMasterDto> getItemBySerialNo(String serialNo)
            throws InventoryApiException {
        if (RMT2String2.isEmpty(serialNo)) {
            throw new InvalidDataException("Item Serial No. is required");
        }
        dao.setDaoUser(this.apiUser);
        List<ItemMasterDto> results;
        StringBuffer msgBuf = new StringBuffer();
        try {
            ItemMaster im = null;
            ItemMasterDto criteria = Rmt2InventoryDtoFactory
                    .createItemMasterInstance(im);
            criteria.setItemSerialNo(serialNo);
            results = this.getItem(criteria);
        } catch (Exception e) {
            this.msg = "Unable to retrieve inventory item(s) by vendor item number: "
                    + serialNo;
            logger.error(this.msg, e);
            throw new InventoryApiException(e);
        }

        if (results == null) {
            msgBuf.append("Inventory items were not found by serial number, ");
            msgBuf.append(serialNo);
            logger.warn(msgBuf);
            return null;
        }
        msgBuf.append(results.size());
        msgBuf.append(" Inventory item object(s) were retrieved by serial number, ");
        msgBuf.append(serialNo);
        logger.info(msgBuf);
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.inventory.InventoryApi#getItem(java.lang.String)
     */
    @Override
    public List<ItemMasterDto> getItem(String criteria) throws InventoryApiException {
        dao.setDaoUser(this.apiUser);
        List<ItemMasterDto> results;
        StringBuffer msgBuf = new StringBuffer();
        try {
            results = dao.fetch(criteria);
        } catch (Exception e) {
            this.msg = "Unable to retrieve inventory item(s) using customer SQL predicate: "
                    + criteria;
            logger.error(this.msg, e);
            throw new InventoryApiException(e);
        }

        if (results == null) {
            msgBuf.append("Inventory items were not found using customer SQL predicate, ");
            msgBuf.append(criteria);
            logger.warn(msgBuf);
            return null;
        }
        msgBuf.append(results.size());
        msgBuf.append(" Inventory item object(s) were retrieved using customer SQL predicate, ");
        msgBuf.append(criteria);
        logger.info(msgBuf);
        return results;
    }

    
    @Override
    public List<ItemMasterTypeDto> getItemType(ItemMasterTypeDto criteria)
            throws InventoryApiException {
        dao.setDaoUser(this.apiUser);
        List<ItemMasterTypeDto> results;
        try {
            results = dao.fetch(criteria);
            return results;
        } catch (Exception e) {
            this.msg = "Error querying inventory item type(s)";
            logger.error(this.msg, e);
            throw new InventoryApiException(e);
        }
    }

    
    
    /*
     * (non-Javadoc)
     * 
     * @see org.modules.inventory.InventoryApi#getItemTypeById(int)
     */
    @Override
    public ItemMasterTypeDto getItemTypeById(Integer itemTypeId) throws InventoryApiException {
        try {
            Verifier.verifyNotNull(itemTypeId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Inventory Item type Id is required", e);
        }
        try {
            Verifier.verifyPositive(itemTypeId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Item Type Id is required and must be greater than zero", e);
        }

        dao.setDaoUser(this.apiUser);
        List<ItemMasterTypeDto> results;
        StringBuffer msgBuf = new StringBuffer();
        try {
            ItemMasterType imt = null;
            ItemMasterTypeDto criteria = Rmt2InventoryDtoFactory
                    .createItemTypeInstance(imt);
            criteria.setItemTypeId(itemTypeId);
            results = this.getItemType(criteria);
        } catch (Exception e) {
            this.msg = "Unable to retrieve inventory item type by item type id: "
                    + itemTypeId;
            logger.error(this.msg, e);
            throw new InventoryApiException(e);
        }
 
        if (results == null) {
            msgBuf.append("Inventory item type, ");
            msgBuf.append(itemTypeId);
            msgBuf.append(", was not found ");
            logger.warn(msgBuf);
            return null;
        }
        if (results.size() > 1) {
            msgBuf.append("Too many inventory items returned for item type id, ");
            msgBuf.append(itemTypeId);
            msgBuf.append(" Count: ");
            msgBuf.append(results.size());
            logger.error(msgBuf);
            throw new InventoryApiException(msgBuf.toString());
        }
        msgBuf.append("Inventory item type object was retrieved for item type id, ");
        msgBuf.append(itemTypeId);
        logger.info(msgBuf);
        return results.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.inventory.InventoryApi#getItemTypes(java.lang.String)
     */
    @Override
    public List<ItemMasterTypeDto> getItemTypes(String itemName) throws InventoryApiException {
        if (RMT2String2.isEmpty(itemName)) {
            throw new InvalidDataException("Item Type Name is required");
        }
        dao.setDaoUser(this.apiUser);
        List<ItemMasterTypeDto> results;
        StringBuffer msgBuf = new StringBuffer();
        try {
            ItemMasterType imt = null;
            ItemMasterTypeDto criteria = Rmt2InventoryDtoFactory
                    .createItemTypeInstance(imt);
            criteria.setItemTypeDescription(itemName);
            results = this.getItemType(criteria);
        } catch (Exception e) {
            this.msg = "Unable to retrieve inventory item type(s) by item name: "
                    + itemName;
            logger.error(this.msg, e);
            throw new InventoryApiException(e);
        }
 
        if (results == null) {
            msgBuf.append("Inventory item types were not found by item name, ");
            msgBuf.append(itemName);
            logger.warn(msgBuf);
            return null;
        }
        msgBuf.append(results.size());
        msgBuf.append(" Inventory item type object(s) were retrieved by item name, ");
        msgBuf.append(itemName);
        logger.info(msgBuf);
        return results;
    }

    @Override
    public List<ItemMasterStatusDto> getItemStatus(ItemMasterStatusDto criteria)
            throws InventoryApiException {
        dao.setDaoUser(this.apiUser);
        List<ItemMasterStatusDto> results;
        try {
            results = dao.fetch(criteria);
            return results;
        } catch (Exception e) {
            this.msg = "Error querying inventory item status(es)";
            logger.error(this.msg, e);
            throw new InventoryApiException(e);
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.modules.inventory.InventoryApi#getItemStatus(java.lang.String)
     */
    @Override
    public List<ItemMasterStatusDto> getItemStatus(String statusName) throws InventoryApiException {
        if (RMT2String2.isEmpty(statusName)) {
            throw new InvalidDataException("Item Status Name is required");
        }
        dao.setDaoUser(this.apiUser);
        List<ItemMasterStatusDto> results;
        StringBuffer msgBuf = new StringBuffer();
        try {
            ItemMasterStatus imt = null;
            ItemMasterStatusDto criteria = Rmt2InventoryDtoFactory
                    .createItemStatusInstance(imt);
            criteria.setEntityName(statusName);
            results = this.getItemStatus(criteria);
        } catch (Exception e) {
            this.msg = "Unable to retrieve inventory item status(es) by status name: "
                    + statusName;
            logger.error(this.msg, e);
            throw new InventoryApiException(e);
        }

        if (results == null) {
            msgBuf.append("Inventory item status were not found by item name, ");
            msgBuf.append(statusName);
            logger.warn(msgBuf);
            return null;
        }
        msgBuf.append(results.size());
        msgBuf.append(" Inventory item status object(s) were retrieved by item name, ");
        msgBuf.append(statusName);
        logger.info(msgBuf);
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.inventory.InventoryApi#getItemStatusById(int)
     */
    @Override
    public ItemMasterStatusDto getItemStatusById(Integer itemStatusId) throws InventoryApiException {
        try {
            Verifier.verifyNotNull(itemStatusId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Inventory Item Status Id is required", e);
        }
        try {
            Verifier.verifyPositive(itemStatusId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Item Status Id is required and must be greater than zero", e);
        }

        dao.setDaoUser(this.apiUser);
        List<ItemMasterStatusDto> results;
        StringBuffer msgBuf = new StringBuffer();
        try {
            ItemMasterStatus imt = null;
            ItemMasterStatusDto criteria = Rmt2InventoryDtoFactory
                    .createItemStatusInstance(imt);
            criteria.setEntityId(itemStatusId);
            results = this.getItemStatus(criteria);
        } catch (Exception e) {
            this.msg = "Unable to retrieve inventory item status by item status id: "
                    + itemStatusId;
            logger.error(this.msg, e);
            throw new InventoryApiException(e);
        }

        if (results == null) {
            msgBuf.append("Inventory item status, ");
            msgBuf.append(itemStatusId);
            msgBuf.append(", was not found ");
            logger.warn(msgBuf);
            return null;
        }
        if (results.size() > 1) {
            msgBuf.append("Too many inventory item status returned for item status id, ");
            msgBuf.append(itemStatusId);
            msgBuf.append(" Count: ");
            msgBuf.append(results.size());
            logger.error(msgBuf);
            throw new InventoryApiException(msgBuf.toString());
        }
        msgBuf.append("Inventory item status object was retrieved for item status id, ");
        msgBuf.append(itemStatusId);
        logger.info(msgBuf);
        return results.get(0);
    }

    @Override
    public List<ItemMasterStatusHistDto> getItemStatusHist(ItemMasterStatusHistDto criteria) 
            throws InventoryApiException {
        if (criteria == null) {
            throw new InvalidDataException("Criteria object is required");
        }
        dao.setDaoUser(this.apiUser);
        List<ItemMasterStatusHistDto> results;
        try {
            results = dao.fetch(criteria);
            return results;
        } catch (Exception e) {
            this.msg = "Error querying inventory item status history";
            logger.error(this.msg, e);
            throw new InventoryApiException(e);
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.modules.inventory.InventoryApi#getItemStatusHistByItemId(int)
     */
    @Override
    public List<ItemMasterStatusHistDto> getItemStatusHistByItemId(Integer itemId)
            throws InventoryApiException {
        try {
            Verifier.verifyNotNull(itemId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Inventory Item Id is required", e);
        }
        try {
            Verifier.verifyPositive(itemId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Item Id is required and must be greater than zero", e);
        }

        dao.setDaoUser(this.apiUser);
        List<ItemMasterStatusHistDto> results;
        StringBuffer msgBuf = new StringBuffer();
        try {
            ItemMasterStatusHist imt = null;
            ItemMasterStatusHistDto criteria = Rmt2InventoryDtoFactory
                    .createItemStatusHistoryInstance(imt);
            criteria.setItemId(itemId);
            results = this.getItemStatusHist(criteria);
        } catch (Exception e) {
            this.msg = "Unable to retrieve inventory item status history by item id: "
                    + itemId;
            logger.error(this.msg, e);
            throw new InventoryApiException(e);
        }

        if (results == null) {
            msgBuf.append("Inventory item status history was not found by item id, ");
            msgBuf.append(itemId);
            logger.warn(msgBuf);
            return null;
        }
        msgBuf.append(results.size());
        msgBuf.append(" Inventory item status history object(s) were retrieved by item id, ");
        msgBuf.append(itemId);
        logger.info(msgBuf);
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.inventory.InventoryApi#getCurrentItemStatusHist(int)
     */
    @Override
    public ItemMasterStatusHistDto getCurrentItemStatusHist(Integer itemId)
            throws InventoryApiException {
        try {
            Verifier.verifyNotNull(itemId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Item Id is required", e);
        }
        if (itemId <= 0) {
            throw new InvalidDataException("Item Id must be greater than zero");
        }
        dao.setDaoUser(this.apiUser);
        List<ItemMasterStatusHistDto> results;
        StringBuffer msgBuf = new StringBuffer();
        try {
            ItemMasterStatusHist imt = null;
            ItemMasterStatusHistDto criteria = Rmt2InventoryDtoFactory
                    .createItemStatusHistoryInstance(imt);
            criteria.setItemId(itemId);
            results = this.getItemStatusHist(criteria);
        } catch (Exception e) {
            this.msg = "Unable to retrieve current inventory item status history by item id: "
                    + itemId;
            logger.error(this.msg, e);
            throw new InventoryApiException(e);
        }

        if (results == null) {
            msgBuf.append("Current inventory item status history was not found by item id, ");
            msgBuf.append(itemId);
            logger.warn(msgBuf);
            return null;
        }
        msgBuf.append(results.size());
        msgBuf.append(" Current inventory item status history object(s) were retrieved by item id, ");
        msgBuf.append(itemId);
        logger.info(msgBuf);
        return results.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.inventory.InventoryApi#getVendorItem(int, int)
     */
    @Override
    public VendorItemDto getVendorItem(Integer vendorId, Integer itemId)
            throws InventoryApiException {
        try {
            Verifier.verifyNotNull(vendorId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Vendor/Creditor Id is required", e);
        }
        try {
            Verifier.verifyNotNull(itemId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Vendor Item Id is required", e);
        }
        
        dao.setDaoUser(this.apiUser);
        List<VendorItemDto> results;
        StringBuffer msgBuf = new StringBuffer();
        try {
            VwVendorItems imt = null;
            VendorItemDto criteria = Rmt2InventoryDtoFactory
                    .createVendorItemInstance(imt);
            criteria.setItemId(itemId);
            criteria.setVendorId(vendorId);
            results = dao.fetch(criteria);
        } catch (Exception e) {
            this.msg = "Unable to retrieve vendor item by item id and vendor id: "
                    + itemId + " " + vendorId;
            logger.error(this.msg, e);
            throw new InventoryApiException(e);
        }

        if (results == null) {
            msgBuf.append(" inventory vendor item was not found by item id and vendor id, ");
            msgBuf.append(itemId);
            msgBuf.append(" ");
            msgBuf.append(vendorId);
            logger.warn(msgBuf);
            return null;
        }
        msgBuf.append(results.size());
        msgBuf.append(" Vendor inventory item object(s) were retrieved by item id and vendor id, ");
        msgBuf.append(itemId);
        msgBuf.append(" ");
        msgBuf.append(vendorId);
        logger.info(msgBuf);
        return results.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.inventory.InventoryApi#getVendorAssignItems(int)
     */
    @Override
    public List<VendorItemDto> getVendorAssignItems(Integer vendorId)
            throws InventoryApiException {
        try {
            Verifier.verifyNotNull(vendorId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Vendor/Creditor Id is required to fetch assigned items", e);
        }
        dao.setDaoUser(this.apiUser);
        List<VendorItemDto> results;
        StringBuffer msgBuf = new StringBuffer();
        try {
            VwVendorItems imt = null;
            VendorItemDto criteria = Rmt2InventoryDtoFactory
                    .createVendorItemInstance(imt);
            criteria.setVendorId(vendorId);
            results = dao.fetch(criteria);
        } catch (Exception e) {
            this.msg = "Unable to retrieve vendor associated inventory items by vendor id: "
                    + vendorId;
            logger.error(this.msg, e);
            throw new InventoryApiException(e);
        }

        if (results == null) {
            msgBuf.append("Vendor associated inventory items were not found by vendor id, ");
            msgBuf.append(vendorId);
            logger.warn(msgBuf);
            return null;
        }
        msgBuf.append(results.size());
        msgBuf.append(" Vendor associated inventory item object(s) were retrieved by vendor id, ");
        msgBuf.append(vendorId);
        logger.info(msgBuf);
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.inventory.InventoryApi#getVendorUnassignItems(int)
     */
    @Override
    public List<ItemMasterDto> getVendorUnassignItems(Integer vendorId)
            throws InventoryApiException {
        try {
            Verifier.verifyNotNull(vendorId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Vendor/Creditor Id is required to fetch unassigned items", e);
        }
        String criteria = RMT2String.replace(
                AccountingSqlConst.SQL_CRTIERIA_VENDOR_UNASSIGNED_ITEM,
                String.valueOf(vendorId), "$1");
        dao.setDaoUser(this.apiUser);
        List<ItemMasterDto> results;
        StringBuffer msgBuf = new StringBuffer();

        try {
            results = dao.fetch(criteria);
        } catch (Exception e) {
            this.msg = "Unable to retrieve unassociated vendor inventory items by vendor id: "
                    + vendorId;
            logger.error(this.msg, e);
            throw new InventoryApiException(e);
        }

        if (results == null) {
            msgBuf.append("Vendor associated inventory items were not found by vendor id, ");
            msgBuf.append(vendorId);
            logger.warn(msgBuf);
            return null;
        }
        msgBuf.append(results.size());
        msgBuf.append(" Vendor associated inventory item object(s) were retrieved by vendor id, ");
        msgBuf.append(vendorId);
        logger.info(msgBuf);

        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.inventory.InventoryApi#getItemAssociations(int)
     */
    @Override
    public List<ItemAssociationDto> getItemAssociations(Integer itemId)
            throws InventoryApiException {
        try {
            Verifier.verifyNotNull(itemId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Item Id is required", e);
        }
        try {
            Verifier.verifyPositive(itemId);
        }
        catch (VerifyException e) {
            throw new InvalidDataException("A valid item id must provided in order to perform an item assoication query", e);
        }
       
        // InventoryDao dao = this.factory.createRmt2OrmDao();
        dao.setDaoUser(this.apiUser);
        List<ItemAssociationDto> results;
        StringBuffer msgBuf = new StringBuffer();
        try {
            VwItemAssociations assoc = null;
            ItemAssociationDto criteria = Rmt2InventoryDtoFactory
                    .createItemAssociationInstance(assoc);
            criteria.setItemId(itemId);
            results = dao.fetch(criteria);
        } catch (Exception e) {
            this.msg = "Unable to retrieve item associations for item id: " + itemId;
            logger.error(this.msg, e);
            throw new InventoryApiException(e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // }

        if (results == null) {
            msgBuf.append("Item associations were not found by item id, ");
            msgBuf.append(itemId);
            logger.warn(msgBuf);
            return null;
        }
        msgBuf.append(results.size());
        msgBuf.append(" Item association object(s) were retrieved by item id, ");
        msgBuf.append(itemId);
        logger.info(msgBuf);
        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.inventory.InventoryApi#updateInventoryItem(org.dto.ItemMasterDto
     * )
     */
    @Override
    public int updateItemMaster(ItemMasterDto item) throws InventoryApiException {
        try {
            Verifier.verifyNotNull(item);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Inventory item master object is required", e);
        }
        
        dao.setDaoUser(this.apiUser);
        this.computeItemRetail(item);
        boolean isNewItem = (item.getItemId() == 0);
        ItemMaster newItem = null;
        ItemMasterDto imDto = Rmt2InventoryDtoFactory.createItemMasterInstance(newItem);

        // Get old version of item record and apply changes
        if (!isNewItem) {
            imDto = this.getItemById(item.getItemId());
            if (imDto == null) {
                this.msg = "Inventory item update error: Item id, "
                        + item.getItemId() + ", does not exist in the system";
                logger.error(this.msg);
                throw new InventoryApiException(this.msg);
            }
        }

        // Capute the original override flag
        int oldOverrideRetailFlag = imDto.getOverrideRetail();

        // add delta to old inventory item master version
        imDto.setItemTypeId(item.getItemTypeId());
        imDto.setVendorId(item.getVendorId());
        imDto.setItemName(item.getItemName());
        imDto.setVendorItemNo(item.getVendorItemNo());
        imDto.setItemSerialNo(item.getItemSerialNo());
        imDto.setQtyOnHand(item.getQtyOnHand());
        imDto.setUnitCost(item.getUnitCost());
        imDto.setMarkup(item.getMarkup());
        imDto.setRetailPrice(item.getRetailPrice());
        imDto.setOverrideRetail(item.getOverrideRetail());
        imDto.setActive(item.getActive());

        // Perform updates
        int rc;
        try {
            // Update Item master
            rc = dao.maintain(imDto);

            // Update statuses
            ItemMasterStatusHistDto imsh = null;
            if (isNewItem) {
                // Indicate that item is in service
                this.changeItemStatus(imDto, InventoryConst.ITEM_STATUS_INSRVC);
            }
            else {
                // Get item master's current status
                imsh = dao.fetchCurrentItemStatusHistory(imDto.getItemId());
                if (imsh == null) {
                    this.msg = "Unable to find status history for inventory item id, "
                            + imDto.getItemId() + ".   History not available.";
                    logger.error(this.msg);
                    throw new RMT2Exception(this.msg);
                }
                // Change the most recent item status, which should be
                // 'Replaced'
                imsh = this.changeItemStatus(imDto, InventoryConst.ITEM_STATUS_REPLACE);

                // User has requested system to activate vendor item override.
                if (imDto.getOverrideRetail() == InventoryConst.ITEM_OVERRIDE_YES
                        && oldOverrideRetailFlag == InventoryConst.ITEM_OVERRIDE_NO) {
                    imsh = this.changeItemStatus(imDto,
                            InventoryConst.ITEM_STATUS_OVERRIDE_ACTIVE);
                }

                // User has requested system to deactivate vendor item override.
                if (imDto.getOverrideRetail() == InventoryConst.ITEM_OVERRIDE_NO
                        && oldOverrideRetailFlag == InventoryConst.ITEM_OVERRIDE_YES) {
                    imsh = this.changeItemStatus(imDto,
                            InventoryConst.ITEM_STATUS_OVERRIDE_INACTIVE);
                }
            }

            // Place Item in "Available" if quantity is greater than zero.
            // Otherwise, 'Out of Stock'.
            int itemStatusId = imDto.getQtyOnHand() <= 0 ? InventoryConst.ITEM_STATUS_OUTSTOCK
                    : InventoryConst.ITEM_STATUS_AVAIL;
            imsh = this.changeItemStatus(imDto, itemStatusId);

            // If item is no longer active, then put in out servive status
            if (!isNewItem) {
                if (imDto.getActive() == 0) {
                    imsh = this.changeItemStatus(imDto,
                            InventoryConst.ITEM_STATUS_OUTSRVC);
                }
            }
            return rc;
        } catch (Exception e) {
            this.msg = "Unable to process inventory item master updates.";
            logger.error(this.msg, e);
            throw new InventoryApiException(this.msg, e);
        }
    }

    // public int updateItemMaster(ItemMasterDto item) throws InventoryException
    // {
    // // Perform updates
    // InventoryDao dao = this.factory.createRmt2OrmDao();
    // dao.setDaoUser(this.apiUser);
    // int rc = 0;
    // try {
    // dao.beginTrans();
    // rc = this.updateItemMaster(item, dao);
    // dao.commitTrans();
    // return rc;
    // } catch (Exception e) {
    // dao.rollbackTrans();
    // this.msg = "Unable to process inventory item master updates.";
    // logger.error(this.msg, e);
    // throw new InventoryException(this.msg, e);
    // } finally {
    // dao.close();
    // dao = null;
    // }

    // this.validateItemMaster(item);
    // this.computeItemRetail(item);
    // boolean newItem = (item.getItemId() == 0);
    // ItemMaster newITem = null;
    // ItemMasterDto imDto = Rmt2InventoryDtoFactory
    // .createItemMasterInstance(newITem);
    //
    // // Get old version of item record and apply changes
    // if (!newItem) {
    // imDto = this.getItemById(item.getItemId());
    // if (imDto == null) {
    // this.msg = "Inventory item update error: Item id, "
    // + item.getItemId() + ", does not exist in the system";
    // logger.error(this.msg);
    // throw new InventoryException(this.msg);
    // }
    // }
    //
    // // Capute the original override flag
    // int oldOverrideRetailFlag = imDto.getOverrideRetail();
    //
    // // add delta to old inventory item master version
    // imDto.setItemTypeId(item.getItemTypeId());
    // imDto.setVendorId(item.getVendorId());
    // imDto.setItemName(item.getItemName());
    // imDto.setVendorItemNo(item.getVendorItemNo());
    // imDto.setItemSerialNo(item.getItemSerialNo());
    // imDto.setQtyOnHand(item.getQtyOnHand());
    // imDto.setUnitCost(item.getUnitCost());
    // imDto.setMarkup(item.getMarkup());
    // imDto.setRetailPrice(item.getRetailPrice());
    // imDto.setOverrideRetail(item.getOverrideRetail());
    // imDto.setActive(item.getActive());
    //
    // // Perform updates
    // InventoryDao dao = this.factory.createRmt2OrmDao();
    // dao.setDaoUser(this.apiUser);
    // int rc;
    // try {
    // dao.beginTrans();
    // // Update Item master
    // rc = dao.maintain(imDto);
    //
    // // Update statuses
    // ItemMasterStatusHistDto imsh = null;
    // if (newItem) {
    // // Indicate that item is in service
    // this.changeItemStatus(dao, imDto,
    // InventoryConst.ITEM_STATUS_INSRVC);
    // }
    // else {
    // // Get item master's current status
    // imsh = dao.fetchCurrentItemStatusHistory(imDto.getItemId());
    // if (imsh == null) {
    // this.msg = "Unable to find status history for inventory item id, "
    // + imDto.getItemId() + ".   History not available.";
    // logger.error(this.msg);
    // throw new RMT2Exception(this.msg);
    // }
    // // Change the most recent item status, which should be
    // // 'Replaced'
    // imsh = this.changeItemStatus(dao, imDto,
    // InventoryConst.ITEM_STATUS_REPLACE);
    //
    // // User has requested system to activate vendor item override.
    // if (imDto.getOverrideRetail() == InventoryConst.ITEM_OVERRIDE_YES
    // && oldOverrideRetailFlag == InventoryConst.ITEM_OVERRIDE_NO) {
    // imsh = this.changeItemStatus(dao, imDto,
    // InventoryConst.ITEM_STATUS_OVERRIDE_ACTIVE);
    // }
    //
    // // User has requested system to deactivate vendor item override.
    // if (imDto.getOverrideRetail() == InventoryConst.ITEM_OVERRIDE_NO
    // && oldOverrideRetailFlag == InventoryConst.ITEM_OVERRIDE_YES) {
    // imsh = this.changeItemStatus(dao, imDto,
    // InventoryConst.ITEM_STATUS_OVERRIDE_INACTIVE);
    // }
    // }
    //
    // // Place Item in "Available" if quantity is greater than zero.
    // // Otherwise, 'Out of Stock'.
    // int itemStatusId = imDto.getQtyOnHand() <= 0 ?
    // InventoryConst.ITEM_STATUS_OUTSTOCK
    // : InventoryConst.ITEM_STATUS_AVAIL;
    // imsh = this.changeItemStatus(dao, imDto, itemStatusId);
    //
    // // If item is no longer active, then put in out servive status
    // if (!newItem) {
    // if (imDto.getActive() == 0) {
    // imsh = this.changeItemStatus(dao, imDto,
    // InventoryConst.ITEM_STATUS_OUTSRVC);
    // }
    // }
    // dao.commitTrans();
    // return rc;
    // } catch (Exception e) {
    // dao.rollbackTrans();
    // this.msg = "Unable to process inventory item master updates.";
    // logger.error(this.msg, e);
    // throw new InventoryException(this.msg, e);
    // } finally {
    // dao.close();
    // dao = null;
    // }
    // }

    // private int updateItemMaster(ItemMasterDto item, InventoryDao dao)
    // throws InventoryException {
    // this.computeItemRetail(item);
    // boolean newItem = (item.getItemId() == 0);
    // ItemMaster newITem = null;
    // ItemMasterDto imDto = Rmt2InventoryDtoFactory
    // .createItemMasterInstance(newITem);
    //
    // // Try to use shared connection if DAO is null
    // if (dao == null) {
    // if (this.getSharedDao().getClient() == null) {
    // this.msg = "Inventory item update error: DAO object is invalid";
    // logger.error(this.msg);
    // throw new InventoryException(this.msg);
    // }
    // dao = this.factory.createRmt2OrmDao(this.getSharedDao());
    // dao.setDaoUser(this.apiUser);
    // }
    //
    // // Get old version of item record and apply changes
    // if (!newItem) {
    // imDto = this.getItemById(item.getItemId());
    // if (imDto == null) {
    // this.msg = "Inventory item update error: Item id, "
    // + item.getItemId() + ", does not exist in the system";
    // logger.error(this.msg);
    // throw new InventoryException(this.msg);
    // }
    // }
    //
    // // Capute the original override flag
    // int oldOverrideRetailFlag = imDto.getOverrideRetail();
    //
    // // add delta to old inventory item master version
    // imDto.setItemTypeId(item.getItemTypeId());
    // imDto.setVendorId(item.getVendorId());
    // imDto.setItemName(item.getItemName());
    // imDto.setVendorItemNo(item.getVendorItemNo());
    // imDto.setItemSerialNo(item.getItemSerialNo());
    // imDto.setQtyOnHand(item.getQtyOnHand());
    // imDto.setUnitCost(item.getUnitCost());
    // imDto.setMarkup(item.getMarkup());
    // imDto.setRetailPrice(item.getRetailPrice());
    // imDto.setOverrideRetail(item.getOverrideRetail());
    // imDto.setActive(item.getActive());
    //
    // // Perform updates
    // int rc;
    // try {
    // // Update Item master
    // rc = dao.maintain(imDto);
    //
    // // Update statuses
    // ItemMasterStatusHistDto imsh = null;
    // if (newItem) {
    // // Indicate that item is in service
    // this.changeItemStatus(dao, imDto,
    // InventoryConst.ITEM_STATUS_INSRVC);
    // }
    // else {
    // // Get item master's current status
    // imsh = dao.fetchCurrentItemStatusHistory(imDto.getItemId());
    // if (imsh == null) {
    // this.msg = "Unable to find status history for inventory item id, "
    // + imDto.getItemId() + ".   History not available.";
    // logger.error(this.msg);
    // throw new RMT2Exception(this.msg);
    // }
    // // Change the most recent item status, which should be
    // // 'Replaced'
    // imsh = this.changeItemStatus(dao, imDto,
    // InventoryConst.ITEM_STATUS_REPLACE);
    //
    // // User has requested system to activate vendor item override.
    // if (imDto.getOverrideRetail() == InventoryConst.ITEM_OVERRIDE_YES
    // && oldOverrideRetailFlag == InventoryConst.ITEM_OVERRIDE_NO) {
    // imsh = this.changeItemStatus(dao, imDto,
    // InventoryConst.ITEM_STATUS_OVERRIDE_ACTIVE);
    // }
    //
    // // User has requested system to deactivate vendor item override.
    // if (imDto.getOverrideRetail() == InventoryConst.ITEM_OVERRIDE_NO
    // && oldOverrideRetailFlag == InventoryConst.ITEM_OVERRIDE_YES) {
    // imsh = this.changeItemStatus(dao, imDto,
    // InventoryConst.ITEM_STATUS_OVERRIDE_INACTIVE);
    // }
    // }
    //
    // // Place Item in "Available" if quantity is greater than zero.
    // // Otherwise, 'Out of Stock'.
    // int itemStatusId = imDto.getQtyOnHand() <= 0 ?
    // InventoryConst.ITEM_STATUS_OUTSTOCK
    // : InventoryConst.ITEM_STATUS_AVAIL;
    // imsh = this.changeItemStatus(dao, imDto, itemStatusId);
    //
    // // If item is no longer active, then put in out servive status
    // if (!newItem) {
    // if (imDto.getActive() == 0) {
    // imsh = this.changeItemStatus(dao, imDto,
    // InventoryConst.ITEM_STATUS_OUTSRVC);
    // }
    // }
    // return rc;
    // } catch (Exception e) {
    // this.msg = "Unable to process inventory item master updates.";
    // logger.error(this.msg, e);
    // throw new InventoryException(this.msg, e);
    // }
    // }

    /**
     * Validates an inventory item master object.
     * 
     * The following validations must be met:
     * <ul>
     * <li>Item object cannot be null</li>
     * <li>The vendor id is not required, but if its id is greateer than zero,
     * then it must exist in the database.</li>
     * <li>Item Description must no be null</li>
     * <li>Item Type must be valid</li>
     * <li>Item Markup must be greater than zero</li>
     * <li>Service item types must have a quantity on hand equal to one, and a
     * mark value equal to one.</li>
     * </ul>
     * 
     * @param item
     *            an instance of {@link ItemMasterDto}
     * @throws InventoryException
     */
    protected void validateItemMaster(ItemMasterDto item)
            throws InventoryApiException {
        if (item == null) {
            this.msg = "Item Master DTO cannot be null";
            logger.error(this.msg);
            throw new InventoryApiException(this.msg);
        }
        // Validate Creditor
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi();
        if (item.getVendorId() > 0) {
            Object cred;
            try {
                cred = api.getByCreditorId(item.getVendorId());
            } catch (CreditorApiException e) {
                throw new InventoryApiException(e);
            } finally {
                api = null;
            }
            if (cred == null) {
                this.msg = "The Vendor associated with the inventory item master does not exist in the system: "
                        + item.getVendorId();
                logger.error(this.msg);
                throw new InventoryApiException(this.msg);
            }
        }

        // Common validations
        if (item.getItemName() == null || item.getItemName().equals("")) {
            this.msg = "Item Name must contain a value and cannot be null";
            logger.error(this.msg);
            throw new InventoryApiException(this.msg);
        }

        if (item.getItemTypeId() <= 0) {
            this.msg = "Item Type is invalid";
            logger.error(this.msg);
            throw new InventoryApiException(this.msg);
        }
        // Mark Up must be greater than zero and cannot be null
        if (item.getMarkup() <= 0) {
            this.msg = "Mark Up must be greater than zero and cannot be null";
            logger.error(this.msg);
            throw new InventoryApiException(this.msg);
        }

        /*
         * Not sure if unit cost should be required to have a value greater than
         * zero. if (itemBase.getUnitCost() <= 0) { throw new
         * ItemMasterException(this.dbo, 419, null); }
         */

        // Service item validations
        if (item.getItemTypeId() == InventoryConst.ITEM_TYPE_SRVC) {
            if (item.getVendorId() > 0) {
                this.msg = "Service items cannot be assoicated with a Vendor";
                logger.error(this.msg);
                throw new InventoryApiException(this.msg);
            }
            // Quantity on Hand must be equal to 1 for service items
            if (item.getQtyOnHand() != 1) {
                this.msg = "Quantity on Hand must be equal to 1 for service items";
                logger.error(this.msg);
                throw new InventoryApiException(this.msg);
            }
            // Mark Up must be equal to 1
            if (item.getMarkup() != 1) {
                this.msg = "Mark Up must be equal to 1";
                logger.error(this.msg);
                throw new InventoryApiException(this.msg);
            }
        }
    }

    /**
     * Calculates the retail price of an item based on its item type value
     * (Merchandise or Service). At this point all data components used for
     * calculating retail should have been verified. The retail price will be
     * determined in one of two ways: 1) as is when the user request that retail
     * price calculations be ignored and just accept the user's input or 2)
     * apply the formula retail price = unit cost * mark up.
     * 
     * @param item
     *            an instance of {@link ItemMasterDto}
     * @throws InventoryException
     */
    private void computeItemRetail(ItemMasterDto item)
            throws InventoryApiException {
        double retailPrice = 0;

        // Determine if user requests us to override retail calculations.
        if (item.getOverrideRetail() == 0) {
            retailPrice = item.getUnitCost() * item.getMarkup();
            item.setRetailPrice(retailPrice);
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modules.inventory.InventoryApi#updateVendorItem(org.dto.VendorItemDto
     * )
     */
    @Override
    public int updateVendorItem(VendorItemDto item) throws InventoryApiException {
        this.validateVendorItem(item);

        // Update Vendor Item
        // InventoryDao dao = this.factory.createRmt2OrmDao();
        dao.setDaoUser(this.apiUser);
        int rc;
        try {
            // dao.beginTrans();
            rc = dao.maintain(item);
            // dao.commitTrans();
            return rc;
        } catch (Exception e) {
            // dao.rollbackTrans();
            this.msg = "Unable to process inventory vendor item updates.";
            logger.error(this.msg, e);
            throw new InventoryApiException(this.msg, e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // }
    }

    /**
     * Validate a vendor item object.
     * 
     * The following validations must be met:
     * <ul>
     * <li>The vendor must exist in the database.</li>
     * <li>The vendor id is required and must be greater than zero.</li>
     * <li>The Vendor Item Number cannot be null</li>
     * <li>The Item Serial Number cannot be null</li>
     * </ul>
     * 
     * @param vi
     *            The {@link VendorItemDto} object
     * @throws InventoryException
     */
    protected void validateVendorItem(VendorItemDto vi) throws InventoryApiException {
        dao.setDaoUser(this.apiUser);
        try {
            Verifier.verifyNotNull(vi);   
        }
        catch (VerifyException e) {
            this.msg = "Vendor Item object cannot be null";
            logger.error(this.msg);
            throw new InventoryApiException(this.msg);
        }
        try {
            Verifier.verifyPositive(vi.getVendorId());
        }
        catch (VerifyException e) {
            this.msg = "The vendor item\'s Vendor Id is invalid: " + vi.getVendorId();
            logger.error(this.msg);
            throw new InventoryApiException(this.msg);
        }
        try {
            Verifier.verifyNotNull(vi.getVendorItemNo());   
        }
        catch (VerifyException e) {
            this.msg = "New and existing merchandise items must have the vendor's version of an item number or part number";
            logger.error(this.msg);
            throw new InventoryApiException(this.msg);
        }
        try {
            Verifier.verifyNotNull(vi.getItemSerialNo());   
        }
        catch (VerifyException e) {
            this.msg = "Merchandise items must have a vendor serial number";
            logger.error(this.msg);
            throw new InventoryApiException(this.msg);
        }
        
        // Validate Creditor
        SubsidiaryApiFactory f = new SubsidiaryApiFactory();
        CreditorApi api = f.createCreditorApi(this.dao);
        Object cred;
        try {
            cred = api.getByCreditorId(vi.getVendorId());
        } catch (CreditorApiException e) {
            throw new InventoryApiException(e);
        } finally {
            api = null;
        }
        if (cred == null) {
            this.msg = "The Vendor associated with the inventory vendor item does not exist in the system: "
                    + vi.getVendorId();
            logger.error(this.msg);
            throw new InventoryApiException(this.msg);
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.inventory.InventoryApi#deleteItemMaster(int)
     */
    @Override
    public int deleteItemMaster(Integer itemId) throws InventoryApiException {
        try {
            Verifier.verifyNotNull(itemId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Inventory Item Id is required", e);
        }
        // Determine if item is tied to one or more sales orders and/or purchase
        // orders.
        List<ItemAssociationDto> associations = this.getItemAssociations(itemId);
        if (associations != null && associations.size() > 0) {
            this.msg = "Invenoty item master , "
                    + itemId
                    + ", cannot be deleted since it is associated with one or more sales orders and/or purchase orders";
            logger.error(this.msg);
            throw new InventoryApiException(this.msg);
        }

        // InventoryDao dao = this.factory.createRmt2OrmDao();
        dao.setDaoUser(this.apiUser);
        int rc;
        try {
            // dao.beginTrans();
            // Remove all items from item master status history
            ItemMasterStatusHistDto imshCriteria = Rmt2InventoryDtoFactory
                    .createItemStatusHistoryInstance(null);
            imshCriteria.setItemId(itemId);
            rc = dao.delete(imshCriteria);
            this.msg = "Total item status history entries removed for item id, "
                    + itemId + ": " + rc;
            logger.info(this.msg);

            // Remove item from inventory.
            ItemMaster nullBean = null;
            ItemMasterDto imCriteria = Rmt2InventoryDtoFactory
                    .createItemMasterInstance(nullBean);
            imCriteria.setItemId(itemId);
            rc = dao.delete(imCriteria);
            this.msg = "Inventory item id, " + itemId
                    + ", was successfully deleted from the system";
            logger.info(this.msg);

            // Commit transaction
            // dao.commitTrans();
            return RMT2Base.SUCCESS;
        } catch (Exception e) {
            // dao.rollbackTrans();
            this.msg = "Unable to delete inventory item due to database error";
            logger.error(this.msg, e);
            throw new InventoryApiException(this.msg, e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.inventory.InventoryApi#pushInventory(int, int)
     */
    @Override
    public double pushInventory(Integer itemId, Integer qty) throws InventoryApiException {
        try {
            Verifier.verifyNotNull(itemId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Item Id is required", e);
        }
        try {
            Verifier.verifyNotNull(qty);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Quantity is required", e);
        }
        
        ItemMasterDto im = this.getItemById(itemId);
        if (im == null) {
            this.msg = "Invenoty item master , " + itemId
                    + ", could not be found...invenory was not increased";
            logger.error(this.msg);
            throw new InventoryApiException(this.msg);
        }
        int origItemQty = im.getQtyOnHand();
        int changeItemQty = origItemQty + qty;
        double changeValue = im.getUnitCost() * changeItemQty;
        im.setQtyOnHand(changeItemQty);
        try {
            // this.updateItemMaster(im, null);
            this.updateItemMaster(im);
        } catch (Exception e) {
            this.msg = "Unable to push inventory for item master , " + itemId
                    + ", due to a database error";
            logger.error(this.msg, e);
            throw new InventoryApiException(this.msg, e);
        }
        return changeValue;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.inventory.InventoryApi#pullInventory(int, int)
     */
    @Override
    public double pullInventory(Integer itemId, Integer qty) throws InventoryApiException {
        try {
            Verifier.verifyNotNull(itemId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Item Id is required", e);
        }
        try {
            Verifier.verifyNotNull(qty);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Quantity is required", e);
        }
        ItemMasterDto im = this.getItemById(itemId);
        if (im == null) {
            this.msg = "Invenoty item master , " + itemId
                    + ", could not be found...invenory was not decreased";
            logger.error(this.msg);
            throw new InventoryApiException(this.msg);
        }
        int origItemQty = im.getQtyOnHand();
        int changeItemQty = origItemQty - qty;
        double changeValue = im.getUnitCost() * changeItemQty;
        im.setQtyOnHand(changeItemQty);
        try {
            // this.updateItemMaster(im, null);
            this.updateItemMaster(im);
        } catch (Exception e) {
            this.msg = "Unable to pull inventory for item master , " + itemId
                    + ", due to a database error";
            logger.error(this.msg, e);
            throw new InventoryApiException(this.msg, e);
        }
        return changeValue;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.inventory.InventoryApi#deactivateItemMaster(int)
     */
    @Override
    public int deactivateItemMaster(Integer itemId) throws InventoryApiException {
        try {
            Verifier.verifyNotNull(itemId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Inventory Item Id is required", e);
        }
        try {
            Verifier.verifyPositive(itemId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Inventory Item Id must be greater than zero", e);
        }
        ItemMasterDto im = this.getItemById(itemId);
        if (im == null) {
            this.msg = "Invenoty item master , " + itemId
                    + ", could not be found...item was not deactivated";
            logger.error(this.msg);
            throw new InventoryApiException(this.msg);
        }
        // InventoryDao dao = this.factory.createRmt2OrmDao();
        dao.setDaoUser(this.apiUser);

        try {
            // dao.beginTrans();
            // Set item inactive
            im.setActive(InventoryConst.ITEM_ACTIVE_NO);
            // Update Item master
            dao.maintain(im);
            // Render item out of service
            this.changeItemStatus(im, InventoryConst.ITEM_STATUS_OUTSRVC);
            // dao.commitTrans();
            return RMT2Base.SUCCESS;
        } catch (Exception e) {
            // dao.rollbackTrans();
            this.msg = "Unable to deactivate item master , " + itemId
                    + ", due to a database error";
            logger.error(this.msg, e);
            throw new InventoryApiException(this.msg, e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modules.inventory.InventoryApi#activateItemMaster(int)
     */
    @Override
    public int activateItemMaster(Integer itemId) throws InventoryApiException {
        try {
            Verifier.verifyNotNull(itemId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Inventory Item Id is required", e);
        }
        try {
            Verifier.verifyPositive(itemId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Inventory Item Id must be greater than zero", e);
        }
        ItemMasterDto im = this.getItemById(itemId);
        if (im == null) {
            this.msg = "Invenoty item master , " + itemId
                    + ", could not be found...item was not activated";
            logger.error(this.msg);
            throw new InventoryApiException(this.msg);
        }
        // InventoryDao dao = this.factory.createRmt2OrmDao();
        dao.setDaoUser(this.apiUser);
        try {
            // dao.beginTrans();
            // Set item active
            im.setActive(InventoryConst.ITEM_ACTIVE_YES);
            // Update Item master
            dao.maintain(im);
            // Render item out of service
            this.changeItemStatus(im, InventoryConst.ITEM_STATUS_INSRVC);
            // Determine if item has quantity to sold or is out of stock.
            int itemStatusId = im.getQtyOnHand() <= 0 ? InventoryConst.ITEM_STATUS_OUTSTOCK
                    : InventoryConst.ITEM_STATUS_AVAIL;
            this.changeItemStatus(im, itemStatusId);
            // dao.commitTrans();
            return RMT2Base.SUCCESS;
        } catch (Exception e) {
            // dao.rollbackTrans();
            this.msg = "Unable to activate item master , " + itemId
                    + ", due to a database error";
            logger.error(this.msg, e);
            throw new InventoryApiException(this.msg, e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // }
    }

    /**
     * Associates one or more inventory items with a vendor.
     * <p>
     * This method iterates through the list of inventory item id's contained in
     * <i>items</i> and assigns each one to a vendor identified as
     * <i>vendorId</i>. If an error is encounterd during an iteration, the
     * entire process is aborted and all previous successful transations are
     * rolled back.
     * 
     * @param vendorId
     *            The id of the vendor
     * @param items
     *            A list inventory item id's
     * @return The number of items assigned to the vendor.
     * @throws InventoryException
     *             An inventory item in <i>items</i> does not exist in the
     *             system or database error trying to assoicate the vendor with
     *             an inventory item.
     */
    @Override
    public int assignVendorItems(Integer vendorId, Integer[] items) throws InventoryApiException {
        try {
            Verifier.verifyNotNull(vendorId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Inventory Vendor Id is required", e);
        }
        try {
            Verifier.verifyPositive(vendorId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Inventory Vendor Id must be greater than zero", e);
        }
        try {
            Verifier.verifyNotNull(items);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("List of Inventory Item Id's is required", e);
        }
        try {
            for (int ndx = 0; ndx < items.length; ndx++) {
                Verifier.verifyNotNull(items[ndx]);
                Verifier.verifyPositive(items[ndx]);
            }
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Inventory Item Id cannot be null and must be greater than zero", e);
        }
        
        int count = 0;
        // InventoryDao dao = this.factory.createRmt2OrmDao();
        dao.setDaoUser(this.apiUser);
        // dao.beginTrans();
        try {
            for (int ndx = 0; ndx < items.length; ndx++) {
                ItemMasterDto imDto = this.getItemById(items[ndx]);
                if (imDto == null) {
                    this.msg = "Item id is not found in the database: "
                            + items[ndx];
                    logger.error(this.msg);
                    throw new InventoryApiException(this.msg);
                }
                VendorItemDto viDto = Rmt2InventoryDtoFactory
                        .createVendorItemInstance(vendorId, imDto);
                try {
                    count += dao.maintain(viDto);
                } catch (Exception e) {
                    this.msg = "Error creating vendor id [" + vendorId
                            + "] and item id {" + imDto.getItemId()
                            + "] associtation";
                    logger.error(this.msg);
                    throw new InventoryApiException(this.msg, e);
                }
            }
            // dao.commitTrans();
            return count;
        } catch (Exception e) {
            // dao.rollbackTrans();
            this.msg = "Unable to persist vendor id/item master association to table, vendor_items.  The assignment of items to vendor is aborted";
            logger.error(this.msg, e);
            throw new InventoryApiException(this.msg, e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // }
    }

    /**
     * Disassociates one or more inventory items from a vendor.
     * <p>
     * This method iterates through the list of inventory item id's contained in
     * <i>items</i> and removes each one from the vendor identified as
     * <i>vendorId</i>. If an error is encounterd during an iteration, the
     * entire process is aborted and all previous successful transations are
     * rolled back.
     * 
     * @param vendorId
     *            The id of the vendor
     * @param items
     *            A list inventory item id's to remove.
     * @return The number of items removedto the vendor.
     * @throws InventoryException
     *             An inventory item in <i>items</i> does not exist in the
     *             system or database error trying to disassoicate the vendor
     *             from an inventory item.
     */
    @Override
    public int removeVendorItems(Integer vendorId, Integer[] items)
            throws InventoryApiException {
        try {
            Verifier.verifyNotNull(vendorId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Vendor Id is required", e);
        }
        try {
            Verifier.verifyPositive(vendorId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Vendor Id must be greater than zero", e);
        }
        try {
            Verifier.verifyNotNull(items);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("List of vendor item id's is required", e);
        }
        try {
            for (int ndx = 0; ndx < items.length; ndx++) {
                Verifier.verifyNotNull(items[ndx]);
                Verifier.verifyPositive(items[ndx]);
            }
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Vendor Item Id cannot be null and must be greater than zero", e);
        }
        
        int count = 0;
        dao.setDaoUser(this.apiUser);
        try {
            for (int ndx = 0; ndx < items.length; ndx++) {
                VendorItemDto viDto = Rmt2InventoryDtoFactory.createVendorItemInstance(null);
                viDto.setVendorId(vendorId);
                viDto.setItemId(items[ndx]);
                try {
                    count += dao.delete(viDto);
                } catch (Exception e) {
                    this.msg = "Error deleting vendor id [" + vendorId
                            + "] and item id {" + items[ndx] + "] associtation";
                    logger.error(this.msg);
                    throw new InventoryApiException(this.msg, e);
                }
            }
            return count;
        } catch (Exception e) {
            this.msg = "Unable to delete vendor id/item master association from table, vendor_items.  The deletion of items to vendor is aborted";
            logger.error(this.msg, e);
            throw new InventoryApiException(this.msg, e);
        }
    }

    /**
     * Changes the override flag to true for one or more of a vendor's items.
     * <p>
     * Activates a vendor-item override for all item id's stored in items
     * collection.
     * 
     * @param vendorId
     *            The id of the vendor that will be assoicated with each item
     *            id.
     * @param items
     *            Collection containing one or more item_master id's to
     *            override.
     * @return The total number of rows effected by the database transaction.
     * @throws ItemMasterException
     */
    @Override
    public int addInventoryOverride(Integer vendorId, Integer[] items)
            throws InventoryApiException {
        try {
            Verifier.verifyNotNull(vendorId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Inventory Vendor Id is required", e);
        }
        try {
            Verifier.verifyPositive(vendorId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Inventory Vendor Id must be greater than zero", e);
        }
        try {
            Verifier.verifyNotNull(items);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("List of Inventory Item Id's is required", e);
        }
        try {
            for (int ndx = 0; ndx < items.length; ndx++) {
                Verifier.verifyNotNull(items[ndx]);
                Verifier.verifyPositive(items[ndx]);
            }
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Inventory Item Id cannot be null and must be greater than zero", e);
        }
        
        int count = 0;
        // InventoryDao dao = this.factory.createRmt2OrmDao();
        dao.setDaoUser(this.apiUser);
        // dao.beginTrans();
        try {
            for (int ndx = 0; ndx < items.length; ndx++) {
                ItemMasterDto imDto = this.getItemById(items[ndx]);
                if (imDto == null) {
                    this.msg = "Item id is not found in the database: "
                            + items[ndx];
                    logger.error(this.msg);
                    throw new InventoryApiException(this.msg);
                }

                // Do not attempt to update an item that is currently overriden.
                if (imDto.getOverrideRetail() == InventoryConst.ITEM_OVERRIDE_YES) {
                    continue;
                }
                imDto.setVendorId(vendorId);
                imDto.setOverrideRetail(InventoryConst.ITEM_OVERRIDE_YES);
                // Ensure that item's quantity on hand is not effected after
                // updates are applied to the database.
                imDto.setQtyOnHand(0);
                try {
                    count += dao.maintain(imDto);
                } catch (Exception e) {
                    this.msg = "Error changing override status of inventory item id, "
                            + imDto.getItemId();
                    logger.error(this.msg);
                    throw new InventoryApiException(this.msg, e);
                }
            }
            // dao.commitTrans();
            return count;
        } catch (Exception e) {
            // dao.rollbackTrans();
            this.msg = "Unable to persist vendor id/item master association to table, vendor_items.  The assignment of items to vendor is aborted";
            logger.error(this.msg, e);
            throw new InventoryApiException(this.msg, e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // }
    }

    /**
     * Changes the override flag to false for one or more of a vendor's item.
     * <p>
     * This method deactivates a vendor-item override targeting the inventory
     * item, itemId. Also, the creditor id of the inventory item master is set
     * to null. An override instructs the system to obtain pricing information
     * for an inventory item from the vendor_items table instead of the
     * item_master table .
     * 
     * @param vendorId
     *            The id of the vendor that will be disassoicated with the item
     *            id.
     * @param items
     *            An array of item id's to change
     * @return The total number of items effected by the database transaction. .
     * @throws ItemMasterException
     */
    @Override
    public int removeInventoryOverride(Integer vendorId, Integer[] items)
            throws InventoryApiException {
        try {
            Verifier.verifyNotNull(vendorId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Inventory Vendor Id is required", e);
        }
        try {
            Verifier.verifyPositive(vendorId);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Inventory Vendor Id must be greater than zero", e);
        }
        try {
            Verifier.verifyNotNull(items);    
        }
        catch (VerifyException e) {
            throw new InvalidDataException("List of Inventory Item Id's is required", e);
        }
        try {
            for (int ndx = 0; ndx < items.length; ndx++) {
                Verifier.verifyNotNull(items[ndx]);
                Verifier.verifyPositive(items[ndx]);
            }
        }
        catch (VerifyException e) {
            throw new InvalidDataException("Inventory Item Id cannot be null and must be greater than zero", e);
        }
        
        int count = 0;
        // InventoryDao dao = this.factory.createRmt2OrmDao();
        dao.setDaoUser(this.apiUser);
        // dao.beginTrans();
        try {
            for (int ndx = 0; ndx < items.length; ndx++) {
                ItemMasterDto imDto = this.getItemById(items[ndx]);
                if (imDto == null) {
                    this.msg = "Item id is not found in the database: "
                            + items[ndx];
                    logger.error(this.msg);
                    throw new InventoryApiException(this.msg);
                }

                // Do not attempt to update an item that is currently overriden.
                if (imDto.getOverrideRetail() == InventoryConst.ITEM_OVERRIDE_NO) {
                    continue;
                }
                imDto.setVendorId(0);
                imDto.setOverrideRetail(InventoryConst.ITEM_OVERRIDE_NO);
                // Ensure that item's quantity on hand is not effected after
                // updates are applied to the database.
                imDto.setQtyOnHand(0);
                try {
                    count += dao.maintain(imDto);
                } catch (Exception e) {
                    this.msg = "Error changing override status of inventory item id, "
                            + imDto.getItemId();
                    logger.error(this.msg);
                    throw new InventoryApiException(this.msg, e);
                }
            }
            // dao.commitTrans();
            return count;
        } catch (Exception e) {
            // dao.rollbackTrans();
            this.msg = "Unable to persist vendor id/item master association to table, vendor_items.  The assignment of items to vendor is aborted";
            logger.error(this.msg, e);
            throw new InventoryApiException(this.msg, e);
        }
        // finally {
        // dao.close();
        // dao = null;
        // }
    }

    // /**
    // * Changes the status of an inventory item.
    // *
    // * @param item
    // * An instance of {@link ItemMasterDto} which is the item master
    // * object targeted for the satus change.
    // * @param newItemStatusId
    // * The id of the item status.
    // * @return The {@link ItemMasterStatusHistDto} object which represents
    // * newItemStatusId
    // * @throws InventoryException
    // * If newItemStatusId is out of sequence, if a database error
    // * occurs, or a system error occurs.
    // */
    // private ItemMasterStatusHistDto changeItemStatus(ItemMasterDto item,
    // int newItemStatusId) throws InventoryException {
    // InventoryDao dao = this.factory.createRmt2OrmDao();
    // dao.setDaoUser(this.apiUser);
    // return this.changeItemStatus(dao, item, newItemStatusId);
    // }

    /**
     * Changes the status of an inventory item which the database transaction
     * object is supplied by the user.
     * 
     * @param dao
     *            the inventory database transaction object
     * @param item
     *            An instance of {@link ItemMasterDto} which is the item master
     *            object targeted for the satus change.
     * @param newItemStatusId
     *            The id of the item status.
     * @return The {@link ItemMasterStatusHistDto} object which represents
     *         newItemStatusId
     * @throws InventoryException
     *             If newItemStatusId is out of sequence, if a database error
     *             occurs, or a system error occurs.
     */
    protected ItemMasterStatusHistDto changeItemStatus(ItemMasterDto item,
            int newItemStatusId) throws InventoryApiException {
        ItemMasterStatusHistDto imsh = null;

        dao.setDaoUser(this.apiUser);
        // Validate newItemStatusId
        List<ItemMasterStatusDto> imsList;
        try {
            ItemMasterStatusDto imsCriteria = Rmt2InventoryDtoFactory
                    .createItemStatusInstance(null);
            imsCriteria.setEntityId(newItemStatusId);
            imsList = dao.fetch(imsCriteria);
        } catch (InventoryDaoException e) {
            this.msg = "Problem querying for new Item status";
            logger.error(this.msg);
            throw new InventoryApiException(this.msg);
        }
        if (imsList == null) {
            this.msg = "New item status id does not exist in the system: "
                    + newItemStatusId;
            logger.error(this.msg);
            throw new InventoryApiException(this.msg);
        }
        if (imsList.size() > 1) {
            this.msg = "New item status id query return multiple items in the result set.  Should only be one element";
            logger.error(this.msg);
            throw new InventoryApiException(this.msg);
        }

        try {
            // End current item status
            imsh = dao.fetchCurrentItemStatusHistory(item.getItemId());
            if (imsh != null) {
                dao.maintain(imsh);
            }

            // Create new item status
            imsh = Rmt2InventoryDtoFactory
                    .createItemStatusHistoryInstance(null);
            imsh.setItemId(item.getItemId());
            imsh.setItemStatusId(newItemStatusId);
            imsh.setUnitCost(item.getUnitCost());
            imsh.setMarkup(item.getMarkup());
            dao.maintain(imsh);
            return imsh;
        } catch (Exception e) {
            this.msg = "Inventory item status history change failed due to a Database or System error";
            logger.error(this.msg, e);
            throw new InventoryApiException(this.msg, e);
        }
    }

    // protected ItemMasterStatusHistDto changeItemStatus(InventoryDao dao,
    // ItemMasterDto item, int newItemStatusId) throws InventoryException {
    // ItemMasterStatusHistDto imsh = null;
    //
    // // Validate newItemStatusId
    // List<ItemMasterStatusDto> imsList;
    // try {
    // ItemMasterStatusDto imsCriteria = Rmt2InventoryDtoFactory
    // .createItemStatusInstance(null);
    // imsCriteria.setEntityId(newItemStatusId);
    // imsList = dao.fetch(imsCriteria);
    // } catch (InventoryDaoException e) {
    // this.msg = "Problem querying for new Item status";
    // logger.error(this.msg);
    // throw new InventoryException(this.msg);
    // }
    // if (imsList == null) {
    // this.msg = "New item status id does not exist in the system: "
    // + newItemStatusId;
    // logger.error(this.msg);
    // throw new InventoryException(this.msg);
    // }
    // if (imsList.size() > 1) {
    // this.msg =
    // "New item status id query return multiple items in the result set.  Should only be one element";
    // logger.error(this.msg);
    // throw new InventoryException(this.msg);
    // }
    //
    // try {
    // // End current item status
    // imsh = dao.fetchCurrentItemStatusHistory(item.getItemId());
    // if (imsh != null) {
    // dao.maintain(imsh);
    // }
    //
    // // Create new item status
    // imsh = Rmt2InventoryDtoFactory
    // .createItemStatusHistoryInstance(null);
    // imsh.setItemId(item.getItemId());
    // imsh.setItemStatusId(newItemStatusId);
    // imsh.setUnitCost(item.getUnitCost());
    // imsh.setMarkup(item.getMarkup());
    // dao.maintain(imsh);
    // return imsh;
    // } catch (Exception e) {
    // this.msg =
    // "Inventory item status history change failed due to a Database or System error";
    // logger.error(this.msg, e);
    // throw new InventoryException(this.msg, e);
    // }
    // }

}
