package org.rmt2.api.postal;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.dao.lookup.LookupDaoException;
import org.dao.mapping.orm.rmt2.TimeZone;
import org.dto.TimeZoneDto;
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
public class TimezoneApiTest extends BaseAddressBookDaoTest {
    private List<TimeZone> mockSingleFetchResponse;
    private List<TimeZone> mockCriteriaFetchResponse;
    private List<TimeZone> mockFetchAllResponse;
    private List<TimeZone> mockNotFoundFetchResponse;
    private TimeZone mockNotFoundUidFetchResponse;
    private TimeZone mockSingleUidFetchResponse;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.mockSingleFetchResponse = this.createMockSingleFetchResponse();
        this.mockCriteriaFetchResponse = this.createMockFetchUsingCriteriaResponse();
        this.mockFetchAllResponse = this.createMockFetchAllResponse();
        this.mockNotFoundFetchResponse = this.createMockNotFoundSearchResultsResponse();
        this.mockNotFoundUidFetchResponse = this.createMockNotFoundUidSearchResultsResponse();
        this.mockSingleUidFetchResponse = this.createMockSingleUidFetchResponse();
    }

    @After
    public void tearDown() throws Exception {
    }

    private List<TimeZone> createMockNotFoundSearchResultsResponse() {
        List<TimeZone> list = null;
        return list;
    }

    private List<TimeZone> createMockSingleFetchResponse() {
        List<TimeZone> list = new ArrayList<TimeZone>();
        TimeZone p = new TimeZone();
        p.setTimeZoneId(100);
        p.setDescr("Central");

        list.add(p);
        return list;
    }

    private TimeZone createMockNotFoundUidSearchResultsResponse() {
        TimeZone rec = null;
        return rec;
    }
    
    private TimeZone createMockSingleUidFetchResponse() {
        TimeZone p = new TimeZone();
        p.setTimeZoneId(100);
        p.setDescr("Central");
        return p;
    }
    /**
     * Use for the following selection criteria: where last name begins with 'C'
     * 
     * @return
     */
    private List<TimeZone> createMockFetchUsingCriteriaResponse() {
        List<TimeZone> list = new ArrayList<TimeZone>();
        
        TimeZone p = new TimeZone();
        p.setTimeZoneId(100);
        p.setDescr("Central");
        list.add(p);

        p = new TimeZone();
        p.setTimeZoneId(200);
        p.setDescr("Mountain");
        list.add(p);

        return list;
    }

    private List<TimeZone> createMockFetchAllResponse() {
        List<TimeZone> list = new ArrayList<TimeZone>();
        TimeZone p = new TimeZone();
        p.setTimeZoneId(100);
        p.setDescr("TimeZoneDescription1");
        list.add(p);

        p = new TimeZone();
        p.setTimeZoneId(101);
        p.setDescr("TimeZoneDescription2");
        list.add(p);

        p = new TimeZone();
        p.setTimeZoneId(102);
        p.setDescr("TimeZoneDescription3");
        list.add(p);

        p = new TimeZone();
        p.setTimeZoneId(103);
        p.setDescr("TimeZoneDescription4");
        list.add(p);
        return list;
    }

    private TimeZoneDto createMockDto(int id, String description) {
        TimeZoneDto dto = Rmt2AddressBookDtoFactory.getNewTimezoneInstance();
        dto.setTimeZoneId(id);
        dto.setTimeZoneDescr(description);
        return dto;
    }
    @Test
    public void testFetchAll() {
        TimeZoneDto criteria = Rmt2AddressBookDtoFactory.getNewTimezoneInstance();
        
        try {
            when(this.mockPersistenceClient.retrieveList(any(TimeZone.class))).thenReturn(this.mockFetchAllResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("All timezone fetch test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<TimeZoneDto> results = null;
        try {
            results = api.getTimezone(criteria);
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(4, results.size());
        
        for (int ndx = 0; ndx < results.size(); ndx++) {
            TimeZoneDto obj = results.get(ndx);
            Assert.assertEquals(obj.getTimeZoneId(), (100 + ndx));
            Assert.assertEquals(obj.getTimeZoneDescr(), "TimeZoneDescription" + (ndx + 1));
        }
    }
    
    @Test
    public void testFetchNotFoundWithCriteria() {
        TimeZoneDto criteria = Rmt2AddressBookDtoFactory.getNewTimezoneInstance();
        criteria.setTimeZoneId(9999);
        try {
            when(this.mockPersistenceClient.retrieveList(any(TimeZone.class))).thenReturn(this.mockNotFoundFetchResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("timezone not found fetch test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<TimeZoneDto> results = null;
        try {
            results = api.getTimezone(criteria);
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(results);
    }
    
       
    @Test
    public void testFetchWithNullCriteria() {
        TimeZoneDto criteria = null;
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<TimeZoneDto> results = null;
        try {
            api.getTimezone(criteria);
            Assert.fail("Expected exception to be thrown due to null criteria");
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFetchSingle() {
        TimeZoneDto criteria = Rmt2AddressBookDtoFactory.getNewTimezoneInstance();
        criteria.setTimeZoneId(100);
        try {
            when(this.mockPersistenceClient.retrieveList(any(TimeZone.class))).thenReturn(this.mockSingleFetchResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("timezone single fetch test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        List<TimeZoneDto> results = null;
        try {
            results = api.getTimezone(criteria);
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        TimeZoneDto rec = results.get(0);
        Assert.assertEquals(100, rec.getTimeZoneId());
        Assert.assertEquals("Central", rec.getTimeZoneDescr());
    }
    
    @Test
    public void testFetchByUid() {
        try {
            when(this.mockPersistenceClient.retrieveObject(any(TimeZone.class))).thenReturn(this.mockSingleUidFetchResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("timezone single UID fetch test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        TimeZoneDto rec = null;
        try {
            rec = api.getTimezone(100);
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(rec);
        Assert.assertEquals(100, rec.getTimeZoneId());
        Assert.assertEquals("Central", rec.getTimeZoneDescr());
    }
    
    @Test
    public void testFetchNotFoundByUid() {
        try {
            when(this.mockPersistenceClient.retrieveObject(any(TimeZone.class))).thenReturn(this.mockNotFoundUidFetchResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("timezone single UID fetch test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        TimeZoneDto rec = null;
        try {
            rec = api.getTimezone(100);
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
        Assert.assertNull(rec);
    }
}
