package org.rmt2.api.profiles;

import org.dao.contacts.ContactsConst;
import org.dao.mapping.orm.rmt2.Address;
import org.dao.mapping.orm.rmt2.Business;
import org.dao.mapping.orm.rmt2.Person;
import org.dao.mapping.orm.rmt2.VwBusinessAddress;
import org.dao.mapping.orm.rmt2.VwCommonContact;
import org.dao.mapping.orm.rmt2.VwPersonAddress;

public class ContactProfileTestData {

    /**
     * 
     * @return
     */
    public static final Address createAddressMockObject() {
        Address a = new Address();
        a.setAddrId(2222);
        a.setBusinessId(1351);
        a.setAddr1("94393 Hall Ave.");
        a.setAddr2("Suite 948");
        a.setAddr3("Room 4");
        a.setAddr4("P.O. Box 84763");
        a.setZip(75028);
        a.setZipext(1234);
        a.setPhoneCell("1111111111");
        a.setPhoneExt("2222222222");
        a.setPhoneFax("3333333333");
        a.setPhoneHome("4444444444");
        a.setPhoneMain("5555555555");
        a.setPhonePager("6666666666");
        a.setPhoneWork("7777777777");
        return a;
    }
    
    /**
     * 
     * @return
     */
    public static final VwCommonContact createCommonContactAddressMockObject() {
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
        a.setAddrPhoneCell("1111111111");
        a.setAddrPhoneExt("2222222222");
        a.setAddrPhoneFax("3333333333");
        a.setAddrPhoneHome("4444444444");
        a.setAddrPhoneMain("5555555555");
        a.setAddrPhonePager("6666666666");
        a.setAddrPhoneWork("7777777777");
        return a;
    }
    
    /**
     * 
     * @return
     */
    public static final Business createBusinessMockObject() {
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
    
    /**
     * 
     * @return
     */
    public static final VwBusinessAddress createBusinessAddressMockObject() {
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

        b.setAddrPhoneCell("1111111111");
        b.setAddrPhoneExt("2222222222");
        b.setAddrPhoneFax("3333333333");
        b.setAddrPhoneHome("4444444444");
        b.setAddrPhoneMain("5555555555");
        b.setAddrPhonePager("6666666666");
        b.setAddrPhoneWork("7777777777");
        return b;
    }
    
    /**
     * 
     * @return
     */
    public static final Person createSingleMockOrmObject() {
        Person p = new Person();
        p.setPersonId(1351);
        p.setFirstname("Dennis");
        p.setLastname("Chambers");
        p.setMidname("Milton");
        p.setTitle(100);
        p.setEmail("royterrell@gte.net");
        p.setMaritalStatusId(20);
        p.setGenderId(12);
        p.setSsn("345-59-4938");
        p.setRaceId(30);
        
        return p;
    }
    
    /**
     * 
     * @return
     */
    public static final VwPersonAddress createPersonAddressMockObject() {
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
        
        p.setAddrPhoneCell("1111111111");
        p.setAddrPhoneExt("2222222222");
        p.setAddrPhoneFax("3333333333");
        p.setAddrPhoneHome("4444444444");
        p.setAddrPhoneMain("5555555555");
        p.setAddrPhonePager("6666666666");
        p.setAddrPhoneWork("7777777777");
        return p;
    }
}
