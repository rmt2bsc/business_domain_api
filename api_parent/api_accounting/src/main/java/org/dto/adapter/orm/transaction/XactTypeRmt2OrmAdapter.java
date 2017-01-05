package org.dto.adapter.orm.transaction;

import org.dao.mapping.orm.rmt2.VwXactList;
import org.dao.mapping.orm.rmt2.VwXactTypeItemActivity;
import org.dao.mapping.orm.rmt2.XactCategory;
import org.dao.mapping.orm.rmt2.XactType;
import org.dto.XactTypeDto;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * table, <i>xact_type</i>.
 * 
 * @author rterrell
 * 
 */
class XactTypeRmt2OrmAdapter extends XactCategoryRmt2OrmAdapter implements
        XactTypeDto {

    private XactType xt;

    /**
     * Create a XactTypeRmt2OrmAdapter without performing any data adaptations
     */
    protected XactTypeRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a XactTypeRmt2OrmAdapter that adapts data coming from the
     * xact_type table
     * 
     * @param xactType
     *            an instance of {@link XactType} or null when the desired
     *            arises to create a newly instantiated instance.
     */
    protected XactTypeRmt2OrmAdapter(XactType xactType) {
        this.updateObjHeirarchy(xactType);

        if (xactType != null) {
            // Setup XactCategory instance at the ancestor level.
            XactCategory xc = new XactCategory();
            xc.setXactCatgId(xactType.getXactCatgId());
            this.updateObjHeirarchy(xc);
        }
        return;
    }

    /**
     * Tries to cast <i>obj</i> as a <i>XactType</i> object.
     * <p>
     * If <i>obj</i> is not of the expected data type, then the process of
     * assigning the data object is ignored.
     * 
     * @param obj
     *            is expected to be an instance of {@link XactType} or null if
     *            the desire is to deal with a newly instantiated data object.
     */
    @Override
    protected void updateObjHeirarchy(Object obj) {
        super.updateObjHeirarchy(obj);
        if (obj == null) {
            this.xt = new XactType();
        }
        else if (obj instanceof XactType) {
            this.xt = (XactType) obj;
        }
        else if (obj instanceof VwXactList) {
            VwXactList in = (VwXactList) obj;
            this.xt = new XactType();
            this.xt.setXactTypeId(in.getXactTypeId());
            this.xt.setXactCatgId(in.getXactCatgId());
            this.xt.setDescription(in.getXactTypeName());
            this.xt.setToMultiplier(in.getToMultiplier());
            this.xt.setFromMultiplier(in.getFromMultiplier());
            this.xt.setToAcctTypeId(in.getToAcctTypeId());
            this.xt.setFromAcctTypeId(in.getFromAcctTypeId());
            this.xt.setFromAcctCatgId(in.getFromAcctCatgId());
            this.xt.setHasSubsidiary(in.getHasSubsidiary());
        }
        else if (obj instanceof VwXactTypeItemActivity) {
            VwXactTypeItemActivity in = (VwXactTypeItemActivity) obj;
            this.xt = new XactType();
            this.xt.setXactTypeId(in.getXactTypeId());
            this.xt.setCode(in.getXactTypeXactCode());
            this.xt.setDescription(in.getXactTypeDescription());
            this.xt.setXactCatgId(in.getXactCategoryId());
        }
        else {
            // Leave XactType object as is...
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeDto#setXactTypeId(int)
     */
    @Override
    public void setXactTypeId(int value) {
        this.xt.setXactTypeId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeDto#getXactTypeId()
     */
    @Override
    public int getXactTypeId() {
        return this.xt.getXactTypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeDto#setXactTypeCode(java.lang.String)
     */
    @Override
    public void setXactTypeCode(String value) {
        this.xt.setCode(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeDto#getXactTypeCode()
     */
    @Override
    public String getXactTypeCode() {
        return this.xt.getCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeDto#setXactTypeDescription(java.lang.String)
     */
    @Override
    public void setXactTypeDescription(String value) {
        this.xt.setDescription(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeDto#getXactTypeDescription()
     */
    @Override
    public String getXactTypeDescription() {
        return this.xt.getDescription();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeDto#setXactTypeToMultiplier(int)
     */
    @Override
    public void setXactTypeToMultiplier(int value) {
        this.xt.setToMultiplier(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeDto#getXactTypeToMultiplier()
     */
    @Override
    public int getXactTypeToMultiplier() {
        return this.xt.getToMultiplier();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeDto#setXactTypeFromMultiplier(int)
     */
    @Override
    public void setXactTypeFromMultiplier(int value) {
        this.xt.setFromMultiplier(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeDto#getXactTypeFromMultiplier()
     */
    @Override
    public int getXactTypeFromMultiplier() {
        return this.xt.getFromMultiplier();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeDto#setXactTypeToAcctTypeId(int)
     */
    @Override
    public void setXactTypeToAcctTypeId(int value) {
        this.xt.setToAcctTypeId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeDto#getXactTypeToAcctTypeId()
     */
    @Override
    public int getXactTypeToAcctTypeId() {
        return this.xt.getToAcctTypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeDto#setXactTypeToAcctCatgId(int)
     */
    @Override
    public void setXactTypeToAcctCatgId(int value) {
        this.xt.setToAcctCatgId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeDto#getXactTypeToAcctCatgId()
     */
    @Override
    public int getXactTypeToAcctCatgId() {
        return this.xt.getToAcctCatgId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeDto#setXactTypeFromAcctTypeId(int)
     */
    @Override
    public void setXactTypeFromAcctTypeId(int value) {
        this.xt.setFromAcctTypeId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeDto#getXactTypeFromAcctTypeId()
     */
    @Override
    public int getXactTypeFromAcctTypeId() {
        return this.xt.getFromAcctTypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeDto#setXactTypeFromAcctCatgId(int)
     */
    @Override
    public void setXactTypeFromAcctCatgId(int value) {
        this.xt.setFromAcctCatgId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeDto#getXactTypeFromAcctCatgId()
     */
    @Override
    public int getXactTypeFromAcctCatgId() {
        return this.xt.getFromAcctCatgId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeDto#setXactTypeHasSubsidiary(int)
     */
    @Override
    public void setXactTypeHasSubsidiary(int value) {
        this.xt.setHasSubsidiary(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactTypeDto#getXactTypeHasSubsidiary()
     */
    @Override
    public int getXactTypeHasSubsidiary() {
        return this.xt.getHasSubsidiary();
    }

}
