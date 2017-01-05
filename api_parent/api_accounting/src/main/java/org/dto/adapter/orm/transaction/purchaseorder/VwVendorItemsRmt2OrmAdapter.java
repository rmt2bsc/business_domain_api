package org.dto.adapter.orm.transaction.purchaseorder;

import org.dao.mapping.orm.rmt2.VwVendorItems;
import org.dto.VwVendorItemDto;

import com.api.foundation.AbstractBaseDtoImpl;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * objects <i>vw_vendor_items</i> view.
 * 
 * @author Roy Terrell
 * 
 */
class VwVendorItemsRmt2OrmAdapter extends AbstractBaseDtoImpl implements VwVendorItemDto {

    private VwVendorItems i;


    /**
     * Create a VwVendorItemsRmt2OrmAdapter without performing any data
     * adaptations
     */
    protected VwVendorItemsRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a VwVendorItemsRmt2OrmAdapter that adapts data coming from the
     * vendor_items table.
     * 
     * @param item
     *            an instance of {@link VwVendorItems} or null when the
     *            desired arises to create a newly instantiated instance.
     */
    protected VwVendorItemsRmt2OrmAdapter(VwVendorItems item) {
        this();
        if (item == null) {
            item = new VwVendorItems();
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
    public void setItemSerialNo(String value) {
        this.i.setItemSerialNo(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#getVendorItemSerialNo()
     */
    @Override
    public String getItemSerialNo() {
        return this.i.getItemSerialNo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#setVendorUnitCost(double)
     */
    @Override
    public void setUnitCost(double value) {
        this.i.setUnitCost(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PurchaseOrderItemDto#getVendorUnitCost()
     */
    @Override
    public double getUnitCost() {
        return this.i.getUnitCost();
    }

    /* (non-Javadoc)
     * @see org.dto.adapter.orm.inventory.ItemMasterRmt2OrmAdapter#setMarkup(double)
     */
    @Override
    public void setMarkup(double value) {
        this.setMarkup(value);
    }

    /* (non-Javadoc)
     * @see org.dto.adapter.orm.inventory.ItemMasterRmt2OrmAdapter#getMarkup()
     */
    @Override
    public double getMarkup() {
        return this.getMarkup();
    }

    /* (non-Javadoc)
     * @see org.dto.adapter.orm.inventory.ItemMasterRmt2OrmAdapter#setOverrideRetail(int)
     */
    @Override
    public void setOverrideRetail(int value) {
        this.setOverrideRetail(value);
    }

    /* (non-Javadoc)
     * @see org.dto.adapter.orm.inventory.ItemMasterRmt2OrmAdapter#getOverrideRetail()
     */
    @Override
    public int getOverrideRetail() {
        return this.getOverrideRetail();
    }

    /* (non-Javadoc)
     * @see org.dto.VwVendorItemDto#setItemName(java.lang.String)
     */
    @Override
    public void setItemName(String value) {
        this.i.setDescription(value);
    }

    /* (non-Javadoc)
     * @see org.dto.VwVendorItemDto#getItemName()
     */
    @Override
    public String getItemName() {
        return this.i.getDescription();
    }

    /* (non-Javadoc)
     * @see org.dto.VwVendorItemDto#setQtyOnHand(int)
     */
    @Override
    public void setQtyOnHand(int value) {
        this.i.setQtyOnHand(value);
    }

    /* (non-Javadoc)
     * @see org.dto.VwVendorItemDto#getQtyOnHand()
     */
    @Override
    public int getQtyOnHand() {
        return this.i.getQtyOnHand();
    }

}
