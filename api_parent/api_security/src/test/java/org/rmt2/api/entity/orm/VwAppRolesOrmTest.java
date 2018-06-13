package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.VwAppRoles;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.SecurityMockDataFactory;

public class VwAppRolesOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        VwAppRoles o = SecurityMockDataFactory.createOrmVwAppRoles(SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID);
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        VwAppRoles o1 = SecurityMockDataFactory.createOrmVwAppRoles(SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID);
        VwAppRoles o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2 = new VwAppRoles();

        result = o1.equals(o2);
        
        o2.setAppRoleId(SecurityMockDataFactory.TEST_APP_ROLE_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setApplicationId(SecurityMockDataFactory.TEST_APP_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setRoleId(SecurityMockDataFactory.TEST_ROLE_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setAppRoleCode("AppRoleCode_" + SecurityMockDataFactory.TEST_APP_ROLE_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setAppRoleName("AppRoleName_" + SecurityMockDataFactory.TEST_APP_ROLE_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setRoleName("RoleName_" + SecurityMockDataFactory.TEST_ROLE_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setAppName("AppName_" + SecurityMockDataFactory.TEST_APP_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setAppRoleDescription("AppRoleDescription_" + SecurityMockDataFactory.TEST_APP_ROLE_ID);
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        VwAppRoles o1 = SecurityMockDataFactory.createOrmVwAppRoles(SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID);
        VwAppRoles o2 = SecurityMockDataFactory.createOrmVwAppRoles(SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID);

        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
