package org.modules.transaction.purchases.vendor;

import java.util.List;

import org.dto.PurchaseOrderDto;
import org.dto.PurchaseOrderItemDto;
import org.dto.PurchaseOrderStatusDto;
import org.dto.PurchaseOrderStatusHistDto;
import org.dto.VendorItemDto;
import org.dto.VwVendorItemDto;
import org.modules.transaction.XactApi;

/**
 * Iterface for managing vendor purchase transactions on account.
 * <p>
 * When a purchase order is submitted, the base transaction amount is posted to
 * the transaction datasource as a positive value, and the creditor activity
 * amount is posted as positive value which increases the value of the
 * creditor's account. Conversely, when a purchase order is cancelled, the base
 * transaction amount is posted to the xact table as a negative value, and the
 * creditor activity amount is posted as negative value which decreases the
 * value of the creditor's account.
 * 
 * @author Roy Terrell
 * 
 */
public interface VendorPurchasesApi extends XactApi {

    /**
     * Finds a purchase order that is associated with value.
     * 
     * @param poId
     *            The Id of the purchase order.
     * @return An instance of {@link PurchaseOrderDto}
     * @throws VendorPurchasesApiException
     */
    PurchaseOrderDto getPurchaseOrder(int poId)
            throws VendorPurchasesApiException;

    /**
     * Finds one or more purchase order using custom selection criteria.
     * 
     * @param customCriteria
     *            A String representing custom criteria.
     * @return A List of {@link PurchaseOrderDto} objects
     * @throws VendorPurchasesApiException
     */
    List<PurchaseOrderDto> getPurchaseOrder(String customCriteria)
            throws VendorPurchasesApiException;

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
    PurchaseOrderItemDto getPurchaseOrderItem(int poItemId)
            throws VendorPurchasesApiException;

    /**
     * Finds all items belonging to a purchase order identified as, value
     * 
     * @param poId
     *            The purchase order id
     * @return A List of {@link PurchaseOrderItemDto} objects
     * @throws VendorPurchasesApiException
     */
    List<PurchaseOrderItemDto> getPurchaseOrderItems(int poId)
            throws VendorPurchasesApiException;

    /**
     * Finds one vendor item object using vendorId and itemId
     * 
     * @param vendorId
     *            The id of the vendor
     * @param itemId
     *            The id of the inventory item
     * @return A List of {@link VendorItemDto} objects
     * @throws VendorPurchasesApiException
     */
    List<VendorItemDto> getVendorItem(int vendorId, int itemId)
            throws VendorPurchasesApiException;

    /**
     * Retreives the current inventory information for a vendor's item.
     * <p>
     * Either the item master version or the external vendor item information of
     * an inventory item is targetd.
     * 
     * @param vendorId
     *            The vendor id
     * @param itemId
     *            The id of the item to retrieve
     * @return An instance of {@link VwVendorItemDto}
     * @throws VendorPurchasesApiException
     */
    VwVendorItemDto getCurrentItemByVendor(int vendorId, int itemId)
            throws VendorPurchasesApiException;

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
    List<PurchaseOrderItemDto> getVendorItemPurchaseOrderItems(int vendorId,
            int poId) throws VendorPurchasesApiException;

    /**
     * Retrieves the purchase order status based on the value of _poStatusId.
     * 
     * @param poStatusId
     *            The id of the purchase order status to retrieve.
     * @return An instance of {@link PurchaseOrderStatusDto}
     * @throws VendorPurchasesApiException
     */
    PurchaseOrderStatusDto getPurchaseOrderStatus(int poStatusId)
            throws VendorPurchasesApiException;

    /**
     * Retrieves the current status of a purchase order.
     * 
     * @param poId
     *            Is the id of the purchase order
     * @return An instance of {@link PurchaseOrderStatusHistDto}
     * @throws VendorPurchasesApiException
     */
    PurchaseOrderStatusHistDto getCurrentPurchaseOrderHistory(int poId)
            throws VendorPurchasesApiException;

    /**
     * Retrieves the status history of a purchase order.
     * 
     * @param poId
     *            The purchase order id
     * @return A List of {@link PurchaseOrderStatusHistDto} objects
     * @throws VendorPurchasesApiException
     */
    List<PurchaseOrderStatusHistDto> getPurchaseOrderHistory(int poId)
            throws VendorPurchasesApiException;

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
     */
    List<VwVendorItemDto> getPurchaseOrderAvailableItems(int vendorId, int poId)
            throws VendorPurchasesApiException;

    /**
     * Creates a new or updates an existing purchase order and its items.
     * 
     * @param po
     *            Purchase Order object.
     * @param items
     *            list of purchase order items.
     * @return int
     * @throws VendorPurchasesApiException
     */
    int updatePurchaseOrder(PurchaseOrderDto po,
            List<PurchaseOrderItemDto> items)
            throws VendorPurchasesApiException;

    /**
     * Deletes a purchase order.
     * 
     * @param poId
     *            The purchase order id
     * @return int
     * @throws VendorPurchasesApiException
     */
    int deletePurchaseOrder(int poId) throws VendorPurchasesApiException;

    /**
     * Deletes one purchase order item.
     * 
     * @param poId
     *            Id of the purchase order which all items will be removed.
     * @param poItemId
     *            Id of the purchase order item
     * @return int
     * @throws VendorPurchasesApiException
     */
    int deleteItem(int poId, int poItemId) throws VendorPurchasesApiException;

    /**
     * Deletes all items belonging to a purchase order.
     * 
     * @param poId
     *            Id of the purchase order which all items will be removed.
     * @return int
     * @throws VendorPurchasesApiException
     */
    int deleteAllItems(int poId) throws VendorPurchasesApiException;

    /**
     * Assigns a new status to a purchase order.
     * 
     * @param poId
     *            The id of the target purchase order
     * @param newStatusId
     *            The id of status that is to be assigned to the purchase order.
     * @throws VendorPurchasesException
     */
    void setPurchaseOrderStatus(int poId, int newStatusId)
            throws VendorPurchasesApiException;

    /**
     * This method will perform a purchases, returns, and allowances transaction
     * for an existing purchase order identified as, purchaseOrderId.
     * 
     * @param purchaseOrderId
     *            The id of a valid purchase order.
     * @throws VendorPurchasesApiException
     */
    void doVendorPurchaseReturnAllow(int purchaseOrderId)
            throws VendorPurchasesApiException;

    /**
     * Creates a purchase order transaction.
     * 
     * @param po
     *            The purchase order
     * @param items
     *            Purchase order items
     * @return Transaction Id.
     * @throws VendorPurchasesApiException
     */
    int submitPurchaseOrder(PurchaseOrderDto po,
            List<PurchaseOrderItemDto> items)
            throws VendorPurchasesApiException;

    /**
     * Computes the total amount of a purchase order.
     * 
     * @param poId
     *            The Id of the target purchase order
     * @return A decimal value.
     * @throws VendorPurchasesApiException
     */
    double calcPurchaseOrderTotal(int poId) throws VendorPurchasesApiException;

    /**
     * Cancels a purchase order.
     * 
     * @param poId
     *            The id of target purchase order
     * @throws VendorPurchasesApiException
     */
    void cancelPurchaseOrder(int poId) throws VendorPurchasesApiException;

    /**
     * Creates a purchases, returns, and allowances transaction for a purchase
     * order.
     * 
     * @param poId
     *            The id of target purchase order
     * @param items
     *            The items that are to be returned.
     * @throws VendorPurchasesApiException
     */
    void returnPurchaseOrder(int poId, List<PurchaseOrderItemDto> items)
            throws VendorPurchasesApiException;

}
