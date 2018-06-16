package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.Country;
import org.dao.mapping.orm.rmt2.TimeZone;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TimezoneOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        TimeZone o = new TimeZone();
        o.setTimeZoneId(100);
        o.setDescr("Central");
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        TimeZone o1 = new TimeZone();
        TimeZone o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1.setTimeZoneId(100);
        o1.setDescr("Central");
        o2 = new TimeZone();

        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setTimeZoneId(100);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setDescr("Central");
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        TimeZone o1 = new TimeZone();
        o1.setTimeZoneId(100);
        o1.setDescr("Central");

        TimeZone o2 = new TimeZone();
        o2.setTimeZoneId(100);
        o2.setDescr("Central");
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
