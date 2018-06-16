package org.dto.adapter.orm.transaction;

import java.util.Date;

import org.dao.mapping.orm.rmt2.VwXactTypeItemActivity;
import org.dao.mapping.orm.rmt2.XactTypeItemActivity;
import org.dto.XactTypeItemActivityDto;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * table <i>xact_type_item_activity</i>.
 * 
 * @author rterrell
 * 
 */
class XactTypeItemActivityRmt2OrmAdapter extends XactTypeItemRmt2OrmAdapter
        implements XactTypeItemActivityDto {

    private XactTypeItemActivity xtia;

    /**
     * Create a XactTypeItemActivityRmt2OrmAdapter without performing any data
     * adaptations
     */
    protected XactTypeItemActivityRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a XactTypeItemActivityRmt2OrmAdapter that adapts data coming from
     * the xact_type_item_activity table
     * 
     * @param actv
     *            an instance of {@link XactTypeItemActivity} or null when the
     *            desired arises to create a newly instantiated instance.
     */
    protected XactTypeItemActivityRmt2OrmAdapter(XactTypeItemActivity actv) {
        this.updateObjHeirarchy(actv);

        // // begin to initialized ancestor data objects based on relavant data
        // // properties containted in item.
        // if (actv != null) {
        // XactTypeItem xti = new XactTypeItem();
        // xti.setXactTypeId(actv.getXactTypeItemActvId());
        // Xact xact = new Xact();
        // xact.setXactId(actv.getXactId());
        // this.updateObjHeirarchy(xti);
        // this.updateObjHeirarchy(xact);
        // }
    }

    /**
     * Create a XactTypeItemActivityRmt2OrmAdapter that adapts data coming from
     * the xact_type_item_activity table
     * 
     * @param actv
     *            an instance of {@link XactTypeItemActivity} or null when the
     *            desired arises to create a newly instantiated instance.
     * @param criteria
     *            custom selection criteria
     */
    protected XactTypeItemActivityRmt2OrmAdapter(XactTypeItemActivity actv,
            String criteria) {
        this.updateObjHeirarchy(actv);
        this.setCriteria(criteria);

        // // begin to initialized ancestor data objects based on relavant data
        // // properties containted in item.
        // if (actv != null) {
        // XactTypeItem xti = new XactTypeItem();
        // xti.setXactTypeId(actv.getXactTypeItemActvId());
        // Xact xact = new Xact();
        // xact.setXactId(actv.getXactId());
        // this.updateObjHeirarchy(xti);
        // this.updateObjHeirarchy(xact);
        // }
    }

    /**
     * Create a XactTypeItemActivityRmt2OrmAdapter that adapts data coming from
     * the vw_xact_type_item_activity view.
     * 
     * @param actv
     *            an instance of {@link VwXactTypeItemActivity}
     */
    protected XactTypeItemActivityRmt2OrmAdapter(VwXactTypeItemActivity actv) {
        this.updateObjHeirarchy(actv);
    }

    /**
     * Create a XactTypeItemActivityRmt2OrmAdapter that adapts data coming from
     * the vw_xact_type_item_activity view.
     * 
     * @param actv
     *            an instance of {@link VwXactTypeItemActivity}
     * @param criteria
     *            custom selection criteria
     */
    protected XactTypeItemActivityRmt2OrmAdapter(VwXactTypeItemActivity actv,
            String criteria) {
        this.updateObjHeirarchy(actv);
        this.setCriteria(criteria);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.adapter.orm.XactTypeItemRmt2OrmAdapter#initDataObject(java.lang.Object)
     */
    @Override
    protected void updateObjHeirarchy(Object obj) {
        super.updateObjHeirarchy(obj);

        boolean targetObject = false;
        if (obj == null) {
            this.xtia = new XactTypeItemActivity();
            targetObject = true;
        }
        else if (obj instanceof XactTypeItemActivity) {
            this.xtia = (XactTypeItemActivity) obj;
            targetObject = true;
        }
        else if (obj instanceof VwXactTypeItemActivity) {
            VwXactTypeItemActivity in = (VwXactTypeItemActivity) obj;
            this.xtia = new XactTypeItemActivity();
            this.xtia.setAmount(in.getItemAmount());
            this.xtia.setXactTypeItemActvId(in.getId());
            this.xtia.setXactId(in.getXactId());
            this.xtia.setXactItemId(in.getXactTypeItemId());
            this.xtia.setDescription(in.getItemName());
            targetObject = true;
        }
        else {
            // Leave XactTypeItemActivity object as is...
        }
        // If this is the main object we are working with, then assign the
        // timestamp properties to the main object's.
        if (targetObject) {
            this.dateCreated = this.xtia.getDateCreated();
            this.dateUpdated = this.xtia.getDateUpdated();
            this.updateUserId = this.xtia.getUserId();
            this.ipCreated = this.xtia.getIpCreated();
            this.ipUpdated = this.xtia.getIpUpdated();
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeItemActivityDto#setXactTypeItemActvId(int)
     */
    @Override
    public void setXactTypeItemActvId(int value) {
        this.xtia.setXactTypeItemActvId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeItemActivityDto#getXactTypeItemActvId()
     */
    @Override
    public int getXactTypeItemActvId() {
        return this.xtia.getXactTypeItemActvId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.XactTypeItemActivityDto#setXactTypeItemActvName(java.lang.String)
     */
    @Override
    public void setXactTypeItemActvName(String value) {
        this.xtia.setDescription(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeItemActivityDto#getXactTypeItemActvName()
     */
    @Override
    public String getXactTypeItemActvName() {
        return this.xtia.getDescription();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeItemActivityDto#setActivityAmount(double)
     */
    @Override
    public void setActivityAmount(double value) {
        this.xtia.setAmount(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeItemActivityDto#getActivityAmount()
     */
    @Override
    public double getActivityAmount() {
        return this.xtia.getAmount();
    }

    @Override
    public void setXactItemId(int value) {
        this.xtia.setXactItemId(value);
    }

    @Override
    public int getXactItemId() {
        return this.xtia.getXactItemId();
    }

    @Override
    public void setXactId(int value) {
       this.xtia.setXactId(value);
    }

    @Override
    public int getXactId() {
        return this.xtia.getXactId();
    }

    @Override
    public String getUpdateUserId() {
        return this.xtia.getUserId();
    }

    @Override
    public void setUpdateUserId(String updateUserId) {
        this.xtia.setUserId(updateUserId);
    }

    @Override
    public Date getDateCreated() {
        return this.xtia.getDateCreated();
    }

    @Override
    public void setDateCreated(Date dateCreated) {
        this.xtia.setDateCreated(dateCreated);
    }

    @Override
    public Date getDateUpdated() {
        return this.xtia.getDateUpdated();
    }

    @Override
    public void setDateUpdated(Date dateUpdated) {
        this.xtia.setDateUpdated(dateUpdated);
    }

    @Override
    public String getIpCreated() {
        return this.xtia.getIpCreated();
    }

    @Override
    public void setIpCreated(String ipCreated) {
        this.xtia.setIpCreated(ipCreated);
    }

    @Override
    public String getIpUpdated() {
        return this.xtia.getIpUpdated();
    }

    @Override
    public void setIpUpdated(String ipUpdated) {
        this.xtia.setIpUpdated(ipUpdated);
    }

}
