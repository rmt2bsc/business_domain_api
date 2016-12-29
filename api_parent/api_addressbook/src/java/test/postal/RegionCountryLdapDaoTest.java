package postal;

import java.util.List;

import junit.framework.Assert;

import org.dao.postal.PostalDaoFactory;
import org.dao.postal.RegionCountryDao;
import org.dto.CountryDto;
import org.dto.RegionDto;
import org.dto.adapter.ldap.LdapAddressBookDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the /JNDILDAP implemetation of the Postal Locatione DAO
 * 
 * @author rterrell
 * 
 */
public class RegionCountryLdapDaoTest {

    private PostalDaoFactory f;

    private RegionCountryDao dao;

    private int usCountryId;

    private String usCountryName;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.f = new PostalDaoFactory();
        this.dao = this.f.createLdapRegionCountryDao();
        this.usCountryId = 1;
        this.usCountryName = "United States";
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
    public void fetchSingleCountry() {
        CountryDto dto = null;
        try {
            dto = this.dao.fetchCountry(this.usCountryId);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(dto);
        Assert.assertEquals(this.usCountryName, dto.getCountryName());
    }

    @Test
    public void fetchCountryUsingOneArgCriteria() {
        CountryDto criteria = LdapAddressBookDtoFactory
                .getCountryInstance(null);
        criteria.setCountryName("U");
        List<CountryDto> results = null;
        try {
            results = this.dao.fetchCountry(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(9, results.size());
    }

    @Test
    public void fetchCountryUsingMultipleArgCriteria() {
        CountryDto criteria = LdapAddressBookDtoFactory
                .getCountryInstance(null);
        criteria.setCountryName("Ve");
        criteria.setCountryCode("V");
        List<CountryDto> results = null;
        try {
            results = this.dao.fetchCountry(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
    }

    @Test
    public void fetchCountryUsingIdArgCriteria() {
        CountryDto criteria = LdapAddressBookDtoFactory
                .getCountryInstance(null);
        criteria.setCountryId(131);
        List<CountryDto> results = null;
        try {
            results = this.dao.fetchCountry(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("Macau", results.get(0).getCountryName());
    }

    @Test
    public void fetchCountryAll() {
        CountryDto criteria = null;
        List<CountryDto> results = null;
        try {
            results = this.dao.fetchCountry(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(240, results.size());
    }

    @Test
    public void fetchRegion() {
        RegionDto results = LdapAddressBookDtoFactory.getRegionInstance(null);
        int regionId = 69; // Texas
        try {
            results = this.dao.fetchRegion(regionId);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertEquals("Texas", results.getStateName());
    }

    @Test
    public void fetchRegionUsingCriteria() {
        RegionDto criteria = LdapAddressBookDtoFactory.getRegionInstance(null);
        criteria.setCountryName("United States");
        criteria.setStateName("w");
        List<RegionDto> results = null;
        try {
            results = this.dao.fetchRegion(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(4, results.size());
    }

    @Test
    public void fetchUnitedStatesRegions() {
        RegionDto criteria = LdapAddressBookDtoFactory.getRegionInstance(null);
        criteria.setCountryName("United States");
        List<RegionDto> results = null;
        try {
            results = this.dao.fetchRegion(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(60, results.size());
    }

    //
    // @Test
    // public void testStateFetchSingle() {
    // RegionDto dto = null;
    // try {
    // dto = this.dao.fetchRegion(this.newYorkstateId);
    // }
    // catch (Exception e) {
    // e.printStackTrace();
    // Assert.fail(e.getMessage());
    // }
    // Assert.assertNotNull(dto);
    // Assert.assertEquals(this.stateName, dto.getStateName());
    // }
    //
    // @Test
    // public void testStateFetchMultiple() {
    // RegionDto criteria = Rmt2AddressBookDtoFactory.getNewRegionInstance();
    // criteria.setCountryId(usCountryId);
    // criteria.setStateName("N");
    // List<RegionDto> results = null;
    // try {
    // results = this.dao.fetchRegion(criteria);
    // }
    // catch (Exception e) {
    // e.printStackTrace();
    // Assert.fail(e.getMessage());
    // }
    // Assert.assertNotNull(results);
    // Assert.assertEquals(9, results.size());
    // }
    //
    // @Test
    // public void testStateFetchAll() {
    // RegionDto criteria = null;
    // List<RegionDto> results = null;
    // try {
    // results = this.dao.fetchRegion(criteria);
    // }
    // catch (Exception e) {
    // e.printStackTrace();
    // Assert.fail(e.getMessage());
    // }
    // Assert.assertNotNull(results);
    // Assert.assertTrue(results.size() > 0);
    // }
    //
    // @Test
    // public void testCountryStateFetchMultiple() {
    // CountryRegionDto criteria =
    // Rmt2AddressBookDtoFactory.getNewCountryRegionInstance();
    // criteria.setCountryId(this.usCountryId);
    // criteria.setStateName("T");
    // List<CountryRegionDto> results = null;
    // try {
    // results = this.dao.fetchCountryRegion(criteria);
    // }
    // catch (Exception e) {
    // e.printStackTrace();
    // Assert.fail(e.getMessage());
    // }
    // Assert.assertNotNull(results);
    // Assert.assertEquals(2, results.size());
    // }
    //
    // @Test
    // public void testRegionPersistence() {
    // int rc = 0;
    // RegionDto dto = Rmt2AddressBookDtoFactory.getNewRegionInstance();
    //
    // // Create record
    // try {
    // dto.setCountryId(1);
    // dto.setStateCode("XX");
    // dto.setStateName("TEST STATE");
    // dto.setStatePermcol("N");
    // rc = this.dao.maintainRegion(dto);
    // }
    // catch (Exception e) {
    // e.printStackTrace();
    // Assert.fail(e.getMessage());
    // }
    // Assert.assertTrue(rc > 0);
    // Assert.assertEquals(rc, dto.getStateId());
    //
    // // Updaete record
    // String modStateName = "MODIFIED TEST STATE";
    // try {
    // dto.setStateName(modStateName);
    // rc = this.dao.maintainRegion(dto);
    // }
    // catch (Exception e) {
    // e.printStackTrace();
    // Assert.fail(e.getMessage());
    // }
    // Assert.assertTrue(rc > 0);
    // Assert.assertEquals(1, rc);
    //
    // // Verify update
    // RegionDto dto2 = null;
    // try {
    // dto2 = this.dao.fetchRegion(dto.getStateId());
    // }
    // catch (Exception e) {
    // e.printStackTrace();
    // Assert.fail(e.getMessage());
    // }
    // Assert.assertNotNull(dto2);
    // Assert.assertEquals(modStateName, dto2.getStateName());
    //
    // // Delete record
    // try {
    // rc = this.dao.deleteRegion(dto2.getStateId());
    // }
    // catch (Exception e) {
    // e.printStackTrace();
    // Assert.fail(e.getMessage());
    // }
    // Assert.assertTrue(rc > 0);
    // Assert.assertEquals(1, rc);
    //
    // return;
    // }
    //
    //
    // @Test
    // public void testCountryPersistence() {
    // int rc = 0;
    // CountryDto dto = Rmt2AddressBookDtoFactory.getNewCountryInstance();
    //
    // // Create record
    // try {
    // dto.setCountryCode("XX");
    // dto.setCountryName("TEST COUNTRY");
    // dto.setCountryPermcol("N");
    // rc = this.dao.maintainCountry(dto);
    // }
    // catch (Exception e) {
    // e.printStackTrace();
    // Assert.fail(e.getMessage());
    // }
    // Assert.assertTrue(rc > 0);
    // Assert.assertEquals(rc, dto.getCountryId());
    //
    // // Updaete record
    // String modCountryName = "MODIFIED TEST COUNTRY";
    // try {
    // dto.setCountryName(modCountryName);
    // rc = this.dao.maintainCountry(dto);
    // }
    // catch (Exception e) {
    // e.printStackTrace();
    // Assert.fail(e.getMessage());
    // }
    // Assert.assertTrue(rc > 0);
    // Assert.assertEquals(1, rc);
    //
    // // Verify update
    // CountryDto dto2 = null;
    // try {
    // dto2 = this.dao.fetchCountry(dto.getCountryId());
    // }
    // catch (Exception e) {
    // e.printStackTrace();
    // Assert.fail(e.getMessage());
    // }
    // Assert.assertNotNull(dto2);
    // Assert.assertEquals(modCountryName, dto2.getCountryName());
    //
    // // Delete record
    // try {
    // rc = this.dao.deleteCountry(dto2.getCountryId());
    // }
    // catch (Exception e) {
    // e.printStackTrace();
    // Assert.fail(e.getMessage());
    // }
    // Assert.assertTrue(rc > 0);
    // Assert.assertEquals(1, rc);
    //
    // return;
    // }

}
