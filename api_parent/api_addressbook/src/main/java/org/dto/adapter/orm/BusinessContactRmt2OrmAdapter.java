package org.dto.adapter.orm;

import java.util.ArrayList;
import java.util.List;

import org.dao.contacts.ContactsConst;
import org.dao.mapping.orm.rmt2.Address;
import org.dao.mapping.orm.rmt2.Business;
import org.dao.mapping.orm.rmt2.VwBusinessAddress;
import org.dto.BusinessContactDto;

import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;

/**
 * Adapts an RMT2 ORM <i>Business</i> and an <i>Address</i> object to an
 * <i>BusinessContactDto</i>.
 * 
 * @author rterrell
 * 
 */
class BusinessContactRmt2OrmAdapter extends AddressRmt2OrmAdapter implements BusinessContactDto {

    private Business bus;
    private List<Integer> businessIdList;

    /**
     * Create a BusinessRmt2OrmAdapter using an instance of
     * <i>VwBusinessAddress</i>, which contains both business and address
     * contact information.
     * 
     * @param bus
     *            an instance of {@link VwBusinessAddress} or null for the
     *            purpose of creating a new Business object
     */
    protected BusinessContactRmt2OrmAdapter(VwBusinessAddress bus) {
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
        this.businessIdList = new ArrayList<Integer>();
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
    protected BusinessContactRmt2OrmAdapter(Business bus, Address addr) {
        super(addr);
        if (bus == null) {
            bus = new Business();
        }
        this.bus = bus;
        this.setContactType(ContactsConst.CONTACT_TYPE_BUSINESS);
        this.businessIdList = new ArrayList<Integer>();
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

    @Override
    public void setContactIdList(List<Integer> value) {
        this.businessIdList = value;
    }

    @Override
    public List<Integer> getContactIdList() {
        return this.businessIdList;
    }
    
    @Override
    public int hashCode() {
        return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.getAddr1()),
                HashCodeAssistant.hashObject(this.getAddr2()), HashCodeAssistant.hashObject(this.getAddr1()),
                HashCodeAssistant.hashObject(this.getAddr4()), HashCodeAssistant.hashObject(this.getAddrId()),
                HashCodeAssistant.hashObject(this.getCategoryId()), HashCodeAssistant.hashObject(this.getCity()),
                HashCodeAssistant.hashObject(this.getContactEmail()),
                HashCodeAssistant.hashObject(this.getContactExt()),
                HashCodeAssistant.hashObject(this.getContactFirstname()),
                HashCodeAssistant.hashObject(this.getContactId()),
                HashCodeAssistant.hashObject(this.getContactLastname()),
                HashCodeAssistant.hashObject(this.getContactLastname()),
                HashCodeAssistant.hashObject(this.getContactName()),
                HashCodeAssistant.hashObject(this.getContactPhone()),
                HashCodeAssistant.hashObject(this.getContactType()),
                HashCodeAssistant.hashObject(this.getEntityTypeId()), HashCodeAssistant.hashObject(this.getPhoneCell()),
                HashCodeAssistant.hashObject(this.getPhoneCompany()), HashCodeAssistant.hashObject(this.getPhoneExt()),
                HashCodeAssistant.hashObject(this.getPhoneFax()), HashCodeAssistant.hashObject(this.getPhoneHome()),
                HashCodeAssistant.hashObject(this.getPhonePager()), HashCodeAssistant.hashObject(this.getPhoneWork()),
                HashCodeAssistant.hashObject(this.getServTypeId()), HashCodeAssistant.hashObject(this.getShortName()),
                HashCodeAssistant.hashObject(this.getState()), HashCodeAssistant.hashObject(this.getTaxId()),
                HashCodeAssistant.hashObject(this.getWebsite()), HashCodeAssistant.hashObject(this.getZip()),
                HashCodeAssistant.hashObject(this.getZipext()), HashCodeAssistant.hashObject(this.getUpdateUserId()),
                HashCodeAssistant.hashObject(this.getDateCreated()),
                HashCodeAssistant.hashObject(this.getDateUpdated()));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BusinessContactDto)) {
            return false;
        }
        final BusinessContactDto other = (BusinessContactDto) obj;

        if (EqualityAssistant.notEqual(this.getAddrId(), other.getAddrId())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getAddr1(), other.getAddr1())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getAddr2(), other.getAddr2())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getAddr3(), other.getAddr3())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getAddr4(), other.getAddr4())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getCategoryId(), other.getCategoryId())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getCity(), other.getCity())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getContactEmail(), other.getContactEmail())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getContactExt(), other.getContactExt())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getContactFirstname(), other.getContactFirstname())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getContactId(), other.getContactId())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getContactLastname(), other.getContactLastname())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getContactName(), other.getContactName())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getContactPhone(), other.getContactPhone())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getContactType(), other.getContactType())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getEntityTypeId(), other.getEntityTypeId())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getPhoneCell(), other.getPhoneCell())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getPhoneCompany(), other.getPhoneCompany())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getPhoneExt(), other.getPhoneExt())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getPhoneFax(), other.getPhoneFax())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getPhoneHome(), other.getPhoneHome())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getPhonePager(), other.getPhonePager())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getPhoneWork(), other.getPhoneWork())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getServTypeId(), other.getServTypeId())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getShortName(), other.getShortName())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getState(), other.getState())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getTaxId(), other.getTaxId())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getWebsite(), other.getWebsite())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getZip(), other.getZip())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getZipext(), other.getZipext())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getUpdateUserId(), other.getUpdateUserId())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getDateCreated(), other.getDateCreated())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getDateUpdated(), other.getDateUpdated())) {
            return false;
        }
        return true;
    }

}
