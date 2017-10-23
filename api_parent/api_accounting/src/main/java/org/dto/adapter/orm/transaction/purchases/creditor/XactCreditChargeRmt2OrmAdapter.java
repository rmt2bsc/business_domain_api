package org.dto.adapter.orm.transaction.purchases.creditor;

import java.util.Date;

import org.dao.mapping.orm.rmt2.VwXactCreditChargeList;
import org.dto.SubsidiaryContactInfoDto;
import org.dto.XactCreditChargeDto;
import org.dto.adapter.orm.account.subsidiary.Rmt2SubsidiaryDtoFactory;
import org.dto.adapter.orm.transaction.XactRmt2OrmAdapter;

/**
 * An RMT2 ORM implementation that adapts data pertaining to the database views
 * <i>vw_xact_credit_charge_list</i> and <i>vw_xact_item_list</i> to an instance
 * of {@link XactCreditChargeDto}.
 * 
 * @author Roy Terrell
 * 
 */
class XactCreditChargeRmt2OrmAdapter extends XactRmt2OrmAdapter implements
        XactCreditChargeDto {

    private VwXactCreditChargeList x;

    private SubsidiaryContactInfoDto c;

    /**
     * Default constructor
     */
    XactCreditChargeRmt2OrmAdapter() {
        this.x = new VwXactCreditChargeList();
        this.c = Rmt2SubsidiaryDtoFactory.createSubsidiaryInstance(null);
    }

    /**
     * Creates a XactCreditChargeRmt2OrmAdapter containing the base transaction
     * data only.
     * 
     * @param xactList
     *            an instance of {@link VwXactCreditChargeList}
     * @param contactInfo
     *            an instance of {@link SubsidiaryContactInfoDto}
     */
    XactCreditChargeRmt2OrmAdapter(VwXactCreditChargeList xactList,
            SubsidiaryContactInfoDto contactInfo) {
        if (xactList == null) {
            xactList = new VwXactCreditChargeList();
        }
        if (contactInfo == null) {
            contactInfo = Rmt2SubsidiaryDtoFactory.createSubsidiaryInstance(null);
        }
        this.x = xactList;
        this.c = contactInfo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#setCreditorId(int)
     */
    @Override
    public void setCreditorId(int value) {
        this.x.setCreditorId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#getCreditorId()
     */
    @Override
    public int getCreditorId() {
        return this.x.getCreditorId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#setBusinessId(int)
     */
    @Override
    public void setBusinessId(int value) {
        this.x.setBusinessId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#getBusinessId()
     */
    @Override
    public int getBusinessId() {
        return this.x.getBusinessId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#setName(java.lang.String)
     */
    @Override
    public void setCreditorName(String value) {
        this.c.setContactName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#getName()
     */
    @Override
    public String getCreditorName() {
        return this.c.getContactName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#setAccountNumber(java.lang.String)
     */
    @Override
    public void setAccountNumber(String value) {
        this.x.setAccountNo(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#getAccountNumber()
     */
    @Override
    public String getAccountNumber() {
        return this.x.getAccountNo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#setExtAccountNumber(java.lang.String)
     */
    @Override
    public void setExtAccountNumber(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#getExtAccountNumber()
     */
    @Override
    public String getExtAccountNumber() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#setActive(int)
     */
    @Override
    public void setActive(int value) {
        this.x.setActive(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#getActive()
     */
    @Override
    public int getActive() {
        return this.x.getActive();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#setBalance(double)
     */
    @Override
    public void setBalance(double value) {
        this.x.setBalance(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#getBalance()
     */
    @Override
    public double getBalance() {
        return this.x.getBalance();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#setXactId(int)
     */
    @Override
    public void setXactId(int value) {
        this.x.setXactId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#getXactId()
     */
    @Override
    public int getXactId() {
        return this.x.getXactId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#setXactSubtypeId(int)
     */
    @Override
    public void setXactSubtypeId(int value) {
        this.x.setXactSubtypeId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#getXactSubtypeId()
     */
    @Override
    public int getXactSubtypeId() {
        return this.x.getXactSubtypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#setXactDate(java.util.Date)
     */
    @Override
    public void setXactDate(Date value) {
        this.x.setXactDate(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#getXactDate()
     */
    @Override
    public Date getXactDate() {
        return this.x.getXactDate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#setXactAmount(double)
     */
    @Override
    public void setXactAmount(double value) {
        this.x.setXactAmount(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#getXactAmount()
     */
    @Override
    public double getXactAmount() {
        return this.x.getXactAmount();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#setXactTenderId(int)
     */
    @Override
    public void setXactTenderId(int value) {
        this.x.setTenderId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#getXactTenderId()
     */
    @Override
    public int getXactTenderId() {
        return this.x.getTenderId();
    }

    // /* (non-Javadoc)
    // * @see org.dto.XactCreditChargeDto#setXactTenderName(java.lang.String)
    // */
    // @Override
    // public void setXactTenderName(String value) {
    // this.x.setTenderDescription(value);
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.XactCreditChargeDto#getXactTenderName()
    // */
    // @Override
    // public String getXactTenderString {
    // return this.x.getTenderDescription();
    // }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#setXactNegInstrNo(java.lang.String)
     */
    @Override
    public void setXactNegInstrNo(String value) {
        this.x.setNegInstrNo(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#getXactNegInstrNo()
     */
    @Override
    public String getXactNegInstrNo() {
        return this.x.getNegInstrNo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#setXactBankTransInd(java.lang.String)
     */
    @Override
    public void setXactBankTransInd(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#getXactBankTransInd()
     */
    @Override
    public String getXactBankTransInd() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#setXactConfirmNo(java.lang.String)
     */
    @Override
    public void setXactConfirmNo(String value) {
        this.x.setConfirmNo(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#getXactConfirmNo()
     */
    @Override
    public String getXactConfirmNo() {
        return this.x.getConfirmNo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#setXactEntityRefNo(java.lang.String)
     */
    @Override
    public void setXactEntityRefNo(String value) {
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#getXactEntityRefNo()
     */
    @Override
    public String getXactEntityRefNo() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#setXactReason(java.lang.String)
     */
    @Override
    public void setXactReason(String value) {
        this.x.setReason(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#getXactReason()
     */
    @Override
    public String getXactReason() {
        return this.x.getReason();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#setDocumentId(int)
     */
    @Override
    public void setDocumentId(int value) {
        this.x.setDocumentId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#getDocumentId()
     */
    @Override
    public int getDocumentId() {
        return this.x.getDocumentId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#setTaxId(java.lang.String)
     */
    @Override
    public void setTaxId(String value) {
        this.c.setTaxId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#getTaxId()
     */
    @Override
    public String getTaxId() {
        return c.getTaxId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#setPhone(java.lang.String)
     */
    @Override
    public void setPhone(String value) {
        this.c.setPhoneCompany(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.XactCreditChargeDto#getPhone()
     */
    @Override
    public String getPhone() {
        return this.c.getPhoneCompany();
    }

    // /* (non-Javadoc)
    // * @see org.dto.XactCreditChargeDto#setXactItems(java.util.List)
    // */
    // @Override
    // public void setXactItems(List<VwXactItemList> items) {
    // this.i = items;
    // }
    //
    // /* (non-Javadoc)
    // * @see org.dto.XactCreditChargeDto#getXactItems()
    // */
    // @Override
    // public List<XactTypeItemActivityDto> getXactItems() {
    // return this.dtoItems;
    // }

}
