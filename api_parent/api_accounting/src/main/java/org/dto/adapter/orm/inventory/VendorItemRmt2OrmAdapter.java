package org.dto.adapter.orm.inventory;

import org.dao.inventory.InventoryDaoFactory;
import org.dao.mapping.orm.rmt2.ItemMaster;
import org.dao.mapping.orm.rmt2.VwVendorItems;
import org.dto.ItemMasterDto;
import org.dto.VendorItemDto;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * view <vvi>vw_vendor_items</vvi>.
 * 
 * @author Roy Terrell
 * 
 */
class VendorItemRmt2OrmAdapter extends ItemMasterRmt2OrmAdapter implements
        VendorItemDto {

    private VwVendorItems vvi;

    /**
     * Create a VendorItemRmt2OrmAdapter without performing any data adaptations
     */
    protected VendorItemRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a VendorItemRmt2OrmAdapter that adapts data coming from the
     * vw_vendor_items view
     * 
     * @param item
     *            an instance of {@link VwVendorItems} or null when the desired
     *            arises to create a newly instantiated instance.
     */
    protected VendorItemRmt2OrmAdapter(VwVendorItems item) {
        this.updateObjHeirarchy(item);
        return;
    }

    /**
     * Create a VendorItemRmt2OrmAdapter that adapts a vendor id and an
     * associated <vvi>ItemMasterDto</vvi> object.
     * 
     * @param vendorId
     *            the vendor's id
     * @param item
     *            an instance of {@link ItemMasterDto}
     */
    protected VendorItemRmt2OrmAdapter(int vendorId, ItemMasterDto item) {
        ItemMaster im = InventoryDaoFactory.createItemMasterRmt2Orm(item);
        super.updateObjHeirarchy(im);
        VwVendorItems obj = new VwVendorItems();
        obj.setCreditorId(vendorId);
        this.vvi = obj;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.adapter.orm.ItemMasterRmt2OrmAdapter#initDataObject(java.lang.Object)
     */
    @Override
    protected void updateObjHeirarchy(Object obj) {
        VwVendorItems item = null;
        if (obj == null) {
            item = new VwVendorItems();
        }
        else if (obj instanceof VwVendorItems) {
            item = (VwVendorItems) obj;
        }
        else {
            return;
        }
        this.vvi = item;

        // Setup item master
        ItemMaster im = new ItemMaster();
        im.setItemId(item.getItemId());
        im.setVendorItemNo(item.getVendorItemNo());
        im.setItemSerialNo(item.getItemSerialNo());
        im.setUnitCost(item.getUnitCost());
        im.setDescription(item.getDescription());
        im.setQtyOnHand(item.getQtyOnHand());
        im.setMarkup(item.getMarkup());
        im.setOverrideRetail(item.getOverrideRetail());
        super.updateObjHeirarchy(im);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#setCreditorId(int)
     */
    @Override
    public void setVendorId(int value) {
        this.vvi.setCreditorId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#getCreditorId()
     */
    @Override
    public int getVendorId() {
        return this.vvi.getCreditorId();
    }

    /* (non-Javadoc)
     * @see org.dto.VendorItemDto#setVendorItemSerialNo(java.lang.String)
     */
    @Override
    public void setVendorItemSerialNo(String value) {
       this.vvi.setVendorItemNo(value);
    }

    /* (non-Javadoc)
     * @see org.dto.VendorItemDto#getVendorItemSerialNo()
     */
    @Override
    public String getVendorItemSerialNo() {
        return this.vvi.getVendorItemNo();
    }

    /* (non-Javadoc)
     * @see org.dto.VendorItemDto#setVendorItemUnitCost(double)
     */
    @Override
    public void setVendorItemUnitCost(double value) {
        this.vvi.setUnitCost(value);
    }

    /* (non-Javadoc)
     * @see org.dto.VendorItemDto#getVendorItemUnitCost()
     */
    @Override
    public double getVendorItemUnitCost() {
        return this.vvi.getUnitCost();
    }

}
