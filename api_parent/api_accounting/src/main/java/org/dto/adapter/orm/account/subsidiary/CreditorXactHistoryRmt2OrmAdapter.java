package org.dto.adapter.orm.account.subsidiary;

import java.util.Date;

import org.dao.mapping.orm.rmt2.VwCreditorXactHist;
import org.dto.CreditorXactHistoryDto;

import com.api.foundation.TransactionDtoImpl;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * view, <i>vw_creditor_xact_hist</i>.
 * 
 * @author rterrell
 * 
 */
class CreditorXactHistoryRmt2OrmAdapter extends TransactionDtoImpl implements
        CreditorXactHistoryDto {

    private VwCreditorXactHist c;

    /**
     * Create a CreditorXactHistoryRmt2OrmAdapter without performing any data
     * adaptations
     */
    protected CreditorXactHistoryRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a CreditorXactHistoryRmt2OrmAdapter that adapts data coming from
     * the vw_creditor_xact_hist view
     * 
     * @param hist
     *            an instance of {@link VwCreditorXactHist} or null when the
     *            desired arises to create a newly instantiated instance.
     */
    protected CreditorXactHistoryRmt2OrmAdapter(VwCreditorXactHist hist) {
        if (hist == null) {
            hist = new VwCreditorXactHist();
        }
        this.c = hist;
        this.dateCreated = hist.getDateCreated();
        this.updateUserId = hist.getUserId();
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorXactHistoryDto#setCreditorId(int)
     */
    @Override
    public void setCreditorId(int value) {
        this.c.setCreditorId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorXactHistoryDto#getCreditorId()
     */
    @Override
    public int getCreditorId() {
        return this.c.getCreditorId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorXactHistoryDto#setAcctId(int)
     */
    @Override
    public void setAcctId(int value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorXactHistoryDto#getAcctId()
     */
    @Override
    public int getAcctId() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorXactHistoryDto#setCreditorTypeId(int)
     */
    @Override
    public void setCreditorTypeId(int value) {
        this.c.setCreditorTypeId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorXactHistoryDto#getCreditorTypeId()
     */
    @Override
    public int getCreditorTypeId() {
        return this.c.getCreditorTypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorXactHistoryDto#setBusinessId(int)
     */
    @Override
    public void setBusinessId(int value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorXactHistoryDto#getBusinessId()
     */
    @Override
    public int getBusinessId() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorXactHistoryDto#setAccountNumber(java.lang.String)
     */
    @Override
    public void setAccountNumber(String value) {
        this.c.setAccountNumber(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorXactHistoryDto#getAccountNumber()
     */
    @Override
    public String getAccountNumber() {
        return this.c.getAccountNumber();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorXactHistoryDto#setCreditLimit(double)
     */
    @Override
    public void setCreditLimit(double value) {
        this.c.setCreditLimit(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorXactHistoryDto#getCreditLimit()
     */
    @Override
    public double getCreditLimit() {
        return this.c.getCreditLimit();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorXactHistoryDto#setApr(double)
     */
    @Override
    public void setApr(double value) {
        this.c.setApr(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorXactHistoryDto#getApr()
     */
    @Override
    public double getApr() {
        return this.c.getApr();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorXactHistoryDto#setActive(int)
     */
    @Override
    public void setActive(int value) {
        this.c.setActive(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorXactHistoryDto#getActive()
     */
    @Override
    public int getActive() {
        return this.c.getActive();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorXactHistoryDto#setExtAccountNumber(java.lang.String)
     */
    @Override
    public void setExtAccountNumber(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorXactHistoryDto#getExtAccountNumber()
     */
    @Override
    public String getExtAccountNumber() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.CreditorXactHistoryDto#setCreditorTypeDescription(java.lang.String
     * )
     */
    @Override
    public void setCreditorTypeDescription(String value) {
        this.c.setCreditorTypeDescription(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorXactHistoryDto#getCreditorTypeDescription()
     */
    @Override
    public String getCreditorTypeDescription() {
        return this.c.getCreditorTypeDescription();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryXactHistoryDto#setXactId(int)
     */
    @Override
    public void setXactId(int value) {
        this.c.setXactId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryXactHistoryDto#getXactId()
     */
    @Override
    public int getXactId() {
        return this.c.getXactId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryXactHistoryDto#setXactTypeId(int)
     */
    @Override
    public void setXactTypeId(int value) {
        this.c.setXactTypeId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryXactHistoryDto#getXactTypeId()
     */
    @Override
    public int getXactTypeId() {
        return this.c.getXactTypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryXactHistoryDto#setXactSubtypeId(int)
     */
    @Override
    public void setXactSubtypeId(int value) {
        this.c.setXactSubtypeId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryXactHistoryDto#getXactSubtypeId()
     */
    @Override
    public int getXactSubtypeId() {
        return this.c.getXactSubtypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryXactHistoryDto#setXactDate(java.util.Date)
     */
    @Override
    public void setXactDate(Date value) {
        this.c.setXactDate(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryXactHistoryDto#getXactDate()
     */
    @Override
    public Date getXactDate() {
        return this.c.getXactDate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryXactHistoryDto#setXactAmount(double)
     */
    @Override
    public void setXactAmount(double value) {
        this.c.setXactAmount(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryXactHistoryDto#getXactAmount()
     */
    @Override
    public double getXactAmount() {
        return this.c.getXactAmount();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryXactHistoryDto#setXactTenderId(int)
     */
    @Override
    public void setXactTenderId(int value) {
        this.c.setTenderId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryXactHistoryDto#getXactTenderId()
     */
    @Override
    public int getXactTenderId() {
        return this.c.getTenderId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryXactHistoryDto#setXactNegInstrNo(java.lang.String)
     */
    @Override
    public void setXactNegInstrNo(String value) {
        this.c.setNegInstrNo(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryXactHistoryDto#getXactNegInstrNo()
     */
    @Override
    public String getXactNegInstrNo() {
        return this.c.getNegInstrNo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.SubsidiaryXactHistoryDto#setXactBankTransInd(java.lang.String)
     */
    @Override
    public void setXactBankTransInd(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryXactHistoryDto#getXactBankTransInd()
     */
    @Override
    public String getXactBankTransInd() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryXactHistoryDto#setXactConfirmNo(java.lang.String)
     */
    @Override
    public void setXactConfirmNo(String value) {
        this.c.setConfirmNo(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryXactHistoryDto#getXactConfirmNo()
     */
    @Override
    public String getXactConfirmNo() {
        return this.c.getConfirmNo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.SubsidiaryXactHistoryDto#setXactEntityRefNo(java.lang.String)
     */
    @Override
    public void setXactEntityRefNo(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryXactHistoryDto#getXactEntityRefNo()
     */
    @Override
    public String getXactEntityRefNo() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryXactHistoryDto#setXactPostedDate(java.util.Date)
     */
    @Override
    public void setXactPostedDate(Date value) {
        this.c.setPostedDate(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryXactHistoryDto#getXactPostedDate()
     */
    @Override
    public Date getXactPostedDate() {
        return this.c.getPostedDate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryXactHistoryDto#setXactReason(java.lang.String)
     */
    @Override
    public void setXactReason(String value) {
        this.c.setReason(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryXactHistoryDto#getXactReason()
     */
    @Override
    public String getXactReason() {
        return this.c.getReason();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryXactHistoryDto#setXactDocumentId(int)
     */
    @Override
    public void setXactDocumentId(int value) {
        this.c.setDocumentId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryXactHistoryDto#getXactDocumentId()
     */
    @Override
    public int getXactDocumentId() {
        return this.c.getDocumentId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.SubsidiaryXactHistoryDto#setXactTypeDescription(java.lang.String)
     */
    @Override
    public void setXactTypeDescription(String value) {
        this.c.setXactTypeName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryXactHistoryDto#getXactTypeDescription()
     */
    @Override
    public String getXactTypeDescription() {
        return this.c.getXactTypeName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryActivityDto#setActivityId(int)
     */
    @Override
    public void setActivityId(int value) {
        this.c.setCreditorActivityId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryActivityDto#getActivityId()
     */
    @Override
    public int getActivityId() {
        return this.c.getCreditorActivityId();
    }

    /**
     * Set the creditor id.
     */
    @Override
    public void setSubsidiaryId(int value) {
        this.c.setCreditorId(value);
    }

    /**
     * Return the creditor id.
     */
    @Override
    public int getSubsidiaryId() {
        return this.c.getCreditorId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryActivityDto#setActivityAmount(double)
     */
    @Override
    public void setActivityAmount(double value) {
        this.c.setCreditorActivityAmount(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryActivityDto#getActivityAmount()
     */
    @Override
    public double getActivityAmount() {
        return this.c.getCreditorActivityAmount();
    }

    /**
     * Set the creditor id.
     */
    @Override
    public void setEntityId(int value) {
        this.c.getCreditorId();
    }

    /**
     * Return creditor id
     */
    @Override
    public int getEntityId() {
        return this.c.getCreditorId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonAccountingDto#setEntityName(java.lang.String)
     */
    @Override
    public void setEntityName(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CommonAccountingDto#getEntityName()
     */
    @Override
    public String getEntityName() {
        return null;
    }

}
