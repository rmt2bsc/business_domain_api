package org.rmt2.entity.adapter;

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
import org.rmt2.api.profiles.ContactProfileTestData;

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

    
    
    @Test
    public void testAddresAdapter() {
        Address addr = ContactProfileTestData.createAddressMockObject();
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
        VwCommonContact o = ContactProfileTestData.createCommonContactAddressMockObject(); 
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
        VwBusinessAddress o = ContactProfileTestData.createBusinessAddressMockObject();
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
        VwPersonAddress o = ContactProfileTestData.createPersonAddressMockObject();
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
