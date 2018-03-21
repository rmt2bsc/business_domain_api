package org.rmt2.entity.adapter;

import org.dao.contacts.ContactsConst;
import org.dao.mapping.orm.rmt2.Address;
import org.dao.mapping.orm.rmt2.VwBusinessAddress;
import org.dao.mapping.orm.rmt2.VwCommonContact;
import org.dao.mapping.orm.rmt2.VwPersonAddress;
import org.dto.AddressDto;
import org.dto.BusinessContactDto;
import org.dto.ContactDto;
import org.dto.PersonalContactDto;
import org.dto.adapter.orm.Rmt2AddressBookDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test GL Account related adapters
 * 
 * @author roy.terrell
 *
 */
public class AddressAdapterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    private Address createAddressMockObject() {
        Address a = new Address();
        a.setAddrId(2222);
        a.setBusinessId(1351);
        a.setAddr1("94393 Hall Ave.");
        a.setAddr2("Suite 948");
        a.setAddr3("Room 4");
        a.setAddr4("P.O. Box 84763");
        a.setZip(75028);
        a.setZipext(1234);
        return a;
    }
    
    private VwCommonContact createCommonContactAddressMockObject() {
        VwCommonContact a = new VwCommonContact();
        a.setContactId(1351);
        a.setContactName("Dennis Chambers");
        a.setContactType(ContactsConst.CONTACT_TYPE_PERSONAL);

        a.setAddrId(2222);
        a.setContactId(1351);
        a.setAddr1("94393 Hall Ave.");
        a.setAddr2("Suite 948");
        a.setAddr3("Room 4");
        a.setAddr4("P.O. Box 84763");
        a.setZipCity("dallas");
        a.setZipState("TX");
        a.setAddrZip(75028);
        a.setAddrZipext(1234);

        return a;
    }
    
    private VwBusinessAddress createBusinessAddressMockObject() {
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
        b.setAddr3("Room 4");
        b.setAddr4("P.O. Box 84763");
        b.setZipCity("dallas");
        b.setZipState("TX");
        b.setAddrZip(75028);
        b.setAddrZipext(1234);

        return b;
    }
    
    private VwPersonAddress createPersonAddressMockObject() {
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
        p.setAddr2("Suite 948");
        p.setAddr3("Room 4");
        p.setAddr4("P.O. Box 84763");
        p.setZipCity("dallas");
        p.setZipState("TX");
        p.setAddrZip(75028);
        p.setAddrZipext(1234);
        return p;
    }
    
    @Test
    public void testAddresAdapter() {
        Address addr = this.createAddressMockObject();
        AddressDto dto = Rmt2AddressBookDtoFactory.getAddressDtoInstance(addr);
        
        Assert.assertEquals(2222, dto.getAddrId());
        Assert.assertEquals("94393 Hall Ave.", dto.getAddr1());
        Assert.assertEquals("Suite 948", dto.getAddr2());
        Assert.assertEquals("Room 4", dto.getAddr3());
        Assert.assertEquals("P.O. Box 84763", dto.getAddr4());
        Assert.assertEquals(75028, dto.getZip());
        Assert.assertEquals(1234, dto.getZipext());

        try {
            dto = Rmt2AddressBookDtoFactory.getAddressDtoInstance(null);
            dto.setAddrId(2222);
            dto.setAddr1("94393 Hall Ave.");
            dto.setAddr2("Suite 948");
            dto.setAddr3("Room 4");
            dto.setAddr4("P.O. Box 84763");
            dto.setZip(75028);
            dto.setZipext(1234);

            Assert.assertEquals(2222, dto.getAddrId());
            Assert.assertEquals("94393 Hall Ave.", dto.getAddr1());
            Assert.assertEquals("Suite 948", dto.getAddr2());
            Assert.assertEquals("Room 4", dto.getAddr3());
            Assert.assertEquals("P.O. Box 84763", dto.getAddr4());
            Assert.assertEquals(75028, dto.getZip());
            Assert.assertEquals(1234, dto.getZipext());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for AddressDto Adapater");
        }

    }
    
    @Test
    public void testCommonContactAddressAdapter() {
        VwCommonContact o = this.createCommonContactAddressMockObject(); 
        ContactDto dto = Rmt2AddressBookDtoFactory.getContactInstance(o);
        
        Assert.assertEquals(2222, dto.getAddrId());
        Assert.assertEquals("94393 Hall Ave.", dto.getAddr1());
        Assert.assertEquals("Suite 948", dto.getAddr2());
        Assert.assertEquals("Room 4", dto.getAddr3());
        Assert.assertEquals("P.O. Box 84763", dto.getAddr4());
        Assert.assertEquals(75028, dto.getZip());
        Assert.assertEquals(1234, dto.getZipext());
        Assert.assertEquals("dallas", dto.getCity());
        Assert.assertEquals("TX", dto.getState());
        
        try {
            dto = Rmt2AddressBookDtoFactory.getContactInstance(null);
            dto.setAddrId(2222);
            dto.setAddr1("94393 Hall Ave.");
            dto.setAddr2("Suite 948");
            dto.setAddr3("Room 4");
            dto.setAddr4("P.O. Box 84763");
            dto.setZip(75028);
            dto.setZipext(1234);
            dto.setCity("dallas");
            dto.setState("TX");

            Assert.assertEquals(2222, dto.getAddrId());
            Assert.assertEquals("94393 Hall Ave.", dto.getAddr1());
            Assert.assertEquals("Suite 948", dto.getAddr2());
            Assert.assertEquals("Room 4", dto.getAddr3());
            Assert.assertEquals("P.O. Box 84763", dto.getAddr4());
            Assert.assertEquals(75028, dto.getZip());
            Assert.assertEquals(1234, dto.getZipext());
            Assert.assertEquals("dallas", dto.getCity());
            Assert.assertEquals("TX", dto.getState());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for ContactDto Adapater");
        }
    }
    
    
    @Test
    public void testBusinessAddressAdapter() {
        VwBusinessAddress o = this.createBusinessAddressMockObject();
        BusinessContactDto dto = Rmt2AddressBookDtoFactory.getBusinessInstance(o);
        
        Assert.assertEquals(2222, dto.getAddrId());
        Assert.assertEquals("94393 Hall Ave.", dto.getAddr1());
        Assert.assertEquals("Suite 948", dto.getAddr2());
        Assert.assertEquals("Room 4", dto.getAddr3());
        Assert.assertEquals("P.O. Box 84763", dto.getAddr4());
        Assert.assertEquals(75028, dto.getZip());
        Assert.assertEquals(1234, dto.getZipext());
        Assert.assertEquals("dallas", dto.getCity());
        Assert.assertEquals("TX", dto.getState());
        
        try {
            dto = Rmt2AddressBookDtoFactory.getBusinessInstance(null);
            dto.setAddrId(2222);
            dto.setAddr1("94393 Hall Ave.");
            dto.setAddr2("Suite 948");
            dto.setAddr3("Room 4");
            dto.setAddr4("P.O. Box 84763");
            dto.setZip(75028);
            dto.setZipext(1234);
            dto.setCity("dallas");
            dto.setState("TX");

            Assert.assertEquals(2222, dto.getAddrId());
            Assert.assertEquals("94393 Hall Ave.", dto.getAddr1());
            Assert.assertEquals("Suite 948", dto.getAddr2());
            Assert.assertEquals("Room 4", dto.getAddr3());
            Assert.assertEquals("P.O. Box 84763", dto.getAddr4());
            Assert.assertEquals(75028, dto.getZip());
            Assert.assertEquals(1234, dto.getZipext());
            Assert.assertEquals("dallas", dto.getCity());
            Assert.assertEquals("TX", dto.getState());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for BusinessContactDto Adapater");
        }
    }
    
    @Test
    public void testPersonAddressAdapter() {
        VwPersonAddress o = this.createPersonAddressMockObject();
        PersonalContactDto dto = Rmt2AddressBookDtoFactory.getPersonInstance(o);
        
        Assert.assertEquals(2222, dto.getAddrId());
        Assert.assertEquals("94393 Hall Ave.", dto.getAddr1());
        Assert.assertEquals("Suite 948", dto.getAddr2());
        Assert.assertEquals("Room 4", dto.getAddr3());
        Assert.assertEquals("P.O. Box 84763", dto.getAddr4());
        Assert.assertEquals(75028, dto.getZip());
        Assert.assertEquals(1234, dto.getZipext());
        Assert.assertEquals("dallas", dto.getCity());
        Assert.assertEquals("TX", dto.getState());
        
        try {
            dto = Rmt2AddressBookDtoFactory.getPersonInstance(null);
            dto.setAddrId(2222);
            dto.setAddr1("94393 Hall Ave.");
            dto.setAddr2("Suite 948");
            dto.setAddr3("Room 4");
            dto.setAddr4("P.O. Box 84763");
            dto.setZip(75028);
            dto.setZipext(1234);
            dto.setCity("dallas");
            dto.setState("TX");

            Assert.assertEquals(2222, dto.getAddrId());
            Assert.assertEquals("94393 Hall Ave.", dto.getAddr1());
            Assert.assertEquals("Suite 948", dto.getAddr2());
            Assert.assertEquals("Room 4", dto.getAddr3());
            Assert.assertEquals("P.O. Box 84763", dto.getAddr4());
            Assert.assertEquals(75028, dto.getZip());
            Assert.assertEquals(1234, dto.getZipext());
            Assert.assertEquals("dallas", dto.getCity());
            Assert.assertEquals("TX", dto.getState());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for PersonalContactDto Adapater");
        }
    }
}
