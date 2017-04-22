/**
 * 
 */
package org.rmt2.api.profiles.business;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.dao.contacts.ContactDaoException;
import org.dao.contacts.ContactsConst;
import org.dao.mapping.orm.rmt2.Business;
import org.dao.mapping.orm.rmt2.VwBusinessAddress;
import org.dto.BusinessContactDto;
import org.dto.ContactDto;
import org.dto.adapter.orm.Rmt2AddressBookDtoFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.contacts.ContactsApi;
import org.modules.contacts.ContactsApiException;
import org.modules.contacts.ContactsApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.BaseDaoTest;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * @author royterrell
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class })
public class BusinessProfileApiTest extends BaseDaoTest {

    private List<VwBusinessAddress> mockSingleProfileFetchResponse;
    private List<VwBusinessAddress> mockCriteriaProfileFetchResponse;
    private List<VwBusinessAddress> mockAllProfileFetchResponse;
    private List<VwBusinessAddress> mockNotFoundFetchResponse;

    @Override
    public void setUp() throws Exception {
        APP_NAME = "addressbook";
        super.setUp();
        this.mockSingleProfileFetchResponse = this.createSingleContactFetchResponse();
        this.mockCriteriaProfileFetchResponse = this.createContactFetchUsingCriteriaResponse();
        this.mockAllProfileFetchResponse = this.createContactFetchAllResponse();
        this.mockNotFoundFetchResponse = this.createNotFoundSearchResultsResponse();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    private List<VwBusinessAddress> createNotFoundSearchResultsResponse() {
        List<VwBusinessAddress> list = new ArrayList<VwBusinessAddress>();
        return list;
    }

    private List<VwBusinessAddress> createSingleContactFetchResponse() {
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
    private List<VwBusinessAddress> createContactFetchUsingCriteriaResponse() {
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

    private List<VwBusinessAddress> createContactFetchAllResponse() {
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

        b = new VwBusinessAddress();
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

    @Test
    public void testAllBusinessContactFetch() {
        BusinessContactDto busDto = Rmt2AddressBookDtoFactory.getNewBusinessInstance();
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwBusinessAddress.class)))
                    .thenReturn(this.mockAllProfileFetchResponse);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("All business contact fetch test case failed");
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
        Assert.assertEquals(3, results.size());

        for (ContactDto contact : results) {
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
        }
    }

    @Test
    public void testSingleBusinessContactFetch() {
        Business bus = new Business();
        bus.setBusinessId(1351);
        BusinessContactDto busDto = Rmt2AddressBookDtoFactory.getBusinessInstance(bus, null);
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwBusinessAddress.class)))
                    .thenReturn(this.mockSingleProfileFetchResponse);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("Single business contact fetch test case failed");
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
    public void testBusinessContactCriteriaFetch() {
        Business bus = new Business();
        bus.setLongname("M");
        BusinessContactDto busDto = Rmt2AddressBookDtoFactory.getBusinessInstance(bus, null);
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwBusinessAddress.class)))
                    .thenReturn(this.mockCriteriaProfileFetchResponse);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("business contact fetch using specific selection criteria test case failed");
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
    public void testNotFoundlBusinessContactFetch() {
        BusinessContactDto busDto = Rmt2AddressBookDtoFactory.getNewBusinessInstance();
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwBusinessAddress.class)))
                    .thenReturn(this.mockNotFoundFetchResponse);
        } catch (ContactDaoException e) {
            e.printStackTrace();
            Assert.fail("Not Found business contact fetch test case failed");
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
    public void testNullCriteriaBusinessContactFetch() {
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
}
