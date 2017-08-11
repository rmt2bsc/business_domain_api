package org.rmt2.api.postal;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.dao.lookup.LookupDaoException;
import org.dao.mapping.orm.rmt2.State;
import org.dto.RegionDto;
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
public class RegionApiTest extends BaseAddressBookDaoTest {
    private List<State> mockSingleFetchResponse;
    private List<State> mockCriteriaFetchResponse;
    private List<State> mockFetchAllResponse;
    private List<State> mockNotFoundFetchResponse;

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

    private List<State> createMockNotFoundSearchResultsResponse() {
        List<State> list = null;
        return list;
    }

    private List<State> createMockSingleFetchResponse() {
        List<State> list = new ArrayList<State>();
        State o = new State();
        o.setStateId(10);
        o.setAbbrCode("TX");
        o.setStateName("Texas");
        o.setCountryId(100);
        list.add(o);
        return list;
    }

    /**
     * Use for the following selection criteria: where last name begins with 'T'
     * 
     * @return
     */
    private List<State> createMockFetchUsingCriteriaResponse() {
        List<State> list = new ArrayList<State>();
        State o = new State();
        o.setStateId(10);
        o.setAbbrCode("TX");
        o.setStateName("Texas");
        o.setCountryId(100);
        list.add(o);

        o = new State();
        o.setStateId(20);
        o.setAbbrCode("TN");
        o.setStateName("Tennessee");
        o.setCountryId(100);
        list.add(o);

        return list;
    }

    private List<State> createMockFetchAllResponse() {
        List<State> list = new ArrayList<State>();
        State o = new State();
        o.setStateId(10);
        o.setAbbrCode("AbbrCode1");
        o.setStateName("StateName1");
        o.setCountryId(100);
        list.add(o);

        o = new State();
        o.setStateId(11);
        o.setAbbrCode("AbbrCode2");
        o.setStateName("StateName2");
        o.setCountryId(100);
        list.add(o);
        
        o = new State();
        o.setStateId(12);
        o.setAbbrCode("AbbrCode3");
        o.setStateName("StateName3");
        o.setCountryId(100);
        list.add(o);
        
        o = new State();
        o.setStateId(13);
        o.setAbbrCode("AbbrCode4");
        o.setStateName("StateName4");
        o.setCountryId(100);
        list.add(o);
        
        o = new State();
        o.setStateId(14);
        o.setAbbrCode("AbbrCode5");
        o.setStateName("StateName5");
        o.setCountryId(100);
        list.add(o);
        return list;
    }

    private RegionDto createMockDto(int stateId, int countryId, String stateName, String stateCode) {
        RegionDto dto = Rmt2AddressBookDtoFactory.getNewRegionInstance();
        dto.setStateId(stateId);
        dto.setStateCode(stateCode);
        dto.setStateName(stateName);
        dto.setCountryId(countryId);
        return dto;
    }
    @Test
    public void testFetchAll() {
        RegionDto criteria = Rmt2AddressBookDtoFactory.getNewRegionInstance();
        try {
            when(this.mockPersistenceClient.retrieveList(any(State.class))).thenReturn(this.mockFetchAllResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("All state/region fetch test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<RegionDto> results = null;
        try {
            results = api.getRegion(criteria);
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(5, results.size());
        
        for (int ndx = 0; ndx < results.size(); ndx++) {
            RegionDto obj = results.get(ndx);
            Assert.assertEquals(obj.getStateId(), (10 + ndx));
            Assert.assertEquals(obj.getCountryId(), 100);
            Assert.assertEquals(obj.getStateCode(), "AbbrCode" + (ndx + 1));
            Assert.assertEquals(obj.getStateName(), "StateName" + (ndx + 1));
        }
    }

    @Test
    public void testFetchSingle() {
        RegionDto criteria = Rmt2AddressBookDtoFactory.getNewRegionInstance();
        criteria.setStateId(10);
        try {
            when(this.mockPersistenceClient.retrieveList(any(State.class))).thenReturn(this.mockSingleFetchResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Single state/region fetch test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<RegionDto> results = null;
        try {
            results = api.getRegion(criteria);
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        RegionDto state = (RegionDto) results.get(0);
        Assert.assertNotNull(state);
        Assert.assertEquals(10, state.getStateId());
        Assert.assertEquals("TX", state.getStateCode());
        Assert.assertEquals("Texas", state.getStateName());
    }
    
    @Test
    public void testFetchByCodeId() {
        RegionDto criteria = Rmt2AddressBookDtoFactory.getNewRegionInstance();
        criteria.setStateId(10);
        try {
            when(this.mockPersistenceClient.retrieveObject(any(State.class))).thenReturn(this.mockSingleFetchResponse.get(0));
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Fetch state/region by code id test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        RegionDto results = null;
        try {
            results = api.getRegion(10);
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertNotNull(results);
        Assert.assertEquals(10, results.getStateId());
        Assert.assertEquals("TX", results.getStateCode());
        Assert.assertEquals("Texas", results.getStateName());
    }

    @Test
    public void testFetchUsingCriteria() {
        RegionDto criteria = Rmt2AddressBookDtoFactory.getNewRegionInstance();
        criteria.setStateName("T");
        try {
            when(this.mockPersistenceClient.retrieveList(any(State.class))).thenReturn(this.mockCriteriaFetchResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Criteria state/region fetch test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<RegionDto> results = null;
        try {
            results = api.getRegion(criteria);
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
        for (RegionDto item : results) {
            Assert.assertTrue(item.getStateName().substring(0, 1).equalsIgnoreCase("T"));
        }
    }

    @Test
    public void testNotFoundlFetch() {
        RegionDto criteria = Rmt2AddressBookDtoFactory.getNewRegionInstance();
        criteria.setStateName("ZZ");
        try {
            when(this.mockPersistenceClient.retrieveList(any(State.class))).thenReturn(this.mockNotFoundFetchResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Criteria state/region fetch test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<RegionDto> results = null;
        try {
            results = api.getRegion(criteria);
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
            api.getRegion(null);
            Assert.fail("Expected exception to be thrown due to null criteria object input");
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate() {
        State origState = new State();
        origState.setAbbrCode("TX");
        origState.setCountryId(10);
        origState.setStateId(100);
        origState.setStateName("Texas");
        RegionDto updateState = this.createMockDto(100, 10, "Modified State", "MS");
        try {
            when(this.mockPersistenceClient.retrieveObject(any(State.class))).thenReturn(origState);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Update state/region fetch test case failed");
        }
        try {
            when(this.mockPersistenceClient.updateRow(any(State.class))).thenReturn(1);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Update state/region update row test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.updateRegion(updateState);
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, rc);
    }
    
    @Test
    public void testUpdateWithZeroCountryId() {
        State origState = new State();
        origState.setAbbrCode("TX");
        origState.setCountryId(0);
        origState.setStateId(100);
        origState.setStateName("Texas");
        RegionDto updateState = this.createMockDto(100, 0, "Mississippi", "MS");
        try {
            when(this.mockPersistenceClient.retrieveObject(any(State.class))).thenReturn(origState);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Update state/region fetch test case failed");
        }
        try {
            when(this.mockPersistenceClient.updateRow(any(State.class))).thenReturn(1);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Update state/region update row test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.updateRegion(updateState);
            Assert.fail("Expected exception to be thrown");
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUpdateWithNullStateName() {
        State origState = new State();
        origState.setAbbrCode("TX");
        origState.setCountryId(0);
        origState.setStateId(100);
        origState.setStateName("Texas");
        RegionDto updateState = this.createMockDto(100, 10, null, "MS");
        try {
            when(this.mockPersistenceClient.retrieveObject(any(State.class))).thenReturn(origState);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Update state/region fetch test case failed");
        }
        try {
            when(this.mockPersistenceClient.updateRow(any(State.class))).thenReturn(1);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Update state/region update row test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.updateRegion(updateState);
            Assert.fail("Expected exception to be thrown");
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUpdateWithullStateCode() {
        State origState = new State();
        origState.setAbbrCode("TX");
        origState.setCountryId(0);
        origState.setStateId(100);
        origState.setStateName("Texas");
        RegionDto updateState = this.createMockDto(100, 10, "Mississippi", null);
        try {
            when(this.mockPersistenceClient.retrieveObject(any(State.class))).thenReturn(origState);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Update state/region fetch test case failed");
        }
        try {
            when(this.mockPersistenceClient.updateRow(any(State.class))).thenReturn(1);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Update state/region update row test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.updateRegion(updateState);
            Assert.fail("Expected exception to be thrown");
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsert() {
        RegionDto updateState = this.createMockDto(0, 100, "Mississippi", "MS");
        try {
            when(this.mockPersistenceClient.insertRow(any(State.class), eq(true))).thenReturn(555);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Insert state/region row test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.updateRegion(updateState);
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(555, rc);
        Assert.assertEquals(555, updateState.getStateId());
    }

    @Test
    public void testInsertWithZeroCountryId() {
        RegionDto updateState = this.createMockDto(0, 0, "Mississippi", "MS");
        try {
            when(this.mockPersistenceClient.insertRow(any(State.class), eq(true))).thenReturn(555);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Insert state/region row test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.updateRegion(updateState);
            Assert.fail("Expected an exception to be thrown");
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testDelete() {
        try {
            when(this.mockPersistenceClient.deleteRow(any(Integer.class))).thenReturn(1);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Delete state/region row test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        int rc = 0;
        try {
            rc = api.deleteRegion(10);
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, rc);
    }

    @Test
    public void testDeleteWithInvalidCodeId() {
        try {
            when(this.mockPersistenceClient.deleteRow(any(Integer.class))).thenReturn(1);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("Delete state/region row test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        try {
            api.deleteRegion(-10);
            Assert.fail("Expected an exception to be thrown");
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
    }
}
