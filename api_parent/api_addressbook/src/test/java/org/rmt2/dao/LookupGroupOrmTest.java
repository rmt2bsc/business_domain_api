package org.rmt2.dao;

import java.util.Date;

import org.dao.mapping.orm.rmt2.GeneralCodesGroup;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LookupGroupOrmTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        GeneralCodesGroup o = new GeneralCodesGroup();
        o.setCodeGrpId(500);
        o.setDescription("Test General Code Group");
        o.setDateCreated(new Date());
        o.setDateUpdated(new Date());
        o.setUserId("test_user");
        String val = o.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        GeneralCodesGroup o1 = new GeneralCodesGroup();
        GeneralCodesGroup o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        Date dt = new Date();
        o1.setCodeGrpId(500);
        o1.setDescription("Test General Code Group");
        o1.setDateCreated(dt);
        o1.setDateUpdated(dt);
        o1.setUserId("test_user");
        o2 = new GeneralCodesGroup();

        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setCodeGrpId(500);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setDescription("Test General Code Group");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setDateCreated(dt);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setDateUpdated(dt);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setUserId("test_user");
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        GeneralCodesGroup o1 = new GeneralCodesGroup();
        o1.setCodeGrpId(500);
        o1.setDescription("Test General Code Group");
        o1.setDateCreated(new Date());
        o1.setDateUpdated(new Date());
        o1.setUserId("test_user");

        GeneralCodesGroup o2 = new GeneralCodesGroup();
        o2.setCodeGrpId(500);
        o2.setDescription("Test General Code Group");
        o2.setDateCreated(new Date());
        o2.setDateUpdated(new Date());
        o2.setUserId("test_user");
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
