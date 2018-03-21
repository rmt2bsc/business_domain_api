package org.rmt2.orm;

import java.util.Date;

import org.dao.mapping.orm.rmt2.GeneralCodes;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LookupCodeOrmTest {

    @Before
    public void setUp() throws Exception {
    }
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToString() {
        GeneralCodes o1 = new GeneralCodes();
        o1.setCodeGrpId(500);
        o1.setCodeId(200);
        o1.setLongdesc("Test General Code");
        o1.setShortdesc("code");
        String val = o1.toString();
        System.out.println(val);
        Assert.assertNotNull(val);
    }

    @Test
    public void testEquality() {
        boolean result = false;
        GeneralCodes o1 = new GeneralCodes();
        GeneralCodes o2 = null;

        result = o1.equals(o2);
        Assert.assertFalse(result);

        Date dt = new Date();
        o1.setCodeGrpId(500);
        o1.setCodeId(200);
        o1.setLongdesc("Test General Code");
        o1.setShortdesc("code");
        o2 = new GeneralCodes();

        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setCodeGrpId(500);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setCodeId(200);
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setLongdesc("Test General Code");
        result = o1.equals(o2);
        Assert.assertFalse(result);
        o2.setShortdesc("code");
        result = o1.equals(o2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCode() {
        GeneralCodes o1 = new GeneralCodes();
        o1.setCodeGrpId(500);
        o1.setCodeId(200);
        o1.setLongdesc("Test General Code");
        o1.setShortdesc("code");

        GeneralCodes o2 = new GeneralCodes();
        o2.setCodeGrpId(500);
        o2.setCodeId(200);
        o2.setLongdesc("Test General Code");
        o2.setShortdesc("code");
        Assert.assertTrue(o1.equals(o2) && o2.equals(o1));
        Assert.assertEquals(o1.hashCode(), o2.hashCode());
    }
}
