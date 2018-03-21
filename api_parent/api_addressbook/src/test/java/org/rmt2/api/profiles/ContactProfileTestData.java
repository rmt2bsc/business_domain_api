package org.rmt2.api.profiles;

import org.dao.contacts.ContactsConst;
import org.dao.mapping.orm.rmt2.Address;
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

        return a;
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

        return b;
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
        return p;
    }
}
