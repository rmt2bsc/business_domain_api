package org.rmt2.entity.adapter;

import org.dao.contacts.ContactsConst;
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
