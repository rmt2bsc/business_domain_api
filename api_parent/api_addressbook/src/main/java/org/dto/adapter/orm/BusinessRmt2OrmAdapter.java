package org.dto.adapter.orm;

import org.dao.contacts.ContactsConst;
import org.dao.mapping.orm.rmt2.Address;
import org.dao.mapping.orm.rmt2.Business;
import org.dao.mapping.orm.rmt2.VwBusinessAddress;
import org.dto.BusinessContactDto;

/**
 * Adapts an RMT2 ORM <i>Business</i> and an <i>Address</i> object to an
 * <i>BusinessContactDto</i>.
 * 
 * @author rterrell
 * 
 */
class BusinessRmt2OrmAdapter extends AddressRmt2OrmAdapter implements
        BusinessContactDto {

    private Business bus;

    /**
     * Create a BusinessRmt2OrmAdapter using an instance of
     * <i>VwBusinessAddress</i>, which contains both business and address
     * contact information.
     * 
     * @param bus
     *            an instance of {@link VwBusinessAddress} or null for the
     *            purpose of creating a new Business object
     */
    protected BusinessRmt2OrmAdapter(VwBusinessAddress bus) {
        super(bus);
        this.bus = new Business();
        if (bus != null) {
            this.bus.setBusinessId(bus.getBusinessId());
            this.bus.setEntityTypeId(bus.getBusEntityTypeId());
            this.bus.setServTypeId(bus.getBusServTypeId());
            this.bus.setLongname(bus.getBusLongname());
            this.bus.setShortname(bus.getBusShortname());
            this.bus.setContactEmail(bus.getContactEmail());
            this.bus.setContactExt(bus.getBusContactExt());
            this.bus.setContactFirstname(bus.getBusContactFirstname());
            this.bus.setContactLastname(bus.getBusContactLastname());
            this.bus.setContactPhone(bus.getBusContactPhone());
            this.bus.setTaxId(bus.getBusTaxId());
            this.bus.setWebsite(bus.getBusWebsite());
            this.bus.setUserId("N/A");
        }

        this.dateCreated = this.bus.getDateCreated();
        this.dateUpdated = this.bus.getDateUpdated();
        this.updateUserId = this.bus.getUserId();
        this.setContactType(ContactsConst.CONTACT_TYPE_BUSINESS);
        return;
    }

    /**
     * Create a BusinessRmt2OrmAdapter using an instance of <i>Business</i> and
     * <i>Address</i>.
     * 
     * @param bus
     *            an instance of {@link Business} or null for the purpose of
     *            creating a new Business object
     * @param addr
     *            an instance of {@link Address} or null for the purpose of
     *            creating a new Address object
     */
    protected BusinessRmt2OrmAdapter(Business bus, Address addr) {
        super(addr);
        if (bus == null) {
            bus = new Business();
        }
        this.bus = bus;
        this.setContactType(ContactsConst.CONTACT_TYPE_BUSINESS);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#setBusinessId(int)
     */
    @Override
    public void setContactId(int value) {
        this.bus.setBusinessId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#getBusinessId()
     */
    @Override
    public int getContactId() {
        return this.bus.getBusinessId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#setEntityTypeId(int)
     */
    @Override
    public void setEntityTypeId(int value) {
        this.bus.setEntityTypeId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#getEntityTypeId()
     */
    @Override
    public int getEntityTypeId() {
        return this.bus.getEntityTypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#setServTypeId(int)
     */
    @Override
    public void setServTypeId(int value) {
        this.bus.setServTypeId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#getServTypeId()
     */
    @Override
    public int getServTypeId() {
        return this.bus.getServTypeId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#setCompanyName(java.lang.String)
     */
    @Override
    public void setContactName(String value) {
        this.bus.setLongname(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#getCompanyName()
     */
    @Override
    public String getContactName() {
        return this.bus.getLongname();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#setContactFirstname(java.lang.String)
     */
    @Override
    public void setContactFirstname(String value) {
        this.bus.setContactFirstname(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#getContactFirstname()
     */
    @Override
    public String getContactFirstname() {
        return this.bus.getContactFirstname();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#setContactLastname(java.lang.String)
     */
    @Override
    public void setContactLastname(String value) {
        this.bus.setContactLastname(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#getContactLastname()
     */
    @Override
    public String getContactLastname() {
        return this.bus.getContactLastname();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#setContactPhone(java.lang.String)
     */
    @Override
    public void setContactPhone(String value) {
        this.bus.setContactPhone(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#getContactPhone()
     */
    @Override
    public String getContactPhone() {
        return this.bus.getContactPhone();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#setContactExt(java.lang.String)
     */
    @Override
    public void setContactExt(String value) {
        this.bus.setContactExt(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#getContactExt()
     */
    @Override
    public String getContactExt() {
        return this.bus.getContactExt();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#setContactEmail(java.lang.String)
     */
    @Override
    public void setContactEmail(String value) {
        this.bus.setContactEmail(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#getContactEmail()
     */
    @Override
    public String getContactEmail() {
        return this.bus.getContactEmail();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#setTaxId(java.lang.String)
     */
    @Override
    public void setTaxId(String value) {
        this.bus.setTaxId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#getTaxId()
     */
    @Override
    public String getTaxId() {
        return this.bus.getTaxId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#setWebsite(java.lang.String)
     */
    @Override
    public void setWebsite(String value) {
        this.bus.setWebsite(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#getWebsite()
     */
    @Override
    public String getWebsite() {
        return this.bus.getWebsite();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#setCategoryId(int)
     */
    @Override
    public void setCategoryId(int value) {
        this.bus.setCategoryId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#getCategoryId()
     */
    @Override
    public int getCategoryId() {
        return this.bus.getCategoryId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#setShortName(java.lang.String)
     */
    @Override
    public void setShortName(String value) {
        this.bus.setShortname(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#getShortName()
     */
    @Override
    public String getShortName() {
        return this.bus.getShortname();
    }

}
