package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.AppRole;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.SecurityMockDataFactory;

public class AppRoleOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        AppRole o = SecurityMockDataFactory.createOrmAppRole(SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID);
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        AppRole o1 = SecurityMockDataFactory.createOrmAppRole(SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID);
        AppRole o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2 = new AppRole();

        result = o1.equals(o2);
        
        o2.setAppRoleId(SecurityMockDataFactory.TEST_APP_ROLE_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setAppId(SecurityMockDataFactory.TEST_APP_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setRoleId(SecurityMockDataFactory.TEST_ROLE_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setCode("Code_" + SecurityMockDataFactory.TEST_APP_ROLE_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setName("Name_" + SecurityMockDataFactory.TEST_APP_ROLE_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setDescription("Description_" + SecurityMockDataFactory.TEST_APP_ROLE_ID);
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        AppRole o1 = SecurityMockDataFactory.createOrmAppRole(SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID);
        AppRole o2 = SecurityMockDataFactory.createOrmAppRole(SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID);

        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
