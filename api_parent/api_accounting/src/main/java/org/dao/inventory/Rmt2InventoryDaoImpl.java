package org.dao.inventory;

import java.util.ArrayList;
import java.util.List;

import org.dao.AccountingDaoImpl;
import org.dao.mapping.orm.rmt2.ItemMaster;
import org.dao.mapping.orm.rmt2.ItemMasterStatus;
import org.dao.mapping.orm.rmt2.ItemMasterStatusHist;
import org.dao.mapping.orm.rmt2.ItemMasterType;
import org.dao.mapping.orm.rmt2.VendorItems;
import org.dao.mapping.orm.rmt2.VwItemAssociations;
import org.dao.mapping.orm.rmt2.VwVendorItems;
import org.dto.ItemAssociationDto;
import org.dto.ItemMasterDto;
import org.dto.ItemMasterStatusDto;
import org.dto.ItemMasterStatusHistDto;
import org.dto.ItemMasterTypeDto;
import org.dto.VendorItemDto;
import org.dto.adapter.orm.inventory.Rmt2InventoryDtoFactory;
import org.modules.inventory.InventoryConst;

import com.api.persistence.CannotRetrieveException;
import com.api.persistence.DatabaseException;
import com.api.persistence.PersistenceClient;
import com.api.util.RMT2Date;
import com.api.util.UserTimestamp;

/**
 * A RMT2 ORM implementation of {@link InventoryDao} that queries, creates,
 * modifies, and deletes inventory item, type, status, and status history
 * entities.
 * 
 * @author Roy Terrell
 * 
 */
public class Rmt2InventoryDaoImpl extends AccountingDaoImpl implements InventoryDao {

    /**
     * Default constructor responsible for establishing a connection to the
     * accounting database.
     */
    public Rmt2InventoryDaoImpl() {
        super();
    }

    /**
     * Default constructor responsible for establishing a connection to the
     * accounting database.
     * 
     * @param appName
     */
    public Rmt2InventoryDaoImpl(String appName) {
        super(appName);
    }

    /**
     * @param client
     */
    Rmt2InventoryDaoImpl(PersistenceClient client) {
        super(client);
    }

    /**
     * Retrieves a list of inventory items from the item_master database table
     * based on the custom selection criteria supplied by the user. User is
     * responsible for using the appropriate SQL predicate syntax in order to
     * successfully retrieve the data.
     * <p>
     * The result set is ordered by item description.
     * 
     * @param criteria
     *            The selection criteria to apply to the query of data source.
     *            When null, all rows are fetched.
     * @returnA List of {@link ItemMasterDto} objects.
     * @throws InventoryDaoException
     *             <i>criteria</i> contains incorrect SQL syntax or general data
     *             access errors
     */
    public List<ItemMasterDto> fetch(String criteria) throws InventoryDaoException {
        ItemMaster obj = new ItemMaster();
        if (criteria != null) {
            obj.addCustomCriteria(criteria);
        }
        obj.addOrderBy(ItemMaster.PROP_DESCRIPTION, ItemMaster.ORDERBY_ASCENDING);

        // Retrieve Data
        List<ItemMaster> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new CannotRetrieveException(e);
        }

        List<ItemMasterDto> list = new ArrayList<ItemMasterDto>();
        for (ItemMaster item : results) {
            ItemMasterDto dto = Rmt2InventoryDtoFactory
                    .createItemMasterInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Retrieves a list of inventory item master objects from the item_master
     * database table.
     * <p>
     * A filter can be applied to the query using one or more properties
     * available in <i>criteria</i> as selection criteria. The following
     * properties can be used build selection criteria: Item Id, Item Serial
     * Number, Item Type Id, Creditor Id, Vendor Item Number, and/or Item
     * Descritpion.
     * <p>
     * Set <i>criteria</i> to null when the desire arises to retrieve all items.
     * <p>
     * The result set is arranged in by item description in ascending order.
     * 
     * @param criteria
     *            An instance of {@link ItemMasterDto} where each property
     *            containing a value is used to build selection criteria for the
     *            sake querying a data source. The properties, itemId,
     *            itemSerialNo, itemTypeId, creditorId, vendorItemNo, and
     *            itemName are the only properties recognized when building
     *            selection criteria. To retrieve all rows in the item_master
     *            table, set this argument to null.
     * @return A List of ItemMasterDto objects representing one or more items or
     *         null when no data is found.
     * @throws InventoryDaoException
     *             General data access errors
     */
    @Override
    public List<ItemMasterDto> fetch(ItemMasterDto criteria) throws InventoryDaoException {
        ItemMaster obj = InventoryDaoFactory.createCriteria(criteria);
        obj.addOrderBy(ItemMaster.PROP_DESCRIPTION, ItemMaster.ORDERBY_ASCENDING);

        // Retrieve Data
        List<ItemMaster> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null || results.isEmpty()) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new CannotRetrieveException("Database Error occurred fetching inventory item master using the following selection criteria: " + obj.toString(), e);
        }

        List<ItemMasterDto> list = new ArrayList<ItemMasterDto>();
        for (ItemMaster item : results) {
            ItemMasterDto dto = Rmt2InventoryDtoFactory.createItemMasterInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Retrieves a list of inventory item master objects from the
     * vw_vendor_items database view.
     * <p>
     * A filter can be applied to the query using one or more properties
     * available in <i>criteria</i> as selection criteria. The following
     * properties can be used build selection criteria: Item Id, Item Serial
     * Number, Creditor Id, and Vendor Item Number.
     * <p>
     * Set <i>criteria</i> to null when the desire arises to retrieve all items.
     * <p>
     * The result set is arranged in by item description in ascending order.
     * 
     * @param criteria
     *            an instance of {@linkplain VendorItemDto} where each property
     *            containing a value is used to build selection criteria for the
     *            sake of querying a data source. The properties, itemId,
     *            itemSerialNo, creditorId, and vendorItemNo are the only
     *            properties recognized when building selection criteria. To
     *            retrieve all rows in the vw_vendor_items view, set this
     *            argument to null.
     * @return A List of one or more instances of {@link VendorItemDto} or null
     *         when no data is found.
     * @throws InventoryDaoException
     *             General data access errors
     */
    @Override
    public List<VendorItemDto> fetch(VendorItemDto criteria)
            throws InventoryDaoException {
        VwVendorItems obj = InventoryDaoFactory.createCriteria(criteria);
        obj.addOrderBy(VwVendorItems.PROP_DESCRIPTION,
                VwVendorItems.ORDERBY_ASCENDING);

        // Retrieve Data
        List<VwVendorItems> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new CannotRetrieveException(e);
        }

        List<VendorItemDto> list = new ArrayList<VendorItemDto>();
        for (VwVendorItems item : results) {
            VendorItemDto dto = Rmt2InventoryDtoFactory
                    .createVendorItemInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Retrieves a list of inventory item type objects from the item_master_type
     * database table.
     * <p>
     * A filter can be applied to the query using one or more properties
     * available in <i>criteria</i> as selection criteria. The following
     * properties can be used build selection criteria: Item Type Id and Item
     * Type Description.
     * <p>
     * Set <i>criteria</i> to null when the desire arises to retrieve all items.
     * <p>
     * The result set is arranged in by item description in ascending order.
     * 
     * @param criteria
     *            an instance of {@link ItemMasterTypeDto} where each non-null
     *            property is considered to be targeted as selection criteria.
     *            The properties, itemTypeId and itemTypeDescription are the
     *            only properties recognized when building selection criteria.
     *            To retrieve all rows in the item_master_type table, set this
     *            argument to null.
     * @return a List of {@link ItemMasterTypeDto} or null when no data is
     *         found.
     * @throws InventoryDaoException
     *             General data access errors
     */
    @Override
    public List<ItemMasterTypeDto> fetch(ItemMasterTypeDto criteria) throws InventoryDaoException {
        ItemMasterType obj = InventoryDaoFactory.createCriteria(criteria);
        obj.addOrderBy(ItemMasterType.PROP_DESCRIPTION, ItemMasterType.ORDERBY_ASCENDING);

        // Retrieve Data
        List<ItemMasterType> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new CannotRetrieveException(e);
        }

        List<ItemMasterTypeDto> list = new ArrayList<ItemMasterTypeDto>();
        for (ItemMasterType item : results) {
            ItemMasterTypeDto dto = Rmt2InventoryDtoFactory.createItemTypeInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Retrieves a list of inventory item status objects from the
     * item_master_status database table.
     * <p>
     * A filter can be applied to the query using one or more properties
     * available in <i>criteria</i> as selection criteria. The following
     * properties can be used build selection criteria: Item Status Id and Item
     * Status Description.
     * <p>
     * Set <i>criteria</i> to null when the desire arises to retrieve all items.
     * <p>
     * The result set is arranged in by item description in ascending order.
     * 
     * @param criteria
     *            an instance of {@link ItemMasterStatusDto} where each non-null
     *            property is considered to be targeted as selection criteria.
     *            The properties, entityId and entityName are the only
     *            properties recognized when building selection criteria.
     *            <i>entityId</i> and <i>entityName</i> are considered common
     *            object properties and are mapped to the item_status_id and
     *            description columns belonging to the item_master_status table.
     *            To retrieve all rows in the item_master_type table, set this
     *            argument to null.
     * @return a List of {@link ItemMasterStatusDto} or null when no data is
     *         found
     * @throws InventoryDaoException
     *             General data access errors
     */
    @Override
    public List<ItemMasterStatusDto> fetch(ItemMasterStatusDto criteria)
            throws InventoryDaoException {
        ItemMasterStatus obj = InventoryDaoFactory.createCriteria(criteria);
        obj.addOrderBy(ItemMasterStatus.PROP_DESCRIPTION,
                ItemMasterStatus.ORDERBY_ASCENDING);
        // Retrieve Data
        List<ItemMasterStatus> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new CannotRetrieveException(e);
        }

        List<ItemMasterStatusDto> list = new ArrayList<ItemMasterStatusDto>();
        for (ItemMasterStatus item : results) {
            ItemMasterStatusDto dto = Rmt2InventoryDtoFactory
                    .createItemStatusInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Retrieves a list of inventory item status objects from the
     * item_master_status_hist database table.
     * <p>
     * A filter can be applied to the query using one or more properties
     * available in <i>criteria</i> as selection criteria. The following
     * properties can be used build selection criteria: Item Status History Id,
     * Item Id, and Item Status Id.
     * <p>
     * Set <i>criteria</i> to null when the desire arises to retrieve all items.
     * <p>
     * The result set is arranged in by the date_created column in descending
     * order.
     * 
     * @param criteria
     *            an instance of {@link ItemMasterStatusHistDto} where each
     *            non-null property is considered to be targeted as selection
     *            criteria. The properties, entityId, itemId, and itemStatusId
     *            are the only properties recognized when building selection
     *            criteria. <i>entityId</i> is considered as a common object
     *            property and is mapped to the item_status_hist_id column which
     *            belongs to the item_master_status_hist table. To retrieve all
     *            rows in the item_master_type table, set this argument to null.
     * @return a List of {@link ItemMasterStatusHistDto} or null when no data is
     *         found. The results are arranged in ascending order by
     *         date_created column.
     * @throws InventoryDaoException
     *             General data access errors
     */
    @Override
    public List<ItemMasterStatusHistDto> fetch(ItemMasterStatusHistDto criteria)
            throws InventoryDaoException {
        ItemMasterStatusHist obj = InventoryDaoFactory.createCriteria(criteria);
        obj.addOrderBy(ItemMasterStatusHist.PROP_DATECREATED,
                ItemMasterStatusHist.ORDERBY_DESCENDING);
        // Retrieve Data
        List<ItemMasterStatusHist> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new CannotRetrieveException(e);
        }

        List<ItemMasterStatusHistDto> list = new ArrayList<ItemMasterStatusHistDto>();
        for (ItemMasterStatusHist item : results) {
            ItemMasterStatusHistDto dto = Rmt2InventoryDtoFactory
                    .createItemStatusHistoryInstance(item);
            list.add(dto);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dao.inventory.InventoryDao#fetchCurrentItemStatusHistory(int)
     */
    @Override
    public ItemMasterStatusHistDto fetchCurrentItemStatusHistory(
            int itemMasterId) throws InventoryDaoException {
        ItemMasterStatusHist obj = new ItemMasterStatusHist();
        obj.addCriteria(ItemMasterStatusHist.PROP_ITEMID, itemMasterId);
        obj.setItemId(itemMasterId);
        obj.addCriteria(ItemMasterStatusHist.PROP_ENDDATE,
                ItemMasterStatusHist.DB_NULL);
        obj.setEndDate(null);
        obj.addOrderBy(ItemMasterStatus.PROP_DESCRIPTION,
                ItemMasterStatus.ORDERBY_ASCENDING);
        // Retrieve Data
        ItemMasterStatusHist results = null;
        try {
            results = (ItemMasterStatusHist) this.client.retrieveObject(obj);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new CannotRetrieveException(e);
        }

        // Convert results to dto
        ItemMasterStatusHistDto dto = Rmt2InventoryDtoFactory
                .createItemStatusHistoryInstance(results);
        return dto;
    }

    /**
     * Retrieves a list of inventory item associations from the
     * vw_item_associations database view.
     * <p>
     * Item assoiciations are considered to be those that are attached to sales
     * orders and/of purchase orders.
     * <p>
     * A filter can be applied to the query using one or more properties
     * available in <i>criteria</i> as selection criteria. The following
     * properties can be used to build selection criteria: Association Id and
     * Assoication Item Id, Item Id, and Assoication Type.
     * <p>
     * Set <i>criteria</i> to null when the desire arises to retrieve all items.
     * <p>
     * The result set is arranged in by the item_id column in ascending order.
     * 
     * @param criteria
     *            an instance of {@link ItemAssociationDto} where each non-null
     *            property is considered to be targeted as selection criteria.
     *            The properties, associationId, associationItemId, itemId, and
     *            assocType are the only properties recognized when building
     *            selection criteria. To retrieve all rows in the
     *            item_master_type table, set this argument to null.
     * @return a List of {@link ItemAssociationDto} or null when no data is
     *         found
     * @throws InventoryDaoException
     *             General data access errors
     */
    @Override
    public List<ItemAssociationDto> fetch(ItemAssociationDto criteria)
            throws InventoryDaoException {
        VwItemAssociations obj = InventoryDaoFactory.createCriteria(criteria);
        obj.addOrderBy(VwItemAssociations.PROP_ITEMID, VwItemAssociations.ORDERBY_ASCENDING);

        // Retrieve Data
        List<VwItemAssociations> results = null;
        try {
            results = this.client.retrieveList(obj);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new CannotRetrieveException(e);
        }

        List<ItemAssociationDto> list = new ArrayList<ItemAssociationDto>();
        for (VwItemAssociations item : results) {
            ItemAssociationDto dto = Rmt2InventoryDtoFactory
                    .createItemAssociationInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Maintains an item master record by persisting changes as either a
     * database insert or update operation.
     * 
     * @param item
     *            an instance of {@link ItemMasterDto}
     * @return the new item master id for insert operations and the total number
     *         of rows effected for update opeartions.
     * @throws InventoryDaoException
     *             <i>item</i> is null or general database access error
     */
    @Override
    public int maintain(ItemMasterDto item) throws InventoryDaoException {
        int rc;
        ItemMaster obj = InventoryDaoFactory.createItemMasterRmt2Orm(item);
        if (obj.getCreditorId() == 0) {
            obj.setNull(ItemMaster.PROP_CREDITORID);
        }

        if (obj.getItemId() <= 0) {
            rc = this.insertItemMaster(obj);
            item.setItemId(obj.getItemId());
        }
        else {
            rc = this.updateItemMaster(obj);
        }
        return rc;
    }

    /**
     * Adds an inventory item to the database. Upon successful completion, the
     * caller can interrogate the <i>item</i> object for property values that
     * were updated by this method including its item id property.
     * 
     * @param item
     *            an instance of {@link ItemMaster} object
     * @return The id of the new inventory item.
     * @throws InventoryDaoException
     */
    private int insertItemMaster(ItemMaster item) throws InventoryDaoException {
        UserTimestamp ut = null;
        int newId;

        // TODO: Add code to API to ensure that item type has a description.
        try {
            ut = RMT2Date.getUserTimeStamp(this.getDaoUser());

            // Set creditor to null if service item. Otherwise, creditor will
            // equal zero and a SQL referential integrity error will occur.
            if (item.getItemTypeId() == InventoryDaoConst.ITEM_TYPE_SRVC) {
                item.setNull(ItemMaster.PROP_CREDITORID);
                item.setOverrideRetail(1);
            }
            item.setActive(InventoryConst.ITEM_ACTIVE_YES);
            item.setDateCreated(ut.getDateCreated());
            item.setDateUpdated(ut.getDateCreated());
            item.setUserId(ut.getLoginId());
            item.setIpCreated(ut.getIpAddr());
            item.setIpUpdated(ut.getIpAddr());
            newId = this.client.insertRow(item, true);
            item.setItemId(newId);
            return newId;
        } catch (Exception e) {
            throw new InventoryDaoException(e);
        }
    }

    /**
     * Updates the inventory item master object.
     * 
     * @param item
     *            intance of {@link ItemMaster}.
     * @return The total number of items updated from transaction.
     * @throws InventoryDaoException
     */
    private int updateItemMaster(ItemMaster item) throws InventoryDaoException {
        UserTimestamp ut = null;
        int rows;
        try {
            ut = RMT2Date.getUserTimeStamp(this.getDaoUser());

            // Ensure that creditor is set to null for service item. Otherwise,
            // creditor will equal zero and a SQL referential integrity error
            // will occur.
            if (item.getItemTypeId() == InventoryDaoConst.ITEM_TYPE_SRVC) {
                item.setNull(ItemMaster.PROP_CREDITORID);
            }
            item.setDateUpdated(ut.getDateCreated());
            item.setUserId(ut.getLoginId());
            item.setIpUpdated(ut.getIpAddr());
            item.addCriteria(ItemMaster.PROP_ITEMID, item.getItemId());
            rows = this.client.updateRow(item);
            return rows;
        } catch (Exception e) {
            throw new InventoryDaoException(e);
        }
    }

    /**
     * Updates the vendor_items table using <i>vendofItem</i> as the source.
     * <p>
     * Only updates of an existing VendorItems object are performed via this
     * method. vendorItemObj must pass all business rule validations before
     * changes are successfully applied to the database.
     * 
     * @param vendorItem
     *            An instance of {@link VendorItems} object
     * @return The total number of items effected by the transaction
     * @throws InventoryDaoException
     *             <i>vendorItem</i> is null or general database access error
     */
    @Override
    public int maintain(VendorItemDto vendorItem) throws InventoryDaoException {
        VendorItems vi = InventoryDaoFactory.createVendorItemRmt2Orm(vendorItem);
        // Perform the actual update
        return this.updateVendorItem(vi);
    }

    /**
     * Applies the modificatons of an existing vendor item to the vendor_items
     * table.
     * 
     * @param item
     *            intance of {@link VendorItems}.
     * @return The total number of items updated from transaction.
     * @throws InventoryDaoException
     */
    private int updateVendorItem(VendorItems item) throws InventoryDaoException {
        int rows = 0;
        try {
            item.addCriteria(VendorItems.PROP_ITEMID, item.getItemId());
            item.addCriteria(VendorItems.PROP_CREDITORID, item.getCreditorId());
            rows = this.client.updateRow(item);
            return rows;
        } catch (Exception e) {
            throw new InventoryDaoException(e);
        }
    }

    /**
     * Maintains an item type record by persisting changes as either a database
     * insert or update operation.
     * 
     * @param itemType
     *            an instance of {@link ItemMasterTypeDto}
     * @return the new item type id for insert operations and the total number
     *         of rows effected for update opeartions.
     * @throws InventoryDaoException
     *             <i>ItemMasterTypeDto</i> is null or general database access
     *             error
     */
    @Override
    public int maintain(ItemMasterTypeDto itemType)
            throws InventoryDaoException {
        if (itemType == null) {
            throw new InventoryDaoException(
                    "Inventory DAO item type update error: ItemMasterTypeDto object is null");
        }
        int rc;
        ItemMasterType obj = InventoryDaoFactory
                .createItemTypeRmt2Orm(itemType);
        if (obj.getItemTypeId() <= 0) {
            rc = this.insertItemType(obj);
        }
        else {
            rc = this.updateItemType(obj);
        }
        return rc;
    }

    /**
     * Adds an inventory item type to the database. Upon successful completion,
     * the caller can interrogate the <i>itemType</i> object for property values
     * that were updated by this method including its item type id property.
     * 
     * @param itemType
     *            an instance of {@link ItemMasterType} object
     * @return The id of the new inventory item.
     * @throws InventoryDaoException
     */
    private int insertItemType(ItemMasterType itemType)
            throws InventoryDaoException {
        UserTimestamp ut = null;
        int newId;

        // TODO: Add code to API to ensure that item type has a description.
        try {
            ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            itemType.setDateCreated(ut.getDateCreated());
            itemType.setDateUpdated(ut.getDateCreated());
            itemType.setUserId(ut.getLoginId());
            newId = this.client.insertRow(itemType, true);
            itemType.setItemTypeId(newId);
            return newId;
        } catch (Exception e) {
            throw new InventoryDaoException(e);
        }
    }

    /**
     * Updates the inventory item type object.
     * 
     * @param itemType
     *            intance of {@link ItemMasterType}.
     * @return The total number of item type objects updated from transaction.
     * @throws InventoryDaoException
     */
    private int updateItemType(ItemMasterType itemType)
            throws InventoryDaoException {
        UserTimestamp ut = null;
        int rows;

        // TODO: Add code to API to ensure that item type has a description.
        try {
            ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            itemType.setDateUpdated(ut.getDateCreated());
            itemType.setUserId(ut.getLoginId());
            itemType.addCriteria(ItemMasterType.PROP_ITEMTYPEID,
                    itemType.getItemTypeId());
            rows = this.client.updateRow(itemType);
            return rows;
        } catch (Exception e) {
            throw new InventoryDaoException(e);
        }
    }

    /**
     * Maintains an item status record by persisting changes as either a
     * database insert or update operation.
     * 
     * @param itemType
     *            an instance of {@link ItemMasterStatusDto}
     * @return the new item status id for insert operations and the total number
     *         of rows effected for update opeartions.
     * @throws InventoryDaoException
     *             <i>status</i> is null or general database access error
     */
    @Override
    public int maintain(ItemMasterStatusDto status)
            throws InventoryDaoException {
        if (status == null) {
            throw new InventoryDaoException(
                    "Inventory DAO item status update error: ItemMasterStatusDto object is null");
        }
        int rc;
        ItemMasterStatus obj = InventoryDaoFactory
                .createItemStatusRmt2Orm(status);
        if (obj.getItemStatusId() <= 0) {
            rc = this.insertItemStatus(obj);
        }
        else {
            rc = this.updateItemStatus(obj);
        }
        return rc;
    }

    /**
     * Adds an inventory item status to the database. Upon successful
     * completion, the caller can interrogate the <i>itemStatus</i> object for
     * property values that were updated by this method including its item type
     * id property.
     * 
     * @param itemStatus
     *            an instance of {@link ItemMasterStatus} object
     * @return The id of the new inventory item status.
     * @throws InventoryDaoException
     */
    private int insertItemStatus(ItemMasterStatus itemStatus)
            throws InventoryDaoException {
        UserTimestamp ut = null;
        int newId;

        // TODO: Add code to API to ensure that item status has a description.
        try {
            ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            itemStatus.setDateCreated(ut.getDateCreated());
            itemStatus.setDateUpdated(ut.getDateCreated());
            itemStatus.setUserId(ut.getLoginId());
            newId = this.client.insertRow(itemStatus, true);
            itemStatus.setItemStatusId(newId);
            return newId;
        } catch (Exception e) {
            throw new InventoryDaoException(e);
        }
    }

    /**
     * Updates the inventory item status object.
     * 
     * @param itemStatus
     *            intance of {@link ItemMasterStatus}.
     * @return The total number of item status objects updated from transaction.
     * @throws InventoryDaoException
     */
    private int updateItemStatus(ItemMasterStatus itemStatus)
            throws InventoryDaoException {
        UserTimestamp ut = null;
        int rows;

        // TODO: Add code to API to ensure that item status has a description.
        try {
            ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            itemStatus.setDateUpdated(ut.getDateCreated());
            itemStatus.setUserId(ut.getLoginId());
            itemStatus.addCriteria(ItemMasterStatus.PROP_ITEMSTATUSID,
                    itemStatus.getItemStatusId());
            rows = this.client.updateRow(itemStatus);
            return rows;
        } catch (Exception e) {
            throw new InventoryDaoException(e);
        }
    }

    /**
     * Maintains an item status history record by persisting changes as either a
     * database insert or update operation.
     * 
     * @param itemStatHist
     *            an instance of {@link ItemMasterStatusHistDto}
     * @return the new item status history id for insert operations and the
     *         total number of rows effected for update opeartions.
     * @throws InventoryDaoException
     *             <i>itemStatHist</i> is null or general database access error
     */
    @Override
    public int maintain(ItemMasterStatusHistDto itemStatHist)
            throws InventoryDaoException {
        if (itemStatHist == null) {
            throw new InventoryDaoException(
                    "Inventory DAO item status history update error: ItemMasterStatusHistDto object is null");
        }
        int rc;
        ItemMasterStatusHist obj = InventoryDaoFactory
                .createItemStatusHistoryRmt2Orm(itemStatHist);
        if (obj.getItemStatusHistId() <= 0) {
            rc = this.insertItemStatusHistory(obj);
        }
        else {
            rc = this.updateItemStatusHistory(obj);
        }
        return rc;
    }

    /**
     * Adds an inventory item status history to the database. Upon successful
     * completion, the caller can interrogate the <i>itemStatus</i> object for
     * property values that were updated by this method including its item type
     * id property.
     * 
     * @param itemStatusHist
     *            an instance of {@link ItemMasterStatusHist} object
     * @return The id of the new inventory item status.
     * @throws InventoryDaoException
     */
    private int insertItemStatusHistory(ItemMasterStatusHist itemStatusHist)
            throws InventoryDaoException {
        UserTimestamp ut = null;
        int newId;

        // TODO: Add code to API to ensure that item status item id, item status
        // id, and effective data
        try {
            ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            itemStatusHist.setDateCreated(ut.getDateCreated());
            itemStatusHist.setEffectiveDate(ut.getDateCreated());
            itemStatusHist.setUserId(ut.getLoginId());
            itemStatusHist.setIpCreated(ut.getIpAddr());
            itemStatusHist.setIpUpdated(ut.getIpAddr());
            newId = this.client.insertRow(itemStatusHist, true);
            itemStatusHist.setItemStatusHistId(newId);
            return newId;
        } catch (Exception e) {
            throw new InventoryDaoException(e);
        }
    }

    /**
     * Updates the inventory item status history object.
     * 
     * @param itemStatusHist
     *            intance of {@link ItemMasterStatusHist}.
     * @return The total number of item status objects updated from transaction.
     * @throws InventoryDaoException
     */
    private int updateItemStatusHistory(ItemMasterStatusHist itemStatusHist)
            throws InventoryDaoException {
        UserTimestamp ut = null;
        int rows;

        // TODO: Add code to API to ensure that item status item id, item status
        // id, and effective data
        try {
            ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            itemStatusHist.setEndDate(ut.getDateCreated());
            itemStatusHist.setUserId(ut.getLoginId());
            itemStatusHist.setIpUpdated(ut.getIpAddr());
            itemStatusHist.addCriteria(
                    ItemMasterStatusHist.PROP_ITEMSTATUSHISTID,
                    itemStatusHist.getItemStatusHistId());
            rows = this.client.updateRow(itemStatusHist);
            return rows;
        } catch (Exception e) {
            throw new InventoryDaoException(e);
        }
    }

    /**
     * Delete one or more inventory item master objects from the item_master
     * database table.
     * <p>
     * A filter can be applied to the query using one or more properties
     * available in <i>criteria</i> as selection criteria. The following
     * properties can be used build selection criteria: Item Id, Item Serial
     * Number, Item Type Id, Creditor Id, Vendor Item Number, and/or Item
     * Descritpion.
     * <p>
     * Set <i>criteria</i> to null when the desire arises to retrieve all items.
     * 
     * @param criteria
     *            An instance of {@link ItemMasterDto} where each property
     *            containing a value is used to build selection criteria for the
     *            sake querying a data source. The properties, itemId,
     *            itemSerialNo, itemTypeId, creditorId, vendorItemNo, and
     *            itemName are the only properties recognized when building
     *            selection criteria. To delete all rows in the item_master
     *            table, set this argument to null.
     * @return The number of rows effected by the delete transaction.
     * @throws InventoryDaoException
     *             General data access errors
     */
    @Override
    public int delete(ItemMasterDto criteria) throws InventoryDaoException {
        ItemMaster obj = InventoryDaoFactory.createCriteria(criteria);
        return this.client.deleteRow(obj);
    }

    /**
     * Delete one or more inventory item type objects from the item_master_type
     * database table.
     * <p>
     * A filter can be applied to the query using one or more properties
     * available in <i>criteria</i> as selection criteria. The following
     * properties can be used build selection criteria: Item Type Id and Item
     * Type Description.
     * <p>
     * Set <i>criteria</i> to null when the desire arises to delete all items.
     * 
     * @param criteria
     *            an instance of {@link ItemMasterTypeDto} where each non-null
     *            property is considered to be targeted as selection criteria.
     *            The properties, itemTypeId and itemTypeDescription are the
     *            only properties recognized when building selection criteria.
     *            To delete all rows in the item_master_type table, set this
     *            argument to null.
     * @return The number of rows effected by the delete transaction.
     * @throws InventoryDaoException
     *             General data access errors
     */
    @Override
    public int delete(ItemMasterTypeDto criteria) throws InventoryDaoException {
        ItemMasterType obj = InventoryDaoFactory.createCriteria(criteria);
        return this.client.deleteRow(obj);
    }

    /**
     * Delete one or more inventory item status objects from the
     * item_master_status database table.
     * <p>
     * A filter can be applied to the query using one or more properties
     * available in <i>criteria</i> as selection criteria. The following
     * properties can be used build selection criteria: Item Status Id and Item
     * Status Description.
     * <p>
     * Set <i>criteria</i> to null when the desire arises to delete all items.
     * 
     * @param criteria
     *            an instance of {@link ItemMasterStatusDto} where each non-null
     *            property is considered to be targeted as selection criteria.
     *            The properties, entityId and entityName are the only
     *            properties recognized when building selection criteria.
     *            <i>entityId</i> and <i>entityName</i> are considered common
     *            object properties and are mapped to the item_status_id and
     *            description columns belonging to the item_master_status table.
     *            To delete all rows in the item_master_type table, set this
     *            argument to null.
     * @return The number of rows effected by the delete transaction.
     * @throws InventoryDaoException
     *             General data access errors
     */
    @Override
    public int delete(ItemMasterStatusDto criteria)
            throws InventoryDaoException {
        ItemMasterStatus obj = InventoryDaoFactory.createCriteria(criteria);
        return this.client.deleteRow(obj);
    }

    /**
     * Delete one or more inventory item status objects from the
     * item_master_status_hist database table.
     * <p>
     * A filter can be applied to the query using one or more properties
     * available in <i>criteria</i> as selection criteria. The following
     * properties can be used build selection criteria: Item Status History Id,
     * Item Id, and Item Status Id.
     * <p>
     * Set <i>criteria</i> to null when the desire arises to delete all items.
     * 
     * @param criteria
     *            an instance of {@link ItemMasterStatusHistDto} where each
     *            non-null property is considered to be targeted as selection
     *            criteria. The properties, entityId, itemId, and itemStatusId
     *            are the only properties recognized when building selection
     *            criteria. <i>entityId</i> is considered as a common object
     *            property and is mapped to the item_status_hist_id column which
     *            belongs to the item_master_status_hist table. To delete all
     *            rows in the item_master_type table, set this argument to null.
     * @return The number of rows effected by the delete transaction.
     * @throws InventoryDaoException
     *             General data access errors
     */
    @Override
    public int delete(ItemMasterStatusHistDto criteria)
            throws InventoryDaoException {
        ItemMasterStatusHist obj = InventoryDaoFactory.createCriteria(criteria);
        return this.client.deleteRow(obj);
    }

    /**
     * Deleted a vendor/item assoication from the vendor_items table by vendor
     * id and item id.
     * 
     * @param vendorItem
     *            an instance of {@link VendorItemDto} where the vendor id and
     *            the item id are used as selection criteria.
     * @return the total number of items deleted
     * @throws InventoryDaoException
     *             Database error
     */
    @Override
    public int delete(VendorItemDto vendorItem) throws InventoryDaoException {
        if (vendorItem == null) {
            throw new InventoryDaoException(
                    "Vendor Item delet failed due to input vendor item object is null");
        }
        int rows;
        try {
            VendorItems item = new VendorItems();
            item.addCriteria(VendorItems.PROP_ITEMID, vendorItem.getItemId());
            item.addCriteria(VendorItems.PROP_CREDITORID,
                    vendorItem.getVendorId());
            rows = this.client.deleteRow(item);
            return rows;
        } catch (Exception e) {
            throw new InventoryDaoException(e);
        }
    }

}
