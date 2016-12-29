package contacts;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.dao.contacts.ContactsConst;
import org.dao.contacts.ContactsDao;
import org.dao.contacts.ContactsDaoFactory;
import org.dto.BusinessContactDto;
import org.dto.ContactDto;
import org.dto.PersonalContactDto;
import org.dto.adapter.orm.Rmt2AddressBookDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the RMT2 ORM implemetation of the Contacts DAO
 * 
 * @author rterrell
 * 
 */
public class ContactsDaoTest {

    private ContactsDaoFactory f;

    private ContactsDao dao;

    private String userId;

    private int raceId = 226;

    private int entityTypeId = 26;

    private int servTypeId = 30;

    private List<String> busIdList;

    String busNames[] = { "ABCCorp", "EXFCorp", "XYZCorp" };

    private String busShortName = "ABC";

    private String ssn = "444-44-4444";

    private String addr1 = "610 Hoover";

    private String addr2 = "Suite 234";

    private String addr3 = "Room 23";

    private String addr4 = "P.O. Box 95439494";

    private int zip = 71106;

    private String phoneHome = "3188689417";

    private String phoneWork = "3188888885";

    private String phoneCell = "3182222222";

    private String phonePager = "3181111111";

    private String phoneFax = "3187777777";

    private String phoneExt = "1234";

    private String phoneMain = "3183333333";

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.f = new ContactsDaoFactory();
        this.dao = this.f.createRmt2OrmDao();
        this.userId = "testuser";

        // Assing user to dao
        this.dao.setDaoUser(this.userId);

        this.addr1 = "610 Hoover";
        this.addr2 = "Suite 234";
        this.addr3 = "Room 23";
        this.addr4 = "P.O. Box 95439494";
        this.zip = 71106;
        this.phoneHome = "3188689417";
        this.phoneWork = "3188888885";
        this.phoneCell = "3182222222";
        this.phonePager = "3181111111";
        this.phoneFax = "3187777777";
        this.phoneExt = "1234";
        this.phoneMain = "3183333333";
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.dao.close();
        this.dao = null;
        this.f = null;
    }

    @Test
    public void testFetchAllCommon() {
        List<ContactDto> results = null;
        try {
            results = this.dao.fetchContact();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 100);
    }

    @Test
    public void testFetchCommonSinglePersonal() {
        ContactDto results = null;
        String contactName = "Terrell, Dione";
        try {
            results = this.dao.fetchContact(980);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(contactName, results.getContactName());
    }

    @Test
    public void testFetchCommonSingleBusiness() {
        ContactDto results = null;
        String contactName = "American Express Financial";
        try {
            results = this.dao.fetchContact(1117);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(contactName, results.getContactName());
    }

    @Test
    public void testFetchCommonByContactName() {
        ContactDto criteria = Rmt2AddressBookDtoFactory.getNewContactInstance();
        criteria.setContactName("A");
        List<ContactDto> results = null;
        try {
            results = this.dao.fetchContact(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 10);
        for (ContactDto item : results) {
            Assert.assertEquals("A", item.getContactName().substring(0, 1));
        }
    }

    @Test
    public void testFetchPersonByContactName() {
        PersonalContactDto criteria = Rmt2AddressBookDtoFactory
                .getNewPersonInstance();
        criteria.setContactName("A");
        List<ContactDto> results = null;
        try {
            results = this.dao.fetchContact(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
        for (ContactDto item : results) {
            Assert.assertNotNull(item);
            Assert.assertTrue(item instanceof PersonalContactDto);
            PersonalContactDto personDto = (PersonalContactDto) item;
            Assert.assertEquals("A", personDto.getContactName().substring(0, 1));
        }
    }

    @Test
    public void testFetchBusinessByContactName() {
        BusinessContactDto criteria = Rmt2AddressBookDtoFactory
                .getNewBusinessInstance();
        criteria.setContactName("V");
        List<ContactDto> results = null;
        try {
            results = this.dao.fetchContact(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
        for (ContactDto item : results) {
            Assert.assertNotNull(item);
            Assert.assertTrue(item instanceof BusinessContactDto);
            BusinessContactDto businessDto = (BusinessContactDto) item;
            Assert.assertEquals("V",
                    businessDto.getContactName().substring(0, 1));
        }
    }

    @Test
    public void testFetchContactWithInvalidCriteria() {
        Object results = null;
        try {
            results = this.dao.fetchContact(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertNull(results);
            return;
        }
        Assert.fail("Fetch of contact with invalid criteria object was expected to fail!");
    }

    @Test
    public void testCreateUpdateDeletePersonAsCommonContact() {
        int rc = 0;
        int newId = 0;

        // Create contact
        ContactDto contact = Rmt2AddressBookDtoFactory.getNewContactInstance();
        contact.setContactName("Edward Maya");
        contact.setContactType(ContactsConst.CONTACT_TYPE_PERSONAL);
        contact.setContactEmail("edwardmaya@verizon.net");
        contact.setUpdateUserId(this.userId);
        try {
            newId = this.dao.maintainContact(contact);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(newId > 0);
        Assert.assertEquals(newId, contact.getContactId());

        // Verify new record exists
        try {
            contact = this.dao.fetchContact(contact.getContactId());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(newId, contact.getContactId());

        // Update contact
        contact.setAddr1(this.addr1);
        contact.setAddr2(this.addr2);
        contact.setAddr3(this.addr3);
        contact.setAddr4(this.addr4);
        contact.setZip(this.zip);
        contact.setPhoneCell(this.phoneCell);
        contact.setPhoneCompany(this.phoneMain);
        contact.setPhoneExt(this.phoneExt);
        contact.setPhoneFax(this.phoneFax);
        contact.setPhoneHome(this.phoneHome);
        contact.setPhonePager(this.phonePager);
        contact.setPhoneWork(this.phoneWork);
        contact.setUpdateUserId(this.userId);

        try {
            rc = this.dao.maintainContact(contact);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(rc, 1);

        // Verify Update
        ContactDto contactUpdate = null;
        try {
            contactUpdate = this.dao.fetchContact(newId);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(newId, contact.getContactId());
        Assert.assertEquals(this.addr1, contactUpdate.getAddr1());
        Assert.assertEquals(this.addr2, contactUpdate.getAddr2());
        Assert.assertEquals(this.addr3, contactUpdate.getAddr3());
        Assert.assertEquals(this.addr4, contactUpdate.getAddr4());
        Assert.assertEquals(this.zip, contactUpdate.getZip());
        Assert.assertEquals(this.phoneCell, contactUpdate.getPhoneCell());
        Assert.assertEquals(this.phoneExt, contactUpdate.getPhoneExt());
        Assert.assertEquals(this.phoneFax, contactUpdate.getPhoneFax());
        Assert.assertEquals(this.phoneHome, contactUpdate.getPhoneHome());
        Assert.assertEquals(this.phonePager, contactUpdate.getPhonePager());
        Assert.assertEquals(this.phoneWork, contactUpdate.getPhoneWork());
        Assert.assertEquals(this.phoneMain, contactUpdate.getPhoneCompany());

        // Delete contact
        try {
            rc = this.dao.deleteContact(contactUpdate);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(rc, 2);
    }

    @Test
    public void testCreatePersonAsCommonContactWithNullName() {
        int newId = 0;

        // Create contact
        ContactDto newContact = Rmt2AddressBookDtoFactory
                .getNewContactInstance();
        newContact.setContactName(null);
        newContact.setContactType(ContactsConst.CONTACT_TYPE_PERSONAL);
        newContact.setContactEmail("edwardmaya@verizon.net");
        newContact.setUpdateUserId(this.userId);
        try {
            newId = this.dao.maintainContact(newContact);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(newId == 0);
            return;
        }
        Assert.fail("Test failed since an exception was not thrown due to the contact name is null");
    }

    @Test
    public void testCreatePersonAsCommonContactWithInvalidContactType() {
        int newId = 0;

        // Create contact
        ContactDto newContact = Rmt2AddressBookDtoFactory
                .getNewContactInstance();
        newContact.setContactName(null);
        newContact.setContactType("xxx");
        newContact.setContactEmail("edwardmaya@verizon.net");
        newContact.setUpdateUserId(this.userId);
        try {
            newId = this.dao.maintainContact(newContact);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(newId == 0);
            return;
        }
        Assert.fail("Test failed since an exception was not thrown due to an invalid contact type value");
    }

    @Test
    public void testCreateUpdateDeletePersonContact() {
        int rc = 0;
        int newId = 0;

        // Create contact
        PersonalContactDto newContact = Rmt2AddressBookDtoFactory
                .getNewPersonInstance();
        newContact.setContactName("Edward Maya");
        newContact.setContactType(ContactsConst.CONTACT_TYPE_PERSONAL);
        newContact.setContactEmail("edwardmaya@verizon.net");
        newContact.setUpdateUserId(this.userId);
        try {
            newId = this.dao.maintainContact(newContact);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(newId > 0);
        Assert.assertEquals(newId, newContact.getContactId());

        // Verify new record exists
        List<ContactDto> list = null;
        PersonalContactDto contact = null;
        try {
            PersonalContactDto criteria = Rmt2AddressBookDtoFactory
                    .getNewPersonInstance();
            criteria.setContactId(newContact.getContactId());
            list = this.dao.fetchContact(criteria);
            contact = (PersonalContactDto) list.get(0);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(newId, contact.getContactId());

        // Update contact data
        contact.setSsn(this.ssn);
        contact.setRaceId(this.raceId);
        contact.setAddr1(this.addr1);
        contact.setAddr2(this.addr2);
        contact.setAddr3(this.addr3);
        contact.setAddr4(this.addr4);
        contact.setZip(this.zip);
        contact.setPhoneCell(this.phoneCell);
        contact.setPhoneCompany(this.phoneMain);
        contact.setPhoneExt(this.phoneExt);
        contact.setPhoneFax(this.phoneFax);
        contact.setPhoneHome(this.phoneHome);
        contact.setPhonePager(this.phonePager);
        contact.setPhoneWork(this.phoneWork);
        contact.setUpdateUserId(this.userId);

        try {
            rc = this.dao.maintainContact(contact);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(rc, 1);

        // Verify Update
        PersonalContactDto contactUpdate = null;
        try {
            PersonalContactDto criteria = Rmt2AddressBookDtoFactory
                    .getNewPersonInstance();
            criteria.setContactId(newId);
            list = this.dao.fetchContact(criteria);
            contactUpdate = (PersonalContactDto) list.get(0);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(newId, contact.getContactId());
        Assert.assertEquals(this.ssn, contactUpdate.getSsn());
        Assert.assertEquals(this.raceId, contactUpdate.getRaceId());
        Assert.assertEquals(this.addr1, contactUpdate.getAddr1());
        Assert.assertEquals(this.addr2, contactUpdate.getAddr2());
        Assert.assertEquals(this.addr3, contactUpdate.getAddr3());
        Assert.assertEquals(this.addr4, contactUpdate.getAddr4());
        Assert.assertEquals(this.zip, contactUpdate.getZip());
        Assert.assertEquals(this.phoneCell, contactUpdate.getPhoneCell());
        Assert.assertEquals(this.phoneExt, contactUpdate.getPhoneExt());
        Assert.assertEquals(this.phoneFax, contactUpdate.getPhoneFax());
        Assert.assertEquals(this.phoneHome, contactUpdate.getPhoneHome());
        Assert.assertEquals(this.phonePager, contactUpdate.getPhonePager());
        Assert.assertEquals(this.phoneWork, contactUpdate.getPhoneWork());
        Assert.assertEquals(this.phoneMain, contactUpdate.getPhoneCompany());

        // Delete contact
        try {
            rc = this.dao.deleteContact(contactUpdate);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(rc, 2);
    }

    @Test
    public void testCreateUpdateDeleteBusinessAsCommonContact() {
        int rc = 0;
        int newId = 0;

        // Create contact
        ContactDto contact = Rmt2AddressBookDtoFactory.getNewContactInstance();
        contact.setContactName("ABC Corporation");
        contact.setContactType(ContactsConst.CONTACT_TYPE_BUSINESS);
        contact.setContactEmail("edwardmaya@verizon.net");
        contact.setUpdateUserId(this.userId);
        try {
            newId = this.dao.maintainContact(contact);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(newId > 0);
        Assert.assertEquals(newId, contact.getContactId());

        // Verify new record exists
        try {
            contact = this.dao.fetchContact(contact.getContactId());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(newId, contact.getContactId());

        // Update contact's address
        contact.setAddr1(this.addr1);
        contact.setAddr2(this.addr2);
        contact.setAddr3(this.addr3);
        contact.setAddr4(this.addr4);
        contact.setZip(this.zip);
        contact.setPhoneCell(this.phoneCell);
        contact.setPhoneCompany(this.phoneMain);
        contact.setPhoneExt(this.phoneExt);
        contact.setPhoneFax(this.phoneFax);
        contact.setPhoneHome(this.phoneHome);
        contact.setPhonePager(this.phonePager);
        contact.setPhoneWork(this.phoneWork);
        contact.setUpdateUserId(this.userId);

        try {
            rc = this.dao.maintainContact(contact);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(rc, 1);

        // Verify Update
        ContactDto contactUpdate = null;
        try {
            contactUpdate = this.dao.fetchContact(newId);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(newId, contact.getContactId());
        Assert.assertEquals(this.addr1, contactUpdate.getAddr1());
        Assert.assertEquals(this.addr2, contactUpdate.getAddr2());
        Assert.assertEquals(this.addr3, contactUpdate.getAddr3());
        Assert.assertEquals(this.addr4, contactUpdate.getAddr4());
        Assert.assertEquals(this.zip, contactUpdate.getZip());
        Assert.assertEquals(this.phoneCell, contactUpdate.getPhoneCell());
        Assert.assertEquals(this.phoneExt, contactUpdate.getPhoneExt());
        Assert.assertEquals(this.phoneFax, contactUpdate.getPhoneFax());
        Assert.assertEquals(this.phoneHome, contactUpdate.getPhoneHome());
        Assert.assertEquals(this.phonePager, contactUpdate.getPhonePager());
        Assert.assertEquals(this.phoneWork, contactUpdate.getPhoneWork());
        Assert.assertEquals(this.phoneMain, contactUpdate.getPhoneCompany());

        // Delete contact
        try {
            rc = this.dao.deleteContact(contactUpdate);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(rc, 2);
    }

    @Test
    public void testCreateUpdateDeleteBusinessContact() {
        int rc = 0;
        int newId = 0;

        // Create contact
        BusinessContactDto newContact = Rmt2AddressBookDtoFactory
                .getNewBusinessInstance();
        newContact.setContactName("ABC Corporation");
        newContact.setContactType(ContactsConst.CONTACT_TYPE_BUSINESS);
        newContact.setContactEmail("edwardmaya@verizon.net");
        newContact.setUpdateUserId(this.userId);
        try {
            newId = this.dao.maintainContact(newContact);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(newId > 0);
        Assert.assertEquals(newId, newContact.getContactId());

        // Verify new record exists
        List<ContactDto> list = null;
        BusinessContactDto contact = null;
        try {
            BusinessContactDto criteria = Rmt2AddressBookDtoFactory
                    .getNewBusinessInstance();
            criteria.setContactId(newContact.getContactId());
            list = this.dao.fetchContact(criteria);
            contact = (BusinessContactDto) list.get(0);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(newId, contact.getContactId());

        // Update contact
        contact.setEntityTypeId(this.entityTypeId);
        contact.setServTypeId(this.servTypeId);
        contact.setShortName(this.busShortName);
        contact.setAddr1(this.addr1);
        contact.setAddr2(this.addr2);
        contact.setAddr3(this.addr3);
        contact.setAddr4(this.addr4);
        contact.setZip(this.zip);
        contact.setPhoneCell(this.phoneCell);
        contact.setPhoneCompany(this.phoneMain);
        contact.setPhoneExt(this.phoneExt);
        contact.setPhoneFax(this.phoneFax);
        contact.setPhoneHome(this.phoneHome);
        contact.setPhonePager(this.phonePager);
        contact.setPhoneWork(this.phoneWork);
        contact.setUpdateUserId(this.userId);

        try {
            rc = this.dao.maintainContact(contact);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(rc, 1);

        // Verify Update
        BusinessContactDto contactUpdate = null;
        try {
            BusinessContactDto criteria = Rmt2AddressBookDtoFactory
                    .getNewBusinessInstance();
            criteria.setContactId(newId);
            list = this.dao.fetchContact(criteria);
            contactUpdate = (BusinessContactDto) list.get(0);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(newId, newContact.getContactId());
        Assert.assertEquals(this.entityTypeId, contactUpdate.getEntityTypeId());
        Assert.assertEquals(this.servTypeId, contactUpdate.getServTypeId());
        Assert.assertEquals(this.busShortName, contactUpdate.getShortName());
        Assert.assertEquals(this.addr1, contactUpdate.getAddr1());
        Assert.assertEquals(this.addr2, contactUpdate.getAddr2());
        Assert.assertEquals(this.addr3, contactUpdate.getAddr3());
        Assert.assertEquals(this.addr4, contactUpdate.getAddr4());
        Assert.assertEquals(this.zip, contactUpdate.getZip());
        Assert.assertEquals(this.phoneCell, contactUpdate.getPhoneCell());
        Assert.assertEquals(this.phoneExt, contactUpdate.getPhoneExt());
        Assert.assertEquals(this.phoneFax, contactUpdate.getPhoneFax());
        Assert.assertEquals(this.phoneHome, contactUpdate.getPhoneHome());
        Assert.assertEquals(this.phonePager, contactUpdate.getPhonePager());
        Assert.assertEquals(this.phoneWork, contactUpdate.getPhoneWork());
        Assert.assertEquals(this.phoneMain, contactUpdate.getPhoneCompany());

        // Delete contact
        try {
            rc = this.dao.deleteContact(contactUpdate);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(rc, 2);
    }

    @Test
    public void testFetchBusinesContactsUsingMultipleBusinessId() {
        int rc = 0;

        // Perform actual query test
        List<BusinessContactDto> list = null;
        try {
            this.createListData();
            list = this.dao.fetchBusinessContact(this.busIdList);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        } finally {
            this.deleteListData();
        }

        // Verify query results
        Assert.assertNotNull(list);
        Assert.assertEquals(3, list.size());
        Assert.assertEquals(busNames[0], list.get(0).getContactName());
        Assert.assertEquals(busNames[1], list.get(1).getContactName());
        Assert.assertEquals(busNames[2], list.get(2).getContactName());

    }

    private void createListData() {
        int rc = 0;
        int newId = 0;
        this.busIdList = new ArrayList<String>();

        // Create contacts
        for (int ndx = 0; ndx < busNames.length; ndx++) {
            BusinessContactDto newContact = Rmt2AddressBookDtoFactory
                    .getNewBusinessInstance();
            newContact.setContactName(busNames[ndx]);
            newContact.setContactType(ContactsConst.CONTACT_TYPE_BUSINESS);
            newContact.setContactEmail(busNames[ndx] + "@verizon.net");
            newContact.setUpdateUserId(this.userId);
            try {
                newId = this.dao.maintainContact(newContact);
                busIdList.add(String.valueOf(newId));
            } catch (Exception e) {
                e.printStackTrace();
                Assert.fail(e.getMessage());
            }
            Assert.assertTrue(newId > 0);
            Assert.assertEquals(newId, newContact.getContactId());
        }
        return;
    }

    private void deleteListData() {
        // Delete contacts
        int rc = 0;
        for (int ndx = 0; ndx < busIdList.size(); ndx++) {
            BusinessContactDto contact = Rmt2AddressBookDtoFactory
                    .getNewBusinessInstance();
            contact.setContactId(Integer.parseInt(busIdList.get(ndx)));
            try {
                rc = this.dao.deleteContact(contact);
            } catch (Exception e) {
                Assert.fail(e.getMessage());
            }
            Assert.assertEquals(rc, 2);
        }
    }
}
