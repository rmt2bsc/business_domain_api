package org.dto.adapter.orm.inventory;

import org.dao.mapping.orm.rmt2.ItemMaster;
import org.dao.mapping.orm.rmt2.ItemMasterType;
import org.dao.mapping.orm.rmt2.VwItemMaster;
import org.dto.ItemMasterDto;

import com.api.foundation.TransactionDtoImpl;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * objects <i>item_master</i> table or the <i>vw_item_master</i> view.
 * 
 * @author rterrell
 * 
 */
public class ItemMasterRmt2OrmAdapter extends TransactionDtoImpl implements ItemMasterDto {

    private ItemMaster i;

    private VwItemMaster v;

    private String unitCostPredicate;

    private String qtyOnHandPredicate;

    // UI-30: Use to manage item master change reason text
    private String changeReason;

    // UI-31: Track list of item id's
    private Integer itemIds[];

    /**
     * Create a ItemMasterRmt2OrmAdapter without performing any data adaptations
     */
    protected ItemMasterRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a ItemMasterRmt2OrmAdapter that adapts data coming from the
     * item_master table
     * 
     * @param item
     *            an instance of {@link ItemMasterDto} or null when the desired
     *            arises to create a newly instantiated instance.
     */
    protected ItemMasterRmt2OrmAdapter(ItemMaster item) {
        // Initialize item master object
        this.updateObjHeirarchy(item);

        // Initialize item master type object
        ItemMasterType imt = new ItemMasterType();
        if (item != null) {
            imt.setItemTypeId(item.getItemTypeId());
        }
        super.updateObjHeirarchy(imt);

        return;
    }

    /**
     * Create a ItemMasterRmt2OrmAdapter that adapts data coming from the
     * vw_item_master view
     * 
     * @param item
     *            an instance of {@link VwItemMaster} or null when the desired
     *            arises to create a newly instantiated instance.
     */
    protected ItemMasterRmt2OrmAdapter(VwItemMaster item) {
        if (item == null) {
            item = new VwItemMaster();
        }
        this.v = item;
        this.dateCreated = item.getItemCreateDate();
        this.dateUpdated = item.getItemUpdateDate();
        this.updateUserId = item.getUpdateUserid();

        // Adapt item master ORM bean
        this.i = new ItemMaster();
        this.i.setItemId(item.getId());
        this.i.setCreditorId(item.getVendorId());
        this.i.setItemTypeId(item.getItemTypeId());
        this.i.setDescription(item.getDescription());
        this.i.setVendorItemNo(item.getVendorItemNo());
        this.i.setItemSerialNo(item.getItemSerialNo());
        this.i.setQtyOnHand(item.getQtyOnHand());
        this.i.setUnitCost(item.getUnitCost());
        this.i.setMarkup(item.getMarkup());
        this.i.setRetailPrice(item.getRetailPrice());
        this.i.setOverrideRetail(item.getOverrideRetail());
        this.i.setActive(item.getActive());

        // Initialize item master type object
        ItemMasterType imt = new ItemMasterType();
        imt.setItemTypeId(item.getItemTypeId());
        imt.setDescription(item.getItemTypeDescription());
        super.updateObjHeirarchy(imt);

        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.api.foundation.TransactionDtoImpl#initDataObject(java.lang.Object)
     */
    @Override
    protected void updateObjHeirarchy(Object obj) {
        super.updateObjHeirarchy(obj);

        ItemMaster item = null;
        if (obj == null) {
            item = new ItemMaster();
        }
        else if (obj instanceof ItemMaster) {
            item = (ItemMaster) obj;
        }
        else {
            return;
        }

        this.i = item;
        this.dateCreated = item.getDateCreated();
        this.dateUpdated = item.getDateUpdated();
        this.updateUserId = item.getUserId();
        this.ipCreated = item.getIpCreated();
        this.ipUpdated = item.getIpUpdated();
        this.v = null;
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#setItemId(int)
     */
    @Override
    public void setItemId(int value) {
        this.i.setItemId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#getItemId()
     */
    @Override
    public int getItemId() {
        return this.i.getItemId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#setDescription(java.lang.String)
     */
    @Override
    public void setItemName(String value) {
        this.i.setDescription(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#getDescription()
     */
    @Override
    public String getItemName() {
        return this.i.getDescription();
    }

    /**
     * Set the item id
     */
    @Override
    public void setEntityId(int value) {
        this.i.setItemId(value);
    }

    /**
     * Get the item id
     */
    @Override
    public int getEntityId() {
        return this.i.getItemId();
    }

    /**
     * Set the item description
     */
    @Override
    public void setEntityName(String value) {
        this.i.setDescription(value);
    }

    /**
     * Get the item description
     */
    @Override
    public String getEntityName() {
        return this.i.getDescription();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#setItemTypeId(int)
     */
    @Override
    public void setItemTypeId(int value) {
        this.i.setItemTypeId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#getItemTypeId()
     */
    @Override
    public int getItemTypeId() {
        return this.i.getItemTypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#setItemTypeDescription(java.lang.String)
     */
    @Override
    public void setItemTypeDescription(String value) {
        if (this.v != null) {
            this.v.setItemTypeDescription(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#getItemTypeDescription()
     */
    @Override
    public String getItemTypeDescription() {
        if (this.v != null) {
            return this.v.getItemTypeDescription();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#setCreditorId(int)
     */
    @Override
    public void setVendorId(int value) {
        this.i.setCreditorId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#getCreditorId()
     */
    @Override
    public int getVendorId() {
        return this.i.getCreditorId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#setVendorItemNo(java.lang.String)
     */
    @Override
    public void setVendorItemNo(String value) {
        this.i.setVendorItemNo(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#getVendorItemNo()
     */
    @Override
    public String getVendorItemNo() {
        return this.i.getVendorItemNo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#setItemSerialNo(java.lang.String)
     */
    @Override
    public void setItemSerialNo(String value) {
        this.i.setItemSerialNo(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#getItemSerialNo()
     */
    @Override
    public String getItemSerialNo() {
        return this.i.getItemSerialNo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#setQtyOnHand(int)
     */
    @Override
    public void setQtyOnHand(int value) {
        this.i.setQtyOnHand(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#getQtyOnHand()
     */
    @Override
    public int getQtyOnHand() {
        return this.i.getQtyOnHand();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#setUnitCost(double)
     */
    @Override
    public void setUnitCost(double value) {
        this.i.setUnitCost(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#getUnitCost()
     */
    @Override
    public double getUnitCost() {
        return this.i.getUnitCost();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#setMarkup(double)
     */
    @Override
    public void setMarkup(double value) {
        this.i.setMarkup(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#getMarkup()
     */
    @Override
    public double getMarkup() {
        return this.i.getMarkup();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#setRetailPrice(double)
     */
    @Override
    public void setRetailPrice(double value) {
        this.i.setRetailPrice(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#getRetailPrice()
     */
    @Override
    public double getRetailPrice() {
        return this.i.getRetailPrice();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#setOverrideRetail(int)
     */
    @Override
    public void setOverrideRetail(int value) {
        this.i.setOverrideRetail(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#getOverrideRetail()
     */
    @Override
    public int getOverrideRetail() {
        return this.i.getOverrideRetail();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#setActive(int)
     */
    @Override
    public void setActive(int value) {
        this.i.setActive(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#getActive()
     */
    @Override
    public int getActive() {
        return this.i.getActive();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#setItemStatusId(int)
     */
    @Override
    public void setItemStatusId(int value) {
        if (this.v != null) {
            this.v.setItemStatusId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#getItemStatusId()
     */
    @Override
    public int getItemStatusId() {
        if (this.v != null) {
            return this.v.getItemStatusId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#setItemStatusDescription(java.lang.String)
     */
    @Override
    public void setItemStatusDescription(String value) {
        if (this.v != null) {
            this.v.setItemStatus(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#getItemStatusDescription()
     */
    @Override
    public String getItemStatusDescription() {
        if (this.v != null) {
            return this.v.getItemStatus();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#getUnitCostPredicate()
     */
    @Override
    public String getUnitCostPredicate() {
        return this.unitCostPredicate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#setUnitCostPredicate(java.lang.String)
     */
    @Override
    public void setUnitCostPredicate(String predicate) {
        this.unitCostPredicate = predicate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#getQtyOnHandPredicate()
     */
    @Override
    public String getQtyOnHandPredicate() {
        return this.qtyOnHandPredicate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#setQtyOnHandPredicate(java.lang.String)
     */
    @Override
    public void setQtyOnHandPredicate(String predicate) {
        this.qtyOnHandPredicate = predicate;
    }

    // UI-30: Added
    @Override
    public void setChangeReason(String value) {
        this.changeReason = value;
    }

    // UI-30: Added
    @Override
    public String getChangeReason() {
        return this.changeReason;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#setItemIds(java.lang.Integer[])
     */
    // UI-31: Track list of item id's
    @Override
    public void setItemIds(Integer[] value) {
        this.itemIds = value;

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterDto#getItemIds()
     */
    // UI-31: Track list of item id's
    @Override
    public Integer[] getItemIds() {
        return this.itemIds;
    }

}
