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
    ItemMasterDto getItemById(int itemId) throws InventoryApiException;

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
    List<ItemMasterDto> getItemByType(int itemTypeId)
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
    List<ItemMasterDto> getItemByVendorId(int vendorId)
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
    ItemMasterTypeDto getItemTypeById(int itemTypeId)
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
    ItemMasterStatusDto getItemStatusById(int itemStatusId)
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
    List<ItemMasterStatusHistDto> getItemStatusHistByItemId(int itemId)
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
    ItemMasterStatusHistDto getCurrentItemStatusHist(int itemId)
            throws InventoryApiException;

    /**
     * Retrieves one vendor item object using vendorId and itemId
     * 
     * @param vendorId
     *            The id of the vendor
     * @param itemId
     *            The id of the inventory item
     * @return An arbitrary object representing a vendor items item.
     * @throws InventoryApiException
     */
    VendorItemDto getVendorItem(Integer vendorId, Integer itemId)
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
     * Fetch all the different entities an item is assoicated with.
     * 
     * @param itemId
     *            The id of the item to query.
     * @return An arbitrary item representing the assoications.
     * @throws InventoryApiException
     */
    List<ItemAssociationDto> getItemAssociations(int itemId)
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
    int deleteItemMaster(int itemId) throws InventoryApiException;

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
    double pushInventory(int itemId, int qty) throws InventoryApiException;

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
    double pullInventory(int itemId, int qty) throws InventoryApiException;

    /**
     * Deactivates an inventory item.
     * 
     * @param itemId
     *            The id of an inventory item.
     * @return 1 for success.
     * @throws InventoryApiException
     *             itemId does not exist in the system or a database error
     *             occurred.
     */
    int deactivateItemMaster(int itemId) throws InventoryApiException;

    /**
     * Activates an inventory item.
     * 
     * @param itemId
     *            The id of an inventory item.
     * @return 1 for success.
     * @throws InventoryApiException
     *             itemId does not exist in the system or a database error
     *             occurred.
     */
    int activateItemMaster(int itemId) throws InventoryApiException;

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
    int assignVendorItems(int vendorId, int items[])
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
    int removeVendorItems(int vendorId, int items[])
            throws InventoryApiException;

    // /**
    // * This method activates a vendor-item override targeting the inventory
    // * item, itemId. An override instructs the system to obtain pricing
    // * information for an inventory item from the vendor_items table instead
    // of
    // * the item_master table . This method puts this concept into effect.
    // *
    // * @param vendorId
    // * The id of the vendor that will be assoicated with an item in
    // * the item_master table.
    // * @param itemId
    // * The target item.
    // * @return The total number of rows effected by the database transaction.
    // * This is ususally 1.
    // * @throws InventoryApiException
    // */
    // int addInventoryOverride(int vendorId, int itemId)
    // throws InventoryApiException;

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
    int addInventoryOverride(int vendorId, int items[])
            throws InventoryApiException;

    // /**
    // * This method deactivates a vendor-item override targeting the inventory
    // * item, itemId. An override instructs the system to obtain pricing
    // * information for an inventory item from the vendor_items table instead
    // of
    // * the item_master table . This method renders this concept ineffective.
    // *
    // * @param vendorId
    // * The id of the vendor that will be disassoicated with the item
    // * id.
    // * @param itemId
    // * The target item.
    // * @return The total number of rows effected by the database transaction.
    // * This is ususally 1.
    // * @throws InventoryApiException
    // */
    // int removeInventoryOverride(int vendorId, int itemId)
    // throws InventoryApiException;

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
    int removeInventoryOverride(int vendorId, int items[])
            throws InventoryApiException;

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
    // * @throws InventoryApiException
    // * If newItemStatusId is out of sequence, if a database error
    // * occurs, or a system error occurs.
    // */
    // ItemMasterStatusHistDto changeItemStatus(ItemMasterDto item,
    // int newItemStatusId) throws InventoryApiException;
}