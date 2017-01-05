package org.dto.adapter.orm.account.subsidiary;

import java.util.Date;

import org.dao.mapping.orm.rmt2.VwCustomerXactHist;
import org.dto.CustomerXactHistoryDto;

import com.api.foundation.TransactionDtoImpl;

/**
 * @author rterrell
 * 
 */
class CustomerXactHistoryRmt2OrmAdapter extends TransactionDtoImpl implements
        CustomerXactHistoryDto {

    private VwCustomerXactHist c;

    /**
     * Create a CustomerXactHistoryRmt2OrmAdapter without performing any data
     * adaptations
     */
    public CustomerXactHistoryRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a CustomerXactHistoryRmt2OrmAdapter that adapts data coming from
     * the vw_customer_xact_hist view
     * 
     * @param hist
     *            an instance of {@link VwCustomerXactHist} or null when the
     *            desired arises to create a newly instantiated instance.
     */
    public CustomerXactHistoryRmt2OrmAdapter(VwCustomerXactHist hist) {
        if (hist == null) {
            hist = new VwCustomerXactHist();
        }
        this.c = hist;
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CustomerXactHistoryDto#setCustomerId(int)
     */
    @Override
    public void setCustomerId(int value) {
        this.c.setCustomerId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CustomerXactHistoryDto#getCustomerId()
     */
    @Override
    public int getCustomerId() {
        return this.c.getCustomerId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CustomerXactHistoryDto#setAccountNo(java.lang.String)
     */
    @Override
    public void setAccountNo(String value) {
        this.c.setAccountNo(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CustomerXactHistoryDto#getAccountNo()
     */
    @Override
    public String getAccountNo() {
        return this.c.getAccountNo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CustomerXactHistoryDto#setPersonId(int)
     */
    @Override
    public void setPersonId(int value) {
        this.c.setPersonId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CustomerXactHistoryDto#getPersonId()
     */
    @Override
    public int getPersonId() {
        return this.c.getPersonId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CustomerXactHistoryDto#setBusinessId(int)
     */
    @Override
    public void setBusinessId(int value) {
        this.c.setBusinessId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CustomerXactHistoryDto#getBusinessId()
     */
    @Override
    public int getBusinessId() {
        return this.getBusinessId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CustomerXactHistoryDto#setCreditLimit(double)
     */
    @Override
    public void setCreditLimit(double value) {
        this.c.setCreditLimit(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CustomerXactHistoryDto#getCreditLimit()
     */
    @Override
    public double getCreditLimit() {
        return this.c.getCreditLimit();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CustomerXactHistoryDto#setActive(int)
     */
    @Override
    public void setActive(int value) {
        this.c.setActive(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CustomerXactHistoryDto#getActive()
     */
    @Override
    public int getActive() {
        return this.c.getActive();
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
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryXactHistoryDto#getXactTenderId()
     */
    @Override
    public int getXactTenderId() {
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryXactHistoryDto#setXactNegInstrNo(java.lang.String)
     */
    @Override
    public void setXactNegInstrNo(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryXactHistoryDto#getXactNegInstrNo()
     */
    @Override
    public String getXactNegInstrNo() {
        return null;
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
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryXactHistoryDto#getXactPostedDate()
     */
    @Override
    public Date getXactPostedDate() {
        return null;
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
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryActivityDto#getActivityId()
     */
    @Override
    public int getActivityId() {
        return 0;
    }

    /**
     * Set the customer id.
     */
    @Override
    public void setSubsidiaryId(int value) {
        this.c.setCustomerId(value);
    }

    /**
     * Return the customer id.
     */
    @Override
    public int getSubsidiaryId() {
        return this.c.getCustomerId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryActivityDto#setActivityAmount(double)
     */
    @Override
    public void setActivityAmount(double value) {
        this.c.setCustomerActivityAmount(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.SubsidiaryActivityDto#getActivityAmount()
     */
    @Override
    public double getActivityAmount() {
        return this.c.getCustomerActivityAmount();
    }

    /**
     * Set the customer id.
     */
    @Override
    public void setEntityId(int value) {
        this.c.setCustomerId(value);
    }

    /**
     * Return customer id
     */
    @Override
    public int getEntityId() {
        return this.c.getCustomerId();
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
