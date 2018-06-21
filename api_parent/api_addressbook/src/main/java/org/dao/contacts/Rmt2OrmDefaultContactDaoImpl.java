package org.dao.contacts;

import java.util.ArrayList;
import java.util.List;

import org.dao.AddressBookDaoImpl;
import org.dao.mapping.orm.rmt2.Address;
import org.dao.mapping.orm.rmt2.Business;
import org.dao.mapping.orm.rmt2.Person;
import org.dao.mapping.orm.rmt2.VwBusinessAddress;
import org.dao.mapping.orm.rmt2.VwCommonContact;
import org.dao.mapping.orm.rmt2.VwPersonAddress;
import org.dto.BusinessContactDto;
import org.dto.ContactDto;
import org.dto.PersonalContactDto;
import org.dto.adapter.orm.Rmt2AddressBookDtoFactory;

import com.NotFoundException;
import com.api.persistence.DatabaseException;
import com.api.util.RMT2Date;
import com.api.util.UserTimestamp;

/**
 * An RMT2 ORM implementation of the {@link ContactsDao} interface which
 * accesses the contacts relational database to query, create, modify, and
 * delete contact entities.
 * <p>
 * This implementation is capable of individually managing the different contact
 * types (personal or business) or combining the types into one common form.
 * 
 * @author Roy Terrell
 * 
 */
class Rmt2OrmDefaultContactDaoImpl extends AddressBookDaoImpl implements ContactsDao {

    /**
     * Default constructor
     */
    protected Rmt2OrmDefaultContactDaoImpl() {
        super();
    }

    /**
     * constructor
     * 
     * @param appName
     *            application name
     */
    protected Rmt2OrmDefaultContactDaoImpl(String appName) {
        super(appName);
    }

    /**
     * Retrieves a list of all common contacts from the <i>vw_common_contact</i>
     * database view.
     * <p>
     * The list will contain a mixture of both personal and business types.
     * Moreover, when the results are received, evaluate the property,
     * contact_type, of each element to determine the type of contact being
     * handled.
     * 
     * @return a List of {@link ContactDto} objects or null if no data is found.
     * @throws ContactDaoException
     *             database acceess error.
     */
    @Override
    public List<ContactDto> fetchContact() throws ContactDaoException {
        VwCommonContact criteria = new VwCommonContact();
        List<VwCommonContact> results = null;
        try {
            results = this.client.retrieveList(criteria);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new ContactDaoException(e);
        }

        List<ContactDto> list = new ArrayList<ContactDto>();
        for (VwCommonContact item : results) {
            ContactDto dto = Rmt2AddressBookDtoFactory.getContactInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Retireves a single common contact based on its primary key from the
     * <i>vw_common_contact</i> database view.
     * <p>
     * The contact contact could be of personal or business type. When the
     * contact is received, evaluate the property, contact_type, of the results
     * in order to verify the type of contact being handled.
     * 
     * @param contactId
     *            the primary key of the contact
     * @return an instance of {@link ContactDto}
     * @throws ContactDaoException
     */
    @Override
    public ContactDto fetchContact(int contactId) throws ContactDaoException {
        VwCommonContact criteria = new VwCommonContact();
        criteria.addCriteria(VwCommonContact.PROP_CONTACTID, contactId);
        VwCommonContact results = null;
        try {
            results = (VwCommonContact) this.client.retrieveObject(criteria);
            if (results == null) {
                return null;
            }
            ContactDto dto = Rmt2AddressBookDtoFactory.getContactInstance(results);
            return dto;
        } catch (DatabaseException e) {
            throw new ContactDaoException(e);
        }
    }

    /**
     * Rettieves a list of contacts based on the selection criteria contained in
     * <i>criteria</i>.
     * <p>
     * The results can be of varying runtime types, which the actual type
     * returned is predicated upon the runtime type of <i>criteria</i>. For
     * instance, when the runtime type of <i>criteria</i> is ContactDto, the
     * selection criteria is applied to a query targeting the vw_common_contact
     * view, and the return type is a List of ContactDtocommon contact senario.
     * When the runtime type of <i>criteria</i> is PersonalContactDto, the
     * selection criteria is applied to a query targeting the vw_person_address
     * view, and the actual return type is a List of PersonalContactDto. Lastly,
     * when the runtime type of <i>criteria</i> is BusinessContactDto, the
     * selection criteria is applied to a query targeting the
     * vw_business_address view, and the actual return type is a List of
     * BusinessContactDto.
     * 
     * @param criteria
     *            an instance of {@link ContactDto} or one of its descendents.
     *            Cannot be null.
     * @return a List of {@link ContactDto} objects or null if no data is found.
     * @throws ContactDaoException
     *             <i>criteria</i> is null or database access error
     */
    @Override
    public List<ContactDto> fetchContact(ContactDto criteria) throws ContactDaoException {
        if (criteria == null) {
            throw new ContactDaoException(
                    "Selection criteria is required when searching for contact using DTO as input");
        }
        if (criteria instanceof PersonalContactDto) {
            return this.getPersonalContact((PersonalContactDto) criteria);
        }
        if (criteria instanceof BusinessContactDto) {
            return this.getBusinessContact((BusinessContactDto) criteria);
        }
        if (criteria instanceof ContactDto) {
            return this.getCommonContact(criteria);
        }
        return null;
    }

    private List<ContactDto> getCommonContact(ContactDto criteriaDto) throws ContactDaoException {
        VwCommonContact criteria = new VwCommonContact();
        if (criteriaDto.getContactIdList() != null && criteriaDto.getContactIdList().size() > 0) {
            Integer[] intArray = criteriaDto.getContactIdList().toArray(new Integer[criteriaDto.getContactIdList().size()]);
        }
        if (criteriaDto.getContactId() > 0) {
            criteria.addCriteria(VwCommonContact.PROP_CONTACTID, criteriaDto.getContactId());
        }
        if (criteriaDto.getContactName() != null) {
            criteria.addLikeClause(VwCommonContact.PROP_CONTACTNAME, criteriaDto.getContactName());
        }
        if (criteriaDto.getContactType() != null) {
            criteria.addCriteria(VwCommonContact.PROP_CONTACTTYPE, criteriaDto.getContactType());
        }
        if (criteriaDto.getPhoneCell() != null) {
            criteria.addCriteria(VwCommonContact.PROP_ADDRPHONECELL, criteriaDto.getPhoneCell());
        }
        if (criteriaDto.getPhoneFax() != null) {
            criteria.addCriteria(VwCommonContact.PROP_ADDRPHONEFAX, criteriaDto.getPhoneFax());
        }
        if (criteriaDto.getPhoneHome() != null) {
            criteria.addCriteria(VwCommonContact.PROP_ADDRPHONEHOME, criteriaDto.getPhoneHome());
        }
        if (criteriaDto.getPhoneCompany() != null) {
            criteria.addCriteria(VwCommonContact.PROP_ADDRPHONEMAIN, criteriaDto.getPhoneCompany());
        }
        if (criteriaDto.getPhoneWork() != null) {
            criteria.addCriteria(VwCommonContact.PROP_ADDRPHONEWORK, criteriaDto.getPhoneWork());
        }
        if (criteriaDto.getZip() > 0) {
            criteria.addCriteria(VwCommonContact.PROP_ADDRZIP, criteriaDto.getZip());
        }
        if (criteriaDto.getCity() != null) {
            criteria.addLikeClause(VwCommonContact.PROP_ZIPCITY, criteriaDto.getCity());
        }
        if (criteriaDto.getState() != null) {
            criteria.addLikeClause(VwCommonContact.PROP_ZIPSTATE, criteriaDto.getState());
        }

        // Set ordering
        criteria.addOrderBy(VwCommonContact.PROP_CONTACTNAME, VwCommonContact.ORDERBY_ASCENDING);

        // Perform query
        List<VwCommonContact> results = null;
        try {
            results = this.client.retrieveList(criteria);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new ContactDaoException(e);
        }
        List<ContactDto> list = new ArrayList<ContactDto>();
        for (VwCommonContact item : results) {
            ContactDto dto = Rmt2AddressBookDtoFactory.getContactInstance(item);
            list.add(dto);
        }
        return list;
    }

    private List<ContactDto> getPersonalContact(PersonalContactDto criteriaDto)
            throws PersonalContactQueryDaoException {
        VwPersonAddress criteria = new VwPersonAddress();
        if (criteriaDto.getContactIdList() != null && criteriaDto.getContactIdList().size() > 0) {
            Integer[] intArray = criteriaDto.getContactIdList().toArray(new Integer[criteriaDto.getContactIdList().size()]);
            criteria.addInClause(VwPersonAddress.PROP_PERSONID, intArray);
        }
        if (criteriaDto.getContactId() > 0) {
            criteria.addCriteria(VwPersonAddress.PROP_PERSONID, criteriaDto.getContactId());
        }
        if (criteriaDto.getFirstname() != null) {
            criteria.addLikeClause(VwPersonAddress.PROP_PERFIRSTNAME, criteriaDto.getFirstname());
        }
        if (criteriaDto.getLastname() != null) {
            criteria.addLikeClause(VwPersonAddress.PROP_PERLASTNAME, criteriaDto.getLastname());
        }
        if (criteriaDto.getContactName() != null) {
            criteria.addLikeClause(VwPersonAddress.PROP_PERSHORTNAME, criteriaDto.getContactName());
        }
        if (criteriaDto.getMaidenname() != null) {
            criteria.addLikeClause(VwPersonAddress.PROP_PERMAIDENNAME, criteriaDto.getMaidenname());
        }
        if (criteriaDto.getSsn() != null) {
            criteria.addCriteria(VwPersonAddress.PROP_PERSSN, criteriaDto.getSsn());
        }
        if (criteriaDto.getContactEmail() != null) {
            criteria.addLikeClause(VwPersonAddress.PROP_PEREMAIL, criteriaDto.getContactEmail());
        }
        if (criteriaDto.getPhoneCell() != null) {
            criteria.addCriteria(VwPersonAddress.PROP_ADDRPHONECELL, criteriaDto.getPhoneCell());
        }
        if (criteriaDto.getPhoneHome() != null) {
            criteria.addCriteria(VwPersonAddress.PROP_ADDRPHONEHOME, criteriaDto.getPhoneHome());
        }
        if (criteriaDto.getPhoneWork() != null) {
            criteria.addCriteria(VwPersonAddress.PROP_ADDRPHONEWORK, criteriaDto.getPhoneWork());
        }
        if (criteriaDto.getZip() > 0) {
            criteria.addCriteria(VwPersonAddress.PROP_ADDRZIP, criteriaDto.getZip());
        }
        if (criteriaDto.getCity() != null) {
            criteria.addLikeClause(VwPersonAddress.PROP_ZIPCITY, criteriaDto.getCity());
        }
        if (criteriaDto.getState() != null) {
            criteria.addLikeClause(VwPersonAddress.PROP_ZIPSTATE, criteriaDto.getState());
        }
        if (criteriaDto.getRaceId() > 0) {
            criteria.addCriteria(VwPersonAddress.PROP_PERRACEID, criteriaDto.getRaceId());
        }
        if (criteriaDto.getMaritalStatusId() > 0) {
            criteria.addCriteria(VwPersonAddress.PROP_PERMARITALSTATUS, criteriaDto.getMaritalStatusId());
        }
        if (criteriaDto.getGenderId() > 0) {
            criteria.addCriteria(VwPersonAddress.PROP_PERGENDERID, criteriaDto.getGenderId());
        }

        // Set ordering.
        criteria.addOrderBy(VwPersonAddress.PROP_PERLASTNAME, VwPersonAddress.ORDERBY_ASCENDING);
        criteria.addOrderBy(VwPersonAddress.PROP_PERFIRSTNAME, VwPersonAddress.ORDERBY_ASCENDING);

        // Perform query
        List<VwPersonAddress> results = null;
        try {
            results = this.client.retrieveList(criteria);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new PersonalContactQueryDaoException(e);
        }
        List<ContactDto> list = new ArrayList<ContactDto>();
        for (VwPersonAddress item : results) {
            ContactDto dto = Rmt2AddressBookDtoFactory.getPersonInstance(item);
            list.add(dto);
        }
        return list;
    }

    private List<ContactDto> getBusinessContact(BusinessContactDto criteriaDto)
            throws PersonalContactQueryDaoException {
        VwBusinessAddress criteria = new VwBusinessAddress();
        if (criteriaDto.getContactIdList() != null && criteriaDto.getContactIdList().size() > 0) {
            Integer[] intArray = criteriaDto.getContactIdList().toArray(new Integer[criteriaDto.getContactIdList().size()]);
            criteria.addInClause(VwBusinessAddress.PROP_BUSINESSID, intArray);
        }
        if (criteriaDto.getContactId() > 0) {
            criteria.addCriteria(VwBusinessAddress.PROP_BUSINESSID, criteriaDto.getContactId());
        }
        if (criteriaDto.getContactName() != null) {
            criteria.addLikeClause(VwBusinessAddress.PROP_BUSLONGNAME, criteriaDto.getContactName());
        }
        if (criteriaDto.getShortName() != null) {
            criteria.addLikeClause(VwBusinessAddress.PROP_BUSSHORTNAME, criteriaDto.getShortName());
        }
        if (criteriaDto.getTaxId() != null) {
            criteria.addCriteria(VwBusinessAddress.PROP_BUSTAXID, criteriaDto.getTaxId());
        }
        if (criteriaDto.getContactEmail() != null) {
            criteria.addLikeClause(VwBusinessAddress.PROP_CONTACTEMAIL, criteriaDto.getContactEmail());
        }
        if (criteriaDto.getPhoneCompany() != null) {
            criteria.addCriteria(VwBusinessAddress.PROP_ADDRPHONEMAIN, criteriaDto.getPhoneCompany());
        }
        if (criteriaDto.getZip() > 0) {
            criteria.addCriteria(VwBusinessAddress.PROP_ADDRZIP, criteriaDto.getZip());
        }
        if (criteriaDto.getCity() != null) {
            criteria.addLikeClause(VwBusinessAddress.PROP_ZIPCITY, criteriaDto.getCity());
        }
        if (criteriaDto.getState() != null) {
            criteria.addLikeClause(VwBusinessAddress.PROP_ZIPSTATE, criteriaDto.getState());
        }
        if (criteriaDto.getEntityTypeId() > 0) {
            criteria.addCriteria(VwBusinessAddress.PROP_BUSENTITYTYPEID, criteriaDto.getEntityTypeId());
        }
        if (criteriaDto.getServTypeId() > 0) {
            criteria.addCriteria(VwBusinessAddress.PROP_BUSSERVTYPEID, criteriaDto.getServTypeId());
        }

        // Set ordering.
        criteria.addOrderBy(VwBusinessAddress.PROP_BUSLONGNAME, VwBusinessAddress.ORDERBY_ASCENDING);

        // Perform query
        List<VwBusinessAddress> results = null;
        try {
            results = this.client.retrieveList(criteria);
            if (results == null || results.isEmpty()) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new BusinessContactQueryDaoException(e);
        }

        List<ContactDto> list = new ArrayList<ContactDto>();
        for (VwBusinessAddress item : results) {
            ContactDto dto = Rmt2AddressBookDtoFactory.getBusinessInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Fetches list of contact DTO's from the <i>vw_business_address</i> view
     * based on the list of business id's supplied in <i>businessId</i>.
     * 
     * 
     * @param busIdList
     *            a List of String objects which each element is a business
     *            id's.
     * @return a List of {@link BusinessContactDto} objects order in ascending
     *         order by busines long name or null when no data is found.
     * @throws ContactDaoException
     */
    @Override
    public List<BusinessContactDto> fetchBusinessContact(List<String> busIdList) throws ContactDaoException {
        if (busIdList == null || busIdList.size() <= 0) {
            throw new ContactDaoException("Business Id list is invalid");
        }
        String ids[] = null;
        ids = (String[]) busIdList.toArray(new String[busIdList.size()]);
        VwBusinessAddress criteria = new VwBusinessAddress();
        criteria.addInClause(VwBusinessAddress.PROP_BUSINESSID, ids);
        criteria.addOrderBy(VwBusinessAddress.PROP_BUSLONGNAME, VwBusinessAddress.ORDERBY_ASCENDING);
        List<VwBusinessAddress> results = null;
        try {
            results = this.client.retrieveList(criteria);
            if (results == null) {
                return null;
            }
        } catch (DatabaseException e) {
            throw new ContactDaoException(e);
        }

        List<BusinessContactDto> list = new ArrayList<BusinessContactDto>();
        for (VwBusinessAddress item : results) {
            BusinessContactDto dto = Rmt2AddressBookDtoFactory.getBusinessInstance(item);
            list.add(dto);
        }
        return list;
    }

    /**
     * Inserts a new or updates an existing contact record in either the person
     * or business tables and the address table.
     * <p>
     * A SQL insert is performed when the primary key is equal to zero. When the
     * primary key id is greater than zero, an SQL update is applied. Special
     * update logic is implemented for each runtime type (personal, business, or
     * common) of <i>contact</i> to recognize certain data requirements in order
     * to successfully persist the contact record. Below is a table depicting
     * the object-to-table mapping as well as the columns required to be
     * populated for the update operations of each contact runtime type:<br>
     * <p>
     * <table border="1">
     * <tr>
     * <th>&nbsp;</th>
     * <th>Table Name</th>
     * <th>Contact Id</th>
     * <th>Contact Name</th>
     * </tr>
     * <tr>
     * <td><b>ContactDto</b></td>
     * <td>Business or Person (evaluate contact_type column to determine
     * table)</td>
     * <td>business.business_id or person.person_id</td>
     * <td>business.longname or person.shortname</td>
     * </tr>
     * <tr>
     * <td><b>BusinessContactDto</b></td>
     * <td>Business</td>
     * <td>business_id</td>
     * <td>longname</td>
     * </tr>
     * <tr>
     * <td><b>PersonalContactDto</b></td>
     * <td>Person</td>
     * <td>person_id</td>
     * <td>shortname</td>
     * </tr>
     * </table>
     * 
     * @param contact
     *            an instance of {@link ContactDto}
     * @return an int value representing either the unique identifier of the
     *         contact inserted, or the total number of rows effected by the
     *         contact update operation.
     * @throws ContactUpdateDaoException
     */
    @Override
    public int maintainContact(ContactDto contact) throws ContactUpdateDaoException {
        this.validateContactBasicInfo(contact);

//        this.client.beginTrans();
        int rc = 0;
        boolean error = false;
        try {
            if (contact instanceof PersonalContactDto) {
                rc = this.maintainPersonalContact((PersonalContactDto) contact);
            }
            else if (contact instanceof BusinessContactDto) {
                rc = this.maintainBusinessContact((BusinessContactDto) contact);
            }
            else if (contact instanceof ContactDto) {
                rc = this.maintainCommonContact(contact);
            }
            else {
                error = false;
                throw new ContactUpdateDaoException(
                        "The Contact input parameter for update operation is of an invalid runtime type.  Must be an instance of ContactDto, PersonalContactDto or BusinessContactDto");
            }
            return rc;
        } catch (ContactUpdateDaoException e) {
            error = true;
            throw e;
        } 
//        finally {
//            if (error) {
//                this.client.rollbackTrans();
//            }
//            else {
//                this.client.commitTrans();
//            }
//        }
    }

    /**
     * This method functions to insert or update a common contact DTO object.
     * <p>
     * An identification process determines if <i>contact</i> is a personal or
     * business contact. Once the contact type is identified, it is determined
     * if a the contact data is to be persisted as a new record or an existing
     * record.
     * 
     * @param contact
     *            an instance of {@link ContactDto}
     * @return an int value representing either the unique identifier of the
     *         contact inserted, or the total number of rows effected by the
     *         contact update operation.
     * @throws ContactUpdateDaoException
     *             <i>contact</i> fails validation test, the contact's profile
     *             does not exist for the update operation, or general database
     *             access error.
     */
    private int maintainCommonContact(ContactDto contact) throws ContactUpdateDaoException {
        this.validateContactGenericInfo(contact);
        int rc = 0;

        // handle business contact
        if (contact.getContactType().equalsIgnoreCase(ContactsConst.CONTACT_TYPE_BUSINESS)) {
            BusinessContactDto dto = Rmt2AddressBookDtoFactory.getNewBusinessInstance();
            if (contact.getContactId() > 0) {
                BusinessContactDto criteria = Rmt2AddressBookDtoFactory.getNewBusinessInstance();
                criteria.setContactId(contact.getContactId());
                List<ContactDto> list = this.fetchContact(criteria);
                if (list != null && list.size() == 1) {
                    dto = (BusinessContactDto) list.get(0);
                }
                else {
                    this.msg = "Common contact process could not locate profile for business contact, "
                            + contact.getContactName();
                    throw new ContactUpdateDaoException(this.msg);
                }
            }
            else {
                dto = Rmt2AddressBookDtoFactory.getNewBusinessInstance();
            }
            this.copyContactCommonInfo(contact, dto);
            try {
                rc = this.maintainBusinessContact(dto);
                // Update business contact with new UID in the event contact was
                // added to the database.
                if (contact.getContactId() == 0) {
                    contact.setContactId(rc);
                }
            } catch (Exception e) {
                this.msg = "Error occurred during common database maintenance for business contact, "
                        + contact.getContactName();
                throw new ContactUpdateDaoException(this.msg, e);
            }
        }

        // handle personal contact
        if (contact.getContactType().equalsIgnoreCase(ContactsConst.CONTACT_TYPE_PERSONAL)) {
            PersonalContactDto dto = Rmt2AddressBookDtoFactory.getNewPersonInstance();
            if (contact.getContactId() > 0) {
                PersonalContactDto criteria = Rmt2AddressBookDtoFactory.getNewPersonInstance();
                criteria.setContactId(contact.getContactId());
                List<ContactDto> list = this.fetchContact(criteria);
                if (list != null && list.size() == 1) {
                    dto = (PersonalContactDto) list.get(0);
                }
                else {
                    this.msg = "Common contact process could not locate profile for personal contact, "
                            + contact.getContactName();
                    throw new ContactUpdateDaoException(this.msg);
                }
            }
            else {
                dto = Rmt2AddressBookDtoFactory.getNewPersonInstance();
            }
            this.copyContactCommonInfo(contact, dto);
            try {
                rc = this.maintainPersonalContact(dto);
                // Update personal contact with new UID in the event contact was
                // added to the database.
                if (contact.getContactId() == 0) {
                    contact.setContactId(rc);
                }
            } catch (Exception e) {
                this.msg = "Error occurred during common database maintenance for personal contact, "
                        + contact.getContactName();
                throw new ContactUpdateDaoException(this.msg, e);
            }
        }
        return rc;
    }

    /**
     * This method functions to add a new or modify an existing personal contact
     * DTO object to the <i>personal</i> table.
     * <p>
     * An identification process determines if transaction is to manage
     * <i>contact</i> as a new or existing a entry to the database.
     * 
     * @param contact
     *            an instance of {@link PersonalContactDto}
     * @return an int value representing either the unique identifier of the
     *         contact inserted, or the total number of rows effected by the
     *         contact update operation.
     * @throws PersonalContactUpdateDaoException
     *             general problem(s) saving data changes for the personal
     *             contact.
     */
    private int maintainPersonalContact(PersonalContactDto contact) throws PersonalContactUpdateDaoException {
        this.validatePersonalContactInfo(contact);

        // Get Person information
        Person p = new Person();
        p.setPersonId(contact.getContactId());
        p.setFirstname(contact.getFirstname());
        p.setLastname(contact.getLastname());
        p.setMidname(contact.getMidname());
        p.setMaidenname(contact.getMaidenname());
        p.setGeneration(contact.getGeneration());
        p.setShortname(contact.getContactName());
        p.setTitle(contact.getTitle());
        p.setGenderId(contact.getGenderId());
        p.setMaritalStatusId(contact.getMaritalStatusId());
        p.setBirthDate(contact.getBirthDate());
        p.setRaceId(contact.getRaceId());
        p.setSsn(contact.getSsn());
        p.setEmail(contact.getContactEmail());
        p.setCategoryId(contact.getCategoryId());
        p.setUserId(contact.getUpdateUserId());

        // Get address info
        Address a = new Address();
        this.copyAddressInfo(contact, a);

        int rc = 0;
        try {
            if (p.getPersonId() > 0) {
                rc = this.updatePersonContact(p, a);
            }
            if (p.getPersonId() == 0) {
                rc = this.insertPersonContact(p, a);
                contact.setContactId(p.getPersonId());
                contact.setAddrId(a.getAddrId());
            }
            return rc;
        } catch (DatabaseException e) {
            this.msg = "Error occurred persisting data changes for personal contact, " + p.getShortname();
            throw new PersonalContactUpdateDaoException(this.msg, e);
        }
    }

    /**
     * Applies a SQL INSERT statement against the <i>person</i> table to add a
     * new personal contact.
     * 
     * @param person
     *            an instance of {@link Person} ORM object containing the
     *            details of the contact that is to be inserted.
     * @param addr
     *            an instance of {@link Address} ORM object containg the
     *            contact's address data that is to be inserted.
     * @return an int value representing the new unique identifier of the
     *         contact inserted.
     * 
     * @throws DatabaseException
     *             general database error
     */
    private int insertPersonContact(Person person, Address addr) throws DatabaseException {
        this.checkPersonalContactFkValues(person);
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            // Create person
            person.setDateCreated(ut.getDateCreated());
            person.setDateUpdated(ut.getDateCreated());
            person.setUserId(ut.getLoginId());
            int newPersonId = this.client.insertRow(person, true);

            // Create address for person
            addr.setDateCreated(ut.getDateCreated());
            addr.setDateUpdated(ut.getDateCreated());
            addr.setUserId(ut.getLoginId());
            addr.setPersonId(newPersonId);
            addr.setNull(Address.PROP_BUSINESSID);
            int newAddressId = this.client.insertRow(addr, true);

            person.setPersonId(newPersonId);
            addr.setAddrId(newAddressId);
            return newPersonId;
        } catch (Exception e) {
            this.msg = "Insert operation failed tageting the Person table";
            throw new PersonalContactUpdateDaoException(this.msg, e);
        }
    }

    /**
     * Applies a SQL UPDATE statement against the <i>person</i> table to modify
     * an existing personal contact.
     * 
     * @param person
     *            an instance of {@link Person} ORM object containing the
     *            details of the contact that is to be updated.
     * @param addr
     *            an instance of {@link Address} ORM object containg the
     *            contact's address data that is to be updated.
     * @return an int value representing the total number of rows effected by
     *         the contact update operation.
     * @throws DatabaseException
     *             general database error saving the personal contact
     * @throws NotFoundException
     *             profile does not exists in the <i>person</i> table for
     *             <i>contact</i>.
     */
    private int updatePersonContact(Person contact, Address addr) throws DatabaseException, NotFoundException {
        // Get orginal contact record and update contents
        Person deltaContact = this.getPersonContactOrm(contact.getPersonId());
        if (deltaContact == null) {
            throw new NotFoundException("Personal contact profile not found in database: " + contact.getPersonId());
        }
        deltaContact.setPersonId(contact.getPersonId());
        deltaContact.setFirstname(contact.getFirstname());
        deltaContact.setLastname(contact.getLastname());
        deltaContact.setMidname(contact.getMidname());
        deltaContact.setMaidenname(contact.getMaidenname());
        deltaContact.setGeneration(contact.getGeneration());
        deltaContact.setShortname(contact.getShortname());
        deltaContact.setTitle(contact.getTitle());
        deltaContact.setGenderId(contact.getGenderId());
        deltaContact.setMaritalStatusId(contact.getMaritalStatusId());
        deltaContact.setBirthDate(contact.getBirthDate());
        deltaContact.setRaceId(contact.getRaceId());
        deltaContact.setSsn(contact.getSsn());
        deltaContact.setEmail(contact.getEmail());
        deltaContact.setCategoryId(contact.getCategoryId());
        deltaContact.setUserId(contact.getUserId());

        // Assing null to those numeric properties that represent foreign keys
        // in the database
        this.checkPersonalContactFkValues(deltaContact);

        // Get orginal address record and update contents with the input Address
        // instance
        Address deltaAddr = this.getAddressInfo(addr.getAddrId());
        this.copyAddressInfo(addr, deltaAddr);

        // Apply changes to personal contact record to the database.
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            // update address
            deltaAddr.setDateUpdated(ut.getDateCreated());
            deltaAddr.setUserId(ut.getLoginId());
            deltaAddr.setNull(Address.PROP_BUSINESSID);
            deltaAddr.addCriteria(Address.PROP_ADDRID, deltaAddr.getAddrId());
            int rc = this.client.updateRow(deltaAddr);

            // update personal contact
            deltaContact.setDateUpdated(ut.getDateCreated());
            deltaContact.setUserId(ut.getLoginId());
            deltaContact.addCriteria(Person.PROP_PERSONID, deltaContact.getPersonId());
            rc = this.client.updateRow(deltaContact);
            return rc;
        } catch (Exception e) {
            this.msg = "Update operation targeting person table failed for contact, " + contact.getPersonId();
            throw new DatabaseException(this.msg, e);
        }
    }

    /**
     * This method functions to add a new or modify an existing business contact
     * DTO object to the <i>business</i> table.
     * <p>
     * An identification process determines if transaction is to manage
     * <i>contact</i> as a new or existing a entry to the database.
     * 
     * @param contact
     *            an instance of {@link BusinessContactDto}
     * @return an int value representing either the unique identifier of the
     *         contact inserted, or the total number of rows effected by the
     *         contact update operation.
     * @throws BusinessContactUpdateDaoException
     *             general problem(s) saving data changes for the business
     *             contact.
     */
    private int maintainBusinessContact(BusinessContactDto contact) throws BusinessContactUpdateDaoException {
        Business b = new Business();
        b.setBusinessId(contact.getContactId());
        b.setEntityTypeId(contact.getEntityTypeId());
        b.setServTypeId(contact.getServTypeId());
        b.setLongname(contact.getContactName());
        b.setShortname(contact.getShortName());
        b.setContactEmail(contact.getContactEmail());
        b.setContactExt(contact.getContactExt());
        b.setContactFirstname(contact.getContactFirstname());
        b.setContactLastname(contact.getContactLastname());
        b.setContactPhone(contact.getContactPhone());
        b.setTaxId(contact.getTaxId());
        b.setWebsite(contact.getWebsite());
        b.setCategoryId(contact.getCategoryId());
        b.setUserId(contact.getUpdateUserId());

        // Get address info
        Address a = new Address();
        this.copyAddressInfo(contact, a);

        int rc = 0;
        if (b.getBusinessId() > 0) {
            rc = this.updateBusinessContact(b, a);
        }
        if (b.getBusinessId() == 0) {
            rc = this.insertBusinessContact(b, a);
            contact.setContactId(b.getBusinessId());
            contact.setAddrId(a.getAddrId());
        }
        return rc;
    }

    /**
     * Applies a SQL INSERT statement against the <i>business</i> table to add a
     * new business contact.
     * 
     * @param contact
     *            an instance of {@link Business} ORM object containing the
     *            details of the contact that is to be inserted.
     * @param addr
     *            an instance of {@link Address} ORM object containg the
     *            contact's address data that is to be inserted.
     * @return an int value representing the new unique identifier of the
     *         contact inserted.
     * 
     * @throws DatabaseException
     *             general database error
     */
    private int insertBusinessContact(Business contact, Address addr) throws DatabaseException {
        this.checkBusinessContactFkValues(contact);
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            // Create person
            contact.setDateCreated(ut.getDateCreated());
            contact.setDateUpdated(ut.getDateCreated());
            contact.setUserId(ut.getLoginId());
            int newBusinessId = this.client.insertRow(contact, true);

            // Create address for person
            addr.setDateCreated(ut.getDateCreated());
            addr.setDateUpdated(ut.getDateCreated());
            addr.setUserId(ut.getLoginId());
            addr.setBusinessId(newBusinessId);
            addr.setNull(Address.PROP_PERSONID);
            int newAddressId = this.client.insertRow(addr, true);

            contact.setBusinessId(newBusinessId);
            addr.setAddrId(newAddressId);
            return newBusinessId;
        } catch (Exception e) {
            this.msg = "Insert operation tageting business table failed";
            throw new BusinessContactUpdateDaoException(this.msg, e);
        }
    }

    /**
     * Applies a SQL UPDATE statement against the <i>business</i> table to apply
     * changes to an existing business contact.
     * 
     * @param contact
     *            an instance of {@link Business} ORM object containing the
     *            details of the contact that is to be updated.
     * @param addr
     *            an instance of {@link Address} ORM object containg the
     *            contact's address data that is to be updated.
     * @return an int value representing the total number of rows effected by
     *         the contact update operation.
     * @throws DatabaseException
     *             general database error saving the business contact
     * @throws NotFoundException
     *             profile does not exists in the <i>business</i> table for
     *             <i>contact</i>.
     */
    private int updateBusinessContact(Business contact, Address addr) throws DatabaseException, NotFoundException {
        // Get orginal contact record and update contents
        Business deltaContact = this.getBusinessContactOrm(contact.getBusinessId());
        if (deltaContact == null) {
            throw new NotFoundException("Business contact profile not found in database: " + contact.getBusinessId());
        }
        deltaContact.setBusinessId(contact.getBusinessId());
        deltaContact.setEntityTypeId(contact.getEntityTypeId());
        deltaContact.setServTypeId(contact.getServTypeId());
        deltaContact.setLongname(contact.getLongname());
        deltaContact.setShortname(contact.getShortname());
        deltaContact.setContactFirstname(contact.getContactFirstname());
        deltaContact.setContactLastname(contact.getContactLastname());
        deltaContact.setContactPhone(contact.getContactPhone());
        deltaContact.setContactExt(contact.getContactExt());
        deltaContact.setContactEmail(contact.getContactEmail());
        deltaContact.setTaxId(contact.getTaxId());
        deltaContact.setWebsite(contact.getWebsite());
        deltaContact.setCategoryId(contact.getCategoryId());
        deltaContact.setUserId(contact.getUserId());

        // Assing null to those numeric properties that represent foreign keys
        // in the database
        this.checkBusinessContactFkValues(deltaContact);

        // Get orginal address record and update contents with the input Address
        // instance
        Address deltaAddr = this.getAddressInfo(addr.getAddrId());
        this.copyAddressInfo(addr, deltaAddr);

        // Apply changes to business contact record to the database.
        try {
            UserTimestamp ut = RMT2Date.getUserTimeStamp(this.getDaoUser());
            // update address
            deltaAddr.setDateUpdated(ut.getDateCreated());
            deltaAddr.setUserId(ut.getLoginId());
            deltaAddr.setNull(Address.PROP_PERSONID);
            deltaAddr.addCriteria(Address.PROP_ADDRID, deltaAddr.getAddrId());
            int rc = this.client.updateRow(deltaAddr);

            // update business contact
            deltaContact.setDateUpdated(ut.getDateCreated());
            deltaContact.setUserId(ut.getLoginId());
            deltaContact.addCriteria(Business.PROP_BUSINESSID, deltaContact.getBusinessId());
            rc = this.client.updateRow(deltaContact);
            return rc;
        } catch (Exception e) {
            this.msg = "Update operation targeting business table failed for contact, " + contact.getBusinessId();
            throw new BusinessContactUpdateDaoException(this.msg, e);
        }
    }

    /**
     * Applies basic validation rules against <i>contact</i> ensuring that it is
     * not null, and the contact name is present.
     * 
     * @param contact
     *            an instance of {@link ContactDto} containing the data to be
     *            validated.
     * @throws InvalidContactDataDaoException
     *             <i>contact</i> is null or the contact name property is null
     */
    protected void validateContactBasicInfo(ContactDto contact) throws InvalidContactDataDaoException {
        if (contact == null) {
            this.msg = "Contact object cannot be null";
            throw new InvalidContactDataDaoException(this.msg);
        }
        if (contact.getContactName() == null) {
            if (contact instanceof PersonalContactDto) {
                // Defer validation...
            }
            else {
                this.msg = "Contact name is required";
                throw new InvalidContactDataDaoException(this.msg);
            }
        }
        return;
    }

    /**
     * Validates a ContactDto object by ensuring that it contact type property's
     * value is present.
     * <p>
     * This method is generally used when <i>contact</i>'s actual runtime type
     * resolves only to the ContactDto interface. Besides being present, it is
     * required that it's value equal "per" or "bus".
     * 
     * @param contact
     *            an instance of {@link ContactDto} containing the data to be
     *            validated.
     * @throws InvalidContactDataDaoException
     *             <i>contact</i>'s property, contactType, is null or is not
     *             equal to <i>per</i> or <i>bus</i>.
     */
    protected void validateContactGenericInfo(ContactDto contact) throws InvalidContactDataDaoException {
        if (contact.getContactType() == null) {
            this.msg = "Contact type is required";
            throw new InvalidContactDataDaoException(this.msg);
        }
        if (!contact.getContactType().equalsIgnoreCase(ContactsConst.CONTACT_TYPE_BUSINESS)
                && !contact.getContactType().equalsIgnoreCase(ContactsConst.CONTACT_TYPE_PERSONAL)) {
            this.msg = "Contact type is required to be either \"bus\" or \"per\"";
            throw new InvalidContactDataDaoException(this.msg);
        }
        return;
    }

    /**
     * Validates a PersonalContactDto object by ensuring that the first name,
     * last name, and short name exists.
     * 
     * @param contact
     *            an instance of {@link PersonalContactDto} containing the data
     *            to be validated.
     * @throws InvalidContactDataDaoException
     *             <i>contact</i>'s properties, firstName, lastName, and/or
     *             shortName is equal to null.
     */
    protected void validatePersonalContactInfo(PersonalContactDto contact) throws InvalidContactDataDaoException {
        if (contact.getFirstname() != null && contact.getLastname() != null && contact.getContactName() == null) {
            contact.setContactName(contact.getLastname() + ", " + contact.getFirstname());
        }
        else if (contact.getFirstname() == null && contact.getLastname() == null && contact.getContactName() != null) {
            String name[] = contact.getContactName().split(" ");
            if (name.length == 1) {
                contact.setFirstname(name[0]);
            }
            for (int ndx = 0; ndx < name.length; ndx++) {
                if (ndx == 0) {
                    contact.setFirstname(name[ndx]);
                    continue;
                }
                if (contact.getLastname() == null) {
                    contact.setLastname(name[ndx]);
                }
                else {
                    contact.setLastname(contact.getLastname() + " " + name[ndx]);
                }
            }
        }
        else if (contact.getFirstname() != null && contact.getLastname() != null && contact.getContactName() != null) {
            // Name is okay. Do Nothing...
        }
        else {
            this.msg = "Contact name is required";
            throw new InvalidContactDataDaoException(this.msg);
        }
        return;
    }

    /**
     * Deletes a contact from either the <i>person</i> or <i>business</i> table,
     * removes all assocated addresses from the <i>address</i> table, and
     * returns the total number of rows deleted from the database.
     * <p>
     * A SQL deletet is performed when <i>contact</i> contains its primary key.
     * Special delete logic is implemented for each runtime type (personal,
     * business, or common) of <i>contact</i> to recognize certain data
     * requirements in order to successfully deleted the contact record. Below
     * is a table depicting the object-to-table mapping as well as the columns
     * required to be populated for the delete operation to be successful for
     * each contact runtime type:<br>
     * <p>
     * <table border="1">
     * <tr>
     * <th>&nbsp;</th>
     * <th>Table Name</th>
     * <th>Contact Id</th>
     * </tr>
     * <tr>
     * <td><b>ContactDto</b></td>
     * <td>Business or Person (evaluate contact_type column to determine
     * table)</td>
     * <td>business.business_id or person.person_id</td>
     * </tr>
     * <tr>
     * <td><b>BusinessContactDto</b></td>
     * <td>Business</td>
     * <td>business_id</td>
     * </tr>
     * <tr>
     * <td><b>PersonalContactDto</b></td>
     * <td>Person</td>
     * <td>person_id</td>
     * </tr>
     * </table>
     * 
     * @param contact
     *            an instance of {@link ContactDto}
     * @return The total number of entities effected by the delete operation
     *         which is the contact and its associated addresses. For instance,
     *         when the total rows effected equals 3, this means that one
     *         contact and two contact addresses were deleted.
     * @throws ContactUpdateDaoException
     */
    @Override
    public int deleteContact(ContactDto contact) throws ContactUpdateDaoException {
        if (contact == null) {
            this.msg = "A Contact instance is required as an input parameter when deleting a contact";
            throw new ContactUpdateDaoException(this.msg);
        }
        if (contact.getContactId() <= 0) {
            this.msg = "Contact Id is required when deleting a contact from the database";
            throw new ContactUpdateDaoException(this.msg);
        }

//        this.client.beginTrans();
        int rc = 0;
        boolean error = false;
        try {
            if (contact instanceof PersonalContactDto) {
                rc = this.deletePersonalContact(contact.getContactId());
            }
            else if (contact instanceof BusinessContactDto) {
                rc = this.deleteBusinessContact(contact.getContactId());
            }
            else if (contact instanceof ContactDto) {
                rc = this.deleteCommonContact(contact);
            }
            else {
                error = false;
                throw new ContactUpdateDaoException(
                        "The Contact input parameter delete operation is of an invalid runtime type.  Must be an instance of ContactDto, PersonalContactDto or BusinessContactDto");
            }
            return rc;
        } catch (ContactUpdateDaoException e) {
            error = true;
            throw e;
        } 
//        finally {
//            if (error) {
//                this.client.rollbackTrans();
//            }
//            else {
//                this.client.commitTrans();
//            }
//        }
    }

    /**
     * Deletes a common contact from the database.
     * 
     * @param contact
     *            an instance of {@link ContactDto}
     * @return the total number of rows deleted.
     * @throws DatabaseException
     *             The contact id is not set in <i>contact</i> or general
     *             database error.
     */
    private int deleteCommonContact(ContactDto contact) throws DatabaseException {
        this.validateContactGenericInfo(contact);
        int rows = 0;
        try {
            if (contact.getContactType().equalsIgnoreCase(ContactsConst.CONTACT_TYPE_PERSONAL)) {
                rows = this.deletePersonalContact(contact.getContactId());
            }
            else {
                rows = this.deleteBusinessContact(contact.getContactId());
            }
            return rows;
        } catch (Exception e) {
            this.msg = "Unable to deleting common contact identified as: " + contact.getContactId();
            throw new ContactUpdateDaoException(this.msg, e);
        }

    }

    /**
     * Deletes a personal contact from the database.
     * 
     * @param contactId
     *            int value representing the contact id.
     * @return the total number of rows deleted.
     * @throws DatabaseException
     *             genreal database access error
     */
    private int deletePersonalContact(int contactId) throws DatabaseException {
        int rows = 0;
        Address a = new Address();
        a.addCriteria(Address.PROP_PERSONID, contactId);
        try {
            rows = this.client.deleteRow(a);
        } catch (Exception e) {
            this.msg = "Error occurred deleting assoicated address records from the address table for personal contact, "
                    + contactId;
            throw new ContactUpdateDaoException(this.msg, e);
        }

        Person p = new Person();
        p.addCriteria(Person.PROP_PERSONID, contactId);
        try {
            return rows += this.client.deleteRow(p);
        } catch (Exception e) {
            this.msg = "Error occurred deleting contact from the person table, " + contactId;
            throw new ContactUpdateDaoException(this.msg, e);
        }
    }

    /**
     * Deletes a business contact from the database.
     * 
     * @param contactId
     *            int value representing the contact id.
     * @return the total number of rows deleted.
     * @throws DatabaseException
     *             genreal database access error
     */
    private int deleteBusinessContact(int contactId) throws DatabaseException {
        int rows = 0;
        Address a = new Address();
        a.addCriteria(Address.PROP_BUSINESSID, contactId);
        try {
            rows = this.client.deleteRow(a);
        } catch (Exception e) {
            this.msg = "Error occurred deleting assoicated address records from the address table for business contact, "
                    + contactId;
            throw new ContactUpdateDaoException(this.msg, e);
        }

        Business b = new Business();
        b.addCriteria(Business.PROP_BUSINESSID, contactId);
        try {
            return rows += this.client.deleteRow(b);
        } catch (Exception e) {
            this.msg = "Error occurred deleting contact from the business table, " + contactId;
            throw new ContactUpdateDaoException(this.msg, e);
        }
    }

    private Person getPersonContactOrm(int contactId) throws ContactDaoException {
        Person criteria = new Person();
        criteria.addCriteria(Person.PROP_PERSONID, contactId);
        Person results = null;
        try {
            results = (Person) this.client.retrieveObject(criteria);
            return results;
        } catch (DatabaseException e) {
            throw new ContactDaoException(e);
        }
    }

    private Business getBusinessContactOrm(int contactId) throws ContactDaoException {
        Business criteria = new Business();
        criteria.addCriteria(Business.PROP_BUSINESSID, contactId);
        Business results = null;
        try {
            results = (Business) this.client.retrieveObject(criteria);
            return results;
        } catch (DatabaseException e) {
            throw new ContactDaoException(e);
        }
    }

    /**
     * Sets a paticular set of person ORM contact properties, whose current
     * values are zero, to null.
     * <p>
     * These properties correspond to foreign keys in the database and zero
     * violates the referential integrity rules. Currently, the properties
     * targeted are: gender id, title, marital status, race id, and category id.
     * 
     * @param person
     *            an instance of {@link Person}
     */
    private void checkPersonalContactFkValues(Person person) {
        if (person.getGenderId() == 0) {
            person.setNull(Person.PROP_GENDERID);
        }
        if (person.getTitle() == 0) {
            person.setNull(Person.PROP_TITLE);
        }
        if (person.getMaritalStatusId() == 0) {
            person.setNull(Person.PROP_MARITALSTATUSID);
        }
        if (person.getRaceId() == 0) {
            person.setNull(Person.PROP_RACEID);
        }
        if (person.getCategoryId() == 0) {
            person.setNull(Person.PROP_CATEGORYID);
        }
    }

    /**
     * Sets a paticular set of business ORM contact properties, whose current
     * values are zero, to null.
     * <p>
     * These properties correspond to foreign keys in the database and zero
     * violates the referential integrity rules. Currently, the properties
     * targeted are: entity type id, service type id, and category id.
     * 
     * @param business
     *            an instance of {@link Business}
     */
    private void checkBusinessContactFkValues(Business business) {
        if (business.getEntityTypeId() == 0) {
            business.setNull(Business.PROP_ENTITYTYPEID);
        }
        if (business.getServTypeId() == 0) {
            business.setNull(Business.PROP_SERVTYPEID);
        }
        if (business.getCategoryId() == 0) {
            business.setNull(Person.PROP_CATEGORYID);
        }
    }

    /**
     * Fetches a particular address by <i>addressId</i> and returns the results
     * as an ORM Address object.
     * 
     * @param addressId
     *            an int value representing the address id or primary key.
     * @return an instance of {@link Address}
     * @throws ContactDaoException
     *             general database access errors.
     */
    protected Address getAddressInfo(int addressId) throws ContactDaoException {
        Address criteria = new Address();
        criteria.addCriteria(Address.PROP_ADDRID, addressId);
        try {
            Address result = (Address) this.client.retrieveObject(criteria);
            return result;
        } catch (DatabaseException e) {
            throw new ContactDaoException(e);
        }
    }

    /**
     * Copies the address information from a <i>ConactDto</i> object to a valid
     * ORM <i>Address</i> object.
     * 
     * @param src
     *            an instance of {@link ContactDto} which is the source of the
     *            data transfer.
     * @param dest
     *            an instance of {@link Address} which is the destination of the
     *            data transfer.
     */
    protected void copyAddressInfo(ContactDto src, Address dest) {
        dest.setAddrId(src.getAddrId());

        // set person id or business id
        if (src instanceof PersonalContactDto) {
            dest.setPersonId(src.getContactId());
        }
        else if (src instanceof BusinessContactDto) {
            dest.setBusinessId(src.getContactId());
        }
        else {
            if (src.getContactType().equalsIgnoreCase(ContactsConst.CONTACT_TYPE_BUSINESS)) {
                dest.setBusinessId(src.getContactId());
            }
            else {
                dest.setPersonId(src.getContactId());
            }
        }

        // set the remaining properties.
        dest.setAddr1(src.getAddr1());
        dest.setAddr2(src.getAddr2());
        dest.setAddr3(src.getAddr3());
        dest.setAddr4(src.getAddr4());
        dest.setZip(src.getZip());
        dest.setZipext(src.getZipext());
        dest.setPhoneCell(src.getPhoneCell());
        dest.setPhoneExt(src.getPhoneExt());
        dest.setPhoneFax(src.getPhoneFax());
        dest.setPhoneHome(src.getPhoneHome());
        dest.setPhoneMain(src.getPhoneCompany());
        dest.setPhonePager(src.getPhonePager());
        dest.setPhoneWork(src.getPhoneWork());
        dest.setUserId(src.getUpdateUserId());
        return;
    }

    /**
     * Copies the address information from one <i>Address</i> object to another.
     * 
     * @param src
     *            an instance of {@link Address} which is the source of the data
     *            transfer.
     * @param dest
     *            an instance of {@link Address} which is the destination of the
     *            data transfer.
     */
    protected void copyAddressInfo(Address src, Address dest) {
        dest.setAddr1(src.getAddr1());
        dest.setAddr2(src.getAddr2());
        dest.setAddr3(src.getAddr3());
        dest.setAddr4(src.getAddr4());
        dest.setZip(src.getZip());
        dest.setZipext(src.getZipext());
        dest.setPhoneCell(src.getPhoneCell());
        dest.setPhoneExt(src.getPhoneExt());
        dest.setPhoneFax(src.getPhoneFax());
        dest.setPhoneHome(src.getPhoneHome());
        dest.setPhoneMain(src.getPhoneMain());
        dest.setPhonePager(src.getPhonePager());
        dest.setPhoneWork(src.getPhoneWork());
        dest.setDateCreated(src.getDateCreated());
        dest.setDateUpdated(src.getDateUpdated());
        dest.setUserId(src.getUserId());
    }

    /**
     * Copies the contact detail information from one <i>ContactDto</i> object
     * to another.
     * 
     * @param src
     *            an instance of {@link ContactDto} which is the source of the
     *            data transfer.
     * @param dest
     *            an instance of {@link ContactDto} which is the destination of
     *            the data transfer.
     */
    protected void copyContactCommonInfo(ContactDto src, ContactDto dest) {
        dest.setContactEmail(src.getContactEmail());
        dest.setContactName(src.getContactName());
        dest.setUpdateUserId(src.getUpdateUserId());
        dest.setAddr1(src.getAddr1());
        dest.setAddr2(src.getAddr2());
        dest.setAddr3(src.getAddr3());
        dest.setAddr4(src.getAddr4());
        dest.setZip(src.getZip());
        dest.setZipext(src.getZipext());
        dest.setPhoneCell(src.getPhoneCell());
        dest.setPhoneExt(src.getPhoneExt());
        dest.setPhoneFax(src.getPhoneFax());
        dest.setPhoneHome(src.getPhoneHome());
        dest.setPhoneCompany(src.getPhoneCompany());
        dest.setPhonePager(src.getPhonePager());
        dest.setPhoneWork(src.getPhoneWork());
        dest.setUpdateUserId(src.getUpdateUserId());
    }

}
