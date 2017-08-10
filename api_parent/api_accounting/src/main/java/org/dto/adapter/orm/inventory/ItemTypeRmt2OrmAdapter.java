package org.dto.adapter.orm.inventory;

import org.dao.mapping.orm.rmt2.ItemMasterType;
import org.dto.ItemMasterTypeDto;

import com.api.foundation.TransactionDtoImpl;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * table <i>item_master_type</i>.
 * 
 * @author rterrell
 * 
 */
class ItemTypeRmt2OrmAdapter extends TransactionDtoImpl implements
        ItemMasterTypeDto {

    private ItemMasterType i;

    /**
     * Create a ItemTypeRmt2OrmAdapter without performing any data adaptations
     */
    protected ItemTypeRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a ItemTypeRmt2OrmAdapter that adapts data coming from the
     * item_master_type table
     * 
     * @param item
     *            an instance of {@link ItemMasterType} or null when the desired
     *            arises to create a newly instantiated instance.
     */
    protected ItemTypeRmt2OrmAdapter(ItemMasterType item) {
        if (item == null) {
            item = new ItemMasterType();
        }
        this.i = item;
        this.dateCreated = item.getDateCreated();
        this.dateUpdated = item.getDateUpdated();
        this.updateUserId = item.getUserId();
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterTypeDto#setItemTypeId(int)
     */
    @Override
    public void setItemTypeId(int value) {
        this.i.setItemTypeId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterTypeDto#getItemTypeId()
     */
    @Override
    public int getItemTypeId() {
        return this.i.getItemTypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterTypeDto#setItemTypeDescription(java.lang.String)
     */
    @Override
    public void setItemTypeDescription(String value) {
        this.i.setDescription(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterTypeDto#getItemTypeDescription()
     */
    @Override
    public String getItemTypeDescription() {
        return this.i.getDescription();
    }

    /**
     * Not implemented
     */
    @Override
    public void setEntityId(int value) {
        this.i.setItemTypeId(value);
    }

    /**
     * Not implemented
     */
    @Override
    public int getEntityId() {
        return this.i.getItemTypeId();
    }

    /**
     * Not implemented
     */
    @Override
    public void setEntityName(String value) {
        this.i.setDescription(value);
    }

    /**
     * Not implemented
     */
    @Override
    public String getEntityName() {
        return this.i.getDescription();
    }

}
