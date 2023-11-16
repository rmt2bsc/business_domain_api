package org.dto.adapter.orm;

import java.util.ArrayList;
import java.util.List;

import org.dao.contacts.ContactsConst;
import org.dao.mapping.orm.rmt2.Address;
import org.dao.mapping.orm.rmt2.Business;
import org.dao.mapping.orm.rmt2.VwBusinessAddress;
import org.dto.BusinessContactDto;

import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;

/**
 * Adapts an RMT2 ORM <i>Business</i> and an <i>Address</i> object to an
 * <i>BusinessContactDto</i>.
 * 
 * @author rterrell
 * 
 */
class BusinessContactRmt2OrmAdapter extends AddressRmt2OrmAdapter implements BusinessContactDto {

    private Business bus;
    private VwBusinessAddress busExt;
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
        this.busExt = new VwBusinessAddress();
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
            
            // Get extended data
            this.busExt.setBusServTypeGrpId(bus.getBusServTypeGrpId());
            this.busExt.setBusServTypeLongdesc(bus.getBusServTypeLongdesc());
            this.busExt.setBusServTypeShortdesc(bus.getBusServTypeShortdesc());
            this.busExt.setBusEntityTypeGrpId(bus.getBusEntityTypeGrpId());
            this.busExt.setBusEntityTypeLongdesc(bus.getBusEntityTypeLongdesc());
            this.busExt.setBusEntityTypeShortdesc(bus.getBusEntityTypeShortdesc());
            this.busExt.setAddrId(bus.getAddrId());
            this.busExt.setAddr1(bus.getAddr1());
            this.busExt.setAddr2(bus.getAddr2());
            this.busExt.setAddr3(bus.getAddr3());
            this.busExt.setAddr4(bus.getAddr4());
            this.busExt.setZipCity(bus.getZipCity());
            this.busExt.setZipState(bus.getZipState());
            this.busExt.setAddrZip(bus.getAddrZip());
            this.busExt.setAddrZipext(bus.getAddrZipext());
            this.busExt.setAddrPhoneCell(bus.getAddrPhoneCell());
            this.busExt.setAddrPhoneExt(bus.getAddrPhoneExt());
            this.busExt.setAddrPhoneFax(bus.getAddrPhoneFax());
            this.busExt.setAddrPhoneHome(bus.getAddrPhoneHome());
            this.busExt.setAddrPhoneMain(bus.getAddrPhoneMain());
            this.busExt.setAddrPhonePager(bus.getAddrPhonePager());
            this.busExt.setAddrPhoneWork(bus.getAddrPhoneWork());
            
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

        // Get extended data
        this.busExt = new VwBusinessAddress();
        if (bus != null) {
            this.busExt.setBusinessId(bus.getBusinessId());
            this.busExt.setBusEntityTypeId(bus.getEntityTypeId());
            this.busExt.setBusServTypeId(bus.getServTypeId());
            this.busExt.setBusLongname(bus.getLongname());
            this.busExt.setBusShortname(bus.getShortname());
            this.busExt.setContactEmail(bus.getContactEmail());
            this.busExt.setBusContactExt(bus.getContactExt());
            this.busExt.setBusContactFirstname(bus.getContactFirstname());
            this.busExt.setBusContactLastname(bus.getContactLastname());
            this.busExt.setBusContactPhone(bus.getContactPhone());
            this.busExt.setBusTaxId(bus.getTaxId());
            this.busExt.setBusWebsite(bus.getWebsite());
        }
        if (addr != null) {
            this.busExt.setAddrId(addr.getAddrId());
            this.busExt.setAddr1(addr.getAddr1());
            this.busExt.setAddr2(addr.getAddr2());
            this.busExt.setAddr3(addr.getAddr3());
            this.busExt.setAddr4(addr.getAddr4());
            this.busExt.setAddrZip(addr.getZip());
            this.busExt.setAddrZipext(addr.getZipext());
            this.busExt.setAddrPhoneCell(addr.getPhoneCell());
            this.busExt.setAddrPhoneExt(addr.getPhoneExt());
            this.busExt.setAddrPhoneFax(addr.getPhoneFax());
            this.busExt.setAddrPhoneHome(addr.getPhoneHome());
            this.busExt.setAddrPhoneMain(addr.getPhoneMain());
            this.busExt.setAddrPhonePager(addr.getPhonePager());
            this.busExt.setAddrPhoneWork(addr.getPhoneWork());
        }
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
                HashCodeAssistant.hashObject(this.getEntityTypeId()),
                HashCodeAssistant.hashObject(this.getEntityTypeGrpId()),
                HashCodeAssistant.hashObject(this.getEntityTypeLongtdesc()),
                HashCodeAssistant.hashObject(this.getEntityTypeShortdesc()),
                HashCodeAssistant.hashObject(this.getPhoneCell()),
                HashCodeAssistant.hashObject(this.getPhoneCompany()), HashCodeAssistant.hashObject(this.getPhoneExt()),
                HashCodeAssistant.hashObject(this.getPhoneFax()), HashCodeAssistant.hashObject(this.getPhoneHome()),
                HashCodeAssistant.hashObject(this.getPhonePager()), HashCodeAssistant.hashObject(this.getPhoneWork()),
                HashCodeAssistant.hashObject(this.getServTypeId()),
                HashCodeAssistant.hashObject(this.getServTypeGrpId()),
                HashCodeAssistant.hashObject(this.getServTypeLongtdesc()),
                HashCodeAssistant.hashObject(this.getServTypeShortdesc()),
                HashCodeAssistant.hashObject(this.getShortName()),
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
        if (EqualityAssistant.notEqual(this.getEntityTypeGrpId(), other.getEntityTypeGrpId())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getEntityTypeLongtdesc(), other.getEntityTypeLongtdesc())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getEntityTypeShortdesc(), other.getEntityTypeShortdesc())) {
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
        if (EqualityAssistant.notEqual(this.getServTypeGrpId(), other.getServTypeGrpId())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getServTypeLongtdesc(), other.getServTypeLongtdesc())) {
            return false;
        }
        if (EqualityAssistant.notEqual(this.getServTypeShortdesc(), other.getServTypeShortdesc())) {
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

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#setEntityTypeGrpId(int)
     */
    @Override
    public void setEntityTypeGrpId(int value) {
        this.busExt.setBusEntityTypeGrpId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#getEntityTypeGrpId()
     */
    @Override
    public int getEntityTypeGrpId() {
        return this.busExt.getBusEntityTypeGrpId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#setEntityTypeShortdesc(java.lang.String)
     */
    @Override
    public void setEntityTypeShortdesc(String value) {
        this.busExt.setBusEntityTypeShortdesc(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#getEntityTypeShortdesc()
     */
    @Override
    public String getEntityTypeShortdesc() {
        return this.busExt.getBusEntityTypeShortdesc();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#setEntityTypeLongdesc(java.lang.String)
     */
    @Override
    public void setEntityTypeLongdesc(String value) {
        this.busExt.setBusEntityTypeLongdesc(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#getEntityTypeLongtdesc()
     */
    @Override
    public String getEntityTypeLongtdesc() {
        return this.busExt.getBusEntityTypeLongdesc();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#setServTypeGrpId(int)
     */
    @Override
    public void setServTypeGrpId(int value) {
        this.busExt.setBusServTypeGrpId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#getServTypeGrpId()
     */
    @Override
    public int getServTypeGrpId() {
        return this.busExt.getBusServTypeGrpId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#setServTypeShortdesc(java.lang.String)
     */
    @Override
    public void setServTypeShortdesc(String value) {
        this.busExt.setBusServTypeShortdesc(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#getServTypeShortdesc()
     */
    @Override
    public String getServTypeShortdesc() {
        return this.busExt.getBusServTypeShortdesc();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#setServTypeLongdesc(java.lang.String)
     */
    @Override
    public void setServTypeLongdesc(String value) {
        this.busExt.setBusServTypeLongdesc(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.BusinessContactDto#getServTypeLongtdesc()
     */
    @Override
    public String getServTypeLongtdesc() {
        return this.busExt.getBusServTypeLongdesc();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.orm.AddressRmt2OrmAdapter#setAddrId(int)
     */
    @Override
    public void setAddrId(int value) {
        this.busExt.setAddrId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.orm.AddressRmt2OrmAdapter#getAddr1()
     */
    @Override
    public int getAddrId() {
        return this.busExt.getAddrId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.orm.AddressRmt2OrmAdapter#setAddr2(java.lang.String)
     */
    @Override
    public void setAddr1(String value) {
        this.busExt.setAddr1(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.orm.AddressRmt2OrmAdapter#getAddr1()
     */
    @Override
    public String getAddr1() {
        return this.busExt.getAddr1();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.orm.AddressRmt2OrmAdapter#setAddr2(java.lang.String)
     */
    @Override
    public void setAddr2(String value) {
        this.busExt.setAddr2(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.orm.AddressRmt2OrmAdapter#getAddr2()
     */
    @Override
    public String getAddr2() {
        return this.busExt.getAddr2();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.orm.AddressRmt2OrmAdapter#setAddr3(java.lang.String)
     */
    @Override
    public void setAddr3(String value) {
        this.busExt.setAddr3(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.orm.AddressRmt2OrmAdapter#getAddr3()
     */
    @Override
    public String getAddr3() {
        return this.busExt.getAddr3();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.orm.AddressRmt2OrmAdapter#setAddr4(java.lang.String)
     */
    @Override
    public void setAddr4(String value) {
        this.busExt.setAddr4(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.orm.AddressRmt2OrmAdapter#getAddr4()
     */
    @Override
    public String getAddr4() {
        return this.busExt.getAddr4();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.orm.AddressRmt2OrmAdapter#setZip(int)
     */
    @Override
    public void setZip(int value) {
        this.busExt.setAddrZip(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.orm.AddressRmt2OrmAdapter#getZip()
     */
    @Override
    public int getZip() {
        return this.busExt.getAddrZip();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.orm.AddressRmt2OrmAdapter#setZipext(int)
     */
    @Override
    public void setZipext(int value) {
        this.busExt.setAddrZipext(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.orm.AddressRmt2OrmAdapter#getZipext()
     */
    @Override
    public int getZipext() {
        return this.busExt.getAddrZipext();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.adapter.orm.AddressRmt2OrmAdapter#setPhoneHome(java.lang.String)
     */
    @Override
    public void setPhoneHome(String value) {
        this.busExt.setAddrPhoneHome(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.orm.AddressRmt2OrmAdapter#getPhoneHome()
     */
    @Override
    public String getPhoneHome() {
        return this.busExt.getAddrPhoneHome();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.adapter.orm.AddressRmt2OrmAdapter#setPhoneWork(java.lang.String)
     */
    @Override
    public void setPhoneWork(String value) {
        this.busExt.setAddrPhoneWork(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.orm.AddressRmt2OrmAdapter#getPhoneWork()
     */
    @Override
    public String getPhoneWork() {
        return this.busExt.getAddrPhoneWork();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.adapter.orm.AddressRmt2OrmAdapter#setPhoneExt(java.lang.String)
     */
    @Override
    public void setPhoneExt(String value) {
        this.busExt.setAddrPhoneExt(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.orm.AddressRmt2OrmAdapter#getPhoneExt()
     */
    @Override
    public String getPhoneExt() {
        return this.busExt.getAddrPhoneExt();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.adapter.orm.AddressRmt2OrmAdapter#setPhoneCompany(java.lang.String
     * )
     */
    @Override
    public void setPhoneCompany(String value) {
        // TODO Auto-generated method stub
        super.setPhoneCompany(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.orm.AddressRmt2OrmAdapter#getPhoneCompany()
     */
    @Override
    public String getPhoneCompany() {
        // TODO Auto-generated method stub
        return super.getPhoneCompany();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.adapter.orm.AddressRmt2OrmAdapter#setPhoneCell(java.lang.String)
     */
    @Override
    public void setPhoneCell(String value) {
        this.busExt.setAddrPhoneCell(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.orm.AddressRmt2OrmAdapter#getPhoneCell()
     */
    @Override
    public String getPhoneCell() {
        return this.busExt.getAddrPhoneCell();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.adapter.orm.AddressRmt2OrmAdapter#setPhoneFax(java.lang.String)
     */
    @Override
    public void setPhoneFax(String value) {
        this.busExt.setAddrPhoneFax(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.orm.AddressRmt2OrmAdapter#getPhoneFax()
     */
    @Override
    public String getPhoneFax() {
        return this.busExt.getAddrPhoneFax();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.adapter.orm.AddressRmt2OrmAdapter#setPhonePager(java.lang.String)
     */
    @Override
    public void setPhonePager(String value) {
        this.busExt.setAddrPhonePager(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.orm.AddressRmt2OrmAdapter#getPhonePager()
     */
    @Override
    public String getPhonePager() {
        return this.busExt.getAddrPhonePager();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.orm.AddressRmt2OrmAdapter#setCity(java.lang.String)
     */
    @Override
    public void setCity(String value) {
        this.busExt.setZipCity(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.orm.AddressRmt2OrmAdapter#getCity()
     */
    @Override
    public String getCity() {
        return this.busExt.getZipCity();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.orm.AddressRmt2OrmAdapter#setState(java.lang.String)
     */
    @Override
    public void setState(String value) {
        this.busExt.setZipState(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.adapter.orm.AddressRmt2OrmAdapter#getState()
     */
    @Override
    public String getState() {
        return this.busExt.getZipState();
    }

}
