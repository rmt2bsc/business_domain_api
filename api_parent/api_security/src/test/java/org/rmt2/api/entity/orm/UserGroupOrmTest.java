package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.UserGroup;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.SecurityMockDataFactory;

public class UserGroupOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        UserGroup o = SecurityMockDataFactory.createOrmUserGroup(SecurityMockDataFactory.TEST_GROUP_ID);
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        UserGroup o1 = SecurityMockDataFactory.createOrmUserGroup(SecurityMockDataFactory.TEST_GROUP_ID);
        UserGroup o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2 = new UserGroup();

        result = o1.equals(o2);
        o2.setGrpId(SecurityMockDataFactory.TEST_GROUP_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2.setDescription("UserGroupDescription_" + SecurityMockDataFactory.TEST_GROUP_ID);
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        UserGroup o1 = SecurityMockDataFactory.createOrmUserGroup(SecurityMockDataFactory.TEST_GROUP_ID);
        UserGroup o2 = SecurityMockDataFactory.createOrmUserGroup(SecurityMockDataFactory.TEST_GROUP_ID);

        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
