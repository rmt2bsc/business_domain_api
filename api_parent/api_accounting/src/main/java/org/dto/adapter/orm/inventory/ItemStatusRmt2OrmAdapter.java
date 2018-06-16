package org.dto.adapter.orm.inventory;

import org.dao.mapping.orm.rmt2.ItemMasterStatus;
import org.dto.ItemMasterStatusDto;

import com.api.foundation.TransactionDtoImpl;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * table <i>item_master_status</i>.
 * 
 * @author rterrell
 * 
 */
class ItemStatusRmt2OrmAdapter extends TransactionDtoImpl implements
        ItemMasterStatusDto {

    private ItemMasterStatus s;

    /**
     * Create a ItemStatusRmt2OrmAdapter without performing any data adaptations
     */
    protected ItemStatusRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a ItemStatusRmt2OrmAdapter that adapts data coming from the
     * item_master_status table
     * 
     * @param status
     *            an instance of {@link ItemMasterStatus} or null when the
     *            desired arises to create a newly instantiated instance.
     */
    protected ItemStatusRmt2OrmAdapter(ItemMasterStatus status) {
        if (status == null) {
            status = new ItemMasterStatus();
        }
        this.s = status;
        this.dateCreated = status.getDateCreated();
        this.dateUpdated = status.getDateUpdated();
        this.updateUserId = status.getUserId();
        return;
    }

    /**
     * Set the item status id
     */
    @Override
    public void setEntityId(int value) {
        this.s.setItemStatusId(value);
    }

    /**
     * Get the item status id
     */
    @Override
    public int getEntityId() {
        return this.s.getItemStatusId();
    }

    /**
     * Set the item status description
     */
    @Override
    public void setEntityName(String value) {
        this.s.setDescription(value);
    }

    /**
     * Get the item status description
     */
    @Override
    public String getEntityName() {
        return this.s.getDescription();
    }

}
