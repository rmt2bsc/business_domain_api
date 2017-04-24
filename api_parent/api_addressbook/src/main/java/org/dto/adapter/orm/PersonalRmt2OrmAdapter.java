package org.dto.adapter.orm;

import java.util.Date;

import org.dao.contacts.ContactsConst;
import org.dao.mapping.orm.rmt2.Address;
import org.dao.mapping.orm.rmt2.Person;
import org.dao.mapping.orm.rmt2.VwPersonAddress;
import org.dto.PersonalContactDto;

/**
 * Adapts an RMT2 ORM <i>Person</i> and an <i>Address</i> object to an
 * <i>PersonalContactDto</i>.
 * 
 * @author Roy Terrell
 * 
 */
class PersonalRmt2OrmAdapter extends AddressRmt2OrmAdapter implements PersonalContactDto {
    private Person per;

    /**
     * Create a PersonalRmt2OrmAdapter using an instance of
     * <i>VwPersonAddress</i>, which contains person and address data pertaining
     * to the contact.
     * 
     * @param per
     *            an instance of {@link VwPersonAddress} or null for the purpose
     *            of creating a new Person object
     */
    protected PersonalRmt2OrmAdapter(VwPersonAddress per) {
        super(per);
        this.per = new Person();
        if (per != null) {
            this.per.setPersonId(per.getPersonId());
            this.per.setFirstname(per.getPerFirstname());
            this.per.setMidname(per.getPerMidname());
            this.per.setLastname(per.getPerLastname());
            this.per.setMaidenname(per.getPerMaidenname());
            this.per.setGeneration(per.getPerGeneration());
            this.per.setShortname(per.getPerShortname());
            this.per.setTitle(per.getPerTitle());
            this.per.setGenderId(per.getPerGenderId());
            this.per.setMaritalStatusId(per.getPerMaritalStatus());
            this.per.setBirthDate(per.getPerBirthDate());
            this.per.setRaceId(per.getPerRaceId());
            this.per.setSsn(per.getPerSsn());
            this.per.setEmail(per.getPerEmail());
            this.per.setUserId("N/A");
        }

        this.dateCreated = this.per.getDateCreated();
        this.dateUpdated = this.per.getDateUpdated();
        this.updateUserId = this.per.getUserId();
        this.setContactType(ContactsConst.CONTACT_TYPE_PERSONAL);
        return;
    }

    /**
     * Create a PersonalRmt2OrmAdapter using an instance of <i>Person</i> and
     * <i>Address</i>.
     * 
     * @param per
     *            an instance of {@link Person} or null for the purpose of
     *            creating a new Person object
     * @param addr
     *            an instance of {@link Address} or null for the purpose of
     *            creating a new Address object
     */
    protected PersonalRmt2OrmAdapter(Person per, Address addr) {
        super(addr);
        if (per == null) {
            per = new Person();
        }
        this.per = per;
        this.setContactType(ContactsConst.CONTACT_TYPE_PERSONAL);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#setPersonId(int)
     */
    @Override
    public void setContactId(int value) {
        this.per.setPersonId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#getPersonId()
     */
    @Override
    public int getContactId() {
        return this.per.getPersonId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#setFirstname(java.lang.String)
     */
    @Override
    public void setFirstname(String value) {
        this.per.setFirstname(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#getFirstname()
     */
    @Override
    public String getFirstname() {
        return this.per.getFirstname();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#setMidname(java.lang.String)
     */
    @Override
    public void setMidname(String value) {
        this.per.setMidname(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#getMidname()
     */
    @Override
    public String getMidname() {
        return this.per.getMidname();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#setLastname(java.lang.String)
     */
    @Override
    public void setLastname(String value) {
        this.per.setLastname(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#getLastname()
     */
    @Override
    public String getLastname() {
        return this.per.getLastname();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#setMaidenname(java.lang.String)
     */
    @Override
    public void setMaidenname(String value) {
        this.per.setMaidenname(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#getMaidenname()
     */
    @Override
    public String getMaidenname() {
        return this.per.getMaidenname();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#setGeneration(java.lang.String)
     */
    @Override
    public void setGeneration(String value) {
        this.per.setGeneration(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#getGeneration()
     */
    @Override
    public String getGeneration() {
        return this.per.getGeneration();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#setShortname(java.lang.String)
     */
    @Override
    public void setContactName(String value) {
        this.per.setShortname(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#getShortname()
     */
    @Override
    public String getContactName() {
        return this.per.getShortname();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#setTitle(int)
     */
    @Override
    public void setTitle(int value) {
        this.per.setTitle(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#getTitle()
     */
    @Override
    public int getTitle() {
        return this.per.getTitle();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#setGenderId(int)
     */
    @Override
    public void setGenderId(int value) {
        this.per.setGenderId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#getGenderId()
     */
    @Override
    public int getGenderId() {
        return this.per.getGenderId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#setMaritalStatusId(int)
     */
    @Override
    public void setMaritalStatusId(int value) {
        this.per.setMaritalStatusId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#getMaritalStatusId()
     */
    @Override
    public int getMaritalStatusId() {
        return this.per.getMaritalStatusId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#setBirthDate(java.util.Date)
     */
    @Override
    public void setBirthDate(Date value) {
        this.per.setBirthDate(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#getBirthDate()
     */
    @Override
    public Date getBirthDate() {
        return this.per.getBirthDate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#setRaceId(int)
     */
    @Override
    public void setRaceId(int value) {
        this.per.setRaceId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#getRaceId()
     */
    @Override
    public int getRaceId() {
        return this.per.getRaceId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#setSsn(java.lang.String)
     */
    @Override
    public void setSsn(String value) {
        this.per.setSsn(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#getSsn()
     */
    @Override
    public String getSsn() {
        return this.per.getSsn();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#setEmail(java.lang.String)
     */
    @Override
    public void setContactEmail(String value) {
        this.per.setEmail(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#getEmail()
     */
    @Override
    public String getContactEmail() {
        return this.per.getEmail();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#setCategoryId(int)
     */
    @Override
    public void setCategoryId(int value) {
        this.per.setCategoryId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.PersonalContactDto#getCategoryId()
     */
    @Override
    public int getCategoryId() {
        return this.per.getCategoryId();
    }

}
