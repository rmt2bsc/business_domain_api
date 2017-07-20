package org.rmt2.api.postal;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dao.lookup.LookupDaoException;
import org.dao.mapping.orm.rmt2.Country;
import org.dto.CountryDto;
import org.dto.adapter.orm.Rmt2AddressBookDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.AddressBookConstants;
import org.modules.postal.PostalApi;
import org.modules.postal.PostalApiException;
import org.modules.postal.PostalApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.BaseAddressBookDaoTest;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class })
public class CountryApiTest extends BaseAddressBookDaoTest {
    private List<Country> mockSingleFetchResponse;
    private List<Country> mockCriteriaFetchResponse;
    private List<Country> mockFetchAllResponse;
    private List<Country> mockNotFoundFetchResponse;

    @Before
    public void setUp() throws Exception {
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
        CountryDto criteria = Rmt2AddressBookDtoFactory.getNewCountryInstance();
        try {
            when(this.mockPersistenceClient.retrieveList(any(Country.class))).thenReturn(this.mockFetchAllResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("All country fetch test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<CountryDto> results = null;
        try {
            results = api.getCountry(criteria);
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(4, results.size());
    }

    @Test
    public void testFetchAllWithNullCriteriaObject() {
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.getCountry(null);
            Assert.fail("Expected exception to be thrown");
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFetchSingle() {
        CountryDto criteria = Rmt2AddressBookDtoFactory.getNewCountryInstance();
        criteria.setCountryId(200);
        try {
            when(this.mockPersistenceClient.retrieveList(any(Country.class))).thenReturn(this.mockSingleFetchResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Fetch single country test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<CountryDto> results = null;
        try {
            results = api.getCountry(criteria);
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
    }

    @Test
    public void testFetchByCodeId() {
        CountryDto criteria = Rmt2AddressBookDtoFactory.getNewCountryInstance();
        criteria.setCountryId(200);
        try {
            when(this.mockPersistenceClient.retrieveObject(any(Country.class)))
                    .thenReturn(this.mockSingleFetchResponse.get(0));
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Fetch country by country id test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        CountryDto results = null;
        try {
            results = api.getCountry(criteria.getCountryId());
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals("United States", results.getCountryName());
    }

    @Test
    public void testFetchUsingCriteria() {
        CountryDto criteria = Rmt2AddressBookDtoFactory.getNewCountryInstance();
        criteria.setCountryName("United");
        try {
            when(this.mockPersistenceClient.retrieveList(any(Country.class)))
                    .thenReturn(this.mockCriteriaFetchResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Fetch country using criteria object test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<CountryDto> results = null;
        try {
            results = api.getCountry(criteria);
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
    }

    @Test
    public void testNotFoundlFetch() {
        CountryDto criteria = Rmt2AddressBookDtoFactory.getNewCountryInstance();
        criteria.setCountryName("Dummy Test");
        try {
            when(this.mockPersistenceClient.retrieveList(any(Country.class)))
                    .thenReturn(this.mockNotFoundFetchResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Not Found country fetch test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<CountryDto> results = null;
        try {
            results = api.getCountry(criteria);
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }

    @Test
    public void testUpdate() {
        Country mockOriginalCountry = new Country();
        mockOriginalCountry.setCountryId(200);
        mockOriginalCountry.setName("United States");
        mockOriginalCountry.setCode("USA");
        CountryDto mockUpdateCountry = this.createMockDto(200, "Modified United States", "MUSA");

        try {
            when(this.mockPersistenceClient.retrieveObject(any(Country.class))).thenReturn(mockOriginalCountry);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Country update test case failed setting up mock retrieve call");
        }
        try {
            when(this.mockPersistenceClient.updateRow(any(Country.class))).thenReturn(1);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Country update test case failed setting up mock update call");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.updateCountry(mockUpdateCountry);
        } catch (Exception e) {
            Assert.fail("COuntry Code API update test case failed");
            e.printStackTrace();
        }
        Assert.assertEquals(1, rc);
        Assert.assertEquals(200, mockUpdateCountry.getCountryId());
        Assert.assertEquals("MUSA", mockUpdateCountry.getCountryCode());
        Assert.assertEquals("Modified United States", mockUpdateCountry.getCountryName());
    }

    @Test
    public void testUpdateWithoutCountryName() {
        Country mockOriginalCountry = new Country();
        mockOriginalCountry.setCountryId(200);
        mockOriginalCountry.setName(null);
        mockOriginalCountry.setCode("USA");
        CountryDto mockUpdateCountry = this.createMockDto(200, null, "USA");

        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.updateCountry(mockUpdateCountry);
            Assert.fail("Expected validation error for null country name");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsert() {
        CountryDto mockUpdateCountry = this.createMockDto(0, "United States", "USA");
        try {
            when(this.mockPersistenceClient.insertRow(any(Country.class), any(Boolean.class))).thenReturn(123);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Country insert test case failed setting up mock update call");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.updateCountry(mockUpdateCountry);
        } catch (Exception e) {
            Assert.fail("Country API insert test case failed");
            e.printStackTrace();
        }
        Assert.assertEquals(123, rc);
        Assert.assertEquals(123, mockUpdateCountry.getCountryId());
        Assert.assertEquals("USA", mockUpdateCountry.getCountryCode());
        Assert.assertEquals("United States", mockUpdateCountry.getCountryName());
    }

    @Test
    public void testInsertWithNullCountryName() {
        CountryDto mockUpdateCountry = this.createMockDto(0, null, "USA");
        try {
            when(this.mockPersistenceClient.insertRow(any(Country.class), any(Boolean.class))).thenReturn(123);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Country insert test case failed setting up mock update call");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.updateCountry(mockUpdateCountry);
            Assert.fail("Expected validation error for null country name");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        CountryDto criteria = Rmt2AddressBookDtoFactory.getNewCountryInstance();
        criteria.setCountryId(200);
        try {
            when(this.mockPersistenceClient.deleteRow(any(Country.class))).thenReturn(1);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Delete country by country id test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        int results = 0;
        try {
            results = api.deleteCountry(criteria.getCountryId());
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, results);
    }

    @Test
    public void testDeleteWithZeroCountryId() {
        CountryDto criteria = Rmt2AddressBookDtoFactory.getNewCountryInstance();
        criteria.setCountryId(0);

        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        int results = 0;
        try {
            results = api.deleteCountry(criteria.getCountryId());
            Assert.fail("Expected validation error for zero country id value");
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteWithNegativeCountryId() {
        CountryDto criteria = Rmt2AddressBookDtoFactory.getNewCountryInstance();
        criteria.setCountryId(-123);

        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        int results = 0;
        try {
            results = api.deleteCountry(criteria.getCountryId());
            Assert.fail("Expected validation error for negative country id value");
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
    }
}
