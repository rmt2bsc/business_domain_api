package org.rmt2.api.entity.adapter;

import org.dao.contacts.ContactsConst;
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
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.profiles.ContactProfileTestData;

/**
 * Test GL Account related adapters
 * 
 * @author roy.terrell
 *
 */
public class ContactProfileAdapterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    
    
    @Test
    public void testCommonContactAddressAdapter() {
        VwCommonContact o = ContactProfileTestData.createCommonContactAddressMockObject(); 
        ContactDto dto = Rmt2AddressBookDtoFactory.getContactInstance(o);
        
        Assert.assertEquals(1351, dto.getContactId());
        Assert.assertEquals("Dennis Chambers", dto.getContactName());
        Assert.assertEquals(ContactsConst.CONTACT_TYPE_PERSONAL, dto.getContactType());
        
        try {
            dto = Rmt2AddressBookDtoFactory.getContactInstance(null);
            dto.setContactId(1351);
            dto.setContactName("Dennis Chambers");
            dto.setContactType(ContactsConst.CONTACT_TYPE_PERSONAL);

            Assert.assertEquals(1351, dto.getContactId());
            Assert.assertEquals("Dennis Chambers", dto.getContactName());
            Assert.assertEquals(ContactsConst.CONTACT_TYPE_PERSONAL, dto.getContactType());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for ContactDto Adapater");
        }
    }
    
    
    @Test
    public void testBusinessAddressAdapter() {
        Address addr = ContactProfileTestData.createAddressMockObject();
        Business bus = ContactProfileTestData.createBusinessMockObject();
        BusinessContactDto dto = Rmt2AddressBookDtoFactory.getBusinessInstance(bus, addr);
        
        // Test address properties
        Assert.assertEquals(2222, dto.getAddrId());
        Assert.assertEquals("94393 Hall Ave.", dto.getAddr1());
        Assert.assertEquals("Suite 948", dto.getAddr2());
        Assert.assertEquals("Room 4", dto.getAddr3());
        Assert.assertEquals("P.O. Box 84763", dto.getAddr4());
        Assert.assertEquals(75028, dto.getZip());
        Assert.assertEquals(1234, dto.getZipext());
        Assert.assertEquals("1111111111", dto.getPhoneCell());
        Assert.assertEquals("2222222222", dto.getPhoneExt());
        Assert.assertEquals("3333333333", dto.getPhoneFax());
        Assert.assertEquals("4444444444", dto.getPhoneHome());
        Assert.assertEquals("5555555555", dto.getPhoneCompany());
        Assert.assertEquals("6666666666", dto.getPhonePager());
        Assert.assertEquals("7777777777", dto.getPhoneWork());
        
        // Test business properties
        Assert.assertEquals(1351, dto.getContactId());
        Assert.assertEquals("Ticket Master", dto.getContactName());
        Assert.assertEquals("roy", dto.getContactFirstname());
        Assert.assertEquals("terrell", dto.getContactLastname());
        Assert.assertEquals("9728882222", dto.getContactPhone());
        Assert.assertEquals("royterrell@gte.net", dto.getContactEmail());
        Assert.assertEquals(130, dto.getServTypeId());
        Assert.assertEquals(100, dto.getEntityTypeId());
        Assert.assertEquals("75-9847382", dto.getTaxId());
        Assert.assertEquals("ticketmaster.com", dto.getWebsite());
        dto.setContactType(ContactsConst.CONTACT_TYPE_BUSINESS);
        
        try {
            dto = Rmt2AddressBookDtoFactory.getBusinessInstance(null, null);
            
            // Set address properties
            dto.setAddrId(2222);
            dto.setAddr1("94393 Hall Ave.");
            dto.setAddr2("Suite 948");
            dto.setAddr3("Room 4");
            dto.setAddr4("P.O. Box 84763");
            dto.setZip(75028);
            dto.setZipext(1234);
            dto.getPhoneCell();
            dto.setPhoneCell("1111111111");
            dto.setPhoneExt("2222222222");
            dto.setPhoneFax("3333333333");
            dto.setPhoneHome("4444444444");
            dto.setPhoneCompany("5555555555");
            dto.setPhonePager("6666666666");
            dto.setPhoneWork("7777777777");
            
            // Set business properties
            dto.setContactId(1351);
            dto.setContactName("Ticket Master");
            dto.setContactFirstname("roy");
            dto.setContactLastname("terrell");
            dto.setContactPhone("9728882222");
            dto.setContactEmail("royterrell@gte.net");
            dto.setServTypeId(130);
            dto.setEntityTypeId(100);
            dto.setTaxId("75-9847382");
            dto.setWebsite("ticketmaster.com");
            dto.setContactType(ContactsConst.CONTACT_TYPE_BUSINESS);

            Assert.assertEquals(2222, dto.getAddrId());
            Assert.assertEquals("94393 Hall Ave.", dto.getAddr1());
            Assert.assertEquals("Suite 948", dto.getAddr2());
            Assert.assertEquals("Room 4", dto.getAddr3());
            Assert.assertEquals("P.O. Box 84763", dto.getAddr4());
            Assert.assertEquals(75028, dto.getZip());
            Assert.assertEquals(1234, dto.getZipext());
            Assert.assertEquals("1111111111", dto.getPhoneCell());
            Assert.assertEquals("2222222222", dto.getPhoneExt());
            Assert.assertEquals("3333333333", dto.getPhoneFax());
            Assert.assertEquals("4444444444", dto.getPhoneHome());
            Assert.assertEquals("5555555555", dto.getPhoneCompany());
            Assert.assertEquals("6666666666", dto.getPhonePager());
            Assert.assertEquals("7777777777", dto.getPhoneWork());
            
            Assert.assertEquals(1351, dto.getContactId());
            Assert.assertEquals("Ticket Master", dto.getContactName());
            Assert.assertEquals("roy", dto.getContactFirstname());
            Assert.assertEquals("terrell", dto.getContactLastname());
            Assert.assertEquals("9728882222", dto.getContactPhone());
            Assert.assertEquals("royterrell@gte.net", dto.getContactEmail());
            Assert.assertEquals(130, dto.getServTypeId());
            Assert.assertEquals(100, dto.getEntityTypeId());
            Assert.assertEquals("75-9847382", dto.getTaxId());
            Assert.assertEquals("ticketmaster.com", dto.getWebsite());
            Assert.assertEquals(ContactsConst.CONTACT_TYPE_BUSINESS, dto.getContactType());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for BusinessContactDto Adapater");
        }
    }
    
    @Test
    public void testVwBusinessAddressAdapter() {
        VwBusinessAddress o = ContactProfileTestData.createBusinessAddressMockObject();
        BusinessContactDto dto = Rmt2AddressBookDtoFactory.getBusinessInstance(o);
        
        Assert.assertEquals(1351, dto.getContactId());
        Assert.assertEquals("Ticket Master", dto.getContactName());
        Assert.assertEquals("roy", dto.getContactFirstname());
        Assert.assertEquals("terrell", dto.getContactLastname());
        Assert.assertEquals("9728882222", dto.getContactPhone());
        Assert.assertEquals("royterrell@gte.net", dto.getContactEmail());
        Assert.assertEquals(130, dto.getServTypeId());
        Assert.assertEquals(100, dto.getEntityTypeId());
        Assert.assertEquals("75-9847382", dto.getTaxId());
        Assert.assertEquals("ticketmaster.com", dto.getWebsite());
        dto.setContactType(ContactsConst.CONTACT_TYPE_BUSINESS);
        
        try {
            dto = Rmt2AddressBookDtoFactory.getBusinessInstance(null);
            dto.setContactId(1351);
            dto.setContactName("Ticket Master");
            dto.setContactFirstname("roy");
            dto.setContactLastname("terrell");
            dto.setContactPhone("9728882222");
            dto.setContactEmail("royterrell@gte.net");
            dto.setServTypeId(130);
            dto.setEntityTypeId(100);
            dto.setTaxId("75-9847382");
            dto.setWebsite("ticketmaster.com");
            dto.setContactType(ContactsConst.CONTACT_TYPE_BUSINESS);

            Assert.assertEquals(1351, dto.getContactId());
            Assert.assertEquals("Ticket Master", dto.getContactName());
            Assert.assertEquals("roy", dto.getContactFirstname());
            Assert.assertEquals("terrell", dto.getContactLastname());
            Assert.assertEquals("9728882222", dto.getContactPhone());
            Assert.assertEquals("royterrell@gte.net", dto.getContactEmail());
            Assert.assertEquals(130, dto.getServTypeId());
            Assert.assertEquals(100, dto.getEntityTypeId());
            Assert.assertEquals("75-9847382", dto.getTaxId());
            Assert.assertEquals("ticketmaster.com", dto.getWebsite());
            Assert.assertEquals(ContactsConst.CONTACT_TYPE_BUSINESS, dto.getContactType());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for BusinessContactDto Adapater");
        }
    }
    
    @Test
    public void testPersonAddressAdapter() {
        Address addr = ContactProfileTestData.createAddressMockObject();
        Person per = ContactProfileTestData.createSingleMockOrmObject();
        PersonalContactDto dto = Rmt2AddressBookDtoFactory.getPersonInstance(per, addr);
        
        // Test address properties
        Assert.assertEquals(2222, dto.getAddrId());
        Assert.assertEquals("94393 Hall Ave.", dto.getAddr1());
        Assert.assertEquals("Suite 948", dto.getAddr2());
        Assert.assertEquals("Room 4", dto.getAddr3());
        Assert.assertEquals("P.O. Box 84763", dto.getAddr4());
        Assert.assertEquals(75028, dto.getZip());
        Assert.assertEquals(1234, dto.getZipext());
        Assert.assertEquals("1111111111", dto.getPhoneCell());
        Assert.assertEquals("2222222222", dto.getPhoneExt());
        Assert.assertEquals("3333333333", dto.getPhoneFax());
        Assert.assertEquals("4444444444", dto.getPhoneHome());
        Assert.assertEquals("5555555555", dto.getPhoneCompany());
        Assert.assertEquals("6666666666", dto.getPhonePager());
        Assert.assertEquals("7777777777", dto.getPhoneWork());
        
        // Test person properties
        Assert.assertEquals(1351, dto.getContactId());
        Assert.assertEquals("Dennis", dto.getFirstname());
        Assert.assertEquals("Chambers", dto.getLastname());
        Assert.assertEquals("Milton", dto.getMidname());
        Assert.assertEquals("royterrell@gte.net", dto.getContactEmail());
        Assert.assertEquals("345-59-4938", dto.getSsn());
        Assert.assertEquals(100, dto.getTitle());
        Assert.assertEquals(20, dto.getMaritalStatusId());
        Assert.assertEquals(12, dto.getGenderId());
        Assert.assertEquals(30, dto.getRaceId());
        
        try {
            dto = Rmt2AddressBookDtoFactory.getPersonInstance(null, null);
            
            dto.setAddrId(2222);
            dto.setAddr1("94393 Hall Ave.");
            dto.setAddr2("Suite 948");
            dto.setAddr3("Room 4");
            dto.setAddr4("P.O. Box 84763");
            dto.setZip(75028);
            dto.setZipext(1234);
            dto.getPhoneCell();
            dto.setPhoneCell("1111111111");
            dto.setPhoneExt("2222222222");
            dto.setPhoneFax("3333333333");
            dto.setPhoneHome("4444444444");
            dto.setPhoneCompany("5555555555");
            dto.setPhonePager("6666666666");
            dto.setPhoneWork("7777777777");
            
            dto.setContactId(1351);
            dto.setFirstname("Dennis");
            dto.setLastname("Chambers");
            dto.setMidname("Milton");
            dto.setTitle(100);
            dto.setContactEmail("royterrell@gte.net");
            dto.setMaritalStatusId(20);
            dto.setGenderId(12);
            dto.setSsn("345-59-4938");
            dto.setRaceId(30);

            Assert.assertEquals(2222, dto.getAddrId());
            Assert.assertEquals("94393 Hall Ave.", dto.getAddr1());
            Assert.assertEquals("Suite 948", dto.getAddr2());
            Assert.assertEquals("Room 4", dto.getAddr3());
            Assert.assertEquals("P.O. Box 84763", dto.getAddr4());
            Assert.assertEquals(75028, dto.getZip());
            Assert.assertEquals(1234, dto.getZipext());
            Assert.assertEquals("1111111111", dto.getPhoneCell());
            Assert.assertEquals("2222222222", dto.getPhoneExt());
            Assert.assertEquals("3333333333", dto.getPhoneFax());
            Assert.assertEquals("4444444444", dto.getPhoneHome());
            Assert.assertEquals("5555555555", dto.getPhoneCompany());
            Assert.assertEquals("6666666666", dto.getPhonePager());
            Assert.assertEquals("7777777777", dto.getPhoneWork());
            
            Assert.assertEquals(1351, dto.getContactId());
            Assert.assertEquals("Dennis", dto.getFirstname());
            Assert.assertEquals("Chambers", dto.getLastname());
            Assert.assertEquals("Milton", dto.getMidname());
            Assert.assertEquals("royterrell@gte.net", dto.getContactEmail());
            Assert.assertEquals("345-59-4938", dto.getSsn());
            Assert.assertEquals(100, dto.getTitle());
            Assert.assertEquals(20, dto.getMaritalStatusId());
            Assert.assertEquals(12, dto.getGenderId());
            Assert.assertEquals(30, dto.getRaceId());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for PersonalContactDto Adapater");
        }
    }
    
    @Test
    public void testVwPersonAddressAdapter() {
        VwPersonAddress o = ContactProfileTestData.createPersonAddressMockObject();
        PersonalContactDto dto = Rmt2AddressBookDtoFactory.getPersonInstance(o);
        
        Assert.assertEquals(1351, dto.getContactId());
        Assert.assertEquals("Dennis", dto.getFirstname());
        Assert.assertEquals("Chambers", dto.getLastname());
        Assert.assertEquals("Milton", dto.getMidname());
        Assert.assertEquals("royterrell@gte.net", dto.getContactEmail());
        Assert.assertEquals("345-59-4938", dto.getSsn());
        Assert.assertEquals(100, dto.getTitle());
        Assert.assertEquals(20, dto.getMaritalStatusId());
        Assert.assertEquals(12, dto.getGenderId());
        Assert.assertEquals(30, dto.getRaceId());
        
        try {
            dto = Rmt2AddressBookDtoFactory.getPersonInstance(null);
            dto.setContactId(1351);
            dto.setFirstname("Dennis");
            dto.setLastname("Chambers");
            dto.setMidname("Milton");
            dto.setTitle(100);
            dto.setContactEmail("royterrell@gte.net");
            dto.setMaritalStatusId(20);
            dto.setGenderId(12);
            dto.setSsn("345-59-4938");
            dto.setRaceId(30);

            Assert.assertEquals(1351, dto.getContactId());
            Assert.assertEquals("Dennis", dto.getFirstname());
            Assert.assertEquals("Chambers", dto.getLastname());
            Assert.assertEquals("Milton", dto.getMidname());
            Assert.assertEquals("royterrell@gte.net", dto.getContactEmail());
            Assert.assertEquals("345-59-4938", dto.getSsn());
            Assert.assertEquals(100, dto.getTitle());
            Assert.assertEquals(20, dto.getMaritalStatusId());
            Assert.assertEquals(12, dto.getGenderId());
            Assert.assertEquals(30, dto.getRaceId());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for PersonalContactDto Adapater");
        }
    }
}
