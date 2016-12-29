package postal;

import java.util.List;

import junit.framework.Assert;

import org.dto.CountryDto;
import org.dto.CountryRegionDto;
import org.dto.IpLocationDto;
import org.dto.RegionDto;
import org.dto.TimeZoneDto;
import org.dto.ZipcodeDto;
import org.dto.adapter.ldap.LdapAddressBookDtoFactory;
import org.dto.adapter.orm.Rmt2AddressBookDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.modules.postal.PostalApi;
import org.modules.postal.PostalApiFactory;

/**
 * Tests the Posal API transactions.
 * 
 * @author rterrell
 * 
 */
public class PostalApiTest {
    private PostalApiFactory f;
    private PostalApi api;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.f = new PostalApiFactory();
        this.api = this.f.createApi();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.api = null;
        this.f = null;
    }

    @Test
    public void testCountryFetchSingle() {
        CountryDto dto = null;
        try {
            dto = this.api.getCountry(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(dto);
    }

    @Test
    public void testCountryFetchMultiple() {
        CountryDto criteria = Rmt2AddressBookDtoFactory.getNewCountryInstance();
        criteria.setCountryName("U");
        List<CountryDto> results = null;

        try {
            results = this.api.getCountry(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
    }

    @Test
    public void testRegionFetchSingle() {
        RegionDto dto = null;
        try {
            dto = this.api.getRegion(53);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(dto);
    }

    @Test
    public void testRegionFetchMultiple() {
        RegionDto criteria = Rmt2AddressBookDtoFactory.getNewRegionInstance();
        criteria.setCountryId(1);
        criteria.setStateName("N");
        List<RegionDto> results = null;
        try {
            results = this.api.getRegion(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
    }

    @Test
    public void testZipFetchSingle() {
        int uid = 64738;
        ZipcodeDto dto = null;
        try {
            dto = this.api.getZipCode(uid);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(dto);
    }

    @Test
    public void testMultipleZipFetch() {
        String state = "TX";
        String city = "Dallas";
        List<ZipcodeDto> list = null;

        try {
            ZipcodeDto criteria = LdapAddressBookDtoFactory
                    .getZipCodeInstance(null);
            criteria.setStateCode(state);
            criteria.setCity(city);
            list = this.api.getZipCode(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void testInvalidSearchCriteriaZipFetch() {
        List<ZipcodeDto> list = null;

        try {
            list = this.api.getZipCode(null);
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
            ZipcodeDto criteria = LdapAddressBookDtoFactory
                    .getZipCodeInstance(null);
            list = this.api.getZipCode(criteria);
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
            dto = this.api.getTimezone(uid);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(dto);
        Assert.assertEquals("Central", dto.getTimeZoneDescr());
    }

    @Test
    public void testTimeZoneFetchUsingDto() {
        String descr = "Mountain";
        List<TimeZoneDto> list = null;

        try {
            TimeZoneDto criteria = LdapAddressBookDtoFactory
                    .getTimezoneInstance(null);
            criteria.setTimeZoneDescr(descr);
            list = this.api.getTimezone(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void testIpLocationFetchUsingAddress() {
        String ip = "71.252.210.138";
        // String ip = "64.89.115.110";
        // String ip = "204.0.69.201";
        IpLocationDto dto = null;

        try {
            dto = this.api.getIpInfo(ip);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(dto);
    }

    @Test
    public void testStateFetchAll() {
        RegionDto criteria = null;
        List<RegionDto> results = null;
        try {
            results = this.api.getRegion(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertTrue(results.size() > 0);
    }

    @Test
    public void testCountryStateFetchMultiple() {
        CountryRegionDto criteria = Rmt2AddressBookDtoFactory
                .getNewCountryRegionInstance();
        criteria.setCountryId(1);
        criteria.setStateName("T");
        List<CountryRegionDto> results = null;
        try {
            results = this.api.getCountryRegion(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
    }

    @Test
    public void testRegionCreateUpdateDelete() {
        int rc = 0;
        RegionDto dto = Rmt2AddressBookDtoFactory.getNewRegionInstance();

        // Create record
        try {
            dto.setCountryId(1);
            dto.setStateCode("XX");
            dto.setStateName("TEST STATE");
            dto.setStatePermcol("N");
            rc = this.api.updateRegion(dto);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc > 0);
        Assert.assertEquals(rc, dto.getStateId());

        // Updaete record
        String modStateName = "MODIFIED TEST STATE";
        try {
            dto.setStateName(modStateName);
            rc = this.api.updateRegion(dto);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc > 0);
        Assert.assertEquals(1, rc);

        // Verify update
        RegionDto dto2 = null;
        try {
            dto2 = this.api.getRegion(dto.getStateId());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(dto2);
        Assert.assertEquals(modStateName, dto2.getStateName());

        // Delete record
        try {
            rc = this.api.deleteRegion(dto2.getStateId());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc > 0);
        Assert.assertEquals(1, rc);

        return;
    }

    @Test
    public void testCountryCreateUpdateDelete() {
        int rc = 0;
        CountryDto dto = Rmt2AddressBookDtoFactory.getNewCountryInstance();

        // Create record
        try {
            dto.setCountryCode("XX");
            dto.setCountryName("TEST COUNTRY");
            dto.setCountryPermcol("N");
            rc = this.api.updateCountry(dto);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc > 0);
        Assert.assertEquals(rc, dto.getCountryId());

        // Updaete record
        String modCountryName = "MODIFIED TEST COUNTRY";
        try {
            dto.setCountryName(modCountryName);
            rc = this.api.updateCountry(dto);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc > 0);
        Assert.assertEquals(1, rc);

        // Verify update
        CountryDto dto2 = null;
        try {
            dto2 = this.api.getCountry(dto.getCountryId());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(dto2);
        Assert.assertEquals(modCountryName, dto2.getCountryName());

        // Delete record
        try {
            rc = this.api.deleteCountry(dto2.getCountryId());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(rc > 0);
        Assert.assertEquals(1, rc);

        return;
    }

}
