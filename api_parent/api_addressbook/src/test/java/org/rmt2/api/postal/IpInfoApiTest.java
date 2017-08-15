package org.rmt2.api.postal;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.dao.lookup.LookupDaoException;
import org.dao.mapping.orm.rmt2.IpLocation;
import org.dao.postal.IpDaoException;
import org.dto.IpLocationDto;
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
import com.api.persistence.DatabaseException;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class })
public class IpInfoApiTest extends BaseAddressBookDaoTest {
    private IpLocation mockSingleFetchResponse;
    private IpLocation mockNotFoundFetchResponse;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.mockSingleFetchResponse = this.createMockSingleFetchResponse();
        this.mockNotFoundFetchResponse = this.createMockNotFoundSearchResultsResponse();
    }

    @After
    public void tearDown() throws Exception {
    }

    private IpLocation createMockNotFoundSearchResultsResponse() {
        IpLocation p = null;
        return p;
    }

    private IpLocation createMockSingleFetchResponse() {
        IpLocation p = this.createMockOrm(200, 123456789, 987654321, 90333.333, 29393.392838, "United States", "USA", "Dallas", "75240", "6");
        return p;
    }

   

    private IpLocationDto createMockDto(int id, double longitude, double latitude, String countyName, String state, String city, String zip, String areaCode) {
        IpLocationDto dto = Rmt2AddressBookDtoFactory.getNewIpLocationInstance();
        dto.setIpRangeId(id);
        dto.setCity(city);
        dto.setPostalCode(zip);;
        dto.setCountry(countyName);
        dto.setRegion(state);
        dto.setAreaCode(areaCode);
        dto.setLatitude(longitude);
        dto.setLongitude(latitude);
        return dto;
    }
    
    private IpLocation createMockOrm(int id, double ipFrom, double ipTo, double longitude, double latitude, String countyName, String countyCode, String city, String zip, String timeZoneId) {
        IpLocation dto = new IpLocation();
        dto.setIpId(id);
        dto.setIpFrom(ipFrom);
        dto.setIpTo(ipTo);
        dto.setCity(city);
        dto.setZipcode(zip);
        dto.setCountryName(countyName);
        dto.setCountryCode(countyCode);
        dto.setTimezone(timeZoneId);
        dto.setLatitude(latitude);
        dto.setLongitude(longitude);
        return dto;
    }
    
    @Test
    public void testFetchSingleUsingOctet() {
        try {
            when(this.mockPersistenceClient.retrieveObject(any(IpLocation.class))).thenReturn(this.mockSingleFetchResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("IP location octet fetch test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        IpLocationDto rec = null;
        try {
            rec = api.getIpInfo("123.345.456.678");
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(rec);
        Assert.assertEquals(200, rec.getIpRangeId());
        Assert.assertEquals(90333.333, rec.getLongitude(), 0);
        Assert.assertEquals(29393.392838, rec.getLatitude(), 0);
        Assert.assertEquals("Dallas", rec.getCity());
        Assert.assertEquals("75240", rec.getPostalCode());
        Assert.assertEquals("United States", rec.getCountry());
    }
    
    @Test
    public void testFetchSingleUsingLongValue() {
        try {
            when(this.mockPersistenceClient.retrieveObject(any(IpLocation.class))).thenReturn(this.mockSingleFetchResponse);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("IP location with long value fetch test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        IpLocationDto rec = null;
        try {
            rec = api.getIpInfo(123456789045L);
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(rec);
        Assert.assertEquals(200, rec.getIpRangeId());
        Assert.assertEquals(90333.333, rec.getLongitude(), 0);
        Assert.assertEquals(29393.392838, rec.getLatitude(), 0);
        Assert.assertEquals("Dallas", rec.getCity());
        Assert.assertEquals("75240", rec.getPostalCode());
        Assert.assertEquals("United States", rec.getCountry());
    }

    @Test
    public void testFetchWithNullOctets() {
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        IpLocationDto rec = null;
        try {
            rec = api.getIpInfo(null);
            Assert.fail("Expected exception to be thrown due to 3 octets");
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchWith3Octets() {
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        IpLocationDto rec = null;
        try {
            rec = api.getIpInfo("123.345.456");
            Assert.fail("Expected exception to be thrown due to 3 octets");
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFetchWithAnInvalidOctet() {
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        IpLocationDto rec = null;
        try {
            rec = api.getIpInfo("123.345.456.abc");
            Assert.fail("Expected exception to be thrown due to 3 octets");
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchWithNoOctets() {
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        IpLocationDto rec = null;
        try {
            rec = api.getIpInfo("123345456225");
            Assert.fail("Expected exception to be thrown due to 3 octets");
        } catch (PostalApiException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testFetchSingleUsingLongValueDaoException() {
        try {
            when(this.mockPersistenceClient.retrieveObject(any(IpLocation.class)))
            .thenThrow(DatabaseException.class);
        } catch (LookupDaoException e) {
            e.printStackTrace();
            Assert.fail("IP location with long value fetch test case failed");
        }
        PostalApiFactory f = new PostalApiFactory();
        PostalApi api = f.createApi(AddressBookConstants.APP_NAME);
        IpLocationDto rec = null;
        try {
            rec = api.getIpInfo(123456789045L);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof PostalApiException);
            Assert.assertTrue(e.getCause() instanceof IpDaoException);
            e.printStackTrace();
        }
    }
}
