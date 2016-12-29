package postal;

import java.util.List;

import junit.framework.Assert;

import org.dao.postal.PostalDaoFactory;
import org.dao.postal.PostalLocationDao;
import org.dto.IpLocationDto;
import org.dto.TimeZoneDto;
import org.dto.ZipcodeDto;
import org.dto.adapter.ldap.LdapAddressBookDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the RMT2 ORM implemetation of the Postal Locatione DAO
 * 
 * @author Roy Terrell
 * 
 */
public class PostalLocationLdapDaoTest {

    private PostalDaoFactory f;

    private PostalLocationDao dao;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.f = new PostalDaoFactory();
        this.dao = this.f.createLdapPostalDao();
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
    public void testFetchByZipId() {
        ZipcodeDto criteria = LdapAddressBookDtoFactory
                .getZipCodeInstance(null);
        criteria.setIdStr("29933-MILEY");
        List<ZipcodeDto> list = null;

        try {
            list = this.dao.fetchZipCode(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(list);
        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.get(0) instanceof ZipcodeDto);
        ZipcodeDto dto = (ZipcodeDto) list.get(0);
        Assert.assertEquals("803", dto.getAreaCode());
        Assert.assertEquals("MILEY", dto.getCity());
        Assert.assertEquals("MILEY", dto.getCityAliasName());
        Assert.assertEquals(29933, dto.getZip());
        Assert.assertEquals("HAMPTON", dto.getCountyName());
        Assert.assertEquals("SC", dto.getStateCode());
        Assert.assertEquals("Eastern", dto.getTimeZoneDescr());
        Assert.assertEquals("29933-MILEY", dto.getIdStr());
        Assert.assertEquals(560.0, dto.getCountiesArea());
        Assert.assertEquals("49", dto.getCountyFips());
        Assert.assertEquals(32.94768, dto.getLatitude());
        Assert.assertEquals(-81.03192, dto.getLongitude());
    }

    @Test
    public void testFetchByZipCode() {
        ZipcodeDto criteria = LdapAddressBookDtoFactory
                .getZipCodeInstance(null);
        criteria.setZip(71106);
        List<ZipcodeDto> list = null;
        try {
            list = this.dao.fetchZipCode(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(list);
        Assert.assertEquals(4, list.size());
    }

    @Test
    public void testFetchByStatenameCityAndZip() {
        ZipcodeDto criteria = LdapAddressBookDtoFactory
                .getZipCodeInstance(null);
        criteria.setStateName("Texas");
        criteria.setCity("Flower Mound");
        criteria.setZip(75028);
        List<ZipcodeDto> list = null;
        try {
            list = this.dao.fetchZipCode(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(list);
        Assert.assertEquals(3, list.size());
    }

    @Test
    public void testFetchByStatenameCityAndZipId() {
        ZipcodeDto criteria = LdapAddressBookDtoFactory
                .getZipCodeInstance(null);
        criteria.setStateName("Louisiana");
        criteria.setCity("shreveport");
        criteria.setIdStr("71106-SHREVEPORT");
        List<ZipcodeDto> list = null;
        try {
            list = this.dao.fetchZipCode(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(list);
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void testFetchByStatecodeAndCity() {
        ZipcodeDto criteria = LdapAddressBookDtoFactory
                .getZipCodeInstance(null);
        criteria.setStateCode("tx");
        criteria.setCity("San Antonio");
        List<ZipcodeDto> list = null;
        try {
            list = this.dao.fetchZipCode(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(list);
        Assert.assertEquals(125, list.size());
    }

    @Test
    public void testFetchByAreaCode() {
        ZipcodeDto criteria = LdapAddressBookDtoFactory
                .getZipCodeInstance(null);
        criteria.setAreaCode("214");
        List<ZipcodeDto> list = null;
        try {
            list = this.dao.fetchZipCode(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void testFetchByStateError() {
        ZipcodeDto criteria = LdapAddressBookDtoFactory
                .getZipCodeInstance(null);
        criteria.setStateCode("TX");
        try {
            this.dao.fetchZipCode(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Assert.fail("Test failed...an exception should have been thrown due to querying on state selection criteria alone");
    }

    @Test
    public void testFetchTimeZoneByTimezoneId() {
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
    public void testFetchSingleTimeZoneByDto() {
        int uid = 6;
        TimeZoneDto criteria = LdapAddressBookDtoFactory
                .getTimezoneInstance(null);
        List<TimeZoneDto> results = null;
        criteria.setTimeZoneId(uid);
        try {
            results = this.dao.fetchTimezone(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertTrue(results.get(0) instanceof TimeZoneDto);
        TimeZoneDto dto = results.get(0);
        Assert.assertEquals("Central", dto.getTimeZoneDescr());
        Assert.assertEquals(6, dto.getTimeZoneId());
    }

    @Test
    public void testFetchAllTimeZones() {
        List<TimeZoneDto> results = null;
        try {
            results = this.dao.fetchTimezone(null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(12, results.size());
    }

    @Test
    public void testFetchIpInfoUsingLongValue() {
        long ip = 1194139145;
        IpLocationDto dto = null;
        try {
            dto = this.dao.fetchIpInfo(ip);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(dto);
    }

    @Test
    public void testFetchIpInfoUsingStringValue() {
        String ip1 = "71.252.210.138";
        String ip2 = "192.168.0.101";
        String ip3 = "151.193.220.29";
        IpLocationDto dto = null;
        try {
            dto = this.dao.fetchIpInfo(ip1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(dto);

        try {
            dto = this.dao.fetchIpInfo(ip2);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(dto);

        try {
            dto = this.dao.fetchIpInfo(ip3);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(dto);
    }
}
