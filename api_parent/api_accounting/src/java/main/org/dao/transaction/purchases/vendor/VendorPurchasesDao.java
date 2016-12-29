package org.dao.transaction.purchases.vendor;

import java.util.List;

import org.dao.transaction.XactDao;
import org.dto.PurchaseOrderDto;
import org.dto.PurchaseOrderItemDto;
import org.dto.PurchaseOrderStatusDto;
import org.dto.PurchaseOrderStatusHistDto;
import org.dto.VendorItemDto;
import org.dto.VwVendorItemDto;

/**
 * Interface for managing vendor purchases DAO interactions.  
 * <p>
 * Contains method declaration pertaining to querying, creating, updating, and deleting of purchases.
 * 
 * @author Roy Terrell
 * 
 */
public interface VendorPurchasesDao extends XactDao {

    /**
     * Fetch purchase orders in which the query is filterd by data contained in a criteria object.
     * 
     * @param criteria
     *            An instance of {@link PurchaseOrderDto} used to filter the result set
     * @return List of {@link PurchaseOrderDto} or null when no data is found.
     * @throws VendorPurchasesDaoException
     */
    List<PurchaseOrderDto> fetchPurchaseOrder(PurchaseOrderDto criteria)
            throws VendorPurchasesDaoException;

    /**
     * Fetch purchase order items in which the query is filterd by data contained in the criteria object.
     * 
     * @param criteria          
     *            An instance of {@link PurchaseOrderItemDto} used to filter the result set regarding purchase order item information.
     * @return List of {@link PurchaseOrderItemDto} or null when no data is found.
     * @throws VendorPurchasesDaoException
     */
    List<PurchaseOrderItemDto> fetchPurchaseOrderItem(PurchaseOrderItemDto criteria)
            throws VendorPurchasesDaoException;

    /**
     * Fetches vendor specfic inventory item data.
     * 
     * @param criteria
     *          an instance of {@link VendorItemDto}
     * @return List of {@link VendorItemDto} or null when no data is found.
     * @throws VendorPurchasesDaoException
     */
    List<VendorItemDto> fetchVendorItem(VendorItemDto criteria) throws VendorPurchasesDaoException;
    
    /**
     * Fetches either the item master version or the external vendor item version of an 
     * inventory item using vendor id and item id.
     * <p>
     * This can be used to fetch external items specific to a particular vendor.   
     * The query is filterd by data contained in <i>criteria</i> object.
     * 
     * @param criteria
     *          an instance of {@link VwVendorItemDto}
     * @return List of {@link VwVendorItemDto} or null when no data is found.
     * @throws VendorPurchasesDaoException
     */
    List<VwVendorItemDto> fetchLocalExternalItem(VwVendorItemDto criteria) throws VendorPurchasesDaoException;
    
    /**
     * Fetch combined purchase order item and vendor item as it pertains to a given vendor and purchase order.
     * 
     * @param vendorId 
     *          The vendor id
     * @param poId 
     *          The purchase order id
     * @return A List of {@link PurchaseOrderItemDto} or null when no data is found.
     * @throws VendorPurchasesDaoException
     */
    List<PurchaseOrderItemDto> fetchCombinedVendorPurchaseOrderItem(int vendorId, int poId) throws VendorPurchasesDaoException;
   
    /**
     * Fetches vendor/purchase order items that are not assoicated with a given purchase order.
     * 
     * @param vendorId
     *          the vendor id
     * @param poId
     *          the purchase order id
     * @return List of {@link VwVendorItemDto} or null when no data is found.
     * @throws VendorPurchasesDaoException
     */
    List<VwVendorItemDto> fetchUnassignedPurchaseOrderItems(int vendorId, int poId) throws VendorPurchasesDaoException;
    
    /**
     * Fetch a list of purchase order statuses based on given selection criteria.
     * 
     * @param criteria
     *          an instance of {@link PurchaseOrderStatusDto}
     * @return A List of {@link PurchaseOrderStatusDto} or null when no data is found.
     * @throws VendorPurchasesDaoException
     */
    List<PurchaseOrderStatusDto> fetchStatus(PurchaseOrderStatusDto criteria) throws VendorPurchasesDaoException;
    
    /**
     * Obtains the status history of a given purchase order.
     * <p>
     * Implementation should be flexible enough to fetch all statuses, a single status, or the current 
     * status of a purchase order.  The purchase order id in <i>criteria</i> is required in order to 
     * single out a specific purchase order.
     * 
     * @param criteria
     *          An instance of {@link PurchaseOrderStatusHistDto} to be used as a query filter.
     * @param currentStatusOnly
     *          Set to true when fetching the current purchase order status only.  Otherwise, set to false.          
     * @return A List of {@link PurchaseOrderStatusHistDto} objects or null if no data is found
     * @throws VendorPurchasesDaoException
     */
    List<PurchaseOrderStatusHistDto> fetchPurchaseOrderHistory(PurchaseOrderStatusHistDto criteria, boolean currentStatusOnly) throws VendorPurchasesDaoException;
    
    
    /**
     * Creates a new or modifies an existing purchase order.
     *  
     * @param po
     *           the base purchase order
     * @return the id of the new purchase order created or the total number of rows modifed when 
     *         an existing purchase order is updated.
     * @throws VendorPurchasesDaoException
     */
    int maintainPurchaseOrder(PurchaseOrderDto po) throws VendorPurchasesDaoException;
    
    /**
     * Creates a new or modifies an existing purchase order item.
     * 
     * @param item
     *          purchase order item.
     * @return the id of the new purchase order item created or the total number of rows modifed when 
     *         an existing purchase order item is updated.
     * @throws VendorPurchasesDaoException
     */
    int maintainPurchaseOrderItem(PurchaseOrderItemDto item) throws VendorPurchasesDaoException;
    
    /**
     * Deletes a purchase order and all of its items from the database.  
     * <p>
     * Purchase order can only be deleted from the system when in Quote status. 
     * It is required that <i>criteria</i> contains a purchase order id.  Otherwise, and error is thrown.
     * 
     * @param criteria
     *          An instance of {@link PurchaseOrderDto} representing the criteria used to delete the purchase order.
     * @return the total number of rows effected
     * @throws VendorPurchasesDaoException
     */
    int deletePurchaseOrder(PurchaseOrderDto criteria) throws VendorPurchasesDaoException;
    
    /**
     * Deletes one or more items belonging to a purchase order.
     * <p>
     * It is required that <i>criteria</i> contains a purchase order id.  Otherwise, and error is thrown.
     * 
     * @param criteria
     *          An instance of {@link PurchaseOrderItemDto} representing the criteria used to delete the purchase order item(s).
     * @return the total number of rows effected
     * @throws VendorPurchasesDaoException
     */
    int deletePurchaseOrderItem(PurchaseOrderItemDto criteria) throws VendorPurchasesDaoException;
    
    /**
     * Assigns a status to a purchase order.
     * 
     * @param dto 
     *          an instance of {@link PurchaseOrderStatusHistDto}
     * @param newStatusId 
     *          The id of status that is to be assigned to the purchase order.
     * @throws VendorPurchasesDaoException
     */
    void changePurchaseOrderStatus(PurchaseOrderStatusHistDto dto, int newStatusId) throws VendorPurchasesDaoException;
}