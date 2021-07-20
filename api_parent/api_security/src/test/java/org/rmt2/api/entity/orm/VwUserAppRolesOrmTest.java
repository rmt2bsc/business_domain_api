package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.VwUserAppRoles;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.SecurityMockDataFactory;

import com.api.util.RMT2Date;

public class VwUserAppRolesOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        VwUserAppRoles o = SecurityMockDataFactory.createOrmVwUserAppRoles(SecurityMockDataFactory.TEST_USER_ID, 
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_GROUP_ID, 
                "user_name",
                "2018-01-01");
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        VwUserAppRoles o1 = SecurityMockDataFactory.createOrmVwUserAppRoles(SecurityMockDataFactory.TEST_USER_ID, 
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_GROUP_ID, 
                "user_name",
                "2018-01-01");
        VwUserAppRoles o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2 = new VwUserAppRoles();

        result = o1.equals(o2);
        
        o2.setUserAppRoleId(SecurityMockDataFactory.TEST_USER_ID + SecurityMockDataFactory.TEST_APP_ID
                + SecurityMockDataFactory.TEST_ROLE_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setLoginUid(SecurityMockDataFactory.TEST_USER_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setAppRoleId(SecurityMockDataFactory.TEST_APP_ROLE_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setApplicationId(SecurityMockDataFactory.TEST_APP_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setRoleId(SecurityMockDataFactory.TEST_ROLE_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setGroupId(SecurityMockDataFactory.TEST_GROUP_ID);
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
        Assert.assertFalse(result);
        
        o2.setGroupDescription("GroupDescription_" + o2.getGroupId());
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setUsername("user_name");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setStartDate(RMT2Date.stringToDate("2018-01-01"));
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setBirthDate(RMT2Date.stringToDate("1966-01-01"));
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setFirstname("firstname_" + o2.getLoginUid());
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setLastname("lastname_" + o2.getLoginUid());
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setEmail(o2.getFirstname() + "." + o2.getLastname() + "@gte.net");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setUserDescription("UserDescription_" + o2.getLoginUid());
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setActive(1);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setSsn("111-11-1111");
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        VwUserAppRoles o1 = SecurityMockDataFactory.createOrmVwUserAppRoles(SecurityMockDataFactory.TEST_USER_ID, 
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_GROUP_ID, 
                "user_name",
                "2018-01-01");
        VwUserAppRoles o2 = SecurityMockDataFactory.createOrmVwUserAppRoles(SecurityMockDataFactory.TEST_USER_ID, 
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_GROUP_ID, 
                "user_name",
                "2018-01-01");

        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
