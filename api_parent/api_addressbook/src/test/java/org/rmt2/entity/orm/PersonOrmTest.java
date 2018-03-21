package org.rmt2.entity.orm;

import org.dao.mapping.orm.rmt2.Person;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PersonOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        Person o = new Person();
        o.setPersonId(1351);
        o.setFirstname("Roy");
        o.setLastname("Terrell");
        o.setEmail("roy.terrell@gte.net");
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        Person o1 = new Person();
        Person o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1.setPersonId(1351);
        o1.setFirstname("Roy");
        o1.setLastname("Terrell");
        o1.setEmail("roy.terrell@gte.net");
        o2 = new Person();

        result = o1.equals(o2);
        o2.setPersonId(1351);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setFirstname("Roy");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setLastname("Terrell");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setEmail("roy.terrell@gte.net");
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        Person o1 = new Person();
        o1.setPersonId(1351);
        o1.setFirstname("Roy");
        o1.setLastname("Terrell");
        o1.setEmail("roy.terrell@gte.net");

        Person o2 = new Person();
        o2.setPersonId(1351);
        o2.setFirstname("Roy");
        o2.setLastname("Terrell");
        o2.setEmail("roy.terrell@gte.net");

        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
