package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.UserResource;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.SecurityMockDataFactory;

public class UserResourceOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        UserResource o = SecurityMockDataFactory.createOrmUserResource(
                SecurityMockDataFactory.TEST_RESOURCE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, "URL", true);
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        UserResource o1 = SecurityMockDataFactory.createOrmUserResource(
                SecurityMockDataFactory.TEST_RESOURCE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, "URL", true);
        UserResource o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2 = new UserResource();

        result = o1.equals(o2);
        o2.setRsrcId(SecurityMockDataFactory.TEST_RESOURCE_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setRsrcTypeId(SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setRsrcSubtypeId(SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setUrl("URL");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setDescription("description_" + SecurityMockDataFactory.TEST_RESOURCE_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setSecured(1);
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        UserResource o1 = SecurityMockDataFactory.createOrmUserResource(
                SecurityMockDataFactory.TEST_RESOURCE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, "URL", true);
        UserResource o2 = SecurityMockDataFactory.createOrmUserResource(
                SecurityMockDataFactory.TEST_RESOURCE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, "URL", true);

        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
