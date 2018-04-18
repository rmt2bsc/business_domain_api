package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.State;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RegionOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        State o1 = new State();
        o1.setAbbrCode("LA");
        o1.setCountryId(100);
        o1.setStateName("Louisiana");
        o1.setStateId(10);
        String val = o1.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        State o1 = new State();
        State o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o1.setAbbrCode("LA");
        o1.setCountryId(100);
        o1.setStateName("Louisiana");
        o1.setStateId(10);

        o2 = new State();
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setAbbrCode("LA");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setCountryId(100);
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
        State o1 = new State();
        o1.setAbbrCode("LA");
        o1.setCountryId(100);
        o1.setStateName("Louisiana");
        o1.setStateId(10);

        State o2 = new State();
        o2.setAbbrCode("LA");
        o2.setCountryId(100);
        o2.setStateName("Louisiana");
        o2.setStateId(10);
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
