package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.UserLogin;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.SecurityMockDataFactory;

import com.util.RMT2Date;

public class UserLoginOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        UserLogin o = SecurityMockDataFactory.createOrmUserLogin(SecurityMockDataFactory.TEST_USER_ID, 
                SecurityMockDataFactory.TEST_GROUP_ID, "user_name", "test1234", "2018-01-01");
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        UserLogin o1 = SecurityMockDataFactory.createOrmUserLogin(SecurityMockDataFactory.TEST_USER_ID, 
                SecurityMockDataFactory.TEST_GROUP_ID, "user_name", "test1234", "2018-01-01");
        o1.setTerminationDate(RMT2Date.stringToDate("2018-01-01"));
        UserLogin o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2 = new UserLogin();

        result = o1.equals(o2);
        o2.setLoginId(SecurityMockDataFactory.TEST_USER_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setGrpId(SecurityMockDataFactory.TEST_GROUP_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setUsername("user_name");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setPassword("test1234");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setStartDate(RMT2Date.stringToDate("2018-01-01"));
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setTerminationDate(RMT2Date.stringToDate("2018-01-01"));
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setBirthDate(RMT2Date.stringToDate("1966-01-01"));
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setFirstname("firstname_" + SecurityMockDataFactory.TEST_USER_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setLastname("lastname_" + SecurityMockDataFactory.TEST_USER_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setEmail(o2.getFirstname() + "." + o2.getLastname() + "@gte.net");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setDescription("description_" + SecurityMockDataFactory.TEST_USER_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setSsn("111-11-1111");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setActive(1);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setLoggedIn(1);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        
        o2.setTotalLogons(5);
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        UserLogin o1 = SecurityMockDataFactory.createOrmUserLogin(SecurityMockDataFactory.TEST_USER_ID, 
                SecurityMockDataFactory.TEST_GROUP_ID, "user_name", "test1234", "2018-01-01");
        UserLogin o2 = SecurityMockDataFactory.createOrmUserLogin(SecurityMockDataFactory.TEST_USER_ID, 
                SecurityMockDataFactory.TEST_GROUP_ID, "user_name", "test1234", "2018-01-01");

        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
