package org.dto.adapter.orm.transaction;

import java.util.Date;

import org.dao.mapping.orm.rmt2.VwGenericXactList;
import org.dto.CommonXactDto;

import com.RMT2Base;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * view, <i>vw_generic_xact_list</i>.
 * 
 * @author Roy Terrell
 * 
 */
class GenericXactRmt2OrmAdapter extends RMT2Base implements CommonXactDto {

    private VwGenericXactList x;

    /**
     * Create a GenericXactRmt2OrmAdapter without performing any data
     * adaptations
     */
    protected GenericXactRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a GenericXactRmt2OrmAdapter that adapts data coming from the
     * <i>vw_generic_xact_list</i> view
     * 
     * @param xact
     *            an instance of {@link VwGenericXactList} or null when the
     *            desired arises to create a newly instantiated instance.
     */
    protected GenericXactRmt2OrmAdapter(VwGenericXactList xact) {
        if (xact == null) {
            xact = new VwGenericXactList();
        }
        this.x = xact;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#setSubsidiaryId(int)
     */
    @Override
    public void setParentEntityId(int value) {
        this.x.setParentEntityId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#getSubsidiaryId()
     */
    @Override
    public int getParentEntityId() {
        return this.x.getParentEntityId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#setSpecXactLevel1Id(int)
     */
    @Override
    public void setSpecXactLevel1Id(int value) {
        this.x.setSpecXactLevel1Id(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#getSpecXactLevel1Id()
     */
    @Override
    public int getSpecXactLevel1Id() {
        return this.x.getSpecXactLevel1Id();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#setSpecXactLevel1Date(java.util.Date)
     */
    @Override
    public void setSpecXactLevel1Date(Date value) {
        this.x.setSpecXactLevel1Date(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#getSpecXactLevel1Date()
     */
    @Override
    public Date getSpecXactLevel1Date() {
        return this.x.getSpecXactLevel1Date();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#setSpecXactLevel2Id(int)
     */
    @Override
    public void setSpecXactLevel2Id(int value) {
        this.x.setSpecXactLevel2Id(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#getSpecXactLevel2Id()
     */
    @Override
    public int getSpecXactLevel2Id() {
        return this.x.getSpecXactLevel2Id();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#setSpecXactLevel2Date(java.util.Date)
     */
    @Override
    public void setSpecXactLevel2Date(Date value) {
        this.x.setSpecXactLevel2Date(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#getSpecXactLevel2Date()
     */
    @Override
    public Date getSpecXactLevel2Date() {
        return this.x.getSpecXactLevel2Date();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#setXactId(int)
     */
    @Override
    public void setXactId(int value) {
        this.x.setXactId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#getXactId()
     */
    @Override
    public int getXactId() {
        return this.x.getXactId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#setXactTypeId(int)
     */
    @Override
    public void setXactTypeId(int value) {
        this.x.setXactTypeId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#getXactTypeId()
     */
    @Override
    public int getXactTypeId() {
        return this.x.getXactTypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#setXactSubtypeId(int)
     */
    @Override
    public void setXactSubtypeId(int value) {
        this.x.setXactSubtypeId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#getXactSubtypeId()
     */
    @Override
    public int getXactSubtypeId() {
        return this.x.getXactSubtypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#setXactDate(java.util.Date)
     */
    @Override
    public void setXactDate(Date value) {
        this.x.setXactDate(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#getXactDate()
     */
    @Override
    public Date getXactDate() {
        return this.x.getXactDate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#setXactDateStr(java.lang.String)
     */
    @Override
    public void setXactDateStr(String value) {
        this.x.setXactDateStr(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#getXactDateStr()
     */
    @Override
    public String getXactDateStr() {
        return this.x.getXactDateStr();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#setXactAmount(double)
     */
    @Override
    public void setXactAmount(double value) {
        this.x.setXactAmount(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#getXactAmount()
     */
    @Override
    public double getXactAmount() {
        return this.x.getXactAmount();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#setConfirmNo(java.lang.String)
     */
    @Override
    public void setConfirmNo(String value) {
        this.x.setConfirmNo(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#getConfirmNo()
     */
    @Override
    public String getConfirmNo() {
        return this.x.getConfirmNo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#setReason(java.lang.String)
     */
    @Override
    public void setReason(String value) {
        this.x.setXactReason(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#getReason()
     */
    @Override
    public String getReason() {
        return this.x.getXactReason();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#setDocumentId(int)
     */
    @Override
    public void setDocumentId(int value) {
        this.x.setDocumentId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#getDocumentId()
     */
    @Override
    public int getDocumentId() {
        return this.x.getDocumentId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#setXactTypeDescription(java.lang.String)
     */
    @Override
    public void setXactTypeDescription(String value) {
        this.x.setXactTypeName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#getXactTypeDescription()
     */
    @Override
    public String getXactTypeDescription() {
        return this.x.getXactTypeName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#setAccountNo(java.lang.String)
     */
    @Override
    public void setAccountNo(String value) {
        this.x.setAccountNo(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#getAccountNo()
     */
    @Override
    public String getAccountNo() {
        return this.x.getAccountNo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#setBusinessId(int)
     */
    @Override
    public void setBusinessId(int value) {
        this.x.setBusinessId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#getBusinessId()
     */
    @Override
    public int getBusinessId() {
        return this.x.getBusinessId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#setBusinessName(java.lang.String)
     */
    @Override
    public void setBusinessName(String value) {
        this.x.setBusinessName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#getBusinessName()
     */
    @Override
    public String getBusinessName() {
        return this.x.getBusinessName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#setInvoiceNo(java.lang.String)
     */
    @Override
    public void setInvoiceNo(String value) {
        this.x.setInvoiceNo(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonXactDto#getInvoiceNo()
     */
    @Override
    public String getInvoiceNo() {
        return this.x.getInvoiceNo();
    }

}
