package org.rmt2.orm;

import org.dao.mapping.orm.rmt2.Country;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CountryOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        Country o = new Country();
        o.setCode("USA");
        o.setCountryId(100);
        o.setName("United States");
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        Country o1 = new Country();
        Country o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1.setCode("USA");
        o1.setCountryId(100);
        o1.setName("United States");
        o2 = new Country();

        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setCode("USA");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setCountryId(100);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setName("United States");
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        Country o1 = new Country();
        o1.setCode("USA");
        o1.setCountryId(100);
        o1.setName("United States");

        Country o2 = new Country();
        o2.setCode("USA");
        o2.setCountryId(100);
        o2.setName("United States");
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
