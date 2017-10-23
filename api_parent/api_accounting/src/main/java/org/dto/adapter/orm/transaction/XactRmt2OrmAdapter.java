package org.dto.adapter.orm.transaction;

import java.util.Date;

import org.dao.mapping.orm.rmt2.VwXactCreditChargeList;
import org.dao.mapping.orm.rmt2.VwXactList;
import org.dao.mapping.orm.rmt2.VwXactTypeItemActivity;
import org.dao.mapping.orm.rmt2.Xact;
import org.dto.XactDto;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * tables <i>xact</i>.
 * 
 * @author rterrell
 * 
 */
public class XactRmt2OrmAdapter extends XactTypeRmt2OrmAdapter implements XactDto {

    private Xact x;

//    private String customCriteria;

    /**
     * Create a XactRmt2OrmAdapter without performing any data adaptations
     */
    protected XactRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a XactRmt2OrmAdapter that adapts data coming from the xact table
     * 
     * @param xact
     *            an instance of {@link Xact} or null when the desired arises to
     *            create a newly instantiated instance.
     */
    protected XactRmt2OrmAdapter(Xact xact) {
        this.updateObjHeirarchy(xact);

        // if (xact != null) {
        // // Setup XactType instance at the ancestor level.
        // XactType xt = new XactType();
        // xt.setXactTypeId(xact.getXactTypeId());
        // this.updateObjHeirarchy(xt);
        // }
        return;
    }

    /**
     * Create a XactRmt2OrmAdapter that adapts data coming from the xact table
     * as well as custom selection criteria.
     * 
     * @param xact
     *            an instance of {@link Xact} or null when the desired arises to
     *            create a newly instantiated instance.
     * @param criteria
     *            custom selection criteria
     */
    protected XactRmt2OrmAdapter(Xact xact, String criteria) {
        this(xact);
        this.setCriteria(criteria);
        return;
    }

    /**
     * Create a XactRmt2OrmAdapter that adapts data coming from the vw_xact_list
     * view.
     * 
     * @param xact
     *            an instance of {@link VwXactList} or null when the desired
     *            arises to create a newly instantiated instance.
     */
    protected XactRmt2OrmAdapter(VwXactList xact) {
        this.updateObjHeirarchy(xact);
        return;
    }

    /**
     * Create a XactRmt2OrmAdapter that adapts data coming from the vw_xact_list
     * view as well as custom selection criteria.
     * 
     * @param xact
     *            an instance of {@link VwXactList} or null when the desired
     *            arises to create a newly instantiated instance.
     * @param criteria
     *            custom selection criteria
     */
    protected XactRmt2OrmAdapter(VwXactList xact, String criteria) {
        this(xact);
        this.setCriteria(criteria);
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.orm.XactTypeRmt2OrmAdapter#initDataObject(java.lang.Object)
     */
    @Override
    protected void updateObjHeirarchy(Object obj) {
        super.updateObjHeirarchy(obj);

        boolean targetObject = false;
        if (obj == null) {
            this.x = new Xact();
            targetObject = true;
        }
        else if (obj instanceof Xact) {
            this.x = (Xact) obj;
            targetObject = true;
        }
        else if (obj instanceof VwXactList) {
            VwXactList in = (VwXactList) obj;
            this.x = new Xact();
            this.x.setXactId(in.getId());
            this.x.setXactTypeId(in.getXactTypeId());
            this.x.setXactSubtypeId(in.getXactSubtypeId());
            this.x.setXactDate(in.getXactDate());
            this.x.setXactAmount(in.getXactAmount());
            this.x.setTenderId(in.getTenderId());
            this.x.setNegInstrNo(in.getNegInstrNo());
            this.x.setConfirmNo(in.getConfirmNo());
            this.x.setPostedDate(in.getPostedDate());
            this.x.setReason(in.getReason());
            this.x.setDocumentId(in.getDocumentId());
            this.x.setDateCreated(in.getCreateDate());
            targetObject = true;
        }
        else if (obj instanceof VwXactCreditChargeList) {
            VwXactCreditChargeList in = (VwXactCreditChargeList) obj;
            this.x = new Xact();
            this.x.setXactId(in.getXactId());
            this.x.setXactTypeId(in.getXactTypeId());
            this.x.setXactSubtypeId(in.getXactSubtypeId());
            this.x.setXactDate(in.getXactDate());
            this.x.setXactAmount(in.getXactAmount());
            this.x.setTenderId(in.getTenderId());
            this.x.setNegInstrNo(in.getNegInstrNo());
            this.x.setConfirmNo(in.getConfirmNo());
            this.x.setPostedDate(in.getPostedDate());
            this.x.setReason(in.getReason());
            this.x.setDocumentId(in.getDocumentId());
            this.x.setDateCreated(in.getCreditorDateCreated());
            targetObject = true;
        }
        else if (obj instanceof VwXactTypeItemActivity) {
            VwXactTypeItemActivity in = (VwXactTypeItemActivity) obj;
            this.x = new Xact();
            this.x.setXactId(in.getXactId());
            this.x.setXactTypeId(in.getXactTypeId());
            this.x.setXactAmount(in.getXactAmount());
            this.x.setXactDate(in.getXactDate());
            this.x.setReason(in.getReason());
            this.x.setConfirmNo(in.getConfirmNo());
            this.x.setDocumentId(in.getDocumentId());
        }
        else {
            // Leave Xact object as is...
        }
        // If this is the main object we are working with, then assign the
        // timestamp properties to the main object's.
        if (targetObject) {
            this.dateCreated = this.x.getDateCreated();
            this.dateUpdated = this.x.getDateUpdated();
            this.updateUserId = this.x.getUserId();
            this.ipCreated = this.x.getIpCreated();
            this.ipUpdated = this.x.getIpUpdated();
        }
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#setXactId(int)
     */
    @Override
    public void setXactId(int value) {
        this.x.setXactId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#getXactId()
     */
    @Override
    public int getXactId() {
        return this.x.getXactId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#setXactSubtypeId(int)
     */
    @Override
    public void setXactSubtypeId(int value) {
        this.x.setXactSubtypeId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#getXactSubtypeId()
     */
    @Override
    public int getXactSubtypeId() {
        return this.x.getXactSubtypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#setXactDate(java.util.Date)
     */
    @Override
    public void setXactDate(Date value) {
        this.x.setXactDate(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#getXactDate()
     */
    @Override
    public Date getXactDate() {
        return this.x.getXactDate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#setXactAmount(double)
     */
    @Override
    public void setXactAmount(double value) {
        this.x.setXactAmount(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#getXactAmount()
     */
    @Override
    public double getXactAmount() {
        return this.x.getXactAmount();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#setXactTenderId(int)
     */
    @Override
    public void setXactTenderId(int value) {
        this.x.setTenderId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#getXactTenderId()
     */
    @Override
    public int getXactTenderId() {
        return this.x.getTenderId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#setXactNegInstrNo(java.lang.String)
     */
    @Override
    public void setXactNegInstrNo(String value) {
        this.x.setNegInstrNo(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#getXactNegInstrNo()
     */
    @Override
    public String getXactNegInstrNo() {
        return this.x.getNegInstrNo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#setXactBankTransInd(java.lang.String)
     */
    @Override
    public void setXactBankTransInd(String value) {
        this.x.setBankTransInd(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#getXactBankTransInd()
     */
    @Override
    public String getXactBankTransInd() {
        return this.x.getBankTransInd();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#setXactConfirmNo(java.lang.String)
     */
    @Override
    public void setXactConfirmNo(String value) {
        this.x.setConfirmNo(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#getXactConfirmNo()
     */
    @Override
    public String getXactConfirmNo() {
        return this.x.getConfirmNo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#setXactEntityRefNo(java.lang.String)
     */
    @Override
    public void setXactEntityRefNo(String value) {
        this.x.setEntityRefNo(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#getXactEntityRefNo()
     */
    @Override
    public String getXactEntityRefNo() {
        return this.x.getEntityRefNo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#setXactPostedDate(java.util.Date)
     */
    @Override
    public void setXactPostedDate(Date value) {
        this.x.setPostedDate(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#getXactPostedDate()
     */
    @Override
    public Date getXactPostedDate() {
        return this.x.getPostedDate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#setXactReason(java.lang.String)
     */
    @Override
    public void setXactReason(String value) {
        this.x.setReason(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#getXactReason()
     */
    @Override
    public String getXactReason() {
        return this.x.getReason();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#setDocumentId(int)
     */
    @Override
    public void setDocumentId(int value) {
        this.x.setDocumentId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#getDocumentId()
     */
    @Override
    public int getDocumentId() {
        return this.x.getDocumentId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#setXactCodeGrpId(int)
     */
    @Override
    public void setXactCodeGrpId(int value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#getXactCodeGrpId()
     */
    @Override
    public int getXactCodeGrpId() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#setXactCodeGrpDescription(java.lang.String)
     */
    @Override
    public void setXactCodeGrpDescription(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#getXactCodeGrpDescription()
     */
    @Override
    public String getXactCodeGrpDescription() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#setXactCodeId(int)
     */
    @Override
    public void setXactCodeId(int value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#getXactCodeId()
     */
    @Override
    public int getXactCodeId() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#setXactCodeDescription(java.lang.String)
     */
    @Override
    public void setXactCodeDescription(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactDto#getXactCodeDescription()
     */
    @Override
    public String getXactCodeDescription() {
        return null;
    }

    @Override
    public String toString() {
        if (x != null) {
            return x.toString();    
        }
        return "";
    }

//    @Override
//    public void setCriteria(String value) {
//        this.customCriteria = value;
//    }
//
//    @Override
//    public String getCriteria() {
//        return this.customCriteria;
//    }

}
