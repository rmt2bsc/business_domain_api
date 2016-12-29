package org.dto.adapter.orm.account.subsidiary;

import org.dao.mapping.orm.rmt2.Customer;
import org.dto.CustomerDto;
import org.rmt2.jaxb.BusinessType;

/**
 * * An RMT2 ORM to DTO implementation that adapts data pertaining to the
 * database table, <i>customer</i>.
 * 
 * @author rterrell
 * 
 */
class CustomerRmt2OrmAdapter extends BusinessContactJaxbAdapter implements
        CustomerDto {

    private Customer c;

    /**
     * Create a CustomerRmt2OrmAdapter that adapts data coming from the creditor
     * table
     * 
     * @param customer
     *            an instance of {@link Customer} or null when the desire arises
     *            to create a newly instantiated object.
     * @param contact
     *            an instance of {@link BusinessType} or null when the desire
     *            arises to create a newly instantiated object.
     */
    protected CustomerRmt2OrmAdapter(Customer customer, BusinessType contact) {
        super(contact);
        if (customer == null) {
            customer = new Customer();
        }
        this.c = customer;
        this.dateCreated = customer.getDateCreated();
        this.dateUpdated = customer.getDateUpdated();
        this.updateUserId = customer.getUserId();
        this.ipCreated = customer.getIpCreated();
        this.ipUpdated = customer.getIpUpdated();
        return;
    }

    /**
     * Set customer id
     */
    @Override
    public void setEntityId(int value) {
        this.c.setCustomerId(value);
    }

    /**
     * Get customer id
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

    /**
     * Sets the value of customer id
     */
    @Override
    public void setSubsidiaryId(int value) {
        this.c.setCustomerId(value);
    }

    /**
     * Get the value of customer id
     */
    @Override
    public int getSubsidiaryId() {
        return this.c.getCustomerId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CustomerDto#setCustomerId(int)
     */
    @Override
    public void setCustomerId(int value) {
        this.c.setCustomerId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CustomerDto#getCustomerId()
     */
    @Override
    public int getCustomerId() {
        return this.c.getCustomerId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CustomerDto#setAcctId(int)
     */
    @Override
    public void setAcctId(int value) {
        this.c.setAcctId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CustomerDto#getAcctId()
     */
    @Override
    public int getAcctId() {
        return this.c.getAcctId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CustomerDto#setAccountNo(java.lang.String)
     */
    @Override
    public void setAccountNo(String value) {
        this.c.setAccountNo(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CustomerDto#getAccountNo()
     */
    @Override
    public String getAccountNo() {
        return this.c.getAccountNo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CustomerDto#setPersonId(int)
     */
    @Override
    public void setPersonId(int value) {
        this.c.setPersonId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CustomerDto#getPersonId()
     */
    @Override
    public int getPersonId() {
        return this.c.getPersonId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CustomerDto#setCreditLimit(double)
     */
    @Override
    public void setCreditLimit(double value) {
        this.c.setCreditLimit(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CustomerDto#getCreditLimit()
     */
    @Override
    public double getCreditLimit() {
        return this.c.getCreditLimit();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CustomerDto#setActive(int)
     */
    @Override
    public void setActive(int value) {
        this.c.setActive(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.CustomerDto#getActive()
     */
    @Override
    public int getActive() {
        return this.c.getActive();
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

}
