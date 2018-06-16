package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.Application;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.SecurityMockDataFactory;

public class ApplicationOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        Application o = SecurityMockDataFactory.createOrmApplication(1000);
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        Application o1 = SecurityMockDataFactory.createOrmApplication(1000);
        Application o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2 = new Application();

        result = o1.equals(o2);
        o2.setAppId(1000);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setName("ApplicationName_" + o2.getAppId());
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setDescription("ApplicationDescription_" + o2.getAppId());
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        Application o1 = SecurityMockDataFactory.createOrmApplication(1000);
        Application o2 = SecurityMockDataFactory.createOrmApplication(1000);

        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
