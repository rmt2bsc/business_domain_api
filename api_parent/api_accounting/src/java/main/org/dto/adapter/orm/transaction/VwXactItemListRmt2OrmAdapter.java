package org.dto.adapter.orm.transaction;

import org.dao.mapping.orm.rmt2.VwXactItemList;
import org.dto.XactTypeItemActivityDto;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * view <i>vw_xact_item_list</i>.
 * 
 * @author rterrell
 * 
 */
class VwXactItemListRmt2OrmAdapter extends XactTypeItemRmt2OrmAdapter implements
        XactTypeItemActivityDto {

    private VwXactItemList vxil;

    /**
     * Create a VwXactItemListRmt2OrmAdapter without performing any data
     * adaptations
     */
    VwXactItemListRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a VwXactItemListRmt2OrmAdapter that adapts data coming from the
     * vw_xact_item_list view.
     * 
     * @param vxil
     *            an instance of {@link VwXactItemList} or null when the desired
     *            arises to create a newly instantiated instance.
     */
    protected VwXactItemListRmt2OrmAdapter(VwXactItemList vxil) {
        if (vxil == null) {
            vxil = new VwXactItemList();
        }
        this.vxil = vxil;
    }

    /**
     * Create a VwXactItemListRmt2OrmAdapter that adapts data coming from the
     * xact_type_item_activity table
     * 
     * @param vxil
     *            an instance of {@link XactTypeItemActivity} or null when the
     *            desired arises to create a newly instantiated instance.
     * @param criteria
     *            custom selection criteria
     */
    protected VwXactItemListRmt2OrmAdapter(VwXactItemList vxil, String criteria) {
        this(vxil);
        this.setCriteria(criteria);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeItemActivityDto#setXactTypeItemActvId(int)
     */
    @Override
    public void setXactTypeItemActvId(int value) {
        this.vxil.setItemActivityId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeItemActivityDto#getXactTypeItemActvId()
     */
    @Override
    public int getXactTypeItemActvId() {
        return this.vxil.getItemActivityId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.XactTypeItemActivityDto#setXactTypeItemActvName(java.lang.String)
     */
    @Override
    public void setXactTypeItemActvName(String value) {
        this.vxil.setItemXactReason(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeItemActivityDto#getXactTypeItemActvName()
     */
    @Override
    public String getXactTypeItemActvName() {
        return this.vxil.getItemXactReason();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeItemActivityDto#setActivityAmount(double)
     */
    @Override
    public void setActivityAmount(double value) {
        this.vxil.setAmount(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeItemActivityDto#getActivityAmount()
     */
    @Override
    public double getActivityAmount() {
        return this.vxil.getAmount();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.adapter.orm.transaction.XactTypeItemRmt2OrmAdapter#setXactItemId(int)
     */
    @Override
    public void setXactItemId(int value) {
        this.vxil.setXactTypeItemId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.orm.transaction.XactTypeItemRmt2OrmAdapter#getXactItemId()
     */
    @Override
    public int getXactItemId() {
        return this.vxil.getXactTypeItemId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.adapter.orm.transaction.XactTypeItemRmt2OrmAdapter#setXactItemName(java
     * .lang.String)
     */
    @Override
    public void setXactItemName(String value) {
        this.vxil.setName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.adapter.orm.transaction.XactTypeItemRmt2OrmAdapter#getXactItemName()
     */
    @Override
    public String getXactItemName() {
        return this.vxil.getName();
    }

}
