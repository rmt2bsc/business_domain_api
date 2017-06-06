package org.rmt2.dao;

import org.dao.mapping.orm.rmt2.VwStateCountry;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CountryRegionOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        VwStateCountry o1 = new VwStateCountry();
        o1.setCountryId(100);
        o1.setCountryName("United States");
        o1.setStateId(10);
        o1.setStateCode("LA");
        o1.setStateName("Louisiana");
        String val = o1.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        VwStateCountry o1 = new VwStateCountry();
        VwStateCountry o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1.setCountryId(100);
        o1.setCountryName("United States");
        o1.setStateName("Louisiana");
        o1.setStateCode("LA");
        o1.setStateId(10);

        o2 = new VwStateCountry();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setStateCode("LA");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setCountryId(100);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setCountryName("United States");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setStateName("Louisiana");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setStateId(10);
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        VwStateCountry o1 = new VwStateCountry();
        o1.setCountryId(100);
        o1.setCountryName("United States");
        o1.setStateId(10);
        o1.setStateCode("LA");
        o1.setStateName("Louisiana");

        VwStateCountry o2 = new VwStateCountry();
        o2.setCountryId(100);
        o2.setCountryName("United States");
        o2.setStateId(10);
        o2.setStateCode("LA");
        o2.setStateName("Louisiana");
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
