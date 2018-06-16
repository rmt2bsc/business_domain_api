package org.rmt2.api.entity.orm;

import org.dao.mapping.orm.rmt2.Roles;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.SecurityMockDataFactory;

public class RolesOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        Roles o = SecurityMockDataFactory.createOrmRoles(SecurityMockDataFactory.TEST_ROLE_ID);
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        Roles o1 = SecurityMockDataFactory.createOrmRoles(SecurityMockDataFactory.TEST_ROLE_ID);
        Roles o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        o2 = new Roles();

        result = o1.equals(o2);
        o2.setRoleId(SecurityMockDataFactory.TEST_ROLE_ID);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setName("RoleName_" + o2.getRoleId());
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setDescription("RoleDescription_" + o2.getRoleId());
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        Roles o1 = SecurityMockDataFactory.createOrmRoles(SecurityMockDataFactory.TEST_ROLE_ID);
        Roles o2 = SecurityMockDataFactory.createOrmRoles(SecurityMockDataFactory.TEST_ROLE_ID);

        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
