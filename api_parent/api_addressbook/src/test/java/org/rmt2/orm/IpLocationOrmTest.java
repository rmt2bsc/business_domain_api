package org.rmt2.orm;

import org.dao.mapping.orm.rmt2.IpLocation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IpLocationOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
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
    public void testToString() {
        IpLocation o = this.createMockOrm(200, 123456789, 987654321, 90333.333, 29393.392838, "United States", "USA", "Dallas", "75240", "6");
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        IpLocation o1 = this.createMockOrm(200, 123456789, 987654321, 90333.333, 29393.392838, "United States", "USA", "Dallas", "75240", "6");
        IpLocation o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2 = new IpLocation();

        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setIpId(200);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setIpFrom(123456789);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setIpTo(987654321);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setLongitude(90333.333);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setLatitude(29393.392838);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setCountryName("United States");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setCountryCode("USA");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setCity("Dallas");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setZipcode("75240");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setTimezone("6");
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        IpLocation o1 = this.createMockOrm(200, 123456789, 987654321, 90333.333, 29393.392838, "United States", "USA", "Dallas", "75240", "6");
        IpLocation o2 = this.createMockOrm(200, 123456789, 987654321, 90333.333, 29393.392838, "United States", "USA", "Dallas", "75240", "6");
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
