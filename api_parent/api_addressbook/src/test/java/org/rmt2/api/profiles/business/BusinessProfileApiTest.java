/**
 * 
 */
package org.rmt2.api.profiles.business;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.dao.contacts.ContactDaoException;
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

    @Override
    public void setUp() throws Exception {
        APP_NAME = "addressbook";
        super.setUp();
        this.mockSingleProfileFetchResponse = this.createSingleContactFetchResponse();
        this.mockCriteriaProfileFetchResponse = this.createContactFetchUsingCriteriaResponse();
        this.mockAllProfileFetchResponse = this.createContactFetchAllResponse();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    private List<VwBusinessAddress> createSingleContactFetchResponse() {
        List<VwBusinessAddress> list = new ArrayList<VwBusinessAddress>();
        VwBusinessAddress b = new VwBusinessAddress();
        b.setBusinessId(1351);
        b.setBusLongname("Ticket Master");
        b.setBusContactFirstname("roy");
        b.setBusContactLastname("terrell");
        b.setBusContactPhone("9728882222");
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
    public void testSingleBusinessContactFetch() {
        Business bus = new Business();
        // Address addr = new Address();

        bus.setBusinessId(1351);
        // bus.setLongname("Ticket Master");

        // addr.setAddrId(2258);
        // addr.setBusinessId(1351);
        // addr.setPhoneMain("2143738000");

        BusinessContactDto busDto = Rmt2AddressBookDtoFactory.getBusinessInstance(bus, null);
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwBusinessAddress.class)))
                    .thenReturn(this.mockSingleProfileFetchResponse);
        } catch (ContactDaoException e) {

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
    }

}
