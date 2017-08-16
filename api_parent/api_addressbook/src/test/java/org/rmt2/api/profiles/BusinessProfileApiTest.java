/**
 * 
 */
package org.rmt2.api.profiles;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.dao.contacts.BusinessContactQueryDaoException;
import org.dao.contacts.BusinessContactUpdateDaoException;
import org.dao.contacts.ContactDaoException;
import org.dao.contacts.ContactUpdateDaoException;
import org.dao.contacts.ContactsConst;
import org.dao.mapping.orm.rmt2.Address;
import org.dao.mapping.orm.rmt2.Business;
import org.dao.mapping.orm.rmt2.VwBusinessAddress;
import org.dto.BusinessContactDto;
import org.dto.ContactDto;
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
 * Test functionality as it pertains to business contact profiles
 * 
 * @author royterrell
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class })
public class BusinessProfileApiTest extends BaseAddressBookDaoTest {

    private List<VwBusinessAddress> mockFetchSingleResponse;
    private List<VwBusinessAddress> mockCriteriaFetchResponse;
    private List<VwBusinessAddress> mockFetchAllResponse;
    private List<VwBusinessAddress> mockNotFoundFetchResponse;
    private Business mockBusinessObject;
    private Address mockAddressObject;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        this.mockFetchSingleResponse = this.createMockSingleFetchResponse();
        this.mockCriteriaFetchResponse = this.createMockFetchUsingCriteriaResponse();
        this.mockFetchAllResponse = this.createMockFetchAllResponse();
        this.mockNotFoundFetchResponse = this.createMockNotFoundSearchResultsResponse();
        this.mockBusinessObject = this.createBusinessMockObject();
        this.mockAddressObject = this.createAddressMockObject();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    private List<VwBusinessAddress> createMockNotFoundSearchResultsResponse() {
        List<VwBusinessAddress> list = null;
        return list;
    }

    private List<VwBusinessAddress> createMockSingleFetchResponse() {
        List<VwBusinessAddress> list = new ArrayList<VwBusinessAddress>();
        VwBusinessAddress b = new VwBusinessAddress();
        b.setBusinessId(1351);
        b.setBusLongname("Ticket Master");
        b.setBusContactFirstname("roy");
        b.setBusContactLastname("terrell");
        b.setBusContactPhone("9728882222");
        b.setContactEmail("royterrell@gte.net");
        b.setBusServTypeId(130);
        b.setBusEntityTypeId(100);
        b.setBusTaxId("75-9847382");
        b.setBusWebsite("ticketmaster.com");

        b.setAddrId(2222);
        b.setAddrBusinessId(1351);
        b.setAddr1("94393 Hall Ave.");
        b.setAddr2("Suite 948");
        b.setAddr3("P.O. Box 84763");
        b.setZipCity("dallas");
        b.setZipState("TX");
        b.setAddrZip(75028);
        b.setAddrZipext(1234);

        list.add(b);
        return list;
    }

    /**
     * Use for the following selection criteria: where longname begins with 'M'
     * 
     * @return
     */
    private List<VwBusinessAddress> createMockFetchUsingCriteriaResponse() {
        List<VwBusinessAddress> list = new ArrayList<VwBusinessAddress>();
        VwBusinessAddress b = new VwBusinessAddress();
        b.setBusinessId(3333);
        b.setBusLongname("Mondo");
        b.setBusContactFirstname("Julianna");
        b.setBusContactLastname("Young");
        b.setBusContactPhone("2158882222");
        b.setContactEmail("jules@gte.net");
        b.setBusServTypeId(130);
        b.setBusEntityTypeId(100);
        b.setBusTaxId("94-7584738");
        b.setBusWebsite("mondo.com");

        b.setAddrId(1111);
        b.setAddrBusinessId(3333);
        b.setAddr1("8439 Elm St");
        b.setAddr2("Suite 45");
        b.setZipCity("dallas");
        b.setZipState("TX");
        b.setAddrZip(75232);
        list.add(b);

        b = new VwBusinessAddress();
        b.setBusinessId(4444);
        b.setBusLongname("Modis");
        b.setBusContactFirstname("Allison");
        b.setBusContactLastname("Wing");
        b.setBusContactPhone("8175559999");
        b.setContactEmail("allison@gte.net");
        b.setBusServTypeId(130);
        b.setBusEntityTypeId(100);
        b.setBusTaxId("64-8888888");
        b.setBusWebsite("modis.com");

        b.setAddrId(55555);
        b.setAddrBusinessId(4444);
        b.setAddr1("77474 Heist Ave");
        b.setZipCity("Boston");
        b.setZipState("MA");
        b.setAddrZip(65069);
        list.add(b);

        return list;
    }

    private List<VwBusinessAddress> createMockFetchAllResponse() {
        List<VwBusinessAddress> list = new ArrayList<VwBusinessAddress>();
        VwBusinessAddress b = new VwBusinessAddress();
        b.setBusinessId(100);
        b.setBusLongname("BusinessName1");
        b.setBusContactFirstname("BusinessContactFirstName1");
        b.setBusContactLastname("BusinessContactLastName1");
        b.setBusContactPhone("BusinessContactPhone1");
        b.setContactEmail("BusinessContactEmail1");
        b.setBusServTypeId(130);
        b.setBusEntityTypeId(200);
        b.setBusTaxId("BusinessTaxId1");
        b.setBusWebsite("BusinessWebsite1");

        b.setAddrId(10);
        b.setAddrBusinessId(100);
        b.setAddr1("Address1-1");
        b.setAddr2("Address2-1");
        b.setAddr3("Address3-1");
        b.setAddr4("Address4-1");
        b.setZipCity("City1");
        b.setZipState("State1");
        b.setAddrZip(75230);
        
        b.setAddrPhoneFax("FaxPhone1");
        b.setAddrPhoneMain("MainPhone1");
        b.setAddrPhoneWork("WorkPhone1");
        b.setAddrPhoneExt("ExtPhone1");
        list.add(b);

        b = new VwBusinessAddress();
        b.setBusinessId(101);
        b.setBusLongname("BusinessName2");
        b.setBusContactFirstname("BusinessContactFirstName2");
        b.setBusContactLastname("BusinessContactLastName2");
        b.setBusContactPhone("BusinessContactPhone2");
        b.setContactEmail("BusinessContactEmail2");
        b.setBusServTypeId(131);
        b.setBusEntityTypeId(201);
        b.setBusTaxId("BusinessTaxId2");
        b.setBusWebsite("BusinessWebsite2");

        b.setAddrId(11);
        b.setAddrBusinessId(101);
        b.setAddr1("Address1-2");
        b.setAddr2("Address2-2");
        b.setAddr3("Address3-2");
        b.setAddr4("Address4-2");
        b.setZipCity("City2");
        b.setZipState("State2");
        b.setAddrZip(75231);
        
        b.setAddrPhoneFax("FaxPhone2");
        b.setAddrPhoneMain("MainPhone2");
        b.setAddrPhoneWork("WorkPhone2");
        b.setAddrPhoneExt("ExtPhone2");
        list.add(b);

        b = new VwBusinessAddress();
        b.setBusinessId(102);
        b.setBusLongname("BusinessName3");
        b.setBusContactFirstname("BusinessContactFirstName3");
        b.setBusContactLastname("BusinessContactLastName3");
        b.setBusContactPhone("BusinessContactPhone3");
        b.setContactEmail("BusinessContactEmail3");
        b.setBusServTypeId(132);
        b.setBusEntityTypeId(202);
        b.setBusTaxId("BusinessTaxId3");
        b.setBusWebsite("BusinessWebsite3");

        b.setAddrId(12);
        b.setAddrBusinessId(102);
        b.setAddr1("Address1-3");
        b.setAddr2("Address2-3");
        b.setAddr3("Address3-3");
        b.setAddr4("Address4-3");
        b.setZipCity("City3");
        b.setZipState("State3");
        b.setAddrZip(75232);
        
        b.setAddrPhoneFax("FaxPhone3");
        b.setAddrPhoneMain("MainPhone3");
        b.setAddrPhoneWork("WorkPhone3");
        b.setAddrPhoneExt("ExtPhone3");
        list.add(b);

        return list;
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

    private Business createBusinessCriteriaMockObject(int contactId) {
        Business criteria = new Business();
        criteria.addCriteria(Business.PROP_BUSINESSID, contactId);
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
        BusinessContactDto busDto = Rmt2AddressBookDtoFactory.getNewBusinessInstance();
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwBusinessAddress.class)))
                    .thenReturn(this.mockFetchAllResponse);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("All business contact fetch test case failed");
        }

        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ContactDto> results = null;
        try {
            results = api.getContact(busDto);
        } catch (ContactsApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(3, results.size());

        for (int ndx = 0; ndx < results.size(); ndx++) {
            ContactDto contact = results.get(ndx);
            Assert.assertNotNull(contact);
            Assert.assertNotNull(contact.getContactType());
            Assert.assertEquals(ContactsConst.CONTACT_TYPE_BUSINESS, contact.getContactType());
            Assert.assertTrue(contact.getContactId() > 0);
            Assert.assertNotNull(contact.getContactName());

            Assert.assertTrue(contact instanceof BusinessContactDto);
            BusinessContactDto busContact = (BusinessContactDto) contact;
            Assert.assertNotNull(busContact.getContactFirstname());
            Assert.assertNotNull(busContact.getContactLastname());
            Assert.assertNotNull(busContact.getContactEmail());
            
            Assert.assertEquals(busContact.getContactId(), (100 + ndx));
            Assert.assertEquals(busContact.getContactName(), "BusinessName" + (ndx + 1));
            Assert.assertEquals(busContact.getContactFirstname(), "BusinessContactFirstName" + (ndx + 1));
            Assert.assertEquals(busContact.getContactLastname(), "BusinessContactLastName" + (ndx + 1));
            Assert.assertEquals(busContact.getContactPhone(), "BusinessContactPhone" + (ndx + 1));
            Assert.assertEquals(busContact.getContactEmail(), "BusinessContactEmail" + (ndx + 1));
            Assert.assertEquals(busContact.getTaxId(), "BusinessTaxId" + (ndx + 1));
            Assert.assertEquals(busContact.getWebsite(), "BusinessWebsite" + (ndx + 1));
            Assert.assertEquals(busContact.getServTypeId(), (ndx + 130));
            Assert.assertEquals(busContact.getEntityTypeId(), (ndx + 200));
            
            Assert.assertEquals(busContact.getAddrId(), (10 + ndx));
            Assert.assertEquals(busContact.getAddr1(), "Address1-" + (ndx + 1));
            Assert.assertEquals(busContact.getAddr2(), "Address2-" + (ndx + 1));
            Assert.assertEquals(busContact.getAddr3(), "Address3-" + (ndx + 1));
            Assert.assertEquals(busContact.getAddr4(), "Address4-" + (ndx + 1));
            Assert.assertEquals(busContact.getCity(), "City" + (ndx + 1));
            Assert.assertEquals(busContact.getState(), "State" + (ndx + 1));
            Assert.assertEquals(busContact.getZip(), (75230 + ndx));
            
            Assert.assertEquals(contact.getPhoneCompany(), "MainPhone" + (ndx + 1));
            Assert.assertEquals(contact.getPhoneExt(), "ExtPhone" + (ndx + 1));
            Assert.assertEquals(contact.getPhoneFax(), "FaxPhone" + (ndx + 1));
            Assert.assertEquals(contact.getPhoneWork(), "WorkPhone" + (ndx + 1));
            
        }
    }

    @Test
    public void testFetchSingle() {
        Business bus = new Business();
        bus.setBusinessId(1351);
        BusinessContactDto busDto = Rmt2AddressBookDtoFactory.getBusinessInstance(bus, null);
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwBusinessAddress.class)))
                    .thenReturn(this.mockFetchSingleResponse);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("Single business contact fetch test case failed");
        }

        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ContactDto> results = null;
        try {
            results = api.getContact(busDto);
        } catch (ContactsApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        ContactDto contact = results.get(0);
        Assert.assertNotNull(contact.getContactType());
        Assert.assertEquals(ContactsConst.CONTACT_TYPE_BUSINESS, contact.getContactType());
        Assert.assertEquals(1351, contact.getContactId());
        Assert.assertNotNull(contact.getContactName());
        Assert.assertEquals("Ticket Master", contact.getContactName());

        Assert.assertTrue(contact instanceof BusinessContactDto);
        BusinessContactDto busContact = (BusinessContactDto) contact;
        Assert.assertNotNull(busContact.getContactFirstname());
        Assert.assertNotNull(busContact.getContactLastname());
        Assert.assertNotNull(busContact.getContactEmail());

    }

    @Test
    public void testFetchSingleUsingDtoOnly() {
        BusinessContactDto busDto = Rmt2AddressBookDtoFactory.getNewBusinessInstance();
        busDto.setContactId(1351);
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwBusinessAddress.class)))
                    .thenReturn(this.mockFetchSingleResponse);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("Single business contact fetch test case failed");
        }

        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ContactDto> results = null;
        try {
            results = api.getContact(busDto);
        } catch (ContactsApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        ContactDto contact = results.get(0);
        Assert.assertNotNull(contact.getContactType());
        Assert.assertEquals(ContactsConst.CONTACT_TYPE_BUSINESS, contact.getContactType());
        Assert.assertEquals(1351, contact.getContactId());
        Assert.assertNotNull(contact.getContactName());
        Assert.assertEquals("Ticket Master", contact.getContactName());

        Assert.assertTrue(contact instanceof BusinessContactDto);
        BusinessContactDto busContact = (BusinessContactDto) contact;
        Assert.assertNotNull(busContact.getContactFirstname());
        Assert.assertNotNull(busContact.getContactLastname());
        Assert.assertNotNull(busContact.getContactEmail());

    }

    @Test
    public void testFetchUsingCriteria() {
        Business bus = new Business();
        bus.setLongname("M");
        BusinessContactDto busDto = Rmt2AddressBookDtoFactory.getBusinessInstance(bus, null);
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwBusinessAddress.class)))
                    .thenReturn(this.mockCriteriaFetchResponse);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("business contact fetch using specific selection criteria test case failed");
        }

        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ContactDto> results = null;
        try {
            results = api.getContact(busDto);
        } catch (ContactsApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
        for (ContactDto contact : results) {
            Assert.assertNotNull(contact);
            Assert.assertNotNull(contact.getContactType());
            Assert.assertEquals(ContactsConst.CONTACT_TYPE_BUSINESS, contact.getContactType());
            Assert.assertTrue(contact.getContactId() > 0);
            Assert.assertNotNull(contact.getContactName());
            Assert.assertEquals("M", contact.getContactName().substring(0, 1));

            Assert.assertTrue(contact instanceof BusinessContactDto);
            BusinessContactDto busContact = (BusinessContactDto) contact;
            Assert.assertNotNull(busContact.getContactFirstname());
            Assert.assertNotNull(busContact.getContactLastname());
            Assert.assertNotNull(busContact.getContactEmail());
        }
    }

    @Test
    public void testFetchUsingUsingCriteiraDtoOnly() {
        BusinessContactDto busDto = Rmt2AddressBookDtoFactory.getNewBusinessInstance();
        busDto.setContactName("M");
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwBusinessAddress.class)))
                    .thenReturn(this.mockCriteriaFetchResponse);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("business contact fetch using specific selection criteria test case failed");
        }

        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ContactDto> results = null;
        try {
            results = api.getContact(busDto);
        } catch (ContactsApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
        for (ContactDto contact : results) {
            Assert.assertNotNull(contact);
            Assert.assertNotNull(contact.getContactType());
            Assert.assertEquals(ContactsConst.CONTACT_TYPE_BUSINESS, contact.getContactType());
            Assert.assertTrue(contact.getContactId() > 0);
            Assert.assertNotNull(contact.getContactName());
            Assert.assertEquals("M", contact.getContactName().substring(0, 1));

            Assert.assertTrue(contact instanceof BusinessContactDto);
            BusinessContactDto busContact = (BusinessContactDto) contact;
            Assert.assertNotNull(busContact.getContactFirstname());
            Assert.assertNotNull(busContact.getContactLastname());
            Assert.assertNotNull(busContact.getContactEmail());
        }
    }

    @Test
    public void testNotFoundlFetch() {
        BusinessContactDto busDto = Rmt2AddressBookDtoFactory.getNewBusinessInstance();
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwBusinessAddress.class)))
                    .thenReturn(this.mockNotFoundFetchResponse);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("Not Found business contact fetch test case failed");
        }

        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<ContactDto> results = null;
        try {
            results = api.getContact(busDto);
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
        BusinessContactDto mockUpdateBusinessDto = this.createMockBusinessContactDto(1351, 2222);
        try {
            when(this.mockPersistenceClient.retrieveObject(any(Object.class))).thenReturn(this.mockBusinessObject,
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
            rc = api.updateContact(mockUpdateBusinessDto);
        } catch (Exception e) {
            Assert.fail("Business contact update test case failed");
            e.printStackTrace();
        }
        Assert.assertEquals(1, rc);
    }

    @Test
    public void testInsert() {
        BusinessContactDto mockUpdateBusinessDto = this.createMockBusinessContactDto(0, 0);
        try {
            when(this.mockPersistenceClient.insertRow(any(BusinessContactDto.class), any(Boolean.class)))
                    .thenReturn(1351);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("Business contact insert test case failed setting up update call");
        }
        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.updateContact(mockUpdateBusinessDto);
        } catch (Exception e) {
            Assert.fail("Business contact insert test case failed");
            e.printStackTrace();
        }
        Assert.assertEquals(1351, rc);
        Assert.assertEquals(1351, mockUpdateBusinessDto.getContactId());
        Assert.assertEquals(1351, mockUpdateBusinessDto.getAddrId());

    }

    @Test
    public void testDelete() {
        BusinessContactDto mockUpdateBusinessDto = this.createMockBusinessContactDto(1351, 2222);
        try {
            when(this.mockPersistenceClient.deleteRow(any(BusinessContactDto.class))).thenReturn(1);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("Business contact delete test case failed setting up update call");
        }
        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.deleteContact(mockUpdateBusinessDto);
        } catch (Exception e) {
            Assert.fail("Business contact delete test case failed");
            e.printStackTrace();
        }
        Assert.assertEquals(2, rc);
    }
    
    @Test
    public void testFetchDaoException() {
        BusinessContactDto busDto = Rmt2AddressBookDtoFactory.getNewBusinessInstance();
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwBusinessAddress.class)))
                       .thenThrow(DatabaseException.class);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("All business contact fetch DAO Exception test case failed");
        }

        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.getContact(busDto);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ContactsApiException);
            Assert.assertTrue(e.getCause() instanceof BusinessContactQueryDaoException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testInsertDaoException() {
        BusinessContactDto mockUpdateBusinessDto = this.createMockBusinessContactDto(0, 0);
        try {
            when(this.mockPersistenceClient.insertRow(any(BusinessContactDto.class), any(Boolean.class)))
                   .thenThrow(DatabaseException.class);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("Business contact insert test case failed setting up update call");
        }
        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.updateContact(mockUpdateBusinessDto);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ContactsApiException);
            Assert.assertTrue(e.getCause() instanceof BusinessContactUpdateDaoException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUpdateDaoException() {
        BusinessContactDto mockUpdateBusinessDto = this.createMockBusinessContactDto(1351, 2222);
        try {
            when(this.mockPersistenceClient.retrieveObject(any(Object.class))).thenReturn(this.mockBusinessObject,
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
            api.updateContact(mockUpdateBusinessDto);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ContactsApiException);
            Assert.assertTrue(e.getCause() instanceof BusinessContactUpdateDaoException);
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteDaoException() {
        BusinessContactDto mockUpdateBusinessDto = this.createMockBusinessContactDto(1351, 2222);
        try {
            when(this.mockPersistenceClient.deleteRow(any(BusinessContactDto.class)))
                  .thenThrow(DatabaseException.class);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("Business contact delete test case failed setting up update call");
        }
        ContactsApiFactory f = new ContactsApiFactory();
        ContactsApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.deleteContact(mockUpdateBusinessDto);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ContactsApiException);
            Assert.assertTrue(e.getCause() instanceof ContactUpdateDaoException);
            e.printStackTrace();
        }
    }
}
