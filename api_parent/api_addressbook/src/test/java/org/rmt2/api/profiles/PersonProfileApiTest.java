package org.rmt2.api.profiles;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.dao.contacts.ContactDaoException;
import org.dao.contacts.ContactUpdateDaoException;
import org.dao.contacts.ContactsConst;
import org.dao.contacts.PersonalContactQueryDaoException;
import org.dao.contacts.PersonalContactUpdateDaoException;
import org.dao.mapping.orm.rmt2.Address;
import org.dao.mapping.orm.rmt2.Person;
import org.dao.mapping.orm.rmt2.VwBusinessAddress;
import org.dao.mapping.orm.rmt2.VwPersonAddress;
import org.dto.BusinessContactDto;
import org.dto.ContactDto;
import org.dto.PersonalContactDto;
import org.dto.adapter.orm.Rmt2AddressBookDtoFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.AddressBookConstants;
import org.modules.contacts.ContactsApi;
import org.modules.contacts.ContactsApiException;
import org.modules.contacts.ContactsApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.BaseAddressBookDaoTest;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Test functionality as it pertains to personal contact profiles
 * 
 * @author royterrell
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class })
public class PersonProfileApiTest extends BaseAddressBookDaoTest {

    private List<VwPersonAddress> mockFetchSingleResponse;
    private List<VwPersonAddress> mockCriteriaFetchResponse;
    private List<VwPersonAddress> mockFetchAllResponse;
    private List<VwPersonAddress> mockNotFoundFetchResponse;
    private Person mockPersonObject;
    private Address mockAddressObject;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        this.mockFetchSingleResponse = this.createMockSingleFetchResponse();
        this.mockCriteriaFetchResponse = this.createMockFetchUsingCriteriaResponse();
        this.mockFetchAllResponse = this.createMockFetchAllResponse();
        this.mockNotFoundFetchResponse = this.createMockNotFoundSearchResultsResponse();
        this.mockPersonObject = this.createSingleMockOrmObject();
        this.mockAddressObject = this.createAddressMockObject();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    private List<VwPersonAddress> createMockNotFoundSearchResultsResponse() {
        List<VwPersonAddress> list = null;
        return list;
    }

    private List<VwPersonAddress> createMockSingleFetchResponse() {
        List<VwPersonAddress> list = new ArrayList<VwPersonAddress>();
        VwPersonAddress p = new VwPersonAddress();
        p.setPersonId(1351);
        p.setPerFirstname("Dennis");
        p.setPerLastname("Chambers");
        p.setPerMidname("Milton");
        p.setPerTitle(100);
        p.setPerEmail("royterrell@gte.net");
        p.setPerMaritalStatus(20);
        p.setPerGenderId(12);
        p.setPerSsn("345-59-4938");
        p.setPerRaceId(30);

        p.setAddrId(2222);
        p.setAddrBusinessId(1351);
        p.setAddr1("94393 Hall Ave.");
        p.setAddr2("Building 9");
        p.setAddr3("Room 947");
        p.setZipCity("dallas");
        p.setZipState("TX");
        p.setAddrZip(75028);
        p.setAddrZipext(1234);

        list.add(p);
        return list;
    }

    /**
     * Use for the following selection criteria: where last name begins with 'C'
     * 
     * @return
     */
    private List<VwPersonAddress> createMockFetchUsingCriteriaResponse() {
        List<VwPersonAddress> list = new ArrayList<VwPersonAddress>();
        VwPersonAddress p = new VwPersonAddress();
        p.setPersonId(1351);
        p.setPerFirstname("Billy");
        p.setPerLastname("Cobham");
        p.setPerMidname("Milton");
        p.setPerTitle(100);
        p.setPerEmail("royterrell@gte.net");
        p.setPerMaritalStatus(20);
        p.setPerGenderId(12);
        p.setPerSsn("345-59-4938");
        p.setPerRaceId(30);

        p.setAddrId(1111);
        p.setAddrBusinessId(3333);
        p.setAddr1("8439 Elm St");
        p.setAddr2("Suite 45");
        p.setZipCity("dallas");
        p.setZipState("TX");
        p.setAddrZip(75232);
        list.add(p);

        p = new VwPersonAddress();
        p.setPersonId(1351);
        p.setPerFirstname("Dennis");
        p.setPerLastname("Chambers");
        p.setPerMidname("Raye");
        p.setPerTitle(100);
        p.setPerEmail("harveymason@gte.net");
        p.setPerMaritalStatus(20);
        p.setPerGenderId(12);
        p.setPerSsn("675-88-7563");
        p.setPerRaceId(30);

        p.setAddrId(55555);
        p.setAddrBusinessId(4444);
        p.setAddr1("77474 Heist Ave");
        p.setZipCity("Boston");
        p.setZipState("MA");
        p.setAddrZip(65069);
        list.add(p);

        return list;
    }

    private List<VwPersonAddress> createMockFetchAllResponse() {
        List<VwPersonAddress> list = new ArrayList<VwPersonAddress>();
        VwPersonAddress p = new VwPersonAddress();
        p.setPersonId(100);
        p.setPerFirstname("Firstname1");
        p.setPerLastname("Lastname1");
        p.setPerMidname("MidName1");
        p.setPerTitle(100);
        p.setPerEmail("Email1");
        p.setPerSsn("SSN1");
        p.setPerRaceId(10);
        p.setPerMaritalStatus(20);
        p.setPerGenderId(30);

        p.setAddrId(1000);
        p.setAddrPersonId(100);
        p.setAddr1("Address1-1");
        p.setAddr2("Address2-1");
        p.setAddr3("Address3-1");
        p.setAddr4("Address4-1");
        p.setZipCity("City1");
        p.setZipState("State1");
        p.setAddrZip(75230);
        
        p.setAddrPhoneCell("CellPhone1");
        p.setAddrPhoneFax("FaxPhone1");
        p.setAddrPhoneHome("HomePhone1");
        p.setAddrPhoneMain("MainPhone1");
        p.setAddrPhonePager("PagerPhone1");
        p.setAddrPhoneWork("WorkPhone1");
        p.setAddrPhoneExt("ExtPhone1");
        list.add(p);

        p = new VwPersonAddress();
        p.setPersonId(101);
        p.setPerFirstname("Firstname2");
        p.setPerLastname("Lastname2");
        p.setPerMidname("MidName2");
        p.setPerTitle(100);
        p.setPerEmail("Email2");
        p.setPerSsn("SSN2");
        p.setPerRaceId(11);
        p.setPerMaritalStatus(21);
        p.setPerGenderId(31);

        p.setAddrId(1001);
        p.setAddrPersonId(101);
        p.setAddr1("Address1-2");
        p.setAddr2("Address2-2");
        p.setAddr3("Address3-2");
        p.setAddr4("Address4-2");
        p.setZipCity("City2");
        p.setZipState("State2");
        p.setAddrZip(75231);
        
        p.setAddrPhoneCell("CellPhone2");
        p.setAddrPhoneFax("FaxPhone2");
        p.setAddrPhoneHome("HomePhone2");
        p.setAddrPhoneMain("MainPhone2");
        p.setAddrPhonePager("PagerPhone2");
        p.setAddrPhoneWork("WorkPhone2");
        p.setAddrPhoneExt("ExtPhone2");
        list.add(p);

        p = new VwPersonAddress();
        p.setPersonId(102);
        p.setPerFirstname("Firstname3");
        p.setPerLastname("Lastname3");
        p.setPerMidname("MidName3");
        p.setPerTitle(100);
        p.setPerEmail("Email3");
        p.setPerSsn("SSN3");
        p.setPerRaceId(12);
        p.setPerMaritalStatus(22);
        p.setPerGenderId(32);

        p.setAddrId(1002);
        p.setAddrPersonId(102);
        p.setAddr1("Address1-3");
        p.setAddr2("Address2-3");
        p.setAddr3("Address3-3");
        p.setAddr4("Address4-3");
        p.setZipCity("City3");
        p.setZipState("State3");
        p.setAddrZip(75232);
        
        p.setAddrPhoneCell("CellPhone3");
        p.setAddrPhoneFax("FaxPhone3");
        p.setAddrPhoneHome("HomePhone3");
        p.setAddrPhoneMain("MainPhone3");
        p.setAddrPhonePager("PagerPhone3");
        p.setAddrPhoneWork("WorkPhone3");
        p.setAddrPhoneExt("ExtPhone3");
        list.add(p);

        return list;
    }

    private PersonalContactDto createMockContactDto(int contactId, int addressId) {
        Person p = this.createSingleMockOrmObject();
        Address a = this.createAddressMockObject();
        p.setPersonId(contactId);
        a.setAddrId(addressId);
        PersonalContactDto perDto = Rmt2AddressBookDtoFactory.getPersonInstance(p, a);
        return perDto;
    }

    private Person createSingleMockOrmObject() {
        Person p = new Person();
        p.setPersonId(1351);
        p.setFirstname("Harvey");
        p.setLastname("Mason");
        p.setMidname("Raye");
        p.setTitle(100);
        p.setEmail("harveymason@gte.net");
        p.setMaritalStatusId(20);
        p.setGenderId(12);
        p.setSsn("675-88-7563");
        p.setRaceId(30);
        return p;
    }

    private Person createCriteriaMockObject(int contactId) {
        Person criteria = new Person();
        criteria.addCriteria(Person.PROP_PERSONID, contactId);
        return criteria;
    }

    private Address createAddressMockObject() {
        Address a = new Address();
        a.setAddrId(2222);
        a.setBusinessId(1351);
        a.setAddr1("94393 Hall Ave.");
        a.setAddr2("Suite 948");
        a.setAddr3("P.O. Box 84763");
        a.setZip(75028);
        a.setZipext(1234);
        return a;
    }

    private Address createAddressCriteriaMockObject(int addressId) {
        Address criteria = new Address();
        criteria.addCriteria(Address.PROP_ADDRID, addressId);
        return criteria;
    }

    @Test
    public void testFetchAll() {
        PersonalContactDto perDto = Rmt2AddressBookDtoFactory.getNewPersonInstance();
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwBusinessAddress.class)))
                    .thenReturn(this.mockFetchAllResponse);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("All personal contact fetch test case failed");
        }

        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ContactDto> results = null;
        try {
            results = api.getContact(perDto);
        } catch (ContactsApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(3, results.size());

        for (int ndx = 0; ndx < results.size(); ndx++) {
            ContactDto contact = results.get(ndx);
            Assert.assertNotNull(contact);
            Assert.assertNotNull(contact.getContactType());
            Assert.assertEquals(ContactsConst.CONTACT_TYPE_PERSONAL, contact.getContactType());
            Assert.assertTrue(contact.getContactId() > 0);
            Assert.assertNotNull(contact.getContactName());

            Assert.assertTrue(contact instanceof PersonalContactDto);
            PersonalContactDto perContact = (PersonalContactDto) contact;
            Assert.assertNotNull(perContact.getFirstname());
            Assert.assertNotNull(perContact.getLastname());
            Assert.assertNotNull(perContact.getContactEmail());
                
                Assert.assertEquals(perContact.getContactId(), (100 + ndx));
                Assert.assertEquals(perContact.getContactName(), "Firstname" + (ndx + 1) + " " + "Lastname" + (ndx + 1));
                Assert.assertEquals(perContact.getFirstname(), "Firstname" + (ndx + 1));
                Assert.assertEquals(perContact.getLastname(), "Lastname" + (ndx + 1));
                Assert.assertEquals(perContact.getMidname(), "MidName" + (ndx + 1));
//                Assert.assertEquals(perContact.getContactPhone(), "BusinessContactPhone" + (ndx + 1));
                Assert.assertEquals(perContact.getContactEmail(), "Email" + (ndx + 1));
                Assert.assertEquals(perContact.getSsn(), "SSN" + (ndx + 1));
                Assert.assertEquals(perContact.getRaceId(), 10 + (ndx));
                Assert.assertEquals(perContact.getMaritalStatusId(), 20 + (ndx));
                Assert.assertEquals(perContact.getGenderId(), 30 + (ndx));
                
                Assert.assertEquals(perContact.getAddrId(), (1000 + ndx));
                Assert.assertEquals(perContact.getAddr1(), "Address1-" + (ndx + 1));
                Assert.assertEquals(perContact.getAddr2(), "Address2-" + (ndx + 1));
                Assert.assertEquals(perContact.getAddr3(), "Address3-" + (ndx + 1));
                Assert.assertEquals(perContact.getAddr4(), "Address4-" + (ndx + 1));
                Assert.assertEquals(perContact.getCity(), "City" + (ndx + 1));
                Assert.assertEquals(perContact.getState(), "State" + (ndx + 1));
                Assert.assertEquals(perContact.getZip(), (75230 + ndx));
                
                Assert.assertEquals(contact.getPhoneCell(), "CellPhone" + (ndx + 1));
                Assert.assertEquals(contact.getPhoneCompany(), "MainPhone" + (ndx + 1));
                Assert.assertEquals(contact.getPhoneExt(), "ExtPhone" + (ndx + 1));
                Assert.assertEquals(contact.getPhoneFax(), "FaxPhone" + (ndx + 1));
                Assert.assertEquals(contact.getPhoneHome(), "HomePhone" + (ndx + 1));
                Assert.assertEquals(contact.getPhonePager(), "PagerPhone" + (ndx + 1));
                Assert.assertEquals(contact.getPhoneWork(), "WorkPhone" + (ndx + 1));
        }
    }

    @Test
    public void testFetchSingle() {
        Person per = new Person();
        per.setPersonId(1351);
        PersonalContactDto perDto = Rmt2AddressBookDtoFactory.getPersonInstance(per, null);
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwPersonAddress.class)))
                    .thenReturn(this.mockFetchSingleResponse);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("Single person contact fetch test case failed");
        }

        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ContactDto> results = null;
        try {
            results = api.getContact(perDto);
        } catch (ContactsApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        ContactDto contact = results.get(0);
        Assert.assertNotNull(contact.getContactType());
        Assert.assertEquals(ContactsConst.CONTACT_TYPE_PERSONAL, contact.getContactType());
        Assert.assertEquals(1351, contact.getContactId());
        Assert.assertNotNull(contact.getContactName());
        Assert.assertEquals("Dennis Chambers", contact.getContactName());

        Assert.assertTrue(contact instanceof PersonalContactDto);
        PersonalContactDto perContact = (PersonalContactDto) contact;
        Assert.assertNotNull(perContact.getFirstname());
        Assert.assertNotNull(perContact.getLastname());
        Assert.assertNotNull(perContact.getContactEmail());

    }

    @Test
    public void testFetchUsingCriteria() {
        Person per = new Person();
        per.setLastname("M");
        PersonalContactDto perDto = Rmt2AddressBookDtoFactory.getPersonInstance(per, null);
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwBusinessAddress.class)))
                    .thenReturn(this.mockCriteriaFetchResponse);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("person contact fetch using specific selection criteria test case failed");
        }

        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ContactDto> results = null;
        try {
            results = api.getContact(perDto);
        } catch (ContactsApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
        for (ContactDto contact : results) {
            Assert.assertNotNull(contact);
            Assert.assertNotNull(contact.getContactType());
            Assert.assertEquals(ContactsConst.CONTACT_TYPE_PERSONAL, contact.getContactType());
            Assert.assertTrue(contact.getContactId() > 0);
            Assert.assertNotNull(contact.getContactName());
            String name[] = contact.getContactName().split(" ");
            Assert.assertEquals(2, name.length);
            Assert.assertEquals("C", name[1].substring(0, 1));

            Assert.assertTrue(contact instanceof PersonalContactDto);
            PersonalContactDto busContact = (PersonalContactDto) contact;
            Assert.assertNotNull(busContact.getFirstname());
            Assert.assertNotNull(busContact.getLastname());
            Assert.assertNotNull(busContact.getContactEmail());
        }
    }

    @Test
    public void testFetchSingleUsingDtoOnly() {
        PersonalContactDto perDto = Rmt2AddressBookDtoFactory.getNewPersonInstance();
        perDto.setContactId(1351);
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwPersonAddress.class)))
                    .thenReturn(this.mockFetchSingleResponse);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("Single person contact fetch test case failed");
        }

        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ContactDto> results = null;
        try {
            results = api.getContact(perDto);
        } catch (ContactsApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        ContactDto contact = results.get(0);
        Assert.assertNotNull(contact.getContactType());
        Assert.assertEquals(ContactsConst.CONTACT_TYPE_PERSONAL, contact.getContactType());
        Assert.assertEquals(1351, contact.getContactId());
        Assert.assertNotNull(contact.getContactName());
        Assert.assertEquals("Dennis Chambers", contact.getContactName());

        Assert.assertTrue(contact instanceof PersonalContactDto);
        PersonalContactDto perContact = (PersonalContactDto) contact;
        Assert.assertNotNull(perContact.getFirstname());
        Assert.assertNotNull(perContact.getLastname());
        Assert.assertNotNull(perContact.getContactEmail());

    }

    @Test
    public void testFetchUsingUsingCriteiraDtoOnly() {
        PersonalContactDto perDto = Rmt2AddressBookDtoFactory.getNewPersonInstance();
        perDto.setContactName("M");
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwBusinessAddress.class)))
                    .thenReturn(this.mockCriteriaFetchResponse);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("person contact fetch using specific selection criteria test case failed");
        }

        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ContactDto> results = null;
        try {
            results = api.getContact(perDto);
        } catch (ContactsApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
        for (ContactDto contact : results) {
            Assert.assertNotNull(contact);
            Assert.assertNotNull(contact.getContactType());
            Assert.assertEquals(ContactsConst.CONTACT_TYPE_PERSONAL, contact.getContactType());
            Assert.assertTrue(contact.getContactId() > 0);
            Assert.assertNotNull(contact.getContactName());
            String name[] = contact.getContactName().split(" ");
            Assert.assertEquals(2, name.length);
            Assert.assertEquals("C", name[1].substring(0, 1));

            Assert.assertTrue(contact instanceof PersonalContactDto);
            PersonalContactDto busContact = (PersonalContactDto) contact;
            Assert.assertNotNull(busContact.getFirstname());
            Assert.assertNotNull(busContact.getLastname());
            Assert.assertNotNull(busContact.getContactEmail());
        }
    }

    @Test
    public void testNotFoundlFetch() {
        PersonalContactDto perDto = Rmt2AddressBookDtoFactory.getNewPersonInstance();
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwBusinessAddress.class)))
                    .thenReturn(this.mockNotFoundFetchResponse);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("Not Found: person contact fetch test case failed");
        }

        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ContactDto> results = null;
        try {
            results = api.getContact(perDto);
        } catch (ContactsApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }

    @Test
    public void testFetchUsingCriteriaNullResults() {
        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.getContact(null);
            Assert.fail("Expected test case to throw an exception");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ContactsApiException);
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate() {
        PersonalContactDto mockUpdatePersonDto = this.createMockContactDto(1351, 2222);
        try {
            when(this.mockPersistenceClient.retrieveObject(any(Object.class))).thenReturn(this.mockPersonObject,
                    this.mockAddressObject);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("Business contact update test case failed setting up business and address object calls");
        }
        try {
            when(this.mockPersistenceClient.updateRow(any(Object.class))).thenReturn(1);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("Business contact update test case failed setting up update call");
        }
        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.updateContact(mockUpdatePersonDto);
        } catch (Exception e) {
            Assert.fail("Business contact update test case failed");
            e.printStackTrace();
        }
        Assert.assertEquals(1, rc);
    }

    @Test
    public void testInsert() {
        PersonalContactDto mockUpdatePersonDto = this.createMockContactDto(0, 0);
        try {
            when(this.mockPersistenceClient.insertRow(any(PersonalContactDto.class), any(Boolean.class)))
                    .thenReturn(1351);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("Person contact insert test case failed setting up update call");
        }
        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.updateContact(mockUpdatePersonDto);
        } catch (Exception e) {
            Assert.fail("Person contact insert test case failed");
            e.printStackTrace();
        }
        Assert.assertEquals(1351, rc);
        Assert.assertEquals(1351, mockUpdatePersonDto.getContactId());
        Assert.assertEquals(1351, mockUpdatePersonDto.getAddrId());

    }

    @Test
    public void testDelete() {
        PersonalContactDto mockUpdatePersonDto = this.createMockContactDto(1351, 2222);
        try {
            when(this.mockPersistenceClient.deleteRow(any(BusinessContactDto.class))).thenReturn(1);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("Person contact delete test case failed setting up update call");
        }
        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.deleteContact(mockUpdatePersonDto);
        } catch (Exception e) {
            Assert.fail("Person contact delete test case failed");
            e.printStackTrace();
        }
        Assert.assertEquals(2, rc);
    }
    
    @Test
    public void testFetchDaoException() {
        PersonalContactDto perDto = Rmt2AddressBookDtoFactory.getNewPersonInstance();
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwBusinessAddress.class)))
                    .thenThrow(DatabaseException.class);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("All personal contact fetch test case failed");
        }

        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.getContact(perDto);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ContactsApiException);
            Assert.assertTrue(e.getCause() instanceof PersonalContactQueryDaoException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testInsertDaoException() {
        PersonalContactDto mockUpdatePersonDto = this.createMockContactDto(0, 0);
        try {
            when(this.mockPersistenceClient.insertRow(any(PersonalContactDto.class), any(Boolean.class)))
                 .thenThrow(DatabaseException.class);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("Person contact insert test case failed setting up update call");
        }
        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.updateContact(mockUpdatePersonDto);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ContactsApiException);
            Assert.assertTrue(e.getCause() instanceof PersonalContactUpdateDaoException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUpdateDaoException() {
        PersonalContactDto mockUpdatePersonDto = this.createMockContactDto(1351, 2222);
        try {
            when(this.mockPersistenceClient.retrieveObject(any(Object.class))).thenReturn(this.mockPersonObject,
                    this.mockAddressObject);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("Business contact update test case failed setting up business and address object calls");
        }
        try {
            when(this.mockPersistenceClient.updateRow(any(Object.class)))
                 .thenThrow(DatabaseException.class);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("Business contact update test case failed setting up update call");
        }
        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.updateContact(mockUpdatePersonDto);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ContactsApiException);
            Assert.assertTrue(e.getCause() instanceof PersonalContactUpdateDaoException);
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteDaoException() {
        PersonalContactDto mockUpdatePersonDto = this.createMockContactDto(1351, 2222);
        try {
            when(this.mockPersistenceClient.deleteRow(any(BusinessContactDto.class)))
                 .thenThrow(DatabaseException.class);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("Person contact delete test case failed setting up update call");
        }
        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.deleteContact(mockUpdatePersonDto);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ContactsApiException);
            Assert.assertTrue(e.getCause() instanceof ContactUpdateDaoException);
            e.printStackTrace();
        }
    }
}
