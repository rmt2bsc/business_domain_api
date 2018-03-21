package org.rmt2.orm;

import org.dao.mapping.orm.rmt2.Business;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BusinessOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        Business o = new Business();
        o.setBusinessId(1351);
        o.setLongname("ABC Company");
        o.setContactFirstname("Roy");
        o.setContactLastname("Terrell");
        o.setContactPhone("3189994444");
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        Business o1 = new Business();
        Business o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1.setBusinessId(1351);
        o1.setLongname("ABC Company");
        o1.setContactFirstname("Roy");
        o1.setContactLastname("Terrell");
        o1.setContactPhone("3189994444");
        o2 = new Business();

        result = o1.equals(o2);
        o2.setBusinessId(1351);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setLongname("ABC Company");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setContactFirstname("Roy");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setContactLastname("Terrell");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setContactPhone("3189994444");
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        Business o1 = new Business();
        o1.setBusinessId(1351);
        o1.setLongname("ABC Company");
        o1.setContactFirstname("Roy");
        o1.setContactLastname("Terrell");
        o1.setContactPhone("3189994444");

        Business o2 = new Business();
        o2.setBusinessId(1351);
        o2.setLongname("ABC Company");
        o2.setContactFirstname("Roy");
        o2.setContactLastname("Terrell");
        o2.setContactPhone("3189994444");

        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
