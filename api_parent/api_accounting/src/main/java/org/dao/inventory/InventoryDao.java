package org.dao.inventory;

import java.util.List;

import org.dto.ItemAssociationDto;
import org.dto.ItemMasterDto;
import org.dto.ItemMasterStatusDto;
import org.dto.ItemMasterStatusHistDto;
import org.dto.ItemMasterTypeDto;
import org.dto.VendorItemDto;

import com.api.persistence.DaoClient;

/**
 * Interface that provides the method prototypes for querying, creating,
 * modifying, and deleting inventory related data.
 * 
 * @author Roy Terrell
 * 
 */
public interface InventoryDao extends DaoClient {

    /**
     * Retrieves a list of inventory items based on the custom selection
     * criteria supplied by the user. User is responsible for using the
     * appropriate SQL predicate syntax in order to successfully retrieve the
     * data.
     * 
     * @param criteria
     *            The selection criteria to apply to the query of data source.
     * @returnA List of {@link ItemMasterDto} objects.
     * @throws InventoryDaoException
     */
    List<ItemMasterDto> fetch(String criteria) throws InventoryDaoException;

    /**
     * Retrieves one or more inventory items using the individual properties
     * contained in <i>criteria</i> as selection criteria.
     * 
     * @param criteria
     *            An instance of {@link ItemMasterDto} where each property
     *            containing a value is used to build selection criteria for the
     *            sake querying a data source.
     * @return A List of ItemMasterDto objects representing one or more items or
     *         null when no data is found.
     * @throws InventoryDaoException
     *             General data access errors
     */
    List<ItemMasterDto> fetch(ItemMasterDto criteria)
            throws InventoryDaoException;

    /**
     * Retrieves one or more extended data inventory items using the individual
     * properties contained in <i>criteria</i> as selection criteria.
     * 
     * @param criteria
     *            An instance of {@link ItemMasterDto} where each property
     *            containing a value is used to build selection criteria for the
     *            sake querying a data source.
     * @return A List of ItemMasterDto objects representing one or more items or
     *         null when no data is found.
     * @throws InventoryDaoException
     *             General data access errors
     */
    List<ItemMasterDto> fetchExt(ItemMasterDto criteria) throws InventoryDaoException;

    /**
     * Retrieves one or more vendor inventory item objects using various
     * selection criteria.
     * 
     * @param criteria
     *            an instance of {@linkplain VendorItemDto} where each property
     *            containing a value is used to build selection criteria for the
     *            sake of querying a data source.
     * @return A List of one or more instances of {@link VendorItemDto} or null
     *         when no data is found.
     * @throws InventoryDaoException
     *             General data access errors
     */
    List<VendorItemDto> fetch(VendorItemDto criteria)
            throws InventoryDaoException;

    /**
     * Retrieves one or more inventory item type objects using varous selection
     * criteria
     * 
     * @param criteria
     *            an instance of {@link ItemMasterTypeDto} where each non-null
     *            property is considered to be targeted as selection criteria.
     * @return a List of {@link ItemMasterTypeDto} or null when no data is
     *         found.
     * @throws InventoryDaoException
     *             General data access errors
     */
    List<ItemMasterTypeDto> fetch(ItemMasterTypeDto criteria)
            throws InventoryDaoException;

    /**
     * Retrieves one inventory item status objects using varous selection
     * criteria
     * 
     * @param criteria
     *            an instance of {@link ItemMasterStatusDto} where each non-null
     *            property is considered to be targeted as selection criteria.
     * @return a List of {@link ItemMasterStatusDto} or null when no data is
     *         found
     * @throws InventoryDaoException
     *             General data access errors
     */
    List<ItemMasterStatusDto> fetch(ItemMasterStatusDto criteria)
            throws InventoryDaoException;

    /**
     * Retrieves one inventory item status history objects using varous
     * selection criteria
     * 
     * @param criteria
     *            an instance of {@link ItemMasterStatusHistDto} where each
     *            non-null property is considered to be targeted as selection
     *            criteria.
     * @return a List of {@link ItemMasterStatusHistDto} or null when no data is
     *         found
     * @throws InventoryDaoException
     *             General data access errors
     */
    List<ItemMasterStatusHistDto> fetch(ItemMasterStatusHistDto criteria)
            throws InventoryDaoException;

    /**
     * Retrieves the current status of an item based on the item's id.
     * 
     * @param itemId
     *            The id of the item to retreive its current status.
     * @return An {@link ItemMasterStatusHistDto} object.
     * @throws InventoryDaoException
     */
    ItemMasterStatusHistDto fetchCurrentItemStatusHistory(int itemMasterId)
            throws InventoryDaoException;

    /**
     * Retrieves one inventory item association objects using varous selection
     * criteria
     * 
     * @param criteria
     *            an instance of {@link ItemAssociationDto} where each non-null
     *            property is considered to be targeted as selection criteria.
     * @return a List of {@link ItemAssociationDto} or null when no data is
     *         found
     * @throws InventoryDaoException
     *             General data access errors
     */
    List<ItemAssociationDto> fetch(ItemAssociationDto criteria)
            throws InventoryDaoException;

    /**
     * Initiates the maintenance of an Item Master Entity. If the id property of
     * <I>item</i> is zero, then an Item master entity is created. Otherwise,
     * the Item Master entity is updated.
     * 
     * @param item
     *            an instance of {@link ItemMasterDto} representing the
     *            inventory item to be updated.
     * @return The total number of items effected by the transaction
     * @throws InventoryDaoException
     *             General data access errors
     */
    int maintain(ItemMasterDto item) throws InventoryDaoException;

    /**
     * Initiates the maintenance of an Vendor Item Entity. Only updates of an
     * existing VendorItems object are performed via this method. vendorItemObj
     * must pass all business rule validations before changes are successfully
     * applied to the database.
     * 
     * @param vendorItem
     *            An instance of {@link VendorItems} object
     * @param add
     *            indicates if <i>vendorItem</i> will be added (true) or updated
     *            (false).
     * @return The total number of items effected by the transaction
     * @throws InventoryDaoException
     *             General data access errors
     */
    int maintain(VendorItemDto vendorItem, boolean add) throws InventoryDaoException;

    /**
     * Initiates the maintenance of an Item Type Entity. If the id property of
     * <I>itemType</i> is zero, then an Item type entity is created. Otherwise,
     * the Item Type entity is updated.
     * 
     * @param itemType
     *            an instance of {@link ItemMasterTypeDto} representing the
     *            inventory item type to be updated.
     * @return The total number of item types effected by the transaction
     * @throws InventoryDaoException
     *             General data access errors
     */
    int maintain(ItemMasterTypeDto itemType) throws InventoryDaoException;

    /**
     * Initiates the maintenance of an Item Status Entity. If the id property of
     * <I>status</i> is zero, then an Item status entity is created. Otherwise,
     * the Item Status entity is updated.
     * 
     * @param status
     *            an instance of {@link ItemMasterStatusDto} representing the
     *            inventory item status to be updated.
     * @return The total number of item status entities effected by the
     *         transaction
     * @throws InventoryDaoException
     *             General data access errors
     */
    int maintain(ItemMasterStatusDto status) throws InventoryDaoException;

    /**
     * Initiates the maintenance of an Item Status History Entity. If the id
     * property of <I>statHist</i> is zero, then an Item status history entity
     * is created. Otherwise, the Item Status History entity is updated.
     * 
     * @param statHist
     *            an instance of {@link ItemMasterStatusHistDto} representing
     *            the inventory item status history to be updated.
     * @return The total number of item status history entities effected by the
     *         transaction
     * @throws InventoryDaoException
     *             General data access errors
     */
    int maintain(ItemMasterStatusHistDto statHist) throws InventoryDaoException;

    /**
     * Removes one or more inventory items from the database.
     * 
     * @param criteria
     *            an instance of {@link ItemMasterDto} containg criteria used to
     *            delete one or more inventory items.
     * @return the total number of items deleted
     * @throws InventoryDaoException
     *             when itemId is associated with one or more sales orders, or a
     *             database error occurred.
     */
    int delete(ItemMasterDto criteria) throws InventoryDaoException;

    /**
     * Removes one or more inventory item types from the database.
     * 
     * @param criteria
     *            an instance of {@link ItemMasterTypeDto} containg criteria
     *            used to delete one or more inventory items.
     * @return the total number of items deleted
     * @throws InventoryDaoException
     *             when itemId is associated with one or more sales orders, or a
     *             database error occurred.
     */
    int delete(ItemMasterTypeDto criteria) throws InventoryDaoException;

    /**
     * Removes one or more inventory item status entities from the database.
     * 
     * @param criteria
     *            an instance of {@link ItemMasterStatusDto} containg criteria
     *            used to delete one or more inventory items.
     * @return the total number of items deleted
     * @throws InventoryDaoException
     *             when itemId is associated with one or more sales orders, or a
     *             database error occurred.
     */
    int delete(ItemMasterStatusDto criteria) throws InventoryDaoException;

    /**
     * Removes one or more inventory item status history entities from the
     * database.
     * 
     * @param criteria
     *            an instance of {@link ItemMasterStatusHistDto} containg
     *            criteria used to delete one or more inventory items.
     * @return the total number of items deleted
     * @throws InventoryDaoException
     *             when itemId is associated with one or more sales orders, or a
     *             database error occurred.
     */
    int delete(ItemMasterStatusHistDto criteria) throws InventoryDaoException;

    /**
     * Removes a vendor/item assoication from the data source.
     * 
     * @param vendorItem
     *            an instance of {@link VendorItemDto}
     * @return the total number of items deleted
     * @throws InventoryDaoException
     */
    int delete(VendorItemDto vendorItem) throws InventoryDaoException;

}