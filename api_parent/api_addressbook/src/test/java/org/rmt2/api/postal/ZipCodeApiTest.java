package org.rmt2.api.postal;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.dao.lookup.LookupDaoException;
import org.dao.mapping.orm.rmt2.Zipcode;
import org.dto.ZipcodeDto;
import org.dto.adapter.orm.Rmt2AddressBookDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.postal.PostalApi;
import org.modules.postal.PostalApiException;
import org.modules.postal.PostalApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.BaseDaoTest;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class })
public class ZipCodeApiTest extends BaseDaoTest {
    private List<Zipcode> mockSingleFetchResponse;
    private List<Zipcode> mockCriteriaFetchResponse;
    private List<Zipcode> mockFetchAllResponse;
    private List<Zipcode> mockNotFoundFetchResponse;

    @Before
    public void setUp() throws Exception {
        APP_NAME = "addressbook";
        super.setUp();
        this.mockSingleFetchResponse = this.createMockSingleFetchResponse();
        this.mockCriteriaFetchResponse = this.createMockFetchUsingCriteriaResponse();
        this.mockFetchAllResponse = this.createMockFetchAllResponse();
        this.mockNotFoundFetchResponse = this.createMockNotFoundSearchResultsResponse();
    }

    @After
    public void tearDown() throws Exception {
    }

    private List<Zipcode> createMockNotFoundSearchResultsResponse() {
        List<Zipcode> list = new ArrayList<Zipcode>();
        return list;
    }

    private List<Zipcode> createMockSingleFetchResponse() {
        List<Zipcode> list = new ArrayList<Zipcode>();
        Zipcode p = this.createMockOrm(71106,71106, "LA", "Shreveport", "318", "Caddo", 6);
        list.add(p);
        return list;
    }

    /**
     * Use for the following selection criteria: where last name begins with 'C'
     * 
     * @return
     */
    private List<Zipcode> createMockFetchUsingCriteriaResponse() {
        List<Zipcode> list = new ArrayList<Zipcode>();
        Zipcode p = this.createMockOrm(75232,75232, "TX", "Dallas", "214", "Dallas", 6);
        list.add(p);

        p = this.createMockOrm(75028,75028, "TX", "Flower Mound", "972", "Denton", 6);
        list.add(p);

        return list;
    }

    private List<Zipcode> createMockFetchAllResponse() {
        List<Zipcode> list = new ArrayList<Zipcode>();
        Zipcode p = this.createMockOrm(75232,75232, "TX", "Dallas", "214", "Dallas", 6);
        list.add(p);

        p = this.createMockOrm(75028,75028, "TX", "Flower Mound", "972", "Denton", 6);
        list.add(p);

        p = this.createMockOrm(71106,71106, "LA", "Shreveport", "318", "Caddo", 6);
        list.add(p);

        p = this.createMockOrm(75240,75240, "TX", "Dallas", "214", "Dallas", 6);
        list.add(p);
        
        p = this.createMockOrm(91040,91040, "MO", "Kansas City", "816", "Jackson", 6);
        list.add(p);
        
        return list;
    }

    private ZipcodeDto createMockDto(int zipId, int zipCode, String state, String city, String areaCode, String countyName, int timeZoneId) {
        ZipcodeDto dto = Rmt2AddressBookDtoFactory.getNewZipCodeInstance();
        dto.setId(zipId);
        dto.setZip(zipCode);
        dto.setStateCode(state);
        dto.setCity(city);
        dto.setAreaCode(areaCode);
        dto.setCountyName(countyName);
        dto.setTimeZoneId(timeZoneId);
        dto.setLatitude(382372382323.3883828);
        dto.setLongitude(48484848.4843949);
        dto.setBlackPopulation(239000);
        dto.setWhitePopulation(10000000);
        dto.setHispanicPopulation(30000);
        dto.setCityAliasName(city + "_Alias");
        dto.setAverageHouseValue(87674.84);
        dto.setCbsa(123.88);
        dto.setCbsaDiv(23.88);
        dto.setCityAliasAbbr("AAB");
        dto.setCityTypeId("City_type");
        dto.setCountiesArea(333);
        dto.setCountyFips("Counties FIPS");
        dto.setDayLightSaving("TRUE");
        dto.setElevation(2345.89);
        dto.setHouseholdsPerZipcode(600);
        dto.setIncomePerHousehold(569.76);
        dto.setMsa(757575);
        dto.setPersonsPerHousehold(4);
        dto.setPmsa(123);
        dto.setZipPopulation(25000);
        return dto;
    }
    
    private Zipcode createMockOrm(int zipId, int zipCode, String state, String city, String areaCode, String countyName, int timeZoneId) {
        Zipcode dto = new Zipcode();
        dto.setZipId(zipId);
        dto.setZip(zipCode);
        dto.setState(state);
        dto.setCity(city);
        dto.setAreaCode(areaCode);
        dto.setCountyName(countyName);
        dto.setTimeZoneId(timeZoneId);
        dto.setLatitude(382372382323.3883828);
        dto.setLongitude(48484848.4843949);
        dto.setBlackPopulation(239000);
        dto.setWhitePopulation(10000000);
        dto.setHispanicPopulation(30000);
        dto.setCityAliasName(city + "_Alias");
        dto.setAverageHouseValue(87674.84);
        dto.setCbsa(123.88);
        dto.setCbsaDiv(23.88);
        dto.setCityAliasAbbr("AAB");
        dto.setCityTypeId("City_type");
        dto.setCountiesArea(333);
        dto.setCountyFips("Counties FIPS");
        dto.setDayLightSaving("TRUE");
        dto.setElevation(2345.89);
        dto.setHouseholdsPerZipcode(600);
        dto.setIncomePerHousehold(569.76);
        dto.setMsa(757575);
        dto.setPersonsPerHousehold(4);
        dto.setPmsa(123);
        dto.setZipcodePopulation(25000);
        return dto;
    }
    @Test
    public void testFetchAll() {
        ZipcodeDto criteria = Rmt2AddressBookDtoFactory.getNewZipCodeInstance();
        criteria.setTimeZoneId(6);
        try {
            when(this.mockPersistenceClient.retrieveList(any(Zipcode.class))).thenReturn(this.mockFetchAllResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("All zipcode fetch test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(APP_NAME);
        List<ZipcodeDto> results = null;
        try {
            results = api.getZipCode(criteria);
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
    }
    
    @Test
    public void testFetchWithEmptyCriteria() {
        ZipcodeDto criteria = Rmt2AddressBookDtoFactory.getNewZipCodeInstance();
        try {
            when(this.mockPersistenceClient.retrieveList(any(Zipcode.class))).thenReturn(this.mockFetchAllResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("zipcode fetch with empty criteria test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(APP_NAME);
        try {
            api.getZipCode(criteria);
            Assert.fail("Expected exception to be thrown due to empty criteria");
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchWithNullCriteria() {
        ZipcodeDto criteria = null;
        try {
            when(this.mockPersistenceClient.retrieveList(any(Zipcode.class))).thenReturn(this.mockFetchAllResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("zipcode fetch with null criteria test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(APP_NAME);
        try {
            api.getZipCode(criteria);
            Assert.fail("Expected exception to be thrown due to null criteria");
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFetchSingle() {
        ZipcodeDto criteria = Rmt2AddressBookDtoFactory.getNewZipCodeInstance();
        criteria.setZip(71106);
        try {
            when(this.mockPersistenceClient.retrieveList(any(Zipcode.class))).thenReturn(this.mockSingleFetchResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Single zipcode fetch test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(APP_NAME);
        List<ZipcodeDto> results = null;
        try {
            results = api.getZipCode(criteria);
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        ZipcodeDto rec = results.get(0);
        Assert.assertEquals(71106, rec.getId());
        Assert.assertEquals(71106, rec.getZip());
        Assert.assertEquals("Shreveport", rec.getCity());
        Assert.assertEquals("LA", rec.getStateCode());
        Assert.assertEquals("318", rec.getAreaCode());
    }
    
    @Test
    public void testFetchByUid() {
        try {
            when(this.mockPersistenceClient.retrieveList(any(Zipcode.class))).thenReturn(this.mockSingleFetchResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("UID zipcode fetch test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(APP_NAME);
        ZipcodeDto rec = null;
        try {
            rec = api.getZipCode(71106);
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(rec);
        Assert.assertEquals(71106, rec.getId());
        Assert.assertEquals(71106, rec.getZip());
        Assert.assertEquals("Shreveport", rec.getCity());
        Assert.assertEquals("LA", rec.getStateCode());
        Assert.assertEquals("318", rec.getAreaCode());
    }
}
