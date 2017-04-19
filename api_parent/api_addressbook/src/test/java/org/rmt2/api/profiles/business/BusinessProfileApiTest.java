/**
 * 
 */
package org.rmt2.api.profiles.business;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.dao.mapping.orm.rmt2.Address;
import org.dao.mapping.orm.rmt2.Business;
import org.dto.BusinessContactDto;
import org.dto.ContactDto;
import org.dto.adapter.jaxb.JaxbAddressBookFactory;
import org.dto.adapter.orm.Rmt2AddressBookDtoFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.contacts.ContactsApiException;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.profiles.BaseProfileTest;

/**
 * @author royterrell
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ BaseProfileTest.class })
public class BusinessProfileApiTest extends BaseProfileTest {

    @Test
    public void testSingleBusinessContactFetch() {
        Business bus = new Business();
        Address addr = new Address();

        bus.setBusinessId(1351);
        bus.setLongname("Ticket Master");

        addr.setAddrId(2258);
        addr.setBusinessId(1351);
        addr.setPhoneMain("2143738000");

        BusinessContactDto busDto = Rmt2AddressBookDtoFactory.getBusinessInstance(bus, addr);

        BusinessContactDto dto = null;
        dto = JaxbAddressBookFactory.createBusinessContactDtoInstance(contact);
        try {
            when(this.mockDao.getContact(any(ContactDto.class))).thenReturn(this.mockSinglePRofileResponse);
        } catch (ContactsApiException e) {

        }

        this.startTest();
    }

}
