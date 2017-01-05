package org.dto.adapter.orm.transaction;

import org.dao.mapping.orm.rmt2.VwMultiDisburseTypeXact;
import org.dao.mapping.orm.rmt2.Xact;
import org.dao.mapping.orm.rmt2.XactType;

import org.dto.MultiDisburseTypeXactDto;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * view, <i>VW_MULTI_DISBURSE_TYPE_XACT</i>.
 * 
 * @author rterrell
 * 
 */
class MultiDisburseTypeXactRmt2OrmAdapter extends XactRmt2OrmAdapter implements
        MultiDisburseTypeXactDto {

    private VwMultiDisburseTypeXact d;

    /**
     * Create a MultiDisburseTypeXactRmt2OrmAdapter without performing any data
     * adaptations
     */
    public MultiDisburseTypeXactRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a MultiDisburseTypeXactRmt2OrmAdapter that adapts data coming from
     * the <i>VW_MULTI_DISBURSE_TYPE_XACT</i> table
     * 
     * @param disbursement
     *            an instance of {@link VwMultiDisburseTypeXact} or null when
     *            the desired arises to create a newly instantiated instance.
     */
    public MultiDisburseTypeXactRmt2OrmAdapter(
            VwMultiDisburseTypeXact disbursement) {
        // Initialize disbursement object
        this.updateObjHeirarchy(disbursement);

        // Initialize Xact object
        Xact xact = new Xact();
        xact.setXactId(this.d.getXactId());
        xact.setXactAmount(this.d.getXactAmount());
        xact.setConfirmNo(this.d.getConfirmNo());
        xact.setNegInstrNo(this.d.getNegInstrNo());
        xact.setTenderId(this.d.getTenderId());
        xact.setReason(this.d.getReason());
        xact.setXactDate(this.d.getXactDate());
        xact.setDateCreated(this.d.getCreateDate());
        xact.setUserId(this.d.getUserId());
        super.updateObjHeirarchy(xact);

        // Initialize XactType object
        XactType xt = new XactType();
        xt.setXactTypeId(this.d.getXactTypeId());
        xt.setDescription(this.d.getXactTypeName());
        xt.setXactCatgId(this.d.getXactCatgId());
        super.updateObjHeirarchy(xt);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.adapter.orm.transaction.XactRmt2OrmAdapter#initDataObject(java.lang.
     * Object)
     */
    @Override
    protected void updateObjHeirarchy(Object obj) {
        super.updateObjHeirarchy(obj);
        VwMultiDisburseTypeXact disb = null;
        if (obj == null) {
            disb = new VwMultiDisburseTypeXact();
        }
        else if (obj instanceof VwMultiDisburseTypeXact) {
            disb = (VwMultiDisburseTypeXact) obj;
        }
        else {
            return;
        }
        this.d = disb;
        this.dateCreated = disb.getCreateDate();
        this.updateUserId = disb.getUserId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.MultiDisburseTypeXactDto#setCreditorId(int)
     */
    @Override
    public void setCreditorId(int value) {
        this.d.setCreditorId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.MultiDisburseTypeXactDto#getCreditorId()
     */
    @Override
    public int getCreditorId() {
        return this.d.getCreditorId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.MultiDisburseTypeXactDto#setCreditorTypeId(int)
     */
    @Override
    public void setCreditorTypeId(int value) {
        this.d.setCreditorTypeId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.MultiDisburseTypeXactDto#getCreditorTypeId()
     */
    @Override
    public int getCreditorTypeId() {
        return this.d.getCreditorTypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.MultiDisburseTypeXactDto#setAccountNumber(java.lang.String)
     */
    @Override
    public void setAccountNumber(String value) {
        this.d.setAccountNumber(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.MultiDisburseTypeXactDto#getAccountNumber()
     */
    @Override
    public String getAccountNumber() {
        return this.d.getAccountNumber();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.MultiDisburseTypeXactDto#setCreditLimit(double)
     */
    @Override
    public void setCreditLimit(double value) {
        this.d.setCreditLimit(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.MultiDisburseTypeXactDto#getCreditLimit()
     */
    @Override
    public double getCreditLimit() {
        return this.d.getCreditLimit();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.MultiDisburseTypeXactDto#setTenderDescription(java.lang.String)
     */
    @Override
    public void setTenderDescription(String value) {
        this.d.setTenderDescription(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.MultiDisburseTypeXactDto#getTenderDescription()
     */
    @Override
    public String getTenderDescription() {
        return this.d.getTenderDescription();
    }

}
