package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.UserResourceType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.SecurityMockDataFactory;

public class UserResourceTypeOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        UserResourceType o = SecurityMockDataFactory.createOrmUserResourceType(
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        UserResourceType o1 = SecurityMockDataFactory.createOrmUserResourceType(
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);
        UserResourceType o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2 = new UserResourceType();
        result = o1.equals(o2);
        
        o2.setRsrcTypeId(SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setDescription("ResourceTypeDescription_" + SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        UserResourceType o1 = SecurityMockDataFactory.createOrmUserResourceType(
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);
        UserResourceType o2 = SecurityMockDataFactory.createOrmUserResourceType(
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);

        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
