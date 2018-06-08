package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.UserResourceSubtype;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.SecurityMockDataFactory;

public class UserResourceSubTypeOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        UserResourceSubtype o = SecurityMockDataFactory
                .createOrmUserResourceSubtype(SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID,
                        SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        UserResourceSubtype o1 = SecurityMockDataFactory
                .createOrmUserResourceSubtype(SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID,
                        SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);
        UserResourceSubtype o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2 = new UserResourceSubtype();
        result = o1.equals(o2);
        
        o2.setRsrcSubtypeId(SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setRsrcTypeId(SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setName("ResourceSubtypeName_" + SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setDescription("ResourceSubtypeDescription_" + SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID);
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        UserResourceSubtype o1 = SecurityMockDataFactory
                .createOrmUserResourceSubtype(SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID,
                        SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);
        
        UserResourceSubtype o2 = SecurityMockDataFactory
                .createOrmUserResourceSubtype(SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID,
                        SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);

        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
