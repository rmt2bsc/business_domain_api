package org.rmt2.dao;

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
        Country c = new Country();
        c.setCode("USA");
        c.setCountryId(100);
        c.setName("United States");
        String val = c.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        Country c1 = new Country();
        Country c2 = null;

        result = c1.equals(c2);
        Assert.assertFalse(result);

        c1.setCode("USA");
        c1.setCountryId(100);
        c1.setName("United States");
        c2 = new Country();

        result = c1.equals(c2);
        Assert.assertFalse(result);
        c2.setCode("USA");
        result = c1.equals(c2);
        Assert.assertFalse(result);
        c2.setCountryId(100);
        result = c1.equals(c2);
        Assert.assertFalse(result);
        c2.setName("United States");
        result = c1.equals(c2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        boolean result = false;
        Country c1 = new Country();
        c1.setCode("USA");
        c1.setCountryId(100);
        c1.setName("United States");
        int hashCode = c1.hashCode();
        Assert.assertTrue(hashCode > 1000);
    }
}
