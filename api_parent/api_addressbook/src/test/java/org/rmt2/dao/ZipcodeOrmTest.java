package org.rmt2.dao;

import org.dao.mapping.orm.rmt2.Zipcode;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ZipcodeOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
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
        dto.setCityAliasName("City_Alias");
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
    public void testToString() {
        Zipcode o1 = this.createMockOrm(71106, 71106, "LA", "Shreveport", "318", "Caddo", 6);
        String val = o1.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        Zipcode o1 = new Zipcode();
        Zipcode o2 = null;
        
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o1 = this.createMockOrm(71106, 71106, "LA", "Shreveport", "318", "Caddo", 6);

        o2 = this.createMockOrm(0, 0, null, null, null, null, 0);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setZipId(71106);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setZip(71106);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setState("LA");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setCity("Shreveport");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setAreaCode("318");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setCountyName("Caddo");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setTimeZoneId(6);
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        Zipcode o1 = this.createMockOrm(71106, 71106, "LA", "Shreveport", "318", "Caddo", 6);
        Zipcode o2 = this.createMockOrm(71106, 71106, "LA", "Shreveport", "318", "Caddo", 6);
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
