package org.rmt2.api.postal;

import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.Zipcode;
import org.dto.ZipcodeDto;
import org.dto.adapter.orm.Rmt2AddressBookDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
        Assert.fail("test Failed");
    }

    @Test
    public void testFetchSingle() {
        Assert.fail("test Failed");
    }
    
    @Test
    public void testFetchByUid() {
        Assert.fail("test Failed");
    }

    @Test
    public void testFetchUsingCriteria() {
        Assert.fail("test Failed");
    }

    @Test
    public void testNotFoundlFetch() {
        Assert.fail("test Failed");
    }

    @Test
    public void testFetchUsingCriteriaNullResults() {
        Assert.fail("test Failed");
    }
    
    @Test
    public void testFetchUsingNullCriteria() {
        Assert.fail("test Failed");
    }

    @Test
    public void testUpdate() {
        Assert.fail("test Failed");
    }
    
    @Test
    public void testUpdateWithoutGroupId() {
        Assert.fail("test Failed");
    }
    
    @Test
    public void testUpdateWithoutLongDescription() {
        Assert.fail("test Failed");
    }
    
    @Test
    public void testUpdateWithoutShortDescription() {
        Assert.fail("test Failed");
    }
    
    @Test
    public void testUpdateWithoutAnyDescriptions() {
        Assert.fail("test Failed");
    }

    @Test
    public void testInsert() {
        Assert.fail("test Failed");
    }

    @Test
    public void testDelete() {
        Assert.fail("test Failed");
    }

    @Test
    public void testDeleteWithInvalidCodeId() {
        Assert.fail("test Failed");
    }
}
