package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.UserAppRole;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.SecurityMockDataFactory;

public class UserAppRoleOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        UserAppRole o = SecurityMockDataFactory.createOrmUserAppRole(SecurityMockDataFactory.TEST_USER_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_USER_ID);
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        UserAppRole o1 = SecurityMockDataFactory.createOrmUserAppRole(SecurityMockDataFactory.TEST_USER_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_USER_ID);
        UserAppRole o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2 = new UserAppRole();

        result = o1.equals(o2);
        
        o2.setUserAppRoleId(SecurityMockDataFactory.TEST_USER_APP_ROLE_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setLoginId(SecurityMockDataFactory.TEST_USER_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setAppRoleId(SecurityMockDataFactory.TEST_APP_ROLE_ID);
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        UserAppRole o1 = SecurityMockDataFactory.createOrmUserAppRole(SecurityMockDataFactory.TEST_USER_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_USER_ID);
        UserAppRole o2 = SecurityMockDataFactory.createOrmUserAppRole(SecurityMockDataFactory.TEST_USER_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_USER_ID);

        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
