package lookup;

import java.util.List;

import junit.framework.Assert;

import org.dao.lookup.LookupDao;
import org.dao.lookup.LookupDaoFactory;
import org.dto.LookupCodeDto;
import org.dto.LookupGroupDto;
import org.dto.adapter.ldap.LdapAddressBookDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the RMT2 ORM Code DAO implementation.
 * 
 * @author rterrell
 * 
 */
public class TestLdapLookupCodeDao {

    private LookupDaoFactory f;

    private LookupDao dao;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.f = new LookupDaoFactory();
        this.dao = this.f.createLdapDao();
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
    public void fetchSingleGroup() {
        int grpId = 15; // Race Culture Group
        LookupGroupDto dto = null;
        try {
            dto = this.dao.fetchGroup(grpId);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch of single lookup group record faile");
        }
        Assert.assertNotNull(dto);
        Assert.assertEquals("Race-Culture", dto.getGrpDescr());
    }

    @Test
    public void fetchGroups() {
        LookupGroupDto criteria = LdapAddressBookDtoFactory
                .getLookupGroupInstance(null);
        criteria.setGrpDescr("B"); // All groups that begin with "B".
        List<LookupGroupDto> list = null;
        try {
            list = this.dao.fetchGroup(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch of multiple lookup groups failed");
        }
        Assert.assertNotNull(list);
        Assert.assertEquals(2, list.size());
    }

    @Test
    public void fetchAllGroups() {
        LookupGroupDto criteria = null;
        List<LookupGroupDto> list = null;
        try {
            list = this.dao.fetchGroup(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch all lookup groups failed");
        }
        Assert.assertNotNull(list);
        Assert.assertEquals(9, list.size());
    }

    @Test
    public void fetchSingleDetail() {
        int uid = 218; // FamilyRelationship - Daughter
        LookupCodeDto dto = null;
        try {
            dto = this.dao.fetchCode(uid);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch of single lookup record faile");
        }
        Assert.assertNotNull(dto);
        Assert.assertEquals("Daughter", dto.getCodeLongName());
    }

    @Test
    public void getAllCodesForAGroup() {
        List<LookupCodeDto> results = null;
        LookupCodeDto criteria = LdapAddressBookDtoFactory
                .getLookupCodeInstance(null);
        criteria.setGrpId(6); // Gender Group
        try {
            results = this.dao.fetchCode(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch of lookup(s) using DTO as criteria failed");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
    }

    @Test
    public void getMultipleCodesForAGroup() {
        List<LookupCodeDto> results = null;
        LookupCodeDto criteria = LdapAddressBookDtoFactory
                .getLookupCodeInstance(null);
        criteria.setGrpId(15); // Gender Group
        criteria.setCodeShortDesc("C"); // All codes that begin with "C".
        try {
            results = this.dao.fetchCode(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Fetch of lookup(s) using DTO as criteria failed");
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
    }
}
