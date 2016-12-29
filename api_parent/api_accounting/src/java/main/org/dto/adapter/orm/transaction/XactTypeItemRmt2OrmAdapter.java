package org.dto.adapter.orm.transaction;

import org.dao.mapping.orm.rmt2.VwXactTypeItemActivity;
import org.dao.mapping.orm.rmt2.Xact;
import org.dao.mapping.orm.rmt2.XactTypeItem;
import org.dto.XactTypeItemDto;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * table <i>xact_type_item</i>.
 * 
 * @author rterrell
 * 
 */
class XactTypeItemRmt2OrmAdapter extends XactRmt2OrmAdapter implements
        XactTypeItemDto {

    private XactTypeItem xti;

    /**
     * Create a XactTypeItemRmt2OrmAdapter without performing any data
     * adaptations
     */
    protected XactTypeItemRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a XactTypeItemRmt2OrmAdapter that adapts data coming from the
     * xact_type_item table
     * 
     * @param item
     *            an instance of {@link XactTypeItem} or null when the desired
     *            arises to create a newly instantiated instance.
     */
    protected XactTypeItemRmt2OrmAdapter(XactTypeItem item) {
        this.updateObjHeirarchy(item);

        // begin to initialized ancestor data objects based on relavant data
        // properties containted in item.
        if (item != null) {
            Xact x = new Xact();
            x.setXactTypeId(item.getXactTypeId());
            this.updateObjHeirarchy(x);
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.orm.XactRmt2OrmAdapter#initDataObject(java.lang.Object)
     */
    @Override
    protected void updateObjHeirarchy(Object obj) {
        super.updateObjHeirarchy(obj);

        boolean targetObject = false;
        if (obj == null) {
            this.xti = new XactTypeItem();
            targetObject = true;
        }
        else if (obj instanceof XactTypeItem) {
            this.xti = (XactTypeItem) obj;
            targetObject = true;
        }
        if (obj instanceof VwXactTypeItemActivity) {
            VwXactTypeItemActivity in = (VwXactTypeItemActivity) obj;
            this.xti = new XactTypeItem();
            this.xti.setXactItemId(in.getXactTypeItemId());
            this.xti.setXactTypeId(in.getXactTypeId());
            this.xti.setName(in.getXactTypeItemName());
        }
        else {
            // Leave Xact object as is...
        }
        // If this is the main object we are working with, then assign the
        // timestamp properties to the main object's.
        if (targetObject) {
            this.dateCreated = this.xti.getDateCreated();
            this.dateUpdated = this.xti.getDateUpdated();
            this.updateUserId = this.xti.getUserId();
        }
        return;

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeItemDto#setXactItemId(int)
     */
    @Override
    public void setXactItemId(int value) {
        this.xti.setXactItemId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeItemDto#getXactItemId()
     */
    @Override
    public int getXactItemId() {
        return this.xti.getXactItemId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeItemDto#setXactItemName(java.lang.String)
     */
    @Override
    public void setXactItemName(String value) {
        this.xti.setName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeItemDto#getXactItemName()
     */
    @Override
    public String getXactItemName() {
        return this.xti.getName();
    }

}
