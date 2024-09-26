package org.dto.adapter.orm.account.subsidiary;

import org.dao.mapping.orm.rmt2.Creditor;
import org.dto.CreditorDto;
import org.rmt2.jaxb.BusinessType;

/**
 * An RMT2 ORM to DTO implementation that adapts data pertaining to the database
 * table, <i>creditor</i>.
 * 
 * @author rterrell
 * 
 */
class CreditorRmt2OrmAdapter extends BusinessContactJaxbAdapter implements
        CreditorDto {

    private Creditor c;
    private Double balance;

    /**
     * Create a CreditorRmt2OrmAdapter that adapts data coming from the creditor
     * table
     * 
     * @param creditor
     *            an instance of {@link Creditor} or null when the desire arises
     *            to create a newly instantiated object.
     * @param contact
     *            an instance of {@link BusinessType} or null when the desire
     *            arises to create a newly instantiated object.
     */
    protected CreditorRmt2OrmAdapter(Creditor creditor, BusinessType contact) {
        super(contact);
        if (creditor == null) {
            creditor = new Creditor();
        }
        this.c = creditor;
        this.dateCreated = creditor.getDateCreated();
        this.dateUpdated = creditor.getDateUpdated();
        this.updateUserId = creditor.getUserId();
        this.ipCreated = creditor.getIpCreated();
        this.ipUpdated = creditor.getIpUpdated();
        return;
    }

    /**
     * Sets the value of creditor id
     */
    @Override
    public void setEntityId(int value) {
        this.c.setCreditorId(value);
    }

    /**
     * Get the value of creditor id
     */
    @Override
    public int getEntityId() {
        return this.c.getCreditorId();
    }

    /**
     * Sets the value of creditor id
     */
    @Override
    public void setSubsidiaryId(int value) {
        this.c.setCreditorId(value);
    }

    /**
     * Get the value of creditor id
     */
    @Override
    public int getSubsidiaryId() {
        return this.c.getCreditorId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorDto#setCreditorId(int)
     */
    @Override
    public void setCreditorId(int value) {
        this.c.setCreditorId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorDto#getCreditorId()
     */
    @Override
    public int getCreditorId() {
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

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorDto#setAcctId(int)
     */
    @Override
    public void setAcctId(int value) {
        this.c.setAcctId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorDto#getAcctId()
     */
    @Override
    public int getAcctId() {
        return this.c.getAcctId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorDto#setCreditorTypeId(int)
     */
    @Override
    public void setCreditorTypeId(int value) {
        this.c.setCreditorTypeId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorDto#getCreditorTypeId()
     */
    @Override
    public int getCreditorTypeId() {
        return this.c.getCreditorTypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorDto#setAccountNumber(java.lang.String)
     */
    @Override
    public void setAccountNo(String value) {
        this.c.setAccountNumber(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorDto#getAccountNumber()
     */
    @Override
    public String getAccountNo() {
        return this.c.getAccountNumber();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorDto#setCreditLimit(double)
     */
    @Override
    public void setCreditLimit(double value) {
        this.c.setCreditLimit(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorDto#getCreditLimit()
     */
    @Override
    public double getCreditLimit() {
        return this.c.getCreditLimit();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorDto#setApr(double)
     */
    @Override
    public void setApr(double value) {
        this.c.setApr(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorDto#getApr()
     */
    @Override
    public double getApr() {
        return this.c.getApr();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorDto#setActive(int)
     */
    @Override
    public void setActive(int value) {
        this.c.setActive(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorDto#getActive()
     */
    @Override
    public int getActive() {
        return this.c.getActive();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorDto#setExtAccountNumber(java.lang.String)
     */
    @Override
    public void setExtAccountNumber(String value) {
        this.c.setExtAccountNumber(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CreditorDto#getExtAccountNumber()
     */
    @Override
    public String getExtAccountNumber() {
        return this.c.getExtAccountNumber();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.adapter.orm.account.subsidiary.BusinessContactJaxbAdapter#getContactId
     * ()
     */
    @Override
    public int getContactId() {
        int busId = super.getContactId();
        if (busId == 0) {
            busId = this.c.getBusinessId();
        }
        return busId;
    }

    /**
     * @return the balance
     */
    public Double getBalance() {
        return this.balance;
    }

    /**
     * @param balance
     *            the balance to set
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

}
