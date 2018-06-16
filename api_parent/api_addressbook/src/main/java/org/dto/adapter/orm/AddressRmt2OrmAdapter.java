package org.dto.adapter.orm;

import org.dao.contacts.ContactsConst;
import org.dao.mapping.orm.rmt2.Address;
import org.dao.mapping.orm.rmt2.VwBusinessAddress;
import org.dao.mapping.orm.rmt2.VwCommonContact;
import org.dao.mapping.orm.rmt2.VwPersonAddress;
import org.dto.AddressDto;

import com.api.foundation.TransactionDtoImpl;

/**
 * Adapts an RMT2 ORM <i>Address</i> object to an <i>AddressDto</i>.
 * 
 * @author rterrell
 * 
 */
public class AddressRmt2OrmAdapter extends TransactionDtoImpl implements AddressDto {

    protected Address src;

    private String contactType;

    private String city;

    private String state;

    /**
     * Default Constructor
     */
    protected AddressRmt2OrmAdapter() {
        return;
    }

    /**
     * Create a AddressRMT2OrmAdapter using an instance of
     * <i>VwCommonContact</i>.
     * 
     * @param contact
     *            an instance of {@link VwCommonContact} containing the address
     *            data of the contact or null for the purpose of creating a new
     *            Address object
     */
    protected AddressRmt2OrmAdapter(VwCommonContact contact) {
        super();
        this.src = new Address();
        if (contact != null) {
            this.src.setAddrId(contact.getAddrId());
            this.contactType = contact.getContactType();
            if (contact.getContactType() != null) {
                if (contact.getContactType().equalsIgnoreCase(ContactsConst.CONTACT_TYPE_PERSONAL)) {
                    this.src.setPersonId(contact.getContactId());
                }
                if (contact.getContactType().equalsIgnoreCase(ContactsConst.CONTACT_TYPE_BUSINESS)) {
                    this.src.setBusinessId(contact.getContactId());
                }
            }
            this.src.setAddr1(contact.getAddr1());
            this.src.setAddr2(contact.getAddr2());
            this.src.setAddr3(contact.getAddr3());
            this.src.setAddr4(contact.getAddr4());
            this.city = contact.getZipCity();
            this.state = contact.getZipState();
            this.src.setZip(contact.getAddrZip());
            this.src.setZipext(contact.getAddrZipext());
            this.src.setPhoneCell(contact.getAddrPhoneCell());
            this.src.setPhoneExt(contact.getAddrPhoneExt());
            this.src.setPhoneFax(contact.getAddrPhoneFax());
            this.src.setPhoneHome(contact.getAddrPhoneHome());
            this.src.setPhoneMain(contact.getAddrPhoneMain());
            this.src.setPhonePager(contact.getAddrPhonePager());
            this.src.setPhoneWork(contact.getAddrPhoneWork());

            this.src.setUserId("N/A");
        }

        this.dateCreated = this.src.getDateCreated();
        this.dateUpdated = this.src.getDateUpdated();
        this.updateUserId = this.src.getUserId();
        return;
    }

    /**
     * Create a AddressRMT2OrmAdapter using an instance of
     * <i>VwBusinessAddress</i>.
     * 
     * @param contact
     *            an instance of {@link VwBusinessAddress} containing the
     *            address data of the contact or null for the purpose of
     *            creating a new Address object
     */
    protected AddressRmt2OrmAdapter(VwPersonAddress contact) {
        super();
        this.src = new Address();
        if (contact != null) {
            this.src.setAddrId(contact.getAddrId());
            this.contactType = ContactsConst.CONTACT_TYPE_PERSONAL;
            this.src.setBusinessId(contact.getPersonId());
            this.src.setAddr1(contact.getAddr1());
            this.src.setAddr2(contact.getAddr2());
            this.src.setAddr3(contact.getAddr3());
            this.src.setAddr4(contact.getAddr4());
            this.city = contact.getZipCity();
            this.state = contact.getZipState();
            this.src.setZip(contact.getAddrZip());
            this.src.setZipext(contact.getAddrZipext());
            this.src.setPhoneCell(contact.getAddrPhoneCell());
            this.src.setPhoneExt(contact.getAddrPhoneExt());
            this.src.setPhoneFax(contact.getAddrPhoneFax());
            this.src.setPhoneHome(contact.getAddrPhoneHome());
            this.src.setPhoneMain(contact.getAddrPhoneMain());
            this.src.setPhonePager(contact.getAddrPhonePager());
            this.src.setPhoneWork(contact.getAddrPhoneWork());

            this.src.setUserId("N/A");
        }

        this.dateCreated = this.src.getDateCreated();
        this.dateUpdated = this.src.getDateUpdated();
        this.updateUserId = this.src.getUserId();
        return;
    }

    /**
     * Create a AddressRMT2OrmAdapter using an instance of
     * <i>VwBusinessAddress</i>.
     * 
     * @param contact
     *            an instance of {@link VwBusinessAddress} containing the
     *            address data of the contact or null for the purpose of
     *            creating a new Address object
     */
    protected AddressRmt2OrmAdapter(VwBusinessAddress contact) {
        super();
        this.src = new Address();
        if (contact != null) {
            this.src.setAddrId(contact.getAddrId());
            this.contactType = ContactsConst.CONTACT_TYPE_BUSINESS;
            this.src.setBusinessId(contact.getBusinessId());
            this.src.setAddr1(contact.getAddr1());
            this.src.setAddr2(contact.getAddr2());
            this.src.setAddr3(contact.getAddr3());
            this.src.setAddr4(contact.getAddr4());
            this.city = contact.getZipCity();
            this.state = contact.getZipState();
            this.src.setZip(contact.getAddrZip());
            this.src.setZipext(contact.getAddrZipext());
            this.src.setPhoneCell(contact.getAddrPhoneCell());
            this.src.setPhoneExt(contact.getAddrPhoneExt());
            this.src.setPhoneFax(contact.getAddrPhoneFax());
            this.src.setPhoneHome(contact.getAddrPhoneHome());
            this.src.setPhoneMain(contact.getAddrPhoneMain());
            this.src.setPhonePager(contact.getAddrPhonePager());
            this.src.setPhoneWork(contact.getAddrPhoneWork());

            this.src.setUserId("N/A");
        }

        this.dateCreated = this.src.getDateCreated();
        this.dateUpdated = this.src.getDateUpdated();
        this.updateUserId = this.src.getUserId();
        return;
    }

    /**
     * Create a AddressRMT2OrmAdapter using an instance of <i>Address</i>.
     * 
     * @param addr
     *            an instance of {@link Address} or null for the purpose of
     *            creating a new Address object
     */
    protected AddressRmt2OrmAdapter(Address addr) {
        super();
        if (addr == null) {
            addr = new Address();
        }
        this.src = addr;
        this.dateCreated = addr.getDateCreated();
        this.dateUpdated = addr.getDateUpdated();
        this.updateUserId = addr.getUserId();
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#setAddrId(int)
     */
    @Override
    public void setAddrId(int value) {
        this.src.setAddrId(value);

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#getAddrId()
     */
    @Override
    public int getAddrId() {
        return this.src.getAddrId();
    }

    /**
     * Sets the contact's type.
     * 
     * @param value
     *            equals either <i>per</i> or <i>bus</i> for Personal or
     *            Business, respectively.
     */
    public void setContactType(String value) {
        this.contactType = value;
    }

    /**
     * Returns the contact's type.
     * 
     * @return equals either <i>per</i> or <i>bus</i> for Personal or Business,
     *         respectively.
     */
    public String getContactType() {
        return this.contactType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#setAddr1(java.lang.String)
     */
    @Override
    public void setAddr1(String value) {
        this.src.setAddr1(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#getAddr1()
     */
    @Override
    public String getAddr1() {
        return this.src.getAddr1();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#setAddr2(java.lang.String)
     */
    @Override
    public void setAddr2(String value) {
        this.src.setAddr2(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#getAddr2()
     */
    @Override
    public String getAddr2() {
        return this.src.getAddr2();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#setAddr3(java.lang.String)
     */
    @Override
    public void setAddr3(String value) {
        this.src.setAddr3(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#getAddr3()
     */
    @Override
    public String getAddr3() {
        return this.src.getAddr3();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#setAddr4(java.lang.String)
     */
    @Override
    public void setAddr4(String value) {
        this.src.setAddr4(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#getAddr4()
     */
    @Override
    public String getAddr4() {
        return this.src.getAddr4();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#setZip(int)
     */
    @Override
    public void setZip(int value) {
        this.src.setZip(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#getZip()
     */
    @Override
    public int getZip() {
        return this.src.getZip();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#setZipext(int)
     */
    @Override
    public void setZipext(int value) {
        this.src.setZipext(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#getZipext()
     */
    @Override
    public int getZipext() {
        return this.src.getZipext();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#setPhoneHome(java.lang.String)
     */
    @Override
    public void setPhoneHome(String value) {
        this.src.setPhoneHome(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#getPhoneHome()
     */
    @Override
    public String getPhoneHome() {
        return this.src.getPhoneHome();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#setPhoneWork(java.lang.String)
     */
    @Override
    public void setPhoneWork(String value) {
        this.src.setPhoneWork(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#getPhoneWork()
     */
    @Override
    public String getPhoneWork() {
        return this.src.getPhoneWork();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#setPhoneExt(java.lang.String)
     */
    @Override
    public void setPhoneExt(String value) {
        this.src.setPhoneExt(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#getPhoneExt()
     */
    @Override
    public String getPhoneExt() {
        return this.src.getPhoneExt();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#setPhoneCompany(java.lang.String)
     */
    @Override
    public void setPhoneCompany(String value) {
        this.src.setPhoneMain(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#getPhoneCompany()
     */
    @Override
    public String getPhoneCompany() {
        return this.src.getPhoneMain();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#setPhoneCell(java.lang.String)
     */
    @Override
    public void setPhoneCell(String value) {
        this.src.setPhoneCell(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#getPhoneCell()
     */
    @Override
    public String getPhoneCell() {
        return this.src.getPhoneCell();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#setPhoneFax(java.lang.String)
     */
    @Override
    public void setPhoneFax(String value) {
        this.src.setPhoneFax(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#getPhoneFax()
     */
    @Override
    public String getPhoneFax() {
        return this.src.getPhoneFax();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#setPhonePager(java.lang.String)
     */
    @Override
    public void setPhonePager(String value) {
        this.src.setPhonePager(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#getPhonePager()
     */
    @Override
    public String getPhonePager() {
        return this.src.getPhonePager();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#setCity(java.lang.String)
     */
    @Override
    public void setCity(String value) {
        this.city = value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#getCity()
     */
    @Override
    public String getCity() {
        return this.city;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#setState(java.lang.String)
     */
    @Override
    public void setState(String value) {
        this.state = value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.AddressDto#getState()
     */
    @Override
    public String getState() {
        return this.state;
    }

}
