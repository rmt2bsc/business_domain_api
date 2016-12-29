package postal;

import java.util.List;

import junit.framework.Assert;

import org.dao.postal.PostalDaoFactory;
import org.dao.postal.PostalLocationDao;
import org.dto.IpLocationDto;
import org.dto.TimeZoneDto;
import org.dto.ZipcodeDto;
import org.dto.adapter.orm.Rmt2AddressBookDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the RMT2 ORM implemetation of the Postal Locatione DAO
 * 
 * @author rterrell
 * 
 */
public class PostalLocationRmt2OrmDaoTest {

    private PostalDaoFactory f;

    private PostalLocationDao dao;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.f = new PostalDaoFactory();
        this.dao = this.f.createRmt2OrmPostalDao();
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
    public void testSingleZipFetch() {
        int uid = 64738;
        ZipcodeDto dto = null;
        try {
            dto = this.dao.fetchZipCode(uid);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(dto);
        Assert.assertEquals("AUSTIN", dto.getCity());
    }

    @Test
    public void testMultipleZipFetch() {
        String state = "TX";
        int total = 4439;
        List<ZipcodeDto> list = null;

        try {
            ZipcodeDto criteria = Rmt2AddressBookDtoFactory
                    .getNewZipCodeInstance();
            criteria.setStateCode(state);
            list = this.dao.fetchZipCode(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
        Assert.assertEquals(total, list.size());
    }

    @Test
    public void testInvalidSearchCriteriaZipFetch() {
        List<ZipcodeDto> list = null;

        try {
            list = this.dao.fetchZipCode(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertNull(list);
            return;
        }
        Assert.fail("Search criteria object should not have been accepted since it was null");
    }

    @Test
    public void testEmptySearchCriteriaZipFetch() {
        List<ZipcodeDto> list = null;

        try {
            ZipcodeDto criteria = Rmt2AddressBookDtoFactory
                    .getNewZipCodeInstance();
            list = this.dao.fetchZipCode(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertNull(list);
            return;
        }
        Assert.fail("Search criteria object should not have been accepted since there was no evidence of any criteria properties set");
    }

    @Test
    public void testSingleTimeZoneFetch() {
        int uid = 6;
        TimeZoneDto dto = null;
        try {
            dto = this.dao.fetchTimezone(uid);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(dto);
        Assert.assertEquals("Central", dto.getTimeZoneDescr());
    }

    @Test
    public void testTimeZoneFetchUsingDto() {
        String descr = "A";
        int total = 2;
        List<TimeZoneDto> list = null;

        try {
            TimeZoneDto criteria = Rmt2AddressBookDtoFactory
                    .getNewTimezoneInstance();
            criteria.setTimeZoneDescr(descr);
            list = this.dao.fetchTimezone(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
        Assert.assertEquals(total, list.size());
    }

    @Test
    public void testIpLocationFetchUsingAddress() {
        String ip = "71.252.210.138";
        // String ip = "64.89.115.110";
        // String ip = "204.0.69.201";
        IpLocationDto dto = null;

        try {
            dto = this.dao.fetchIpInfo(ip);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(dto);

    }
}
