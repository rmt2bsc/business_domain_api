package org.rmt2.api;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dao.mapping.orm.rmt2.Country;
import org.dto.CountryDto;
import org.dto.adapter.orm.Rmt2AddressBookDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class })
public class ExampleApiTest extends BaseDaoTest {
    private List<Country> mockSingleFetchResponse;
    private List<Country> mockCriteriaFetchResponse;
    private List<Country> mockFetchAllResponse;
    private List<Country> mockNotFoundFetchResponse;

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

    private List<Country> createMockNotFoundSearchResultsResponse() {
        List<Country> list = null;
        return list;
    }

    private List<Country> createMockSingleFetchResponse() {
        List<Country> list = new ArrayList<Country>();
        Country o = new Country();
        o.setCountryId(200);
        o.setName("United States");
        o.setCode("USA");

        list.add(o);
        return list;
    }

    /**
     * Use for the following selection criteria: where last name begins with 'C'
     * 
     * @return
     */
    private List<Country> createMockFetchUsingCriteriaResponse() {
        List<Country> list = new ArrayList<Country>();
        Country o = new Country();
        o.setCountryId(200);
        o.setName("United States");
        o.setCode("USA");
        list.add(o);

        o = new Country();
        o.setCountryId(300);
        o.setName("United Kingdom");
        o.setCode("UK");
        list.add(o);

        return list;
    }

    private List<Country> createMockFetchAllResponse() {
        List<Country> list = new ArrayList<Country>();
        Country o = new Country();
        o.setCountryId(200);
        o.setName("United States");
        o.setCode("USA");
        list.add(o);

        o = new Country();
        o.setCountryId(300);
        o.setName("Mexico");
        o.setCode("MEX");
        list.add(o);

        o = new Country();
        o.setCountryId(100);
        o.setName("Canada");
        o.setCode("CAN");
        list.add(o);

        o = new Country();
        o.setCountryId(400);
        o.setName("France");
        o.setCode("FRA");
        list.add(o);
        return list;
    }

    private CountryDto createMockDto(int countryId, String name, String code) {
        CountryDto dto = Rmt2AddressBookDtoFactory.getNewCountryInstance();
        dto.setCountryId(countryId);
        dto.setCountryCode(code);
        dto.setCountryName(name);
        dto.setUpdateUserId("test_user");
        dto.setDateCreated(new Date());
        dto.setDateUpdated(new Date());
        return dto;
    }

    @Test
    public void testFetchAll() {
        Assert.fail("Test Failed");
    }

    @Test
    public void testFetchWithNullCriteriaObject() {
        Assert.fail("Test Failed");
    }

    @Test
    public void testFetchSingle() {
        Assert.fail("Test Failed");
    }

    @Test
    public void testFetchByUid() {
        Assert.fail("Test Failed");
    }

    @Test
    public void testFetchUsingCriteria() {
        Assert.fail("Test Failed");
    }

    @Test
    public void testNotFoundlFetch() {
        Assert.fail("Test Failed");
    }

    @Test
    public void testUpdate() {
        Assert.fail("Test Failed");
    }

    @Test
    public void testInsert() {
        Assert.fail("Test Failed");
    }

    @Test
    public void testDelete() {
        Assert.fail("Test Failed");
    }

    @Test
    public void testDeleteWithZeroUid() {
        Assert.fail("Test Failed");
    }

}
