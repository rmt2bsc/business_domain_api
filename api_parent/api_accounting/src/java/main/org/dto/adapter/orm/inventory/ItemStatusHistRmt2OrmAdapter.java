package org.dto.adapter.orm.inventory;

import java.util.Date;

import org.dao.mapping.orm.rmt2.ItemMasterStatusHist;
import org.dto.ItemMasterStatusHistDto;

import com.api.foundation.TransactionDtoImpl;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * table <i>item_master_status_hist</i>.
 * 
 * @author rterrell
 * 
 */
class ItemStatusHistRmt2OrmAdapter extends TransactionDtoImpl implements
        ItemMasterStatusHistDto {

    private ItemMasterStatusHist i;

    /**
     * Create a ItemStatusHistRmt2OrmAdapter without performing any data
     * adaptations
     */
    protected ItemStatusHistRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a ItemStatusHistRmt2OrmAdapter that adapts data coming from the
     * item_master_status_hist table
     * 
     * @param item
     *            an instance of {@link ItemMasterStatusHist} or null when the
     *            desired arises to create a newly instantiated instance.
     */
    protected ItemStatusHistRmt2OrmAdapter(ItemMasterStatusHist item) {
        if (item == null) {
            item = new ItemMasterStatusHist();
        }
        this.i = item;
        this.dateCreated = item.getDateCreated();
        this.updateUserId = item.getUserId();
        this.ipCreated = item.getIpCreated();
        this.ipUpdated = item.getIpUpdated();
        return;
    }

    /**
     * Set the item master status history internal unique identifier
     */
    @Override
    public void setEntityId(int value) {
        this.i.setItemStatusHistId(value);
    }

    /**
     * Get the item master status history internal unique identifier
     */
    @Override
    public int getEntityId() {
        return this.i.getItemStatusHistId();
    }

    /**
     * Not implemented
     */
    @Override
    public void setEntityName(String value) {
        return;
    }

    /**
     * Not implemented.
     * <p>
     * Always returns null.
     */
    @Override
    public String getEntityName() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterStatusHistDto#setItemId(int)
     */
    @Override
    public void setItemId(int value) {
        this.i.setItemId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterStatusHistDto#getItemId()
     */
    @Override
    public int getItemId() {
        return this.i.getItemId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterStatusHistDto#setItemStatusId(int)
     */
    @Override
    public void setItemStatusId(int value) {
        this.i.setItemStatusId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterStatusHistDto#getItemStatusId()
     */
    @Override
    public int getItemStatusId() {
        return this.i.getItemStatusId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterStatusHistDto#setUnitCost(double)
     */
    @Override
    public void setUnitCost(double value) {
        this.i.setUnitCost(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterStatusHistDto#getUnitCost()
     */
    @Override
    public double getUnitCost() {
        return this.i.getUnitCost();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterStatusHistDto#setMarkup(double)
     */
    @Override
    public void setMarkup(double value) {
        this.i.setMarkup(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterStatusHistDto#getMarkup()
     */
    @Override
    public double getMarkup() {
        return this.i.getMarkup();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterStatusHistDto#setEffectiveDate(java.util.Date)
     */
    @Override
    public void setEffectiveDate(Date value) {
        this.i.setEffectiveDate(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterStatusHistDto#getEffectiveDate()
     */
    @Override
    public Date getEffectiveDate() {
        return this.i.getEffectiveDate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterStatusHistDto#setEndDate(java.util.Date)
     */
    @Override
    public void setEndDate(Date value) {
        this.i.setEndDate(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterStatusHistDto#getEndDate()
     */
    @Override
    public Date getEndDate() {
        return this.i.getEndDate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterStatusHistDto#setReason(java.lang.String)
     */
    @Override
    public void setReason(String value) {
        this.i.setReason(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ItemMasterStatusHistDto#getReason()
     */
    @Override
    public String getReason() {
        return this.i.getReason();
    }

}
