package org.dto.adapter.orm.transaction.purchaseorder;

import org.dao.mapping.orm.rmt2.VendorItems;
import org.dto.VendorItemDto;
import org.dto.adapter.orm.inventory.ItemMasterRmt2OrmAdapter;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * objects <i>vendor_items</i> table.
 * 
 * @author Roy Terrell
 * 
 */
class VendorItemsRmt2OrmAdapter extends ItemMasterRmt2OrmAdapter
        implements VendorItemDto {

    private VendorItems i;


    /**
     * Create a VendorItemsRmt2OrmAdapter without performing any data
     * adaptations
     */
    protected VendorItemsRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a VendorItemsRmt2OrmAdapter that adapts data coming from the
     * vendor_items table.
     * 
     * @param item
     *            an instance of {@link VendorItems} or null when the
     *            desired arises to create a newly instantiated instance.
     */
    protected VendorItemsRmt2OrmAdapter(VendorItems item) {
        this();
        if (item == null) {
            item = new VendorItems();
        }
        this.i = item;
        return;
    }


    @Override
    public void setVendorId(int value) {
        this.i.setCreditorId(value);
    }

    @Override
    public int getVendorId() {
        return this.i.getCreditorId();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#setItemId(int)
     */
    @Override
    public void setItemId(int value) {
        this.i.setItemId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#getItemId()
     */
    @Override
    public int getItemId() {
        return this.i.getItemId();
    }

    @Override
    public void setVendorItemNo(String value) {
        this.i.setVendorItemNo(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#getVendorItemNo()
     */
    @Override
    public String getVendorItemNo() {
        return this.i.getVendorItemNo();
    }

    @Override
    public void setVendorItemSerialNo(String value) {
        this.i.setItemSerialNo(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#getVendorItemSerialNo()
     */
    @Override
    public String getVendorItemSerialNo() {
        return this.i.getItemSerialNo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#setVendorUnitCost(double)
     */
    @Override
    public void setVendorItemUnitCost(double value) {
        this.i.setUnitCost(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#getVendorUnitCost()
     */
    @Override
    public double getVendorItemUnitCost() {
        return this.i.getUnitCost();
    }

}
