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
import org.dao.mapping.orm.rmt2.Person;
import org.dao.mapping.orm.rmt2.VwBusinessAddress;
import org.dao.mapping.orm.rmt2.VwCommonContact;
import org.dto.BusinessContactDto;
import org.dto.ContactDto;
import org.dto.PersonalContactDto;
import org.dto.adapter.orm.Rmt2AddressBookDtoFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.contacts.ContactsApi;
import org.modules.contacts.ContactsApiException;
import org.modules.contacts.ContactsApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.BaseDaoTest;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * @author royterrell
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class })
public class CommonProfileApiTest extends BaseDaoTest {

    private List<VwCommonContact> mockFetchSingleResponse;
    private List<VwCommonContact> mockCriteriaFetchResponse;
    private List<VwCommonContact> mockFetchAllResponse;
    private List<VwCommonContact> mockNotFoundFetchResponse;
    private Person mockPersonObject;
    private Address mockAddressObject;

    @Override
    public void setUp() throws Exception {
        APP_NAME = "addressbook";
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
        List<VwCommonContact> list = new ArrayList<VwCommonContact>();
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

        p = new VwCommonContact();
        p.setContactId(3458);
        p.setContactName("Billy Cobham");
        p.setContactType(ContactsConst.CONTACT_TYPE_PERSONAL);

        p.setAddrId(4444);
        p.setContactId(3458);
        p.setAddr1("3333 Wingstop Ave");
        p.setZipCity("dallas");
        p.setZipState("TX");
        p.setAddrZip(75232);
        p.setAddrZipext(3333);
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
        ContactDto criteria = Rmt2AddressBookDtoFactory.getNewContactInstance();
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwCommonContact.class)))
                    .thenReturn(this.mockFetchAllResponse);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("All personal contact fetch test case failed");
        }

        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(APP_NAME);
        List<ContactDto> results = null;
        try {
            results = api.getContact(criteria);
        } catch (ContactsApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(3, results.size());

        for (ContactDto contact : results) {
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
        ContactsApi api = f.createApi(APP_NAME);
        List<ContactDto> results = null;
        try {
            results = api.getContact(criteria);
        } catch (ContactsApiException e) {
            e.printStackTrace();
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
        ContactsApi api = f.createApi(APP_NAME);
        List<ContactDto> results = null;
        try {
            results = api.getContact(criteria);
        } catch (ContactsApiException e) {
            e.printStackTrace();
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
        ContactsApi api = f.createApi(APP_NAME);
        List<ContactDto> results = null;
        try {
            results = api.getContact(criteria);
        } catch (ContactsApiException e) {
            e.printStackTrace();
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
        ContactsApi api = f.createApi(APP_NAME);
        List<ContactDto> results = null;
        try {
            results = api.getContact(criteria);
        } catch (ContactsApiException e) {
            e.printStackTrace();
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
    public void testNotFoundlFetch() {
        PersonalContactDto busDto = Rmt2AddressBookDtoFactory.getNewPersonInstance();
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwBusinessAddress.class)))
                    .thenReturn(this.mockNotFoundFetchResponse);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("Not Found: person contact fetch test case failed");
        }

        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(APP_NAME);
        List<ContactDto> results = null;
        try {
            results = api.getContact(busDto);
        } catch (ContactsApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(0, results.size());
    }

    @Test
    public void testFetchUsingCriteriaNullResults() {
        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(APP_NAME);
        try {
            api.getContact(null);
            Assert.fail("Expected test case to throw an exception");
        } catch (Exception e) {
            String expectedMsg = "Error retrieving list of contacts using DTO as criteria";
            Assert.assertTrue(e instanceof ContactDaoException);
            Assert.assertEquals(expectedMsg, e.getMessage());
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
        ContactsApi api = f.createApi(APP_NAME);
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
        ContactsApi api = f.createApi(APP_NAME);
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
        ContactsApi api = f.createApi(APP_NAME);
        int rc = 0;
        try {
            rc = api.deleteContact(mockUpdatePersonDto);
        } catch (Exception e) {
            Assert.fail("Person contact delete test case failed");
            e.printStackTrace();
        }
        Assert.assertEquals(2, rc);
    }
}
