package org.rmt2.api.postal;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.dao.lookup.LookupDaoException;
import org.dao.mapping.orm.rmt2.VwStateCountry;
import org.dto.CountryRegionDto;
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
public class CountryRegionApiTest extends BaseAddressBookDaoTest {
    private List<VwStateCountry> mockSingleFetchResponse;
    private List<VwStateCountry> mockCriteriaFetchResponse;
    private List<VwStateCountry> mockFetchAllResponse;
    private List<VwStateCountry> mockNotFoundFetchResponse;

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

    private List<VwStateCountry> createMockNotFoundSearchResultsResponse() {
        List<VwStateCountry> list = null;
        return list;
    }

    private List<VwStateCountry> createMockSingleFetchResponse() {
        List<VwStateCountry> list = new ArrayList<VwStateCountry>();
        VwStateCountry o = new VwStateCountry();
        o.setStateId(10);
        o.setStateCode("TX");
        o.setStateName("Texas");
        o.setCountryId(100);
        o.setCountryName("United States");
        list.add(o);
        return list;
    }

    /**
     * Use for the following selection criteria: where last name begins with 'T'
     * 
     * @return
     */
    private List<VwStateCountry> createMockFetchUsingCriteriaResponse() {
        List<VwStateCountry> list = new ArrayList<VwStateCountry>();
        VwStateCountry o = new VwStateCountry();
        o.setStateId(10);
        o.setStateCode("TX");
        o.setStateName("Texas");
        o.setCountryId(100);
        o.setCountryName("United States");
        list.add(o);

        o = new VwStateCountry();
        o.setStateId(20);
        o.setStateCode("TN");
        o.setStateName("Tennessee");
        o.setCountryId(100);
        o.setCountryName("United States");
        list.add(o);

        return list;
    }

    private List<VwStateCountry> createMockFetchAllResponse() {
        List<VwStateCountry> list = new ArrayList<VwStateCountry>();
        VwStateCountry o = new VwStateCountry();
        o.setStateId(10);
        o.setStateCode("TX");
        o.setStateName("Texas");
        o.setCountryId(100);
        o.setCountryName("United States");
        list.add(o);

        o = new VwStateCountry();
        o.setStateId(20);
        o.setStateCode("TN");
        o.setStateName("Tennessee");
        o.setCountryId(100);
        o.setCountryName("United States");
        list.add(o);
        
        o = new VwStateCountry();
        o.setStateId(30);
        o.setStateCode("LA");
        o.setStateName("Louisiana");
        o.setCountryId(100);
        o.setCountryName("United States");
        list.add(o);
        
        o = new VwStateCountry();
        o.setStateId(40);
        o.setStateCode("CA");
        o.setStateName("California");
        o.setCountryId(100);
        o.setCountryName("United States");
        list.add(o);
        
        o = new VwStateCountry();
        o.setStateId(50);
        o.setStateCode("NY");
        o.setStateName("New York");
        o.setCountryId(100);
        o.setCountryName("United States");
        list.add(o);
        return list;
    }

    private CountryRegionDto createMockDto(int countryId, String countryName, String countryCode, int stateId, String stateName, String stateCode) {
        CountryRegionDto dto = Rmt2AddressBookDtoFactory.getNewCountryRegionInstance();
        dto.setStateId(stateId);
        dto.setStateCode(stateCode);
        dto.setStateName(stateName);
        dto.setCountryId(countryId);
        dto.setCountryName(countryName);
        dto.setCountryCode(countryCode);
        return dto;
    }
    @Test
    public void testFetchAll() {
        CountryRegionDto criteria = Rmt2AddressBookDtoFactory.getNewCountryRegionInstance();
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwStateCountry.class))).thenReturn(this.mockFetchAllResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("All country state/region fetch test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<CountryRegionDto> results = null;
        try {
            results = api.getCountryRegion(criteria);
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
    }

    @Test
    public void testFetchSingle() {
        CountryRegionDto criteria = Rmt2AddressBookDtoFactory.getNewCountryRegionInstance();
        criteria.setStateId(10);
        criteria.setCountryId(100);
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwStateCountry.class))).thenReturn(this.mockSingleFetchResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Single country state/region fetch test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<CountryRegionDto> results = null;
        try {
            results = api.getCountryRegion(criteria);
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        CountryRegionDto state = (CountryRegionDto) results.get(0);
        Assert.assertNotNull(state);
        Assert.assertEquals(10, state.getStateId());
        Assert.assertEquals("TX", state.getStateCode());
        Assert.assertEquals("Texas", state.getStateName());
    }
    
   

   

    @Test
    public void testNotFoundlFetch() {
        CountryRegionDto criteria = Rmt2AddressBookDtoFactory.getNewCountryRegionInstance();
        criteria.setStateId(199);
        criteria.setCountryId(9999);
        try {
            when(this.mockPersistenceClient.retrieveList(any(VwStateCountry.class))).thenReturn(this.mockNotFoundFetchResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Single country state/region fetch test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<CountryRegionDto> results = null;
        try {
            results = api.getCountryRegion(criteria);
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }

    @Test
    public void testFetchUsingNullCriteria() {
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.getCountryRegion(null);
            Assert.fail("Expected exception to be thrown due to null criteria object input");
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
    }
}
