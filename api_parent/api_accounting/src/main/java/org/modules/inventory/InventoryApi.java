package org.modules.inventory;

import java.util.List;

import org.dto.ItemAssociationDto;
import org.dto.ItemMasterDto;
import org.dto.ItemMasterStatusDto;
import org.dto.ItemMasterStatusHistDto;
import org.dto.ItemMasterTypeDto;
import org.dto.VendorItemDto;

import com.api.foundation.TransactionApi;

/**
 * Interface that provides the method prototypes for querying, creating,
 * modifying, deleting, and general management of inventory system.
 * 
 * @author Roy Terrell
 * 
 */
public interface InventoryApi extends TransactionApi {

    /**
     * Retrieves one or more items using item type id
     * 
     * @param criteria
     *            An instance of {@link ItemMasterDto} representing the
     *            selection criteria.
     * @return A List of {@link ItemMasterDto} objects.
     * @throws InventoryApiException
     */
    List<ItemMasterDto> getItem(ItemMasterDto criteria)
            throws InventoryApiException;

    /**
     * Retrieves an ArrayList of of any inventory related object based on the
     * base view, base class, and custom criteria supplied by the user. User is
     * responsible for setting the base view and class so that the API will know
     * what data to retrieve.
     * 
     * @param criteria
     *            An instance of {@link ItemMasterDto} representing the
     *            selection criteria.
     * @returnA A List of {@link ItemMasterDto} objects which contains extended
     *          inventory master item data.
     * @throws InventoryApiException
     *             <i>criteria</i> contains invalid predicate or general data
     *             access error
     */
    List<ItemMasterDto> getItemExt(ItemMasterDto criteria) throws InventoryApiException;

    /**
     * Retrieves an item object by item id.
     * 
     * @param itemId
     *            The id of the item
     * @return An instance of {@link ItemMasterDto}
     * @throws InventoryApiException
     *             general data access error
     * @throws InvalidDataException
     *             <i>itemId</i> is invalid
     */
    ItemMasterDto getItemById(Integer itemId) throws InventoryApiException;

    /**
     * Retrieves one or more items using item type id
     * 
     * @param itemTypeId
     *            The item type id
     * @return A List of {@link ItemMasterDto} objects.
     * @throws InventoryApiException
     *             general data access error
     * @throws InvalidDataException
     *             <i>itemTypeId</i> is invalid
     */
    List<ItemMasterDto> getItemByType(Integer itemTypeId)
            throws InventoryApiException;

    /**
     * Retrieves one or more items using the id of the vendor.
     * 
     * @param vendorId
     *            The id of Vendor
     * @return A List of {@link ItemMasterDto} objects.
     * @throws InventoryApiException
     *             general data access error
     * @throws InvalidDataException
     *             <i>vendorId</i> is invalid
     */
    List<ItemMasterDto> getItemByVendorId(Integer vendorId)
            throws InventoryApiException;

    /**
     * Retrieves one or more items using the vendor's item number
     * 
     * @param vendItemNo
     *            The vendor's verison of the item number.
     * @return A List of {@link ItemMasterDto} objects.
     * @throws InventoryApiException
     *             general data access error
     * @throws InvalidDataException
     *             <i>vendItemNo</i> is invalid
     */
    List<ItemMasterDto> getItemByVendorItemNo(String vendItemNo)
            throws InventoryApiException;

    /**
     * Retrieves one or more items using the item's serial number
     * 
     * @param serialNo
     *            The serial number of the item.
     * @return A List of {@link ItemMasterDto} objects.
     * @throws InventoryApiException
     *             general data access error
     * @throws InvalidDataException
     *             <i>serialNo</i> is invalid
     */
    List<ItemMasterDto> getItemBySerialNo(String serialNo)
            throws InventoryApiException;

    /**
     * Retrieves an ArrayList of of any inventory related object based on the
     * base view, base class, and custom criteria supplied by the user. User is
     * responsible for setting the base view and class so that the API will know
     * what data to retrieve.
     * 
     * @param criteria
     *            The selection criteria to apply to the query of data source.
     * @returnA A List of {@link ItemMasterDto} objects.
     * @throws InventoryApiException
     *             <i>criteria</i> contains invalid predicate or general data
     *             access error
     */
    List<ItemMasterDto> getItem(String criteria) throws InventoryApiException;

    /**
     * Retrieves Item Type data using various selection criteria.
     * 
     * @param criteria
     *            Instance of {@link ItemMasterTypeDto}
     * @return A List of {@link ItemMasterTypeDto} objects.
     * @throws InventoryApiException
     *             general data access error
     */
    List<ItemMasterTypeDto> getItemType(ItemMasterTypeDto criteria)
            throws InventoryApiException;

    /**
     * Retrieves Item Type data using item type id.
     * 
     * @param itemTypeId
     *            The id of the item type/
     * @return An instance of {@link ItemMasterTypeDto}
     * @throws InventoryApiException
     *             general data access error
     * @throws InvalidDataException
     *             <i>itemTypeId</i> is invalid
     */
    ItemMasterTypeDto getItemTypeById(Integer itemTypeId)
            throws InventoryApiException;

    /**
     * Retrieve one or more item type objects using criteria.
     * 
     * @param itemName
     *            The name of the item
     * @return List of {@link ItemMasterTypeDto} objects representing one or
     *         more item types.
     * @throws InventoryApiException
     *             general data access error
     * @throws InvalidDataException
     *             <i>itemName</i> is invalid or null
     */
    List<ItemMasterTypeDto> getItemTypes(String itemName)
            throws InventoryApiException;

    /**
     * Retrieves a list of item master statuses using custom selection criteria.
     * 
     * @param criteria
     *            an instance of {@link ItemMasterStatusDto}
     * @return List of {@link ItemMasterStatusDto} objects
     * @throws InventoryApiException
     */
    List<ItemMasterStatusDto> getItemStatus(ItemMasterStatusDto criteria)
            throws InventoryApiException;

    /**
     * Retrieves a list of item master statuses based on status name.
     * 
     * @param statusName
     *            The name of status to retrieve
     * @return List of {@link ItemMasterStatusDto} objects representing one or
     *         more item statuses.
     * @throws InventoryApiException
     *             general data access error
     * @throws InvalidDataException
     *             <i>statusName</i> is invalid or null
     */
    List<ItemMasterStatusDto> getItemStatus(String statusName)
            throws InventoryApiException;

    /**
     * Retrieves Item master status by status id.
     * 
     * @param itemStatusId
     * @return An {@link ItemMasterStatusDto} object representing an item
     *         status.
     * @throws InventoryApiException
     *             general data access error
     * @throws InvalidDataException
     *             <i>itemStatusId</i> is invalid
     */
    ItemMasterStatusDto getItemStatusById(Integer itemStatusId)
            throws InventoryApiException;

    /**
     * Retrieves one or more item status history items using custom selection
     * criteria.
     * 
     * @param criteria
     *            An instance of {@link ItemMasterStatusHistDto}
     * @return List of {@link ItemMasterStatusDto} objects
     * @throws InventoryApiException
     */
    List<ItemMasterStatusHistDto> getItemStatusHist(
            ItemMasterStatusHistDto criteria) throws InventoryApiException;

    /**
     * Retrieves one or more item status history items by item id.
     * 
     * @param id
     *            The id of the item to retrieve its statuses.
     * @return List of {@link ItemMasterStatusDto} objects
     * @throws InventoryApiException
     *             general data access error
     * @throws InvalidDataException
     *             <i>itemId</i> is invalid
     */
    List<ItemMasterStatusHistDto> getItemStatusHistByItemId(Integer itemId)
            throws InventoryApiException;

    /**
     * Retrieves the current status of an item based on the item's id.
     * 
     * @param itemId
     *            The id of the item to retreive its current status.
     * @return An instance of of {@link ItemMasterStatusDto}
     * @throws InventoryApiException
     *             general data access error
     * @throws InvalidDataException
     *             <i>itemId</i> is invalid
     */
    ItemMasterStatusHistDto getCurrentItemStatusHist(Integer itemId)
            throws InventoryApiException;

    /**
     * Retrieves one vendor item object using vendorId and itemId
     * 
     * @param vendorId
     *            The id of the vendor
     * @param itemId
     *            The id of the inventory item
     * @return List of arbitrary objects representing one or more vendor items.
     * @throws InventoryApiException
     */
    List<VendorItemDto> getVendorItem(Integer vendorId, Integer itemId)
            throws InventoryApiException;

    /**
     * Retrieves those inventory items that are assigned to a particular vendor
     * from the database.
     * 
     * @param vendorId
     *            The id of the target vendor.
     * @return List of arbitrary objects representing one or more vendor items.
     * @throws InventoryApiException
     */
    List<VendorItemDto> getVendorAssignItems(Integer vendorId)
            throws InventoryApiException;

    /**
     * Retrieves those inventory items that are not assigned to a particular
     * vendor.
     * 
     * @param vendorId
     *            The id of the target vendor.
     * @return List of {@link ItemMasterDto} objects representing one or more
     *         unassigned vendor items.
     * @throws InventoryApiException
     */
    List<ItemMasterDto> getVendorUnassignItems(Integer vendorId)
            throws InventoryApiException;

    /**
     * Fetch all item associations pertaining to sales orders and purchase
     * orders.
     * 
     * @param itemId
     *            The id of the item to query.
     * @return A list of {@link ItemAssociationDto}
     * @throws InventoryApiException
     */
    List<ItemAssociationDto> getItemAssociations(Integer itemId)
            throws InventoryApiException;

    /**
     * Adds a new or modifies an existing inventory item. If the id of the item
     * object is zero, then an inventory item is created. Otherwise, the
     * inventory item is updated.
     * 
     * @param item
     *            the inventory item to create or update
     * @return The id of the item maintained.
     * @throws InventoryApiException
     */
    int updateItemMaster(ItemMasterDto item) throws InventoryApiException;

    /**
     * Adds a new or modifies an existing vendor item. If the id of the item
     * object is zero, then a vendor item is created. Otherwise, the vendor item
     * is updated.
     * 
     * @param item
     *            The {@link VendorItemDto} object to create or update.
     * @return The id of the vendor item maintained.
     * @throws InventoryApiException
     */
    int updateVendorItem(VendorItemDto item) throws InventoryApiException;

    /**
     * Removes ainventory item from the database.
     * 
     * @param itemId
     *            The id of the item to delete
     * @return 1 for success
     * @throws InventoryApiException
     *             when itemId is associated with one or more sales orders, or a
     *             database error occurred.
     */
    int deleteItemMaster(Integer itemId) throws InventoryApiException;

    /**
     * Increases the count of an item in inventory.
     * 
     * @param itemId
     *            The id of the target item
     * @param qty
     *            The quantity to increase the inventory item by.
     * @return The dollar value of the item's inventory after the quantity
     *         increase.
     * @throws InventoryApiException
     */
    double pushInventory(Integer itemId, Integer qty) throws InventoryApiException;

    /**
     * Decreases the count of an item in inventory.
     * 
     * @param itemId
     *            The id of the target item
     * @param qty
     *            The quantity to decrease the inventory item by.
     * @return The dollar value of the item's inventory after the quantity
     *         decrease.
     * @throws InventoryApiException
     */
    double pullInventory(Integer itemId, Integer qty) throws InventoryApiException;

    /**
     * Deactivates an inventory item.
     * 
     * @param itemId
     *            The id of an inventory item.
     * @return 1 for success.
     * @throws InventoryApiException
     *             <i>itemId</i> does not exist in the system or a database error
     *             occurred or is already deactivated.
     */
    int deactivateItemMaster(Integer itemId) throws InventoryApiException;

    /**
     * Activates an inventory item.
     * 
     * @param itemId
     *            The id of an inventory item.
     * @return 1 for success.
     * @throws InventoryApiException
     *             <i>itemId</i> does not exist in the system or a database error
     *             occurred or is already activated.
     */
    int activateItemMaster(Integer itemId) throws InventoryApiException;

    /**
     * Associates one or more inventory items with a vendor.
     * 
     * @param vendorId
     *            The id of the vendor
     * @param items
     *            A list inventory item id's
     * @return The number of items assigned to the vendor.
     * @throws InventoryApiException
     */
    int assignVendorItems(Integer vendorId, Integer[] items)
            throws InventoryApiException;

    /**
     * Disassociates one or more inventory items from a vendor.
     * 
     * @param vendorId
     *            The id of the vendor
     * @param items
     *            A list inventory item id's
     * @return The number of items unassigned from the vendor.
     * @throws InventoryApiException
     */
    int removeVendorItems(Integer vendorId, Integer[] items) throws InventoryApiException;

    /**
     * Changes the override flag to true for one or more of a vendor's items.
     * 
     * @param vendorId
     *            The id of the vendor that will be assoicated with each item
     *            id.
     * @param items
     *            Collection containing one or more item_master id's to
     *            override.
     * @return The total number of rows effected by the database transaction.
     * @throws InventoryApiException
     */
    int addInventoryOverride(Integer vendorId, Integer[] items)
            throws InventoryApiException;

    /**
     * Changes the override flag to false for one or more of a vendor's items..
     * 
     * @param vendorId
     *            The id of the vendor that will be disassoicated with each item
     *            id.
     * @param items
     *            Collection containing one or more item_master id's to
     *            deactivate item overrides.
     * @return The total number of rows effected by the database transaction.
     * @throws InventoryApiException
     */
    int removeInventoryOverride(Integer vendorId, Integer items[])
            throws InventoryApiException;

}