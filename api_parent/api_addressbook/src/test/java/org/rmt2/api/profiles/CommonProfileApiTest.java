/**
 * 
 */
package org.rmt2.api.profiles;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.dao.contacts.ContactDaoException;
import org.dao.contacts.ContactsConst;
import org.dao.mapping.orm.rmt2.Address;
import org.dao.mapping.orm.rmt2.Business;
import org.dao.mapping.orm.rmt2.Person;
import org.dao.mapping.orm.rmt2.VwCommonContact;
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

import com.InvalidDataException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Test functionality as it pertains to common contact profiles
 * 
 * @author royterrell
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class })
public class CommonProfileApiTest extends BaseAddressBookDaoTest {

    private List<VwCommonContact> mockFetchSingleResponse;
    private List<VwCommonContact> mockCriteriaFetchResponse;
    private List<VwCommonContact> mockFetchAllResponse;
    private List<VwCommonContact> mockNotFoundFetchResponse;
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

    private List<VwCommonContact> createMockNotFoundSearchResultsResponse() {
        List<VwCommonContact> list = null;
        return list;
    }

    private List<VwCommonContact> createMockSingleFetchResponse() {
        List<VwCommonContact> list = new ArrayList<VwCommonContact>();
        VwCommonContact p = new VwCommonContact();
        p.setContactId(1351);
        p.setContactName("Dennis Chambers");
        p.setContactType(ContactsConst.CONTACT_TYPE_PERSONAL);

        p.setAddrId(2222);
        p.setContactId(1351);
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
     * Use for the following selection criteria: where contact name begins with
     * 'D'
     * 
     * @return
     */
    private List<VwCommonContact> createMockFetchUsingCriteriaResponse() {
        List<VwCommonContact> list = new ArrayList<VwCommonContact>();
        VwCommonContact p = new VwCommonContact();
        p.setContactId(1351);
        p.setContactName("Dennis Chambers");
        p.setContactType(ContactsConst.CONTACT_TYPE_PERSONAL);

        p.setAddrId(2222);
        p.setContactId(1351);
        p.setAddr1("94393 Hall Ave.");
        p.setZipCity("dallas");
        p.setZipState("TX");
        p.setAddrZip(75028);
        p.setAddrZipext(1234);
        list.add(p);

        p = new VwCommonContact();
        p.setContactId(2045);
        p.setContactName("DQ Conenient Store");
        p.setContactType(ContactsConst.CONTACT_TYPE_BUSINESS);

        p.setAddrId(3333);
        p.setContactId(2045);
        p.setAddr1("6473 Lemmon Ave");
        p.setAddr2("Building 847");
        p.setAddr3("Room 947");
        p.setZipCity("dallas");
        p.setZipState("TX");
        p.setAddrZip(75240);
        p.setAddrZipext(9876);
        list.add(p);

        return list;
    }

    private List<VwCommonContact> createMockFetchAllResponse() {
        List<VwCommonContact> list = new ArrayList<VwCommonContact>();
        VwCommonContact p = new VwCommonContact();
        p.setContactId(100);
        p.setContactName("ContactName1");
        p.setContactType(ContactsConst.CONTACT_TYPE_PERSONAL);

        p.setAddrId(1000);
        p.setContactId(100);
        p.setAddr1("Address1-1");
        p.setAddr2("Address2-1");
        p.setAddr3("Address3-1");
        p.setAddr4("Address4-1");
        p.setZipCity("City1");
        p.setZipState("State1");
        p.setAddrZip(75020);
        p.setAddrZipext(1231);
        p.setAddrPhoneCell("CellPhone1");
        p.setAddrPhoneFax("FaxPhone1");
        p.setAddrPhoneHome("HomePhone1");
        p.setAddrPhoneMain("MainPhone1");
        p.setAddrPhonePager("PagerPhone1");
        p.setAddrPhoneWork("WorkPhone1");
        p.setAddrPhoneExt("ExtPhone1");
        list.add(p);

        p = new VwCommonContact();
        p.setContactId(101);
        p.setContactName("ContactName2");
        p.setContactType(ContactsConst.CONTACT_TYPE_BUSINESS);

        p.setAddrId(1001);
        p.setContactId(101);
        p.setAddr1("Address1-2");
        p.setAddr2("Address2-2");
        p.setAddr3("Address3-2");
        p.setAddr4("Address4-2");
        p.setZipCity("City2");
        p.setZipState("State2");
        p.setAddrZip(75021);
        p.setAddrZipext(1232);
        p.setAddrPhoneCell("CellPhone2");
        p.setAddrPhoneFax("FaxPhone2");
        p.setAddrPhoneHome("HomePhone2");
        p.setAddrPhoneMain("MainPhone2");
        p.setAddrPhonePager("PagerPhone2");
        p.setAddrPhoneWork("WorkPhone2");
        p.setAddrPhoneExt("ExtPhone2");
        list.add(p);

        p = new VwCommonContact();
        p.setContactId(102);
        p.setContactName("ContactName3");
        p.setContactType(ContactsConst.CONTACT_TYPE_PERSONAL);

        p.setAddrId(1002);
        p.setContactId(102);
        p.setAddr1("Address1-3");
        p.setAddr2("Address2-3");
        p.setAddr3("Address3-3");
        p.setAddr4("Address4-3");
        p.setZipCity("City3");
        p.setZipState("State3");
        p.setAddrZip(75022);
        p.setAddrZipext(1233);
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

    private PersonalContactDto createMockPersonalContactDto(int contactId, int addressId) {
        Person p = this.createSingleMockOrmObject();
        Address a = this.createAddressMockObject();
        p.setPersonId(contactId);
        a.setAddrId(addressId);
        PersonalContactDto perDto = Rmt2AddressBookDtoFactory.getPersonInstance(p, a);
        return perDto;
    }
    
    private Business createBusinessMockObject() {
        Business b = new Business();
        b.setBusinessId(1351);
        b.setLongname("Ticket Master");
        b.setContactFirstname("roy");
        b.setContactLastname("terrell");
        b.setContactPhone("9728882222");
        b.setContactEmail("royterrell@gte.net");
        b.setServTypeId(130);
        b.setEntityTypeId(100);
        b.setTaxId("75-9847382");
        b.setWebsite("ticketmaster.com");
        return b;
    }
    
    private BusinessContactDto createMockBusinessContactDto(int contactId, int addressId) {
        Business b = this.createBusinessMockObject();
        b.setBusinessId(contactId);
        b.setContactFirstname("Dennis");
        b.setContactLastname("Chambers");
        b.setContactPhone("8159999999");
        b.setContactEmail("dennischambers@gmail.com");

        Address a = this.createAddressMockObject();
        a.setAddrId(addressId);
        a.setBusinessId(contactId);
        a.setAddr1("2300 Flora Dr");
        a.setAddr2("Suite 100");
        a.setAddr3("P.O. Box 100");
        a.setZip(75232);
        a.setZipext(9999);

        BusinessContactDto busDto = Rmt2AddressBookDtoFactory.getBusinessInstance(b, a);
        return busDto;
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
        ContactDto criteria = Rmt2AddressBookDtoFactory.getNewContactInstance();
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwCommonContact.class)))
                    .thenReturn(this.mockFetchAllResponse);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("All personal contact fetch test case failed");
        }

        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ContactDto> results = null;
        try {
            results = api.getContact(criteria);
        } catch (ContactsApiException e) {
            e.printStackTrace();
            Assert.fail("An unexpected exception was thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(3, results.size());

        for (int ndx = 0; ndx < results.size(); ndx++) {
            ContactDto contact = results.get(ndx);
            Assert.assertNotNull(contact);
            Assert.assertNotNull(contact.getContactType());
            Assert.assertTrue(contact.getContactType().equalsIgnoreCase(ContactsConst.CONTACT_TYPE_PERSONAL)
                    || contact.getContactType().equalsIgnoreCase(ContactsConst.CONTACT_TYPE_BUSINESS));
            Assert.assertTrue(contact.getContactId() > 0);
            Assert.assertNotNull(contact.getContactName());

            Assert.assertTrue(contact instanceof ContactDto);
            Assert.assertNotNull(contact.getContactName());
            Assert.assertNotNull(contact.getCity());
            Assert.assertNotNull(contact.getState());
            
            
            Assert.assertEquals(contact.getContactId(), (100 + ndx));
            Assert.assertEquals(contact.getContactName(), "ContactName" + (ndx + 1));
            
            Assert.assertEquals(contact.getAddrId(), (1000 + ndx));
            Assert.assertEquals(contact.getAddr1(), "Address1-" + (ndx + 1));
            Assert.assertEquals(contact.getAddr2(), "Address2-" + (ndx + 1));
            Assert.assertEquals(contact.getAddr3(), "Address3-" + (ndx + 1));
            Assert.assertEquals(contact.getAddr4(), "Address4-" + (ndx + 1));
            Assert.assertEquals(contact.getCity(), "City" + (ndx + 1));
            Assert.assertEquals(contact.getState(), "State" + (ndx + 1));
            Assert.assertEquals(contact.getZip(), (75020 + ndx));
            
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
        VwCommonContact commonContact = new VwCommonContact();
        commonContact.setContactId(1351);
        ContactDto criteria = Rmt2AddressBookDtoFactory.getContactInstance(commonContact);
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwCommonContact.class)))
                    .thenReturn(this.mockFetchSingleResponse);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("Single person contact fetch test case failed");
        }

        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ContactDto> results = null;
        try {
            results = api.getContact(criteria);
        } catch (ContactsApiException e) {
            e.printStackTrace();
            Assert.fail("An unexpected exception was thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        ContactDto contact = results.get(0);
        Assert.assertNotNull(contact.getContactType());
        Assert.assertTrue(contact.getContactType().equalsIgnoreCase(ContactsConst.CONTACT_TYPE_PERSONAL)
                || contact.getContactType().equalsIgnoreCase(ContactsConst.CONTACT_TYPE_BUSINESS));
        Assert.assertEquals(1351, contact.getContactId());
        Assert.assertNotNull(contact.getContactName());
        Assert.assertEquals("Dennis Chambers", contact.getContactName());

        Assert.assertTrue(contact instanceof ContactDto);
        Assert.assertNotNull(contact.getContactName());
        Assert.assertNotNull(contact.getCity());
        Assert.assertNotNull(contact.getState());

    }

    @Test
    public void testFetchUsingCriteria() {
        VwCommonContact commonContact = new VwCommonContact();
        commonContact.setContactName("M");
        ContactDto criteria = Rmt2AddressBookDtoFactory.getContactInstance(commonContact);
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwCommonContact.class)))
                    .thenReturn(this.mockCriteriaFetchResponse);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("person contact fetch using specific selection criteria test case failed");
        }

        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ContactDto> results = null;
        try {
            results = api.getContact(criteria);
        } catch (ContactsApiException e) {
            e.printStackTrace();
            Assert.fail("An unexpected exception was thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
        for (ContactDto contact : results) {
            Assert.assertNotNull(contact);
            Assert.assertNotNull(contact.getContactType());
            Assert.assertTrue(contact.getContactType().equalsIgnoreCase(ContactsConst.CONTACT_TYPE_PERSONAL)
                    || contact.getContactType().equalsIgnoreCase(ContactsConst.CONTACT_TYPE_BUSINESS));
            Assert.assertTrue(contact.getContactId() > 0);
            Assert.assertNotNull(contact.getContactName());
            String name[] = contact.getContactName().split(" ");
            Assert.assertTrue(name.length > 1);
            Assert.assertEquals("D", name[0].substring(0, 1));

            Assert.assertTrue(contact instanceof ContactDto);
            Assert.assertNotNull(contact.getContactName());
            Assert.assertNotNull(contact.getCity());
            Assert.assertNotNull(contact.getState());
        }
    }

    @Test
    public void testFetchSingleUsingDtoOnly() {
        ContactDto criteria = Rmt2AddressBookDtoFactory.getNewContactInstance();
        criteria.setContactId(1351);
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwCommonContact.class)))
                    .thenReturn(this.mockFetchSingleResponse);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("Single person contact fetch test case failed");
        }

        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ContactDto> results = null;
        try {
            results = api.getContact(criteria);
        } catch (ContactsApiException e) {
            e.printStackTrace();
            Assert.fail("An unexpected exception was thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        ContactDto contact = results.get(0);
        Assert.assertNotNull(contact.getContactType());
        Assert.assertTrue(contact.getContactType().equalsIgnoreCase(ContactsConst.CONTACT_TYPE_PERSONAL)
                || contact.getContactType().equalsIgnoreCase(ContactsConst.CONTACT_TYPE_BUSINESS));
        Assert.assertEquals(1351, contact.getContactId());
        Assert.assertNotNull(contact.getContactName());
        Assert.assertEquals("Dennis Chambers", contact.getContactName());

        Assert.assertTrue(contact instanceof ContactDto);
        Assert.assertNotNull(contact.getContactName());
        Assert.assertNotNull(contact.getCity());
        Assert.assertNotNull(contact.getState());

    }

    @Test
    public void testFetchUsingUsingCriteiraDtoOnly() {
        ContactDto criteria = Rmt2AddressBookDtoFactory.getNewContactInstance();
        criteria.setContactName("M");
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwCommonContact.class)))
                    .thenReturn(this.mockCriteriaFetchResponse);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("person contact fetch using specific selection criteria test case failed");
        }

        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ContactDto> results = null;
        try {
            results = api.getContact(criteria);
        } catch (ContactsApiException e) {
            e.printStackTrace();
            Assert.fail("An unexpected exception was thrown");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
        for (ContactDto contact : results) {
            Assert.assertNotNull(contact);
            Assert.assertNotNull(contact.getContactType());
            Assert.assertTrue(contact.getContactType().equalsIgnoreCase(ContactsConst.CONTACT_TYPE_PERSONAL)
                    || contact.getContactType().equalsIgnoreCase(ContactsConst.CONTACT_TYPE_BUSINESS));
            Assert.assertTrue(contact.getContactId() > 0);
            Assert.assertNotNull(contact.getContactName());
            String name[] = contact.getContactName().split(" ");
            Assert.assertTrue(name.length > 1);
            Assert.assertEquals("D", name[0].substring(0, 1));

            Assert.assertTrue(contact instanceof ContactDto);
            Assert.assertNotNull(contact.getContactName());
            Assert.assertNotNull(contact.getCity());
            Assert.assertNotNull(contact.getState());
        }
    }

    @Test
    public void testFetchDaoException() {
        ContactDto criteria = Rmt2AddressBookDtoFactory.getNewContactInstance();
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwCommonContact.class)))
                  .thenThrow(DatabaseException.class);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("All personal contact fetch test case failed");
        }

        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.getContact(criteria);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof ContactsApiException);
            Assert.assertTrue(e.getCause() instanceof ContactDaoException);
        }
    }
    
    @Test
    public void testNotFoundlFetch() {
        ContactDto criteria = Rmt2AddressBookDtoFactory.getNewContactInstance();
        criteria.setContactName("M");
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwCommonContact.class)))
                    .thenReturn(null);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("common contact fetch using specific selection criteria test case failed");
        }

        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ContactDto> results = null;
        try {
            results = api.getContact(criteria);
        } catch (ContactsApiException e) {
            e.printStackTrace();
            Assert.fail("An unexpected exception was thrown");
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
            e.printStackTrace();
            Assert.assertTrue(e instanceof ContactsApiException);
        }
    }

    @Test
    public void testUpdate() {
        // Chose to test common functionality with a person type contact.
        ContactDto mockCommonContactDto = this.createMockPersonalContactDto(1351, 2222);
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
            rc = api.updateContact(mockCommonContactDto);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An unexpected exception was thrown");
        }
        Assert.assertEquals(1, rc);
    }

    @Test
    public void testInsert() {
        // Chose to test common functionality with a person type contact.
        ContactDto mockCommonContactDto = this.createMockPersonalContactDto(0, 0);
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
            rc = api.updateContact(mockCommonContactDto);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An unexpected exception was thrown");
        }
        Assert.assertEquals(1351, rc);
        Assert.assertEquals(1351, mockCommonContactDto.getContactId());
        Assert.assertEquals(1351, mockCommonContactDto.getAddrId());

    }

    @Test
    public void testDelete() {
        // Chose to test common functionality with a person type contact.
        ContactDto mockCommonContactDto = this.createMockPersonalContactDto(1351, 2222);
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
            rc = api.deleteContact(mockCommonContactDto);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Common contact delete test case failed");
        }
        Assert.assertEquals(2, rc);
    }
    
   
    
    @Test
    public void testInsertDaoException() {
        // Chose to test common functionality with a person type contact.
        ContactDto mockCommonContactDto = this.createMockPersonalContactDto(0, 0);
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
            api.updateContact(mockCommonContactDto);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof ContactsApiException);
            Assert.assertTrue(e.getCause() instanceof ContactDaoException);
            
        }
    }
    
    @Test
    public void testUpdateDaoException() {
        // Chose to test common functionality with a person type contact.
        ContactDto mockCommonContactDto = this.createMockPersonalContactDto(1351, 2222);
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
        try {
            api.updateContact(mockCommonContactDto);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof ContactsApiException);
            Assert.assertTrue(e.getCause() instanceof ContactDaoException);
        }
    }
    
    @Test
    public void testDeleteDaoException() {
        // Chose to test common functionality with a person type contact.
        ContactDto mockCommonContactDto = this.createMockPersonalContactDto(1351, 2222);
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
            api.deleteContact(mockCommonContactDto);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof ContactsApiException);
            Assert.assertTrue(e.getCause() instanceof ContactDaoException);
        }
    }
    
    @Test
    public void testDelete_ValidationError_ContactDto_Null() {
        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.deleteContact(null);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(
                    "A Contact crtieria instance is required as an input parameter when deleting a contact",
                    e.getMessage());
        }
    }
    
    @Test
    public void testDelete_ValidationError_ContactDto_ContactId_Negative() {
        // Chose to test common functionality with a person type contact.
        ContactDto mockCommonContactDto = this.createMockPersonalContactDto(1351, 2222);
        try {
            when(this.mockPersistenceClient.deleteRow(any(BusinessContactDto.class)))
                  .thenThrow(DatabaseException.class);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("Common contact delete test case failed setting up update call");
        }
        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            mockCommonContactDto.setContactId(-1234);
            api.deleteContact(mockCommonContactDto);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(
                    "A valid Contact Id is required when deleting a contact from the database",
                    e.getMessage());
        }
    }
    
    @Test
    public void testDelete_ValidationError_ContactDto_ContactId_Zero() {
        ContactDto mockCommonContactDto = this.createMockPersonalContactDto(1351, 2222);
        try {
            when(this.mockPersistenceClient.deleteRow(any(BusinessContactDto.class)))
                  .thenThrow(DatabaseException.class);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("Personal contact delete test case failed setting up update call");
        }
        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            mockCommonContactDto.setContactId(0);
            api.deleteContact(mockCommonContactDto);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(
                    "A valid Contact Id is required when deleting a contact from the database",
                    e.getMessage());
        }
    }
    
    
    // TEst business DTO
    BusinessContactDto mockUpdateBusinessDto = this.createMockBusinessContactDto(1351, 2222);
    @Test
    public void testUpdate_With_Business_DTO() {
        // Chose to test common functionality with a person type contact.
        ContactDto mockCommonContactDto = this.createMockBusinessContactDto(1351, 2222);
        try {
            Business mockBusinessObject = this.createBusinessMockObject();
            when(this.mockPersistenceClient.retrieveObject(any(Object.class))).thenReturn(mockBusinessObject,
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
            rc = api.updateContact(mockCommonContactDto);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Business contact update test case failed");
        }
        Assert.assertEquals(1, rc);
    }

    @Test
    public void testUpdate_Validation_Contact_Data_Object_Null() {
        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.updateContact(null);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals("A Contact data object is required as an input parameter for update operation", e.getMessage());
        }
    }
    
    @Test
    public void testInsert_With_Business_DTO() {
        // Chose to test common functionality with a person type contact.
        ContactDto mockCommonContactDto = this.createMockBusinessContactDto(0, 0);
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
            rc = api.updateContact(mockCommonContactDto);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An unexpected exception was thrown");
        }
        Assert.assertEquals(1351, rc);
        Assert.assertEquals(1351, mockCommonContactDto.getContactId());
    }

    @Test
    public void testDelete_With_Business_DTO() {
        // Chose to test common functionality with a person type contact.
        ContactDto mockCommonContactDto = this.createMockBusinessContactDto(1351, 2222);
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
            rc = api.deleteContact(mockCommonContactDto);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Common contact delete test case failed");
        }
        Assert.assertEquals(2, rc);
    }
    
   
    
    @Test
    public void testInsertDaoException_With_Business_DTO() {
        // Chose to test common functionality with a person type contact.
        ContactDto mockCommonContactDto = this.createMockBusinessContactDto(0, 0);
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
            api.updateContact(mockCommonContactDto);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof ContactsApiException);
            Assert.assertTrue(e.getCause() instanceof ContactDaoException);
        }
    }
    
    @Test
    public void testUpdateDaoException_With_Business_DTO() {
        // Chose to test common functionality with a person type contact.
        ContactDto mockCommonContactDto = this.createMockBusinessContactDto(1351, 2222);
        try {
            Business mockBusinessObject = this.createBusinessMockObject();
            when(this.mockPersistenceClient.retrieveObject(any(Object.class))).thenReturn(mockBusinessObject,
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
        try {
            api.updateContact(mockCommonContactDto);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof ContactsApiException);
            Assert.assertTrue(e.getCause() instanceof ContactDaoException);
        }
    }
    
    @Test
    public void testDeleteDaoException_With_Business_DTO() {
        // Chose to test common functionality with a person type contact.
        ContactDto mockCommonContactDto = this.createMockBusinessContactDto(1351, 2222);
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
            api.deleteContact(mockCommonContactDto);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof ContactsApiException);
            Assert.assertTrue(e.getCause() instanceof ContactDaoException);
        }
    }
    
    @Test
    public void testDelete_ValidationError_ContactDto_ContactId_Negative_With_Business_DTO() {
        // Chose to test common functionality with a person type contact.
        ContactDto mockCommonContactDto = this.createMockBusinessContactDto(1351, 2222);
        try {
            when(this.mockPersistenceClient.deleteRow(any(BusinessContactDto.class)))
                  .thenThrow(DatabaseException.class);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("Common contact delete test case failed setting up update call");
        }
        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            mockCommonContactDto.setContactId(-1234);
            api.deleteContact(mockCommonContactDto);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(
                    "A valid Contact Id is required when deleting a contact from the database",
                    e.getMessage());
        }
    }
    
    @Test
    public void testDelete_ValidationError_ContactDto_ContactId_Zero_With_Business_DTO() {
        ContactDto mockCommonContactDto = this.createMockBusinessContactDto(1351, 2222);
        try {
            when(this.mockPersistenceClient.deleteRow(any(BusinessContactDto.class)))
                  .thenThrow(DatabaseException.class);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("Personal contact delete test case failed setting up update call");
        }
        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            mockCommonContactDto.setContactId(0);
            api.deleteContact(mockCommonContactDto);
            Assert.fail("An exception was expected to be thrown");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidDataException);
            Assert.assertEquals(
                    "A valid Contact Id is required when deleting a contact from the database",
                    e.getMessage());
        }
    }
}
